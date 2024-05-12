package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

/**
 *  Draws a world that is mostly empty except for a small region.
 */
public class BoringWorldDemo {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        // fills in a block (x?) tiles wide by (y?) tiles tall
        /**
        for (int x = 0; x < 1; x += 1) {
            for (int y = 0; y < 2; y += 1) {
                world[x][y] = Tileset.FLOWER;
            }
        }*/

        Position p = new Position(3, 10);
        TETile type = Tileset.WALL;
        Hexagon.addHexagon(world, p, 6, type);
        // draws the world to the screen
        ter.renderFrame(world);
    }


}
