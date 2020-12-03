package br.com.java.estudo;

import java.util.ArrayList;
import java.util.List;

public class GeneticAlgorithm {

    private double crossoverRate;
    private int elitismCount;
    private double mutationRate;
    private int populationSize;
    private int tournamentSize;

    public GeneticAlgorithm(int populationSize, double mutationRate, double crossoverRate, int elitismCount, int tournamentSize) {
        this.crossoverRate = crossoverRate;
        this.elitismCount = elitismCount;
        this.mutationRate = mutationRate;
        this.populationSize = populationSize;
        this.tournamentSize = tournamentSize;
    }

    public Population initPopulation() {
        return new Population(populationSize);
    }

    public boolean condintionisNotMet(int generation, int maxGenerations) {

        if (generation <= maxGenerations) {
            return true;
        }
        return false;
    }

    public void evaluation(Population population) {

        for (int i = 0; i < population.getChromosomes().length; i++) {
            this.fitnessCalculus(population.getChromosome(i));
        }
    }

    public void fitnessCalculus(Chromosome chromosome) {

        Maze maze = new Maze();
        Agent agent = new Agent(maze, chromosome);

        Network network = new Network(8, 4);
        network.setPesosNaRede(8, chromosome.getWeight());

        boolean agentWalked = true;
        List<String> path = new ArrayList<String>();
        while (agentWalked) {
            double[] surrondingsResults = agent.getSurroundings();
            double[] weightResults = network.propagation(surrondingsResults);
            agentWalked = agent.walk(chooseHighestWeight(weightResults));
            path.add(agent.getCoordinates());
        }

        double score = agent.getScore();
        chromosome.setFitness(score);
        chromosome.setPath(path);
    }


    public int chooseHighestWeight(double[] x) {

        int biggest = 0;

        for (int i = 1; i < x.length; i++) {
            if (x[biggest] < x[i]) {
                biggest = i;
            }
        }
        return biggest;
    }

    public Population mutate(Population population) {

        Population newPopulation = new Population(this.populationSize);

        for (int populationIndex = 0; populationIndex < population.getPopulationSize(); populationIndex++) {
            Chromosome chromosome = population.getFittest(populationIndex);

            for (int weightIndex = 0; weightIndex < chromosome.getPesosLength(); weightIndex++)
                if (populationIndex >= this.elitismCount) {
                    if (this.mutationRate > Math.random()) {
                        double newPeso = Utils.genarateRandomWeigth();
                        chromosome.setGene(weightIndex, newPeso);
                    }
                }

            newPopulation.setChromosome(populationIndex, chromosome);
        }

        return newPopulation;
    }

    public Population crossover(Population population) {

        Population newPopulation = new Population(population.getPopulationSize());

        for (int populationIndex = 0; populationIndex < population.getPopulationSize(); populationIndex++) {
            Chromosome parent1 = population.getFittest(populationIndex);

            if (this.crossoverRate > Math.random() && populationIndex >= this.elitismCount) {
                Chromosome offspring = new Chromosome();

                Chromosome parent2 = this.selectParent(population);

                for (int geneIndex = 0; geneIndex < parent1.getPesosLength(); geneIndex++) {
                    double media = (parent1.getPeso(geneIndex) + parent2.getPeso(geneIndex)) / 2;
                    offspring.setGene(geneIndex, media);
                }

                newPopulation.setChromosome(populationIndex, offspring);
            } else {
                newPopulation.setChromosome(populationIndex, parent1);
            }
        }

        return newPopulation;
    }

    public Chromosome selectParent(Population population) {

        Population tournament = new Population(this.tournamentSize);

        population.shuffle();
        for (int i = 0; i < this.tournamentSize; i++) {
            Chromosome tournamentIndividual = population.getChromosome(i);
            tournament.setChromosome(i, tournamentIndividual);
        }

        return tournament.getFittest(0);
    }
}
