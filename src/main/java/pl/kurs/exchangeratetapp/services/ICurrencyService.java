package pl.kurs.exchangeratetapp.services;

import pl.kurs.exchangeratetapp.exceptions.InvalidInputDataException;
import pl.kurs.exchangeratetapp.exceptions.LostConnectionException;

import java.math.BigDecimal;

public interface ICurrencyService {
    BigDecimal exchange(String currencyFrom, String currencyTo, BigDecimal amount) throws InvalidInputDataException, LostConnectionException;
}
