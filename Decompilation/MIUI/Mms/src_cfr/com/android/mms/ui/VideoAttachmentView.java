/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.BitmapFactory
 *  android.graphics.drawable.Drawable
 *  android.media.MediaMetadataRetriever
 *  android.net.Uri
 *  android.util.AttributeSet
 *  android.util.Log
 *  android.view.View
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.android.mms.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.android.mms.ui.SlideViewInterface;
import java.util.Map;

public class VideoAttachmentView
extends LinearLayout
implements SlideViewInterface {
    private ImageView mThumbnailView;

    public VideoAttachmentView(Context context) {
        super(context);
    }

    public VideoAttachmentView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static Bitmap createVideoThumbnail(Context context, Uri uri) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(context, uri);
        context = mediaMetadataRetriever.getFrameAtTime(-1);
        mediaMetadataRetriever.release();
        return context;
        {
            catch (RuntimeException runtimeException) {
                return context;
            }
        }
        catch (RuntimeException runtimeException) {
            try {
                mediaMetadataRetriever.release();
                return null;
            }
            catch (RuntimeException var0_2) {
                return null;
            }
        }
        catch (Throwable throwable) {
            try {
                mediaMetadataRetriever.release();
            }
            catch (RuntimeException var1_6) {
                throw throwable;
            }
            throw throwable;
        }
    }

    protected void onFinishInflate() {
        this.mThumbnailView = (ImageView)this.findViewById(2131820900);
    }

    @Override
    public void pauseAudio() {
    }

    @Override
    public void pauseVideo() {
    }

    @Override
    public void reset() {
    }

    @Override
    public void seekAudio(int n) {
    }

    @Override
    public void seekVideo(int n) {
    }

    @Override
    public void setAudio(Uri uri, String string2, Map<String, ?> map) {
    }

    @Override
    public void setAudioDuration(int n) {
    }

    @Override
    public void setImage(String string2, Drawable drawable2) {
    }

    @Override
    public void setImageRegionFit(String string2) {
    }

    @Override
    public void setImageVisibility(boolean bl) {
    }

    @Override
    public void setText(String string2, String string3) {
    }

    @Override
    public void setTextVisibility(boolean bl) {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void setVideo(String string2, Uri uri) {
        try {
            string2 = uri = VideoAttachmentView.createVideoThumbnail(this.mContext, uri);
            if (uri == null) {
                string2 = BitmapFactory.decodeResource((Resources)this.getResources(), (int)2130837760);
            }
            this.mThumbnailView.setImageBitmap((Bitmap)string2);
            return;
        }
        catch (OutOfMemoryError var1_2) {
            Log.e((String)"VideoAttachmentView", (String)"setVideo: out of memory: ", (Throwable)var1_2);
            return;
        }
    }

    @Override
    public void setVideoVisibility(boolean bl) {
    }

    @Override
    public void startAudio() {
    }

    @Override
    public void startVideo() {
    }

    @Override
    public void stopAudio() {
    }

    @Override
    public void stopVideo() {
    }
}

