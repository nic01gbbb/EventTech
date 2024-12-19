package com.example.controlers;


import com.example.domain.address.Address;
import com.example.domain.address.AddressRequestDTO;
import com.example.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/address")
public class AddressController {


    @Autowired
    AddressService addressService;


    @RequestMapping("/create")
    public ResponseEntity<String> createAddress(@RequestBody AddressRequestDTO addressRequestDTO) {
        try {
            addressService.createAddress(addressRequestDTO);
            return ResponseEntity.ok("Address created successfully");

        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }


    }


}
