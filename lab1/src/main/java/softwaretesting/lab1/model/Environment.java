package softwaretesting.lab1.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Environment {
    private final Temperature temperature;
    private final Weather weather;
    private final List<Entity> entities;

    public Environment(Temperature temperature, Weather weather, Entity... entities) {
        this.temperature = temperature;
        this.weather = weather;
        this.entities = new ArrayList<>(Arrays.asList(entities));
    }
}
