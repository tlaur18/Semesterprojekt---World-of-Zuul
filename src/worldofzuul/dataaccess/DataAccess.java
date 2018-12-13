/*
@author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
* @version 2018.12.14

DataAccess Creates a file at the path given in the argument
Saves and loads the highscores in a file.
*/
package worldofzuul.dataaccess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Morten K. Jensen
 */
public class DataAccess {

    private final String path;
   /*
    Construtor that creates the Dataaccess object and sets the path of the file
    */
    public DataAccess(String path) {
        this.path = path;
    }
    /*
    loads the strings from the file with the given path
    Plaaces the strings in an arrayList and returning it.
    */
    public List<String> load() {
        List<String> highscore = new ArrayList<>();
        try {
            File f = new File(path);
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                highscore.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Nothing found. Empty Result.");
        }
        return highscore;
    }
    /*
    Uses the List of Strings from the argument and using the for each loop
    and the printwriter to write them to the file.
    */
    public void save(List<String> data) {
        try {
            File f = new File(path);
            PrintWriter pw = new PrintWriter(f);
            for (String d : data) {
                pw.println(d);
            }
            pw.close();
        } catch (FileNotFoundException ex) {
            System.out.println("No file. Nothing is saved!.");
        }
    }

}
