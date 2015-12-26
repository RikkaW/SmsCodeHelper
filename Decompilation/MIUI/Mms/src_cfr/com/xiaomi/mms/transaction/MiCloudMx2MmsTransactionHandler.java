/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.ContentUris
 *  android.content.Context
 *  android.database.Cursor
 *  android.net.Uri
 *  android.text.TextUtils
 *  android.util.Log
 *  com.xiaomi.micloudsdk.micloudrichmedia.UploadEntity
 *  com.xiaomi.micloudsdk.micloudrichmedia.UploadFile
 *  com.xiaomi.micloudsdk.micloudrichmedia.UploadResult
 *  java.io.File
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.ArrayList
 */
package com.xiaomi.mms.transaction;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.audio.AudioHelper;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.util.MSimUtils;
import com.xiaomi.micloudsdk.micloudrichmedia.UploadEntity;
import com.xiaomi.micloudsdk.micloudrichmedia.UploadFile;
import com.xiaomi.micloudsdk.micloudrichmedia.UploadResult;
import com.xiaomi.mms.data.Mx2MessageModel;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.mx.data.Attachment;
import com.xiaomi.mms.mx.utils.AttachmentUtils;
import com.xiaomi.mms.net.exception.MxLogicException;
import com.xiaomi.mms.transaction.MiCloudMxMmsTransactionHandler;
import com.xiaomi.mms.transaction.MiCloudRichMediaManagerFactory;
import com.xiaomi.mms.transaction.MxActivateService;
import com.xiaomi.mms.transaction.MxMessageService;
import com.xiaomi.mms.transaction.MxMessageTrackService;
import com.xiaomi.mms.transaction.MxMmsTransactionHandler;
import com.xiaomi.mms.transaction.PushSession;
import com.xiaomi.mms.utils.Mx2PduPersister;
import com.xiaomi.mms.utils.MxConfigCompat;
import com.xiaomi.mms.utils.MxMessagePduHelper;
import com.xiaomi.mms.utils.MxMessageUtils;
import com.xiaomi.mms.utils.MxRequestEnv;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MiCloudMx2MmsTransactionHandler
implements MxMmsTransactionHandler {
    private static final String[] MX_MMS_PROJECT = new String[]{"ct_t", "m_size", "mx_id", "mx_status", "sim_id"};
    private MiCloudMxMmsTransactionHandler.MiCloudMediaManagerCallback mCallback;
    private Context mContext;
    private MiCloudRichMediaManagerFactory mFactory;

    public MiCloudMx2MmsTransactionHandler(Context context, MiCloudMxMmsTransactionHandler.MiCloudMediaManagerCallback miCloudMediaManagerCallback, MiCloudRichMediaManagerFactory miCloudRichMediaManagerFactory) {
        this.mContext = context;
        this.mCallback = miCloudMediaManagerCallback;
        this.mFactory = miCloudRichMediaManagerFactory;
    }

    @Override
    public Uri handleReceiveMxMms(Uri uri) {
        Log.v((String)"MiCloudMx2MmsTransactionHandler.RICH", (String)"handleReceiveMx2");
        return null;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    @Override
    public Uri handleSendMxMms(Uri var1_1) {
        block31 : {
            block30 : {
                var23_3 = Mx2PduPersister.loadMessageByUri(this.mContext, var1_1);
                if (var23_3 == null) {
                    Log.v((String)"MiCloudMx2MmsTransactionHandler.RICH", (String)"PduDBUtils.loadMessageFromPdu fail");
                    var21_4 = null;
lbl5: // 4 sources:
                    do {
                        return var21_4;
                        break;
                    } while (true);
                }
                var21_4 = var23_3.getAttachments();
                var19_9 = var20_8 = null;
                if (var21_4 != null) {
                    var19_9 = var20_8;
                    if (!var21_4.isEmpty()) {
                        var19_9 = (Attachment)var21_4.get(0);
                    }
                }
                var21_4 = var1_1;
                if (var19_9 == null) ** GOTO lbl5
                var21_4 = var1_1;
                if (TextUtils.isEmpty((CharSequence)var19_9.filename)) ** GOTO lbl5
                var20_8 = new File(var19_9.getLocalPath(this.mContext, AudioHelper.getAudioDir()));
                if (var20_8.exists()) {
                    Log.v((String)"MiCloudMx2MmsTransactionHandler.RICH", (String)"file exist");
lbl20: // 2 sources:
                    do {
                        var24_10 = var20_8.getAbsolutePath();
                        var2_11 = var19_9.filename.lastIndexOf(".");
                        var20_8 = ".jpg";
                        if (var2_11 > 0) {
                            var20_8 = var19_9.filename.substring(var2_11);
                        }
                        var25_12 = new ArrayList();
                        var21_4 = Conversation.get(var23_3.getThreadId()).getRecipients().iterator();
                        while (var21_4.hasNext()) {
                            var22_13 = (Contact)var21_4.next();
                            if ((var22_13 = MxIdCache.getOrQuery(this.mContext, var22_13.getNumber())) == null) continue;
                            var25_12.add(var22_13.getMId());
                        }
                        break;
                        break;
                    } while (true);
                } else {
                    MxMessagePduHelper.setResponseStatus(this.mContext, var1_1, 228);
                    ** continue;
                }
                var2_11 = 0;
                var3_14 = 0;
                var16_15 = 0;
                var14_16 = -1;
                var21_4 = this.mContext.getContentResolver().query(var1_1, MiCloudMx2MmsTransactionHandler.MX_MMS_PROJECT, null, null, null);
                var10_17 = var16_15;
                var12_18 = var14_16;
                if (var21_4 == null) ** GOTO lbl58
                var10_17 = var16_15;
                var2_11 = var3_14;
                var12_18 = var14_16;
                try {
                    if (!var21_4.moveToFirst()) ** GOTO lbl-1000
                    if (1 != var21_4.getInt(3)) break block30;
                    var2_11 = 1;
lbl51: // 2 sources:
                    do {
                        var10_17 = var21_4.getLong(2);
                        var12_18 = var21_4.getLong(4);
                        break;
                    } while (true);
                }
                catch (Throwable var1_2) {
                    var21_4.close();
                    throw var1_2;
                }
lbl-1000: // 2 sources:
                {
                    var21_4.close();
lbl58: // 2 sources:
                    var21_4 = var1_1;
                    if (var2_11 == 0) ** continue;
                    var14_16 = ContentUris.parseId((Uri)var1_1);
                    var5_19 = var2_11 = MSimUtils.getSlotIdBySimInfoId(var12_18);
                    if (var2_11 < 0) {
                        var5_19 = var2_11 = MSimUtils.getInsertedSlotId();
                        if (var2_11 < 0) {
                            MxMessagePduHelper.handleMxMmsFailed(this.mContext, var14_16, true, true);
                            return var1_1;
                        }
                    }
                    break block31;
                }
            }
            var2_11 = 0;
            ** while (true)
        }
        var18_20 = MxActivateService.isMxEnabled(this.mContext, var5_19);
        var21_4 = PushSession.getInstance(this.mContext);
        var26_21 = var21_4.getMyFullMid(var5_19);
        var27_22 = var21_4.getMyMid(var5_19);
        var2_11 = 0;
        var4_23 = 0;
        if (var25_12 != null) ** GOTO lbl84
        MxMessagePduHelper.setResponseStatus(this.mContext, var1_1, 224);
lbl80: // 5 sources:
        while (var4_23 != 0) {
            MxMessagePduHelper.markMmsStatus(this.mContext, var1_1, 128);
            return var1_1;
        }
        ** GOTO lbl189
lbl84: // 1 sources:
        if (var18_20) ** GOTO lbl87
        MxMessagePduHelper.setResponseStatus(this.mContext, var1_1, 224);
        ** GOTO lbl80
lbl87: // 1 sources:
        if (var26_21 != null && var27_22 != null) ** GOTO lbl90
        MxMessagePduHelper.setResponseStatus(this.mContext, var1_1, 130);
        ** GOTO lbl80
lbl90: // 1 sources:
        var28_24 = new ArrayList(4);
        var3_14 = 0;
        do {
            block35 : {
                block34 : {
                    block33 : {
                        block32 : {
                            var4_23 = var2_11;
                            if (var3_14 >= var25_12.size()) ** GOTO lbl80
                            var6_25 = var4_23 = var3_14 + 4;
                            if (var4_23 > var25_12.size()) {
                                var6_25 = var25_12.size();
                            }
                            for (var4_23 = var3_14; var4_23 < var6_25; ++var4_23) {
                                var28_24.add(var25_12.get(var4_23));
                            }
                            var21_4 = new UploadFile(var24_10, "mixin2", (String)var20_8);
                            var4_23 = var2_11;
                            MxRequestEnv.getMxRequestEnv(this.mContext).setSimIndex(var5_19).setIsMxV2(true);
                            var4_23 = var2_11;
                            var21_4 = MxMessageUtils.tryUploadFile(this.mContext, var1_1, var27_22, (UploadEntity)var21_4, var28_24, MxConfigCompat.getMxV2RichMediaUrl(this.mContext, var27_22), this.mCallback, this.mFactory);
                            var4_23 = var2_11;
                            if (var21_4 == null) break block32;
                            var4_23 = var2_11;
                            if (TextUtils.isEmpty((CharSequence)var21_4.shareId)) ** GOTO lbl179
                            var4_23 = var2_11;
                            if (var21_4.expireAt <= 0) ** GOTO lbl179
                            var8_27 = 1;
                            var4_23 = var2_11;
                            var29_29 = var21_4.shareId;
                            var4_23 = var2_11;
                            var22_13 = var21_4.downloadUrl;
                            var4_23 = var2_11;
                            var19_9.shareId = var29_29;
                            var4_23 = var2_11;
                            var19_9.link = var22_13;
                            var4_23 = var2_11;
                            var12_18 = var21_4.expireAt;
                            var4_23 = var2_11;
                            var21_4 = var22_13 = var23_3.getBody();
                            if (var22_13 == null) {
                                var21_4 = "";
                            }
                            var4_23 = var2_11;
                            var9_28 = AttachmentUtils.getMessageTypeFromMimeType(var19_9.mimeType);
                            var4_23 = var2_11;
                            var16_15 = System.currentTimeMillis();
                            var4_23 = var2_11;
                            var23_3.setDate(var16_15 / 1000);
                            var4_23 = var2_11;
                            var23_3.setDateSent(var16_15 / 1000);
                            var4_23 = var2_11;
                            var23_3.setDateMsPart(var16_15 % 1000);
                            var7_26 = var3_14;
lbl140: // 2 sources:
                            var4_23 = var8_27;
                            if (var7_26 < var6_25) {
                                var4_23 = var2_11;
                                if (this.mCallback.onUploadMediaSuccess(var1_1, var10_17, (String)var21_4, var26_21, (String)var25_12.get(var7_26), String.valueOf((int)var9_28), var29_29, MxMessageService.adjustExpiredTime(var12_18), var19_9.datasize, var23_3.buildXmppExtension())) break block33;
                                var4_23 = 0;
                            }
                            if (var4_23 == 0) break block34;
                            var4_23 = var2_11;
                            Mx2PduPersister.updateMxMessage(this.mContext, var23_3, var23_3.getMsgId());
                            var4_23 = var2_11;
                            MxMessagePduHelper.setResponseStatus(this.mContext, var1_1, 128);
                            var4_23 = 1;
                            var2_11 = 1;
                            MxMessageTrackService.startTrack(this.mContext);
                            var4_23 = var2_11;
                        }
lbl155: // 4 sources:
                        do {
                            var28_24.clear();
                            if (var4_23 != 0) break block35;
                            Log.w((String)"MiCloudMx2MmsTransactionHandler.RICH", (String)"sending progress stops, due to uploading failure");
                            ** GOTO lbl80
                            break;
                        } while (true);
                        catch (IOException var21_5) {
                            try {
                                Log.e((String)"MiCloudMx2MmsTransactionHandler.RICH", (String)"error when construct upload entity", (Throwable)var21_5);
                                throw new MxLogicException("failed to construct upload entity", var21_5);
                            }
                            catch (MxLogicException var21_6) lbl-1000: // 2 sources:
                            {
                                do {
                                    MyLog.e("MiCloudMx2MmsTransactionHandler.RICH", "upload mx2 richmedia error", (Throwable)var21_4);
                                    MxMessagePduHelper.setResponseStatus(this.mContext, var1_1, 224);
                                    var4_23 = var2_11;
                                    break;
                                } while (true);
                            }
                        }
                    }
                    ++var7_26;
                    ** GOTO lbl140
                }
                var4_23 = var2_11;
                MxMessagePduHelper.setResponseStatus(this.mContext, var1_1, 224);
                var4_23 = var2_11;
                ** GOTO lbl155
lbl179: // 2 sources:
                var4_23 = var2_11;
                MyLog.e("MiCloudMx2MmsTransactionHandler.RICH", "no shared id or expireAt value, uploading failed");
                var4_23 = var2_11;
                MxMessagePduHelper.setResponseStatus(this.mContext, var1_1, 224);
                var4_23 = var2_11;
                ** continue;
            }
            var3_14 += 4;
            var2_11 = var4_23;
        } while (true);
lbl189: // 1 sources:
        MxMessagePduHelper.handleMxMmsFailed(this.mContext, var14_16, true, true);
        return var1_1;
        catch (MxLogicException var21_7) {
            var2_11 = var4_23;
            ** continue;
        }
    }

    @Override
    public void setSendByMxV2(boolean bl) {
    }
}

