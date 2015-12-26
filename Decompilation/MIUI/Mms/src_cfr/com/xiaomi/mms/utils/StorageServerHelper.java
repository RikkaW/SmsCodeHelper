/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.accounts.NetworkErrorException
 *  android.content.Context
 *  com.xiaomi.micloudsdk.exception.CloudParameterError
 *  com.xiaomi.micloudsdk.exception.CloudRichMediaServerException
 *  com.xiaomi.micloudsdk.exception.CloudServerException
 *  com.xiaomi.micloudsdk.exception.FileTooLargeException
 *  com.xiaomi.micloudsdk.micloudrichmedia.MiCloudRichMediaManager
 *  com.xiaomi.micloudsdk.micloudrichmedia.MicloudDownload
 *  com.xiaomi.micloudsdk.micloudrichmedia.MicloudDownload$Result
 *  com.xiaomi.micloudsdk.micloudrichmedia.UploadEntity
 *  com.xiaomi.micloudsdk.micloudrichmedia.UploadResult
 *  java.io.File
 *  java.io.FileInputStream
 *  java.io.FileOutputStream
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.xiaomi.mms.utils;

import android.accounts.NetworkErrorException;
import android.content.Context;
import com.xiaomi.micloudsdk.exception.CloudParameterError;
import com.xiaomi.micloudsdk.exception.CloudRichMediaServerException;
import com.xiaomi.micloudsdk.exception.CloudServerException;
import com.xiaomi.micloudsdk.exception.FileTooLargeException;
import com.xiaomi.micloudsdk.micloudrichmedia.MiCloudRichMediaManager;
import com.xiaomi.micloudsdk.micloudrichmedia.MicloudDownload;
import com.xiaomi.micloudsdk.micloudrichmedia.UploadEntity;
import com.xiaomi.micloudsdk.micloudrichmedia.UploadResult;
import com.xiaomi.mms.net.exception.InvalidTokenException;
import com.xiaomi.mms.net.exception.MxLogicException;
import com.xiaomi.mms.net.exception.MxV2DownloadException;
import com.xiaomi.mms.net.exception.ServerErrorException;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

public class StorageServerHelper {
    public static void downloadFile(MiCloudRichMediaManager miCloudRichMediaManager, String string2, File file) throws IOException, ServerErrorException, InvalidTokenException {
        try {
            if (StorageServerHelper.isPublicUrl(string2)) {
                StorageServerHelper.downloadFileFromPublicUrl(miCloudRichMediaManager, string2, file);
                return;
            }
            miCloudRichMediaManager.download2(string2, "mixin2", file);
            return;
        }
        catch (NetworkErrorException var0_1) {
            throw new IOException((Throwable)var0_1);
        }
        catch (CloudRichMediaServerException var0_2) {
            if (StorageServerHelper.isServiceTokenInvalid((CloudServerException)var0_2)) {
                throw new InvalidTokenException("download mx2.0 richmedia failed, because token invalid when downloading pdu", (Throwable)var0_2);
            }
            throw new ServerErrorException("download mx2.0 richmedia failed, because micloud server error", (Throwable)var0_2);
        }
        catch (CloudParameterError var0_3) {
            throw new ServerErrorException("download mx2.0 richmedia failed, because param error", (Throwable)var0_3);
        }
    }

    private static void downloadFileFromPublicUrl(MiCloudRichMediaManager object, String fileOutputStream, File file) throws CloudRichMediaServerException, IOException {
        if ((fileOutputStream = fileOutputStream.split("\\s+")).length == 2) {
            MyLog.d("StorageServerHelper", "download multi-media part form public url");
            object = object.downloadFromPublicUrl(fileOutputStream[0], fileOutputStream[1]);
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write((byte[])object);
            fileOutputStream.close();
        }
    }

