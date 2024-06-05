package com.example.model;

import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DateTimeBeanTest {

    @Test
    public void testGetCurrentDateTime() {
        DateTimeBean dateTimeBean = new DateTimeBean();
        String currentDateTime = dateTimeBean.getCurrentDateTime();
        assertNotNull(currentDateTime);

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        sdf.setLenient(false);
        try {
            sdf.parse(currentDateTime);
            assertTrue(true);
        } catch (ParseException e) {
            assertTrue(false);
        }
    }
}