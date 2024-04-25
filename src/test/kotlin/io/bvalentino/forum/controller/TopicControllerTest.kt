package io.bvalentino.forum.controller

import io.bvalentino.forum.config.DatabaseContainerConfig
import io.bvalentino.forum.config.security.JWTUtil
import io.bvalentino.forum.config.security.SecurityConfig
import io.bvalentino.forum.model.Role
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@Import(SecurityConfig::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TopicControllerTest : DatabaseContainerConfig() {

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @Autowired
    private lateinit var jwtUtil: JWTUtil

    private lateinit var mockMvc: MockMvc

    private var token: String? = null

    companion object {

        private const val RESOURCE = "/topics"
        private const val RESOURCE_ID = RESOURCE.plus("/%s")

    }

    @BeforeEach
    fun setup() {
        token = this.getToken()

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply<DefaultMockMvcBuilder>(SecurityMockMvcConfigurers.springSecurity())
            .build()
    }

    @Test
    fun `Should return status code 400 when retrieving topics without token`() {
        mockMvc.get(RESOURCE) {
            headers { token?.let { this.setBearerAuth("") } }
        }
        .andExpect { status { is4xxClientError() } }
    }

    @Test
    fun `Should return status code 200 when retrieving topics with token`() {
        mockMvc.get(RESOURCE) {
            headers { token?.let { this.setBearerAuth(it) } }
        }
        .andExpect { status { is2xxSuccessful() } }
    }

    @Test
    fun `Should return status code 200 when retrieving topics by its ID with token`() {
        mockMvc.get(RESOURCE_ID.format("1")) {
            headers { token?.let { this.setBearerAuth(it) } }
        }
        .andExpect { status { is2xxSuccessful() } }
    }

    private fun getToken(): String? {
        return jwtUtil.generateToken("ana@email.com", mutableListOf(Role(name = "READ_WRITE", id = 1)))
    }

}