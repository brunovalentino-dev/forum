package io.bvalentino.forum.service

import io.bvalentino.forum.config.security.APIUserDetails
import io.bvalentino.forum.exception.NotFoundException
import io.bvalentino.forum.model.ForumUser
import io.bvalentino.forum.repository.UserRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) : UserDetailsService {

    fun findUser(id: Long): ForumUser {
        return try {
            userRepository.getReferenceById(id)
        }
        catch (e: EntityNotFoundException) {
            throw NotFoundException("User not found in database! Please verify input data.")
        }
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val foundUser = userRepository.findByEmail(username)
            ?: throw NotFoundException("User not found in database! Please verify input data.")

        return APIUserDetails(foundUser)
    }

}
