package com.example.prices.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CommodityPricesResponse {
    private String name;
    private String interval;
    private String unit;

    @JsonProperty("data")
    private List<PriceData> data;

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<PriceData> getData() {
        return data;
    }

    public void setData(List<PriceData> data) {
        this.data = data;
    }

}
