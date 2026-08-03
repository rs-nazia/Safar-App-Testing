package com.safar.mobile

import com.safar.mobile.patterns.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * 5. CAUSE-EFFECT GRAPHING BASED TESTING
 * Tests decision table matrix created from boolean cause/effect conditions.
 *
 * Cause C1: Account starts with "01" (valid BD mobile)
 * Cause C2: Account length is 11 digits
 * Cause C3: Amount > 0
 *
 * Effect E1: bKash payment succeeds
 * Effect E2: Nagad payment succeeds
 * Effect E3: Payment rejected
 */
class CauseEffectDecisionTest {

    @Test
    fun testCauseEffect_C1_C2_C3_True_bKashSuccess() {
        // Cause: C1=True, C2=True, C3=True -> Effect: E1=Success
        val adapter = BKashPaymentAdapter()
        val result = adapter.executePayment("01711223344", 500.0)

        assertTrue(result.success)
        assertEquals("bKash", result.provider)
        assertTrue(result.transactionId.startsWith("BKASH-TXN-"))
    }

    @Test
    fun testCauseEffect_C2_False_bKashFailure() {
        // Cause: C2=False (Length != 11) -> Effect: E3=Rejected
        val adapter = BKashPaymentAdapter()
        val result = adapter.executePayment("01711223", 500.0)

        assertFalse(result.success)
        assertEquals("ERROR", result.transactionId)
    }

    @Test
    fun testCauseEffect_C1_True_C3_True_NagadSuccess() {
        // Cause: C1=True, C3=True -> Effect: E2=Nagad Success
        val adapter = NagadPaymentAdapter()
        val result = adapter.executePayment("01899887766", 1200.0)

        assertTrue(result.success)
        assertEquals("Nagad", result.provider)
        assertTrue(result.transactionId.startsWith("NAGAD-TXN-"))
    }

    @Test
    fun testCauseEffect_C1_False_NagadFailure() {
        // Cause: C1=False (Does not start with "01") -> Effect: E3=Rejected
        val adapter = NagadPaymentAdapter()
        val result = adapter.executePayment("09999999999", 1200.0)

        assertFalse(result.success)
        assertEquals("FAILED", result.transactionId)
    }
}
