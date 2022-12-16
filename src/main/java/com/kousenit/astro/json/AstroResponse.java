package com.kousenit.astro.json;

import java.util.List;

public record AstroResponse(
        int number,
        String message,
        List<Assignment> people
) {
    public record Assignment(String craft, String name) {}
}
