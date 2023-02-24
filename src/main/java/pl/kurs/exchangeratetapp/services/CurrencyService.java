package pl.kurs.exchangeratetapp.services;

import pl.kurs.exchangeratetapp.exceptions.InvalidInputDataException;
import pl.kurs.exchangeratetapp.exceptions.LostConnectionException;

import java.math.BigDecimal;

public class CurrencyService implements ICurrencyService {
    private IRateService rateService;

    public CurrencyService(IRateService rateService) {
        this.rateService = rateService;
    }


    @Override
    public BigDecimal exchange(String currencyFrom, String currencyTo, BigDecimal amount) throws InvalidInputDataException, LostConnectionException {
        if (currencyFrom.isBlank() || currencyTo.isBlank())
            throw new InvalidInputDataException("Przekazano błędne dane!");
        if (amount.doubleValue() <= 0) throw new InvalidInputDataException("Wartość musi być większa od 0!");
        BigDecimal rate = rateService.getRate(currencyFrom, currencyTo);

        return rate.multiply(amount);
    }
}