    public static byte[] downloadPdu(MiCloudRichMediaManager object, String string2) throws IOException, ServerErrorException, InvalidTokenException {
        try {
            if (StorageServerHelper.isPublicUrl(string2)) {
                return StorageServerHelper.downloadPduFromPublicUrl((MiCloudRichMediaManager)object, string2);
            }
            object = object.download2(string2, "mixin");
            return object;
        }
        catch (NetworkErrorException var0_1) {
            throw new IOException("download mx richmedia failed, because io exception ", (Throwable)var0_1);
        }
        catch (CloudRichMediaServerException var0_2) {
            if (StorageServerHelper.isServiceTokenInvalid((CloudServerException)var0_2)) {
                throw new InvalidTokenException("download mx richmedia failed, because token expired", (Throwable)var0_2);
            }
            throw new ServerErrorException("download mx richmedia failed, because micloud server error", (Throwable)var0_2);
        }
        catch (CloudParameterError var0_3) {
            throw new ServerErrorException("download mx richmedia failed, because param error", (Throwable)var0_3);
        }
    }

    private static byte[] downloadPduFromPublicUrl(MiCloudRichMediaManager miCloudRichMediaManager, String arrstring) throws IOException, InvalidTokenException, ServerErrorException, CloudRichMediaServerException {
        if ((arrstring = arrstring.split("\\s+")).length == 2) {
            return miCloudRichMediaManager.downloadFromPublicUrl(arrstring[0], arrstring[1]);
        }
        return null;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static byte[] downloadPduViaMxV2(Context var0, MiCloudRichMediaManager var1_1, String var2_6) throws IOException, ServerErrorException, InvalidTokenException, MxV2DownloadException {
        var5_7 = (Object)var0.getCacheDir() + File.separator + var2_6;
        var3_8 = null;
        var4_9 = null;
        var0 = new File(var5_7);
        try {
            StorageServerHelper.downloadFile((MiCloudRichMediaManager)var1_1, var2_6, (File)var0);
            if (!var0.exists()) {
                throw new MxV2DownloadException("download File is not exsit,may be in other idc");
            }
            var2_6 = new FileInputStream(var5_7);
            ** GOTO lbl15
        }
        catch (Throwable var1_2) {
            block9 : {
                var2_6 = var3_8;
                ** GOTO lbl29
lbl15: // 1 sources:
                try {
                    var1_1 = new byte[var2_6.available()];
                    var2_6.read((byte[])var1_1);
                    if (var2_6 == null) break block9;
                }
                catch (Throwable var1_5) {}
                var2_6.close();
            }
            if (var0.exists() == false) return var1_1;
            var0.delete();
            return var1_1;
            catch (Throwable var1_4) {
                var2_6 = var3_8;
                var0 = var4_9;
            }
            if (var2_6 != null) {
                var2_6.close();
            }
            if (var0.exists() == false) throw var1_3;
            var0.delete();
            throw var1_3;
        }
    }

    private static boolean isPublicUrl(String string2) {
        return string2.startsWith("http");
    }

    private static boolean isServiceTokenInvalid(CloudServerException cloudServerException) {
        int n = cloudServerException.getStatusCode();
        if (n == 401 || n == 400) {
            return true;
        }
        return false;
    }

    public static UploadResult uploadFile(MiCloudRichMediaManager miCloudRichMediaManager, UploadEntity uploadEntity, Collection<String> collection) throws MxLogicException, IOException, ServerErrorException, InvalidTokenException {
        try {
            miCloudRichMediaManager = miCloudRichMediaManager.upload(uploadEntity, collection);
            return miCloudRichMediaManager;
        }
        catch (NetworkErrorException var0_1) {
            throw new IOException("upload mx richmedia failed, because no active network", (Throwable)var0_1);
        }
        catch (CloudParameterError var0_2) {
            throw new ServerErrorException("upload mx richmedia failed, because param error", (Throwable)var0_2);
        }
        catch (FileTooLargeException var0_3) {
            throw new ServerErrorException("upload mx richmedia failed, because file too large", (Throwable)var0_3);
        }
        catch (CloudRichMediaServerException var0_4) {
            if (StorageServerHelper.isServiceTokenInvalid((CloudServerException)var0_4)) {
                throw new InvalidTokenException("upload mx richmedia failed, because token is invalid or expired", (Throwable)var0_4);
            }
            throw new ServerErrorException("upload mx richmedia failed, because micloud server error", (Throwable)var0_4);
        }
    }
}

