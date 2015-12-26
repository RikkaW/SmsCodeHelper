/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 *  java.util.HashSet
 */
package com.xiaomi.smsunderstand.task;

import com.xiaomi.common.FileOperator;
import com.xiaomi.common.Log;
import com.xiaomi.common.StringInt;
import com.xiaomi.common.StringProcess;
import com.xiaomi.smsunderstand.EntityInfo;
import com.xiaomi.smsunderstand.SMSUnderstand;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class BankCardNumberRecognition {
    private static HashSet<String> allBankNames;
    private static int allBankNamesMaxLength;
    private static String bankCardBinNumberPrefixFileName;
    private static String bankCardNoFrontGuideFileName;
    private static HashSet<String> bankCardNoFrontGuideWords;
    private static int bankCardNoFrontGuideWordsMaxLength;
    private static HashMap<String, String> bankName2NormalizationBankName;
    private static String bankNamesFileName;
    private static boolean ifInitial;
    private static HashMap<String, String> lengthAndPrefixIndex2BankName;

    static {
        ifInitial = false;
        bankCardNoFrontGuideWords = new HashSet();
        bankCardNoFrontGuideWordsMaxLength = -1;
        bankCardNoFrontGuideFileName = null;
        bankCardBinNumberPrefixFileName = null;
        bankNamesFileName = null;
        lengthAndPrefixIndex2BankName = new HashMap();
        allBankNames = new HashSet();
        allBankNamesMaxLength = -1;
        bankName2NormalizationBankName = new HashMap();
    }

    public static boolean freeResource() {
        if (!ifInitial) {
            return true;
        }
        bankCardNoFrontGuideWordsMaxLength = -1;
        allBankNamesMaxLength = -1;
        bankCardNoFrontGuideWords.clear();
        lengthAndPrefixIndex2BankName.clear();
        allBankNames.clear();
        bankName2NormalizationBankName.clear();
        ifInitial = false;
        return true;
    }

    private static String getNormalizationName(String string2) {
        if (bankName2NormalizationBankName.containsKey((Object)string2)) {
            return (String)bankName2NormalizationBankName.get((Object)string2);
        }
        return string2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean initial() throws IOException {
        if (ifInitial) {
            return true;
        }
        bankCardNoFrontGuideFileName = String.valueOf((Object)SMSUnderstand.dictionaryPath) + "/bankCardNoFrontGuide.txt";
        bankCardBinNumberPrefixFileName = String.valueOf((Object)SMSUnderstand.dictionaryPath) + "/bankCardBinNumberPrefix.txt";
        bankNamesFileName = String.valueOf((Object)SMSUnderstand.dictionaryPath) + "/bankNames.txt";
        bankCardNoFrontGuideWordsMaxLength = FileOperator.readDic2Set(bankCardNoFrontGuideFileName, bankCardNoFrontGuideWords);
        BankCardNumberRecognition.readBankCardBinNumberPrefix();
        Iterator<String> iterator = FileOperator.readFile(bankNamesFileName).iterator();
        block0 : do {
            if (!iterator.hasNext()) {
                ifInitial = true;
                return true;
            }
            String[] arrstring = iterator.next().split("\\t");
            String string2 = null;
            int n = 0;
            do {
                if (n >= arrstring.length) continue block0;
                String string3 = string2;
                if (!arrstring[n].equals((Object)"")) {
                    String string4 = arrstring[n].toLowerCase();
                    allBankNames.add((Object)string4);
                    if (string4.length() > allBankNamesMaxLength) {
                        allBankNamesMaxLength = string4.length();
                    }
                    if (n == 0) {
                        string3 = string4;
                    } else {
                        string3 = string2;
                        if (n > 0) {
                            string3 = string2;
                            if (string2 != null) {
                                bankName2NormalizationBankName.put((Object)string4, (Object)string2);
                                string3 = string2;
                            }
                        }
                    }
                }
                ++n;
                string2 = string3;
            } while (true);
            break;
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static double isRightNumber(EntityInfo var0, String var1_1) {
        block25 : {
            var10_2 = 0;
            if (!BankCardNumberRecognition.ifInitial) {
                return 0.0;
            }
            var11_4 = var0.getNumberCount();
            if (var11_4 != var0.getTarget_nomal().length()) {
                return 0.0;
            }
            if (var11_4 < 15) return 0.0;
            if (var11_4 > 19) {
                return 0.0;
            }
            var13_5 = "";
            var9_6 = 6;
            var8_7 = 0;
            do {
                if (var9_6 >= 2 && var9_6 <= 10) ** GOTO lbl17
                var2_10 = 0.1;
                ** GOTO lbl42
lbl17: // 1 sources:
                var14_8 = var0.getTarget_nomal().substring(0, var9_6);
                var15_9 = String.valueOf((int)var11_4) + "$" + (String)var14_8;
                if (!BankCardNumberRecognition.lengthAndPrefixIndex2BankName.containsKey((Object)var15_9)) ** GOTO lbl23
                var13_5 = (String)BankCardNumberRecognition.lengthAndPrefixIndex2BankName.get((Object)var15_9);
                var2_10 = (double)var9_6 * 0.15;
                ** GOTO lbl42
lbl23: // 1 sources:
                var15_9 = String.valueOf((int)(var11_4 + 1)) + "$" + (String)var14_8;
                if (!BankCardNumberRecognition.lengthAndPrefixIndex2BankName.containsKey((Object)var15_9)) ** GOTO lbl28
                var13_5 = (String)BankCardNumberRecognition.lengthAndPrefixIndex2BankName.get((Object)var15_9);
                var2_10 = (double)var9_6 * 0.13;
                ** GOTO lbl42
lbl28: // 1 sources:
                var15_9 = String.valueOf((int)(var11_4 - 1)) + "$" + (String)var14_8;
                if (!BankCardNumberRecognition.lengthAndPrefixIndex2BankName.containsKey((Object)var15_9)) ** GOTO lbl33
                var13_5 = (String)BankCardNumberRecognition.lengthAndPrefixIndex2BankName.get((Object)var15_9);
                var2_10 = (double)var9_6 * 0.13;
                ** GOTO lbl42
lbl33: // 1 sources:
                var15_9 = String.valueOf((int)(var11_4 + 2)) + "$" + (String)var14_8;
                if (!BankCardNumberRecognition.lengthAndPrefixIndex2BankName.containsKey((Object)var15_9)) ** GOTO lbl38
                var13_5 = (String)BankCardNumberRecognition.lengthAndPrefixIndex2BankName.get((Object)var15_9);
                var2_10 = 0.11 * (double)var9_6;
                ** GOTO lbl42
lbl38: // 1 sources:
                var14_8 = String.valueOf((int)(var11_4 - 2)) + "$" + (String)var14_8;
                if (!BankCardNumberRecognition.lengthAndPrefixIndex2BankName.containsKey(var14_8)) ** GOTO lbl58
                var13_5 = (String)BankCardNumberRecognition.lengthAndPrefixIndex2BankName.get(var14_8);
                var2_10 = 0.11 * (double)var9_6;
lbl42: // 6 sources:
                var8_7 = var2_10 > 0.1 ? 1 : 0;
                var11_4 = var0.getStartPosition();
                var6_11 = var2_10;
                if (var11_4 > 0) {
                    block24 : {
                        if (var1_1.charAt(var11_4 - 1) != '\uff1a' && var1_1.charAt(var11_4 - 1) != ':' && var1_1.charAt(var11_4 - 1) != '\u662f') {
                            var9_6 = var11_4;
                            if (var1_1.charAt(var11_4 - 1) != '\u4e3a') break block24;
                        }
                        var9_6 = var11_4 - 1;
                    }
                    var4_3 = var2_10;
                    if (StringProcess.EndWithDic(var1_1.substring(0, var9_6), BankCardNumberRecognition.bankCardNoFrontGuideWords, BankCardNumberRecognition.bankCardNoFrontGuideWordsMaxLength) != StringProcess.ContainEnum.None) {
                        var4_3 = var2_10 * 2.5;
                    }
                    var6_11 = var4_3;
                    if (var8_7 == 0) {
                        break;
                    }
                }
                ** GOTO lbl-1000
lbl58: // 1 sources:
                var8_7 = var8_7 > 0 ? (var8_7 *= -1) : var8_7 * -1 + 1;
                var9_6 = var8_7 + 6;
            } while (true);
            var14_8 = StringProcess.findAllResultsFromList(var1_1, BankCardNumberRecognition.allBankNames, BankCardNumberRecognition.allBankNamesMaxLength);
            var9_6 = -1;
            var11_4 = Integer.MAX_VALUE;
            var8_7 = var10_2;
            do {
                if (var8_7 < var14_8.size()) ** GOTO lbl69
                var12_12 = var9_6;
                ** GOTO lbl86
lbl69: // 1 sources:
                var10_2 = ((StringInt)var14_8.get(var8_7)).getNum();
                if (var10_2 < var0.getStartPosition()) {
                    var10_2 = var0.getStartPosition() - var10_2 - ((StringInt)var14_8.get(var8_7)).getName().length();
                } else if (var10_2 >= var0.getEndPosition()) {
                    var10_2 = var0.getEndPosition() - var10_2;
                } else {
                    Log.i("BankCardNumberRecognition", "ifRightNumber Error:\t" + var1_1);
                }
                if (var10_2 <= 0) {
                    if (var9_6 >= 0) {
                        var12_12 = var9_6;
                        if (var11_4 - var10_2 * -1 > 5) {
                            var12_12 = var9_6;
                            if ((double)var11_4 / ((double)(var10_2 * -1) + 0.01) >= 2.0) {
                                var12_12 = var8_7;
                            }
                        }
                    } else {
                        var12_12 = var8_7;
                    }
lbl86: // 3 sources:
                    if (var12_12 < 0) break;
                    var2_10 = var4_3 * 4.0;
                    var1_1 = ((StringInt)var14_8.get(var12_12)).getName();
                    break block25;
                }
                if (var11_4 > var10_2) {
                    var9_6 = var8_7;
                } else {
                    var10_2 = var11_4;
                }
                ++var8_7;
                var11_4 = var10_2;
            } while (true);
            var6_11 = var4_3;
            if (var14_8.size() == 0) {
                var1_1 = var13_5;
                var2_10 = 0.01;
            } else lbl-1000: // 2 sources:
            {
                var1_1 = var13_5;
                var2_10 = var6_11;
            }
        }
        if (!var1_1.equals((Object)"")) {
            var0.setRemark(BankCardNumberRecognition.getNormalizationName(var1_1));
        }
        var4_3 = var2_10;
        if (var2_10 < 1.0) return var4_3;
        return 1.0;
    }

    private static boolean readBankCardBinNumberPrefix() throws IOException {
        Iterator<String> iterator = FileOperator.readFile(bankCardBinNumberPrefixFileName).iterator();
        while (iterator.hasNext()) {
            String string2 = iterator.next();
            String[] arrstring = string2.split("\t");
            if (arrstring.length < 3) continue;
            try {
                lengthAndPrefixIndex2BankName.put((Object)(String.valueOf((Object)arrstring[arrstring.length - 2].trim()) + "$" + arrstring[arrstring.length - 1]), (Object)arrstring[0].trim());
                continue;
            }
            catch (Exception var2_3) {
                Log.i("BankCardNumberRecognition", "readBankCardBinNumberPrefix Error:\t" + string2);
                continue;
            }
            break;
        }
        return true;
    }
}

