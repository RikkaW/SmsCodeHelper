import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.android.mms.ui.ComposeMessageActivity;

public class ss
  extends Handler
{
  public ss(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void handleMessage(Message paramMessage)
  {
    boolean bool1 = true;
    vv localvv = (vv)obj;
    if (localvv != null) {}
    switch (what)
    {
    default: 
      Log.w("Mms/compose", "Unknown message: " + what);
    case 3: 
    case 2: 
      do
      {
        return;
        ComposeMessageActivity.a(a, localvv);
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
            break label203;
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
      label203:
      ComposeMessageActivity localComposeMessageActivity = a;
      Uri localUri = t;
      lr locallr = B;
      boolean bool2 = localvv.y();
      int i = v;
      pt localpt = a.t();
      if (arg1 == 1) {}
      for (;;)
      {
        wd.a(localComposeMessageActivity, localUri, locallr, bool2, i, 0, localpt, bool1, f, arg2, A);
        return;
        bool1 = false;
      }
      wd.b(a, R);
      return;
    }
    ComposeMessageActivity.b(a, localvv);
  }
}

/* Location:
 * Qualified Name:     ss
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */