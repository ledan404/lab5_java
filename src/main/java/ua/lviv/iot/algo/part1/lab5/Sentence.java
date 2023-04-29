package ua.lviv.iot.algo.part1.lab5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sentence {
    public String replaceFirst(String text) {
        Pattern patternSentence = Pattern.compile("(\\w\\s*)+?[.!?]");
        Pattern patternWord = Pattern.compile("\\w+");
        Pattern patternLetter = Pattern.compile("\\b[euioa]\\w*", Pattern.CASE_INSENSITIVE);
        Matcher matcherSentence = patternSentence.matcher(text);
        StringBuilder result = new StringBuilder();
        while (matcherSentence.find()) {
            String sentence = matcherSentence.group();
            String wordWithLetter = "";
            String longWord = "";
            Matcher matcherLetter = patternLetter.matcher(sentence);
            Matcher matcherWord = patternWord.matcher(sentence);
            if (matcherLetter.find()) {
                wordWithLetter = matcherLetter.group();
            }
            int wordLetterPosition = 0;
            int longWordPosition = 0;
            int wordsCount = 0;
            while (matcherWord.find()) {
                wordsCount++;
                String current = matcherWord.group();
                if (current.equals(wordWithLetter)) {
                    wordLetterPosition = wordsCount;
                }
                if (current.length() > longWord.length()) {
                    longWord = current;
                    longWordPosition = wordsCount;
                }
            }
            if (wordWithLetter != "" && longWord != "" && wordLetterPosition != longWordPosition) {
                if (wordLetterPosition > longWordPosition) {
                    sentence = sentence.replaceAll("(.*?)(\\b" + longWord + "\\w*)(.*)", "$1" + wordWithLetter + "$3");
                    sentence = sentence.replaceAll("(" + wordWithLetter + ")(.*?)(" + wordWithLetter + ")", "$1$2" + longWord);
                }
                if (longWordPosition > wordLetterPosition) {
                    sentence = sentence.replaceAll("(.*?)(\\b" + wordWithLetter + "\\w*)(.*)", "$1" + longWord + "$3");
                    sentence = sentence.replaceAll("(" + longWord + ")(.*?)(" + longWord + ")", "$1$2" + wordWithLetter);
                }
            }
            result.append(sentence.concat("\n"));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Sentence sentence = new Sentence();
        System.out.println(sentence.replaceFirst("Lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident sunt in culpa qui officia deserunt mollit anim id est laborum."));
    }
}
