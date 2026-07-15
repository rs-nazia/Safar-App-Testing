package com.safar.backend.controller

import com.safar.backend.model.Destination
import com.safar.backend.model.DestinationType
import com.safar.backend.repository.DestinationRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/destinations")
@CrossOrigin(origins = ["*"]) // Allow unrestricted access for dev (emulator)
class DestinationController(private val repository: DestinationRepository) {

    @GetMapping
    fun getAll(): List<Destination> = repository.findAll()

    @GetMapping("/trending")
    fun getTrending(): List<Destination> = repository.findByType(DestinationType.TRENDING)

    @GetMapping("/community")
    fun getCommunity(): List<Destination> = repository.findByType(DestinationType.COMMUNITY)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): Destination? = repository.findById(id).orElse(null)
}
