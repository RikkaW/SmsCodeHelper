/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Double
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.util.ArrayList
 *  java.util.Map$Entry
 */
package com.xiaomi.nlp;

import com.xiaomi.common.Bisection;
import com.xiaomi.common.IntInt;
import com.xiaomi.nlp.KeywordsType;
import com.xiaomi.nlp.Parser;
import com.xiaomi.nlp.PatternActionContent;
import com.xiaomi.nlp.PatternKeywords;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class PatternForNER {
    private static /* synthetic */ int[] $SWITCH_TABLE$com$xiaomi$nlp$PatternKeywords;
    public static final String endPatternReplaceStr;
    public static final String startPatternReplaceStr;
    private static String[] toBeRepace;
    public List<PatternActionContent> constraintContent;
    public int containSign = 0;
    private int[] endKeywords;
    public boolean isRegularExpression = false;
    private int[] keywords;
    public String leftProduction;
    private IntInt[] mustKeywords;
    public String pattern;
    public String remark;
    public List<PatternActionContent> resultContent;
    public double score;
    private int[] startKeywords;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    static /* synthetic */ int[] $SWITCH_TABLE$com$xiaomi$nlp$PatternKeywords() {
        int[] arrn;
        arrn = $SWITCH_TABLE$com$xiaomi$nlp$PatternKeywords;
        if (arrn != null) {
            return arrn;
        }
        arrn = new int[PatternKeywords.values().length];
        try {
            arrn[PatternKeywords.Accept.ordinal()] = 28;
        }
        catch (NoSuchFieldError var1_30) {}
        try {
            arrn[PatternKeywords.CharLenLess.ordinal()] = 20;
        }
        catch (NoSuchFieldError var1_29) {}
        try {
            arrn[PatternKeywords.CharLenLonger.ordinal()] = 19;
        }
        catch (NoSuchFieldError var1_28) {}
        try {
            arrn[PatternKeywords.ContainDic.ordinal()] = 25;
        }
        catch (NoSuchFieldError var1_27) {}
        try {
            arrn[PatternKeywords.ContainPOS.ordinal()] = 23;
        }
        catch (NoSuchFieldError var1_26) {}
        try {
            arrn[PatternKeywords.EndWithDic.ordinal()] = 10;
        }
        catch (NoSuchFieldError var1_25) {}
        try {
            arrn[PatternKeywords.EndWithPOS.ordinal()] = 12;
        }
        catch (NoSuchFieldError var1_24) {}
        try {
            arrn[PatternKeywords.EqualDic.ordinal()] = 16;
        }
        catch (NoSuchFieldError var1_23) {}
        try {
            arrn[PatternKeywords.Extract.ordinal()] = 1;
        }
        catch (NoSuchFieldError var1_22) {}
        try {
            arrn[PatternKeywords.HH.ordinal()] = 5;
        }
        catch (NoSuchFieldError var1_21) {}
        try {
            arrn[PatternKeywords.MM.ordinal()] = 3;
        }
        catch (NoSuchFieldError var1_20) {}
        try {
            arrn[PatternKeywords.NoContainDic.ordinal()] = 26;
        }
        catch (NoSuchFieldError var1_19) {}
        try {
            arrn[PatternKeywords.NoContainPOS.ordinal()] = 24;
        }
        catch (NoSuchFieldError var1_18) {}
        try {
            arrn[PatternKeywords.NoEndWithDic.ordinal()] = 11;
        }
        catch (NoSuchFieldError var1_17) {}
        try {
            arrn[PatternKeywords.NoEndWithPOS.ordinal()] = 13;
        }
        catch (NoSuchFieldError var1_16) {}
        try {
            arrn[PatternKeywords.NoEqualDic.ordinal()] = 15;
        }
        catch (NoSuchFieldError var1_15) {}
        try {
            arrn[PatternKeywords.NoNull.ordinal()] = 27;
        }
        catch (NoSuchFieldError var1_14) {}
        try {
            arrn[PatternKeywords.NoStartWithDic.ordinal()] = 17;
        }
        catch (NoSuchFieldError var1_13) {}
        try {
            arrn[PatternKeywords.NoStartWithPOS.ordinal()] = 18;
        }
        catch (NoSuchFieldError var1_12) {}
        try {
            arrn[PatternKeywords.OTHER.ordinal()] = 30;
        }
        catch (NoSuchFieldError var1_11) {}
        try {
            arrn[PatternKeywords.Reject.ordinal()] = 29;
        }
        catch (NoSuchFieldError var1_10) {}
        try {
            arrn[PatternKeywords.Remark.ordinal()] = 9;
        }
        catch (NoSuchFieldError var1_9) {}
        try {
            arrn[PatternKeywords.Score.ordinal()] = 8;
        }
        catch (NoSuchFieldError var1_8) {}
        try {
            arrn[PatternKeywords.StartWithDic.ordinal()] = 14;
        }
        catch (NoSuchFieldError var1_7) {}
        try {
            arrn[PatternKeywords.WordsLenLess.ordinal()] = 22;
        }
        catch (NoSuchFieldError var1_6) {}
        try {
            arrn[PatternKeywords.WordsLenLonger.ordinal()] = 21;
        }
        catch (NoSuchFieldError var1_5) {}
        try {
            arrn[PatternKeywords.dd.ordinal()] = 4;
        }
        catch (NoSuchFieldError var1_4) {}
        try {
            arrn[PatternKeywords.mm.ordinal()] = 6;
        }
        catch (NoSuchFieldError var1_3) {}
        try {
            arrn[PatternKeywords.ss.ordinal()] = 7;
        }
        catch (NoSuchFieldError var1_2) {}
        try {
            arrn[PatternKeywords.yyyy.ordinal()] = 2;
        }
        catch (NoSuchFieldError var1_1) {}
        $SWITCH_TABLE$com$xiaomi$nlp$PatternKeywords = arrn;
        return arrn;
    }

    static {
        startPatternReplaceStr = String.valueOf((char)'\u0015');
        endPatternReplaceStr = String.valueOf((char)'\u0016');
        toBeRepace = new String[]{"<", "+<", ">", ">+", "yyyy", "+<#yyyy>+", "MM", "+<#MM>+", "dd", "+<#dd>+", "HH", "+<#HH>+", "mm", "+<#mm>+", "ss", "+<#ss>+", "$End", endPatternReplaceStr, "$Start", startPatternReplaceStr, "\\r", "\r", "\\n", "\n"};
    }

    public PatternForNER(String string2, String string3, String string4) {
        this.initail(string2, string3, string4);
    }

    /*
     * Enabled aggressive block sorting
     */
    private String changePattern(String arrstring) {
        if (this.isRegularExpression) {
            return arrstring;
        }
        arrstring = this.replaceAll(arrstring.trim().replace('\uff1a', ':').replace('\uff0c', ',')).split("[\\+]+");
        StringBuffer stringBuffer = new StringBuffer();
        int n = 0;
        do {
            if (n >= arrstring.length) {
                if (stringBuffer.charAt(stringBuffer.length() - 1) != '+') break;
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                return stringBuffer.toString();
            }
            if (arrstring[n].length() != 0) {
                if (arrstring[n].charAt(0) == '<' && arrstring[n].charAt(arrstring[n].length() - 1) == '>') {
                    if (arrstring[n].charAt(1) == '?') {
                        if (stringBuffer.length() > 0 && stringBuffer.charAt(stringBuffer.length() - 1) == '+') {
                            stringBuffer.replace(stringBuffer.length() - 1, stringBuffer.length(), "&&&");
                        }
                        stringBuffer.append(arrstring[n]).append("&&&");
                    } else {
                        stringBuffer.append(arrstring[n]).append('+');
                    }
                } else {
                    stringBuffer.append('<').append(arrstring[n]).append(">+");
                }
            }
            ++n;
        } while (true);
        if (!stringBuffer.substring(stringBuffer.length() - "&&&".length()).equals((Object)"&&&")) return stringBuffer.toString();
        stringBuffer.delete(stringBuffer.length() - "&&&".length(), stringBuffer.length());
        return stringBuffer.toString();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void initail(String arrstring, String object, String string2) {
        this.leftProduction = arrstring;
        if (arrstring.endsWith("-R>")) {
            this.isRegularExpression = true;
        }
        this.score = 0.0;
        this.pattern = this.changePattern((String)object);
        arrstring = string2.split("\t");
        this.constraintContent = new ArrayList(3);
        this.resultContent = new ArrayList(3);
        int n = 0;
        while (n < arrstring.length) {
            if (!arrstring[n].equals((Object)"")) {
                Parser.printMemory("PatternActionContent begin " + arrstring[n]);
                object = new PatternActionContent(arrstring[n]);
                Parser.printMemory("PatternActionContent end " + arrstring[n]);
                switch (PatternForNER.$SWITCH_TABLE$com$xiaomi$nlp$PatternKeywords()[object.keywords.ordinal()]) {
                    case 30: {
                        break;
                    }
                    default: {
                        this.constraintContent.add((PatternActionContent)object);
                        break;
                    }
                    case 9: {
                        this.remark = object.parameter;
                        break;
                    }
                    case 8: {
                        this.score = Double.valueOf((String)object.parameter);
                        break;
                    }
                    case 1: {
                        this.resultContent.add((PatternActionContent)object);
                    }
                }
            }
            ++n;
        }
        return;
    }

    /*
     * Enabled aggressive block sorting
     */
    private String replaceAll(String string2) {
        boolean[] arrbl = new boolean[toBeRepace.length / 2];
        StringBuffer stringBuffer = new StringBuffer(string2.length() + 20);
        int n = 0;
        int n2 = 1;
        block0 : do {
            if (n2 == 0) {
                if (n < string2.length()) {
                    stringBuffer.append(string2.substring(n));
                }
                return stringBuffer.toString();
            }
            int n3 = Integer.MAX_VALUE;
            int n4 = 0;
            int n5 = -1;
            n2 = 0;
            do {
                int n6;
                if (n2 >= toBeRepace.length / 2) {
                    n2 = n4;
                    if (n5 < 0) continue block0;
                    stringBuffer.append(string2.substring(n, n3)).append(toBeRepace[n5 * 2 + 1]);
                    n = toBeRepace[n5 * 2].length() + n3;
                    n2 = n4;
                    continue block0;
                }
                if (arrbl[n2]) {
                    n6 = n3;
                } else {
                    int n7 = string2.indexOf(toBeRepace[n2 * 2], n);
                    if (n7 < 0) {
                        arrbl[n2] = true;
                        n6 = n3;
                    } else {
                        n6 = n3;
                        if (n3 > n7) {
                            n5 = n2;
                            n6 = n7;
                            n4 = 1;
                        }
                    }
                }
                ++n2;
                n3 = n6;
            } while (true);
            break;
        } while (true);
    }

    public boolean containsEnd() {
        if ((this.containSign & 1) > 0) {
            return true;
        }
        return false;
    }

    public boolean containsStart() {
        if ((this.containSign & 2) > 0) {
            return true;
        }
        return false;
    }

    public boolean containsStartRegularExpress() {
        if ((this.containSign & 4) > 0) {
            return true;
        }
        return false;
    }

    public boolean containsWildcard() {
        if ((this.containSign & 8) > 0) {
            return true;
        }
        return false;
    }

    public int findKeywords(int n, int n2) {
        switch (n2) {
            default: {
                return -1;
            }
            case 1: {
                return Bisection.search(n, this.keywords);
            }
            case 2: {
                return Bisection.search(n, this.startKeywords);
            }
            case 4: {
                return Bisection.search(n, this.endKeywords);
            }
            case 8: 
        }
        return Bisection.search(n, this.mustKeywords);
    }

    public int[] getKeywords() {
        return this.keywords;
    }

    public IntInt[] getMustKeywords() {
        return this.mustKeywords;
    }

    public int[] getStartKeywords() {
        return this.startKeywords;
    }

    public void setAllKeywords(TreeMap<Integer, Integer> object, int[] object2) {
        this.keywords = new int[object2[0]];
        int n = 0;
        this.startKeywords = new int[object2[1]];
        int n2 = 0;
        this.endKeywords = new int[object2[2]];
        int n3 = 0;
        this.mustKeywords = new IntInt[object2[3]];
        int n4 = 0;
        object = object.entrySet().iterator();
        while (object.hasNext()) {
            object2 = (Object)((Map.Entry)object.next());
            int n5 = (Integer)object2.getValue();
            int n6 = n;
            if (KeywordsType.isTypeEqual(n5, 1)) {
                this.keywords[n] = (Integer)object2.getKey();
                n6 = n + 1;
            }
            int n7 = n2;
            if (KeywordsType.isTypeEqual(n5, 4)) {
                this.endKeywords[n2] = (Integer)object2.getKey();
                n7 = n2 + 1;
            }
            int n8 = n3;
            if (KeywordsType.isTypeEqual(n5, 2)) {
                this.startKeywords[n3] = (Integer)object2.getKey();
                n8 = n3 + 1;
            }
            n3 = n8;
            n = n6;
            n2 = n7;
            if (!KeywordsType.isTypeEqual(n5, 8)) continue;
            this.mustKeywords[n4] = new IntInt((Integer)object2.getKey(), n5 >> 16);
            ++n4;
            n3 = n8;
            n = n6;
            n2 = n7;
        }
        return;
    }

    public void setContainsEnd() {
        this.containSign |= 1;
    }

    public void setContainsStart() {
        this.containSign |= 2;
    }

    public void setContainsStartRegularExpress() {
        this.containSign |= 4;
    }

    public void setContainsWildcard() {
        this.containSign |= 8;
    }

    public String toString() {
        return String.valueOf((Object)this.leftProduction) + " ::= " + this.pattern;
    }
}

