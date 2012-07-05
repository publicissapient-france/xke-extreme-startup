package fr.xebia.extremestartup.logviewer;

import org.fest.assertions.AssertExtension;

import java.text.SimpleDateFormat;

import static fr.xebia.extremestartup.logviewer.Log.Status.*;
import static org.fest.assertions.Assertions.assertThat;

public class LogAssertion implements AssertExtension {

    private final Log actualLog;

    public LogAssertion(Log actual) {
        actualLog = actual;
    }

    public LogAssertion questionIdIsEqualTo(String expectedQuestionId) {
        assertThat(actualLog.getQuestionId()).isEqualTo(expectedQuestionId);
        return this;
    }

    public LogAssertion questionIsEqualTo(String expectedQuestion) {
        assertThat(actualLog.getQuestion()).isEqualTo(expectedQuestion);
        return this;
    }

    public LogAssertion answerIsEqualTo(String expectedAnswear) {
        assertThat(actualLog.getAnswer()).isEqualTo(expectedAnswear);
        return this;
    }

    public LogAssertion expectedIsEqualTo(String expectedExpected) {
        assertThat(actualLog.getExpected()).isEqualTo(expectedExpected);
        return this;
    }

    public LogAssertion noAnswer() {
        assertThat(actualLog.getStatus()).isEqualTo(NO_ANSWER);
        return this;
    }

    public LogAssertion isWrong() {
        assertThat(actualLog.getStatus()).isEqualTo(WRONG);
        return this;
    }

    public LogAssertion isCorrect() {
        assertThat(actualLog.getStatus()).isEqualTo(CORRECT);
        return this;
    }

    public LogAssertion scoreIsEqualTo(int expectedScore) {
        assertThat(actualLog.getScore()).isEqualTo(expectedScore);
        return this;
    }

}