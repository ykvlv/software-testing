package softwaretesting.lab1.task3.model;

public enum WeatherPhenomenon {
    WINDY("ветренно"), RAINY("дождливо"), CLOUDY("облачно"), SUNNY("солнечно");
    private final String name;

    WeatherPhenomenon(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
