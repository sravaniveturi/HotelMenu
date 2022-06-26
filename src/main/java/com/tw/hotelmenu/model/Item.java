package com.tw.hotelmenu.model;


import com.tw.hotelmenu.exception.InvalidItemFieldsException;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "menu_Items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name", nullable = false, unique = true)
    private String name;

    @Column(name = "item_price")
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void setName(String name) throws InvalidItemFieldsException{
        if(name == null)
            throw new InvalidItemFieldsException();
        this.name = name;
    }

    public void setPrice(double price) throws  InvalidItemFieldsException{
        if(price == 0)
            throw  new InvalidItemFieldsException();
        this.price = price;
    }

    public void updateWith(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

