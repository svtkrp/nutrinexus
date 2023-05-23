package com.svtkrp.nutrinexus.validation;

import java.util.HashMap;
import java.util.Map;

public class Errors {

    private final Map<String, String> errors;

    public Errors() {
        this.errors = new HashMap<>();
    }

    public Map<String, String> getErrors() {
        return this.errors;
    }

    public boolean hasErrors() {
        return this.errors.size() > 0;
    }

    public void reject(String error) {
        this.errors.put("_global", error);
    }

    public void rejectValue(String field, String error) {
        this.errors.put(field, error);
    }

}
