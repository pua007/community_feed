package org.fastcampus.post.domain.common;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DatetimeInfoTest {

    @Test
    void givenCreated_whenUpdated_thenTimeAndStateArsUpdated() throws InterruptedException {
        //given
        DatetimeInfo datetimeInfo = new DatetimeInfo();
        LocalDateTime initialLocalDateTime = datetimeInfo.getDateTime();


        //when
        Thread.sleep(1);
        datetimeInfo.updateEditDatetime();
        LocalDateTime lastLocalDateTime = datetimeInfo.getDateTime();

        System.out.println("Initial DateTime: " + initialLocalDateTime);
        System.out.println("Updated DateTime: " + lastLocalDateTime);

        //then
        assertTrue(datetimeInfo.isEdited());
        assertNotEquals(initialLocalDateTime, lastLocalDateTime, "Timestamps should not be equal.");
    }
}
