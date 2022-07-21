package com.zemoso.training.springbootproject.entity;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.junit.Assert.*;


class ItemTest {
    @Mock
    Item item;


    @Test
    void itemTest(){
        item = new Item(1,"Polo T shirt",599,15,"A good quality t-shirt");
        assertEquals(1,item.getId());
        assertEquals("Polo T shirt",item.getItemName());
        assertEquals(599,item.getItemPrice());
        assertEquals(15,item.getQuantity());
        assertEquals("A good quality t-shirt",item.getItemDescription());
    }

    @Test
    void itemIdTest(){
         Item item = new Item(1,"Polo T shirt",599,15,"A good quality t-shirt");
        assertEquals(1,item.getId());
    }

    @Test
    void itemNameTest(){
        item = new Item(1,"Polo T shirt",599,15,"A good quality t-shirt");
        assertEquals("Polo T shirt",item.getItemName());
    }


    @Test
    void itemPriceTest(){
        item = new Item(1,"Polo T shirt",599,15,"A good quality t-shirt");
        assertEquals(599,item.getItemPrice());
    }

    @Test
    void itemQuantityTest(){
        item = new Item(1,"Polo T shirt",599,15,"A good quality t-shirt");
        assertEquals(15,item.getQuantity());
    }

    @Test
    void itemDescriptionTest(){
        item = new Item(1,"Polo T shirt",599,15,"A good quality t-shirt");
        assertEquals("A good quality t-shirt",item.getItemDescription());
    }

    @Test
    void itemSetIdTest(){
        item = new Item(1,"Polo T shirt",599,15,"A good quality t-shirt");
        item.setId(5);
        assertEquals(5,item.getId());
    }

    @Test
    void itemSetNameTest(){
        item = new Item(1,"Polo T shirt",599,15,"A good quality t-shirt");
        item.setItemName("Polo Blue Shirt");
        assertEquals("Polo Blue Shirt",item.getItemName());
    }

    @Test
    void itemSetQuantityTest(){
        item = new Item(1,"Polo T shirt",599,15,"A good quality t-shirt");
        item.setQuantity(20);
        assertEquals(20,item.getQuantity());
    }

    @Test
    void itemSetPriceTest(){
        item = new Item(1,"Polo T shirt",599,15,"A good quality t-shirt");
        item.setItemPrice(200);
        assertEquals(200,item.getItemPrice());
    }

    @Test
    void itemSetDescriptionTest(){
        item = new Item(1,"Polo T shirt",599,15,"A good quality t-shirt");
        item.setItemDescription("A Branded product");
        assertEquals("A Branded product",item.getItemDescription());
    }


}
