import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

public class aiq
{
  private Context a;
  private aiq.a b;
  private air c;
  private ais d;
  
  public aiq(Context paramContext, air paramair)
  {
    a = paramContext;
    c = paramair;
    b = new aiq.a(null);
  }
  
  public void a()
  {
    Intent localIntent = new Intent();
    localIntent.setAction("com.meizu.flymesms.BIND");
    localIntent.setPackage("com.meizu.cloud");
    a.bindService(localIntent, b, 1);
  }
  
  public boolean a(aio paramaio)
  {
    if (d != null)
    {
      d.a(paramaio);
      return true;
    }
    a();
    return false;
  }
  
  public boolean a(aip paramaip)
  {
    if (d != null)
    {
      d.a("com.android.mms", paramaip);
      return true;
    }
    a();
    return false;
  }
  
  public boolean a(String paramString)
  {
    if (d != null)
    {
      d.a(paramString);
      return true;
    }
    a();
    return false;
  }
  
  public boolean a(String paramString1, String paramString2, PendingIntent paramPendingIntent, String paramString3, boolean paramBoolean)
  {
    if (d != null)
    {
      d.a(paramString1, paramString2, paramPendingIntent, paramString3, paramBoolean);
      return true;
    }
    a();
    return false;
  }
  
  public boolean a(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong)
  {
    if (d != null)
    {
      d.a(paramString1, paramString2, paramString3, paramString4, paramLong);
      return true;
    }
    a();
    return false;
  }
  
  public boolean a(String[] paramArrayOfString, PendingIntent paramPendingIntent)
  {
    if (d != null)
    {
      d.a(paramArrayOfString, paramPendingIntent);
      return true;
    }
    a();
    return false;
  }
  
  public boolean a(String[] paramArrayOfString, String paramString1, byte[] paramArrayOfByte, String paramString2, boolean paramBoolean, String paramString3)
  {
    if (d != null)
    {
      d.a(paramArrayOfString, paramString1, paramArrayOfByte, paramString2, paramBoolean, paramString3);
      return true;
    }
    a();
    return false;
  }
  
  public boolean b(aio paramaio)
  {
    if (d != null)
    {
      d.b(paramaio);
      return true;
    }
    return false;
  }
  
  public boolean b(aip paramaip)
  {
    if (d != null)
    {
      d.b("com.android.mms", paramaip);
      return true;
    }
    return false;
  }
  
  class a
    implements ServiceConnection
  {
    private a() {}
    
    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      aiq.a(aiq.this, ais.a.a(paramIBinder));
      if (aiq.a(aiq.this) != null) {
        aiq.a(aiq.this).a();
      }
    }
    
    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      aiq.a(aiq.this, null);
      if (aiq.a(aiq.this) != null) {
        aiq.a(aiq.this).b();
      }
    }
  }
}

/* Location:
 * Qualified Name:     aiq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */