package com.tw.hotelmenu.controller;


import com.tw.hotelmenu.model.Item;
import com.tw.hotelmenu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping("/index")
    public List<Item> getItems() {
       return menuService.getAllItems();
    }

}
