package com.bajaj.bfhl.service;

import com.bajaj.bfhl.dto.BfhlRequest;
import com.bajaj.bfhl.dto.BfhlResponse;

/**
 * Service interface for processing the BFHL data array.
 * Any implementation must handle classification, summation, and string manipulation.
 */
public interface BfhlService {

    /**
     * Processes the incoming data array and returns a fully populated response.
     *
     * @param request the incoming request containing a list of mixed strings
     * @return a response with separated numbers, alphabets, special chars, etc.
     */
    BfhlResponse processData(BfhlRequest request);
}
