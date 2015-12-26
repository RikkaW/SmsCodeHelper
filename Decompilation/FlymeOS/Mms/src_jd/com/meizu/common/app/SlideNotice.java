package com.meizu.common.app;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.meizu.common.R.color;
import com.meizu.common.R.dimen;
import com.meizu.common.R.id;
import com.meizu.common.R.layout;
import com.meizu.common.util.ResourceUtils;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

public class SlideNotice
{
  public static final int LENGTH_LONG = 1;
  public static final int LENGTH_SHORT = 0;
  private static final int NOTICE_CLICK = 0;
  public static final int NOTICE_TYPE_FAILURE = 0;
  public static final int NOTICE_TYPE_SUCCESS = 1;
  public static final int SHOW_ANIMATION_DURATION = 300;
  private static final String TAG = "SlideNotice";
  private static SlideNoticeManagerService mService;
  private Context mContext;
  private int mDuration;
  private SlideViewController mSlideViewController;
  private Toast mToast;
  
  public SlideNotice(Context paramContext)
  {
    if (paramContext == null) {
      throw new IllegalArgumentException("context can not be null!");
    }
    mContext = paramContext;
    mSlideViewController = new SlideViewController(this);
  }
  
  public SlideNotice(Context paramContext, CharSequence paramCharSequence, int paramInt)
  {
    this(paramContext);
    mToast = Toast.makeText(paramContext, paramCharSequence, paramInt);
  }
  
  private static SlideNoticeManagerService getService()
  {
    if (mService != null) {
      return mService;
    }
    mService = new SlideNoticeManagerService();
    return mService;
  }
  
  public static SlideNotice makeNotice(Context paramContext, CharSequence paramCharSequence)
  {
    return makeNotice(paramContext, paramCharSequence, 1, 0);
  }
  
  public static SlideNotice makeNotice(Context paramContext, CharSequence paramCharSequence, int paramInt)
  {
    return makeNotice(paramContext, paramCharSequence, 0, 0);
  }
  
  public static SlideNotice makeNotice(Context paramContext, CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    return new SlideNotice(paramContext, paramCharSequence, paramInt2);
  }
  
  public static SlideNotice makeSlideNotice(Context paramContext, CharSequence paramCharSequence)
  {
    return makeSlideNotice(paramContext, paramCharSequence, 1, 0);
  }
  
  public static SlideNotice makeSlideNotice(Context paramContext, CharSequence paramCharSequence, int paramInt)
  {
    paramContext = new SlideNotice(paramContext);
    paramContext.setText(paramCharSequence);
    paramContext.setNoticeType(paramInt);
    return paramContext;
  }
  
  public static SlideNotice makeSlideNotice(Context paramContext, CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    paramContext = new SlideNotice(paramContext);
    paramContext.setText(paramCharSequence);
    paramContext.setNoticeType(paramInt1);
    mDuration = paramInt2;
    return paramContext;
  }
  
  public void cancel()
  {
    if (mToast != null) {
      mToast.cancel();
    }
  }
  
  public void cancelWithoutAnim()
  {
    mSlideViewController.destroy();
  }
  
  public void finish()
  {
    cancel();
  }
  
  public int getDuration()
  {
    return mDuration;
  }
  
  public boolean isShowing()
  {
    return mSlideViewController.isShowing();
  }
  
  public void resetSlideFrom()
  {
    mSlideViewController.resetTop();
  }
  
  public void setActionBarToTop(boolean paramBoolean)
  {
    mSlideViewController.setBelowDefaultActionBar(paramBoolean);
  }
  
  public void setAnchorView(View paramView)
  {
    mSlideViewController.setAnchor(paramView);
  }
  
  public void setBeginColor(int paramInt)
  {
    mSlideViewController.setBeginColor(paramInt);
  }
  
  public void setBelowDefaultActionBar(boolean paramBoolean)
  {
    mSlideViewController.setBelowDefaultActionBar(paramBoolean);
  }
  
  public void setCustomView(View paramView)
  {
    mSlideViewController.setCustomView(paramView);
  }
  
  public void setDuration(int paramInt)
  {
    mDuration = paramInt;
    if (mToast != null) {
      mToast.setDuration(paramInt);
    }
  }
  
  public void setEndColor(int paramInt)
  {
    mSlideViewController.setEndColor(paramInt);
  }
  
  public void setGravity(int paramInt)
  {
    mSlideViewController.setGravity(paramInt);
  }
  
  public void setHeight(int paramInt)
  {
    mSlideViewController.setNoticeHeight(paramInt);
  }
  
  public void setIsOverlaidByStatusBar(boolean paramBoolean)
  {
    mSlideViewController.setIsOverlaidByStatusBar(paramBoolean);
  }
  
  public void setLeft(int paramInt)
  {
    mSlideViewController.setLeft(paramInt);
  }
  
  public void setNoTitleBarStyle(boolean paramBoolean)
  {
    mSlideViewController.setIsOverlaidByStatusBar(paramBoolean);
  }
  
  public void setNoticeType(int paramInt)
  {
    if (mSlideViewController.isShowing()) {
      return;
    }
    switch (paramInt)
    {
    default: 
      setBeginColor(mContext.getResources().getColor(R.color.mc_slide_notice_success_begin_color));
      setEndColor(mContext.getResources().getColor(R.color.mc_slide_notice_success_end_color));
      return;
    }
    setBeginColor(mContext.getResources().getColor(R.color.mc_slide_notice_failure_begin_color));
    setEndColor(mContext.getResources().getColor(R.color.mc_slide_notice_failure_end_color));
  }
  
  public void setOnClickNoticeListener(OnClickNoticeListener paramOnClickNoticeListener)
  {
    mSlideViewController.setOnClickNoticeListener(paramOnClickNoticeListener);
  }
  
  public void setText(CharSequence paramCharSequence)
  {
    mSlideViewController.setText(paramCharSequence);
    if (mToast != null) {
      mToast.setText(paramCharSequence);
    }
  }
  
  public void setTop(int paramInt)
  {
    mSlideViewController.setTop(paramInt);
  }
  
  public void setWidth(int paramInt)
  {
    mSlideViewController.setNoticeWidth(paramInt);
  }
  
  public void show()
  {
    if (mToast != null) {
      mToast.show();
    }
  }
  
  public void show(boolean paramBoolean) {}
  
  public void showAndFinish(long paramLong) {}
  
  public void slideToCancel()
  {
    mSlideViewController.hide();
    getService().cancelNotice(mSlideViewController);
  }
  
  public void slideToShow()
  {
    getService().enqueueNotice(mSlideViewController.getText(), mSlideViewController, mDuration);
  }
  
  public void slideToShow(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      mSlideViewController.show();
      return;
    }
    show();
  }
  
  static final class NoticeHandler
    extends Handler
  {
    private WeakReference<SlideNotice> mNotice;
    
    public NoticeHandler(SlideNotice paramSlideNotice)
    {
      mNotice = new WeakReference(paramSlideNotice);
    }
    
    public void handleMessage(Message paramMessage)
    {
      switch (what)
      {
      default: 
        return;
      }
      ((SlideNotice.OnClickNoticeListener)obj).onClick((SlideNotice)mNotice.get());
    }
  }
  
  public static abstract interface OnClickNoticeListener
  {
    public abstract void onClick(SlideNotice paramSlideNotice);
  }
  
  static final class SlideContainerView
    extends FrameLayout
  {
    public SlideContainerView(Context paramContext)
    {
      super();
    }
  }
  
  class SlideViewController
    implements SlideNoticeManagerService.NoticeCallBack
  {
    private static final int SLIDE_ANIMATION_TYPE_HIDE = 1;
    private static final int SLIDE_ANIMATION_TYPE_SHOW = 0;
    private boolean isBelowDefaultActionBar;
    private boolean isSupportedImmersedStatusBar;
    private int mActionBarHeight;
    private WeakReference<View> mAnchor;
    private AnimationExecutor mAnimationExecutor;
    private int mAnimationType;
    private int mBeginColor;
    private LinearLayout mContent;
    private final View.OnClickListener mContentClickListener = new SlideNotice.SlideViewController.4(this);
    private FrameLayout mCustom;
    private View mCustomView;
    private int[] mDrawingLocation = new int[2];
    private int mEndColor;
    final Handler mHandler = new Handler();
    final Runnable mHide = new SlideNotice.SlideViewController.3(this);
    private boolean mIsOverlaidByStatusBar = false;
    private boolean mIsOverlaidByStatusBarSet;
    private int mLeft;
    private Message mNoticeClickMsg;
    private Handler mNoticeHandler;
    private int mNoticeHeight;
    private LinearLayout mNoticePanel;
    private TextView mNoticeView;
    private int mNoticeWidth;
    private SlideNotice.OnClickNoticeListener mOnClickNoticeListener;
    private ViewTreeObserver.OnScrollChangedListener mOnScrollChangedListener = new SlideNotice.SlideViewController.1(this);
    private WindowManager.LayoutParams mParams = new WindowManager.LayoutParams();
    final Runnable mShow = new SlideNotice.SlideViewController.2(this);
    private int mSlidHeight;
    private View mSlideContent;
    private View mSlideView;
    private int mSlideY = -1;
    private int mStatusBarHeight;
    private int mTop;
    private WindowManager mWManager;
    private boolean showing;
    private CharSequence text;
    
    public SlideViewController(SlideNotice paramSlideNotice)
    {
      mNoticeHandler = new SlideNotice.NoticeHandler(paramSlideNotice);
      init();
    }
    
    private void destroy()
    {
      if ((mSlideView != null) && (mSlideView.getParent() != null)) {
        mWManager.removeView(mSlideView);
      }
      unregisterForScrollChanged();
      mOnScrollChangedListener = null;
      showing = false;
    }
    
    private void findViewPosition(View paramView, WindowManager.LayoutParams paramLayoutParams)
    {
      int i = paramView.getHeight();
      paramView.getLocationInWindow(mDrawingLocation);
      x = mDrawingLocation[0];
      y = (i + mDrawingLocation[1]);
    }
    
    private int getActionBarHeight(Context paramContext)
    {
      TypedValue localTypedValue = new TypedValue();
      if (paramContext.getTheme().resolveAttribute(16843499, localTypedValue, true)) {
        return TypedValue.complexToDimensionPixelSize(data, paramContext.getResources().getDisplayMetrics());
      }
      return 144;
    }
    
    private int getStatusBarHeight(Context paramContext)
    {
      try
      {
        Class localClass = Class.forName("com.android.internal.R$dimen");
        Object localObject = localClass.newInstance();
        int i = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
        i = paramContext.getResources().getDimensionPixelSize(i);
        return i;
      }
      catch (Exception paramContext)
      {
        Log.e("ResurceUtils", "get status bar height fail", paramContext);
      }
      return 75;
    }
    
    private void init()
    {
      mSlideContent = LayoutInflater.from(mContext).inflate(R.layout.mc_slide_notice_content, null);
      mNoticeView = ((TextView)mSlideContent.findViewById(R.id.noticeView));
      mNoticePanel = ((LinearLayout)mSlideContent.findViewById(R.id.noticePanel));
      mCustom = ((FrameLayout)mSlideContent.findViewById(R.id.custom));
      mSlideContent.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
      mStatusBarHeight = getStatusBarHeight(mContext);
      mActionBarHeight = getActionBarHeight(mContext);
      mAnimationExecutor = new AnimationExecutor();
      Context localContext = mContext.getApplicationContext();
      if (localContext != null) {}
      for (mWManager = ((WindowManager)localContext.getSystemService("window"));; mWManager = ((WindowManager)mContext.getSystemService("window")))
      {
        mParams.height = -2;
        mParams.width = -1;
        mParams.gravity = 51;
        mParams.format = -3;
        mParams.setTitle("SlideNotice:" + Integer.toHexString(hashCode()));
        mParams.flags = 40;
        isSupportedImmersedStatusBar = setTransStatusBar(mParams, true);
        if (!isSupportedImmersedStatusBar) {
          mStatusBarHeight = 0;
        }
        if (!(mContext instanceof Activity)) {
          mActionBarHeight = mContext.getResources().getDimensionPixelSize(R.dimen.mz_action_bar_default_height);
        }
        return;
      }
    }
    
    private void invokeNotice(WindowManager.LayoutParams paramLayoutParams)
    {
      if (mContext != null) {
        packageName = mContext.getPackageName();
      }
      mWManager.addView(mSlideView, paramLayoutParams);
    }
    
    private void prepareNotice(WindowManager.LayoutParams paramLayoutParams)
    {
      Object localObject;
      if (mSlideView == null)
      {
        localObject = new SlideNotice.SlideContainerView(mContext);
        ((FrameLayout)localObject).addView(mSlideContent);
        mSlideView = ((View)localObject);
      }
      if ((mContext instanceof Activity))
      {
        localObject = ((Activity)mContext).getWindow().getDecorView().getWindowToken();
        if (localObject != null)
        {
          token = ((IBinder)localObject);
          type = 1000;
          return;
        }
        type = 2005;
        return;
      }
      type = 2005;
    }
    
    private boolean setTransStatusBar(WindowManager.LayoutParams paramLayoutParams, boolean paramBoolean)
    {
      for (;;)
      {
        int i;
        try
        {
          if (Build.VERSION.SDK_INT < 19)
          {
            Field localField = paramLayoutParams.getClass().getDeclaredField("meizuFlags");
            localField.setAccessible(true);
            i = localField.getInt(paramLayoutParams);
            if (!paramBoolean) {
              break label116;
            }
            i = 0x40 | i;
            localField.setInt(paramLayoutParams, i);
            return true;
          }
          if (paramBoolean)
          {
            flags |= 0x4000000;
            return true;
          }
        }
        catch (Exception paramLayoutParams)
        {
          Log.e("SlideNotice", "Can't set the status bar to be transparent, Caused by:" + paramLayoutParams.getMessage());
          return false;
        }
        flags &= 0xFBFFFFFF;
        return true;
        label116:
        i &= 0xFFFFFFBF;
      }
    }
    
    private void setupContent()
    {
      mNoticeView.setVisibility(8);
      if ((mTop < mStatusBarHeight) && (!mIsOverlaidByStatusBarSet)) {
        mIsOverlaidByStatusBar = true;
      }
      if (mIsOverlaidByStatusBar)
      {
        mNoticeView = ((TextView)mSlideContent.findViewById(R.id.noticeView_no_title_bar));
        mNoticeView.getLayoutParams()).topMargin = mContext.getResources().getDimensionPixelSize(R.dimen.mc_slide_notice_textview_margin_top);
      }
      for (;;)
      {
        if (mNoticeHeight > 0) {
          mNoticePanel.getLayoutParams().height = mNoticeHeight;
        }
        mNoticeView.setText(text);
        mNoticeView.setVisibility(0);
        mNoticePanel.setBackgroundColor(mBeginColor);
        mNoticePanel.setVisibility(0);
        return;
        mNoticeView.getLayoutParams()).topMargin = 0;
        mNoticeView = ((TextView)mSlideContent.findViewById(R.id.noticeView));
      }
    }
    
    private boolean setupCustom()
    {
      if (mCustomView != null)
      {
        if (mCustomView.getParent() == mCustom) {
          mCustom.removeView(mCustomView);
        }
        mCustom.addView(mCustomView);
        ViewGroup.LayoutParams localLayoutParams = mCustomView.getLayoutParams();
        width = -1;
        height = -2;
        mCustom.setVisibility(0);
        return true;
      }
      mCustom.setVisibility(8);
      return false;
    }
    
    private void setupPosition()
    {
      if (mAnchor != null) {
        if (mAnchor == null) {
          break label59;
        }
      }
      label59:
      for (View localView = (View)mAnchor.get();; localView = null)
      {
        if ((localView != null) && (localView.getParent() != null))
        {
          WindowManager.LayoutParams localLayoutParams = new WindowManager.LayoutParams();
          findViewPosition(localView, localLayoutParams);
          mTop = y;
        }
        return;
      }
    }
    
    private void setupView()
    {
      if (!setupCustom()) {
        setupContent();
      }
      for (;;)
      {
        if (mOnClickNoticeListener != null)
        {
          mSlideContent.setOnClickListener(mContentClickListener);
          mNoticeClickMsg = mNoticeHandler.obtainMessage(0, mOnClickNoticeListener);
        }
        return;
        mNoticePanel.setVisibility(8);
      }
    }
    
    private void unregisterForScrollChanged()
    {
      Object localObject = mAnchor;
      if (localObject != null) {}
      for (localObject = (View)((WeakReference)localObject).get();; localObject = null)
      {
        if (localObject != null) {
          ((View)localObject).getViewTreeObserver().removeOnScrollChangedListener(mOnScrollChangedListener);
        }
        mAnchor = null;
        return;
      }
    }
    
    private void update(int paramInt1, int paramInt2)
    {
      if ((!showing) || (mSlideView == null) || (mSlideView.getParent() == null)) {
        return;
      }
      mTop = paramInt2;
      mParams.y = mTop;
      mWManager.updateViewLayout(mSlideView, mParams);
    }
    
    public int getHeight()
    {
      return mSlideY;
    }
    
    public CharSequence getText()
    {
      return text;
    }
    
    public int getTop()
    {
      return mTop;
    }
    
    public void handleHide()
    {
      if (!showing) {
        return;
      }
      if (mSlideY < 0)
      {
        if (mAnimationExecutor.isRunning()) {
          mAnimationExecutor.cancel();
        }
        destroy();
        return;
      }
      mAnimationExecutor.start(mSlideY, 0);
      mAnimationType = 1;
    }
    
    public boolean handleShow()
    {
      if (showing) {
        return false;
      }
      prepareNotice(mParams);
      setupPosition();
      setupView();
      mParams.x = mLeft;
      mParams.y = mTop;
      mSlideContent.setVisibility(0);
      invokeNotice(mParams);
      mSlideContent.getViewTreeObserver().addOnPreDrawListener(new SlideNotice.SlideViewController.5(this));
      showing = true;
      return true;
    }
    
    public void hide()
    {
      try
      {
        mHandler.post(mHide);
        return;
      }
      catch (Exception localException)
      {
        handleHide();
      }
    }
    
    public boolean isShowing()
    {
      return showing;
    }
    
    public void resetTop()
    {
      if (isBelowDefaultActionBar)
      {
        mTop = (mActionBarHeight + mStatusBarHeight);
        return;
      }
      mTop = 0;
    }
    
    public void setAnchor(View paramView)
    {
      if (paramView != null)
      {
        unregisterForScrollChanged();
        mAnchor = new WeakReference(paramView);
        paramView = paramView.getViewTreeObserver();
        if (paramView != null) {
          paramView.addOnScrollChangedListener(mOnScrollChangedListener);
        }
      }
    }
    
    public void setBeginColor(int paramInt)
    {
      mBeginColor = paramInt;
    }
    
    public void setBelowDefaultActionBar(boolean paramBoolean)
    {
      isBelowDefaultActionBar = paramBoolean;
      if (isBelowDefaultActionBar)
      {
        mTop = (mActionBarHeight + mStatusBarHeight);
        return;
      }
      mTop = 0;
    }
    
    public void setCustomView(View paramView)
    {
      mCustomView = paramView;
    }
    
    public void setEndColor(int paramInt)
    {
      mEndColor = paramInt;
    }
    
    public void setGravity(int paramInt)
    {
      mNoticePanel.setGravity(paramInt);
    }
    
    public void setHeight(int paramInt)
    {
      int i = ResourceUtils.getGradualColor(mBeginColor, mEndColor, paramInt / (mSlidHeight + 0.1F), 1);
      mNoticePanel.setBackgroundColor(i);
      i = ResourceUtils.getGradualColor(16777215, -1, paramInt / (mSlidHeight + 0.1F), 1);
      mNoticeView.setTextColor(i);
      mSlideY = paramInt;
      mSlideContent.setTranslationY(mSlideY - mSlidHeight);
    }
    
    public void setIsOverlaidByStatusBar(boolean paramBoolean)
    {
      if (!isSupportedImmersedStatusBar) {
        return;
      }
      mIsOverlaidByStatusBar = paramBoolean;
      mIsOverlaidByStatusBarSet = true;
    }
    
    public void setLeft(int paramInt)
    {
      mLeft = paramInt;
    }
    
    public void setNoticeHeight(int paramInt)
    {
      mNoticeHeight = paramInt;
    }
    
    public void setNoticeWidth(int paramInt)
    {
      mNoticeWidth = paramInt;
    }
    
    public void setOnClickNoticeListener(SlideNotice.OnClickNoticeListener paramOnClickNoticeListener)
    {
      mOnClickNoticeListener = paramOnClickNoticeListener;
    }
    
    public void setText(CharSequence paramCharSequence)
    {
      text = paramCharSequence;
      if (mNoticeView != null) {
        mNoticeView.setText(paramCharSequence);
      }
    }
    
    public void setTop(int paramInt)
    {
      mTop = paramInt;
    }
    
    public void show()
    {
      try
      {
        mHandler.post(mShow);
        return;
      }
      catch (Exception localException)
      {
        handleShow();
      }
    }
    
    class AnimationExecutor
    {
      private ValueAnimator anim;
      private SlideNotice.SlideViewController.SlidingAnimatorListener animatorListener = new SlideNotice.SlideViewController.SlidingAnimatorListener(SlideNotice.SlideViewController.this, null);
      
      public AnimationExecutor() {}
      
      public void cancel()
      {
        if ((anim != null) && (anim.isRunning())) {
          anim.cancel();
        }
      }
      
      public boolean isRunning()
      {
        if (anim == null) {
          return false;
        }
        return anim.isRunning();
      }
      
      public void start(int paramInt1, int paramInt2)
      {
        if ((anim != null) && (anim.isRunning())) {
          anim.cancel();
        }
        anim = ObjectAnimator.ofInt(mSlideViewController, "height", new int[] { paramInt1, paramInt2 });
        anim.setDuration(300L);
        anim.addListener(animatorListener);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();
      }
    }
    
    class SlidingAnimatorListener
      implements Animator.AnimatorListener
    {
      private SlidingAnimatorListener() {}
      
      public void onAnimationCancel(Animator paramAnimator) {}
      
      public void onAnimationEnd(Animator paramAnimator)
      {
        if (mAnimationType == 0) {}
        while (mSlideY > 0) {
          return;
        }
        SlideNotice.SlideViewController.access$1402(SlideNotice.SlideViewController.this, -1);
        mSlideViewController.destroy();
      }
      
      public void onAnimationRepeat(Animator paramAnimator) {}
      
      public void onAnimationStart(Animator paramAnimator)
      {
        SlideNotice.SlideViewController.access$1302(mSlideViewController, true);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.app.SlideNotice
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */