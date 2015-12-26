import android.location.Country;
import android.location.ICountryDetector;
import android.location.ICountryListener.Stub;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.android.mms.MmsApp.b;
import java.util.HashMap;

public class ly
{
  private final ICountryDetector a;
  private final HashMap<MmsApp.b, ly.a> b;
  
  public ly(ICountryDetector paramICountryDetector)
  {
    a = paramICountryDetector;
    b = new HashMap();
  }
  
  public Country a()
  {
    try
    {
      Country localCountry = a.detectCountry();
      return localCountry;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("CountryDetector", "detectCountry: RemoteException", localRemoteException);
    }
    return null;
  }
  
  public void a(MmsApp.b paramb)
  {
    synchronized (b)
    {
      ly.a locala = (ly.a)b.get(paramb);
      if (locala != null) {}
      try
      {
        b.remove(paramb);
        a.removeCountryListener(locala);
        return;
      }
      catch (RemoteException paramb)
      {
        for (;;)
        {
          Log.e("CountryDetector", "removeCountryListener: RemoteException", paramb);
        }
      }
    }
  }
  
  public void a(MmsApp.b paramb, Looper paramLooper)
  {
    synchronized (b)
    {
      if (!b.containsKey(paramb)) {
        paramLooper = new ly.a(paramb, paramLooper);
      }
      try
      {
        a.addCountryListener(paramLooper);
        b.put(paramb, paramLooper);
        return;
      }
      catch (RemoteException paramb)
      {
        for (;;)
        {
          Log.e("CountryDetector", "addCountryListener: RemoteException", paramb);
        }
      }
    }
  }
  
  static final class a
    extends ICountryListener.Stub
  {
    private final MmsApp.b a;
    private final Handler b;
    
    public a(MmsApp.b paramb, Looper paramLooper)
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
}

/* Location:
 * Qualified Name:     ly
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */