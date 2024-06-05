package com.example.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RBeanTest {

    @Test
    public void testValue() {
        RBean rBean = new RBean();
        Double value = 2.0;
        rBean.setValue(value);
        assertEquals(value, rBean.getValue());
    }

    @Test
    public void testValidate() {
        RBean rBean = new RBean();

        String expected = "Input R!";
        try {
            rBean.validate(null, null, null);
        } catch (Exception e) {
            assertEquals(expected, e.getMessage());
        }

        expected = "R must be in range [1;3]! with step 0.5!";
        try {
            rBean.validate(null, null, 6.0);
        } catch (Exception e) {
            assertEquals(expected, e.getMessage());
        }
    }
        
        
}