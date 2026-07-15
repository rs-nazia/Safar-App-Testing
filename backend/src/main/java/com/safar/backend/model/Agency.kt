package com.safar.backend.model

import jakarta.persistence.*

@Entity
data class Agency(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val name: String,
    val location: String,
    val experience: String,
    val services: String,
    val countries: String,
    val rating: String,
    val verified: Boolean = false,
    val logo: String,
    val price: String,
    val regId: String,
    val address: String,
    val phone: String,
    val email: String
)
