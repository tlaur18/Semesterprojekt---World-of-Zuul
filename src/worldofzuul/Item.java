package worldofzuul;

public class Item {
    private String name;
    private String description;
    private boolean pickedUp;
    
    public Item(String name, String description){
        this.name = name;
        this.description = description;
        pickedUp = false;
    }
    
    /**
     * @return the name
     */
    public String getName(){
        return name;
    }
    
    /**
     * @param name 
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the pickedUp
     */
    public boolean isPickedUp() {
        return pickedUp;
    }

    /**
     * @param pickedUp the pickedUp to set
     */
    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }
    
    
    
}
