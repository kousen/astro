package com.kousenit.astro;

import com.kousenit.astro.services.AstroGateway;
import com.kousenit.astro.services.AstroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
public class AstroApplication {

    public static void main(String[] args) {
        SpringApplication.run(AstroApplication.class, args);
    }

    @Bean
    public AstroGateway astroGateway() {
        WebClient client = WebClient.create("http://api.open-notify.org");
        HttpServiceProxyFactory factory =
            HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
        return factory.createClient(AstroGateway.class);
    }

    @Bean
    public CommandLineRunner initialize(AstroService service) {
        return args -> service.dailyUpdate();
    }
}
