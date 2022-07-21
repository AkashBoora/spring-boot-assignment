package com.zemoso.training.springbootproject.service;

import com.zemoso.training.springbootproject.dto.ItemDto;
import com.zemoso.training.springbootproject.entity.Item;

import java.util.List;

public interface ItemService {

    public List<ItemDto> findAll();

    public void saveItem(Item item);

    void deleteById(int itemId);

    Item findById(int itemId);
}
