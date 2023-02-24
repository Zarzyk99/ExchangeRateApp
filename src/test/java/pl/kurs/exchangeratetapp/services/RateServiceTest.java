package pl.kurs.exchangeratetapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.mockito.Mockito;
import pl.kurs.exchangeratetapp.config.AppConfig;
import pl.kurs.exchangeratetapp.exceptions.InvalidInputDataException;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;

import static org.junit.Assert.*;

public class RateServiceTest {
    //todo kończymy na kolejnej lekcji

    @Test
    public void shouldReturn3coma9RateForUsdPlnPair() throws IOException, InvalidInputDataException {
        //given

        String baseHttp = AppConfig.EXCHANGERATESAPI_PAGE;
        String endpoint = AppConfig.PAGE_ENDPOINT;
        String privateApiKey = AppConfig.PRIVATE_API_KEY;
        String currencyParameter = "USD";

        UrlStringBuilder urlStringBuilderMock = Mockito.mock(UrlStringBuilder.class);
        Mockito.when(urlStringBuilderMock
                        .buildUrl(currencyParameter))
                .thenReturn(baseHttp + endpoint + currencyParameter + privateApiKey);
        String preparedUrl = urlStringBuilderMock.buildUrl(currencyParameter);
        ObjectMapper mapperMock = Mockito.mock(ObjectMapper.class);

        String jsonString = "{\"base\":\"USD\",\"results\":{\"PLN\":3.9000000000},\"base\":\"USD\",\"updated\":\"2021-12-16 13:14:35\",\"ms\":2}";
        JsonNode testNode = new ObjectMapper().readTree(jsonString);

        Mockito.when(mapperMock.readTree(new URL(preparedUrl)))
                .thenReturn(testNode); //zamockowane zachowanie objectMapperMocka


        RateService getRateFromUrlService = new RateService(mapperMock, urlStringBuilderMock);

        //when
        BigDecimal exchangeRateFromUrl = getRateFromUrlService.getRate("USD", "PLN");

        //then
        assertTrue(BigDecimal.valueOf(3.9).compareTo(exchangeRateFromUrl) == 0);


    }

}