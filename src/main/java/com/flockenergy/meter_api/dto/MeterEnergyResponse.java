package com.flockenergy.meter_api.dto;

import java.util.List;

public class MeterEnergyResponse {

    private List<MeterEnergyDto> data;

    public List<MeterEnergyDto> getData() {
        return data;
    }

    public void setData(List<MeterEnergyDto> data) {
        this.data = data;
    }
}