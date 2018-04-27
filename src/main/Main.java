package main;

import main.states.Play;
import main.states.Testing;
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
//        addState(new Play(0));
        addState(new Testing(1));
        enterState(1);
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Main("Game"));
//        app.setDisplayMode(1280, 768, false);
        app.setDisplayMode(800, 800, false);
        app.setTargetFrameRate(60);
//        app.setAlwaysRender(true);
        app.setShowFPS(false);
        app.setVSync(true);
        app.start();
    }

}
