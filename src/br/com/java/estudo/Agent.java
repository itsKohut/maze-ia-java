package br.com.java.estudo;

import java.util.ArrayList;
import java.util.List;

import static br.com.java.estudo.Main.*;
import static br.com.java.estudo.Utils.distancia;

public class Agent {

    private Maze maze;
    public int coordinateX;
    public int coordinateY;
    private int moves;
    private int collectedCoins;
    private Chromosome chromosome;
    private int maxMoves;

    public Agent(Maze maze, Chromosome chromosome) {
        this.maxMoves = 100;
        this.coordinateX = 0;
        this.coordinateY = 0;
        this.moves = 0;
        this.collectedCoins = 0;
        this.maze = maze;
        this.chromosome = chromosome;
    }

    public boolean walk(int direction) {
        boolean caminhou = false;

        if (moves > maxMoves) {
            collectedCoins = 0;
            moves = 0;

        } else {

            if (direction == 1 && this.maze.isValid(this.coordinateX, this.coordinateY - 1)) {
                this.coordinateY--;
                caminhou = true;

            } else if (direction == 3 && this.maze.isValid(this.coordinateX, this.coordinateY + 1)) {
                this.coordinateY++;
                caminhou = true;


            } else if (direction == 2 && this.maze.isValid(this.coordinateX + 1, this.coordinateY)) {
                this.coordinateX++;
                caminhou = true;


            } else if (direction == 0 && this.maze.isValid(this.coordinateX - 1, this.coordinateY)) {
                this.coordinateX--;
                caminhou = true;

            }

            if (caminhou) {
                this.chromosome.path.add(this.getCoordinates());
                this.moves++;
                if (this.maze.getPositionValue(this.coordinateX, this.coordinateY) == "M") {
                    this.maze.removeCoin(coordinateX, coordinateY);
                    this.collectedCoins++;
                }
            }

            if (this.maze.isValid(coordinateX, coordinateY) && this.maze.getPositionValue(coordinateX, coordinateY) == "S") {
                rodar = false;
                System.out.println("Achou saida");
            }
        }

        return caminhou;
    }

    public double[] getSurroundings() {

        double[] visao = new double[8];
        double dist = 0;

        int index = 0;

        if (!this.maze.isValid(coordinateX - 1, coordinateY)) { // cima
            visao[index] = 1;
        } else {
            visao[index] = this.maze.getPositionTranslated(coordinateX - 1, coordinateY); // uma cima

            if (!this.maze.isValid(coordinateX - 2, coordinateY)) {
                visao[index] = visao[index];
            } else {
                visao[index] = +this.maze.getPositionTranslated(coordinateX - 2, coordinateY); // duas cima
                dist = distancia(coordinateX - 2, coordinateY, this.maze.getExitCoordinateX(), this.maze.getExitCoordinateY());
            }
        }

        index++;

        visao[index] = distancia(coordinateX - 1, coordinateY, this.maze.getExitCoordinateX(), this.maze.getExitCoordinateY()) + dist;
        visao[index] = dist == 0 ? visao[index] / 1 : visao[index] / 2;

        index++;
        dist = 0;

        if (!this.maze.isValid(coordinateX, coordinateY - 1)) {
            visao[index] = 1;
        } else {
            visao[index] = this.maze.getPositionTranslated(coordinateX, coordinateY - 1);

            if (!this.maze.isValid(coordinateX, coordinateY - 2)) {
                visao[index] = visao[index];
            } else {
                visao[index] =+ this.maze.getPositionTranslated(coordinateX, coordinateY - 2);
                dist = distancia(coordinateX, coordinateY - 2, this.maze.getExitCoordinateX(), this.maze.getExitCoordinateY());
            }
        }

        index++;

        visao[index] = distancia(coordinateX, coordinateY - 1, this.maze.getExitCoordinateX(), this.maze.getExitCoordinateY()) + dist;  //distancia da saída
        visao[index] = dist == 0 ? visao[index] / 1 : visao[index] / 2;

        index++;
        dist = 0;

        if (!this.maze.isValid(coordinateX + 1, coordinateY)) {
            visao[index] = 1;
        } else {
            visao[index] = this.maze.getPositionTranslated(coordinateX + 1, coordinateY);

            if (!this.maze.isValid(coordinateX + 2, coordinateY)) {
                visao[index] = visao[index];
            } else {
                visao[index] =+ this.maze.getPositionTranslated(coordinateX + 2, coordinateY);
                 dist = distancia(coordinateX + 2, coordinateY, this.maze.getExitCoordinateX(), this.maze.getExitCoordinateY());
            }
        }

        index++;

        visao[index] = distancia(coordinateX + 1, coordinateY, this.maze.getExitCoordinateX(), this.maze.getExitCoordinateY()) + dist;  //distancia da saída
        visao[index] = dist == 0 ? visao[index] / 1 : visao[index] / 2;

        index++;
        dist = 0;

        if (!this.maze.isValid(coordinateX, coordinateY + 1)) {
            visao[index] = 1;
        } else {
            visao[index] = this.maze.getPositionTranslated(coordinateX, coordinateY + 1);

            if (!this.maze.isValid(coordinateX, coordinateY + 2)) {
                visao[index] = visao[index];
            } else {
                visao[index] =+ this.maze.getPositionTranslated(coordinateX, coordinateY + 2);
                dist = distancia(coordinateX, coordinateY + 2, this.maze.getExitCoordinateX(), this.maze.getExitCoordinateY()) + dist;  //distancia da saída

            }
        }

        index++;
        visao[index] = distancia(coordinateX, coordinateY + 1, this.maze.getExitCoordinateX(), this.maze.getExitCoordinateY()) + dist;  //distancia da saída
        visao[index] = dist == 0 ? visao[index] / 1 : visao[index] / 2;

        return visao;
    }


    public double getScore() {

        double teste = this.collectedCoins * 2;
        double teste1 = moves;
        double teste2 = teste + teste1;

        double distancia = Utils.distancia(9, 9, 9, 9) + 0.1;
        return teste2 / distancia;
    }

    public String getCoordinates() {
        return this.coordinateX + " - " + this.coordinateY;
    }

}
