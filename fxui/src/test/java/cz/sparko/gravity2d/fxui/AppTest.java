package cz.sparko.gravity2d.fxui;

import org.testng.annotations.Test;

import java.util.Random;
import java.util.stream.IntStream;

import static org.testng.Assert.*;

public class AppTest {
    @Test
    public void given_when_then() {
        Random r = new Random();
        IntStream.range(0,1000)
                .map(i -> r.nextInt(2))
                .peek(System.out::println)
                .reduce((a, b) -> a + b)
                .ifPresent(System.out::println);
    }
}