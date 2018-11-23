package worldofzuul;

import WorldOfZuulIO.TextIO;

public class Start {

    public static void main(String[] args) throws InterruptedException {
        TextIO textIO = new TextIO(new Game());
        textIO.play();
    }

}
