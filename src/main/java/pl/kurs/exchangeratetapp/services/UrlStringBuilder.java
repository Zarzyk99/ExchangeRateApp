package pl.kurs.exchangeratetapp.services;

import pl.kurs.exchangeratetapp.config.AppConfig;

public class UrlStringBuilder implements IUrlStringBuilder{

    @Override
    public String buildUrl(String currencyParameter) {
        return AppConfig.EXCHANGERATESAPI_PAGE + AppConfig.PAGE_ENDPOINT + currencyParameter + AppConfig.PRIVATE_API_KEY;
    }
}
