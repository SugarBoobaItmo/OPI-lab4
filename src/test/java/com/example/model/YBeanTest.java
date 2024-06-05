package com.example.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class YBeanTest {

    @Test
    public void testValue() {
        YBean yBean = new YBean();
        Double value = 2.0;
        yBean.setValue(value);
        assertEquals(value, yBean.getValue());
    }

    @Test
    public void testValidate() {
        YBean yBean = new YBean();

        String expected = "Input Y!";
        try {
            yBean.validate(null, null, null);
        } catch (Exception e) {
            assertEquals(expected, e.getMessage());
        }

        expected = "Y must be in range [-3;5]!";
        try {
            yBean.validate(null, null, 6.0);
        } catch (Exception e) {
            assertEquals(expected, e.getMessage());
        }
    }
        
}