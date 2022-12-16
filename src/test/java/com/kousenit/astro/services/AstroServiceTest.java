package com.kousenit.astro.services;

import com.kousenit.astro.entities.CraftAndNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class AstroServiceTest {
    @Autowired
    private AstroService service;

    private List<CraftAndNumber> saved;

    @BeforeEach
    void setUp() {
        List<CraftAndNumber> entries = List.of(
                new CraftAndNumber(
                        LocalDate.now(), "ISS", 3),
                new CraftAndNumber(
                        LocalDate.now(), "Hubble", 1));
        saved = service.saveAll(entries);
    }

    @Test
    void save() {
        CraftAndNumber b5 = service.save(new CraftAndNumber(
                LocalDate.now(), "Babylon 5", 3));
        assertThat(b5.getId()).isNotNull();
        System.out.println(b5);
    }

    @Test
    void saveAll() {
        assertThat(saved).hasSize(2);
    }

    @Test
    void findByDate() {
        List<CraftAndNumber> found = service.findByDate(
                LocalDate.now().toString());
        assertThat(found).contains(saved.get(0), saved.get(1));
    }

    @Test
    void findAll() {
        List<CraftAndNumber> found = service.findAll();
        assertThat(found).contains(saved.get(0), saved.get(1));
    }

    @Test
    void dailyUpdate() {
        List<CraftAndNumber> craftAndNumbers = service.dailyUpdate();
        assertThat(craftAndNumbers).allMatch(craftAndNumber ->
                craftAndNumber.getCraft().length() > 0 &&
                craftAndNumber.getNumberOfAstronauts() >= 0);
        craftAndNumbers.forEach(System.out::println);
    }
}