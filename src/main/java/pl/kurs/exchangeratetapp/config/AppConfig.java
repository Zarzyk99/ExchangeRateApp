package pl.kurs.exchangeratetapp.config;

public interface AppConfig {
    //https://api.fastforex.io/fetch-all?from=EUR&api_key=51c632107c-9a87ee79b8-rje1zh

    String EXCHANGERATESAPI_PAGE = "https://api.fastforex.io";
    String PAGE_ENDPOINT = "/fetch-all?from=";
    String PRIVATE_API_KEY = "&api_key=0a90c839f2-809d9af1da-rjuzpj";
}
