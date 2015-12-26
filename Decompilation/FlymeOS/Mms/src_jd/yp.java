import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.MediaController;
import com.android.mms.ui.SlideView;

public class yp
  implements View.OnTouchListener
{
  public yp(SlideView paramSlideView) {}
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() == 1) && (SlideView.g(a) != null))
    {
      if (!SlideView.g(a).isShowing()) {
        break label43;
      }
      SlideView.g(a).hide();
    }
    for (;;)
    {
      return false;
      label43:
      SlideView.g(a).show();
    }
  }
}

/* Location:
 * Qualified Name:     yp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */