package byog.lab5;
import byog.TileEngine.TETile;

public class Hexagon {


    public static void addHexagon(TETile[][] world, Position p, int size, TETile type) {
        if (size < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }

        addLower(p.x, p.y, size, world, type);
        addUpper(p.x, p.y + size * 2 - 1, size, world, type);
    }

    private static void addLower(int x, int y, int size, TETile[][] world, TETile type) {
        for (int i = 0; i < size; i++) {
            for (int j = -i; j < size + i; j++) {
                setTile(world, x + j, y + i, type);
            }
        }
    }

    private static void addUpper(int x, int y, int size, TETile[][] world, TETile type) {
        for (int i = 0; i < size; i++) {
            for (int j = -i; j < size + i; j++) {
                setTile(world, x + j, y - i, type);
            }
        }
    }

    private static void setTile(TETile[][] world, int x, int y, TETile type) {
        if (x >= 0 && x < world.length && y >= 0 && y < world[0].length) {
            world[x][y] = type;
        }
    }


}
