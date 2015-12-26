package android.support.v7.internal.widget;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

class MzSlidePopupWindow$SlidePopupViewContainer
  extends FrameLayout
{
  private static final String TAG = "MzSlidePopupWindow.SlidePopupViewContainer";
  
  public MzSlidePopupWindow$SlidePopupViewContainer(MzSlidePopupWindow paramMzSlidePopupWindow, Context paramContext)
  {
    super(paramContext);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() == 0) || (paramMotionEvent.getAction() == 4))
    {
      this$0.dismiss(true);
      return true;
    }
    return super.onTouchEvent(paramMotionEvent);
  }
  
  public void sendAccessibilityEvent(int paramInt)
  {
    if (MzSlidePopupWindow.access$100(this$0) != null)
    {
      MzSlidePopupWindow.access$100(this$0).sendAccessibilityEvent(paramInt);
      return;
    }
    super.sendAccessibilityEvent(paramInt);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.MzSlidePopupWindow.SlidePopupViewContainer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */