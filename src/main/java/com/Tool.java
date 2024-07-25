package com;
public class Tool {
    private String code;
    private String type;
    private String brand;
    private double dailyCharge;
    private boolean weekdayCharge;
    private boolean weekendCharge;
    private boolean holidayCharge;

    public Tool(String code, String type, String brand, double dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        this.code = code;
        this.type = type;
        this.brand = brand;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    // Getters
    public String getCode() { return code; }
    public String getType() { return type; }
    public String getBrand() { return brand; }
    public double getDailyCharge() { return dailyCharge; }
    public boolean isWeekdayCharge() { return weekdayCharge; }
    public boolean isWeekendCharge() { return weekendCharge; }
    public boolean isHolidayCharge() { return holidayCharge; }
}