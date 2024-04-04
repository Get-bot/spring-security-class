package com.ezbytes.springsecuritybasic.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/welcome")
class WelcomeController {

    @GetMapping("")
    fun welcome(): String {
        return "Welcome to Spring Security"
    }

}