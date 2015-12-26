import com.android.mms.model.Model;
import com.android.mms.ui.SlideshowEditActivity;

public class yv
  implements lj
{
  public yv(SlideshowEditActivity paramSlideshowEditActivity) {}
  
  public void onModelChanged(Model arg1, boolean paramBoolean)
  {
    synchronized (a)
    {
      SlideshowEditActivity.a(a, true);
      a.setResult(-1, SlideshowEditActivity.a(a));
      SlideshowEditActivity.b(a);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     yv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */