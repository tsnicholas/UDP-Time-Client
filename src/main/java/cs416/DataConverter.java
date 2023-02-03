package cs416;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataConverter {
    private final Calendar calendar;

    public DataConverter() {
        calendar = setCalendar();
    }

    private Calendar setCalendar() {
        Calendar output = Calendar.getInstance();
        output.set(1900, Calendar.JANUARY, 1, 0, 0, 0);
        return output;
    }

    public String getTime(long data) {
        addDataToCalendar(data);
        FieldPosition fieldPosition = new FieldPosition(DateFormat.YEAR_FIELD);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        StringBuffer buffer = simpleDateFormat.format(calendar.getTime(), new StringBuffer(), fieldPosition);
        return buffer.toString();
    }

    private void addDataToCalendar(long data) {
        // Add the number of years since the beginning of 1900.
        double years = data * 0.00000003171;
        calendar.add(Calendar.YEAR, (int)years);
        // Add the number of days since the beginning of this year.
        double days = extractRemainder(years) * 365;
        calendar.add(Calendar.DAY_OF_YEAR, (int)days);
        // Add the remaining hours since 1/1/1900.
        double hours = extractRemainder(days) * 24;
        calendar.add(Calendar.HOUR, (int)hours);
        // Add the remaining minutes since 1/1/1900.
        double minutes = extractRemainder(hours) * 60;
        calendar.add(Calendar.MINUTE, (int)minutes);
    }

    private double extractRemainder(double value) {
        double remainder = value - (int)value;
        System.out.println((int)value + " : " + remainder);
        return remainder;
    }
}
