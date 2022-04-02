import Items.Treasure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MapRandomizer {
    private Room[][][] map;
    int seed;

    public MapRandomizer(Room[][][] map, int seed) {
        this.map = map;
        this.seed = seed;
    }

    public void shuffleRoomDescriptions(ArrayList<String> roomDescriptions) {
        Collections.shuffle(roomDescriptions, new Random(this.seed));
    }

    public void randomizeTreasurePlacements(ArrayList<Treasure> treasures) {
        for(Room[][] floor : map) {
            for(Room[] row : floor) {
                for(Room room : row) {

                }
            }
        }
    }
}
