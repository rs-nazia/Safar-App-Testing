package com.safar.mobile.patterns

/**
 * 1. SINGLETON DESIGN PATTERN
 * Thread-safe singleton for user authentication session management.
 */
class SessionManager private constructor() {

    var currentUser: String? = null
        private set
    var authToken: String? = null
        private set

    val isLoggedIn: Boolean
        get() = !authToken.isNullOrEmpty()

    fun startSession(username: String, token: String) {
        currentUser = username
        authToken = token
    }

    fun endSession() {
        currentUser = null
        authToken = null
    }

    companion object {
        @Volatile
        private var instance: SessionManager? = null

        fun getInstance(): SessionManager {
            return instance ?: synchronized(this) {
                instance ?: SessionManager().also { instance = it }
            }
        }

        fun resetInstanceForTesting() {
            instance = null
        }
    }
}
