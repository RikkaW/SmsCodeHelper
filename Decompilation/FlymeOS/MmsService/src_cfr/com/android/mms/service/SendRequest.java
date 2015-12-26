/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.PendingIntent
 *  android.content.ContentResolver
 *  android.content.ContentUris
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.Intent
 *  android.database.sqlite.SQLiteException
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.os.Binder
 *  android.os.Bundle
 *  android.os.RemoteException
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.provider.Telephony$Mms$Outbox
 *  android.provider.Telephony$MmsSms
 *  android.provider.Telephony$MmsSms$PendingMessages
 *  android.service.carrier.ICarrierMessagingCallback
 *  android.service.carrier.ICarrierMessagingService
 *  android.telephony.CarrierMessagingServiceManager
 *  android.text.TextUtils
 *  android.util.Log
 *  com.google.android.mms.MmsException
 *  com.meizu.android.mms.pdu.MzGenericPdu
 *  com.meizu.android.mms.pdu.MzPduParser
 *  com.meizu.android.mms.pdu.MzPduPersister
 *  com.meizu.android.mms.pdu.MzSendConf
 *  com.meizu.android.mms.pdu.MzSendReq
 *  com.meizu.android.mms.util.MzSqliteWrapper
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.HashMap
 */
package com.android.mms.service;

import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.Telephony;
import android.service.carrier.ICarrierMessagingCallback;
import android.service.carrier.ICarrierMessagingService;
import android.telephony.CarrierMessagingServiceManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.service.ApnSettings;
import com.android.mms.service.MmsConfig;
import com.android.mms.service.MmsHttpClient;
import com.android.mms.service.MmsNetworkManager;
import com.android.mms.service.MmsRequest;
import com.android.mms.service.exception.MmsHttpException;
import com.google.android.mms.MmsException;
import com.meizu.android.mms.pdu.MzGenericPdu;
import com.meizu.android.mms.pdu.MzPduParser;
import com.meizu.android.mms.pdu.MzPduPersister;
import com.meizu.android.mms.pdu.MzSendConf;
import com.meizu.android.mms.pdu.MzSendReq;
import com.meizu.android.mms.util.MzSqliteWrapper;
import com.meizu.mms.utils.MzMmsServiceUtils;
import java.util.HashMap;

public class SendRequest
extends MmsRequest {
    private final String mLocationUrl;
    private byte[] mPduData;
    private final Uri mPduUri;
    private final PendingIntent mSentIntent;

    public SendRequest(MmsRequest.RequestManager requestManager, int n, Uri uri, Uri uri2, String string, PendingIntent pendingIntent, String string2, Bundle bundle) {
        super(requestManager, uri2, n, string2, bundle);
        this.mPduUri = uri;
        this.mPduData = null;
        this.mLocationUrl = string;
        this.mSentIntent = pendingIntent;
    }

    public SendRequest(MmsRequest.RequestManager requestManager, int n, byte[] arrby, Uri uri, String string, PendingIntent pendingIntent, String string2, Bundle bundle) {
        super(requestManager, uri, n, string2, bundle);
        this.mPduUri = null;
        this.mPduData = arrby;
        this.mLocationUrl = string;
        this.mSentIntent = pendingIntent;
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean readPduFromContentUri() {
        StringBuilder stringBuilder = new StringBuilder().append("readPduFromContentUri");
        String string = this.mPduUri == null ? null : this.mPduUri.toString();
        Log.d((String)"MmsService", (String)stringBuilder.append(string).toString());
        if (this.mPduData != null) {
            return true;
        }
        int n = this.mMmsConfig.getMaxMessageSize();
        this.mPduData = this.mRequestManager.readPduFromContentUri(this.mPduUri, n);
        if (this.mPduData == null) return false;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    protected int checkResponse(byte[] object) {
        int n = 1;
        if (object == null || object.length == 0) {
            Log.d((String)"MmsService", (String)"checkResponse get response is null, maybe is not a normal sending");
            return -1;
        }
        Log.d((String)"MmsService", (String)("SendConf length = " + object.length));
        object = (MzSendConf)new MzPduParser((byte[])object).parse();
        if (object == null) {
            Log.e((String)"MmsService", (String)"No M-Send.conf received.");
            return 1;
        }
        int n2 = object.getResponseStatus();
        Log.d((String)"MmsService", (String)("checkResponse get response  = " + n2));
        if (n2 != 128) return n;
        return -1;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    protected byte[] doHttp(Context object, MmsNetworkManager object2, ApnSettings apnSettings) throws MmsHttpException {
        if ((object2 = object2.getOrCreateHttpClient()) == null) {
            Log.e((String)"MmsService", (String)"MMS network is not ready!");
            throw new MmsHttpException(0, "MMS network is not ready");
        }
        if (this.mLocationUrl != null) {
            object = this.mLocationUrl;
            do {
                return object2.execute((String)object, this.mPduData, "POST", apnSettings.isProxySet(), apnSettings.getProxyAddress(), apnSettings.getProxyPort(), this.mMmsConfig);
                break;
            } while (true);
        }
        object = apnSettings.getMmscUrl();
        return object2.execute((String)object, this.mPduData, "POST", apnSettings.isProxySet(), apnSettings.getProxyAddress(), apnSettings.getProxyPort(), this.mMmsConfig);
    }

    @Override
    protected PendingIntent getPendingIntent() {
        return this.mSentIntent;
    }

    public int getRetryIndex(Context context, Uri uri) {
        int n;
        block5 : {
            int n2;
            long l = ContentUris.parseId((Uri)uri);
            uri = Telephony.MmsSms.PendingMessages.CONTENT_URI.buildUpon();
            uri.appendQueryParameter("protocol", "mms");
            uri.appendQueryParameter("message", String.valueOf((long)l));
            context = MzSqliteWrapper.query((Context)context, (ContentResolver)context.getContentResolver(), (Uri)uri.build(), (String[])null, (String)null, (String[])null, (String)null);
            n = n2 = -1;
            if (context != null) {
                n = n2;
                if (context.getCount() != 1) break block5;
                n = n2;
                if (!context.moveToFirst()) break block5;
                n = context.getInt(context.getColumnIndexOrThrow("retry_index"));
                n2 = context.getInt(context.getColumnIndexOrThrow("err_type"));
                Log.d((String)"MmsService", (String)("queryPendingMessageRetryIndex, retryIndex = " + n + ", errorType = " + n2));
            }
        }
        return n;
        finally {
            context.close();
        }
    }

    public void markPendingMessageErrorType(Context context, Uri uri) {
        ContentValues contentValues = new ContentValues(2);
        long l = ContentUris.parseId((Uri)uri);
        contentValues.put("err_type", Integer.valueOf((int)19));
        contentValues.put("retry_index", Integer.valueOf((int)4));
        MzSqliteWrapper.update((Context)context, (ContentResolver)context.getContentResolver(), (Uri)Telephony.MmsSms.PendingMessages.CONTENT_URI, (ContentValues)contentValues, (String)("msg_id=" + l), (String[])null);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    @Override
    protected Uri persistIfRequired(Context var1_1, int var2_5, byte[] var3_6) {
        if (this.mMessageUri == null) {
            return null;
        }
        var5_7 = Binder.clearCallingIdentity();
        var7_9 = var8_8 = null;
        if (var3_6 == null) ** GOTO lbl15
        var7_9 = var8_8;
        try {
            if (var3_6.length > 0) {
                var3_6 = new MzPduParser((byte[])var3_6).parse();
                var7_9 = var8_8;
                if (var3_6 != null) {
                    var7_9 = var8_8;
                    if (var3_6 instanceof MzSendConf) {
                        var7_9 = (MzSendConf)var3_6;
                    }
                }
            }
lbl15: // 8 sources:
            var3_6 = new ContentValues();
            var4_10 = this.getRetryIndex(var1_1, this.mMessageUri);
            if (var7_9 != null) ** GOTO lbl25
            if (var4_10 >= 4) ** GOTO lbl21
            var3_6.put("resp_st", Integer.valueOf((int)134));
            ** GOTO lbl38
lbl21: // 1 sources:
            try {
                var3_6.put("resp_st", Integer.valueOf((int)9527));
                var3_6.put("msg_box", Integer.valueOf((int)5));
            }
            catch (SQLiteException var1_2) {
                Log.e((String)"MmsService", (String)"SendRequest.persistIfRequired: can not update message", (Throwable)var1_2);
                return null;
            }
lbl25: // 1 sources:
            if (var2_5 == -1) {
                var3_6.put("msg_box", Integer.valueOf((int)2));
            }
            var3_6.put("resp_st", Integer.valueOf((int)var7_9.getResponseStatus()));
            if (MzMmsServiceUtils.checkIsFailedRespStatus(var7_9.getResponseStatus())) {
                this.markPendingMessageErrorType(var1_1, this.mMessageUri);
                Log.e((String)"MmsService", (String)("SendRequest.persistIfRequired: Server returned an error code: " + var7_9.getResponseStatus()));
            }
            var3_6.put("m_id", MzPduPersister.toIsoString((byte[])var7_9.getMessageId()));
lbl38: // 3 sources:
            Log.d((String)"MmsService", (String)("MzMmsServiceUtils.isCTVersion() = " + MzMmsServiceUtils.isCTVersion() + ", result = " + var2_5 + ", retryIndex = " + var4_10));
            if (MzMmsServiceUtils.isCTVersion() && var2_5 != -1) {
                var3_6.put("resp_st", Integer.valueOf((int)9527));
                this.markPendingMessageErrorType(var1_1, this.mMessageUri);
            }
            MzSqliteWrapper.update((Context)var1_1, (ContentResolver)var1_1.getContentResolver(), (Uri)this.mMessageUri, (ContentValues)var3_6, (String)null, (String[])null);
            var1_1 = this.mMessageUri;
            Binder.restoreCallingIdentity((long)var5_7);
            return var1_1;
            catch (RuntimeException var1_3) {
                Log.e((String)"MmsService", (String)"SendRequest.persistIfRequired: can not parse response", (Throwable)var1_3);
                return null;
            }
        }
        finally {
            Binder.restoreCallingIdentity((long)var5_7);
        }
    }

    @Override
    protected boolean prepareForHttpRequest() {
        return this.readPduFromContentUri();
    }

    @Override
    protected void putOriginalUrl(Intent intent) {
        if (this.mMessageUri != null) {
            intent.putExtra("oriuri", this.mMessageUri.toString());
            return;
        }
        intent.putExtra("oriuri", "");
    }

    @Override
    protected void revokeUriPermission(Context context) {
        if (this.mPduUri != null) {
            context.revokeUriPermission(this.mPduUri, 1);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void storeInOutbox(Context context) {
        long l = Binder.clearCallingIdentity();
        try {
            if (!this.readPduFromContentUri()) {
                Log.e((String)"MmsService", (String)"SendRequest.storeInOutbox: empty PDU");
                return;
            }
            if (this.mMessageUri == null) {
                MzGenericPdu mzGenericPdu = new MzPduParser(this.mPduData).parse();
                if (mzGenericPdu == null) {
                    Log.e((String)"MmsService", (String)"SendRequest.storeInOutbox: can't parse input PDU");
                    return;
                }
                if (!(mzGenericPdu instanceof MzSendReq)) {
                    Log.d((String)"MmsService", (String)"SendRequest.storeInOutbox: not SendReq");
                    return;
                }
                this.mMessageUri = MzPduPersister.getPduPersister((Context)context).persist(mzGenericPdu, Telephony.Mms.Outbox.CONTENT_URI, true, true, null);
                if (this.mMessageUri == null) {
                    Log.e((String)"MmsService", (String)"SendRequest.storeInOutbox: can not persist message");
                    return;
                }
                mzGenericPdu = new ContentValues(5);
                mzGenericPdu.put("date", Long.valueOf((long)(System.currentTimeMillis() / 1000)));
                mzGenericPdu.put("read", Integer.valueOf((int)1));
                mzGenericPdu.put("seen", Integer.valueOf((int)1));
                if (!TextUtils.isEmpty((CharSequence)this.mCreator)) {
                    mzGenericPdu.put("creator", this.mCreator);
                }
                mzGenericPdu.put("sub_id", Integer.valueOf((int)this.mSubId));
                if (MzSqliteWrapper.update((Context)context, (ContentResolver)context.getContentResolver(), (Uri)this.mMessageUri, (ContentValues)mzGenericPdu, (String)null, (String[])null) == 1) return;
                Log.e((String)"MmsService", (String)"SendRequest.storeInOutbox: failed to update message");
                return;
            }
            ContentValues contentValues = new ContentValues(3);
            contentValues.put("date", Long.valueOf((long)(System.currentTimeMillis() / 1000)));
            contentValues.put("msg_box", Integer.valueOf((int)4));
            contentValues.put("sub_id", Integer.valueOf((int)this.mSubId));
            if (MzSqliteWrapper.update((Context)context, (ContentResolver)context.getContentResolver(), (Uri)this.mMessageUri, (ContentValues)contentValues, (String)null, (String[])null) == 1) return;
            Log.e((String)"MmsService", (String)"SendRequest.storeInOutbox: failed to update message");
            return;
        }
        catch (MmsException var1_2) {
            Log.e((String)"MmsService", (String)"SendRequest.storeInOutbox: can not persist/update message", (Throwable)var1_2);
            return;
        }
        catch (RuntimeException var1_3) {
            Log.e((String)"MmsService", (String)"SendRequest.storeInOutbox: unexpected parsing failure", (Throwable)var1_3);
            return;
        }
        finally {
            Binder.restoreCallingIdentity((long)l);
        }
    }

    @Override
    protected boolean transferResponse(Intent intent, byte[] arrby) {
        if (arrby != null) {
            intent.putExtra("android.telephony.extra.MMS_DATA", arrby);
        }
        return true;
    }

    public void trySendingByCarrierApp(Context context, String string) {
        CarrierSendManager carrierSendManager = new CarrierSendManager();
        carrierSendManager.sendMms(context, string, new CarrierSendCompleteCallback(context, carrierSendManager));
    }

    private final class CarrierSendCompleteCallback
    extends MmsRequest.CarrierMmsActionCallback {
        private final CarrierSendManager mCarrierSendManager;
        private final Context mContext;

        public CarrierSendCompleteCallback(Context context, CarrierSendManager carrierSendManager) {
            this.mContext = context;
            this.mCarrierSendManager = carrierSendManager;
        }

        public void onDownloadMmsComplete(int n) {
            Log.e((String)"MmsService", (String)("Unexpected onDownloadMmsComplete call with result: " + n));
        }

        public void onSendMmsComplete(int n, byte[] arrby) {
            Log.d((String)"MmsService", (String)("Carrier app result for send: " + n));
            this.mCarrierSendManager.disposeConnection(this.mContext);
            if (!SendRequest.this.maybeFallbackToRegularDelivery(n)) {
                SendRequest.this.processResult(this.mContext, MmsRequest.toSmsManagerResult(n), arrby, 0);
            }
        }
    }

    private final class CarrierSendManager
    extends CarrierMessagingServiceManager {
        private volatile CarrierSendCompleteCallback mCarrierSendCompleteCallback;

        private CarrierSendManager() {
        }

        protected void onServiceReady(ICarrierMessagingService iCarrierMessagingService) {
            Uri uri = null;
            try {
                if (SendRequest.this.mLocationUrl != null) {
                    uri = Uri.parse((String)SendRequest.this.mLocationUrl);
                }
                iCarrierMessagingService.sendMms(SendRequest.this.mPduUri, SendRequest.this.mSubId, uri, (ICarrierMessagingCallback)this.mCarrierSendCompleteCallback);
                return;
            }
            catch (RemoteException var1_2) {
                Log.e((String)"MmsService", (String)("Exception sending MMS using the carrier messaging service: " + (Object)var1_2));
                this.mCarrierSendCompleteCallback.onSendMmsComplete(1, null);
                return;
            }
        }

        void sendMms(Context context, String string, CarrierSendCompleteCallback carrierSendCompleteCallback) {
            this.mCarrierSendCompleteCallback = carrierSendCompleteCallback;
            if (this.bindToCarrierMessagingService(context, string)) {
                Log.v((String)"MmsService", (String)"bindService() for carrier messaging service succeeded");
                return;
            }
            Log.e((String)"MmsService", (String)"bindService() for carrier messaging service failed");
            carrierSendCompleteCallback.onSendMmsComplete(1, null);
        }
    }

}

