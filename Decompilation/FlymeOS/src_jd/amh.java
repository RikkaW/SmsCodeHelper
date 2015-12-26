import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class amh
{
  private amh.a[] a;
  private amh.a[] b;
  private final long c;
  private final long d;
  private final amh.b e;
  
  protected amh(String paramString, Context paramContext)
  {
    JSONObject localJSONObject = new JSONObject(paramString);
    JSONArray localJSONArray;
    int k;
    int i;
    String str;
    if (localJSONObject.has("targets"))
    {
      localJSONArray = localJSONObject.getJSONArray("targets");
      k = localJSONArray.length();
      if (k > 0)
      {
        a = new amh.a[k];
        i = 0;
        if (i < k)
        {
          paramString = localJSONArray.getJSONObject(i);
          str = paramString.getString("ip");
          if (paramString.has("authKey")) {}
          for (paramString = paramString.getString("authKey");; paramString = null)
          {
            a[i] = new amh.a(str, paramString);
            i += 1;
            break;
          }
        }
      }
    }
    if (localJSONObject.has("baks"))
    {
      localJSONArray = localJSONObject.getJSONArray("baks");
      k = localJSONArray.length();
      if (k > 0)
      {
        b = new amh.a[k];
        i = j;
        if (i < k)
        {
          paramString = localJSONArray.getJSONObject(i);
          str = paramString.getString("ip");
          if (paramString.has("authKey")) {}
          for (paramString = paramString.getString("authKey");; paramString = null)
          {
            b[i] = new amh.a(str, paramString);
            i += 1;
            break;
          }
        }
      }
    }
    if (localJSONObject.has("expire")) {}
    for (c = localJSONObject.getLong("expire");; c = 5L)
    {
      d = SystemClock.elapsedRealtime();
      e = amh.b.b(paramContext);
      return;
    }
  }
  
  public ami a(String paramString)
  {
    try
    {
      String str = Uri.parse(paramString).getAuthority();
      if (!TextUtils.isEmpty(str))
      {
        if ((a != null) && (a.length > 0)) {
          localObject1 = a[0];
        }
        while (localObject1 != null)
        {
          Object localObject2 = a;
          localObject1 = b;
          int i = paramString.indexOf(str);
          if (i != -1)
          {
            paramString = (String)localObject2 + paramString.substring(i + str.length());
            localObject2 = new ArrayList(2);
            ((List)localObject2).add(new Pair("Mz_Host", str));
            if (!TextUtils.isEmpty((CharSequence)localObject1)) {
              ((List)localObject2).add(new Pair("Authorization", "Basic " + Base64.encodeToString(((String)localObject1).getBytes(), 2)));
            }
            return new ami(paramString, (List)localObject2);
            if ((b == null) || (b.length <= 0)) {
              break label243;
            }
            localObject1 = b[0];
          }
          else
          {
            anf.d("cant re construct url:" + paramString);
          }
        }
      }
      return null;
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
        continue;
        label243:
        Object localObject1 = null;
      }
    }
  }
  
  public boolean a(Context paramContext)
  {
    boolean bool;
    if (SystemClock.elapsedRealtime() - d > c * 60000L) {
      bool = true;
    }
    while (!bool) {
      if (!e.a(paramContext))
      {
        return true;
        bool = false;
      }
      else
      {
        return false;
      }
    }
    anf.c("Proxy info time expire!");
    return bool;
  }
  
  static class a
  {
    public final String a;
    public final String b;
    
    public a(String paramString1, String paramString2)
    {
      a = paramString1;
      b = paramString2;
    }
  }
  
  static class b
  {
    private final int a;
    private final String b;
    
    private b(int paramInt, String paramString)
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
}

/* Location:
 * Qualified Name:     amh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */