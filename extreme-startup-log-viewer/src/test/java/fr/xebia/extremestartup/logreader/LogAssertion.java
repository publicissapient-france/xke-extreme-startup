package fr.xebia.extremestartup.logreader;

import org.fest.assertions.AssertExtension;

import static fr.xebia.extremestartup.logreader.Log.Status.*;
import static org.fest.assertions.Assertions.assertThat;

class LogAssertion implements AssertExtension {

    private final Log actualLog;

    LogAssertion(Log actual) {
        actualLog = actual;
    }

    LogAssertion questionIdIsEqualTo(String expectedQuestionId) {
        assertThat(actualLog.getQuestionId()).isEqualTo(expectedQuestionId);
        return this;
    }

    LogAssertion questionIsEqualTo(String expectedQuestion) {
        assertThat(actualLog.getQuestion()).isEqualTo(expectedQuestion);
        return this;
    }

    LogAssertion answerIsEqualTo(String expectedAnswear) {
        assertThat(actualLog.getAnswer()).isEqualTo(expectedAnswear);
        return this;
    }

    LogAssertion noServerResponse() {
        assertThat(actualLog.getStatus()).isEqualTo(NO_SERVER_RESPONSE);
        return this;
    }

    LogAssertion isWrong() {
        assertThat(actualLog.getStatus()).isEqualTo(WRONG);
        return this;
    }

    LogAssertion isCorrect() {
        assertThat(actualLog.getStatus()).isEqualTo(CORRECT);
        return this;
    }

    LogAssertion isErrorResponse() {
        assertThat(actualLog.getStatus()).isEqualTo(ERROR_RESPONSE);
        return this;
    }

    LogAssertion scoreIsEqualTo(int expectedScore) {
        assertThat(actualLog.getScore()).isEqualTo(expectedScore);
        return this;
    }

    LogAssertion questionIsOfType(QuestionType expectedQuestionType) {
        assertThat(actualLog.getQuestionType()).isEqualTo(expectedQuestionType);
        return this;
    }
}