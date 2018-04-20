package main.world.harvestable;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tree extends Harvestable {

    public Tree() {
        super(0);

        try {
            image = new Image("res/harvestables/tree.png");
        }catch(SlickException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void draw() {
        image.draw(x, y - image.getHeight() + 24);
    }

    @Override
    public void harvest() {

    }

}
