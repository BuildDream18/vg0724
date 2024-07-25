package com;
import org.junit.jupiter.api.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class RentalServiceTest {
    private RentalService rentalService = new RentalService();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");

    @Test
    public void testCheckout() throws ParseException {
        Date checkoutDate = dateFormat.parse("7/2/20");

        try {
            RentalAgreement agreement = rentalService.checkout("LADW", 3, 10, checkoutDate);
            assertNotNull(agreement);
            assertEquals("LADW", agreement.getTool().getCode());
            assertEquals(3, agreement.getRentalDays());
            assertEquals(10, agreement.getDiscountPercent());
            assertEquals(checkoutDate, agreement.getCheckoutDate());
        } catch (InvalidRentalException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    public void testInvalidRentalDays() {
        Date checkoutDate = new Date();
        Exception exception = assertThrows(InvalidRentalException.class, () -> {
            rentalService.checkout("JAKR", 0, 10, checkoutDate);
        });
        assertEquals("Rental day count must be 1 or greater.", exception.getMessage());
    }

    @Test
    public void testInvalidDiscountPercent() {
        Date checkoutDate = new Date();
        Exception exception = assertThrows(InvalidRentalException.class, () -> {
            rentalService.checkout("JAKR", 5, 101, checkoutDate);
        });
        assertEquals("Discount percent must be between 0 and 100.", exception.getMessage());
    }
}