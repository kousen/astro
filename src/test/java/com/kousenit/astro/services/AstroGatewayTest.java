package com.kousenit.astro.services;

import com.kousenit.astro.json.AstroResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class AstroGatewayTest {
    @Autowired
    private AstroGateway gateway;

    @Test
    void getAstronautResponse() {
        AstroResponse response = gateway.getAstronautResponse();
        assertNotNull(response);
        assertAll(
                () -> assertThat(response.number()).isGreaterThanOrEqualTo(0),
                () -> assertThat(response.message()).isEqualTo("success"),
                () -> assertThat(response.people()).hasSize(response.number())
        );
        System.out.println("There are " + response.number() + " astronauts in space");
    }
}