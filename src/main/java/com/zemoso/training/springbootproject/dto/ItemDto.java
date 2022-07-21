package com.zemoso.training.springbootproject.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    private int id;

    @NotEmpty(message = "Item name Should not be Empty")
    private String itemName;


    @NotNull(message = "Item Price Should not be null")
    @Range(min = 100, message = "Item Price Should be greater than or equal to 100")
    private int itemPrice;

    @NotNull
    @Range(min = 0, message = "Quantity should be greater than or equal to 1")
    private int quantity;

    private String itemDescription;
}
