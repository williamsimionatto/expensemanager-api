package william.expensemanagerapi.validation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateValidation {
  public static boolean isDateInPeriod(Date date, Date startDate, Date endDate) {
    date = new Date(date.getTime() + TimeZone.getDefault().getRawOffset());
    startDate = addDays(startDate, 1);
    endDate = addDays(endDate, 1);
    return date.after(startDate) && date.before(endDate);
  }

  public static String formatDate(Date date, String pattern) {
    date = addDays(date, 1);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    return simpleDateFormat.format(date);
  }

  public static Date addDays(Date date, int days) {
    return new Date(date.getTime() + days * 86400000);
  }
}
