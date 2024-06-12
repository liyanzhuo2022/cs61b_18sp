package byog.Core;

import byog.TileEngine.TETile;
import byog.lab5.Position;

import java.io.Serializable;
import java.util.Random;

import static byog.Core.Game.HEIGHT;
import static byog.Core.Game.WIDTH;
import static byog.TileEngine.Tileset.*;

public class PlayerWithInput implements Serializable {

    private static final long serialVersionUID = 122122122456L;

    Position current;
    int score;

    public PlayerWithInput() {
        this.current = new Position(0, 0);
        this.score = 0;
    }


    /**Initialize the Player's position (at the new game).
     * The method would put the Player on the 1st floor TETile and show on the screen*/
    public void initialPlayer(TETile[][] world) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (world[i][j] == FLOOR) {
                    world[i][j] = PLAYER;
                    current = new Position(i, j);
                    return;
                }
            }
        }
    }


    /**The player must be able to control some sort of entity
     * that can moved around using the W, A, S, and D keys.
     * The player could only walk on the FLOOR.(or, change it into FLOOR.)
     * @param world the state of the world, which will be mutated
     *              and shown on the screen
     * */
    public void playerWalk(TETile[][] world, char[] command) {
        Position next;

        for (char input: command) {
            System.out.println(input);
            input = Character.toLowerCase(input);
            next = getNextPos(input, world);

            if (world[next.x][next.y] == SAND) {
                playerTrans(world);
            } else if (world[next.x][next.y] == LOCKED_DOOR) {
                if (score >= 3) {
                    GUI.gameWin();
                }
            } else if (world[next.x][next.y] != WALL) {
                TETile tile = world[next.x][next.y];
                moveToNext(world, next);
                updateScores(tile);
            }
        }
    }


    /**The entity must be able to interact with the world in some way.
     * creative mechanics 1: player will get or lose scores depends on
     * what she touches.
     * When the player touches something special, this method will
     * record the current Scores and show them on the screen.
     * Also, the thing got touched became FLOOR.
     * */
    private void updateScores(TETile tile) {
        if (tile == TREE) {
            score += 1;
        }
        if (tile == FLOWER) {
            score -= 1;
        }
        if (score < -1) {
            System.out.println("You Lose!");
            System.exit(0);
        }
        if (score >= 4) {
            System.out.println("You Win!");
            System.exit(0);
        }

    }

    /**creative mechanics 1: Transport the Player to a random FLOOR tile
     * if the Player touches the SAND.
     */
    private void playerTrans(TETile[][] world) {
        int numOfFloors = 0;
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (world[i][j] == FLOOR) {
                    numOfFloors++;
                }
            }
        }

        Position next = setRandomTile(world, numOfFloors, FLOOR, PLAYER);
        moveToNext(world, next);
    }


    // A helper method to return the next position of the Player.
    // Also save when getting Q.
    private Position getNextPos(char input, TETile[][] world) {
        Position newPos = new Position(current.x, current.y);

        if (input == 'w') {
            newPos.y += 1;
        }
        if (input == 's') {
            newPos.y -= 1;
        }
        if (input == 'a') {
            newPos.x -= 1;
        }
        if (input == 'd') {
            newPos.x += 1;
        }

        if (input == 'q') {
            GameState gameState = new GameState(world, this);
            Game.saveGame(gameState);
            System.out.println("Player Position: " + current.x + "," + current.y);
        }

        return newPos;
    }


    /**This is a helper method, it moves the player from current position to
     * the next position, and changes the current position into FLOOR. It also updates
     * the current position.
     */
    private void moveToNext(TETile[][] world, Position next) {
        world[next.x][next.y] = PLAYER;
        if (next.x != current.x || next.y != current.y) {
            world[current.x][current.y] = FLOOR;
        }
        current = next;
    }

    // A helper method very similar to Map.setRandomTile.
    // However, it returns the next Position of the Random Tile instead.
    private Position setRandomTile(TETile[][] world, int numOfBase,
                                   TETile typeOfBase, TETile typeToChange) {
        Random random = new Random();
        Position next = new Position(0, 0);

        int index = random.nextInt(numOfBase);
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (world[i][j] == typeOfBase) {
                    if (index == 0) {
                        world[i][j] = typeToChange;
                        next = new Position(i, j);
                    }
                    index--;
                }
            }
        }

        if (next.x == 0 && next.y == 0) {
            throw new NullPointerException("Run out FLOOR Tile");
        }

        return next;
    }

}

