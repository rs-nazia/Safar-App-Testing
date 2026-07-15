package com.safar.backend.model

import jakarta.persistence.*

@Entity
data class UserRecord(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    var name: String,
    var email: String,
    var phone: String? = null,
    var profileImage: String? = null,
    val isAdmin: Boolean = false
)
