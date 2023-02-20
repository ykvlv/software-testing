package softwaretesting.lab1.model;

public class House extends Building {
    private final Human owner;

    public House(String name, Location location, int hp, Human owner) {
        super(name, location, hp);
        this.owner = owner;
    }
}
