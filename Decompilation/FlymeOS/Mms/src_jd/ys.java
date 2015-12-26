import android.view.View;
import android.view.View.OnClickListener;
import android.widget.MediaController;
import com.android.mms.ui.SlideshowActivity;

public class ys
  implements View.OnClickListener
{
  public ys(SlideshowActivity paramSlideshowActivity) {}
  
  public void onClick(View paramView)
  {
    if ((SlideshowActivity.a(a) != null) && (SlideshowActivity.d(a) != null)) {
      SlideshowActivity.d(a).show();
    }
    if (SlideshowActivity.a(a).q() <= 1) {
      return;
    }
    SlideshowActivity.a(a).m();
  }
}

/* Location:
 * Qualified Name:     ys
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */