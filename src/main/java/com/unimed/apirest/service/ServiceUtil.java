package com.unimed.apirest.service;

import java.net.URI;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

public class ServiceUtil {
    static <T> URI resourceUri(T resourceId) {
        Objects.requireNonNull(resourceId);
        return Optional.of(resourceId)
                .map(id -> fromCurrentRequest().path("/{id}")
                        .buildAndExpand(id).toUri())
                .orElseThrow(IllegalArgumentException::new);
    }
}
