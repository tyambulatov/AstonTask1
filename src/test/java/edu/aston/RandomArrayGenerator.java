package edu.aston;

import java.util.Random;

public class RandomArrayGenerator {

    private static final int MIN_SIZE = 11;
    private static final int MAX_SIZE = 100;
    private static final Random random = new Random();

    public static int[] generate() {
        int size = random.nextInt(MAX_SIZE - MIN_SIZE) + MIN_SIZE;
        int[] array = new int[size];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt();
        }

        return array;
    }
}
