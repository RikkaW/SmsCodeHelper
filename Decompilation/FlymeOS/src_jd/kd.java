import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;

public class kd
{
  private static kd c;
  private final Context a;
  private ke b;
  
  private kd(Context paramContext)
  {
    a = paramContext;
    b(paramContext.getResources().getConfiguration());
  }
  
  public static kd a()
  {
    if (c == null) {
      throw new IllegalStateException("Uninitialized.");
    }
    return c;
  }
  
  private ke a(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException("Unsupported display type: " + paramInt);
    case 10: 
      return new kc(a, 10);
    }
    return new kc(a, 11);
  }
  
  public static void a(Context paramContext)
  {
    if (c != null) {
      Log.w("LayoutManager", "Already initialized.");
    }
    c = new kd(paramContext);
  }
  
  private void b(Configuration paramConfiguration)
  {
    if (orientation == 1) {}
    for (int i = 11;; i = 10)
    {
      b = a(i);
      return;
    }
  }
  
  public void a(Configuration paramConfiguration)
  {
    b(paramConfiguration);
  }
  
  public ke b()
  {
    return b;
  }
}

/* Location:
 * Qualified Name:     kd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */