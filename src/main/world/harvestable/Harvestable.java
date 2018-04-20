package main.world.harvestable;

import org.newdawn.slick.Image;

public abstract class Harvestable {

    protected int id;

    protected Image image;

    protected int x;
    protected int y;

    public Harvestable(int id) {
        this.id = id;
    }

    public abstract void harvest();
    public abstract void draw();

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

}
