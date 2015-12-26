/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.net.Uri
 *  android.os.Environment
 *  android.text.TextUtils
 *  com.google.android.mms.ContentType
 *  com.xiaomi.accountsdk.account.data.ExtendedAuthToken
 *  com.xiaomi.micloudsdk.micloudrichmedia.MiCloudRichMediaManager
 *  com.xiaomi.micloudsdk.micloudrichmedia.UploadEntity
 *  com.xiaomi.micloudsdk.micloudrichmedia.UploadResult
 *  java.io.File
 *  java.io.FileInputStream
 *  java.io.FileOutputStream
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.xiaomi.mms.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import com.android.mms.MmsApp;
import com.android.mms.audio.AudioHelper;
import com.android.mms.data.Conversation;
import com.android.mms.data.Mx2WorkingMessage;
import com.android.mms.data.WorkingMessage;
import com.google.android.mms.ContentType;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.micloudsdk.micloudrichmedia.MiCloudRichMediaManager;
import com.xiaomi.micloudsdk.micloudrichmedia.UploadEntity;
import com.xiaomi.micloudsdk.micloudrichmedia.UploadResult;
import com.xiaomi.mms.data.Mx2MessageModel;
import com.xiaomi.mms.mx.cache.ImageCacheUtils;
import com.xiaomi.mms.mx.common.GlobalData;
import com.xiaomi.mms.mx.data.Attachment;
import com.xiaomi.mms.net.exception.InvalidTokenException;
import com.xiaomi.mms.net.exception.MxLogicException;
import com.xiaomi.mms.net.exception.ServerErrorException;
import com.xiaomi.mms.transaction.MiCloudMxMmsTransactionHandler;
import com.xiaomi.mms.transaction.MiCloudRichMediaManagerFactory;
import com.xiaomi.mms.utils.Mx2PduPersister;
import com.xiaomi.mms.utils.MxMessagePduHelper;
import com.xiaomi.mms.utils.StorageServerHelper;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class MxMessageUtils {
    public static boolean convertMx2toMms(Context context, WorkingMessage.MessageStatusListener object, Uri uri, boolean bl) {
        Uri uri2 = null;
        Mx2MessageModel mx2MessageModel = Mx2PduPersister.loadMessageByUri(context, uri);
        Uri uri3 = uri2;
        if (mx2MessageModel != null) {
            Conversation conversation = Conversation.createNew(context, mx2MessageModel.getThreadId());
            object = Mx2WorkingMessage.loadDraft((WorkingMessage.MessageStatusListener)object, conversation);
            object.setConversation(conversation);
            uri3 = uri2;
            if (mx2MessageModel.hasAttachment()) {
                Attachment attachment = mx2MessageModel.getAttachment();
                File file = attachment.getFile(context, AudioHelper.getAudioDir());
                uri3 = uri2;
                if (file != null) {
                    object.removeAttachment(false);
                    String string2 = mx2MessageModel.getMxType();
                    if (!string2.equals((Object)String.valueOf((int)3))) {
                        mx2MessageModel.setAttachments(null);
                    }
                    mx2MessageModel.setMxType(String.valueOf((int)0));
                    object.setMxMessage(mx2MessageModel);
                    object.setMessageUri(uri);
                    object.persistMxMessage();
                    object.setText(mx2MessageModel.getBody());
                    object.setSubject(null, false);
                    uri = MxMessageUtils.createTempFileWithExtension(file, MxMessageUtils.getExtensionByMimeType(attachment.mimeType));
                    uri3 = uri2;
                    if (uri != null) {
                        uri3 = uri2;
                        if (uri.exists()) {
                            object.setAttachment(MxMessageUtils.getAttachmentType(string2), Uri.fromFile((File)uri), true);
                            uri3 = object.saveAsMms(false);
                            conversation.setDraftState(false);
                            if (uri3 != null && bl) {
                                MxMessagePduHelper.markMmsAsCommon(context, uri3);
                            }
                            uri.delete();
                        }
                    }
                }
            }
        }
        if (uri3 != null) {
            return true;
        }
        return false;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void copyFile(File var0, File var1_2) throws IOException {
        if (var0.getAbsolutePath().equals((Object)var1_2.getAbsolutePath())) {
            return;
        }
        var3_6 = null;
        var7_15 = null;
        var4_16 = null;
        var6_17 = null;
        var5_18 = null;
        var0 = new FileInputStream(var0);
        var1_2 = new FileOutputStream((File)var1_2);
        try {
            var3_7 = new byte[1024];
            while ((var2_20 = var0.read(var3_7)) >= 0) {
                var1_2.write(var3_7, 0, var2_20);
            }
            ** GOTO lbl22
        }
        catch (IOException var3_8) {
            var5_18 = var1_2;
            var1_2 = var3_8;
            ** GOTO lbl38
lbl22: // 1 sources:
            if (var0 != null) {
                var0.close();
            }
            if (var1_2 == null) return;
            var1_2.close();
            return;
            catch (Throwable var1_3) {
                var3_13 = var0;
                var4_16 = var6_17;
                var0 = var1_3;
                ** GOTO lbl-1000
            }
            catch (Throwable var5_19) {
                var3_14 = var0;
                var4_16 = var1_2;
                var0 = var5_19;
                ** GOTO lbl-1000
            }
            catch (IOException var1_5) {}
lbl38: // 3 sources:
            do {
                block15 : {
                    var3_10 = var0;
                    var4_16 = var5_18;
                    try {
                        MyLog.e("MxMessageUtils.RICH", var1_2.toString());
                        if (var0 == null) break block15;
                    }
                    catch (Throwable var0_1) lbl-1000: // 3 sources:
                    {
                        if (var3_12 != null) {
                            var3_12.close();
                        }
                        if (var4_16 == null) throw var0;
                        var4_16.close();
                        throw var0;
                    }
                    var0.close();
                }
                if (var5_18 == null) return;
                var5_18.close();
                return;
                break;
            } while (true);
        }
        catch (IOException var1_4) {
            var0 = var7_15;
            ** continue;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static File createTempFileWithExtension(File var0, String var1_4) {
        var1_4 = null;
        var2_5 = new File(MxMessageUtils.getImageCacheTempDir(), var0.getName());
        if (var2_5.exists() != false) return var2_5;
        MxMessageUtils.copyFile(var0, var2_5);
        return var2_5;
        catch (IOException var0_1) {
            var0_2 = var1_4;
            ** GOTO lbl13
            catch (IOException var0_3) {
                var0_2 = var2_5;
            }
lbl13: // 2 sources:
            MyLog.v("MxMessageUtils.RICH", "createTempFile create dir failed");
            return var0_2;
        }
    }

    private static int getAttachmentType(String string2) {
        switch (Integer.parseInt((String)string2)) {
            default: {
                return -1;
            }
            case 2: {
                return 1;
            }
            case 3: 
        }
        return 3;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String getExtensionByMimeType(String string2) {
        String string3;
        String string4 = string3 = "";
        if (TextUtils.isEmpty((CharSequence)string2)) return string4;
        if (ContentType.isImageType((String)string2) && !"image/gif".equalsIgnoreCase(string2)) {
            return ".jpg";
        }
        string4 = string3;
        if (!"audio/amr".equalsIgnoreCase(string2)) return string4;
        return ".amr";
    }

    public static File getImageCacheTempDir() {
        File file;
        File file2 = file = new File(ImageCacheUtils.getExternalCacheDir(GlobalData.app()).getAbsolutePath() + "/tmp");
        if (!file.exists()) {
            file2 = file;
            if (!file.mkdirs()) {
                MyLog.v("MxMessageUtils.RICH", "getImageCacheTempDir create dir failed");
                file2 = null;
            }
        }
        return file2;
    }

    public static String getMmsExternalFileDir() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/Mms";
    }

    public static String getNotificationDescByMx2Type(int n) {
        switch (n) {
            default: {
                return null;
            }
            case 2: {
                return MmsApp.getApp().getString(2131362364);
            }
            case 3: 
        }
        return MmsApp.getApp().getString(2131362363);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String saveAttachmentFileToDisk(Context context, Attachment object) {
        Object var4_3 = null;
        File file = object.getFile(context, AudioHelper.getAudioDir());
        String string2 = (Object)Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DOWNLOADS + "/";
        object = var4_3;
        if (file != null) {
            object = string2 + file.getName();
            try {
                boolean bl;
                string2 = new File((String)object);
                boolean bl2 = bl = string2.getParentFile().exists();
                if (!bl) {
                    bl2 = string2.getParentFile().mkdirs();
                }
                if (bl2) {
                    MxMessageUtils.copyFile(file, new File((String)object));
                } else {
                    MyLog.v("MxMessageUtils.RICH", "saveAttachmentFileToDisk create dir failed");
                    object = var4_3;
                }
            }
            catch (IOException var1_2) {
                MyLog.v("MxMessageUtils.RICH", "saveAttachmentFileToDisk fail " + (Object)((Object)var1_2));
                object = var4_3;
            }
        }
        if (!TextUtils.isEmpty((CharSequence)object)) {
            context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile((File)new File((String)object))));
        }
        return object;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static UploadResult tryUpload(Context context, Uri uri, String string2, UploadEntity uploadEntity, Collection<String> collection, String string3, MiCloudMxMmsTransactionHandler.MiCloudMediaManagerCallback miCloudMediaManagerCallback, MiCloudRichMediaManagerFactory miCloudRichMediaManagerFactory) {
        ExtendedAuthToken extendedAuthToken = null;
        int n = 3;
        do {
            boolean bl;
            ExtendedAuthToken extendedAuthToken2;
            boolean bl2;
            block15 : {
                bl2 = false;
                extendedAuthToken2 = null;
                bl = false;
                try {
                    ExtendedAuthToken extendedAuthToken3 = miCloudMediaManagerCallback.getMediaManagerToken(string2);
                    if (extendedAuthToken3 != null) {
                        extendedAuthToken2 = extendedAuthToken3;
                        MyLog.d("MxMessageUtils.RICH", "upload mx richmedia with fileToken : " + extendedAuthToken3.toPlain());
                        extendedAuthToken2 = extendedAuthToken3;
                        break block15;
                    }
                    extendedAuthToken2 = extendedAuthToken3;
                    MyLog.d("MxMessageUtils.RICH", "upload mx richmedia, but fileToken is null");
                    extendedAuthToken2 = extendedAuthToken3;
                }
                catch (IOException var13_18) {
                    MyLog.e("MxMessageUtils.RICH", "io error when getting file token");
                    bl = true;
                }
            }
            if (bl) {
                MxMessagePduHelper.setResponseStatus(context, uri, 195);
                bl = bl2;
            } else if (extendedAuthToken2 == null) {
                MyLog.e("MxMessageUtils.RICH", "failed to get file token, upload ignore");
                MxMessagePduHelper.setResponseStatus(context, uri, 225);
                bl = bl2;
            } else {
                extendedAuthToken2 = miCloudRichMediaManagerFactory.getMiCloudRichMediaManager(context, string2, extendedAuthToken2, string3);
                try {
                    extendedAuthToken = extendedAuthToken2 = StorageServerHelper.uploadFile((MiCloudRichMediaManager)extendedAuthToken2, uploadEntity, collection);
                    bl = bl2;
                }
                catch (MxLogicException var11_13) {
                    MyLog.e("MxMessageUtils.RICH", "error when uploading pdu", var11_13);
                    MxMessagePduHelper.setResponseStatus(context, uri, 224);
                    bl = bl2;
                }
                catch (IOException var11_14) {
                    MyLog.e("MxMessageUtils.RICH", "io error when uploading pdu", var11_14);
                    MxMessagePduHelper.setResponseStatus(context, uri, 195);
                    bl = bl2;
                }
                catch (ServerErrorException var11_15) {
                    MyLog.e("MxMessageUtils.RICH", "server error when uploading pdu", var11_15);
                    MxMessagePduHelper.setResponseStatus(context, uri, 195);
                    bl = bl2;
                }
                catch (InvalidTokenException var11_16) {
                    MyLog.e("MxMessageUtils.RICH", "upload mx richmeida failed, because token invalid or expired, retty refresh file token", var11_16);
                    bl = true;
                    miCloudMediaManagerCallback.onMediaManagerTokenInvalid(string2);
                }
            }
            if (!bl || n <= 0) {
                return extendedAuthToken;
            }
            --n;
        } while (true);
    }

    public static UploadResult tryUploadFile(Context context, Uri uri, String string2, UploadEntity uploadEntity, Collection<String> collection, String string3, MiCloudMxMmsTransactionHandler.MiCloudMediaManagerCallback miCloudMediaManagerCallback, MiCloudRichMediaManagerFactory miCloudRichMediaManagerFactory) {
        return MxMessageUtils.tryUpload(context, uri, string2, uploadEntity, collection, string3, miCloudMediaManagerCallback, miCloudRichMediaManagerFactory);
    }
}

