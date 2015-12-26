import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

public class abl
{
  public static final Uri a = Uri.parse("content://mms-sms/canonical-addresses");
  private static boolean b = false;
  
  public static final abl.a a(String paramString1, String paramString2, long paramLong)
  {
    abl.a locala = new abl.a();
    if (zj.a(paramString1, paramString2))
    {
      b = true;
      c = 1;
    }
    return locala;
  }
  
  public static final Uri a(Context paramContext, ContentValues paramContentValues, abl.a parama)
  {
    zj.a(paramContentValues);
    return null;
  }
  
  public static boolean a(Context paramContext, long paramLong)
  {
    paramContext = gr.a(paramContext, paramLong, true);
    if (paramContext == null) {}
    while (paramContext.h().size() != 1) {
      return false;
    }
    return true;
  }
  
  public static boolean a(Context paramContext, long[] paramArrayOfLong)
  {
    int i = 0;
    while (i < paramArrayOfLong.length)
    {
      if (!a(paramContext, paramArrayOfLong[i])) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  private static final boolean a(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    try
    {
      boolean bool = zj.a(paramString);
      return bool;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static final boolean a(String paramString1, String paramString2, Runnable paramRunnable)
  {
    if (a(paramString1)) {
      return false;
    }
    try
    {
      zj.b(paramString1, paramString2);
      return true;
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        paramString1.printStackTrace();
      }
    }
  }
  
  public static final class a
  {
    public gm a;
    public boolean b;
    public int c;
  }
}

/* Location:
 * Qualified Name:     abl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */