package softwaretesting.lab1.task3.model;

import softwaretesting.lab1.task3.model.exception.EntityNotInEnvironmentException;

import java.util.*;
import java.util.stream.Collectors;

public class Environment {
    private int temperature;
    private final Set<WeatherPhenomenon> weatherPhenomena;
    private final Set<Entity> entities;

    public Environment(int temperature, Set<WeatherPhenomenon> weatherPhenomena, Entity... entities) {
        this.temperature = temperature;
        this.weatherPhenomena = weatherPhenomena;
        this.entities = new HashSet<>(Arrays.asList(entities));
    }

    public void changeTemperatureBy(int number) {
        System.out.printf("~ Температура изменилась %s -> %s ~\n", temperature, temperature + number);
        temperature += number;
    }

    public int getTemperature(){
        return temperature;
    }

    public void addWeatherPhenomenon(WeatherPhenomenon weatherPhenomenon) {
        if (weatherPhenomena.contains(weatherPhenomenon)) {
            System.out.printf("~ По прежнему %s ~\n", weatherPhenomenon.getName());
        } else {
            System.out.printf("~ Становится %s ~\n", weatherPhenomenon.getName());
            weatherPhenomena.add(weatherPhenomenon);
        }
    }

    public void removeWeatherPhenomenon(WeatherPhenomenon weatherPhenomenon) {
        if (weatherPhenomena.contains(weatherPhenomenon)) {
            System.out.printf("~ Уже не так %s ~\n", weatherPhenomenon.getName());
            weatherPhenomena.remove(weatherPhenomenon);
        } else {
            System.out.printf("~ По прежнему не %s ~\n", weatherPhenomenon.getName());
        }
    }

    public Set<Entity> getScopeOf(Entity entity) {
        if (!entities.contains(entity)) throw new EntityNotInEnvironmentException("Сущность не находится в окружении");
        return entities.stream()
                .filter(e -> entity.getLocation().equals(e.getLocation()))
                .filter(e -> !entity.equals(e))
                .collect(Collectors.toSet());
    }

    public void movableGoTo(Movable who, Entity to) {
        if (!entities.contains(who) || !entities.contains(to))
            throw new EntityNotInEnvironmentException("Сущность не находится в окружении");
        System.out.printf("%s направляется к %s\n", who.getName(), to.getName());
        who.setLocation(to.getLocation());
    }

    public void printScopeOf(Entity entity) {
        System.out.printf(
                "В зоне видимости %s находится: %s\n",
                entity,
                getScopeOf(entity).stream()
                        .map(Entity::toString)
                        .collect(Collectors.joining(", "))
        );
    }

    public Set<WeatherPhenomenon> getWeatherPhenomena() {
        return weatherPhenomena;
    }
}
