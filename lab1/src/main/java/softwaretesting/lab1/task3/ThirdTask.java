package softwaretesting.lab1.task3;

import softwaretesting.lab1.task3.model.*;

import java.util.Collections;
import java.util.HashSet;

public class ThirdTask {
    private ThirdTask() {}

    public static void run() {
        Location arthurHouseLocation = new Location("улица Пушкина дом Артура");
        Location pushkinStreetLocation = new Location("улица Пушкина");

        Human arthur = new Human("Артур", pushkinStreetLocation);
        Bulldozer bulldozer = new Bulldozer("Caterpillar D6R2", pushkinStreetLocation, 8, 15);
        House arthurHouse = new House("дом Артура", arthurHouseLocation, 100, arthur);

        Environment environment = new Environment(
                25,
                new HashSet<>(Collections.singleton(WeatherPhenomenon.CLOUDY)),
                arthur, arthurHouse, bulldozer
        );

        bulldozer.goTo(arthurHouse);                                // бульдозер едет к дому артура
        arthur.goTo(arthurHouse);                                   // артур бежит к дому
        environment.changeTemperatureBy(-5);                // становится холодно
        environment.addWeatherPhenomenon(WeatherPhenomenon.WINDY);  // подул ветер
        environment.addWeatherPhenomenon(WeatherPhenomenon.RAINY);  // пошел дождь
        bulldozer.demolish(arthurHouse);                            // бульдозер сносит дом артура
        bulldozer.compact(arthurHouse);                             // бульдозер УпЛоТнЯеТ дом артура
        environment.printScopeOf(arthur);                           // что видит артур?
    }
}
