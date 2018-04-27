package main.states;

import main.generation.Noise;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Testing extends BasicGameState {


    private int id;

    private Input input;

    private Image platform;
    private Image human;
    private Image rat;

    private float x = 200;
    private float y = 184;

    float defaultX = x;
    float defaultY = y;

    private boolean attack = false;
    private int attackAnimation = 0;
    private int shakeCount = 0;


    float[][] map;

    Color[][] colors;

    int w = 800, h = 800;
    private int scale = 1;

    public Testing(int id) {
        this.id = id;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        container.getGraphics().setBackground(Color.decode("#261B1B"));
        input = container.getInput();


        generate();


    }

    private Color colorLerp(float ratio) {
        return new Color(ratio, ratio, ratio);
    }

    private void generate() {
        float randomScale = (float) Math.random() * 40 + 50;
        randomScale = 400F;
        System.out.println("GENERATE: " + randomScale);
        map = Noise.GenerateNoiseMap(w, h, 0, randomScale, 5, .5F, 2);
//        DecimalFormat df = new DecimalFormat("00.00");
//        for(int row=0; row<map.length; row++) {
//            for(int col=0; col<map[0].length; col++) {
//                System.out.print(df.format(map[row][col]) + " ");
//            }
//            System.out.println();
//        }

        colors = new Color[h][w];
        for(int row=0; row<map.length; row++) {
            for (int col=0; col<map[0].length; col++) {

//                if(map[row][col] < .3)
//                    colors[row][col] = Color.decode("#5A9619");
//                else
//                    colors[row][col] = Color.decode("#3763C3");

                if(map[row][col] < .3)
                    colors[row][col] = Color.decode("#305AAD");
                else if(map[row][col] < .4)
                    colors[row][col] = Color.decode("#3666C7");
                else if(map[row][col] < .45)
                    colors[row][col] = Color.decode("#D1CF7E");
                else if(map[row][col] < .55)
                    colors[row][col] = Color.decode("#5A9619");
                else if(map[row][col] < .65)
                    colors[row][col] = Color.decode("#406B13");
                else if(map[row][col] < .75)
                    colors[row][col] = Color.decode("#5E4440");
                else if(map[row][col] < .9)
                    colors[row][col] = Color.decode("#4B3B37");
                else if(map[row][col] < 1)
                    colors[row][col] = Color.white;


            }
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

//        platform.draw(0, container.getHeight() - platform.getHeight());
//        human.draw(x, y);
//        rat.draw(container.getWidth() - rat.getWidth() - 200, container.getHeight() - platform.getHeight() - rat.getHeight());
//
//        if(input.isKeyPressed(Input.KEY_SPACE) && !attack) {
//            attack = true;
////            human.setImageColor(1, 0, 0);
//        }

        g.setBackground(Color.decode("#3763C3"));

        for(int row=0; row<map.length; row++) {
            for(int col=0; col<map[0].length; col++) {
//                g.setColor(map[row][col] < 0.4 ? Color.black : Color.white);
//                g.setColor(colorLerp(1));
                g.setColor(colors[row][col]);
//                g.setColor(colorLerp(map[row][col]));
                g.fillRect(col * scale, row * scale, scale, scale);
            }
        }

        if(container.getInput().isKeyPressed(Input.KEY_SPACE)) {
            generate();
        }
//
//        System.out.println(container.getFPS());

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

        if(attack) {
            x = container.getWidth() - rat.getWidth() - rat.getWidth() / 2 - 200;
            attackAnimation += delta;
            if(attackAnimation >= 500) {
                x = 200;
                attack = false;
                attackAnimation = 0;
            }
//            if(attackAnimation >= 30) {
//                float shakeX = (int) (Math.random() * 6) - 3;
//                float shakeY = (int) (Math.random() * 6) - 3;
//                x = defaultX;
//                y = defaultY;
//                x += shakeX;
//                y += shakeY;
//                attackAnimation = 0;
//                shakeCount ++;
//            }
//            if(shakeCount == 5)  {
//                attack = false;
//                x = defaultX;
//                y = defaultY;
//                shakeCount = 0;
//                human.setImageColor(1, 1, 1);
//            }
//            attackAnimation += delta;


        }




    }

    @Override
    public int getID() {
        return id;
    }


}
