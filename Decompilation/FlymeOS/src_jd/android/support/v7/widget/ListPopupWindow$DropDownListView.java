package android.support.v7.widget;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.appcompat.R.attr;
import android.support.v7.internal.widget.ListViewCompat;
import android.view.MotionEvent;
import android.view.View;

class ListPopupWindow$DropDownListView
  extends ListViewCompat
{
  private ViewPropertyAnimatorCompat mClickAnimation;
  private boolean mDrawsInPressedState;
  private boolean mHijackFocus;
  private boolean mListSelectionHidden;
  private ListViewAutoScrollHelper mScrollHelper;
  
  public ListPopupWindow$DropDownListView(Context paramContext, boolean paramBoolean)
  {
    super(paramContext, null, R.attr.dropDownListViewStyle);
    mHijackFocus = paramBoolean;
    setCacheColorHint(0);
  }
  
  private void clearPressedItem()
  {
    mDrawsInPressedState = false;
    setPressed(false);
    drawableStateChanged();
    if (mClickAnimation != null)
    {
      mClickAnimation.cancel();
      mClickAnimation = null;
    }
  }
  
  private void clickPressedItem(View paramView, int paramInt)
  {
    performItemClick(paramView, paramInt, getItemIdAtPosition(paramInt));
  }
  
  private void setPressedItem(View paramView, int paramInt, float paramFloat1, float paramFloat2)
  {
    mDrawsInPressedState = true;
    setPressed(true);
    layoutChildren();
    setSelection(paramInt);
    positionSelectorLikeTouchCompat(paramInt, paramView, paramFloat1, paramFloat2);
    setSelectorEnabled(false);
    refreshDrawableState();
  }
  
  public boolean hasFocus()
  {
    return (mHijackFocus) || (super.hasFocus());
  }
  
  public boolean hasWindowFocus()
  {
    return (mHijackFocus) || (super.hasWindowFocus());
  }
  
  public boolean isFocused()
  {
    return (mHijackFocus) || (super.isFocused());
  }
  
  public boolean isInTouchMode()
  {
    return ((mHijackFocus) && (mListSelectionHidden)) || (super.isInTouchMode());
  }
  
  public boolean onForwardedEvent(MotionEvent paramMotionEvent, int paramInt)
  {
    int i = MotionEventCompat.getActionMasked(paramMotionEvent);
    switch (i)
    {
    default: 
      paramInt = 0;
    case 3: 
      for (bool = true;; bool = false)
      {
        label37:
        if ((!bool) || (paramInt != 0)) {
          clearPressedItem();
        }
        if (!bool) {
          break;
        }
        if (mScrollHelper == null) {
          mScrollHelper = new ListViewAutoScrollHelper(this);
        }
        mScrollHelper.setEnabled(true);
        mScrollHelper.onTouch(this, paramMotionEvent);
        label93:
        return bool;
        paramInt = 0;
      }
    }
    for (boolean bool = false;; bool = true)
    {
      int j = paramMotionEvent.findPointerIndex(paramInt);
      if (j < 0)
      {
        paramInt = 0;
        bool = false;
        break label37;
      }
      paramInt = (int)paramMotionEvent.getX(j);
      j = (int)paramMotionEvent.getY(j);
      int k = pointToPosition(paramInt, j);
      if (k == -1)
      {
        paramInt = 1;
        break label37;
      }
      View localView = getChildAt(k - getFirstVisiblePosition());
      setPressedItem(localView, k, paramInt, j);
      if (i != 1) {
        break;
      }
      clickPressedItem(localView, k);
      break;
      if (mScrollHelper == null) {
        break label93;
      }
      mScrollHelper.setEnabled(false);
      return bool;
    }
  }
  
  protected boolean touchModeDrawsInPressedStateCompat()
  {
    return (mDrawsInPressedState) || (super.touchModeDrawsInPressedStateCompat());
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.ListPopupWindow.DropDownListView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */