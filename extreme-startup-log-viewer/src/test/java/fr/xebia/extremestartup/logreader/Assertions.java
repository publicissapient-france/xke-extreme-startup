package fr.xebia.extremestartup.logreader;

class Assertions {

    static LogAssertion assertThat(Log actualLog) {
        return new LogAssertion(actualLog);
    }

    static PlayerAssertion assertThat(Player actualPlayer) {
        return new PlayerAssertion(actualPlayer);
    }

}