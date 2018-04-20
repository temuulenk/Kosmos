package main;

import main.states.Play;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {


    public Main(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        addState(new Play(0));
        enterState(0);
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Main("Game"));
        app.setDisplayMode(1280, 768, false);
        app.setTargetFrameRate(60);
        app.setAlwaysRender(true);
        app.setShowFPS(true);
        app.start();
    }

}
