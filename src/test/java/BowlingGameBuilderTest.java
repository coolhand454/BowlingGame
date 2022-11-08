import bo.Frame;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import service.GamePlay;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class BowlingGameBuilderTest {

    @Mock
    BufferedReader bufferedReader;

    Frame frame;

    Map<String, List<Frame>> playerGame = new HashMap<>();

    String[] stringBuilder;
    String[] stringBuilder2;


    @BeforeEach
    void setup() {

        MockitoAnnotations.initMocks(this);
        frame = new Frame();

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

    }

    @Test
    void testBowlingGameBuidler() throws IOException {
        //when( bufferedReader.readLine()).thenReturn("Tom 1");
        GamePlay gamePlay = new GamePlay();

        //gamePlay.buildBowlingGame("Tom 10", playerGame = new HashMap<>());
//        assertFalse(playerGame.isEmpty());
//        assertEquals(1, playerGame.get("Tom").get(0).getPinFallCount().get(0));

        for (int pos = 0; pos < stringBuilder.length; pos ++) {
            gamePlay.buildBowlingGame(stringBuilder[pos], playerGame );
        }
        gamePlay.assignScores(playerGame);
        gamePlay.printBowlingResults(playerGame);

        playerGame = new HashMap<>();
        for (int pos = 0; pos < stringBuilder2.length; pos ++) {
            gamePlay.buildBowlingGame(stringBuilder2[pos], playerGame );
        }
        gamePlay.assignScores(playerGame);
        gamePlay.printBowlingResults(playerGame);



//        playerGame = new HashMap<>();
//        frame = new Frame();
//
//        List<String> pinFallChars = new ArrayList<>();
//        List<Integer> pinFallCount = new ArrayList<>();
//        List<Frame> frames = new ArrayList<>();
//
//
//        frame.setPinFallChars(pinFallChars);
//        frame.setPinFallCount(pinFallCount);
//
//        frames.add(frame);
//        playerGame.put("Tom", frames);
//
//        gamePlay.buildBowlingGame("Tom 10", playerGame );
//        gamePlay.buildBowlingGame("Tom 5", playerGame );
//
//
//        playerGame = new HashMap<>();
//        frame = new Frame();
//
//        pinFallChars = new ArrayList<>();
//         pinFallCount = new ArrayList<>();
//         frames = new ArrayList<>();
//
//        frame.setPinFallChars(pinFallChars);
//        frame.setPinFallCount(pinFallCount);
//
//        frames.add(frame);
//
//        playerGame.put("Tom", frames);
//        gamePlay.buildBowlingGame("Tom 5", playerGame );
//        gamePlay.buildBowlingGame("Tom 5", playerGame );
//
//        playerGame = new HashMap<>();
//        frame = new Frame();
//
//        pinFallChars = new ArrayList<>();
//        pinFallCount = new ArrayList<>();
//        frames = new ArrayList<>();
//
//        frame.setPinFallChars(pinFallChars);
//        frame.setPinFallCount(pinFallCount);
//
//        frames.add(frame);
//
//        playerGame.put("Tom", frames);
//        gamePlay.buildBowlingGame("Tom 10", playerGame );
//        //gamePlay.buildBowlingGame("Tom 5", playerGame );

        System.out.println();

    }
}
