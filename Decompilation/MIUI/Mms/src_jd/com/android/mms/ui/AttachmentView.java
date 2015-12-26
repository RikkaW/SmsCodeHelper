package com.android.mms.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.android.mms.data.WorkingMessage;
import com.android.mms.model.AudioModel;
import com.android.mms.model.FileAttachmentModel;
import com.android.mms.model.ImageModel;
import com.android.mms.model.SlideModel;
import com.android.mms.model.SlideshowModel;
import com.android.mms.model.VideoModel;
import java.util.ArrayList;
import miui.os.Build;

public class AttachmentView
  extends FrameLayout
{
  private static int[] EDIT_BUTTONS = { 2131820666, 2131820667, 2131820668, 2131820896, 2131820897, 2131820898, 2131820550, 2131820551, 2131820552, 2131820874, 2131820875, 2131820876, 2131820895 };
  private String mAudioName;
  private TextView mAudioNameView;
  private Drawable mDefaultThumbnailMaskDrawable;
  private Handler mHandler;
  private ThumbnailView mImageView;
  private ViewTreeObserver.OnGlobalLayoutListener mLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener()
  {
    public void onGlobalLayout()
    {
      mHandler.post(new Runnable()
      {
        public void run()
        {
          if ((mPopupWindow != null) && (mPopupWindow.isShowing())) {
            showPopup();
          }
        }
      });
    }
  };
  private FloatPanelView mPanel;
  private ImageView mPlayIcon;
  private PopupWindow mPopupWindow;
  private SlideshowModel mSlideshow;
  private Drawable mThumbnailTransparentDrawable;
  
  public AttachmentView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }
  
  /* Error */
  private Bitmap createVideoThumbnail(android.net.Uri paramUri)
  {
    // Byte code:
    //   0: new 82	android/media/MediaMetadataRetriever
    //   3: dup
    //   4: invokespecial 84	android/media/MediaMetadataRetriever:<init>	()V
    //   7: astore_2
    //   8: aload_2
    //   9: aload_0
    //   10: invokevirtual 88	com/android/mms/ui/AttachmentView:getContext	()Landroid/content/Context;
    //   13: aload_1
    //   14: invokevirtual 92	android/media/MediaMetadataRetriever:setDataSource	(Landroid/content/Context;Landroid/net/Uri;)V
    //   17: aload_2
    //   18: ldc2_w 93
    //   21: invokevirtual 98	android/media/MediaMetadataRetriever:getFrameAtTime	(J)Landroid/graphics/Bitmap;
    //   24: astore_1
    //   25: aload_2
    //   26: invokevirtual 101	android/media/MediaMetadataRetriever:release	()V
    //   29: aload_1
    //   30: areturn
    //   31: astore_1
    //   32: aload_2
    //   33: invokevirtual 101	android/media/MediaMetadataRetriever:release	()V
    //   36: aconst_null
    //   37: areturn
    //   38: astore_1
    //   39: aconst_null
    //   40: areturn
    //   41: astore_1
    //   42: aload_2
    //   43: invokevirtual 101	android/media/MediaMetadataRetriever:release	()V
    //   46: aload_1
    //   47: athrow
    //   48: astore_2
    //   49: aload_1
    //   50: areturn
    //   51: astore_2
    //   52: goto -6 -> 46
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	55	0	this	AttachmentView
    //   0	55	1	paramUri	android.net.Uri
    //   7	36	2	localMediaMetadataRetriever	android.media.MediaMetadataRetriever
    //   48	1	2	localRuntimeException1	RuntimeException
    //   51	1	2	localRuntimeException2	RuntimeException
    // Exception table:
    //   from	to	target	type
    //   8	25	31	java/lang/RuntimeException
    //   32	36	38	java/lang/RuntimeException
    //   8	25	41	finally
    //   25	29	48	java/lang/RuntimeException
    //   42	46	51	java/lang/RuntimeException
  }
  
  private void init(Context paramContext)
  {
    paramContext = paramContext.getResources();
    mDefaultThumbnailMaskDrawable = paramContext.getDrawable(2130838013);
    mThumbnailTransparentDrawable = paramContext.getDrawable(2130838014);
  }
  
  private void setAudioName(String paramString)
  {
    mAudioName = paramString;
  }
  
  private void setImageBitmap(Bitmap paramBitmap, boolean paramBoolean)
  {
    setImageDrawable(new BitmapDrawable(getContext().getResources(), paramBitmap), paramBoolean);
  }
  
  private void setImageDrawable(Drawable paramDrawable, boolean paramBoolean)
  {
    mImageView.setBackgroundDrawable(mDefaultThumbnailMaskDrawable, 0);
    if (paramDrawable == null) {
      mImageView.setImageResource(2130837759, true);
    }
    while (paramBoolean)
    {
      mPlayIcon.setVisibility(0);
      return;
      mImageView.setMaskDrawable(null, mThumbnailTransparentDrawable);
      mImageView.setImageDrawable(paramDrawable, true);
      MessageUtils.setAttachmentAnimation(mImageView, paramDrawable);
    }
    mPlayIcon.setVisibility(8);
  }
  
  private void setPanelResid(int paramInt)
  {
    mPanel = ((FloatPanelView)((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(paramInt, null));
    paramInt = 0;
    while (paramInt < EDIT_BUTTONS.length)
    {
      View localView = mPanel.findViewById(EDIT_BUTTONS[paramInt]);
      if (localView != null) {
        localView.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            Message.obtain(mHandler, paramAnonymousView.getId()).sendToTarget();
            mHandler.post(new Runnable()
            {
              public void run()
              {
                dismissPopup();
              }
            });
          }
        });
      }
      paramInt += 1;
    }
  }
  
  public void dismissPopup()
  {
    if ((mPopupWindow != null) && (mPopupWindow.isShowing()))
    {
      mPopupWindow.dismiss();
      getViewTreeObserver().removeGlobalOnLayoutListener(mLayoutListener);
    }
  }
  
  public boolean onBackPressed()
  {
    if ((mPopupWindow != null) && (mPopupWindow.isShowing()))
    {
      dismissPopup();
      return true;
    }
    return false;
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    dismissPopup();
  }
  
  protected void onFinishInflate()
  {
    mImageView = ((ThumbnailView)findViewById(2131820791));
    mImageView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
    mPlayIcon = ((ImageView)findViewById(2131820619));
    setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        showPopup();
      }
    });
  }
  
  public void setAudioNameView(TextView paramTextView)
  {
    mAudioNameView = paramTextView;
  }
  
  public void setHandler(Handler paramHandler)
  {
    mHandler = paramHandler;
  }
  
  public void showPopup()
  {
    if (mPanel == null) {
      return;
    }
    if (mPopupWindow == null)
    {
      mPopupWindow = new PopupWindow(getContext(), null, 16843464);
      mPopupWindow.setWindowLayoutType(1002);
      mPopupWindow.setOutsideTouchable(true);
      mPopupWindow.setBackgroundDrawable(new ColorDrawable(getContext().getResources().getColor(17170445)));
      mPopupWindow.setInputMethodMode(2);
    }
    int[] arrayOfInt1 = new int[2];
    int[] arrayOfInt2 = new int[2];
    getLocationOnScreen(arrayOfInt1);
    int i = arrayOfInt1[1];
    View localView = (View)getParent();
    while ((localView.getParent() instanceof View))
    {
      localView.getLocationOnScreen(arrayOfInt2);
      j = i;
      if (arrayOfInt2[1] > i) {
        j = arrayOfInt2[1];
      }
      localView = (View)localView.getParent();
      i = j;
    }
    if (i >= arrayOfInt1[1] + mImageView.getHeight())
    {
      dismissPopup();
      return;
    }
    mPanel.measure(0, 0);
    int j = mPanel.getMeasuredHeight();
    int k = arrayOfInt1[0] + mImageView.getWidth() / 2;
    int m = Math.max(0, k - mPanel.getMeasuredWidth() / 2);
    mPanel.setArrowPos(k - m);
    if (mPopupWindow.getContentView() != mPanel) {
      mPopupWindow.setContentView(mPanel);
    }
    if (!mPopupWindow.isShowing())
    {
      mPopupWindow.showAtLocation(this, 0, 0, 0);
      getViewTreeObserver().addOnGlobalLayoutListener(mLayoutListener);
    }
    mPopupWindow.update(m, i - j, mPanel.getMeasuredWidth(), mPanel.getMeasuredHeight());
  }
  
  public void update(WorkingMessage paramWorkingMessage)
  {
    setAudioName(null);
    if (!paramWorkingMessage.hasAttachment())
    {
      setVisibility(8);
      updateAudioName();
      return;
    }
    mImageView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
    mSlideshow = paramWorkingMessage.getSlideshow();
    if (!paramWorkingMessage.hasSlideshow()) {
      if (mSlideshow.sizeOfFilesAttach() == 1) {
        if (((FileAttachmentModel)mSlideshow.getAttachFiles().get(0)).isVCard())
        {
          mImageView.setImageResource(2130837633);
          setPanelResid(2130968717);
          mPlayIcon.setVisibility(8);
          setVisibility(0);
        }
      }
    }
    for (;;)
    {
      updateAudioName();
      return;
      throw new IllegalStateException("Unsupported attachment type");
      paramWorkingMessage = mSlideshow.get(0);
      if ((Build.IS_CM_CUSTOMIZATION_TEST) && (paramWorkingMessage.hasAudio()) && (paramWorkingMessage.hasImage())) {
        setAudioName(paramWorkingMessage.getAudio().getSrc());
      }
      if (paramWorkingMessage.hasImage())
      {
        setImageDrawable(paramWorkingMessage.getImage().getDrawable(), false);
        setPanelResid(2130968627);
        mPlayIcon.setVisibility(8);
        setVisibility(0);
      }
      else if (paramWorkingMessage.hasVideo())
      {
        setImageBitmap(createVideoThumbnail(paramWorkingMessage.getVideo().getUri()), true);
        setPanelResid(2130968718);
        mPlayIcon.setVisibility(0);
        setVisibility(0);
      }
      else if (paramWorkingMessage.hasAudio())
      {
        paramWorkingMessage = paramWorkingMessage.getAudio();
        mImageView.setImageResource(2130837711);
        setPanelResid(2130968579);
        mPlayIcon.setVisibility(8);
        setVisibility(0);
        setAudioName(paramWorkingMessage.getSrc());
        continue;
        mImageView.setImageResource(2130837773, false);
        setPanelResid(2130968709);
        mPlayIcon.setVisibility(8);
        setVisibility(0);
      }
    }
  }
  
  public void updateAudioName()
  {
    if ((Build.IS_CM_CUSTOMIZATION_TEST) && (mAudioNameView != null))
    {
      if (!TextUtils.isEmpty(mAudioName))
      {
        mAudioNameView.setText(mAudioName);
        mAudioNameView.setVisibility(0);
      }
    }
    else {
      return;
    }
    mAudioNameView.setVisibility(8);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.AttachmentView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */