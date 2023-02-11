package softwaretesting.lab1.model;

public abstract class Building extends Entity {
    private int hp;

    public Building(String name, Location location, int hp) {
        super(name, location);
        this.hp = hp;
    }

    public void changeHpBy(int number) {
        this.hp += number;
        if (this.hp < 0) this.hp = 0;
    }

    public int getHp() {
        return hp;
    }
}
