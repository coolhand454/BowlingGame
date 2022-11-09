import bo.Frame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.GamePlay;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowlingGameBuilderTest {

    Frame frame;

    Map<String, List<Frame>> playerGame = new HashMap<>();

    String[] stringBuilder;
    String[] stringBuilder2;

    GamePlay gamePlay;
    Map<String, List<Frame>> playerGame2;


    @BeforeEach
    void setup() {

        MockitoAnnotations.initMocks(this);
        frame = new Frame();
        gamePlay = new GamePlay();
        List<String> pinFallChars = new ArrayList<>();
        List<Integer> pinFallCount = new ArrayList<>();
        List<Frame> frames = new ArrayList<>();

        pinFallChars.add("1");
        pinFallChars.add("4");
        pinFallCount.add(1);
        pinFallCount.add(4);

        frame.setPinFallChars(pinFallChars);
        frame.setPinFallCount(pinFallCount);

        frames.add(frame);
        playerGame.put("Tom", frames);

        stringBuilder = new String[]{
                "Jeff 10",
                "John 3",
                "John 7",
                "Jeff 7",
                "Jeff 3",
                "John 6",
                "John 3",
                "Jeff 9",
                "Jeff 0",
                "John 10",
                "Jeff 10",
                "John 8",
                "John 1",
                "Jeff 0",
                "Jeff 8",
                "John 10",
                "Jeff 8",
                "Jeff 2",
                "John 10",
                "Jeff F",
                "Jeff 6",
                "John 9",
                "John 0",
                "Jeff 10",
                "John 7",
                "John 3",
                "Jeff 10",
                "John 4",
                "John 4",
                "Jeff 10",
                "Jeff 8",
                "Jeff 1",
                "John 4",
                "John 6",
                "John 7"};

        stringBuilder2 = new String[]{
                "Carl 10",
                "Carl 10",
                "Carl 10",
                "Carl 10",
                "Carl 10",
                "Carl 10",
                "Carl 10",
                "Carl 10",
                "Carl 10",
                "Carl 10",
                "Carl 10",
                "Carl 10"};
        try {
            for (int pos = 0; pos < stringBuilder.length; pos++) {

                gamePlay.buildBowlingGame(stringBuilder[pos], playerGame);

            }

            playerGame2 = new HashMap<>();
            for (int pos = 0; pos < stringBuilder2.length; pos++) {
                gamePlay.buildBowlingGame(stringBuilder2[pos], playerGame2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testBowlingGameBuidler() throws IOException {

        assertEquals("4", playerGame.get("Tom").get(0).getPinFallChars().get(1));
        assertEquals("8", playerGame.get("Jeff").get(5).getPinFallChars().get(0));
        assertEquals("", playerGame.get("John").get(2).getPinFallChars().get(1));
        assertEquals("X", playerGame2.get("Carl").get(2).getPinFallChars().get(0));


    }

    @Test
    void testBowlingAssignScores() throws IOException {
        gamePlay.assignScores(playerGame);
        gamePlay.assignScores(playerGame2);

        assertEquals(5, playerGame.get("Tom").get(0).getScore());
        assertEquals(84, playerGame.get("Jeff").get(5).getScore());
        assertEquals(44, playerGame.get("John").get(2).getScore());
        assertEquals(90, playerGame2.get("Carl").get(2).getScore());
    }

    @Test
    void testBowlingPrintGame() throws IOException {
        gamePlay.assignScores(playerGame);
        gamePlay.assignScores(playerGame2);
        gamePlay.printBowlingResults(playerGame);

        gamePlay.printBowlingResults(playerGame2);
    }
}
