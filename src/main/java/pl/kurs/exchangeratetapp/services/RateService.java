package pl.kurs.exchangeratetapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.kurs.exchangeratetapp.exceptions.InvalidInputDataException;
import pl.kurs.exchangeratetapp.exceptions.LostConnectionException;


import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;

public class RateService implements IRateService {
    private ObjectMapper mapper;
    private IUrlStringBuilder urlStringBuilder;

    public RateService(ObjectMapper mapper, IUrlStringBuilder urlStringBuilder) {
        this.mapper = mapper;
        this.urlStringBuilder = urlStringBuilder;
    }

    @Override
    public BigDecimal getRate(String fromCurrencyMark, String toCurrencyMark) throws InvalidInputDataException, LostConnectionException {
        String preparedUrl = urlStringBuilder.buildUrl(fromCurrencyMark);

        JsonNode mainNode = null;
        try {
            mainNode = mapper.readTree(new URL(preparedUrl));
        } catch (IOException e) {
            throw new LostConnectionException("Brak dostepu do internetu", e.getCause());
        }


        JsonNode results = mainNode.get("results");
        JsonNode specificRateNode = results.get(toCurrencyMark);
        if (specificRateNode == null) throw new InvalidInputDataException("Przekazano błędne dane");

        return new BigDecimal(specificRateNode.asText());
    }
}
