package com.tw.hotelmenu.controller;

import com.tw.hotelmenu.model.Item;
import com.tw.hotelmenu.service.MenuService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class MenuControllerTest {

    @MockBean
    MenuService menuService;

    @Autowired
    MockMvc mockMVC;

    @Test
    void shouldThrowExceptionWnenMenuIsEmpty() throws Exception {
        List<Item> itemsSaved = Lists.newArrayList();
        when(menuService.getAllItems()).thenReturn(itemsSaved);

        mockMVC.perform(get("/menus/index"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void shouldGetAllItemsWhenMenuNotEmpty() throws Exception {
        List<Item> itemsSaved = Arrays.asList(new Item("Idly", 45), new Item("Chapati", 60));
        when(menuService.getAllItems()).thenReturn(itemsSaved);

        String jsonContent = "[{\"name\": \"Idly\", \"price\" : 45}, {\"name\": \"Chapati\", \"price\" : 60}]";
        mockMVC.perform(get("/menu/index"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonContent));

        verify(menuService, times(1)).getAllItems();
    }

    @Test
    void shouldCreateItem() throws Exception {
        Item itemSaved = new Item("Idly", 45);
        itemSaved.setId((1L));
        when(menuService.createItem(any())).thenReturn(itemSaved);

        String jsonContent = "[{\"id\": \"1L\",\"name\": \"Idly\", \"price\" : 45}]";
        mockMVC.perform(post("/menu/new")
                        .param("id", String.valueOf(1L))
                        .param("name","Idly")
                        .param("price", String.valueOf(45)))
                .andExpect(status().isOk())
                .andExpect(content().json(String.valueOf(itemSaved)));

        verify(menuService, times(1)).createItem(any());
    }


}