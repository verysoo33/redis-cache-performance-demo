package soo.demo.util;

import org.springframework.util.ObjectUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    static public Date getNullDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse("1700-01-01");
    }

    static public String getTodayDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    static public String getTodayDateOnlyString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }

    static public String getTodayDateTimeString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }

    static public boolean isToday(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (sdf.format(new Date()).equals(sdf.format(date))) {
            return true;
        }
        return false;
    }

    static public String getAmPmValue(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        if (Integer.parseInt(sdf.format(date)) >= 12) {
            return "오후";
        }
        return "오전";
    }

    static public String getTodayDatePath() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        return sdf.format(new Date());
    }

    static public String getStringDateFormatYmd(String value) {
        if (value == null || value.length() != 8) {
            return value;
        }

        return value.substring(0, 4).concat(".").concat(value.substring(4, 6)).concat(".")
                .concat(value.substring(6, 8));
    }

    private static final String[] weekDayString = {"월", "화", "수", "목", "금", "토", "일"};

    // 월-일 1111111 문자열로 변환
    static public String getWeekDayString(String value) {
        if (value.length() < 7) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        char[] charArray = value.toCharArray();
        for (int i = 0; i < 7; i++) {
            if (charArray[i] == '1') {
                sb.append(weekDayString[i]).append(" / ");
            }
        }
        sb.delete(sb.length() - 3, sb.length());
        return sb.toString();
    }

    public static String getMinusMonthString(Integer value) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        cal.add(Calendar.MONTH, value * -1);
        return sdf.format(cal.getTime());
    }

    public static String getPrevMonthFirstDateString() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) - 1, 1);
        return sdf.format(cal.getTime());
    }

    public static String getPrevMonthLastDateString() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return sdf.format(cal.getTime());
    }

    static public Date getStringToDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        String new_date = sdf2.format(sdf.parse(date));

        return sdf2.parse(new_date);
    }

    // 날짜 yyyy-MM-dd 로 변환 후 Time
    static public Date getDateFormat(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateFm = dateFormat.format(date); // 오늘날짜
        Date result = null;
        try {
            result = new Date(dateFormat.parse(dateFm).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * (ISO 8601) 날짜 -> 유닉스 타임스탬프
     *
     * @param dateStr
     * @param format
     * @return
     */
    static public Long getTimeStampISO8601(String dateStr, String format) {
        if (ObjectUtils.isEmpty(dateStr) || ObjectUtils.isEmpty(format)) {
            return 0L;
        }

        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX"); // 넘어온 값의 형식
            SimpleDateFormat df2 = new SimpleDateFormat(format); // 내가 바꿀 형식

            Date date = df.parse(dateStr);
            dateStr = df2.format(date);

            return Timestamp.valueOf(dateStr).getTime() / 1000;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0L;
    }

    /**
     * 현재 일시를 파라미터로 전달받은 포멧으로 변환
     * @param type 현재 일시를 변환할 포멧(ex: yyyy)
     * @return 변환된 현재 일시
     */
    static public String getTodayPathByParams(String type) {
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        return sdf.format(new Date());
    }

    /**
     * dday 계산 (0 이상일 때만 계산)
     * - 24시간 이상 남은 경우엔 날짜만 표시 (ex. D-18)
     * - 종료 당일에는 날짜 제거 하고 시간만 표시 (ex. D-12:00)
     *
     * @param now         : 현재 날짜
     * @param compareDate : 남은 dday 계산 기준이 되는 날짜
     * @return
     */
    public static String getDday(LocalDateTime now, Date compareDate) {
        String dDay = "";
        try {
            LocalDateTime date = compareDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            if (date.isAfter(now)) {
                long hourVal = now.until(date, ChronoUnit.HOURS);
                String minutes = String.format("%02d", (now.until(date, ChronoUnit.MINUTES) % 60));
                String hours = String.format("%02d", (now.until(date, ChronoUnit.HOURS) % 24));
                String days = String.valueOf(now.until(date, ChronoUnit.DAYS));

                // dDay = "D-" + days + " " + hours + ":" + minutes;

                // 24시간 이상 남은 경우엔 날짜만 표시 (ex. D-18)
                if (hourVal >= 24) {
                    dDay = days + "일 남음";
                } else if (now.getDayOfMonth() == date.getDayOfMonth()) {
                    // 종료 당일에는 날짜 제거 하고 시간만 표시 (ex. D-12:00)
                    // 1시간 이상 남음
                    if (Integer.valueOf(hours) >= 0) {
                        dDay = Integer.valueOf(hours) + "시간 남음";
                    } else {
                        dDay = Integer.valueOf(minutes) + "분 남음";
                    }
                }
            }
        } catch (Exception e) {
        }

        return dDay;
    }
}
