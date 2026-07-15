package com.safar.backend.repository

import com.safar.backend.model.UserRecord
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserRecord, Int> {
    fun findByEmail(email: String): UserRecord?
}
