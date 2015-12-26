/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.database.Cursor
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.text.TextUtils
 *  android.util.Log
 *  android.webkit.MimeTypeMap
 *  com.google.android.mms.MmsException
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 */
package com.android.mms.model;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.android.mms.ContentRestrictionException;
import com.android.mms.dom.events.EventImpl;
import com.android.mms.model.ContentRestrictionFactory;
import com.android.mms.model.MediaModel;
import com.google.android.mms.MmsException;
import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.events.Event;

public class AudioModel
extends MediaModel {
    private final HashMap<String, String> mExtras = new HashMap();

    public AudioModel(Context context, Uri uri) throws MmsException {
        this(context, null, null, uri);
        this.initModelFromUri(uri);
        this.checkContentRestriction();
    }

    public AudioModel(Context context, String string, String string2, Uri uri) throws MmsException {
        super(context, "audio", string, string2, uri);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void initFromContentUri(Uri object) throws MmsException {
        Object object2 = this.mContext.getContentResolver();
        Cursor cursor = SqliteWrapper.query((Context)this.mContext, (ContentResolver)object2, (Uri)object, (String[])null, (String)null, (String[])null, (String)null);
        if (cursor == null) {
            throw new MmsException("Bad URI: " + object);
        }
        try {
            if (!cursor.moveToFirst()) throw new MmsException("Nothing found: " + object);
            if (AudioModel.isMmsUri((Uri)object)) {
                object = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
                this.mContentType = cursor.getString(cursor.getColumnIndexOrThrow("ct"));
            } else {
                object2 = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
                this.mContentType = cursor.getString(cursor.getColumnIndexOrThrow("mime_type"));
                object = cursor.getString(cursor.getColumnIndexOrThrow("album"));
                if (!TextUtils.isEmpty((CharSequence)object)) {
                    this.mExtras.put((Object)"album", object);
                }
                String string = cursor.getString(cursor.getColumnIndexOrThrow("artist"));
                object = object2;
                if (!TextUtils.isEmpty((CharSequence)string)) {
                    this.mExtras.put((Object)"artist", (Object)string);
                    object = object2;
                }
            }
            this.setInternalSrc(object.substring(object.lastIndexOf(47) + 1));
            if (!TextUtils.isEmpty((CharSequence)this.mContentType)) return;
            {
                throw new MmsException("Type of media is unknown.");
            }
        }
        finally {
            cursor.close();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void initFromFile(Uri uri) {
        String string;
        String string2 = string = uri.getPath();
        if (string != null) {
            string2 = string.substring(string.lastIndexOf(47) + 1);
        }
        this.setInternalSrc(string2);
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        string2 = string = MimeTypeMap.getFileExtensionFromUrl((String)this.mSrc);
        if (TextUtils.isEmpty((CharSequence)string)) {
            int n = this.mSrc.lastIndexOf(46);
            string2 = string;
            if (n >= 0) {
                string2 = this.mSrc.substring(n + 1);
            }
        }
        string2 = string2 == null ? null : mimeTypeMap.getMimeTypeFromExtension(string2.toLowerCase());
        this.mContentType = string2;
        if (Log.isLoggable((String)"Mms:app", (int)2)) {
            Log.v((String)"Mms/media", (String)("New AudioModel initFromFile created: mSrc=" + this.mSrc + " mContentType=" + this.mContentType + " mUri=" + (Object)uri));
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void initModelFromUri(Uri uri) throws MmsException {
        if (uri.getScheme().equals((Object)"content")) {
            this.initFromContentUri(uri);
        } else if (uri.getScheme().equals((Object)"file")) {
            this.initFromFile(uri);
        }
        this.initMediaDuration();
    }

    protected void checkContentRestriction() throws ContentRestrictionException {
        ContentRestrictionFactory.getContentRestriction().checkAudioContentType(this.mContentType);
    }

    public Map<String, ?> getExtras() {
        return this.mExtras;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void handleEvent(Event event) {
        String string = event.getType();
        MediaModel.MediaAction mediaAction = MediaModel.MediaAction.NO_ACTIVE_ACTION;
        if (string.equals((Object)"SmilMediaStart")) {
            mediaAction = MediaModel.MediaAction.START;
            this.pauseMusicPlayer();
        } else if (string.equals((Object)"SmilMediaEnd")) {
            mediaAction = MediaModel.MediaAction.STOP;
        } else if (string.equals((Object)"SmilMediaPause")) {
            mediaAction = MediaModel.MediaAction.PAUSE;
        } else if (string.equals((Object)"SmilMediaSeek")) {
            mediaAction = MediaModel.MediaAction.SEEK;
            this.mSeekTo = ((EventImpl)event).getSeekTo();
        }
        this.appendAction(mediaAction);
        this.notifyModelChanged(false);
    }

    @Override
    protected boolean isPlayable() {
        return true;
    }
}

