/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.database.Cursor
 *  android.net.Uri
 *  android.text.TextUtils
 *  android.util.Log
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.xiaomi.mms.transaction;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.mms.mx.fw.futils.HmsConstants;
import com.xiaomi.mms.transaction.MsgThread;
import java.io.Closeable;

public class MipubThread
extends MsgThread {
    public MipubThread(long l, String string2) {
        super(l, string2);
    }

    private void closeQuietly(Closeable closeable) {
        if (closeable == null) {
            Log.d((String)"MipubThread", (String)"closeQuietly, get a null object");
            return;
        }
        try {
            closeable.close();
            return;
        }
        catch (Exception var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    private Uri getMipubDetailUri(long l, String string2, Uri uri) {
        Uri uri2;
        uri = uri2 = Uri.withAppendedPath((Uri)uri, (String)String.valueOf((long)l));
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            uri = Uri.withAppendedPath((Uri)uri2, (String)("?subId=" + string2));
        }
        return uri;
    }

    private int getMipubIdFromAddress(String string2) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            Log.e((String)"MipubThread", (String)"getMipubIdFromAddress, get a empty address: ");
            return 0;
        }
        int n = string2.indexOf("@");
        if (-1 == n || n == 0) {
            Log.e((String)"MipubThread", (String)("getMipubIdFromAddress, get a invalid address: " + string2));
            return 0;
        }
        return Integer.valueOf((String)string2.substring(0, n));
    }

    private String getSenderNameFromDb(Context context, String string2) {
        Uri uri;
        block4 : {
            Object var3_4 = null;
            uri = Mipub.CONTENT_URI_MIPUB_DETAIL_ITEM;
            ContentResolver contentResolver = context.getContentResolver();
            context = null;
            try {
                uri = contentResolver.query(uri, new String[]{"name"}, "address = ?", new String[]{string2}, null);
                string2 = var3_4;
                if (uri == null) break block4;
                string2 = var3_4;
                context = uri;
            }
            catch (Throwable var2_3) {
                this.closeQuietly((Closeable)context);
                throw var2_3;
            }
            if (!uri.moveToFirst()) break block4;
            context = uri;
            string2 = uri.getString(0);
        }
        this.closeQuietly((Closeable)uri);
        return string2;
    }

    private String getSubMipubIdFromAddress(String string2) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            Log.e((String)"MipubThread", (String)"getSubMipubIdFromAddress, get a empty address: ");
            return "";
        }
        int n = string2.indexOf("/");
        if (n != -1 && n != 0) {
            return string2.substring(n, string2.length());
        }
        Log.e((String)"MipubThread", (String)("getSubMipubIdFromAddress, get a invalid address: " + string2));
        return "";
    }

    private boolean loadMipubDetailBackgroundFromService(Context context, String string2) {
        long l = this.getMipubIdFromAddress(string2);
        if (l <= 0) {
            return false;
        }
        string2 = this.getMipubDetailUri(l, this.getSubMipubIdFromAddress(string2), Mipub.CONTENT_URI_MIPUB_DETAIL_ITEM_NET);
        context = context.getContentResolver();
        try {
            context = context.query((Uri)string2, null, null, null, null);
        }
        catch (Throwable var1_2) {
            this.closeQuietly(null);
            throw var1_2;
        }
        this.closeQuietly((Closeable)context);
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) return false;
        if (this.getClass() != object.getClass()) {
            return false;
        }
        object = (MipubThread)object;
        if (this.mThreadId == object.mThreadId) return true;
        return false;
    }

    @Override
    public Intent getConversationClickIntent(Context context) {
        context = new Intent();
        context.setAction("com.miui.mipub.action_open_thread");
        context.putExtra("thread_id", this.mThreadId);
        context.putExtra("open_cv_from", "notification");
        context.setPackage(this.getPackageName());
        return context;
    }

    @Override
    public Intent getCvListClickIntent(Context context) {
        context = new Intent();
        context.setAction("com.miui.mipub.action_open_threadlist");
        context.setPackage("com.miui.mipub");
        context.putExtra("open_cvlist_from", "notification");
        return context;
    }

    @Override
    public String getPackageName() {
        return "com.miui.mipub";
    }

    @Override
    public Uri getPeoplePreferenceUri(String string2) {
        return null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public String getSenderName(Context context, String string2) {
        String string3;
        String string4 = string3 = this.getSenderNameFromDb(context, string2);
        if (!TextUtils.isEmpty((CharSequence)string3)) return string4;
        boolean bl = false;
        try {
            boolean bl2;
            bl = bl2 = this.loadMipubDetailBackgroundFromService(context, string2);
        }
        catch (Exception var5_5) {
            Log.e((String)"MipubThread", (String)"loadMipubDetailBackgroundFromService ", (Throwable)var5_5);
        }
        if (bl) {
            string3 = this.getSenderNameFromDb(context, string2);
        }
        string4 = string3;
        if (!TextUtils.isEmpty((CharSequence)string3)) return string4;
        return context.getString(2131361986);
    }

    public int hashCode() {
        return ("MipubThread" + this.mThreadId).hashCode();
    }

    public static interface Mipub {
        public static final Uri CONTENT_URI;
        public static final Uri CONTENT_URI_DETAIL;
        public static final Uri CONTENT_URI_MIPUB_DETAIL_ITEM;
        public static final Uri CONTENT_URI_MIPUB_DETAIL_ITEM_NET;
        public static final String[] STATUS_PROJECTION;

        default static {
            STATUS_PROJECTION = new String[]{"thread_id", "date", "_id", "snippet", "address"};
            CONTENT_URI = HmsConstants.MIPUB_MSG_URI;
            CONTENT_URI_DETAIL = Uri.parse((String)"content://com.miui.mipub.MipubDetailProvider/");
            CONTENT_URI_MIPUB_DETAIL_ITEM_NET = Uri.withAppendedPath((Uri)CONTENT_URI_DETAIL, (String)"mipub_detail_item_net");
            CONTENT_URI_MIPUB_DETAIL_ITEM = Uri.withAppendedPath((Uri)CONTENT_URI_DETAIL, (String)"mipub_detail_item");
        }
    }

}

