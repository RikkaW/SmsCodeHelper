import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.android.mms.location.CustomeAutoCompleteTextView;

public class ks
  implements View.OnTouchListener
{
  public ks(CustomeAutoCompleteTextView paramCustomeAutoCompleteTextView) {}
  
  private boolean a(View paramView, int paramInt1, int paramInt2)
  {
    Rect localRect = new Rect();
    paramView.getDrawingRect(localRect);
    int[] arrayOfInt = new int[2];
    paramView.getLocationOnScreen(arrayOfInt);
    left = arrayOfInt[0];
    top = arrayOfInt[1];
    right += arrayOfInt[0];
    int i = bottom;
    bottom = (arrayOfInt[1] + i);
    return localRect.contains(paramInt1, paramInt2);
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    return (paramMotionEvent.getAction() == 4) && (a(a, (int)paramMotionEvent.getRawX(), (int)paramMotionEvent.getRawY()));
  }
}

/* Location:
 * Qualified Name:     ks
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */