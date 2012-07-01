package fr.xebia.extremestartup.logviewer;

import org.junit.Test;

import java.util.Set;

import static fr.xebia.extremestartup.logviewer.LogReader.readLogs;
import static org.fest.assertions.Assertions.assertThat;

public class LogReaderTest {

    @Test
    public void test() {
        Set<Log> logs = readLogs(LogReaderTest.class.getResourceAsStream("log.txt"));

        assertThat(logs).hasSize(1);
        Log log = logs.iterator().next();
        Assertions.assertThat(log.getPlayer())
                .playerNameIsEqualTo("sebastian")
                .playerIdIsEqualTo("1f9b8900")
                .playerURLIsEqualTo("http://127.0.0.1:8080")
                .scoreIsEqualTo(20);
        Assertions.assertThat(log)
                .questionIdIsEqualTo("1f9baef0")
                .questionIsEqualTo("what is 16 plus 5")
                .answerIsEqualTo("21")
                .expectedIsEqualTo("21")
                .isCorrect()
                .scoreIsEqualTo(10);
    }

}