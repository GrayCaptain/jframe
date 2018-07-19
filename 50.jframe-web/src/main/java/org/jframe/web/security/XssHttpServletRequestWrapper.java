package org.jframe.web.security;

import org.apache.commons.lang.StringEscapeUtils;
import org.jframe.core.exception.KnownException;
import org.jframe.core.exception.ResultCode;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author qq
 * @date 2018/7/17
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private static Pattern patterns = Pattern.compile("<script>(.*?)</script>" + "|src[\r\n]*=[\r\n]*\\\'(.*?)\\\'"
            + "|src[\r\n]*=[\r\n]*\\\"(.*?)\\\"" + "|</script>"
            + "|<script(.*?)>" + "|eval\\((.*?)\\)"
            + "|expression\\((.*?)\\)" + "|javascript:" + "|vbscript:"
            + "|onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
            | Pattern.DOTALL);

    private static Pattern patterns2 = Pattern.compile("(<.*>.*</.*>)|(<.*/?>)", Pattern.CASE_INSENSITIVE
            | Pattern.MULTILINE
            | Pattern.DOTALL);

    private static Pattern comment = Pattern.compile("/\\*.*\\*/", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
            | Pattern.DOTALL);

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        checkXSS();
    }

    @Override
    public String[] getParameterValues(String parameter) {
        return stripXSS(super.getParameterValues(parameter));
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> parameterMap = super.getParameterMap();
        if (parameterMap == null) {
            return null;
        }
        Map<String, String[]> newParameterMap = new HashMap<>();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            newParameterMap.put(entry.getKey(), stripXSS(entry.getValue()));
        }
        return Collections.unmodifiableMap(newParameterMap);
    }

    @Override
    public String getParameter(String parameter) {
        return stripXSS(super.getParameter(parameter));
    }

    @Override
    public String getHeader(String name) {
        return stripXSS(super.getHeader(name));
    }


    private void checkXSS() {
        Set<Map.Entry<String, String[]>> set = super.getParameterMap().entrySet();
        for (Map.Entry<String, String[]> entry : set) {
            String[] values = entry.getValue();
            if (values != null) {
                for (String value : values) {
                    if (patterns.matcher(value).find() || patterns2.matcher(value).find()) {
                        throw new KnownException(ResultCode.ATTACK, String.format("参数:[%s] 值:[%s]发现XSS注入",
                                entry.getKey(), HtmlUtils.htmlEscape(value)));
                    }
                }
            }

        }
    }


    private static String stripXSS(String value) {
        if (value != null) {
            value = value.replaceAll("\0", "");
            value = comment.matcher(value).replaceAll("");
            value = patterns.matcher(value).replaceAll("");

            value = StringEscapeUtils.escapeHtml(value);
//            value = StringEscapeUtils.escapeJavaScript(value);
            value = StringEscapeUtils.escapeSql(value);
        }
        return value;
    }

    private static String[] stripXSS(String[] values) {
        if (values == null) {
            return null;
        }
        String[] encodedValues = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            encodedValues[i] = stripXSS(values[i]);
        }
        return encodedValues;
    }


}