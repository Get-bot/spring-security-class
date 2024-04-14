package com.ezbytes.dto

import com.ezbytes.model.Authorities
import com.ezbytes.model.User


data class UserRegister(
    var username: String,
    var password: String
)

fun UserRegister.toEntity() = User (
    username = this.username,
    password = this.password,
    enabled = true,
    authorities = mutableListOf(Authorities(username = this.username, authority = "USER"))
)
