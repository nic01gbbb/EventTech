package com.example.domain.address;

import com.example.domain.event.Event;

import java.util.UUID;

public record AddressRequestDTO(String city, String uf, UUID event) {
}
