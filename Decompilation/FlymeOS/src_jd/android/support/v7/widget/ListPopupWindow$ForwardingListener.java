package android.support.v7.widget;

import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewParent;

public abstract class ListPopupWindow$ForwardingListener
  implements View.OnTouchListener
{
  private int mActivePointerId;
  private Runnable mDisallowIntercept;
  private boolean mForwarding;
  private final int mLongPressTimeout;
  private final float mScaledTouchSlop;
  private final View mSrc;
  private final int mTapTimeout;
  private final int[] mTmpLocation = new int[2];
  private Runnable mTriggerLongPress;
  private boolean mWasLongPress;
  
  public ListPopupWindow$ForwardingListener(View paramView)
  {
    mSrc = paramView;
    mScaledTouchSlop = ViewConfiguration.get(paramView.getContext()).getScaledTouchSlop();
    mTapTimeout = ViewConfiguration.getTapTimeout();
    mLongPressTimeout = ((mTapTimeout + ViewConfiguration.getLongPressTimeout()) / 2);
  }
  
  private void clearCallbacks()
  {
    if (mTriggerLongPress != null) {
      mSrc.removeCallbacks(mTriggerLongPress);
    }
    if (mDisallowIntercept != null) {
      mSrc.removeCallbacks(mDisallowIntercept);
    }
  }
  
  private void onLongPress()
  {
    clearCallbacks();
    View localView = mSrc;
    if ((!localView.isEnabled()) || (localView.isLongClickable())) {}
    while (!onForwardingStarted()) {
      return;
    }
    if (localView.getParent() != null) {
      localView.getParent().requestDisallowInterceptTouchEvent(true);
    }
    long l = SystemClock.uptimeMillis();
    MotionEvent localMotionEvent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
    localView.onTouchEvent(localMotionEvent);
    localMotionEvent.recycle();
    mForwarding = true;
    mWasLongPress = true;
  }
  
  private boolean onTouchForwarded(MotionEvent paramMotionEvent)
  {
    boolean bool1 = true;
    View localView = mSrc;
    Object localObject = getPopup();
    if ((localObject == null) || (!((ListPopupWindow)localObject).isShowing())) {}
    do
    {
      return false;
      localObject = ListPopupWindow.access$600((ListPopupWindow)localObject);
    } while ((localObject == null) || (!((ListPopupWindow.DropDownListView)localObject).isShown()));
    MotionEvent localMotionEvent = MotionEvent.obtainNoHistory(paramMotionEvent);
    toGlobalMotionEvent(localView, localMotionEvent);
    toLocalMotionEvent((View)localObject, localMotionEvent);
    boolean bool2 = ((ListPopupWindow.DropDownListView)localObject).onForwardedEvent(localMotionEvent, mActivePointerId);
    localMotionEvent.recycle();
    int i = MotionEventCompat.getActionMasked(paramMotionEvent);
    if ((i != 1) && (i != 3))
    {
      i = 1;
      if ((!bool2) || (i == 0)) {
        break label124;
      }
    }
    for (;;)
    {
      return bool1;
      i = 0;
      break;
      label124:
      bool1 = false;
    }
  }
  
  private boolean onTouchObserved(MotionEvent paramMotionEvent)
  {
    View localView = mSrc;
    if (!localView.isEnabled()) {}
    int i;
    do
    {
      return false;
      switch (MotionEventCompat.getActionMasked(paramMotionEvent))
      {
      default: 
        return false;
      case 0: 
        mActivePointerId = paramMotionEvent.getPointerId(0);
        mWasLongPress = false;
        if (mDisallowIntercept == null) {
          mDisallowIntercept = new DisallowIntercept(null);
        }
        localView.postDelayed(mDisallowIntercept, mTapTimeout);
        if (mTriggerLongPress == null) {
          mTriggerLongPress = new TriggerLongPress(null);
        }
        localView.postDelayed(mTriggerLongPress, mLongPressTimeout);
        return false;
      case 2: 
        i = paramMotionEvent.findPointerIndex(mActivePointerId);
      }
    } while ((i < 0) || (pointInView(localView, paramMotionEvent.getX(i), paramMotionEvent.getY(i), mScaledTouchSlop)));
    clearCallbacks();
    if (localView.getParent() != null) {
      localView.getParent().requestDisallowInterceptTouchEvent(true);
    }
    return true;
    clearCallbacks();
    return false;
  }
  
  private static boolean pointInView(View paramView, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return (paramFloat1 >= -paramFloat3) && (paramFloat2 >= -paramFloat3) && (paramFloat1 < paramView.getRight() - paramView.getLeft() + paramFloat3) && (paramFloat2 < paramView.getBottom() - paramView.getTop() + paramFloat3);
  }
  
  private boolean toGlobalMotionEvent(View paramView, MotionEvent paramMotionEvent)
  {
    int[] arrayOfInt = mTmpLocation;
    paramView.getLocationOnScreen(arrayOfInt);
    paramMotionEvent.offsetLocation(arrayOfInt[0], arrayOfInt[1]);
    return true;
  }
  
  private boolean toLocalMotionEvent(View paramView, MotionEvent paramMotionEvent)
  {
    int[] arrayOfInt = mTmpLocation;
    paramView.getLocationOnScreen(arrayOfInt);
    paramMotionEvent.offsetLocation(-arrayOfInt[0], -arrayOfInt[1]);
    return true;
  }
  
  public abstract ListPopupWindow getPopup();
  
  public boolean onForwardingStarted()
  {
    ListPopupWindow localListPopupWindow = getPopup();
    if ((localListPopupWindow != null) && (!localListPopupWindow.isShowing())) {
      localListPopupWindow.show();
    }
    return true;
  }
  
  public boolean onForwardingStopped()
  {
    ListPopupWindow localListPopupWindow = getPopup();
    if ((localListPopupWindow != null) && (localListPopupWindow.isShowing())) {
      localListPopupWindow.dismiss();
    }
    return true;
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    boolean bool2 = false;
    boolean bool3 = mForwarding;
    if (bool3)
    {
      if (mWasLongPress) {
        bool1 = onTouchForwarded(paramMotionEvent);
      }
      for (;;)
      {
        mForwarding = bool1;
        if (!bool1)
        {
          bool1 = bool2;
          if (!bool3) {}
        }
        else
        {
          bool1 = true;
        }
        return bool1;
        if ((onTouchForwarded(paramMotionEvent)) || (!onForwardingStopped())) {
          bool1 = true;
        } else {
          bool1 = false;
        }
      }
    }
    if ((onTouchObserved(paramMotionEvent)) && (onForwardingStarted())) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      if (bool1)
      {
        long l = SystemClock.uptimeMillis();
        paramView = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
        mSrc.onTouchEvent(paramView);
        paramView.recycle();
      }
      break;
    }
  }
  
  class DisallowIntercept
    implements Runnable
  {
    private DisallowIntercept() {}
    
    public void run()
    {
      ViewParent localViewParent = mSrc.getParent();
      if (localViewParent != null) {
        localViewParent.requestDisallowInterceptTouchEvent(true);
      }
    }
  }
  
  class TriggerLongPress
    implements Runnable
  {
    private TriggerLongPress() {}
    
    public void run()
    {
      ListPopupWindow.ForwardingListener.this.onLongPress();
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.ListPopupWindow.ForwardingListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */