package com.safar.mobile

import com.safar.mobile.patterns.*
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * 2. PARAMETERIZED TESTING
 * Executes automated tests against multiple input dataset parameters.
 */
@RunWith(Parameterized::class)
class ParameterizedPricingTest(
    private val rawAmount: Double,
    private val discountPct: Double,
    private val expectedPrice: Double
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "Test index {index}: rawAmount={0}, discount={1}%, expected={2}")
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(100.0, 0.0, 100.0),    // 0% discount
                arrayOf(100.0, 10.0, 90.0),    // 10% discount
                arrayOf(250.0, 20.0, 200.0),   // 20% discount
                arrayOf(500.0, 50.0, 250.0),   // 50% discount
                arrayOf(0.0, 15.0, 0.0)        // $0 base price
            )
        }
    }

    @Test
    fun testSeasonalDiscountCalculation() {
        val strategy = SeasonalDiscountStrategy(discountPct)
        val context = PriceCalculatorContext(strategy)

        val actualPrice = context.computeFinalPrice(rawAmount)

        assertEquals(expectedPrice, actualPrice, 0.001)
    }
}
