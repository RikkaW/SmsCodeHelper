import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

public class amk
  implements amn
{
  private final int a;
  private boolean b = false;
  private int c = -1;
  private List<String> d;
  private int e = 0;
  private boolean f = false;
  private int g = 0;
  
  public amk(int paramInt)
  {
    a = paramInt;
  }
  
  public ami a(Context paramContext, String paramString)
  {
    if (!b)
    {
      paramContext = new amg().a(paramContext);
      if (paramContext != null)
      {
        g = 0;
        b = true;
        ami localami = paramContext.a(paramString);
        if (localami != null)
        {
          anf.d("Transform url success: " + a);
          return localami;
        }
        anf.d("Cant transform url: " + paramString + ", proxy: " + paramContext);
      }
    }
    for (;;)
    {
      return null;
      anf.d("Get relocate ip failed!");
      continue;
      anf.d("Relocate had used before!");
    }
  }
  
  public void a()
  {
    c += 1;
    if (f)
    {
      f = false;
      int i = g + 1;
      g = i;
      if (i <= 10)
      {
        anf.c("Reduce download time while relocate 302: " + g);
        c -= 1;
      }
    }
    anf.c("start download time: " + (c + 1));
  }
  
  public void a(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      if (d == null) {
        d = new ArrayList(1);
      }
      for (;;)
      {
        d.add(paramString);
        e = 0;
        return;
        d.clear();
      }
    }
    d = null;
  }
  
  public ami b(Context paramContext, String paramString)
  {
    return null;
  }
  
  public boolean b()
  {
    return c < a;
  }
  
  public String c()
  {
    if ((d != null) && (d.size() > e))
    {
      List localList = d;
      int i = e;
      e = (i + 1);
      return (String)localList.get(i);
    }
    return null;
  }
  
  public void d()
  {
    if (b) {
      b = false;
    }
    amg.a();
  }
  
  public void e()
  {
    f = true;
  }
}

/* Location:
 * Qualified Name:     amk
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */