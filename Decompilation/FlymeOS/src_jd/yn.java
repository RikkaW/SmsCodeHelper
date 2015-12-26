import android.view.View;
import android.view.View.OnClickListener;
import android.widget.MediaController;
import com.android.mms.ui.SlideView;

public class yn
  implements View.OnClickListener
{
  public yn(SlideView paramSlideView) {}
  
  public void onClick(View paramView)
  {
    if (SlideView.g(a) != null)
    {
      if (SlideView.g(a).isShowing()) {
        SlideView.g(a).hide();
      }
    }
    else {
      return;
    }
    SlideView.g(a).show();
  }
}

/* Location:
 * Qualified Name:     yn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */