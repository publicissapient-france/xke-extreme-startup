package fr.xebia.extremestartup;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;
import static java.lang.Math.max;
import static java.lang.System.err;
import static java.lang.System.out;
import static java.util.Calendar.*;
import static java.util.Locale.US;
import static java.util.regex.Pattern.compile;

public class WebServer extends AbstractHandler {

    public static final String QUERY_PARAMETER = "q";

    @Override
    public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch)
            throws IOException, ServletException {
        String question = request.getParameter(QUERY_PARAMETER);

        String extremeStartupResponse = handle(question.substring(10));

        if (extremeStartupResponse.isEmpty()) {
            err.println(question);
        } else {
            out.println(question + " ? " + extremeStartupResponse);
        }

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter writer = response.getWriter();
        writer.println(extremeStartupResponse);
        writer.close();
    }

    String handle(String question) {
        Matcher additionMatcher = compile("what is (\\d+) plus (\\d+)").matcher(question);
        if (additionMatcher.matches()) {
            return String.valueOf(valueOf(additionMatcher.group(1)) + valueOf(additionMatcher.group(2)));
        }

        Matcher maximumMatcher = compile("which of the following numbers is the largest: (.+)").matcher(question);
        if (maximumMatcher.matches()) {
            Integer max = 0;
            for (String number : maximumMatcher.group(1).split(", ")) {
                max = max(max, valueOf(number));
            }
            return max.toString();
        }

        Matcher productMatcher = compile("what is the product of \\[(\\d+), (\\d+), (\\d+), (\\d+), (\\d+), (\\d+), (\\d+), (\\d+), (\\d+), (\\d+)\\]").matcher(question);
        if (productMatcher.matches()) {
            BigDecimal product = new BigDecimal(1);
            for (int i = 1; i <= 10; i++) {
                BigDecimal b = new BigDecimal(productMatcher.group(i));
                product = product.multiply(b);
            }
            return product.toString();
        }

        Matcher shaMatcher = compile("what is the sha1 for \"([\\w_]+)\"").matcher(question);
        if (shaMatcher.matches()) {
            try {
                byte[] digest = MessageDigest.getInstance("SHA-1").digest(shaMatcher.group(1).getBytes());
                StringBuilder sb = new StringBuilder();
                for (byte aByteDigest : digest) {
                    String hex = Integer.toHexString(0xFF & aByteDigest);
                    if (hex.length() == 1) {
                        sb.append('0');
                    }
                    sb.append(hex);
                }
                return sb.toString();
            } catch (NoSuchAlgorithmException e) {
                err.println(e.getMessage());
            }
        }

        Matcher piMatcher = compile("what is the (\\d+)(?:st|nd|rd|th) decimal of Pi").matcher(question);
        if (piMatcher.matches()) {
            Integer nthDecimal = valueOf(piMatcher.group(1));
            return "141592653589793238462643383279502884197169399375105820974944592307816406286208998628034825342117"
                    .substring(nthDecimal - 1, nthDecimal);
        }

        Matcher feetToMeterMatcher = compile("how much is (\\d+) feet in meters").matcher(question);
        if (feetToMeterMatcher.matches()) {
            return String.valueOf(valueOf(feetToMeterMatcher.group(1)) * 0.3048);
        }

        Matcher hexaAddition = compile("what is the decimal value of 0x([\\da-f]+) plus 0x([\\da-f]+)").matcher(question);
        if (hexaAddition.matches()) {
            return String.valueOf(parseInt(hexaAddition.group(1), 16) + parseInt(hexaAddition.group(2), 16));
        }

        if ("what is the answer to life, the universe and everything".equals(question)) {
            return "42";
        }
        if ("in which language was the first 'hello, world' written".equals(question)) {
            return "c";
        }
        if ("what does 'RTFM' stand for".equals(question)) {
            return "Read The Fucking Manual";
        }
        if ("who counted to infinity twice".equals(question)) {
            return "Chuck Norris";
        }
        if ("who said 'Luke, I am your father'".equals(question)) {
            return "Darth Vader";
        }

        Matcher dayOfWeekMatcher = compile("which day of the week is \\s?([\\d]+) (\\w{3}) (\\d+)").matcher(question);
        if (dayOfWeekMatcher.matches()) {
            Calendar parsedDate = new GregorianCalendar();
            parsedDate.set(DATE, valueOf(dayOfWeekMatcher.group(1)));

            Integer month = null;
            switch (dayOfWeekMatcher.group(2)) {
                case "Jan" : month = JANUARY; break;
                case "Feb" : month = FEBRUARY; break;
                case "Mar" : month = MARCH; break;
                case "Apr" : month = APRIL; break;
                case "May" : month = MAY; break;
                case "Jun" : month = JUNE; break;
                case "Jul" : month = JULY; break;
                case "Aug" : month = AUGUST; break;
                case "Sep" : month = SEPTEMBER; break;
                case "Oct" : month = OCTOBER; break;
                case "Nov" : month = NOVEMBER; break;
                case "Dec" : month = DECEMBER; break;
            }
            if (month != null) {
                parsedDate.set(MONTH, month);
            }

            parsedDate.set(YEAR, valueOf(dayOfWeekMatcher.group(3)));

            return parsedDate.getDisplayName(DAY_OF_WEEK, LONG, US);
        }

        Matcher httpResponseMatcher = compile("what HTTP response status do you get when you send a GET request to " +
                "(?:(http://(?:\\d{1,3}\\.){3}\\d{1,3}(?:\\:\\d+)?/http_code/[0-9a-z]{8})|a teapot)").matcher(question);
        if (httpResponseMatcher.matches()) {
            if ("a teapot".equals(httpResponseMatcher.group(1))) {
                return "418";
            }
            URLConnection urlConnection = null;
            try {
                urlConnection = new URL(httpResponseMatcher.group(1)).openConnection();
                return String.valueOf(((HttpURLConnection)urlConnection).getResponseCode());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    ((HttpURLConnection)urlConnection).disconnect();
                }
            }
        }

        return "";
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        server.setHandler(new WebServer());
        server.start();
        server.join();
    }

}