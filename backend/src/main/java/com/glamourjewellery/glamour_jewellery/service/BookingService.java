package com.glamourjewellery.glamour_jewellery.service;

import com.glamourjewellery.glamour_jewellery.entity.Booking;
import com.glamourjewellery.glamour_jewellery.enums.BookingEnum;
import com.glamourjewellery.glamour_jewellery.pojo.BookingPojo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BookingService {
    Booking purchaseProduct(BookingPojo bookingPojo);
    Booking updatePurchase(Long purchaseId, BookingPojo bookingPojo);
    void deletePurchase(Long purchaseId);
    Optional<Booking> getPurchaseById(Long purchaseId);
    List<Booking> getAllPurchases();
    List<Booking> getPurchasesByDate(Date purchaseDate);

    List<Booking> getPurchasesByUserId(Long userId);
    List<Booking> getPurchasesByPaymentStatus(BookingEnum paymentStatus);
}
