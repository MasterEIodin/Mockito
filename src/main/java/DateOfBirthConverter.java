import java.time.LocalDate;

public class DateOfBirthConverter {

    public LocalDate convertEgnToDateOfBirth(String egn) {
        int day, month, year;
        month = Integer.parseInt(egn.substring(2, 4));
        if (month > 12) {
            month = month - 40;
            year = 2000 + Integer.parseInt(egn.substring(0, 2));
        } else {
            year = 1900 + Integer.parseInt(egn.substring(0, 2));
        }
        day = Integer.parseInt(egn.substring(4, 6));
        LocalDate dateOfBirth = LocalDate.of(year, month, day);
        return dateOfBirth;
    }
}
