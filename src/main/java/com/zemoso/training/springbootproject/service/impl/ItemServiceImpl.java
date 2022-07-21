package com.zemoso.training.springbootproject.service.impl;

import com.zemoso.training.springbootproject.dao.ItemRepository;
import com.zemoso.training.springbootproject.dto.ItemDto;
import com.zemoso.training.springbootproject.entity.Item;
import com.zemoso.training.springbootproject.service.ItemService;
import com.zemoso.training.springbootproject.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<ItemDto> findAll() {
        List<Item> items = itemRepository.findAll();
        List<ItemDto> itemDtos= new ArrayList<>();
        for(Item item:items){
            itemDtos.add(modelMapper.map(item,ItemDto.class));
        }
        return itemDtos;
    }

    @Override
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public void saveItemDto(ItemDto itemDto) {
        Item item = modelMapper.map(itemDto,Item.class);
        saveItem(item);
    }

    @Override
    public void deleteById(int itemId) {
        itemRepository.deleteById(itemId);
    }

    @Override
    public Item findById(int itemId) {
        Optional<Item> result = itemRepository.findById(itemId);

        Item item =null;

        if(result.isPresent()){
            item = result.get();
        }else{
            throw new ObjectNotFoundException("Did not find Object for id - "+itemId);
        }
        return item;
    }

    @Override
    public ItemDto findByIdDto(int itemId) {
        Item item = findById(itemId);
        return modelMapper.map(item,ItemDto.class);
    }
}
