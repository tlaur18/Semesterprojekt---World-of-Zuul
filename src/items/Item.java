/*
 * This is a super-class for all the items in the game.
 *
 * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
 * @version 2018.12.14
 *
 */
package items;

import javafx.scene.image.ImageView;

public class Item {

    private String name;
    private String description;
    private ImageView img;
    
    
    /* This is a constructor for the Item class, it takes in 
     * a name, description, url for the image and a position.
     * Sets the position and size of the ImageView
     */
    public Item(String name, String description, String imgURL, int imgX, int imgY) {
        this.name = name;
        this.description = description;
        img = new ImageView(imgURL);
        img.fitHeightProperty().set(50);
        img.fitWidthProperty().set(50);
        img.setTranslateX(imgX);
        img.setTranslateY(imgY);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageView getImage() {
        return img;
    }
}
