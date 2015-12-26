import android.content.Context;
import com.meizu.statsapp.UsageStatsProxy;
import java.util.HashMap;
import java.util.Map;

public class and
{
  private static and a;
  private UsageStatsProxy b;
  private Context c;
  private final String d;
  
  public and(Context paramContext)
  {
    c = paramContext.getApplicationContext();
    d = paramContext.getPackageName();
    b = UsageStatsProxy.a(paramContext, true);
  }
  
  public static final and a(Context paramContext)
  {
    try
    {
      if (a == null) {
        a = new and(paramContext);
      }
      paramContext = a;
      return paramContext;
    }
    finally {}
  }
  
  public void a(and.a parama, String paramString)
  {
    a(parama, paramString, null);
  }
  
  public void a(and.a parama, String paramString1, String paramString2)
  {
    a(parama, paramString1, paramString2, false);
  }
  
  public void a(and.a parama, String paramString1, String paramString2, boolean paramBoolean)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("update_action", parama.a());
    localHashMap.put("package_name", d);
    if (paramString1 != null) {
      localHashMap.put("new_version", paramString1);
    }
    if (paramString2 != null) {
      localHashMap.put("old_version", paramString2);
    }
    if (paramBoolean) {
      localHashMap.put("update_manual", "manual");
    }
    b.a("update.component.app", localHashMap);
  }
  
  public static enum a
  {
    private String n;
    
    private a(String paramString)
    {
      n = paramString;
    }
    
    public String a()
    {
      return n;
    }
  }
}

/* Location:
 * Qualified Name:     and
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */