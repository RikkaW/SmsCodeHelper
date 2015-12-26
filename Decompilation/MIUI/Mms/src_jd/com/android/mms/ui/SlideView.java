package com.android.mms.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.text.method.HideReturnsTransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsoluteLayout;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;
import com.android.mms.layout.LayoutManager;
import com.android.mms.layout.LayoutParameters;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class SlideView
  extends AbsoluteLayout
  implements AdaptableSlideViewInterface
{
  private View mAudioInfoView;
  private MediaPlayer mAudioPlayer;
  private boolean mConformanceMode;
  private ImageView mImageView;
  private boolean mIsPrepared;
  private MediaController mMediaController;
  MediaPlayer.OnPreparedListener mPreparedListener = new MediaPlayer.OnPreparedListener()
  {
    public void onPrepared(MediaPlayer paramAnonymousMediaPlayer)
    {
      SlideView.access$002(SlideView.this, true);
      if (mSeekWhenPrepared > 0)
      {
        mAudioPlayer.seekTo(mSeekWhenPrepared);
        SlideView.access$102(SlideView.this, 0);
      }
      if (mStartWhenPrepared)
      {
        mAudioPlayer.start();
        SlideView.access$302(SlideView.this, false);
        SlideView.this.displayAudioInfo();
      }
      if (mStopWhenPrepared)
      {
        mAudioPlayer.stop();
        mAudioPlayer.release();
        SlideView.access$202(SlideView.this, null);
        SlideView.access$502(SlideView.this, false);
        SlideView.this.hideAudioInfo();
      }
    }
  };
  private ScrollView mScrollText;
  private ScrollView mScrollViewPort;
  private int mSeekWhenPrepared;
  private AdaptableSlideViewInterface.OnSizeChangedListener mSizeChangedListener;
  private boolean mStartWhenPrepared;
  private boolean mStopWhenPrepared;
  private float mTextSize = 0.0F;
  private TextView mTextView;
  private VideoView mVideoView;
  private LinearLayout mViewPort;
  
  public SlideView(Context paramContext)
  {
    super(paramContext);
  }
  
  public SlideView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  private void displayAudioInfo()
  {
    if (mAudioInfoView != null) {
      mAudioInfoView.setVisibility(0);
    }
  }
  
  private void hideAudioInfo()
  {
    if (mAudioInfoView != null) {
      mAudioInfoView.setVisibility(8);
    }
  }
  
  private void initAudioInfoView(String paramString)
  {
    if (mAudioInfoView == null)
    {
      mAudioInfoView = LayoutInflater.from(getContext()).inflate(2130968689, null);
      mAudioInfoView.getHeight();
      if (!mConformanceMode) {
        break label86;
      }
      mViewPort.addView(mAudioInfoView, new LinearLayout.LayoutParams(-1, 82));
    }
    for (;;)
    {
      ((TextView)mAudioInfoView.findViewById(2131820846)).setText(paramString);
      mAudioInfoView.setVisibility(8);
      return;
      label86:
      addView(mAudioInfoView, new AbsoluteLayout.LayoutParams(-1, 82, 0, getHeight() - 82));
    }
  }
  
  public void enableMMSConformanceMode(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    mConformanceMode = true;
    if (mScrollViewPort == null)
    {
      mScrollViewPort = new ScrollView(mContext)
      {
        private int mBottomY;
        
        protected void onLayout(boolean paramAnonymousBoolean, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4)
        {
          int i = 0;
          super.onLayout(paramAnonymousBoolean, paramAnonymousInt1, paramAnonymousInt2, paramAnonymousInt3, paramAnonymousInt4);
          if (getChildCount() > 0)
          {
            paramAnonymousInt2 = getChildAt(0).getHeight();
            paramAnonymousInt3 = getHeight();
            paramAnonymousInt1 = i;
            if (paramAnonymousInt3 < paramAnonymousInt2) {
              paramAnonymousInt1 = paramAnonymousInt2 - paramAnonymousInt3;
            }
            mBottomY = paramAnonymousInt1;
          }
        }
        
        protected void onScrollChanged(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4)
        {
          if (((paramAnonymousInt2 == 0) || (paramAnonymousInt2 >= mBottomY)) && (mMediaController != null)) {
            mMediaController.show();
          }
        }
      };
      mScrollViewPort.setScrollBarStyle(50331648);
      mViewPort = new LinearLayout(mContext);
      mViewPort.setOrientation(1);
      mViewPort.setGravity(17);
      mViewPort.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (mMediaController != null) {
            mMediaController.show();
          }
        }
      });
      mScrollViewPort.addView(mViewPort, new FrameLayout.LayoutParams(-1, -2));
      addView(mScrollViewPort);
    }
    Object localObject = new TreeMap(new Comparator()
    {
      public int compare(SlideView.Position paramAnonymousPosition1, SlideView.Position paramAnonymousPosition2)
      {
        int k = mLeft;
        int i = mTop;
        int m = mLeft;
        int j = i - mTop;
        i = j;
        if (j == 0) {
          i = k - m;
        }
        j = i;
        if (i == 0) {
          j = -1;
        }
        return j;
      }
    });
    if ((paramInt1 >= 0) && (paramInt2 >= 0))
    {
      mTextView = new TextView(mContext);
      mTextView.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
      mTextView.setTextSize(0, mTextSize);
      mTextView.setPadding(5, 5, 5, 5);
      ((TreeMap)localObject).put(new Position(paramInt1, paramInt2), mTextView);
    }
    if ((paramInt3 >= 0) && (paramInt4 >= 0))
    {
      mImageView = new ImageView(mContext);
      mImageView.setPadding(0, 5, 0, 5);
      ((TreeMap)localObject).put(new Position(paramInt3, paramInt4), mImageView);
      mVideoView = new VideoView(mContext);
      ((TreeMap)localObject).put(new Position(paramInt3 + 1, paramInt4), mVideoView);
    }
    localObject = ((TreeMap)localObject).values().iterator();
    if (((Iterator)localObject).hasNext())
    {
      View localView = (View)((Iterator)localObject).next();
      if ((localView instanceof VideoView)) {
        mViewPort.addView(localView, new LinearLayout.LayoutParams(-1, LayoutManager.getInstance().getLayoutParameters().getHeight()));
      }
      for (;;)
      {
        localView.setVisibility(8);
        break;
        mViewPort.addView(localView, new LinearLayout.LayoutParams(-1, -2));
      }
    }
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (mSizeChangedListener != null) {
      mSizeChangedListener.onSizeChanged(paramInt1, paramInt2 - 82);
    }
  }
  
  public void pauseAudio()
  {
    if ((mAudioPlayer != null) && (mIsPrepared) && (mAudioPlayer.isPlaying())) {
      mAudioPlayer.pause();
    }
    mStartWhenPrepared = false;
  }
  
  public void pauseVideo()
  {
    if (mVideoView != null) {
      mVideoView.pause();
    }
  }
  
  public void reset()
  {
    if (mScrollText != null) {
      mScrollText.setVisibility(8);
    }
    if (mImageView != null) {
      mImageView.setVisibility(8);
    }
    if (mAudioPlayer != null) {
      stopAudio();
    }
    if (mVideoView != null)
    {
      stopVideo();
      mVideoView.setVisibility(8);
    }
    if (mTextView != null) {
      mTextView.setVisibility(8);
    }
    if (mScrollViewPort != null)
    {
      mScrollViewPort.scrollTo(0, 0);
      mScrollViewPort.setLayoutParams(new AbsoluteLayout.LayoutParams(-1, -1, 0, 0));
    }
  }
  
  public void seekAudio(int paramInt)
  {
    if ((mAudioPlayer != null) && (mIsPrepared))
    {
      mAudioPlayer.seekTo(paramInt);
      return;
    }
    mSeekWhenPrepared = paramInt;
  }
  
  public void seekVideo(int paramInt)
  {
    if ((mVideoView != null) && (paramInt > 0)) {
      mVideoView.seekTo(paramInt);
    }
  }
  
  public void setAudio(Uri paramUri, String paramString, Map<String, ?> paramMap)
  {
    if (paramUri == null) {
      throw new IllegalArgumentException("Audio URI may not be null.");
    }
    if (mAudioPlayer != null)
    {
      mAudioPlayer.reset();
      mAudioPlayer.release();
      mAudioPlayer = null;
    }
    mIsPrepared = false;
    mStartWhenPrepared = false;
    mSeekWhenPrepared = 0;
    mStopWhenPrepared = false;
    try
    {
      mAudioPlayer = new MediaPlayer();
      mAudioPlayer.setOnPreparedListener(mPreparedListener);
      mAudioPlayer.setDataSource(mContext, paramUri);
      mAudioPlayer.prepareAsync();
      initAudioInfoView(paramString);
      return;
    }
    catch (IOException paramUri)
    {
      for (;;)
      {
        Log.e("SlideView", "Unexpected IOException.", paramUri);
        mAudioPlayer.release();
        mAudioPlayer = null;
      }
    }
  }
  
  public void setAudioDuration(int paramInt) {}
  
  public void setImage(String paramString, Drawable paramDrawable)
  {
    if (mImageView == null)
    {
      mImageView = new ImageView(mContext);
      mImageView.setPadding(0, 5, 0, 5);
      addView(mImageView, new AbsoluteLayout.LayoutParams(-2, -2, 0, 0));
    }
    paramString = paramDrawable;
    if (paramDrawable == null) {}
    try
    {
      paramString = getResources().getDrawable(2130837759);
      mImageView.setVisibility(0);
      MessageUtils.setAttachmentImage(mImageView, paramString, true);
      return;
    }
    catch (OutOfMemoryError paramString)
    {
      Log.e("SlideView", "setImage: out of memory: ", paramString);
    }
  }
  
  public void setImageRegion(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((mImageView != null) && (!mConformanceMode)) {
      mImageView.setLayoutParams(new AbsoluteLayout.LayoutParams(paramInt3, paramInt4, paramInt1, paramInt2));
    }
  }
  
  public void setImageRegionFit(String paramString) {}
  
  public void setImageVisibility(boolean paramBoolean)
  {
    int j = 0;
    int i = 0;
    if (mImageView != null)
    {
      if (!mConformanceMode) {
        break label41;
      }
      localImageView = mImageView;
      if (!paramBoolean) {
        break label35;
      }
    }
    for (;;)
    {
      localImageView.setVisibility(i);
      return;
      label35:
      i = 8;
    }
    label41:
    ImageView localImageView = mImageView;
    if (paramBoolean) {}
    for (i = j;; i = 4)
    {
      localImageView.setVisibility(i);
      return;
    }
  }
  
  public void setMediaController(MediaController paramMediaController)
  {
    mMediaController = paramMediaController;
  }
  
  public void setOnSizeChangedListener(AdaptableSlideViewInterface.OnSizeChangedListener paramOnSizeChangedListener)
  {
    mSizeChangedListener = paramOnSizeChangedListener;
  }
  
  public void setText(String paramString1, String paramString2)
  {
    if (!mConformanceMode)
    {
      if (mScrollText == null)
      {
        mScrollText = new ScrollView(mContext);
        mScrollText.setScrollBarStyle(50331648);
        addView(mScrollText, new AbsoluteLayout.LayoutParams(-2, -2, 0, 0));
      }
      if (mTextView == null)
      {
        mTextView = new TextView(mContext);
        mTextView.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        mScrollText.addView(mTextView);
      }
      mScrollText.requestFocus();
    }
    mTextView.setTextSize(0, mTextSize);
    mTextView.setVisibility(0);
    mTextView.setText(paramString2);
  }
  
  public void setTextRegion(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((mScrollText != null) && (!mConformanceMode)) {
      mScrollText.setLayoutParams(new AbsoluteLayout.LayoutParams(paramInt3, paramInt4, paramInt1, paramInt2));
    }
  }
  
  public void setTextSize(float paramFloat)
  {
    mTextSize = paramFloat;
    if (mTextView != null) {
      mTextView.setTextSize(0, mTextSize);
    }
  }
  
  public void setTextVisibility(boolean paramBoolean)
  {
    int j = 0;
    int i = 0;
    if (mConformanceMode) {
      if (mTextView != null)
      {
        localObject = mTextView;
        if (!paramBoolean) {
          break label35;
        }
        ((TextView)localObject).setVisibility(i);
      }
    }
    label35:
    while (mScrollText == null) {
      for (;;)
      {
        return;
        i = 8;
      }
    }
    Object localObject = mScrollText;
    if (paramBoolean) {}
    for (i = j;; i = 4)
    {
      ((ScrollView)localObject).setVisibility(i);
      return;
    }
  }
  
  public void setVideo(String paramString, Uri paramUri)
  {
    if (mVideoView == null)
    {
      mVideoView = new VideoView(mContext);
      addView(mVideoView, new AbsoluteLayout.LayoutParams(-2, -2, 0, 0));
    }
    mVideoView.setVisibility(0);
    mVideoView.setVideoURI(paramUri);
  }
  
  public void setVideoRegion(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((mVideoView != null) && (!mConformanceMode)) {
      mVideoView.setLayoutParams(new AbsoluteLayout.LayoutParams(paramInt3, paramInt4, paramInt1, paramInt2));
    }
  }
  
  public void setVideoVisibility(boolean paramBoolean)
  {
    int j = 0;
    int i = 0;
    if (mVideoView != null)
    {
      if (!mConformanceMode) {
        break label41;
      }
      localVideoView = mVideoView;
      if (!paramBoolean) {
        break label35;
      }
    }
    for (;;)
    {
      localVideoView.setVisibility(i);
      return;
      label35:
      i = 8;
    }
    label41:
    VideoView localVideoView = mVideoView;
    if (paramBoolean) {}
    for (i = j;; i = 4)
    {
      localVideoView.setVisibility(i);
      return;
    }
  }
  
  public void startAudio()
  {
    if ((mAudioPlayer != null) && (mIsPrepared))
    {
      mAudioPlayer.start();
      mStartWhenPrepared = false;
      displayAudioInfo();
      return;
    }
    mStartWhenPrepared = true;
  }
  
  public void startVideo()
  {
    if (mVideoView != null) {
      mVideoView.start();
    }
  }
  
  public void stopAudio()
  {
    if ((mAudioPlayer != null) && (mIsPrepared))
    {
      mAudioPlayer.stop();
      mAudioPlayer.release();
      mAudioPlayer = null;
      hideAudioInfo();
      return;
    }
    mStopWhenPrepared = true;
  }
  
  public void stopVideo()
  {
    if (mVideoView != null) {
      mVideoView.stopPlayback();
    }
  }
  
  private class Position
  {
    public int mLeft;
    public int mTop;
    
    public Position(int paramInt1, int paramInt2)
    {
      mTop = paramInt2;
      mLeft = paramInt1;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */