import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class akk
{
  public static final long a(Context paramContext)
  {
    paramContext = amx.c(paramContext);
    long l = 0L;
    if (paramContext != null) {
      l = paramContext.getLong("check_update_time", 0L);
    }
    return l;
  }
  
  public static final void a(Context paramContext, int paramInt)
  {
    paramContext = amx.c(paramContext).edit();
    paramContext.putInt("cur_need_update", paramInt);
    paramContext.apply();
  }
  
  public static final boolean a(Context paramContext, long paramLong)
  {
    long l1 = a(paramContext);
    if (d(paramContext) == 1) {}
    for (;;)
    {
      return true;
      if (l1 > 0L)
      {
        long l2 = System.currentTimeMillis();
        if (paramLong > 0L) {}
        while (Math.abs(l2 - l1) <= paramLong)
        {
          return false;
          paramLong = 259200000L;
        }
      }
    }
  }
  
  public static final void b(Context paramContext)
  {
    b(paramContext, System.currentTimeMillis());
  }
  
  private static final void b(Context paramContext, long paramLong)
  {
    paramContext = amx.c(paramContext).edit();
    paramContext.putLong("check_update_time", paramLong);
    paramContext.apply();
  }
  
  public static final void c(Context paramContext)
  {
    b(paramContext, 0L);
  }
  
  private static final int d(Context paramContext)
  {
    paramContext = amx.c(paramContext);
    int i = 0;
    if (paramContext != null) {
      i = paramContext.getInt("cur_need_update", 0);
    }
    return i;
  }
}

/* Location:
 * Qualified Name:     akk
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */