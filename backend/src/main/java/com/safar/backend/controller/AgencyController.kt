package com.safar.backend.controller

import com.safar.backend.model.Agency
import com.safar.backend.repository.AgencyRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/agencies")
@CrossOrigin(origins = ["*"])
class AgencyController(private val repository: AgencyRepository) {

    @GetMapping
    fun getAll(): List<Agency> = repository.findAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): Agency? = repository.findById(id).orElse(null)

    @PostMapping("/register")
    fun register(@RequestBody agency: Agency): Agency = repository.save(agency)
}
