package com.svtkrp.nutrinexus.validation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Getter
@RequiredArgsConstructor
public class ValidationException extends RuntimeException {

    private final Map<String, String> errors;

}
