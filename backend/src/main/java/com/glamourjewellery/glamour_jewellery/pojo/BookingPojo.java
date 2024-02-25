package com.glamourjewellery.glamour_jewellery.pojo;

import com.glamourjewellery.glamour_jewellery.enums.BookingEnum;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class BookingPojo {

    private Long purchaseId;

    @NotNull(message = "Purchase date is required")
    private Date purchaseDate;

    @NotNull(message = "Product is required")
    private Long productId;

    @NotNull(message = "User is required")
    private Long userId;

    private Long categoryId;

    @NotNull(message = "Quantity of persons is required")
    private Integer quantityPersons;

    private Double totalAmount;

    @NotNull(message = "Payment status is required")
    private BookingEnum paymentStatus;
}
