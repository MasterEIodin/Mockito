
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import com.github.javafaker.Faker;


import static java.lang.System.currentTimeMillis;

public class DataGenerator {
    private Faker faker = new Faker();

    public String uniqueEmail() {
        return "Automation" + currentTimeMillis() + faker.internet().emailAddress();
    }

    public String username() {
        String username = faker.name().username().replace(".", "") + randomDigits(4);
        return username.substring(0, Math.min(20, username.length()));
    }

    public String address() {
        return faker.address().fullAddress();
    }

    public String randomDigits(int digitsCount) {
        return faker.number().digits(digitsCount);
    }

    public int numberBetween(int min, int max) {
        return faker.number().numberBetween(min, max);
    }

    public Date dateOfBirth() {
        return dateOfBirth(18, 100);
    }

    public Date dateOfBirth(int min, int max) {
        Date yearsAgo = sameDayYearsAgo(min);
        Date date;
        do {
            date = faker.date().birthday(min, max);
        } while (!date.before(yearsAgo));
        LocalDate tempDate = date.toInstant().atZone(ZoneId.of("GMT")).toLocalDate();
        return Date.from(tempDate.atStartOfDay(ZoneId.of("GMT")).toInstant());
    }

    public Date sameDayYearsAgo(int age) {
        return Date.from(LocalDate.now().minusYears(age).atStartOfDay(ZoneId.of("GMT")).toInstant());
    }

    public int evenNumbersFrom0to8() {
        return faker.number().numberBetween(0, 4) * 2;
    }

    public int oddNumbersFrom1to9() {
        return evenNumbersFrom0to8() + 1;
    }

    public String firstName() {
        return faker.name().firstName();
    }

    public String lastName() {
        return faker.name().lastName();
    }

    public String translationText() {
        return faker.name().firstName();
    }

    public long randomLongAmount(int min, int max) {
        return (long) faker.number().randomDouble(2, min, max);
    }

    public double randomDoubleAmount(int min, int max) {
        return faker.number().randomDouble(2, min, max);
    }

    public String randomText() {
        return faker.lorem().characters(10, 15);
    }

    public String beerNames() {
        return faker.beer().name();
    }

    public String randomHexColorCode() {
        int randNum = (int) Math.floor(Math.random() * 16777215);
        return String.format("#%06x", randNum);
    }
}
