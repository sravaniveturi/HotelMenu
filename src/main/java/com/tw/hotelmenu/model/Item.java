package com.tw.hotelmenu.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
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

    public void setName(String name) {
        if(name.isEmpty())
            throw new NullPointerException("Item Name field cannot be null.");
        this.name = name;
    }

    public void setPrice(double price) {
        if(price == 0)
            throw  new NullPointerException("Item price cannot be empty.");
        this.price = price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Item{id=").append(id).append(",name=").append(name).append(",price=").append(price).append("}");
        return sb.toString();
    }
}

