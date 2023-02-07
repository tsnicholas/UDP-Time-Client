package time_project;

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
        // Subtract the data by 5 hours to account for EST.
        long timeZoneConversion = data - 18000;
        double days_remainder = addNumOfDays(timeZoneConversion);
        double hours_remainder = addNumOfHours(days_remainder);
        addNumOfMinutes(hours_remainder);
    }

    private double addNumOfDays(long timeZoneConversion) {
        double days = timeZoneConversion / 86400.0; // number of days since 1/1/1900.
        calendar.add(Calendar.DATE, (int)days);
        return extractRemainder(days);
    }

    private double addNumOfHours(double remainder) {
        double hours = remainder * 24; // number of remaining hours.
        calendar.add(Calendar.HOUR, (int)hours);
        return extractRemainder(hours);
    }

    private void addNumOfMinutes(double remainder) {
        double minutes = remainder * 60; // number of remaining minutes.
        calendar.add(Calendar.MINUTE, (int)minutes);
    }

    // Return the numbers after the decimal only.
    private double extractRemainder(double value) {
        return value - (int)value;
    }
}
