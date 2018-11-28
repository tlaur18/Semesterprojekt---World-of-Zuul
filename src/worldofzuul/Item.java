package worldofzuul;

import javafx.scene.image.ImageView;

public class Item {

    private String name;
    private String description;
    private ImageView img;

    public Item(String name, String description, String imgURL) {
        this.name = name;
        this.description = description;
        img = new ImageView(imgURL);
        img.fitHeightProperty().set(50);
        img.fitWidthProperty().set(50);
        img.setTranslateX(Math.random() * 400);
        img.setTranslateY(Math.random() * 400);
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
