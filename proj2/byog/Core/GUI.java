package byog.Core;

import byog.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Font;
import java.io.Serializable;

public class GUI implements Serializable {
    private static final long serialVersionUID = 1231231231L;

    /* Method used for drawing the start Main Menu of the Game.* */
    public static void drawMenu() {
        StdDraw.setPenColor(StdDraw.WHITE);

        Font font1 = new Font("Monaco", Font.BOLD, 50);
        StdDraw.setFont(font1);
        StdDraw.text(Game.WIDTH / 2, Game.HEIGHT / 4 * 3, "CS61B: Eating Healthy Plants!");
        Font font2 = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font2);
        StdDraw.text(Game.WIDTH / 2, Game.HEIGHT / 2, "New Game (Press N)");
        StdDraw.text(Game.WIDTH / 2, Game.HEIGHT / 2 - 3, "Load Game (Press L)");
        StdDraw.text(Game.WIDTH / 2, Game.HEIGHT / 2 - 6, "Quit (Press Q)");

        StdDraw.show();
    }

    /**Heads Up Display 1
     * It should:
     * display the description of the tile under the mouse pointer.
     * Also modify Room.java, leave some space for HUD.
     **/
    public static void drawDescription(TETile[][] world) {

        if (StdDraw.mouseX() >= 0 & StdDraw.mouseX() <= Game.WIDTH
                & StdDraw.mouseY() >= 0 & StdDraw.mouseY() <= Game.HEIGHT) {

            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledRectangle(Game.WIDTH / 4, Game.HEIGHT - 2, Game.WIDTH / 4, 2);

            StdDraw.setPenColor(StdDraw.WHITE);
            String tileDesc = world[(int) StdDraw.mouseX()][(int) StdDraw.mouseY()].description();
            Font font = new Font("Monaco", Font.PLAIN, 20);
            StdDraw.setFont(font);
            StdDraw.text(Game.WIDTH / 4 - 4, Game.HEIGHT - 2, tileDesc);

            StdDraw.show();

        }
    }

    /**Creative mechanics 3: player will win the Game if she touches
     * door(a special wall) with >= 3 Scores, or she gets full 4 scores.
     * Print the result on the screen and quit the game.
     */
    public static void gameWin() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        Font font = new Font("Monaco", Font.BOLD, 50);
        StdDraw.setFont(font);
        StdDraw.text(Game.WIDTH / 2, Game.HEIGHT / 2, "You Win!");
        StdDraw.show();
        StdDraw.pause(5000);
        System.exit(0);
    }

    /**Creative mechanics 3: player will lose the Game if scores <= -1.
     * Print the result on the screen and quit the game.
     */
    public static void gameLose() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        Font font = new Font("Monaco", Font.BOLD, 50);
        StdDraw.setFont(font);
        StdDraw.text(Game.WIDTH / 2, Game.HEIGHT / 2, "You Lose!");
        StdDraw.show();
        StdDraw.pause(5000);
        System.exit(0);
    }

}
