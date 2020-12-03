package br.com.java.estudo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chromosome {

    private double[] chromosome;
    private List<String> path;
    private double fitness;

    public Chromosome() {
        this.path = new ArrayList<>();
        this.chromosome = new double[9 * 8 + 9 * 4];  //9= 8 entradas + bias , 8 neuronios na camada oculta e 4 na camada de saída

        for (int i = 0; i < this.chromosome.length; i++) {
            this.chromosome[i] = Utils.genarateRandomWeigth();
        }
    }

    public int getPesosLength() {
        return this.chromosome.length;
    }


    public void setGene(int posicao, double novopeso) {
        this.chromosome[posicao] = novopeso;
    }

    public double[] getWeight() {
        return chromosome;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getPeso(int pesoIndex) {
        return this.chromosome[pesoIndex];
    }

    public void setPath(List<String> path) {
        this.path = new ArrayList<String>();
        for (int i = 0; i < path.size(); i++) {
            this.path.add(path.get(i));
        }
    }

    @Override
    public String toString() {
        return "Chromosome{" +
                ", path=" + path +
                ", fitness=" + fitness +
                '}';
    }
}
