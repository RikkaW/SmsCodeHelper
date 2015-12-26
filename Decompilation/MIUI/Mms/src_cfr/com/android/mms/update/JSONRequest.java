/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.preference.PreferenceManager
 *  android.util.Log
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  miui.util.CoderUtils
 */
package com.android.mms.update;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import com.android.mms.update.Request;
import miui.util.CoderUtils;

public class JSONRequest
extends Request {
    private static long sExpiredTime;
    private String mData;
    private String mDescription;
    private String mEtag;
    private SharedPreferences mPref;
    private int mStatus = -1;

    public JSONRequest(Context context, String string2) {
        super(context, string2);
        this.mPref = PreferenceManager.getDefaultSharedPreferences((Context)context);
    }

    /*
     * Exception decompiling
     */
    private int getRequestStatus() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Nonsensical loop would be emitted - failure
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.LoopIdentifier.considerAsDoLoopStart(LoopIdentifier.java:403)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.LoopIdentifier.identifyLoops1(LoopIdentifier.java:60)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:614)
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

    private String getUrlRequestTimeKey(String string2) {
        return String.format((String)"pref_last_request_mms_time", (Object[])new Object[]{CoderUtils.encodeSHA((String)string2)});
    }

    private boolean prohibitRequest(String string2) {
        boolean bl;
        boolean bl2 = bl = false;
        if (this.mPref.getBoolean("pref_mms_client_error", false)) {
            bl2 = bl;
            if (Math.abs((long)(System.currentTimeMillis() - this.mPref.getLong(this.getUrlRequestTimeKey(string2), 0))) < sExpiredTime) {
                bl2 = true;
            }
        }
        return bl2;
    }

    private void setClientError(String string2, boolean bl) {
        this.mPref.edit().putBoolean("pref_mms_client_error", bl).commit();
        if (bl) {
            Log.e((String)"JSONRequest", (String)("setClientError for url " + string2));
            this.mPref.edit().putLong(this.getUrlRequestTimeKey(string2), System.currentTimeMillis()).commit();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public int getStatus() {
        if (this.mStatus != -1) return this.mStatus;
        this.mStatus = this.getRequestStatus();
        if (this.mStatus != 4) return this.mStatus;
        if (!"GET".equals((Object)this.mRequestMethod)) return this.mStatus;
        try {
            Log.d((String)"JSONRequest", (String)"server error, sleep 5000ms and retry");
            Thread.sleep((long)5000);
            this.mStatus = this.getRequestStatus();
            return this.mStatus;
        }
        catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
            return this.mStatus;
        }
    }

    public String requestData() {
        return this.mData;
    }
}

