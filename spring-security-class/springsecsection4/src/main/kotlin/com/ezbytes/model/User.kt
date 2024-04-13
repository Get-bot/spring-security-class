package com.ezbytes.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User (
    @Id
    val username: String? = null,
    val password: String,
    val enabled: Boolean,

    @OneToMany(fetch = FetchType.LAZY ,cascade = [CascadeType.ALL])
    @JoinColumn(name = "username")
    val authorities: MutableList<Authorities> = mutableListOf()
)