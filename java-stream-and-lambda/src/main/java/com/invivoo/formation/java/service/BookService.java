package com.invivoo.formation.java.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class BookService {

    static Optional<String> getLongestWord(List<String> words) {
        return words.stream()
                    .max(Comparator.comparing(String::length));

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

    /**
     * Un palindrome est un mot que l'on peut lire indifféremment de gauche à droite
     * et de droite à gauche.
     * <p>
     * Par exemple ELLE
     *
     * @param words
     * @param minLength
     *
     * @return
     */
    static List<String> getAllPalindromesUppercase(List<String> words, int minLength) {
        return Collections.emptyList();
    }

    static String getMostPresentPalindromeUppercase(List<String> words, int minLength) {
        return "";
    }

    /**
     * Par exemple avec un shift de 3 voici l'alphabet
     * Nous admettons qu'il faut passer les lettres en minuscules et nous n'avons pas
     * d'autres caractère en paramètre de la fonction que des lettres
     * <p>
     * clair   : abcz
     * chiffré : defc
     *
     * @param word  to encrypt
     * @param shift
     *
     * @return
     */
    static String encryptWithCaesarCipher(String word, int shift) {
        return "";
    }

}
