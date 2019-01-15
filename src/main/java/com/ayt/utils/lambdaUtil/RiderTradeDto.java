package com.ayt.utils.lambdaUtil;

/**
 * Description
 * Author ayt  on 2019110
 */
public class RiderTradeDto {
    private String code ;
    private String mean;

    public RiderTradeDto(String code, String mean) {
        this.code = code;
        this.mean = mean;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }
}
