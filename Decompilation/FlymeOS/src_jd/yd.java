import android.view.View;
import android.view.View.OnClickListener;
import com.android.mms.ui.SlideEditorActivity;

public class yd
  implements View.OnClickListener
{
  public yd(SlideEditorActivity paramSlideEditorActivity) {}
  
  public void onClick(View paramView)
  {
    if ((SlideEditorActivity.a(a) >= 0) && (SlideEditorActivity.a(a) < SlideEditorActivity.b(a).size()))
    {
      SlideEditorActivity.c(a).b(SlideEditorActivity.a(a));
      int i = SlideEditorActivity.b(a).size();
      if (i > 0)
      {
        if (SlideEditorActivity.a(a) >= i) {
          SlideEditorActivity.d(a);
        }
        SlideEditorActivity.e(a);
        SlideEditorActivity.f(a);
      }
    }
    else
    {
      return;
    }
    a.finish();
  }
}

/* Location:
 * Qualified Name:     yd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */