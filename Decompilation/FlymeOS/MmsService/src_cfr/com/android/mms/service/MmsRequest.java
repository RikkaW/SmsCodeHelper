/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.PendingIntent
 *  android.app.PendingIntent$CanceledException
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.net.Uri
 *  android.os.Bundle
 *  android.service.carrier.ICarrierMessagingCallback
 *  android.service.carrier.ICarrierMessagingCallback$Stub
 *  android.util.Log
 *  com.meizu.android.mms.util.MzSqliteWrapper
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.android.mms.service;

import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.service.carrier.ICarrierMessagingCallback;
import android.util.Log;
import com.android.mms.service.ApnSettings;
import com.android.mms.service.MmsConfig;
import com.android.mms.service.MmsConfigManager;
import com.android.mms.service.MmsNetworkManager;
import com.android.mms.service.exception.MmsHttpException;
import com.meizu.android.mms.util.MzSqliteWrapper;

public abstract class MmsRequest {
    protected String mCreator;
    protected Uri mMessageUri;
    protected MmsConfig.Overridden mMmsConfig;
    protected Bundle mMmsConfigOverrides;
    protected RequestManager mRequestManager;
    protected int mSubId;

    public MmsRequest(RequestManager requestManager, Uri uri, int n, String string, Bundle bundle) {
        this.mRequestManager = requestManager;
        this.mMessageUri = uri;
        this.mSubId = n;
        this.mCreator = string;
        this.mMmsConfigOverrides = bundle;
        this.mMmsConfig = null;
    }

    private boolean ensureMmsConfigLoaded() {
        MmsConfig mmsConfig;
        if (this.mMmsConfig == null && (mmsConfig = MmsConfigManager.getInstance().getMmsConfigBySubId(this.mSubId)) != null) {
            this.mMmsConfig = new MmsConfig.Overridden(mmsConfig, this.mMmsConfigOverrides);
        }
        if (this.mMmsConfig != null) {
            return true;
        }
        return false;
    }

    protected static int toSmsManagerResult(int n) {
        switch (n) {
            default: {
                return 1;
            }
            case 0: {
                return -1;
            }
            case 1: 
        }
        return 6;
    }

    protected abstract int checkResponse(byte[] var1);

    protected abstract byte[] doHttp(Context var1, MmsNetworkManager var2, ApnSettings var3) throws MmsHttpException;

    /*
     * Exception decompiling
     */
    public void execute(Context var1_1, MmsNetworkManager var2_2) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 10[CATCHBLOCK]
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

    protected abstract PendingIntent getPendingIntent();

    public int getSubId() {
        return this.mSubId;
    }

    protected boolean maybeFallbackToRegularDelivery(int n) {
        if (n == 1 || n == 1) {
            Log.d((String)"MmsService", (String)"Sending/downloading MMS by IP failed.");
            this.mRequestManager.addSimRequest(this);
            return true;
        }
        return false;
    }

    protected abstract Uri persistIfRequired(Context var1, int var2, byte[] var3);

    protected abstract boolean prepareForHttpRequest();

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public void processResult(Context var1_1, int var2_3, byte[] var3_4, int var4_6) {
        block15 : {
            block13 : {
                var9_7 = this.getPendingIntent();
                if (var9_7 == null) ** GOTO lbl33
                var8_8 = Uri.parse((String)var9_7.getIntent().getStringExtra("bundle_uri"));
                Log.d((String)"MmsService", (String)("processResult: mMessageUri = " + var8_8.toString()));
                var7_10 = null;
                var6_11 = null;
                try {
                    var8_8 = MzSqliteWrapper.query((Context)var1_1, (ContentResolver)var1_1.getContentResolver(), (Uri)var8_8, (String[])new String[]{"_id", "thread_id", "m_size"}, (String)null, (String[])null, (String)null);
                    if (var8_8 != null) {
                        var6_11 = var8_8;
                        var7_10 = var8_8;
                        if (var8_8.getCount() != 0) break block13;
                    }
                    var6_11 = var8_8;
                    var7_10 = var8_8;
                    Log.e((String)"MmsService", (String)"Wap push has been deleted !");
                    if (var8_8 == null) return;
                }
                catch (Exception var8_9) {
                    block14 : {
                        var7_10 = var6_11;
                        try {
                            Log.e((String)"MmsService", (String)"MmsRequest: processResult query fail !", (Throwable)var8_9);
                            if (var6_11 == null) break block14;
                        }
                        catch (Throwable var1_2) {
                            if (var7_10 == null) throw var1_2;
                            var7_10.close();
                            throw var1_2;
                        }
                        var6_11.close();
                    }
                    var6_11 = this.persistIfRequired(var1_1, var2_3, var3_4);
                    Log.d((String)"MmsService", (String)"processResult: prepare");
                    if (var9_7 != null) {
                        var5_12 = true;
                        var7_10 = new Intent();
                        if (var3_4 != null) {
                            var5_12 = this.transferResponse(var7_10, var3_4);
                        }
                        if (var6_11 != null) {
                            Log.d((String)"MmsService", (String)("processResult: messageUri = " + (Object)var6_11));
                            var7_10.putExtra("uri", var6_11.toString());
                        }
                        this.putOriginalUrl(var7_10);
                        if (var2_3 == 4 && var4_6 != 0) {
                            var7_10.putExtra("android.telephony.extra.MMS_HTTP_STATUS", var4_6);
                        }
                        if (!var5_12) {
                            var2_3 = 5;
                        }
                        Log.d((String)"MmsService", (String)"processResult: pendingIntent will be Send.");
                        var9_7.send(var1_1, var2_3, var7_10);
                    }
                    break block15;
                }
                var8_8.close();
                return;
            }
            if (var8_8 != null) {
                var8_8.close();
            }
            ** GOTO lbl33
            catch (PendingIntent.CanceledException var3_5) {
                Log.e((String)"MmsService", (String)"MmsRequest: sending pending intent canceled", (Throwable)var3_5);
            }
        }
        this.revokeUriPermission(var1_1);
    }

    protected abstract void putOriginalUrl(Intent var1);

    protected abstract void revokeUriPermission(Context var1);

    protected abstract boolean transferResponse(Intent var1, byte[] var2);

    protected abstract class CarrierMmsActionCallback
    extends ICarrierMessagingCallback.Stub {
        protected CarrierMmsActionCallback() {
        }

        public void onFilterComplete(boolean bl) {
            Log.e((String)"MmsService", (String)("Unexpected onFilterComplete call with result: " + bl));
        }

        public void onSendMultipartSmsComplete(int n, int[] arrn) {
            Log.e((String)"MmsService", (String)("Unexpected onSendMultipartSmsComplete call with result: " + n));
        }

        public void onSendSmsComplete(int n, int n2) {
            Log.e((String)"MmsService", (String)("Unexpected onSendSmsComplete call with result: " + n));
        }
    }

    public static interface RequestManager {
        public void addSimRequest(MmsRequest var1);

        public boolean getAutoPersistingPref();

        public byte[] readPduFromContentUri(Uri var1, int var2);

        public boolean writePduToContentUri(Uri var1, byte[] var2);
    }

}

