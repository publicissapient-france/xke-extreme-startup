package fr.xebia.extremestartup.logreader;

import org.junit.Test;

import java.util.List;

import static fr.xebia.extremestartup.logreader.LogReader.readLogs;
import static org.fest.assertions.Assertions.assertThat;

public class LogReaderTest {

    @Test
    public void should_parse_one_request() {
        List<Log> logs = readLogs(LogReaderTest.class.getResourceAsStream("log.txt"));

        assertThat(logs).hasSize(1);
        Log log = logs.iterator().next();
        Assertions.assertThat(log.getPlayer())
                .playerNameIsEqualTo("Sébastian")
                .playerURLIsEqualTo("http://127.0.0.1:8080")
                .scoreIsEqualTo(10);
        Assertions.assertThat(log)
                .questionIdIsEqualTo("1f9baef0")
                .questionIsEqualTo("what is 16 plus 5")
                .answerIsEqualTo("21")
                .isCorrect()
                .scoreIsEqualTo(10);
    }

    @Test
    public void should_parse_big_request() {
        List<Log> logs = readLogs(LogReaderTest.class.getResourceAsStream("log-with-big-answear.txt"));

        Assertions.assertThat(logs.iterator().next()).answerIsEqualTo("<!doctype html>\n" +
                "<!--[if lt ie 7 ]> <html lang=\"en\" class=\"no-js ie6\"> <![endif]-->\n" +
                "<!--[if ie 7 ]>    <html lang=\"en\" class=\"no-js ie7\"> <![endif]-->\n" +
                "<!--[if ie 8 ]>    <html lang=\"en\" class=\"no-js ie8\"> <![endif]-->\n" +
                "<!--[if ie 9 ]>    <html lang=\"en\" class=\"no-js ie9\"> <![endif]-->\n" +
                "<!--[if (gt ie 9)|!(ie)]><!--> <html lang=\"en\" class=\"no-js\"><!--<![endif]-->\n" +
                "\t<head>\n" +
                "\t\t<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\">\n" +
                "\t\t<meta http-equiv=\"x-ua-compatible\" content=\"ie=edge,chrome=1\">\n" +
                "\t\t<title>welcome to grails</title>\n" +
                "\t\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "\t\t<link rel=\"shortcut icon\" href=\"/untitled/static/images/favicon.ico\" type=\"image/x-icon\">\n" +
                "\t\t<link rel=\"apple-touch-icon\" href=\"/untitled/static/images/apple-touch-icon.png\">\n" +
                "\t\t<link rel=\"apple-touch-icon\" sizes=\"114x114\" href=\"/untitled/static/images/apple-touch-icon-retina.png\">\n" +
                "\t\t<link rel=\"stylesheet\" href=\"/untitled/static/css/main.css\" type=\"text/css\">\n" +
                "\t\t<link rel=\"stylesheet\" href=\"/untitled/static/css/mobile.css\" type=\"text/css\">\n" +
                "\n" +
                "\t\t<meta name=\"layout\" content=\"main\"/>\n" +
                "\n" +
                "\t\t<style type=\"text/css\" media=\"screen\">\n" +
                "\t\t\t#status {\n" +
                "\t\t\t\tbackground-color: #eee;\n" +
                "\t\t\t\tborder: .2em solid #fff;\n" +
                "\t\t\t\tmargin: 2em 2em 1em;\n" +
                "\t\t\t\tpadding: 1em;\n" +
                "\t\t\t\twidth: 12em;\n" +
                "\t\t\t\tfloat: left;\n" +
                "\t\t\t\t-moz-box-shadow: 0px 0px 1.25em #ccc;\n" +
                "\t\t\t\t-webkit-box-shadow: 0px 0px 1.25em #ccc;\n" +
                "\t\t\t\tbox-shadow: 0px 0px 1.25em #ccc;\n" +
                "\t\t\t\t-moz-border-radius: 0.6em;\n" +
                "\t\t\t\t-webkit-border-radius: 0.6em;\n" +
                "\t\t\t\tborder-radius: 0.6em;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.ie6 #status {\n" +
                "\t\t\t\tdisplay: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t#status ul {\n" +
                "\t\t\t\tfont-size: 0.9em;\n" +
                "\t\t\t\tlist-style-type: none;\n" +
                "\t\t\t\tmargin-bottom: 0.6em;\n" +
                "\t\t\t\tpadding: 0;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t#status li {\n" +
                "\t\t\t\tline-height: 1.3;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t#status h1 {\n" +
                "\t\t\t\ttext-transform: uppercase;\n" +
                "\t\t\t\tfont-size: 1.1em;\n" +
                "\t\t\t\tmargin: 0 0 0.3em;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t#page-body {\n" +
                "\t\t\t\tmargin: 2em 1em 1.25em 18em;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\th2 {\n" +
                "\t\t\t\tmargin-top: 1em;\n" +
                "\t\t\t\tmargin-bottom: 0.3em;\n" +
                "\t\t\t\tfont-size: 1em;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\tp {\n" +
                "\t\t\t\tline-height: 1.5;\n" +
                "\t\t\t\tmargin: 0.25em 0;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t#controller-list ul {\n" +
                "\t\t\t\tlist-style-position: inside;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t#controller-list li {\n" +
                "\t\t\t\tline-height: 1.3;\n" +
                "\t\t\t\tlist-style-position: inside;\n" +
                "\t\t\t\tmargin: 0.25em 0;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t@media screen and (max-width: 480px) {\n" +
                "\t\t\t\t#status {\n" +
                "\t\t\t\t\tdisplay: none;\n" +
                "\t\t\t\t}\n" +
                "\n" +
                "\t\t\t\t#page-body {\n" +
                "\t\t\t\t\tmargin: 0 1em 1em;\n" +
                "\t\t\t\t}\n" +
                "\n" +
                "\t\t\t\t#page-body h1 {\n" +
                "\t\t\t\t\tmargin-top: 0;\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t</style>\n" +
                "\n" +
                "\n" +
                "\t</head>\n" +
                "\t<body>\n" +
                "\t\t<div id=\"grailslogo\" role=\"banner\"><a href=\"http://grails.org\"><img src=\"/untitled/static/images/grails_logo.png\" alt=\"grails\"/></a></div>\n" +
                "\n" +
                "\t\t<a href=\"#page-body\" class=\"skip\">skip to content&hellip;</a>\n" +
                "\t\t<div id=\"status\" role=\"complementary\">\n" +
                "\t\t\t<h1>application status</h1>\n" +
                "\t\t\t<ul>\n" +
                "\t\t\t\t<li>app version: 0.1</li>\n" +
                "\t\t\t\t<li>grails version: 2.0.3</li>\n" +
                "\t\t\t\t<li>groovy version: 1.8.6</li>\n" +
                "\t\t\t\t<li>jvm version: 1.6.0_26</li>\n" +
                "\t\t\t\t<li>reloading active: true</li>\n" +
                "\t\t\t\t<li>controllers: 0</li>\n" +
                "\t\t\t\t<li>domains: 0</li>\n" +
                "\t\t\t\t<li>services: 1</li>\n" +
                "\t\t\t\t<li>tag libraries: 12</li>\n" +
                "\t\t\t</ul>\n" +
                "\t\t\t<h1>installed plugins</h1>\n" +
                "\t\t\t<ul>\n" +
                "\n" +
                "\t\t\t\t\t<li>logging - 2.0.3</li>\n" +
                "\n" +
                "\t\t\t\t\t<li>core - 2.0.3</li>\n" +
                "\n" +
                "\t\t\t\t\t<li>servlets - 2.0.3</li>\n" +
                "\n" +
                "\t\t\t\t\t<li>i18n - 2.0.3</li>\n" +
                "\n" +
                "\t\t\t\t\t<li>urlmappings - 2.0.3</li>\n" +
                "\n" +
                "\t\t\t\t\t<li>jquery - 1.7.1</li>\n" +
                "\n" +
                "\t\t\t\t\t<li>resources - 1.1.6</li>\n" +
                "\n" +
                "\t\t\t\t\t<li>tomcat - 2.0.3</li>\n" +
                "\n" +
                "\t\t\t\t\t<li>datasource - 2.0.3</li>\n" +
                "\n" +
                "\t\t\t\t\t<li>groovypages - 2.0.3</li>\n" +
                "\n" +
                "\t\t\t\t\t<li>codecs - 2.0.3</li>\n" +
                "\n" +
                "\t\t\t\t\t<li>controllers - 2.0.3</li>\n" +
                "\n" +
                "\t\t\t\t\t<li>mimetypes - 2.0.3</li>\n" +
                "\n" +
                "\t\t\t\t\t<li>filters - 2.0.3</li>\n" +
                "\n" +
                "\t\t\t\t\t<li>domainclass - 2.0.3</li>\n" +
                "\n" +
                "\t\t\t\t\t<li>scaffolding - 2.0.3</li>\n" +
                "\n" +
                "\t\t\t\t\t<li>hibernate - 2.0.3</li>\n" +
                "\n" +
                "\t\t\t\t\t<li>converters - 2.0.3</li>\n" +
                "\n" +
                "\t\t\t\t\t<li>validation - 2.0.3</li>\n" +
                "\n" +
                "\t\t\t\t\t<li>services - 2.0.3</li>\n" +
                "\n" +
                "\t\t\t</ul>\n" +
                "\t\t</div>\n" +
                "\t\t<div id=\"page-body\" role=\"main\">\n" +
                "\t\t\t<h1>welcome to grails</h1>\n" +
                "\t\t\t<p>congratulations, you have successfully started your first grails application! at the moment\n" +
                "\t\t\t   this is the default page, feel free to modify it to either redirect to a controller or display whatever\n" +
                "\t\t\t   content you may choose. below is a list of controllers that are currently deployed in this application,\n" +
                "\t\t\t   click on each to execute its default action:</p>\n" +
                "\n" +
                "\t\t\t<div id=\"controller-list\" role=\"navigation\">\n" +
                "\t\t\t\t<h2>available controllers:</h2>\n" +
                "\t\t\t\t<ul>\n" +
                "\n" +
                "\t\t\t\t</ul>\n" +
                "\t\t\t</div>\n" +
                "\t\t</div>\n" +
                "\n" +
                "\t\t<div class=\"footer\" role=\"contentinfo\"></div>\n" +
                "\t\t<div id=\"spinner\" class=\"spinner\" style=\"display:none;\">loading&hellip;</div>\n" +
                "\n" +
                "        <script src=\"/untitled/static/js/application.js\" type=\"text/javascript\" ></script>\n" +
                "\n" +
                "\t</body>\n" +
                "</html>");
    }

    @Test
    public void should_parse_url_with_trailing_slash() {
        List<Log> logs = readLogs(LogReaderTest.class.getResourceAsStream("log-with-trailing-slash-in-uri.txt"));

        Assertions.assertThat(logs.iterator().next().getPlayer()).playerURLIsEqualTo("http://127.0.0.1:8080/");
    }

    @Test
    public void should_parse_url_without_protocol() {
        List<Log> logs = readLogs(LogReaderTest.class.getResourceAsStream("log-without-protocol-in-uri.txt"));

        Assertions.assertThat(logs.iterator().next().getPlayer()).playerURLIsEqualTo("127.0.0.1:8080");
    }

    @Test
    public void should_parse_negative_scores() {
        List<Log> logs = readLogs(LogReaderTest.class.getResourceAsStream("log-with-negative-score.txt"));
        Log log = logs.iterator().next();

        Assertions.assertThat(log.getPlayer()).scoreIsEqualTo(-10);
        Assertions.assertThat(log).isWrong().scoreIsEqualTo(-10);
    }

    @Test
    public void should_parse_player_without_name() {
        Log log = readLogs(LogReaderTest.class.getResourceAsStream("log-without-player-name.txt")).iterator().next();

        Assertions.assertThat(log.getPlayer()).playerNameIsEqualTo("");
    }

    @Test
    public void should_parse_real_world_log() {
        List<Log> logs = readLogs(LogReaderTest.class.getResourceAsStream("log-xke.txt"));

        assertThat(logs).hasSize(11772);
    }

}