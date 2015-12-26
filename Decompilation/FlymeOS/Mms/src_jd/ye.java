import android.os.Handler;
import com.android.mms.ui.BasicSlideEditorView.a;
import com.android.mms.ui.SlideEditorActivity;

public class ye
  implements BasicSlideEditorView.a
{
  public ye(SlideEditorActivity paramSlideEditorActivity) {}
  
  public void a(String paramString)
  {
    if (!a.isFinishing()) {}
    try
    {
      SlideEditorActivity.c(a).a(SlideEditorActivity.a(a), paramString);
      SlideEditorActivity.f(a);
      return;
    }
    catch (fk paramString)
    {
      for (;;)
      {
        SlideEditorActivity.h(a).removeCallbacks(SlideEditorActivity.g(a));
        SlideEditorActivity.h(a).postDelayed(SlideEditorActivity.g(a), 300L);
      }
    }
  }
}

/* Location:
 * Qualified Name:     ye
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */