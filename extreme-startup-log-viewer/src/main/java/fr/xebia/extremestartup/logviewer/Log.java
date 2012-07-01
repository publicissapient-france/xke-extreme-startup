package fr.xebia.extremestartup.logviewer;

import java.util.Date;

public class Log {

    private final Date date;
    private final Player player;
    private final String questionId;
    private final String question;
    private final String expected;
    private final String answer;
    private final Status status;
    private final int score;

    Log(Player player, String questionId, String question, String expected, String answer, Status status, int score) {
        this(null, player, questionId, question, expected, answer, status, score);
    }

    Log(Date date, Player player, String questionId, String question, String expected, String answer, Status status) {
        this(date, player, questionId, question, expected, answer, status, 0);
    }

    private Log(Date date, Player player, String questionId, String question, String expected, String answer,
                Status status, int score) {
        this.date = date;
        this.player = player;
        this.questionId = questionId;
        this.question = question;
        this.answer = answer;
        this.expected = expected;
        this.status = status;
        this.score = score;
    }

    Date getDate() {
        return date;
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

    String getExpected() {
        return expected;
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
        NO_ANSWER, CORRECT, WRONG
    }

}