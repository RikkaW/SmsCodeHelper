/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.ArrayList
 *  java.util.HashMap
 */
package com.xiaomi.nlp;

import com.xiaomi.common.Log;
import com.xiaomi.common.StringProcess;
import com.xiaomi.nlp.KeyWordAccessable;
import com.xiaomi.nlp.PatternForNER;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RuleFunction {
    private static /* synthetic */ int[] $SWITCH_TABLE$com$xiaomi$nlp$RuleFunction$Variable;
    public static String constFolderName = "Data/Constant/";
    public static HashMap<Integer, String[]> pattern2Seg = new HashMap();
    public Map<String, ? extends ArrayList<? extends KeyWordAccessable>> constants;
    private int wildCardMaxMatchLength = 18;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    static /* synthetic */ int[] $SWITCH_TABLE$com$xiaomi$nlp$RuleFunction$Variable() {
        int[] arrn;
        arrn = $SWITCH_TABLE$com$xiaomi$nlp$RuleFunction$Variable;
        if (arrn != null) {
            return arrn;
        }
        arrn = new int[Variable.values().length];
        try {
            arrn[Variable.CHAR.ordinal()] = 16;
        }
        catch (NoSuchFieldError var1_22) {}
        try {
            arrn[Variable.CONSTANT.ordinal()] = 19;
        }
        catch (NoSuchFieldError var1_21) {}
        try {
            arrn[Variable.DAY.ordinal()] = 11;
        }
        catch (NoSuchFieldError var1_20) {}
        try {
            arrn[Variable.END.ordinal()] = 22;
        }
        catch (NoSuchFieldError var1_19) {}
        try {
            arrn[Variable.ENG.ordinal()] = 17;
        }
        catch (NoSuchFieldError var1_18) {}
        try {
            arrn[Variable.HOUR.ordinal()] = 12;
        }
        catch (NoSuchFieldError var1_17) {}
        try {
            arrn[Variable.KONG.ordinal()] = 3;
        }
        catch (NoSuchFieldError var1_16) {}
        try {
            arrn[Variable.MINUTE.ordinal()] = 13;
        }
        catch (NoSuchFieldError var1_15) {}
        try {
            arrn[Variable.MONTH.ordinal()] = 10;
        }
        catch (NoSuchFieldError var1_14) {}
        try {
            arrn[Variable.NPOS.ordinal()] = 1;
        }
        catch (NoSuchFieldError var1_13) {}
        try {
            arrn[Variable.NUMBER.ordinal()] = 15;
        }
        catch (NoSuchFieldError var1_12) {}
        try {
            arrn[Variable.NWORD.ordinal()] = 18;
        }
        catch (NoSuchFieldError var1_11) {}
        try {
            arrn[Variable.OTHER.ordinal()] = 20;
        }
        catch (NoSuchFieldError var1_10) {}
        try {
            arrn[Variable.POS.ordinal()] = 2;
        }
        catch (NoSuchFieldError var1_9) {}
        try {
            arrn[Variable.SECOND.ordinal()] = 14;
        }
        catch (NoSuchFieldError var1_8) {}
        try {
            arrn[Variable.STAR.ordinal()] = 5;
        }
        catch (NoSuchFieldError var1_7) {}
        try {
            arrn[Variable.START.ordinal()] = 21;
        }
        catch (NoSuchFieldError var1_6) {}
        try {
            arrn[Variable.STAR_POS.ordinal()] = 8;
        }
        catch (NoSuchFieldError var1_5) {}
        try {
            arrn[Variable.STAR_PREFIX.ordinal()] = 7;
        }
        catch (NoSuchFieldError var1_4) {}
        try {
            arrn[Variable.STAR_SUFFIX.ordinal()] = 6;
        }
        catch (NoSuchFieldError var1_3) {}
        try {
            arrn[Variable.WORD_POS.ordinal()] = 4;
        }
        catch (NoSuchFieldError var1_2) {}
        try {
            arrn[Variable.YEAR.ordinal()] = 9;
        }
        catch (NoSuchFieldError var1_1) {}
        $SWITCH_TABLE$com$xiaomi$nlp$RuleFunction$Variable = arrn;
        return arrn;
    }

    public RuleFunction() {
        this.wildCardMaxMatchLength = 18;
    }

    public RuleFunction(int n) {
        this.wildCardMaxMatchLength = n;
    }

    public static Variable analyzeTag(String string2) {
        if ("*".equals((Object)string2)) {
            return Variable.STAR;
        }
        if (string2.startsWith("^#")) {
            return Variable.NPOS;
        }
        if (string2.length() > 1 && string2.startsWith("^")) {
            return Variable.NWORD;
        }
        if (string2.equals((Object)"#yyyy")) {
            return Variable.YEAR;
        }
        if (string2.equals((Object)"#MM")) {
            return Variable.MONTH;
        }
        if (string2.equals((Object)"#dd")) {
            return Variable.DAY;
        }
        if (string2.equals((Object)"#HH")) {
            return Variable.HOUR;
        }
        if (string2.equals((Object)"#mm")) {
            return Variable.MINUTE;
        }
        if (string2.equals((Object)"#ss")) {
            return Variable.SECOND;
        }
        if (string2.equals((Object)PatternForNER.endPatternReplaceStr)) {
            return Variable.END;
        }
        if (string2.equals((Object)PatternForNER.startPatternReplaceStr)) {
            return Variable.START;
        }
        if (string2.equals((Object)"#m")) {
            return Variable.NUMBER;
        }
        if (string2.equals((Object)"#char")) {
            return Variable.CHAR;
        }
        if (string2.equals((Object)"#eng")) {
            return Variable.ENG;
        }
        if (string2.length() > 1 && string2.startsWith("#")) {
            return Variable.POS;
        }
        if ("!\u7a7a".equals((Object)string2)) {
            return Variable.KONG;
        }
        if (string2.length() > 1 && string2.startsWith("!")) {
            return Variable.CONSTANT;
        }
        return Variable.OTHER;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static int getMaxNumEndPos(String string2, int n) {
        while (n < string2.length()) {
            int n2 = n;
            if (string2.charAt(n) < '0') return n2;
            n2 = n;
            if (string2.charAt(n) > '9') return n2;
            ++n;
        }
        return string2.length();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static int getStartPos(ArrayList<? extends KeyWordAccessable> var0, String var1_1) {
        var3_2 = 0;
        var2_3 = var0.size() - 1;
        var4_4 = (0 + var2_3) / 2;
        do {
            if (var3_2 > var2_3 || ((KeyWordAccessable)var0.get(var4_4)).getKeyWord().startsWith(var1_1)) ** GOTO lbl9
            if (var1_1.compareTo(((KeyWordAccessable)var0.get(var4_4)).getKeyWord()) >= 0) ** GOTO lbl11
            var2_3 = var4_4 - 1;
            ** GOTO lbl12
lbl9: // 1 sources:
            if (var3_2 <= var2_3) break;
            return -1;
lbl11: // 1 sources:
            var3_2 = var4_4 + 1;
lbl12: // 2 sources:
            var4_4 = (var3_2 + var2_3) / 2;
        } while (true);
        var2_3 = var4_4 - 1;
        while (var2_3 >= 0) {
            if (!((KeyWordAccessable)var0.get(var2_3)).getKeyWord().startsWith(var1_1)) {
                return var2_3 + 1;
            }
            --var2_3;
        }
        return var2_3 + 1;
    }

    public boolean contain(String string2, String string3) {
        String string4 = string3;
        if (!string3.startsWith("<*>")) {
            string4 = "<*>+" + string3;
        }
        return this.startWith(string2, string4);
    }

    public boolean endWith(String string2, String string3) {
        String string4 = string3;
        if (!string3.startsWith("<*>")) {
            string4 = "<*>+" + string3;
        }
        return this.strEqual(string2, string4);
    }

    public void loadConstants(Map<String, ? extends ArrayList<? extends KeyWordAccessable>> map) {
        this.constants = map;
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public int maxNumStartWith(String var1_1, int var2_2, String var3_3) {
        var10_4 = String.valueOf((char)var1_1.charAt(var2_2));
        var5_5 = -1;
        var11_6 = var3_3.split("\\|");
        var9_7 = var11_6.length;
        var6_8 = 0;
        block20 : do {
            if (var6_8 >= var9_7) {
                return var5_5;
            }
            var12_12 = var11_6[var6_8];
            var3_3 = RuleFunction.analyzeTag(var12_12);
            var4_9 = var5_5;
            switch (RuleFunction.$SWITCH_TABLE$com$xiaomi$nlp$RuleFunction$Variable()[var3_3.ordinal()]) {
                default: {
                    var4_9 = var5_5;
                }
lbl15: // 67 sources:
                do {
                    case 4: 
                    case 5: 
                    case 8: lbl-1000: // 4 sources:
                    {
                        do {
                            ++var6_8;
                            var5_5 = var4_9;
                            continue block20;
                            break;
                        } while (true);
                    }
                    break;
                } while (true);
                case 18: {
                    var4_9 = var5_5;
                    if (var10_4.equals((Object)var12_12.substring(1))) ** GOTO lbl15
                    if (var5_5 <= 1) ** GOTO lbl27
                    var4_9 = var5_5;
                    ** GOTO lbl15
lbl27: // 1 sources:
                    var4_9 = 1;
                    ** GOTO lbl15
                }
                case 3: {
                    var4_9 = var5_5;
                    if (var5_5 > 0) ** GOTO lbl15
                    var4_9 = 0;
                    ** GOTO lbl15
                }
                case 6: {
                    var4_9 = var5_5;
                    if (!var10_4.endsWith(var12_12.substring(1))) ** GOTO lbl15
                    var4_9 = var5_5;
                    if (var5_5 > 1) ** GOTO lbl15
                    var4_9 = 1;
                    ** GOTO lbl15
                }
                case 7: {
                    var4_9 = var5_5;
                    if (!var10_4.startsWith(var12_12.substring(0, var12_12.length() - 1))) ** GOTO lbl15
                    var4_9 = var5_5;
                    if (var5_5 > 1) ** GOTO lbl15
                    var4_9 = 1;
                    ** GOTO lbl15
                }
                case 21: {
                    var4_9 = var5_5;
                    if (var2_2 != 0) ** GOTO lbl15
                    var4_9 = var5_5;
                    if (var5_5 > 0) ** GOTO lbl15
                    var4_9 = 0;
                    ** GOTO lbl15
                }
                case 22: {
                    var4_9 = var5_5;
                    if (var1_1.length() > var2_2) ** GOTO lbl15
                    var4_9 = var5_5;
                    if (var5_5 > 0) ** GOTO lbl15
                    var4_9 = 0;
                    ** GOTO lbl15
                }
                case 9: {
                    var7_10 = RuleFunction.getMaxNumEndPos(var1_1, var2_2);
                    var4_9 = var5_5;
                    if (var7_10 <= var2_2) ** GOTO lbl15
                    var3_3 = var1_1.substring(var2_2, var7_10);
                    if (var3_3.length() == 4) ** GOTO lbl70
                    var4_9 = var5_5;
                    if (var3_3.length() != 2) ** GOTO lbl15
lbl70: // 2 sources:
                    if ((var8_11 = Integer.parseInt((String)var3_3)) < 100) ** GOTO lbl73
                    var4_9 = var5_5;
                    if (var8_11 <= 999) ** GOTO lbl15
lbl73: // 2 sources:
                    if (var8_11 >= 100) ** GOTO lbl83
                    var4_9 = var5_5;
                    if (var1_1.length() <= var7_10) ** GOTO lbl15
                    var4_9 = var5_5;
                    if (var1_1.charAt(var7_10) != '\u5e74') ** GOTO lbl15
                    do {
                        var4_9 = var5_5;
                        if (var5_5 > var7_10 - var2_2) ** GOTO lbl15
                        var4_9 = var7_10 - var2_2;
                        ** GOTO lbl15
                        break;
                    } while (true);
lbl83: // 1 sources:
                    if (var8_11 < 2061) ** continue;
                    var4_9 = var5_5;
                    ** GOTO lbl15
                }
                case 10: {
                    var7_10 = RuleFunction.getMaxNumEndPos(var1_1, var2_2);
                    var4_9 = var5_5;
                    if (var7_10 <= var2_2) ** GOTO lbl15
                    var3_3 = var1_1.substring(var2_2, var7_10);
                    if (var3_3.length() == 1) ** GOTO lbl94
                    var4_9 = var5_5;
                    if (var3_3.length() != 2) ** GOTO lbl15
lbl94: // 2 sources:
                    var8_11 = Integer.parseInt((String)var3_3);
                    var4_9 = var5_5;
                    if (var8_11 < 1) ** GOTO lbl15
                    var4_9 = var5_5;
                    if (var8_11 > 12) ** GOTO lbl15
                    var4_9 = var5_5;
                    if (var5_5 > var7_10 - var2_2) ** GOTO lbl15
                    var4_9 = var7_10 - var2_2;
                    ** GOTO lbl15
                }
                case 11: {
                    var7_10 = RuleFunction.getMaxNumEndPos(var1_1, var2_2);
                    var4_9 = var5_5;
                    if (var7_10 <= var2_2) ** GOTO lbl15
                    var3_3 = var1_1.substring(var2_2, var7_10);
                    if (var3_3.length() == 1) ** GOTO lbl111
                    var4_9 = var5_5;
                    if (var3_3.length() != 2) ** GOTO lbl15
lbl111: // 2 sources:
                    var8_11 = Integer.parseInt((String)var3_3);
                    var4_9 = var5_5;
                    if (var8_11 < 1) ** GOTO lbl15
                    var4_9 = var5_5;
                    if (var8_11 > 31) ** GOTO lbl15
                    var4_9 = var5_5;
                    if (var5_5 > var7_10 - var2_2) ** GOTO lbl15
                    var4_9 = var7_10 - var2_2;
                    ** GOTO lbl15
                }
                case 12: {
                    var7_10 = RuleFunction.getMaxNumEndPos(var1_1, var2_2);
                    var4_9 = var5_5;
                    if (var7_10 <= var2_2) ** GOTO lbl15
                    var3_3 = var1_1.substring(var2_2, var7_10);
                    if (var3_3.length() == 1) ** GOTO lbl128
                    var4_9 = var5_5;
                    if (var3_3.length() != 2) ** GOTO lbl15
lbl128: // 2 sources:
                    var4_9 = var5_5;
                    if (Integer.parseInt((String)var3_3) > 24) ** GOTO lbl15
                    var4_9 = var5_5;
                    if (var5_5 > var7_10 - var2_2) ** GOTO lbl15
                    var4_9 = var7_10 - var2_2;
                    ** GOTO lbl15
                }
                case 13: {
                    var7_10 = RuleFunction.getMaxNumEndPos(var1_1, var2_2);
                    var4_9 = var5_5;
                    if (var7_10 <= var2_2) ** GOTO lbl15
                    var3_3 = var1_1.substring(var2_2, var7_10);
                    if (var3_3.length() == 1) ** GOTO lbl142
                    var4_9 = var5_5;
                    if (var3_3.length() != 2) ** GOTO lbl15
lbl142: // 2 sources:
                    var4_9 = var5_5;
                    if (Integer.parseInt((String)var3_3) > 60) ** GOTO lbl15
                    var4_9 = var5_5;
                    if (var5_5 > var7_10 - var2_2) ** GOTO lbl15
                    var4_9 = var7_10 - var2_2;
                    ** GOTO lbl15
                }
                case 14: {
                    var7_10 = RuleFunction.getMaxNumEndPos(var1_1, var2_2);
                    var4_9 = var5_5;
                    if (var7_10 <= var2_2) ** GOTO lbl15
                    var3_3 = var1_1.substring(var2_2, var7_10);
                    if (var3_3.length() == 1) ** GOTO lbl156
                    var4_9 = var5_5;
                    if (var3_3.length() != 2) ** GOTO lbl15
lbl156: // 2 sources:
                    var4_9 = var5_5;
                    if (Integer.parseInt((String)var3_3) > 60) ** GOTO lbl15
                    var4_9 = var5_5;
                    if (var5_5 > var7_10 - var2_2) ** GOTO lbl15
                    var4_9 = var7_10 - var2_2;
                    ** GOTO lbl15
                }
                case 15: {
                    var7_10 = RuleFunction.getMaxNumEndPos(var1_1, var2_2);
                    var4_9 = var5_5;
                    if (var7_10 <= var2_2) ** GOTO lbl15
                    var4_9 = var5_5;
                    if (var5_5 > var7_10 - var2_2) ** GOTO lbl15
                    var4_9 = var7_10 - var2_2;
                    ** GOTO lbl15
                }
                case 16: {
                    var7_10 = var2_2;
                    block24 : do {
                        var4_9 = var5_5;
                        if (var7_10 >= var1_1.length()) ** GOTO lbl15
                        if (var1_1.charAt(var7_10) >= '0' && var1_1.charAt(var7_10) <= '9' || var1_1.charAt(var7_10) >= 'a' && var1_1.charAt(var7_10) <= 'z') ** GOTO lbl180
                        var4_9 = var5_5;
                        if (var1_1.charAt(var7_10) < 'A') ** GOTO lbl15
                        var4_9 = var5_5;
                        if (var1_1.charAt(var7_10) > 'Z') ** GOTO lbl15
lbl180: // 2 sources:
                        if (var5_5 <= var7_10 - var2_2 + 1) break;
lbl181: // 2 sources:
                        do {
                            ++var7_10;
                            continue block24;
                            break;
                        } while (true);
                        break;
                    } while (true);
                    var5_5 = var7_10 - var2_2 + 1;
                    ** continue;
                }
                case 17: {
                    var7_10 = var2_2;
                    block26 : do {
                        var4_9 = var5_5;
                        if (var7_10 >= var1_1.length()) ** GOTO lbl15
                        if (var1_1.charAt(var7_10) >= 'a' && var1_1.charAt(var7_10) <= 'z') ** GOTO lbl196
                        var4_9 = var5_5;
                        if (var1_1.charAt(var7_10) < 'A') ** GOTO lbl15
                        var4_9 = var5_5;
                        if (var1_1.charAt(var7_10) > 'Z') ** GOTO lbl15
lbl196: // 2 sources:
                        if (var5_5 <= var7_10 - var2_2 + 1) break;
lbl197: // 2 sources:
                        do {
                            ++var7_10;
                            continue block26;
                            break;
                        } while (true);
                        break;
                    } while (true);
                    var5_5 = var7_10 - var2_2 + 1;
                    ** continue;
                }
                case 19: {
                    var13_13 = this.constants.get(var12_12);
                    if (var13_13 != null) ** GOTO lbl208
                    Log.println("No such constant : " + var12_12);
                    var4_9 = var5_5;
                    ** GOTO lbl15
lbl208: // 1 sources:
                    var7_10 = RuleFunction.getStartPos(var13_13, var10_4);
                    var4_9 = var5_5;
                    if (var7_10 == -1) ** GOTO lbl15
                    var4_9 = var5_5;
                    block28 : do {
                        if (var7_10 >= var13_13.size()) ** GOTO lbl15
                        if (!var10_4.equals((Object)((KeyWordAccessable)var13_13.get(var7_10)).getKeyWord())) ** GOTO lbl223
                        if (var4_9 <= 1) break;
                        var5_5 = var4_9;
lbl217: // 5 sources:
                        do {
                            ++var7_10;
                            var4_9 = var5_5;
                            continue block28;
                            break;
                        } while (true);
                        break;
                    } while (true);
                    var5_5 = 1;
                    ** GOTO lbl217
lbl223: // 1 sources:
                    if (((KeyWordAccessable)var13_13.get(var7_10)).getKeyWord().startsWith(var10_4)) ** break;
                    ** continue;
                    var8_11 = var2_2 + 1;
                    var3_3 = var10_4;
                    do {
                        var5_5 = var4_9;
                        if (var8_11 >= var1_1.length()) ** GOTO lbl217
                        if (!(var3_3 = String.valueOf((Object)var3_3) + var1_1.charAt(var8_11)).equals((Object)((KeyWordAccessable)var13_13.get(var7_10)).getKeyWord())) ** GOTO lbl235
                        var5_5 = var4_9;
                        if (var4_9 <= var8_11 - var2_2 + 1) {
                            var5_5 = var8_11 - var2_2 + 1;
                        }
                        ** GOTO lbl217
lbl235: // 1 sources:
                        var5_5 = var4_9;
                        if (((KeyWordAccessable)var13_13.get(var7_10)).getKeyWord().startsWith((String)var3_3)) ** break;
                        ** continue;
                        ++var8_11;
                    } while (true);
                }
                case 20: 
            }
            break;
        } while (true);
        var3_3 = "";
        var7_10 = var2_2;
        do {
            var4_9 = var5_5;
            if (var7_10 >= var1_1.length()) ** GOTO lbl-1000
            if (!(var3_3 = String.valueOf((Object)var3_3) + var1_1.charAt(var7_10)).equals((Object)var12_12)) ** GOTO lbl251
            var4_9 = var5_5;
            if (var5_5 <= var7_10 - var2_2 + 1) {
                var4_9 = var7_10 - var2_2 + 1;
            }
            ** GOTO lbl-1000
lbl251: // 1 sources:
            var4_9 = var5_5;
            if (var12_12.startsWith((String)var3_3)) ** break;
            ** continue;
            ++var7_10;
        } while (true);
    }

    public boolean startWith(String string2, String string3) {
        String string4 = string3;
        if (!string3.endsWith("<*>")) {
            string4 = String.valueOf((Object)string3) + "+<*>";
        }
        return this.strEqual(string2, string4, new ArrayList());
    }

    public boolean strEqual(String string2, String string3) {
        return this.strEqual(string2, string3, new ArrayList());
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean strEqual(String var1_1, String var2_2, ArrayList<Integer> var3_3) {
        var2_2 = StringProcess.noRegexSplit((String)var2_2, "+");
        var3_3.clear();
        var4_4 = 0;
        do {
            if (var4_4 > var2_2.length) break;
            var3_3.add((Object)0);
            ++var4_4;
        } while (true);
        var5_5 = 0;
        var4_4 = 0;
        block1 : do {
            if (var4_4 >= var2_2.length) ** GOTO lbl33
            var3_3.set(var4_4, (Object)var5_5);
            var12_12 = var2_2[var4_4];
            if (var12_12.length() <= 2 || var12_12.charAt(0) != '<' || var12_12.charAt(var12_12.length() - 1) != '>') ** GOTO lbl28
            var12_12 = var12_12.substring(1, var12_12.length() - 1);
            if (var5_5 < var1_1.length()) ** GOTO lbl30
            if (!"*".equals((Object)var12_12) && var12_12.indexOf("!\u7a7a") == -1) {
                if (var12_12.contains((CharSequence)PatternForNER.endPatternReplaceStr) == false) return false;
                var6_6 = var4_4;
                var4_4 = var5_5;
                var5_5 = var6_6;
            } else {
                var6_6 = var5_5;
                var5_5 = var4_4;
                var4_4 = var6_6;
            }
            ** GOTO lbl43
lbl28: // 1 sources:
            Log.println("Bad parameter : " + var12_12);
            return false;
lbl30: // 1 sources:
            if ("*".equals((Object)var12_12)) {
                if (var4_4 == var2_2.length - 1) {
                    var5_5 = var1_1.length();
lbl33: // 2 sources:
                    var3_3.set(var2_2.length, (Object)var5_5);
                    if (var5_5 != var1_1.length()) return false;
                    return true;
                }
                break;
            }
            var6_6 = this.maxNumStartWith(var1_1, var5_5, var12_12);
            if (var6_6 == -1) {
                return false;
            }
            var6_6 = var5_5 + var6_6;
            var5_5 = var4_4;
            var4_4 = var6_6;
lbl43: // 4 sources:
            do {
                var6_6 = var4_4;
                var4_4 = var5_5 + 1;
                var5_5 = var6_6;
                continue block1;
                break;
            } while (true);
            break;
        } while (true);
        var8_8 = 0;
        var10_10 = 0;
        var6_6 = var4_4 + 1;
        do {
            if (var6_6 >= var2_2.length) ** GOTO lbl67
            var3_3.set(var6_6, (Object)(var5_5 + var8_8));
            var12_12 = var2_2[var6_6];
            if (var12_12.length() <= 2 || var12_12.charAt(0) != '<' || var12_12.charAt(var12_12.length() - 1) != '>') ** GOTO lbl71
            if ("*".equals((Object)(var12_12 = var12_12.substring(1, var12_12.length() - 1)))) ** GOTO lbl67
            if (var5_5 + var8_8 < var1_1.length()) ** GOTO lbl73
            var9_9 = var10_10;
            var7_7 = var8_8;
            var11_11 = var6_6;
            if (var12_12.indexOf("!\u7a7a") == -1) {
                if (var12_12.contains((CharSequence)PatternForNER.endPatternReplaceStr) == false) return false;
                var11_11 = var6_6;
                var7_7 = var8_8;
                var9_9 = var10_10;
            }
            ** GOTO lbl82
lbl67: // 2 sources:
            if (var4_4 + 1 >= var3_3.size() || (Integer)var3_3.get(var4_4 + 1) <= 0) break;
            if ((Integer)var3_3.get(var4_4 + 1) - (Integer)var3_3.get(var4_4) >= this.wildCardMaxMatchLength) return false;
            if (StringProcess.findSegPunctuation(var1_1, (Integer)var3_3.get(var4_4), (Integer)var3_3.get(var4_4 + 1)) < 0) break;
            return false;
lbl71: // 1 sources:
            Log.println("Bad parameter : " + var12_12);
            return false;
lbl73: // 1 sources:
            var7_7 = this.maxNumStartWith(var1_1, var5_5 + var8_8, var12_12);
            if (var7_7 == -1 || var6_6 == var2_2.length - 1 && var5_5 + var8_8 + var7_7 < var1_1.length()) {
                var7_7 = var9_9 = var10_10 + 1;
                var11_11 = var4_4;
            } else {
                var3_3.set(var6_6, (Object)(var5_5 + var8_8));
                var7_7 = var8_8 + var7_7;
                var9_9 = var10_10;
                var11_11 = var6_6;
            }
lbl82: // 3 sources:
            var6_6 = var11_11 + 1;
            var10_10 = var9_9;
            var8_8 = var7_7;
        } while (true);
        var4_4 = var6_6 - 1;
        var6_6 = var5_5 + var8_8;
        var5_5 = var4_4;
        var4_4 = var6_6;
        ** while (true)
    }

    public static enum Variable {
        NPOS,
        POS,
        KONG,
        WORD_POS,
        STAR,
        STAR_SUFFIX,
        STAR_PREFIX,
        STAR_POS,
        YEAR,
        MONTH,
        DAY,
        HOUR,
        MINUTE,
        SECOND,
        NUMBER,
        CHAR,
        ENG,
        NWORD,
        CONSTANT,
        OTHER,
        START,
        END;
        

        private Variable(String string3, int n2) {
        }
    }

}

