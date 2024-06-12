package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.lab5.Position;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Font;
import java.io.Serializable;
import java.util.Random;

import static byog.Core.Game.HEIGHT;
import static byog.Core.Game.WIDTH;
import static byog.TileEngine.Tileset.*;

public class Player implements Serializable {
    private static final long serialVersionUID = 122122122L;

    Position current;
    int score;

    public Player() {
        this.current = new Position(0, 0);
        this.score = 0;
    }


    public Player(Position current, int score) {
        this.current = current;
        this.score = score;
    }


    /**Initialize the Player's position (at the new game).
     * The method would put the Player on the 1st floor TETile and show on the screen*/
    public void initialPlayer(TETile[][] world, TERenderer ter) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (world[i][j] == FLOOR) {
                    world[i][j] = PLAYER;
                    current = new Position(i, j);
                    ter.renderFrame(world);
                    drawScores(world, ter);
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
    public void playerWalk(TETile[][] world, TERenderer ter) {
        char input;
        Position next;

        while (true) {
            GUI.drawDescription(world);
            drawScores(world, ter);

            if (StdDraw.hasNextKeyTyped()) {
                input = StdDraw.nextKeyTyped();
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
                    updateScores(world, tile, ter);
                }

                ter.renderFrame(world);
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

    private void updateScores(TETile[][] world, TETile tile, TERenderer ter) {
        if (tile == TREE) {
            score += 1;
            drawScores(world, ter);
        }
        if (tile == FLOWER) {
            score -= 1;
            drawScores(world, ter);
        }
        if (score < -1) {
            GUI.gameLose();
        }
        if (score >= 4) {
            GUI.gameWin();
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
    // Also save and quit the game when getting Q.
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
            System.exit(0);
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


    /**Heads Up Display 2
     * The method displays the current Scores of the Player.
     * */
    public void drawScores(TETile[][] world, TERenderer ter) {

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledRectangle(Game.WIDTH / 4 * 3, Game.HEIGHT - 2, Game.WIDTH / 4, 2);

        StdDraw.setPenColor(StdDraw.WHITE);
        Font font = new Font("Monaco", Font.PLAIN, 20);
        StdDraw.setFont(font);
        StdDraw.text(Game.WIDTH / 4 * 3 + 4, Game.HEIGHT - 2, "SCORE: " + score);

        StdDraw.show();
    }



}
