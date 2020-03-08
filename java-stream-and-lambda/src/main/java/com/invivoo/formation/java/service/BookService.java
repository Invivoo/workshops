package com.invivoo.formation.java.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class BookService {

    static Optional<String> getLongestWord(List<String> words) {
        return words.stream()
                    .max(Comparator.comparing(String::length));
    }

    static List<String> getWordsOfSizeUppercase(List<String> words, int size) {
        return words.stream()
                    .filter(word -> word.length() == size)
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());
    }

    static List<String> getDistinctSmallestWordsSorted(List<String> words) {
        OptionalInt min = words.stream()
                               .mapToInt(String::length)
                               .min();

        if (min.isPresent()) {
            int tailleMin = min.getAsInt();

            return words.stream()
                        .filter(word -> word.length() == tailleMin)
                        .distinct()
                        .sorted()
                        .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    static List<String> getDistinctSmallestWordsSorted2(List<String> words) {
        return words.stream()
                    .distinct()
                    .collect(Collectors.groupingBy(String::length))
                    .entrySet().stream()
                    .min(Comparator.comparing(Map.Entry::getKey))
                    .map(Map.Entry::getValue)
                    .orElse(Collections.emptyList())
                    .stream()
                    .sorted()
                    .collect(Collectors.toList());
    }


    static Double getAverageWordLenght(List<String> words) {
        return words.stream()
                    .mapToInt(String::length)
                    .average()
                    .orElse(0.);
    }

    static Map<Integer, List<String>> groupWordsByLenght(List<String> words) {
        return words.stream()
                    .collect(Collectors.groupingBy(String::length));
    }

    static Map<String, Long> getWordOccurrences(List<String> words) {
        return words.stream()
                    .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
    }

    static Map<Boolean, List<String>> getPartionnedWordsByIfContainsUppercase(List<String> words) {
        return words.stream()
                    .collect(Collectors.partitioningBy(w -> w.chars().anyMatch(Character::isUpperCase)));
    }

    static Map.Entry<String, Long> getMostPresentWord(List<String> words) {
        return words.stream()
                    .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                    .entrySet()
                    .stream()
                    .max(Comparator.comparing(Map.Entry::getValue))
                    .get();
    }

    static List<String> getAllDistinctLettersOrderedAlphabetically(List<String> words) {
        return words.stream()
                    .flatMap(BookService::getAlphabeticalCharactersOfWord)
                    .map(String::toUpperCase)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
    }

    private static Stream<String> getAlphabeticalCharactersOfWord(String word) {
        return word.chars()
                   .filter(Character::isLetter)
                   .mapToObj(i -> Character.toString((char) i));
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
    public static List<String> getAllPalindromesUppercase(List<String> words, int minLength) {
        return words.stream()
                    .map(String::toUpperCase)
                    .filter(word -> word.length() >= minLength)
                    .filter(word -> IntStream.range(0, word.length() / 2).allMatch(i -> word.charAt(i) == word.charAt(word.length() - i - 1)))
                    .sorted()
                    .collect(Collectors.toList());
    }

    public static List<String> getAllPalindromesUppercaseNoDistinct(List<String> words, int minLength) {
        return words.stream()
                    .map(String::toUpperCase)
                    .filter(word -> word.length() >= minLength)
                    .filter(word -> IntStream.range(0, word.length() / 2).allMatch(i -> word.charAt(i) == word.charAt(word.length() - i - 1)))
                    .sorted()
                    .collect(Collectors.toList());
    }

    static String getMostPresentPalindromeUppercase(List<String> words, int minLength) {
        return getAllPalindromesUppercaseNoDistinct(words, minLength).stream()
                                                                     .collect(Collectors.groupingBy(word -> word, Collectors.counting()))
                                                                     .entrySet().stream()
                                                                     .max(Comparator.comparing(Map.Entry::getValue))
                                                                     .map(Map.Entry::getKey)
                                                                     .orElse(null);
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
    public static String encryptWithCaesarCipher2(String word, int shift) {
        return word.toLowerCase()
                   .chars()
                   .map(i -> i + shift)
                   .map(i -> (char) i > 'z' ? i - 26 : i)
                   .mapToObj(i -> Character.toString((char) i))
                   .collect(Collectors.joining());
    }

    static String encryptWithCaesarCipher(String word, int shift) {
        return word.chars()
                   .mapToObj(i -> Character.toString((char) i))
                   .map(letter -> getShifted(shift, letter))
                   .map(String::valueOf)
                   .collect(Collectors.joining());
    }

    private static Character getShifted(int shift, String letter) {
        final String alphabet = "abcdefghijklmnopqrstuvwxyz";
        final int alphabetSize = alphabet.length();

        int shifted = alphabet.indexOf(letter) + shift;
        if (shifted >= alphabetSize) {
            shifted = shifted - alphabetSize;
        }
        return alphabet.charAt(shifted);
    }

}
