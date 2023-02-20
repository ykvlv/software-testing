package softwaretesting.lab1.task3.model;

public abstract class Entity {
    private final String name;
    private Location location;

    protected Entity(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return name;
    }
}
