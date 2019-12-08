package com.invivoo.formation.java.service;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookServiceTest {

    private static final String GERMINAL_BOOK_NAME = "germinal.txt";
    private static final String NOT_WORD_CHAR = "[^A-Za-z]";
    private static final String LONGEST_WORD = "proportionnellement";
    private static List<String> germinalWords;

    @BeforeAll
    static void setUp() throws IOException, URISyntaxException {
        URI bookUri = BookServiceTest.class.getClassLoader().getResource(GERMINAL_BOOK_NAME).toURI();

        try (Stream<String> lines = Files.lines(Paths.get(bookUri))) {
            germinalWords = lines.map(word -> word.replaceAll(NOT_WORD_CHAR, " "))
                                 .flatMap(line -> Stream.of(line.split(" ")))
                                 .filter(word -> word.length() > 1)
                                 .filter(StringUtils::isNotBlank)
                                 .collect(Collectors.toList());
        }
    }

    @Test
    void getLongestWord() {
        Optional<String> longestWord = BookService.getLongestWord(germinalWords);

        assertOptionalContainAndEquals(longestWord, LONGEST_WORD);
    }

    @Test
    void getWordsOfSizeUppercase() {
        List<String> wordsOfSizeUppercase = BookService.getWordsOfSizeUppercase(germinalWords, LONGEST_WORD.length());

        assertEquals(1, wordsOfSizeUppercase.size());
        assertEquals(LONGEST_WORD.toUpperCase(), wordsOfSizeUppercase.get(0));
    }

    @Test
    void getSmallestWords() {
        List<String> distinctSmallestWordsSorted = BookService.getDistinctSmallestWordsSorted(germinalWords);

        assertEquals(164, distinctSmallestWordsSorted.size());
        assertEquals("vu", distinctSmallestWordsSorted.get(distinctSmallestWordsSorted.size() - 1));
    }

    @Test
    void getAverageWordLenght() {
        Double averageWordLenght = BookService.getAverageWordLenght(germinalWords);

        assertEquals(4, averageWordLenght.intValue());
    }

    @Test
    void groupWordsByLenght() {
        Map<Integer, List<String>> lenghtsWordsList = BookService.groupWordsByLenght(germinalWords);

        assertEquals(17, lenghtsWordsList.size());
        assertEquals(5, lenghtsWordsList.get(17).size());
    }

    @Test
    void getWordOccurrences() {
        Map<String, Long> wordOccurrences = BookService.getWordOccurrences(germinalWords);

        assertEquals(3772, wordOccurrences.get("le"));
    }

    @Test
    void getPartionnedWordsByIfContainsUppercase() {
        Map<Boolean, List<String>> partionned = BookService.getPartionnedWordsByIfContainsUppercase(germinalWords);

        assertEquals(12837, partionned.get(true).size());
        assertEquals(154447, partionned.get(false).size());
    }

    @Test
    void getMostPresentWord() {
        Map.Entry<String, Long> mostPresentWord = BookService.getMostPresentWord(germinalWords);

        assertEquals("de", mostPresentWord.getKey());
        assertEquals(7231, mostPresentWord.getValue());
    }

    @Test
    void getAllDistinctLettersOrderedAlphabetically() {
        List<String> allBookLetters = BookService.getAllDistinctLettersOrderedAlphabetically(germinalWords);

        assertEquals(26, allBookLetters.size());
        assertEquals("[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z]", allBookLetters.toString());
    }

    @Test
    void getAllPalindromes() {
        List<String> palindromes = BookService.getAllPalindromesUppercase(germinalWords, 3);

        assertEquals(17, palindromes.size());
        assertTrue(palindromes.contains("ELLE"));
    }

    @Test
    void getMostPresentPalindromes() {
        String mostPresentPalindrome = BookService.getMostPresentPalindrome(germinalWords, 3);

        assertEquals("SES", mostPresentPalindrome);
    }

    @Test
    void encryptWithCaesarCipher() {
        String encryptedWithShift1 = BookService.encryptWithCaesarCipher("abcde", 1);
        String encryptedWithShift10 = BookService.encryptWithCaesarCipher("xyz", 10);

        assertEquals("bcdef", encryptedWithShift1);
        assertEquals("hij", encryptedWithShift10);
    }

    @Test
    void encryptWithCaesarCipher2() {
        String encryptedWithShift1 = BookService.encryptWithCaesarCipher2("abcde", 1);
        String encryptedWithShift10 = BookService.encryptWithCaesarCipher2("xyz", 10);

        assertEquals("bcdef", encryptedWithShift1);
        assertEquals("hij", encryptedWithShift10);
    }

    private static <T> void assertOptionalContainAndEquals(Optional<T> value, T expected) {
        Assertions.assertTrue(value.isPresent());
        System.out.println(expected.equals(value.get()));
        value.ifPresent(actual -> assertEquals(actual, expected));
    }
}