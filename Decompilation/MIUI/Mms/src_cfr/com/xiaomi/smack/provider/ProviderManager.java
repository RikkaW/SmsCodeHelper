/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.ClassLoader
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.util.ArrayList
 *  java.util.concurrent.ConcurrentHashMap
 */
package com.xiaomi.smack.provider;

import com.xiaomi.smack.provider.PacketExtensionProvider;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProviderManager {
    private static ProviderManager instance;
    private Map<String, Object> extensionProviders = new ConcurrentHashMap();
    private Map<String, Object> iqProviders = new ConcurrentHashMap();

    private ProviderManager() {
        this.initialize();
    }

    private ClassLoader[] getClassLoaders() {
        ClassLoader[] arrclassLoader = new ClassLoader[]{ProviderManager.class.getClassLoader(), Thread.currentThread().getContextClassLoader()};
        ArrayList arrayList = new ArrayList();
        for (ClassLoader classLoader : arrclassLoader) {
            if (classLoader == null) continue;
            arrayList.add(classLoader);
        }
        return arrayList.toArray((T[])new ClassLoader[arrayList.size()]);
    }

    public static ProviderManager getInstance() {
        synchronized (ProviderManager.class) {
            if (instance == null) {
                instance = new ProviderManager();
            }
            ProviderManager providerManager = instance;
            return providerManager;
        }
    }

    private String getProviderKey(String string2, String string3) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(string2).append("/>");
        if (string2 != null) {
            stringBuilder.append("<").append(string3).append("/>");
        }
        return stringBuilder.toString();
    }

    public void addExtensionProvider(String string2, String string3, Object object) {
        if (!(object instanceof PacketExtensionProvider) && !(object instanceof Class)) {
            throw new IllegalArgumentException("Provider must be a PacketExtensionProvider or a Class instance.");
        }
        string2 = this.getProviderKey(string2, string3);
        this.extensionProviders.put(string2, object);
    }

    public Object getExtensionProvider(String string2, String string3) {
        string2 = this.getProviderKey(string2, string3);
        return this.extensionProviders.get(string2);
    }

    /*
     * Exception decompiling
     */
    protected void initialize() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.lang.IllegalStateException: Backjump on non jumping statement [] lbl82 : TryStatement: try { 8[TRYBLOCK]

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
}

