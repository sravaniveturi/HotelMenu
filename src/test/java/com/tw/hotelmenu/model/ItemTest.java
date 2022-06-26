package com.tw.hotelmenu.model;

import com.tw.hotelmenu.exception.InvalidItemFieldsException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ItemTest {

    @Test
    void shouldPersistItemWhenNameIsNotNull() throws InvalidItemFieldsException {
        Item idly = new Item();

        idly.setName("idly");
        idly.setPrice(45);

        assertEquals("idly", idly.getName());
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() throws InvalidItemFieldsException {
        Item item = new Item();

        assertThrows(InvalidItemFieldsException.class, () -> item.setName(null));
    }

    @Test
    void shouldThrowExceptionWhenPriceIsEmpty() throws InvalidItemFieldsException {
        Item sandwich = new Item();

        sandwich.setName("sandwich");

        assertThrows(InvalidItemFieldsException.class, () -> sandwich.setPrice(0));
    }

}