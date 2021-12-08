package com.dguzowski.currency.exchange

import com.dguzowski.currency.exchange.controller.AccountController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class ApplicationContextTest extends Specification {

    @Autowired(required = false)
    private AccountController accountController

    def "when context is loaded then all expected beans are created"() {
        expect: "the WebController is created"
        accountController
    }
}
