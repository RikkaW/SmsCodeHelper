/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.media.MediaMetadataRetriever
 *  android.net.Uri
 *  android.util.Log
 *  com.google.android.mms.MmsException
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.ArrayList
 */
package com.android.mms.model;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.util.Log;
import com.android.mms.model.Model;
import com.google.android.mms.MmsException;
import java.util.ArrayList;
import org.w3c.dom.events.EventListener;

public abstract class MediaModel
extends Model
implements EventListener {
    protected int mBegin;
    protected String mContentType;
    protected Context mContext;
    private byte[] mData;
    protected int mDuration;
    protected short mFill;
    private final ArrayList<MediaAction> mMediaActions;
    protected boolean mMediaResizeable;
    protected int mSeekTo;
    protected int mSize;
    protected String mSrc;
    protected String mTag;
    private Uri mUri;

    public MediaModel(Context context, String string, String string2, String string3, Uri uri) throws MmsException {
        this.mContext = context;
        this.mTag = string;
        this.mContentType = string2;
        this.mUri = uri;
        this.setInternalSrc(string3);
        this.initMediaSize();
        this.mMediaActions = new ArrayList();
    }

    public MediaModel(Context context, String string, String string2, String string3, byte[] arrby) {
        if (arrby == null) {
            throw new IllegalArgumentException("data may not be null.");
        }
        this.mContext = context;
        this.mTag = string;
        this.mContentType = string2;
        this.mData = arrby;
        this.mSize = arrby.length;
        this.mMediaActions = new ArrayList();
        this.setInternalSrc(string3);
    }

    /*
     * Exception decompiling
     */
    private void initMediaSize() throws MmsException {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [12[UNCONDITIONALDOLOOP]], but top level block is 2[TRYBLOCK]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }

    public static boolean isMmsUri(Uri uri) {
        return uri.getAuthority().startsWith("mms");
    }

    public void appendAction(MediaAction mediaAction) {
        this.mMediaActions.add((Object)mediaAction);
    }

    public int getBegin() {
        return this.mBegin;
    }

    public String getContentType() {
        return this.mContentType;
    }

    public MediaAction getCurrentAction() {
        if (this.mMediaActions.size() == 0) {
            return MediaAction.NO_ACTIVE_ACTION;
        }
        return (MediaAction)((Object)this.mMediaActions.remove(0));
    }

    public byte[] getData() {
        if (this.mData != null) {
            byte[] arrby = new byte[this.mData.length];
            System.arraycopy((Object)this.mData, (int)0, (Object)arrby, (int)0, (int)this.mData.length);
            return arrby;
        }
        return null;
    }

    public int getDuration() {
        return this.mDuration;
    }

    public boolean getMediaResizable() {
        return this.mMediaResizeable;
    }

    public int getMediaSize() {
        return this.mSize;
    }

    public int getSeekTo() {
        return this.mSeekTo;
    }

    public String getSrc() {
        return this.mSrc;
    }

    public Uri getUri() {
        return this.mUri;
    }

    protected void initMediaDuration() throws MmsException {
        if (this.mUri == null) {
            throw new IllegalArgumentException("Uri may not be null.");
        }
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        int n = 0;
        try {
            mediaMetadataRetriever.setDataSource(this.mContext, this.mUri);
            String string = mediaMetadataRetriever.extractMetadata(9);
            if (string != null) {
                n = Integer.parseInt((String)string);
            }
            this.mDuration = n;
            return;
        }
        catch (Exception var3_4) {
            Log.e((String)"Mms/media", (String)("MediaMetadataRetriever failed to get duration for " + this.mUri.getPath()), (Throwable)var3_4);
            throw new MmsException((Throwable)var3_4);
        }
        finally {
            mediaMetadataRetriever.release();
        }
    }

    public boolean isAudio() {
        return this.mTag.equals((Object)"audio");
    }

    public boolean isImage() {
        return this.mTag.equals((Object)"img");
    }

    protected boolean isPlayable() {
        return false;
    }

    public boolean isText() {
        return this.mTag.equals((Object)"text");
    }

    public boolean isVideo() {
        return this.mTag.equals((Object)"video");
    }

    protected void pauseMusicPlayer() {
        if (Log.isLoggable((String)"Mms:app", (int)2)) {
            Log.d((String)"Mms/media", (String)"pauseMusicPlayer");
        }
        Intent intent = new Intent("com.android.music.musicservicecommand");
        intent.putExtra("command", "pause");
        this.mContext.sendBroadcast(intent);
    }

    protected void resizeMedia(int n, long l) throws MmsException {
    }

    public void setBegin(int n) {
        this.mBegin = n;
        this.notifyModelChanged(true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void setDuration(int n) {
        if (this.isPlayable() && n < 0) {
            try {
                this.initMediaDuration();
            }
            catch (MmsException var2_2) {
                Log.e((String)"Mms/media", (String)var2_2.getMessage(), (Throwable)var2_2);
                return;
            }
        } else {
            this.mDuration = n;
        }
        this.notifyModelChanged(true);
    }

    public void setFill(short s) {
        this.mFill = s;
        this.notifyModelChanged(true);
    }

    void setInternalSrc(String string) {
        if (string != null) {
            this.mSrc = string.replace((CharSequence)" ", (CharSequence)"_");
        }
    }

    void setUri(Uri uri) {
        this.mUri = uri;
    }

    public static enum MediaAction {
        NO_ACTIVE_ACTION,
        START,
        STOP,
        PAUSE,
        SEEK;
        

        private MediaAction() {
        }
    }

}

