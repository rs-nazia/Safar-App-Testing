package com.safar.mobile.patterns

/**
 * 6. STRATEGY DESIGN PATTERN
 * Interface and strategy implementations for dynamic price and discount calculations.
 */
interface PricingStrategy {
    fun calculatePrice(rawAmount: Double): Double
}

class StandardPricingStrategy : PricingStrategy {
    override fun calculatePrice(rawAmount: Double): Double = rawAmount
}

class SeasonalDiscountStrategy(val discountPercentage: Double) : PricingStrategy {
    override fun calculatePrice(rawAmount: Double): Double {
        val discount = rawAmount * (discountPercentage / 100.0)
        return rawAmount - discount
    }
}

class VipMemberStrategy : PricingStrategy {
    override fun calculatePrice(rawAmount: Double): Double {
        // Flat 20% discount for VIP plus waived $10 booking fee
        return (rawAmount * 0.80).coerceAtLeast(0.0)
    }
}

class PriceCalculatorContext(private var strategy: PricingStrategy = StandardPricingStrategy()) {
    fun setStrategy(newStrategy: PricingStrategy) {
        this.strategy = newStrategy
    }

    fun computeFinalPrice(amount: Double): Double {
        require(amount >= 0.0) { "Price cannot be negative" }
        return strategy.calculatePrice(amount)
    }
}
