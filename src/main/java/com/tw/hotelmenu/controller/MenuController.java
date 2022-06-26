package com.tw.hotelmenu.controller;


import com.tw.hotelmenu.model.Item;
import com.tw.hotelmenu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @ResponseStatus(HttpStatus.CREATED)
    public Item  addItem(@RequestParam String name, @RequestParam double price) {

        Item item = new Item(name, price);
        return menuService.addItem(item);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestParam String name, @RequestParam double price) {

       return menuService.findById(id)
               .map( oldItem-> {
                   oldItem.setId(id);
                   oldItem.setName(name);
                   oldItem.setPrice(price);

                   Item newItem = menuService.addItem(oldItem);
                   return new ResponseEntity<>(newItem, HttpStatus.OK);
               })
               .orElseGet(() -> ResponseEntity.notFound().build());
    }

   @DeleteMapping("/remove/{id}")
    public ResponseEntity<Item> removeItem(@PathVariable Long id){
        if(!menuService.findById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
       menuService.delete(id);
       return new ResponseEntity<>(HttpStatus.ACCEPTED);
   }

}
