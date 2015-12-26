/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Double
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.text.SimpleDateFormat
 *  java.util.ArrayList
 *  java.util.Calendar
 *  java.util.Date
 *  java.util.HashSet
 *  java.util.regex.Pattern
 */
package com.xiaomi.smsunderstand.task;

import com.xiaomi.common.CalenderHelper;
import com.xiaomi.common.FileOperator;
import com.xiaomi.common.Log;
import com.xiaomi.common.StringProcess;
import com.xiaomi.nlp.ParseResult;
import com.xiaomi.nlp.ParseType;
import com.xiaomi.nlp.Parser;
import com.xiaomi.nlp.PatternForNER;
import com.xiaomi.smsunderstand.EntityInfo;
import com.xiaomi.smsunderstand.EntityType;
import com.xiaomi.smsunderstand.SMSUnderstand;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class DateTimeRecognition {
    private static double acceptScore;
    private static double acceptThr;
    private static String dateTimeFrontGuideFileName;
    private static HashSet<String> dateTimeFrontGuideWords;
    private static int dateTimeFrontGuideWordsMaxLength;
    private static String dateTimePatternsFileName;
    private static SimpleDateFormat datetimeFormat;
    private static int defaultHour;
    private static boolean festival;
    private static final byte[] festivalDate;
    private static boolean ifInitial;
    private static boolean intercalary;
    private static int lastSign;
    private static int lastValue;
    private static int maxVagueDistance;
    private static SimpleDateFormat[] numberTimePattern;
    private static String[] numberTimePatternStr;
    private static SimpleDateFormat onlyDateFormat;
    private static SimpleDateFormat onlyTimeFormat;
    private static Parser parser;
    public static Pattern patNumberSequence;
    private static double rejectThr;
    private static Calendar smsTime;
    private static boolean solar;
    private static Calendar time;
    private static boolean[] timeItemSet;

    static {
        ifInitial = false;
        dateTimeFrontGuideWords = new HashSet();
        dateTimeFrontGuideWordsMaxLength = -1;
        dateTimeFrontGuideFileName = null;
        dateTimePatternsFileName = null;
        maxVagueDistance = 2;
        acceptScore = 0.3;
        festivalDate = new byte[]{1, 23, 1, 30, 1, 31, 2, 14, 4, 5, 6, 2, 9, 8, 2, 11, 2, 18, 2, 19, 3, 5, 4, 5, 6, 20, 9, 27, 2, 1, 2, 7, 2, 8, 2, 22, 4, 4, 6, 9, 9, 15};
        numberTimePatternStr = new String[]{"yyyyMMdd", "yyyyMMddHHmmss", "yyyyMMdd HHmmss", "yyyyMMddHHmm"};
        numberTimePattern = new SimpleDateFormat[numberTimePatternStr.length];
        acceptThr = 0.5;
        rejectThr = 0.1;
        defaultHour = 9;
        smsTime = Calendar.getInstance();
        festival = false;
        intercalary = false;
        solar = true;
        lastValue = -1;
        lastSign = 0;
        datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        onlyDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        onlyTimeFormat = new SimpleDateFormat("HH:mm:ss");
        patNumberSequence = Pattern.compile((String)"(((\\d)+ (\\d)+)|((\\d)+))");
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static Calendar changeResult(List<String> var0, List<String> var1_1) throws ParseException {
        DateTimeRecognition.lastSign = 0;
        DateTimeRecognition.time = Calendar.getInstance();
        DateTimeRecognition.time.setFirstDayOfWeek(2);
        DateTimeRecognition.time.set(DateTimeRecognition.smsTime.get(1), DateTimeRecognition.smsTime.get(2), DateTimeRecognition.smsTime.get(5), DateTimeRecognition.defaultHour, 0, 0);
        DateTimeRecognition.timeItemSet = new boolean[6];
        DateTimeRecognition.intercalary = false;
        DateTimeRecognition.solar = true;
        DateTimeRecognition.lastValue = -1;
        DateTimeRecognition.festival = false;
        var5_2 = 0;
        var3_3 = -1;
        var2_4 = 0;
        block0 : do {
            if (var5_2 >= var0.size() || (var7_7 = var1_1.get(var5_2)) == null) ** GOTO lbl20
            if (!var7_7.trim().equals((Object)"")) ** GOTO lbl23
            var4_5 = var3_3;
            var3_3 = var2_4;
            var2_4 = var4_5;
            ** GOTO lbl108
lbl20: // 1 sources:
            if (!DateTimeRecognition.timeItemSet[2] && !DateTimeRecognition.timeItemSet[3]) {
                return null;
            }
            ** GOTO lbl80
lbl23: // 1 sources:
            var8_8 = ((String)var0.get(var5_2)).trim();
            if (!var8_8.equals((Object)"")) ** GOTO lbl29
            var4_5 = var2_4;
            var2_4 = var3_3;
            var3_3 = var4_5;
            ** GOTO lbl108
lbl29: // 1 sources:
            if (!var7_7.equals((Object)"<#yyyy>")) ** GOTO lbl40
            if (DateTimeRecognition.timeItemSet[0]) ** GOTO lbl105
            var4_5 = var6_6 = Integer.parseInt((String)var8_8);
            if (var6_6 < 100) {
                var4_5 = var6_6 < 18 ? var6_6 + 2000 : var6_6 + 1900;
            }
            DateTimeRecognition.time.set(1, var4_5);
            DateTimeRecognition.timeItemSet[0] = true;
            var4_5 = var2_4;
            var2_4 = var3_3;
            var3_3 = var4_5;
            ** GOTO lbl108
lbl40: // 1 sources:
            if (!var7_7.equals((Object)"<#MM>")) ** GOTO lbl48
            if (DateTimeRecognition.timeItemSet[1]) ** GOTO lbl105
            DateTimeRecognition.time.set(2, Integer.parseInt((String)var8_8) - 1);
            DateTimeRecognition.timeItemSet[1] = true;
            var4_5 = var2_4;
            var2_4 = var3_3;
            var3_3 = var4_5;
            ** GOTO lbl108
lbl48: // 1 sources:
            if (!var7_7.equals((Object)"<#dd>")) ** GOTO lbl56
            if (DateTimeRecognition.timeItemSet[2]) ** GOTO lbl105
            DateTimeRecognition.time.set(5, Integer.parseInt((String)var8_8));
            DateTimeRecognition.timeItemSet[2] = true;
            var4_5 = var2_4;
            var2_4 = var3_3;
            var3_3 = var4_5;
            ** GOTO lbl108
lbl56: // 1 sources:
            if (!var7_7.equals((Object)"<#HH>")) ** GOTO lbl64
            if (DateTimeRecognition.timeItemSet[3]) ** GOTO lbl105
            DateTimeRecognition.time.set(11, Integer.parseInt((String)var8_8));
            DateTimeRecognition.timeItemSet[3] = true;
            var4_5 = var2_4;
            var2_4 = var3_3;
            var3_3 = var4_5;
            ** GOTO lbl108
lbl64: // 1 sources:
            if (!var7_7.equals((Object)"<#mm>")) ** GOTO lbl72
            if (DateTimeRecognition.timeItemSet[4]) ** GOTO lbl105
            DateTimeRecognition.time.set(12, Integer.parseInt((String)var8_8));
            DateTimeRecognition.timeItemSet[4] = true;
            var4_5 = var2_4;
            var2_4 = var3_3;
            var3_3 = var4_5;
            ** GOTO lbl108
lbl72: // 1 sources:
            if (!var7_7.equals((Object)"<#ss>")) break;
            if (DateTimeRecognition.timeItemSet[5]) ** GOTO lbl105
            DateTimeRecognition.time.set(13, Integer.parseInt((String)var8_8));
            DateTimeRecognition.timeItemSet[5] = true;
            var4_5 = var2_4;
            var2_4 = var3_3;
            var3_3 = var4_5;
            ** GOTO lbl108
lbl80: // 1 sources:
            if (var3_3 > 0 && DateTimeRecognition.timeItemSet[3]) {
                var2_4 = DateTimeRecognition.time.get(11);
                if (var3_3 == 1 && var2_4 >= 0 && var2_4 < 12) {
                    DateTimeRecognition.time.add(11, 12);
                } else if (var3_3 == 2 && var2_4 >= 6 && var2_4 < 12) {
                    DateTimeRecognition.time.add(11, 12);
                }
            } else if (var3_3 == 1) {
                DateTimeRecognition.time.set(11, 13);
            } else if (var3_3 == 2) {
                DateTimeRecognition.time.set(11, 20);
            }
            if (DateTimeRecognition.solar != false) return DateTimeRecognition.time;
            if (DateTimeRecognition.festival != false) return DateTimeRecognition.time;
            var0 = CalenderHelper.getSunDate(DateTimeRecognition.time.get(1), DateTimeRecognition.time.get(2) + 1, DateTimeRecognition.time.get(5));
            if (var0.size() == 0) {
                return null;
            }
            if (DateTimeRecognition.intercalary && var0.size() != 2) {
                return null;
            }
            var0 = DateTimeRecognition.intercalary != false ? (List)((int[])var0.get(1)) : (List)((int[])var0.get(0));
            DateTimeRecognition.time.set(1, var0[0]);
            DateTimeRecognition.time.set(2, (int)(var0[1] - true));
            DateTimeRecognition.time.set(5, var0[2]);
            return DateTimeRecognition.time;
lbl105: // 6 sources:
            var4_5 = var2_4;
            var2_4 = var3_3;
            var3_3 = var4_5;
lbl108: // 11 sources:
            do {
                ++var5_2;
                var4_5 = var3_3;
                var3_3 = var2_4;
                var2_4 = var4_5;
                continue block0;
                break;
            } while (true);
            break;
        } while (true);
        var7_7 = DateTimeRecognition.segTargetSegStr(var8_8);
        var6_6 = var7_7.length;
        var4_5 = 0;
        do {
            if (var4_5 < var6_6) ** GOTO lbl123
            var4_5 = var2_4;
            var2_4 = var3_3;
            var3_3 = var4_5;
            ** GOTO lbl108
lbl123: // 1 sources:
            var8_8 = var7_7[var4_5];
            if (var8_8.startsWith("week")) {
                DateTimeRecognition.getTime(var8_8, 5);
            } else if (var8_8.endsWith("year")) {
                DateTimeRecognition.getTime(var8_8, 1);
            } else if (var8_8.endsWith("month")) {
                DateTimeRecognition.getTime(var8_8, 2);
            } else if (var8_8.endsWith("day")) {
                DateTimeRecognition.getTime(var8_8, 5);
            } else if (var8_8.endsWith("hour")) {
                DateTimeRecognition.getTime(var8_8, 11);
            } else if (var8_8.endsWith("min")) {
                DateTimeRecognition.getTime(var8_8, 12);
            } else if (var8_8.equals((Object)"Fhalf")) {
                var3_3 = 0;
            } else if (var8_8.equals((Object)"Shalf")) {
                var3_3 = 1;
            } else if (var8_8.equals((Object)"night")) {
                var3_3 = 2;
            } else if (var8_8.equals((Object)"\u5e74")) {
                var0.set(var5_2, "year");
                DateTimeRecognition.getTime(var8_8, 1);
            } else if (var8_8.equals((Object)"\u6708")) {
                var0.set(var5_2, "month");
                DateTimeRecognition.getTime(var8_8, 2);
            } else if (var8_8.equals((Object)"\u5206")) {
                var0.set(var5_2, "min");
                DateTimeRecognition.getTime(var8_8, 12);
            } else if (var8_8.equals((Object)"\u65e5")) {
                var0.set(var5_2, "day");
                DateTimeRecognition.getTime(var8_8, 5);
            } else if (var8_8.equals((Object)"\u79d2")) {
                var0.set(var5_2, "second");
                DateTimeRecognition.getTime(var8_8, 13);
            } else {
                if (var8_8.equals((Object)"to")) {
                    var2_4 = var3_3;
                    var3_3 = 1;
                    ** continue;
                }
                if (var8_8.equals((Object)"xiaonian")) {
                    DateTimeRecognition.getFestivalDate(0);
                } else if (var8_8.equals((Object)"chuxi")) {
                    DateTimeRecognition.getFestivalDate(1);
                } else if (var8_8.equals((Object)"chunjie")) {
                    DateTimeRecognition.getFestivalDate(2);
                } else if (var8_8.equals((Object)"yuanxiao")) {
                    DateTimeRecognition.getFestivalDate(3);
                } else if (var8_8.equals((Object)"qingming")) {
                    DateTimeRecognition.getFestivalDate(4);
                } else if (var8_8.equals((Object)"duanwu")) {
                    DateTimeRecognition.getFestivalDate(5);
                } else if (var8_8.equals((Object)"zhongqiu")) {
                    DateTimeRecognition.getFestivalDate(6);
                } else {
                    DateTimeRecognition.parseSign(var8_8);
                }
            }
            ++var4_5;
        } while (true);
    }

    private static List<ParseResult> filterResults(List<ParseResult> object, String string2) {
        if (object.size() == 0) {
            return object;
        }
        ArrayList arrayList = new ArrayList(object.size());
        object = object.iterator();
        block6 : while (object.hasNext()) {
            char c2;
            ParseResult parseResult = (ParseResult)object.next();
            if (DateTimeRecognition.startKuohao(parseResult.getSource(), parseResult.getStartPositionInSentence(), parseResult.getEndPositionInSentence(), string2) || DateTimeRecognition.frontBackChar(parseResult.getSource(), parseResult.getStartPositionInSentence(), parseResult.getEndPositionInSentence(), string2) || parseResult.getSource().length() <= 3 && parseResult.getStartPositionInSentence() > 0 && (c2 = parseResult.getSource().charAt(0)) >= '0' && c2 <= '9' && string2.charAt(parseResult.getStartPositionInSentence() - 1) == '.' && parseResult.getStartPositionInSentence() > 1 && ((c2 = string2.charAt(parseResult.getStartPositionInSentence() - 2)) >= '0' || c2 <= '9')) continue;
            if (parser.match("<#m>+<.|/|:|\uff1a|->+<#m>", parseResult.getSource()) || StringProcess.isNumber(parseResult.getSource())) {
                char c3;
                if (parseResult.getEndPositionInSentence() < string2.length()) {
                    c2 = string2.charAt(parseResult.getEndPositionInSentence());
                    if ((c2 >= 'a' && c2 <= 'z' || c2 >= 'A' && c2 <= 'Z') && parseResult.getEndPositionInSentence() + 1 < string2.length() && ((c3 = string2.charAt(parseResult.getEndPositionInSentence() + 1)) < 'a' || c3 > 'z') && (c3 < 'A' || c3 > 'Z')) continue;
                    switch (c2) {
                        case '%': 
                        case 'G': 
                        case 'K': 
                        case 'M': 
                        case 'g': 
                        case 'k': 
                        case 'm': 
                        case '\u5143': 
                        case '\u5929': 
                        case '\u5e74': 
                        case '\u6708': {
                            continue block6;
                        }
                    }
                }
                if (parseResult.getStartPositionInSentence() > 0) {
                    c2 = string2.charAt(parseResult.getStartPositionInSentence() - 1);
                    if ((c2 >= 'a' && c2 <= 'z' || c2 >= 'A' && c2 <= 'Z') && parseResult.getStartPositionInSentence() > 1 && ((c3 = string2.charAt(parseResult.getStartPositionInSentence() - 2)) < 'a' || c3 > 'z') && (c3 < 'A' || c3 > 'Z')) continue;
                    switch (c2) {
                        case '$': 
                        case '\u00a3': 
                        case '\u00a5': 
                        case '\u20ac': 
                        case '\u5e01': 
                        case '\uffe5': {
                            continue block6;
                        }
                    }
                }
            }
            arrayList.add(parseResult);
        }
        return arrayList;
    }

    public static List<EntityInfo> findKnowledge(String string2, long l) throws Exception {
        ArrayList arrayList = new ArrayList();
        if (!ifInitial) {
            return arrayList;
        }
        smsTime.setTimeInMillis(l);
        Iterator<ParseResult> iterator = DateTimeRecognition.mergeResults(DateTimeRecognition.filterResults(parser.findKnowledge(string2, 0.0), string2), string2).iterator();
        do {
            if (!iterator.hasNext()) {
                DateTimeRecognition.findNumberSequenceTime(string2, arrayList);
                return arrayList;
            }
            ParseResult parseResult = iterator.next();
            Calendar calendar = DateTimeRecognition.changeResult(parseResult.getSegments_replace(), parseResult.getSegments_pattern());
            if (calendar == null || parseResult.getSource().length() == 2 && parseResult.getSource().endsWith("\u65f6") || (parseResult.getSource().length() == 2 || parseResult.getSource().length() == 3) && parseResult.getSource().endsWith("\u53f7")) continue;
            EntityInfo entityInfo = new EntityInfo();
            entityInfo.setTarget(parseResult.getSource());
            entityInfo.setTargetNomal(datetimeFormat.format(calendar.getTime()));
            entityInfo.setEntityType(EntityType.TIME);
            entityInfo.setRemark(parseResult.getPattern().toString());
            entityInfo.setConfidence(parseResult.getConfidence());
            entityInfo.setStartPosition(parseResult.getStartPositionInSentence());
            entityInfo.setEndPosition(parseResult.getEndPositionInSentence());
            double d2 = entityInfo.getConfidence();
            int n = entityInfo.getStartPosition();
            if (n > 0) {
                int n2;
                block8 : {
                    if (string2.charAt(n - 1) != '\uff1a' && string2.charAt(n - 1) != ':' && string2.charAt(n - 1) != '\u662f') {
                        n2 = n;
                        if (string2.charAt(n - 1) != '\u4e3a') break block8;
                    }
                    n2 = n - 1;
                }
                if ((n2 = StringProcess.EndWithDicWithVagueDistance(string2.substring(0, n2), dateTimeFrontGuideWords, dateTimeFrontGuideWordsMaxLength, maxVagueDistance)) >= 0) {
                    d2 = (1.5 + (double)maxVagueDistance - (double)n2) * d2;
                }
            }
            double d3 = d2;
            if (d2 >= 1.0) {
                d3 = 1.0;
            }
            entityInfo.setConfidence(d3);
            arrayList.add(entityInfo);
        } while (true);
    }

    /*
     * Exception decompiling
     */
    public static void findNumberSequenceTime(String var0, List<EntityInfo> var1_1) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 12[SIMPLE_IF_TAKEN]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
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

    public static String formatDate(long l) {
        if (l <= 0) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(l);
        return onlyDateFormat.format(calendar.getTime());
    }

    public static String formatDate(String object, long l) {
        try {
            object = DateTimeRecognition.findKnowledge((String)object, l);
            if (object.size() != 1) {
                return "";
            }
        }
        catch (Exception var0_1) {
            return "";
        }
        try {
            object = datetimeFormat.parse(((EntityInfo)object.get(0)).getTarget_nomal());
        }
        catch (ParseException var0_2) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime((Date)object);
        return onlyDateFormat.format(calendar.getTime());
    }

    public static String formatDateTime(long l) {
        if (l <= 0) {
            return "";
        }
        return datetimeFormat.format((Object)l);
    }

    public static String formatDateTime(String object, long l) {
        try {
            object = DateTimeRecognition.findKnowledge((String)object, l);
            if (object.size() != 1) {
                return "";
            }
        }
        catch (Exception var0_1) {
            return "";
        }
        return ((EntityInfo)object.get(0)).getTarget_nomal();
    }

    public static String formatTime(String object, long l) {
        try {
            object = DateTimeRecognition.findKnowledge((String)object, l);
            if (object.size() != 1) {
                return "";
            }
        }
        catch (Exception var0_1) {
            return "";
        }
        try {
            object = datetimeFormat.parse(((EntityInfo)object.get(0)).getTarget_nomal());
        }
        catch (ParseException var0_2) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime((Date)object);
        return onlyTimeFormat.format(calendar.getTime());
    }

    public static boolean freeResource() {
        if (!ifInitial) {
            return true;
        }
        dateTimeFrontGuideWords.clear();
        parser = null;
        ifInitial = false;
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static boolean frontBackChar(String object, int n, int n2, String object2) {
        StringProcess.ASCType aSCType;
        object = StringProcess.getASCType(object2.charAt(n));
        if ((object == StringProcess.ASCType.Number || object == StringProcess.ASCType.EnglishUpper || object == StringProcess.ASCType.EnglishLowerCase) && --n >= 0 && (object == (aSCType = StringProcess.getASCType(object2.charAt(n))) || object == StringProcess.ASCType.EnglishUpper && aSCType == StringProcess.ASCType.EnglishLowerCase || object == StringProcess.ASCType.EnglishLowerCase && aSCType == StringProcess.ASCType.EnglishUpper) || ((object = StringProcess.getASCType(object2.charAt(n2 - 1))) == StringProcess.ASCType.Number || object == StringProcess.ASCType.EnglishUpper || object == StringProcess.ASCType.EnglishLowerCase) && n2 < object2.length() && (object == (object2 = StringProcess.getASCType(object2.charAt(n2))) || object == StringProcess.ASCType.EnglishUpper && object2 == StringProcess.ASCType.EnglishLowerCase || object == StringProcess.ASCType.EnglishLowerCase && object2 == StringProcess.ASCType.EnglishUpper)) {
            return true;
        }
        return false;
    }

    /*
     * Exception decompiling
     */
    private static boolean getFestivalDate(int var0) {
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

    /*
     * Enabled aggressive block sorting
     */
    private static int getSegCount(ParseResult parseResult) {
        int n = 0;
        while (n < parseResult.getSegments().size() && parseResult.getSegments().get(n) != null) {
            ++n;
        }
        return n;
    }

    /*
     * Exception decompiling
     */
    private static void getTime(String var0, int var1_1) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.CannotPerformDecode: reachable test BLOCK was exited and re-entered.
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Misc.getFarthestReachableInRange(Misc.java:143)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.examineSwitchContiguity(SwitchReplacer.java:385)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.replaceRawSwitches(SwitchReplacer.java:65)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:422)
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

    public static boolean initial() throws Exception {
        if (ifInitial) {
            return true;
        }
        Log.i("DateTimeRecognition", "inital Time!");
        dateTimeFrontGuideFileName = String.valueOf((Object)SMSUnderstand.dictionaryPath) + "/dateTimeFrontGuide.txt";
        dateTimePatternsFileName = String.valueOf((Object)SMSUnderstand.dictionaryPath) + "/patterns/DateTime.pattern";
        dateTimeFrontGuideWordsMaxLength = FileOperator.readDic2Set(dateTimeFrontGuideFileName, dateTimeFrontGuideWords);
        parser = new Parser(dateTimePatternsFileName);
        int n = 0;
        do {
            if (n >= numberTimePattern.length) {
                ifInitial = true;
                return true;
            }
            DateTimeRecognition.numberTimePattern[n] = new SimpleDateFormat(numberTimePatternStr[n]);
            ++n;
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static List<ParseResult> mergeResults(List<ParseResult> var0, String var1_1) {
        block30 : {
            block33 : {
                block32 : {
                    block31 : {
                        if (var0.size() == 0) {
                            return var0;
                        }
                        var9_2 = new boolean[var0.size() - 1];
                        var4_3 = -10;
                        var2_4 = -1;
                        var3_5 = 0;
                        block7 : do {
                            if (var3_5 < var0.size()) ** GOTO lbl21
                            if (var2_4 < 0 || var1_1.length() <= var4_3) ** GOTO lbl-1000
                            switch (var1_1.charAt(var4_3)) lbl-1000: // 3 sources:
                            {
                                do {
                                    default: lbl-1000: // 2 sources:
                                    {
                                        var3_5 = -1;
                                        var2_4 = var0.size() - 1;
lbl15: // 2 sources:
                                        if (var2_4 > 0) break block7;
                                        var1_1 = new ArrayList(var0.size());
                                        var0 = var0.iterator();
lbl18: // 3 sources:
                                        if (!var0.hasNext()) {
                                            return var1_1;
                                        }
                                        break block30;
                                    }
                                    break;
                                } while (true);
lbl21: // 1 sources:
                                var10_8 = (ParseResult)var0.get(var3_5);
                                if (var3_5 == 0) {
                                    var4_3 = var10_8.getEndPositionInSentence();
lbl24: // 2 sources:
                                    do {
                                        ++var3_5;
                                        continue block7;
                                        break;
                                    } while (true);
                                }
                                var5_6 = var10_8.getStartPositionInSentence();
                                if (var5_6 == var4_3) {
                                    var8_7 = true;
lbl30: // 6 sources:
                                    do {
                                        var9_2[var3_5 - 1] = var8_7;
                                        var4_3 = var10_8.getEndPositionInSentence();
                                        ** continue;
                                        break;
                                    } while (true);
                                }
                                if (var5_6 != var4_3 + 1) ** GOTO lbl-1000
                                if ((var4_3 = (int)var1_1.charAt(var4_3)) != 30340) ** GOTO lbl38
                                var8_7 = true;
                                ** GOTO lbl30
lbl38: // 1 sources:
                                if (var4_3 != 32) ** GOTO lbl41
                                var8_7 = true;
                                ** GOTO lbl30
lbl41: // 1 sources:
                                switch (var4_3) {
                                    do {
                                        default: lbl-1000: // 2 sources:
                                        {
                                            var8_7 = false;
                                            ** GOTO lbl30
                                        }
                                        break;
                                    } while (true);
                                    case 40: 
                                    case 65288: {
                                        if (var2_4 >= 0) ** continue;
                                        var8_7 = false;
                                        var2_4 = var3_5;
                                        ** GOTO lbl30
                                    }
                                    case 41: 
                                    case 65289: 
                                }
                                if (var2_4 > 0) {
                                    var9_2[var2_4 - 1] = true;
                                }
                                var8_7 = true;
                                var2_4 = -1;
                                ** continue;
                                case ')': 
                                case '\uff09': {
                                    if (var2_4 <= 0) ** GOTO lbl-1000
                                    var9_2[var2_4 - 1] = true;
                                    ** continue;
                                }
                            }
                            break;
                        } while (true);
                        if (var2_4 <= 1) ** GOTO lbl86
                        if (var3_5 != -1 || !var9_2[var2_4 - 1]) ** GOTO lbl68
                        var4_3 = var2_4;
lbl64: // 5 sources:
                        do {
                            --var2_4;
                            var3_5 = var4_3;
                            ** GOTO lbl15
                            break;
                        } while (true);
lbl68: // 1 sources:
                        var4_3 = var3_5;
                        if (var3_5 == -1) ** GOTO lbl64
                        var4_3 = var3_5;
                        if (var9_2[var2_4 - 1]) ** GOTO lbl64
lbl72: // 2 sources:
                        do {
                            var4_3 = var3_5;
                            var3_5 = var2_4;
lbl75: // 3 sources:
                            var10_8 = (ParseResult)var0.get(var3_5);
                            var5_6 = 0;
lbl77: // 2 sources:
                            if (var5_6 < var10_8.getSegments().size()) break block31;
lbl78: // 2 sources:
                            do {
                                var6_9 = var10_8.getEndPositionInSentence();
                                var7_10 = var3_5 + 1;
                                var3_5 = var5_6;
                                var5_6 = var7_10;
lbl83: // 2 sources:
                                if (var5_6 <= var4_3) break block32;
                                var4_3 = -1;
                                ** GOTO lbl64
                                break;
                            } while (true);
                            break;
                        } while (true);
lbl86: // 1 sources:
                        if (!var9_2[var2_4 - 1]) {
                            var4_3 = var3_5;
                            if (var3_5 != -1) ** break;
                            ** continue;
                        }
                        ** while (!var9_2[var2_4 - 1])
lbl91: // 1 sources:
                        if (var3_5 != -1) ** GOTO lbl146
                        var4_3 = 1;
                        var3_5 = 0;
                        ** GOTO lbl75
                    }
                    ** while (var10_8.getSegments().get((int)var5_6) == null)
lbl97: // 1 sources:
                    ++var5_6;
                    ** GOTO lbl77
                }
                var11_11 = (ParseResult)var0.get(var5_6);
                if (var11_11.getStartPositionInSentence() - var6_9 != 1) ** GOTO lbl110
                var12_12 = var1_1.substring(var10_8.getEndPositionInSentence(), var10_8.getEndPositionInSentence() + 1);
                if (var3_5 >= var10_8.getSegments().size()) ** GOTO lbl121
                var10_8.getSegments().set(var3_5, (Object)var12_12);
                var10_8.getSegments_pattern().set(var3_5, (Object)var12_12);
                var13_13 = var10_8.getSegments_replace();
                var6_9 = var3_5 + 1;
                var13_13.set(var3_5, var12_12);
                var3_5 = var6_9;
lbl110: // 3 sources:
                do {
                    var6_9 = 0;
lbl112: // 2 sources:
                    if (var6_9 < var11_11.getSegments().size()) break block33;
lbl113: // 2 sources:
                    do {
                        var11_11.setType(ParseType.NoPass);
                        var6_9 = var11_11.getEndPositionInSentence();
                        var10_8.setConfidence(var11_11.getConfidence() + var10_8.getConfidence() - var11_11.getConfidence() * var10_8.getConfidence());
                        var10_8.setEndPositionInSentence(var6_9);
                        var10_8.setSource(var1_1.substring(var10_8.getStartPositionInSentence(), var6_9));
                        ++var5_6;
                        ** GOTO lbl83
                        break;
                    } while (true);
                    break;
                } while (true);
lbl121: // 1 sources:
                var10_8.getSegments().add((Object)var12_12);
                var10_8.getSegments_pattern().add((Object)var12_12);
                var10_8.getSegments_replace().add(var12_12);
                ++var3_5;
                ** while (true)
            }
            ** while (var11_11.getSegments().get((int)var6_9) == null)
lbl128: // 1 sources:
            if (var3_5 >= var10_8.getSegments().size()) ** GOTO lbl136
            var10_8.getSegments().set(var3_5, (Object)((String)var11_11.getSegments().get(var6_9)));
            var10_8.getSegments_pattern().set(var3_5, (Object)((String)var11_11.getSegments_pattern().get(var6_9)));
            var10_8.getSegments_replace().set(var3_5, var11_11.getSegments_replace().get(var6_9));
            ++var3_5;
lbl133: // 2 sources:
            do {
                ++var6_9;
                ** GOTO lbl112
                break;
            } while (true);
lbl136: // 1 sources:
            var10_8.getSegments().add((Object)((String)var11_11.getSegments().get(var6_9)));
            var10_8.getSegments_pattern().add((Object)((String)var11_11.getSegments_pattern().get(var6_9)));
            var10_8.getSegments_replace().add(var11_11.getSegments_replace().get(var6_9));
            ++var3_5;
            ** while (true)
        }
        var9_2 = (ParseResult)var0.next();
        if (var9_2.getType() == ParseType.NoPass || var9_2.getConfidence() < DateTimeRecognition.acceptScore || DateTimeRecognition.getSegCount((ParseResult)var9_2) == 1 && (var9_2.getSegments_replace().get(0).equals((Object)"lunar") || ((String)var9_2.getSegments().get(0)).length() <= 1)) ** GOTO lbl18
        var1_1.add(var9_2);
        ** GOTO lbl18
lbl146: // 1 sources:
        var4_3 = var3_5;
        var3_5 = 0;
        ** GOTO lbl75
    }

    private static void parseSign(String string2) {
        DateTimeRecognition.parseSign(string2.split(" "));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static void parseSign(String[] arrstring) {
        int n = arrstring.length;
        int n2 = 0;
        boolean bl = false;
        int n3 = 0;
        do {
            if (n2 >= n) {
                if (bl) {
                    lastSign = n3;
                }
                return;
            }
            String string2 = arrstring[n2];
            if (string2.equals((Object)"last")) {
                --n3;
                bl = true;
            } else if (string2.equals((Object)"next")) {
                ++n3;
                bl = true;
            } else if (string2.equals((Object)"current")) {
                bl = true;
            } else if (string2.equals((Object)"lunar")) {
                solar = false;
            } else if (string2.equals((Object)"solar")) {
                solar = true;
            } else if (string2.equals((Object)"intercalary")) {
                intercalary = true;
            } else {
                try {
                    lastValue = Integer.valueOf((String)string2);
                }
                catch (Exception var5_6) {}
            }
            ++n2;
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static String[] segTargetSegStr(String var0) {
        var5_1 = new ArrayList();
        var1_2 = var0.indexOf(" ", 0);
        var2_3 = 0;
        do {
            if (var1_2 <= 0) {
                var5_1.add(var0.substring(var2_3));
                return var5_1.toArray(new String[var5_1.size()]);
            }
            var6_6 = var0.substring(var2_3, var1_2);
            if (var6_6.endsWith("year") || var6_6.endsWith("month")) ** GOTO lbl-1000
            var3_4 = var2_3;
            if (var6_6.endsWith("day")) lbl-1000: // 2 sources:
            {
                var3_4 = var2_3;
                if (var1_2 < var0.length()) {
                    var4_5 = var0.charAt(var1_2 + 1);
                    var3_4 = var2_3;
                    if (var4_5 >= '0') {
                        var3_4 = var2_3;
                        if (var4_5 <= '9') {
                            var5_1.add(var6_6);
                            var3_4 = var1_2 + 1;
                        }
                    }
                }
            }
            var1_2 = var0.indexOf(" ", var1_2 + 1);
            var2_3 = var3_4;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static boolean startKuohao(String string2, int n, int n2, String string3) {
        if (!parser.match("<#m>+</|-|.>+<#m>", string2)) return false;
        int n3 = n - 1;
        if (n3 >= 0 && n2 < string3.length()) {
            if (string3.charAt(n3) == '(' && string3.charAt(n2) == ')') {
                return true;
            }
            if (string3.charAt(n3) == '[') {
                if (string3.charAt(n2) == ']') return true;
            }
            if (string3.charAt(n3) == '\u3010') {
                if (string3.charAt(n2) == '\u3011') return true;
            }
            if (string3.charAt(n3) != '\uff08') return false;
            if (string3.charAt(n2) == '\uff09') return true;
            return false;
        }
        if (n != 0) return false;
        if (n2 >= string3.length()) return false;
        if (string3.charAt(n2) == ':') return true;
        if (string3.charAt(n2) != '\uff1a') return false;
        return true;
    }
}

