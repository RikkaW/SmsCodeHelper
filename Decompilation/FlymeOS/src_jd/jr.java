import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.android.mms.fragmentstyle.FavoriteActivity;

public class jr
  extends Handler
{
  public jr(FavoriteActivity paramFavoriteActivity) {}
  
  public void handleMessage(Message paramMessage)
  {
    boolean bool1 = true;
    vv localvv = (vv)obj;
    if (localvv != null) {}
    switch (what)
    {
    case 3: 
    default: 
      Log.w("FavoriteActivity", "Unknown message: " + what);
    case 2: 
      do
      {
        return;
        switch (v)
        {
        case 6: 
        default: 
          return;
        case 0: 
        case 1: 
        case 2: 
        case 3: 
        case 4: 
        case 5: 
        case 7: 
          if (!localvv.D()) {
            break label191;
          }
          if (v == 1)
          {
            wd.a(a, wd.m(Z));
            return;
          }
          break;
        }
      } while (v != 2);
      wd.b(a, wd.m(Z));
      return;
      label191:
      FavoriteActivity localFavoriteActivity = a;
      Uri localUri = t;
      lr locallr = B;
      boolean bool2 = localvv.y();
      int i = v;
      pt localpt = a.b();
      if (arg1 == 1) {}
      for (;;)
      {
        wd.a(localFavoriteActivity, localUri, locallr, bool2, i, 0, localpt, bool1, f, arg2, A);
        return;
        bool1 = false;
      }
      wd.b(a, R);
      return;
    }
    FavoriteActivity.a(a, localvv);
  }
}

/* Location:
 * Qualified Name:     jr
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */