/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.BitSet
 */
package com.xiaomi.smsunderstand;

import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

public class SMSBodyKeywordInfo {
    private String[] areas;
    private List<String[]> keywords;
    private String patternName;
    private String[] phoneNumberParts;
    private BitSet phoneNumberPrefixs;
    private BitSet phoneNumberSuffixs;

    public SMSBodyKeywordInfo(List<String[]> list, String[] arrstring, BitSet bitSet, BitSet bitSet2, String[] arrstring2, String string2) {
        this.keywords = list;
        this.phoneNumberParts = arrstring;
        this.phoneNumberPrefixs = bitSet;
        this.phoneNumberSuffixs = bitSet2;
        this.areas = arrstring2;
        this.patternName = string2;
    }

    /*
     * Enabled aggressive block sorting
     */
    private int[] findFirstIndex(String string2, int n, String[] arrstring) {
        int n2 = Integer.MAX_VALUE;
        int n3 = Integer.MAX_VALUE;
        int n4 = arrstring.length;
        int n5 = 0;
        do {
            int n6;
            if (n5 >= n4) {
                if (n2 == Integer.MAX_VALUE) break;
                return new int[]{n2, n3};
            }
            String string3 = arrstring[n5];
            int n7 = string2.indexOf(string3, n);
            if (n7 < 0) {
                n6 = n2;
            } else {
                n6 = n2;
                if (n2 > n7) {
                    n6 = n7;
                    n3 = n7 + string3.length();
                }
            }
            ++n5;
            n2 = n6;
        } while (true);
        return null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private int matchArea(String string2) {
        if (this.areas == null) return 0;
        if (this.areas.length == 0) {
            return 0;
        }
        if (this.areas == null) return -1;
        if (this.areas.length <= 0) return -1;
        if (string2 == null) return -1;
        if (string2.equals((Object)"")) {
            return -1;
        }
        int n = 0;
        while (n < this.areas.length) {
            int n2 = n;
            if (string2.startsWith(this.areas[n])) return n2;
            ++n;
        }
        return -1;
    }

    /*
     * Enabled aggressive block sorting
     */
    private int matchPhoneNumber(String string2) {
        if (this.phoneNumberParts == null) return 0;
        if (this.phoneNumberParts.length == 0) {
            return 0;
        }
        int n = 0;
        while (n < this.phoneNumberParts.length) {
            boolean bl = this.phoneNumberPrefixs.get(n);
            boolean bl2 = this.phoneNumberSuffixs.get(n);
            if (bl && bl2) {
                int n2 = n;
                if (string2.equals((Object)this.phoneNumberParts[n])) return n2;
            } else if (bl ? string2.startsWith(this.phoneNumberParts[n]) : string2.endsWith(this.phoneNumberParts[n])) {
                return n;
            }
            ++n;
        }
        return -1;
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean matchSMSBody(String string2) {
        if (this.keywords != null && this.keywords.size() != 0) {
            int n = 0;
            Iterator<String[]> iterator = this.keywords.iterator();
            while (iterator.hasNext()) {
                int[] arrn = this.findFirstIndex(string2, n, iterator.next());
                if (arrn == null) {
                    return false;
                }
                n = arrn[1];
            }
        }
        return true;
    }

    public String getPatternName() {
        return this.patternName;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean match(String string2, String string3, String string4) {
        if (this.matchArea(string3) < 0 || this.matchPhoneNumber(string2) < 0) {
            return false;
        }
        return this.matchSMSBody(string4);
    }
}

