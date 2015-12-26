import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import com.ted.sdk.yellow.provider.YellowPageProvider;
import com.ted.sdk.yellow.util.SoftRefArray;

public class asl
{
  private static final String a = asl.class.getSimpleName();
  private static final Uri b = Uri.withAppendedPath(YellowPageProvider.a, "marker");
  private static final Uri c = Uri.withAppendedPath(YellowPageProvider.a, "corrector");
  private static asl g = null;
  private static Context h;
  private SoftRefArray<asl.b> d = new SoftRefArray();
  private asl.c e;
  private asl.a f;
  private Handler i = new Handler();
  
  public asl(Context paramContext)
  {
    h = paramContext;
    e = new asl.c(i);
    f = new asl.a(i);
    h.getContentResolver().registerContentObserver(b, true, e);
    h.getContentResolver().registerContentObserver(c, true, f);
  }
  
  public static asl a(Context paramContext)
  {
    if ((g == null) && (paramContext != null)) {}
    try
    {
      if (g == null) {
        g = new asl(paramContext.getApplicationContext());
      }
      return g;
    }
    finally {}
  }
  
  public static void b()
  {
    if (g != null)
    {
      g.c();
      g = null;
    }
  }
  
  private void c()
  {
    try
    {
      h.getContentResolver().unregisterContentObserver(e);
      h.getContentResolver().unregisterContentObserver(f);
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.getMessage();
      }
    }
    finally {}
  }
  
  protected void a()
  {
    asr.a().b();
    int k = d.size();
    int j = 0;
    for (;;)
    {
      if (j >= k) {
        return;
      }
      asl.b localb = (asl.b)d.get(j);
      if (localb != null) {}
      try
      {
        localb.a();
        j += 1;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localException.printStackTrace();
        }
      }
    }
  }
  
  class a
    extends ContentObserver
  {
    public a(Handler paramHandler)
    {
      super();
    }
    
    public void onChange(boolean paramBoolean)
    {
      a();
    }
  }
  
  public static abstract interface b
  {
    public abstract void a();
  }
  
  class c
    extends ContentObserver
  {
    public c(Handler paramHandler)
    {
      super();
    }
    
    public void onChange(boolean paramBoolean)
    {
      a();
    }
  }
}

/* Location:
 * Qualified Name:     asl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */