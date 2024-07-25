package com;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        RentalService rentalService = new RentalService();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");

        try {
            Date checkoutDate = dateFormat.parse("9/3/15");
            RentalAgreement agreement = rentalService.checkout("JAKR", 5, 101, checkoutDate);
            agreement.printAgreement();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (InvalidRentalException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}