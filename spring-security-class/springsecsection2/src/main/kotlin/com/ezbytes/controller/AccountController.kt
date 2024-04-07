package com.ezbytes.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController {

    @GetMapping("/myAccount")
    fun getAccountDetails(): String {
        return "My Account"
    }

}