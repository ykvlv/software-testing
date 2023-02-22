package softwaretesting.lab1.task3.model;

import softwaretesting.lab1.task3.model.exception.CantCompactBuildingException;

public class Bulldozer extends Movable {
    private final int tons;
    private final int speed;

    public Bulldozer(String name, Location location, int tons, int speed) {
        super(name, location);
        this.tons = tons;
        this.speed = speed;
    }

    public void demolish(Building building) {
        int damage = tons * speed;
        System.out.printf(
                "%s наносит %s урона %s\n",
                this.getName(),
                damage,
                building.getName()
        );
        building.damage(damage);
    }

    public void compact(Building building) {
        if (!building.isDestroyed())
            throw new CantCompactBuildingException(
                    String.format(
                            "%s не может ползать по %s. Здание не разрушено до конца\n",
                            this.getName(),
                            building.getName()
                    )
            );
        System.out.printf("%s ползает по %s\n", this.getName(), building.getName());
    }
}
