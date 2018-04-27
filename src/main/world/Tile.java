package main.world;

import main.interfaces.Drawable;
import main.world.harvestable.Harvestable;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Tile implements Drawable {

    private int row;
    private int col;
    private int x;
    private int y;

    private int id;

    private final int tileSize = 32;

    private Image tile;
    private Image additionalAttachedImage;

    private static boolean loadedSpriteSheet = false;
    private static SpriteSheet tileSheet;

    private Harvestable resource;


    public Tile(int row, int col, int id) {
        this.row = row;
        this.col = col;
        this.x = col * tileSize;
        this.y = row * tileSize;

        this.id = id;

        if(!loadedSpriteSheet) {
            try {
                tileSheet = new SpriteSheet(new Image("res/tilemap/tilemap.png"), tileSize, tileSize, 2);
            } catch (SlickException e) {
                e.printStackTrace();
            }
            loadedSpriteSheet = true;
        }

    }

    @Override
    public void draw() {
        if(id == 1) {
            if(tile != null)
                tile.draw(x, y);
            if(additionalAttachedImage != null)
                additionalAttachedImage.draw(x, y + tileSize);
            if(resource != null)
                resource.draw();
        }
    }

    public void initialize(Tile[][] map) {
        int sum = 0;
        
        // North, 2^0
        if(isValid(map, row - 1, col) && map[row - 1][col].isCollidable()) sum += 1;
        
        // East, 2^1
        if(isValid(map, row, col + 1) && map[row][col + 1].isCollidable()) sum += 2;
        
        // South, 2^2
        if(isValid(map, row + 1, col) && map[row + 1][col].isCollidable()) sum += 4;
        
        // West, 2^3
        if(isValid(map, row, col - 1) && map[row][col - 1].isCollidable()) sum += 8;


        // Edge case blocks which requires ground block addition to the bottom
        if(sum == 0) {
            additionalAttachedImage = tileSheet.getSubImage(0, 2);
        }else if(sum == 3) {
            additionalAttachedImage = tileSheet.getSubImage(3, 2);
        }else if(sum == 9) {
            additionalAttachedImage = tileSheet.getSubImage(9, 2);
        }else if(sum == 11) {
            additionalAttachedImage = tileSheet.getSubImage(11, 2);
        }

        // Center tile exception
        if(sum == 15) {
            if(isValid(map, row - 1, col - 1) && isValid(map, row + 1, col + 1) && !map[row - 1][col - 1].isCollidable() && !map[row + 1][col + 1].isCollidable()) tile = tileSheet.getSubImage(15, 6);
            else if(isValid(map, row - 1, col + 1) && isValid(map, row + 1, col - 1) && !map[row - 1][col + 1].isCollidable() && !map[row + 1][col - 1].isCollidable()) tile = tileSheet.getSubImage(15, 7);
            else if(isValid(map, row - 1, col - 1) && !map[row - 1][col - 1].isCollidable()) tile = tileSheet.getSubImage(15, 2);
            else if(isValid(map, row - 1, col + 1) && !map[row - 1][col + 1].isCollidable()) tile = tileSheet.getSubImage(15, 3);
            else if(isValid(map, row + 1, col - 1) && !map[row + 1][col - 1].isCollidable()) tile = tileSheet.getSubImage(15, 4);
            else if(isValid(map, row + 1, col + 1) && !map[row + 1][col + 1].isCollidable()) tile = tileSheet.getSubImage(15, 5);
            else tile = tileSheet.getSubImage(15, 1);
        }
        else {
            tile = tileSheet.getSubImage(sum, 1);
        }

    }

    public void attachResource(Harvestable resource) {
        this.resource = resource;

        resource.setPosition(x, y);

    }

    private boolean isValid(Tile[][] map, int row, int col) {
        return row >= 0 && row < map.length && col >= 0 && col < map[0].length;
    }

    public boolean isCollidable() {
        return id != 0;
    }

    public boolean hasResource() {
        return resource != null;
    }

    public int getId() {
        return id;
    }

}
