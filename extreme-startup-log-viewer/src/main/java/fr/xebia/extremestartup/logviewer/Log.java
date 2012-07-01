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

    public Log(Date date, Player player, String questionId, String question, String expected, String answer,
               Status status) {
        this.date = date;
        this.player = player;
        this.questionId = questionId;
        this.question = question;
        this.answer = answer;
        this.expected = expected;
        this.status = status;
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

    enum Status {
        NO_ANSWER, CORRECT, WRONG;
    }

}