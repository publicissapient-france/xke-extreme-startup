package fr.xebia.extremestartup.logreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogReader {

    private static final Pattern[] PATTERNS = new Pattern[]{
            Pattern.compile("For player (.*) \\((.+)\\)"),
            Pattern.compile("\tquestion: ([\\da-f]{8}): (.+)"),
            Pattern.compile("\tresult: (.*)"),
            Pattern.compile("added (-?\\d+) to player .*'s score. It is now (-?\\d+)"),
    };

    static List<Log> readLogs(InputStream in) {
        final List<Log> logs = new LinkedList<>();
        readLogs(in, new LogCallback() {
            public void newLog(int logNumber, Log newLog) {
                logs.add(newLog);
            }
        });
        return logs;
    }

    static void readLogs(InputStream in, LogCallback logCallback) {
        BufferedReader out = new BufferedReader(new InputStreamReader(in));
        String currentLine;
        String[] logLines = new String[4];
        StringBuilder answer = new StringBuilder();
        int logNumber = 0;
        int currentLineCount = 0;
        int lineCount = 0;
        boolean gettingAnswer = false;

        try {
            while ((currentLine = out.readLine()) != null) {
                lineCount++;
                if (currentLine.startsWith("For player ")
                        || currentLine.startsWith("==")
                        || currentLine.startsWith(">>")) {
                    if (logLines[0] != null) {
                        try {
                            logCallback.newLog(logNumber++, readLog(answer, logLines));
                        } catch (Exception e) {
                            System.err.println("line " + lineCount + " : " + e.getMessage());
                        }
                        logLines = new String[4];
                        answer = new StringBuilder();
                        currentLineCount = 0;
                    }
                }
                if (currentLine.startsWith("\tanswer:")) {
                    gettingAnswer = true;
                }
                if (currentLine.startsWith("\tresult:")) {
                    gettingAnswer = false;
                }
                if (gettingAnswer) {
                    if (answer.length() > 0) {
                        answer.append('\n');
                    }
                    answer.append(currentLine);
                    continue;
                }
                if (currentLine.startsWith("For player")
                        || currentLine.startsWith("\t")
                        || currentLine.startsWith("added ")) {
                    try {
                        logLines[currentLineCount++] = currentLine;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("line " + lineCount + " : unkown line \"" + currentLine + "\"");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Log readLog(StringBuilder answer, String... logLines) {
        Matcher[] matchers = new Matcher[4];
        for (int i = 0; i < 4; i++) {
            Matcher matcher = PATTERNS[i].matcher(logLines[i]);
            if (matcher.matches()) {
                matchers[i] = matcher;
            } else {
                throw new RuntimeException(
                        "no match for \"" + PATTERNS[i].pattern() + "\" given \"" + logLines[i] + "\"");
            }
        }

        String playerName = matchers[0].group(1);
        String playerURL = matchers[0].group(2);
        String questionScore = matchers[3].group(1);
        String questionId = matchers[1].group(1);
        String question = matchers[1].group(2);
        String status = matchers[2].group(1).toUpperCase();
        String score = matchers[3].group(2);

        return new Log(
                new Player(playerName, playerURL).updateScore(Integer.valueOf(questionScore)),
                questionId,
                question,
                answer.toString().replaceFirst("\tanswer: ", ""),
                Log.Status.valueOf(status),
                Integer.valueOf(score));
    }

}