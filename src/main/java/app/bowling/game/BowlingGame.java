package app.bowling.game;

import app.bowling.game.service.GamePlay;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BowlingGame {

    public static void main(String [] args){
        Logger logger = Logger.getLogger(BowlingGame.class.getName());

        GamePlay gamePlay = new GamePlay();

        if (args.length == 1 && !args[0].equalsIgnoreCase("")) {
            gamePlay.playGame(args[0]);
        } else {
           logger.log(Level.WARNING, "Missing file name. \n Ex: mvn clean package exec:java -Dexec.mainClass=\"app.bowling.game.BowlingGame\" -Dexec.args=playerGame.txt");
        }
    }
}
