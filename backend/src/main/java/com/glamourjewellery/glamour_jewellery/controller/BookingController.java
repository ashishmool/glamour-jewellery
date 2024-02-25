package com.glamourjewellery.glamour_jewellery.controller;

import com.glamourjewellery.glamour_jewellery.entity.Booking;
import com.glamourjewellery.glamour_jewellery.pojo.BookingPojo;
import com.glamourjewellery.glamour_jewellery.enums.BookingEnum;
import com.glamourjewellery.glamour_jewellery.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/save")
    public Booking purchaseProduct(@RequestBody BookingPojo bookingPojo) {
        return bookingService.purchaseProduct(bookingPojo);
    }

    @PutMapping("/update/{purchaseId}")
    public Booking updatePurchase(@PathVariable Long purchaseId, @RequestBody BookingPojo bookingPojo) {
        return bookingService.updatePurchase(purchaseId, bookingPojo);
    }

    @DeleteMapping("/delete/{purchaseId}")
    public void deletePurchase(@PathVariable Long purchaseId) {
        bookingService.deletePurchase(purchaseId);
    }

    @GetMapping("/get/{purchaseId}")
    public Optional<Booking> getPurchaseById(@PathVariable Long purchaseId) {
        return bookingService.getPurchaseById(purchaseId);
    }

    @GetMapping("/getAll")
    public List<Booking> getAllPurchases() {
        return bookingService.getAllPurchases();
    }

    @GetMapping("/get-by-date")
    public List<Booking> getPurchasesByDate(@RequestParam("purchaseDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date purchaseDate) {
        return bookingService.getPurchasesByDate(purchaseDate);
    }

    @GetMapping("/get-by-payment-status")
    public List<Booking> getPurchasesByPaymentStatus(@RequestParam("paymentStatus") BookingEnum paymentStatus) {
        return bookingService.getPurchasesByPaymentStatus(paymentStatus);
    }


    @GetMapping("/getByUserId/{userId}") // Updated mapping to retrieve userId from path
    public List<Booking> getPurchasesByUserId(@PathVariable Long userId) { // Use @PathVariable to retrieve userId from path
        return bookingService.getPurchasesByUserId(userId);
    }
}
