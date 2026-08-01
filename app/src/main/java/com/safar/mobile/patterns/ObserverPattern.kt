package com.safar.mobile.patterns

/**
 * 4. OBSERVER DESIGN PATTERN
 * Subject/Observer interface allowing subscribers to observe booking status changes.
 */
interface BookingObserver {
    fun onBookingStatusChanged(bookingId: String, status: String)
}

class BookingStatusSubject {
    private val observers = mutableListOf<BookingObserver>()
    var currentStatus: String = "INIT"
        private set

    fun registerObserver(observer: BookingObserver) {
        if (!observers.contains(observer)) {
            observers.add(observer)
        }
    }

    fun unregisterObserver(observer: BookingObserver) {
        observers.remove(observer)
    }

    fun updateStatus(bookingId: String, newStatus: String) {
        currentStatus = newStatus
        notifyObservers(bookingId, newStatus)
    }

    private fun notifyObservers(bookingId: String, status: String) {
        for (observer in observers) {
            observer.onBookingStatusChanged(bookingId, status)
        }
    }

    fun observerCount(): Int = observers.size
}

class UiNotificationObserver : BookingObserver {
    var lastNotification: String? = null
        private set

    override fun onBookingStatusChanged(bookingId: String, status: String) {
        lastNotification = "UI Alert: Booking $bookingId status is now $status"
    }
}

class AnalyticsObserver : BookingObserver {
    var totalEventCount: Int = 0
        private set

    override fun onBookingStatusChanged(bookingId: String, status: String) {
        totalEventCount++
    }
}
