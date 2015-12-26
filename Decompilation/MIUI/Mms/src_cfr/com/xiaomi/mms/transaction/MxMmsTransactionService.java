/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentUris
 *  android.content.Context
 *  android.content.Intent
 *  android.database.Cursor
 *  android.net.Uri
 *  android.os.Bundle
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.text.TextUtils
 *  com.xiaomi.accountsdk.account.data.ExtendedAuthToken
 *  com.xiaomi.accountsdk.activate.ActivateManager
 *  com.xiaomi.accountsdk.activate.ActivateManager$ActivateManagerFuture
 *  com.xiaomi.micloudsdk.micloudrichmedia.MiCloudRichMediaManager
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  miui.push.CommonPacketExtension
 *  miui.push.Message
 *  miui.push.ServiceClient
 */
package com.xiaomi.mms.transaction;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.text.TextUtils;
import com.android.mms.util.MSimUtils;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.accountsdk.activate.ActivateManager;
import com.xiaomi.micloudsdk.micloudrichmedia.MiCloudRichMediaManager;
import com.xiaomi.mms.transaction.MiCloudMxMmsTransactionHandler;
import com.xiaomi.mms.transaction.MiCloudRichMediaManagerFactory;
import com.xiaomi.mms.transaction.MxMmsTransactionHandler;
import com.xiaomi.mms.transaction.PushSession;
import com.xiaomi.mms.transaction.WakenService;
import com.xiaomi.mms.utils.MiCloudMmsCodec;
import com.xiaomi.mms.utils.MxConfigCompat;
import com.xiaomi.mms.utils.MxMessageLogicHelper;
import com.xiaomi.mms.utils.MxMessagePduHelper;
import com.xiaomi.mms.utils.PrefUtils;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.IOException;
import java.util.LinkedHashSet;
import miui.push.CommonPacketExtension;
import miui.push.Message;
import miui.push.ServiceClient;

public class MxMmsTransactionService
extends WakenService
implements MiCloudMxMmsTransactionHandler.MiCloudMediaManagerCallback,
MiCloudRichMediaManagerFactory {
    protected ExtendedAuthToken[] mFileToken;
    protected int mInvalidTokenCount;
    protected boolean mIsSendByMxV2 = false;
    protected MxMmsTransactionHandler mMxMmsTransactionHandler;
    protected ExtendedAuthToken[] mOtherIDCFileToken;
    protected MiCloudRichMediaManager[] mOtherIDCRichMediaManager;
    protected MiCloudRichMediaManager[] mRichMediaManager;
    protected final LinkedHashSet<MxTransaction> mTransactionSet = new LinkedHashSet();

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void enqueueAll() {
        Cursor cursor = MxMessagePduHelper.getPendingMxMessages((Context)this);
        if (cursor == null) return;
        {
            try {
                cursor.moveToPosition(-1);
                int n = 0;
                while (cursor.moveToNext()) {
                    int n2 = cursor.getColumnIndex("_id");
                    int n3 = cursor.getColumnIndexOrThrow("msg_box");
                    MxTransaction mxTransaction = new MxTransaction(ContentUris.withAppendedId((Uri)Telephony.Mms.CONTENT_URI, (long)cursor.getLong(n2)), cursor.getInt(n3));
                    if (this.mTransactionSet.contains((Object)mxTransaction)) continue;
                    ++n;
                    this.mTransactionSet.add((Object)mxTransaction);
                }
                MyLog.i("MxMmsTransactionService", "mx mms enqueued, count: " + n);
                return;
            }
            finally {
                cursor.close();
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void enqueueRetrieveTransaction(Uri object) {
        if (object == null) {
            MyLog.e("MxMmsTransactionService", "retrieve mms with null uri");
            return;
        } else {
            MxMessagePduHelper.markFailedMessageAsPending((Context)this, (Uri)object);
            if (this.mTransactionSet.contains(object = new MxTransaction((Uri)object, 1))) return;
            {
                this.mTransactionSet.add(object);
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void enqueueSendTransaction(Uri object) {
        if (object == null) {
            MyLog.e("MxMmsTransactionService", "send mms with null uri");
            return;
        } else {
            if (this.mTransactionSet.contains(object = new MxTransaction((Uri)object, 2))) return;
            {
                this.mTransactionSet.add(object);
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private ExtendedAuthToken getFileToken(String object) throws IOException {
        int n = PushSession.getInstance((Context)this).getSimIdByMid((String)object);
        if (n == -1) {
            return null;
        }
        if (this.mFileToken[n] != null) {
            return this.mFileToken[n];
        }
        Object object2 = PrefUtils.getString((Context)this, this.getFileTokenPref() + n);
        this.mFileToken[n] = ExtendedAuthToken.parse((String)object2);
        if (this.mFileToken[n] != null) {
            return this.mFileToken[n];
        }
        if (object != null) {
            Object object3 = ActivateManager.get((Context)this).getSimAuthToken(n, this.getMxSid(n));
            object2 = null;
            Object object4 = null;
            object = object2;
            try {
                object3 = (Bundle)object3.getResult();
                object = object2;
                object = object2 = object3.getString("user_token");
                object = object3 = object3.getString("user_security");
                object4 = object2;
                object2 = object;
            }
            catch (Exception var3_4) {
                MyLog.e("MxMmsTransactionService", "failed to get file upload token from server", var3_4);
                object2 = object4;
                object4 = object;
            }
            if (object4 != null && object2 != null) {
                this.mFileToken[n] = ExtendedAuthToken.build((String)object4, (String)object2);
            }
        }
        if (this.mFileToken[n] != null) {
            PrefUtils.saveString((Context)this, this.getFileTokenPref() + n, this.mFileToken[n].toPlain());
        }
        return this.mFileToken[n];
    }

    private void receiveMms(Uri uri) {
        this.mMxMmsTransactionHandler.handleReceiveMxMms(uri);
    }

    private void sendMms(Uri uri) {
        this.mMxMmsTransactionHandler.handleSendMxMms(uri);
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean sendPushMessage(Uri object, String string2, long l, String string3, String string4, String string5, String string6, long l2, long l3) {
        if (l <= 0) {
            l = MxMessageLogicHelper.nextMiId(true, false);
        }
        MxMessagePduHelper.moveMxMmsToOutbox((Context)this, (Uri)object, System.currentTimeMillis(), l);
        object = MxMessageLogicHelper.constructMmsBody(string3, l);
        string2 = MxMessageLogicHelper.obtainMessage(string2, string4);
        string2.setPacketID(String.valueOf((long)l));
        string3 = MxMessageLogicHelper.base64Encode((String)object);
        if (TextUtils.isEmpty((CharSequence)string3)) {
            string2.setBody((String)object);
        } else {
            string2.setBody(string3, "base64");
        }
        string2.addExtension(MxMessageLogicHelper.constructMmsAttachment(string6, string5, l2, l3));
        return ServiceClient.getInstance((Context)this).sendMessage((Message)string2);
    }

    public static void startRetrieveMms(Context context, Uri uri) {
        Intent intent = new Intent(context, (Class)MxMmsTransactionService.class);
        intent.setAction("com.xiaomi.mms.ACTION_RETRIEVE");
        intent.setData(uri);
        MxMmsTransactionService.beginStartingService(context, intent);
    }

    public static void startSendMms(Context context, Uri uri) {
        Intent intent = new Intent(context, (Class)MxMmsTransactionService.class);
        intent.setAction("com.xiaomi.mms.ACTION_SEND_MMS");
        intent.setData(uri);
        MxMmsTransactionService.beginStartingService(context, intent);
    }

    public static void startSendMms(Context context, Uri uri, boolean bl) {
        Intent intent = new Intent(context, (Class)MxMmsTransactionService.class);
        intent.putExtra("key_send_by_mx_V2", bl);
        intent.setAction("com.xiaomi.mms.ACTION_SEND_MMS");
        intent.setData(uri);
        MxMmsTransactionService.beginStartingService(context, intent);
    }

    protected String getFileTokenPref() {
        if (this.mIsSendByMxV2) {
            return "pref_mx2_file_token";
        }
        return "pref_file_token";
    }

    @Override
    public ExtendedAuthToken getMediaManagerToken(String string2) throws IOException {
        if (this.mInvalidTokenCount < 3) {
            return this.getFileToken(string2);
        }
        return null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public MiCloudRichMediaManager getMiCloudRichMediaManager(Context context, String string2, ExtendedAuthToken extendedAuthToken, String string3) {
        int n = PushSession.getInstance((Context)this).getSimIdByMid(string2);
        if (n == -1) {
            return null;
        }
        if (this.mRichMediaManager[n] == null) {
            this.mRichMediaManager[n] = new MiCloudRichMediaManager(context, string2, extendedAuthToken, string3);
            do {
                return this.mRichMediaManager[n];
                break;
            } while (true);
        }
        this.mRichMediaManager[n].updateAuthToken(extendedAuthToken);
        return this.mRichMediaManager[n];
    }

    protected String getMxSid(int n) {
        String string2 = PushSession.getInstance(this.getBaseContext()).getMyMid(n);
        if (this.mIsSendByMxV2) {
            return MxConfigCompat.getMxV2Sid(this.getBaseContext(), string2);
        }
        return MxConfigCompat.getMxV1Sid(this.getBaseContext(), string2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public MiCloudRichMediaManager getOtherIDCMiCloudRichMediaManager(Context context, String string2, ExtendedAuthToken extendedAuthToken, String string3) {
        int n = PushSession.getInstance((Context)this).getSimIdByMid(string2);
        if (n == -1) {
            return null;
        }
        if (this.mOtherIDCRichMediaManager[n] == null) {
            this.mOtherIDCRichMediaManager[n] = new MiCloudRichMediaManager(context, string2, extendedAuthToken, string3);
            do {
                return this.mOtherIDCRichMediaManager[n];
                break;
            } while (true);
        }
        this.mOtherIDCRichMediaManager[n].updateAuthToken(extendedAuthToken);
        return this.mOtherIDCRichMediaManager[n];
    }

    /*
     * Exception decompiling
     */
    @Override
    protected void handleIntent(Intent var1_1) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Extractable last case doesn't follow previous
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.examineSwitchContiguity(SwitchReplacer.java:486)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.replaceRawSwitches(SwitchReplacer.java:65)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:422)
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

    protected void invalidFileToken(String string2) {
        int n = PushSession.getInstance((Context)this).getSimIdByMid(string2);
        if (n == -1) {
            MyLog.e("MxMmsTransactionService", "refresh file token failed, because sim index == -1)");
            return;
        }
        this.mFileToken[n] = null;
        PrefUtils.remove((Context)this, this.getFileTokenPref() + n);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        int n = MSimUtils.getMultiSimCount();
        this.mFileToken = new ExtendedAuthToken[n];
        this.mRichMediaManager = new MiCloudRichMediaManager[n];
        this.mMxMmsTransactionHandler = new MiCloudMxMmsTransactionHandler((Context)this, this, this);
        this.mOtherIDCFileToken = new ExtendedAuthToken[n];
        this.mOtherIDCRichMediaManager = new MiCloudRichMediaManager[n];
    }

    @Override
    public boolean onDownloadMediaSuccess(Uri uri, String string2, byte[] arrby) {
        this.mInvalidTokenCount = 0;
        long l = ContentUris.parseId((Uri)uri);
        if (uri != null) {
            try {
                MiCloudMmsCodec.decodeMmsBody((Context)this, l, arrby, false);
                return true;
            }
            catch (Exception var2_3) {
                MxMessagePduHelper.setResponseStatus((Context)this, uri, 226);
                return false;
            }
        }
        MxMessagePduHelper.setResponseStatus((Context)this, uri, 228);
        return false;
    }

    @Override
    public void onMediaManagerTokenInvalid(String string2) {
        this.invalidFileToken(string2);
        ++this.mInvalidTokenCount;
    }

    @Override
    public boolean onUploadMediaSuccess(Uri uri, long l, String string2, String string3, String string4, String string5, String string6, long l2, long l3, String string7) {
        if (TextUtils.isEmpty((CharSequence)string3)) {
            MyLog.w("MxMmsTransactionService", "my full mid is null,push connection not established");
            MxMessagePduHelper.setResponseStatus((Context)this, uri, 195);
            return false;
        }
        return this.sendPushMessage(uri, string3, l, string2, string4, string5, string6, l2, l3);
    }

    private static class MxTransaction {
        final int mMsgBox;
        final Uri mUri;

        private MxTransaction(Uri uri, int n) {
            this.mUri = uri;
            this.mMsgBox = n;
        }

        /*
         * Enabled aggressive block sorting
         */
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null) return false;
            if (this.getClass() != object.getClass()) {
                return false;
            }
            object = (MxTransaction)object;
            if (this.mMsgBox != object.mMsgBox) {
                return false;
            }
            if (this.mUri != null) {
                if (this.mUri.equals((Object)object.mUri)) return true;
                return false;
            }
            if (object.mUri == null) return true;
            return false;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public int hashCode() {
            int n;
            if (this.mUri != null) {
                n = this.mUri.hashCode();
                do {
                    return n * 31 + this.mMsgBox;
                    break;
                } while (true);
            }
            n = 0;
            return n * 31 + this.mMsgBox;
        }
    }

}

