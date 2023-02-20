package softwaretesting.lab1.test3;

import org.junit.jupiter.api.Test;
import softwaretesting.lab1.task3.model.Environment;
import softwaretesting.lab1.task3.model.WeatherPhenomenon;

import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class TestThird {

    @Test
    public void tempirature() {
        int startTemperature = 3;
        Environment environment = new Environment(
                startTemperature,
                new HashSet<>(Collections.singleton(WeatherPhenomenon.CLOUDY))
        );
        environment.changeTemperatureBy(-5);
        assertEquals(startTemperature - 5, environment.getTemperature());
        environment.changeTemperatureBy(20);
        assertEquals(startTemperature - 5 + 20, environment.getTemperature());
    }
}
