package cz.sparko.gravity2d.util;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PointTest {
    @DataProvider
    public Object[][] distanceData() {
        return new Object[][]{
                {new Point(9, 7), new Point(3, 2), 7.8102},
                {new Point(3, 2), new Point(9, 7), 7.8102},
                {new Point(-3, 5), new Point(7, -1), 11.661},
                {new Point(4, 3), new Point(9, 4.5), 5.22}
        };
    }

    @Test(dataProvider = "distanceData")
    public void givenTwoPoints_whenDistance_thenCalculateCorrectDistance(Point p1, Point p2,
                                                                         double expectedDistance) {
        assertEquals(p1.distance(p2), expectedDistance, 0.001);
    }


    @DataProvider
    public Object[][] directionData() {
        return new Object[][]{
                {new Point(0, 0), new Point(7, 2), new Vector2d(7, 2)},
                {new Point(7, 2), new Point(0, 0), new Vector2d(-7, -2)}
        };
    }

    @Test(dataProvider = "directionData")
    public void givenTwoPoints_whenDirection_thenCorrectDirection(Point p1, Point p2,
                                                                  Vector2d expectedDirection) {
        assertEquals(p1.direction(p2), expectedDirection);
    }
}