package softwaretesting.lab1;

import softwaretesting.lab1.model.*;

public class ThirdTask {
    private ThirdTask() {}

    public static void run() {
        Arthur arthur = new Arthur();
        Bulldozer bulldozer = new Bulldozer();
        ArthurHouse arthurHouse = new ArthurHouse();
        Environment environment = new Environment(
                new Temperature(),
                new Weather(),
                arthur, arthurHouse, bulldozer
        );

        bulldozer.demolish(arthurHouse);                            // бульдозер сносит дом артура
        arthur.runTo(arthurHouse);                                  // артур бежит к дому
        environment.addTemperature(-5);                             // становится холодно
        environment.addWeatherPhenomena(WeatherPhenomena.WINDY);    // подул ветер
        environment.addWeatherPhenomena(WeatherPhenomena.RAINY);    // пошел дождь
        bulldozer.compact(arthurHouse);                             // бульдозер УпЛоТнЯеТ дом артура
        arthur.printScope();                                        // что видит артур?
    }
}
