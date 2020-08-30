package tck.java.time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.junit.BeforeClass;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.runner.RunWith;
import org.junit.Test;

/**
 * Test LocalDate.
 */
@RunWith(DataProviderRunner.class)
public class TCKLocalDate {

    @DataProvider
    public static Object[][] provider_sampleBadParse() {
        return new Object[][]{
                {"2008/07/05"},
                {"10000-01-01"},
        };
    }

    @Test(expected=DateTimeParseException.class)
    @UseDataProvider("provider_sampleBadParse")
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
                    assertTrue("Comparing" + a + " <=> " + b, a.compareTo(b) < 0);
                    assertEquals("Comparing" + a + " <=> " + b, a.isBefore(b), true);
                    assertEquals("Comparing" + a + " <=> " + b, a.isAfter(b), false);
                    assertEquals("Comparing" + a + " <=> " + b, a.equals(b), false);
                } else if (i > j) {
                    assertTrue("Comparing" + a + " <=> " + b, a.compareTo(b) > 0);
                    assertEquals("Comparing" + a + " <=> " + b, a.isBefore(b), false);
                    assertEquals("Comparing" + a + " <=> " + b, a.isAfter(b), true);
                    assertEquals("Comparing" + a + " <=> " + b, a.equals(b), false);
                } else {
                    assertEquals("Comparing" + a + " <=> " + b, a.compareTo(b), 0);
                    assertEquals("Comparing" + a + " <=> " + b, a.isBefore(b), false);
                    assertEquals("Comparing" + a + " <=> " + b, a.isAfter(b), false);
                    assertEquals("Comparing" + a + " <=> " + b, a.equals(b), true);
                }
            }
        }
    }
}
