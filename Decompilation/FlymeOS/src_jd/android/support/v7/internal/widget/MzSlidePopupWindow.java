package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

public class MzSlidePopupWindow
  extends AppCompatPopupWindow
  implements View.OnAttachStateChangeListener
{
  private static final int SLIDE_ANIMATION_DURATION = 200;
  private static final String TAG = "MzSlidePopupWindow";
  private boolean isDetachedFromWindow;
  private Drawable mBackground;
  private View mContentView;
  private Context mContext;
  private DismissAnimator mDismissAnimator = new DismissAnimator(null);
  private int[] mDrawingLocation = new int[2];
  private boolean mOnTop;
  private int[] mScreenLocation = new int[2];
  
  public MzSlidePopupWindow(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    mContext = paramContext;
    mBackground = getBackground();
    setBackgroundDrawable(new ColorDrawable(Integer.MIN_VALUE));
    setSupperMzClippingEnabled(true);
  }
  
  private boolean findPosition(View paramView, int paramInt1, int paramInt2, int paramInt3)
  {
    paramInt1 = paramView.getHeight();
    paramView.getLocationInWindow(mDrawingLocation);
    paramView.getLocationOnScreen(mScreenLocation);
    Rect localRect = new Rect();
    paramView.getWindowVisibleDisplayFrame(localRect);
    return bottom - mScreenLocation[1] - paramInt1 - paramInt2 < mScreenLocation[1] - paramInt2 - top;
  }
  
  private void preparePopup()
  {
    setWidth(mContext.getResources().getDisplayMetrics().widthPixels);
    setWindowLayoutMode(-1, -1);
    if (mOnTop)
    {
      FrameLayout.LayoutParams localLayoutParams = (FrameLayout.LayoutParams)mContentView.getLayoutParams();
      gravity = 80;
      mContentView.setLayoutParams(localLayoutParams);
    }
    mContentView.getViewTreeObserver().addOnPreDrawListener(new MzSlidePopupWindow.1(this));
  }
  
  public void dismiss()
  {
    dismiss(true);
  }
  
  public void dismiss(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if ((isShowing()) && (!mDismissAnimator.isRunning())) {
        mDismissAnimator.start();
      }
      return;
    }
    super.dismiss();
  }
  
  public View getContentView()
  {
    return super.getContentView();
  }
  
  public void onViewAttachedToWindow(View paramView)
  {
    isDetachedFromWindow = false;
  }
  
  public void onViewDetachedFromWindow(View paramView)
  {
    isDetachedFromWindow = true;
  }
  
  public void setContentView(View paramView)
  {
    if (isShowing()) {
      return;
    }
    mContentView = paramView;
    if ((mContext == null) && (mContentView != null)) {
      mContext = mContentView.getContext();
    }
    paramView = null;
    if (mContentView != null)
    {
      mContentView.setBackgroundDrawable(mBackground);
      paramView = new SlidePopupViewContainer(mContext);
      FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-1, -2);
      paramView.addView(mContentView, localLayoutParams);
      paramView.addOnAttachStateChangeListener(this);
    }
    super.setContentView(paramView);
  }
  
  public void setSupperMzClippingEnabled(boolean paramBoolean)
  {
    try
    {
      Method localMethod = PopupWindow.class.getDeclaredMethod("setMzClippingEnabled", new Class[] { Boolean.TYPE });
      localMethod.setAccessible(true);
      localMethod.invoke(this, new Object[] { Boolean.valueOf(paramBoolean) });
      return;
    }
    catch (Exception localException) {}
  }
  
  public void showAsDropDown(View paramView, int paramInt1, int paramInt2, int paramInt3)
  {
    mOnTop = findPosition(paramView, paramInt1, paramInt2, paramInt3);
    preparePopup();
    super.showAsDropDown(paramView, paramInt1, paramInt2, paramInt3);
  }
  
  public void supperDismiss()
  {
    if (!isDetachedFromWindow) {
      super.dismiss();
    }
  }
  
  public void update(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.update(paramView, paramInt1, paramInt2, -1, -1);
  }
  
  class DismissAnimator
  {
    private boolean isRunning;
    private Animation mAnimation;
    
    private DismissAnimator() {}
    
    void DismissAnimator() {}
    
    public boolean isRunning()
    {
      return isRunning;
    }
    
    public void start()
    {
      if (mOnTop) {}
      for (int i = mContentView.getMeasuredHeight();; i = -mContentView.getMeasuredHeight())
      {
        mAnimation = new TranslateAnimation(0.0F, 0.0F, 0.0F, i);
        mAnimation.setAnimationListener(new MzSlidePopupWindow.DismissAnimator.1(this));
        mAnimation.setDuration(200L);
        mContentView.startAnimation(mAnimation);
        isRunning = true;
        return;
      }
    }
  }
  
  class SlidePopupViewContainer
    extends FrameLayout
  {
    private static final String TAG = "MzSlidePopupWindow.SlidePopupViewContainer";
    
    public SlidePopupViewContainer(Context paramContext)
    {
      super();
    }
    
    public boolean onTouchEvent(MotionEvent paramMotionEvent)
    {
      if ((paramMotionEvent.getAction() == 0) || (paramMotionEvent.getAction() == 4))
      {
        dismiss(true);
        return true;
      }
      return super.onTouchEvent(paramMotionEvent);
    }
    
    public void sendAccessibilityEvent(int paramInt)
    {
      if (mContentView != null)
      {
        mContentView.sendAccessibilityEvent(paramInt);
        return;
      }
      super.sendAccessibilityEvent(paramInt);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.MzSlidePopupWindow
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */