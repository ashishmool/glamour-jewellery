package com.glamourjewellery.glamour_jewellery.entity;

import com.glamourjewellery.glamour_jewellery.enums.BookingEnum;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bookings")
@Getter
@Setter
public class Booking {

    @Id
    @SequenceGenerator(name = "booking_seq_gen", sequenceName = "booking_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "booking_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long purchaseId;

    @Column(name = "purchase_date", nullable = false)
    private Date purchaseDate;


    @Column(name = "product_id")
    private Long productId;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "user_id")
    private Long userId;


//    @ManyToOne
//    @JoinColumn(name = "product_id", nullable = false)
//    private Product product;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private SystemUser user;



    @Column(name = "quantity_persons", nullable = false)
    private Integer quantityPersons;

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private BookingEnum paymentStatus;
}
