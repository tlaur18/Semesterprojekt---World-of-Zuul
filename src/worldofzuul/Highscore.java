package worldofzuul;

public class Highscore implements Comparable<Highscore> {

    private String name;
    private int score;

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public Highscore() {
        this("*N/A*", 0);
    }

    public Highscore(String name, int score) {
        this.score = score;
        this.name = name;
    }

    @Override
    public String toString() {
        String string = "";
        string += name + ",";
        string += score;
        return string;
    }

    @Override
    public int compareTo(Highscore h) {
        return h.getScore() - score;
    }
}
