import android.util.Log;
import com.android.mms.MmsApp;

final class abg
  extends Thread
{
  abg(int paramInt, String paramString) {}
  
  public void run()
  {
    for (;;)
    {
      try
      {
        if (5 == aac.a(a))
        {
          bool = true;
          Log.e("SmsCenterUtils", "setSmsCenterToSim slotId = " + a + ", isSimReady = " + bool);
          if (bool)
          {
            abe.a(MmsApp.c(), b, a);
            abe.b(b, a);
            return;
          }
          Log.e("SmsCenterUtils", "setSmsCenterToSim slotId = " + a + " is not ready");
          return;
        }
      }
      catch (Exception localException)
      {
        return;
      }
      boolean bool = false;
    }
  }
}

/* Location:
 * Qualified Name:     abg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */