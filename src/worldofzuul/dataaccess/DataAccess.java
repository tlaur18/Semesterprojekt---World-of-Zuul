/**
 *
 * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
 * @version 2018.12.14
 * 
 */

package worldofzuul.dataaccess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataAccess {

    private final String path;

    public DataAccess(String path) {
        this.path = path;
    }

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
