package fr.xebia.extremestartup.logviewer;

public class Assertions {

    static LogAssertion assertThat(Log actualLog) {
        return new LogAssertion(actualLog);
    }

    static PlayerAssertion assertThat(Player actualPlayer) {
        return new PlayerAssertion(actualPlayer);
    }

}