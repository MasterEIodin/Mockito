import org.testng.Assert;
import org.testng.annotations.Test;

public class EGNGeneratorTest {

    EGNGenerator egnGenerator = new EGNGenerator();

    @Test
    public void testMaleEGN() {
        String maleEGN = egnGenerator.generateUniqueEgn("MALE");
        int gender = Integer.parseInt(String.valueOf(maleEGN.charAt(8)));
        Assert.assertTrue(gender % 2 == 0);
    }

    @Test
    public void testFemaleEGN() {
        String femaleEGN = egnGenerator.generateUniqueEgn("FEMALE");
        int gender = Integer.parseInt(String.valueOf(femaleEGN.charAt(8)));
        Assert.assertTrue(gender % 2 == 1);
    }

    @Test
    public void testEGNConsistsOf10Digits() {
        Assert.assertTrue(egnGenerator.generateUniqueEgn("MALE").length() == 10);
    }
}
