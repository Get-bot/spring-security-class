package com.ezbytes.controller

import com.ezbytes.dto.UserRegister
import com.ezbytes.dto.toEntity
import com.ezbytes.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(
    private val userRepository: UserRepository
) {

    @PostMapping("/register")
    fun registerUser(@RequestBody userRegister: UserRegister) : ResponseEntity<String> {
        try {
            userRepository.save(userRegister.toEntity())
            return ResponseEntity.ok("User registered successfully")
        } catch (e: Exception) {
            throw Exception("User already exists")
        }
    }



}