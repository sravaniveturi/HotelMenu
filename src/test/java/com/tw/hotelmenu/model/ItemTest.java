package com.tw.hotelmenu.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ItemTest {

    @Test
    void shouldPersistItemWhenNameIsNotNull() {
        Item idly = new Item();

        idly.setName("idly");
        idly.setPrice(45);

        assertEquals("idly", idly.getName());
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        Item item = new Item();

        item.setPrice(65);

        assertThrows(NullPointerException.class, () -> item.setName(null));
    }

    @Test
    void shouldThrowExceptionWhenPriceIsEmpty() {
        Item sandwich = new Item();

        sandwich.setName("sandwich");

        assertThrows(NullPointerException.class, () -> sandwich.setPrice(0));
    }

}