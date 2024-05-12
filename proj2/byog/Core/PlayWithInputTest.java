package byog.Core;

import byog.TileEngine.TETile;

public class PlayWithInputTest {

    public static void main(String[] args) {

        StringTest("45");
    }

    public static void StringTest(String input) {
        Game game = new Game();
        TETile[][] world = game.playWithInputString(input);
        game.ter.renderFrame(world);
    }
}
