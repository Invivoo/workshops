package com.invivoo.formation.java.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class BookService {

    public static Optional<String> getLongestWord(List<String> words) {
        return Optional.empty();
    }

    public static List<String> getWordsOfSizeUppercase(List<String> words, int size) {
        return Collections.emptyList();
    }

    public static List<String> getDistinctSmallestWordsSorted(List<String> words) {
        return Collections.emptyList();
    }

    public static Double getAverageWordLenght(List<String> words) {
        return 0d;
    }

    public static Map<Integer, List<String>> groupWordsByLenght(List<String> words) {
        return new HashMap<>();
    }

    public static Map<String, Long> getWordOccurrences(List<String> words) {
        return new HashMap<>();
    }

    public static Map<Boolean, List<String>> getPartionnedWordsByIfContainsUppercase(List<String> words) {
        return new HashMap<>();
    }

    private static boolean containUppercase(String word) {
        return word.chars().anyMatch(Character::isUpperCase);
    }

    public static Map.Entry<String, Long> getMostPresentWord(List<String> words) {
        return null;
    }

    private static Stream<String> getAlphabeticalCharactersOfWord(String word) {
        return null;
    }

    public static List<String> getAllDistinctLettersOrderedAlphabetically(List<String> words) {
        return Collections.emptyList();
    }


    public static List<String> getAllPalindromesUppercase(List<String> words, int minLength) {
        return Collections.emptyList();
    }

    public static String getMostPresentPalindrome(List<String> words, int minLength) {
        return "";
    }

    public static String encryptWithCaesarCipher(String word, int shift) {
        return "";
    }

    public static String encryptWithCaesarCipher2(String word, int shift) {
        return "";
    }

}
