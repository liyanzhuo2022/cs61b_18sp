package byog.Core;

import byog.TileEngine.TETile;

public class PlayWithInputTest {

    public static void main(String[] args) {

        // stringTest("n123sswwdasdassadwas");
        // stringTest("n123sss:q");

    }

    public static void stringTest(String input) {
        Game game = new Game();
        TETile[][] world = game.playWithInputString(input);
        game.ter.renderFrame(world);
    }
}
