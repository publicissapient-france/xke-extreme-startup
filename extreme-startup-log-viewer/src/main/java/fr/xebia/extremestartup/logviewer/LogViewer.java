package fr.xebia.extremestartup.logviewer;

import javax.xml.bind.DatatypeConverter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogViewer {

    private static final Pattern LOG_LINE_PATTERN = Pattern
            .compile("I, \\[([\\d\\-T:\\.]+).+" +
                    "Player: ([a-z]+) \\(([\\da-f]{8}): (http://[\\d\\.:]+)\\)\\|" +
                    "\\|question: ([\\da-f]{8}): ([\\w\\s:,.]+)" +
                    "\\|answer: ([\\w\\s]*)" +
                    "\\|expected: ([\\w\\s]*)" +
                    "\\|result: (.*)");

    static Log readLine(String line) throws MalformedURLException {
        final Matcher matcher = LOG_LINE_PATTERN.matcher(line);

        if (!matcher.matches()) {
            throw new IllegalArgumentException();
        }

        return new Log(
                DatatypeConverter.parseDateTime(matcher.group(1)).getTime(),
                new Player(matcher.group(3), matcher.group(2), new URL(matcher.group(4))),
                matcher.group(5),
                matcher.group(6),
                matcher.group(8), matcher.group(7),
                Log.Status.valueOf(matcher.group(9).toUpperCase()));
    }

}