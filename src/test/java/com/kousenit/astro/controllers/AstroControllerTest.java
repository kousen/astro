package com.kousenit.astro.controllers;

import com.kousenit.astro.entities.CraftAndNumber;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AstroControllerTest {
    @Autowired
    private WebTestClient client;

    @Test
    void today() {
        client.get().uri("/astro/")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(CraftAndNumber.class)
                .consumeWith(System.out::println);
    }

    @Test
    void day_that_works() {
        client.get().uri("/astro/2022-12-16")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(CraftAndNumber.class)
                .consumeWith(System.out::println);
    }

    @Test
    void day_that_does_not_work() {
        client.get().uri("/astro/1969-07-20")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(CraftAndNumber.class)
                .hasSize(0);
    }

}