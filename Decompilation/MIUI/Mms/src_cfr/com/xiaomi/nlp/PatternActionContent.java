/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package com.xiaomi.nlp;

import com.xiaomi.common.Log;
import com.xiaomi.common.StringInt;
import com.xiaomi.common.StringProcess;
import com.xiaomi.nlp.PatternKeywords;
import java.util.ArrayList;
import java.util.List;

public class PatternActionContent {
    private static String[] splitPat = new String[]{":", "\uff1a"};
    public List<StringInt> contents;
    public PatternKeywords keywords;
    public String parameter;
    public String remark = null;

    public PatternActionContent(String string2) {
        this.fillContent(StringProcess.noRegexSplit(string2, splitPat));
    }

    /*
     * Enabled aggressive block sorting
     */
    private void fillContent(String[] arrstring) {
        if (arrstring.length < 2) {
            Log.println("PattrenActionContent()\u53c2\u6570\u9519\u8bef\uff1a" + arrstring);
            this.keywords = PatternKeywords.OTHER;
            return;
        }
        arrstring[0] = arrstring[0].trim();
        arrstring[1] = arrstring[1].trim();
        if (arrstring[0].equals((Object)"NoNull")) {
            this.keywords = PatternKeywords.NoNull;
        } else if (arrstring[0].equals((Object)"Remark")) {
            this.keywords = PatternKeywords.Remark;
        } else if (arrstring[0].equals((Object)"Score")) {
            this.keywords = PatternKeywords.Score;
        } else if (arrstring[0].equals((Object)"ContainPOS")) {
            this.keywords = PatternKeywords.ContainPOS;
        } else if (arrstring[0].equals((Object)"EndWithPOS")) {
            this.keywords = PatternKeywords.EndWithPOS;
        } else if (arrstring[0].equals((Object)"EndWithDic")) {
            this.keywords = PatternKeywords.EndWithDic;
        } else if (arrstring[0].equals((Object)"NoEndWithDic")) {
            this.keywords = PatternKeywords.NoEndWithDic;
        } else if (arrstring[0].equals((Object)"NoEndWithPOS")) {
            this.keywords = PatternKeywords.NoEndWithPOS;
        } else if (arrstring[0].equals((Object)"NoContainPOS")) {
            this.keywords = PatternKeywords.NoContainPOS;
        } else if (arrstring[0].equals((Object)"NoEqualDic")) {
            this.keywords = PatternKeywords.NoEqualDic;
        } else if (arrstring[0].equals((Object)"EqualDic")) {
            this.keywords = PatternKeywords.EqualDic;
        } else if (arrstring[0].equals((Object)"StartWithDic")) {
            this.keywords = PatternKeywords.StartWithDic;
        } else if (arrstring[0].equals((Object)"ContainDic")) {
            this.keywords = PatternKeywords.ContainDic;
        } else if (arrstring[0].equals((Object)"NoContainDic")) {
            this.keywords = PatternKeywords.NoContainDic;
        } else if (arrstring[0].equals((Object)"NoStartWithDic")) {
            this.keywords = PatternKeywords.NoStartWithDic;
        } else if (arrstring[0].equals((Object)"NoStartWithPOS")) {
            this.keywords = PatternKeywords.NoStartWithPOS;
        } else if (arrstring[0].equals((Object)"CharLenLess")) {
            this.keywords = PatternKeywords.CharLenLess;
        } else if (arrstring[0].equals((Object)"CharLenLonger")) {
            this.keywords = PatternKeywords.CharLenLonger;
        } else if (arrstring[0].equals((Object)"WordsLenLonger")) {
            this.keywords = PatternKeywords.WordsLenLonger;
        } else if (arrstring[0].equals((Object)"WordsLenLess")) {
            this.keywords = PatternKeywords.WordsLenLess;
        } else if (arrstring[0].equals((Object)"Accept")) {
            this.keywords = PatternKeywords.Accept;
        } else if (arrstring[0].equals((Object)"Reject")) {
            this.keywords = PatternKeywords.Reject;
        } else if (arrstring[0].equals((Object)"yyyy")) {
            this.keywords = PatternKeywords.yyyy;
        } else if (arrstring[0].equals((Object)"mm")) {
            this.keywords = PatternKeywords.mm;
        } else if (arrstring[0].equals((Object)"MM")) {
            this.keywords = PatternKeywords.MM;
        } else if (arrstring[0].equals((Object)"dd")) {
            this.keywords = PatternKeywords.dd;
        } else if (arrstring[0].equals((Object)"HH")) {
            this.keywords = PatternKeywords.HH;
        } else if (arrstring[0].equals((Object)"ss")) {
            this.keywords = PatternKeywords.ss;
        } else if (arrstring[0].startsWith("Ext_")) {
            this.keywords = PatternKeywords.Extract;
            this.remark = arrstring[0].substring(4);
        } else {
            this.keywords = PatternKeywords.OTHER;
        }
        String[] arrstring2 = StringProcess.noRegexSplit(arrstring[1], "+");
        this.contents = new ArrayList(1);
        int n = 0;
        do {
            if (n >= arrstring2.length) {
                if (arrstring.length != 3) break;
                this.parameter = arrstring[2].trim();
                return;
            }
            arrstring2[n] = arrstring2[n].trim();
            if (!arrstring2[n].equals((Object)"")) {
                if (arrstring2[n].startsWith("\"") && arrstring2[n].endsWith("\"")) {
                    this.contents.add(new StringInt(arrstring2[n].substring(1, arrstring2[n].length() - 1), -1));
                } else {
                    int n2 = arrstring2[n].indexOf("(");
                    if (n2 < 0) {
                        if (StringProcess.isNumber(arrstring2[n])) {
                            this.contents.add(new StringInt("", Integer.valueOf((String)arrstring2[n])));
                        } else {
                            this.contents.add(new StringInt(arrstring2[n], -1));
                        }
                    } else {
                        String string2 = arrstring2[n].substring(0, n2).toLowerCase();
                        n2 = Integer.valueOf((String)arrstring2[n].substring(n2 + 1, arrstring2[n].length() - 1));
                        this.contents.add(new StringInt(string2, n2));
                    }
                }
            }
            ++n;
        } while (true);
        this.parameter = "";
    }
}

