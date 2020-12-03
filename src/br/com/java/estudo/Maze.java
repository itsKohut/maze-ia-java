package br.com.java.estudo;

public class Maze {

    private String[][] matrix;

    public Maze() {
        matrix = new String[][]{
                {"E", "0", "0", "1", "0", "M", "0", "0", "M", "1"},
                {"0", "1", "0", "M", "0", "1", "0", "1", "0", "M"},
                {"0", "0", "M", "0", "1", "1", "M", "0", "1", "1"},
                {"M", "1", "1", "0", "M", "1", "1", "M", "0", "1"},
                {"M", "0", "0", "0", "0", "1", "1", "0", "1", "1"},
                {"1", "1", "1", "1", "0", "1", "1", "0", "1", "1"},
                {"1", "0", "1", "1", "0", "1", "1", "M", "0", "M"},
                {"M", "0", "M", "M", "0", "1", "1", "1", "1", "1"},
                {"1", "M", "1", "M", "0", "0", "M", "M", "1", "1"},
                {"1", "M", "1", "M", "1", "M", "0", "0", "0", "S"}
        };
    }

    public int getExitCoordinateX() {
        return 9;
    }

    public int getExitCoordinateY() {
        return 9;
    }

    public String getPositionValue(int x, int y) {

        if (this.isValid(x, y)) {
            return this.matrix[x][y];
        }
        return "1";
    }

    public void removeCoin(int x, int y) {
        this.matrix[x][y] = "0";
    }

    public double getPositionTranslated(int x, int y) {

        if (this.isValid(x, y)) {
            String value = this.getPositionValue(x, y);

            switch (value) {

                case "E":
                    return 0;

                case "0":
                    return 0;

                case "1":
                    return 1;

                case "M":
                    return 8;

                case "S":
                    return 100;
            }
        }

        return 1;
    }

    public boolean isWall(int x, int y) {
        if (this.matrix[x][y].equals("1")) {
            return true;
        }
        return false;
    }

    public boolean isWithinMaze(int x, int y) {
        if (x < 0 || x > 9 || y < 0 || y > 9) {
            return false;
        }
        return true;
    }

    public boolean isValid(int x, int y) {
        return isWithinMaze(x, y) && !this.isWall(x, y);
    }
}

