package fr.xebia.extremestartup.logviewer;

import org.junit.Test;

import static fr.xebia.extremestartup.logviewer.Assertions.assertThat;

public class LogViewerTest {

    @Test
    public void should_read_a_no_answer_log_line() throws Exception {
        final String line = "I, [2012-06-09T23:15:00.657641 #46016]  INFO -- :" +
                " Player: sebastian (82e54950: http://127.0.0.1:8080)|" +
                "|question: 18c6de10: what is 6 multiplied by 6" +
                "|answer: " +
                "|expected: 36" +
                "|result: no_answer";

        final Log log = LogViewer.readLine(line);

        assertThat(log)
                .dateIsEqualTo("2012-06-09T23:15:00.657")
                .questionIdIsEqualTo("18c6de10").questionIsEqualTo("what is 6 multiplied by 6")
                .answerIsEqualTo("")
                .expectedIsEqualTo("36")
                .noAnswer();
        assertThat(log.getPlayer())
                .playerNameIsEqualTo("sebastian")
                .playerIdIsEqualTo("82e54950")
                .playerURLIsEqualTo("http://127.0.0.1:8080");
    }

    @Test
    public void should_read_a_correct_empty_log_line() throws Exception {
        final String line = "I, [2012-06-09T23:12:50.403143 #46016]  INFO -- :" +
                " Player: sebastian (82e54950: http://127.0.0.1:8080)|" +
                "|question: cb236150: which of the following numbers is both a square and a cube: 961, 784, 468, 861" +
                "|answer: " +
                "|expected: " +
                "|result: correct";

        final Log log = LogViewer.readLine(line);

        assertThat(log).isCorrect();
    }

    @Test
    public void shoud_read_a_wrong_log_line() throws Exception {
        final String line = "I, [2012-06-09T22:37:28.341948 #45581]  INFO -- :" +
                " Player: sebastian (ad91ac00: http://127.0.0.1:8080)|" +
                "|question: da4a9030: my name is chuck. what is my name" +
                "|answer: sebastian" +
                "|expected: chuck" +
                "|result: wrong";

        final Log log = LogViewer.readLine(line);

        assertThat(log).answerIsEqualTo("sebastian").isWrong();
    }

}