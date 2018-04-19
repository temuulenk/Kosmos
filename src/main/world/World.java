package main.world;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class World {

    private GameContainer container;
    private Input input;

    private int tileSize = 32;
    private int rows = 24;
    private int cols = 40;

    private Tile[][] map;


    private boolean mouseDown = false;
    private int startX = -1;
    private int startY = -1;
    private int endX = -1;
    private int endY = -1;


    public World(GameContainer container) {
        this.container = container;
        this.input = container.getInput();

        map = new Tile[rows][cols];

        for(int row=0; row<map.length; row++) {
            for(int col=0; col<map[0].length; col++) {
                map[row][col] = new Tile(row, col, 0);
            }
        }

        generateArea();

        for(int row=0; row<map.length; row++) {
            for(int col=0; col<map[0].length; col++) {
                map[row][col].initialize(map);
            }
        }

    }

    public void draw() {
        container.getGraphics().setColor(new Color(1, 1, 1, .3F));
        for(int row=0; row<map.length; row++) {
            for(int col=0; col<map[0].length; col++) {
                map[row][col].draw();

//                container.getGraphics().drawLine(col * tileSize + 4, row * tileSize, col * tileSize + 28, row * tileSize);
//                container.getGraphics().drawLine(col * tileSize, row * tileSize + 4, col * tileSize, row * tileSize + 28);

            }
        }

        createUsingMouseArea();


    }

    private void generateArea() {
        for(int row=5; row<15; row++) {
            for(int col=15; col<25; col++) {
                map[row][col] = new Tile(row, col, 1);
            }
        }
    }

    private void createUsingMouseArea() {
        if(startX == -1) {
            if(input.isMouseButtonDown(0)) {
                if(!mouseDown) {
                    startX = input.getMouseX();
                    startY = input.getMouseY();
                    mouseDown = true;
                }
            }
        }

        if(mouseDown) {
            container.getGraphics().setColor(new Color(1, 1, 1));
            container.getGraphics().drawRect(startX, startY, input.getMouseX() - startX, input.getMouseY() - startY);
            if(!input.isMouseButtonDown(0)) {
                endX = input.getMouseX();
                endY = input.getMouseY();
                mouseDown = false;

                int gridx = startX / tileSize;
                int gridy = startY / tileSize;

                int cols = ((endX + tileSize) - (gridx * tileSize)) / tileSize;
                int rows = ((endY + tileSize) - (gridy * tileSize)) / tileSize;

                for(int row=0; row<rows; row++) {
                    for(int col=0; col<cols; col++) {
                        if(isValid(gridy + row, gridx + col)) {
                            map[gridy + row][gridx + col] = new Tile(gridy + row, gridx + col, 1);
                            updateTile(gridy + row, gridx + col);
                        }
                    }
                }
                startX = startY = endX = endY = -1;
            }
        }

        if(input.isMousePressed(1)) {
            int mx = input.getMouseX() / 32;
            int my = input.getMouseY() / 32;
            map[my][mx] = new Tile(my, mx, 0);
            updateTile(my, mx);
        }


    }

    private void updateTile(int row, int col) {
        for(int y=-1; y<=1; y++) {
            for(int x=-1; x<=1; x++) {
                if(isValid(row + y, col + x)) {
                    map[row + y][col + x].initialize(map);
                }
            }
        }
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < map.length && col >= 0 && col < map[0].length;
    }


}
