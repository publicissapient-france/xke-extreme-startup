package fr.xebia.extremestartup.logviewer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogReader {

    private static final Pattern[] PATTERNS = new Pattern[]{
            Pattern.compile("For player ([a-z]+) \\(([\\da-f]{8}): (http://[\\d\\.:]+)\\)"),
            Pattern.compile("\tquestion: ([\\da-f]{8}): ([\\w\\s:,.]+)"),
            Pattern.compile("\tanswer: ([\\w\\s]*) \\(expected: ([\\w\\s]*)\\)"),
            Pattern.compile("\tresult: (.*)"),
            Pattern.compile("\tscore: (-?\\d+)"),
            Pattern.compile("added (?:-?\\d+) to player [a-z]+'s score. It is now (-?\\d+)"),
    };

    static Set<Log> readLogs(InputStream in) {
        Set<Log> logs = new HashSet<>();
        BufferedReader out = new BufferedReader(new InputStreamReader(in));

        String currentLine;

        String[] logLines = new String[6];
        int currentLineCount = 0;

        try {
            while ((currentLine = out.readLine()) != null) {
                if (currentLine.startsWith("For player ")
                        || currentLine.startsWith("==")
                        || currentLine.startsWith(">>")) {
                    if (logLines[0] != null) {
                        System.out.println(currentLine);
                        logs.add(readLog(logLines));
                        logLines = new String[6];
                        currentLineCount = 0;
                    }
                }
                if (currentLine.startsWith("For player")
                        || currentLine.startsWith("\t")
                        || currentLine.startsWith("added ")) {
                    logLines[currentLineCount++] = currentLine;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return logs;
    }

    private static Log readLog(String... logLines) throws MalformedURLException {
        Matcher[] matchers = new Matcher[6];
        for (int i = 0; i < 6; i++) {
            Matcher matcher = PATTERNS[i].matcher(logLines[i]);
            if (matcher.matches()) {
                matchers[i] = matcher;
            }
        }

        return new Log(new Player(matchers[0].group(2), matchers[0].group(1), new URL(matchers[0].group(3)))
                .updateScore(Integer.valueOf(matchers[5].group(1))), matchers[1].group(1), matchers[1].group(2),
                matchers[2].group(2), matchers[2].group(1), Log.Status.valueOf(matchers[3].group(1).toUpperCase()),
                Integer.valueOf(matchers[4].group(1)));
    }

}