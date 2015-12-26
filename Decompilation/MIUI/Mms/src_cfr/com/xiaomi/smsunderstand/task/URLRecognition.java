/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Character
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashSet
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
package com.xiaomi.smsunderstand.task;

import com.xiaomi.common.FileOperator;
import com.xiaomi.common.StringProcess;
import com.xiaomi.smsunderstand.EntityInfo;
import com.xiaomi.smsunderstand.SMSUnderstand;
import com.xiaomi.smsunderstand.task.FlowRecognition;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLRecognition {
    private static boolean ifInitial;
    private static Pattern patURL;
    private static Pattern patURLCheck;
    private static HashSet<String> urlContainWords;
    private static String urlContainWordsFileName;
    private static int urlContainWordsMaxLength;

    static {
        urlContainWords = new HashSet();
        urlContainWordsMaxLength = -1;
        urlContainWordsFileName = null;
        ifInitial = false;
        patURLCheck = Pattern.compile((String)"\\.[0-9]+$");
        patURL = Pattern.compile((String)"[\\da-zA-Z/\\.?:%&=\\-_]{4,}", (int)2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static boolean checkURL(String string2) throws IOException {
        if (StringProcess.getEngCharCount(string2) <= 0) {
            return false;
        }
        if (URLRecognition.getPointCount(string2) <= 0) return false;
        if (string2.endsWith(".")) return false;
        if (FlowRecognition.check(string2)) return false;
        if (string2.contains((CharSequence)"..")) return false;
        if (patURLCheck.matcher((CharSequence)string2).find()) return false;
        try {
            if (!ifInitial) {
                SMSUnderstand.initial();
            }
            URLRecognition.initial();
            if (StringProcess.findAllResultsFromList(string2.toLowerCase(), urlContainWords, urlContainWordsMaxLength).size() <= 0) return false;
            return true;
        }
        catch (IOException var0_1) {
            return false;
        }
    }

    public static String formatUrl(String string2) {
        String string3 = (string2 = StringProcess.trimEnd(StringProcess.trimStart(string2, Character.valueOf((char)':'), Character.valueOf((char)'/'), Character.valueOf((char)' '), Character.valueOf((char)'\t')), Character.valueOf((char)' '), Character.valueOf((char)'\t'))).toLowerCase();
        if (string3.startsWith("http://") || string3.startsWith("https://") || string3.startsWith("ftp://")) {
            return string2;
        }
        return "http://" + string2;
    }

    public static boolean freeResource() {
        if (!ifInitial) {
            return true;
        }
        urlContainWords.clear();
        urlContainWordsMaxLength = -1;
        ifInitial = false;
        return true;
    }

    private static int getPointCount(String string2) {
        int n = 0;
        int n2 = 0;
        while (n2 < string2.length()) {
            int n3 = n;
            if (string2.charAt(n2) == '.') {
                n3 = n + 1;
            }
            ++n2;
            n = n3;
        }
        return n;
    }

    public static boolean initial() throws IOException {
        if (ifInitial) {
            return true;
        }
        urlContainWordsFileName = String.valueOf((Object)SMSUnderstand.dictionaryPath) + "/urlContainWords.txt";
        Iterator<String> iterator = FileOperator.readFile(urlContainWordsFileName).iterator();
        block0 : do {
            if (!iterator.hasNext()) {
                ifInitial = true;
                return true;
            }
            String[] arrstring = iterator.next().split("\\t");
            int n = arrstring.length;
            int n2 = 0;
            do {
                if (n2 >= n) continue block0;
                String string2 = arrstring[n2];
                if (!string2.equals((Object)"")) {
                    urlContainWords.add((Object)string2.toLowerCase());
                    if (urlContainWordsMaxLength < string2.length()) {
                        urlContainWordsMaxLength = string2.length();
                    }
                }
                ++n2;
            } while (true);
            break;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static double isRightNumber(EntityInfo entityInfo, String string2) {
        int n;
        if (!ifInitial || (n = StringProcess.findAllResultsFromList(entityInfo.getTarget().toLowerCase(), urlContainWords, urlContainWordsMaxLength).size()) <= 0) {
            return 0.0;
        }
        return 1.0 - Math.pow((double)0.1, (double)n);
    }
}

