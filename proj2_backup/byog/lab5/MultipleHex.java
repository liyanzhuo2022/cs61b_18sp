package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class MultipleHex {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static void addRandomHex(TETile[][] world, Position p, int size) {
        int random = new Random().nextInt(5);
        TETile type;
        switch (random) {
            case 0:
                type = Tileset.WALL;
                break;
            case 1:
                type = Tileset.FLOWER;
                break;
            case 2:
                type = Tileset.GRASS;
                break;
            case 3:
                type = Tileset.SAND;
                break;
            case 4:
                type = Tileset.TREE;
                break;
            default:
                type = Tileset.TREE;
                break;
        }
        Hexagon.addHexagon(world, p, size, type);
    }

    public static void addMultipleHex(TETile[][] world, Position p, int size) {
        for (int Row = 0; Row < 5; Row++) {
            int lineMax = LineMax(Row);
            for (int Line = 0; Line < lineMax; Line++) {
                int x = p.x + XOffset(Row, size);
                int y = p.y + YOffeset(Row, Line, size);
                Position newHex = new Position(x, y);
                addRandomHex(world, newHex, size);
            }
        }
    }

    private static int LineMax(int Row) {
        int max;
        switch (Row) {
            case 0: return 3;
            case 1: return 4;
            case 2: return 5;
            case 3: return 4;
            case 4: return 3;
            default: return 3;
        }
    }

    private static int XOffset(int Row, int size) {
        return Row * (size * 2 - 1);
    }

    private static int YOffeset(int Row, int Line, int size) {
        int lineMax = LineMax(Row);
        return (lineMax - 3) * (- size) + size * 2 * Line;
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        Position p = new Position(5, 10);
        addMultipleHex(world, p, 3);

        ter.renderFrame(world);
    }

}
