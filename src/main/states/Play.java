package main.states;

import main.world.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Play extends BasicGameState {

    private int id;


    private World world;

    public Play(int id) {
        this.id = id;
    }


    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        container.getGraphics().setBackground(Color.decode("#3095FC"));

        world = new World(container);

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        world.draw();
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

    }


    @Override
    public int getID() {
        return 0;
    }


}
