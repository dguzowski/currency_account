package com.dguzowski.currency.exchange.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@WebMvcTest
class AccountControllerTest extends Specification {

    @Autowired
    MockMvc mvc

    def "should return not found status if there is no account"() {
        expect: "status should be NOT_FOUND (404)"

        when:
        def result = mvc.perform(get("/api/accounts/${UUID.randomUUID()}"))

        then:
        result.andExpect(status().isNotFound())
    }
}
