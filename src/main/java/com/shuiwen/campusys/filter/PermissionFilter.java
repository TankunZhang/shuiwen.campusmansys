package com.shuiwen.campusys.filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.shuiwen.campusys.util.ServerSecurity;

public class PermissionFilter implements Filter {
    private static final Logger LOGGER = org.apache.log4j.Logger.getLogger(PermissionFilter.class);
    
    private static List<String> imsiList = new ArrayList<String>();
    private static String forbiddenMessage;
    private static String systemErrorMessage;
    
    static {
        //init imsiList
        InputStream inputStream = PermissionFilter.class.getClassLoader().getResourceAsStream("monitor.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        String allowedImsi = properties.getProperty("allowedImsi");
        imsiList = Arrays.asList(allowedImsi.split(","));
        
        //
        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("errorCode", 403);
        errorMap.put("errorMessage", "forbidden");
        forbiddenMessage = new Gson().toJson(errorMap);
        
        Map<String, Object> systemErrorMap = new HashMap<String, Object>();
        systemErrorMap.put("errorCode", 500);
        systemErrorMap.put("errorMessage", "server error");
        systemErrorMessage = new Gson().toJson(systemErrorMap);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String encryptedImsi = ((HttpServletRequest)request).getHeader("imsi");
        String imsi = ServerSecurity.custom_decrypt(encryptedImsi);
        if (!imsiList.contains(imsi)) {
            PrintWriter printWriter = ((HttpServletResponse)response).getWriter();
            printWriter.write(ServerSecurity.custom_encrypt(forbiddenMessage));
            printWriter.flush();
            return;
        }
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e);
            PrintWriter printWriter = ((HttpServletResponse)response).getWriter();
            printWriter.write(ServerSecurity.custom_encrypt(systemErrorMessage));
            printWriter.flush();
        }
    }

    @Override
    public void destroy() {

    }

}
