# Prices Application

## Overview

The **Prices Application** is a Spring Boot project designed to fetch and display various financial data using the Alpha Vantage API. This application allows users to retrieve commodity prices, current commodity prices, exchange rates, and stock information.

## Class Summary

### PricesController

Handles HTTP requests and defines endpoints:
- `/api/v1/prices/commodity`: Fetches commodity prices.
- `/api/v1/prices/commodity/current`: Retrieves the current price of a commodity.
- `/api/v1/prices/exchange-rate`: Retrieves the exchange rate between two currencies.
- `/api/v1/prices/stock/monthly`: Provides stock information for the last month.

### PricesService

Contains business logic for interacting with the Alpha Vantage API:
- `getCommodityPrices(String function, String interval)`: Fetches commodity prices.
- `getCurrentCommodityPrice(String function)`: Retrieves the current price.
- `getExchangeRate(String fromCurrency, String toCurrency)`: Fetches exchange rate.
- `getStockInfoLastMonth(String symbol)`: Retrieves stock information.

### Other Classes

- `CommodityPricesResponse`: Represents commodity price response.
- `ExchangeRateResponse`: Represents exchange rate response.
- `PriceData`: Represents price data points.
- `StockResponse`: Represents stock information response.
- `AppConfig`: Configures `RestTemplate` bean.
- `PricesApplication`: Entry point of the Spring Boot application.

## Example Requests and Responses

- **Commodity Prices**:  
  **Request**: `http://localhost:8080/api/v1/prices/commodity?function=Natural_Gas&interval=daily`  
  **Response**:
  ```json
  {
      "name": "Henry Hub Natural Gas Spot Price",
      "interval": "daily",
      "unit": "dollars per million BTU",
      "data": [
          {
              "date": "2024-07-23",
              "value": "2.13"
          },
          {
              "date": "2024-07-22",
              "value": "2.19"
          }
          // More data points...
      ]
  }

- **Current Commodity Price**:  
  **Request**: `http://localhost:8080/api/v1/prices/commodity/current?function=Corn`  
  **Response**:
  ```json
  {
      191.240942946429
  }

- **Exchange Rate**:  
  **Request**: `http://localhost:8080/api/v1/prices/exchange-rate?fromCurrency=USD&toCurrency=JPY`  
  **Response**:
  ```json
  {
      USD/JPY : 153.72300000
  }

- **Stock Information**:  
  **Request**: `http://localhost:8080/api/v1/prices/stock/monthly?symbol=IBM`  
  **Response**:
  ```json
  {
      IBM (2024-07-26):
       Open: 173.4500
       Close: 191.7500
       High: 196.2600
       Low: 173.3800
       Volume: 69298457
  }


## Dependencies

- Spring Boot Starter
- Spring Boot Starter Web
- Spring Boot Starter Test

## Application Properties

- `spring.application.name=Prices`
- `alphavantage.api.key=YOUR_API_KEY`

## Obtaining a Free API Key

This project uses the Alpha Vantage API to retrieve financial data. To use this API, you need to obtain a free API key from Alpha Vantage. Follow these steps to get your API key:

1. Visit the Alpha Vantage website at [Alpha Vantage Support](https://www.alphavantage.co/support/#api-key).
2. Click on the "Get Your Free API Key" button.
3. Sign up for an account if you don't already have one.
4. Once signed in, your API key will be provided to you. Keep this key secure and do not share it publicly.

After obtaining your API key, add it to your application's configuration file under the property `alphavantage.api.key`:



## JDK

Compatible with JDK 17 and higher, including JDK 20.
