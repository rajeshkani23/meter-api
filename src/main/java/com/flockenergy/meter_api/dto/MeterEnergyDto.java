package com.flockenergy.meter_api.dto;

public class MeterEnergyDto {

    private String timestamp;
    private String kwh;
    private String kvah;
    private String voltR;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getKwh() {
        return kwh;
    }

    public void setKwh(String kwh) {
        this.kwh = kwh;
    }

    public String getKvah() {
        return kvah;
    }

    public void setKvah(String kvah) {
        this.kvah = kvah;
    }

    public String getVoltR() {
        return voltR;
    }

    public void setVoltR(String voltR) {
        this.voltR = voltR;
    }
}