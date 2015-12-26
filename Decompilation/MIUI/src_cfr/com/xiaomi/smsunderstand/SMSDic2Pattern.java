/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.BitSet
 *  java.util.HashMap
 *  java.util.HashSet
 *  java.util.Map$Entry
 */
package com.xiaomi.smsunderstand;

import com.xiaomi.common.ACAutomation;
import com.xiaomi.common.ACAutomationable;
import com.xiaomi.common.FileOperator;
import com.xiaomi.smsunderstand.SMSBodyKeywordInfo;
import com.xiaomi.smsunderstand.StringAndIntArray;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SMSDic2Pattern {
    private List<SMSBodyKeywordInfo> bodyKeyword2Pattern;
    private List<StringAndIntArray> phoneNumberPrefixsAndPatIndex;
    private ACAutomation phoneNumberPrefixsAutomation;
    private List<StringAndIntArray> phoneNumberSuffixsAndPatIndex;
    private ACAutomation phoneNumberSuffixsAutomation;
    private List<StringAndIntArray> startKeywordsAndPatIndex;
    private ACAutomation startKeywordsAutomation;

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public SMSDic2Pattern(String var1_1) throws IOException {
        super();
        var1_1 = FileOperator.readFile((String)var1_1);
        this.bodyKeyword2Pattern = new ArrayList();
        var13_2 = new HashMap();
        var12_3 = new HashMap();
        var11_4 = new HashMap();
        var14_5 = var1_1.iterator();
        do {
            block18 : {
                if (!var14_5.hasNext()) ** GOTO lbl25
                var5_9 = var14_5.next();
                var2_6 = var5_9.indexOf("//");
                var1_1 = var5_9;
                if (var2_6 >= 0) {
                    var1_1 = var5_9.substring(0, var2_6);
                }
                if ((var9_13 = var1_1.split("[\\s\\t]+")).length < 2) continue;
                var15_15 = var9_13[var9_13.length - 1];
                var1_1 = null;
                var6_10 = null;
                if (var9_13.length != 3 || !var9_13[0].toLowerCase().equals((Object)"all")) ** GOTO lbl50
                var5_9 = var6_10;
                if (var9_13[0].startsWith("area:")) {
                    var1_1 = var9_13[0].substring(5);
                    var5_9 = var6_10;
                }
                ** GOTO lbl56
lbl25: // 1 sources:
                this.startKeywordsAndPatIndex = new ArrayList();
                var1_1 = var13_2.entrySet().iterator();
                do {
                    if (!var1_1.hasNext()) break;
                    var5_9 = (Map.Entry)var1_1.next();
                    this.startKeywordsAndPatIndex.add(new StringAndIntArray((String)var5_9.getKey(), (List)var5_9.getValue()));
                } while (true);
                this.phoneNumberPrefixsAndPatIndex = new ArrayList();
                var1_1 = var12_3.entrySet().iterator();
                do {
                    if (!var1_1.hasNext()) break;
                    var5_9 = (Map.Entry)var1_1.next();
                    this.phoneNumberPrefixsAndPatIndex.add(new StringAndIntArray((String)var5_9.getKey(), (List)var5_9.getValue()));
                } while (true);
                this.phoneNumberSuffixsAndPatIndex = new ArrayList();
                var1_1 = var11_4.entrySet().iterator();
                do {
                    if (!var1_1.hasNext()) {
                        this.startKeywordsAutomation = new ACAutomation(this.startKeywordsAndPatIndex, false);
                        this.phoneNumberPrefixsAutomation = new ACAutomation(this.phoneNumberPrefixsAndPatIndex, false);
                        this.phoneNumberSuffixsAutomation = new ACAutomation(this.phoneNumberSuffixsAndPatIndex, false);
                        return;
                    }
                    var5_9 = (Map.Entry)var1_1.next();
                    this.phoneNumberSuffixsAndPatIndex.add(new StringAndIntArray((String)var5_9.getKey(), (List)var5_9.getValue()));
                } while (true);
lbl50: // 1 sources:
                var5_9 = new ArrayList(var9_13.length - 2);
                var2_6 = 0;
                do {
                    if (var2_6 >= var9_13.length - 2) ** GOTO lbl56
                    if (var9_13[var2_6].startsWith("area:")) {
                        var1_1 = var9_13[var2_6].substring(5);
lbl56: // 3 sources:
                        if (var9_13[0].toLowerCase().equals((Object)"all")) {
                            var5_9 = null;
                        }
                        var6_10 = null;
                        if (var1_1 != null) {
                            var6_10 = var1_1.split("[\\|]+");
                        }
                        var1_1 = null;
                        var7_11 = null;
                        var8_12 = null;
                        if (!var9_13[var9_13.length - 2].toLowerCase().equals((Object)"all")) {
                            var1_1 = var9_13[var9_13.length - 2].split("[\\|]+");
                            var7_11 = new BitSet(var1_1.length);
                            var8_12 = new BitSet(var1_1.length);
                            this.analysisPhoneNumberPart(var1_1, var7_11, var8_12);
                        }
                        var3_7 = this.bodyKeyword2Pattern.size();
                        if (var5_9 == null || var5_9.size() <= 0) break;
                        var16_16 = (String[])var5_9.get(0);
                        var4_8 = var16_16.length;
                        var2_6 = 0;
                        break block18;
                    }
                    var5_9.add(var9_13[var2_6].split("[\\|]+"));
                    ++var2_6;
                } while (true);
                if (var1_1 == null || var1_1.length <= 0) continue;
                for (var2_6 = 0; var2_6 < var1_1.length; ++var2_6) {
                    var16_16 = var1_1[var2_6];
                    if (var7_11.get(var2_6)) {
                        var9_13 = var10_14 = (List)var12_3.get(var16_16);
                        if (var10_14 == null) {
                            var9_13 = new ArrayList(3);
                            var12_3.put(var16_16, var9_13);
                        }
                        var9_13.add((int)var3_7);
                    }
                    if (!var8_12.get(var2_6)) continue;
                    var9_13 = var10_14 = (List)var11_4.get(var16_16);
                    if (var10_14 == null) {
                        var9_13 = new ArrayList(3);
                        var11_4.put(var16_16, var9_13);
                    }
                    var9_13.add((int)var3_7);
                }
                ** GOTO lbl97
            }
lbl96: // 2 sources:
            if (var2_6 < var4_8) break;
lbl97: // 2 sources:
            this.bodyKeyword2Pattern.add(new SMSBodyKeywordInfo((List<String[]>)var5_9, var1_1, var7_11, var8_12, var6_10, var15_15));
        } while (true);
        var17_17 = var16_16[var2_6];
        var9_13 = var10_14 = (List)var13_2.get(var17_17);
        if (var10_14 == null) {
            var9_13 = new ArrayList(3);
            var13_2.put(var17_17, var9_13);
        }
        var9_13.add((int)var3_7);
        ++var2_6;
        ** GOTO lbl96
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private int analysisPhoneNumberPart(String[] var1_1, BitSet var2_2, BitSet var3_3) {
        if (var1_1 == null) return 0;
        if (var1_1.length == 0) {
            return 0;
        }
        var4_4 = 0;
        do {
            if (var4_4 >= var1_1.length) {
                return var1_1.length;
            }
            var7_7 = var1_1[var4_4];
            var5_5 = false;
            if (var7_7.endsWith("$")) {
                var3_3.set(var4_4, true);
                var5_5 = true;
            }
            var6_6 = false;
            if (!var7_7.startsWith("^")) ** GOTO lbl-1000
            var2_2.set(var4_4, true);
            var6_6 = true;
            if (!var6_6) ** GOTO lbl21
            if (!var5_5) ** GOTO lbl-1000
            var1_1[var4_4] = var1_1[var4_4].substring(1, var1_1[var4_4].length() - 1);
            ** GOTO lbl29
lbl21: // 1 sources:
            if (var6_6) lbl-1000: // 2 sources:
            {
                var1_1[var4_4] = var1_1[var4_4].substring(1);
            } else if (var5_5) lbl-1000: // 2 sources:
            {
                var1_1[var4_4] = var1_1[var4_4].substring(0, var1_1[var4_4].length() - 1);
            } else {
                var2_2.set(var4_4, true);
                var1_1[var4_4] = var1_1[var4_4];
            }
lbl29: // 4 sources:
            ++var4_4;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    private HashSet<Integer> getCandPatIndex(String iterator, String iterator2) {
        int[] arrn;
        HashSet hashSet = new HashSet();
        Iterator<int[]> iterator3 = this.startKeywordsAutomation.find((String)((Object)iterator2)).iterator();
        do {
            if (!iterator3.hasNext()) break;
            arrn = iterator3.next();
            hashSet.addAll(this.startKeywordsAndPatIndex.get(arrn[0]).getIndexs());
        } while (true);
        Iterator<int[]> iterator4 = this.phoneNumberPrefixsAutomation.startWith((String)((Object)iterator), 0).iterator();
        do {
            if (!iterator4.hasNext()) break;
            arrn = iterator4.next();
            hashSet.addAll(this.phoneNumberPrefixsAndPatIndex.get(arrn[0]).getIndexs());
        } while (true);
        iterator = this.phoneNumberSuffixsAutomation.startWith((String)((Object)iterator), 0).iterator();
        while (iterator.hasNext()) {
            int[] arrn2 = iterator.next();
            hashSet.addAll(this.phoneNumberSuffixsAndPatIndex.get(arrn2[0]).getIndexs());
        }
        return hashSet;
    }

    public int getPatternSize() {
        if (this.bodyKeyword2Pattern == null) {
            return 0;
        }
        return this.bodyKeyword2Pattern.size();
    }

    public List<String> match(String string2, String string3, String string4) {
        Object object = this.getCandPatIndex(string2, string4);
        ArrayList arrayList = new ArrayList();
        object = object.iterator();
        while (object.hasNext()) {
            Integer n = (Integer)object.next();
            if (!this.bodyKeyword2Pattern.get(n).match(string2, string3, string4)) continue;
            arrayList.add(this.bodyKeyword2Pattern.get(n).getPatternName());
        }
        return arrayList;
    }
}

