package softwaretesting.lab1.test3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import softwaretesting.lab1.task3.model.*;
import softwaretesting.lab1.task3.model.exception.CantCompactBuildingException;
import softwaretesting.lab1.task3.model.exception.EntityNotInEnvironmentException;
import softwaretesting.lab1.task3.model.exception.NotInAllowedSetException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {
    static Location arthurHouseLocation;
    static Location pushkinStreetLocation;
    static Human arthur;
    static Bulldozer bulldozer;
    static House arthurHouse;
    static Environment environment;

    @BeforeAll
    public static void createAll() {
        arthurHouseLocation = new Location("улица Пушкина дом Артура");
        pushkinStreetLocation = new Location("улица Пушкина");

        arthur = new Human("Артур", pushkinStreetLocation);
        bulldozer = new Bulldozer("Caterpillar D6R2", pushkinStreetLocation, 8, 15);
        arthurHouse = new House("дом Артура", arthurHouseLocation, 100, arthur);

        environment = new Environment(
                25,
                new HashSet<>(Collections.singleton(WeatherPhenomenon.CLOUDY)),
                arthur, arthurHouse, bulldozer
        );
    }

    @Test
    public void temperature() {
        int startTemperature = environment.getTemperature();

        environment.changeTemperatureBy(-5);
        assertEquals(startTemperature - 5, environment.getTemperature());

        environment.changeTemperatureBy(20);
        assertEquals(startTemperature - 5 + 20, environment.getTemperature());
    }

    @Test
    public void weatherPhenomena() {
        Set<WeatherPhenomenon> startWeatherPhenomena = environment.getWeatherPhenomena();

        environment.addWeatherPhenomenon(WeatherPhenomenon.RAINY);
        startWeatherPhenomena.add(WeatherPhenomenon.RAINY);
        assertEquals(startWeatherPhenomena, environment.getWeatherPhenomena());

        environment.removeWeatherPhenomenon(WeatherPhenomenon.SUNNY);
        startWeatherPhenomena.remove(WeatherPhenomenon.SUNNY);
        assertEquals(startWeatherPhenomena, environment.getWeatherPhenomena());
    }

    @Test
    public void scopeOf() {
        Set<Entity> initialScope = environment.getScopeOf(arthur);

        environment.movableGoTo(bulldozer, arthurHouse);
        initialScope.remove(bulldozer);
        assertEquals(initialScope, environment.getScopeOf(arthur));

        environment.movableGoTo(arthur, arthurHouse);

        Set<Entity> properScope = Set.of(arthurHouse, bulldozer);
        assertEquals(properScope, environment.getScopeOf(arthur));

        Human notInEnvironment = new Human("Ноунейм", new Location("Улица ноунеймов"));

        assertThrows(EntityNotInEnvironmentException.class, () -> {
            environment.movableGoTo(notInEnvironment, arthur);
        });
    }

    @Test
    public void demolishBuilding() {
        bulldozer.demolish(arthurHouse);
        assertTrue(arthurHouse.isDestroyed());

        assertThrows(NotInAllowedSetException.class, () -> {
            arthurHouse.repair(-100);
        });

        arthurHouse.repair(100);

        assertFalse(arthurHouse.isDestroyed());
        assertThrows(CantCompactBuildingException.class, () -> {
            bulldozer.compact(arthurHouse);
        });
    }
}
