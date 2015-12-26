/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
package cn.com.xy.sms.sdk.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class b {
    private static String a = "(?:(?:http|https|ftp)://)?(?:[a-zA-Z0-9]{1,30})(?:\\.[a-zA-Z0-9]{1,30}){1,4}(?:[/?][^\\s]+)?|\u8c28\u9632|\u8bc8\u9a97|(?:\u6e29\u99a8|\u7279\u522b)?\u63d0[\u9192\u793a]|\u6cc4\u9732|\u56de\u590d|\u5c4f\u853d|\u62e8\u6253|[\u81f4\u901f]\u7535|\u547c\u53eb|\u8bf7\u52ff|\u52ff\u5411|\u6ce8\u610f";
    private static Pattern b = Pattern.compile((String)"(?:(?:http|https|ftp)://)?(?:[a-zA-Z0-9]{1,30})(?:\\.[a-zA-Z0-9]{1,30}){1,4}(?:[/?][^\\s]+)?|\u8c28\u9632|\u8bc8\u9a97|(?:\u6e29\u99a8|\u7279\u522b)?\u63d0[\u9192\u793a]|\u6cc4\u9732|\u56de\u590d|\u5c4f\u853d|\u62e8\u6253|[\u81f4\u901f]\u7535|\u547c\u53eb|\u8bf7\u52ff|\u52ff\u5411|\u6ce8\u610f");
    private static String c = "([\\[\u3014])|([\u3015\\]])";
    private static Pattern d = Pattern.compile((String)"([\\[\u3014])|([\u3015\\]])");

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static String a(String object) {
        int n2;
        Object object2 = d.matcher((CharSequence)object);
        if (object2.find()) {
            object = new StringBuffer();
            do {
                String string2 = object2.group(1);
                String string3 = object2.group(2);
                if (string2 != null) {
                    object2.appendReplacement((StringBuffer)object, "\u3010");
                }
                if (string3 == null) continue;
                object2.appendReplacement((StringBuffer)object, "\u3011");
            } while (object2.find());
            object2.appendTail((StringBuffer)object);
            object = object.toString();
        }
        object2 = object.replaceAll("([:\uff1a ])[:\uff1a ]+", "$1").replaceAll("([,\uff0c\u3002\uff1b\uff01!;\\?][^\u3010,\uff0c\u3002\uff1b\uff01!;\\?]*)\u3010(?=[^\u3011]*[,\uff0c\u3002\uff1b\uff01!;\\?])[^\u3011]+\u3011", "$1:").replaceFirst("[\\(\uff08\u3010]\\d/\\d[\\)\uff09\u3011]", "");
        int n3 = object2.length();
        if ('\u3010' == object2.charAt(0) && (n2 = object2.indexOf(12305)) != -1 && b.b((String)(object = object2.substring(1, n2)))) {
            return object;
        }
        if ('\u3011' != object2.charAt(--n3)) return null;
        n2 = object2.lastIndexOf(12304);
        if (n2 < 0) return null;
        object = object2 = object2.substring(n2 + 1, n3);
        if (b.b((String)object2)) return object;
        return null;
    }

    private static boolean b(String string2) {
        if (string2 != null && string2.trim().length() > 0 && !b.matcher((CharSequence)string2).find()) {
            return true;
        }
        return false;
    }

    private static String c(String string2) {
        Matcher matcher = d.matcher((CharSequence)string2);
        if (matcher.find()) {
            string2 = new StringBuffer();
            do {
                String string3 = matcher.group(1);
                String string4 = matcher.group(2);
                if (string3 != null) {
                    matcher.appendReplacement((StringBuffer)string2, "\u3010");
                }
                if (string4 == null) continue;
                matcher.appendReplacement((StringBuffer)string2, "\u3011");
            } while (matcher.find());
            matcher.appendTail((StringBuffer)string2);
            string2 = string2.toString();
        }
        return string2.replaceAll("([:\uff1a ])[:\uff1a ]+", "$1").replaceAll("([,\uff0c\u3002\uff1b\uff01!;\\?][^\u3010,\uff0c\u3002\uff1b\uff01!;\\?]*)\u3010(?=[^\u3011]*[,\uff0c\u3002\uff1b\uff01!;\\?])[^\u3011]+\u3011", "$1:").replaceFirst("[\\(\uff08\u3010]\\d/\\d[\\)\uff09\u3011]", "");
    }
}

