package com.kousenit.astro.services;

import com.kousenit.astro.json.AstroResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest
class AstroGatewayTest {
    @Autowired
    private AstroGateway gateway;

    @Test
    void getAstronautResponse() {
        ResponseEntity<AstroResponse> response = gateway.getAstronautResponse();
        assertNotNull(response);
        if (response.getStatusCode() == OK) {
            AstroResponse data = response.getBody();
            assertThat(data).isNotNull();
            assertAll("astronauts",
                    () -> assertThat(data.message()).isEqualTo("success"),
                    () -> assertThat(data.number()).isGreaterThanOrEqualTo(0),
                    () -> assertThat(data.people()).hasSize(data.number()));
            System.out.println("There are " + data.number() + " astronauts in space");
        } else {
            assertThat(response).isNotNull();
        }
    }
}