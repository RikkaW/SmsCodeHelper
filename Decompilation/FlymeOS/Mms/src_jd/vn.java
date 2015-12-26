import android.graphics.Bitmap;
import android.view.View;
import com.android.mms.ui.MeizuSlideshowActivity;
import com.android.mms.ui.MeizuSlideshowActivity.a;

public class vn
  implements Runnable
{
  public vn(MeizuSlideshowActivity.a parama, vp.a parama1, View paramView) {}
  
  public void run()
  {
    int i = MeizuSlideshowActivity.a.a(c, a);
    Bitmap localBitmap = null;
    if (i == 4) {
      if (a.e) {
        localBitmap = MeizuSlideshowActivity.a(c.a, a.c.k(), true);
      }
    }
    for (;;)
    {
      c.a.runOnUiThread(new vo(this, i, localBitmap));
      return;
      if (i == 2) {
        localBitmap = MeizuSlideshowActivity.a(c.a, a.c.k(), false);
      }
    }
  }
}

/* Location:
 * Qualified Name:     vn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */