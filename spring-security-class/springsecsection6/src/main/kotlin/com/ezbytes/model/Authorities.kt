package com.ezbytes.model

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Authorities (
    @Id
    val username: String? = null,
    val authority: String? = null
)