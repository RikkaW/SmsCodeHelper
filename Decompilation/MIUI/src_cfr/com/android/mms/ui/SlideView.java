/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.media.MediaPlayer
 *  android.media.MediaPlayer$OnPreparedListener
 *  android.net.Uri
 *  android.text.method.HideReturnsTransformationMethod
 *  android.text.method.TransformationMethod
 *  android.util.AttributeSet
 *  android.util.Log
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.AbsoluteLayout
 *  android.widget.AbsoluteLayout$LayoutParams
 *  android.widget.FrameLayout
 *  android.widget.FrameLayout$LayoutParams
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.MediaController
 *  android.widget.ScrollView
 *  android.widget.TextView
 *  android.widget.VideoView
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.Comparator
 */
package com.android.mms.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;
import com.android.mms.layout.LayoutManager;
import com.android.mms.layout.LayoutParameters;
import com.android.mms.ui.AdaptableSlideViewInterface;
import com.android.mms.ui.MessageUtils;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class SlideView
extends AbsoluteLayout
implements AdaptableSlideViewInterface {
    private View mAudioInfoView;
    private MediaPlayer mAudioPlayer;
    private boolean mConformanceMode;
    private ImageView mImageView;
    private boolean mIsPrepared;
    private MediaController mMediaController;
    MediaPlayer.OnPreparedListener mPreparedListener;
    private ScrollView mScrollText;
    private ScrollView mScrollViewPort;
    private int mSeekWhenPrepared;
    private AdaptableSlideViewInterface.OnSizeChangedListener mSizeChangedListener;
    private boolean mStartWhenPrepared;
    private boolean mStopWhenPrepared;
    private float mTextSize = 0.0f;
    private TextView mTextView;
    private VideoView mVideoView;
    private LinearLayout mViewPort;

    public SlideView(Context context) {
        super(context);
        this.mPreparedListener = new MediaPlayer.OnPreparedListener(){

            public void onPrepared(MediaPlayer mediaPlayer) {
                SlideView.this.mIsPrepared = true;
                if (SlideView.this.mSeekWhenPrepared > 0) {
                    SlideView.this.mAudioPlayer.seekTo(SlideView.this.mSeekWhenPrepared);
                    SlideView.this.mSeekWhenPrepared = 0;
                }
                if (SlideView.this.mStartWhenPrepared) {
                    SlideView.this.mAudioPlayer.start();
                    SlideView.this.mStartWhenPrepared = false;
                    SlideView.this.displayAudioInfo();
                }
                if (SlideView.this.mStopWhenPrepared) {
                    SlideView.this.mAudioPlayer.stop();
                    SlideView.this.mAudioPlayer.release();
                    SlideView.this.mAudioPlayer = null;
                    SlideView.this.mStopWhenPrepared = false;
                    SlideView.this.hideAudioInfo();
                }
            }
        };
    }

    public SlideView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPreparedListener = new ;
    }

    private void displayAudioInfo() {
        if (this.mAudioInfoView != null) {
            this.mAudioInfoView.setVisibility(0);
        }
    }

    private void hideAudioInfo() {
        if (this.mAudioInfoView != null) {
            this.mAudioInfoView.setVisibility(8);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void initAudioInfoView(String string2) {
        if (this.mAudioInfoView == null) {
            this.mAudioInfoView = LayoutInflater.from((Context)this.getContext()).inflate(2130968689, null);
            this.mAudioInfoView.getHeight();
            if (this.mConformanceMode) {
                this.mViewPort.addView(this.mAudioInfoView, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, 82));
            } else {
                this.addView(this.mAudioInfoView, (ViewGroup.LayoutParams)new AbsoluteLayout.LayoutParams(-1, 82, 0, this.getHeight() - 82));
            }
        }
        ((TextView)this.mAudioInfoView.findViewById(2131820846)).setText((CharSequence)string2);
        this.mAudioInfoView.setVisibility(8);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void enableMMSConformanceMode(int n, int n2, int n3, int n4) {
        this.mConformanceMode = true;
        if (this.mScrollViewPort == null) {
            this.mScrollViewPort = new ScrollView(this.mContext){
                private int mBottomY;

                protected void onLayout(boolean bl, int n, int n2, int n3, int n4) {
                    int n5 = 0;
                    super.onLayout(bl, n, n2, n3, n4);
                    if (this.getChildCount() > 0) {
                        n2 = this.getChildAt(0).getHeight();
                        n3 = this.getHeight();
                        n = n5;
                        if (n3 < n2) {
                            n = n2 - n3;
                        }
                        this.mBottomY = n;
                    }
                }

                protected void onScrollChanged(int n, int n2, int n3, int n4) {
                    if ((n2 == 0 || n2 >= this.mBottomY) && SlideView.this.mMediaController != null) {
                        SlideView.this.mMediaController.show();
                    }
                }
            };
            this.mScrollViewPort.setScrollBarStyle(50331648);
            this.mViewPort = new LinearLayout(this.mContext);
            this.mViewPort.setOrientation(1);
            this.mViewPort.setGravity(17);
            this.mViewPort.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    if (SlideView.this.mMediaController != null) {
                        SlideView.this.mMediaController.show();
                    }
                }
            });
            this.mScrollViewPort.addView((View)this.mViewPort, (ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, -2));
            this.addView((View)this.mScrollViewPort);
        }
        Object object = new TreeMap(new Comparator<Position>(){

            public int compare(Position position, Position position2) {
                int n;
                int n2 = position.mLeft;
                int n3 = position.mTop;
                int n4 = position2.mLeft;
                n3 = n = n3 - position2.mTop;
                if (n == 0) {
                    n3 = n2 - n4;
                }
                n = n3;
                if (n3 == 0) {
                    n = -1;
                }
                return n;
            }
        });
        if (n >= 0 && n2 >= 0) {
            this.mTextView = new TextView(this.mContext);
            this.mTextView.setTransformationMethod((TransformationMethod)HideReturnsTransformationMethod.getInstance());
            this.mTextView.setTextSize(0, this.mTextSize);
            this.mTextView.setPadding(5, 5, 5, 5);
            object.put(new Position(n, n2), this.mTextView);
        }
        if (n3 >= 0 && n4 >= 0) {
            this.mImageView = new ImageView(this.mContext);
            this.mImageView.setPadding(0, 5, 0, 5);
            object.put(new Position(n3, n4), this.mImageView);
            this.mVideoView = new VideoView(this.mContext);
            object.put(new Position(n3 + 1, n4), this.mVideoView);
        }
        object = object.values().iterator();
        while (object.hasNext()) {
            View view = (View)object.next();
            if (view instanceof VideoView) {
                this.mViewPort.addView(view, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, LayoutManager.getInstance().getLayoutParameters().getHeight()));
            } else {
                this.mViewPort.addView(view, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
            }
            view.setVisibility(8);
        }
        return;
    }

    protected void onSizeChanged(int n, int n2, int n3, int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        if (this.mSizeChangedListener != null) {
            this.mSizeChangedListener.onSizeChanged(n, n2 - 82);
        }
    }

    @Override
    public void pauseAudio() {
        if (this.mAudioPlayer != null && this.mIsPrepared && this.mAudioPlayer.isPlaying()) {
            this.mAudioPlayer.pause();
        }
        this.mStartWhenPrepared = false;
    }

    @Override
    public void pauseVideo() {
        if (this.mVideoView != null) {
            this.mVideoView.pause();
        }
    }

    @Override
    public void reset() {
        if (this.mScrollText != null) {
            this.mScrollText.setVisibility(8);
        }
        if (this.mImageView != null) {
            this.mImageView.setVisibility(8);
        }
        if (this.mAudioPlayer != null) {
            this.stopAudio();
        }
        if (this.mVideoView != null) {
            this.stopVideo();
            this.mVideoView.setVisibility(8);
        }
        if (this.mTextView != null) {
            this.mTextView.setVisibility(8);
        }
        if (this.mScrollViewPort != null) {
            this.mScrollViewPort.scrollTo(0, 0);
            this.mScrollViewPort.setLayoutParams((ViewGroup.LayoutParams)new AbsoluteLayout.LayoutParams(-1, -1, 0, 0));
        }
    }

    @Override
    public void seekAudio(int n) {
        if (this.mAudioPlayer != null && this.mIsPrepared) {
            this.mAudioPlayer.seekTo(n);
            return;
        }
        this.mSeekWhenPrepared = n;
    }

    @Override
    public void seekVideo(int n) {
        if (this.mVideoView != null && n > 0) {
            this.mVideoView.seekTo(n);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void setAudio(Uri uri, String string2, Map<String, ?> map) {
        if (uri == null) {
            throw new IllegalArgumentException("Audio URI may not be null.");
        }
        if (this.mAudioPlayer != null) {
            this.mAudioPlayer.reset();
            this.mAudioPlayer.release();
            this.mAudioPlayer = null;
        }
        this.mIsPrepared = false;
        this.mStartWhenPrepared = false;
        this.mSeekWhenPrepared = 0;
        this.mStopWhenPrepared = false;
        try {
            this.mAudioPlayer = new MediaPlayer();
            this.mAudioPlayer.setOnPreparedListener(this.mPreparedListener);
            this.mAudioPlayer.setDataSource(this.mContext, uri);
            this.mAudioPlayer.prepareAsync();
        }
        catch (IOException var1_2) {
            Log.e((String)"SlideView", (String)"Unexpected IOException.", (Throwable)var1_2);
            this.mAudioPlayer.release();
            this.mAudioPlayer = null;
        }
        this.initAudioInfoView(string2);
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
        if (this.mImageView == null) {
            this.mImageView = new ImageView(this.mContext);
            this.mImageView.setPadding(0, 5, 0, 5);
            this.addView((View)this.mImageView, (ViewGroup.LayoutParams)new AbsoluteLayout.LayoutParams(-2, -2, 0, 0));
        }
        var1_1 = var2_3;
        if (var2_3 != null) ** GOTO lbl9
        try {
            var1_1 = this.getResources().getDrawable(2130837759);
lbl9: // 2 sources:
            this.mImageView.setVisibility(0);
            MessageUtils.setAttachmentImage(this.mImageView, (Drawable)var1_1, true);
            return;
        }
        catch (OutOfMemoryError var1_2) {
            Log.e((String)"SlideView", (String)"setImage: out of memory: ", (Throwable)var1_2);
            return;
        }
    }

    @Override
    public void setImageRegion(int n, int n2, int n3, int n4) {
        if (this.mImageView != null && !this.mConformanceMode) {
            this.mImageView.setLayoutParams((ViewGroup.LayoutParams)new AbsoluteLayout.LayoutParams(n3, n4, n, n2));
        }
    }

    @Override
    public void setImageRegionFit(String string2) {
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void setImageVisibility(boolean bl) {
        int n = 0;
        int n2 = 0;
        if (this.mImageView == null) return;
        if (this.mConformanceMode) {
            ImageView imageView = this.mImageView;
            if (!bl) {
                n2 = 8;
            }
            imageView.setVisibility(n2);
            return;
        }
        ImageView imageView = this.mImageView;
        n2 = bl ? n : 4;
        imageView.setVisibility(n2);
    }

    public void setMediaController(MediaController mediaController) {
        this.mMediaController = mediaController;
    }

    @Override
    public void setOnSizeChangedListener(AdaptableSlideViewInterface.OnSizeChangedListener onSizeChangedListener) {
        this.mSizeChangedListener = onSizeChangedListener;
    }

    @Override
    public void setText(String string2, String string3) {
        if (!this.mConformanceMode) {
            if (this.mScrollText == null) {
                this.mScrollText = new ScrollView(this.mContext);
                this.mScrollText.setScrollBarStyle(50331648);
                this.addView((View)this.mScrollText, (ViewGroup.LayoutParams)new AbsoluteLayout.LayoutParams(-2, -2, 0, 0));
            }
            if (this.mTextView == null) {
                this.mTextView = new TextView(this.mContext);
                this.mTextView.setTransformationMethod((TransformationMethod)HideReturnsTransformationMethod.getInstance());
                this.mScrollText.addView((View)this.mTextView);
            }
            this.mScrollText.requestFocus();
        }
        this.mTextView.setTextSize(0, this.mTextSize);
        this.mTextView.setVisibility(0);
        this.mTextView.setText((CharSequence)string3);
    }

    @Override
    public void setTextRegion(int n, int n2, int n3, int n4) {
        if (this.mScrollText != null && !this.mConformanceMode) {
            this.mScrollText.setLayoutParams((ViewGroup.LayoutParams)new AbsoluteLayout.LayoutParams(n3, n4, n, n2));
        }
    }

    public void setTextSize(float f2) {
        this.mTextSize = f2;
        if (this.mTextView != null) {
            this.mTextView.setTextSize(0, this.mTextSize);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void setTextVisibility(boolean bl) {
        ScrollView scrollView;
        int n = 0;
        int n2 = 0;
        if (this.mConformanceMode) {
            if (this.mTextView == null) return;
            {
                TextView textView = this.mTextView;
                if (!bl) {
                    n2 = 8;
                }
                textView.setVisibility(n2);
                return;
            }
        } else {
            if (this.mScrollText == null) return;
            {
                scrollView = this.mScrollText;
                n2 = bl ? n : 4;
            }
        }
        scrollView.setVisibility(n2);
    }

    @Override
    public void setVideo(String string2, Uri uri) {
        if (this.mVideoView == null) {
            this.mVideoView = new VideoView(this.mContext);
            this.addView((View)this.mVideoView, (ViewGroup.LayoutParams)new AbsoluteLayout.LayoutParams(-2, -2, 0, 0));
        }
        this.mVideoView.setVisibility(0);
        this.mVideoView.setVideoURI(uri);
    }

    @Override
    public void setVideoRegion(int n, int n2, int n3, int n4) {
        if (this.mVideoView != null && !this.mConformanceMode) {
            this.mVideoView.setLayoutParams((ViewGroup.LayoutParams)new AbsoluteLayout.LayoutParams(n3, n4, n, n2));
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void setVideoVisibility(boolean bl) {
        int n = 0;
        int n2 = 0;
        if (this.mVideoView == null) return;
        if (this.mConformanceMode) {
            VideoView videoView = this.mVideoView;
            if (!bl) {
                n2 = 8;
            }
            videoView.setVisibility(n2);
            return;
        }
        VideoView videoView = this.mVideoView;
        n2 = bl ? n : 4;
        videoView.setVisibility(n2);
    }

    @Override
    public void startAudio() {
        if (this.mAudioPlayer != null && this.mIsPrepared) {
            this.mAudioPlayer.start();
            this.mStartWhenPrepared = false;
            this.displayAudioInfo();
            return;
        }
        this.mStartWhenPrepared = true;
    }

    @Override
    public void startVideo() {
        if (this.mVideoView != null) {
            this.mVideoView.start();
        }
    }

    @Override
    public void stopAudio() {
        if (this.mAudioPlayer != null && this.mIsPrepared) {
            this.mAudioPlayer.stop();
            this.mAudioPlayer.release();
            this.mAudioPlayer = null;
            this.hideAudioInfo();
            return;
        }
        this.mStopWhenPrepared = true;
    }

    @Override
    public void stopVideo() {
        if (this.mVideoView != null) {
            this.mVideoView.stopPlayback();
        }
    }

    private class Position {
        public int mLeft;
        public int mTop;

        public Position(int n, int n2) {
            this.mTop = n2;
            this.mLeft = n;
        }
    }

}

