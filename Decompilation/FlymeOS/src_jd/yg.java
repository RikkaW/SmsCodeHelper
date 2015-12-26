import android.view.View;
import android.view.View.OnClickListener;
import com.android.mms.ui.SlideEditorActivity;

public class yg
  implements View.OnClickListener
{
  public yg(SlideEditorActivity paramSlideEditorActivity) {}
  
  public void onClick(View paramView)
  {
    paramView = SlideEditorActivity.b(a).a(SlideEditorActivity.a(a));
    if ((paramView != null) && (paramView.g()))
    {
      wd.a(2131492902, a, 0, 1, true, 0);
      return;
    }
    wd.b(a, 1);
    SlideEditorActivity.f(a);
  }
}

/* Location:
 * Qualified Name:     yg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */