package com.cewb.app.utility;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.cewb.app.security.service.UserPrinciple;

public class AppUtility {
	
	/**
     * Checks if the given object is null
     * @param obj
     * @return
     */
    public static boolean isNull(Object obj) {
        return (obj == null);
    }
    
    /**
     * Checks if the given string is blank
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
    	return (str == "");
    }
    
    /**
     * Concatenates the given array of String with a delimiter
     * @param stringList
     * @param delimiter
     * @return
     */
    public static String arrayToString(List<String> stringList, String delimiter) {
        if(stringList.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
                
        for(String s : stringList) {
            sb.append(s);
            sb.append(delimiter);
        }
        
        /*
         * returns the concatenated string excluding the last delimiter
         */            
        return sb.substring(0, sb.length() - delimiter.length());        
    }
    
    public static String getSqlKeyword(String word) {
    	if(word == null)
    		return "%%";
    	return "%"+word+"%";
    }
    
    // Add security helpers here once security dependencies are added
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
    
    public static Long getAuthenticatedUserId() {
    	return ((UserPrinciple)getAuthentication().getPrincipal()).getId();
    }
}
