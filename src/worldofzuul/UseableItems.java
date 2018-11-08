package worldofzuul;

public abstract class UseableItems extends Items{
    
    public UseableItems(String name, String description) {
        super(name, description);
    }
    
    public abstract void use();
}
