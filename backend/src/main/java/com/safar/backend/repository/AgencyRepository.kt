package com.safar.backend.repository

import com.safar.backend.model.Agency
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AgencyRepository : JpaRepository<Agency, Int>
