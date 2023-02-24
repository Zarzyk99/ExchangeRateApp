package pl.kurs.exchangeratetapp.services;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.kurs.exchangeratetapp.exceptions.InvalidInputDataException;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CurrencyServiceTest {
    @Mock
    IRateService rateService;
    CurrencyService currencyService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        currencyService = new CurrencyService(rateService);
    }

    @Test
    public void shouldReturn500PlnFrom100Usd() throws InvalidInputDataException {
        //given
        Mockito.when(rateService.getRate("USD", "PLN")).thenReturn(new BigDecimal(5.0));

        //when
        BigDecimal exchangeResult = currencyService.exchange("USD", "PLN", new BigDecimal(100));

        //then
        Assertions.assertThat(exchangeResult).isEqualTo(new BigDecimal(500));
    }

    @Test
    public void shouldThrowInvalidInputDataExceptionWhenOneOfCurrencyIsEmpty() {

        Exception e = Assert.assertThrows(InvalidInputDataException.class, () -> currencyService.exchange("", "PLN", new BigDecimal(100)));

        Assertions.assertThat(e)
                .isExactlyInstanceOf(InvalidInputDataException.class)
                .hasMessage("Przekazano błędne dane!")
                .hasFieldOrProperty("message");
    }


    @Test
    public void shouldThrowInvalidInputDataExceptionWhenAmountIsLessThanZero() {

        Exception e = Assert.assertThrows(InvalidInputDataException.class, () -> currencyService.exchange("USD", "PLN", new BigDecimal(-5.0)));

        Assertions.assertThat(e)
                .isExactlyInstanceOf(InvalidInputDataException.class)
                .hasMessage("Wartość musi być większa od 0!")
                .hasFieldOrProperty("message");


    }
    //todo zrobić testy sprawdzające czy wyjątki są odpowiednio rzucane

}