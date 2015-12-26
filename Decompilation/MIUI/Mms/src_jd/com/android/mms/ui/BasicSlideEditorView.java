package com.android.mms.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.Map;

public class BasicSlideEditorView
  extends RelativeLayout
  implements SlideViewInterface
{
  private TextView mAudioNameView;
  private TextView mAudioTimeView;
  private View mAudioView;
  private EditText mEditText;
  private ImageView mImageView;
  private View.OnClickListener mImageViewOnClickListener;
  private OnTextChangedListener mOnTextChangedListener;
  private boolean mOnTextChangedListenerEnabled = true;
  private ImageView mPlayIcon;
  
  public BasicSlideEditorView(Context paramContext)
  {
    super(paramContext);
  }
  
  public BasicSlideEditorView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public void onFinishInflate()
  {
    mImageView = ((ImageView)findViewById(2131820618));
    mImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (mImageViewOnClickListener != null) {
          mImageViewOnClickListener.onClick(paramAnonymousView);
        }
      }
    });
    mPlayIcon = ((ImageView)findViewById(2131820619));
    mAudioView = findViewById(2131820620);
    mAudioNameView = ((TextView)findViewById(2131820621));
    mAudioTimeView = ((TextView)findViewById(2131820622));
    mEditText = ((EditText)findViewById(2131820623));
    mEditText.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable) {}
      
      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        if ((mOnTextChangedListenerEnabled) && (mOnTextChangedListener != null)) {
          mOnTextChangedListener.onTextChanged(paramAnonymousCharSequence.toString());
        }
      }
    });
  }
  
  public void pauseAudio() {}
  
  public void pauseVideo() {}
  
  public void reset()
  {
    mImageView.setImageResource(2130837977);
    mPlayIcon.setVisibility(8);
    mAudioView.setVisibility(8);
    mOnTextChangedListenerEnabled = false;
    mEditText.setText("");
    mOnTextChangedListenerEnabled = true;
  }
  
  public void seekAudio(int paramInt) {}
  
  public void seekVideo(int paramInt) {}
  
  public void setAudio(Uri paramUri, String paramString, Map<String, ?> paramMap)
  {
    mAudioView.setVisibility(0);
    mAudioNameView.setText(paramString);
  }
  
  public void setAudioDuration(int paramInt)
  {
    int i = paramInt;
    if (paramInt <= 0) {
      i = 1;
    }
    mAudioTimeView.setText(mContext.getResources().getQuantityString(2131623938, i, new Object[] { Integer.valueOf(i) }));
  }
  
  public void setImage(String paramString, Drawable paramDrawable)
  {
    try
    {
      MessageUtils.setAttachmentImage(mImageView, paramDrawable, true);
      return;
    }
    catch (OutOfMemoryError paramString)
    {
      Log.e("BasicSlideEditorView", "setImage: out of memory: ", paramString);
    }
  }
  
  public void setImageRegionFit(String paramString) {}
  
  public void setImageViewOnClickListener(View.OnClickListener paramOnClickListener)
  {
    mImageViewOnClickListener = paramOnClickListener;
  }
  
  public void setImageVisibility(boolean paramBoolean) {}
  
  public void setOnTextChangedListener(OnTextChangedListener paramOnTextChangedListener)
  {
    mOnTextChangedListener = paramOnTextChangedListener;
  }
  
  public void setText(String paramString1, String paramString2)
  {
    mOnTextChangedListenerEnabled = false;
    if ((paramString2 != null) && (!paramString2.equals(mEditText.getText().toString())))
    {
      mEditText.setText(paramString2);
      mEditText.setSelection(paramString2.length());
    }
    mOnTextChangedListenerEnabled = true;
  }
  
  public void setTextVisibility(boolean paramBoolean) {}
  
  public void setVideo(String paramString, Uri paramUri)
  {
    try
    {
      paramString = VideoAttachmentView.createVideoThumbnail(mContext, paramUri);
      if (paramString == null)
      {
        paramString = BitmapFactory.decodeResource(getResources(), 2130837760);
        mImageView.setImageBitmap(paramString);
        mPlayIcon.setVisibility(8);
        return;
      }
      mImageView.setImageBitmap(paramString);
      mPlayIcon.setVisibility(0);
      return;
    }
    catch (OutOfMemoryError paramString)
    {
      Log.e("BasicSlideEditorView", "setVideo: out of memory: ", paramString);
    }
  }
  
  public void setVideoVisibility(boolean paramBoolean) {}
  
  public void startAudio() {}
  
  public void startVideo() {}
  
  public void stopAudio() {}
  
  public void stopVideo() {}
  
  public static abstract interface OnTextChangedListener
  {
    public abstract void onTextChanged(String paramString);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.BasicSlideEditorView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */