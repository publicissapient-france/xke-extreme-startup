package fr.xebia.extremestartup;

import org.junit.Test;

import java.text.ParseException;

import static org.fest.assertions.Assertions.assertThat;

public class WebServerTest {

    @Test
    public void should_answear_sha1() {
        assertThat(new WebServer().handle("what is the sha1 for \"shelter_tent\""))
                .isEqualTo("ba2181046a2308af3b6949b2cf7e068628b0c49e");
    }

    @Test
    public void should_answear_hexa_addtion() {
        assertThat(new WebServer().handle("what is the decimal value of 0x259 plus 0x2b0")).isEqualTo("1289");
    }

    @Test
    public void should_answear_which_day_of_the_week() throws ParseException {
        assertThat(new WebServer().handle("which day of the week is  4 Mar 2002")).isEqualTo("Monday");
    }

}
