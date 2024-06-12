package byog.Core;

import byog.TileEngine.TETile;
import byog.lab5.Position;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

import static byog.Core.Game.HEIGHT;
import static byog.Core.Game.WIDTH;
import static byog.TileEngine.Tileset.*;


public class Map implements Serializable {
    private static final long serialVersionUID = 121121121L;

    /** Method used to generate a World Map following the requirements in Phase 1.
     * @param random the Random object generate by the user input
     * @param world the 2D TETile[][] representing the worldFrame at the beginning,
     *              which will be mutated into the World Map.
     * */
    public static void drawMap(Random random, TETile[][] world) {
        int numOfRooms = random.nextInt(45) + 20;
        int numCopy = numOfRooms;
        Room[] rooms = new Room[numOfRooms];
        int index = 0;
        while (numOfRooms > 0) {
            Room room = Room.drawOneRoom(random, world);
            if (room != null) {
                rooms[index] = room;
                numOfRooms--;
                index++;
            }
        }

        RoomComparator comparator = new RoomComparator();
        Arrays.sort(rooms, comparator);

        connectRooms(rooms, numCopy, world);
        addSpecialTile(random, world);
    }

    // a helper method used to connect all the rooms
    // given an array of rooms, connect the adjacent rooms
    private static void connectRooms(Room[] rooms, int n, TETile[][] world) {
        for (int i = 1; i < n; i++) {
            connectTwoRooms(rooms[i - 1], rooms[i], world);
        }
    }

    // a helper method used to connect two rooms by hallways
    private static void connectTwoRooms(Room r1, Room r2, TETile[][] world) {
        Position p1 = r1.connect;
        Position p2 = r2.connect;
        Hallways.drawHallway(p1, p2, world);
    }

    /**Generate some special TETile on the World Map.
     * The place of the tiles must be random.
     * 1. 1 Locked Door on the wall
     * 2. 5 Trees on the Floor
     * 3. 4 Flowers on the Floor
     * 4. 2 Sands on the Floor
     * */
    private static void addSpecialTile(Random random, TETile[][] world) {
        // calculate the number of floors and walls
        int numOfFloors = 0;
        int numOfWalls = 0;
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (world[i][j] == FLOOR) {
                    numOfFloors++;
                }
                if (world[i][j] == WALL) {
                    numOfWalls++;
                }
            }
        }

        // generate the Door
        numOfWalls = setRandomTile(random, world, numOfWalls, WALL, LOCKED_DOOR);


        // generate the Trees
        for (int i = 0; i < 5; i++) {
            numOfFloors = setRandomTile(random, world, numOfFloors, FLOOR, TREE);
        }

        // generate the flowers
        for (int i = 0; i < 4; i++) {
            numOfFloors = setRandomTile(random, world, numOfFloors, FLOOR, FLOWER);
        }

        // generate the sands
        for (int i = 0; i < 2; i++) {
            numOfFloors = setRandomTile(random, world, numOfFloors, FLOOR, SAND);
        }

    }

    /** This is a helper method for addSpecialTile.
     *  The method will put a designated TETile type (such as TREE, SAND) on the Base
     *  (such as FLOOR, WALL), in random place.
     *  It picks the random place by generate an integer (i) by Random object,
     *  and put it in the ith index of the Base.
     * @param random the Random object used through the Game.
     * @param world the world state, which will be mutated.
     * @param numOfBase the current number of the Base tile.
     * @param typeOfBase the TETile type of the Base
     * @param typeToChange the designated TETile type that the user wants
     *                     to change the base tile into.
     * @return return the current number of Base after change one base tile into new type.
     * */
    private static int setRandomTile(Random random, TETile[][] world, int numOfBase,
                                     TETile typeOfBase, TETile typeToChange) {
        int index = random.nextInt(numOfBase);
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (world[i][j] == typeOfBase) {
                    if (index == 0) {
                        world[i][j] = typeToChange;
                    }
                    index--;
                }
            }
        }
        return numOfBase - 1;
    }

}
