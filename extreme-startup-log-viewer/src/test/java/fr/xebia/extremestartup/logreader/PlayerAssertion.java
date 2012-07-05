package fr.xebia.extremestartup.logreader;

import static org.fest.assertions.Assertions.assertThat;

public class PlayerAssertion {

    private final Player actualPlayer;

    PlayerAssertion(final Player actualPlayer) {
        this.actualPlayer = actualPlayer;
    }

    PlayerAssertion playerNameIsEqualTo(String expectedPlayerName) {
        assertThat(actualPlayer.getPlayerName()).isEqualTo(expectedPlayerName);
        return this;
    }

    public PlayerAssertion playerURLIsEqualTo(String expectedURL) {
        assertThat(actualPlayer.getPlayerURL()).isEqualTo(expectedURL);
        return this;
    }

    public PlayerAssertion scoreIsEqualTo(int expectedScore) {
        assertThat(actualPlayer.getScore()).isEqualTo(expectedScore);
        return this;
    }

}