package com.talentsprint.cycleshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talentsprint.cycleshop.dto.CycleJsonInputIdCount;
import com.talentsprint.cycleshop.entity.Cycle;
import com.talentsprint.cycleshop.exception.CycleNotFoundException;
import com.talentsprint.cycleshop.exception.InsufficientStockException;
import com.talentsprint.cycleshop.service.CycleService;

@RestController
@RequestMapping("/api/cycles")
public class CycleRestController {

    
    @Autowired
    private CycleService cycleService;


    @PostMapping("/{id}/borrow")
    public ResponseEntity<String> borrowCycle(
    		@RequestBody CycleJsonInputIdCount IdCount) {
    	System.out.println("In Post");
        try {
            cycleService.borrowCycle(IdCount.getId(), IdCount.getCount());
            return ResponseEntity.ok("Cycle borrowed successfully.");
        } catch (CycleNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (InsufficientStockException e) {
            return ResponseEntity.badRequest().body("Insufficient stock.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<String> returnCycle(
    		@RequestBody CycleJsonInputIdCount IdCount) {
        try {
            cycleService.returnCycle(IdCount.getId(), IdCount.getCount());
            return ResponseEntity.ok("Cycle returned successfully.");
        } catch (CycleNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    @PostMapping("/{id}/restock")
    public ResponseEntity<String> restockCycle(
    		@RequestBody CycleJsonInputIdCount IdCount) {
        try {
            cycleService.restockBy(IdCount.getId(), IdCount.getCount());
            return ResponseEntity.ok("Cycle restocked successfully.");
        } catch (CycleNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }
    
    @GetMapping("/list-data")
    public ResponseEntity<List<Cycle>> listAvailableCycles(Authentication authentication) {
    	Jwt jwt = (Jwt) authentication.getPrincipal();
        System.out.println(jwt.getClaimAsString("scope"));
    	return ResponseEntity.ok(cycleService.listAvailableCycles());
    }
}
