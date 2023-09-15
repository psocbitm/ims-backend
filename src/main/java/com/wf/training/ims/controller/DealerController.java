package com.wf.training.ims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.wf.training.ims.exception.ResourceNotFoundException;
import com.wf.training.ims.model.Address;
import com.wf.training.ims.model.Dealer;
import com.wf.training.ims.service.DealerService;

@RestController
@RequestMapping(value="/api")
@CrossOrigin(origins = "http://localhost:3000") // Replace with your frontend URL
public class DealerController {

    @Autowired
    private DealerService dservice;

    @PostMapping("/register")
    public ResponseEntity<String> createDealer(@Validated @RequestBody Dealer dealer) {
        Address address = dealer.getAddress();
        address.setDealer(dealer);
        dealer.setAddress(address);

        Dealer registeredDealer = dservice.registerDealer(dealer);
        if (registeredDealer != null) {
            return ResponseEntity.ok("Registration successful");
        } else {
            return ResponseEntity.badRequest().body("Registration failed");
        }
    }

    @PostMapping("/login")
    public Boolean loginDealer(@Validated @RequestBody Dealer dealer) throws ResourceNotFoundException {
        String email = dealer.getEmail();
        String password = dealer.getPassword();

        Dealer d = dservice.loginDealer(email).orElseThrow(() ->
                new ResourceNotFoundException("Dealer not found for this id :: "));

        return email.equals(d.getEmail()) && password.equals(d.getPassword());
    }
}
