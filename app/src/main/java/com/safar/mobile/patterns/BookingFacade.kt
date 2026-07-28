package com.safar.mobile.patterns

/**
 * 3. FACADE DESIGN PATTERN
 * High-level facade hiding subsystem complexities (Inventory, Payment, Notification).
 */
class InventorySubsystem {
    fun checkAvailability(itemId: String): Boolean = itemId.isNotBlank() && !itemId.startsWith("FULL")
}

class PaymentSubsystem {
    fun processPayment(account: String, amount: Double): Boolean = account.isNotBlank() && amount > 0.0
}

class NotificationSubsystem {
    fun sendConfirmation(email: String, bookingId: String): String = "Confirmation sent to $email for $bookingId"
}

data class TripBookingRequest(
    val flightId: String,
    val hotelId: String,
    val customerEmail: String,
    val paymentAccount: String,
    val totalAmount: Double
)

data class TripBookingResponse(
    val isSuccess: Boolean,
    val bookingReference: String?,
    val message: String
)

class SafarBookingFacade(
    private val inventory: InventorySubsystem = InventorySubsystem(),
    private val payment: PaymentSubsystem = PaymentSubsystem(),
    private val notification: NotificationSubsystem = NotificationSubsystem()
) {
    fun bookCompleteTrip(request: TripBookingRequest): TripBookingResponse {
        if (!inventory.checkAvailability(request.flightId)) {
            return TripBookingResponse(false, null, "Flight unavailable")
        }
        if (!inventory.checkAvailability(request.hotelId)) {
            return TripBookingResponse(false, null, "Hotel unavailable")
        }
        val paymentSuccess = payment.processPayment(request.paymentAccount, request.totalAmount)
        if (!paymentSuccess) {
            return TripBookingResponse(false, null, "Payment failed")
        }

        val bookingRef = "SAFAR-${System.currentTimeMillis() % 100000}"
        notification.sendConfirmation(request.customerEmail, bookingRef)

        return TripBookingResponse(true, bookingRef, "Booking completed successfully")
    }
}
