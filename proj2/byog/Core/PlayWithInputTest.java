package byog.Core;
import java.util.Arrays;
import byog.TileEngine.TETile;

public class PlayWithInputTest {

    public static void main(String[] args) {

        compareWorlds("n123sss", "n123sss:q");

    }

    public static void compareWorlds(String input1, String input2) {
        Game game = new Game();
        TETile[][] world1 = game.playWithInputString(input1);
        TETile[][] world2 = game.playWithInputString(input2);

        if (Arrays.deepEquals(world1, world2)) {
            System.out.println("Test Passed: Both inputs produce the same world.");
        } else {
            System.out.println("Test Failed: The worlds are different.");
        }
    }
}
