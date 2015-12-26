/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.net.Uri
 *  android.text.TextUtils
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  miui.push.CommonPacketExtension
 *  miui.push.Message
 *  miui.push.ServiceClient
 */
package com.xiaomi.mms.transaction;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.xiaomi.mms.transaction.MiCloudMx2MmsTransactionHandler;
import com.xiaomi.mms.transaction.MiCloudMxMmsTransactionHandler;
import com.xiaomi.mms.transaction.MiCloudRichMediaManagerFactory;
import com.xiaomi.mms.transaction.MxMmsTransactionHandler;
import com.xiaomi.mms.transaction.MxMmsTransactionService;
import com.xiaomi.mms.transaction.PushSession;
import com.xiaomi.mms.utils.MxConfigCompat;
import com.xiaomi.mms.utils.MxMessageLogicHelper;
import com.xiaomi.mms.utils.MxMessagePduHelper;
import com.xiaomi.mms.utils.logger.MyLog;
import miui.push.CommonPacketExtension;
import miui.push.Message;
import miui.push.ServiceClient;

public class Mx2MmsTransactionService
extends MxMmsTransactionService {
    /*
     * Enabled aggressive block sorting
     */
    private boolean sendPushMessage(Uri object, String string2, long l, String string3, String string4, String string5, String string6, long l2, long l3, String string7) {
        if (l <= 0) {
            l = MxMessageLogicHelper.nextMiId(true, false);
        }
        MxMessagePduHelper.moveMxMmsToOutbox((Context)this, (Uri)object, System.currentTimeMillis(), l);
        object = MxMessageLogicHelper.constructMxBody(string3, l, string5);
        string3 = MxMessageLogicHelper.constructMxAttachment(string6, string5, l2, l3, string7);
        string2 = MxMessageLogicHelper.obtainMessage(string2, string4);
        string2.setPacketID(String.valueOf((long)l));
        string4 = MxMessageLogicHelper.base64Encode((String)object);
        if (TextUtils.isEmpty((CharSequence)string4)) {
            string2.setBody((String)object);
        } else {
            string2.setBody(string4, "base64");
        }
        string2.addExtension((CommonPacketExtension)string3);
        return ServiceClient.getInstance((Context)this).sendMessage((Message)string2);
    }

    public static void startRetrieveMx2(Context context, Uri uri) {
        Intent intent = new Intent(context, (Class)Mx2MmsTransactionService.class);
        intent.setAction("com.xiaomi.mms.ACTION_RETRIEVE");
        intent.setData(uri);
        Mx2MmsTransactionService.beginStartingService(context, intent);
    }

    public static void startSendMx2(Context context, Uri uri) {
        Intent intent = new Intent(context, (Class)Mx2MmsTransactionService.class);
        intent.setAction("com.xiaomi.mms.ACTION_SEND_MMS");
        intent.setData(uri);
        Mx2MmsTransactionService.beginStartingService(context, intent);
    }

    @Override
    protected String getFileTokenPref() {
        return "pref_mx2_file_token";
    }

    @Override
    protected String getMxSid(int n) {
        String string2 = PushSession.getInstance(this.getBaseContext()).getMyMid(n);
        return MxConfigCompat.getMxV2Sid(this.getBaseContext(), string2);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.mMxMmsTransactionHandler = new MiCloudMx2MmsTransactionHandler((Context)this, this, this);
    }

    @Override
    public boolean onDownloadMediaSuccess(Uri uri, String string2, byte[] arrby) {
        throw new UnsupportedOperationException("UnsupportedOperationException : Mx2Message should not go here");
    }

    @Override
    public boolean onUploadMediaSuccess(Uri uri, long l, String string2, String string3, String string4, String string5, String string6, long l2, long l3, String string7) {
        if (string3 == null) {
            MyLog.w("Mx2MmsTransactionService.RICH", "my full mid is null,push connection not established");
            MxMessagePduHelper.setResponseStatus((Context)this, uri, 195);
            return false;
        }
        return this.sendPushMessage(uri, string3, l, string2, string4, string5, string6, l2, l3, string7);
    }
}

