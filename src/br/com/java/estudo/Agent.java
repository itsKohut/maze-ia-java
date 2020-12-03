package br.com.java.estudo;

import static br.com.java.estudo.Utils.distancia;

public class Agent {

    private Maze maze;
    private int coordinateX;
    private int coordinateY;
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

                //System.out.println("ESQUERDA - " + this.coordinateX + " - " + this.coordinateY);

            } else if (direction == 3 && this.maze.isValid(this.coordinateX, this.coordinateY + 1)) {
                this.coordinateY++;
                caminhou = true;

                //System.out.println("DIREITA - " + this.coordinateX + " - " + this.coordinateY);

            } else if (direction == 2 && this.maze.isValid(this.coordinateX + 1, this.coordinateY)) {
                this.coordinateX++;
                caminhou = true;

                //System.out.println("BAIXO - " + this.coordinateX + " - " + this.coordinateY);

            } else if (direction == 0 && this.maze.isValid(this.coordinateX - 1, this.coordinateY)) {
                this.coordinateX--;
                caminhou = true;

                // System.out.println("CIMA - " + this.coordinateX + " - " + this.coordinateY);
            }

            if (caminhou) {
                this.moves++;
                if (this.maze.getPositionValue(this.coordinateX, this.coordinateY) == "M") {
                    this.maze.removeCoin(coordinateX, coordinateY);
                    this.collectedCoins++;
                }
            }

            if (this.maze.isValid(coordinateX, coordinateY) && this.maze.getPositionValue(coordinateX, coordinateY) == "S") {
                System.out.println("Achou saida");
            }
        }

        return caminhou;
    }

    public double[] getSurroundings() {
        double[] visao = new double[8];

        int index = 0;

        if (!this.maze.isValid(coordinateX - 1, coordinateY)) {
            visao[index] = 1;
        } else {
            visao[index] = this.maze.getPositionTranslated(coordinateX - 1, coordinateY);
        }

        index++;
        visao[index] = distancia(coordinateX - 1, coordinateY, this.maze.getExitCoordinateX(), this.maze.getExitCoordinateY());
        index++;


        if (!this.maze.isValid(coordinateX, coordinateY - 1)) {
            visao[index] = 1;
        } else {
            visao[index] = this.maze.getPositionTranslated(coordinateX, coordinateY - 1);
        }

        index++;
        visao[index] = distancia(coordinateX, coordinateY - 1, this.maze.getExitCoordinateX(), this.maze.getExitCoordinateY());  //distancia da saída
        index++;

        if (!this.maze.isValid(coordinateX + 1, coordinateY)) {
            visao[index] = 1;
        } else {
            visao[index] = this.maze.getPositionTranslated(coordinateX + 1, coordinateY);
        }

        index++;
        visao[index] = distancia(coordinateX + 1, coordinateY, this.maze.getExitCoordinateX(), this.maze.getExitCoordinateY());  //distancia da saída
        index++;

        if (!this.maze.isValid(coordinateX, coordinateY + 1)) {
            visao[index] = 1;
        } else {
            visao[index] = this.maze.getPositionTranslated(coordinateX, coordinateY + 1);
        }

        index++;
        visao[index] = distancia(coordinateX, coordinateY + 1, this.maze.getExitCoordinateX(), this.maze.getExitCoordinateY());  //distancia da saída
        index++;

        return visao;
    }


    public double getScore() {

        double teste = this.collectedCoins * 2;
        double teste1 = moves;
        double teste2 = teste + teste1;

        double distancia = Utils.distancia(9, 9, 9, 9) + 0.1;
        return teste2 / distancia;
    }

}
