package byog.Core;
import java.util.Arrays;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;


/** Phase 2 Test */

public class PlayWithInputTest {

    public static void main(String[] args) {
        compareWorlds("n123ssswww", "n123sss:q", "lwww");
        //generateWorld("n123sswwdasdassadwas");
        //generateWorld("n123www");
        //generateWorld("lwww");
    }

    //Generate a World for me to do visual tests.
    public static void generateWorld(String input) {
        Game game = new Game();
        TETile[][] world = game.playWithInputString(input);
        showWorld(world);
    }

    /** The method tests whether the playWithInputString method could generate the
     *  same TETile[][] worlds when takes in Strings that contain the same seed numbers.
     *  It will print out whether the playWithInputString method passes the test.
     * @param input1 the first Input String
     * @param input2 the second Input String
     * */
    public static void compareWorlds(String input1, String input2, String input3) {
        Game game1 = new Game();
        TETile[][] world1 = game1.playWithInputString(input1);
        //showWorld(world1);

        Game game2 = new Game();
        TETile[][] world2 = game2.playWithInputString(input2);
        //showWorld(world2);

        TETile[][] world3 = game2.playWithInputString(input3);
        //showWorld(world3);

        if (Arrays.deepEquals(world1, world3)) {
            System.out.println("Test Passed: Both inputs produce the same world.");
        } else {
            System.out.println("Test Failed: The worlds are different.");
        }

    }

    /** The method would draw the input TETile[][] world on the screen.
     *  This is a visual test.
     * */
    public static void showWorld(TETile[][] world) {
        TERenderer ter = new TERenderer();
        ter.initialize(Game.WIDTH, Game.HEIGHT);
        ter.renderFrame(world);
    }

}
