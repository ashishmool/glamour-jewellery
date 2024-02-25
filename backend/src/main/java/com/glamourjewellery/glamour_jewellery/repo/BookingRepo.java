package com.glamourjewellery.glamour_jewellery.repo;

import com.glamourjewellery.glamour_jewellery.entity.Booking;
import com.glamourjewellery.glamour_jewellery.enums.BookingEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Long> {
    List<Booking> findByPurchaseDate(Date purchaseDate);
    List<Booking> findByPaymentStatus(BookingEnum paymentStatus);
    List<Booking> findByUserId(Long userId);
}
