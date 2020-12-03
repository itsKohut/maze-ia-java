package br.com.java.estudo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Population {

    private Chromosome[] population;

    public Population(int populationSize) {
        this.population = new Chromosome[populationSize];
        for (int individualCount = 0; individualCount < populationSize; individualCount++) {

            Chromosome individual = new Chromosome();
            this.population[individualCount] = individual;
        }
    }

    public Chromosome[] getPopulation() {
        return population;
    }

    public Chromosome[] getChromosomes() {
        return this.population;
    }

    public Chromosome getFittest(int position) {

        Arrays.sort(this.population, new Comparator<Chromosome>() {
            @Override
            public int compare(Chromosome o1, Chromosome o2) {
                if (o1.getFitness() > o2.getFitness()) {
                    return -1;
                } else if (o1.getFitness() < o2.getFitness()) {
                    return 1;
                }
                return 0;
            }
        });

        return this.population[position];
    }

    public int getPopulationSize() {
        return this.population.length;
    }

    public Chromosome getChromosome(int position) {
        return this.population[position];
    }

    public void setChromosome(int position, Chromosome newChromosome) {
        this.population[position] = newChromosome;
    }

    public void shuffle() {
        Random rnd = new Random();
        for (int i = population.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            Chromosome a = population[index];
            population[index] = population[i];
            population[i] = a;
        }
    }
}
