package com.zemoso.training.springbootproject.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "items")
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "item_name")
    @NotEmpty(message = "Item name Should not be Empty")
    private String itemName;

    @Column(name = "item_price")
    @NotNull(message = "Item Price Should not be null")
    @Range(min = 100, message = "Item Price Should be greater than or equal to 100")
    private int itemPrice;


    @Column(name = "quantity")
    @NotNull(message = "Item Price Should not be null")
    @Range(min = 0, message = "Quantity should be greater than or equal to 0")
    private int quantity;


    @Column(name = "item_description")
    private String itemDescription;

    public Item() {
    }

    public Item(int id, String itemName, int itemPrice, int quantity, String itemDescription) {
        this.id = id;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.itemDescription = itemDescription;
    }

}
