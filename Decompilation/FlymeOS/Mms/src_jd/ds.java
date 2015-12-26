import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class ds
  implements View.OnTouchListener
{
  ds(dn paramdn) {}
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    int i = dn.d(a).findViewById(br.d.pop_layout).getTop();
    int j = (int)paramMotionEvent.getY();
    if ((paramMotionEvent.getAction() == 1) && (j < i)) {
      a.dismiss();
    }
    return true;
  }
}

/* Location:
 * Qualified Name:     ds
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */