/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.HashMap
 *  java.util.HashSet
 *  java.util.regex.Pattern
 */
package com.xiaomi.smsunderstand.task;

import com.xiaomi.common.FileOperator;
import com.xiaomi.common.StringInt;
import com.xiaomi.common.StringProcess;
import com.xiaomi.smsunderstand.EntityInfo;
import com.xiaomi.smsunderstand.SMSUnderstand;
import com.xiaomi.smsunderstand.task.Express;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class ExpressNumberRecognition {
    private static String cleanNumberRegex;
    private static HashMap<String, String> expName2NormalizationexpName;
    private static HashMap<String, Integer> expName2NumberLength;
    private static HashSet<String> expNoFrontGuideWords;
    private static int expNoFrontGuideWordsMaxLength;
    private static HashSet<String> expressName;
    private static String expressNameFileName;
    private static int expressNameMaxLength;
    private static String expressNoFrontGuideWordsFileName;
    private static boolean ifInitial;
    private static int maxVagueDistance;
    private static Pattern patForCleanNum;

    static {
        expressName = new HashSet();
        expressNameMaxLength = -1;
        ifInitial = false;
        expressNameFileName = null;
        expressNoFrontGuideWordsFileName = null;
        maxVagueDistance = 10;
        cleanNumberRegex = "(\\\\n)|(\\\\r)|(\\\\t)";
        patForCleanNum = Pattern.compile((String)"[0-9a-zA-Z]*[0-9]{5,}[0-9a-zA-Z]*");
        expNoFrontGuideWords = new HashSet();
        expNoFrontGuideWordsMaxLength = -1;
        expName2NormalizationexpName = new HashMap();
        expName2NumberLength = new HashMap();
    }

    public static List<StringInt> findExpNamesFromText(String string2) {
        return StringProcess.findAllResultsFromList(string2, expressName, expressNameMaxLength);
    }

    public static boolean freeResource() {
        if (!ifInitial) {
            return true;
        }
        expressNameMaxLength = -1;
        expNoFrontGuideWordsMaxLength = -1;
        expressName.clear();
        expNoFrontGuideWords.clear();
        expName2NormalizationexpName.clear();
        expName2NumberLength.clear();
        Express.clear();
        ifInitial = false;
        return true;
    }

    private static String getNormalizationName(String string2) {
        if (expName2NormalizationexpName.containsKey((Object)string2)) {
            return (String)expName2NormalizationexpName.get((Object)string2);
        }
        return string2;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static boolean initial() throws IOException {
        if (ExpressNumberRecognition.ifInitial) {
            return true;
        }
        ExpressNumberRecognition.expressNameFileName = String.valueOf((Object)SMSUnderstand.dictionaryPath) + "/expressName.txt";
        ExpressNumberRecognition.expressNoFrontGuideWordsFileName = String.valueOf((Object)SMSUnderstand.dictionaryPath) + "/expressNoFrontGuideWords.txt";
        var6 = FileOperator.readFile(ExpressNumberRecognition.expressNameFileName);
        var9_1 = new ArrayList();
        var10_2 = var6.iterator();
        block0 : do lbl-1000: // 3 sources:
        {
            if (!var10_2.hasNext()) {
                var6 = var9_1.iterator();
                do {
                    if (!var6.hasNext()) {
                        ExpressNumberRecognition.expNoFrontGuideWordsMaxLength = FileOperator.readDic2Set(ExpressNumberRecognition.expressNoFrontGuideWordsFileName, ExpressNumberRecognition.expNoFrontGuideWords);
                        ExpressNumberRecognition.ifInitial = true;
                        return true;
                    }
                    var7_9 = (String)var6.next();
                    ExpressNumberRecognition.expressName.add(var7_9);
                    if (var7_9.length() <= ExpressNumberRecognition.expressNameMaxLength) continue;
                    ExpressNumberRecognition.expressNameMaxLength = var7_9.length();
                } while (true);
            }
            var11_11 = var10_2.next().split("\\t");
            var0_3 = 0;
            var4_7 = var9_1.size();
            var6 = null;
            var1_4 = 0;
            do {
                if (var1_4 < var11_11.length) ** GOTO lbl36
                if (var0_3 == 0) ** GOTO lbl-1000
                var1_4 = var4_7;
                do {
                    if (var1_4 >= var9_1.size()) continue block0;
                    if (!ExpressNumberRecognition.expName2NumberLength.containsKey(var9_1.get(var1_4))) {
                        ExpressNumberRecognition.expName2NumberLength.put((Object)((String)var9_1.get(var1_4)), (Object)var0_3);
                    }
                    ++var1_4;
                } while (true);
lbl36: // 1 sources:
                var3_6 = var11_11[var1_4].indexOf("---");
                if (var3_6 < 0 && !var11_11[var1_4].equals((Object)"")) {
                    var8_10 = var11_11[var1_4].toLowerCase();
                    var9_1.add(var8_10);
                    if (var1_4 == 0) {
                        var7_9 = var8_10;
                        var2_5 = var0_3;
                    } else {
                        var2_5 = var0_3;
                        var7_9 = var6;
                        if (var1_4 > 0) {
                            var2_5 = var0_3;
                            var7_9 = var6;
                            if (var6 != null) {
                                ExpressNumberRecognition.expName2NormalizationexpName.put((Object)var8_10, var6);
                                var2_5 = var0_3;
                                var7_9 = var6;
                            }
                        }
                    }
                } else {
                    var2_5 = var0_3;
                    var7_9 = var6;
                    if (var3_6 > 0) {
                        var8_10 = var11_11[var1_4].substring("---".length() + var3_6).trim().split("\\^");
                        var5_8 = var8_10.length;
                        var3_6 = 0;
                        do {
                            var2_5 = var0_3;
                            var7_9 = var6;
                            if (var3_6 >= var5_8) break;
                            var7_9 = var8_10[var3_6];
                            var2_5 = var0_3;
                            if (!var7_9.equals((Object)"")) {
                                var2_5 = var0_3 | 1 << Integer.parseInt((String)var7_9);
                            }
                            ++var3_6;
                            var0_3 = var2_5;
                        } while (true);
                    }
                }
                ++var1_4;
                var0_3 = var2_5;
                var6 = var7_9;
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
        if (!ExpressNumberRecognition.ifInitial) {
            return 0.0;
        }
        if (!var0.getTarget().toLowerCase().startsWith(var0.getRemark())) ** GOTO lbl32
        var6_3 = var0.getEngCharCount();
        var8_4 = var0.getNumberCount();
        var7_5 = 0;
        do {
            if (var7_5 >= var0.getRemark().length()) {
                var0.setEngCharCount(var6_3);
                var0.setNumberCount(var8_4);
                break;
            }
            var11_8 = StringProcess.getASCType(var0.getRemark().charAt(var7_5));
            if (var11_8 != StringProcess.ASCType.Number) ** GOTO lbl17
            var9_6 = var8_4 - 1;
            var10_7 = var6_3;
            ** GOTO lbl23
lbl17: // 1 sources:
            if (var11_8 == StringProcess.ASCType.EnglishLowerCase) ** GOTO lbl-1000
            var9_6 = var8_4;
            var10_7 = var6_3;
            if (var11_8 == StringProcess.ASCType.EnglishUpper) lbl-1000: // 2 sources:
            {
                var10_7 = var6_3 - 1;
                var9_6 = var8_4;
            }
lbl23: // 4 sources:
            ++var7_5;
            var8_4 = var9_6;
            var6_3 = var10_7;
        } while (true);
        for (var6_3 = var0.getRemark().length(); var6_3 < var0.getTarget().length() && ((var7_5 = (int)var0.getTarget().charAt(var6_3)) == 58 || var7_5 == 65306); ++var6_3) {
        }
        var0.setTarget(var0.getTarget().substring(var6_3));
        var0.setStartPosition(var6_3 + var0.getStartPosition());
        var0.setTarget_nomal(var0.getTarget());
lbl32: // 2 sources:
        if ((var6_3 = var1_1.indexOf(var0.getRemark())) < 0 || var1_1.length() <= (var6_3 += var0.getRemark().length())) ** GOTO lbl-1000
        if (var1_1.charAt(var6_3) != '\uff1a' && var1_1.charAt(var6_3) != ':' && var1_1.charAt(var6_3) != '\u662f' && var1_1.charAt(var6_3) != '\u4e3a') ** GOTO lbl37
        if (var6_3 + 1 == var0.getStartPosition()) ** GOTO lbl-1000
        var6_3 = 1;
        ** GOTO lbl41
lbl37: // 1 sources:
        if (StringProcess.getASCType(var1_1.charAt(var6_3)) == StringProcess.ASCType.Number && var6_3 != var0.getStartPosition()) {
            var6_3 = 1;
        } else lbl-1000: // 3 sources:
        {
            var6_3 = 0;
        }
lbl41: // 3 sources:
        var0.setRemark(ExpressNumberRecognition.getNormalizationName(var0.getRemark()));
        if (var0.getStartPosition() == 0) {
            return 0.0;
        }
        if (var0.getTarget().length() < 6) {
            return 0.0;
        }
        if (var0.getTarget().length() != var0.getEngCharCount() + var0.getNumberCount()) {
            return 0.0;
        }
        var4_2 = -1.0;
        var8_4 = var0.getStartPosition();
        var2_9 = var4_2;
        if (var8_4 > 0) {
            block17 : {
                if (var1_1.charAt(var8_4 - 1) != '\uff1a' && var1_1.charAt(var8_4 - 1) != ':' && var1_1.charAt(var8_4 - 1) != '\u662f') {
                    var7_5 = var8_4;
                    if (var1_1.charAt(var8_4 - 1) != '\u4e3a') break block17;
                }
                var7_5 = var8_4 - 1;
            }
            var7_5 = StringProcess.EndWithDicWithVagueDistance(var1_1.substring(0, var7_5), ExpressNumberRecognition.expNoFrontGuideWords, ExpressNumberRecognition.expNoFrontGuideWordsMaxLength, ExpressNumberRecognition.maxVagueDistance);
            var2_9 = var4_2;
            if (var7_5 >= 0) {
                var2_9 = 1.0 / (double)(ExpressNumberRecognition.maxVagueDistance + 1);
                var2_9 = 1.0 - (double)var7_5 * var2_9;
            }
        }
        if (ExpressNumberRecognition.expName2NumberLength.containsKey((Object)var0.getRemark())) {
            if (((Integer)ExpressNumberRecognition.expName2NumberLength.get((Object)var0.getRemark()) & 1 << var0.getTarget_nomal().length()) == 0) {
                return 0.0;
            }
            var2_9 = var2_9 > 0.5 ? 1.0 : 2.0 * var2_9;
        }
        if (var0.getRemark().contains((CharSequence)"\u4eac\u4e1c")) {
            var7_5 = var1_1.indexOf("\u60a8\u7684\u8ba2\u5355");
            var8_4 = var1_1.indexOf("\u53d1\u8d27");
            var2_9 = var7_5 < var0.getStartPosition() && var8_4 > var0.getEndPosition() ? 1.0 : 0.0;
        }
        var4_2 = var2_9;
        if (var6_3 == 0) return var4_2;
        return var2_9 * 0.8;
    }
}

