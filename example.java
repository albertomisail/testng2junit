package tck.java.time;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test LocalDate.
 */
@Test
public class TCKLocalDate {

    @DataProvider(name="sampleBadParse")
    Object[][] provider_sampleBadParse() {
        return new Object[][]{
                {"2008/07/05"},
                {"10000-01-01"},
        };
    }

    @Test(dataProvider="sampleBadParse", expectedExceptions={DateTimeParseException.class})
    public void factory_parse_invalidText(String unparsable) {
        LocalDate.parse(unparsable);
    }
    
    @Test
    public void test_comparisons() {
        doTest_comparisons_LocalDate(
                LocalDate.of(2007, 1, 1),
                LocalDate.of(2007, 12, 31),
                LocalDate.of(2008, 1, 1),
                LocalDate.of(2008, 2, 29)
        );
    }

    void doTest_comparisons_LocalDate(LocalDate... localDates) {
        for (int i = 0; i < localDates.length; i++) {
            LocalDate a = localDates[i];
            for (int j = 0; j < localDates.length; j++) {
                LocalDate b = localDates[j];
                if (i < j) {
                    assertTrue(a.compareTo(b) < 0, "Comparing" + a + " <=> " + b);
                    assertEquals(a.isBefore(b), true, "Comparing" + a + " <=> " + b);
                    assertEquals(a.isAfter(b), false, "Comparing" + a + " <=> " + b);
                    assertEquals(a.equals(b), false, "Comparing" + a + " <=> " + b);
                } else if (i > j) {
                    assertTrue(a.compareTo(b) > 0, "Comparing" + a + " <=> " + b);
                    assertEquals(a.isBefore(b), false, "Comparing" + a + " <=> " + b);
                    assertEquals(a.isAfter(b), true, "Comparing" + a + " <=> " + b);
                    assertEquals(a.equals(b), false, "Comparing" + a + " <=> " + b);
                } else {
                    assertEquals(a.compareTo(b), 0, "Comparing" + a + " <=> " + b);
                    assertEquals(a.isBefore(b), false, "Comparing" + a + " <=> " + b);
                    assertEquals(a.isAfter(b), false, "Comparing" + a + " <=> " + b);
                    assertEquals(a.equals(b), true, "Comparing" + a + " <=> " + b);
                }
            }
        }
    }
}
