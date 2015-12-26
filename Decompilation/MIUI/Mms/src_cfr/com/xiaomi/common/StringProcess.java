/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Character
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.ArrayList
 *  java.util.HashSet
 *  java.util.regex.Pattern
 */
package com.xiaomi.common;

import com.xiaomi.common.Log;
import com.xiaomi.common.StringInt;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public final class StringProcess {
    public static Pattern rxChinese = Pattern.compile((String)"^[\uff0c\u3001\u300a\u300b\u3002\uff1b\uff1f\uff01\uff1a\u2018\u201c\u201d\u2019\u3010\u3011\uff08\uff09\u4e00-\u9fa5\uf900-\ufa2d]$");

    public static ContainEnum EndWithDic(String string2, HashSet<String> hashSet, int n) {
        int n2;
        if (string2.equals((Object)"")) {
            return ContainEnum.None;
        }
        n = n2 = string2.length() - n;
        if (n2 < 0) {
            n = 0;
        }
        while (n < string2.length()) {
            if (hashSet.contains((Object)string2.substring(n))) {
                if (n == 0) {
                    return ContainEnum.Equal;
                }
                return ContainEnum.EndWith;
            }
            ++n;
        }
        return ContainEnum.None;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int EndWithDicWithVagueDistance(String string2, Set<String> set, int n, int n2) {
        if (set == null) return -1;
        if (set.size() == 0) {
            return -1;
        }
        if (string2.length() < 2) {
            return -1;
        }
        int n3 = 0;
        block0 : while (n3 <= n2) {
            int n4;
            int n5 = n4 = string2.length() - n - n3;
            if (n4 < 0) {
                n5 = 0;
            }
            do {
                if (n5 >= string2.length() - n3) {
                    ++n3;
                    continue block0;
                }
                n4 = n3;
                if (set.contains(string2.substring(n5, string2.length() - n3))) return n4;
                ++n5;
            } while (true);
            break;
        }
        return -1;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int StartWithDicWithVagueDistance(String string2, Set<String> set, int n, int n2) {
        if (set == null) return -1;
        if (set.size() == 0) {
            return -1;
        }
        if (string2.length() < 1) {
            return -1;
        }
        int n3 = 0;
        block0 : while (n3 <= n2) {
            int n4;
            int n5 = n4 = n + n3;
            if (n4 > string2.length()) {
                n5 = string2.length();
            }
            n4 = n5;
            do {
                if (n4 <= n3) {
                    ++n3;
                    continue block0;
                }
                n5 = n3;
                if (set.contains(string2.substring(n3, n4))) return n5;
                --n4;
            } while (true);
            break;
        }
        return -1;
    }

    public static int count(String string2, String string3) {
        int n = 0;
        while (string2 != null && string2.length() != 0 && string2.indexOf(string3) != -1) {
            ++n;
            string2 = string2.substring(string2.indexOf(string3) + 1);
        }
        return n;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean endWith(String string2, int n, String string3) {
        if (n >= string3.length()) {
            int n2 = string3.length() - 1;
            int n3 = n - 1;
            do {
                if (n3 < n - string3.length()) {
                    return true;
                }
                if (string2.charAt(n3) != string3.charAt(n2)) break;
                --n3;
                --n2;
            } while (true);
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static List<StringInt> findAllResultsFromList(String string2, HashSet<String> hashSet, int n) {
        ArrayList arrayList = new ArrayList();
        int n2 = 0;
        try {
            do {
                StringInt stringInt;
                if ((stringInt = StringProcess.findResultFromListWithPosition(string2, n2, hashSet, n)) == null) {
                    return arrayList;
                }
                arrayList.add(stringInt);
                n2 = stringInt.getNum();
                int n3 = stringInt.getName().length();
                n2 += n3;
            } while (true);
        }
        catch (StackOverflowError var1_2) {
            Log.i("StringProcess", string2);
            return arrayList;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int findFirstChar(String string2, int n) {
        while (n < string2.length()) {
            int n2;
            if (string2.charAt(n) >= '0') {
                n2 = n;
                if (string2.charAt(n) <= '9') return n2;
            }
            if (string2.charAt(n) >= 'a') {
                n2 = n;
                if (string2.charAt(n) <= 'z') return n2;
            }
            if (string2.charAt(n) >= 'A') {
                n2 = n;
                if (string2.charAt(n) <= 'Z') return n2;
            }
            ++n;
        }
        return -1;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int findFirstEng(String string2, int n) {
        while (n < string2.length()) {
            int n2;
            if (string2.charAt(n) >= 'a') {
                n2 = n;
                if (string2.charAt(n) <= 'z') return n2;
            }
            if (string2.charAt(n) >= 'A') {
                n2 = n;
                if (string2.charAt(n) <= 'Z') return n2;
            }
            ++n;
        }
        return -1;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int findFirstNumber(String string2, int n) {
        while (n < string2.length()) {
            if (string2.charAt(n) >= '0') {
                int n2 = n;
                if (string2.charAt(n) <= '9') return n2;
            }
            ++n;
        }
        return -1;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static StringInt findResultFromListWithPosition(String string2, int n, HashSet<String> hashSet, int n2) {
        if (n >= string2.length()) {
            return null;
        }
        int n3 = n;
        block0 : while (n3 < string2.length()) {
            int n4;
            n = n4 = n3 + n2;
            if (n4 > string2.length()) {
                n = string2.length();
            }
            do {
                if (n < n3) {
                    ++n3;
                    continue block0;
                }
                String string3 = string2.substring(n3, n);
                if (hashSet.contains((Object)string3)) {
                    return new StringInt(n3, string3);
                }
                --n;
            } while (true);
            break;
        }
        return null;
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

    public static ASCType getASCType(char c2) {
        if (c2 >= 'a' && c2 <= 'z') {
            return ASCType.EnglishLowerCase;
        }
        if (c2 >= 'A' && c2 <= 'Z') {
            return ASCType.EnglishUpper;
        }
        if (c2 >= '0' && c2 <= '9') {
            return ASCType.Number;
        }
        switch (c2) {
            default: {
                return ASCType.Other;
            }
            case '\u4e00': 
            case '\u4e03': 
            case '\u4e07': 
            case '\u4e09': 
            case '\u4e5d': 
            case '\u4e8c': 
            case '\u4e94': 
            case '\u4ebf': 
            case '\u516b': 
            case '\u516d': 
            case '\u5341': 
            case '\u5343': 
            case '\u56db': 
            case '\u767e': 
        }
        return ASCType.ChineseNumber;
    }

    public static ASCType getASCType(String string2) {
        if (string2 == null || string2.length() != 1) {
            return ASCType.Other;
        }
        return StringProcess.getASCType(string2.charAt(0));
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static /* varargs */ int getCharCount(String var0, Character ... var1_1) {
        var3_2 = 0;
        var2_3 = 0;
        block0 : do {
            if (var2_3 >= var0.length()) {
                return var3_2;
            }
            var5_5 = var0.charAt(var2_3);
            var6_6 = var1_1.length;
            var4_4 = 0;
            do {
                if (var4_4 >= var6_6) ** GOTO lbl13
                if (var5_5 == var1_1[var4_4].charValue()) {
                    ++var3_2;
lbl13: // 2 sources:
                    ++var2_3;
                    continue block0;
                }
                ++var4_4;
            } while (true);
            break;
        } while (true);
    }

    /*
     * Exception decompiling
     */
    public static int getEngCharCount(String var0) {
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

    public static int getNumberCount(String string2) {
        int n = 0;
        int n2 = 0;
        while (n2 < string2.length()) {
            int n3 = n;
            if (string2.charAt(n2) >= '0') {
                n3 = n;
                if (string2.charAt(n2) <= '9') {
                    n3 = n + 1;
                }
            }
            ++n2;
            n = n3;
        }
        return n;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean ifCharTypeSame(String object, int n, int n2) {
        ASCType aSCType;
        boolean bl;
        boolean bl2 = true;
        if (n >= object.length()) return false;
        if (n2 >= object.length()) {
            return false;
        }
        char c2 = object.charAt(n);
        char c3 = object.charAt(n2);
        object = StringProcess.getASCType(c2);
        if (object == (aSCType = StringProcess.getASCType(c3))) {
            bl = bl2;
            if (object == ASCType.Number) return bl;
        }
        if (object == aSCType) {
            bl = bl2;
            if (object == ASCType.ChineseNumber) return bl;
        }
        if (object != ASCType.EnglishLowerCase) {
            if (object != ASCType.EnglishUpper) return false;
        }
        bl = bl2;
        if (aSCType == ASCType.EnglishLowerCase) return bl;
        bl = bl2;
        if (aSCType == ASCType.EnglishUpper) return bl;
        return false;
    }

    public static boolean isNumber(String string2) {
        int n = 0;
        while (n < string2.length()) {
            if (string2.charAt(n) < '0' || string2.charAt(n) > '9') {
                return false;
            }
            ++n;
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean isRealNumber(String string2) {
        boolean bl = false;
        boolean bl2 = false;
        int n = 0;
        while (n < string2.length()) {
            boolean bl3;
            if (string2.charAt(n) == '.') {
                bl3 = bl;
                if (bl2) return bl3;
                bl2 = true;
            } else {
                bl3 = bl;
                if (string2.charAt(n) < '0') return bl3;
                if (string2.charAt(n) > '9') {
                    return false;
                }
            }
            ++n;
        }
        return true;
    }

    public static String[] noRegexSplit(String string2, String string3) {
        return StringProcess.noRegexSplit(string2, string3, true);
    }

    public static String[] noRegexSplit(String string2, String string3, boolean bl) {
        if (string2.equals((Object)"")) {
            return new String[]{""};
        }
        ArrayList arrayList = new ArrayList();
        int n = 0;
        do {
            int n2;
            if ((n2 = string2.indexOf(string3, n)) < 0) {
                if (n < string2.length() || !bl) {
                    arrayList.add(string2.substring(n));
                }
                return arrayList.toArray(new String[arrayList.size()]);
            }
            if (n2 > n || !bl) {
                arrayList.add(string2.substring(n, n2));
            }
            n = n2 + string3.length();
        } while (true);
    }

    public static String[] noRegexSplit(String string2, String[] arrstring) {
        return StringProcess.noRegexSplit(string2, arrstring, true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static String[] noRegexSplit(String string2, String[] arrstring, boolean bl) {
        if (string2.equals((Object)"")) {
            return new String[]{""};
        }
        ArrayList arrayList = new ArrayList();
        boolean[] arrbl = new boolean[arrstring.length];
        int n = 0;
        int n2 = 1;
        block0 : do {
            if (n2 == 0) {
                if (n < string2.length() || !bl) {
                    arrayList.add(string2.substring(n));
                }
                return arrayList.toArray(new String[arrayList.size()]);
            }
            int n3 = Integer.MAX_VALUE;
            int n4 = 0;
            int n5 = -1;
            n2 = 0;
            do {
                int n6;
                if (n2 >= arrstring.length) {
                    n2 = n4;
                    if (n5 < 0) continue block0;
                    if (n3 > n || !bl) {
                        arrayList.add(string2.substring(n, n3));
                    }
                    n = arrstring[n5].length() + n3;
                    n2 = n4;
                    continue block0;
                }
                if (arrbl[n2]) {
                    n6 = n3;
                } else {
                    int n7 = string2.indexOf(arrstring[n2], n);
                    if (n7 < 0) {
                        arrbl[n2] = true;
                        n6 = n3;
                    } else {
                        n6 = n3;
                        if (n3 > n7) {
                            n5 = n2;
                            n6 = n7;
                            n4 = 1;
                        }
                    }
                }
                ++n2;
                n3 = n6;
            } while (true);
            break;
        } while (true);
    }

    public static boolean startWith(String string2, int n, String string3) {
        return string2.startsWith(string3, n);
    }

    public static /* varargs */ String trim(String string2, Character ... arrcharacter) {
        return StringProcess.trimEnd(StringProcess.trimStart(string2, arrcharacter), arrcharacter);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static /* varargs */ String trimEnd(String var0, Character ... var1_1) {
        if (var0 == null) return var0;
        if (var1_1 == null) {
            return var0;
        }
        var4_2 = var0.length();
        var2_3 = var0.length() - 1;
        block0 : do {
            if (var2_3 < 0) {
                return var0.substring(0, var4_2);
            }
            var7_7 = false;
            if (var1_1.length != 0) break;
            var3_4 = var4_2;
            var5_5 = var7_7;
            if (Character.isWhitespace((char)var0.charAt(var2_3))) {
                var3_4 = var2_3;
                var5_5 = true;
            }
lbl16: // 5 sources:
            do {
                var4_2 = var3_4;
                if (var5_5 == false) return var0.substring(0, var4_2);
                --var2_3;
                var4_2 = var3_4;
                continue block0;
                break;
            } while (true);
            break;
        } while (true);
        var6_6 = 0;
        do {
            var3_4 = var4_2;
            var5_5 = var7_7;
            if (var6_6 >= var1_1.length) ** GOTO lbl16
            if (var0.charAt(var2_3) == var1_1[var6_6].charValue()) {
                var3_4 = var2_3;
                var5_5 = true;
                ** continue;
            }
            ++var6_6;
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static /* varargs */ String trimStart(String var0, Character ... var1_1) {
        if (var0 == null) return var0;
        if (var1_1 == null) {
            return var0;
        }
        var3_2 = 0;
        var5_3 = 0;
        block0 : do {
            if (var5_3 >= var0.length()) {
                return var0.substring(var3_2);
            }
            var7_7 = false;
            if (var1_1.length != 0) break;
            var4_5 = var7_7;
            var2_4 = var3_2;
            if (Character.isWhitespace((char)var0.charAt(var5_3))) {
                var2_4 = var5_3 + 1;
                var4_5 = true;
            }
lbl16: // 5 sources:
            do {
                var3_2 = var2_4;
                if (var4_5 == false) return var0.substring(var3_2);
                ++var5_3;
                var3_2 = var2_4;
                continue block0;
                break;
            } while (true);
            break;
        } while (true);
        var6_6 = 0;
        do {
            var4_5 = var7_7;
            var2_4 = var3_2;
            if (var6_6 >= var1_1.length) ** GOTO lbl16
            if (var0.charAt(var5_3) == var1_1[var6_6].charValue()) {
                var2_4 = var5_3 + 1;
                var4_5 = true;
                ** continue;
            }
            ++var6_6;
        } while (true);
    }

    public static int wordsCount(String string2) {
        int n = 0;
        int n2 = string2.indexOf("  ", 0);
        while (n2 > 0) {
            ++n;
            n2 = string2.indexOf("  ", n2 + 1);
        }
        return n;
    }

    public static enum ASCType {
        EnglishUpper,
        EnglishLowerCase,
        Number,
        ChineseNumber,
        Other;
        

        private ASCType(String string3, int n2) {
        }
    }

    public static enum ContainEnum {
        StartWith,
        EndWith,
        Equal,
        None;
        

        private ContainEnum(String string3, int n2) {
        }
    }

}

