/*
@author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
* @version 2018.12.14

 Class for creating Highscore Objects with name and score.
*/
package worldofzuul;


public class Highscore implements Comparable<Highscore> {

    private String name;
    private int score;

    // No arg constructior for creating the Highscore Object
    public Highscore() {
        this("*N/A*", 0);
    }

    /* Constructor with name and score as argument, 
    /   for creating a connection to the player and the player score.
    */
    public Highscore(String name, int score) {
        this.score = score;
        this.name = name;
    }
    
     public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
    
    /* 
      Custom toString method to make name and score in the right order for saving
      the comma is the seperator the DataAccess layer uses for seperating name 
      and score
    */
    @Override
    public String toString() {
        String string = "";
        string += name + ",";
        string += score;
        return string;
    }
    
    /* 
      Custom compareTo to help the Database sort by score instead of name.
    */
    @Override
    public int compareTo(Highscore h) {
        return h.getScore() - score;
    }
}
