/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.drawable.BitmapDrawable
 *  android.graphics.drawable.Drawable
 *  android.media.MediaMetadataRetriever
 *  android.net.Uri
 *  android.text.TextUtils
 *  android.text.method.HideReturnsTransformationMethod
 *  android.text.method.TransformationMethod
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
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.mms.ui.SlideViewInterface;
import com.android.mms.ui.ThumbnailView;
import java.util.Map;

public class SlideListItemView
extends LinearLayout
implements SlideViewInterface {
    private ImageView mAttachmentIcon;
    private TextView mAttachmentName;
    private ThumbnailView mImagePreview;
    private int mImagePreviewSize;
    private ImageView mPlayIcon;
    private TextView mTextPreview;

    public SlideListItemView(Context context) {
        super(context);
    }

    public SlideListItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        this.mTextPreview = (TextView)this.findViewById(2131820884);
        this.mTextPreview.setTransformationMethod((TransformationMethod)HideReturnsTransformationMethod.getInstance());
        this.mImagePreview = (ThumbnailView)this.findViewById(2131820881);
        this.mPlayIcon = (ImageView)this.findViewById(2131820619);
        this.mAttachmentName = (TextView)this.findViewById(2131820885);
        this.mAttachmentIcon = (ImageView)this.findViewById(2131820886);
        this.mImagePreviewSize = this.getResources().getDimensionPixelSize(2131427437);
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
        if (string2 != null) {
            this.mAttachmentName.setText((CharSequence)string2);
            this.mAttachmentName.setVisibility(0);
            this.mAttachmentIcon.setImageResource(2130837764);
            this.mAttachmentIcon.setVisibility(0);
            return;
        }
        this.mAttachmentName.setText((CharSequence)"");
        this.mAttachmentName.setVisibility(8);
        this.mAttachmentIcon.setImageDrawable(null);
        this.mAttachmentIcon.setVisibility(8);
    }

    @Override
    public void setAudioDuration(int n) {
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    @Override
    public void setImage(String var1_1, Drawable var2_3) {
        var1_1 = var2_3;
        if (var2_3 != null) ** GOTO lbl5
        try {
            var1_1 = this.getResources().getDrawable(2130837759);
lbl5: // 2 sources:
            this.mImagePreview.setImageDrawable(this.getResources().getDrawable(2130837978), (Drawable)var1_1, this.mImagePreviewSize, this.mImagePreviewSize);
            return;
        }
        catch (OutOfMemoryError var1_2) {
            Log.e((String)"SlideListItemView", (String)"setImage: out of memory: ", (Throwable)var1_2);
            return;
        }
    }

    @Override
    public void setImageRegionFit(String string2) {
    }

    @Override
    public void setImageVisibility(boolean bl) {
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void setText(String string2, String string3) {
        this.mTextPreview.setText((CharSequence)string3);
        string2 = this.mTextPreview;
        int n = TextUtils.isEmpty((CharSequence)string3) ? 8 : 0;
        string2.setVisibility(n);
    }

    @Override
    public void setTextVisibility(boolean bl) {
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    @Override
    public void setVideo(String string2, Uri uri) {
        if (string2 != null) {
            this.mAttachmentName.setText((CharSequence)string2);
            this.mAttachmentName.setVisibility(0);
            this.mAttachmentIcon.setImageResource(2130837878);
            this.mAttachmentIcon.setVisibility(0);
        } else {
            this.mAttachmentName.setText((CharSequence)"");
            this.mAttachmentName.setVisibility(8);
            this.mAttachmentIcon.setImageDrawable(null);
            this.mAttachmentIcon.setVisibility(8);
        }
        string2 = new MediaMetadataRetriever();
        string2.setDataSource(this.getContext(), uri);
        this.mImagePreview.setImageDrawable(this.getResources().getDrawable(2130837978), (Drawable)new BitmapDrawable(this.getResources(), string2.getFrameAtTime(-1)), this.mImagePreviewSize, this.mImagePreviewSize);
        this.mPlayIcon.setVisibility(0);
        string2.release();
        return;
        {
            catch (RuntimeException runtimeException) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            try {
                string2.release();
                return;
            }
            catch (RuntimeException var1_2) {
                return;
            }
        }
        catch (Throwable throwable) {
            try {
                string2.release();
            }
            catch (RuntimeException var1_4) {
                throw throwable;
            }
            throw throwable;
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

