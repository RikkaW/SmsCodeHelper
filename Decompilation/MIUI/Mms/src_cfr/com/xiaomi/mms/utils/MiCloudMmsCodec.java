/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentProviderOperation
 *  android.content.ContentProviderOperation$Builder
 *  android.content.ContentProviderResult
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.OperationApplicationException
 *  android.database.Cursor
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.os.RemoteException
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.text.TextUtils
 *  android.util.Base64
 *  android.util.Log
 *  java.io.ByteArrayOutputStream
 *  java.io.FileNotFoundException
 *  java.io.OutputStream
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.nio.charset.Charset
 *  java.util.ArrayList
 *  miui.util.GZIPCodec
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.xiaomi.mms.utils;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.Telephony;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.xiaomi.mms.utils.IOUtils;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import miui.util.GZIPCodec;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MiCloudMmsCodec {
    private static final String[] MMS_PART_PROJECTION = new String[]{"_id", "ct", "cid", "cl", "text"};

    public static void decodeMmsBody(Context context, long l, byte[] arrby, boolean bl) throws IOException, JSONException, RemoteException, OperationApplicationException {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        MiCloudMmsCodec.makeMmsPartWriteOps(arrayList, arrayList2, l, arrby, bl);
        MiCloudMmsCodec.writeMmsPartFiles(context.getContentResolver(), arrayList, arrayList2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static byte[] encodeMmsBody(ContentResolver object, long l, boolean bl) throws JSONException, IOException {
        Uri.Builder builder = Telephony.Mms.CONTENT_URI.buildUpon().appendPath(String.valueOf((long)l)).appendPath("part");
        String string2 = bl ? "1" : "0";
        if ((string2 = object.query(builder.appendQueryParameter("caller_is_syncadapter", string2).build(), MMS_PART_PROJECTION, null, null, null)) == null) {
            Log.e((String)"MiCloudMmsCodec", (String)"encodeMmsBody: Failed to access mms info in database");
            throw new IOException();
        }
        try {
            if (string2.getCount() <= 0) return null;
            builder = new JSONArray();
            while (string2.moveToNext()) {
                l = string2.getLong(0);
                String string3 = string2.getString(1);
                String string4 = string2.getString(2);
                String string5 = string2.getString(3);
                String string6 = string2.getString(4);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("content_type", (Object)string3);
                jSONObject.put("content_id", (Object)string4);
                if (string5 != null) {
                    jSONObject.put("content_location", (Object)string5);
                }
                if (!TextUtils.isEmpty((CharSequence)string6)) {
                    jSONObject.put("text", (Object)string6);
                }
                if ((string3 = (String)MiCloudMmsCodec.readMmsPart((ContentResolver)object, l, bl)) != null) {
                    jSONObject.put("data", (Object)Base64.encodeToString((byte[])string3, (int)0));
                }
                builder.put((Object)jSONObject);
            }
            object = GZIPCodec.encode((byte[])builder.toString().getBytes(StandardCharsets.UTF_8));
            return object;
        }
        finally {
            string2.close();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void makeMmsPartWriteOps(ArrayList<ContentProviderOperation> arrayList, ArrayList<byte[]> arrayList2, long l, byte[] object, boolean bl) throws IOException, JSONException {
        Object object2 = Telephony.Mms.CONTENT_URI.buildUpon().appendPath(String.valueOf((long)l)).appendPath("part");
        String string2 = bl ? "1" : "0";
        string2 = object2.appendQueryParameter("caller_is_syncadapter", string2).appendQueryParameter("supress_making_mms_preview", "1").build();
        object2 = ContentProviderOperation.newDelete((Uri)string2).build();
        arrayList2.add((Object)null);
        arrayList.add(object2);
        object = new JSONArray(new String(GZIPCodec.decode((byte[])object), StandardCharsets.UTF_8));
        int n = 0;
        while (n < object.length()) {
            Object object3 = object.getJSONObject(n);
            object2 = object3.getString("content_type");
            int n2 = object2.equals((Object)"application/smil") ? -1 : 0;
            String string3 = object3.optString("content_id", null);
            String string4 = object3.optString("content_location", null);
            String string5 = object3.optString("text");
            object3 = Base64.decode((String)object3.optString("data"), (int)0);
            ContentValues contentValues = new ContentValues();
            contentValues.put("mid", Long.valueOf((long)l));
            contentValues.put("seq", Integer.valueOf((int)n2));
            contentValues.put("ct", (String)object2);
            contentValues.put("cid", string3);
            contentValues.put("cl", string4);
            if (object2.equals((Object)"text/plain")) {
                contentValues.put("chset", Integer.valueOf((int)106));
            }
            if (object2.equals((Object)"text/plain") || object2.equals((Object)"application/smil")) {
                contentValues.put("text", string5);
                arrayList2.add((Object)null);
            } else {
                arrayList2.add(object3);
            }
            arrayList.add((Object)ContentProviderOperation.newInsert((Uri)string2).withValues(contentValues).build());
            ++n;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static byte[] readMmsPart(ContentResolver var0, long var1_2, boolean var3_3) throws IOException {
        var6_4 = Telephony.Mms.CONTENT_URI.buildUpon().appendPath("part").appendPath(String.valueOf((long)var1_2));
        var5_8 = var3_3 != false ? "1" : "0";
        var10_10 = var6_4.appendQueryParameter("caller_is_syncadapter", (String)var5_8).build();
        var6_4 = null;
        var5_8 = null;
        var9_11 = null;
        var8_12 = null;
        var5_8 = var0 = var0.openInputStream(var10_10);
        var6_4 = var0;
        var7_13 = new ByteArrayOutputStream();
        if (var0 != null) ** GOTO lbl15
        try {
            throw new IOException("Cannot open input stream for " + (Object)var10_10);
lbl15: // 1 sources:
            var5_8 = new byte[4096];
            while ((var4_14 = var0.read((byte[])var5_8)) > 0) {
                var7_13.write((byte[])var5_8, 0, var4_14);
            }
            var5_8 = var7_13.toByteArray();
            ** GOTO lbl27
        }
        catch (FileNotFoundException var5_9) {
            var5_8 = var7_13;
            ** GOTO lbl39
            catch (Throwable var6_5) {
                var5_8 = var7_13;
                ** GOTO lbl33
lbl27: // 1 sources:
                IOUtils.closeQuietly((InputStream)var0);
                IOUtils.closeQuietly((OutputStream)var7_13);
                return var5_8;
                catch (Throwable var6_7) {
                    var0 = var5_8;
                    var5_8 = var9_11;
                }
lbl33: // 2 sources:
                IOUtils.closeQuietly((InputStream)var0);
                IOUtils.closeQuietly((OutputStream)var5_8);
                throw var6_6;
            }
            catch (FileNotFoundException var0_1) {
                var0 = var6_4;
                var5_8 = var8_12;
            }
lbl39: // 2 sources:
            IOUtils.closeQuietly((InputStream)var0);
            IOUtils.closeQuietly((OutputStream)var5_8);
            return null;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void writeMmsPartFiles(ContentResolver contentResolver, ArrayList<ContentProviderOperation> arrayList, ArrayList<byte[]> arrayList2) throws RemoteException, OperationApplicationException, IOException {
        ContentProviderResult[] arrcontentProviderResult = contentResolver.applyBatch("mms", arrayList);
        int n = 0;
        do {
            if (n >= arrcontentProviderResult.length) {
                arrayList.clear();
                arrayList2.clear();
                return;
            }
            byte[] arrby = (byte[])arrayList2.get(n);
            if (arrby != null) {
                Uri uri = arrcontentProviderResult[n].uri.buildUpon().appendQueryParameter("supress_making_mms_preview", "1").build();
                OutputStream outputStream = contentResolver.openOutputStream(uri);
                try {
                    outputStream.write(arrby);
                    Log.v((String)"MiCloudMmsCodec", (String)("wrote " + arrby.length + " bytes to " + (Object)uri));
                }
                finally {
                    IOUtils.closeQuietly(outputStream);
                }
            }
            ++n;
        } while (true);
    }
}

