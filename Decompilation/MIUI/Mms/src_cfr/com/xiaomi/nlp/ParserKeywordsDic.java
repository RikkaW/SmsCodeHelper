/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.HashMap
 */
package com.xiaomi.nlp;

import com.xiaomi.common.ConstraintACAutomation;
import com.xiaomi.common.StringProcess;
import com.xiaomi.nlp.ParserKeywords;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ParserKeywordsDic {
    private ConstraintACAutomation keyWordsACAutomation;
    private List<ParserKeywords> keywords = new ArrayList();
    private int mustKeywordsCount = 0;
    private Map<String, Integer> tempKeywords2Index = new HashMap();

    public void addKeyWordsPatIndex(int n, List<Integer> list) {
        this.keywords.get(n).addPatIndex(list);
    }

    public int addKeywords(String string2, int n) {
        if (this.tempKeywords2Index == null) {
            return -1;
        }
        int n2 = this.getKeyWordsIndex(string2);
        if (n2 >= 0) {
            if (n == 8 && !this.getKeywords(n2).isTypeEqual(8)) {
                ++this.mustKeywordsCount;
            }
            this.setKeyWordsType(n2, n);
            return n2;
        }
        ParserKeywords parserKeywords = new ParserKeywords(string2, n);
        if (n == 8) {
            ++this.mustKeywordsCount;
        }
        this.keywords.add(parserKeywords);
        this.tempKeywords2Index.put(string2, this.keywords.size() - 1);
        return this.keywords.size() - 1;
    }

    public boolean buildDic() {
        if (this.tempKeywords2Index == null) {
            return false;
        }
        this.keyWordsACAutomation = new ConstraintACAutomation<ParserKeywords>(this.keywords);
        this.tempKeywords2Index = null;
        return true;
    }

    public List<int[]> find(String string2, int n) {
        return this.keyWordsACAutomation.find(string2, n);
    }

    public int[] findFirst(String string2, int n, int n2) {
        return this.keyWordsACAutomation.findFirst(string2, n, n2);
    }

    public int[] findFirst(String string2, int n, int n2, int n3) {
        return this.keyWordsACAutomation.findFirst(string2, n, n2, n3);
    }

    /*
     * Enabled aggressive block sorting
     */
    public int[] findFirst(String string2, int n, Set<Integer> iterator) {
        int n2 = Integer.MAX_VALUE;
        int n3 = -1;
        iterator = iterator.iterator();
        do {
            if (!iterator.hasNext()) {
                if (n3 < 0) break;
                return new int[]{n3, n2, this.getKeywords(n3).getKeyWords().length() + n2 - 1};
            }
            Integer n4 = (Integer)iterator.next();
            String string3 = this.getKeywords(n4).getKeyWords();
            int n5 = string3.equals((Object)"CHAR") ? StringProcess.findFirstChar(string2, n) : (string3.equals((Object)"NUM") ? StringProcess.findFirstNumber(string2, n) : (string3.equals((Object)"ENG") ? StringProcess.findFirstEng(string2, n) : string2.indexOf(string3, n)));
            if (n5 < 0 || n5 >= n2) continue;
            n3 = n4;
            n2 = n5;
        } while (true);
        return null;
    }

    public List<int[]> findFirstAll(String string2, int n, int n2) {
        return this.keyWordsACAutomation.findFirstAll(string2, n, n2);
    }

    public List<int[]> findFirstAll(String string2, int n, int n2, int n3) {
        return this.keyWordsACAutomation.findFirstAll(string2, n, n2, n3);
    }

    public int getKeyWordsIndex(String string2) {
        if (this.keyWordsACAutomation != null) {
            return this.keyWordsACAutomation.contains(string2, 1);
        }
        if ((string2 = this.tempKeywords2Index.get(string2)) == null) {
            return -1;
        }
        return string2.intValue();
    }

    public ParserKeywords getKeywords(int n) {
        return this.keywords.get(n);
    }

    public int getMustKeywordsCount() {
        return this.mustKeywordsCount;
    }

    public int[] getPatIndexByKeywords(int n) {
        return this.keywords.get(n).getPatIndex();
    }

    public boolean isStartKeyWords(int n) {
        return this.keywords.get(n).isStartKeyWords();
    }

    public void setKeyWordsType(int n, int n2) {
        this.keywords.get(n).setKeyWordsType(n2);
    }

    public List<int[]> startWith(String string2, int n, int n2) {
        return this.keyWordsACAutomation.startWith(string2, n, n2);
    }
}

