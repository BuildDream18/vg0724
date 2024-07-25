package com;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RentalAgreement {
    private Tool tool;
    private int rentalDays;
    private int discountPercent;
    private Date checkoutDate;
    private Date dueDate;
    private int chargeableDays;
    private double preDiscountCharge;
    private double discountAmount;
    private double finalCharge;

    public RentalAgreement(Tool tool, int rentalDays, int discountPercent, Date checkoutDate, Date dueDate, int chargeableDays, double preDiscountCharge, double discountAmount, double finalCharge) {
        this.tool = tool;
        this.rentalDays = rentalDays;
        this.discountPercent = discountPercent;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.chargeableDays = chargeableDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public Tool getTool() {
        return tool;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void printAgreement() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        DecimalFormat currencyFormat = new DecimalFormat("$#,##0.00");
        DecimalFormat percentFormat = new DecimalFormat("#%");

        System.out.println("Tool code: " + tool.getCode());
        System.out.println("Tool type: " + tool.getType());
        System.out.println("Tool brand: " + tool.getBrand());
        System.out.println("Rental days: " + rentalDays);
        System.out.println("Check out date: " + dateFormat.format(checkoutDate));
        System.out.println("Due date: " + dateFormat.format(dueDate));
        System.out.println("Daily rental charge: " + currencyFormat.format(tool.getDailyCharge()));
        System.out.println("Charge days: " + chargeableDays);
        System.out.println("Pre-discount charge: " + currencyFormat.format(preDiscountCharge));
        System.out.println("Discount percent: " + percentFormat.format(discountPercent / 100.0));
        System.out.println("Discount amount: " + currencyFormat.format(discountAmount));
        System.out.println("Final charge: " + currencyFormat.format(finalCharge));
    }
}