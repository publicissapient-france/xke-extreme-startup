package fr.xebia.extremestartup.logreader;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public enum QuestionType {

    NAME(compile("what is your name")),
    ADDITION(compile("what is \\d+ plus \\d+")),
    MAXIMUM(compile("which of the following numbers is the largest: \\d+(?:, \\d+)*")),
    MULTIPLICATION(compile("what is \\d+ multiplied by \\d+")),
    SQUARE_CUBE(compile("which of the following numbers is both a square and a cube: \\d+(?:, \\d+)*")),
    GENERAL_KNOWLEDGE(compile("who is the Prime Minister of Great Britain" +
            "|which city is the Eiffel tower in" +
            "|what currency did Spain use before the Euro" +
            "|what colour is a banana" +
            "|who played James Bond in the film Dr No")),
    PRIMES(compile("which of the following numbers are primes: \\d+(?:, \\d+)*")),
    SUBSTRACTION(compile("what is \\d+ minus \\d+")),
    FIBONACCI(compile("what is the \\d+(?:st|nd|th) number in the Fibonacci sequence")),
    POWER(compile("what is \\d+ to the power of \\d+")),
    ADDITION_ADDITION(compile("what is \\d+ plus \\d+ plus \\d+")),
    ADDITION_MULTIPLICATION(compile("what is \\d+ plus \\d+ multiplied by \\d+")),
    MULTIPLICATION_ADDITION(compile("what is \\d+ multiplied by \\d+ plus \\d+")),
    ANAGRAM(compile("which of the following is an anagram of \"\\w+\": \\w+(?:, \\w+)*")),
    SCRABBLE(compile("what is the english scrabble score of \\w+")),
    UNKNOWN(compile(""));

    private final Pattern pattern;

    QuestionType(Pattern pattern) {
        this.pattern = pattern;
    }

    static QuestionType getTypeFromQuestion(String question) {
        for (QuestionType questionType : QuestionType.values()) {
            if (questionType.pattern.matcher(question).matches()) {
                return questionType;
            }
        }
        return UNKNOWN;
    }
}
