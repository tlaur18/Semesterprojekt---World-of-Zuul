package worldofzuul;

public abstract class UseableItems extends Items implements Usable{
    
    public UseableItems(String name, String description) {
        super(name, description);
    }

    @Override
    public abstract void use();
}
