package com.kousenit.astro.dao;

import com.kousenit.astro.entities.CraftAndNumber;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface CraftAndNumberDao
    extends ListCrudRepository<CraftAndNumber, Integer> {

    List<CraftAndNumber> findByDate(LocalDate date);
}
