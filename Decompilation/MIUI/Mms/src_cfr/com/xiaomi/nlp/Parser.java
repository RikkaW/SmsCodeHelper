/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Boolean
 *  java.lang.Character
 *  java.lang.Double
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.Runtime
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.System
 *  java.util.ArrayList
 *  java.util.BitSet
 *  java.util.Collections
 *  java.util.HashMap
 *  java.util.HashSet
 *  java.util.Map$Entry
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
package com.xiaomi.nlp;

import com.xiaomi.common.Bisection;
import com.xiaomi.common.FileOperator;
import com.xiaomi.common.IntInt;
import com.xiaomi.common.Log;
import com.xiaomi.common.StringInt;
import com.xiaomi.common.StringIntInt;
import com.xiaomi.common.StringProcess;
import com.xiaomi.common.StringString;
import com.xiaomi.nlp.KeyWordAccessable;
import com.xiaomi.nlp.KeywordsType;
import com.xiaomi.nlp.NLP;
import com.xiaomi.nlp.ParseResult;
import com.xiaomi.nlp.ParseType;
import com.xiaomi.nlp.ParserKeywords;
import com.xiaomi.nlp.ParserKeywordsDic;
import com.xiaomi.nlp.PartOfParseResult;
import com.xiaomi.nlp.PatternActionContent;
import com.xiaomi.nlp.PatternForNER;
import com.xiaomi.nlp.PatternKeywords;
import com.xiaomi.nlp.VersionControl;
import com.xiaomi.smsunderstand.SMSUnderstand;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser
extends VersionControl {
    private static /* synthetic */ int[] $SWITCH_TABLE$com$xiaomi$nlp$PatternKeywords;
    private static String[] segPatsSignal;
    public static long startMemory;
    private static int timeOutThr;
    private double acceptThr;
    private boolean caseSensitive = true;
    private int charReplaceIndex = -1;
    private Map<String, ArrayList<StringString>> constant = new HashMap();
    private boolean containCHAR = false;
    private boolean containENG = false;
    private boolean containEnd = false;
    private boolean containNUM = false;
    private boolean containStart = false;
    private boolean containStartRegularExpress = false;
    private boolean containWildCard = false;
    private boolean containWildcard = false;
    private boolean currentStartRegular = false;
    private int currentStartRegularStartIndexInSource = 0;
    private int endTrimLength = 0;
    private int engReplaceIndex = -1;
    private boolean finalProduction = true;
    private BitSet forbidMatchIndex;
    private HashMap<Integer, Integer> keyWords2Count;
    private ParserKeywordsDic keywordsDic = new ParserKeywordsDic();
    private HashMap<String, StringIntInt> knowledge;
    private boolean mustKP = false;
    private NLP nlp;
    private Map<String, List<Integer>> noTerminal2patternIndex = new HashMap();
    private int numberReplaceIndex = -1;
    private boolean oneMatch = false;
    private String parserName;
    private List<PatternForNER> patterns;
    private int patternsCount = 0;
    private boolean processedMostLeftRegularPrefix = true;
    private boolean replaceSeg = false;
    private List<ParseResult> returnValue = null;
    private String segPunctuation = "[\u3002\uff01!\uff1f?\uff1b;]";
    private ArrayList<String> segments;
    private ArrayList<String> segments_pattern;
    private boolean startContainCHAR = false;
    private boolean startContainENG = false;
    private boolean startContainNUM = false;
    private List<Integer> startRegularExpressPatIndex = null;
    private int startTrimLength = 0;
    private String targetSegment = null;
    private String task;
    private long timeoutStart = System.currentTimeMillis();
    private int wildCardMaxMatchLength = 18;

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
        timeOutThr = 100;
        startMemory = -1;
        segPatsSignal = new String[]{"+", "&&&"};
    }

    public Parser() {
        this.nlp = new NLP(this.wildCardMaxMatchLength);
    }

    public Parser(String string2) {
        this.buildPattern(string2);
        Parser.printMemory("buildPattern");
        this.nlp = new NLP(this.wildCardMaxMatchLength, this.constant);
    }

    private void addNoTerminal2patternIndex(String string2, int n) {
        ArrayList arrayList;
        ArrayList arrayList2 = arrayList = this.noTerminal2patternIndex.get(string2);
        if (arrayList == null) {
            arrayList2 = new ArrayList();
            this.noTerminal2patternIndex.put(string2, (List<Integer>)arrayList2);
        }
        arrayList2.add(n);
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean addOtherStartEndKeywords(String list, int[] arrn, List<int[]> list2, List<Integer> list3, boolean bl, boolean bl2) {
        int[] arrn2;
        if (arrn[2] == arrn[1]) {
            return bl2;
        }
        if ((list = this.keywordsDic.findFirstAll((String)((Object)list), arrn[1], arrn[2], 1)) == null || list.size() == 0) {
            return bl2;
        }
        list2.remove(list2.size() - 1);
        if (bl) {
            list3.remove(list3.size() - 1);
        }
        for (int i = list.size() - 1; i >= 0 && (arrn2 = list.get(i))[1] == arrn[1] && (arrn2[1] != arrn[1] || arrn2[2] != arrn[2]); --i) {
            list2.add(arrn2);
            boolean bl3 = bl2;
            if (!bl2) {
                bl3 = bl2;
                if (this.findMust(this.keywordsDic.getKeywords(arrn2[0]).getKeyWords())) {
                    bl3 = true;
                }
            }
            if (bl3 && this.keywordsDic.getKeywords(arrn2[0]).isEndkeyWords()) {
                list3.add(list2.size() - 1);
            }
            bl2 = bl3;
        }
        list2.add(arrn);
        if (bl) {
            list3.add(list2.size() - 1);
        }
        return bl2;
    }

    private static void addTempMustKeywords(Map<Integer, Integer> map, int n) {
        Parser.setTempKeywords(map, n, 8);
        map.put(n, map.get(n) + 65536);
    }

    private static void addTempMustKeywords(Map<Integer, Integer> map, int n, int n2) {
        Parser.setTempKeywords(map, n, 8);
        map.put(n, map.get(n) + 65536 * n2);
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private Boolean analyseConstraint(List<PatternActionContent> var1_1, String[] var2_2, int var3_3) {
        var6_4 = var1_1.iterator();
        var1_1 = true;
        block27 : do {
            if (!var6_4.hasNext()) {
                return var1_1;
            }
            var5_6 = (PatternActionContent)var6_4.next();
            var7_7 = new StringBuffer();
            var8_8 = var5_6.contents.iterator();
            block28 : do {
                if (var8_8.hasNext()) ** GOTO lbl22
                var4_5 = 1;
                block29 : do {
                    if (var4_5 == 0) continue block27;
                    var7_7 = var7_7.toString();
                    switch (Parser.$SWITCH_TABLE$com$xiaomi$nlp$PatternKeywords()[var5_6.keywords.ordinal()]) lbl-1000: // 59 sources:
                    {
                        do {
                            default: lbl-1000: // 5 sources:
                            {
                                do {
                                    var5_6 = var1_1;
                                    if (var1_1 == false) return var5_6;
                                    continue block27;
                                    break;
                                } while (true);
                            }
                            break;
                        } while (true);
lbl22: // 1 sources:
                        var9_9 = var8_8.next();
                        var4_5 = var9_9.getNum() - var3_3;
                        if (var4_5 < 0) {
                            var4_5 = 0;
                            continue block29;
                        }
                        if (var4_5 >= var2_2.length - 1) {
                            var4_5 = 0;
                            continue block29;
                        }
                        if (var9_9.getName().equals((Object)"")) {
                            var7_7.append(var2_2[var4_5]);
                            continue block28;
                        }
                        var7_7.append(var2_2[var4_5]);
                        continue block28;
                        case 1: {
                            ** GOTO lbl-1000
                        }
                        case 27: {
                            if (var7_7.length() != 0) ** GOTO lbl-1000
                            var1_1 = false;
                            ** GOTO lbl-1000
                        }
                        case 15: {
                            if (!NLP.EqualDic_Seg((String)var7_7, var5_6.parameter)) ** GOTO lbl-1000
                            var1_1 = false;
                            ** GOTO lbl-1000
                        }
                        case 16: {
                            if (NLP.EqualDic_Seg((String)var7_7, var5_6.parameter)) ** GOTO lbl-1000
                            var1_1 = false;
                            ** GOTO lbl-1000
                        }
                        case 25: {
                            if (this.nlp.containDic((String)var7_7, var5_6.parameter)) ** GOTO lbl-1000
                            var1_1 = false;
                            ** GOTO lbl-1000
                        }
                        case 26: {
                            if (!this.nlp.containDic((String)var7_7, var5_6.parameter)) ** GOTO lbl-1000
                            var1_1 = false;
                            ** GOTO lbl-1000
                        }
                        case 23: {
                            if (this.nlp.containPOS((String)var7_7, var5_6.parameter)) ** GOTO lbl-1000
                            var1_1 = false;
                            ** GOTO lbl-1000
                        }
                        case 24: {
                            if (!this.nlp.containPOS((String)var7_7, var5_6.parameter)) ** GOTO lbl-1000
                            var1_1 = false;
                            ** GOTO lbl-1000
                        }
                        case 19: {
                            if (var7_7.length() > Integer.valueOf((String)var5_6.parameter)) ** GOTO lbl-1000
                            var1_1 = false;
                            ** GOTO lbl-1000
                        }
                        case 20: {
                            if (var7_7.length() < Integer.valueOf((String)var5_6.parameter)) ** GOTO lbl-1000
                            var1_1 = false;
                            ** GOTO lbl-1000
                        }
                        case 21: {
                            if (StringProcess.wordsCount((String)var7_7) > Integer.valueOf((String)var5_6.parameter)) ** GOTO lbl-1000
                            var1_1 = false;
                            ** GOTO lbl-1000
                        }
                        case 22: {
                            if (StringProcess.wordsCount((String)var7_7) < Integer.valueOf((String)var5_6.parameter)) ** GOTO lbl-1000
                            var1_1 = false;
                            ** GOTO lbl-1000
                        }
                        case 18: {
                            if (!this.nlp.startWithPOS((String)var7_7, var5_6.parameter)) ** GOTO lbl-1000
                            var1_1 = false;
                            ** GOTO lbl-1000
                        }
                        case 13: {
                            if (!this.nlp.endWithPOS((String)var7_7, var5_6.parameter)) ** GOTO lbl-1000
                            var1_1 = false;
                            ** GOTO lbl-1000
                        }
                        case 12: {
                            if (!this.nlp.noEndWithPOS((String)var7_7, var5_6.parameter)) ** GOTO lbl-1000
                            var1_1 = false;
                            ** GOTO lbl-1000
                        }
                        case 11: {
                            if (!this.nlp.endWithDic((String)var7_7, var5_6.parameter)) ** GOTO lbl-1000
                            var1_1 = false;
                            ** GOTO lbl-1000
                        }
                        case 10: {
                            if (this.nlp.endWithDic((String)var7_7, var5_6.parameter)) ** GOTO lbl-1000
                            var1_1 = false;
                            ** GOTO lbl-1000
                        }
                        case 17: {
                            if (!this.nlp.startWithDic((String)var7_7, var5_6.parameter)) ** GOTO lbl-1000
                            var1_1 = false;
                            ** GOTO lbl-1000
                        }
                        case 14: {
                            if (this.nlp.startWithDic((String)var7_7, var5_6.parameter)) ** GOTO lbl-1000
                            var1_1 = false;
                            ** GOTO lbl-1000
                        }
                        case 2: {
                            if (var7_7.length() == 4 || var7_7.length() == 2) ** GOTO lbl113
                            var1_1 = false;
                            ** GOTO lbl-1000
lbl113: // 1 sources:
                            if (StringProcess.isNumber((String)var7_7)) ** GOTO lbl116
                            var1_1 = false;
                            ** GOTO lbl-1000
lbl116: // 1 sources:
                            var4_5 = Integer.parseInt((String)var7_7);
                            if (var4_5 < 100 || var4_5 > 999) ** GOTO lbl120
                            var1_1 = false;
                            ** GOTO lbl-1000
lbl120: // 1 sources:
                            if (var4_5 < 2018) ** GOTO lbl-1000
                            var1_1 = false;
                            ** GOTO lbl-1000
                        }
                        case 3: {
                            if (var7_7.length() == 1 || var7_7.length() == 2) ** GOTO lbl127
                            var1_1 = false;
                            ** GOTO lbl-1000
lbl127: // 1 sources:
                            if (StringProcess.isNumber((String)var7_7)) ** GOTO lbl130
                            var1_1 = false;
                            ** GOTO lbl-1000
lbl130: // 1 sources:
                            var4_5 = Integer.parseInt((String)var7_7);
                            if (var4_5 >= 1 && var4_5 <= 12) ** GOTO lbl-1000
                            var1_1 = false;
                            ** GOTO lbl-1000
                        }
                        case 4: {
                            if (var7_7.length() == 1 || var7_7.length() == 2) ** GOTO lbl138
                            var1_1 = false;
                            ** GOTO lbl-1000
lbl138: // 1 sources:
                            if (StringProcess.isNumber((String)var7_7)) ** GOTO lbl141
                            var1_1 = false;
                            ** GOTO lbl-1000
lbl141: // 1 sources:
                            var4_5 = Integer.parseInt((String)var7_7);
                            if (var4_5 >= 1 && var4_5 <= 31) ** GOTO lbl-1000
                            var1_1 = false;
                            ** GOTO lbl-1000
                        }
                        case 5: {
                            if (var7_7.length() == 1 || var7_7.length() == 2) ** GOTO lbl149
                            var1_1 = false;
                            ** GOTO lbl-1000
lbl149: // 1 sources:
                            if (StringProcess.isNumber((String)var7_7)) ** GOTO lbl152
                            var1_1 = false;
                            ** GOTO lbl-1000
lbl152: // 1 sources:
                            if (Integer.parseInt((String)var7_7) <= 24) ** GOTO lbl-1000
                            var1_1 = false;
                            ** GOTO lbl-1000
                        }
                        case 6: {
                            if (var7_7.length() == 1 || var7_7.length() == 2) ** GOTO lbl159
                            var1_1 = false;
                            ** GOTO lbl-1000
lbl159: // 1 sources:
                            if (StringProcess.isNumber((String)var7_7)) ** GOTO lbl162
                            var1_1 = false;
                            ** GOTO lbl-1000
lbl162: // 1 sources:
                            if (Integer.parseInt((String)var7_7) <= 60) ** GOTO lbl-1000
                            var1_1 = false;
                            ** continue;
                        }
                        case 7: 
                    }
                    break;
                } while (true);
                break;
            } while (true);
            break;
        } while (true);
        if (var7_7.length() == 1 || var7_7.length() == 2) ** GOTO lbl169
        var1_1 = false;
        ** GOTO lbl-1000
lbl169: // 1 sources:
        if (StringProcess.isNumber((String)var7_7)) ** GOTO lbl172
        var1_1 = false;
        ** GOTO lbl-1000
lbl172: // 1 sources:
        if (Integer.parseInt((String)var7_7) <= 60) ** GOTO lbl-1000
        var1_1 = false;
        ** while (true)
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void buildPattern(String var1_1) {
        var8_3 = new ArrayList();
        try {}
        catch (IOException var1_2) {
            return;
        }
        FileOperator.readFile((String)var1_1, var8_3);
        this.parserName = FileOperator.getFileNameWithoutExtend((String)var1_1);
        this.initialPatternsNumber((List<String>)var8_3);
        var9_4 = new ArrayList();
        var10_5 = var8_3.iterator();
        block4 : do {
            if (!var10_5.hasNext()) ** GOTO lbl98
            var1_1 = ((String)var10_5.next()).trim();
            if (var1_1.equals((Object)"") || var1_1.startsWith("//")) continue;
            if (!var1_1.startsWith("<!")) ** GOTO lbl88
            var11_12 = StringProcess.noRegexSplit((String)var1_1, new String[]{"::=", "|"});
            if (var11_12.length < 2) continue;
            var11_12[0] = StringProcess.trim(var11_12[0].trim(), new Character[]{Character.valueOf((char)'<'), Character.valueOf((char)'>')});
            if (var11_12[0].equals((Object)"!must")) {
                var2_6 = 1;
                do {
                    if (var2_6 >= var11_12.length) continue block4;
                    var11_12[var2_6] = var11_12[var2_6].trim();
                    if (!var11_12[var2_6].equals((Object)"")) {
                        this.keywordsDic.addKeywords((String)var11_12[var2_6], 8);
                    }
                    ++var2_6;
                } while (true);
            }
            if (var11_12[0].equals((Object)"!segPunctuation")) {
                if (var11_12.length == 2) {
                    this.segPunctuation = "[" + var11_12[1].trim() + "]";
                    continue;
                }
                Log.i("Parser", "segPunctuation Error ");
                continue;
            }
            if (var11_12[0].equals((Object)"!task")) {
                if (var11_12.length == 2) {
                    this.task = var11_12[1].trim();
                    continue;
                }
                Log.i("Parser", "task Error ");
                continue;
            }
            if (var11_12[0].equals((Object)"!version")) {
                if (var11_12.length == 2) {
                    this.version = var11_12[1].trim();
                    if (this.matchResourceVersion(SMSUnderstand.getVersion()) != null) continue;
                    return;
                }
                Log.i("Parser", "version Error ");
                continue;
            }
            if (var11_12[0].equals((Object)"!platform")) {
                if (var11_12.length == 2) {
                    this.platform = Integer.parseInt((String)var11_12[1].trim());
                    continue;
                }
                Log.i("Parser", "platform Error ");
                continue;
            }
            if (var11_12[0].equals((Object)"!systemLevel")) {
                if (var11_12.length == 2) {
                    this.systemLevel = Integer.parseInt((String)var11_12[1].trim());
                    continue;
                }
                Log.i("Parser", "systemLevels Error ");
                continue;
            }
            if (var11_12[0].equals((Object)"!caseSensitive")) {
                if (var11_12.length == 2) {
                    var7_11 = Integer.parseInt((String)var11_12[1].trim()) != 0;
                    this.caseSensitive = var7_11;
                    continue;
                }
                Log.i("Parser", "caseSensitive Error ");
                continue;
            }
            if (var11_12[0].equals((Object)"!wildCardMaxMatchLength")) {
                if (var11_12.length == 2) {
                    this.wildCardMaxMatchLength = Integer.parseInt((String)var11_12[1].trim());
                    continue;
                }
                Log.i("Parser", "wildCardMaxMatchLength Error ");
                continue;
            }
            if (var11_12[0].equals((Object)"!mustKP")) {
                if (var11_12.length == 2) {
                    this.mustKP = Boolean.parseBoolean((String)var11_12[1].trim());
                    continue;
                }
                Log.i("Parser", "mustKP Error ");
                continue;
            }
            if (var11_12[0].equals((Object)"!oneMatch")) {
                if (var11_12.length == 2) {
                    this.oneMatch = Boolean.parseBoolean((String)var11_12[1].trim());
                    continue;
                }
                Log.i("Parser", "oneMatch Error ");
                continue;
            }
            if (this.constant.containsKey(var11_12[0])) {
                Log.i("buildPattern", String.valueOf((Object)var11_12[0]) + " has contained!");
                continue;
            }
            ** GOTO lbl131
lbl88: // 1 sources:
            if ((var1_1 = StringProcess.noRegexSplit((String)var1_1, "::=")).length != 2) continue;
            var11_12 = var1_1[0].trim();
            var8_3 = var1_1[1].trim();
            var2_6 = var8_3.indexOf(9);
            if (var2_6 > 0) {
                var1_1 = var8_3.substring(var2_6 + 1);
                var8_3 = var8_3.substring(0, var2_6);
            } else {
                var1_1 = "";
            }
            ** GOTO lbl155
lbl98: // 1 sources:
            Parser.printMemory("buildPattern -1");
            var2_6 = 0;
            do {
                if (var2_6 >= var9_4.size()) {
                    Parser.printMemory("buildPattern -2");
                    if (this.engReplaceIndex >= 0) {
                        this.containENG = true;
                        if (this.keywordsDic.isStartKeyWords(this.engReplaceIndex)) {
                            this.startContainENG = true;
                        }
                    }
                    if (this.charReplaceIndex >= 0) {
                        this.containCHAR = true;
                        if (this.keywordsDic.isStartKeyWords(this.charReplaceIndex)) {
                            this.startContainCHAR = true;
                        }
                    }
                    if (this.numberReplaceIndex >= 0) {
                        this.containNUM = true;
                        if (this.keywordsDic.isStartKeyWords(this.numberReplaceIndex)) {
                            this.startContainNUM = true;
                        }
                    }
                    this.fillKeywords();
                    Parser.printMemory("buildPattern -3");
                    this.keywordsDic.buildDic();
                    Parser.printMemory("buildPattern -4");
                    if (this.containStart) {
                        this.startTrimLength = PatternForNER.startPatternReplaceStr.length();
                    }
                    if (this.containEnd) {
                        this.endTrimLength = PatternForNER.endPatternReplaceStr.length();
                    }
                    if (this.version == null) {
                        Parser.printMemory("buildPattern -4.51");
                        this.version = SMSUnderstand.getVersion();
                    }
                    Parser.printMemory("buildPattern -5");
                    return;
                }
                this.keywordsDic.addKeyWordsPatIndex(var2_6, (List)var9_4.get(var2_6));
                ++var2_6;
            } while (true);
lbl131: // 1 sources:
            var12_13 = new ArrayList(var11_12.length - 1);
            var1_1 = "";
            var2_6 = 1;
            do {
                if (var2_6 >= var11_12.length) {
                    Collections.sort((List)var12_13);
                    this.constant.put((String)var11_12[0], (ArrayList<StringString>)var12_13);
                    continue block4;
                }
                var8_3 = StringProcess.noRegexSplit(var11_12[var2_6].trim(), "@");
                if (var8_3.length == 2) {
                    var1_1 = var8_3[1];
                    this.replaceSeg = true;
                }
                if (!this.caseSensitive) {
                    var8_3[0] = var8_3[0].toLowerCase();
                }
                if (!var8_3[0].equals((Object)"")) {
                    if (var8_3[0].equals((Object)"\uff0c")) {
                        var8_3[0] = ",";
                    } else if (var8_3[0].equals((Object)"\uff1a")) {
                        var8_3[0] = ":";
                    }
                    this.keywordsDic.addKeywords(var8_3[0], 1);
                    var12_13.add((Object)new StringString(var8_3[0], (String)var1_1));
                }
                ++var2_6;
            } while (true);
lbl155: // 2 sources:
            this.finalProduction = var11_12.startsWith("<??") == false;
            var8_3 = StringProcess.noRegexSplit((String)var8_3, "|||");
            var4_8 = var8_3.length;
            var2_6 = 0;
            block8 : do {
                if (var2_6 >= var4_8) ** break;
                var12_13 = var8_3[var2_6];
                if (!var12_13.equals((Object)"")) {
                    Parser.printMemory("buildPattern - befor " + (String)var12_13);
                    try {
                        var12_13 = new PatternForNER((String)var11_12, (String)var12_13, (String)var1_1);
                    }
                    catch (Exception var12_14) {}
                    Parser.printMemory("buildPattern - end " + var12_13.toString());
                    var13_15 = new TreeMap<Integer, Integer>();
                    var5_9 = this.patterns.size();
                    var14_16 = this.getKeyWords((PatternForNER)var12_13, var13_15);
                    if (var14_16[0]) {
                        this.containWildCard = true;
                        var12_13.setContainsWildcard();
                    }
                    if (var14_16[1]) {
                        this.containStart = true;
                        var12_13.setContainsStart();
                    }
                    if (var14_16[2]) {
                        this.containEnd = true;
                        var12_13.setContainsEnd();
                    }
                    if (var14_16[3]) {
                        this.containStartRegularExpress = true;
                        var12_13.setContainsStartRegularExpress();
                        break block4;
                    }
                    break block4;
                }
lbl185: // 4 sources:
                do {
                    ++var2_6;
                    continue block8;
                    break;
                } while (true);
                break;
            } while (true);
            break;
        } while (true);
        this.patterns.add((PatternForNER)var12_13);
        this.addNoTerminal2patternIndex((String)var11_12, var5_9);
        var15_17 = new int[4];
        var16_18 = var13_15.entrySet().iterator();
        block10 : do {
            if (!var16_18.hasNext()) {
                this.setPatKeyWords((PatternForNER)var12_13, var13_15, var15_17);
                ** continue;
            }
            var17_19 = var16_18.next();
            var6_10 = var9_4.size();
            if ((Integer)var17_19.getKey() < var6_10) ** GOTO lbl-1000
            var3_7 = 0;
            do {
                if (var3_7 > (Integer)var17_19.getKey() - var6_10) lbl-1000: // 2 sources:
                {
                    ((List)var9_4.get((Integer)var17_19.getKey())).add((int)var5_9);
                    if (!Parser.parseKeywordsSize((Integer)var17_19.getValue(), var15_17)) continue block10;
                    if (var14_16[0]) {
                        this.keywordsDic.setKeyWordsType((Integer)var17_19.getKey(), 16);
                    }
                    if (!var14_16[2]) continue block10;
                    this.keywordsDic.setKeyWordsType((Integer)var17_19.getKey(), 32);
                    continue block10;
                }
                var9_4.add(new ArrayList());
                ++var3_7;
            } while (true);
            break;
        } while (true);
    }

    private boolean checkMustKP(List<ParseResult> object) {
        if (!this.mustKP) {
            return true;
        }
        object = object.iterator();
        do {
            if (object.hasNext()) continue;
            return false;
        } while (!((ParseResult)object.next()).getPattern().leftProduction.endsWith("-M>"));
        return true;
    }

    private boolean checkOneMatch(List<ParseResult> list) {
        if (this.oneMatch && list.size() > 0 && this.checkMustKP(list)) {
            return true;
        }
        return false;
    }

    private boolean containKeyWordsInPat(int n, int n2, int n3) {
        return this.containKeyWordsInPat(this.patterns.get(n), n2, n3);
    }

    private boolean containKeyWordsInPat(PatternForNER patternForNER, int n, int n2) {
        if (patternForNER.findKeywords(n, n2) >= 0) {
            return true;
        }
        return false;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private boolean extractKnowledge(List<PatternActionContent> var1_1, List<String> var2_2, int var3_3, int var4_4, int var5_5, int var6_6, HashSet<String> var7_7) {
        block18 : {
            block19 : {
                block20 : {
                    if (var1_1 == null) return false;
                    if (var1_1.size() == 0) {
                        return false;
                    }
                    if (var3_3 > var2_2.size()) return false;
                    if (var4_4 > var2_2.size()) {
                        return false;
                    }
                    var1_1 = var1_1.iterator();
                    block0 : do lbl-1000: // 3 sources:
                    {
                        if (!var1_1.hasNext()) {
                            return true;
                        }
                        var15_15 = (PatternActionContent)var1_1.next();
                        if (var15_15.keywords != PatternKeywords.Extract) ** GOTO lbl-1000
                        var9_9 = -1;
                        var14_14 = 0;
                        var8_8 = var6_6;
                        block1 : do {
                            if (var14_14 >= var15_15.contents.size()) {
                                if (var9_9 < 0) continue block0;
                                this.knowledge.put((Object)var15_15.remark, (Object)new StringIntInt(null, var8_8 + this.currentStartRegularStartIndexInSource, var9_9 + this.currentStartRegularStartIndexInSource));
                                var7_7.add((Object)var15_15.remark);
                                return true;
                            }
                            var16_16 = var15_15.contents.get(var14_14);
                            if (var5_5 != var16_16.getNum()) {
                                var12_12 = var8_8;
                                var8_8 = var9_9;
                            } else {
                                var10_10 = var3_3;
                                var11_11 = var9_9;
                                var9_9 = var10_10;
                                var10_10 = var11_11;
                                break block0;
                            }
lbl32: // 4 sources:
                            do {
                                ++var14_14;
                                var9_9 = var8_8;
                                var8_8 = var12_12;
                                continue block1;
                                break;
                            } while (true);
                            break;
                        } while (true);
                        break;
                    } while (true);
                    do {
                        if (var9_9 >= var4_4) {
                            var16_16 = this.parserFunction(var16_16.getWord());
                            var12_12 = var8_8;
                            var13_13 = var10_10;
                            if (var16_16 == null) break block18;
                            var17_17 = var16_16[0];
                            var9_9 = var8_8;
                            var11_11 = var10_10;
                            if (var14_14 != 0) break block19;
                            if (!var17_17.equals((Object)"trimstart")) break;
                            var9_9 = var16_16.length == 2 ? Integer.MAX_VALUE : Integer.valueOf((String)var16_16[2]);
                            var12_12 = this.trimStart(this.targetSegment, var8_8, var10_10, var16_16[1], var9_9);
                            break block20;
                        }
                        var11_11 = var10_10;
                        if (var14_14 == 0) {
                            var11_11 = var10_10;
                            if (var9_9 == var3_3) {
                                var11_11 = var8_8;
                            }
                        }
                        var10_10 = var11_11 + var2_2.get(var9_9).length();
                        ++var9_9;
                    } while (true);
                    var12_12 = var8_8;
                    if (var17_17.equals((Object)"trimstartall")) {
                        var9_9 = var16_16.length == 2 ? Integer.MAX_VALUE : Integer.valueOf((String)var16_16[2]);
                        var12_12 = this.trimStartAll(this.targetSegment, var8_8, var10_10, var16_16[1], var9_9);
                    }
                }
                var9_9 = var12_12;
                var11_11 = var10_10;
                if (var15_15.contents.size() == 1) {
                    if (var17_17.equals((Object)"trim")) {
                        var8_8 = var16_16.length == 3 ? Integer.MAX_VALUE : Integer.valueOf((String)var16_16[3]);
                        var9_9 = this.trimStart(this.targetSegment, var12_12, var10_10, var16_16[1], var8_8);
                        var11_11 = this.trimEnd(this.targetSegment, var9_9, var10_10, var16_16[2], var8_8);
                    } else {
                        var9_9 = var12_12;
                        var11_11 = var10_10;
                        if (var17_17.equals((Object)"trimall")) {
                            var8_8 = var16_16.length == 3 ? Integer.MAX_VALUE : Integer.valueOf((String)var16_16[3]);
                            var9_9 = this.trimStartAll(this.targetSegment, var12_12, var10_10, var16_16[1], var8_8);
                            var11_11 = this.trimEndAll(this.targetSegment, var9_9, var10_10, var16_16[2], var8_8);
                        }
                    }
                }
            }
            var12_12 = var9_9;
            var13_13 = var11_11;
            if (var14_14 != var15_15.contents.size() - 1) ** GOTO lbl94
            if (!var17_17.equals((Object)"trimend")) ** GOTO lbl87
            var8_8 = var16_16.length == 2 ? Integer.MAX_VALUE : Integer.valueOf((String)var16_16[2]);
            var8_8 = this.trimEnd(this.targetSegment, var9_9, var11_11, var16_16[1], var8_8);
            var12_12 = var9_9;
            ** GOTO lbl32
lbl87: // 1 sources:
            var12_12 = var9_9;
            var13_13 = var11_11;
            if (!var17_17.equals((Object)"trimendall")) ** GOTO lbl94
            var8_8 = var16_16.length == 2 ? Integer.MAX_VALUE : Integer.valueOf((String)var16_16[2]);
            var8_8 = this.trimEndAll(this.targetSegment, var9_9, var11_11, var16_16[1], var8_8);
            var12_12 = var9_9;
            ** GOTO lbl32
        }
        var8_8 = var13_13;
        ** while (true)
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private boolean extractKnowledge(List<PatternActionContent> var1_1, String[] var2_2, int var3_3, int var4_4, HashSet<String> var5_5) {
        block17 : {
            block18 : {
                block19 : {
                    if (var1_1 == null) return false;
                    if (var1_1.size() == 0) {
                        return false;
                    }
                    var1_1 = var1_1.iterator();
                    block0 : do {
                        if (!var1_1.hasNext()) {
                            return true;
                        }
                        var13_13 = (PatternActionContent)var1_1.next();
                        if (var13_13.keywords != PatternKeywords.Extract) continue;
                        var7_7 = -1;
                        var10_10 = 0;
                        var6_6 = var4_4;
                        block1 : do {
                            if (var10_10 >= var13_13.contents.size()) {
                                if (var7_7 < 0) continue block0;
                                this.knowledge.put((Object)var13_13.remark, (Object)new StringIntInt(null, var6_6 + this.currentStartRegularStartIndexInSource, var7_7 + this.currentStartRegularStartIndexInSource));
                                var5_5.add((Object)var13_13.remark);
                                continue block0;
                            }
                            var14_14 = var13_13.contents.get(var10_10);
                            var12_12 = var14_14.getNum() - var3_3;
                            if (var12_12 < 0) {
                                Log.i("Parser", "extractKnowledge Error!");
                                var9_9 = var6_6;
                                var8_8 = var7_7;
                            } else {
                                var8_8 = var7_7;
                                var9_9 = var6_6;
                                if (var12_12 < var2_2.length - 1) {
                                    var14_14 = this.parserFunction(var14_14.getWord());
                                    var11_11 = 0;
                                    var9_9 = 0;
                                    if (var10_10 == 0) break block0;
                                    var9_9 = var6_6;
                                    var6_6 = var11_11;
                                    var8_8 = var7_7;
                                    var7_7 = var9_9;
                                    break block17;
                                }
                            }
lbl38: // 5 sources:
                            do {
                                ++var10_10;
                                var7_7 = var8_8;
                                var6_6 = var9_9;
                                continue block1;
                                break;
                            } while (true);
                            break;
                        } while (true);
                        break;
                    } while (true);
                    var7_7 = 0;
                    var8_8 = var6_6;
                    var6_6 = var7_7;
                    do {
                        if (var6_6 >= var12_12) {
                            if (var14_14 == null) break block18;
                            var15_15 = var14_14[0];
                            if (!var15_15.equals((Object)"trimstart")) break;
                            var6_6 = var14_14.length == 2 ? Integer.MAX_VALUE : Integer.valueOf((String)var14_14[2]);
                            var6_6 = this.trimStart(var2_2[var12_12], 0, var2_2[var12_12].length(), var14_14[1], var6_6);
                            break block19;
                        }
                        var8_8 += var2_2[var6_6].length();
                        ++var6_6;
                    } while (true);
                    if (var15_15.equals((Object)"trimstartall")) {
                        var6_6 = var14_14.length == 2 ? Integer.MAX_VALUE : Integer.valueOf((String)var14_14[2]);
                        var6_6 = this.trimStartAll(var2_2[var12_12], 0, var2_2[var12_12].length(), var14_14[1], var6_6);
                    } else if (var15_15.equals((Object)"trim")) {
                        var6_6 = var14_14.length == 3 ? Integer.MAX_VALUE : Integer.valueOf((String)var14_14[3]);
                        var6_6 = this.trimStart(var2_2[var12_12], 0, var2_2[var12_12].length(), var14_14[1], var6_6);
                    } else {
                        var6_6 = var9_9;
                        if (var15_15.equals((Object)"trimall")) {
                            var6_6 = var14_14.length == 3 ? Integer.MAX_VALUE : Integer.valueOf((String)var14_14[3]);
                            var6_6 = this.trimStartAll(var2_2[var12_12], 0, var2_2[var12_12].length(), var14_14[1], var6_6);
                        }
                    }
                }
                var7_7 = var8_8 + var6_6;
                ** GOTO lbl75
            }
            var7_7 = var8_8;
            var6_6 = var11_11;
        }
        if (var10_10 != var13_13.contents.size() - 1) ** GOTO lbl98
        var11_11 = var2_2[var12_12].length();
        if (var14_14 == null) ** GOTO lbl101
        var9_9 = 0;
        if (var10_10 == 0) {
            var9_9 = var6_6;
        }
        if (!(var15_15 = var14_14[0]).equals((Object)"trimend")) ** GOTO lbl86
        var6_6 = var14_14.length == 2 ? Integer.MAX_VALUE : Integer.valueOf((String)var14_14[2]);
        var6_6 = this.trimEnd(var2_2[var12_12], var9_9, var2_2[var12_12].length(), var14_14[1], var6_6);
        ** GOTO lbl102
lbl86: // 1 sources:
        if (!var15_15.equals((Object)"trimendall")) ** GOTO lbl90
        var6_6 = var14_14.length == 2 ? Integer.MAX_VALUE : Integer.valueOf((String)var14_14[2]);
        var6_6 = this.trimEndAll(var2_2[var12_12], var9_9, var2_2[var12_12].length(), var14_14[1], var6_6);
        ** GOTO lbl102
lbl90: // 1 sources:
        if (!var15_15.equals((Object)"trim")) ** GOTO lbl94
        var6_6 = var14_14.length == 3 ? Integer.MAX_VALUE : Integer.valueOf((String)var14_14[3]);
        var6_6 = this.trimEnd(var2_2[var12_12], var9_9, var2_2[var12_12].length(), var14_14[2], var6_6);
        ** GOTO lbl102
lbl94: // 1 sources:
        if (!var15_15.equals((Object)"trimall")) ** GOTO lbl101
        var6_6 = var14_14.length == 3 ? Integer.MAX_VALUE : Integer.valueOf((String)var14_14[3]);
        var6_6 = this.trimEndAll(var2_2[var12_12], var9_9, var2_2[var12_12].length(), var14_14[2], var6_6);
        ** GOTO lbl102
lbl98: // 1 sources:
        var8_8 += var2_2[var12_12].length();
        var9_9 = var7_7;
        ** GOTO lbl38
lbl101: // 2 sources:
        var6_6 = var11_11;
lbl102: // 5 sources:
        var8_8 += var6_6;
        var9_9 = var7_7;
        ** while (true)
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void fillKeywords() {
        var3_1 = 0;
        if (this.containCHAR) ** GOTO lbl14
        if (!this.containENG) ** GOTO lbl6
        if (this.containNUM) ** GOTO lbl42
        ** GOTO lbl62
lbl6: // 1 sources:
        if (this.containNUM == false) return;
        var2_10 = this.keywordsDic.getKeywords(this.numberReplaceIndex).getType();
        var1_6 = 0;
        while (var1_6 <= 9) {
            this.keywordsDic.addKeywords(String.valueOf((int)var1_6), var2_10 | 1);
            ++var1_6;
        }
        return;
lbl14: // 1 sources:
        var4_2 = this.keywordsDic.getKeywords(this.charReplaceIndex).getType();
        var1_3 = this.numberReplaceIndex >= 0 ? this.keywordsDic.getKeywords(this.numberReplaceIndex).getType() : 0;
        var2_7 = 0;
        do {
            if (var2_7 > 9) {
                var2_7 = this.keywordsDic.getKeywords(this.charReplaceIndex).getType();
                var1_3 = var3_1;
                if (this.engReplaceIndex >= 0) {
                    var1_3 = this.keywordsDic.getKeywords(this.engReplaceIndex).getType();
                    break;
                }
                break;
            }
            this.keywordsDic.addKeywords(String.valueOf((int)var2_7), var4_2 | 1 | var1_3);
            ++var2_7;
        } while (true);
        var2_7 = var2_7 | 1 | var1_3;
        var1_3 = 0;
        do {
            if (var1_3 > 25) break;
            this.keywordsDic.addKeywords(String.valueOf((char)((char)(var1_3 + 65))), var2_7);
            ++var1_3;
        } while (true);
        var1_3 = 0;
        do {
            if (var1_3 > 25) {
                return;
            }
            this.keywordsDic.addKeywords(String.valueOf((char)((char)(var1_3 + 97))), var2_7);
            ++var1_3;
        } while (true);
lbl42: // 1 sources:
        var2_8 = this.keywordsDic.getKeywords(this.numberReplaceIndex).getType();
        var1_4 = 0;
        do {
            if (var1_4 > 9) break;
            this.keywordsDic.addKeywords(String.valueOf((int)var1_4), var2_8 | 1);
            ++var1_4;
        } while (true);
        var2_8 = this.keywordsDic.getKeywords(this.engReplaceIndex).getType() | 1;
        var1_4 = 0;
        do {
            if (var1_4 > 25) {
                var1_4 = 0;
                while (var1_4 <= 25) {
                    this.keywordsDic.addKeywords(String.valueOf((char)((char)(var1_4 + 97))), var2_8);
                    ++var1_4;
                }
                return;
            }
            this.keywordsDic.addKeywords(String.valueOf((char)((char)(var1_4 + 65))), var2_8);
            ++var1_4;
        } while (true);
lbl62: // 1 sources:
        var2_9 = this.keywordsDic.getKeywords(this.engReplaceIndex).getType() | 1;
        var1_5 = 0;
        do {
            if (var1_5 > 25) {
                var1_5 = 0;
                while (var1_5 <= 25) {
                    this.keywordsDic.addKeywords(String.valueOf((char)((char)(var1_5 + 97))), var2_9);
                    ++var1_5;
                }
                return;
            }
            this.keywordsDic.addKeywords(String.valueOf((char)((char)(var1_5 + 65))), var2_9);
            ++var1_5;
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private int[] findFirstKeywordsByNoTerminal(String var1_1, int var2_2, String var3_3) {
        var11_4 = new HashSet();
        if (!this.noTerminal2patternIndex.containsKey(var3_3)) {
            return null;
        }
        var12_5 = this.noTerminal2patternIndex.get(var3_3).iterator();
        var5_6 = Integer.MAX_VALUE;
        var6_7 = -1;
        var4_8 = Integer.MAX_VALUE;
        var9_9 = null;
        block0 : do {
            if (!var12_5.hasNext()) {
                if (var11_4.size() <= 0) break;
                return this.keywordsDic.findFirst(var1_1, var2_2, (Set<Integer>)var11_4);
            }
            var7_10 = var12_5.next();
            if (this.patterns.get((int)var7_10).isRegularExpression) {
                var10_12 = var9_9;
                if (var9_9 == null) {
                    var10_12 = var1_1.substring(var2_2);
                }
                var13_13 = Pattern.compile((String)this.patterns.get((int)var7_10).pattern).matcher((CharSequence)var10_12);
                var9_9 = var10_12;
                if (!var13_13.find()) continue;
                var9_9 = var10_12;
                if (var13_13.start() >= var5_6) continue;
                var5_6 = var13_13.start();
                var4_8 = var13_13.end();
                var6_7 = var7_10;
                --var4_8;
                var9_9 = var10_12;
                continue;
            }
            if (var9_9 != null) {
                Log.println("Parser.findFirstKeywordsByNoTerminal Error:" + var3_3);
                return null;
            }
            var10_12 = this.getStartKeyWordsByPatIndex(var7_10);
            var8_11 = var10_12.length;
            var7_10 = 0;
            do {
                if (var7_10 < var8_11) ** break;
                continue block0;
                var11_4.add((int)var10_12[var7_10]);
                ++var7_10;
            } while (true);
            break;
        } while (true);
        if (var6_7 < 0) return null;
        return new int[]{var6_7, var5_6 + var2_2, var4_8 + var2_2};
    }

    /*
     * Unable to fully structure code
     */
    private List<ParseResult> findKnowledgeSeg(String var1_1, double var2_2) throws Exception {
        block40 : {
            block36 : {
                block35 : {
                    this.targetSegment = var1_1;
                    var17_3 = new ArrayList();
                    if (!this.findMust(var1_1)) {
                        return var17_3;
                    }
                    this.acceptThr = var2_2;
                    var16_4 = new int[var1_1.length() * 2];
                    var4_5 = 0;
                    block0 : do {
                        if (System.currentTimeMillis() - this.timeoutStart > (long)Parser.timeOutThr) {
                            Log.println(String.valueOf((int)Parser.timeOutThr) + " millseconds. " + this.parserName + " Parse TimeOut: " + var1_1);
                            return var17_3;
                        }
                        if (this.checkOneMatch((List<ParseResult>)var17_3)) {
                            return var17_3;
                        }
                        var18_17 = this.keywordsDic.findFirstAll(var1_1, var4_5, 2);
                        if (var18_17 == null || var18_17.size() == 0) {
                            if (this.containStartRegularExpress) {
                                var4_5 = 0;
lbl18: // 2 sources:
                                if ((var16_4 = (Object)this.getStartRegularExpressResult(var1_1, var4_5)) != null) break;
                                return this.nomalResults((List<ParseResult>)var17_3);
                            }
                            break block35;
                        }
                        var7_8 = 0;
                        var5_6 = 0;
                        do {
                            block38 : {
                                block37 : {
                                    block39 : {
                                        if (var7_8 >= var18_17.size()) {
                                            var6_7 = var5_6;
                                            var5_6 = var4_5;
lbl27: // 2 sources:
                                            do {
                                                var4_5 = var5_6;
                                                if (var6_7 != 0) continue block0;
                                                var4_5 = this.getCandNextStartIndex(var1_1, var18_17.get(0)[1]);
                                                continue block0;
                                                break;
                                            } while (true);
                                        }
                                        System.currentTimeMillis();
                                        var19_18 = new ArrayList();
                                        var22_22 = this.keywordsDic.getKeywords(var18_17.get(var7_8)[0]);
                                        var19_18.add(var18_17.get(var7_8));
                                        var20_19 = new ArrayList();
                                        var21_20 = new ArrayList();
                                        var12_13 = this.findMust(var22_22.getKeyWords());
                                        if (var12_13 && var22_22.isEndkeyWords() && var22_22.getKeyWords().length() >= 1) {
                                            var20_19.add((int)0);
                                        }
                                        var16_4 = this.keywordsDic.findFirst(var1_1, var18_17.get(var7_8)[2] + 1, 1);
                                        System.currentTimeMillis();
                                        var15_16 = var22_22.isStartKeywordsContainsWildcard();
                                        block4 : do {
                                            if (var16_4 == null) lbl-1000: // 3 sources:
                                            {
                                                do {
                                                    System.currentTimeMillis();
                                                    if (!var12_13) break block36;
                                                    System.currentTimeMillis();
                                                    var6_7 = var21_20.size() - 1;
                                                    var8_9 = var20_19.size() - 1;
lbl52: // 2 sources:
                                                    if (var8_9 >= 0) break block37;
lbl53: // 3 sources:
                                                    do {
                                                        System.currentTimeMillis();
                                                        var6_7 = var5_6;
                                                        var5_6 = var4_5;
                                                        var4_5 = var6_7;
lbl58: // 2 sources:
                                                        if (var4_5 != 0) {
                                                            var6_7 = var4_5;
                                                            ** continue;
                                                        }
                                                        break block38;
                                                        break;
                                                    } while (true);
                                                    break;
                                                } while (true);
                                            }
                                            var14_15 = false;
                                            var6_7 = ((int[])var19_18.get(var19_18.size() - 1))[2];
                                            if (var16_4[1] <= var6_7 + 1) break block39;
                                            if (!var15_16 || var16_4[1] - var6_7 - true >= this.wildCardMaxMatchLength || StringProcess.findSegPunctuation(var1_1, (int)(var6_7 + true), (int)var16_4[1]) > 0) break;
                                            var21_20.add((int)var19_18.size());
                                            var6_7 = true;
lbl68: // 2 sources:
                                            do {
                                                var19_18.add(var16_4);
                                                var13_14 = var12_13;
                                                if (!var12_13) {
                                                    var13_14 = var12_13;
                                                    if (this.findMust(this.keywordsDic.getKeywords((int)var16_4[0]).getKeyWords())) {
                                                        var13_14 = true;
                                                    }
                                                }
                                                var12_13 = var14_15;
                                                if (var13_14) {
                                                    var12_13 = var14_15;
                                                    if (this.keywordsDic.getKeywords((int)var16_4[0]).isEndkeyWords()) {
                                                        var20_19.add((int)(var19_18.size() - 1));
                                                        var12_13 = true;
                                                    }
                                                }
                                                var13_14 = var14_15 = this.addOtherStartEndKeywords(var1_1, (int[])var16_4, (List<int[]>)var19_18, (List<Integer>)var20_19, var12_13, var13_14);
                                                if (var6_7 != false) {
                                                    var22_23 = this.keywordsDic.findFirst(var1_1, (int)(var16_4[1] + true), (int)var16_4[2], 1);
                                                    var13_14 = var14_15;
                                                    if (var22_23 != null) {
                                                        var13_14 = var14_15;
                                                        if (var22_23[2] == var16_4[2]) {
                                                            var19_18.add(var22_23);
                                                            var12_13 = var14_15;
                                                            if (!var14_15) {
                                                                var12_13 = var14_15;
                                                                if (this.findMust(this.keywordsDic.getKeywords(var22_23[0]).getKeyWords())) {
                                                                    var12_13 = true;
                                                                }
                                                            }
                                                            var13_14 = var12_13;
                                                            if (var12_13) {
                                                                var13_14 = var12_13;
                                                                if (this.keywordsDic.getKeywords(var22_23[0]).isEndkeyWords()) {
                                                                    var20_19.add((int)(var19_18.size() - 1));
                                                                    var13_14 = var12_13;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                                var16_4 = this.keywordsDic.findFirst(var1_1, (int)(var16_4[2] + true), 1);
                                                var12_13 = var13_14;
                                                continue block4;
                                                break;
                                            } while (true);
                                            break;
                                        } while (true);
                                        var6_7 = var16_4[1];
                                        ** GOTO lbl-1000
                                    }
                                    if (var16_4[1] <= ((int[])var19_18.get(var19_18.size() - 1))[2]) {
                                        Log.i("Parser", "findKnowledge Error:\t" + var1_1);
                                        ** continue;
                                    }
                                    break block40;
                                }
                                var9_10 = ((int[])var19_18.get((Integer)var20_19.get(var8_9)))[2];
                                if (!StringProcess.ifCharTypeSame(var1_1, var9_10, var9_10 + 1)) ** GOTO lbl116
                                do {
                                    --var8_9;
                                    ** GOTO lbl52
                                    break;
                                } while (true);
lbl116: // 1 sources:
                                var10_11 = (Integer)var20_19.get(var8_9);
                                this.containWildcard = false;
                                block11 : do {
                                    if (var6_7 >= 0) ** GOTO lbl127
lbl120: // 2 sources:
                                    do {
                                        var11_12 = ((int[])var19_18.get(0))[1];
                                        var9_10 = ((int[])var19_18.get(var10_11))[2] + 1;
                                        if (var9_10 - var11_12 >= 1) break block11;
                                        var5_6 = 1;
                                        var4_5 = var9_10;
                                        ** GOTO lbl53
                                        break;
                                    } while (true);
lbl127: // 1 sources:
                                    if (var10_11 >= (Integer)var21_20.get((int)var6_7)) {
                                        this.containWildcard = true;
                                        ** continue;
                                    }
                                    --var6_7;
                                } while (true);
                                if ((var16_4 = this.ifRightKnowledge(var1_1, (List<int[]>)var19_18, 0, var10_11)) == null) ** continue;
                                var17_3.add(var16_4);
                                var4_5 = var16_4.getEndPositionInSentence();
                                var5_6 = 1;
                                ** continue;
                            }
                            ++var7_8;
                            var6_7 = var5_6;
                            var5_6 = var4_5;
                            var4_5 = var6_7;
                        } while (true);
                        break;
                    } while (true);
                    var17_3.add(var16_4);
                    var4_5 = var16_4.getEndPositionInSentence();
                    ** GOTO lbl18
                }
                return var17_3;
            }
            var6_7 = var4_5;
            var4_5 = var5_6;
            var5_6 = var6_7;
            ** GOTO lbl58
        }
        var6_7 = false;
        ** while (true)
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean findMust(String list) {
        if (this.keywordsDic.getMustKeywordsCount() == 0 || (list = this.keywordsDic.find((String)((Object)list), 8)) != null && list.size() != 0) {
            return true;
        }
        return false;
    }

    private IntInt findMustKeyWordsInPat(int n, int n2) {
        return this.findMustKeyWordsInPat(this.patterns.get(n), n2);
    }

    private IntInt findMustKeyWordsInPat(PatternForNER patternForNER, int n) {
        if ((n = patternForNER.findKeywords(n, 8)) >= 0) {
            return patternForNER.getMustKeywords()[n];
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    private String[] firstRegularResults(String string2, String string3, boolean bl) {
        String[] arrstring;
        if (!bl) {
            Log.println("Parser.firstRegularResults Error:" + string3);
            return null;
        }
        if (!(string3 = Pattern.compile((String)string3).matcher((CharSequence)string2)).find()) return null;
        {
            arrstring = new String[2];
            arrstring[0] = string3.group();
            this.currentStartRegularStartIndexInSource = string3.start();
            if (string3.end() < string2.length()) {
                arrstring[1] = string2.substring(string3.end());
                return arrstring;
            }
        }
        arrstring[1] = "";
        return arrstring;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private List<Integer> getCandPatternIndex(List<int[]> var1_1, int var2_2, int var3_3) {
        var8_4 = new HashSet();
        var10_5 = this.keywordsDic.getKeywords(((int[])var1_1.get(0))[0]);
        var9_6 = new ArrayList(2);
        if ((var10_5 = StringProcess.getASCType(var10_5.getKeyWords())) == StringProcess.ASCType.Number) {
            if (this.startContainNUM) {
                var9_6.add((int)this.numberReplaceIndex);
            }
            if (this.startContainCHAR) {
                var9_6.add((int)this.charReplaceIndex);
            }
        } else if (var10_5 == StringProcess.ASCType.EnglishLowerCase || var10_5 == StringProcess.ASCType.EnglishUpper) {
            if (this.startContainENG) {
                var9_6.add((int)this.engReplaceIndex);
            }
            if (this.startContainCHAR) {
                var9_6.add((int)this.charReplaceIndex);
            }
        }
        if (var9_6.size() == 0) {
            var9_6.add((int)((int[])var1_1.get(0))[0]);
        }
        var4_7 = 0;
        do {
            if (var4_7 >= var9_6.size()) break;
            this.keyWords2Count.put((Object)((Integer)var9_6.get(var4_7)), (Object)1);
            ++var4_7;
        } while (true);
        var10_5 = var9_6.iterator();
        block1 : do {
            if (!var10_5.hasNext()) {
                var4_7 = var2_2 + 1;
                break;
            }
            var5_8 = (Integer)var10_5.next();
            var11_11 = this.keywordsDic.getPatIndexByKeywords(var5_8);
            var6_9 = var11_11.length;
            var4_7 = 0;
            do {
                if (var4_7 >= var6_9) continue block1;
                var7_10 = var11_11[var4_7];
                if (this.containKeyWordsInPat(var7_10, var5_8, 2)) {
                    var8_4.add((Object)var7_10);
                }
                ++var4_7;
            } while (true);
            break;
        } while (true);
        block3 : do {
            if (var4_7 <= var3_3) {
                var10_5 = StringProcess.getASCType(this.keywordsDic.getKeywords(((int[])var1_1.get(var4_7))[0]).getKeyWords());
                var9_6.clear();
                if (var10_5 == StringProcess.ASCType.Number) {
                    if (this.containNUM) {
                        var9_6.add((int)this.numberReplaceIndex);
                    }
                    if (this.containCHAR) {
                        var9_6.add((int)this.charReplaceIndex);
                    }
                } else if (var10_5 == StringProcess.ASCType.EnglishLowerCase || var10_5 == StringProcess.ASCType.EnglishUpper) {
                    if (this.containENG) {
                        var9_6.add((int)this.engReplaceIndex);
                    }
                    if (this.containCHAR) {
                        var9_6.add((int)this.charReplaceIndex);
                    }
                }
                if (var9_6.size() == 0) {
                    var9_6.add((int)((int[])var1_1.get(var4_7))[0]);
                }
                var2_2 = 0;
                if (var4_7 < var3_3) {
                    var2_2 = ((int[])var1_1.get(var4_7))[1] == ((int[])var1_1.get(var4_7 + 1))[1] ? 1 : 0;
                }
            } else {
                var1_1 = new HashSet(var8_4.size());
                var8_4 = var8_4.iterator();
lbl62: // 3 sources:
                if (!var8_4.hasNext()) {
                    var1_1 = new ArrayList((Collection)var1_1);
                    Collections.sort((List)var1_1);
                    return var1_1;
                }
                break;
            }
            var10_5 = var9_6.iterator();
            do {
                if (!var10_5.hasNext()) {
                    ++var4_7;
                    continue block3;
                }
                var5_8 = (Integer)var10_5.next();
                if (!this.keyWords2Count.containsKey((Object)var5_8)) {
                    this.keyWords2Count.put((Object)var5_8, (Object)1);
                } else {
                    this.keyWords2Count.put((Object)var5_8, (Object)((Integer)this.keyWords2Count.get((Object)var5_8) + 1));
                }
                if (this.containWildCard || var9_6.size() != 1 || var2_2 != 0) continue;
                this.retainAll((Collection<Integer>)var8_4, this.keywordsDic.getPatIndexByKeywords(var5_8));
            } while (true);
            break;
        } while (true);
        var9_6 = (Integer)var8_4.next();
        var3_3 = 0;
        var10_5 = this.getMustKeyWordsByPatIndex(var9_6.intValue());
        var4_7 = var10_5.length;
        var2_2 = 0;
        do {
            if (var2_2 < var4_7) ** GOTO lbl89
            var2_2 = var3_3;
            ** GOTO lbl95
lbl89: // 1 sources:
            var11_11 = var10_5[var2_2];
            if (this.keyWords2Count.containsKey((Object)var11_11.getFirst())) ** GOTO lbl93
            var2_2 = 1;
            ** GOTO lbl95
lbl93: // 1 sources:
            if ((Integer)this.keyWords2Count.get((Object)var11_11.getFirst()) >= var11_11.getSecond()) ** GOTO lbl98
            var2_2 = 1;
lbl95: // 3 sources:
            if (var2_2 == 0) break;
            this.forbidMatchIndex.set(var9_6.intValue(), true);
            ** GOTO lbl62
lbl98: // 1 sources:
            ++var2_2;
        } while (true);
        var1_1.add((Object)var9_6);
        ** GOTO lbl62
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private List<Integer> getCandPatternIndexByNonTerminal(String var1_1, String var2_2) {
        var7_3 = new ArrayList();
        if (var1_1.endsWith("-R>")) ** GOTO lbl7
        if (this.currentStartRegular) ** GOTO lbl15
        if ((var2_2 = this.getStartKeywords((String)var2_2)) == null) {
            return var7_3;
        }
        ** GOTO lbl21
lbl7: // 1 sources:
        var1_1 = this.noTerminal2patternIndex.get(var1_1).iterator();
        do {
            if (!var1_1.hasNext()) {
                return var7_3;
            }
            var3_4 = var1_1.next();
            if (this.patterns.get((int)var3_4).isRegularExpression == false) return new ArrayList();
            var7_3.add((int)var3_4);
        } while (true);
lbl15: // 1 sources:
        var1_1 = this.noTerminal2patternIndex.get(var1_1).iterator();
        do {
            if (!var1_1.hasNext()) {
                return var7_3;
            }
            var7_3.add((int)var1_1.next());
        } while (true);
lbl21: // 1 sources:
        var1_1 = this.noTerminal2patternIndex.get(var1_1).iterator();
        block2 : do {
            block11 : {
                if (!var1_1.hasNext()) {
                    return var7_3;
                }
                var5_7 = var1_1.next();
                if (this.forbidMatchIndex.get(var5_7)) continue;
                var8_9 = this.getStartKeyWordsByPatIndex(var5_7);
                if (var8_9 == null) ** GOTO lbl42
                var4_6 = var8_9.length;
                var3_5 = 0;
                do {
                    if (var3_5 < var4_6) ** GOTO lbl35
                    var3_5 = 0;
                    ** GOTO lbl37
lbl35: // 1 sources:
                    if (var2_2.contains((Object)((int)var8_9[var3_5]))) {
                        var3_5 = 1;
lbl37: // 2 sources:
                        if (var3_5 != 0) break;
                        var4_6 = 1;
                        break block11;
                    }
                    ++var3_5;
                } while (true);
lbl42: // 2 sources:
                var4_6 = 0;
            }
            if (var4_6 != 0) ** GOTO lbl50
            var8_9 = this.getMustKeyWordsByPatIndex(var5_7);
            var6_8 = var8_9.length;
            var3_5 = 0;
            do {
                if (var3_5 < var6_8) ** GOTO lbl52
lbl50: // 2 sources:
                var3_5 = 0;
                ** GOTO lbl58
lbl52: // 1 sources:
                var9_10 = var8_9[var3_5];
                if (this.keyWords2Count.containsKey((Object)var9_10.getFirst())) ** GOTO lbl56
                var3_5 = 1;
                ** GOTO lbl58
lbl56: // 1 sources:
                if ((Integer)this.keyWords2Count.get((Object)var9_10.getFirst()) < var9_10.getSecond()) {
                    var3_5 = 1;
lbl58: // 3 sources:
                    if (var3_5 == 0) break;
                    this.forbidMatchIndex.set(var5_7, true);
                    continue block2;
                }
                ++var3_5;
            } while (true);
            if (var4_6 != 0) continue;
            var7_3.add((int)var5_7);
        } while (true);
    }

    private List<String> getConst(String arrstring) {
        arrstring = arrstring.split("[<>\\|]");
        ArrayList arrayList = new ArrayList();
        int n = arrstring.length;
        int n2 = 0;
        while (n2 < n) {
            String string2 = arrstring[n2];
            if (string2.startsWith("!") && !string2.equals((Object)"!\u7a7a")) {
                arrayList.add(string2);
            }
            ++n2;
        }
        return arrayList;
    }

    private boolean[] getKeyWords(PatternForNER patternForNER, Map<Integer, Integer> map) {
        ArrayList arrayList = new ArrayList();
        patternForNER = (PatternForNER)this.getKeyWords(patternForNER, map, (List<String>)arrayList);
        patternForNER[0] = (PatternForNER)(patternForNER[0] | this.getMustKeyWords((List<String>)arrayList, map));
        return patternForNER;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private boolean[] getKeyWords(PatternForNER var1_1, Map<Integer, Integer> var2_2, List<String> var3_3) {
        block21 : {
            if (var1_1.isRegularExpression) {
                var1_1 = (String[])new boolean[4];
                var1_1[3] = (String)true ? 1 : 0;
                return var1_1;
            }
            var1_1 = StringProcess.noRegexSplit(var1_1.pattern, Parser.segPatsSignal);
            var9_4 = false;
            var8_5 = false;
            var4_6 = 0;
            block0 : do {
                if (var4_6 >= var1_1.length) {
                    return new boolean[]{var8_5, this.getTwoEndsKeyWords(var1_1, var2_2, 2), this.getTwoEndsKeyWords(var1_1, var2_2, 4), var9_4};
                }
                var11_11 = var1_1[var4_6];
                if (!var11_11.startsWith("<?")) ** GOTO lbl23
                var3_3.add((String)var11_11);
                if (var4_6 != 0) ** GOTO lbl-1000
                var12_12 = this.noTerminal2patternIndex.get(var11_11);
                if (var12_12 != null) break;
                Log.i("Parser", "!noTerminal2patternIndex.containsKey(" + (String)var11_11 + ")");
                var10_10 = var9_4;
                var9_4 = var8_5;
                var8_5 = var10_10;
                ** GOTO lbl51
lbl23: // 1 sources:
                if (var11_11.equals((Object)"<*>")) {
                    var8_5 = var9_4;
                    var9_4 = true;
                } else if (var11_11.equals((Object)"<#m>") || var11_11.equals((Object)"<#yyyy>") || var11_11.equals((Object)"<#MM>") || var11_11.equals((Object)"<#dd>") || var11_11.equals((Object)"<#HH>") || var11_11.equals((Object)"<#mm>") || var11_11.equals((Object)"<#ss>")) {
                    if (this.numberReplaceIndex < 0) {
                        this.numberReplaceIndex = this.keywordsDic.addKeywords("NUM", 64);
                    }
                    Parser.addTempMustKeywords(var2_2, this.numberReplaceIndex);
                    var10_10 = var8_5;
                    var8_5 = var9_4;
                    var9_4 = var10_10;
                } else if (var11_11.equals((Object)"<#char>")) {
                    if (this.charReplaceIndex < 0) {
                        this.charReplaceIndex = this.keywordsDic.addKeywords("CHAR", 64);
                    }
                    Parser.addTempMustKeywords(var2_2, this.charReplaceIndex);
                    var10_10 = var8_5;
                    var8_5 = var9_4;
                    var9_4 = var10_10;
                } else {
                    if (!var11_11.equals((Object)"<#eng>")) break block21;
                    if (this.engReplaceIndex < 0) {
                        this.engReplaceIndex = this.keywordsDic.addKeywords("ENG", 64);
                    }
                    Parser.addTempMustKeywords(var2_2, this.engReplaceIndex);
                    var10_10 = var8_5;
                    var8_5 = var9_4;
                    var9_4 = var10_10;
                }
lbl51: // 8 sources:
                do {
                    ++var4_6;
                    var10_10 = var9_4;
                    var9_4 = var8_5;
                    var8_5 = var10_10;
                    continue block0;
                    break;
                } while (true);
                break;
            } while (true);
            var11_11 = var12_12.iterator();
            do {
                if (var11_11.hasNext()) ** GOTO lbl64
                var10_10 = var8_5;
                var8_5 = var9_4;
                var9_4 = var10_10;
                ** GOTO lbl51
lbl64: // 1 sources:
                var5_7 = (Integer)var11_11.next();
            } while (!this.patterns.get((int)var5_7).isRegularExpression);
            var10_10 = true;
            var9_4 = var8_5;
            var8_5 = var10_10;
            ** GOTO lbl51
        }
        var11_11 = StringProcess.noRegexSplit(StringProcess.trim((String)var11_11, new Character[]{Character.valueOf((char)'<'), Character.valueOf((char)'>')}), "|");
        var6_8 = var11_11.length;
        var5_7 = 0;
        block3 : do {
            if (var5_7 >= var6_8) lbl-1000: // 2 sources:
            {
                var10_10 = var8_5;
                var8_5 = var9_4;
                var9_4 = var10_10;
                ** continue;
            }
            var12_12 = StringProcess.trim(var11_11[var5_7], new Character[]{Character.valueOf((char)'*')});
            if (var12_12.equals((Object)"!\u7a7a")) ** GOTO lbl-1000
            if (var12_12.startsWith("!") && !var12_12.equals((Object)"!")) ** GOTO lbl89
            var7_9 = this.keywordsDic.addKeywords((String)var12_12, 1);
            if (var11_11.length == 1) {
                Parser.addTempMustKeywords(var2_2, var7_9);
            } else {
                Parser.setTempKeywords(var2_2, var7_9, 1);
            }
            ** GOTO lbl-1000
lbl89: // 1 sources:
            var12_12 = (List)this.constant.get(var12_12);
            var13_13 = var12_12.iterator();
            do {
                if (!var13_13.hasNext()) lbl-1000: // 4 sources:
                {
                    ++var5_7;
                    continue block3;
                }
                var14_14 = (StringString)var13_13.next();
                var7_9 = this.keywordsDic.getKeyWordsIndex(var14_14.getKeyWord());
                if (var11_11.length == 1 && var12_12.size() == 1) {
                    Parser.addTempMustKeywords(var2_2, var7_9);
                    continue;
                }
                Parser.setTempKeywords(var2_2, var7_9, 1);
            } while (true);
            break;
        } while (true);
    }

    private int[] getKeyWordsByPatIndex(int n) {
        return this.patterns.get(n).getKeywords();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void getKeyWordsOfNonTerminal(Map<Integer, Integer> map, String iterator, int n) {
        if (this.noTerminal2patternIndex.containsKey(iterator)) {
            iterator = this.noTerminal2patternIndex.get(iterator).iterator();
            while (iterator.hasNext()) {
                for (int n2 : this.getKeyWordsByPatIndex(iterator.next())) {
                    Parser.setTempKeywords(map, n2, n);
                    if (!this.finalProduction) continue;
                    this.keywordsDic.setKeyWordsType(n2, n);
                }
            }
        }
    }

    /*
     * Exception decompiling
     */
    private boolean getMustKeyWords(List<String> var1_1, Map<Integer, Integer> var2_2) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[UNCONDITIONALDOLOOP]], but top level block is 5[UNCONDITIONALDOLOOP]
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

    private IntInt[] getMustKeyWordsByPatIndex(int n) {
        return this.patterns.get(n).getMustKeywords();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void getSegReplace(List<ParseResult> var1_1) {
        block8 : {
            var4_2 = var1_1.iterator();
            block0 : do {
                if (!var4_2.hasNext()) {
                    return;
                }
                var5_5 = (ParseResult)var4_2.next();
                var6_6 = var5_5.getSegments_pattern();
                var7_7 = var5_5.getSegments();
                var8_8 = new ArrayList(var6_6.size());
                var2_3 = 0;
                block1 : do {
                    if (var2_3 < var6_6.size() && (var9_9 = var6_6.get(var2_3)) != null) {
                        var10_10 = this.getConst(var9_9);
                        if (var10_10.size() != 0 && !var7_7.get(var2_3).equals((Object)"")) break block0;
                        var1_1 = var7_7.get(var2_3);
                    } else {
                        var5_5.setSegments_replace((List<String>)var8_8);
                        continue block0;
                    }
lbl18: // 3 sources:
                    do {
                        var8_8.add(var1_1);
                        ++var2_3;
                        continue block1;
                        break;
                    } while (true);
                    break;
                } while (true);
                break;
            } while (true);
            var1_1 = var10_10.iterator();
            do {
                if (!var1_1.hasNext()) {
                    var1_1 = null;
                    break block8;
                }
                var11_11 = var1_1.next();
            } while ((var3_4 = this.containInDic(var11_11 = this.constant.get(var11_11), var7_7.get(var2_3))) < 0);
            var1_1 = ((StringString)var11_11.get(var3_4)).getSecond();
        }
        if (var1_1 != null) ** GOTO lbl18
        Log.i("Parser", "getSegReplace Error:" + var9_9 + "\t" + var7_7.get(var2_3) + "\t" + var10_10);
        var1_1 = var7_7.get(var2_3);
        ** while (true)
    }

    private int[] getStartKeyWordsByPatIndex(int n) {
        return this.patterns.get(n).getStartKeywords();
    }

    /*
     * Enabled aggressive block sorting
     */
    private HashSet<Integer> getStartKeywords(String list) {
        list = list.equals((Object)"") ? this.keywordsDic.startWith(PatternForNER.endPatternReplaceStr, 0, 1) : this.keywordsDic.startWith((String)((Object)list), 0, 1);
        if (list == null || list.size() == 0) {
            return null;
        }
        HashSet hashSet = new HashSet();
        list = list.iterator();
        while (list.hasNext()) {
            int[] arrn = (int[])list.next();
            String string2 = this.keywordsDic.getKeywords(arrn[0]).getKeyWords();
            if (string2.length() == 1) {
                char c2 = string2.charAt(0);
                if (c2 >= '0' && c2 <= '9') {
                    if (this.containNUM) {
                        hashSet.add((Object)this.numberReplaceIndex);
                    }
                    if (this.containCHAR) {
                        hashSet.add((Object)this.charReplaceIndex);
                    }
                    if (this.containCHAR || this.containNUM) continue;
                    hashSet.add((Object)arrn[0]);
                    continue;
                }
                if (c2 >= 'a' && c2 <= 'z' || c2 >= 'A' && c2 <= 'Z') {
                    if (this.containENG) {
                        hashSet.add((Object)this.engReplaceIndex);
                    }
                    if (this.containCHAR) {
                        hashSet.add((Object)this.charReplaceIndex);
                    }
                    if (this.containCHAR || this.containENG) continue;
                    hashSet.add((Object)arrn[0]);
                    continue;
                }
                hashSet.add((Object)arrn[0]);
                continue;
            }
            hashSet.add((Object)arrn[0]);
        }
        return hashSet;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private ParseResult getStartRegularExpressResult(String var1_1, int var2_2) {
        if (this.startRegularExpressPatIndex != null) ** GOTO lbl8
        var5_3 = new HashSet();
        var3_4 = 0;
        do {
            if (var3_4 >= this.patterns.size()) {
                this.startRegularExpressPatIndex = new ArrayList((Collection)var5_3);
                Collections.sort(this.startRegularExpressPatIndex);
lbl8: // 2 sources:
                var3_4 = var2_2;
                if (var2_2 < this.startTrimLength) {
                    var3_4 = this.startTrimLength;
                }
                var2_2 = var4_6 = var1_1.length() - this.endTrimLength;
                if (var4_6 > var1_1.length() - this.endTrimLength) {
                    var2_2 = var1_1.length() - this.endTrimLength;
                }
                if (this.findMust((String)(var1_1 = var1_1.substring(var3_4, var2_2)))) break;
                return null;
            }
            var6_5 = this.patterns.get(var3_4);
            if (var6_5.containsStartRegularExpress() && !var6_5.leftProduction.startsWith("<??")) {
                var5_3.add((int)var3_4);
            }
            ++var3_4;
        } while (true);
        if (var1_1.length() == StringProcess.getNumberCount((String)var1_1)) {
            return null;
        }
        this.keyWords2Count.clear();
        this.forbidMatchIndex.clear();
        this.knowledge.clear();
        this.currentStartRegular = true;
        var1_1 = this.parse((String)var1_1, var3_4 -= this.startTrimLength, this.startRegularExpressPatIndex);
        System.currentTimeMillis();
        if (var1_1 == null) return null;
        if (var1_1.getType() == ParseType.NoPass) return null;
        if (var1_1.getPattern().score < this.acceptThr) return null;
        var4_6 = this.startTrimLength;
        var1_1.setStartPositionInSentence(this.currentStartRegularStartIndexInSource);
        if (var1_1.knowledge == null) {
            var1_1.knowledge = new HashMap();
        }
        if (this.knowledge == null || this.knowledge.size() == 0) {
            var1_1.knowledge.put((Object)StringProcess.trim(var1_1.getPattern().leftProduction, new Character[]{Character.valueOf((char)'<'), Character.valueOf((char)'>'), Character.valueOf((char)'?')}), (Object)new StringIntInt(null, var3_4, var2_2 - var4_6));
        } else {
            var1_1.knowledge.putAll(this.knowledge);
        }
        var5_3 = var1_1.getPattern().resultContent.iterator();
        do {
            if (!var5_3.hasNext()) {
                var1_1.setConfidence(var1_1.getPattern().score);
                var1_1.setParserName(this.parserName);
                return var1_1;
            }
            var6_5 = (PatternActionContent)var5_3.next();
            if (var6_5.contents == null || var6_5.contents.size() <= 0 || var6_5.contents.get(0).getNum() != -1) continue;
            var1_1.knowledge.put((Object)var6_5.remark, (Object)new StringIntInt(var6_5.contents.get(0).getName(), 0, 0));
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private boolean getTwoEndsKeyWords(String[] var1_1, Map<Integer, Integer> var2_2, int var3_3) {
        block37 : {
            var4_4 = 0;
            var8_5 = var3_3 == 2;
            if (!var8_5) {
                var4_4 = var1_1.length - 1;
            }
            var11_6 = false;
            var5_7 = 0;
            var7_8 = var4_4;
            var4_4 = var5_7;
            block0 : while (var7_8 >= 0) {
                if (var7_8 >= var1_1.length) return var11_6;
                if (var4_4 != 0) {
                    return var11_6;
                }
                var13_13 = var1_1[var7_8];
                if (!var13_13.startsWith("<?")) ** GOTO lbl33
                if (var4_4 == 0) {
                    if (!var8_5) {
                        var14_14 = this.noTerminal2patternIndex.get(var13_13);
                        if (var14_14 == null) {
                            Log.i("Parser", "!noTerminal2patternIndex.containsKey(" + (String)var13_13 + ")");
                            continue;
                        }
                        if (var14_14.size() == 1 && this.patterns.get((int)var14_14.get((int)0).intValue()).isRegularExpression) {
                            var4_4 = 1;
                            continue;
                        }
                        this.getKeyWordsOfNonTerminal(var2_2, (String)var13_13, var3_3);
                    } else {
                        this.getKeyWordsOfNonTerminal(var2_2, (String)var13_13, var3_3);
                    }
                    var4_4 = 1;
                }
                var12_12 = var11_6;
                if (var8_5) ** GOTO lbl67
                var12_12 = var11_6;
                if (var7_8 != var1_1.length - 1) ** GOTO lbl67
                ** GOTO lbl59
lbl33: // 1 sources:
                var12_12 = var11_6;
                if (var13_13.equals((Object)"<*>")) ** GOTO lbl137
                if (!var13_13.equals((Object)"<#m>") && !var13_13.equals((Object)"<#yyyy>") && !var13_13.equals((Object)"<#MM>") && !var13_13.equals((Object)"<#dd>") && !var13_13.equals((Object)"<#HH>") && !var13_13.equals((Object)"<#mm>") && !var13_13.equals((Object)"<#ss>")) ** GOTO lbl43
                var12_12 = var11_6;
                if (var4_4 != 0) ** GOTO lbl137
                Parser.setTempKeywords(var2_2, this.numberReplaceIndex, var3_3);
                if (this.finalProduction) {
                    this.keywordsDic.setKeyWordsType(this.numberReplaceIndex, var3_3);
                }
                var4_4 = 1;
                ** GOTO lbl78
lbl43: // 1 sources:
                if (!var13_13.equals((Object)"<#char>")) ** GOTO lbl51
                var12_12 = var11_6;
                if (var4_4 != 0) ** GOTO lbl137
                Parser.setTempKeywords(var2_2, this.charReplaceIndex, var3_3);
                if (this.finalProduction) {
                    this.keywordsDic.setKeyWordsType(this.charReplaceIndex, var3_3);
                }
                var4_4 = 1;
                ** GOTO lbl78
lbl51: // 1 sources:
                if (!var13_13.equals((Object)"<#eng>")) break block37;
                var12_12 = var11_6;
                if (var4_4 != 0) ** GOTO lbl137
                Parser.setTempKeywords(var2_2, this.engReplaceIndex, var3_3);
                if (this.finalProduction) {
                    this.keywordsDic.setKeyWordsType(this.engReplaceIndex, var3_3);
                }
                var4_4 = 1;
                ** GOTO lbl78
lbl59: // 1 sources:
                var14_14 = this.noTerminal2patternIndex.get(var13_13).iterator();
                do {
                    if (!var14_14.hasNext()) {
                        var12_12 = var11_6;
                        break;
                    }
                    var5_7 = (Integer)var14_14.next();
                    var11_6 = var12_12 = this.patterns.get(var5_7).containsEnd();
                } while (!var12_12);
lbl67: // 4 sources:
                if (!var8_5 || var7_8 != 0) {
                    var11_6 = var12_12;
                } else {
                    var13_13 = this.noTerminal2patternIndex.get(var13_13).iterator();
                    var11_6 = var12_12;
                    while (var13_13.hasNext()) {
                        var5_7 = (Integer)var13_13.next();
                        var11_6 = var12_12 = this.patterns.get(var5_7).containsStart();
                        if (!var12_12) continue;
                        var11_6 = var12_12;
                        break;
                    }
                }
lbl78: // 8 sources:
                do {
                    if (var8_5) {
                        ++var7_8;
                        continue block0;
                    }
                    --var7_8;
                    continue block0;
                    break;
                } while (true);
            }
            return var11_6;
        }
        var14_14 = StringProcess.noRegexSplit(StringProcess.trim((String)var13_13, new Character[]{Character.valueOf((char)'<'), Character.valueOf((char)'>')}), "|");
        var10_11 = var14_14.length;
        var6_9 = 0;
        var5_7 = 0;
        var9_10 = 0;
        block4 : do {
            if (var9_10 < var10_11) ** GOTO lbl99
            var12_12 = var11_6;
            if (var6_9 == 0) ** GOTO lbl137
            var12_12 = var11_6;
            if (var5_7 != 0) ** GOTO lbl137
            var4_4 = 1;
            ** GOTO lbl78
lbl99: // 1 sources:
            var15_15 = StringProcess.trim(var14_14[var9_10], new Character[]{Character.valueOf((char)'*')});
            if (var15_15.equals((Object)"!\u7a7a")) {
                var5_7 = 1;
            } else if (var15_15.equals((Object)PatternForNER.endPatternReplaceStr)) {
                if (!var8_5 && var7_8 == var1_1.length - 1) {
                    var11_6 = true;
                } else if (!var8_5) {
                    Log.i("Parser", "End define Error:" + (String)var13_13);
                }
                var6_9 = this.keywordsDic.getKeyWordsIndex((String)var15_15);
                Parser.setTempKeywords(var2_2, var6_9, var3_3);
                if (this.finalProduction) {
                    this.keywordsDic.setKeyWordsType(var6_9, var3_3);
                }
                var6_9 = 1;
            } else if (var15_15.equals((Object)PatternForNER.startPatternReplaceStr)) {
                if (var8_5 && var7_8 == 0) {
                    var12_12 = true;
                } else {
                    var12_12 = var11_6;
                    if (var8_5) {
                        Log.i("Parser", "Start define Error:" + (String)var13_13);
                        var12_12 = var11_6;
                    }
                }
                var6_9 = this.keywordsDic.getKeyWordsIndex((String)var15_15);
                Parser.setTempKeywords(var2_2, var6_9, var3_3);
                if (this.finalProduction) {
                    this.keywordsDic.setKeyWordsType(var6_9, var3_3);
                }
                var6_9 = 1;
                var11_6 = var12_12;
            } else {
                if (!var15_15.equals((Object)"!") && var15_15.startsWith("!")) break;
                var6_9 = this.keywordsDic.getKeyWordsIndex((String)var15_15);
                Parser.setTempKeywords(var2_2, var6_9, var3_3);
                if (this.finalProduction) {
                    this.keywordsDic.setKeyWordsType(var6_9, var3_3);
                }
                var6_9 = 1;
            }
            ** GOTO lbl139
lbl137: // 6 sources:
            var11_6 = var12_12;
            ** continue;
lbl139: // 5 sources:
            do {
                ++var9_10;
                continue block4;
                break;
            } while (true);
            break;
        } while (true);
        var15_15 = ((List)this.constant.get(var15_15)).iterator();
        do {
            if (!var15_15.hasNext()) {
                var6_9 = 1;
                ** continue;
            }
            var16_16 = (StringString)var15_15.next();
            var6_9 = this.keywordsDic.getKeyWordsIndex(var16_16.getKeyWord());
            Parser.setTempKeywords(var2_2, var6_9, var3_3);
            if (!this.finalProduction) continue;
            this.keywordsDic.setKeyWordsType(var6_9, var3_3);
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    private ParseResult ifRightKnowledge(String object, List<int[]> object2, int n, int n2) {
        int n3;
        int n4 = ((int[])object2.get(0))[1];
        if (n4 < this.startTrimLength) {
            n4 = this.startTrimLength;
        }
        int n5 = n3 = ((int[])object2.get(n2))[2] + 1;
        if (n3 > object.length() - this.endTrimLength) {
            n5 = object.length() - this.endTrimLength;
        }
        if (!this.findMust((String)(object = object.substring(n4, n5)))) {
            return null;
        }
        if (object.length() == StringProcess.getNumberCount((String)object)) {
            return null;
        }
        if (object.contains((CharSequence)"GPRS\u4f18\u60e0 \u7701\u5185GP5M\u514d\u8d39\uff0c\u5269\u4f59 5.00 MB")) {
            Log.println("ok");
        }
        this.keyWords2Count.clear();
        this.forbidMatchIndex.clear();
        this.knowledge.clear();
        this.currentStartRegular = false;
        object2 = this.getCandPatternIndex((List<int[]>)object2, n, n2);
        System.currentTimeMillis();
        n = n4 - this.startTrimLength;
        object = this.parse((String)object, n, (List<Integer>)object2);
        System.currentTimeMillis();
        if (object != null && object.getType() != ParseType.NoPass && object.getPattern().score >= this.acceptThr) {
            n2 = n5 - this.startTrimLength;
            object.setStartPositionInSentence(n);
            object.setEndPositionInSentence(n2);
            if (object.knowledge == null) {
                object.knowledge = new HashMap();
            }
            if (this.knowledge == null || this.knowledge.size() == 0) {
                object.knowledge.put((Object)StringProcess.trim(object.getPattern().leftProduction, Character.valueOf((char)'<'), Character.valueOf((char)'>'), Character.valueOf((char)'?')), (Object)new StringIntInt(null, n, n2));
            } else {
                object.knowledge.putAll(this.knowledge);
            }
        } else {
            return null;
        }
        object2 = object.getPattern().resultContent.iterator();
        do {
            if (!object2.hasNext()) {
                object.setConfidence(object.getPattern().score);
                object.setParserName(this.parserName);
                return object;
            }
            PatternActionContent patternActionContent = (PatternActionContent)object2.next();
            if (patternActionContent.contents == null || patternActionContent.contents.size() <= 0 || patternActionContent.contents.get(0).getNum() != -1) continue;
            object.knowledge.put((Object)patternActionContent.remark, (Object)new StringIntInt(patternActionContent.contents.get(0).getName(), 0, 0));
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void initialPatternsNumber(List<String> var1_1) {
        var2_2 = 0;
        block0 : do {
            if (var2_2 >= var1_1.size()) {
                this.patterns = new ArrayList(this.patternsCount);
                this.forbidMatchIndex = new BitSet(this.patternsCount);
                this.keyWords2Count = new HashMap();
                this.knowledge = new HashMap();
                this.keywordsDic = new ParserKeywordsDic();
                return;
            }
            var1_1.set(var2_2, var1_1.get(var2_2).trim());
            if (!var1_1.get(var2_2).startsWith("<?")) ** GOTO lbl-1000
            ++this.patternsCount;
            var3_3 = var1_1.get(var2_2).indexOf("|||");
            do {
                if (var3_3 <= 0) lbl-1000: // 2 sources:
                {
                    ++var2_2;
                    continue block0;
                }
                ++this.patternsCount;
                var3_3 = var1_1.get(var2_2).indexOf("|||", var3_3 + 3);
            } while (true);
            break;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    private List<ParseResult> nomalResults(List<ParseResult> object) {
        Collections.sort((List)object);
        ArrayList arrayList = new ArrayList();
        ParseResult parseResult = null;
        Iterator iterator = object.iterator();
        object = parseResult;
        while (iterator.hasNext()) {
            parseResult = (ParseResult)iterator.next();
            if (object == null) {
                arrayList.add(parseResult);
            } else {
                if (object.getStartPositionInSentence() <= parseResult.getStartPositionInSentence() && object.getEndPositionInSentence() >= parseResult.getEndPositionInSentence()) continue;
                arrayList.add(parseResult);
            }
            object = parseResult;
        }
        return arrayList;
    }

    /*
     * Enabled aggressive block sorting
     */
    private ParseResult parse(String string2, int n, List<Integer> arrayList) {
        Object object = new String[]();
        Iterator iterator = arrayList.iterator();
        arrayList = object;
        do {
            if (!iterator.hasNext()) {
                if (arrayList != null && arrayList.size() != 0) {
                    return this.nomalResults((List<ParseResult>)arrayList).get(0);
                }
                return null;
            }
            Integer n2 = (Integer)iterator.next();
            if (this.patterns.get((int)n2.intValue()).leftProduction.startsWith("<??")) continue;
            String string3 = this.patterns.get((int)n2.intValue()).pattern;
            if (!this.patterns.get((int)n2.intValue()).isRegularExpression) {
                object = StringProcess.noRegexSplit(string3, "&&&");
                this.segments = new ArrayList(20);
                this.segments_pattern = new ArrayList(20);
            } else {
                object = new String[]{string3};
                this.segments = new ArrayList(1);
                this.segments_pattern = new ArrayList(1);
            }
            if ((object = this.parse(0, string2, n, n2, (String[])object, 0, 0, 0, new HashSet())) == null || !object.getParseOK().booleanValue()) continue;
            int n3 = this.segments.size() - 1;
            do {
                if (n3 < object.getNextADDStartPositon()) {
                    if (this.currentStartRegular) break;
                    return new ParseResult(ParseType.OnlyPass, 0, 0, this.patterns.get(n2), string2, this.segments, this.segments_pattern);
                }
                this.segments.set(n3, (Object)null);
                this.segments_pattern.set(n3, (Object)null);
                --n3;
            } while (true);
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            arrayList.add(new ParseResult(ParseType.OnlyPass, this.currentStartRegularStartIndexInSource, object.getNextStartPositionInTarget() + this.currentStartRegularStartIndexInSource, this.patterns.get(n2), string2, this.segments, this.segments_pattern));
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private PartOfParseResult parse(int var1_1, String var2_2, int var3_3, int var4_4, String[] var5_5, int var6_6, int var7_7, int var8_8, HashSet<String> var9_9) {
        if (var6_6 >= var5_5.length) {
            if (var1_1 > 0) return new String[](true, var8_8, var3_3, (String)var2_2);
            if (var2_2.length() == 0) return new String[](true, var8_8, var3_3, (String)var2_2);
            if (var1_1 != 0) return null;
            if (this.currentStartRegular == false) return null;
            return new String[](true, var8_8, var3_3, (String)var2_2);
        }
        var14_12 = var5_5[var6_6];
        if (!this.patterns.get((int)var4_4).isRegularExpression && var14_12.startsWith("<?")) ** GOTO lbl21
        Log.println("parsing:" + (String)var14_12);
        if (!this.processedMostLeftRegularPrefix || this.currentStartRegular && var1_1 == 0 && var6_6 == 0) {
            var2_2 = this.firstRegularResults((String)var2_2, (String)var14_12, this.patterns.get((int)var4_4).isRegularExpression);
            this.processedMostLeftRegularPrefix = true;
        } else {
            var13_11 = var14_12.endsWith("<*>") != false && var6_6 < var5_5.length && var5_5[var6_6 + 1].startsWith("<?") != false ? var5_5[var6_6 + 1] : null;
            var2_2 = this.startWith((String)var2_2, (String)var14_12, (String)var13_11, this.patterns.get((int)var4_4).isRegularExpression);
        }
        if (var2_2 == null) {
            return null;
        }
        if (!this.analyseConstraint(this.patterns.get((int)var4_4).constraintContent, var2_2, var7_7).booleanValue()) {
            return null;
        }
        ** GOTO lbl51
lbl21: // 1 sources:
        var15_13 = this.getCandPatternIndexByNonTerminal((String)var14_12, (String)var2_2);
        var10_14 = 0;
        do {
            if (var10_14 >= var15_13.size()) {
                return null;
            }
            if (this.currentStartRegular && var1_1 == 0 && var6_6 == 0) {
                this.processedMostLeftRegularPrefix = false;
            }
            var14_12 = this.patterns.get(var15_13.get(var10_14));
            var13_10 = var14_12.isRegularExpression == false ? StringProcess.noRegexSplit(var14_12.pattern, "&&&") : new String[]{var14_12.pattern};
            var16_20 = new HashSet();
            var13_10 = this.parse(var1_1 + 1, (String)var2_2, var3_3, var15_13.get(var10_14), (String[])var13_10, 0, 0, var8_8, var16_20);
            if (var13_10 != null && var13_10.getParseOK().booleanValue()) {
                var11_16 = var13_10.getNextADDStartPositon();
                var12_18 = var13_10.getNextStartPositionInTarget();
                this.extractKnowledge(this.patterns.get((int)var4_4).resultContent, (List<String>)this.segments, var8_8, var11_16, var7_7, var3_3, var9_9);
                var14_12 = this.parse(var1_1, var13_10.getLeftStr(), var12_18, var4_4, var5_5, var6_6 + 1, var7_7 + 1, var11_16, var16_20);
                if (var14_12 != null) {
                    var13_10 = var14_12;
                    if (var14_12.getParseOK() != false) return var13_10;
                }
                for (Object var14_12 : var16_20) {
                    this.knowledge.remove(var14_12);
                }
            } else {
                var13_10 = var16_20.iterator();
lbl45: // 2 sources:
                if (var13_10.hasNext()) break;
            }
            ++var10_14;
        } while (true);
        var14_12 = (String)var13_10.next();
        this.knowledge.remove(var14_12);
        ** GOTO lbl45
lbl51: // 1 sources:
        this.extractKnowledge(this.patterns.get((int)var4_4).resultContent, var2_2, var7_7, var3_3, var9_9);
        var12_19 = this.segments.size();
        var13_11 = this.patterns.get((int)var4_4).isRegularExpression == false ? var14_12.split("\\+") : new String[]{var14_12};
        var11_17 = 0;
        var10_15 = var3_3;
        var3_3 = var8_8;
        var8_8 = var11_17;
        do {
            if (var8_8 >= var2_2.length - 1) {
                return this.parse(var1_1, var2_2[var2_2.length - 1], var10_15, var4_4, var5_5, var6_6 + 1, var2_2.length + var7_7 - 1, var3_3, var9_9);
            }
            var10_15 += var2_2[var8_8].length();
            if (var3_3 < var12_19) {
                this.segments_pattern.set(var3_3, (Object)var13_11[var8_8]);
                this.segments.set(var3_3, (Object)var2_2[var8_8]);
                ++var3_3;
            } else {
                this.segments_pattern.add((Object)var13_11[var8_8]);
                this.segments.add((Object)var2_2[var8_8]);
                ++var3_3;
            }
            ++var8_8;
        } while (true);
    }

    private static boolean parseKeywordsSize(int n, int[] arrn) {
        if (KeywordsType.isTypeEqual(n, 1)) {
            arrn[0] = arrn[0] + 1;
        }
        if (KeywordsType.isTypeEqual(n, 8)) {
            arrn[3] = arrn[3] + 1;
        }
        if (KeywordsType.isTypeEqual(n, 4)) {
            arrn[2] = arrn[2] + 1;
        }
        if (KeywordsType.isTypeEqual(n, 2)) {
            arrn[1] = arrn[1] + 1;
            return true;
        }
        return false;
    }

    private String[] parserFunction(String string2) {
        if (string2 != null && !string2.equals((Object)"")) {
            return StringProcess.noRegexSplit(string2, "_");
        }
        return null;
    }

    public static void printMemory(String string2) {
        if (Log.localTest) {
            Log.println(String.valueOf((Object)string2) + " Memory : " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - startMemory) / 1024 + " KB");
        }
    }

    private void recoverPosition(List<ParseResult> object, int n) {
        object = object.iterator();
        block0 : while (object.hasNext()) {
            Object object2 = (ParseResult)object.next();
            object2.setStartPositionInSentence(object2.getStartPositionInSentence() + n);
            object2.setEndPositionInSentence(object2.getEndPositionInSentence() + n);
            object2 = object2.knowledge.entrySet().iterator();
            do {
                if (!object2.hasNext()) continue block0;
                Map.Entry entry = (Map.Entry)object2.next();
                if (((StringIntInt)entry.getValue()).getName() != null) continue;
                ((StringIntInt)entry.getValue()).setStartIndex(((StringIntInt)entry.getValue()).getStartIndex() + n);
                ((StringIntInt)entry.getValue()).setEndIndex(((StringIntInt)entry.getValue()).getEndIndex() + n);
            } while (true);
            break;
        }
        return;
    }

    private boolean retainAll(Collection<Integer> object, int[] arrn) {
        boolean bl = false;
        object = object.iterator();
        while (object.hasNext()) {
            if (Bisection.search((int)((Integer)object.next()), arrn) >= 0) continue;
            object.remove();
            bl = true;
        }
        return bl;
    }

    private void setPatKeyWords(PatternForNER patternForNER, TreeMap<Integer, Integer> treeMap, int[] arrn) {
        patternForNER.setAllKeywords(treeMap, arrn);
    }

    private static void setTempKeywords(Map<Integer, Integer> map, int n, int n2) {
        n2 |= 1;
        Integer n3 = map.get(n);
        if (n3 == null) {
            map.put(n, n2);
            return;
        }
        map.put(n, n3 | n2);
    }

    public static void setTimeOutThr(int n) {
        timeOutThr = n;
    }

    private String[] startWith(String string2, String arrstring, String string3) {
        ArrayList arrayList = new ArrayList();
        if (string3 == null) {
            if (!this.nlp.patMatch(string2, String.valueOf((Object)arrstring) + "+<*>", arrayList)) {
                return null;
            }
        } else {
            if (!this.nlp.patMatch(string2, (String)arrstring, arrayList)) {
                return null;
            }
            arrstring = (String[])this.findFirstKeywordsByNoTerminal(string2, (Integer)arrayList.get(arrayList.size() - 2), string3);
            if (arrstring == null) {
                return null;
            }
            arrayList.set(arrayList.size() - 1, (Object)((int)arrstring[1]));
            arrayList.add((Object)string2.length());
        }
        int n = arrayList.size();
        arrstring = new String[n - 1];
        int n2 = 0;
        while (n2 < n - 1) {
            arrstring[n2] = string2.substring(((Integer)arrayList.get(n2)).intValue(), ((Integer)arrayList.get(n2 + 1)).intValue());
            ++n2;
        }
        return arrstring;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private String[] startWith(String string2, String arrstring, String string3, boolean bl) {
        if (!bl) {
            return this.startWith(string2, (String)arrstring, string3);
        }
        string3 = Pattern.compile((String)("^" + (String)arrstring)).matcher((CharSequence)string2);
        arrstring = null;
        if (!string3.find()) return arrstring;
        arrstring = new String[2];
        arrstring[0] = string3.group();
        if (string3.end() < string2.length()) {
            arrstring[1] = string2.substring(string3.end());
            return arrstring;
        }
        arrstring[1] = "";
        return arrstring;
    }

    /*
     * Enabled aggressive block sorting
     */
    private int trimEnd(String string2, int n, int n2, String string3, int n3) {
        if (n2 - n <= n3) return n2;
        int n4 = 0;
        int n5 = -1;
        int n6 = 0;
        do {
            int n7;
            int n8;
            if (n6 >= this.constant.get(string3).size()) {
                if (n5 < 0) return n2;
                return n2 - ((StringString)this.constant.get(string3).get(n5)).getKeyWord().length();
            }
            String string4 = ((StringString)this.constant.get(string3).get(n6)).getKeyWord();
            if (!StringProcess.endWith(string2, n2, string4)) {
                n7 = n4;
                n8 = n5;
            } else {
                n8 = n5;
                n7 = n4;
                if (string4.length() > n4) {
                    n8 = n5;
                    n7 = n4;
                    if (n2 - n - string4.length() >= n3) {
                        n7 = string4.length();
                        n8 = n6;
                    }
                }
            }
            ++n6;
            n5 = n8;
            n4 = n7;
        } while (true);
    }

    private int trimEndAll(String string2, int n, int n2, String string3, int n3) {
        int n4;
        while ((n4 = this.trimEnd(string2, n, n2, string3, n3)) != n2) {
            n2 = n4;
        }
        return n4;
    }

    /*
     * Enabled aggressive block sorting
     */
    private int trimStart(String string2, int n, int n2, String string3, int n3) {
        if (n2 - n <= n3) return n;
        int n4 = 0;
        int n5 = -1;
        int n6 = 0;
        do {
            int n7;
            int n8;
            if (n6 >= this.constant.get(string3).size()) {
                if (n5 < 0) return n;
                return n + ((StringString)this.constant.get(string3).get(n5)).getKeyWord().length();
            }
            String string4 = ((StringString)this.constant.get(string3).get(n6)).getKeyWord();
            if (!StringProcess.startWith(string2, n, string4)) {
                n7 = n4;
                n8 = n5;
            } else {
                n8 = n5;
                n7 = n4;
                if (string4.length() > n4) {
                    n8 = n5;
                    n7 = n4;
                    if (n2 - n - string4.length() >= n3) {
                        n7 = string4.length();
                        n8 = n6;
                    }
                }
            }
            ++n6;
            n5 = n8;
            n4 = n7;
        } while (true);
    }

    private int trimStartAll(String string2, int n, int n2, String string3, int n3) {
        int n4;
        while ((n4 = this.trimStart(string2, n, n2, string3, n3)) != n) {
            n = n4;
        }
        return n4;
    }

    /*
     * Enabled aggressive block sorting
     */
    public int containInDic(ArrayList<StringString> arrayList, String string2) {
        int n = 0;
        int n2 = arrayList.size() - 1;
        int n3 = (0 + n2) / 2;
        while (n <= n2) {
            int n4 = n3;
            if (((StringString)arrayList.get(n3)).getKeyWord().equals((Object)string2)) return n4;
            if (string2.compareTo(((StringString)arrayList.get(n3)).getKeyWord()) < 0) {
                n2 = n3 - 1;
            } else {
                n = n3 + 1;
            }
            n3 = (n + n2) / 2;
        }
        return -1;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public List<ParseResult> findKnowledge(String var1_1, double var2_2) throws Exception {
        var5_3 = var1_1;
        if (!this.caseSensitive) {
            var5_3 = var1_1.toLowerCase();
        }
        if (this.patterns == null) return new ArrayList();
        if (this.patterns.size() == 0) {
            return new ArrayList();
        }
        if (!this.findMust((String)var5_3)) {
            return new ArrayList();
        }
        this.targetSegment = null;
        this.timeoutStart = System.currentTimeMillis();
        var1_1 = this.segPunctuation.toLowerCase().equals((Object)"[null]") != false ? new String[]{var5_3} : var5_3.split(this.segPunctuation);
        if (var1_1.length == 0) {
            return new ArrayList();
        }
        var5_3 = new int[var1_1.length];
        var5_3[0] = false;
        var4_4 = 1;
        do {
            if (var4_4 > var1_1.length) break;
            if (var4_4 < var1_1.length) {
                var5_3[var4_4] = var5_3[var4_4 - 1] + var1_1[var4_4 - 1].length() + true;
            }
            var1_1[var4_4 - 1] = this.containStart == false && this.containEnd == false ? var1_1[var4_4 - 1] : (this.containEnd != false && this.containStart == false ? String.valueOf((Object)var1_1[var4_4 - 1]) + PatternForNER.endPatternReplaceStr : (this.containEnd == false && this.containStart != false ? String.valueOf((Object)PatternForNER.startPatternReplaceStr) + var1_1[var4_4 - 1] : String.valueOf((Object)PatternForNER.startPatternReplaceStr) + var1_1[var4_4 - 1] + PatternForNER.endPatternReplaceStr));
            ++var4_4;
        } while (true);
        var6_5 = new ArrayList();
        var4_4 = 0;
        block1 : do {
            if (var4_4 >= var1_1.length) {
                return var6_5;
            }
            var7_6 = this.findKnowledgeSeg(var1_1[var4_4], var2_2);
            if (var7_6 != null && var7_6.size() != 0) {
                this.recoverPosition(var7_6, (int)var5_3[var4_4]);
                if (this.replaceSeg) {
                    this.getSegReplace(var7_6);
                }
                var7_6 = var7_6.iterator();
                break;
            }
            do {
                ++var4_4;
                continue block1;
                break;
            } while (true);
            break;
        } while (true);
        do {
            if (!var7_6.hasNext()) {
                if (!this.checkOneMatch((List<ParseResult>)var6_5)) ** continue;
                return var6_5;
            }
            var8_7 = (ParseResult)var7_6.next();
            if (this.task == null || this.task.toLowerCase().equals((Object)"null")) {
                var8_7.setTask(StringProcess.trim(var8_7.getPattern().leftProduction, new Character[]{Character.valueOf((char)'<'), Character.valueOf((char)'>'), Character.valueOf((char)'?')}));
            } else {
                var8_7.setTask(this.task);
            }
            var6_5.add(var8_7);
        } while (true);
    }

    public int getCandNextStartIndex(String string2, int n) {
        do {
            if (n >= string2.length() - 1) {
                return -1;
            }
            if (!StringProcess.ifCharTypeSame(string2, n, n + 1)) break;
            ++n;
        } while (true);
        return n + 1;
    }

    public boolean match(String string2, String string3) {
        return this.nlp.strEqual(string3, string2);
    }
}

