package util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimestampUtil {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Timestamp getTimestampStringAbortMillisecond() {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        String currentTimestampStr = dateFormat.format(currentTimestamp);
        return Timestamp.valueOf(currentTimestampStr);
    }
}
