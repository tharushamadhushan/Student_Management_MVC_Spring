package com.example.studentmanagement.exception;

public class InvalidDataException extends RuntimeException{
    public InvalidDataException(String message){
        super(message);
    }
}
