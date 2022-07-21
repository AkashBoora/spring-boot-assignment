package com.zemoso.training.springbootproject.service;


import com.zemoso.training.springbootproject.dao.ItemRepository;
import com.zemoso.training.springbootproject.dto.ItemDto;
import com.zemoso.training.springbootproject.entity.Item;
import com.zemoso.training.springbootproject.exceptions.ObjectNotFoundException;
import com.zemoso.training.springbootproject.service.impl.ItemServiceImpl;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@SpringBootTest
@RunWith(SpringRunner.class)
class ItemServiceImplTest {

    @Autowired
    private ModelMapper modelMapper;

    @Mock
    Item item;

    @Mock
    ItemDto itemDto;
    @MockBean
    ItemRepository itemRepository;

    @Autowired
    ItemServiceImpl itemServiceImpl;


    @Test
    void findAll(){
        item = new Item(1,"Polo T shirt",599,15,"A good quality t-shirt");
        Mockito.when(itemRepository.findAll()).thenReturn(Stream.of(item).collect(Collectors.toList()));
        assertEquals(1,itemServiceImpl.findAll().size());
    }

    @Test
    void findById(){
        item = new Item(2,"Polo T shirt",599,15,"A good quality t-shirt");
        doReturn(Optional.of(item))
                .when(itemRepository)
                .findById(2);
        assertEquals(2,itemServiceImpl.findById(2).getId());
    }

    @Test
    void saveItem(){
        itemServiceImpl.saveItem(item);
        verify(itemRepository,times(1)).save(any());
    }

    @Test
    void deleteById(){
        item = new Item(2,"Polo T shirt",599,15,"A good quality t-shirt");
        itemServiceImpl.deleteById(2);
        verify(itemRepository,times(1)).deleteById(2);
    }

    @Test
    void findByIdException(){
        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            itemServiceImpl.findById(100);
        });

        String expectedMessage = "Did not find Object for id - 100";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


}
