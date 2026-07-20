package com.safar.mobile.patterns

/**
 * 2. FACTORY METHOD DESIGN PATTERN
 * Interface and concrete factory classes for generating different booking types.
 */
enum class BookingType {
    FLIGHT, HOTEL, TOUR
}

sealed class ServiceBooking(
    val id: String,
    val title: String,
    val basePrice: Double
) {
    abstract fun calculateFinalCost(taxRate: Double): Double
}

class FlightBooking(id: String, title: String, price: Double, val airline: String) : ServiceBooking(id, title, price) {
    override fun calculateFinalCost(taxRate: Double): Double = basePrice + (basePrice * taxRate) + 15.0 // $15 airport fee
}

class HotelBooking(id: String, title: String, price: Double, val nights: Int) : ServiceBooking(id, title, price) {
    override fun calculateFinalCost(taxRate: Double): Double = (basePrice * nights) * (1.0 + taxRate)
}

class TourBooking(id: String, title: String, price: Double, val groupSize: Int) : ServiceBooking(id, title, price) {
    override fun calculateFinalCost(taxRate: Double): Double {
        val discount = if (groupSize >= 4) 0.10 else 0.0
        val discountedBase = basePrice * (1 - discount)
        return (discountedBase * groupSize) * (1.0 + taxRate)
    }
}

object ServiceBookingFactory {
    fun createBooking(
        type: BookingType,
        id: String,
        title: String,
        price: Double,
        quantityOrOption: Int = 1
    ): ServiceBooking {
        return when (type) {
            BookingType.FLIGHT -> FlightBooking(id, title, price, airline = "Safar Airways")
            BookingType.HOTEL -> HotelBooking(id, title, price, nights = quantityOrOption)
            BookingType.TOUR -> TourBooking(id, title, price, groupSize = quantityOrOption)
        }
    }
}
