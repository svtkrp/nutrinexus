package com.svtkrp.nutrinexus.controller;

import com.svtkrp.nutrinexus.domain.UserModel;
import com.svtkrp.nutrinexus.form.UserEditCredsForm;
import com.svtkrp.nutrinexus.service.UserService;
import com.svtkrp.nutrinexus.validation.Errors;
import com.svtkrp.nutrinexus.validation.Orders;
import lombok.RequiredArgsConstructor;
import com.svtkrp.nutrinexus.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("all")
    public List<UserModel> all() {
        return userService.findAllUsers();
    }

    @GetMapping("usernameLike/{username}")
    public List<UserModel> allByUsernameLike(@PathVariable("username") String username) {
        return userService.findAllUsersByUsernameLike(username);
    }

    @GetMapping("username/{username}")
    public UserModel getByUsername(@PathVariable("username") String username) {
        return userService.findUserByUsername(username);
    }

    @GetMapping("id/{id}")
    public UserModel getById(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }

    @PostMapping("create")
    public UserModel create(@Validated(Orders.All.class) @RequestBody UserEditCredsForm form) {
        Errors errors = new Errors();
        UserModel user = userService.createUser(form, errors);
        check(errors);
        return user;
    }

    private void check(Errors errors) {
        if (errors.hasErrors()) {
            throw new ValidationException(errors.getErrors());
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValidExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        throw new ValidationException(errors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public Map<String, String> handleValidationExceptions(
            ValidationException ex) {
        return ex.getErrors();
    }
}
