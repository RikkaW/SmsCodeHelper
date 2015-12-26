/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.HashMap
 *  java.util.HashSet
 */
package com.xiaomi.smsunderstand.task;

import com.xiaomi.common.Log;
import com.xiaomi.nlp.Parser;
import com.xiaomi.smsunderstand.EntityInfo;
import com.xiaomi.smsunderstand.SMSUnderstand;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SpecialEntityRecognition {
    private static boolean ifInitial = false;
    private static HashMap<String, Parser> parserFileName2Parser;
    private static Set<String> parserToFree;

    /*
     * Exception decompiling
     */
    public static List<EntityInfo> findKnowledge(String var0, String var1_1, HashSet<String> var2_2) throws Exception {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[UNCONDITIONALDOLOOP]], but top level block is 2[UNCONDITIONALDOLOOP]
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

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean freeParseHard() {
        if (!ifInitial) {
            return false;
        }
        if (parserToFree == null) return true;
        Iterator<String> iterator = parserToFree.iterator();
        do {
            if (!iterator.hasNext()) {
                parserToFree = null;
                return true;
            }
            String string2 = iterator.next();
            parserFileName2Parser.remove((Object)string2);
            Log.i("Time", String.valueOf((Object)string2) + " free finish.");
        } while (true);
    }

    public static boolean freeParseHard(String string2) {
        if (!ifInitial) {
            return false;
        }
        parserFileName2Parser.remove((Object)string2);
        Log.i("Time", String.valueOf((Object)string2) + " free finish.");
        return true;
    }

    public static boolean freeParseSoft(String string2) {
        if (!ifInitial) {
            return false;
        }
        if (parserToFree == null) {
            parserToFree = new HashSet(10);
        }
        parserToFree.add(string2);
        Log.i("Time", String.valueOf((Object)string2) + " free finish.");
        return true;
    }

    public static boolean freeResource() {
        if (!ifInitial) {
            return true;
        }
        parserFileName2Parser = null;
        ifInitial = false;
        return true;
    }

    public static boolean initial() throws Exception {
        if (ifInitial) {
            return true;
        }
        Log.i("SpecialEntityRecognition", "inital SpecialEntity!");
        parserFileName2Parser = new HashMap();
        ifInitial = true;
        return true;
    }

    public static boolean loadParse(String string2) {
        if (!ifInitial) {
            return false;
        }
        if ((Parser)parserFileName2Parser.get((Object)string2) == null) {
            long l = System.currentTimeMillis();
            Parser parser = new Parser(String.valueOf((Object)SMSUnderstand.dictionaryPath) + "/patterns/" + string2 + ".pattern");
            long l2 = System.currentTimeMillis();
            Log.i("Time", String.valueOf((Object)string2) + " load time:" + (l2 - l) + " ms.");
            parserFileName2Parser.put((Object)string2, (Object)parser);
        }
        return true;
    }
}

