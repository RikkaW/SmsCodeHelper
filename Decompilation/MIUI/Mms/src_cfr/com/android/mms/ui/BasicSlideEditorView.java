/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.BitmapFactory
 *  android.graphics.drawable.Drawable
 *  android.net.Uri
 *  android.text.Editable
 *  android.text.TextWatcher
 *  android.util.AttributeSet
 *  android.util.Log
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.EditText
 *  android.widget.ImageView
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.android.mms.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.SlideViewInterface;
import com.android.mms.ui.VideoAttachmentView;
import java.util.Map;

public class BasicSlideEditorView
extends RelativeLayout
implements SlideViewInterface {
    private TextView mAudioNameView;
    private TextView mAudioTimeView;
    private View mAudioView;
    private EditText mEditText;
    private ImageView mImageView;
    private View.OnClickListener mImageViewOnClickListener;
    private OnTextChangedListener mOnTextChangedListener;
    private boolean mOnTextChangedListenerEnabled = true;
    private ImageView mPlayIcon;

    public BasicSlideEditorView(Context context) {
        super(context);
    }

    public BasicSlideEditorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onFinishInflate() {
        this.mImageView = (ImageView)this.findViewById(2131820618);
        this.mImageView.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (BasicSlideEditorView.this.mImageViewOnClickListener != null) {
                    BasicSlideEditorView.this.mImageViewOnClickListener.onClick(view);
                }
            }
        });
        this.mPlayIcon = (ImageView)this.findViewById(2131820619);
        this.mAudioView = this.findViewById(2131820620);
        this.mAudioNameView = (TextView)this.findViewById(2131820621);
        this.mAudioTimeView = (TextView)this.findViewById(2131820622);
        this.mEditText = (EditText)this.findViewById(2131820623);
        this.mEditText.addTextChangedListener(new TextWatcher(){

            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int n, int n2, int n3) {
            }

            public void onTextChanged(CharSequence charSequence, int n, int n2, int n3) {
                if (BasicSlideEditorView.this.mOnTextChangedListenerEnabled && BasicSlideEditorView.this.mOnTextChangedListener != null) {
                    BasicSlideEditorView.this.mOnTextChangedListener.onTextChanged(charSequence.toString());
                }
            }
        });
    }

    @Override
    public void pauseAudio() {
    }

    @Override
    public void pauseVideo() {
    }

    @Override
    public void reset() {
        this.mImageView.setImageResource(2130837977);
        this.mPlayIcon.setVisibility(8);
        this.mAudioView.setVisibility(8);
        this.mOnTextChangedListenerEnabled = false;
        this.mEditText.setText((CharSequence)"");
        this.mOnTextChangedListenerEnabled = true;
    }

    @Override
    public void seekAudio(int n) {
    }

    @Override
    public void seekVideo(int n) {
    }

    @Override
    public void setAudio(Uri uri, String string2, Map<String, ?> map) {
        this.mAudioView.setVisibility(0);
        this.mAudioNameView.setText((CharSequence)string2);
    }

    @Override
    public void setAudioDuration(int n) {
        int n2 = n;
        if (n <= 0) {
            n2 = 1;
        }
        this.mAudioTimeView.setText((CharSequence)this.mContext.getResources().getQuantityString(2131623938, n2, new Object[]{n2}));
    }

    @Override
    public void setImage(String string2, Drawable drawable) {
        try {
            MessageUtils.setAttachmentImage(this.mImageView, drawable, true);
            return;
        }
        catch (OutOfMemoryError var1_2) {
            Log.e((String)"BasicSlideEditorView", (String)"setImage: out of memory: ", (Throwable)var1_2);
            return;
        }
    }

    @Override
    public void setImageRegionFit(String string2) {
    }

    public void setImageViewOnClickListener(View.OnClickListener onClickListener) {
        this.mImageViewOnClickListener = onClickListener;
    }

    @Override
    public void setImageVisibility(boolean bl) {
    }

    public void setOnTextChangedListener(OnTextChangedListener onTextChangedListener) {
        this.mOnTextChangedListener = onTextChangedListener;
    }

    @Override
    public void setText(String string2, String string3) {
        this.mOnTextChangedListenerEnabled = false;
        if (string3 != null && !string3.equals((Object)this.mEditText.getText().toString())) {
            this.mEditText.setText((CharSequence)string3);
            this.mEditText.setSelection(string3.length());
        }
        this.mOnTextChangedListenerEnabled = true;
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
            string2 = VideoAttachmentView.createVideoThumbnail(this.mContext, uri);
            if (string2 == null) {
                string2 = BitmapFactory.decodeResource((Resources)this.getResources(), (int)2130837760);
                this.mImageView.setImageBitmap((Bitmap)string2);
                this.mPlayIcon.setVisibility(8);
                return;
            }
            this.mImageView.setImageBitmap((Bitmap)string2);
            this.mPlayIcon.setVisibility(0);
            return;
        }
        catch (OutOfMemoryError var1_2) {
            Log.e((String)"BasicSlideEditorView", (String)"setVideo: out of memory: ", (Throwable)var1_2);
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

    public static interface OnTextChangedListener {
        public void onTextChanged(String var1);
    }

}

