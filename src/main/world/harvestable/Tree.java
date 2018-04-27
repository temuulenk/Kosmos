package main.world.harvestable;

import main.resources.Resource;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tree extends Harvestable {

    public Tree() {
        super(0);

        loot.addResource(Resource.WOOD, 3, 5, 1);

        try {
            image = new Image("res/harvestable/tree.png");
        }catch(SlickException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void draw() {
        image.draw(x, y);
    }

    @Override
    public void harvest() {

    }

    @Override
    public void onDeath() {
        
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x + 2;
        this.y = y - image.getHeight() + 25;
    }

}
