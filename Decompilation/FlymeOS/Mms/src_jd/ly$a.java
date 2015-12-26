import android.location.Country;
import android.location.ICountryListener.Stub;
import android.os.Handler;
import android.os.Looper;
import com.android.mms.MmsApp.b;

final class ly$a
  extends ICountryListener.Stub
{
  private final MmsApp.b a;
  private final Handler b;
  
  public ly$a(MmsApp.b paramb, Looper paramLooper)
  {
    a = paramb;
    if (paramLooper != null)
    {
      b = new Handler(paramLooper);
      return;
    }
    b = new Handler();
  }
  
  public void onCountryDetected(Country paramCountry)
  {
    b.post(new lz(this, paramCountry));
  }
}

/* Location:
 * Qualified Name:     ly.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */