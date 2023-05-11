package xcode.ingot.shared;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Utils {
    public static String generateSecureId() {
        return UUID.randomUUID().toString();
    }

    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24 hour

    public static String encryptor(String value, boolean isEncrypt) {
        StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();

        jasypt.setPassword("xcode");

        return isEncrypt ? jasypt.encrypt(value) :jasypt.decrypt(value);
    }

    public static long getDifferenceDays(Date date1, Date date2) {
        long diff = date2.getTime() - date1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static Date setDateTime(Date date, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, hour);

        if (hour == 7) {
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
        } else {
            cal.set(Calendar.MINUTE, 58);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 999);
        }

        return cal.getTime();
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static String[] stringToArray(String requests) {
        return requests.split(",");
    }

    public static Date getTomorrowDate() {
        return new Date(System.currentTimeMillis() + EXPIRE_DURATION);
    }
}
