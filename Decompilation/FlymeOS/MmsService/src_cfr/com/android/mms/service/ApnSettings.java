/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.database.Cursor
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.service;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.android.mms.service.exception.ApnException;

public class ApnSettings {
    private static final String[] APN_PROJECTION = new String[]{"type", "mmsc", "mmsproxy", "mmsport", "name", "apn", "bearer", "protocol", "roaming_protocol", "authtype", "mvno_type", "mvno_match_data", "proxy", "port", "server", "user", "password"};
    private final String mDebugText;
    private final String mProxyAddress;
    private final int mProxyPort;
    private final String mServiceCenter;

    public ApnSettings(String string, String string2, int n, String string3) {
        this.mServiceCenter = string;
        this.mProxyAddress = string2;
        this.mProxyPort = n;
        this.mDebugText = string3;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static String getDebugText(Cursor cursor) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("APN [");
        int n = 0;
        do {
            if (n >= cursor.getColumnCount()) {
                stringBuilder.append("]");
                return stringBuilder.toString();
            }
            String string = cursor.getColumnName(n);
            String string2 = cursor.getString(n);
            if (!TextUtils.isEmpty((CharSequence)string2)) {
                if (n > 0) {
                    stringBuilder.append(' ');
                }
                stringBuilder.append(string).append('=').append(string2);
            }
            ++n;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static boolean isValidApnType(String arrstring, String string) {
        if (TextUtils.isEmpty((CharSequence)arrstring)) {
            return true;
        }
        arrstring = arrstring.split(",");
        int n = arrstring.length;
        int n2 = 0;
        while (n2 < n) {
            String string2 = arrstring[n2].trim();
            if (string2.equals((Object)string)) return true;
            if (string2.equals((Object)"*")) return true;
            ++n2;
        }
        return false;
    }

    /*
     * Exception decompiling
     */
    public static ApnSettings load(Context var0, String var1_4, int var2_5) throws ApnException {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 5[CATCHBLOCK]
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

    private static String trimWithNullCheck(String string) {
        if (string != null) {
            return string.trim();
        }
        return null;
    }

    public String getMmscUrl() {
        return this.mServiceCenter;
    }

    public String getProxyAddress() {
        return this.mProxyAddress;
    }

    public int getProxyPort() {
        return this.mProxyPort;
    }

    public boolean isProxySet() {
        if (!TextUtils.isEmpty((CharSequence)this.mProxyAddress)) {
            return true;
        }
        return false;
    }

    public String toString() {
        return this.mDebugText;
    }
}

