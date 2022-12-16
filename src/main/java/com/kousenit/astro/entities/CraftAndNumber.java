package com.kousenit.astro.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class CraftAndNumber {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private LocalDate date;

    @NotBlank(message = "Craft name is required")
    private String craft;

    @Positive(message = "Number of astronauts must be positive")
    private int numberOfAstronauts;

    public CraftAndNumber() {}

    public CraftAndNumber(LocalDate date, String craft, int numberOfAstronauts) {
        this.date = date;
        this.craft = craft;
        this.numberOfAstronauts = numberOfAstronauts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCraft() {
        return craft;
    }

    public void setCraft(String craft) {
        this.craft = craft;
    }

    public int getNumberOfAstronauts() {
        return numberOfAstronauts;
    }

    public void setNumberOfAstronauts(int numberOfAstronauts) {
        this.numberOfAstronauts = numberOfAstronauts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CraftAndNumber that = (CraftAndNumber) o;
        return numberOfAstronauts == that.numberOfAstronauts && Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(craft, that.craft);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, craft, numberOfAstronauts);
    }

    @Override
    public String toString() {
        return "CraftAndNumber{" +
                "id=" + id +
                ", date=" + date +
                ", craft='" + craft + '\'' +
                ", numberOfAstronauts=" + numberOfAstronauts +
                '}';
    }
}
