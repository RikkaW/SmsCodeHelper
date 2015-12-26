import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

public class aai
{
  public static final Uri a = null;
  
  public static void a(Context paramContext, int paramInt)
  {
    a(paramContext, "android.intent.action.MMS_BLOCK_LIST", "mms", paramInt);
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, int paramInt)
  {
    paramContext = new aaj(paramString1, paramContext, paramString2);
    if (paramInt > 0)
    {
      new Handler().postDelayed(paramContext, paramInt);
      return;
    }
    paramContext.run();
  }
  
  public static class a
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
  
  public static class b {}
}

/* Location:
 * Qualified Name:     aai
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */