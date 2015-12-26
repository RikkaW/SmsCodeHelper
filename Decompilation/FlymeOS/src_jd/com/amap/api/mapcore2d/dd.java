package com.amap.api.mapcore2d;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class dd
{
  private static String a = null;
  private static boolean b = false;
  private static String c = null;
  private static String d = null;
  private static String e = null;
  private static String f = null;
  
  public static String a(Context paramContext)
  {
    try
    {
      if ((a != null) && (!"".equals(a))) {
        return a;
      }
      if (paramContext.checkCallingOrSelfPermission("android.permission.WRITE_SETTINGS") == 0) {
        a = Settings.System.getString(paramContext.getContentResolver(), "mqBRboGZkQPcAkyk");
      }
      if ((a != null) && (!"".equals(a)))
      {
        paramContext = a;
        return paramContext;
      }
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
    try
    {
      if ("mounted".equals(Environment.getExternalStorageState()))
      {
        paramContext = Environment.getExternalStorageDirectory().getAbsolutePath();
        paramContext = new File(paramContext + "/.UTSystemConfig/Global/Alvin2.xml");
        if (paramContext.exists()) {
          SAXParserFactory.newInstance().newSAXParser().parse(paramContext, new a());
        }
      }
      return a;
    }
    catch (FileNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
    catch (ParserConfigurationException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
    catch (SAXException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
    catch (IOException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  private static List<ScanResult> a(List<ScanResult> paramList)
  {
    int k = paramList.size();
    int i = 0;
    while (i < k - 1)
    {
      int j = 1;
      while (j < k - i)
      {
        if (get1level > getlevel)
        {
          ScanResult localScanResult = (ScanResult)paramList.get(j - 1);
          paramList.set(j - 1, paramList.get(j));
          paramList.set(j, localScanResult);
        }
        j += 1;
      }
      i += 1;
    }
    return paramList;
  }
  
  static String b(Context paramContext)
  {
    localStringBuilder = new StringBuilder();
    if (paramContext != null) {}
    try
    {
      if (paramContext.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") != 0) {
        return localStringBuilder.toString();
      }
      paramContext = (WifiManager)paramContext.getSystemService("wifi");
      if (paramContext.isWifiEnabled())
      {
        paramContext = paramContext.getScanResults();
        if ((paramContext == null) || (paramContext.size() == 0)) {
          return localStringBuilder.toString();
        }
        paramContext = a(paramContext);
        int j = 1;
        int i = 0;
        if ((i < paramContext.size()) && (i < 10))
        {
          ScanResult localScanResult = (ScanResult)paramContext.get(i);
          if (j != 0) {
            j = 0;
          }
          for (;;)
          {
            localStringBuilder.append(BSSID);
            i += 1;
            break;
            localStringBuilder.append("||");
          }
        }
      }
      return localStringBuilder.toString();
    }
    catch (Throwable paramContext)
    {
      ed.a(paramContext, "DeviceInfo", "getWifiMacs");
      paramContext.printStackTrace();
    }
  }
  
  static String c(Context paramContext)
  {
    try
    {
      if ((c != null) && (!"".equals(c))) {
        return c;
      }
      if (paramContext.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") != 0) {
        return c;
      }
      c = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        ed.a(paramContext, "DeviceInfo", "getDeviceMac");
        paramContext.printStackTrace();
      }
    }
    return c;
  }
  
  static String d(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (;;)
    {
      try
      {
        if (paramContext.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
          return localStringBuilder.toString();
        }
        paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getCellLocation();
        if (!(paramContext instanceof GsmCellLocation)) {
          continue;
        }
        paramContext = (GsmCellLocation)paramContext;
        i = paramContext.getCid();
        localStringBuilder.append(paramContext.getLac()).append("||").append(i).append("&bt=gsm");
      }
      catch (Throwable paramContext)
      {
        int i;
        int j;
        int k;
        ed.a(paramContext, "DeviceInfo", "cellInfo");
        paramContext.printStackTrace();
        continue;
      }
      return localStringBuilder.toString();
      if ((paramContext instanceof CdmaCellLocation))
      {
        paramContext = (CdmaCellLocation)paramContext;
        i = paramContext.getSystemId();
        j = paramContext.getNetworkId();
        k = paramContext.getBaseStationId();
        if ((i >= 0) && (j >= 0) && (k < 0)) {}
        localStringBuilder.append(i).append("||").append(j).append("||").append(k).append("&bt=cdma");
      }
    }
  }
  
  static String e(Context paramContext)
  {
    try
    {
      paramContext = v(paramContext);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  static int f(Context paramContext)
  {
    try
    {
      int i = w(paramContext);
      return i;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
    return -1;
  }
  
  public static int g(Context paramContext)
  {
    try
    {
      int i = u(paramContext);
      return i;
    }
    catch (Throwable paramContext)
    {
      ed.a(paramContext, "DeviceInfo", "getActiveNetWorkType");
      paramContext.printStackTrace();
    }
    return -1;
  }
  
  static int h(Context paramContext)
  {
    try
    {
      int i = u(paramContext);
      return i;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
    return -1;
  }
  
  static String i(Context paramContext)
  {
    try
    {
      if (paramContext.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
        return null;
      }
      paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (paramContext == null) {
        break label61;
      }
      paramContext = paramContext.getActiveNetworkInfo();
      if (paramContext == null) {
        break label61;
      }
      paramContext = paramContext.getExtraInfo();
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        ed.a(paramContext, "DeviceInfo", "getNetworkExtraInfo");
        paramContext.printStackTrace();
        paramContext = null;
      }
    }
    return paramContext;
    label61:
    return null;
  }
  
  static String j(Context paramContext)
  {
    for (;;)
    {
      try
      {
        if ((d != null) && (!"".equals(d))) {
          return d;
        }
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
        i = widthPixels;
        j = heightPixels;
        if (j <= i) {
          continue;
        }
        paramContext = i + "*" + j;
        d = paramContext;
      }
      catch (Throwable paramContext)
      {
        int i;
        int j;
        ed.a(paramContext, "DeviceInfo", "getReslution");
        paramContext.printStackTrace();
        continue;
      }
      return d;
      paramContext = j + "*" + i;
    }
  }
  
  static String k(Context paramContext)
  {
    try
    {
      paramContext = t(paramContext);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  static String l(Context paramContext)
  {
    try
    {
      paramContext = t(paramContext);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      ed.a(paramContext, "DeviceInfo", "getActiveNetworkTypeName");
      paramContext.printStackTrace();
    }
    return null;
  }
  
  static String m(Context paramContext)
  {
    try
    {
      if ((e != null) && (!"".equals(e))) {
        return e;
      }
      if (paramContext.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
        return e;
      }
      e = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        ed.a(paramContext, "DeviceInfo", "getDeviceID");
        paramContext.printStackTrace();
      }
    }
    return e;
  }
  
  static String n(Context paramContext)
  {
    try
    {
      paramContext = r(paramContext);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      ed.a(paramContext, "DeviceInfo", "getSubscriberId");
      paramContext.printStackTrace();
    }
    return null;
  }
  
  static String o(Context paramContext)
  {
    try
    {
      paramContext = s(paramContext);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      ed.a(paramContext, "DeviceInfo", "getNetworkOperatorName");
      paramContext.printStackTrace();
    }
    return null;
  }
  
  static String p(Context paramContext)
  {
    try
    {
      paramContext = s(paramContext);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  static String q(Context paramContext)
  {
    try
    {
      paramContext = r(paramContext);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  private static String r(Context paramContext)
  {
    if ((f != null) && (!"".equals(f))) {
      return f;
    }
    if (paramContext.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
      return f;
    }
    f = ((TelephonyManager)paramContext.getSystemService("phone")).getSubscriberId();
    return f;
  }
  
  private static String s(Context paramContext)
  {
    if (paramContext.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
      return null;
    }
    return ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperatorName();
  }
  
  private static String t(Context paramContext)
  {
    if (paramContext.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
      return "";
    }
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext == null) {
      return "";
    }
    paramContext = paramContext.getActiveNetworkInfo();
    if (paramContext == null) {
      return "";
    }
    return paramContext.getTypeName();
  }
  
  private static int u(Context paramContext)
  {
    if ((paramContext == null) || (paramContext.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0)) {
      return -1;
    }
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext == null) {
      return -1;
    }
    paramContext = paramContext.getActiveNetworkInfo();
    if (paramContext == null) {
      return -1;
    }
    return paramContext.getType();
  }
  
  private static String v(Context paramContext)
  {
    paramContext = n(paramContext);
    if ((paramContext == null) || (paramContext.length() < 5)) {
      return "";
    }
    return paramContext.substring(3, 5);
  }
  
  private static int w(Context paramContext)
  {
    if (paramContext.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
      return -1;
    }
    return ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkType();
  }
  
  static class a
    extends DefaultHandler
  {
    public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    {
      if (dd.a()) {
        dd.a(new String(paramArrayOfChar, paramInt1, paramInt2));
      }
    }
    
    public void endElement(String paramString1, String paramString2, String paramString3)
    {
      dd.a(false);
    }
    
    public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
    {
      if ((paramString2.equals("string")) && ("UTDID".equals(paramAttributes.getValue("name")))) {
        dd.a(true);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.dd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */