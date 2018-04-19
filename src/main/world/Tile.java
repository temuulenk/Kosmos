package main.world;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Tile {

    private int row;
    private int col;
    private int x;
    private int y;

    private int id;

    private Image tile;

    private static boolean loadedSpriteSheet = false;
    private static SpriteSheet tileSheet;

    public Tile(int row, int col, int id) {
        this.row = row;
        this.col = col;
        this.x = col * 32;
        this.y = row * 32;

        this.id = id;

        if(!loadedSpriteSheet) {
            try {
                tileSheet = new SpriteSheet(new Image("res/tilemap/tilemap.png"), 32, 32, 2);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            loadedSpriteSheet = true;
        }

    }

    public void draw() {
        if(id == 1 && tile != null) {
            tile.draw(x, y);
        }
    }

    public void initialize(Tile[][] map) {
        int sum = 0;
        if(isValid(map, row - 1, col) && map[row - 1][col].isInteractable()) sum += 1;
        if(isValid(map, row, col + 1) && map[row][col + 1].isInteractable()) sum += 2;
        if(isValid(map, row + 1, col) && map[row + 1][col].isInteractable()) sum += 4;
        if(isValid(map, row, col - 1) && map[row][col - 1].isInteractable()) sum += 8;

        if(sum == 15) {
            if(isValid(map, row - 1, col - 1) && !map[row - 1][col - 1].isInteractable()) tile = tileSheet.getSubImage(15, 2);
            else if(isValid(map, row - 1, col + 1) && !map[row - 1][col + 1].isInteractable()) tile = tileSheet.getSubImage(15, 3);
            else if(isValid(map, row + 1, col - 1) && !map[row + 1][col - 1].isInteractable()) tile = tileSheet.getSubImage(15, 4);
            else if(isValid(map, row + 1, col + 1) && !map[row + 1][col + 1].isInteractable()) tile = tileSheet.getSubImage(15, 5);
            else tile = tileSheet.getSubImage(15, 1);
        }
        else {
            tile = tileSheet.getSubImage(sum, 1);
        }

    }

    private boolean isValid(Tile[][] map, int row, int col) {
        return row >= 0 && row < map.length && col >= 0 && col < map[0].length;
    }

    private boolean isInteractable() {
        return id != 0;
    }

    public int getId() {
        return id;
    }

}
