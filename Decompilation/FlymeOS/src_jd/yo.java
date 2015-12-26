import com.android.mms.ui.SlideView;
import com.android.mms.ui.SlideView.a;
import java.util.Comparator;

public class yo
  implements Comparator<SlideView.a>
{
  public yo(SlideView paramSlideView) {}
  
  public int a(SlideView.a parama1, SlideView.a parama2)
  {
    int k = b;
    int i = a;
    int m = b;
    int j = i - a;
    i = j;
    if (j == 0) {
      i = k - m;
    }
    j = i;
    if (i == 0) {
      j = -1;
    }
    return j;
  }
}

/* Location:
 * Qualified Name:     yo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */