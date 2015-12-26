/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.util.ArrayList
 *  java.util.HashMap
 *  java.util.HashSet
 *  java.util.Map$Entry
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
package com.xiaomi.smsunderstand.task;

import com.xiaomi.common.FileOperator;
import com.xiaomi.common.StringIntInt;
import com.xiaomi.common.StringProcess;
import com.xiaomi.nlp.ParseResult;
import com.xiaomi.nlp.Parser;
import com.xiaomi.nlp.PatternForNER;
import com.xiaomi.smsunderstand.EntityInfo;
import com.xiaomi.smsunderstand.EntityType;
import com.xiaomi.smsunderstand.SMSUnderstand;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberRecognition {
    private static HashMap<String, Integer> areaCode2numberLength;
    private static String areaCode2numberLengthFileName;
    private static HashSet<String> badFrontGuideWords;
    private static String badFrontGuideWordsFileName;
    private static int badFrontGuideWordsMaxLength;
    private static HashMap<String, String> commonServiceNumber2Name;
    private static String commonServiceNumberFileName;
    private static boolean ifInitial;
    private static int maxVagueDistance;
    private static String mobileNoPrefixFileName;
    private static String mobileNoRegex;
    private static Parser parser;
    private static Pattern patFormobileNo_full;
    private static HashSet<String> phoneNoFrontGuideWords;
    private static String phoneNoFrontGuideWordsFileName;
    private static int phoneNoFrontGuideWordsMaxLength;
    private static Pattern phoneNumberNomal;

    static {
        mobileNoRegex = "((\\+86)|(86))?(12520)?(mobileNoPrefix)[0-9]{8}";
        ifInitial = false;
        phoneNoFrontGuideWords = new HashSet();
        phoneNoFrontGuideWordsMaxLength = -1;
        phoneNoFrontGuideWordsFileName = null;
        badFrontGuideWords = new HashSet();
        badFrontGuideWordsMaxLength = -1;
        badFrontGuideWordsFileName = null;
        mobileNoPrefixFileName = null;
        areaCode2numberLengthFileName = null;
        commonServiceNumberFileName = null;
        commonServiceNumber2Name = new HashMap();
        areaCode2numberLength = new HashMap();
        maxVagueDistance = 2;
        phoneNumberNomal = Pattern.compile((String)"^((\\+|86|12520|12521|12522|12523|12524|12525|12526|12527|12528|12529)+)(.*?)$");
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static List<EntityInfo> findKnowledge(String var0, String var1_1) throws Exception {
        var9_2 = new ArrayList();
        if (!PhoneNumberRecognition.ifInitial) {
            return var9_2;
        }
        var10_3 = PhoneNumberRecognition.parser.findKnowledge(var0, 0.3);
        var11_4 = var10_3.iterator();
        var4_5 = 1;
        block0 : do {
            if (!var11_4.hasNext()) {
                return var9_2;
            }
            var12_12 = var11_4.next();
            var6_9 = var10_3.size();
            var13_13 = var12_12.knowledge.entrySet().iterator();
            var3_7 = false;
            do {
                if (!var13_13.hasNext()) ** GOTO lbl47
                var14_14 = (Map.Entry)var13_13.next();
                var5_8 = ((StringIntInt)var14_14.getValue()).getStartIndex();
                var7_10 = ((StringIntInt)var14_14.getValue()).getEndIndex();
                var2_6 = var3_7;
                if (var7_10 < var0.length()) {
                    var8_11 = var0.charAt(var7_10);
                    if (var8_11 >= '0' && var8_11 <= '9' || var8_11 >= 'a' && var8_11 <= 'z' || var8_11 >= 'A' && var8_11 <= 'Z') {
                        var2_6 = true;
                    } else {
                        var2_6 = var3_7;
                        if (var8_11 == '.') {
                            var2_6 = var3_7;
                            if (var7_10 + 1 < var0.length()) {
                                var8_11 = var0.charAt(var7_10 + 1);
                                var2_6 = var3_7;
                                if (var8_11 >= '0') {
                                    var2_6 = var3_7;
                                    if (var8_11 <= '9') {
                                        var2_6 = true;
                                    }
                                }
                            }
                        }
                    }
                }
                if (!var2_6) ** GOTO lbl44
                var5_8 = var10_3.size() - 1;
                do {
                    var3_7 = var2_6;
                    if (var5_8 > var6_9) {
                        var10_3.remove(var5_8);
                        --var5_8;
                        continue;
                    }
                    ** GOTO lbl47
                    break;
                } while (true);
lbl44: // 1 sources:
                var15_15 = var0.substring(var5_8, var7_10);
                if (var15_15.equals((Object)var1_1) && var12_12.knowledge.size() == 1) {
                    var3_7 = true;
lbl47: // 3 sources:
                    if (!var3_7 && var12_12.knowledge.size() == 1) {
                        var13_13 = new EntityInfo();
                        var13_13.setTarget(var1_1);
                        var13_13.noNomal();
                        var13_13.setEntityType(EntityType.SPECIALENTITY);
                        var13_13.setGroupEntity(String.valueOf((Object)String.valueOf((int)var4_5)) + "_" + var12_12.getTask().toLowerCase());
                        var13_13.setRemark("sendtonumber");
                        var13_13.setConfidence(var12_12.getPattern().score);
                        var13_13.setStartPosition(-1);
                        var13_13.setEndPosition(-1);
                        var9_2.add(var13_13);
                    }
                    ++var4_5;
                    continue block0;
                }
                var16_16 = new EntityInfo();
                var16_16.setTarget(var15_15);
                var16_16.noNomal();
                var16_16.setEntityType(EntityType.SPECIALENTITY);
                var16_16.setGroupEntity(String.valueOf((Object)String.valueOf((int)var4_5)) + "_" + var12_12.getTask().toLowerCase());
                var16_16.setRemark(((String)var14_14.getKey()).toLowerCase());
                var16_16.setConfidence(var12_12.getPattern().score);
                var16_16.setStartPosition(((StringIntInt)var14_14.getValue()).getStartIndex());
                var16_16.setEndPosition(((StringIntInt)var14_14.getValue()).getEndIndex());
                var9_2.add(var16_16);
                var3_7 = var2_6;
            } while (true);
            break;
        } while (true);
    }

    public static boolean freeResource() {
        if (!ifInitial) {
            return true;
        }
        phoneNoFrontGuideWordsMaxLength = -1;
        badFrontGuideWordsMaxLength = -1;
        phoneNoFrontGuideWords.clear();
        badFrontGuideWords.clear();
        commonServiceNumber2Name.clear();
        areaCode2numberLength.clear();
        parser = null;
        ifInitial = false;
        return true;
    }

    public static double ifLandLineNo(String string2, String string3) {
        String string4;
        if (!ifInitial) {
            return 0.0;
        }
        int n = string3.length();
        if (n != 7 && n != 8 && n != 11 && n != 12 && n != 15 && n != 16) {
            return 0.0;
        }
        if (n < 10) {
            n = string2.charAt(0);
            if (n < 50 || n > 56) {
                return 0.0;
            }
            return 0.3;
        }
        String string5 = string4 = string3.substring(0, 4);
        if (!areaCode2numberLength.containsKey((Object)string4)) {
            string5 = string3 = string3.substring(0, 3);
            if (!areaCode2numberLength.containsKey((Object)string3)) {
                return 0.0;
            }
        }
        int n2 = string5.length();
        if (n != (Integer)areaCode2numberLength.get((Object)string5) + n2) {
            return 0.1;
        }
        if (string2.contains((CharSequence)(String.valueOf((Object)string5) + "-")) || string2.contains((CharSequence)(String.valueOf((Object)string5) + "\u2014"))) {
            return 1.0;
        }
        return 0.9;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean ifMobileNo(String string2) {
        if (!ifInitial || !patFormobileNo_full.matcher((CharSequence)string2).find() || string2.equals((Object)"13800138000")) {
            return false;
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static double ifServiceNo(String string2, String string3) {
        double d2;
        if (!ifInitial) return 0.0;
        int n = string3.length();
        if (n < 10) {
            if (n == 3) {
                if (!commonServiceNumber2Name.containsKey((Object)string3) || string2.length() != 3) return 0.0;
                return 1.0;
            } else {
                if (string3.charAt(0) != '1' && string3.charAt(0) != '9' || n <= 7 && string2.length() != n) return 0.0;
                return 0.3;
            }
        }
        String string4 = string3.substring(0, 3);
        if (!areaCode2numberLength.containsKey((Object)string4)) return 0.0;
        if (string4.charAt(0) >= '2' && string4.charAt(0) <= '8' && string4.charAt(1) == '0' && string4.charAt(2) == '0') {
            d2 = 0.8;
        } else {
            if (string4.charAt(0) == '0') {
                n = string2.indexOf(string4);
                if (n <= 0) return 0.0;
                {
                    int n2 = n + 3;
                    if (string2.charAt(n2 + 3) != '-') {
                        n = n2;
                        if (string2.charAt(n2 + 3) != '\u2014') return PhoneNumberRecognition.ifServiceNo(string2.substring(n), string3.substring(3));
                    }
                    n = n2 + 1;
                    return PhoneNumberRecognition.ifServiceNo(string2.substring(n), string3.substring(3));
                }
            }
            if (string4.charAt(0) != '1' && string4.charAt(0) != '9') {
                return 0.0;
            }
            d2 = 0.0;
        }
        int n3 = string4.length();
        if (n != (Integer)areaCode2numberLength.get((Object)string4) + n3) {
            return d2;
        }
        if (string2.contains((CharSequence)(String.valueOf((Object)string4) + "-")) || string2.contains((CharSequence)(String.valueOf((Object)string4) + "\u2014"))) return 1.0;
        return d2;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static boolean initial() throws IOException {
        if (PhoneNumberRecognition.ifInitial) {
            return true;
        }
        PhoneNumberRecognition.mobileNoPrefixFileName = String.valueOf((Object)SMSUnderstand.dictionaryPath) + "/mobileNoPrefix.txt";
        PhoneNumberRecognition.phoneNoFrontGuideWordsFileName = String.valueOf((Object)SMSUnderstand.dictionaryPath) + "/phoneNoFrontGuideWords.txt";
        PhoneNumberRecognition.areaCode2numberLengthFileName = String.valueOf((Object)SMSUnderstand.dictionaryPath) + "/areaCode2numberLength.txt";
        PhoneNumberRecognition.commonServiceNumberFileName = String.valueOf((Object)SMSUnderstand.dictionaryPath) + "/commonServiceNumber.txt";
        PhoneNumberRecognition.badFrontGuideWordsFileName = String.valueOf((Object)SMSUnderstand.dictionaryPath) + "/badPhoneNoFrontGuideWords.txt";
        try {}
        catch (IOException var2_2) {
            return false;
        }
        var3 = FileOperator.readFile(PhoneNumberRecognition.mobileNoPrefixFileName);
        var2_1 = new StringBuffer();
        var3 = var3.iterator();
        block2 : do lbl-1000: // 3 sources:
        {
            if (!var3.hasNext()) ** GOTO lbl20
            var4_5 = (String[])var3.next();
            if (var4_5.startsWith("//")) ** GOTO lbl-1000
            ** GOTO lbl42
lbl20: // 1 sources:
            PhoneNumberRecognition.mobileNoRegex = PhoneNumberRecognition.mobileNoRegex.replace((CharSequence)"mobileNoPrefix", (CharSequence)var2_1.toString());
            PhoneNumberRecognition.patFormobileNo_full = Pattern.compile((String)("^" + PhoneNumberRecognition.mobileNoRegex + "$"));
            PhoneNumberRecognition.phoneNoFrontGuideWordsMaxLength = FileOperator.readDic2Set(PhoneNumberRecognition.phoneNoFrontGuideWordsFileName, PhoneNumberRecognition.phoneNoFrontGuideWords);
            PhoneNumberRecognition.badFrontGuideWordsMaxLength = FileOperator.readDic2Set(PhoneNumberRecognition.badFrontGuideWordsFileName, PhoneNumberRecognition.badFrontGuideWords);
            var2_1 = FileOperator.readFile(PhoneNumberRecognition.areaCode2numberLengthFileName).iterator();
            do {
                if (!var2_1.hasNext()) {
                    PhoneNumberRecognition.commonServiceNumber2Name = FileOperator.readToMapChangeCol(PhoneNumberRecognition.commonServiceNumberFileName);
                    PhoneNumberRecognition.parser = new Parser(String.valueOf((Object)SMSUnderstand.dictionaryPath) + "/patterns/SendTo.pattern");
                    PhoneNumberRecognition.ifInitial = true;
                    return true;
                }
                var3 = ((String)var2_1.next()).trim();
                if (var3.startsWith("//") || (var3 = var3.split("\t")).length < 2) continue;
                var0_3 = 7;
                if (var3.length > 2) {
                    var0_3 = Integer.parseInt((String)var3[2]);
                }
                if (!PhoneNumberRecognition.areaCode2numberLength.containsKey((Object)var3[1])) {
                    PhoneNumberRecognition.areaCode2numberLength.put((Object)var3[1], (Object)var0_3);
                    continue;
                }
                if ((Integer)PhoneNumberRecognition.areaCode2numberLength.get((Object)var3[1]) >= var0_3) continue;
                PhoneNumberRecognition.areaCode2numberLength.put((Object)var3[1], (Object)var0_3);
            } while (true);
lbl42: // 1 sources:
            var4_5 = var4_5.split("\\s");
            var1_4 = var4_5.length;
            var0_3 = 0;
            do {
                if (var0_3 >= var1_4) continue block2;
                var5_6 = var4_5[var0_3];
                if (!var4_5.equals((Object)"")) {
                    if (var2_1.length() > 0) {
                        var2_1.append('|').append(var5_6);
                    } else {
                        var2_1.append(var5_6);
                    }
                }
                ++var0_3;
            } while (true);
            break;
        } while (true);
    }

    public static String isPhoneNumber(String string2, String string3) {
        int n = StringProcess.getNumberCount(string3);
        if (n == 4) {
            return "\u975e\u7535\u8bdd\u53f7\u7801";
        }
        if (n != string3.length()) {
            return "\u975e\u7535\u8bdd\u53f7\u7801";
        }
        if (string3.length() < 3 || string3.length() > 16) {
            return "\u975e\u7535\u8bdd\u53f7\u7801";
        }
        if (PhoneNumberRecognition.ifMobileNo(string2)) {
            return "\u79fb\u52a8\u7535\u8bdd";
        }
        if (PhoneNumberRecognition.ifLandLineNo(string2, string3) > 0.2) {
            return "\u5ea7\u673a\u7535\u8bdd";
        }
        if (PhoneNumberRecognition.ifServiceNo(string2, string3) > 0.2) {
            return "\u670d\u52a1\u7535\u8bdd";
        }
        return "\u975e\u7535\u8bdd\u53f7\u7801";
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static double isRightNumber(EntityInfo var0, String var1_1) {
        if (!PhoneNumberRecognition.ifInitial) {
            return 0.0;
        }
        var6_3 = var0.getTarget_nomal().length();
        if (var6_3 == 4) {
            return 0.0;
        }
        if (var0.getNumberCount() != var6_3) {
            return 0.0;
        }
        if (var6_3 < 3) return 0.0;
        if (var6_3 > 16) {
            return 0.0;
        }
        if (!PhoneNumberRecognition.ifMobileNo(var0.getTarget_nomal())) ** GOTO lbl15
        var2_4 = 1.0;
        var9_5 = "\u79fb\u52a8\u7535\u8bdd";
        ** GOTO lbl26
lbl15: // 1 sources:
        var2_4 = PhoneNumberRecognition.ifServiceNo(var0.getTarget(), var0.getTarget_nomal());
        if (var2_4 >= 0.3) ** GOTO lbl22
        var4_2 = PhoneNumberRecognition.ifLandLineNo(var0.getTarget(), var0.getTarget_nomal());
        if (var2_4 >= var4_2 || var4_2 <= 0.3) ** GOTO lbl24
        var9_5 = "\u5ea7\u673a\u7535\u8bdd";
        var2_4 = var4_2;
        ** GOTO lbl26
lbl22: // 1 sources:
        var9_5 = "\u670d\u52a1\u7535\u8bdd";
        ** GOTO lbl26
lbl24: // 1 sources:
        var2_4 = -1.0;
        var9_5 = "";
lbl26: // 4 sources:
        var4_2 = var2_4;
        if (var2_4 <= 0.0) return var4_2;
        var7_7 = var8_6 = var0.getStartPosition();
        var4_2 = var2_4;
        if (var8_6 > 0) {
            block15 : {
                if (var1_1.charAt(var8_6 - 1) != '\uff1a' && var1_1.charAt(var8_6 - 1) != ':' && var1_1.charAt(var8_6 - 1) != '\u662f') {
                    var6_3 = var8_6;
                    if (var1_1.charAt(var8_6 - 1) != '\u4e3a') break block15;
                }
                var6_3 = var8_6 - 1;
            }
            var7_7 = StringProcess.EndWithDicWithVagueDistance(var1_1.substring(0, var6_3), PhoneNumberRecognition.phoneNoFrontGuideWords, PhoneNumberRecognition.phoneNoFrontGuideWordsMaxLength, PhoneNumberRecognition.maxVagueDistance);
            var4_2 = var2_4;
            if (var7_7 >= 0) {
                var4_2 = var2_4 * (1.5 + (double)PhoneNumberRecognition.maxVagueDistance - (double)var7_7);
            }
            var7_7 = var6_3;
            if (StringProcess.EndWithDicWithVagueDistance(var1_1.substring(0, var6_3), PhoneNumberRecognition.badFrontGuideWords, PhoneNumberRecognition.badFrontGuideWordsMaxLength, 1) >= 0) {
                var4_2 = 0.0;
                var7_7 = var6_3;
            }
        }
        if (var0.getEndPosition() != var1_1.length()) ** GOTO lbl47
        var2_4 = var4_2 * 2.0;
        ** GOTO lbl59
lbl47: // 1 sources:
        if (var1_1.charAt(var1_1.length() - 1) != '\u3011' && var1_1.charAt(var1_1.length() - 1) != ']') ** GOTO lbl-1000
        var8_6 = var0.getEndPosition();
        if (var8_6 == var1_1.length() - 1 && var7_7 > 0 && (var1_1.charAt(var7_7 - 1) == '\u3010' || var1_1.charAt(var7_7 - 1) == '[')) {
            var2_4 = var4_2 * 3.0;
        } else {
            var6_3 = var7_7 = var1_1.lastIndexOf(12304);
            if (var7_7 < 0) {
                var6_3 = var1_1.lastIndexOf(91);
            }
            if (var6_3 > 0 && var8_6 == var6_3) {
                var2_4 = var4_2 * 2.5;
            } else lbl-1000: // 2 sources:
            {
                var2_4 = var4_2;
            }
        }
lbl59: // 4 sources:
        var4_2 = var2_4;
        if (var2_4 > 1.0) {
            var4_2 = 1.0;
        }
        var0.setRemark(var9_5);
        return var4_2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String nomalPhoneNumber(String string2) {
        if (string2 == null) {
            return "";
        }
        Matcher matcher = phoneNumberNomal.matcher((CharSequence)string2);
        String string3 = string2;
        if (!matcher.find()) return string3;
        string3 = string2;
        if (matcher.groupCount() < 1) return string3;
        return string2.substring(matcher.group(1).length());
    }
}

