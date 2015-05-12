//package com.shuiwen.campusys.service;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Collections;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.shuiwen.campusys.bean.WarnningMessage;
//import com.shuiwen.campusys.dao.BusinessReportDAO;
//import com.shuiwen.campusys.dao.ProvinceReportDAO;
//import com.shuiwen.campusys.service.impl.CampusServiceImpl;
//import com.shuiwen.campusys.util.MonitorUtil;
//
///**
// * Created by zhaofrancis on 14-8-9.
// */
//public class WarnningService {
//    private static final Logger LOGGER = LoggerFactory.getLogger(WarnningService.class);
//    
//    @Autowired
//    private BusinessReportDAO businessReportDAO;
//    
//    @Autowired
//    private ProvinceReportDAO provinceReportDAO;
//    
////    public static final double WEEKEND_WEIGHT = 0.8;
////    public static final double SUMMER_HOLIDAY = 1.1;
////    public static final double NORMAL_WEIGHT = 1.0;
//    
//    private static final double WARNNING_PERCENT = 0.5;
//
//    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//    
//    public static Map<String, Double> avgValueMap = new HashMap<String, Double>();
////    public static Map<String, Double> provinceAvgValueMap;
//    
//    public static List<WarnningMessage> warnningMessages = new ArrayList<WarnningMessage>();
//    
//
//    public void queryAvgValue() {
//        LOGGER.info("start query avg value");
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DAY_OF_MONTH, -1);
//        String endDate = dateFormat.format(calendar.getTime());
//        calendar.add(Calendar.MONTH, -1);
//        String startDate = dateFormat.format(calendar.getTime());
//        
//        String hour = CampusServiceImpl.hourFormat.format(calendar.getTime());
//        String minute = CampusServiceImpl.minutesFormat.format(calendar.getTime());
//        String businessMinuteInterval = MonitorUtil.getBusinessMinuteInterval(minute);
//        String provinceMinuteInterval = MonitorUtil.getProvinceMinuteInterval(minute);
//        
//        Map<String, Double> businessAvgValueMap = businessReportDAO.queryAllBusinessReportAvg(startDate, endDate, hour, businessMinuteInterval);
//        Map<String, Double> provinceAvgValueMap = provinceReportDAO.queryAllProvinceReportAvg(startDate, endDate, hour, provinceMinuteInterval);
////        LOGGER.info("BUSINESS: " + businessAvgValueMap.toString());
////        LOGGER.info("PROVINCE: " + provinceAvgValueMap.toString());
//        avgValueMap.putAll(businessAvgValueMap);
//        avgValueMap.putAll(provinceAvgValueMap);
//        LOGGER.info(avgValueMap.toString());
//    }
//    
//    
//    public void queryBusinessValue() {
//        LOGGER.info("start query business value");
////        Date todayDate = new Date();
////        String date = dateFormat.format(todayDate);
////        
////        String hour = CampusServiceImpl.hourFormat.format(todayDate);
////        String minute = CampusServiceImpl.minutesFormat.format(todayDate);
////        String businessMinuteInterval = MonitorUtil.getBusinessMinuteInterval(minute);
////        Map<String, Object> businessReportMap = businessReportDAO.queryMinuteReport(date, hour, businessMinuteInterval);
//        Map<String, Object> businessReportMap = businessReportDAO.queryMinuteReport("", "", "");
//        String date = String.valueOf(businessReportMap.remove("REPORTDATE"));
//        String hour = String.valueOf(businessReportMap.remove("REPORTHOUR"));
//        String businessMinuteInterval = String.valueOf(businessReportMap.remove("REPORTQUJIAN"));
//        
////        if (null == avgValueMap || avgValueMap.isEmpty()) {
//            this.queryAvgValue();
////        }
//        
//        if (null == avgValueMap || avgValueMap.isEmpty()) {
//            LOGGER.error("cna't get avg value.");
//            return;
//        }
//        
//        LOGGER.info("business params: " + date + " " + hour + " " + businessMinuteInterval);
//        if (null == businessReportMap || businessReportMap.isEmpty()) {
//            LOGGER.error("current business report is null. " + date + " " + hour + " " + businessMinuteInterval);
//            return;
//        }
//        
//        for (String category : businessReportMap.keySet()) {
//            int currentValue = MonitorUtil.getIntValue(businessReportMap, category);
//            
//            Double avgValue = avgValueMap.get(category);
//            if (null == avgValue) {
//                LOGGER.error("can't get avg value of " + category);
//                continue;
//            }
//            LOGGER.info("currentValue: " + currentValue + ", avgValue: " + avgValue + ", catetegory: " + category);
//            double avgPrecent = currentValue / avgValue;
//            if (avgPrecent <= WarnningService.WARNNING_PERCENT) {
//                //TODO store warnning message
//                WarnningService.warnningMessages.add(new WarnningMessage(hour, businessMinuteInterval, avgPrecent));
//            }
//        }
//    }
//    
//    public void queryProvinceValue() {
//        LOGGER.info("start query province value");
////        Date todayDate = new Date().ge;
//        Date todayDate = new Date(System.currentTimeMillis() - MonitorUtil.PROVINCE_MILLISENCOND_INTERVAL);
//        String date = dateFormat.format(todayDate);
//        
//        String hour = CampusServiceImpl.hourFormat.format(todayDate);
//        String minute = CampusServiceImpl.minutesFormat.format(todayDate);
//        String provinceMinuteInterval = MonitorUtil.getProvinceMinuteInterval(minute);
//        
//        Map<String, Integer> provinceReportMap = provinceReportDAO.queryMinuteReport(date, hour, provinceMinuteInterval);
//        
////        if (null == avgValueMap || avgValueMap.isEmpty()) {
//            this.queryAvgValue();
////        }
//        
//        if (null == avgValueMap || avgValueMap.isEmpty()) {
//            LOGGER.error("can't get avg value.");
//            return;
//        }
//        
//        LOGGER.info("province params: " + date + " " + hour + " " + provinceMinuteInterval);
//        if (null == provinceReportMap || provinceReportMap.isEmpty()) {
//            LOGGER.error("province report map is null. " + date + " " + hour + " " + provinceMinuteInterval);
//            return;
//        }
//        
//        for (String province : provinceReportMap.keySet()) {
//            int currentValue = provinceReportMap.get(province);
//            
//            Double avgValue = avgValueMap.get(province);
//            if (null == avgValue) {
//                LOGGER.error("can't get avg value of " + province);
//                continue;
//            }
//            LOGGER.info("currentValue: " + currentValue + ", avgValue: " + avgValue + ", catetegory: " + province);
//            double avgPrecent = currentValue / avgValue;
//            if (avgPrecent <= WarnningService.WARNNING_PERCENT) {
//                //TODO store warnning message
//                WarnningService.warnningMessages.add(new WarnningMessage(hour, provinceMinuteInterval, avgPrecent));
//            }
//        }
//    }
//    
//    public static List<WarnningMessage> readWarnningMessage() {
//        //TODO  care about mulit-thread.  
//        //TODO make sure all can read the error message.
//        List<WarnningMessage> messages = new ArrayList<WarnningMessage>(); 
//        Collections.copy(messages, WarnningService.warnningMessages);
////        WarnningService.warnningMessages = new ArrayList<WarnningMessage>();
//        return messages;
//    }
//
//}
