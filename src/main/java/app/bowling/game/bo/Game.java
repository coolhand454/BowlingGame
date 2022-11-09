package app.bowling.game.bo;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Game {

    private Map<String, List<Frame>> playerFrames;

    public Map<String, List<Frame>> getPlayerFrames() {
        if(playerFrames == null){
            return new HashMap<>();
        }
        return playerFrames;
    }

}
