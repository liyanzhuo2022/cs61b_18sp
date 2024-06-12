package byog.Core;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.Position;

import java.io.Serializable;
import java.util.Random;

public class Room implements Serializable {
    private static final long serialVersionUID = 1231231231234L;
    int width;
    int length;
    Position p;
    Position connect;

    public Room(int width, int length, Position p, Position connect) {
        this.width = width;
        this.length = length;
        this.p = p;
        this.connect = connect;
    }

    public int getPx() {
        return p.x;
    }

    /** Calling the method will draw a non-overlap room with floor and wall, and return the room.
     * @param random the Random object generate by the user input
     * @param world the 2D TETile[][] representing the world state,
     *              which will be mutated with an additional room.
     * @return the room object that is drawn by the method
     * */
    public static Room drawOneRoom(Random random, TETile[][] world) {
        int width = random.nextInt(6) + 1;
        int length = random.nextInt(6) + 1;
        int x = random.nextInt(Game.WIDTH - width - 1 - 5) + 1; // leave some space for HUD
        int y = random.nextInt(Game.HEIGHT - length - 1 - 5) + 1;
        Position p = new Position(x, y);
        int cx = random.nextInt(width) + p.x;
        int cy = random.nextInt(length) + p.y;
        Position connect = new Position(cx, cy);

        if (!checkOverLap(width, length, p, world)) {
            drawFloor(width, length, p, world);
            drawWall(width, length, p, world);
            Room room = new Room(width, length, p, connect);
            return room;
        } else {
            return null;
        }
    }

    private static boolean checkOverLap(int width, int length, Position p, TETile[][] world) {
        for (int i = p.x - 1; i < p.x + width + 1; i++) {
            for (int j = p.y - 1; j < p.y + length + 1; j++) {
                if (world[i][j] != Tileset.NOTHING) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void drawFloor(int width, int length, Position p, TETile[][] world) {
        for (int i = p.x; i < p.x + width; i++) {
            for (int j = p.y; j < p.y + length; j++) {
                world[i][j] = Tileset.FLOOR;
            }
        }
    }

    private static void drawWall(int width, int length, Position p, TETile[][] world) {
        for (int i = p.x - 1; i < p.x + width + 1; i++) {
            for (int j = p.y - 1; j < p.y + length + 1; j++) {
                if (world[i][j] == Tileset.NOTHING) {
                    world[i][j] = Tileset.WALL;
                }
            }
        }

    }


}
