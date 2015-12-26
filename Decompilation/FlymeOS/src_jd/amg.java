import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import java.util.ArrayList;
import java.util.List;

public class amg
{
  private static amh a;
  
  public static void a()
  {
    a = null;
  }
  
  private amh b(Context paramContext)
  {
    try
    {
      String str = c(paramContext);
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(new Pair("sim_card_sp", str));
      localArrayList.add(new Pair("rule_id", "15"));
      str = a("http://servicecut.meizu.com/interface/locate", localArrayList);
      if (!TextUtils.isEmpty(str))
      {
        anf.a("Proxy info: " + str);
        return new amh(str, paramContext);
      }
      anf.d("Proxy response is null!");
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        anf.d("Load proxy exception!");
        paramContext.printStackTrace();
      }
    }
    return null;
  }
  
  private String c(Context paramContext)
  {
    if (anl.j(paramContext)) {
      return "wifi";
    }
    return anl.k(paramContext);
  }
  
  public amh a(Context paramContext)
  {
    if ((a != null) && (!a.a(paramContext))) {
      return a;
    }
    a = new amg().b(paramContext);
    return a;
  }
  
  protected String a(String paramString, List<Pair<String, String>> paramList)
  {
    return ank.a(paramString, paramList);
  }
}

/* Location:
 * Qualified Name:     amg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */