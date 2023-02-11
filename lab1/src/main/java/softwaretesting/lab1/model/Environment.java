package softwaretesting.lab1.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Environment {
    private int temperature;
    private final Set<WeatherPhenomenon> weatherPhenomena;
    private final List<Entity> entities;

    public Environment(int temperature, Set<WeatherPhenomenon> weatherPhenomena, Entity... entities) {
        this.temperature = temperature;
        this.weatherPhenomena = weatherPhenomena;
        this.entities = new ArrayList<>(Arrays.asList(entities));
    }

    public void changeTemperatureBy(int number) {
        System.out.printf("~ Температура изменилась %s -> %s\n", temperature, temperature + number);
        temperature += number;
    }

    public void addWeatherPhenomenon(WeatherPhenomenon weatherPhenomenon) {
        if (weatherPhenomena.contains(weatherPhenomenon)) {
            System.out.printf("~ По прежнему %s ~\n", weatherPhenomenon.getName());
        } else {
            System.out.printf("~ Становится %s ~\n", weatherPhenomenon.getName());
            weatherPhenomena.add(weatherPhenomenon);
        }
    }

    public void printScopeOf(Entity entity) {
        System.out.printf(
                "В зоне видимости %s находится: %s",
                entity.getName(),
                entities.stream()
                        .filter(e -> e.getLocation().equals(entity.getLocation()) && e != entity)
                        .map(Entity::toString)
                        .collect(Collectors.joining(", "))
        );
    }
}
