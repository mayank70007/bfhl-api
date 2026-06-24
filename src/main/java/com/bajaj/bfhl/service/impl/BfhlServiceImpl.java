package com.bajaj.bfhl.service.impl;

import com.bajaj.bfhl.dto.BfhlRequest;
import com.bajaj.bfhl.dto.BfhlResponse;
import com.bajaj.bfhl.service.BfhlService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    @Value("${bfhl.user.id}")
    private String userId;

    @Value("${bfhl.email}")
    private String email;

    @Value("${bfhl.roll.number}")
    private String rollNumber;

    @Override
    public BfhlResponse processRequest(BfhlRequest request) {
        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        BigInteger sum = BigInteger.ZERO;

        List<String> originalAlphabets = new ArrayList<>();

        List<String> data = request != null ? request.getData() : null;
        if (data != null) {
            for (String item : data) {
                if (item == null || item.isEmpty()) {
                    continue;
                }
                if (item.matches("^\\d+$")) {
                    BigInteger num = new BigInteger(item);
                    if (num.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
                        evenNumbers.add(item);
                    } else {
                        oddNumbers.add(item);
                    }
                    sum = sum.add(num);
                } else if (item.matches("^[a-zA-Z]+$")) {
                    alphabets.add(item.toUpperCase());
                    originalAlphabets.add(item);
                } else {
                    specialCharacters.add(item);
                }
            }
        }

        String concatString = buildConcatString(originalAlphabets);

        return BfhlResponse.builder()
                .isSuccess(true)
                .userId(userId)
                .email(email)
                .rollNumber(rollNumber)
                .oddNumbers(oddNumbers)
                .evenNumbers(evenNumbers)
                .alphabets(alphabets)
                .specialCharacters(specialCharacters)
                .sum(sum.toString())
                .concatString(concatString)
                .build();
    }

    private String buildConcatString(List<String> originalAlphabets) {
        if (originalAlphabets == null || originalAlphabets.isEmpty()) {
            return "";
        }
        StringBuilder combined = new StringBuilder();
        for (String s : originalAlphabets) {
            combined.append(s);
        }
        String reversed = combined.reverse().toString();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            if (i % 2 == 0) {
                result.append(Character.toUpperCase(c));
            } else {
                result.append(Character.toLowerCase(c));
            }
        }
        return result.toString();
    }
}
