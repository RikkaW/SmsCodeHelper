import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.meizu.statsapp.UsageStatsProxy;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class amv
{
  final String a;
  private UsageStatsProxy b;
  private Context c;
  
  public amv(Context paramContext)
  {
    b = UsageStatsProxy.a(paramContext, true);
    c = paramContext;
    a = UUID.randomUUID().toString();
  }
  
  private void a(int paramInt1, int paramInt2, String paramString)
  {
    new amw(this, paramInt1, paramInt2, paramString).execute(new Void[0]);
  }
  
  private void a(Map<String, String> paramMap, String paramString)
  {
    try
    {
      paramMap.put("uuid", a);
      paramMap.put("clientip", amq.a());
      if (!TextUtils.isEmpty(paramString))
      {
        paramString = Uri.parse(paramString).getHost();
        if (!TextUtils.isEmpty(paramString)) {
          paramMap.put("serverip", amq.a(paramString));
        }
      }
      paramMap.put("product", c.getPackageName());
      anf.a("Write push usage log:");
      paramString = paramMap.keySet().iterator();
      while (paramString.hasNext())
      {
        String str = (String)paramString.next();
        anf.a(str + "=" + (String)paramMap.get(str));
      }
      b.a("update.push.system.app", paramMap);
    }
    catch (Exception paramMap)
    {
      paramMap.printStackTrace();
      return;
    }
  }
  
  public void a(int paramInt, String paramString)
  {
    a(4, paramInt, paramString);
  }
  
  public void a(String paramString)
  {
    a(1, 200, paramString);
  }
  
  public void b(String paramString)
  {
    a(2, 200, paramString);
  }
  
  public void c(String paramString)
  {
    a(3, 200, paramString);
  }
}

/* Location:
 * Qualified Name:     amv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */