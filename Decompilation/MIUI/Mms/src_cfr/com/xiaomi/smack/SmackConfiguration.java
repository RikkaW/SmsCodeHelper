/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.PrintStream
 *  java.lang.Class
 *  java.lang.ClassLoader
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.util.ArrayList
 *  java.util.Vector
 *  org.xmlpull.v1.XmlPullParser
 */
package com.xiaomi.smack;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Vector;
import org.xmlpull.v1.XmlPullParser;

public final class SmackConfiguration {
    private static Vector<String> defaultMechs;
    private static int keepAliveInterval;
    private static int packetReplyTimeout;
    private static int pingInterval;
    private static int serverShutdownTimeout;

    /*
     * Exception decompiling
     */
    static {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.lang.IllegalStateException: Backjump on non jumping statement [] lbl66 : TryStatement: try { 6[TRYBLOCK]

        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner$1.call(Cleaner.java:44)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner$1.call(Cleaner.java:22)
        // org.benf.cfr.reader.util.graph.GraphVisitorDFS.process(GraphVisitorDFS.java:68)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner.removeUnreachableCode(Cleaner.java:54)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.RemoveDeterministicJumps.apply(RemoveDeterministicJumps.java:35)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:507)
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

    private SmackConfiguration() {
    }

    public static int getCheckAliveInterval() {
        return keepAliveInterval;
    }

    private static ClassLoader[] getClassLoaders() {
        ClassLoader[] arrclassLoader = new ClassLoader[]{SmackConfiguration.class.getClassLoader(), Thread.currentThread().getContextClassLoader()};
        ArrayList arrayList = new ArrayList();
        for (ClassLoader classLoader : arrclassLoader) {
            if (classLoader == null) continue;
            arrayList.add(classLoader);
        }
        return arrayList.toArray((T[])new ClassLoader[arrayList.size()]);
    }

    public static int getPingInteval() {
        return pingInterval;
    }

    public static String getVersion() {
        return "3.1.0";
    }

    private static void parseClassToLoad(XmlPullParser object) throws Exception {
        object = object.nextText();
        try {
            Class.forName((String)object);
            return;
        }
        catch (ClassNotFoundException var1_1) {
            System.err.println("Error! A startup class specified in smack-config.xml could not be loaded: " + (String)object);
            return;
        }
    }

    private static int parseIntProperty(XmlPullParser xmlPullParser, int n) throws Exception {
        try {
            int n2 = Integer.parseInt((String)xmlPullParser.nextText());
            return n2;
        }
        catch (NumberFormatException var0_1) {
            var0_1.printStackTrace();
            return n;
        }
    }
}

