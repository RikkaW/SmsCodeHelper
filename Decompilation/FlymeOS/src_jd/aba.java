import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import java.util.ArrayList;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;

public class aba
{
  public static final Uri a = Uri.parse("content://com.meizu.cloud/recvtype_bind_sim");
  private static aba f;
  private Context b;
  private Handler c;
  private aba.b d;
  private int e = -1;
  private aiq g = null;
  private aio h = new abb(this);
  private air i = new abc(this);
  
  private aba(Context paramContext, aba.b paramb)
  {
    b = paramContext;
    c = new Handler();
    d = paramb;
    e();
  }
  
  public static aba a()
  {
    if (f == null) {
      throw new IllegalStateException("Uninitialized.");
    }
    return f;
  }
  
  public static ArrayList<abd> a(Intent paramIntent)
  {
    localArrayList = new ArrayList();
    try
    {
      paramIntent = new JSONArray(paramIntent.getStringExtra("results"));
      int j = 0;
      while (j < paramIntent.length())
      {
        localArrayList.add(new abd(paramIntent.getJSONObject(j)));
        j += 1;
      }
      return localArrayList;
    }
    catch (JSONException paramIntent)
    {
      paramIntent.printStackTrace();
    }
  }
  
  public static void a(Context paramContext, aba.b paramb)
  {
    if (f != null) {
      Log.w("SipSmsManager", "Already initialized.");
    }
    f = new aba(paramContext, paramb);
  }
  
  private void e()
  {
    if (g == null) {}
    try
    {
      g = new aiq(b, i);
      g.a();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private aiq f()
  {
    if (g != null) {
      return g;
    }
    Log.e("SipSmsManager", "getMzCloudService is null begin bindSmsService");
    e();
    return g;
  }
  
  public void a(aio paramaio)
  {
    Log.e("SipSmsManager", "registerCallback");
    long l = System.currentTimeMillis();
    try
    {
      if (f() != null) {
        f().a(paramaio);
      }
      wd.a("SipSmsManager", "registerCallback", l);
      return;
    }
    catch (Exception paramaio)
    {
      for (;;)
      {
        Log.e("SipSmsManager", "registerCallback error" + paramaio.getMessage());
      }
    }
  }
  
  public void a(aip paramaip)
  {
    Log.e("SipSmsManager", "registerFileListener");
    long l = System.currentTimeMillis();
    try
    {
      if (f() != null) {
        f().a(paramaip);
      }
      wd.a("SipSmsManager", "registerFileListener", l);
      return;
    }
    catch (Exception paramaip)
    {
      for (;;)
      {
        Log.e("SipSmsManager", "registerFileListener error" + paramaip.getMessage());
      }
    }
  }
  
  public void a(Context paramContext, String[] paramArrayOfString)
  {
    Intent localIntent = new Intent("com.android.mms.sip.dest_check_popup");
    localIntent.setData(Uri.parse("content://sipmessagedestcheck/"));
    paramContext = PendingIntent.getBroadcast(paramContext, 0, localIntent, 134217728);
    boolean bool = a().a(paramArrayOfString, paramContext);
    Log.d("SipSmsManager", "checkSipOnline result = " + bool);
  }
  
  public void a(String paramString1, String paramString2, PendingIntent paramPendingIntent, String paramString3, int paramInt, boolean paramBoolean)
  {
    long l = System.currentTimeMillis();
    paramString2 = a(paramString2);
    Log.e("SipSmsManager", "sendSipTextMessage, fragment size is " + paramString2.length);
    for (;;)
    {
      try
      {
        if (f() != null)
        {
          paramInt = 0;
          if (paramInt < paramString2.length) {
            if (paramInt == paramString2.length - 1)
            {
              f().a(paramString1, paramString2[paramInt], paramPendingIntent, paramString3, paramBoolean);
            }
            else
            {
              String str = UUID.randomUUID().toString();
              f().a(paramString1, paramString2[paramInt], null, str, false);
            }
          }
        }
      }
      catch (Exception paramString1)
      {
        Log.e("SipSmsManager", "sendSipTextMessage error" + paramString1.getMessage());
      }
      for (;;)
      {
        wd.a("SipSmsManager", "sendSipTextMessage", l);
        return;
        Log.e("SipSmsManager", "getMzCloudService() is return null!");
      }
      paramInt += 1;
    }
  }
  
  public boolean a(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong)
  {
    Log.e("SipSmsManager", "getFlymeMMS");
    long l = System.currentTimeMillis();
    try
    {
      if (f() != null) {
        bool = f().a(paramString1, paramString2, paramString3, paramString4, paramLong);
      }
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        try
        {
          Log.e("SipSmsManager", "getFlymeMMS result = " + bool);
          wd.a("SipSmsManager", "getFlymeMMS", l);
          return bool;
        }
        catch (Exception paramString1)
        {
          continue;
        }
        paramString1 = paramString1;
        boolean bool = false;
        Log.e("SipSmsManager", "getFlymeMMS error" + paramString1.getMessage());
        continue;
        bool = false;
      }
    }
  }
  
  public boolean a(String[] paramArrayOfString, PendingIntent paramPendingIntent)
  {
    Log.d("SipSmsManager", "checkSipOnlineAsync");
    long l = System.currentTimeMillis();
    try
    {
      if (f() != null)
      {
        boolean bool = f().a(paramArrayOfString, paramPendingIntent);
        return bool;
      }
    }
    catch (Exception paramArrayOfString)
    {
      Log.e("SipSmsManager", "checkSipDestAddrIsAvailable error" + paramArrayOfString.getMessage());
      wd.a("SipSmsManager", "checkSipOnlineAsync", l);
    }
    return false;
  }
  
  public boolean a(String[] paramArrayOfString, String paramString1, byte[] paramArrayOfByte, String paramString2, boolean paramBoolean, String paramString3)
  {
    Log.e("SipSmsManager", "sendFlymeMMS");
    long l = System.currentTimeMillis();
    try
    {
      if (f() != null)
      {
        paramBoolean = f().a(paramArrayOfString, paramString1, paramArrayOfByte, paramString2, paramBoolean, paramString3);
        wd.a("SipSmsManager", "sendFlymeMMS", l);
        return paramBoolean;
      }
    }
    catch (Exception paramArrayOfString)
    {
      for (;;)
      {
        Log.e("SipSmsManager", "sendFlymeMMS error" + paramArrayOfString.getMessage());
        paramBoolean = false;
      }
    }
  }
  
  public String[] a(String paramString)
  {
    int k = (paramString.length() + 1000 - 1) / 1000;
    String[] arrayOfString = new String[k];
    int m = paramString.length();
    int j = 0;
    if (j < k)
    {
      if (m > (j + 1) * 1000) {
        arrayOfString[j] = paramString.substring(j * 1000, (j + 1) * 1000);
      }
      for (;;)
      {
        j += 1;
        break;
        arrayOfString[j] = paramString.substring(j * 1000, m);
      }
    }
    return arrayOfString;
  }
  
  public void b(aio paramaio)
  {
    Log.e("SipSmsManager", "unregisterCallback");
    long l = System.currentTimeMillis();
    try
    {
      if (g != null) {
        g.b(paramaio);
      }
      wd.a("SipSmsManager", "unregisterCallback", l);
      return;
    }
    catch (Exception paramaio)
    {
      for (;;)
      {
        Log.e("SipSmsManager", "unregisterCallback error" + paramaio.getMessage());
      }
    }
  }
  
  public void b(aip paramaip)
  {
    Log.e("SipSmsManager", "unregisterFileListener");
    long l = System.currentTimeMillis();
    try
    {
      if (g != null) {
        g.b(paramaip);
      }
      wd.a("SipSmsManager", "unregisterFileListener", l);
      return;
    }
    catch (Exception paramaip)
    {
      for (;;)
      {
        Log.e("SipSmsManager", "unregisterFileListener error" + paramaip.getMessage());
      }
    }
  }
  
  public void b(String paramString)
  {
    Log.e("SipSmsManager", "cancelFlymeMMSTransaction");
    long l = System.currentTimeMillis();
    try
    {
      if (f() != null) {
        f().a(paramString);
      }
      wd.a("SipSmsManager", "cancelFlymeMMSTransaction", l);
      return;
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        Log.e("SipSmsManager", "cancelFlymeMMSTransaction error" + paramString.getMessage());
      }
    }
  }
  
  public boolean b()
  {
    return true;
  }
  
  public boolean c()
  {
    return (!wd.j()) || (e == 0);
  }
  
  public boolean d()
  {
    return e == 1;
  }
  
  public static abstract class a
    extends aip
  {}
  
  public static abstract interface b
  {
    public abstract void a(int paramInt);
  }
}

/* Location:
 * Qualified Name:     aba
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */