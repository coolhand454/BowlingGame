package bo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    private Map<String, List<Frame>> playerFrames;

    public Map<String, List<Frame>> getPlayerFrames() {
        if(playerFrames == null){
            return new HashMap<>();
        }
        return playerFrames;
    }

    public void setPlayerFrames(Map<String, List<Frame>> playerFrames) {
        this.playerFrames = playerFrames;
    }
}
