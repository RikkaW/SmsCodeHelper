import android.graphics.Rect;
import android.view.TouchDelegate;
import android.view.View;

final class wr
  implements Runnable
{
  wr(View paramView1, int paramInt1, int paramInt2, int paramInt3, int paramInt4, View paramView2) {}
  
  public void run()
  {
    Rect localRect = new Rect();
    a.getHitRect(localRect);
    left -= b;
    top -= c;
    right += d;
    bottom += e;
    f.setTouchDelegate(new TouchDelegate(localRect, a));
  }
}

/* Location:
 * Qualified Name:     wr
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */