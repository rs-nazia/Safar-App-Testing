package com.safar.mobile

import com.safar.mobile.patterns.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

/**
 * 4. PATH TESTING (Basis Path / Cyclomatic Complexity Coverage)
 * Ensures 100% path coverage across all independent decision paths in TourBooking logic.
 *
 * Path 1: Group size < 4 (No discount branch executed)
 * Path 2: Group size >= 4 (Discount branch executed)
 * Path 3: Flight Unavailable branch in Facade
 * Path 4: Hotel Unavailable branch in Facade
 */
class PathCoverageBookingTest {

    @Test
    fun testPath1_GroupSizeLessThanFour_NoDiscount() {
        // Path 1: groupSize = 2 (< 4) -> discount = 0.0
        val tour = TourBooking("TR-1", "Cox's Bazar Tour", 100.0, groupSize = 2)
        // Cost = (100.0 * 2) * 1.10 (tax 10%) = 220.0
        val finalCost = tour.calculateFinalCost(0.10)

        assertEquals(220.0, finalCost, 0.001)
    }

    @Test
    fun testPath2_GroupSizeFourOrMore_WithDiscount() {
        // Path 2: groupSize = 4 (>= 4) -> discount = 0.10 (10% off base)
        // Discounted base = 100 * 0.90 = 90.0
        // Cost = (90.0 * 4) * 1.10 = 396.0
        val tour = TourBooking("TR-2", "Sylhet Green Tour", 100.0, groupSize = 4)
        val finalCost = tour.calculateFinalCost(0.10)

        assertEquals(396.0, finalCost, 0.001)
    }

    @Test
    fun testPath3_FacadeFlightUnavailable() {
        val facade = SafarBookingFacade()
        val request = TripBookingRequest(
            flightId = "FULL-FL-99", // Triggers Flight Unavailable branch
            hotelId = "HT-101",
            customerEmail = "path@safar.com",
            paymentAccount = "01800000000",
            totalAmount = 200.0
        )

        val result = facade.bookCompleteTrip(request)

        assertFalse(result.isSuccess)
        assertEquals("Flight unavailable", result.message)
    }

    @Test
    fun testPath4_FacadeHotelUnavailable() {
        val facade = SafarBookingFacade()
        val request = TripBookingRequest(
            flightId = "FL-101",
            hotelId = "FULL-HT-99", // Triggers Hotel Unavailable branch
            customerEmail = "path@safar.com",
            paymentAccount = "01800000000",
            totalAmount = 200.0
        )

        val result = facade.bookCompleteTrip(request)

        assertFalse(result.isSuccess)
        assertEquals("Hotel unavailable", result.message)
    }
}
