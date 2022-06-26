package com.tw.hotelmenu.service;

import com.tw.hotelmenu.model.Item;
import com.tw.hotelmenu.repository.ItemRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class MenuServiceTest {

    @MockBean
    ItemRepository itemRepository;

    @Autowired
    MenuService menuService;

    @Test
    void shouldReturnAllItemsWhenMenuIsNotEmpty(){
        List<Item> itemsSaved = Arrays.asList(new Item("Idly", 45), new Item("Chapati", 60));
        when(itemRepository.findAll()).thenReturn(itemsSaved);

        List<Item> itemsReturned = menuService.getAllItems();

        assertEquals(itemsSaved, itemsReturned);
    }

    @Test
    void shouldReturnEmptyWhenMenuIsEmpty(){
        List<Item> itemsSaved = Lists.newArrayList();
        when(itemRepository.findAll()).thenReturn(itemsSaved);

        List<Item> itemsReturned = menuService.getAllItems();

        assertEquals(itemsSaved, itemsReturned);
    }

    @Test
    void  shouldCreateAnItem() throws Exception {
        Item itemSaved = new Item("Idly", 45);
        when(itemRepository.save(any())).thenReturn(itemSaved);

        Item itemReturned = menuService.addItem(itemSaved);

        assertEquals(itemReturned , itemSaved);
    }

    @Test
    void shouldUpdateItem(){
        Item newItem = new Item("Idly", 60);
        when(itemRepository.save(any())).thenReturn(newItem);

        Item updatedItem = menuService.addItem(newItem);

        assertEquals(updatedItem, newItem);
    }

    @Test
    void shouldDeleteItem() throws Exception {
        Item newItem = new Item("Idly", 60);
        newItem.setId(1L);
        when(itemRepository.save(newItem)).thenReturn(newItem);

        menuService.addItem(newItem);
        menuService.delete(newItem.getId());

        assertFalse(menuService.findById(newItem.getId()).isPresent());

    }

}