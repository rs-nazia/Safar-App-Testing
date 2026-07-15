package com.safar.backend.model

import jakarta.persistence.*

@Entity
data class Destination(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val title: String,
    val location: String,
    @Column(length = 2000)
    val description: String, // Increased length for detailed descriptions
    val imageUrl: String,
    val price: String? = null,
    val duration: String? = null,
    @Enumerated(EnumType.STRING)
    val type: DestinationType = DestinationType.TRENDING
)

enum class DestinationType {
    COMMUNITY, TRENDING, POPULAR
}
