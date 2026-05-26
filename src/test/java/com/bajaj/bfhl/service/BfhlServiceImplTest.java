package com.bajaj.bfhl.service;

import com.bajaj.bfhl.dto.BfhlRequest;
import com.bajaj.bfhl.dto.BfhlResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BfhlServiceImpl — covers all three examples from the problem
 * statement plus edge cases.
 */
class BfhlServiceImplTest {

    private BfhlService service;

    @BeforeEach
    void setUp() {
        service = new BfhlServiceImpl();
    }

    // ─── Example A ───────────────────────────────────────────────────

    @Test
    @DisplayName("Example A: mixed data with numbers, letters, and special chars")
    void testExampleA() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("a", "1", "334", "4", "R", "$"));
        BfhlResponse response = service.processData(request);

        assertTrue(response.isSuccess());
        assertEquals(List.of("1"), response.getOddNumbers());
        assertEquals(List.of("334", "4"), response.getEvenNumbers());
        assertEquals(List.of("A", "R"), response.getAlphabets());
        assertEquals(List.of("$"), response.getSpecialCharacters());
        assertEquals("339", response.getSum());
        assertEquals("Ra", response.getConcatString());
    }

    // ─── Example B ───────────────────────────────────────────────────

    @Test
    @DisplayName("Example B: more mixed data including multiple special chars")
    void testExampleB() {
        BfhlRequest request = new BfhlRequest(
                Arrays.asList("2", "a", "y", "4", "&", "-", "*", "5", "92", "b")
        );
        BfhlResponse response = service.processData(request);

        assertTrue(response.isSuccess());
        assertEquals(List.of("5"), response.getOddNumbers());
        assertEquals(List.of("2", "4", "92"), response.getEvenNumbers());
        assertEquals(List.of("A", "Y", "B"), response.getAlphabets());
        assertEquals(List.of("&", "-", "*"), response.getSpecialCharacters());
        assertEquals("103", response.getSum());
        assertEquals("ByA", response.getConcatString());
    }

    // ─── Example C ───────────────────────────────────────────────────

    @Test
    @DisplayName("Example C: only multi-character alphabetic strings")
    void testExampleC() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("A", "ABCD", "DOE"));
        BfhlResponse response = service.processData(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertEquals(List.of("A", "ABCD", "DOE"), response.getAlphabets());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("EoDdCbAa", response.getConcatString());
    }

    // ─── Edge cases ──────────────────────────────────────────────────

    @Test
    @DisplayName("Empty data array should return empty lists and sum = 0")
    void testEmptyData() {
        BfhlRequest request = new BfhlRequest(Collections.emptyList());
        BfhlResponse response = service.processData(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    @DisplayName("Only numbers — no alphabets or special characters")
    void testOnlyNumbers() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("1", "2", "3", "100"));
        BfhlResponse response = service.processData(request);

        assertEquals(List.of("1", "3"), response.getOddNumbers());
        assertEquals(List.of("2", "100"), response.getEvenNumbers());
        assertTrue(response.getAlphabets().isEmpty());
        assertEquals("106", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    @DisplayName("Only special characters — no numbers or alphabets")
    void testOnlySpecialChars() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("@", "#", "!", "^"));
        BfhlResponse response = service.processData(request);

        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertEquals(List.of("@", "#", "!", "^"), response.getSpecialCharacters());
        assertEquals("0", response.getSum());
    }

    @Test
    @DisplayName("Single alphabet should return itself in uppercase")
    void testSingleAlphabet() {
        BfhlRequest request = new BfhlRequest(List.of("z"));
        BfhlResponse response = service.processData(request);

        assertEquals(List.of("Z"), response.getAlphabets());
        assertEquals("Z", response.getConcatString());
    }

    @Test
    @DisplayName("Negative numbers should be classified as odd/even correctly")
    void testNegativeNumbers() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("-3", "-4", "0"));
        BfhlResponse response = service.processData(request);

        assertEquals(List.of("-3"), response.getOddNumbers());
        assertEquals(List.of("-4", "0"), response.getEvenNumbers());
        assertEquals("-7", response.getSum());
    }
}
