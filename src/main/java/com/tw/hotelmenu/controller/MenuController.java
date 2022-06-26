package com.tw.hotelmenu.controller;


import com.tw.hotelmenu.exception.InvalidInputException;
import com.tw.hotelmenu.exception.ItemNotFoundException;
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
    public Item  addItem(@RequestParam String name, @RequestParam double price) {
        if(name == null || price <= 0){
            throw new InvalidInputException();
        }
        Item item = new Item(name, price);
        return menuService.save(item);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestParam String name, @RequestParam double price){

       return menuService.findById(id)
               .map( oldItem-> {
                  oldItem.updateWith(id,name,price);

                   Item newItem = menuService.save(oldItem);
                   return new ResponseEntity<>(newItem, HttpStatus.OK);
               })
               .orElseThrow(ItemNotFoundException::new);
    }

   @DeleteMapping("/remove/{id}")
    public ResponseEntity<Item> removeItem(@PathVariable Long id){
        if(!menuService.findById(id).isPresent()){
            throw new ItemNotFoundException();
        }
       menuService.delete(id);
       return new ResponseEntity<>(HttpStatus.ACCEPTED);
   }

}
