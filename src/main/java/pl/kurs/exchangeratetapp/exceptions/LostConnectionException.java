package pl.kurs.exchangeratetapp.exceptions;

import java.net.UnknownHostException;

public class LostConnectionException extends Exception{
    public LostConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
