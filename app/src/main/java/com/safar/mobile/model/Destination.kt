package com.safar.mobile.model

data class Destination(
    val id: Int,
    val title: String,
    val location: String,
    val description: String,
    val imageUrl: String,
    val price: String? = null,
    val duration: String? = null,
    val type: DestinationType = DestinationType.TRENDING,
    // Rich Details for Tours
    val itinerary: Map<String, List<String>>? = null, // "Day 1" -> ["Arrive", "Visit..."]
    val inclusions: List<String>? = null,
    val exclusions: List<String>? = null,
    val hotelName: String? = null,
    val hotelGrade: String? = null, // e.g. "3 Star"
    val category: String? = null, // Nature, Honeymoon, etc.
    val rating: Float = 4.5f
)

enum class DestinationType {
    COMMUNITY, TRENDING, POPULAR, SYLHET_PACKAGE, INTERNATIONAL
}
