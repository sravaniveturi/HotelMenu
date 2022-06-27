package com.tw.hotelmenu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class MenuControllerTest {

    @MockBean
    MenuService menuService;

    @Autowired
    MockMvc mockMVC;

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
    void shouldShowEmptyMenuWhenMenuIsEmpty() throws Exception {
        List<Item> itemsSaved = Lists.newArrayList();
        when(menuService.getAllItems()).thenReturn(itemsSaved);

        mockMVC.perform(get("/menu/index"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void shouldCreateItem() throws Exception {
        Item itemSaved = new Item("Idly", 45);
        itemSaved.setId(1L);
        when(menuService.save(any())).thenReturn(itemSaved);

       mockMVC.perform(post("/menu/new")
                        .param("name", "Idly")
                        .param("price", String.valueOf(45)))
                .andExpect(status().isOk())
                .andExpect(content().json(asJson(itemSaved)));
    }

    @Test
    void cannotCreateItemWithNameAsNull() throws Exception {
        Item itemSaved = new Item(null, 45);
        itemSaved.setId(1L);
        when(menuService.save(any())).thenReturn(itemSaved);

        mockMVC.perform(post("/menu/new")
                .param("price", String.valueOf(45)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldUpdateItemPrice() throws Exception {
        Item initialItem = new Item("Idly", 45);
        initialItem.setId(1L);
        Item updatedItem = new Item(1L, "Idly", 60);
        when(menuService.findById(anyLong())).thenReturn(Optional.of(initialItem));
        when(menuService.save(any())).thenReturn(updatedItem);


        mockMVC.perform(put("/menu/edit/1")
                        .param("name", "Idly")
                        .param("price", String.valueOf(60)))
                .andExpect(status().isOk())
                .andExpect(content().json(asJson(updatedItem)));

    }

    @Test
    void cannotUpdateItemWithIdNotFound() throws Exception {

        mockMVC.perform(put("/menu/edit/1")
                        .param("name", "Idly")
                        .param("price", String.valueOf(60)))
                .andExpect(status().isNotFound());

    }

    @Test
    void deleteItem() throws Exception {
        Item item = new Item(1L,"idly",45);
        when(menuService.findById(any())).thenReturn(Optional.of(item));
        doNothing().when(menuService).delete(any());

        mockMVC.perform(delete("/menu/remove/1"))
                .andExpect(status().isAccepted());

    }

    @Test
    void cannotDeleteItemWithIdNotFound() throws Exception {
        mockMVC.perform(delete("/menu/remove/1"))
                .andExpect(status().isNotFound());
    }


    private static String asJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}