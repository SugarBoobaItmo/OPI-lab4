package com.example.utils;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class AreaValidatorTest {

    @Test
    public void testCheckArea() {
        assertTrue(AreaValidator.checkArea(0.5, -0.5, 1));
        assertFalse(AreaValidator.checkArea(1, 1, 1));
    }

    @Test
    public void testCheckSector() {
        assertTrue(AreaValidator.checkSector(0.5, -0.5, 1));
        assertFalse(AreaValidator.checkSector(-3, -1, 1));
    }

    @Test
    public void testCheckTriangle() {
        assertTrue(AreaValidator.checkTriangle(-0.5, 0.0, 1));
        assertFalse(AreaValidator.checkTriangle(1, 1, 1));
    }

    @Test
    public void testCheckSquare() {
        assertTrue(AreaValidator.checkSquare(0.5, 0.5, 1));
        assertFalse(AreaValidator.checkSquare(1, 1, 1));
    }
}