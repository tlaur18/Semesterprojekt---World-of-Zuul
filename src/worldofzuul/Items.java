package worldofzuul;

public class Items {

    private String name;
    private String description;
    private boolean pickedUp;

    public Items(String name, String description) {
        this.name = name;
        this.description = description;
        pickedUp = false;
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

    public boolean isPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }

}
