package pl.kurs.exchangeratetapp.services;

import pl.kurs.exchangeratetapp.exceptions.InvalidInputDataException;
import pl.kurs.exchangeratetapp.exceptions.LostConnectionException;

import java.math.BigDecimal;

public interface IRateService {
    BigDecimal getRate(String fromCurrencyMark, String toCurrencyMark) throws InvalidInputDataException, LostConnectionException;
}
