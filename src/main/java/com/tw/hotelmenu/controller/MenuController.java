package com.tw.hotelmenu.controller;


import com.tw.hotelmenu.model.Item;
import com.tw.hotelmenu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping("/index")
    public List<Item> getAllItems() {
       return menuService.getAllItems();
    }

    @PostMapping("/new")
    public Item createItem(@RequestParam String name, @RequestParam double price) throws Exception {
        Item item = new Item(name, price);
        return menuService.createItem(item);
    }

}
