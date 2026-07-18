package com.safar.mobile.patterns

/**
 * 5. ADAPTER DESIGN PATTERN
 * Adapts legacy or third-party payment gateway APIs to unified PaymentProcessor interface.
 */
interface PaymentProcessor {
    fun executePayment(accountNumber: String, amountInBdt: Double): PaymentResult
}

data class PaymentResult(val success: Boolean, val transactionId: String, val provider: String)

// Third-party bKash API (incompatible method names)
class BKashLegacyApi {
    fun sendMoneyViaBKash(phoneNo: String, bdt: Double): String {
        return if (phoneNo.length == 11) "BKASH-TXN-${System.currentTimeMillis() % 10000}" else "ERROR"
    }
}

// Third-party Nagad API (incompatible signature)
class NagadLegacyApi {
    fun makeNagadTransfer(account: String, sum: Double): Boolean {
        return account.startsWith("01") && sum > 0
    }
}

// Adapter for bKash
class BKashPaymentAdapter(private val bKashApi: BKashLegacyApi = BKashLegacyApi()) : PaymentProcessor {
    override fun executePayment(accountNumber: String, amountInBdt: Double): PaymentResult {
        val txn = bKashApi.sendMoneyViaBKash(accountNumber, amountInBdt)
        val success = txn != "ERROR"
        return PaymentResult(success, txn, "bKash")
    }
}

// Adapter for Nagad
class NagadPaymentAdapter(private val nagadApi: NagadLegacyApi = NagadLegacyApi()) : PaymentProcessor {
    override fun executePayment(accountNumber: String, amountInBdt: Double): PaymentResult {
        val success = nagadApi.makeNagadTransfer(accountNumber, amountInBdt)
        val txn = if (success) "NAGAD-TXN-${System.currentTimeMillis() % 10000}" else "FAILED"
        return PaymentResult(success, txn, "Nagad")
    }
}
