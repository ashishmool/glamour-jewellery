package com.glamourjewellery.glamour_jewellery.service.impl;

import com.glamourjewellery.glamour_jewellery.entity.Booking;
import com.glamourjewellery.glamour_jewellery.enums.BookingEnum;
import com.glamourjewellery.glamour_jewellery.pojo.BookingPojo;
import com.glamourjewellery.glamour_jewellery.repo.BookingRepo;
import com.glamourjewellery.glamour_jewellery.service.BookingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepo bookingRepo;

    @Override
    public Booking purchaseProduct(BookingPojo bookingPojo) {
        Booking booking = new Booking();
        booking.setPurchaseDate(new Date());
        booking.setProductId(bookingPojo.getProductId());
        booking.setUserId(bookingPojo.getUserId());
        booking.setCategoryId(bookingPojo.getCategoryId());
        booking.setQuantityPersons(bookingPojo.getQuantityPersons());
        booking.setPaymentStatus(BookingEnum.PENDING); // Default status
        booking.setTotalAmount(bookingPojo.getTotalAmount());

//        userPurchasedProduct.setTotalAmount(userPurchasedProductPojo.getProductPrice() * userPurchasedProductPojo.getQuantityPersons());


        return bookingRepo.save(booking);
    }

    @Override
    public Booking updatePurchase(Long purchaseId, BookingPojo bookingPojo) {
        Booking existingPurchase = bookingRepo.findById(purchaseId)
                .orElseThrow(() -> new EntityNotFoundException("Purchase not found with ID: " + purchaseId));

        existingPurchase.setPaymentStatus(bookingPojo.getPaymentStatus());

        return bookingRepo.save(existingPurchase);
    }

    @Override
    public void deletePurchase(Long purchaseId) {
        bookingRepo.deleteById(purchaseId);
    }

    @Override
    public Optional<Booking> getPurchaseById(Long purchaseId) {
        return bookingRepo.findById(purchaseId);
    }

    @Override
    public List<Booking> getAllPurchases() {
        return bookingRepo.findAll();
    }

    @Override
    public List<Booking> getPurchasesByDate(Date purchaseDate) {
        return bookingRepo.findByPurchaseDate(purchaseDate);
    }

    @Override
    public List<Booking> getPurchasesByUserId(Long userId){
        return bookingRepo.findByUserId(userId);}
    @Override
    public List<Booking> getPurchasesByPaymentStatus(BookingEnum paymentStatus) {
        return bookingRepo.findByPaymentStatus(paymentStatus);
    }
}
