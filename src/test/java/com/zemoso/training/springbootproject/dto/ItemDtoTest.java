package com.zemoso.training.springbootproject.dto;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

class ItemDtoTest {

    @Mock
    ItemDto itemDto;

    @Test
    void itemDtoTest(){
        itemDto = new ItemDto(1,"Polo T Shirt",599,15,"Good qality t-shirt");
        assertEquals(1,itemDto.getId());
        assertEquals("Polo T Shirt",itemDto.getItemName());
        assertEquals(599,itemDto.getItemPrice());
        assertEquals(15,itemDto.getQuantity());
        assertEquals("Good qality t-shirt",itemDto.getItemDescription());
    }
}
