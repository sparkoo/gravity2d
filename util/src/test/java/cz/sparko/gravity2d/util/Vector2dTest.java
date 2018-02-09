package cz.sparko.gravity2d.util;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class Vector2dTest {
    @Test
    public void givenTwoVectors_whenAdd_thenNewVectorCreatedWithAddedValues() {
        Vector2d v1 = new Vector2d(1.23, 2.34);
        Vector2d v2 = new Vector2d(3.45, 4.56);

        Vector2d result = v1.add(v2);

        Vector2d expectedResult = new Vector2d(4.68, 6.90);

        assertEquals(result.getX(), expectedResult.getX(), 0.001);
        assertEquals(result.getY(), expectedResult.getY(), 0.001);
        assertNotSame(result, v1);
        assertNotSame(result, v2);
    }

    @Test
    public void givenTwoVectors_whenSubtract_thenNewVectorCreatedWithAddedValues() {
        Vector2d v1 = new Vector2d(3.45, 4.56);
        Vector2d v2 = new Vector2d(1.23, 2.34);

        Vector2d result = v1.subtract(v2);

        Vector2d expectedResult = new Vector2d(2.22, 2.22);

        assertEquals(result.getX(), expectedResult.getX(), 0.001);
        assertEquals(result.getY(), expectedResult.getY(), 0.001);
        assertNotSame(result, v1);
        assertNotSame(result, v2);
    }
}