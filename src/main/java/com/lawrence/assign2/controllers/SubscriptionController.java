package com.lawrence.assign2.controllers;

import com.lawrence.assign2.dtos.SubscriptionDto;
import com.lawrence.assign2.services.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping("/subscription/{email}")
    public ResponseEntity getListOfSubscription (@PathVariable String email) {

        List<SubscriptionDto> list = subscriptionService.fetchListOfSubscription(email);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/subscription")
    public ResponseEntity subscribe(@RequestBody SubscriptionDto subscriptionDto) {
        subscriptionService.subscribe(subscriptionDto);
        return ResponseEntity.ok("Succeed!");
    }

    @PutMapping("/subscription")
    public ResponseEntity unsubscribe(@RequestBody SubscriptionDto subscriptionDto) {
        subscriptionService.unsubscribe(subscriptionDto);
        return ResponseEntity.ok("Deleted!");
    }
}
