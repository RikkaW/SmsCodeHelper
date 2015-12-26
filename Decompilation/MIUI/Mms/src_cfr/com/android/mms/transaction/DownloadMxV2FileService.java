/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.IntentService
 *  android.content.ComponentName
 *  android.content.ContentUris
 *  android.content.Context
 *  android.content.Intent
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.text.TextUtils
 *  android.util.Log
 *  com.xiaomi.accountsdk.account.data.ExtendedAuthToken
 *  com.xiaomi.micloudsdk.micloudrichmedia.MiCloudRichMediaManager
 *  java.io.File
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.ArrayList
 *  java.util.Collections
 *  java.util.HashSet
 */
package com.android.mms.transaction;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Telephony;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.audio.AudioHelper;
import com.android.mms.util.MSimUtils;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.micloudsdk.micloudrichmedia.MiCloudRichMediaManager;
import com.xiaomi.mms.mx.common.GlobalData;
import com.xiaomi.mms.mx.data.Attachment;
import com.xiaomi.mms.net.exception.InvalidTokenException;
import com.xiaomi.mms.net.exception.ServerErrorException;
import com.xiaomi.mms.transaction.MxV2FileTokenUtils;
import com.xiaomi.mms.transaction.PushSession;
import com.xiaomi.mms.utils.Mx2PduPersister;
import com.xiaomi.mms.utils.MxConfigCompat;
import com.xiaomi.mms.utils.MxMessagePduHelper;
import com.xiaomi.mms.utils.MxRequestEnv;
import com.xiaomi.mms.utils.StorageServerHelper;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DownloadMxV2FileService
extends IntentService {
    private static Set<String> sDownloadIngSharedIdSet = Collections.synchronizedSet((Set)new HashSet());

    public DownloadMxV2FileService() {
        super("DownloadMxV2FileService");
    }

    private void downloadAudioFile(String string, int n, String string2) {
        if ((string2 = new File(string2)).exists()) {
            return;
        }
        this.downloadFile(this.getBaseContext(), string, (File)string2, n);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void downloadFile(Context context, String string, File file, int n) {
        boolean bl = false;
        int n2 = 3;
        do {
            boolean bl2 = false;
            MiCloudRichMediaManager miCloudRichMediaManager = this.getMiCloudRichMediaManager(context, string, n);
            boolean bl3 = bl;
            boolean bl4 = bl2;
            if (miCloudRichMediaManager != null) {
                try {
                    MxRequestEnv.getMxRequestEnv(context).setSimIndex(n).setIsMxV2(true);
                    StorageServerHelper.downloadFile(miCloudRichMediaManager, string, file);
                    boolean bl5 = file.exists();
                    bl3 = bl;
                    bl4 = bl2;
                    if (!bl5) {
                        bl4 = true;
                        bl3 = bl;
                    }
                }
                catch (InvalidTokenException var11_12) {
                    bl3 = true;
                    MxV2FileTokenUtils.invalidFileToken(GlobalData.app(), PushSession.getInstance(GlobalData.app()).getMyMid(n));
                    MyLog.e("DownloadFileService.RICH", "fail to download file, InvalidTokenException ", var11_12);
                    bl4 = bl2;
                }
                catch (ServerErrorException var11_13) {
                    bl4 = true;
                    MyLog.e("DownloadFileService.RICH", "fail to download file, ServerErrorException ", var11_13);
                    bl3 = bl;
                }
                catch (IOException var11_14) {
                    bl4 = true;
                    MyLog.e("DownloadFileService.RICH", "fail to download file, IOException", var11_14);
                    bl3 = bl;
                }
            }
            bl = bl3;
            if (bl4) {
                miCloudRichMediaManager = this.getOtherIDCMiCloudRichMediaManager(context, string, n);
                bl = bl3;
                if (miCloudRichMediaManager != null) {
                    try {
                        MxRequestEnv.getMxRequestEnv(context).setSimIndex(n).setIsMxV2(true);
                        StorageServerHelper.downloadFile(miCloudRichMediaManager, string, file);
                        bl = bl3;
                    }
                    catch (InvalidTokenException var11_15) {
                        bl = true;
                        MxV2FileTokenUtils.invalidOtherIDCFileToken(GlobalData.app(), PushSession.getInstance(GlobalData.app()).getMyMid(n));
                        MyLog.e("DownloadFileService.RICH", "fail to download file  from other idc, InvalidTokenException ", var11_15);
                    }
                    catch (ServerErrorException var11_16) {
                        MyLog.e("DownloadFileService.RICH", "fail to download file from other idc, ServerErrorException ", var11_16);
                        bl = bl3;
                    }
                    catch (IOException var11_17) {
                        MyLog.e("DownloadFileService.RICH", "fail to download file from other idc, IOException", var11_17);
                        bl = bl3;
                    }
                }
            }
            if (!bl || n2 <= 0) {
                return;
            }
            --n2;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    private MiCloudRichMediaManager getMiCloudRichMediaManager(Context context, String string, int n) {
        ExtendedAuthToken extendedAuthToken;
        if (n < 0 || TextUtils.isEmpty((CharSequence)(string = PushSession.getInstance(GlobalData.app()).getMyMid(n))) || (extendedAuthToken = MxV2FileTokenUtils.getFileTokenbysimIndex(GlobalData.app(), n)) == null) {
            return null;
        }
        return new MiCloudRichMediaManager(GlobalData.app(), string, extendedAuthToken, MxConfigCompat.getMxV2RichMediaUrl(context, string));
    }

    /*
     * Enabled aggressive block sorting
     */
    private MiCloudRichMediaManager getOtherIDCMiCloudRichMediaManager(Context context, String string, int n) {
        ExtendedAuthToken extendedAuthToken;
        if (n < 0 || TextUtils.isEmpty((CharSequence)(string = PushSession.getInstance(GlobalData.app()).getMyMid(n))) || (extendedAuthToken = MxV2FileTokenUtils.getOtherIDCFileTokenbysimIndex(GlobalData.app(), n)) == null) {
            return null;
        }
        return new MiCloudRichMediaManager(GlobalData.app(), string, extendedAuthToken, MxConfigCompat.getOtherIDCMxV2RichMediaUrl(context, string));
    }

    public static boolean isDownloading(String string) {
        return sDownloadIngSharedIdSet.contains(string);
    }

    public static void startDownloadAudio(Context context, String string, int n, Uri uri, boolean bl) {
        DownloadMxV2FileService.startDownloadAudio(context, string, n, uri, bl, null);
    }

    public static void startDownloadAudio(Context context, String string, int n, Uri uri, boolean bl, String string2) {
        Intent intent = new Intent("com.xiaomi.mms.action.download.audio");
        intent.setClass(GlobalData.app(), (Class)DownloadMxV2FileService.class);
        intent.putExtra("shareId", string);
        intent.putExtra(MSimUtils.SLOT_ID, n);
        intent.putExtra("uri", uri.toString());
        intent.putExtra("isMsgIn", bl);
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            intent.putExtra("fileName", string2);
        }
        context.startService(intent);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected void onHandleIntent(Intent object) {
        Object object2;
        if (object == null) {
            return;
        }
        if (!"com.xiaomi.mms.action.download.audio".equalsIgnoreCase(object.getAction())) return;
        String string = object.getStringExtra("shareId");
        if (TextUtils.isEmpty((CharSequence)string)) {
            Log.e((String)"DownloadFileService.RICH", (String)"Download audio get shareId is null");
            return;
        }
        int n = MSimUtils.getSlotIdFromIntent((Intent)object);
        Uri uri = Uri.parse((String)object.getStringExtra("uri"));
        boolean bl = object.getBooleanExtra("isMsgIn", false);
        object = object2 = object.getStringExtra("fileName");
        if (TextUtils.isEmpty((CharSequence)object2)) {
            object = "" + System.currentTimeMillis() + ".amr";
        }
        object2 = AudioHelper.getAudioDir(true);
        object2 = (String)object2 + "/" + (String)object;
        sDownloadIngSharedIdSet.add(string);
        this.downloadAudioFile(string, n, (String)object2);
        sDownloadIngSharedIdSet.remove(string);
        if (!new File((String)object2).exists()) return;
        object2 = Mx2PduPersister.loadMessageByUri(this.getApplicationContext(), uri);
        if (object2 == null) {
            Log.v((String)"DownloadFileService.RICH", (String)("DownloadFileService.   loadMessageByUri    fail    uri = " + (Object)uri));
            return;
        }
        object2 = object2.getAttachment();
        object2.filename = object;
        object = new ArrayList();
        object.add(object2);
        n = Mx2PduPersister.updateExtension(this.getApplicationContext(), uri, object);
        Log.i((String)"DownloadFileService.RICH", (String)("onHandleIntent    result = " + n));
        if (!bl) return;
        MxMessagePduHelper.markMmsMxStatus(this.getApplicationContext(), ContentUris.withAppendedId((Uri)Telephony.Mms.CONTENT_URI, (long)ContentUris.parseId((Uri)uri)).buildUpon().appendQueryParameter("blocked_flag", "2").build(), 65537);
    }
}

