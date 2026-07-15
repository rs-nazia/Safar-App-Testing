package com.safar.mobile.model

data class Flight(
    val id: String,
    val airline: String,
    val airlineLogoUrl: String, // Placeholder for URL or resource ID logic
    val from: String,
    val to: String,
    val departureTime: String,
    val arrivalTime: String,
    val duration: String,
    val price: Double
)
