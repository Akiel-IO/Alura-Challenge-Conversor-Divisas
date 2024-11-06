package com.alura.conversor.models;

public class ConversionResume {
    private String amount;
    private String baseCode;
    private String targetCode;
    private String conversionRate;

    public String getAmount() {
        return amount;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public String getTargetCode() {
        return targetCode;
    }

    public String getConversionRate() {
        return conversionRate;
    }

    public ConversionResume(ApiRecord apiResponse, String amount) {
        this.amount = amount;
        this.baseCode = apiResponse.baseCode();
        this.targetCode = apiResponse.targetCode();
        this.conversionRate = apiResponse.conversionResult();
    }

    public String showResume() {
        return getAmount()+"["+getBaseCode()+"]Equivalen a: "+getConversionRate()+"["+getTargetCode()+"]";
    }
}
