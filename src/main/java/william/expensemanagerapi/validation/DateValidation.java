package william.expensemanagerapi.validation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateValidation {
  public static boolean isDateInPeriod(Date date, Date startDate, Date endDate) {
    date = new Date(date.getTime() + TimeZone.getDefault().getRawOffset());
    boolean isInPeriod = (date.equals(startDate) || date.after(startDate)) && (date.equals(endDate) || date.before(endDate));

    return isInPeriod;
  }

  public static String formatDate(Date date, String pattern) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    return simpleDateFormat.format(date);
  }

  public static Date addDays(Date date, int days) {
    return new Date(date.getTime() + days * 86400000);
  }
}
