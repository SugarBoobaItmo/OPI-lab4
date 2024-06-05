package com.example.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class XBeanTest {

    @Test
    public void testValue() {
        XBean xBean = new XBean();
        Double value = 2.0;
        xBean.setValue(value);
        assertEquals(value, xBean.getValue());
    }

    @Test
    public void testValidate() {
        XBean xBean = new XBean();

        String expected = "Input X!";
        try {
            xBean.validate(null, null, null);
        } catch (Exception e) {
            assertEquals(expected, e.getMessage());
        }

        expected = "X must be in range [-5;5] with step 1!";
        try {
            xBean.validate(null, null, 6.0);
        } catch (Exception e) {
            assertEquals(expected, e.getMessage());
        }
    }
}