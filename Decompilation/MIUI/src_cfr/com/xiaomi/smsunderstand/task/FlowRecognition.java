/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Double
 *  java.lang.Integer
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.HashMap
 *  java.util.HashSet
 *  java.util.Map$Entry
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
package com.xiaomi.smsunderstand.task;

import com.xiaomi.common.FileOperator;
import com.xiaomi.common.Log;
import com.xiaomi.common.StringProcess;
import com.xiaomi.nlp.OntologyTaskFrame;
import com.xiaomi.smsunderstand.AttrInfo;
import com.xiaomi.smsunderstand.EntityInfo;
import com.xiaomi.smsunderstand.EntityType;
import com.xiaomi.smsunderstand.OntologyResults;
import com.xiaomi.smsunderstand.SMSUnderstand;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FlowRecognition {
    private static double flowFinish;
    private static List<List> flowForbidWordTable;
    private static String flowForbidWordTableFileName;
    private static Set<String> flowFrontGuideWords;
    private static String flowFrontGuideWordsFileName;
    private static int flowFrontGuideWordsMaxLength;
    private static List<List> flowKeyWordAndScoreTable;
    private static Map<String, Double> flowKeyWordsAndScore;
    private static String flowKeyWordsAndScoreFileName;
    private static int flowKeyWordsAndScoreMaxLength;
    private static Pattern flowPat;
    private static List<List> flowPredictWordTable;
    private static String[] flowTag;
    private static double flowThrAbs;
    private static double flowThrRate;
    private static boolean ifInitial;
    private static double parseError;
    private static String[] units;

    static {
        flowFrontGuideWords = new HashSet();
        flowFrontGuideWordsFileName = null;
        ifInitial = false;
        flowKeyWordsAndScore = new HashMap();
        flowKeyWordsAndScoreFileName = null;
        flowKeyWordAndScoreTable = null;
        flowPredictWordTable = null;
        flowForbidWordTableFileName = null;
        flowForbidWordTable = null;
        flowThrRate = 0.1;
        flowThrAbs = 5.0;
        flowTag = new String[]{"daily_total", "daily_used", "daily_remained", "daily_exceed", "add_total", "add_used", "add_remained", "add_exceed", "leisure_total", "leisure_used", "leisure_remained", "leisure_exceed"};
        flowFinish = Double.MAX_VALUE;
        parseError = -6000000.0;
        units = new String[]{"G", "M", "K"};
        flowPat = Pattern.compile((String)"^[0-9]+((\\.)[0-9]+)?(mb|gb|kb|tb|m|g|k|t)$", (int)2);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static List<EntityInfo> analysisFlowFromSimplePart(List<List<Integer>> var0, int var1_1, String var2_2) {
        block27 : {
            block26 : {
                var13_3 = new ArrayList();
                var8_4 = (Integer)((List)var0.get(0)).get(var1_1);
                var9_5 = (Integer)((List)var0.get(1)).get(var1_1);
                var3_6 = var8_4;
                block4 : do {
                    if (var3_6 > var9_5) {
                        var0 = var13_3.iterator();
                        var3_6 = 0;
                        do {
                            if (!var0.hasNext()) {
                                var0 = var13_3.iterator();
                                break;
                            }
                            var10_11 = (EntityInfo)var0.next();
                            if (!var10_11.getTarget().equalsIgnoreCase("2G") && !var10_11.getTarget().equalsIgnoreCase("3G") && !var10_11.getTarget().equalsIgnoreCase("4G")) continue;
                            ++var3_6;
                        } while (true);
lbl17: // 4 sources:
                        if (!var0.hasNext()) {
                            if (var13_3 == null) return var13_3;
                            FlowRecognition.filtForbidWorsd(var13_3, var2_2, var8_4, var9_5);
                            return var13_3;
                        }
                        var10_11 = (EntityInfo)var0.next();
                        if (var10_11.getTarget().equalsIgnoreCase("2G") || var10_11.getTarget().equalsIgnoreCase("3G") || var10_11.getTarget().equalsIgnoreCase("4G")) {
                            if (var3_6 > 1) {
                                var10_11.setConfidence(var10_11.getConfidence() * 0.3);
                            } else {
                                var10_11.setConfidence(var10_11.getConfidence() * 0.88);
                            }
                        }
                        break block26;
                    }
                    var6_9 = 0;
                    do {
                        if (var6_9 >= FlowRecognition.flowKeyWordAndScoreTable.get(0).size()) {
                            var4_7 = 0;
                            break;
                        }
                        if (var2_2.startsWith((String)FlowRecognition.flowKeyWordAndScoreTable.get(0).get(var6_9), var3_6)) break block4;
                        ++var6_9;
                    } while (true);
lbl36: // 2 sources:
                    do {
                        if (var4_7 != 0) continue block4;
                        ++var3_6;
                        continue block4;
                        break;
                    } while (true);
                    break;
                } while (true);
                var5_8 = 0;
                var11_14 = "";
                var4_7 = var3_6 - 1;
                do lbl-1000: // 2 sources:
                {
                    if (var4_7 < var8_4) ** GOTO lbl51
                    if (var2_2.charAt(var4_7) != ' ' && var2_2.charAt(var4_7) != '\u3000') ** GOTO lbl48
                    ++var5_8;
                    ** GOTO lbl-1000
lbl48: // 1 sources:
                    if (var2_2.charAt(var4_7) != '.' && (var2_2.charAt(var4_7) < '0' || var2_2.charAt(var4_7) > '9')) ** GOTO lbl51
                    var11_14 = String.valueOf((char)var2_2.charAt(var4_7)) + (String)var11_14;
                    ** GOTO lbl-1000
lbl51: // 2 sources:
                    var10_11 = var11_14;
                    if (var11_14.indexOf(46) != var11_14.lastIndexOf(46)) {
                        var10_11 = var11_14.substring(var11_14.indexOf(46) + 1);
                    }
                    var12_15 = var10_11;
                    var11_14 = var10_11;
                    if (var10_11.contains((CharSequence)".")) {
                        var12_15 = var10_11;
                        var11_14 = var10_11;
                        if (var3_6 - var10_11.length() == var8_4) {
                            var11_14 = var10_11;
                            var12_15 = FlowRecognition.brotherFilt(var0, var1_1, (String)var10_11, var2_2);
                        }
                    }
                    var11_14 = var12_15;
                    Double.valueOf((String)var12_15).doubleValue();
                    var11_14 = var12_15;
                    var10_11 = new EntityInfo();
                    var11_14 = var12_15;
                    var10_11.setStartPosition(var3_6 - var12_15.length() - var5_8);
                    var11_14 = var12_15;
                    var10_11.setEndPosition(((String)FlowRecognition.flowKeyWordAndScoreTable.get(0).get(var6_9)).length() + (var3_6 - 1));
                    var11_14 = var12_15;
                    var10_11.setConfidence((Double)FlowRecognition.flowKeyWordAndScoreTable.get(1).get(var6_9));
                    var11_14 = var12_15;
                    var10_11.setTarget(String.valueOf((Object)var12_15) + (String)FlowRecognition.flowKeyWordAndScoreTable.get(0).get(var6_9));
                    var11_14 = var12_15;
                    var10_11.setTarget_nomalFLOW(String.valueOf((Object)var12_15) + FlowRecognition.regularization((String)FlowRecognition.flowKeyWordAndScoreTable.get(0).get(var6_9)));
                    var11_14 = var12_15;
                    var10_11.setEntityType(EntityType.FLOW);
                    var11_14 = var12_15;
                    var10_11.setRemark((String)FlowRecognition.flowKeyWordAndScoreTable.get(0).get(var6_9));
                    var11_14 = var12_15;
                    var10_11.setNumberCount(var12_15.length());
                    var11_14 = var12_15;
                    var10_11.setEngCharCount(((String)FlowRecognition.flowKeyWordAndScoreTable.get(0).get(var6_9)).length());
                    var11_14 = var12_15;
                    var13_3.add(var10_11);
                    ** GOTO lbl-1000
                    break;
                } while (true);
                catch (Exception var10_12) {
                    if (var11_14 == null || var11_14.length() != 0 || !((String)FlowRecognition.flowKeyWordAndScoreTable.get(0).get(var6_9)).contains((CharSequence)")")) lbl-1000: // 4 sources:
                    {
                        do {
                            var3_6 = ((String)FlowRecognition.flowKeyWordAndScoreTable.get(0).get(var6_9)).length() + var3_6;
                            var4_7 = 1;
                            ** continue;
                            break;
                        } while (true);
                    }
                    var5_8 = ((String)FlowRecognition.flowKeyWordAndScoreTable.get(0).get(var6_9)).length();
                    var4_7 = 0;
                    var7_10 = var5_8 + var3_6;
                    do {
                        if (var7_10 <= var9_5) ** GOTO lbl101
                        var5_8 = var4_7;
                        ** GOTO lbl115
lbl101: // 1 sources:
                        if (var2_2.charAt(var7_10) != '.' && (var2_2.charAt(var7_10) < '0' || var2_2.charAt(var7_10) > '9')) ** GOTO lbl105
                        var10_11 = String.valueOf((Object)var11_14) + var2_2.charAt(var7_10);
                        var5_8 = var4_7;
                        ** GOTO lbl132
lbl105: // 1 sources:
                        if (var2_2.charAt(var7_10) < 'a') ** GOTO lbl108
                        var5_8 = var4_7;
                        if (var2_2.charAt(var7_10) <= 'z') ** GOTO lbl115
lbl108: // 2 sources:
                        if (var2_2.charAt(var7_10) >= 'A' && var2_2.charAt(var7_10) <= 'Z') ** GOTO lbl116
                        var5_8 = ++var4_7;
                        if (var4_7 >= 4) ** GOTO lbl115
                        var5_8 = var4_7;
                        var10_11 = var11_14;
                        if (var11_14.length() <= 0) ** GOTO lbl132
                        ** GOTO lbl116
lbl115: // 3 sources:
                        var4_7 = var5_8;
lbl116: // 3 sources:
                        try {
                            Double.valueOf((String)var11_14).doubleValue();
                            var10_11 = new EntityInfo();
                            var10_11.setStartPosition(var3_6);
                            var10_11.setEndPosition(((String)FlowRecognition.flowKeyWordAndScoreTable.get(0).get(var6_9)).length() + var3_6 + var11_14.length() + var4_7 - 1);
                            var10_11.setConfidence((Double)FlowRecognition.flowKeyWordAndScoreTable.get(1).get(var6_9));
                            var10_11.setTarget(var2_2.substring(var10_11.getStartPosition(), var10_11.getEndPosition() + 1));
                            var10_11.setTarget_nomalFLOW(var10_11.getTarget());
                            var10_11.setEntityType(EntityType.FLOW);
                            var10_11.setRemark((String)FlowRecognition.flowKeyWordAndScoreTable.get(0).get(var6_9));
                            var10_11.setNumberCount(var11_14.length());
                            var10_11.setEngCharCount(((String)FlowRecognition.flowKeyWordAndScoreTable.get(0).get(var6_9)).length());
                            var13_3.add(var10_11);
                        }
                        catch (Exception var10_13) {}
                        ** continue;
lbl132: // 2 sources:
                        ++var7_10;
                        var4_7 = var5_8;
                        var11_14 = var10_11;
                    } while (true);
                }
lbl-1000: // 2 sources:
                {
                    --var4_7;
                    ** while (true)
                }
            }
            var11_14 = FlowRecognition.flowPredictWordTable.get(0).iterator();
            do {
                if (!var11_14.hasNext()) {
                    var1_1 = 0;
                } else {
                    var12_15 = var11_14.next();
                    var14_16 = var2_2.substring(var8_4, var10_11.getStartPosition());
                    var1_1 = var14_16.lastIndexOf((String)var12_15);
                    if (var1_1 == -1 || var14_16.length() - var1_1 - ((String)var12_15).length() >= 4) continue;
                    var1_1 = 1;
                }
                break block27;
            } while ((var1_1 = var2_2.substring(var10_11.getEndPosition() + 1, var9_5 + 1).indexOf((String)var12_15)) == -1 || var1_1 >= 4);
            var1_1 = 1;
        }
        if (var1_1 != 0) ** GOTO lbl17
        var10_11.setConfidence(var10_11.getConfidence() * 0.85);
        if (var10_11.getEndPosition() + 1 >= var2_2.length() || !((var1_1 = (int)var2_2.charAt(var10_11.getEndPosition() + 1)) >= 48 && var1_1 <= 57 || var1_1 >= 97 && var1_1 <= 122 || var1_1 >= 65 && var1_1 <= 90 || var1_1 == 47) && var1_1 != 27599) ** GOTO lbl17
        var10_11.setConfidence(var10_11.getConfidence() * 0.3);
        ** GOTO lbl17
    }

    /*
     * Exception decompiling
     */
    private static String brotherFilt(List<List<Integer>> var0, int var1_1, String var2_2, String var3_3) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Statement already marked as first in another block
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.markFirstStatementInBlock(Op03SimpleStatement.java:412)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Misc.markWholeBlock(Misc.java:219)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.ConditionalRewriter.considerAsSimpleIf(ConditionalRewriter.java:619)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.ConditionalRewriter.identifyNonjumpingConditionals(ConditionalRewriter.java:45)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:669)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }

    public static boolean check(String string2) throws IOException {
        if (flowPat.matcher((CharSequence)string2).find()) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static List<List<Integer>> divideWords(String string2, int n, int n2, List<String> list) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ArrayList());
        arrayList.add(new ArrayList());
        int n3 = n;
        do {
            int n4;
            int n5;
            boolean bl;
            block5 : {
                String string3;
                if (n3 > n2 || n > n2) {
                    return arrayList;
                }
                bl = false;
                Iterator<String> iterator = list.iterator();
                do {
                    if (!iterator.hasNext()) {
                        n4 = n;
                        n5 = n3;
                        break block5;
                    }
                    string3 = iterator.next();
                } while (n != n2 && !string2.startsWith(string3, n));
                bl = true;
                if (n - 1 > n3) {
                    ((List)arrayList.get(0)).add(n3);
                    ((List)arrayList.get(1)).add(n - 1);
                }
                n5 = n4 = n + string3.length();
            }
            n3 = n5;
            n = n4;
            if (bl) continue;
            n = n4 + 1;
            n3 = n5;
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static void filtForbidWorsd(List<EntityInfo> var0, String var1_1, int var2_2, int var3_3) {
        var0 = var0.iterator();
        block0 : do {
            if (!var0.hasNext()) {
                return;
            }
            var8_7 = (EntityInfo)var0.next();
            var9_8 = var1_1.substring(var8_7.getStartPosition(), var8_7.getEndPosition() + 1).toUpperCase();
            var7_6 = 0;
            do {
                if (var7_6 >= FlowRecognition.flowForbidWordTable.get(0).size()) continue block0;
                var10_9 = (String)FlowRecognition.flowForbidWordTable.get(0).get(var7_6);
                var11_10 = (String)FlowRecognition.flowForbidWordTable.get(1).get(var7_6);
                var12_11 = (String)FlowRecognition.flowForbidWordTable.get(2).get(var7_6);
                var4_4 = (Double)FlowRecognition.flowForbidWordTable.get(3).get(var7_6);
                if (!var11_10.equals((Object)"end")) ** GOTO lbl18
                if (!var9_8.endsWith(var10_9)) ** GOTO lbl-1000
                var6_5 = true;
                ** GOTO lbl22
lbl18: // 1 sources:
                if (var11_10.equals((Object)"full") && var9_8.equals((Object)var10_9)) {
                    var6_5 = true;
                } else lbl-1000: // 2 sources:
                {
                    var6_5 = false;
                }
lbl22: // 3 sources:
                if (var6_5 && var1_1.substring(Math.max((int)(var8_7.getStartPosition() - var12_11.length() - 5), (int)var2_2), Math.min((int)var3_3, (int)(var8_7.getEndPosition() + var12_11.length() + 4))).contains((CharSequence)var12_11)) {
                    var8_7.setConfidence(var8_7.getConfidence() * var4_4);
                }
                ++var7_6;
            } while (true);
            break;
        } while (true);
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static boolean filtNotFullMsg(String string2) {
        Iterator<String> iterator = FlowRecognition.getSplitTokens(3).iterator();
        do {
            if (iterator.hasNext()) continue;
            if (string2.charAt(1) < '0') return true;
            if (string2.charAt(1) > '9') return true;
            if (string2.charAt(3) < '0') return true;
            if (string2.charAt(3) > '9') return true;
            if (string2.charAt(1) < '0') return false;
            char c2 = string2.charAt(1);
            if (c2 <= '9') return true;
            return false;
        } while (!string2.startsWith(iterator.next()));
        return false;
        catch (Exception exception) {
            Log.println(string2);
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static List<EntityInfo> findKnowledge(String list) throws IOException {
        int n;
        Object object;
        ArrayList arrayList;
        block7 : {
            double d2;
            int n2 = 0;
            if (!ifInitial) {
                FlowRecognition.initial();
            }
            if (!FlowRecognition.filtNotFullMsg((String)((Object)list))) {
                return null;
            }
            object = String.valueOf((Object)list) + "\n";
            arrayList = new ArrayList();
            list = object.contains((CharSequence)"\uff1b") || object.contains((CharSequence)";") || StringProcess.count((String)object, "\r") > 2 || StringProcess.count((String)object, "\n") > 2 || StringProcess.count((String)object, "\\n") > 2 || StringProcess.count((String)object, "\\r") > 2 ? FlowRecognition.getSplitTokens(1) : FlowRecognition.getSplitTokens(2);
            list = FlowRecognition.divideWords((String)object, 0, object.length() - 1, list);
            n = 0;
            do {
                if (n >= ((List)list.get(0)).size()) break;
                arrayList.addAll(FlowRecognition.analysisFlowFromSimplePart(list, n, (String)object));
                ++n;
            } while (true);
            list = arrayList.iterator();
            do {
                if (list.hasNext()) continue;
                n = n2;
                break block7;
            } while (!(object = (EntityInfo)((Object)list.next())).getRemark().equalsIgnoreCase("g") || (d2 = Double.valueOf((String)object.getTarget().substring(0, object.getNumberCount())).doubleValue()) == 2.0 || d2 == 3.0 || d2 == 4.0);
            n = 1;
        }
        list = arrayList.iterator();
        while (list.hasNext()) {
            object = (EntityInfo)list.next();
            if (n == 0 && (object.getTarget().equalsIgnoreCase("2G") || object.getTarget().equalsIgnoreCase("3G") || object.getTarget().equalsIgnoreCase("4G"))) {
                object.setConfidence(object.getConfidence() * 0.7);
            }
            object.setEndPosition(object.getEndPosition() + 1);
        }
        return arrayList;
    }

    public static boolean freeResource() {
        if (!ifInitial) {
            return true;
        }
        flowFrontGuideWords.clear();
        flowFrontGuideWordsMaxLength = -1;
        flowKeyWordsAndScore.clear();
        flowKeyWordAndScoreTable = null;
        flowPredictWordTable = null;
        flowForbidWordTable = null;
        ifInitial = false;
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static List<String> getSplitTokens(int n) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("\\r");
        arrayList.add("\\n");
        arrayList.add("\n");
        arrayList.add(";");
        arrayList.add("\r");
        arrayList.add("\u3002");
        arrayList.add("\uff1b");
        arrayList.add("\uff01");
        arrayList.add("\uff1f");
        arrayList.add("!");
        arrayList.add("?");
        if (n == 1) {
            return arrayList;
        }
        arrayList.add(",");
        arrayList.add("\uff0c");
        if (n == 2) return arrayList;
        arrayList.add("/");
        arrayList.add("\u3001");
        arrayList.add(".");
        arrayList.add("\\");
        return arrayList;
    }

    public static boolean initial() throws IOException {
        if (ifInitial) {
            return true;
        }
        flowFrontGuideWordsFileName = String.valueOf((Object)SMSUnderstand.dictionaryPath) + "/flowFrontGuideWords.txt";
        flowFrontGuideWordsMaxLength = FileOperator.readDic2Set(flowFrontGuideWordsFileName, flowFrontGuideWords);
        ArrayList arrayList = new ArrayList();
        arrayList.add("String");
        flowPredictWordTable = FileOperator.readDic2Form(flowFrontGuideWordsFileName, arrayList);
        flowKeyWordsAndScoreFileName = String.valueOf((Object)SMSUnderstand.dictionaryPath) + "/flowKeyWordsAndScore.txt";
        flowForbidWordTableFileName = String.valueOf((Object)SMSUnderstand.dictionaryPath) + "/flowForbidWordTable.txt";
        flowKeyWordsAndScoreMaxLength = FileOperator.readDic2Map(flowKeyWordsAndScoreFileName, flowKeyWordsAndScore);
        arrayList = new ArrayList();
        arrayList.add("String");
        arrayList.add("double");
        flowKeyWordAndScoreTable = FileOperator.readDic2Form(flowKeyWordsAndScoreFileName, arrayList);
        arrayList = new ArrayList();
        arrayList.add("String");
        arrayList.add("String");
        arrayList.add("String");
        arrayList.add("Double");
        flowForbidWordTable = FileOperator.readDic2Form(flowForbidWordTableFileName, arrayList);
        ifInitial = true;
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static String judge(String string2, String string3) {
        if (string3.length() <= 1 || !string3.contains((CharSequence)".") || string2.indexOf(".") != string3.indexOf(".")) {
            return string2;
        }
        return string2.substring(string2.indexOf(".") + 1);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean lackOfFlow(OntologyResults ontologyResults, OntologyTaskFrame ontologyTaskFrame) {
        double[] arrd = FlowRecognition.regularizationFlow(ontologyResults, ontologyTaskFrame);
        if (arrd == null) {
            return false;
        }
        int n = 0;
        do {
            if (n >= flowTag.length) break;
            int n2 = ontologyTaskFrame.getAttrIndexByName(flowTag[n], n);
            if (n2 == -1) {
                arrd[n] = 0.0;
            } else {
                ontologyResults.getAttrInfos().get(n2).setValue(String.valueOf((double)arrd[n]));
            }
            ++n;
        } while (true);
        double d2 = 0.0;
        double d3 = 0.0;
        n = 0;
        do {
            if (n >= 3) {
                if (d2 <= 0.0 || d3 > flowThrAbs) break;
                Log.println("lackOfFlow!");
                return true;
            }
            double d4 = arrd[n * 4];
            double d5 = arrd[n * 4 + 2];
            if (arrd[n * 4 + 3] > 0.0) {
                return true;
            }
            d2 += d4;
            d3 += d5;
            ++n;
        } while (true);
        if (d2 > 0.0 && d3 / d2 < flowThrRate) {
            Log.println("lackOfFlow!");
            return true;
        }
        return false;
    }

    private static String regularization(String string2) {
        if ((string2 = string2.toLowerCase()).contains((CharSequence)"g")) {
            return "GB";
        }
        if (string2.contains((CharSequence)"m")) {
            return "MB";
        }
        if (string2.contains((CharSequence)"k")) {
            return "KB";
        }
        return null;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static double regularizationFlow(String var0) {
        if (var0.length() != 1) ** GOTO lbl-1000
        switch (var0.charAt(0)) {
            default: lbl-1000: // 2 sources:
            {
                var12_2 = var0.toUpperCase();
                var3_4 = 0.0;
                var9_5 = 0;
                var10_6 = 0;
                break;
            }
            case '\u5149': 
            case '\u5b8c': 
            case '\u5c3d': {
                return FlowRecognition.flowFinish;
            }
        }
        do {
            if (var9_5 < var0.length() && var10_6 < FlowRecognition.units.length) {
                var11_11 = var12_2.indexOf(FlowRecognition.units[var10_6], var9_5);
                if (var11_11 <= 0) {
                    ++var10_6;
                    continue;
                }
            } else {
                if (var9_5 >= var0.length()) return var3_4;
                if (StringProcess.isRealNumber(var0)) break;
                return FlowRecognition.parseError;
            }
            if (var11_11 < var0.length() - 1) {
                var7_9 = var0.charAt(var11_11 + 1);
                if (var7_9 == 'b') {
                    var7_9 = '\u0001';
                    var8_10 = false;
                } else if (var7_9 == 'B') {
                    var7_9 = '\u0001';
                    var8_10 = true;
                } else {
                    var7_9 = '\u0000';
                    var8_10 = true;
                }
            } else {
                var7_9 = '\u0000';
                var8_10 = true;
            }
            var1_7 = 1.0;
            if (var10_6 == 0) {
                var1_7 = 1024.0;
            } else if (var10_6 == 2) {
                var1_7 = 9.765625E-4;
            }
            var5_8 = var1_7;
            if (!var8_10) {
                var5_8 = var1_7 / 8.0;
            }
            if (!StringProcess.isRealNumber(var13_12 = var0.substring(var9_5, var11_11))) {
                Log.i("wpz", var0);
                return FlowRecognition.parseError;
            }
            try {
                var1_7 = Double.parseDouble((String)var13_12);
                var3_4 += var5_8 * var1_7;
                ** if (var7_9 == '\u0000') goto lbl-1000
            }
            catch (Exception var12_3) {
                Log.i("wpz", var0);
                return FlowRecognition.parseError;
            }
lbl-1000: // 1 sources:
            {
                var9_5 = var11_11 + 2;
                continue;
            }
lbl-1000: // 1 sources:
            {
            }
            var9_5 = var11_11 + 1;
        } while (true);
        try {
            return Double.parseDouble((String)var0);
        }
        catch (Exception var0_1) {
            return FlowRecognition.parseError;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static double[] regularizationFlow(OntologyResults var0, OntologyTaskFrame var1_1) {
        var7_2 = new double[FlowRecognition.flowTag.length];
        var8_3 = new HashMap();
        var2_4 = 0;
        do {
            if (var2_4 >= FlowRecognition.flowTag.length) break;
            var3_5 = var1_1.getAttrIndexByName(FlowRecognition.flowTag[var2_4], var2_4);
            if (var3_5 == -1) {
                var7_2[var2_4] = 0.0;
            } else {
                var5_6 = var0.getAttrInfos().get(var3_5).getValue();
                if (var5_6 == null) {
                    var7_2[var2_4] = 0.0;
                } else {
                    var9_8 = var5_6.split("[:#]");
                    if (var9_8.length == 2) {
                        var7_2[var2_4] = Double.parseDouble((String)var9_8[1]);
                    } else {
                        for (var3_5 = 0; var3_5 < var9_8.length; var3_5 += 2) {
                            var10_9 = Integer.valueOf((String)var9_8[var3_5]);
                            var5_6 = var6_7 = (Map)var8_3.get((Object)var10_9);
                            if (var6_7 == null) {
                                var5_6 = new HashMap();
                                var8_3.put(var10_9, var5_6);
                            }
                            var5_6.put((int)var2_4, (double)Double.parseDouble((String)var9_8[var3_5 + 1]));
                        }
                    }
                }
            }
            ++var2_4;
        } while (true);
        var2_4 = 0;
        do {
            if (var2_4 >= 3) break;
            if (var7_2[var2_4 * 4 + 3] > 0.0) {
                return var7_2;
            }
            ++var2_4;
        } while (true);
        var2_4 = 0;
        do {
            if (var2_4 >= var7_2.length) break;
            if (!FlowRecognition.regularizationGroup(var7_2, var2_4)) {
                Log.i("Time", "FlowRecognition.regularizationFlow Error:" + var0.toString());
                Log.println("FlowRecognition.regularizationFlow Error:" + var0.toString());
                return null;
            }
            var2_4 += 4;
        } while (true);
        var0 = var8_3.values().iterator();
        block4 : do lbl-1000: // 3 sources:
        {
            if (!var0.hasNext()) {
                return var7_2;
            }
            var5_6 = new double[4];
            var1_1 = (Map)var0.next();
            var3_5 = FlowRecognition.regularizationParseGroup((double[])var5_6, var1_1);
            if (var3_5 < 0) ** GOTO lbl-1000
            var2_4 = 0;
            do {
                if (var2_4 >= var5_6.length) continue block4;
                var4_10 = var3_5 * 4 + var2_4;
                var7_2[var4_10] = var7_2[var4_10] + var5_6[var2_4];
                ++var2_4;
            } while (true);
            break;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static boolean regularizationGroup(double[] arrd, int n) {
        if (Math.abs((double)(arrd[n + 0] - arrd[n + 1] - arrd[n + 2] + arrd[n + 3])) <= 1.0E-6) {
            return true;
        }
        if (arrd[n + 1] == flowFinish || arrd[n + 3] > 0.0) {
            arrd[n + 2] = 0.0;
            if (arrd[n + 0] > 0.0) {
                arrd[n + 1] = arrd[n + 0] - arrd[n + 2] + arrd[n + 3];
            } else {
                arrd[n + 0] = arrd[n + 3];
                arrd[n + 1] = 0.0;
            }
        } else if (arrd[n + 2] == 0.0) {
            arrd[n + 2] = arrd[n + 0] - arrd[n + 1];
        } else if (arrd[n + 2] > 0.0) {
            arrd[n + 3] = 0.0;
            if (arrd[n + 0] == 0.0) {
                arrd[n + 0] = arrd[n + 1] + arrd[n + 2];
            } else if (arrd[n + 1] == 0.0) {
                arrd[n + 1] = arrd[n + 0] - arrd[n + 2];
            }
        } else if (arrd[n + 2] == parseError && arrd[n + 0] >= 0.0 && arrd[n + 1] >= 0.0) {
            arrd[n + 2] = arrd[n + 0] - arrd[n + 1] + arrd[n + 3];
        }
        if (Math.abs((double)(arrd[n + 0] - arrd[n + 1] - arrd[n + 2] + arrd[n + 3])) <= 1.0E-6) return true;
        return false;
    }

    private static int regularizationParseGroup(double[] arrd, Map<Integer, Double> object) {
        object = object.entrySet().iterator();
        int n = -1;
        do {
            if (!object.hasNext()) {
                if (FlowRecognition.regularizationGroup(arrd, 0)) break;
                return -1;
            }
            Map.Entry entry = (Map.Entry)object.next();
            int n2 = (Integer)entry.getKey() / 4;
            if (n >= 0 && n != n2) {
                Log.i("Time", "FlowRecognition.regularizationParseGroup Error:" + n2 + "\t" + n);
                Log.println("FlowRecognition.regularizationParseGroup Error:" + n2 + "\t" + n);
                return -1;
            }
            arrd[((Integer)entry.getKey()).intValue() % 4] = (Double)entry.getValue();
            n = n2;
        } while (true);
        return n;
    }
}

