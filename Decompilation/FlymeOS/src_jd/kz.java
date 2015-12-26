import android.view.MotionEvent;
import com.amap.api.maps2d.AMap.OnMapTouchListener;
import com.android.mms.location.amap.GetLocationView;

public class kz
  implements AMap.OnMapTouchListener
{
  public kz(GetLocationView paramGetLocationView) {}
  
  public void onTouch(MotionEvent paramMotionEvent)
  {
    int i = (int)paramMotionEvent.getX();
    int j = (int)paramMotionEvent.getY();
    switch (paramMotionEvent.getAction())
    {
    }
    do
    {
      return;
      GetLocationView.a(a, i);
      GetLocationView.b(a, j);
      GetLocationView.b(a, a.getMapCenter());
      a.a();
      return;
    } while (((Math.abs(GetLocationView.q(a) - i) <= GetLocationView.n()) && (Math.abs(GetLocationView.r(a) - j) <= GetLocationView.n())) || (kx.a(GetLocationView.s(a), a.getMapCenter())));
    a.setMoveByUser(true);
  }
}

/* Location:
 * Qualified Name:     kz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */