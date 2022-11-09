package app.bowling.game;

import app.bowling.game.service.GamePlay;

public class BowlingGame {

    public static void main(String [] args){

        GamePlay gamePlay = new GamePlay();
        gamePlay.playGame(args[0]);
    }
}
