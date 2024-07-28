package com;
import java.util.*;

public class RentalService {
    private Map<String, Tool> tools = new HashMap<>();

    public RentalService() {
        tools.put("CHNS", new Tool("CHNS", "Chainsaw", "Stihl", 1.49, true, false, true));
        tools.put("LADW", new Tool("LADW", "Ladder", "Werner", 1.99, true, true, false));
        tools.put("JAKD", new Tool("JAKD", "Jackhammer", "DeWalt", 2.99, true, false, false));
        tools.put("JAKR", new Tool("JAKR", "Jackhammer", "Ridgid", 2.99, true, false, false));
    }

    public RentalAgreement checkout(String toolCode, int rentalDays, int discountPercent, Date checkoutDate) throws InvalidRentalException {
        validateInputs(rentalDays, discountPercent, toolCode);

        Tool tool = tools.get(toolCode);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkoutDate);
        calendar.add(Calendar.DAY_OF_YEAR, rentalDays);
        Date dueDate = calendar.getTime();

        ChargeCalculationResult result = calculateCharges(tool, rentalDays, checkoutDate);

        double discountAmount = result.preDiscountCharge * discountPercent / 100;
        double finalCharge = result.preDiscountCharge - discountAmount;

        return new RentalAgreement(tool, rentalDays, discountPercent, checkoutDate, dueDate, result.chargeableDays, result.preDiscountCharge, discountAmount, finalCharge);
    }

    private void validateInputs(int rentalDays, int discountPercent, String toolCode) throws InvalidRentalException {
        if (rentalDays < 1) {
            throw new InvalidRentalException("Rental day count must be 1 or greater.");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new InvalidRentalException("Discount percent must be between 0 and 100.");
        }
        if (!tools.containsKey(toolCode)) {
            throw new InvalidRentalException("Invalid tool code.");
        }
    }

    private ChargeCalculationResult calculateCharges(Tool tool, int rentalDays, Date checkoutDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkoutDate);

        double preDiscountCharge = 0;
        int chargeableDays = 0;

        for (int i = 0; i < rentalDays; i++) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            boolean isWeekend = (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY);
            boolean isHoliday = isHoliday(calendar);

            if ((isWeekend && tool.isWeekendCharge()) || (!isWeekend && tool.isWeekdayCharge()) || (isHoliday && tool.isHolidayCharge())) {
                preDiscountCharge += tool.getDailyCharge();
                chargeableDays++;
            }
        }

        return new ChargeCalculationResult(preDiscountCharge, chargeableDays);
    }

    private boolean isHoliday(Calendar calendar) {
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        // Independence Day
        if (month == Calendar.JULY && (day == 4 || (day == 3 && dayOfWeek == Calendar.FRIDAY) || (day == 5 && dayOfWeek == Calendar.MONDAY))) {
            return true;
        }

        // Labor Day (first Monday in September)
        if (month == Calendar.SEPTEMBER && dayOfWeek == Calendar.MONDAY && day <= 7) {
            return true;
        }

        return false;
    }

    private static class ChargeCalculationResult {
        double preDiscountCharge;
        int chargeableDays;

        ChargeCalculationResult(double preDiscountCharge, int chargeableDays) {
            this.preDiscountCharge = preDiscountCharge;
            this.chargeableDays = chargeableDays;
        }
    }
}