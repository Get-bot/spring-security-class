package com.ezbytes.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class NoticesController {

    @GetMapping("/notices")
    fun getNotices(): String {
        return "Notices"
    }
}