package com.ezbytes.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BalanceController {

    @GetMapping("/myBalance")
    fun getBalanceDetails(): String {
        return "My Balance"
    }
}