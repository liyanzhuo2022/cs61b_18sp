package byog.Core;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.Position;

import java.util.Arrays;
import java.util.Random;

public class Room {
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

    public static void drawMultipleRooms(Random random, TETile[][] world) {
        int numOfRooms = random.nextInt(45) + 20;
        int numcp = numOfRooms;
        Room[] rooms = new Room[numOfRooms];
        int index = 0;
        while (numOfRooms > 0) {
            Room room = drawOneRoom(random, world);
            if (room != null) {
                rooms[index] = room;
                numOfRooms--;
                index++;
            }
        }

        RoomComparator comparator = new RoomComparator();
        Arrays.sort(rooms, comparator);

        connectRooms(rooms, numcp, world);
    }

    private static void connectRooms(Room[] rooms, int n, TETile[][] world) {
        for (int i = 1; i < n; i++) {
            connectTwoRooms(rooms[i - 1], rooms[i], world);
        }
    }

    private static void connectTwoRooms(Room r1, Room r2, TETile[][] world) {
        Position p1= r1.connect;
        Position p2 = r2.connect;
        Hallways.drawHallway(p1, p2, world);
    }


    private static Room drawOneRoom(Random random, TETile[][] world) {
        int width = random.nextInt(6) + 1;
        int length = random.nextInt(6) + 1;
        int x = random.nextInt(Game.WIDTH - width - 1) + 1;
        int y = random.nextInt(Game.HEIGHT - length - 1) + 1;
        Position p = new Position(x, y);
        int cx = random.nextInt(width) + p.x;
        int cy = random.nextInt(length) + p.y;
        Position connect = new Position(cx, cy);

        if (checkOverLap(width, length, p, world) == false) {
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
