package com.tw.hotelmenu.service;

import com.tw.hotelmenu.model.Item;
import com.tw.hotelmenu.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    public Optional<Item> findByName(String name) {
        return itemRepository.findByName(name);
    }

    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

}
