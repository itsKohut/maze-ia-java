package br.com.java.estudo;

public class Main {

    public static void main(String[] args) {

        int maxGenerations = 1000;
        GeneticAlgorithm ga = new GeneticAlgorithm(200, 0.05, 0.9, 1, 6);
        Population population = ga.initPopulation();

        int generation = 0;

        while (ga.condintionisNotMet(generation, maxGenerations)) { // achou

            ga.evaluation(population);

            Chromosome fittest = population.getFittest(0);
           // System.out.println("Generation " + generation + " Best solution (" + fittest.getFitness() + "): " + fittest.toString());

            population = ga.crossover(population);
            population = ga.mutate(population);
            ga.evaluation(population);
            generation++;
        }

        System.out.println("terminou");
    }
}
