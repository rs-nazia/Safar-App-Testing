package com.safar.mobile.patterns

/**
 * 7. COMPOSITE DESIGN PATTERN
 * Enables individual tour activities and combined tour bundles to be treated uniformly.
 */
interface TourPackageComponent {
    val title: String
    fun getCost(): Double
    fun getDetails(): String
}

class SingleActivity(
    override val title: String,
    private val price: Double
) : TourPackageComponent {
    override fun getCost(): Double = price
    override fun getDetails(): String = "$title ($$price)"
}

class BundleTourPackage(
    override val title: String,
    private val packageDiscountPercentage: Double = 5.0
) : TourPackageComponent {
    private val components = mutableListOf<TourPackageComponent>()

    fun add(component: TourPackageComponent) {
        components.add(component)
    }

    fun remove(component: TourPackageComponent) {
        components.remove(component)
    }

    fun getChildren(): List<TourPackageComponent> = components

    override fun getCost(): Double {
        val totalRaw = components.sumOf { it.getCost() }
        return totalRaw * (1.0 - packageDiscountPercentage / 100.0)
    }

    override fun getDetails(): String {
        val childrenDetails = components.joinToString { it.getDetails() }
        return "$title [Includes: $childrenDetails] (Total Package: $$${getCost()})"
    }
}
