package edu.goncharova.exceptions;



public class CustomSqlException extends RuntimeException {

    public CustomSqlException() {
        super();
    }

    public CustomSqlException(String message) {

        super(message);
    }
}
