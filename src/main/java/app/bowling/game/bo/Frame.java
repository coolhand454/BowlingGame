package app.bowling.game.bo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
public class Frame {

    private String playerName;
    private int score;
    private int rolls;
    private List<Integer> pinFallCount;
    private List<String> pinFallChars;
    private int frameNumber;



    public List<Integer> getPinFallCount() {
        if (pinFallCount == null){
            pinFallCount = new ArrayList<>();
        }
        return pinFallCount;
    }



    public List<String> getPinFallChars() {
        if (pinFallChars == null){
            pinFallChars = new ArrayList<>();
        }
        return pinFallChars;
    }

    public void setScore(int score) {
        this.score = getScore() + score;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "playerName='" + playerName + '\'' +
                ", score=" + score +
                ", rolls=" + rolls +
                ", pinFallCount=" + pinFallCount +
                ", pinFallChars=" + pinFallChars +
                ", frameNumber=" + frameNumber +
                '}';
    }
}
