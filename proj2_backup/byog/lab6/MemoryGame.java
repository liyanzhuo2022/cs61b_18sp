package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        Random random = new Random(seed);
        MemoryGame game = new MemoryGame(40, 40, random);
        game.startGame();
    }


    public MemoryGame(int width, int height, Random random) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        //Initialize random number generator
        this.rand = random;
    }

    // generate a random string at the length of n
    public String generateRandomString(int n) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int index = rand.nextInt(26);
            result.append(CHARACTERS[index]);
        }
        return result.toString();
    }

    // draw the StdDraw frame with GUI, put String s at the middle
    public void drawFrame(String s) {
        //Take the string and display it in the center of the screen
        //If game is not over, display relevant game information at the top of the screen
        StdDraw.clear(Color.BLACK);

        // draw GUI
        if (!gameOver) {
            drawGUI();
        }

        // draw text String s at the middle
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(width / 2, height / 2, s);
        StdDraw.show();
    }

    // draw the GUI of the game, with different round and guidance
    public void drawGUI() {
        String s1 = "Round: " + round;
        String s2;
        if (playerTurn) {
            s2 = "Type!";
        } else {
            s2 = "Watch!";
        }
        int index = round % ENCOURAGEMENT.length;
        String s3 = ENCOURAGEMENT[index];

        Font smallFont = new Font("Monaco", Font.BOLD, 20);
        StdDraw.setFont(smallFont);
        StdDraw.setPenColor(StdDraw.WHITE);
        //StdDraw.clear(Color.BLACK);
        StdDraw.textLeft(1, height - 1, s1);
        StdDraw.text(width / 2, height - 1, s2);
        StdDraw.textRight(width - 1, height - 1, s3);
        StdDraw.line(0, height - 2, width, height - 2);
        StdDraw.show();
    }

    //Display each character in letters, making sure to blank the screen between letters
    public void flashSequence(String letters) {

        char[] str = letters.toCharArray();

        for (char letter : str) {
            drawFrame(String.valueOf(letter));
            StdDraw.pause(1000);
            drawFrame("");
            StdDraw.pause(500);
        }

        StdDraw.show();
    }

    // get the user input String and show it on the screen
    public String solicitNCharsInput(int n) {
        StringBuilder input = new StringBuilder();
        drawFrame("");
        // StdDraw's methods keeps tracking the input of the user
        while (input.length() < n) {
            if (StdDraw.hasNextKeyTyped()) {
                input.append(StdDraw.nextKeyTyped());
                drawFrame(input.toString());
            }
        }
        StdDraw.pause(1000);
        return input.toString();
    }

    // the Game Loop
    public void startGame() {
        //Set any relevant variables before the game starts
        //Establish Game loop
        round = 1;
        gameOver = false;

        while(!gameOver) {
            drawFrame("Round: " + round);
            StdDraw.pause(1000);
            drawFrame("");
            StdDraw.pause(500);

            playerTurn = false;
            String s = generateRandomString(round);
            flashSequence(s);
            playerTurn = true;
            String input = solicitNCharsInput(round);

            if (s.equals(input)) {
                drawFrame("Correct, well done!");
                StdDraw.pause(1500);
                round++;
            } else {
                gameOver = true;
                drawFrame("Game Over! You made it to round: " + round);
                StdDraw.pause(2000);
                StdDraw.clear(Color.BLACK);
                StdDraw.show();
                StdDraw.pause(500);
            }
        }

    }

}
