package time_project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DataConverterTest {
    private final DataConverter dataConverter = new DataConverter();

    @Test
    public void test_getTime_startOf70s() {
        Assertions.assertEquals("12/31/69, 7:00 PM", dataConverter.getTime( 2208988800L));
    }

    @Test
    public void test_getTime_startOf80s() {
        Assertions.assertEquals("12/31/79, 7:00 PM", dataConverter.getTime(2524521600L));
    }

    @Test
    public void test_getTime_modernCase() {
        Assertions.assertEquals("2/6/23, 11:30 AM", dataConverter.getTime(3884689849L));
    }
}
