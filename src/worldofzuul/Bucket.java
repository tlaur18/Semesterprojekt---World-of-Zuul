package worldofzuul;

public class Bucket extends UseableItems {
    
    public Bucket(String name, String description){
        super(name, description);
    }

    @Override
    public void use() {
        System.out.println("hej");
    }

}
