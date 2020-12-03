package br.com.java.estudo;

import java.util.Random;

public class Utils {

    public static int distancia(int actualX, int actualY, int destinationX, int destinationY) {
        return Math.abs(actualX - destinationX) + Math.abs(actualY - destinationY);
    }

    public static double genarateRandomWeigth() {

        Random generator = new Random();

        double weight = generator.nextDouble();
        if (generator.nextBoolean()) {
            weight = weight * -1;
        }

        return weight;
    }
}
