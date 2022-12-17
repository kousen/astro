package com.kousenit.astro.services;

public sealed interface Result<T>
    permits Success, Failure {

    static <T> Result<T> success(T value) {
        return new Success<>(value);
    }

    static <T> Result<T> failure(String message) {
        return new Failure<>(message);
    }
}

record Success<T>(T value) implements Result<T> {}

record Failure<T>(String message) implements Result<T> {}

