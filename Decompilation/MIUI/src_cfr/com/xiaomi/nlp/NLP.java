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
 *  java.util.regex.Pattern
 */
package com.xiaomi.nlp;

import com.xiaomi.common.ConstantData;
import com.xiaomi.nlp.KeyWordAccessable;
import com.xiaomi.nlp.RuleFunction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

public class NLP {
    public static Pattern rxChinese;
    public static Pattern rxChineseWord;
    private static HashMap<String, HashSet<String>> sentenceTrimStartPos;
    private RuleFunction ruleFunction = null;

    static {
        sentenceTrimStartPos = new HashMap();
        rxChinese = Pattern.compile((String)"^[\uff0c\u3002\uff1b\uff1f~\uff01\uff1a\u2018\u201c\u201d\u2019\u3010\u3011\uff08\uff09\u4e00-\u9fa5\uf900-\ufa2d]$");
        rxChineseWord = Pattern.compile((String)"[\u4e00-\u9fa5\uf900-\ufa2d]+");
    }

    public NLP() {
    }

    public NLP(int n) {
    }

    public NLP(int n, Map<String, ? extends ArrayList<? extends KeyWordAccessable>> map) {
        this.ruleFunction = new RuleFunction(n);
        this.ruleFunction.loadConstants(map);
    }

    public static boolean EqualDic_Seg(String string2, String string3) {
        if (((HashSet)ConstantData.DICs.get((Object)string3)).contains((Object)NLP.getWordsFromSeg(string2))) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static String getWordsFromSeg(String string2) {
        String string3 = "";
        String[] arrstring = string2.split(" ", 0);
        int n = arrstring.length;
        int n2 = 0;
        string2 = string3;
        while (n2 < n) {
            string3 = arrstring[n2];
            int n3 = string3.lastIndexOf("/");
            string2 = n3 >= 0 ? String.valueOf((Object)string2) + string3.substring(0, n3) : String.valueOf((Object)string2) + string3;
            ++n2;
        }
        return string2;
    }

    public final boolean contain(String string2, String string3) {
        if (string2.equals((Object)"")) {
            return false;
        }
        return this.ruleFunction.contain(string2, string3);
    }

    public final boolean containDic(String string2, String string3) {
        if (string3.charAt(0) == '#') {
            return this.contain(string2, "<" + string3.substring(1) + ">");
        }
        if (string3.charAt(0) != '<') {
            return this.contain(string2, "<" + string3.substring(1) + ">");
        }
        return this.contain(string2, string3);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public final boolean containPOS(String string2, String string3) {
        if (string2.length() == 0) {
            return false;
        }
        if (string3.charAt(0) == '#') {
            return this.contain(string2, "<*/" + string3.substring(1) + ">");
        }
        Object object = (HashSet)ConstantData.DICs.get((Object)string3);
        if (object.size() == 0) return false;
        string3 = new StringBuffer();
        object = object.iterator();
        while (object.hasNext()) {
            String string4 = (String)object.next();
            string3.append("*/" + string4 + "|");
        }
        return this.contain(string2, "<" + string3.deleteCharAt(string3.length() - 1).toString() + ">");
    }

    public final boolean endWithDic(String string2, String string3) {
        if (string3.charAt(0) == '#') {
            return this.ruleFunction.endWith(string2, "<" + string3.substring(1) + ">");
        }
        if (string3.charAt(0) != '<') {
            return this.ruleFunction.endWith(string2, "<!" + string3 + ">");
        }
        return this.ruleFunction.endWith(string2, string3);
    }

    /*
     * Enabled aggressive block sorting
     */
    public final boolean endWithPOS(String string2, String string3) {
        int n;
        int n2;
        if (string2.length() == 0 || (n2 = string2.lastIndexOf("/")) < 0) {
            return false;
        }
        int n3 = n = string2.indexOf(" ", n2 + 1);
        if (n < 0) {
            n3 = string2.length();
        }
        if (string3.charAt(0) == '#') {
            return string2.substring(n2 + 1, n3).equals((Object)string3.substring(1));
        }
        return ((HashSet)ConstantData.DICs.get((Object)string3)).contains((Object)string2.substring(n2 + 1, n3));
    }

    public final boolean noEndWithPOS(String string2, String string3) {
        if (this.endWithPOS(string2, string3)) {
            return false;
        }
        return true;
    }

    public boolean patMatch(String string2, String string3, ArrayList<Integer> arrayList) {
        return this.ruleFunction.strEqual(string2, string3, arrayList);
    }

    public final boolean startWithDic(String string2, String string3) {
        if (string3.charAt(0) == '#') {
            return this.ruleFunction.startWith(string2, "<" + string3.substring(1) + ">");
        }
        if (string3.charAt(0) != '<') {
            return this.ruleFunction.startWith(string2, "<!" + string3 + ">");
        }
        return this.ruleFunction.startWith(string2, string3);
    }

    /*
     * Enabled aggressive block sorting
     */
    public final boolean startWithPOS(String string2, String string3) {
        int n;
        int n2;
        if (string2.length() == 0 || (n2 = string2.indexOf("/")) < 0) {
            return false;
        }
        int n3 = n = string2.indexOf(" ", n2 + 1);
        if (n < 0) {
            n3 = string2.length();
        }
        if (string3.charAt(0) == '#') {
            return string2.substring(n2 + 1, n3).equals((Object)string3.substring(1));
        }
        return ((HashSet)ConstantData.DICs.get((Object)string3)).contains((Object)string2.substring(n2 + 1, n3));
    }

    public final boolean strEqual(String string2, String string3) {
        if (string2.equals((Object)"")) {
            return false;
        }
        return this.ruleFunction.strEqual(string2, string3);
    }
}

