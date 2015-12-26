/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentProvider
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.UriMatcher
 *  android.database.Cursor
 *  android.net.Uri
 *  android.os.ParcelFileDescriptor
 *  android.text.TextUtils
 *  android.util.Log
 *  java.io.File
 *  java.io.FileNotFoundException
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.android.mms;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;

public class TempFileProvider
extends ContentProvider {
    public static final Uri SCRAP_CONTENT_URI;
    private static String TAG;
    private static final UriMatcher sURLMatcher;

    static {
        TAG = "TempFileProvider";
        SCRAP_CONTENT_URI = Uri.parse((String)"content://mms_temp_file/scrapSpace");
        sURLMatcher = new UriMatcher(-1);
        sURLMatcher.addURI("mms_temp_file", "scrapSpace", 1);
    }

    public static String getScrapPath(Context context) {
        return TempFileProvider.getScrapPath(context, ".temp.jpg");
    }

    public static String getScrapPath(Context context, String string) {
        if (context != null) {
            return context.getCacheDir().getAbsolutePath() + "/" + string;
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private ParcelFileDescriptor getTempStoreFd() {
        String string = TempFileProvider.getScrapPath(this.getContext());
        File file = null;
        if (TextUtils.isEmpty((CharSequence)string)) {
            return null;
        }
        try {
            File file2 = new File(string);
            File file3 = file2.getParentFile();
            if (file3.exists()) return file2 = ParcelFileDescriptor.open((File)file2, (int)939524096);
            if (file3.mkdirs()) return file2 = ParcelFileDescriptor.open((File)file2, (int)939524096);
            Log.e((String)TAG, (String)("[TempFileProvider] tempStoreFd: " + file3.getPath() + "does not exist!"));
            return null;
        }
        catch (Exception var2_4) {
            Log.e((String)TAG, (String)("getTempStoreFd: error creating pfd for " + string), (Throwable)var2_4);
            return file;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static Uri renameScrapFile(String string, String string2, Context context) {
        String string3 = TempFileProvider.getScrapPath(context);
        if (TextUtils.isEmpty((CharSequence)string3)) {
            return null;
        }
        String string4 = string2;
        if (string2 == null) {
            string4 = "";
        }
        string = new File(TempFileProvider.getScrapPath(context, ".temp" + string4 + string));
        string2 = new File(string3);
        string.delete();
        if (!string2.renameTo((File)string)) return null;
        return Uri.fromFile((File)string);
    }

    public int delete(Uri uri, String string, String[] arrstring) {
        return 0;
    }

    public String getType(Uri uri) {
        return "*/*";
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public boolean onCreate() {
        return true;
    }

    public ParcelFileDescriptor openFile(Uri uri, String string) throws FileNotFoundException {
        int n = sURLMatcher.match(uri);
        if (Log.isLoggable((String)TAG, (int)2)) {
            Log.d((String)TAG, (String)("openFile: uri=" + (Object)uri + ", mode=" + string));
        }
        switch (n) {
            default: {
                return null;
            }
            case 1: 
        }
        return this.getTempStoreFd();
    }

    public Cursor query(Uri uri, String[] arrstring, String string, String[] arrstring2, String string2) {
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String string, String[] arrstring) {
        return 0;
    }
}

