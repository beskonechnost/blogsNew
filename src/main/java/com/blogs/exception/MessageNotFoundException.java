package com.blogs.exception;

public class MessageNotFoundException  extends RuntimeException {

    private static final String NOT_FOUND_EXCEPTION_MESSAGE = "Message with requested ID was not found!";

    public MessageNotFoundException(Long id) {
        super(NOT_FOUND_EXCEPTION_MESSAGE + " ID: " + id);
    }
}
