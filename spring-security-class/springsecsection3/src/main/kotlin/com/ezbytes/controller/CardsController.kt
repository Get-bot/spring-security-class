package com.ezbytes.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CardsController {

    @GetMapping("/myCards")
    fun getCardDetails(): String {
        return "My Cards"
    }

}