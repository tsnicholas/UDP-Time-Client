package cs416;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DataConverterTest {
    private final DataConverter dataConverter = new DataConverter();

    @Test
    public void test_getTime_PastCase() {
        Assertions.assertEquals("9/12/01, 9:50 AM", dataConverter.getTime(3209246400L));
    }

    @Test
    public void test_getTime_ModernCase() {
        Assertions.assertEquals("2/3/23, 5:04 PM", dataConverter.getTime(3884402060L));
    }
}
