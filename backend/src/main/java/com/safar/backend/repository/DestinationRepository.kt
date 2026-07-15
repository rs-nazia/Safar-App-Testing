package com.safar.backend.repository

import com.safar.backend.model.Destination
import com.safar.backend.model.DestinationType
import org.springframework.data.jpa.repository.JpaRepository

interface DestinationRepository : JpaRepository<Destination, Int> {
    fun findByType(type: DestinationType): List<Destination>
}
