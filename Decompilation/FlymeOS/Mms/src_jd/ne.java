import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import cn.com.xy.sms.sdk.action.AbsSdkDoAction;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.util.SdkCallBack;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.core.AMapLocException;
import com.android.mms.MmsApp;
import com.android.mms.smartmessage.SmartMessageViewContainer;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.ui.NotificationReply;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import org.json.JSONObject;
import sdk.meizu.traffic.TrafficSellerActivity;

public class ne
  extends AbsSdkDoAction
{
  private static Drawable a;
  private static ne.b e;
  private LocationManagerProxy b;
  private ne.a c;
  private Handler d;
  
  private void a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    if (TextUtils.isEmpty(paramString2)) {}
    do
    {
      return;
      if ("SEND_SMS".equals(paramString1))
      {
        if (SmartMessageViewContainer.a)
        {
          e.a(paramString2);
          return;
        }
        wd.a(2131493811, paramContext, 0, 1, true, 0, aaa.b());
        return;
      }
      if ("external/payment".equals(paramString1))
      {
        paramString1 = aaw.a.a(paramString2, paramString3);
        if (paramString1 != null) {
          try
          {
            paramContext.startActivity(paramString1);
            aab.a(paramContext, "PaymentClick", "ComposeMessageActivity");
            return;
          }
          catch (ActivityNotFoundException paramString1)
          {
            wd.a(2131493775, paramContext, 0, 1, true, 0, aaa.b());
            return;
          }
        }
        wd.a(2131493776, paramContext, 0, 1, true, 0, aaa.b());
        return;
      }
    } while (!"external/flow".equals(paramString1));
    paramString1 = new Intent(paramContext, TrafficSellerActivity.class);
    paramString1.putExtra("url", "https://magneto.meizu.com/flowrecharge/html/internal.html");
    paramContext.startActivity(paramString1);
  }
  
  public static void a(Drawable paramDrawable)
  {
    a = paramDrawable;
  }
  
  public static void a(ne.b paramb)
  {
    e = paramb;
  }
  
  public void a()
  {
    if (b != null)
    {
      b.removeUpdates(c);
      b.destory();
      b = null;
    }
    if (d != null)
    {
      d.removeMessages(4100);
      d = null;
    }
    c = null;
  }
  
  public void callPhone(Context paramContext, String paramString, int paramInt, Map<String, String> paramMap)
  {
    paramString = paramString.replace("-", "");
    paramMap = new Intent();
    paramMap.setAction("android.intent.action.VIEW");
    paramMap.setData(Uri.parse("tel:" + paramString));
    try
    {
      paramContext.startActivity(paramMap);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public void deleteMsgForDatabase(Context paramContext, String paramString)
  {
    MessagingNotification.a(paramContext, paramContext.getResources().getString(2131492923, new Object[] { Integer.valueOf(1) }), new nf(this, paramString, paramContext), null, null).show();
  }
  
  public String getContactName(Context paramContext, String paramString)
  {
    return "";
  }
  
  public Drawable getDrawableByNumber(Context paramContext, String paramString, Map<String, Object> paramMap)
  {
    return a;
  }
  
  public String getIccidBySimIndex(int paramInt)
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
      Log.e("MySdkDoAction", "getSimSerialNumber error");
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
  
  public void getLocation(Context paramContext, Handler paramHandler)
  {
    Log.d("MySdkDoAction", "getLocation()");
    d = paramHandler;
    c = new ne.a(null);
    b = LocationManagerProxy.getInstance(paramContext);
    b.requestLocationData("lbs", -1L, 10.0F, c);
  }
  
  /* Error */
  public java.util.List<JSONObject> getReceiveMsgByReceiveTime(String paramString, long paramLong1, long paramLong2, int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 7
    //   3: new 301	java/lang/StringBuffer
    //   6: dup
    //   7: ldc_w 303
    //   10: invokespecial 305	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
    //   13: astore 9
    //   15: aload 9
    //   17: lload_2
    //   18: invokevirtual 308	java/lang/StringBuffer:append	(J)Ljava/lang/StringBuffer;
    //   21: pop
    //   22: aload 9
    //   24: ldc_w 310
    //   27: invokevirtual 313	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   30: pop
    //   31: aload 9
    //   33: lload 4
    //   35: invokevirtual 308	java/lang/StringBuffer:append	(J)Ljava/lang/StringBuffer;
    //   38: pop
    //   39: aload_1
    //   40: invokestatic 319	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   43: ifne +373 -> 416
    //   46: aload 9
    //   48: ldc_w 321
    //   51: invokevirtual 313	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   54: pop
    //   55: iconst_1
    //   56: anewarray 39	java/lang/String
    //   59: astore 8
    //   61: aload 8
    //   63: iconst_0
    //   64: aload_1
    //   65: aastore
    //   66: aload 8
    //   68: astore_1
    //   69: invokestatic 327	cn/com/xy/sms/sdk/constant/Constant:getContext	()Landroid/content/Context;
    //   72: invokevirtual 331	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   75: astore 8
    //   77: ldc_w 333
    //   80: invokestatic 165	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   83: astore 10
    //   85: aload 9
    //   87: invokevirtual 334	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   90: astore 9
    //   92: new 148	java/lang/StringBuilder
    //   95: dup
    //   96: invokespecial 149	java/lang/StringBuilder:<init>	()V
    //   99: ldc_w 336
    //   102: invokevirtual 155	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   105: iload 6
    //   107: invokevirtual 339	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   110: ldc_w 341
    //   113: invokevirtual 155	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   116: invokevirtual 159	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   119: astore 11
    //   121: aload 8
    //   123: aload 10
    //   125: iconst_5
    //   126: anewarray 39	java/lang/String
    //   129: dup
    //   130: iconst_0
    //   131: ldc_w 343
    //   134: aastore
    //   135: dup
    //   136: iconst_1
    //   137: ldc_w 345
    //   140: aastore
    //   141: dup
    //   142: iconst_2
    //   143: ldc_w 347
    //   146: aastore
    //   147: dup
    //   148: iconst_3
    //   149: ldc_w 349
    //   152: aastore
    //   153: dup
    //   154: iconst_4
    //   155: ldc_w 351
    //   158: aastore
    //   159: aload 9
    //   161: aload_1
    //   162: aload 11
    //   164: invokevirtual 357	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   167: astore_1
    //   168: aload_1
    //   169: ifnull +241 -> 410
    //   172: aload_1
    //   173: invokeinterface 362 1 0
    //   178: ifle +232 -> 410
    //   181: new 364	java/util/ArrayList
    //   184: dup
    //   185: invokespecial 365	java/util/ArrayList:<init>	()V
    //   188: astore 7
    //   190: aload_1
    //   191: invokeinterface 369 1 0
    //   196: ifeq +142 -> 338
    //   199: new 371	org/json/JSONObject
    //   202: dup
    //   203: invokespecial 372	org/json/JSONObject:<init>	()V
    //   206: astore 8
    //   208: aload 8
    //   210: ldc_w 374
    //   213: aload_1
    //   214: iconst_0
    //   215: invokeinterface 376 2 0
    //   220: invokevirtual 380	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   223: pop
    //   224: aload 8
    //   226: ldc -26
    //   228: aload_1
    //   229: iconst_1
    //   230: invokeinterface 376 2 0
    //   235: invokevirtual 380	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   238: pop
    //   239: aload 8
    //   241: ldc_w 382
    //   244: aload_1
    //   245: iconst_2
    //   246: invokeinterface 376 2 0
    //   251: invokevirtual 380	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   254: pop
    //   255: aload 8
    //   257: ldc_w 384
    //   260: aload_1
    //   261: iconst_3
    //   262: invokeinterface 376 2 0
    //   267: invokevirtual 380	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   270: pop
    //   271: aload 8
    //   273: ldc_w 386
    //   276: aload_1
    //   277: iconst_4
    //   278: invokeinterface 376 2 0
    //   283: invokevirtual 380	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   286: pop
    //   287: aload 7
    //   289: aload 8
    //   291: invokeinterface 391 2 0
    //   296: pop
    //   297: goto -107 -> 190
    //   300: astore 8
    //   302: aload 7
    //   304: astore 9
    //   306: aload_1
    //   307: astore 7
    //   309: aload 9
    //   311: astore_1
    //   312: aload 8
    //   314: invokevirtual 392	org/json/JSONException:printStackTrace	()V
    //   317: aload_1
    //   318: astore 8
    //   320: aload 7
    //   322: ifnull +13 -> 335
    //   325: aload 7
    //   327: invokeinterface 395 1 0
    //   332: aload_1
    //   333: astore 8
    //   335: aload 8
    //   337: areturn
    //   338: aload 7
    //   340: astore 8
    //   342: aload_1
    //   343: ifnull -8 -> 335
    //   346: aload_1
    //   347: invokeinterface 395 1 0
    //   352: aload 7
    //   354: areturn
    //   355: astore 7
    //   357: aconst_null
    //   358: astore_1
    //   359: aload_1
    //   360: ifnull +9 -> 369
    //   363: aload_1
    //   364: invokeinterface 395 1 0
    //   369: aload 7
    //   371: athrow
    //   372: astore 7
    //   374: goto -15 -> 359
    //   377: astore 8
    //   379: aload 7
    //   381: astore_1
    //   382: aload 8
    //   384: astore 7
    //   386: goto -27 -> 359
    //   389: astore 8
    //   391: aconst_null
    //   392: astore_1
    //   393: goto -81 -> 312
    //   396: astore 8
    //   398: aconst_null
    //   399: astore 9
    //   401: aload_1
    //   402: astore 7
    //   404: aload 9
    //   406: astore_1
    //   407: goto -95 -> 312
    //   410: aconst_null
    //   411: astore 7
    //   413: goto -75 -> 338
    //   416: aconst_null
    //   417: astore_1
    //   418: goto -349 -> 69
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	421	0	this	ne
    //   0	421	1	paramString	String
    //   0	421	2	paramLong1	long
    //   0	421	4	paramLong2	long
    //   0	421	6	paramInt	int
    //   1	352	7	localObject1	Object
    //   355	15	7	localObject2	Object
    //   372	8	7	localObject3	Object
    //   384	28	7	localObject4	Object
    //   59	231	8	localObject5	Object
    //   300	13	8	localJSONException1	org.json.JSONException
    //   318	23	8	localObject6	Object
    //   377	6	8	localObject7	Object
    //   389	1	8	localJSONException2	org.json.JSONException
    //   396	1	8	localJSONException3	org.json.JSONException
    //   13	392	9	localObject8	Object
    //   83	41	10	localUri	Uri
    //   119	44	11	str	String
    // Exception table:
    //   from	to	target	type
    //   190	297	300	org/json/JSONException
    //   69	168	355	finally
    //   172	190	372	finally
    //   190	297	372	finally
    //   312	317	377	finally
    //   69	168	389	org/json/JSONException
    //   172	190	396	org/json/JSONException
  }
  
  public View getThirdPopupView(Context paramContext, String paramString, Map<String, Object> paramMap, SdkCallBack paramSdkCallBack)
  {
    return null;
  }
  
  public void markAsReadForDatabase(Context paramContext, String paramString)
  {
    Log.d("MySdkDoAction", "markAsReadForDatabase()");
    MessagingNotification.b(paramContext, 1);
  }
  
  public void openAppByAppName(Context paramContext, String paramString1, String paramString2)
  {
    if ((checkHasAppName(paramContext, paramString1)) || (paramString2 != null)) {
      super.openAppByAppName(paramContext, paramString1, paramString2);
    }
  }
  
  public void openSms(Context paramContext, String paramString, Map<String, String> paramMap)
  {
    String str = (String)paramMap.get("simIndex");
    paramMap = (String)paramMap.get("thread_id");
    Log.d("MySdkDoAction", "openSms() --> phoneNum = " + paramString + ", simId = " + str + ", threadid = " + paramMap);
    paramString = ComposeMessageActivity.a(paramContext, Long.parseLong(paramMap), zv.c(Integer.parseInt(str)));
    paramString.setFlags(872415232);
    paramContext.startActivity(paramString);
  }
  
  public int otherOrderTraffic(Context paramContext, JSONObject paramJSONObject, Map<String, String> paramMap)
  {
    Log.d("MySdkDoAction", "otherOrderTraffic()");
    a(paramContext, "external/flow", "flow", "");
    return 0;
  }
  
  public void repayment(Context paramContext, JSONObject paramJSONObject)
  {
    Log.v("MySdkDoAction", "MySdkDoAction() --> repayment()");
  }
  
  public void replySms(Context paramContext, String paramString1, String paramString2, String paramString3, Map<String, String> paramMap)
  {
    paramString1 = (String)paramMap.get("simIndex");
    paramString2 = (String)paramMap.get("thread_id");
    paramMap = new Intent(paramContext, NotificationReply.class);
    paramMap.putExtra("smsThreadId", Long.parseLong(paramString2));
    paramMap.putExtra("smsMessageBody", paramString3);
    paramMap.putExtra("smsSlotId", Integer.parseInt(paramString1));
    paramMap.addFlags(268435456);
    paramContext.startActivity(paramMap);
  }
  
  public void sendSms(Context paramContext, String paramString1, String paramString2, int paramInt, Map<String, String> paramMap)
  {
    a(paramContext, "SEND_SMS", paramString2, "");
    Log.v("MySdkDoAction", "MySdkDoAction() --> sendSms(): phoneNum = " + paramString1 + ", sms_body = " + paramString2);
  }
  
  public void zfbRecharge(Context paramContext, JSONObject paramJSONObject, Map<String, String> paramMap)
  {
    paramJSONObject = "";
    if (paramMap != null) {
      paramJSONObject = (String)paramMap.get("address");
    }
    a(paramContext, "external/payment", "recharge", paramJSONObject);
    Log.d("MySdkDoAction", "MySdkDoAction() --> zfbRecharge(): stringStringMap = " + paramMap + ", address = " + paramJSONObject);
  }
  
  public void zfbRepayment(Context paramContext, JSONObject paramJSONObject)
  {
    JSONObject localJSONObject = JsonUtil.parseStrToJsonObject((String)JsonUtil.getValueFromJsonObject(paramJSONObject, "extend"));
    paramJSONObject = "";
    if (localJSONObject != null) {
      paramJSONObject = (String)JsonUtil.getValFromJsonObject(localJSONObject, "address");
    }
    a(paramContext, "external/payment", "repayment", paramJSONObject);
    Log.d("MySdkDoAction", "MySdkDoAction() --> zfbRepayment(): js = " + localJSONObject + ", address = " + paramJSONObject);
  }
  
  class a
    implements AMapLocationListener
  {
    private a() {}
    
    public void onLocationChanged(Location paramLocation) {}
    
    public void onLocationChanged(AMapLocation paramAMapLocation)
    {
      Log.d("MySdkDoAction", "onLocationChanged()~~~~");
      if (paramAMapLocation == null)
      {
        ne.a(ne.this).obtainMessage(4100).sendToTarget();
        return;
      }
      if (paramAMapLocation.getAMapException().getErrorCode() != 0)
      {
        Log.d("MySdkDoAction", "errCode = " + paramAMapLocation.getAMapException().getErrorCode() + ", errMsg = " + paramAMapLocation.getAMapException().getErrorMessage());
        ne.a(ne.this).obtainMessage(4100).sendToTarget();
        return;
      }
      Message localMessage = ne.a(ne.this).obtainMessage(4102);
      Bundle localBundle = new Bundle();
      localBundle.putDouble("latitude", paramAMapLocation.getLatitude());
      localBundle.putDouble("longitude", paramAMapLocation.getLongitude());
      localMessage.setData(localBundle);
      localMessage.sendToTarget();
      a();
    }
    
    public void onProviderDisabled(String paramString) {}
    
    public void onProviderEnabled(String paramString) {}
    
    public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle) {}
  }
  
  public static abstract interface b
  {
    public abstract void a(String paramString);
  }
}

/* Location:
 * Qualified Name:     ne
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */