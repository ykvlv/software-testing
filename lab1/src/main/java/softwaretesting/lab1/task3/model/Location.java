package softwaretesting.lab1.task3.model;

public class Location {
    private final String name;

    public Location(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
