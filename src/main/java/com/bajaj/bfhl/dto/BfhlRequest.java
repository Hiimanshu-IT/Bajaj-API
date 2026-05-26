package com.bajaj.bfhl.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * Request DTO for the /bfhl endpoint.
 * Expects a JSON body like: { "data": ["a", "1", "334", "R", "$"] }
 */
public class BfhlRequest {

    @NotNull(message = "The 'data' field cannot be null")
    private List<String> data;

    public BfhlRequest() {
    }

    public BfhlRequest(List<String> data) {
        this.data = data;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
