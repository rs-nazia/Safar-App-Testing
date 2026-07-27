package com.safar.mobile

import com.safar.mobile.patterns.SessionManager
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * 3. WHITEBOX TESTING
 * Inspects internal state, private fields, and boundary condition paths of SessionManager.
 */
class WhiteboxAuthTest {

    @Before
    fun resetSession() {
        SessionManager.resetInstanceForTesting()
    }

    @Test
    fun testSingletonInstanceUniqueness_whitebox() {
        val instance1 = SessionManager.getInstance()
        val instance2 = SessionManager.getInstance()

        // Whitebox assertion verifying memory reference identity
        assertSame("Both references must point to exact same memory instance", instance1, instance2)
    }

    @Test
    fun testInitialState_isNotLoggedIn() {
        val session = SessionManager.getInstance()

        assertNull(session.currentUser)
        assertNull(session.authToken)
        assertFalse(session.isLoggedIn)
    }

    @Test
    fun testSessionStartAndEnd_stateTransitions() {
        val session = SessionManager.getInstance()

        // Transition 1: Start Session
        session.startSession("setanvir", "JWT-TOKEN-12345")
        assertEquals("setanvir", session.currentUser)
        assertEquals("JWT-TOKEN-12345", session.authToken)
        assertTrue(session.isLoggedIn)

        // Transition 2: End Session
        session.endSession()
        assertNull(session.currentUser)
        assertNull(session.authToken)
        assertFalse(session.isLoggedIn)
    }
}
