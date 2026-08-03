package com.safar.mobile

import com.safar.mobile.patterns.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

/**
 * 1. MOCKITO UNIT TESTING
 * Demonstrates Mockito mocking of subsystems to test SafarBookingFacade in isolation.
 */
class MockitoBookingTest {

    private lateinit var mockInventory: InventorySubsystem
    private lateinit var mockPayment: PaymentSubsystem
    private lateinit var mockNotification: NotificationSubsystem
    private lateinit var facade: SafarBookingFacade

    @Before
    fun setUp() {
        mockInventory = mock()
        mockPayment = mock()
        mockNotification = mock()

        facade = SafarBookingFacade(mockInventory, mockPayment, mockNotification)
    }

    @Test
    fun testSuccessfulTripBooking_usingMockito() {
        // Arrange
        whenever(mockInventory.checkAvailability("FL-101")).thenReturn(true)
        whenever(mockInventory.checkAvailability("HT-202")).thenReturn(true)
        whenever(mockPayment.processPayment(any(), any())).thenReturn(true)
        whenever(mockNotification.sendConfirmation(any(), any())).thenReturn("OK")

        val request = TripBookingRequest(
            flightId = "FL-101",
            hotelId = "HT-202",
            customerEmail = "test@safar.com",
            paymentAccount = "01700000000",
            totalAmount = 450.0
        )

        // Act
        val response = facade.bookCompleteTrip(request)

        // Assert
        assertTrue(response.isSuccess)
        assertNotNull(response.bookingReference)
        assertEquals("Booking completed successfully", response.message)

        // Verify Mock Interaction Call Counts
        verify(mockInventory, times(1)).checkAvailability("FL-101")
        verify(mockInventory, times(1)).checkAvailability("HT-202")
        verify(mockPayment, times(1)).processPayment("01700000000", 450.0)
        verify(mockNotification, times(1)).sendConfirmation(any(), any())
    }

    @Test
    fun testFailedPayment_usingMockito() {
        // Arrange
        whenever(mockInventory.checkAvailability(any())).thenReturn(true)
        whenever(mockPayment.processPayment(any(), any())).thenReturn(false)

        val request = TripBookingRequest(
            flightId = "FL-101",
            hotelId = "HT-202",
            customerEmail = "test@safar.com",
            paymentAccount = "01700000000",
            totalAmount = 450.0
        )

        // Act
        val response = facade.bookCompleteTrip(request)

        // Assert
        assertFalse(response.isSuccess)
        assertEquals("Payment failed", response.message)
        verify(mockNotification, never()).sendConfirmation(any(), any())
    }
}
