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
import com.android.mms.model.RegionMediaModel;
import com.android.mms.model.RegionModel;
import com.google.android.mms.MmsException;
import org.w3c.dom.events.Event;

public class VideoModel
extends RegionMediaModel {
    public VideoModel(Context context, Uri uri, RegionModel regionModel) throws MmsException {
        this(context, null, null, uri, regionModel);
        this.initModelFromUri(uri);
        this.checkContentRestriction();
    }

    public VideoModel(Context context, String string, String string2, Uri uri, RegionModel regionModel) throws MmsException {
        super(context, "video", string, string2, uri, regionModel);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void initFromContentUri(Uri uri) throws MmsException {
        int n;
        Object object;
        block11 : {
            object = this.mContext.getContentResolver();
            Cursor cursor = SqliteWrapper.query((Context)this.mContext, (ContentResolver)object, (Uri)uri, (String[])null, (String)null, (String[])null, (String)null);
            if (cursor == null) throw new MmsException("Bad URI: " + (Object)uri);
            boolean bl = cursor.moveToFirst();
            if (!bl) throw new MmsException("Nothing found: " + (Object)uri);
            try {
                object = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
            }
            catch (IllegalArgumentException var4_4) {
                object = uri.toString();
            }
            this.setInternalSrc(object.substring(object.lastIndexOf(47) + 1));
            this.mContentType = "mms".equals((Object)uri.getHost()) ? cursor.getString(cursor.getColumnIndexOrThrow("ct")) : cursor.getString(cursor.getColumnIndexOrThrow("mime_type"));
            finally {
                cursor.close();
            }
            if (!TextUtils.isEmpty((CharSequence)this.mContentType)) break block11;
            throw new MmsException("Type of media is unknown.");
        }
        if (this.mContentType.equals((Object)"video/mp4") && !TextUtils.isEmpty((CharSequence)this.mSrc) && (n = this.mSrc.lastIndexOf(".")) != -1) {
            try {
                object = this.mSrc.substring(n + 1);
                if (!TextUtils.isEmpty((CharSequence)object) && (object.equalsIgnoreCase("3gp") || object.equalsIgnoreCase("3gpp") || object.equalsIgnoreCase("3g2"))) {
                    this.mContentType = "video/3gpp";
                }
            }
            catch (IndexOutOfBoundsException indexOutOfBoundsException) {}
        }
        if (!Log.isLoggable((String)"Mms:app", (int)2)) return;
        Log.v((String)"Mms/media", (String)("New VideoModel initFromContentUri created: mSrc=" + this.mSrc + " mContentType=" + this.mContentType + " mUri=" + (Object)uri));
    }

    /*
     * Enabled aggressive block sorting
     */
    private void initFromFile(Uri uri) {
        String string;
        this.setInternalSrc(uri.getPath());
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String string2 = string = MimeTypeMap.getFileExtensionFromUrl((String)this.mSrc);
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
            Log.v((String)"Mms/media", (String)("New VideoModel initFromFile created: mSrc=" + this.mSrc + " mContentType=" + this.mContentType + " mUri=" + (Object)uri));
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
        ContentRestrictionFactory.getContentRestriction().checkVideoContentType(this.mContentType);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void handleEvent(Event object) {
        String string = object.getType();
        if (Log.isLoggable((String)"Mms:app", (int)2)) {
            Log.v((String)"Mms/media", (String)("[VideoModel] handleEvent " + object.getType() + " on " + this));
        }
        Object object2 = MediaModel.MediaAction.NO_ACTIVE_ACTION;
        if (string.equals((Object)"SmilMediaStart")) {
            object2 = MediaModel.MediaAction.START;
            this.pauseMusicPlayer();
            this.mVisible = true;
        } else if (string.equals((Object)"SmilMediaEnd")) {
            object2 = object = MediaModel.MediaAction.STOP;
            if (this.mFill != 1) {
                this.mVisible = false;
                object2 = object;
            }
        } else if (string.equals((Object)"SmilMediaPause")) {
            object2 = MediaModel.MediaAction.PAUSE;
            this.mVisible = true;
        } else if (string.equals((Object)"SmilMediaSeek")) {
            object2 = MediaModel.MediaAction.SEEK;
            this.mSeekTo = ((EventImpl)object).getSeekTo();
            this.mVisible = true;
        }
        this.appendAction((MediaModel.MediaAction)((Object)object2));
        this.notifyModelChanged(false);
    }

    @Override
    protected boolean isPlayable() {
        return true;
    }
}

