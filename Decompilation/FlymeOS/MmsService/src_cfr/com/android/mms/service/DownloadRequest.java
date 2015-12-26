/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.ActivityManagerNative
 *  android.app.PendingIntent
 *  android.content.BroadcastReceiver
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.Intent
 *  android.content.pm.UserInfo
 *  android.database.sqlite.SQLiteException
 *  android.net.Uri
 *  android.os.Binder
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.RemoteException
 *  android.os.UserHandle
 *  android.os.UserManager
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.provider.Telephony$Mms$Inbox
 *  android.service.carrier.ICarrierMessagingCallback
 *  android.service.carrier.ICarrierMessagingService
 *  android.telephony.CarrierMessagingServiceManager
 *  android.text.TextUtils
 *  android.util.Log
 *  com.google.android.mms.MmsException
 *  com.meizu.android.mms.pdu.MzGenericPdu
 *  com.meizu.android.mms.pdu.MzPduParser
 *  com.meizu.android.mms.pdu.MzPduPersister
 *  com.meizu.android.mms.pdu.MzRetrieveConf
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

import android.app.ActivityManagerNative;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.UserInfo;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
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
import com.meizu.android.mms.pdu.MzRetrieveConf;
import com.meizu.android.mms.util.MzSqliteWrapper;
import com.meizu.mms.utils.MzMmsServiceUtils;
import java.util.HashMap;

public class DownloadRequest
extends MmsRequest {
    private final Uri mContentUri;
    private final PendingIntent mDownloadedIntent;
    private final String mLocationUrl;

    public DownloadRequest(MmsRequest.RequestManager requestManager, int n, String string, Uri uri, PendingIntent pendingIntent, String string2, Bundle bundle) {
        super(requestManager, null, n, string2, bundle);
        this.mLocationUrl = string;
        this.mDownloadedIntent = pendingIntent;
        this.mContentUri = uri;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void notifyOfDownload(Context context) {
        Object object;
        Intent intent = new Intent("android.provider.Telephony.MMS_DOWNLOADED");
        intent.addFlags(134217728);
        Object object2 = null;
        try {
            object = ActivityManagerNative.getDefault().getRunningUserIds();
        }
        catch (RemoteException var4_5) {}
        object2 = object;
        object = object2;
        if (object2 == null) {
            object = new int[1];
            object[0] = UserHandle.ALL.getIdentifier();
        }
        object2 = (UserManager)context.getSystemService("user");
        int n = object.length - 1;
        while (n >= 0) {
            UserInfo userInfo;
            UserHandle userHandle = new UserHandle((int)object[n]);
            if (object[n] == false || !object2.hasUserRestriction("no_sms", userHandle) && (userInfo = object2.getUserInfo((int)object[n])) != null && !userInfo.isManagedProfile()) {
                context.sendOrderedBroadcastAsUser(intent, userHandle, "android.permission.RECEIVE_MMS", 18, null, null, -1, null, null);
            }
            --n;
        }
        return;
    }

    @Override
    protected int checkResponse(byte[] arrby) {
        return -1;
    }

    @Override
    protected byte[] doHttp(Context object, MmsNetworkManager mmsNetworkManager, ApnSettings apnSettings) throws MmsHttpException {
        object = mmsNetworkManager.getOrCreateHttpClient();
        if (object == null) {
            Log.e((String)"MmsService", (String)"MMS network is not ready!");
            throw new MmsHttpException(0, "MMS network is not ready");
        }
        return object.execute(this.mLocationUrl, null, "GET", apnSettings.isProxySet(), apnSettings.getProxyAddress(), apnSettings.getProxyPort(), this.mMmsConfig);
    }

    @Override
    protected PendingIntent getPendingIntent() {
        return this.mDownloadedIntent;
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    @Override
    protected Uri persistIfRequired(Context context, int n, byte[] object) {
        this.notifyOfDownload(context);
        if (!this.mRequestManager.getAutoPersistingPref() && !"com.android.mms".equals((Object)this.mCreator)) {
            return null;
        }
        Log.d((String)"MmsService", (String)"DownloadRequest.persistIfRequired");
        if (object == null || object.length < 1) {
            Log.e((String)"MmsService", (String)"DownloadRequest.persistIfRequired: empty response");
            return null;
        }
        long l = Binder.clearCallingIdentity();
        try {
            object = new MzPduParser((byte[])object).parse();
            if (object == null || !(object instanceof MzRetrieveConf)) {
                Log.e((String)"MmsService", (String)"DownloadRequest.persistIfRequired: invalid parsed PDU");
                return null;
            }
            MzSqliteWrapper.delete((Context)context, (ContentResolver)context.getContentResolver(), (Uri)Telephony.Mms.CONTENT_URI, (String)"m_type=? AND ct_l =?", (String[])new String[]{Integer.toString((int)130), this.mLocationUrl});
            object = MzPduPersister.getPduPersister((Context)context).persist((MzGenericPdu)object, Telephony.Mms.Inbox.CONTENT_URI, true, true, null);
            if (object == null) {
                Log.e((String)"MmsService", (String)"DownloadRequest.persistIfRequired: can not persist message");
                return null;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("date", Long.valueOf((long)(System.currentTimeMillis() / 1000)));
            contentValues.put("read", Integer.valueOf((int)0));
            contentValues.put("seen", Integer.valueOf((int)0));
            if (!TextUtils.isEmpty((CharSequence)this.mCreator)) {
                contentValues.put("creator", this.mCreator);
            }
            contentValues.put("sub_id", Integer.valueOf((int)this.mSubId));
            contentValues.put("ct_l", this.mLocationUrl);
            contentValues.put("association_id", new Long(System.currentTimeMillis()));
            contentValues.put("imsi", MzMmsServiceUtils.getSubscriberIdBySubId(this.mSubId));
            contentValues.put("sim_id", Integer.valueOf((int)MzMmsServiceUtils.getSlotIdBySubId(this.mSubId)));
            if (MzSqliteWrapper.update((Context)context, (ContentResolver)context.getContentResolver(), (Uri)object, (ContentValues)contentValues, (String)null, (String[])null) == 1) return object;
            Log.e((String)"MmsService", (String)"DownloadRequest.persistIfRequired: can not update message");
            return object;
        }
        catch (MmsException var1_2) {
            Log.e((String)"MmsService", (String)"DownloadRequest.persistIfRequired: can not persist message", (Throwable)var1_2);
            return null;
        }
        catch (SQLiteException var1_3) {
            Log.e((String)"MmsService", (String)"DownloadRequest.persistIfRequired: can not update message", (Throwable)var1_3);
            return null;
            {
                catch (Throwable throwable) {
                    throw throwable;
                }
            }
            catch (RuntimeException runtimeException) {
                Log.e((String)"MmsService", (String)"DownloadRequest.persistIfRequired: can not parse response", (Throwable)runtimeException);
                return null;
            }
        }
        finally {
            Binder.restoreCallingIdentity((long)l);
            return null;
        }
    }

    @Override
    protected boolean prepareForHttpRequest() {
        return true;
    }

    @Override
    protected void putOriginalUrl(Intent intent) {
        if (this.mLocationUrl != null) {
            intent.putExtra("oriuri", this.mLocationUrl.toString());
            return;
        }
        intent.putExtra("oriuri", "");
    }

    @Override
    protected void revokeUriPermission(Context context) {
        if (this.mContentUri != null) {
            context.revokeUriPermission(this.mContentUri, 2);
        }
    }

    @Override
    protected boolean transferResponse(Intent intent, byte[] arrby) {
        return this.mRequestManager.writePduToContentUri(this.mContentUri, arrby);
    }

    public void tryDownloadingByCarrierApp(Context context, String string) {
        CarrierDownloadManager carrierDownloadManager = new CarrierDownloadManager();
        carrierDownloadManager.downloadMms(context, string, new CarrierDownloadCompleteCallback(context, carrierDownloadManager));
    }

    private final class CarrierDownloadCompleteCallback
    extends MmsRequest.CarrierMmsActionCallback {
        private final CarrierDownloadManager mCarrierDownloadManager;
        private final Context mContext;

        public CarrierDownloadCompleteCallback(Context context, CarrierDownloadManager carrierDownloadManager) {
            this.mContext = context;
            this.mCarrierDownloadManager = carrierDownloadManager;
        }

        public void onDownloadMmsComplete(int n) {
            Log.d((String)"MmsService", (String)("Carrier app result for download: " + n));
            this.mCarrierDownloadManager.disposeConnection(this.mContext);
            if (!DownloadRequest.this.maybeFallbackToRegularDelivery(n)) {
                DownloadRequest.this.processResult(this.mContext, MmsRequest.toSmsManagerResult(n), null, 0);
            }
        }

        public void onSendMmsComplete(int n, byte[] arrby) {
            Log.e((String)"MmsService", (String)("Unexpected onSendMmsComplete call with result: " + n));
        }
    }

    private final class CarrierDownloadManager
    extends CarrierMessagingServiceManager {
        private volatile CarrierDownloadCompleteCallback mCarrierDownloadCallback;

        private CarrierDownloadManager() {
        }

        void downloadMms(Context context, String string, CarrierDownloadCompleteCallback carrierDownloadCompleteCallback) {
            this.mCarrierDownloadCallback = carrierDownloadCompleteCallback;
            if (this.bindToCarrierMessagingService(context, string)) {
                Log.v((String)"MmsService", (String)"bindService() for carrier messaging service succeeded");
                return;
            }
            Log.e((String)"MmsService", (String)"bindService() for carrier messaging service failed");
            carrierDownloadCompleteCallback.onDownloadMmsComplete(1);
        }

        protected void onServiceReady(ICarrierMessagingService iCarrierMessagingService) {
            try {
                iCarrierMessagingService.downloadMms(DownloadRequest.this.mContentUri, DownloadRequest.this.mSubId, Uri.parse((String)DownloadRequest.this.mLocationUrl), (ICarrierMessagingCallback)this.mCarrierDownloadCallback);
                return;
            }
            catch (RemoteException var1_2) {
                Log.e((String)"MmsService", (String)("Exception downloading MMS using the carrier messaging service: " + (Object)var1_2));
                this.mCarrierDownloadCallback.onDownloadMmsComplete(1);
                return;
            }
        }
    }

}

