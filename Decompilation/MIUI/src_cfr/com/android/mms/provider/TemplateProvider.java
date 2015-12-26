/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentProvider
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.UriMatcher
 *  android.database.Cursor
 *  android.database.MatrixCursor
 *  android.net.Uri
 *  android.os.ParcelFileDescriptor
 *  android.util.Log
 *  java.io.File
 *  java.io.FileNotFoundException
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.android.mms.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.android.mms.ui.MessageUtils;
import java.io.File;
import java.io.FileNotFoundException;

public class TemplateProvider
extends ContentProvider {
    private static final String[] COLUMNS;
    private static final UriMatcher sURLMatcher;

    static {
        sURLMatcher = new UriMatcher(-1);
        COLUMNS = new String[]{"allowed"};
        sURLMatcher.addURI("msg-template", "downloads", 1);
        sURLMatcher.addURI("msg-template", "version", 2);
        sURLMatcher.addURI("msg-template", "updated", 3);
        sURLMatcher.addURI("msg-template", "allowed", 4);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private ParcelFileDescriptor getFileFD(String string) {
        Object var2_2 = null;
        try {
            File file = new File(string);
            File file2 = file.getParentFile();
            if (!file2.exists() && !file2.mkdirs()) {
                Log.e((String)"TemplateProvider", (String)("[TemplateProvider] getFileFD: " + file2.getPath() + "does not exist!"));
                return null;
            }
            string = file = ParcelFileDescriptor.open((File)file, (int)939524096);
        }
        catch (Exception var3_4) {
            Log.e((String)"TemplateProvider", (String)("getFileFD: error creating pfd for " + string), (Throwable)var3_4);
            string = var2_2;
        }
        Log.v((String)"TemplateProvider", (String)"getTempStoreFd success!");
        return string;
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
        switch (sURLMatcher.match(uri)) {
            default: {
                Log.e((String)"TemplateProvider", (String)(" Unsupported uri: " + (Object)uri));
                return null;
            }
            case 1: {
                return this.getFileFD("/data/data/com.android.mms/app_understand/downloads.tmp");
            }
            case 2: {
                return this.getFileFD("/data/data/com.android.mms/app_understand/version");
            }
            case 3: 
        }
        return this.getFileFD("/data/data/com.android.mms/app_understand/understand.zip");
    }

    /*
     * Enabled aggressive block sorting
     */
    public Cursor query(Uri uri, String[] arrstring, String string, String[] arrstring2, String string2) {
        int n = 1;
        switch (sURLMatcher.match(uri)) {
            default: {
                return null;
            }
            case 4: 
        }
        uri = new MatrixCursor(COLUMNS);
        if (!MessageUtils.isMessagingTemplateAllowed(this.getContext())) {
            n = 0;
        }
        uri.addRow(new Object[]{n});
        return uri;
    }

    public int update(Uri uri, ContentValues contentValues, String string, String[] arrstring) {
        return 0;
    }
}

