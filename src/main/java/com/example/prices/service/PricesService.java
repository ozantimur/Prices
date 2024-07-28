package com.example.prices.service;

import com.example.prices.response.CommodityPricesResponse;
import com.example.prices.response.ExchangeRateResponse;
import com.example.prices.response.StockResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PricesService {

    @Value("${alphavantage.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public PricesService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CommodityPricesResponse getCommodityPrices(String function, String interval) {
        if (interval == null || interval.isEmpty()) {
            interval = "monthly";
        }
        String url = String.format("https://www.alphavantage.co/query?function=%s&interval=%s&apikey=%s", function, interval, apiKey);
        return restTemplate.getForObject(url, CommodityPricesResponse.class);
    }

    public String getCurrentCommodityPrice(String function) {
        String url = String.format("https://www.alphavantage.co/query?function=%s&interval=daily&apikey=%s", function, apiKey);
        CommodityPricesResponse response = restTemplate.getForObject(url, CommodityPricesResponse.class);
        System.out.println(response);
        return response.getData().get(0).getValue();
    }

    public String getExchangeRate(String fromCurrency, String toCurrency) {
        String url = String.format("https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=%s&to_currency=%s&apikey=%s", fromCurrency, toCurrency, apiKey);
        ExchangeRateResponse.RealtimeCurrencyExchangeRate exchangeRate = restTemplate.getForObject(url, ExchangeRateResponse.class).getRealtimeCurrencyExchangeRate();
        return exchangeRate.getFromCurrencyCode() + "/" + exchangeRate.getToCurrencyCode() + " : " + exchangeRate.getExchangeRate();
    }

    public String getStockInfoLastMonth(String symbol) {
        try {
            String url = String.format("https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=%s&apikey=%s", symbol, apiKey);
            StockResponse stockResponse = restTemplate.getForObject(url, StockResponse.class);

            if (stockResponse == null || stockResponse.getMonthlyTimeSeries() == null || stockResponse.getMonthlyTimeSeries().isEmpty()) {
                return "No data available for the specified symbol.";
            }

            String latestDate = stockResponse.getMonthlyTimeSeries().keySet().iterator().next(); // Get the latest date
            StockResponse.MonthlyData data = stockResponse.getMonthlyTimeSeries().get(latestDate);

            if (data == null) {
                return "No data available for the latest date.";
            }

            String response = stockResponse.getMetaData().getSymbol() + " (" + stockResponse.getMetaData().getLastRefreshed() + "):\n"
                    + " Open: " + data.getOpen() + "\n"
                    + " Close: " + data.getClose() + "\n"
                    + " High: " + data.getHigh() + "\n"
                    + " Low: " + data.getLow() + "\n"
                    + " Volume: " + data.getVolume();

            return response;

        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while fetching stock information.";
        }
    }

}
