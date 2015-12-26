import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;

public class os
  extends ow
  implements Runnable
{
  private Thread a;
  private String p;
  
  public os(Context paramContext, int paramInt, String paramString1, String paramString2, long paramLong)
  {
    super(paramContext, paramInt, paramLong);
    g = Uri.parse(paramString2);
    p = paramString1;
    c = paramString2;
    l = "";
    a(or.a(paramContext));
  }
  
  public void a()
  {
    a = new Thread(this, "SendTransaction");
    a.start();
  }
  
  public int c()
  {
    return 2;
  }
  
  public void run()
  {
    Log.d("SendTransaction", "SendTransaction: run");
    try
    {
      Object localObject = new Intent("com.android.mms.transaction.TRANSACION_PROCESSED");
      ((Intent)localObject).putExtra("subscription", f);
      ((Intent)localObject).putExtra("bundle_uri", g.toString());
      localObject = PendingIntent.getBroadcast(b, 0, (Intent)localObject, 134217728);
      Log.d("SendTransaction", "send MMS with param, mUri = " + g + ", subId" + f);
      SmsManager localSmsManager = aac.c(f);
      Uri localUri = g;
      Bundle localBundle = ga.F();
      aau.a(localSmsManager, "sendStoredMultimediaMessage", new Class[] { Uri.class, Bundle.class, PendingIntent.class }, new Object[] { localUri, localBundle, localObject });
      return;
    }
    catch (Throwable localThrowable)
    {
      Log.e("SendTransaction", Log.getStackTraceString(localThrowable));
      f().a(2);
      f().a(g);
      d();
    }
  }
}

/* Location:
 * Qualified Name:     os
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */