package com.invivoo.formation.java.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class BookService {

    static Optional<String> getLongestWord(List<String> words) {
        return Optional.empty();
    }

    static List<String> getWordsOfSizeUppercase(List<String> words, int size) {
        return Collections.emptyList();
    }

    static List<String> getDistinctSmallestWordsSorted(List<String> words) {
        return Collections.emptyList();
    }

    static Double getAverageWordLenght(List<String> words) {
        return 0d;
    }

    static Map<Integer, List<String>> groupWordsByLenght(List<String> words) {
        return new HashMap<>();
    }

    static Map<String, Long> getWordOccurrences(List<String> words) {
        return new HashMap<>();
    }

    static Map<Boolean, List<String>> getPartionnedWordsByIfContainsUppercase(List<String> words) {
        return new HashMap<>();
    }

    static Map.Entry<String, Long> getMostPresentWord(List<String> words) {
        return null;
    }

    static List<String> getAllDistinctLettersOrderedAlphabetically(List<String> words) {
        return Collections.emptyList();
    }

    static List<String> getAllPalindromesUppercase(List<String> words, int minLength) {
        return Collections.emptyList();
    }

    static String getMostPresentPalindrome(List<String> words, int minLength) {
        return "";
    }

    /**
     * Par exemple avec un shift de 3 voici l'alphabet
     *
     * clair   : ABCDEFGHIJKLMNOPQRSTUVWXYZ
     * chiffré : DEFGHIJKLMNOPQRSTUVWXYZABC
     *
     * @param word to encrypt
     * @param shift
     * @return
     */
    static String encryptWithCaesarCipher(String word, int shift) {
        return "";
    }

}
