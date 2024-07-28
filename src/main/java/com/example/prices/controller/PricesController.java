package com.example.prices.controller;

import com.example.prices.response.CommodityPricesResponse;
import com.example.prices.response.ExchangeRateResponse;
import com.example.prices.service.PricesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/prices")
public class PricesController {

    private final PricesService pricesService;

    @Autowired
    public PricesController(PricesService pricesService) {
        this.pricesService = pricesService;
    }

    @GetMapping("/commodity")
    public CommodityPricesResponse getCommodityPrices(@RequestParam String function, @RequestParam(required = false, defaultValue = "monthly") String interval) {
        return pricesService.getCommodityPrices(function, interval);
    }

    @GetMapping("/commodity/current")
    public String getCurrentCommodityPrice(@RequestParam String function) {
        return pricesService.getCurrentCommodityPrice(function);
    }

    @GetMapping("/exchange-rate")
    public String getExchangeRate(
            @RequestParam String fromCurrency,
            @RequestParam String toCurrency) {
        return pricesService.getExchangeRate(fromCurrency, toCurrency);
    }

    @GetMapping("/stock/monthly")
    public String getStockInfoLastMonth(@RequestParam String symbol) {
        return pricesService.getStockInfoLastMonth(symbol);
    }
}
