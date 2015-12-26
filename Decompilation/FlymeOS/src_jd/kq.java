import com.android.mms.location.BaseGetLocationView;

public class kq
  implements Runnable
{
  public kq(BaseGetLocationView paramBaseGetLocationView) {}
  
  public void run()
  {
    a.e = true;
    if (a.q) {
      return;
    }
    a.i();
    kf.a(true, 4, "location/BaseGetLocationView", " screen shot time out");
    if (a.b())
    {
      a.a(2131493346, "run");
      return;
    }
    a.a(2131493347, "run");
  }
}

/* Location:
 * Qualified Name:     kq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */