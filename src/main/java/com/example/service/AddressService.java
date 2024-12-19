package com.example.service;

import com.example.domain.address.Address;
import com.example.domain.address.AddressRequestDTO;
import com.example.domain.event.Event;
import com.example.domain.event.EventRequestDTO;
import com.example.repositories.AddressRepository;
import com.example.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    EventRepository eventRepository;


    public Address createAddress(AddressRequestDTO addressRequestDTO) {
        Event event = eventRepository.findById(addressRequestDTO.event()).orElseThrow(() ->
                new RuntimeException("Event not found"));


        Address address = new Address(addressRequestDTO.city(), addressRequestDTO.uf(), event);
        addressRepository.save(address);
        return address;


    }


}
