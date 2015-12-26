import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.util.ParseManager;
import cn.com.xy.sms.util.ParseSmsToBubbleUtil;
import com.android.mms.MmsApp;
import com.android.mms.view.MessageListItemSms.a;
import com.meizu.mpay.service.MzMPayHelper;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class aaw
{
  private static final String[] a = { "address", "body", "association_id" };
  private static String b = "type = 5";
  
  public static aaw.a a(String paramString)
  {
    if (!a()) {
      return null;
    }
    return aaw.a.a(paramString);
  }
  
  public static CharSequence a(aaw.a parama, CharSequence paramCharSequence)
  {
    return parama.b(paramCharSequence.toString());
  }
  
  public static String a(int paramInt)
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)MmsApp.c().getSystemService("phone");
    try
    {
      String str = (String)TelephonyManager.class.getDeclaredMethod("getSimSerialNumber", new Class[] { Integer.TYPE }).invoke(localTelephonyManager, new Object[] { Integer.valueOf((int)aac.b(paramInt)) });
      return str;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localNoSuchMethodException.printStackTrace();
      Log.e("", "getSimSerialNumber error");
      return localTelephonyManager.getSimSerialNumber();
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      for (;;)
      {
        localInvocationTargetException.printStackTrace();
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      for (;;)
      {
        localIllegalAccessException.printStackTrace();
      }
    }
  }
  
  public static String a(gr paramgr)
  {
    if ((a()) && (paramgr.h() != null) && (paramgr.h().size() == 1)) {
      return ((gm)paramgr.h().get(0)).d();
    }
    return null;
  }
  
  public static void a(Activity paramActivity, String paramString1, String paramString2)
  {
    HashMap localHashMap;
    if (!TextUtils.isEmpty(paramString1))
    {
      localHashMap = new HashMap();
      localHashMap.put("simIndex", "0");
      localHashMap.put("address", paramString2);
    }
    try
    {
      ParseManager.doAction(paramActivity, paramString1, localHashMap);
      aab.a(paramActivity, "BubbleClick", "ComposeMessageActivity");
      return;
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        paramString1.printStackTrace();
      }
    }
  }
  
  public static void a(Uri paramUri)
  {
    MmsApp localMmsApp = MmsApp.c();
    try
    {
      Cursor localCursor = localMmsApp.getContentResolver().query(paramUri, a, b, null, null);
      Object localObject;
      String str;
      if ((localCursor == null) || (localCursor != null)) {
        localCursor.close();
      }
    }
    finally
    {
      try
      {
        if ((localCursor.getCount() > 0) && (localCursor.moveToFirst()))
        {
          localObject = localCursor.getString(localCursor.getColumnIndex("address"));
          str = localCursor.getString(localCursor.getColumnIndex("body"));
          localObject = a((String)localObject);
          if ((localObject != null) && (a((aaw.a)localObject, str) != null)) {
            localMmsApp.getContentResolver().delete(paramUri, null, null);
          }
        }
        if (localCursor != null) {
          localCursor.close();
        }
        return;
      }
      finally
      {
        for (;;) {}
      }
      paramUri = finally;
      localCursor = null;
    }
    throw paramUri;
  }
  
  public static void a(Uri paramUri, SmsMessage[] paramArrayOfSmsMessage, int paramInt)
  {
    if (a()) {
      new Thread(new aax(paramUri, paramArrayOfSmsMessage, paramInt)).start();
    }
  }
  
  public static void a(MmsApp paramMmsApp, boolean paramBoolean)
  {
    Log.v("ServiceNumberHelper", "init() --> iniSdk(): isOperatorOpen() = " + a());
    if (a()) {}
    try
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("ONLINE_UPDATE_SDK", "1");
      localHashMap.put("SUPPORT_NETWORK_TYPE", "2");
      localHashMap.put("SECRETKEY", "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKbSNrFtSCKL/NmEIwczrM8M29UpsfzDJfTTZjIHzF3K3y80+cwXRD7XLUIUiXAQ/YopPCa6hN71u/BM9vOFHsEmy5Ox9ao+NLawMYNNw68BFCQv2aVASYi33fqXfjLTqPHhdnCrVNedSeQQR21x8jayqbKM2DtLwVz8PqYpczw/AgMBAAECgYBoEhqGw8tNqhFazYFgu88h+D6ok/Ny4XerbbqCmbTYCnkDpUP1G8q6fVjBsbgwDplteN4Kty+vPJQ7jHg/YZvWF/o2tF8w/k6bNF2io4FKFOx04ga5yY59Wuu0l9pA1Y1NAE2ykDd/RN/H3yuQG13CR7iXCwuHkCczt9ntYXqKMQJBAM8HP6FFeYCW+IiaTyT6xGgGy4T3DtnwrxszRRBIqeuUXEqqJC58RXLRSziB7cHopJs8A4wCDH0C4dyr2GTiVWkCQQDOSDDI0EP917B/olSRWgwWz28n2pfAIORSoYH+KL0CalAW2mgdIgI2ZhvIbZxyboH4tEoTth0zzKBcJJAfBQdnAkEAp/FNYNonEnVl8AqdoXX71heNCbQRTCK/KeWRZQBNN1oG9FrJNxyAif/WcWSVJvQ+c99fUThoQRERgB23UT954QJAVNISXUBl9Mbv6EuTgoEIX4jEKBsWMwZTXDbVAPE3ZvrYG82K6g/F4SBzZCLOJa+S+fUIULqdn2MQvVK7gEXNGQJAWl55mMtiB5vrCNHjUD4wJkmbVLTlPOcD0w+Q6elYQ/iCPFDP40bVZ+oa3+WUSIQJmkoJYYfNI/Kkq2dAUd2xXQ==");
      cn.com.xy.sms.sdk.log.LogManager.debug = false;
      cn.com.xy.sms.sdk.log.LogManager.writeFileLog = true;
      ParseManager.initSdk(paramMmsApp, "D6mKXM8MEIZU", "", true, true, localHashMap);
      cn.com.xy.sms.sdk.constant.Constant.Test = false;
      ParseManager.deleteMatchCache("", "session_lasttime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-09-30 12:00:00").getTime());
      if (!paramBoolean)
      {
        Thread.sleep(500L);
        ParseSmsToBubbleUtil.beforeHandParseReceiveSms(200, 3);
      }
      return;
    }
    catch (Exception paramMmsApp)
    {
      Log.v("ServiceNumberHelper", "init() --> Exception");
      paramMmsApp.printStackTrace();
    }
  }
  
  public static void a(MessageListItemSms.a parama, JSONArray paramJSONArray)
  {
    if (paramJSONArray == null)
    {
      Log.d("bubble_log", "jsonArray = null");
      return;
    }
    Log.d("bubble_log", "jsonArray != null");
    int i;
    if ((paramJSONArray != null) && (paramJSONArray.length() > 0))
    {
      i = paramJSONArray.length();
      Log.d("bubble_log", "jsonArray.length() = " + i);
      if (i != 1) {
        break label174;
      }
    }
    for (;;)
    {
      try
      {
        paramJSONArray = paramJSONArray.getJSONObject(0);
        a = ((String)JsonUtil.getValueFromJsonObject(paramJSONArray, "btn_name"));
        b = ((String)JsonUtil.getValueFromJsonObject(paramJSONArray, "action"));
        c = ((String)JsonUtil.getValueFromJsonObject(paramJSONArray, "action_data"));
        g = 1;
        Log.d("bubble_log", "setBubbleData() --> msgItem.mBubbleSize = " + g);
        return;
      }
      catch (Exception parama)
      {
        parama.printStackTrace();
        return;
      }
      Log.d("bubble_log", "jsonArray.length() = 0");
      return;
      label174:
      if (i >= 2)
      {
        JSONObject localJSONObject = paramJSONArray.getJSONObject(0);
        a = ((String)JsonUtil.getValueFromJsonObject(localJSONObject, "btn_name"));
        b = ((String)JsonUtil.getValueFromJsonObject(localJSONObject, "action"));
        c = ((String)JsonUtil.getValueFromJsonObject(localJSONObject, "action_data"));
        paramJSONArray = paramJSONArray.getJSONObject(1);
        d = ((String)JsonUtil.getValueFromJsonObject(paramJSONArray, "btn_name"));
        e = ((String)JsonUtil.getValueFromJsonObject(paramJSONArray, "action"));
        f = ((String)JsonUtil.getValueFromJsonObject(paramJSONArray, "action_data"));
        g = 2;
      }
    }
  }
  
  public static boolean a()
  {
    return (!MmsApp.d) && (!wd.c(MmsApp.c().getContentResolver()));
  }
  
  private static int b(int paramInt)
  {
    String str = zv.c(paramInt);
    if (!TextUtils.isEmpty(str))
    {
      if ((str.startsWith("46000")) || (str.startsWith("46002")) || (str.startsWith("46007"))) {
        return 1;
      }
      if (str.startsWith("46001")) {
        return 2;
      }
      if (str.startsWith("46003")) {
        return 3;
      }
    }
    return -1;
  }
  
  private static String b(int paramInt, String paramString, Map<String, String> paramMap)
  {
    int i = 0;
    int m;
    int j;
    if ((zv.i == 2) && (zv.e == 2))
    {
      int k = b(0);
      m = b(1);
      Log.d("", "operatorOne = " + k + ", operatorTwo = " + m + ", address = " + paramString);
      if (k == m) {
        break label304;
      }
      j = ParseManager.getOperatorByNum(paramString);
      if (j == k)
      {
        paramInt = i;
        Log.d("", "getSimIccidAndCenterNum(): address = " + paramString + ", operatorType = " + j + ", seletSlotId = " + paramInt);
      }
    }
    label175:
    label304:
    for (;;)
    {
      String str;
      switch (paramInt)
      {
      default: 
        str = "";
        paramString = "";
        if (!TextUtils.isEmpty(paramString)) {
          paramMap.put("cnum", paramString);
        }
        break;
      }
      for (;;)
      {
        Log.d("", "getSimIccidAndCenterNum(): centerNum = " + paramString);
        return str;
        if (j == m)
        {
          paramInt = 1;
          break;
        }
        paramInt = -1;
        break;
        str = a(paramInt);
        paramString = abe.a(paramInt);
        break label175;
        if (zv.e == 1)
        {
          paramInt = zv.i();
          str = a(paramInt);
          paramString = abe.a(paramInt);
          break label175;
        }
        str = "";
        paramString = "";
        break label175;
        paramMap.put("code", "CN");
      }
    }
  }
  
  public static class a
  {
    static String f = "1";
    private static String g;
    public String a;
    String b;
    String c;
    public ArrayList<a> d;
    HashMap<String, String> e = new HashMap();
    
    public a() {}
    
    public a(String paramString)
    {
      if (paramString != null) {
        try
        {
          if (paramString.length() > 0)
          {
            paramString = new JSONArray(paramString);
            String[] arrayOfString1 = new String[paramString.length()];
            int i = 0;
            while (i < paramString.length())
            {
              Object localObject = paramString.getJSONObject(i);
              a locala1 = new a();
              if (((JSONObject)localObject).has("name")) {
                a = ((JSONObject)localObject).getString("name");
              }
              if (((JSONObject)localObject).has("type")) {
                b = ((JSONObject)localObject).getString("type");
              }
              if (((JSONObject)localObject).has("action_data")) {
                c = ((JSONObject)localObject).getString("action_data");
              }
              a(locala1);
              arrayOfString1[i] = ((JSONObject)localObject).getString("name");
              if ((((JSONObject)localObject).has("secondmenu")) && (((JSONObject)localObject).getJSONArray("secondmenu").length() > 0))
              {
                localObject = ((JSONObject)localObject).getJSONArray("secondmenu");
                String[] arrayOfString2 = new String[((JSONArray)localObject).length()];
                int j = 0;
                while (j < ((JSONArray)localObject).length())
                {
                  JSONObject localJSONObject = ((JSONArray)localObject).getJSONObject(j);
                  arrayOfString2[j] = localJSONObject.getString("name");
                  a locala2 = new a();
                  if (localJSONObject.has("name")) {
                    a = localJSONObject.getString("name");
                  }
                  if (localJSONObject.has("type")) {
                    b = localJSONObject.getString("type");
                  }
                  if (localJSONObject.has("action_data")) {
                    c = localJSONObject.getString("action_data");
                  }
                  locala1.a(locala2);
                  j += 1;
                }
              }
              i += 1;
            }
          }
          return;
        }
        catch (Exception paramString)
        {
          paramString.printStackTrace();
        }
      }
    }
    
    public static a a(String paramString)
    {
      paramString = a(paramString, -1);
      if (!TextUtils.isEmpty(paramString)) {
        return new a(paramString);
      }
      return null;
    }
    
    public static Intent a(String paramString1, String paramString2)
    {
      Intent localIntent = null;
      Log.d("ServiceNumberHelper", "getPaymentIntent(): address = " + paramString2);
      if ("repayment".equals(paramString1)) {
        localIntent = MzMPayHelper.getAlipayCardPaymentIntent(paramString2, null, null, 0);
      }
      while (!"recharge".equals(paramString1)) {
        return localIntent;
      }
      return MzMPayHelper.getAlipayRechargeIntent(paramString2, null, 0);
    }
    
    public static String a(String paramString, int paramInt)
    {
      int j = 1;
      g = paramString;
      Log.d("ServiceNumberHelper", "getXYJsonByAddr(): mConvAddress = " + g);
      int i = j;
      try
      {
        if (!TextUtils.isEmpty(f)) {
          i = Integer.parseInt(f);
        }
        Object localObject = new HashMap();
        String str = aaw.a(paramInt, paramString, (Map)localObject);
        localObject = ParseManager.queryMenuByPhoneNum(MmsApp.c().getApplicationContext(), paramString, i, str, (Map)localObject);
        Log.v("", "getXYJsonByAddr() --> address = " + paramString + ", simIccid = " + str + ", seletSlotId = " + paramInt + ", TextUtils.isEmpty(json1) = " + TextUtils.isEmpty((CharSequence)localObject));
        return (String)localObject;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localException.printStackTrace();
          i = j;
        }
      }
    }
    
    public void a(a parama)
    {
      if (d == null) {
        d = new ArrayList();
      }
      d.add(parama);
    }
    
    public void a(Activity paramActivity)
    {
      HashMap localHashMap;
      if (!TextUtils.isEmpty(c))
      {
        localHashMap = new HashMap();
        localHashMap.put("simIndex", "0");
        localHashMap.put("address", g);
      }
      try
      {
        ParseManager.doAction(paramActivity, c, localHashMap);
        aab.a(paramActivity, "MenuClick", "ComposeMessageActivity");
        return;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localException.printStackTrace();
        }
      }
    }
    
    public String b(String paramString)
    {
      return (String)e.get(paramString);
    }
  }
}

/* Location:
 * Qualified Name:     aaw
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */