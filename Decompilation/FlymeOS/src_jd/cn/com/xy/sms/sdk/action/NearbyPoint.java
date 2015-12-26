package cn.com.xy.sms.sdk.action;

import android.app.Activity;
import android.os.Handler;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class NearbyPoint
{
  public static final int DO_GET_LOCATION = 4101;
  public static final int GET_QUERY_URL_FAILURE = 4099;
  public static final int QUERY_PARAM_ERROR = 4098;
  public static final int QUERY_REQUEST_ERROR = 4100;
  public static final String QUERY_RESULT = "queryResult";
  public static final int QUERY_RESULT_RECEIVE = 4097;
  private Activity a;
  private c b = null;
  private Handler c;
  private double d;
  private double e;
  private String f;
  private int g = 0;
  
  public NearbyPoint(Activity paramActivity, Handler paramHandler)
  {
    c = paramHandler;
  }
  
  private String a(String paramString, double paramDouble1, double paramDouble2, int paramInt1, int paramInt2)
  {
    return a("6a0ddfcfdf1a1e7a1f38501fc5d218bf", paramString, paramDouble1, paramDouble2, paramInt1, "json", 2, paramInt2);
  }
  
  private static String a(String paramString1, String paramString2, double paramDouble1, double paramDouble2, int paramInt1, String paramString3, int paramInt2, int paramInt3)
  {
    if ((paramString1.length() == 32) && (paramString2 != null) && (paramDouble1 >= 0.0D) && (paramDouble2 >= 0.0D) && (paramInt1 > 0) && ((paramString3.equalsIgnoreCase("json")) || (paramString3.equalsIgnoreCase("xml"))))
    {
      paramString1 = new StringBuffer();
      paramString1.append("http://android.bizport.cn:9998/AndroidWeb/getPlaceListAPI?");
      paramString1.append("query=");
    }
    try
    {
      paramString1.append(URLEncoder.encode(paramString2, "UTF-8"));
      paramString1.append("&lat=");
      paramString1.append(paramDouble1);
      paramString1.append("&lng=");
      paramString1.append(paramDouble2);
      paramString1.append("&radius=");
      paramString1.append(paramInt1);
      paramString1.append("&scope=");
      paramString1.append(2);
      paramString1.append("&page_num=");
      paramString1.append(paramInt3);
      paramString1.append("&output=");
      paramString1.append(paramString3);
      return paramString1.toString();
      return null;
    }
    catch (UnsupportedEncodingException paramString2)
    {
      for (;;)
      {
        paramString2.printStackTrace();
      }
    }
  }
  
  public double getLocationLatitude()
  {
    return d;
  }
  
  public double getLocationLongitude()
  {
    return e;
  }
  
  public void sendMapQueryUrl(String paramString, double paramDouble1, double paramDouble2, int paramInt)
  {
    if (b != null)
    {
      b.isInterrupted();
      b = null;
    }
    f = paramString;
    d = paramDouble1;
    e = paramDouble2;
    g = paramInt;
    b = new c(this, (byte)0);
    b.start();
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.action.NearbyPoint
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */