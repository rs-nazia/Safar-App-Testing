package com.safar.backend.controller

import com.safar.backend.model.UserRecord
import com.safar.backend.repository.UserRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = ["*"])
class UserController(private val repository: UserRepository) {

    @GetMapping("/{email}")
    fun getByEmail(@PathVariable email: String): UserRecord? = repository.findByEmail(email)

    @PutMapping("/update")
    fun updateProfile(@RequestBody user: UserRecord): UserRecord {
        val existing = repository.findByEmail(user.email) ?: return repository.save(user)
        existing.name = user.name
        existing.phone = user.phone
        existing.profileImage = user.profileImage
        return repository.save(existing)
    }
}
