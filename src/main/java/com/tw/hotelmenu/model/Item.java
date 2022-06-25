package com.tw.hotelmenu.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "menu_Items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name", nullable = false)
    private String name;

    @Column(name = "item_price")
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void setName(String name) {
        if(name.equals(null))
            throw new NullPointerException("Item Name field cannot be null.");
        this.name = name;
    }

    public void setPrice(double price) {
        if(price == 0)
            throw  new NullPointerException("Item price cannot be empty.");
        this.price = price;
    }
}

