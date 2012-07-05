package fr.xebia.extremestartup.logreader;

class Log {

    private final Player player;
    private final String questionId;
    private final String question;
    private final String answer;
    private final Status status;
    private final int score;

    Log(Player player, String questionId, String question, String answer, Status status, int score) {
        this.player = player;
        this.questionId = questionId;
        this.question = question;
        this.answer = answer;
        this.status = status;
        this.score = score;
    }

    String getQuestionId() {
        return questionId;
    }

    String getQuestion() {
        return question;
    }

    String getAnswer() {
        return answer;
    }

    Status getStatus() {
        return status;
    }

    Player getPlayer() {
        return player;
    }

    public int getScore() {
        return score;
    }

    enum Status {
        CORRECT,
        ERROR_RESPONSE,
        NO_SERVER_RESPONSE,
        WRONG
    }

}