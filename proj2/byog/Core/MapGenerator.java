package byog.Core;

// generate rooms without overlapping
// connect rooms with L hallways

import java.util.Random;

public class MapGenerator {

    // anything about random should use one instance
    public Random createRandom(long SEED) {
        Random random = new Random(SEED);
        return random;
    }


}
