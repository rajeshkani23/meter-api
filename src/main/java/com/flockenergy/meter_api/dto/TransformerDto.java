
package com.flockenergy.meter_api.dto;

public class TransformerDto {

    private String code;
    private String name;
    private String feederCode;
    private Integer capacityKva;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeederCode() {
        return feederCode;
    }

    public void setFeederCode(String feederCode) {
        this.feederCode = feederCode;
    }

    public Integer getCapacityKva() {
        return capacityKva;
    }

    public void setCapacityKva(Integer capacityKva) {
        this.capacityKva = capacityKva;
    }

    @Override
    public String toString() {
        return "TransformerDto{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", feederCode='" + feederCode + '\'' +
                ", capacityKva=" + capacityKva +
                '}';
    }
}
