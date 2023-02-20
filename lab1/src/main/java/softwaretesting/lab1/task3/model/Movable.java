package softwaretesting.lab1.task3.model;

public abstract class Movable extends Entity {
    protected Movable(String name, Location location) {
        super(name, location);
    }

    public void goTo(Entity entity) {
        System.out.printf("%s направляется к %s\n", this.getName(), entity.getName());
        this.setLocation(entity.getLocation());
    }
}
