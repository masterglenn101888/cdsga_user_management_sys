package ph.edu.cdsga.sms.ums.utils.object;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public final class ObjectUtils {
    private static final Logger logger = LoggerFactory.getLogger(ObjectUtils.class);

    private ObjectUtils() {
        // hide the implicit public one.
    }

    public static void copyProperties(Object sourceObject, Object targetObject) {
        BeanUtils.copyProperties(sourceObject, targetObject);
    }

    public static String getCurrentDateAndTime() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return dateFormat.format(date);
    }

    public static Date getLocalDateTime() {
        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Boolean isValidCsv(String path) {
        String extension = path.substring(path.length() - 4);

        if (!".csv".equals(extension)) {
            logger.error("File should be CSV");
            return false;
        }
        return true;
    }

    public static String toDecimalFormat(Object obj){
        return new DecimalFormat("###,###").format(obj);
    }

    public static boolean isNullOrEmpty(String str){
        return str == null || str.trim().length() == 0 || str.equals("");
    }

    public static boolean isNullOrEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }
}
