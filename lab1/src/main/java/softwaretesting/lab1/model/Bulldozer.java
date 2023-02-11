package softwaretesting.lab1.model;

public class Bulldozer extends Movable {
    private final int tons;
    private final int speed;

    public Bulldozer(String name, Location location, int tons, int speed) {
        super(name, location);
        this.tons = tons;
        this.speed = speed;
    }

    public void demolish(Building building) {
        System.out.printf("%s разрушает %s\n", this.getName(), building.getName());
        int prevHp = building.getHp();
        building.changeHpBy(-tons * speed);
        System.out.printf(
                "%s наностит %s урона. Здоровье %s: %s -> %s\n",
                this.getName(),
                tons * speed,
                building.getName(),
                prevHp,
                building.getHp()
        );
    }

    public void compact(Building building) {
        if (building.getHp() > 0) {
            System.out.printf("%s не может ползать по %s. Здание не разрушено до конца\n", this.getName(), building.getName());
        } else {
            System.out.printf("%s ползает по %s\n", this.getName(), building.getName());
        }
    }
}
