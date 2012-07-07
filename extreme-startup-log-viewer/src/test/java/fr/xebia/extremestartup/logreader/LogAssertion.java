package fr.xebia.extremestartup.logreader;

import org.fest.assertions.AssertExtension;

import static fr.xebia.extremestartup.logreader.Log.Status.*;
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

    public LogAssertion noServerResponse() {
        assertThat(actualLog.getStatus()).isEqualTo(NO_SERVER_RESPONSE);
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

    public LogAssertion isErrorResponse() {
        assertThat(actualLog.getStatus()).isEqualTo(ERROR_RESPONSE);
        return this;
    }

    public LogAssertion scoreIsEqualTo(int expectedScore) {
        assertThat(actualLog.getScore()).isEqualTo(expectedScore);
        return this;
    }

    public LogAssertion questionIsOfType(QuestionType expectedQuestionType) {
        assertThat(actualLog.getQuestionType()).isEqualTo(expectedQuestionType);
        return this;
    }
}