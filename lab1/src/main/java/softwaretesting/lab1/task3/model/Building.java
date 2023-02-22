package softwaretesting.lab1.task3.model;

import softwaretesting.lab1.task3.model.exception.NotInAllowedSetException;

public abstract class Building extends Entity {
    private int hp;

    public Building(String name, Location location, int hp) {
        super(name, location);
        this.hp = hp;
    }

    public void damage(int hp) {
        if (hp < 0) throw new NotInAllowedSetException("Урон должен быть больше либо равно нулю");
        int prev = this.hp;
        this.hp = Math.max(0, this.hp - hp);
        System.out.printf(
                "Здание %s получило урон, здоровье %s -> %s\n",
                this.getName(),
                prev,
                this.hp
        );
    }

    public void repair(int hp) {
        if (hp < 0) throw new NotInAllowedSetException("Лечение должно быть больше либо равно нулю");
        int prev = this.hp;
        this.hp += hp;
        if (this.hp < 0) this.hp = Integer.MAX_VALUE;
        System.out.printf(
                "Здание %s получило лечение, здоровье %s -> %s\n",
                this.getName(),
                prev,
                this.hp
        );
    }

    public boolean isDestroyed() {
        return hp <= 0;
    }

}
