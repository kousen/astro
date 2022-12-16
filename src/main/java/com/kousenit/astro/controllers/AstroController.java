package com.kousenit.astro.controllers;

import com.kousenit.astro.entities.CraftAndNumber;
import com.kousenit.astro.services.AstroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/astro")
public class AstroController {

    private final AstroService service;

    @Autowired
    public AstroController(AstroService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<CraftAndNumber> today() {
        return service.findByDate(LocalDate.now().toString());
    }

    @GetMapping("{date}")
    public List<CraftAndNumber> byDate(@PathVariable String date) {
        return service.findByDate(date);
    }
}
