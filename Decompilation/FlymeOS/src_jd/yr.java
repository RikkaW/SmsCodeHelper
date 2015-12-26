import android.view.View;
import android.view.View.OnClickListener;
import android.widget.MediaController;
import com.android.mms.ui.SlideshowActivity;

public class yr
  implements View.OnClickListener
{
  public yr(SlideshowActivity paramSlideshowActivity) {}
  
  public void onClick(View paramView)
  {
    if ((SlideshowActivity.a(a) != null) && (SlideshowActivity.d(a) != null)) {
      SlideshowActivity.d(a).show();
    }
    SlideshowActivity.a(a).l();
  }
}

/* Location:
 * Qualified Name:     yr
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */