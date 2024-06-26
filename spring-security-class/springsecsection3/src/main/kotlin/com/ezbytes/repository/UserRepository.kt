package com.ezbytes.repository

import com.ezbytes.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository : JpaRepository<User, Int>{
    fun findByUsername(username: String?): Optional<User>
}