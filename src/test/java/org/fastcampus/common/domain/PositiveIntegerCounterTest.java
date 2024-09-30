package org.fastcampus.common.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositiveIntegerCounterTest {
     @Test
    void givenCreated_WhenIncrease_ThenCountIsOne(){
         //given
         PositiveIntegerCounter counter = new PositiveIntegerCounter();
         //when
         counter.increase();
         //then
         assertEquals(1, counter.getCount());
     }


    @Test
    void givenCreatedIncreased_WhenDecrease_ThenCountIsZero(){
        //given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();
        counter.increase();
        //when
        counter.decrease();
        //then
        assertEquals(0, counter.getCount());
    }

    @Test
    void givenCreated_WhenDecrease_ThenCountIsZero(){
        //given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();
        //when
        counter.decrease();
        //then
        assertEquals(0, counter.getCount());
    }

}
