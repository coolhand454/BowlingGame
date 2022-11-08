import service.GamePlay;

public class BowlingGame {

    public static void main(String [] args){

        GamePlay gamePlay = new GamePlay();
        gamePlay.playGame(args[0]);
    }
}
