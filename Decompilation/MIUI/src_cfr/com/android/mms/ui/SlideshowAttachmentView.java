/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.graphics.drawable.Drawable
 *  android.media.MediaMetadataRetriever
 *  android.net.Uri
 *  android.util.AttributeSet
 *  android.util.Log
 *  android.view.View
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.android.mms.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.SlideViewInterface;
import java.util.Map;

public class SlideshowAttachmentView
extends LinearLayout
implements SlideViewInterface {
    private ImageView mImageView;
    private TextView mTextView;

    public SlideshowAttachmentView(Context context) {
        super(context);
    }

    public SlideshowAttachmentView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        this.mImageView = (ImageView)this.findViewById(2131820878);
        this.mTextView = (TextView)this.findViewById(2131820879);
    }

    @Override
    public void pauseAudio() {
    }

    @Override
    public void pauseVideo() {
    }

    @Override
    public void reset() {
        this.mImageView.setImageURI(null);
        this.mTextView.setText((CharSequence)"");
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
        MessageUtils.setAttachmentImage(this.mImageView, drawable2, true);
    }

    @Override
    public void setImageRegionFit(String string2) {
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void setImageVisibility(boolean bl) {
        ImageView imageView = this.mImageView;
        int n = bl ? 0 : 4;
        imageView.setVisibility(n);
    }

    @Override
    public void setText(String string2, String string3) {
        this.mTextView.setText((CharSequence)string3);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void setTextVisibility(boolean bl) {
        TextView textView = this.mTextView;
        int n = bl ? 0 : 4;
        textView.setVisibility(n);
    }

    @Override
    public void setVideo(String string2, Uri uri) {
        string2 = new MediaMetadataRetriever();
        try {
            string2.setDataSource(this.getContext(), uri);
            this.mImageView.setImageBitmap(string2.getFrameAtTime(-1));
            return;
        }
        catch (RuntimeException var2_3) {
            Log.e((String)"SlideshowAttachmentView", (String)"Unexpected RuntimeException.", (Throwable)var2_3);
            return;
        }
        finally {
            string2.release();
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

