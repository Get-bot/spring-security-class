package com.ezbytes.dto

import com.ezbytes.model.Authorities
import com.ezbytes.model.User


data class UserRegister(
    val username: String,
    val password: String
)

fun UserRegister.toEntity() = User (
    username = this.username,
    password = this.password,
    enabled = true,
    authorities = mutableListOf(Authorities(username = this.username, authority = "USER"))
)
