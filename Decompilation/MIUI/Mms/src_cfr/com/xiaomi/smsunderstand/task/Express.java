/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
package com.xiaomi.smsunderstand.task;

import com.xiaomi.common.FileOperator;
import com.xiaomi.smsunderstand.SMSUnderstand;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Express
implements Comparable<Express> {
    private static HashMap<String, String> name2BizCode;
    private static Pattern pat;
    private String name;
    private String number;

    static {
        pat = Pattern.compile((String)"^.+?(?=(\u5feb\u9012|\u5feb\u8fd0|\u901f\u8fd0|\u901f\u9012|\u7269\u6d41)+$)");
    }

    public static void clear() {
        name2BizCode = null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String getBizCodeByName(String string2) {
        String string3;
        if (name2BizCode == null && !Express.loadBizCode()) {
            return "";
        }
        String string4 = string2.toLowerCase();
        string2 = string3 = (String)name2BizCode.get((Object)string4);
        if (string3 != null) return string2;
        string2 = Express.trimSuffix(string4);
        if (string2.equals((Object)string4)) return "";
        string2 = string3 = (String)name2BizCode.get((Object)string2);
        if (string3 != null) return string2;
        return "";
    }

    private static boolean loadBizCode() {
        String string2;
        String string3 = String.valueOf((Object)SMSUnderstand.dictionaryPath) + "/expressBizCode.txt";
        name2BizCode = new HashMap();
        try {
            string3 = FileOperator.createBufferedReaderByFileName(string3);
            do {
                if ((string2 = string3.readLine()) != null) break block7;
                break;
            } while (true);
        }
        catch (IOException var1_1) {
            return false;
        }
        {
            int n;
            block7 : {
                string3.close();
                return true;
            }
            String string4 = string2.trim();
            if (string4.equals((Object)"") || (n = string4.indexOf("\t")) <= 0) continue;
            if (n >= string4.length() - 1) continue;
            string2 = string4.substring(0, n).toLowerCase();
            string4 = string4.substring(n + 1);
            name2BizCode.put((Object)string2, (Object)string4);
            String string5 = Express.trimSuffix(string2);
            if (string5 == null) continue;
            if (string5.equals((Object)"") || string5.equals((Object)string2)) continue;
            name2BizCode.put((Object)string5, (Object)string4);
            continue;
        }
    }

    private static String trimSuffix(String string2) {
        Matcher matcher = pat.matcher((CharSequence)string2);
        if (matcher.find()) {
            string2 = matcher.group();
        }
        return string2;
    }

    @Override
    public int compareTo(Express express) {
        return this.name.compareTo(express.name);
    }

    public String toString() {
        return String.valueOf((Object)this.name) + "\t" + this.number;
    }
}

