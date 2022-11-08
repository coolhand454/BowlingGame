package service;

import bo.Frame;
import bo.Game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GamePlay {

    public void playGame(String playerGameFile) {
        Game game = new Game();
        try {
            BufferedReader br
                    = new BufferedReader(new FileReader(playerGameFile));
            Map<String, List<Frame>> playerFrames = game.getPlayerFrames();
            String bowlerRolls;
            while ((bowlerRolls = br.readLine()) != null) {
                buildBowlingGame(bowlerRolls, playerFrames);

            }
            assignScores(playerFrames);
            printBowlingResults(playerFrames);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void printBowlingResults(Map<String, List<Frame>> playerFrames) {
        Formatter fmtPins = new Formatter();
        Formatter fmtScore = new Formatter();
        Formatter fmtFrameNumber = new Formatter();
        fmtFrameNumber.format("%-7s", "Frame ");
        fmtPins.format("%-7s", "Pinfall ");
        fmtScore.format("%-7s", "Score ");


        for (Map.Entry<String, List<Frame>> stringListEntry : playerFrames.entrySet()) {
            String playerName = stringListEntry.getKey();
            List<Frame> frames = stringListEntry.getValue();
            List<String> pinFallStringArray = new ArrayList<>();


            for (int pinfallPos = 0; pinfallPos < frames.size(); pinfallPos++) {
                frames.get(pinfallPos).setFrameNumber(pinfallPos + 1);

                String pinFall = Arrays.toString(frames.get(pinfallPos).getPinFallChars().toArray()).replace("[", "")
                        .replace("]", "")
                        .replace(",", "");
                pinFallStringArray.add(pinFall);

                fmtFrameNumber.format("%-7s", frames.get(pinfallPos).getFrameNumber());
                fmtPins.format("%-7s", pinFall);
                fmtScore.format("%-7s", frames.get(pinfallPos).getScore());

            }
            System.out.println(fmtFrameNumber);
            System.out.println(playerName);
            System.out.println(fmtPins);
            System.out.println(fmtScore);
            System.out.println();

            fmtPins = new Formatter();
            fmtScore = new Formatter();
            fmtFrameNumber = new Formatter();

            fmtFrameNumber.format("%-7s", "Frame ");
            fmtPins.format("%-7s", "Pinfall ");
            fmtScore.format("%-7s", "Score ");

        }
    }

    public void buildBowlingGame(String playerLine, Map<String, List<Frame>> playerFrames) throws IOException {

        String bowlerRolls;
        String[] namePins = playerLine.split(" ");
        String name = namePins[0];
        String pinFall = namePins[1];
        int pinFallCount = Integer.valueOf(!pinFall.equalsIgnoreCase("F") ? pinFall : "0");

        if (playerFrames.containsKey(name)) {
            List<Frame> currentFrames = playerFrames.get(name);
            Frame currentFrame;
            int frameSize = currentFrames.size();

            currentFrame = currentFrames.get(frameSize - 1);

            if ( currentFrame.getPinFallChars().isEmpty() && frameSize < 10) {
                if (pinFallCount == 10) {
                    currentFrame.getPinFallChars().add("X");
                    currentFrame.getPinFallChars().add("");
                    currentFrame.getPinFallCount().add(pinFallCount);
                    currentFrame.setPlayerName(name);
                    currentFrame.setRolls(1);
                    currentFrames.add(new Frame());


                } else {
                    currentFrame.getPinFallCount().add(pinFallCount);
                    currentFrame.setPlayerName(name);
                    currentFrame.getPinFallChars().add(pinFall);
                    currentFrame.setRolls(currentFrame.getPinFallCount().size());

                }
            } else if (frameSize == 10) {
                if (pinFallCount == 10) {
                    currentFrame.getPinFallChars().add("X");
                    currentFrame.getPinFallCount().add(pinFallCount);
                    currentFrame.setPlayerName(name);
                    currentFrame.setRolls(currentFrame.getPinFallChars().size());

                } else {

                    currentFrame.getPinFallCount().add(pinFallCount);
                    currentFrame.setPlayerName(name);
                    findFrame(pinFall, currentFrame);


                }
            } else {

                    Frame newStrikeFrame = new Frame();
                    currentFrame.getPinFallCount().add(pinFallCount);
                    currentFrame.setPlayerName(name);
                    currentFrame.setRolls(currentFrame.getPinFallChars().size());

                    findFrame(pinFall, currentFrame);

                    currentFrames.add(newStrikeFrame);

            }


        } else {
            List<Frame> frames = new ArrayList<>();
            Frame currentFrame = new Frame();
            if (pinFallCount == 10) {
                currentFrame.getPinFallChars().add("X");
                currentFrame.getPinFallChars().add("");
                currentFrame.getPinFallCount().add(pinFallCount);
                currentFrame.setPlayerName(name);
                currentFrame.setRolls(1);
                frames.add(currentFrame);
                frames.add(new Frame());
            } else {
                currentFrame.getPinFallChars().add(pinFall);
                currentFrame.getPinFallCount().add(pinFallCount);
                currentFrame.setPlayerName(name);
                currentFrame.setRolls(currentFrame.getPinFallCount().size());
                frames.add(currentFrame);
            }

            playerFrames.put(name, frames);

        }


    }

    private void findFrame(String pinFall, Frame currentFrame) {
        if (currentFrame.getPinFallChars().size() == 1 && currentFrame.getPinFallCount().stream()
                .reduce(0, (subtotal, element) -> subtotal + element) == 10) {
            currentFrame.getPinFallChars().add("/");
        } else {
            currentFrame.getPinFallChars().add(pinFall);
        }
        currentFrame.setRolls(currentFrame.getPinFallChars().size());
    }

    public void assignScores(Map<String, List<Frame>> playerFrames) {

        for (Map.Entry<String, List<Frame>> stringListEntry : playerFrames.entrySet()) {

            List<Frame> frames = stringListEntry.getValue();
            for (int framePos = 0; framePos < frames.size(); ++framePos) {

                Frame frame = frames.get(framePos);
                if (framePos != 0) {
                    frame.setScore(frames.get(framePos - 1).getScore());
                }

                if (frame.getPinFallCount().size() == 1 && frame.getPinFallChars().get(0).equalsIgnoreCase("X")) {
                    if (frames.get(framePos + 1).getRolls() == 1 && frames.get(framePos + 2).getRolls() == 2) {
                        int nextRollScore1 = frames.get(framePos + 1).getPinFallCount().get(0);
                        int nextRollScore2 = frames.get(framePos + 2).getPinFallCount().get(0);
                        frames
                                .get(framePos)
                                .setScore(10 + nextRollScore1 + nextRollScore2);

                    } else if (frames.get(framePos + 1).getRolls() == 2) {
                        int nextRollScore1 = frames.get(framePos + 1).getPinFallCount().get(0);
                        int nextRollScore2 = frames.get(framePos + 1).getPinFallCount().get(1);
                        frames
                                .get(framePos)
                                .setScore(10 + nextRollScore1 + nextRollScore2);

                    } else if (frames.get(framePos + 1).getRolls() == 1) {
                        int nextRollScore1 = frames.get(framePos + 1).getPinFallCount().get(0);
                        int nextRollScore2 = frames.get(framePos + 2).getPinFallCount().get(0);
                        frames
                                .get(framePos)
                                .setScore(10 + nextRollScore1 + nextRollScore2);


                    }
                    else if (frames.get(framePos + 1).getRolls() == 3) {

                        int nextRollScore1 = frames.get(framePos + 1).getPinFallCount().get(0);
                        int nextRollScore2 = frames.get(framePos + 1).getPinFallCount().get(1);
                        frames
                                .get(framePos)
                                .setScore(10 + nextRollScore1 + nextRollScore2);
                    }


                } else if (frames.get(framePos).getRolls() == 3
                        && frames
                        .get(framePos)
                        .getPinFallCount()
                        .stream()
                        .reduce(0, (subtotal, element) -> subtotal + element) == 30) {

                    int nextRollScore1 = frames.get(framePos).getPinFallCount().get(1);
                    int nextRollScore2 = frames.get(framePos).getPinFallCount().get(2);
                    frames
                            .get(framePos)
                            .setScore(10 + nextRollScore1 + nextRollScore2);

                } else if (frame.getPinFallCount().size() == 2 && frame.getPinFallChars().get(1).equalsIgnoreCase("/")) {
                    int nextRollScore = frames.get(framePos + 1).getPinFallCount().get(0);
                    frames
                            .get(framePos)
                            .setScore(10 + nextRollScore);

                } else {
                    frames
                            .get(framePos)
                            .setScore(frames.get(framePos)
                                    .getPinFallCount().stream()
                                    .reduce(0, (subtotal, element) -> subtotal + element));
                }


            }
        }

    }


}
