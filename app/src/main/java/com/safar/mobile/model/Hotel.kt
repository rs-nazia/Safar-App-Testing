package com.safar.mobile.model

data class Hotel(
    val id: String,
    val name: String,
    val location: String,
    val pricePerNight: String,
    val rating: Float,
    val imageUrl: String,
    val facilities: List<String>,
    val description: String? = null,
    val grade: String? = null // e.g. "5 Star"
)
