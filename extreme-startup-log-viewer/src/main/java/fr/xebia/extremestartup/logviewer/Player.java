package fr.xebia.extremestartup.logviewer;

import java.net.URL;

public class Player {

    private String playerId;
    private String playerName;
    private URL playerURL;

    Player(String playerId, String playerName, URL playerURL) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerURL = playerURL;
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
}