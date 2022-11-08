package bo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Frame {

    private String playerName;
    private int score;
    private int rolls;
    private List<Integer> pinFallCount;
    private List<String> pinFallChars;
    private int frameNumber;

    public int getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(int frameNumber) {
        this.frameNumber = frameNumber;
    }


    public List<Integer> getPinFallCount() {
        if (pinFallCount == null){
            pinFallCount = new ArrayList<>();
        }
        return pinFallCount;
    }

    public void setPinFallCount(List<Integer> pinFallCount) {
        this.pinFallCount = pinFallCount;
    }

    public List<String> getPinFallChars() {
        if (pinFallChars == null){
            pinFallChars = new ArrayList<>();
        }
        return pinFallChars;
    }

    public void setPinFallChars(List<String> pinFallChars) {
        this.pinFallChars = pinFallChars;
    }

    public Frame() {

    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = getScore() + score;
    }

    public int getRolls() {
        return rolls;
    }

    public void setRolls(int rolls) {
        this.rolls = rolls;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(frameNumber)
                .append(Arrays.toString(pinFallChars.toArray()).replace("[" ,"")
                        .replace("]" ,"")
                        .replace(",", ""))
                .append(score)
                .toString();
    }
}
