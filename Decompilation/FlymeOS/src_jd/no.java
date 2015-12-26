import android.content.Context;
import com.android.mms.MmsApp;

public class no
  extends nn
{
  private static final int[] b = { 0, 60000, 300000, 600000, 1800000 };
  private static final int[] c = { 0, 300000, 300000, 300000 };
  private static final int[] d = { 15000, 15000, 15000, 15000 };
  private static int[] e;
  
  public no(Context paramContext, int paramInt)
  {
    super(paramInt);
    if (MmsApp.b)
    {
      e = c;
      if (a >= 0) {
        break label64;
      }
      paramInt = 0;
      label26:
      a = paramInt;
      if (a < e.length) {
        break label72;
      }
    }
    label64:
    label72:
    for (paramInt = e.length - 1;; paramInt = a)
    {
      a = paramInt;
      return;
      e = d;
      break;
      paramInt = a;
      break label26;
    }
  }
  
  public int a()
  {
    return e.length;
  }
  
  public long b()
  {
    return e[a];
  }
}

/* Location:
 * Qualified Name:     no
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */