package com.shuiwen.campusys.util;

import java.text.DecimalFormat;
import java.util.Map;

/**
 * Created by zhaofrancis on 14-8-9.
 */
public class CampusUtil {
    public static String minutIntervalFormat = "%1$02d-%2$02d";
    private static final int BUSINESS_MINUTE_INTERVAL = 5;
    private static final int PROVINCE_MINUTE_INTERVAL = 30;
    
    public static final long PROVINCE_MILLISENCOND_INTERVAL = PROVINCE_MINUTE_INTERVAL * 60L * 1000L;
    
    public static String getPrecent(int firstValue, int secondValue) {
        if (firstValue == 0) {
            return "0%";
        }

        if (secondValue == 0) {
            return "--";
        }
        String precent = new DecimalFormat("0.0").format(firstValue * 1.0 / secondValue * 100);
        return precent + "%";
    }
    
    public static String getPrecent(double precent) {
        String precentStr = new DecimalFormat("0.0").format(precent * 100);
        return precentStr + "%";
    }
    
    public static String getBusinessMinuteInterval(String minute) {
        int a= (int) ( Integer.valueOf(minute) / BUSINESS_MINUTE_INTERVAL);
        return String.format(minutIntervalFormat, a * BUSINESS_MINUTE_INTERVAL, (a + 1) * BUSINESS_MINUTE_INTERVAL - 1) + "%";
    }
    
    public static String getProvinceMinuteInterval(String minute) {
        int a= (int) (Integer.valueOf(minute) / PROVINCE_MINUTE_INTERVAL);
        return String.format(minutIntervalFormat, a * PROVINCE_MINUTE_INTERVAL, (a + 1) * PROVINCE_MINUTE_INTERVAL - 1) + "%";        
    }
    
    public static int getIntValue(Map<String, Object> resultsMap, String key) {
        if (null == resultsMap || resultsMap.isEmpty()) {
            return 0;
        }
        if (null == resultsMap.get(key)) {
            return 0;
        }
        String value = String.valueOf(resultsMap.get(key));
        if(value.contains(".")){
            value = value.substring(0, value.indexOf("."));
        }
        return Integer.valueOf(value.replaceAll(",", ""));
    }
    
    
    public static void main(String[] arags) {
        System.out.println(CampusUtil.getBusinessMinuteInterval("12"));
    }
}
