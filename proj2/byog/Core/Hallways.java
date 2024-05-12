package byog.Core;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.Position;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Hallways {

    public static void drawHallway(Position p1, Position p2, TETile[][] world) {
        Position mid = new Position(p1.x, p2.y);
        drawStraight(p1, p2, world);
        drawConner(mid, world);
    }

    private static void drawStraight(Position p1, Position p2, TETile[][] world) {
        int yMin = min(p1.y, p2.y);
        int yMax = max(p1.y, p2.y);
        for (int i = yMin; i <= yMax; i++) {
            if (world[p1.x][i] != Tileset.FLOOR) {
                world[p1.x][i] = Tileset.FLOOR;
            }
            if (world[p1.x - 1][i] == Tileset.NOTHING) {
                world[p1.x - 1][i] = Tileset.WALL;
            }
            if (world[p1.x + 1][i] == Tileset.NOTHING) {
                world[p1.x + 1][i] = Tileset.WALL;
            }
        }

        int xMin = min(p1.x, p2.x);
        int xMax = max(p1.x, p2.x);
        for (int i = xMin; i <= xMax; i++) {
            if (world[i][p2.y] != Tileset.FLOOR) {
                world[i][p2.y] = Tileset.FLOOR;
            }
            if (world[i][p2.y - 1] == Tileset.NOTHING) {
                world[i][p2.y - 1] = Tileset.WALL;
            }
            if (world[i][p2.y + 1] == Tileset.NOTHING) {
                world[i][p2.y + 1] = Tileset.WALL;
            }
        }
    }

    private static void drawConner(Position mid, TETile[][] world) {
        drawEmpty(mid.x - 1, mid.y, world);
        drawEmpty(mid.x + 1, mid.y, world);
        drawEmpty(mid.x, mid.y + 1, world);
        drawEmpty(mid.x, mid.y - 1, world);
    }

    private static void drawEmpty(int x, int y, TETile[][] world) {
        if (world[x][y] == Tileset.NOTHING) {
            world[x][y] = Tileset.WALL;
        }
    }


}
