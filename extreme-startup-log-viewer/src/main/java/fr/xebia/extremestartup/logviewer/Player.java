package fr.xebia.extremestartup.logviewer;

import java.net.URL;

public class Player {

    private final String playerId;
    private final String playerName;
    private final URL playerURL;
    private int score;

    Player(String playerId, String playerName, URL playerURL) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerURL = playerURL;
        this.score = 0;
    }

    String getPlayerId() {
        return playerId;
    }

    String getPlayerName() {
        return playerName;
    }

    URL getPlayerURL() {
        return playerURL;
    }

    int getScore() {
        return score;
    }

    Player updateScore(int score) {
        this.score += score;
        return this;
    }

}