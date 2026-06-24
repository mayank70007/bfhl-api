package com.bajaj.bfhl.service;

import com.bajaj.bfhl.dto.BfhlRequest;
import com.bajaj.bfhl.dto.BfhlResponse;
import com.bajaj.bfhl.service.impl.BfhlServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BfhlServiceTest {

    private BfhlServiceImpl bfhlService;

    @BeforeEach
    void setUp() {
        bfhlService = new BfhlServiceImpl();
        ReflectionTestUtils.setField(bfhlService, "userId", "mayank_24062003");
        ReflectionTestUtils.setField(bfhlService, "email", "your_email@example.com");
        ReflectionTestUtils.setField(bfhlService, "rollNumber", "2310990000");
    }

    @Test
    void test1() {
        BfhlRequest request = BfhlRequest.builder()
                .data(Arrays.asList("a", "1", "334", "4", "R", "$"))
                .build();
        BfhlResponse response = bfhlService.processRequest(request);

        assertTrue(response.isSuccess());
        assertEquals(List.of("1"), response.getOddNumbers());
        assertEquals(Arrays.asList("334", "4"), response.getEvenNumbers());
        assertEquals(Arrays.asList("A", "R"), response.getAlphabets());
        assertEquals(List.of("$"), response.getSpecialCharacters());
        assertEquals(List.of("$"), response.getSepcialCharacters());
        assertEquals("339", response.getSum());
        assertEquals("Ra", response.getConcatString());
    }

    @Test
    void test2() {
        BfhlRequest request = BfhlRequest.builder()
                .data(Arrays.asList("2", "a", "y", "4", "&", "-", "*", "5", "92", "b"))
                .build();
        BfhlResponse response = bfhlService.processRequest(request);

        assertTrue(response.isSuccess());
        assertEquals(List.of("5"), response.getOddNumbers());
        assertEquals(Arrays.asList("2", "4", "92"), response.getEvenNumbers());
        assertEquals(Arrays.asList("A", "Y", "B"), response.getAlphabets());
        assertEquals(Arrays.asList("&", "-", "*"), response.getSpecialCharacters());
        assertEquals(Arrays.asList("&", "-", "*"), response.getSepcialCharacters());
        assertEquals("103", response.getSum());
        assertEquals("ByA", response.getConcatString());
    }

    @Test
    void test3() {
        BfhlRequest request = BfhlRequest.builder()
                .data(Arrays.asList("A", "ABCD", "DOE"))
                .build();
        BfhlResponse response = bfhlService.processRequest(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertEquals(Arrays.asList("A", "ABCD", "DOE"), response.getAlphabets());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("EoDdCbAa", response.getConcatString());
    }

    @Test
    void test4() {
        BfhlRequest request = BfhlRequest.builder()
                .data(Collections.emptyList())
                .build();
        BfhlResponse response = bfhlService.processRequest(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    void test5() {
        BfhlRequest request = BfhlRequest.builder()
                .data(Arrays.asList("1", "3", "5", "7"))
                .build();
        BfhlResponse response = bfhlService.processRequest(request);

        assertTrue(response.isSuccess());
        assertEquals(Arrays.asList("1", "3", "5", "7"), response.getOddNumbers());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertEquals("16", response.getSum());
    }

    @Test
    void test6() {
        BfhlRequest request = BfhlRequest.builder()
                .data(Arrays.asList("2", "4", "6", "8"))
                .build();
        BfhlResponse response = bfhlService.processRequest(request);

        assertTrue(response.isSuccess());
        assertEquals(Arrays.asList("2", "4", "6", "8"), response.getEvenNumbers());
        assertTrue(response.getOddNumbers().isEmpty());
        assertEquals("20", response.getSum());
    }

    @Test
    void test7() {
        BfhlRequest request = BfhlRequest.builder()
                .data(Arrays.asList("$", "@", "#"))
                .build();
        BfhlResponse response = bfhlService.processRequest(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertEquals(Arrays.asList("$", "@", "#"), response.getSpecialCharacters());
        assertEquals(Arrays.asList("$", "@", "#"), response.getSepcialCharacters());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    void test8() {
        BfhlRequest request = BfhlRequest.builder()
                .data(Arrays.asList("abc", "XYZ"))
                .build();
        BfhlResponse response = bfhlService.processRequest(request);

        assertTrue(response.isSuccess());
        assertEquals(Arrays.asList("ABC", "XYZ"), response.getAlphabets());
        assertEquals("ZyXcBa", response.getConcatString());
        assertEquals("0", response.getSum());
    }

    @Test
    void test9() {
        BfhlRequest request = BfhlRequest.builder()
                .data(Arrays.asList("999999", "888888"))
                .build();
        BfhlResponse response = bfhlService.processRequest(request);

        assertTrue(response.isSuccess());
        assertEquals("1888887", response.getSum());
    }

    @Test
    void test10() {
        BfhlRequest request = BfhlRequest.builder()
                .data(Arrays.asList("abc123", "1a", "@test"))
                .build();
        BfhlResponse response = bfhlService.processRequest(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertEquals(Arrays.asList("abc123", "1a", "@test"), response.getSpecialCharacters());
        assertEquals(Arrays.asList("abc123", "1a", "@test"), response.getSepcialCharacters());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }
}
