package com.kousenit.astro.services;

import com.kousenit.astro.json.AstroResponse;
import org.springframework.web.service.annotation.GetExchange;

public interface AstroGateway {
    @GetExchange("/astros.json")
    AstroResponse getAstronautResponse();
}
