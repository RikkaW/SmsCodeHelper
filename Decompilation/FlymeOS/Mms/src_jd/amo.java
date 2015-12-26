import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.meizu.statsapp.UsageStatsProxy;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class amo
{
  final String a;
  private UsageStatsProxy b;
  private Context c;
  
  public amo(Context paramContext)
  {
    b = UsageStatsProxy.a(paramContext, true);
    c = paramContext;
    a = UUID.randomUUID().toString();
  }
  
  private void a(String paramString1, int paramInt1, String paramString2, int paramInt2, String paramString3, String paramString4, String paramString5)
  {
    new amp(this, paramInt1, paramString1, paramString2, paramInt2, paramString3, paramString4, paramString5).execute(new Void[0]);
  }
  
  private void a(Map<String, String> paramMap)
  {
    for (;;)
    {
      try
      {
        paramMap.put("uuid", a);
        paramMap.put("clientip", amq.a());
        localObject = null;
        if (paramMap.containsKey("redirect_url"))
        {
          localObject = (String)paramMap.get("redirect_url");
          if (!TextUtils.isEmpty((CharSequence)localObject))
          {
            localObject = Uri.parse((String)localObject).getHost();
            if (!TextUtils.isEmpty((CharSequence)localObject)) {
              paramMap.put("serverip", amq.a((String)localObject));
            }
          }
          paramMap.put("product", c.getPackageName());
          anf.a("Write usage log:");
          localObject = paramMap.keySet().iterator();
          if (!((Iterator)localObject).hasNext()) {
            break;
          }
          String str = (String)((Iterator)localObject).next();
          anf.a(str + "=" + (String)paramMap.get(str));
          continue;
        }
        if (!paramMap.containsKey("requrl")) {
          continue;
        }
      }
      catch (Exception paramMap)
      {
        paramMap.printStackTrace();
        return;
      }
      Object localObject = (String)paramMap.get("requrl");
    }
    b.a("dns.download.app", paramMap);
  }
  
  public void a(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4, String paramString5)
  {
    a(paramString1, 3, paramString2, paramInt, paramString3, paramString4, paramString5);
  }
  
  public void a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    a(paramString1, 1, paramString2, 200, paramString3, paramString4, paramString5);
  }
  
  public void b(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4, String paramString5)
  {
    a(paramString1, 2, paramString2, paramInt, paramString3, paramString4, paramString5);
  }
  
  public void b(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    a(paramString1, 4, paramString2, 100002, paramString3, paramString4, paramString5);
  }
}

/* Location:
 * Qualified Name:     amo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */