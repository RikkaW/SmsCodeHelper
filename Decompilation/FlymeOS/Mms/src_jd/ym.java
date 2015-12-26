import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.ScrollView;
import com.android.mms.ui.SlideView;

public class ym
  extends ScrollView
{
  private int b;
  
  public ym(SlideView paramSlideView, Context paramContext)
  {
    super(paramContext);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = 0;
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (getChildCount() > 0)
    {
      paramInt2 = getChildAt(0).getHeight();
      paramInt3 = getHeight();
      paramInt1 = i;
      if (paramInt3 < paramInt2) {
        paramInt1 = paramInt2 - paramInt3;
      }
      b = paramInt1;
    }
  }
  
  protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {}
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = super.onTouchEvent(paramMotionEvent);
    if ((paramMotionEvent.getAction() == 1) && (SlideView.g(a) != null))
    {
      if (SlideView.g(a).isShowing()) {
        SlideView.g(a).hide();
      }
    }
    else {
      return bool;
    }
    SlideView.g(a).show();
    return bool;
  }
}

/* Location:
 * Qualified Name:     ym
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */