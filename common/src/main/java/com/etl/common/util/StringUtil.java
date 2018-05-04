package com.etl.common.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by houlongbin on 2016/12/19.
 */
public class StringUtil {
    private static final String[] hex = { "00", "01", "02", "03", "04", "05",
            "06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "1A", "1B",
            "1C", "1D", "1E", "1F", "20", "21", "22", "23", "24", "25", "26",
            "27", "28", "29", "2A", "2B", "2C", "2D", "2E", "2F", "30", "31",
            "32", "33", "34", "35", "36", "37", "38", "39", "3A", "3B", "3C",
            "3D", "3E", "3F", "40", "41", "42", "43", "44", "45", "46", "47",
            "48", "49", "4A", "4B", "4C", "4D", "4E", "4F", "50", "51", "52",
            "53", "54", "55", "56", "57", "58", "59", "5A", "5B", "5C", "5D",
            "5E", "5F", "60", "61", "62", "63", "64", "65", "66", "67", "68",
            "69", "6A", "6B", "6C", "6D", "6E", "6F", "70", "71", "72", "73",
            "74", "75", "76", "77", "78", "79", "7A", "7B", "7C", "7D", "7E",
            "7F", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89",
            "8A", "8B", "8C", "8D", "8E", "8F", "90", "91", "92", "93", "94",
            "95", "96", "97", "98", "99", "9A", "9B", "9C", "9D", "9E", "9F",
            "A0", "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "AA",
            "AB", "AC", "AD", "AE", "AF", "B0", "B1", "B2", "B3", "B4", "B5",
            "B6", "B7", "B8", "B9", "BA", "BB", "BC", "BD", "BE", "BF", "C0",
            "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "CA", "CB",
            "CC", "CD", "CE", "CF", "D0", "D1", "D2", "D3", "D4", "D5", "D6",
            "D7", "D8", "D9", "DA", "DB", "DC", "DD", "DE", "DF", "E0", "E1",
            "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "EA", "EB", "EC",
            "ED", "EE", "EF", "F0", "F1", "F2", "F3", "F4", "F5", "F6", "F7",
            "F8", "F9", "FA", "FB", "FC", "FD", "FE", "FF" };

    private static final byte[] val = { 63, 63, 63, 63, 63, 63, 63, 63, 63, 63,
            63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63,
            63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63,
            63, 63, 63, 63, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 63, 63, 63, 63, 63,
            63, 63, 10, 11, 12, 13, 14, 15, 63, 63, 63, 63, 63, 63, 63, 63, 63,
            63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63,
            10, 11, 12, 13, 14, 15, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63,
            63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63,
            63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63,
            63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63,
            63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63,
            63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63,
            63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63,
            63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63,
            63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63,
            63, 63, 63, 63, 63, 63 };

    public static boolean exists(String value, String[] source,
                                 boolean caseInsensitive, boolean useRegex) {
        if (source != null) {
            int len = source.length;
            for (int i = 0; i < len; ++i) {
                if (useRegex) {
                    Pattern p = (caseInsensitive) ? Pattern.compile(source[i],
                            2) : Pattern.compile(source[i]);
                    if (p.matcher(value).find())
                        return true;
                } else if ((source[i].equals(value))
                        || ((source[i].equalsIgnoreCase(value)) && (caseInsensitive))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isTrue(String value) {
        return (("1".equals(value)) || ("true".equalsIgnoreCase(value)) || ("��"
                .equals(value)));
    }

    public static boolean isFalse(String value) {
        return (("0".equals(value)) || ("false".equalsIgnoreCase(value)) || ("��"
                .equals(value)));
    }

    public static String null2blank(String value) {
        return ((value == null) ? "" : value);
    }

    public static boolean isEmpty(String value) {
        return ((value == null) || ("".equals(value.trim())));
    }

    public static int parseInt(String value, int defaultInt) {
        int val = defaultInt;
        try {
            val = Integer.parseInt(value.replaceAll(",", "").replace("+", ""));
        } catch (Exception localException) {
        }
        return val;
    }

    public static long parseLong(String value, long defaultLong) {
        long val = defaultLong;
        try {
            val = Long.parseLong(value.replaceAll(",", "").replace("+", ""));
        } catch (Exception localException) {
        }
        return val;
    }

    public static double parseDouble(String value, double defaultDouble) {
        double val = defaultDouble;
        try {
            val = Double
                    .parseDouble(value.replaceAll(",", "").replace("+", ""));
        } catch (Exception localException) {
        }
        return val;
    }

    public static boolean parseBoolean(String value, boolean defaultBoolean) {
        boolean val = defaultBoolean;
        try {
            if (isEmpty(value)) {
                return val;
            }
            val = Boolean.parseBoolean(value);
        } catch (Exception localException) {
        }
        return val;
    }

    public static Enum<?> parseEnmu(Class<?> em, String value,
                                    Enum<?> defaultVal) {
        Enum en = null;
        try {
            en = (Enum) em.getMethod("valueOf", new Class[] { String.class })
                    .invoke(em, new Object[] { value });
        } catch (Exception e) {
            en = defaultVal;
        }

        return en;
    }

    public static Date toDate(String time) {
        return DateUtil.toDate(time);
    }

    public static String dateToString(Date time) {
        return DateUtil.dateToString(time);
    }

    public static String getSysdate() {
        return DateUtil.getSysdate();
    }

    public static String getSysdatetime() {
        return DateUtil.getSysdatetime();
    }

    public static String byteToString(byte[] b, boolean bUpper) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; ++n) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else {
                hs = hs + stmp;
            }
        }
        if (bUpper) {
            return hs.toUpperCase();
        }
        return hs.toLowerCase();
    }

    public static String getMD5(String value) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
        digest.update(value.getBytes());
        return byteToString(digest.digest(), false);
    }

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static String limitLength(String str, int maxLength) {
        if (isEmpty(str)) {
            return "";
        }
        if (str.length() > maxLength) {
            return str.substring(0, maxLength);
        }
        return str;
    }

    public static String limitBytes(String str, int maxBytes) {
        if (isEmpty(str)) {
            return "";
        }
        byte[] b = str.getBytes();
        int byteLength = b.length;
        if (byteLength > maxBytes) {
            return new String(b, 0, maxBytes);
        }
        return str;
    }

    public static boolean isNumber(String str) {
        if (isEmpty(str)) {
            return false;
        }
        Pattern p = Pattern.compile("^(-?\\d+)(\\.\\d+)?");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static String patchString(String source, int len, char patch,
                                     boolean beforePos) {
        StringBuffer buf = new StringBuffer(source);
        while (buf.length() < len) {
            if (beforePos)
                buf.insert(0, patch);
            else {
                buf.append(patch);
            }
        }
        return buf.toString();
    }

    public static String joinArray(String[] array, String regex) {
        if (array == null) {
            return null;
        }
        regex = null2blank(regex);
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            buf.append(regex).append(array[i]);
        }
        return ((buf.length() > 0) ? buf.substring(regex.length()) : "");
    }

    public static String[] split2Array(String string, String regex) {
        if (isEmpty(string)) {
            return new String[0];
        }
        return string.split(regex);
    }

    public static String joinList(List<String> list, String regex) {
        if (list == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer();
        regex = null2blank(regex);
        for (String s : list) {
            buf.append(regex).append(s);
        }
        return ((buf.length() > 0) ? buf.substring(regex.length()) : "");
    }

    public static List<String> split2List(String string, String regex) {
        List res = new ArrayList();
        String[] array = split2Array(string, regex);
        for (int i = 0; i < array.length; ++i) {
            res.add(array[i]);
        }
        return res;
    }

    public static String escape(String s) {
        StringBuffer sbuf = new StringBuffer();
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            int ch = s.charAt(i);
            if ((65 <= ch) && (ch <= 90)) {
                sbuf.append((char) ch);
            } else if ((97 <= ch) && (ch <= 122)) {
                sbuf.append((char) ch);
            } else if ((48 <= ch) && (ch <= 57)) {
                sbuf.append((char) ch);
            } else if ((ch == 45) || (ch == 95) || (ch == 46) || (ch == 42)
                    || (ch == 43) || (ch == 64) || (ch == 47)) {
                sbuf.append((char) ch);
            } else if (ch <= 127) {
                sbuf.append('%');
                sbuf.append(hex[ch]);
            } else {
                sbuf.append('%');
                sbuf.append('u');
                sbuf.append(hex[(ch >> 8)]);
                sbuf.append(hex[(0xFF & ch)]);
            }
        }
        return sbuf.toString();
    }

    public static String unescape(String s) {
        StringBuffer sbuf = new StringBuffer();
        int i = 0;
        int len = s.length();
        while (i < len) {
            int ch = s.charAt(i);
            if ((65 <= ch) && (ch <= 90)) {
                sbuf.append((char) ch);
            } else if ((97 <= ch) && (ch <= 122)) {
                sbuf.append((char) ch);
            } else if ((48 <= ch) && (ch <= 57)) {
                sbuf.append((char) ch);
            } else if ((ch == 45) || (ch == 95) || (ch == 46) || (ch == 42)
                    || (ch == 43) || (ch == 64) || (ch == 47)) {
                sbuf.append((char) ch);
            } else if (ch == 37) {
                int cint = 0;
                if ('u' != s.charAt(i + 1)) {
                    cint = cint << 4 | val[s.charAt(i + 1)];
                    cint = cint << 4 | val[s.charAt(i + 2)];
                    i += 2;
                } else {
                    cint = cint << 4 | val[s.charAt(i + 2)];
                    cint = cint << 4 | val[s.charAt(i + 3)];
                    cint = cint << 4 | val[s.charAt(i + 4)];
                    cint = cint << 4 | val[s.charAt(i + 5)];
                    i += 5;
                }
                sbuf.append((char) cint);
            }
            ++i;
        }
        return sbuf.toString();
    }

    public static String URLEncoding(String value, String enc) {
        try {
            return URLEncoder.encode(value, enc);
        } catch (Exception localException) {
        }
        return value;
    }

    public static String URLDecoding(String value, String enc) {
        try {
            return URLDecoder.decode(value, enc);
        } catch (Exception localException) {
        }
        return value;
    }

    public static String XMLEncode(String source)
    {
        if (source != null) {
            StringBuffer buf = new StringBuffer(source);
            int i = 0;
            while (true) { char c = buf.charAt(i);
                if (c == '<') {
                    buf.replace(i, i + 1, "&lt;");
                    i += 3;
                } else if (c == '>') {
                    buf.replace(i, i + 1, "&gt;");
                } else if (c == '"') {
                    buf.replace(i, i + 1, "&quot;");
                    i += 6;
                } else {
                    ++i;
                }
                if (i >= buf.length())
                {
                    return buf.toString(); } }
        }else{
            return "";
        }

    }

    public static String XMLDecode(String source)
    {
        if (source != null) {
            StringBuffer buf = new StringBuffer(source);
            int len = buf.length();
            int i = 0;
            while (true) { char c = buf.charAt(i);
                if (c == '&') {
                    String temp = buf.substring(i, i + 4);
                    if (temp.equals("&lt;")) {
                        buf.replace(i, i + 4, "<");
                    } else if (temp.equals("&gt;")) {
                        buf.replace(i, i + 4, ">");
                    } else {
                        temp = buf.substring(i, i + 6);
                        if (temp.equals("&quot;")) {
                            buf.replace(i, i + 6, "\"");
                        }
                    }
                    len = buf.length();
                }
                ++i;

                if (i >= len)
                {
                    return buf.toString(); } }
        }else{
            return "";
        }
    }

    public static String html2plan(String source)
    {
        if (source != null) {
            StringBuffer buf = new StringBuffer(source);
            int i = 0;
            while (true) { char c = buf.charAt(i);
                if (c == '<') {
                    buf.replace(i, i + 1, "&lt;");
                    i += 3;
                } else if (c == '>') {
                    buf.replace(i, i + 1, "&gt;");
                } else if (c == '"') {
                    buf.replace(i, i + 1, "&quot;");
                    i += 6;
                } else if (c == ' ') {
                    buf.replace(i, i + 1, "&nbsp;");
                    i += 6;
                } else if (c == '\n') {
                    buf.replace(i, i + 1, "<br>");
                    i += 4;
                } else {
                    ++i;
                }
                if (i >= buf.length())
                {
                    return buf.toString(); } }
        }
        return "";
    }

    public static String javaScriptEncode(String source) {
        String ret = null2blank(source);
        ret = ret.replaceAll("\\\\", "\\\\");
        ret = ret.replaceAll("\"", "\\\"");
        ret = ret.replaceAll("'", "\\'");
        ret = ret.replaceAll("\r\n", "\\r\\n");
        ret = ret.replaceAll("\n", "\\n");
        ret = ret.replaceAll("\t", "\\t");
        return ret;
    }

    public static String plan2html(String source)
    {
        if (source != null) {
            StringBuffer buf = new StringBuffer(source);
            int i = 0;
            while (true) { char c = buf.charAt(i);
                if (c == '&') {
                    String temp = buf.substring(i, i + 4);
                    if (temp.equals("&lt;")) {
                        buf.replace(i, i + 4, "<");
                    } else if (temp.equals("&gt;")) {
                        buf.replace(i, i + 4, ">");
                    } else {
                        temp = buf.substring(i, i + 6);
                        if (temp.equals("&quot;")) {
                            buf.replace(i, i + 6, "\"");
                        }
                    }
                    ++i;
                } else if (c == ' ') {
                    buf.replace(i, i + 1, "&nbsp;");
                    i += 5;
                } else if (c == '\n') {
                    buf.replace(i, i + 1, "<br>");
                    i += 3;
                } else {
                    ++i;
                }
                if (i >= buf.length())
                {
                    return buf.toString(); } }
        }
        return "";
    }

    public static String obj2String(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof String)
            return ((String) obj);
        if (obj instanceof String[]) {
            return ((((String[]) obj).length == 0) ? null : ((String[]) obj)[0]);
        }
        return obj.toString();
    }

    public static String[] sortStringArray(String[] arrStr) {
        for (int i = 0; i < arrStr.length; ++i) {
            for (int j = arrStr.length - 1; j > i; --j) {
                if (null2blank(arrStr[i]).compareTo(null2blank(arrStr[j])) <= 0)
                    continue;
                String temp = arrStr[i];
                arrStr[i] = arrStr[j];
                arrStr[j] = temp;
            }
        }

        return arrStr;
    }
}
