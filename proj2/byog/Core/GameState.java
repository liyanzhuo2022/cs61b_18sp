package byog.Core;

import byog.TileEngine.TETile;

import java.io.Serializable;

public class GameState implements Serializable {
    private static final long serialVersionUID = 123123123456L;

    TETile[][] world;
    Player player;
    PlayerWithInput playerWithInput;

    public GameState(TETile[][] world, Player player) {
        this.world = world;
        this.player = player;
    }

    public GameState(TETile[][] world, PlayerWithInput playerWithInput) {
        this.world = world;
        this.playerWithInput = playerWithInput;
    }
}
