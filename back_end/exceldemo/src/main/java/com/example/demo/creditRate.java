package com.example.demo;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class creditRate {

	@Excel(name = "rate", orderNum = "0")
	private String rate;

	@Excel(name = "year", orderNum = "1")
	private String year;

    public creditRate(String rate, String year) {
        this.rate = rate;
        this.year = year;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

}
