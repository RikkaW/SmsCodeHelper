import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import cn.com.xy.sms.util.ParseManager;
import com.android.mms.MmsApp;
import com.meizu.mpay.service.MzMPayHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class aaw$a
{
  static String f = "1";
  private static String g;
  public String a;
  String b;
  String c;
  public ArrayList<a> d;
  HashMap<String, String> e = new HashMap();
  
  public aaw$a() {}
  
  public aaw$a(String paramString)
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

/* Location:
 * Qualified Name:     aaw.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */