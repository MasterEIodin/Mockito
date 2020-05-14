import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class EGNGenerator {

    private long[] egnWeights = {2, 4, 8, 5, 10, 9, 7, 3, 6};

    private DataGenerator dataGenerator = new DataGenerator();

    public String generateEgn(String gender, Date dob) {
        SimpleDateFormat formatShortYear = new SimpleDateFormat("yy");
        SimpleDateFormat formatLongYear = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
        SimpleDateFormat formatDay = new SimpleDateFormat("dd");

        // Set timezone of formatter to UTC to ignore local timezone when formatting
        formatShortYear.setTimeZone(TimeZone.getTimeZone("UTC"));
        formatLongYear.setTimeZone(TimeZone.getTimeZone("UTC"));
        formatMonth.setTimeZone(TimeZone.getTimeZone("UTC"));
        formatDay.setTimeZone(TimeZone.getTimeZone("UTC"));

        int longYear = Integer.parseInt(formatLongYear.format(dob));
        int shortYear = Integer.parseInt(formatShortYear.format(dob));
        int month = Integer.parseInt(formatMonth.format(dob));
        int day = Integer.parseInt(formatDay.format(dob));

        if (longYear < 1900) {
            month += 20;
        } else if (longYear > 1999) {
            month += 40;
        }

        String genderDigit;
        if (gender.equalsIgnoreCase("MALE")) {
            genderDigit = String.format("%01d", dataGenerator.evenNumbersFrom0to8());
        } else {
            genderDigit = String.format("%01d", dataGenerator.oddNumbersFrom1to9());
        }

        long sum = 0;

        String egn = String.format("%02d%02d%02d%02d%s", shortYear, month, day, dataGenerator.numberBetween(1, 99), genderDigit);

        for (int i = 0; i < egnWeights.length; i++) {
            sum = sum + (Character.getNumericValue(egn.charAt(i)) * egnWeights[i]);
        }

        long remainder = sum % 11;
        if (remainder == 10) {
            remainder = 0;
        }
        return egn.concat(String.valueOf(remainder));
    }

    public String generateUniqueEgn(String gender) {
        return generateUniqueEgn(gender, 18, 100);
    }

    public String generateUniqueEgn(String gender, int minAge, int maxAge) {
        return generateUniqueEgn(gender, dataGenerator.dateOfBirth(minAge, maxAge));
    }

    public String generateUniqueEgn(String gender, Date dob) {
        return  generateEgn(gender, dob);
    }
}
