package com.amap.api.services.core;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class d
{
  public static double a(double paramDouble)
  {
    return Double.parseDouble(new DecimalFormat("0.000000", new DecimalFormatSymbols(Locale.US)).format(paramDouble));
  }
  
  public static double a(int paramInt)
  {
    return paramInt / 111700.0D;
  }
  
  public static String a(LatLonPoint paramLatLonPoint)
  {
    if (paramLatLonPoint == null) {
      return "";
    }
    double d1 = a(paramLatLonPoint.getLongitude());
    double d2 = a(paramLatLonPoint.getLatitude());
    return d1 + "," + d2;
  }
  
  public static String a(Date paramDate)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("HH:mm");
    if (paramDate != null) {
      return localSimpleDateFormat.format(paramDate);
    }
    return "";
  }
  
  public static String a(List<LatLonPoint> paramList)
  {
    if (paramList == null) {
      return "";
    }
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    while (i < paramList.size())
    {
      LatLonPoint localLatLonPoint = (LatLonPoint)paramList.get(i);
      double d1 = a(localLatLonPoint.getLongitude());
      double d2 = a(localLatLonPoint.getLatitude());
      localStringBuffer.append(d1).append(",").append(d2);
      localStringBuffer.append(";");
      i += 1;
    }
    localStringBuffer.deleteCharAt(localStringBuffer.length() - 1);
    return localStringBuffer.toString();
  }
  
  public static void a(Throwable paramThrowable, String paramString1, String paramString2)
  {
    ay localay = ay.b();
    if (localay != null) {
      localay.b(paramThrowable, paramString1, paramString2);
    }
    paramThrowable.printStackTrace();
  }
  
  public static boolean a(String paramString)
  {
    return (paramString == null) || (paramString.trim().length() == 0);
  }
  
  public static void b(String paramString)
  {
    Object localObject;
    try
    {
      localObject = new JSONObject(paramString);
      if (!((JSONObject)localObject).has("status")) {
        return;
      }
      if (!((JSONObject)localObject).has("info")) {
        return;
      }
      paramString = ((JSONObject)localObject).getString("status");
      localObject = ((JSONObject)localObject).getString("info");
      if ((paramString.equals("1")) || (!paramString.equals("0"))) {
        return;
      }
      if ((((String)localObject).equals("INVALID_USER_KEY")) || (((String)localObject).equals("INSUFFICIENT_PRIVILEGES")) || (((String)localObject).equals("INVALID_USER_SCODE")) || (((String)localObject).equals("INVALID_USER_SIGNATURE"))) {
        throw new AMapException("key鉴权失败");
      }
    }
    catch (JSONException paramString)
    {
      a(paramString, "CoreUtil", "paseAuthFailurJson");
      return;
    }
    if ((((String)localObject).equals("SERVICE_NOT_EXIST")) || (((String)localObject).equals("服务正在维护中"))) {
      throw new AMapException("服务不存在或服务正在维护中");
    }
    if (((String)localObject).startsWith("UNKNOWN_ERROR")) {
      throw new AMapException("未知的错误");
    }
    if ((((String)localObject).equals("INVALID_PARAMS")) || (((String)localObject).equals("参数缺失或格式非法")) || (((String)localObject).equals("账号未激活或已被冻结"))) {
      throw new AMapException("无效的参数 - IllegalArgumentException");
    }
    if ((((String)localObject).equals("OVER_QUOTA")) || (((String)localObject).equals("USER_VISIT_TOO_FREQUENTLY")) || (((String)localObject).equals("USER_DAILY_VISITS_EXCESS")) || (((String)localObject).equals("IP_FORBIDDEN"))) {
      throw new AMapException("请求次数超过配额");
    }
    if (((String)localObject).equals("SERVICE_RESPONSE_ERROR")) {
      throw new AMapException("服务返回信息失败");
    }
    throw new AMapException((String)localObject);
  }
  
  public static Date c(String paramString)
  {
    Object localObject = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try
    {
      localObject = ((SimpleDateFormat)localObject).parse(paramString);
      return (Date)localObject;
    }
    catch (ParseException localParseException)
    {
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
      try
      {
        paramString = localSimpleDateFormat.parse(paramString);
        return paramString;
      }
      catch (ParseException paramString)
      {
        a(localParseException, "CoreUtil", "parseString2Date");
      }
    }
    return null;
  }
  
  public static Date d(String paramString)
  {
    if ((paramString == null) || (paramString.trim().equals(""))) {
      return null;
    }
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("HHmm");
    try
    {
      paramString = localSimpleDateFormat.parse(paramString);
      return paramString;
    }
    catch (ParseException paramString)
    {
      a(paramString, "CoreUtil", "parseString2Time");
    }
    return null;
  }
  
  public static Date e(String paramString)
  {
    if ((paramString == null) || (paramString.trim().equals(""))) {
      return null;
    }
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("HH:mm");
    try
    {
      paramString = localSimpleDateFormat.parse(paramString);
      return paramString;
    }
    catch (ParseException paramString)
    {
      a(paramString, "CoreUtil", "parseTime");
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.d
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */