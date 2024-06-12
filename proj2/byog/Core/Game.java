package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Game implements Serializable {
    private static final long serialVersionUID = 123123123L;

    private transient TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        Game game = new Game();
        ter.initialize(Game.WIDTH, Game.HEIGHT);

        GUI.drawMenu();
        StdDraw.pause(1000);

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();

                if (c == 'N' || c == 'n') {
                    playWithSeed(game);
                }
                if (c == 'L' || c == 'l') {
                    GameState gameState = loadGame();
                    playWithGameState(gameState);
                }

            }
        }
    }


    /**A helper method of playWithKeyboard().
     * When the user presses N, a new game starts.
     * */
    private void playWithSeed(Game game) {

        StringBuilder numbers = new StringBuilder();

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                if (Character.isDigit(c)) {
                    numbers.append(c);
                } else {
                    TETile[][] world = game.generateWorldWithSeed(String.valueOf(numbers));
                    Player player = new Player();
                    GameState gameState = new GameState(world, player);
                    playWithGameState(gameState);
                }
            }
        }
    }

    private TETile[][] generateWorldWithSeed(String numbers) {
        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                finalWorldFrame[x][y] = Tileset.NOTHING;
            }
        }

        long seed = numbers.hashCode();
        Random random = new Random(seed);
        Map.drawMap(random, finalWorldFrame);

        return finalWorldFrame;
    }

    private void playWithGameState(GameState gameState) {
        ter.renderFrame(gameState.world);
        StdDraw.enableDoubleBuffering();
        gameState.player.initialPlayer(gameState.world, ter);
        gameState.player.playerWalk(gameState.world, ter);
    }


    private static GameState loadGame() {
        File f = new File("./game.ser");
        if (f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                GameState gameState = (GameState) os.readObject();
                os.close();
                return gameState;
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
                System.exit(0);
            }
        }

        /* In the case no Game has been saved yet, we return a new one. */
        int seedDefault = 123;
        Game game = new Game();
        TETile[][] world = game.generateWorldWithSeed(String.valueOf(seedDefault));
        Player player = new Player();
        GameState gameState = new GameState(world, player);
        return gameState;
    }


    /**A helper method of playWithKeyboard().
     * When the user presses Q, save the current game into a file.
     * */
    public static void saveGame(GameState gameSate) {

        File f = new File("./game.ser");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(gameSate);
            os.close();
        }  catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    }


    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the finalWorldFrame that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                finalWorldFrame[x][y] = Tileset.NOTHING;
            }
        }

        char[] inputArr = input.toCharArray();
        char[] command = CommandExtractor.extractCommand(input);

        if (inputArr[0] == 'n' || inputArr[0] == 'N') {
            String numbers = extractNumbers(input);
            long seed = numbers.hashCode();
            Random random = new Random(seed);
            Map.drawMap(random, finalWorldFrame);

            PlayerWithInput playerWithInput = new PlayerWithInput();
            playerWithInput.initialPlayer(finalWorldFrame);
            playerWithInput.playerWalk(finalWorldFrame, command);
        }

        if (inputArr[0] == 'l' || inputArr[0] == 'L') {
            GameState gameState = loadGame();
            finalWorldFrame = gameState.world;
            PlayerWithInput playerWithInput = gameState.playerWithInput;
            playerWithInput.initialPlayer(finalWorldFrame);
            playerWithInput.playerWalk(finalWorldFrame, command);
        }

        return finalWorldFrame;
    }


    /** A helper method of playWithInputString to get the seed from input string.
     * It extracts a group of numbers from the input String and return them as a String.
     * @param input the input String of playWithInputString
     * @return the group of numbers in the input String as a String
     * */
    private static String extractNumbers(String input) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.group();
        }
        return "No number found";
    }
}
