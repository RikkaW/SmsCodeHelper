import android.widget.TextView;
import com.android.mms.ui.SlideView.b;
import com.android.mms.ui.SlideshowActivity;

public class yu
  implements SlideView.b
{
  public yu(SlideshowActivity paramSlideshowActivity) {}
  
  public void a(int paramInt)
  {
    TextView localTextView = SlideshowActivity.h(a);
    StringBuilder localStringBuilder = new StringBuilder();
    if (SlideshowActivity.g(a) != null) {}
    for (String str = SlideshowActivity.g(a);; str = "")
    {
      localTextView.setText(str + "(" + String.valueOf(paramInt + 1) + "/" + String.valueOf(SlideshowActivity.b(a)) + ")");
      return;
    }
  }
}

/* Location:
 * Qualified Name:     yu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */