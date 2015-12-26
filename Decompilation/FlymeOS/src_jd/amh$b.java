import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

class amh$b
{
  private final int a;
  private final String b;
  
  private amh$b(int paramInt, String paramString)
  {
    a = paramInt;
    b = paramString;
  }
  
  public static b b(Context paramContext)
  {
    for (;;)
    {
      try
      {
        Object localObject = (ConnectivityManager)paramContext.getSystemService("connectivity");
        if (localObject != null)
        {
          localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
          if ((localObject != null) && (((NetworkInfo)localObject).isAvailable()))
          {
            int i = ((NetworkInfo)localObject).getType();
            if (i == 1)
            {
              paramContext = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo();
              if (paramContext == null) {
                break label155;
              }
              paramContext = paramContext.getSSID();
              if (!TextUtils.isEmpty(paramContext)) {
                break label152;
              }
              paramContext = ((NetworkInfo)localObject).getTypeName();
              anf.c("Current network type:" + i + "," + paramContext);
              return new b(i, paramContext);
            }
            if (i != 0) {
              break label155;
            }
            paramContext = anl.k(paramContext);
            continue;
          }
        }
        continue;
      }
      catch (Exception paramContext)
      {
        anf.c("InstanceCurrent exception!");
        paramContext.printStackTrace();
        anf.c("InstanceCurrent no network!");
        return new b(-1, null);
      }
      label152:
      label155:
      paramContext = null;
    }
  }
  
  public boolean a(Context paramContext)
  {
    paramContext = b(paramContext);
    if (a == -1)
    {
      anf.c("Check network match while no network");
      return true;
    }
    return equals(paramContext);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool1 = true;
    boolean bool2 = true;
    if (this == paramObject) {
      return bool2;
    }
    if ((paramObject != null) && ((paramObject instanceof b)))
    {
      paramObject = (b)paramObject;
      if (a == a)
      {
        if (b == null) {
          if (b != null) {}
        }
        for (;;)
        {
          bool2 = bool1;
          if (bool1) {
            break;
          }
          anf.c("Network key change:" + b + "->" + b);
          return bool1;
          bool1 = false;
          continue;
          bool1 = b.equals(b);
        }
      }
      anf.c("Network type change:" + a + "->" + a);
    }
    for (;;)
    {
      return false;
      anf.c("Check network match while object is illegal:" + paramObject);
    }
  }
}

/* Location:
 * Qualified Name:     amh.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */