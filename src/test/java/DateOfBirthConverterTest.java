import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DateOfBirthConverterTest {

    private DateOfBirthConverter converter = new DateOfBirthConverter();

    @Test
    public void testDateOfBirthConverterYearOfBirthBefore2000() {
        EGNGenerator mockito = mock(EGNGenerator.class);
        String egn = "7610037721";
        when(mockito.generateUniqueEgn("FEMALE")).thenReturn(egn);
        LocalDate dateOfBirth = converter.convertEgnToDateOfBirth(egn);
        LocalDate dateToCompare = LocalDate.of(1976, 10, 03);
        Assert.assertEquals(dateOfBirth, dateToCompare);
    }

    @Test
    public void testDateOfBirthConverterYearOfBirthAfter2000() {
        EGNGenerator mockito = mock(EGNGenerator.class);
        String egn = "0052214060";
        when(mockito.generateUniqueEgn("FEMALE")).thenReturn(egn);
        LocalDate dateOfBirth = converter.convertEgnToDateOfBirth(egn);
        LocalDate dateToCompare = LocalDate.of(2000, 12, 21);
        Assert.assertEquals(dateOfBirth, dateToCompare);
    }
}