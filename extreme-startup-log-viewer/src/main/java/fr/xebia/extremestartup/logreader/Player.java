package fr.xebia.extremestartup.logreader;

class Player {

    private final String playerName;
    private final String playerURL;
    private int score;

    Player(String playerName, String playerURL) {
        this.playerName = playerName;
        this.playerURL = playerURL;
        this.score = 0;
    }

    String getPlayerName() {
        return playerName;
    }

    String getPlayerURL() {
        return playerURL;
    }

    int getScore() {
        return score;
    }

    Player updateScore(int score) {
        this.score += score;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (!playerURL.equals(player.playerURL)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return playerURL.hashCode();
    }
}