package com.tw.hotelmenu.repository;

import com.tw.hotelmenu.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
