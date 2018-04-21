package main.world.harvestable;

import main.interfaces.Damageable;
import main.interfaces.Drawable;
import main.resources.Resource;
import org.newdawn.slick.Image;

import java.util.ArrayList;

public abstract class Harvestable implements Drawable, Damageable {

    protected int id;

    protected Image image;

    protected int x;
    protected int y;

    protected int health;

    protected Loot loot;

    public Harvestable(int id) {
        this.id = id;
        this.loot = new Loot();
    }

    public abstract void harvest();

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void dealDamage(Damageable entity) {
        return;
    }

    @Override
    public void takeDamage(Damageable source) {
        health -= 1;
        if(health <= 0) onDeath();
    }

    public int getId() {
        return id;
    }

}
