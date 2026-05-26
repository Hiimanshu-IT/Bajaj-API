package com.bajaj.bfhl.service;

import com.bajaj.bfhl.dto.BfhlRequest;
import com.bajaj.bfhl.dto.BfhlResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of BfhlService.
 * Handles all the heavy lifting — classifying input items, computing the sum,
 * and building the alternating-caps reversed concatenation string.
 */
@Service
public class BfhlServiceImpl implements BfhlService {

    // ─── Update these three constants with YOUR details ───────────────
    private static final String USER_ID    = "himanshu_bajaj_26052004";   // full_name_ddmmyyyy
    private static final String EMAIL      = "himanshu@example.com";      // your email
    private static final String ROLL_NO    = "ACRO123";                   // your college roll number
    // ──────────────────────────────────────────────────────────────────

    @Override
    public BfhlResponse processData(BfhlRequest request) {

        List<String> data = request.getData();

        List<String> oddNumbers       = new ArrayList<>();
        List<String> evenNumbers      = new ArrayList<>();
        List<String> alphabets        = new ArrayList<>();
        List<String> specialChars     = new ArrayList<>();
        long totalSum                 = 0;

        // A single pass through the input to classify each element
        for (String item : data) {
            if (isNumeric(item)) {
                long num = Long.parseLong(item);
                totalSum += num;
                if (num % 2 == 0) {
                    evenNumbers.add(item);           // keep the original string form
                } else {
                    oddNumbers.add(item);
                }
            } else if (isAlphabetic(item)) {
                alphabets.add(item.toUpperCase());   // spec says convert alphabets to uppercase
            } else {
                specialChars.add(item);              // everything else is a special character
            }
        }

        // Build the concatenation string (reversed + alternating caps)
        String concatString = buildConcatString(alphabets);

        return BfhlResponse.builder()
                .isSuccess(true)
                .userId(USER_ID)
                .email(EMAIL)
                .rollNumber(ROLL_NO)
                .oddNumbers(oddNumbers)
                .evenNumbers(evenNumbers)
                .alphabets(alphabets)
                .specialCharacters(specialChars)
                .sum(String.valueOf(totalSum))
                .concatString(concatString)
                .build();
    }

    // ─── Helper methods ──────────────────────────────────────────────

    /**
     * Checks if a string represents a valid integer (including negatives).
     */
    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) return false;
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if every character in the string is a letter (a-z, A-Z).
     * Multi-character alphabetic strings like "ABCD" are still considered alphabets.
     */
    private boolean isAlphabetic(String str) {
        if (str == null || str.isEmpty()) return false;
        for (char c : str.toCharArray()) {
            if (!Character.isLetter(c)) return false;
        }
        return true;
    }

    /**
     * Concatenates all individual characters from the alphabets list,
     * reverses the result, and applies alternating capitalisation
     * (uppercase at index 0, lowercase at index 1, and so on).
     *
     * Example: alphabets = ["A", "ABCD", "DOE"]
     *   → joined chars  = "AABCDDOE"
     *   → reversed       = "EODDCBAA"
     *   → alternating    = "EoDdCbAa"
     */
    private String buildConcatString(List<String> alphabets) {
        // Step 1: Flatten all characters into a single string
        StringBuilder joined = new StringBuilder();
        for (String alpha : alphabets) {
            joined.append(alpha);   // alphabets are already uppercase at this point
        }

        // Step 2: Reverse it
        String reversed = joined.reverse().toString();

        // Step 3: Apply alternating caps (uppercase at even indices, lowercase at odd)
        StringBuilder result = new StringBuilder(reversed.length());
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            result.append(i % 2 == 0 ? Character.toUpperCase(c) : Character.toLowerCase(c));
        }

        return result.toString();
    }
}
