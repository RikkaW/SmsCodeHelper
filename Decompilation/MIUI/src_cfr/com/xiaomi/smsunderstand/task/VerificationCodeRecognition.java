/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.HashMap
 *  java.util.HashSet
 *  java.util.Map$Entry
 */
package com.xiaomi.smsunderstand.task;

import com.xiaomi.common.FileOperator;
import com.xiaomi.common.Log;
import com.xiaomi.common.StringInt;
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

public class VerificationCodeRecognition {
    private static boolean ifInitial = false;
    private static Parser parser;
    private static HashSet<String> verificationCodeGuideWords;
    private static String verificationCodeGuideWordsFileName;
    private static int verificationCodeGuideWordsMaxLength;

    static {
        verificationCodeGuideWords = new HashSet();
        verificationCodeGuideWordsMaxLength = -1;
        verificationCodeGuideWordsFileName = null;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static List<EntityInfo> findKnowledge(String string2) throws Exception {
        ArrayList arrayList = new ArrayList();
        if (!ifInitial) {
            return arrayList;
        }
        Iterator<ParseResult> iterator = parser.findKnowledge(string2, 0.3).iterator();
        block0 : while (iterator.hasNext()) {
            ParseResult parseResult = iterator.next();
            Iterator iterator2 = parseResult.knowledge.entrySet().iterator();
            do {
                if (!iterator2.hasNext()) continue block0;
                Map.Entry entry = (Map.Entry)iterator2.next();
                String string3 = ((StringIntInt)entry.getValue()).getName() == null ? string2.substring(((StringIntInt)entry.getValue()).getStartIndex(), ((StringIntInt)entry.getValue()).getEndIndex()) : ((StringIntInt)entry.getValue()).getName();
                EntityInfo entityInfo = new EntityInfo();
                entityInfo.setTarget(string3);
                entityInfo.noNomal();
                if (!((String)entry.getKey()).equals((Object)"verificationCode")) continue;
                entityInfo.setEntityType(EntityType.VERIFICATIONCODE);
                entityInfo.setRemark(parseResult.getPattern().toString());
                entityInfo.setConfidence(parseResult.getPattern().score);
                entityInfo.setStartPosition(((StringIntInt)entry.getValue()).getStartIndex());
                entityInfo.setEndPosition(((StringIntInt)entry.getValue()).getEndIndex());
                arrayList.add(entityInfo);
            } while (true);
            break;
        }
        return arrayList;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int findSegPunctuation(String string2, int n, int n2) {
        while (n < n2) {
            int n3 = n;
            switch (string2.charAt(n)) {
                case '!': 
                case '.': 
                case ';': 
                case '?': 
                case '\u3002': 
                case '\uff01': 
                case '\uff1b': 
                case '\uff1f': {
                    return n3;
                }
            }
            ++n;
        }
        return -1;
    }

    public static boolean freeResource() {
        if (!ifInitial) {
            return true;
        }
        verificationCodeGuideWords.clear();
        verificationCodeGuideWordsMaxLength = -1;
        parser = null;
        ifInitial = false;
        return true;
    }

    public static boolean initial() throws IOException {
        if (ifInitial) {
            return true;
        }
        verificationCodeGuideWordsFileName = String.valueOf((Object)SMSUnderstand.dictionaryPath) + "/verificationCodeGuideWords.txt";
        Iterator<String> iterator = FileOperator.readFile(verificationCodeGuideWordsFileName).iterator();
        block0 : do {
            if (!iterator.hasNext()) {
                parser = new Parser(String.valueOf((Object)SMSUnderstand.dictionaryPath) + "/patterns/VerificationCode.pattern");
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
                    verificationCodeGuideWords.add((Object)string2.toLowerCase());
                    if (verificationCodeGuideWordsMaxLength < string2.length()) {
                        verificationCodeGuideWordsMaxLength = string2.length();
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
    public static double isRightNumber(EntityInfo entityInfo, String string2, int n) {
        double d2;
        double d3;
        int n2;
        int n3;
        block14 : {
            if (!ifInitial) {
                return 0.0;
            }
            n2 = entityInfo.getTarget().length();
            if (n2 != entityInfo.getTarget_nomal().length()) {
                return 0.0;
            }
            if (n2 < 4) return 0.0;
            if (n2 > 18) {
                return 0.0;
            }
            if (entityInfo.getNumberCount() + entityInfo.getEngCharCount() != n2) {
                return 0.0;
            }
            List<StringInt> list = StringProcess.findAllResultsFromList(string2, verificationCodeGuideWords, verificationCodeGuideWordsMaxLength);
            if (list.size() <= 0) {
                return 0.0;
            }
            Object object2 = string2.substring(0, entityInfo.getStartPosition());
            if (object2.endsWith("\u5c3e\u53f7")) return 0.0;
            if (object2.endsWith("\u53f7\u4e3a")) {
                return 0.0;
            }
            d2 = d3 = 1.0 / (0.1 + (double)n) + (double)entityInfo.getNumberCount() / (double)(n2 * 5);
            if (n2 != 4) {
                d2 = d3;
                if (n2 != 6) {
                    n = n2 < 4 ? 3 : 1;
                    d2 = d3 * (4.0 / (double)(n * n2));
                }
            }
            n3 = 1;
            n = 0;
            for (Object object2 : list) {
                if (object2.getNum() < entityInfo.getStartPosition()) {
                    if (VerificationCodeRecognition.findSegPunctuation(string2, object2.getNum() + object2.getName().length(), entityInfo.getStartPosition()) >= 0) continue;
                    n2 = 1;
                    n = 1;
                    if (Integer.MAX_VALUE <= entityInfo.getStartPosition() - object2.getNum() - object2.getName().length()) break;
                    n = entityInfo.getStartPosition() - object2.getNum() - object2.getName().length();
                    n3 = 1;
                } else {
                    if (VerificationCodeRecognition.findSegPunctuation(string2, entityInfo.getEndPosition() - 1, object2.getNum()) >= 0) continue;
                    n2 = 1;
                    n = 1;
                    if (Integer.MAX_VALUE <= object2.getNum() - entityInfo.getEndPosition()) break;
                    n = object2.getNum() - entityInfo.getEndPosition();
                    n3 = -1;
                }
                break block14;
            }
            int n4 = Integer.MAX_VALUE;
            n2 = n;
            n = n4;
        }
        if (n2 == 0) {
            return 0.0;
        }
        if (n < 0) {
            Log.i("VerificationCodeRecognition", "VerificationCodeRecognition.isRightNumber Error\uff1a" + string2);
        }
        n2 = n;
        if (n3 < 0) {
            n2 = n * 4;
        }
        d2 = d3 = d2 + 0.5 / ((double)n2 + 0.1);
        if (d3 <= 1.0) return d2;
        return 1.0;
    }
}

