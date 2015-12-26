import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

public class aai$a
  extends aai.b
{
  public static final Uri a = Uri.parse("content://blockresult/");
  
  public static String a(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getContentResolver().call(a, "getBlockCalls", null, null).getString("extra_info", null);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static String b(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getContentResolver().call(a, "getBlockMessages", null, null).getString("extra_info", null);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     aai.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */