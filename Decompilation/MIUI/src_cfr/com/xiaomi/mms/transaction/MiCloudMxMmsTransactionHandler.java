/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.ContentUris
 *  android.content.Context
 *  android.database.Cursor
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.os.Bundle
 *  android.text.TextUtils
 *  com.google.android.collect.Lists
 *  com.google.android.mms.MmsException
 *  com.google.android.mms.pdu.EncodedStringValue
 *  com.google.android.mms.pdu.GenericPdu
 *  com.google.android.mms.pdu.MiuiPduPersister
 *  com.google.android.mms.pdu.SendReq
 *  com.google.android.mms.util.SqliteWrapper
 *  com.xiaomi.accountsdk.account.data.ExtendedAuthToken
 *  com.xiaomi.accountsdk.activate.ActivateManager
 *  com.xiaomi.accountsdk.activate.ActivateManager$ActivateManagerFuture
 *  com.xiaomi.micloudsdk.micloudrichmedia.MiCloudRichMediaManager
 *  com.xiaomi.micloudsdk.micloudrichmedia.UploadData
 *  com.xiaomi.micloudsdk.micloudrichmedia.UploadEntity
 *  com.xiaomi.micloudsdk.micloudrichmedia.UploadResult
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 */
package com.xiaomi.mms.transaction;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.android.mms.data.Contact;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.util.DownloadManager;
import com.android.mms.util.MSimUtils;
import com.google.android.collect.Lists;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.GenericPdu;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.SendReq;
import com.google.android.mms.util.SqliteWrapper;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.accountsdk.activate.ActivateManager;
import com.xiaomi.micloudsdk.micloudrichmedia.MiCloudRichMediaManager;
import com.xiaomi.micloudsdk.micloudrichmedia.UploadData;
import com.xiaomi.micloudsdk.micloudrichmedia.UploadEntity;
import com.xiaomi.micloudsdk.micloudrichmedia.UploadResult;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.net.exception.InvalidTokenException;
import com.xiaomi.mms.net.exception.MxLogicException;
import com.xiaomi.mms.net.exception.MxV2DownloadException;
import com.xiaomi.mms.net.exception.ServerErrorException;
import com.xiaomi.mms.transaction.MiCloudRichMediaManagerFactory;
import com.xiaomi.mms.transaction.MxActivateService;
import com.xiaomi.mms.transaction.MxMessageService;
import com.xiaomi.mms.transaction.MxMessageTrackService;
import com.xiaomi.mms.transaction.MxMmsTransactionHandler;
import com.xiaomi.mms.transaction.PushSession;
import com.xiaomi.mms.utils.MiCloudMmsCodec;
import com.xiaomi.mms.utils.MxConfigCompat;
import com.xiaomi.mms.utils.MxMessagePduHelper;
import com.xiaomi.mms.utils.MxMessageUtils;
import com.xiaomi.mms.utils.MxRequestEnv;
import com.xiaomi.mms.utils.PrefUtils;
import com.xiaomi.mms.utils.StorageServerHelper;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.IOException;
import java.util.ArrayList;

public class MiCloudMxMmsTransactionHandler
implements MxMmsTransactionHandler {
    private static final String[] MX_MMS_PROJECT = new String[]{"ct_t", "m_size", "mx_id", "mx_status", "sim_id"};
    private MiCloudMediaManagerCallback mCallback;
    private Context mContext;
    private MiCloudRichMediaManagerFactory mFactory;
    private boolean mIsSendByMxV2;

    public MiCloudMxMmsTransactionHandler(Context context, MiCloudMediaManagerCallback miCloudMediaManagerCallback, MiCloudRichMediaManagerFactory miCloudRichMediaManagerFactory) {
        this.mContext = context;
        this.mCallback = miCloudMediaManagerCallback;
        this.mFactory = miCloudRichMediaManagerFactory;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String getAddr(Uri uri, Context context) {
        MiuiPduPersister miuiPduPersister = MiuiPduPersister.getPduPersister((Context)context);
        context = null;
        try {
            uri = (SendReq)miuiPduPersister.load(uri);
        }
        catch (MmsException var0_1) {
            uri = context;
        }
        if (uri == null) {
            return null;
        }
        return Contact.get(uri.getTo()[0].getString()).getMxPhoneNumber();
    }

    /*
     * Enabled aggressive block sorting
     */
    private ExtendedAuthToken getFileToken(String string2, String string3) {
        ExtendedAuthToken extendedAuthToken;
        if (TextUtils.isEmpty((CharSequence)string2)) return null;
        if (TextUtils.isEmpty((CharSequence)string3)) {
            return null;
        }
        int n = PushSession.getInstance(this.mContext).getSimIdByMid(string2);
        boolean bl = MxConfigCompat.isMxV2Richmedia(string3);
        string3 = bl ? "pref_mx2_file_token" : "pref_file_token";
        string3 = extendedAuthToken = ExtendedAuthToken.parse((String)PrefUtils.getString(this.mContext, string3 + n));
        if (extendedAuthToken != null) return string3;
        if (bl) {
            string3 = MxConfigCompat.getMxV2Sid(this.mContext, string2);
            return this.getFileTokenFromServer(string2, string3);
        }
        string3 = MxConfigCompat.getMxV1Sid(this.mContext, string2);
        return this.getFileTokenFromServer(string2, string3);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private ExtendedAuthToken getFileTokenFromServer(String string2, String string3) {
        int n = PushSession.getInstance(this.mContext).getSimIdByMid(string2);
        if (n == -1) return null;
        if (TextUtils.isEmpty((CharSequence)string2)) {
            return null;
        }
        String string4 = null;
        String string5 = null;
        string2 = string4;
        try {
            Bundle bundle = (Bundle)ActivateManager.get((Context)this.mContext).getSimAuthToken(n, string3).getResult();
            string2 = string4;
            string2 = string3 = bundle.getString("user_token");
            string2 = string4 = bundle.getString("user_security");
            string5 = string3;
            string3 = string2;
        }
        catch (Exception var2_3) {
            MyLog.e("MiCloudMxMmsTransactionHandler", "failed to get file upload token from server", var2_3);
            string3 = string5;
            string5 = string2;
        }
        if (string5 == null) return null;
        if (string3 == null) return null;
        return ExtendedAuthToken.build((String)string5, (String)string3);
    }

    private String getFileTokenPref(String string2) {
        if (MxConfigCompat.isMxV2Richmedia(string2)) {
            return "pref_mx2_file_token";
        }
        return "pref_file_token";
    }

    /*
     * Enabled aggressive block sorting
     */
    private ExtendedAuthToken getOtherIDCFileToken(String string2, String string3) {
        ExtendedAuthToken extendedAuthToken;
        if (TextUtils.isEmpty((CharSequence)string2)) return null;
        if (TextUtils.isEmpty((CharSequence)string3)) {
            return null;
        }
        int n = PushSession.getInstance(this.mContext).getSimIdByMid(string2);
        boolean bl = MxConfigCompat.isMxV2Richmedia(string3);
        string3 = bl ? "pref_mx2_file_token" : "pref_file_token";
        string3 = extendedAuthToken = ExtendedAuthToken.parse((String)PrefUtils.getString(this.mContext, string3 + n + "_idc"));
        if (extendedAuthToken != null) return string3;
        if (bl) {
            string3 = MxConfigCompat.getOtherIDCMxV2Sid(this.mContext, string2);
            return this.getFileTokenFromServer(string2, string3);
        }
        string3 = MxConfigCompat.getOtherIDCMxV1Sid(this.mContext, string2);
        return this.getFileTokenFromServer(string2, string3);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private byte[] tryDownload(Uri uri, String string2, String string3) {
        Object var10_4 = null;
        int n = 3;
        do {
            void var11_25;
            void var10_5;
            boolean bl = false;
            boolean bl2 = false;
            Object var13_56 = null;
            boolean bl3 = false;
            ExtendedAuthToken extendedAuthToken = null;
            Object var11_26 = null;
            ExtendedAuthToken extendedAuthToken2 = extendedAuthToken;
            try {
                void var11_28;
                if (MxConfigCompat.isMxV2Richmedia(string3)) {
                    extendedAuthToken2 = extendedAuthToken;
                    String string4 = MxConfigCompat.getMxV2RichMediaUrl(this.mContext, string2);
                }
                extendedAuthToken2 = var11_28;
                extendedAuthToken2 = extendedAuthToken = this.getFileToken(string2, string3);
            }
            catch (Exception var11_31) {
                MyLog.e("MiCloudMxMmsTransactionHandler", "io error when getting file token", var11_31);
                bl3 = true;
                ExtendedAuthToken extendedAuthToken3 = extendedAuthToken2;
                extendedAuthToken2 = var13_56;
            }
            if (bl3) {
                MxMessagePduHelper.setResponseStatus(this.mContext, uri, 195);
                bl = bl2;
                void var11_30 = var10_5;
            } else if (extendedAuthToken2 == null) {
                MyLog.w("MiCloudMxMmsTransactionHandler", "failed to get token, cannot retrieve mms");
                MxMessagePduHelper.setResponseStatus(this.mContext, uri, 225);
                void var11_33 = var10_5;
                bl = bl2;
            } else {
                void var10_9;
                boolean bl4;
                void var11_29;
                boolean bl5 = false;
                MiCloudRichMediaManager miCloudRichMediaManager = this.mFactory.getMiCloudRichMediaManager(this.mContext, string2, extendedAuthToken2, (String)var11_29);
                extendedAuthToken2 = var10_5;
                var13_56 = var10_5;
                extendedAuthToken = var10_5;
                void var15_58 = var10_5;
                try {
                    void var11_36;
                    if (MxConfigCompat.isMxV2Richmedia(string3)) {
                        extendedAuthToken2 = var10_5;
                        var13_56 = var10_5;
                        extendedAuthToken = var10_5;
                        var15_58 = var10_5;
                        byte[] arrby = StorageServerHelper.downloadPduViaMxV2(this.mContext, miCloudRichMediaManager, string3);
                    } else {
                        extendedAuthToken2 = var10_5;
                        var13_56 = var10_5;
                        extendedAuthToken = var10_5;
                        var15_58 = var10_5;
                        byte[] arrby = StorageServerHelper.downloadPdu(miCloudRichMediaManager, string3);
                    }
                    void var10_7 = var11_36;
                    bl4 = bl;
                    bl2 = bl5;
                    if (var11_36 == null) {
                        extendedAuthToken2 = var11_36;
                        var13_56 = var11_36;
                        extendedAuthToken = var11_36;
                        var15_58 = var11_36;
                        MyLog.w("MiCloudMxMmsTransactionHandler", "download error because the message is expired");
                        extendedAuthToken2 = var11_36;
                        var13_56 = var11_36;
                        extendedAuthToken = var11_36;
                        var15_58 = var11_36;
                        byte[] arrby = new byte[]{};
                        bl2 = bl5;
                        bl4 = bl;
                    }
                }
                catch (IOException var10_10) {
                    bl2 = true;
                    MyLog.e("MiCloudMxMmsTransactionHandler", "io error when downloading pdu", var10_10);
                    MxMessagePduHelper.setResponseStatus(this.mContext, uri, 195);
                    ExtendedAuthToken extendedAuthToken4 = extendedAuthToken2;
                    bl4 = bl;
                }
                catch (ServerErrorException var10_12) {
                    bl2 = true;
                    MyLog.e("MiCloudMxMmsTransactionHandler", "server error when downloading pdu", var10_12);
                    MxMessagePduHelper.setResponseStatus(this.mContext, uri, 195);
                    Object var10_13 = var13_56;
                    bl4 = bl;
                }
                catch (InvalidTokenException var10_14) {
                    MyLog.e("MiCloudMxMmsTransactionHandler", "token expired", var10_14);
                    bl4 = true;
                    this.invalidFileToken(string2, string3);
                    ExtendedAuthToken extendedAuthToken5 = extendedAuthToken;
                    bl2 = bl5;
                }
                catch (MxV2DownloadException var10_16) {
                    bl2 = true;
                    MyLog.e("MiCloudMxMmsTransactionHandler", "MxV2DownloadException ", var10_16);
                    void var10_17 = var15_58;
                    bl4 = bl;
                }
                void var11_38 = var10_9;
                bl = bl4;
                if (bl2) {
                    void var11_39 = var10_9;
                    bl = bl4;
                    if (MxConfigCompat.isMxV2Richmedia(string3)) {
                        extendedAuthToken = null;
                        Object var11_40 = null;
                        var13_56 = null;
                        extendedAuthToken2 = extendedAuthToken;
                        try {
                            void var11_42;
                            if (MxConfigCompat.isMxV2Richmedia(string3)) {
                                extendedAuthToken2 = extendedAuthToken;
                                String string5 = MxConfigCompat.getOtherIDCMxV2RichMediaUrl(this.mContext, string2);
                            }
                            extendedAuthToken2 = var11_42;
                            extendedAuthToken2 = extendedAuthToken = this.getOtherIDCFileToken(string2, string3);
                        }
                        catch (Exception var11_46) {
                            MyLog.e("MiCloudMxMmsTransactionHandler", "io error when getting other idc file token", var11_46);
                            bl3 = true;
                            ExtendedAuthToken extendedAuthToken6 = extendedAuthToken2;
                            extendedAuthToken2 = var13_56;
                        }
                        if (bl3) {
                            MxMessagePduHelper.setResponseStatus(this.mContext, uri, 195);
                            void var11_44 = var10_9;
                            bl = bl4;
                        } else if (extendedAuthToken2 == null) {
                            MyLog.w("MiCloudMxMmsTransactionHandler", "failed to get other idc token, cannot retrieve mms");
                            MxMessagePduHelper.setResponseStatus(this.mContext, uri, 225);
                            void var11_48 = var10_9;
                            bl = bl4;
                        } else {
                            void var11_43;
                            MiCloudRichMediaManager miCloudRichMediaManager = this.mFactory.getOtherIDCMiCloudRichMediaManager(this.mContext, string2, extendedAuthToken2, (String)var11_43);
                            try {
                                byte[] arrby = StorageServerHelper.downloadPduViaMxV2(this.mContext, miCloudRichMediaManager, string3);
                                bl = bl4;
                            }
                            catch (InvalidTokenException var11_51) {
                                MyLog.e("MiCloudMxMmsTransactionHandler", "other idc token expired", var11_51);
                                bl = true;
                                this.invalidOtherIDCFileToken(string2, string3);
                                void var11_52 = var10_9;
                            }
                            catch (Exception var11_53) {
                                MyLog.e("MiCloudMxMmsTransactionHandler", "download file from other idc error", var11_53);
                                void var11_54 = var10_9;
                                bl = bl4;
                            }
                        }
                    }
                }
            }
            if (!bl || n <= 0) {
                return var11_25;
            }
            --n;
            void var10_18 = var11_25;
        } while (true);
    }

    public boolean canSendByMxV2() {
        return this.mIsSendByMxV2;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    @Override
    public Uri handleReceiveMxMms(Uri var1_1) {
        var10_3 = var1_1.buildUpon().appendQueryParameter("blocked_flag", "2").build();
        var8_5 = -1;
        var1_1 = SqliteWrapper.query((Context)this.mContext, (ContentResolver)this.mContext.getContentResolver(), (Uri)var10_3, (String[])new String[]{"sim_id"}, (String)null, (String[])null, (String)null);
        var6_6 = var8_5;
        if (var1_1 != null) {
            block17 : {
                var6_6 = var8_5;
                if (!var1_1.moveToFirst()) break block17;
                var2_7 = var1_1.getInt(0);
                var6_6 = var2_7;
            }
            var1_1.close();
        }
        if (var6_6 == -1) {
            MyLog.w("MiCloudMxMmsTransactionHandler", "sim id is null, cannot retrieve mms");
            MxMessagePduHelper.setResponseStatus(this.mContext, var10_3, 130);
            MxMessagePduHelper.handleMxMmsFailed(this.mContext, ContentUris.parseId((Uri)var10_3), false);
            return null;
        }
        ** GOTO lbl22
        catch (Throwable var10_4) {
            var1_1.close();
            throw var10_4;
        }
lbl22: // 1 sources:
        DownloadManager.getInstance().markState(var10_3, 129, var6_6);
        var8_5 = ContentUris.parseId((Uri)var10_3);
        var4_8 = 0;
        var5_9 = MSimUtils.getSlotIdBySimInfoId(var6_6);
        var12_10 = PushSession.getInstance(this.mContext).getMyMid(var5_9);
        if (var12_10 != null) ** GOTO lbl34
        MyLog.w("MiCloudMxMmsTransactionHandler", "mx user id is null, cannot retrieve mms");
        MxMessagePduHelper.setResponseStatus(this.mContext, var10_3, 130);
        var1_1 = var10_3;
        var2_7 = var4_8;
        var6_6 = var8_5;
        ** GOTO lbl82
lbl34: // 1 sources:
        var1_1 = null;
        var11_11 = null;
        var2_7 = -1;
        var13_12 = SqliteWrapper.query((Context)this.mContext, (ContentResolver)this.mContext.getContentResolver(), (Uri)var10_3, (String[])new String[]{"ct_l", "mx_status"}, (String)null, (String[])null, (String)null);
        var3_13 = var2_7;
        if (var13_12 != null) {
            var1_1 = var11_11;
            if (var13_12.moveToFirst()) {
                var1_1 = var13_12.getString(0);
                var2_7 = var13_12.getInt(1);
            }
            var3_13 = var2_7;
        }
        if (var3_13 == 1) ** GOTO lbl54
        var2_7 = 1;
        var6_6 = var8_5;
        var1_1 = var10_3;
        ** GOTO lbl82
        finally {
            var13_12.close();
        }
lbl54: // 1 sources:
        if (TextUtils.isEmpty((CharSequence)var1_1)) {
            MyLog.w("MiCloudMxMmsTransactionHandler", "share id is empty");
            MxMessagePduHelper.setResponseStatus(this.mContext, var10_3, 224);
            var6_6 = var8_5;
            var2_7 = var4_8;
            var1_1 = var10_3;
        } else {
            MxRequestEnv.getMxRequestEnv(this.mContext).setSimIndex(var5_9).setIsMxV2(MxConfigCompat.isMxV2Richmedia((String)var1_1));
            var11_11 = this.tryDownload(var10_3, var12_10, (String)var1_1);
            if (var11_11 != null && var11_11.length > 0) {
                var1_1 = MxMessagePduHelper.persistRetrieveConf(this.mContext, var10_3);
                var6_6 = ContentUris.parseId((Uri)var1_1);
                if (this.mCallback.onDownloadMediaSuccess((Uri)var1_1, var12_10, (byte[])var11_11)) {
                    MxMessagePduHelper.setResponseStatus(this.mContext, (Uri)var1_1, 128);
                    MessagingNotification.blockingUpdateNewMessageIndicator(this.mContext, true, false);
                    var2_7 = 1;
                } else {
                    MxMessagePduHelper.setResponseStatus(this.mContext, (Uri)var1_1, 226);
                    var2_7 = var4_8;
                }
            } else {
                var6_6 = var8_5;
                var2_7 = var4_8;
                var1_1 = var10_3;
                if (var11_11 != null) {
                    MxMessagePduHelper.setResponseStatus(this.mContext, var10_3, 228);
                    var6_6 = var8_5;
                    var2_7 = var4_8;
                    var1_1 = var10_3;
                }
            }
        }
lbl82: // 8 sources:
        if (var2_7 != 0) {
            MxMessagePduHelper.markMmsStatus(this.mContext, (Uri)var1_1, 128);
            return var1_1;
        }
        MxMessagePduHelper.handleMxMmsFailed(this.mContext, var6_6, false);
        return var1_1;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    @Override
    public Uri handleSendMxMms(Uri var1_1) {
        var22_3 = MiuiPduPersister.getPduPersister((Context)this.mContext);
        var23_5 = null;
        try {
            var23_5 = var22_3 = (SendReq)var22_3.load(var1_1);
        }
        catch (MmsException var22_4) {
            MyLog.e("MiCloudMxMmsTransactionHandler", "failed to load mms", (Throwable)var22_4);
        }
        if (var23_5 == null) {
            return var1_1;
        }
        var2_6 = 0;
        var3_7 = 0;
        var22_3 = null;
        var24_8 = null;
        var19_9 = 0;
        var17_10 = 0;
        var15_11 = -1;
        var25_12 = this.mContext.getContentResolver().query(var1_1, MiCloudMxMmsTransactionHandler.MX_MMS_PROJECT, null, null, null);
        var9_16 = var19_9;
        var11_17 = var17_10;
        var13_18 = var15_11;
        if (var25_12 != null) {
            block29 : {
                var9_16 = var19_9;
                var22_3 = var24_8;
                var11_17 = var17_10;
                var2_6 = var3_7;
                var13_18 = var15_11;
                if (!var25_12.moveToFirst()) break block29;
                var2_6 = 1 == var25_12.getInt(3) ? 1 : 0;
                var22_3 = var25_12.getString(0);
                var11_17 = var25_12.getLong(1);
                var9_16 = var25_12.getLong(2);
                var3_7 = var25_12.getInt(4);
                var13_18 = var3_7;
            }
            var25_12.close();
        }
        if (var2_6 == 0) return var1_1;
        var15_11 = ContentUris.parseId((Uri)var1_1);
        var8_19 = MSimUtils.getSlotIdBySimInfoId(var13_18);
        if (var8_19 < 0) {
            MxMessagePduHelper.handleMxMmsFailed(this.mContext, var15_11);
            return var1_1;
        }
        ** GOTO lbl47
        catch (Throwable var1_2) {
            var25_12.close();
            throw var1_2;
        }
lbl47: // 1 sources:
        var27_20 = var23_5.getTo();
        var21_21 = MxActivateService.isMxEnabled(this.mContext, var8_19);
        var24_8 = PushSession.getInstance(this.mContext);
        var28_22 = var24_8.getMyFullMid(var8_19);
        var29_23 = var24_8.getMyMid(var8_19);
        var3_7 = 0;
        var4_24 = 0;
        if (var27_20 != null) ** GOTO lbl58
        MxMessagePduHelper.setResponseStatus(this.mContext, var1_1, 224);
        var3_7 = var4_24;
        ** GOTO lbl92
lbl58: // 1 sources:
        if (var21_21) ** GOTO lbl62
        MxMessagePduHelper.setResponseStatus(this.mContext, var1_1, 224);
        var3_7 = var4_24;
        ** GOTO lbl92
lbl62: // 1 sources:
        if (var28_22 != null && var29_23 != null) ** GOTO lbl66
        MxMessagePduHelper.setResponseStatus(this.mContext, var1_1, 130);
        var3_7 = var4_24;
        ** GOTO lbl92
lbl66: // 1 sources:
        var30_25 = Lists.newArrayList();
        var5_26 = var27_20.length;
        for (var2_6 = 0; var2_6 < var5_26; ++var2_6) {
            var24_8 = Contact.get(var27_20[var2_6].getString());
            if ((var24_8 = MxIdCache.getOrQuery(this.mContext, var24_8.getMxPhoneNumber())) == null || !var24_8.allowMms()) break;
            var30_25.add(var24_8.getMId());
        }
        var24_8 = null;
        var25_12 = var23_5.getSubject();
        var23_5 = var24_8;
        if (var25_12 != null) {
            var23_5 = var25_12.getString();
        }
        if (var27_20.length != var30_25.size()) ** GOTO lbl89
        var24_8 = null;
        try {
            var24_8 = var25_12 = (Object)MiCloudMmsCodec.encodeMmsBody(this.mContext.getContentResolver(), var15_11, false);
        }
        catch (Exception var25_13) {
            MyLog.e("MiCloudMxMmsTransactionHandler", "error when constructing mms", var25_13);
        }
        if (var24_8 != null) ** GOTO lbl99
        MxMessagePduHelper.setResponseStatus(this.mContext, var1_1, 228);
        var3_7 = var4_24;
        ** GOTO lbl92
lbl89: // 1 sources:
        MyLog.d("MiCloudMxMmsTransactionHandler", "some recipients are offline");
        MxMessagePduHelper.setResponseStatus(this.mContext, var1_1, 224);
        var3_7 = var4_24;
lbl92: // 7 sources:
        do {
            if (var3_7 != 0) {
                MxMessagePduHelper.markMmsStatus(this.mContext, var1_1, 128);
                return var1_1;
            }
            MyLog.d("MiCloudMxMmsTransactionHandler", "failed to send mi mms, id: " + var15_11);
            MxMessagePduHelper.handleMxMmsFailed(this.mContext, var15_11);
            return var1_1;
            break;
        } while (true);
lbl99: // 1 sources:
        var31_27 = new ArrayList(4);
        var4_24 = 0;
        var2_6 = var3_7;
        block14 : do {
            block30 : {
                var3_7 = var2_6;
                if (var4_24 >= var27_20.length) ** GOTO lbl92
                var6_28 = var3_7 = var4_24 + 4;
                if (var3_7 > var27_20.length) {
                    var6_28 = var27_20.length;
                }
                for (var3_7 = var4_24; var3_7 < var6_28; ++var3_7) {
                    var31_27.add(var30_25.get(var3_7));
                }
                MxRequestEnv.getMxRequestEnv(this.mContext).setSimIndex(var8_19);
                var5_26 = var2_6;
                try {
                    if (this.canSendByMxV2()) {
                        var5_26 = var2_6;
                        var25_12 = new UploadData((byte[])var24_8, "mixin2", ".gz");
                        break block30;
                    }
                    var5_26 = var2_6;
                    ** try [egrp 6[TRYBLOCK] [19 : 950->970)] { 
lbl121: // 1 sources:
                }
                catch (MxLogicException var25_14) {
                    MxMessagePduHelper.setResponseStatus(this.mContext, var1_1, 224);
                    var2_6 = var5_26;
                    ** GOTO lbl181
                }
                {
                    var25_12 = new UploadData((byte[])var24_8, "mixin", ".gz");
                }
            }
            var5_26 = var2_6;
            MxRequestEnv.getMxRequestEnv(this.mContext).setSimIndex(var8_19).setIsMxV2(this.canSendByMxV2());
            var26_30 = null;
            var5_26 = var2_6;
            if (this.canSendByMxV2()) {
                var5_26 = var2_6;
                var26_30 = MxConfigCompat.getMxV2RichMediaUrl(this.mContext, var29_23);
            }
            var5_26 = var2_6;
            var25_12 = MxMessageUtils.tryUploadFile(this.mContext, var1_1, var29_23, (UploadEntity)var25_12, var31_27, var26_30, this.mCallback, this.mFactory);
            var3_7 = var2_6;
            if (var25_12 == null) ** GOTO lbl175
            var5_26 = var2_6;
            var26_30 = var25_12.shareId;
            var5_26 = var2_6;
            var13_18 = var25_12.expireAt;
            var5_26 = var2_6;
            if (TextUtils.isEmpty((CharSequence)var26_30) || var13_18 <= 0) ** GOTO lbl151
            ** GOTO lbl157
lbl146: // 2 sources:
            catch (IOException var25_15) {
                var5_26 = var2_6;
                MyLog.e("MiCloudMxMmsTransactionHandler", "error when construct upload entity", var25_15);
                var5_26 = var2_6;
                throw new MxLogicException("failed to construct upload entity", var25_15);
            }
lbl151: // 1 sources:
            var5_26 = var2_6;
            MyLog.e("MiCloudMxMmsTransactionHandler", "no shared id or expireAt value, uploading failed");
            var5_26 = var2_6;
            MxMessagePduHelper.setResponseStatus(this.mContext, var1_1, 224);
            var3_7 = var2_6;
            ** GOTO lbl175
lbl157: // 1 sources:
            var7_29 = 1;
            var3_7 = var4_24;
            do {
                var5_26 = var7_29;
                if (var3_7 >= var6_28) ** GOTO lbl165
                var5_26 = var2_6;
                if (!this.mCallback.onUploadMediaSuccess(var1_1, var9_16, (String)var23_5, var28_22, (String)var30_25.get(var3_7), (String)var22_3, var26_30, MxMessageService.adjustExpiredTime(var13_18), var11_17, null)) {
                    var5_26 = 0;
lbl165: // 2 sources:
                    if (var5_26 != 0) {
                        var5_26 = var2_6;
                        MxMessagePduHelper.setResponseStatus(this.mContext, var1_1, 128);
                        var5_26 = 1;
                        var3_7 = 1;
                        MxMessageTrackService.startTrack(this.mContext);
                    } else {
                        var5_26 = var2_6;
                        MxMessagePduHelper.setResponseStatus(this.mContext, var1_1, 224);
                        var3_7 = var2_6;
                    }
lbl175: // 4 sources:
                    var2_6 = var3_7;
                    if (var3_7 == 0) {
                        var5_26 = var3_7;
                        MyLog.d("MiCloudMxMmsTransactionHandler", "sending progress stops, due to uploading failure");
                        ** continue;
                    } else {
                        ** GOTO lbl181
                    }
                }
                ** GOTO lbl184
lbl181: // 3 sources:
                var31_27.clear();
                var4_24 += 4;
                continue block14;
lbl184: // 1 sources:
                ++var3_7;
            } while (true);
            break;
        } while (true);
    }

    protected void invalidFileToken(String string2, String string3) {
        int n = PushSession.getInstance(this.mContext).getSimIdByMid(string2);
        if (n == -1) {
            MyLog.e("MiCloudMxMmsTransactionHandler", "refresh file token failed, because sim index == -1)");
            return;
        }
        PrefUtils.remove(this.mContext, this.getFileTokenPref(string3) + n);
    }

    protected void invalidOtherIDCFileToken(String string2, String string3) {
        int n = PushSession.getInstance(this.mContext).getSimIdByMid(string2);
        if (n == -1) {
            MyLog.e("MiCloudMxMmsTransactionHandler", "refresh other idc file token failed, because sim index == -1)");
            return;
        }
        PrefUtils.remove(this.mContext, this.getFileTokenPref(string3) + n + "_idc");
    }

    @Override
    public void setSendByMxV2(boolean bl) {
        this.mIsSendByMxV2 = bl;
    }

    public static interface MiCloudMediaManagerCallback {
        public ExtendedAuthToken getMediaManagerToken(String var1) throws IOException;

        public boolean onDownloadMediaSuccess(Uri var1, String var2, byte[] var3);

        public void onMediaManagerTokenInvalid(String var1);

        public boolean onUploadMediaSuccess(Uri var1, long var2, String var4, String var5, String var6, String var7, String var8, long var9, long var11, String var13);
    }

}

