package dominio;

public abstract class Player {
    private int score;
    private String color;
    private String name;
    private int[] piedras;

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
        this.score = 0;
        this.piedras = new int[] { 0, 0 };
    }

    public abstract Celda[][] jugar(int row, int col, String tipoPiedra);

    public int getScore() {
        return score;
    }

    public void setPiedrasPesadas(int a) {
        this.piedras[0] += a;
    }

    public int getPiedrasPesadas() {
        return this.piedras[0];
    }

    public void setPiedrasLigeras(int a) {
        this.piedras[1] += a;
    }

    public int getPiedrasLigeras() {
        return this.piedras[1];
    }

    public void ronda(String tipoPiedra) {
        if (tipoPiedra.equals("Pesada")) {
            piedras[0]--;
        } else {
            piedras[1]--;
        }
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addScore(int score) {
        this.score += score;
    }
}