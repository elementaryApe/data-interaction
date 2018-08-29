package com.herman.di.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串模板处理工具类
 *
 * @author hsh
 * @create 2018-08-29 15:45
 **/
public class StringTemplateUtils {

    private static final String DEF_REGEX = "\\{(.+?)\\}";

    public StringTemplateUtils() {
    }

    public static String render(String template, Map<String, String> data) {
        return render(template, data, "\\{(.+?)\\}");
    }

    public static String render(String template, Map<String, String> data, String regex) {
        if(StringUtils.isBlank(template)) {
            return "";
        } else if(StringUtils.isBlank(regex)) {
            return template;
        } else if(data != null && data.size() != 0) {
            try {
                StringBuffer e = new StringBuffer();
                Pattern pattern = Pattern.compile(regex);

                Matcher matcher;
                String value;
                for(matcher = pattern.matcher(template); matcher.find(); matcher.appendReplacement(e, value)) {
                    String name = matcher.group(1);
                    value = (String)data.get(name);
                    if(value == null) {
                        value = "";
                    }
                }

                matcher.appendTail(e);
                return e.toString();
            } catch (Exception var8) {
                return template;
            }
        } else {
            return template;
        }
    }

}
