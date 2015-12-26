import android.content.Context;
import android.util.Log;
import com.android.mms.MmsApp;
import java.math.BigDecimal;

public class kn
{
  public static String a = "com.android.mms.intent.action.GET_LOCATION_BAIDU";
  public static String b = "com.android.mms.intent.action.GET_LOCATION_AMAP";
  public static String c = "com.android.mms.intent.action.GET_LOCATION_GOOGLE";
  
  public static final String a(Context paramContext, double paramDouble)
  {
    if (paramDouble < 1000.0D) {
      return paramContext.getString(2131493345, new Object[] { new BigDecimal(paramDouble).setScale(0, 4).toString() });
    }
    return paramContext.getString(2131493344, new Object[] { new BigDecimal(paramDouble / 1000.0D).setScale(1, 4).toString() });
  }
  
  public static boolean a()
  {
    Log.i("BaseGetLocationUtils", "isUseGoogle MmsApp.PRODUCT_INTERNATIONAL = " + MmsApp.d);
    return MmsApp.d;
  }
  
  public static boolean a(String paramString)
  {
    return paramString.startsWith("http://j.map.baidu.com");
  }
  
  public static boolean b(String paramString)
  {
    return paramString.startsWith("http://maps.google.com/maps?");
  }
  
  public static boolean c(String paramString)
  {
    return (paramString.startsWith("http://mo.amap.com/?")) || (paramString.startsWith("http://m.amap.com/?"));
  }
}

/* Location:
 * Qualified Name:     kn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */