import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.meizu.statsapp.UsageStatsProxy.Event;
import com.meizu.statsapp.UsageStatusLog;
import com.meizu.statsapp.util.Utils;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ajt
{
  private static volatile ajt a;
  private static Object b = new Object();
  private Context c;
  private boolean d;
  private volatile boolean e;
  private ajq f;
  private HandlerThread g = new HandlerThread("StatsUploadThread");
  private ajt.b h;
  private SharedPreferences i;
  private Map<String, Map<String, List<UsageStatsProxy.Event>>> j = new HashMap();
  private int k;
  private ajs l;
  private boolean m;
  private int n;
  private long o;
  private long p;
  private boolean q = false;
  private volatile boolean r = false;
  private int s = 0;
  private long t = 0L;
  private boolean u = false;
  
  private ajt(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    c = paramContext;
    d = paramBoolean1;
    e = paramBoolean2;
    int i1;
    if (d) {
      i1 = ajk.c;
    }
    for (;;)
    {
      n = i1;
      f = ajq.a(c, d);
      i = c.getSharedPreferences("com.meizu.stats", 0);
      o = i.getLong("last_upload_time", 0L);
      q = i.getBoolean("today_upload_flag", false);
      s = i.getInt("ratio", 1);
      t = i.getLong("online_flow_sum", 0L);
      if (!d) {
        UsageStatusLog.initLog();
      }
      g.start();
      h = new ajt.b(g.getLooper());
      if (d)
      {
        l = new ajs();
        paramContext = new IntentFilter();
        paramContext.addAction("android.net.conn.CONNECTIVITY_CHANGE");
      }
      try
      {
        c.unregisterReceiver(l);
        c.registerReceiver(l, paramContext);
        h.post(new aju(this));
        if (!d)
        {
          paramContext = new IntentFilter();
          paramContext.addAction("android.intent.action.BATTERY_CHANGED");
          paramContext = c.registerReceiver(null, paramContext);
          if ((paramContext != null) && (paramContext.getIntExtra("plugged", 0) != 0)) {
            r = true;
          }
          paramContext = new IntentFilter();
          paramContext.addAction("android.intent.action.TIME_SET");
          paramContext.addAction("android.intent.action.ACTION_POWER_CONNECTED");
          paramContext.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
          c.registerReceiver(new ajt.a(null), paramContext);
          a(System.currentTimeMillis());
          b();
        }
        return;
        i1 = ajk.a;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
  }
  
  public static ajt a(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (a == null) {}
    synchronized (b)
    {
      if (a == null) {
        a = new ajt(paramContext, paramBoolean1, paramBoolean2);
      }
      return a;
    }
  }
  
  private ajy a(byte[] paramArrayOfByte)
  {
    String str1 = Utils.bytesToHexString(Utils.getMD5(paramArrayOfByte));
    int i1 = (int)(System.currentTimeMillis() / 1000L);
    String str2 = String.valueOf(new Random().nextInt() + i1);
    String str3 = String.valueOf(i1);
    return new ajy(new ajz[] { new akb("nonce", str2), new akb("ts", str3), new akb("md5", str1), new akb("sign", Utils.bytesToHexString(Utils.getMD5(String.format("key=OjUiuYe80AUYnbgBNT6&nonce=%s&ts=%s&md5=%s", new Object[] { str2, str3, str1 }).getBytes()))), new ajx("collect", "collect", paramArrayOfByte, null, "UTF-8") });
  }
  
  private String a(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 1: 
      return "action_x";
    case 2: 
      return "page";
    }
    return "log";
  }
  
  private JSONObject a(String paramString)
  {
    if (j.size() < 1) {}
    for (;;)
    {
      return null;
      JSONObject localJSONObject = new JSONObject();
      try
      {
        localJSONObject.put("ver", "2.0");
        a(localJSONObject, paramString);
        paramString = e();
        if (paramString != null)
        {
          localJSONObject.put("apps", paramString);
          return localJSONObject;
        }
      }
      catch (JSONException paramString)
      {
        paramString.printStackTrace();
      }
    }
    return null;
  }
  
  private void a(long paramLong)
  {
    PendingIntent localPendingIntent = PendingIntent.getBroadcast(c, 1, new Intent("com.meizu.usagestats.check_upload"), 134217728);
    AlarmManager localAlarmManager = (AlarmManager)c.getSystemService("alarm");
    if (localAlarmManager == null) {
      return;
    }
    localAlarmManager.cancel(localPendingIntent);
    localAlarmManager.set(1, 28800000L + paramLong, localPendingIntent);
    p = paramLong;
  }
  
  private void a(JSONObject paramJSONObject, String paramString)
  {
    paramJSONObject.put("device", Build.MODEL);
    paramJSONObject.put("os_version", Build.VERSION.RELEASE);
    paramJSONObject.put("imei", Utils.getIMEI(c));
    paramJSONObject.put("country", Utils.getCountry(c));
    paramJSONObject.put("operator", Utils.getOperater(c));
    paramJSONObject.put("root", Utils.isRoot(c));
    paramJSONObject.put("sn", Utils.getSN());
    paramJSONObject.put("flyme_uid", Utils.getFlymeUid(c));
    String str = paramString;
    if (TextUtils.isEmpty(paramString)) {
      str = Build.DISPLAY;
    }
    paramJSONObject.put("flyme_ver", str);
    paramJSONObject.put("mac_address", Utils.getMACAddress(c));
    paramJSONObject.put("product_model", Utils.PRODUCT_MODEL);
    paramJSONObject.put("build_mask", Utils.BUILD_MASK);
  }
  
  static void a(boolean paramBoolean)
  {
    if (a != null)
    {
      if (paramBoolean) {
        a.b(2);
      }
    }
    else {
      return;
    }
    a.b(3);
  }
  
  private boolean a(JSONObject paramJSONObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramJSONObject != null)
    {
      bool1 = bool2;
      if (paramJSONObject.toString().getBytes().length < 10240L)
      {
        bool1 = bool2;
        if (q)
        {
          UsageStatusLog.d("UsageStatsUploader", "offline this data.size=" + paramJSONObject.toString().getBytes().length);
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  private boolean a(JSONObject paramJSONObject, boolean paramBoolean)
  {
    if (paramJSONObject == null) {}
    for (;;)
    {
      return false;
      String str = paramJSONObject.toString();
      if ((!TextUtils.isEmpty(str)) && ((paramBoolean) || (((!d) || (b(paramJSONObject))) && ((d) || (a(paramJSONObject))))))
      {
        UsageStatusLog.d("UsageStatsUploader", "uploadEvent data=" + paramJSONObject);
        paramJSONObject = a(str.getBytes());
        if (paramJSONObject != null)
        {
          int i1 = 0;
          while ((i1 < 3) && (g()))
          {
            if (d(ajv.a("https://uxip.meizu.com/api/upload", paramJSONObject)))
            {
              UsageStatusLog.d("UsageStatsUploader", "uploadEvents, upload successfully.");
              f();
              return true;
            }
            i1 += 1;
          }
        }
      }
    }
  }
  
  private JSONArray b(String paramString)
  {
    long l1 = 0L;
    Object localObject1 = (Map)j.get(paramString);
    if ((localObject1 == null) || (((Map)localObject1).size() < 1)) {
      return null;
    }
    paramString = new JSONArray();
    localObject1 = ((Map)localObject1).entrySet().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (Map.Entry)((Iterator)localObject1).next();
      Object localObject3 = (List)((Map.Entry)localObject2).getValue();
      if ((localObject3 != null) && (((List)localObject3).size() >= 1))
      {
        JSONObject localJSONObject1 = new JSONObject();
        localJSONObject1.put("sid", ((Map.Entry)localObject2).getKey());
        localObject2 = new JSONArray();
        localObject3 = ((List)localObject3).iterator();
        if (((Iterator)localObject3).hasNext())
        {
          UsageStatsProxy.Event localEvent = (UsageStatsProxy.Event)((Iterator)localObject3).next();
          long l2 = l1;
          if (0L == l1) {
            l2 = localEvent.k();
          }
          JSONObject localJSONObject2 = new JSONObject();
          localJSONObject2.put("network", localEvent.j());
          localJSONObject2.put("type", a(localEvent.b()));
          localJSONObject2.put("name", localEvent.a());
          if (2 == localEvent.b()) {
            if (localEvent.g() == null) {}
          }
          for (;;)
          {
            try
            {
              localJSONObject2.put("launch", ((JSONObject)localEvent.g()).get("start_time"));
              localJSONObject2.put("terminate", ((JSONObject)localEvent.g()).get("stop_time"));
              ((JSONArray)localObject2).put(localJSONObject2);
              l1 = l2;
            }
            catch (Exception localException)
            {
              localException.printStackTrace();
              continue;
            }
            localJSONObject2.put("time", localException.c());
            localJSONObject2.put("page", localException.f());
            localJSONObject2.put("value", localException.g());
          }
        }
        localJSONObject1.put("channel_id", l1);
        localJSONObject1.put("events", localObject2);
        paramString.put(localJSONObject1);
      }
    }
    if (paramString.length() > 0) {
      return paramString;
    }
    return null;
  }
  
  private void b(int paramInt)
  {
    h.sendEmptyMessage(paramInt);
  }
  
  private void b(long paramLong)
  {
    o = paramLong;
    i.edit().putLong("last_upload_time", o).apply();
  }
  
  private boolean b(JSONObject paramJSONObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramJSONObject != null)
    {
      long l1 = t + paramJSONObject.toString().getBytes().length;
      UsageStatusLog.d("UsageStatsUploader", "mOnlineDayFlowSum=" + t + " online this data.size=" + paramJSONObject.toString().getBytes().length);
      bool1 = bool2;
      if (l1 < 1048576L)
      {
        t = l1;
        i.edit().putLong("online_flow_sum", t).apply();
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private String c(String paramString)
  {
    PackageManager localPackageManager = c.getPackageManager();
    if (localPackageManager == null) {
      return "";
    }
    Object localObject = null;
    try
    {
      paramString = localPackageManager.getPackageInfo(paramString, 0);
      if (paramString != null) {
        return versionName;
      }
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
        paramString = (String)localObject;
      }
    }
    return "";
  }
  
  /* Error */
  private JSONObject c()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aload_0
    //   3: getfield 96	ajt:f	Lajq;
    //   6: aload_0
    //   7: getfield 89	ajt:n	I
    //   10: invokevirtual 638	ajq:a	(I)Landroid/database/Cursor;
    //   13: astore_3
    //   14: aload_3
    //   15: ifnonnull +5 -> 20
    //   18: aconst_null
    //   19: areturn
    //   20: ldc_w 445
    //   23: new 447	java/lang/StringBuilder
    //   26: dup
    //   27: invokespecial 448	java/lang/StringBuilder:<init>	()V
    //   30: ldc_w 640
    //   33: invokevirtual 454	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: aload_3
    //   37: invokeinterface 645 1 0
    //   42: invokevirtual 457	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   45: invokevirtual 458	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   48: invokestatic 460	com/meizu/statsapp/UsageStatusLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   51: aload_0
    //   52: getfield 67	ajt:j	Ljava/util/Map;
    //   55: invokeinterface 648 1 0
    //   60: aload_3
    //   61: invokeinterface 651 1 0
    //   66: ifeq +191 -> 257
    //   69: aload_3
    //   70: invokestatic 654	ajq:a	(Landroid/database/Cursor;)Lcom/meizu/statsapp/UsageStatsProxy$Event;
    //   73: astore 4
    //   75: aload 4
    //   77: ifnull -17 -> 60
    //   80: aload_1
    //   81: ifnonnull +215 -> 296
    //   84: aload 4
    //   86: invokevirtual 656	com/meizu/statsapp/UsageStatsProxy$Event:l	()Ljava/lang/String;
    //   89: astore_2
    //   90: aload_2
    //   91: astore_1
    //   92: aload 4
    //   94: invokevirtual 658	com/meizu/statsapp/UsageStatsProxy$Event:e	()Ljava/lang/String;
    //   97: astore 5
    //   99: aload 4
    //   101: invokevirtual 660	com/meizu/statsapp/UsageStatsProxy$Event:d	()Ljava/lang/String;
    //   104: astore_2
    //   105: aload_0
    //   106: getfield 67	ajt:j	Ljava/util/Map;
    //   109: aload 5
    //   111: invokeinterface 664 2 0
    //   116: ifne +59 -> 175
    //   119: new 64	java/util/HashMap
    //   122: dup
    //   123: invokespecial 65	java/util/HashMap:<init>	()V
    //   126: astore 6
    //   128: new 666	java/util/ArrayList
    //   131: dup
    //   132: invokespecial 667	java/util/ArrayList:<init>	()V
    //   135: astore 7
    //   137: aload 7
    //   139: aload 4
    //   141: invokeinterface 670 2 0
    //   146: pop
    //   147: aload 6
    //   149: aload_2
    //   150: aload 7
    //   152: invokeinterface 673 3 0
    //   157: pop
    //   158: aload_0
    //   159: getfield 67	ajt:j	Ljava/util/Map;
    //   162: aload 5
    //   164: aload 6
    //   166: invokeinterface 673 3 0
    //   171: pop
    //   172: goto -112 -> 60
    //   175: aload_0
    //   176: getfield 67	ajt:j	Ljava/util/Map;
    //   179: aload 5
    //   181: invokeinterface 495 2 0
    //   186: checkcast 293	java/util/Map
    //   189: astore 5
    //   191: aload 5
    //   193: aload_2
    //   194: invokeinterface 664 2 0
    //   199: ifne +36 -> 235
    //   202: new 666	java/util/ArrayList
    //   205: dup
    //   206: invokespecial 667	java/util/ArrayList:<init>	()V
    //   209: astore 6
    //   211: aload 6
    //   213: aload 4
    //   215: invokeinterface 670 2 0
    //   220: pop
    //   221: aload 5
    //   223: aload_2
    //   224: aload 6
    //   226: invokeinterface 673 3 0
    //   231: pop
    //   232: goto -172 -> 60
    //   235: aload 5
    //   237: aload_2
    //   238: invokeinterface 495 2 0
    //   243: checkcast 524	java/util/List
    //   246: aload 4
    //   248: invokeinterface 670 2 0
    //   253: pop
    //   254: goto -194 -> 60
    //   257: aload_3
    //   258: invokeinterface 676 1 0
    //   263: aload_0
    //   264: aload_1
    //   265: invokespecial 678	ajt:a	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   268: areturn
    //   269: astore_2
    //   270: aload_2
    //   271: invokevirtual 565	java/lang/Exception:printStackTrace	()V
    //   274: aload_3
    //   275: invokeinterface 676 1 0
    //   280: goto -17 -> 263
    //   283: astore_1
    //   284: aload_3
    //   285: invokeinterface 676 1 0
    //   290: aload_1
    //   291: athrow
    //   292: astore_2
    //   293: goto -23 -> 270
    //   296: goto -204 -> 92
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	299	0	this	ajt
    //   1	264	1	localObject1	Object
    //   283	8	1	localObject2	Object
    //   89	149	2	str	String
    //   269	2	2	localException1	Exception
    //   292	1	2	localException2	Exception
    //   13	272	3	localCursor	android.database.Cursor
    //   73	174	4	localEvent	UsageStatsProxy.Event
    //   97	139	5	localObject3	Object
    //   126	99	6	localObject4	Object
    //   135	16	7	localArrayList	ArrayList
    // Exception table:
    //   from	to	target	type
    //   92	172	269	java/lang/Exception
    //   175	232	269	java/lang/Exception
    //   235	254	269	java/lang/Exception
    //   60	75	283	finally
    //   84	90	283	finally
    //   92	172	283	finally
    //   175	232	283	finally
    //   235	254	283	finally
    //   270	274	283	finally
    //   60	75	292	java/lang/Exception
    //   84	90	292	java/lang/Exception
  }
  
  private void c(boolean paramBoolean)
  {
    q = paramBoolean;
    i.edit().putBoolean("today_upload_flag", q).apply();
  }
  
  /* Error */
  private JSONObject d()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 96	ajt:f	Lajq;
    //   4: aload_0
    //   5: getfield 89	ajt:n	I
    //   8: invokestatic 201	java/lang/System:currentTimeMillis	()J
    //   11: ldc2_w 684
    //   14: lsub
    //   15: invokevirtual 688	ajq:a	(IJ)Landroid/database/Cursor;
    //   18: astore_3
    //   19: aload_3
    //   20: ifnonnull +5 -> 25
    //   23: aconst_null
    //   24: areturn
    //   25: ldc_w 445
    //   28: new 447	java/lang/StringBuilder
    //   31: dup
    //   32: invokespecial 448	java/lang/StringBuilder:<init>	()V
    //   35: ldc_w 690
    //   38: invokevirtual 454	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: aload_3
    //   42: invokeinterface 645 1 0
    //   47: invokevirtual 457	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   50: invokevirtual 458	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   53: invokestatic 460	com/meizu/statsapp/UsageStatusLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   56: aload_0
    //   57: getfield 67	ajt:j	Ljava/util/Map;
    //   60: invokeinterface 648 1 0
    //   65: aconst_null
    //   66: astore_1
    //   67: aload_3
    //   68: invokeinterface 651 1 0
    //   73: ifeq +176 -> 249
    //   76: aload_0
    //   77: getfield 67	ajt:j	Ljava/util/Map;
    //   80: invokeinterface 296 1 0
    //   85: getstatic 692	ajk:b	I
    //   88: if_icmpge +161 -> 249
    //   91: aload_3
    //   92: invokestatic 654	ajq:a	(Landroid/database/Cursor;)Lcom/meizu/statsapp/UsageStatsProxy$Event;
    //   95: astore 4
    //   97: aload 4
    //   99: ifnull -32 -> 67
    //   102: aload_1
    //   103: ifnonnull +293 -> 396
    //   106: aload 4
    //   108: invokevirtual 656	com/meizu/statsapp/UsageStatsProxy$Event:l	()Ljava/lang/String;
    //   111: astore_2
    //   112: aload_2
    //   113: astore_1
    //   114: aload 4
    //   116: invokevirtual 658	com/meizu/statsapp/UsageStatsProxy$Event:e	()Ljava/lang/String;
    //   119: astore 5
    //   121: aload 4
    //   123: invokevirtual 660	com/meizu/statsapp/UsageStatsProxy$Event:d	()Ljava/lang/String;
    //   126: astore_2
    //   127: aload_0
    //   128: getfield 67	ajt:j	Ljava/util/Map;
    //   131: aload 5
    //   133: invokeinterface 664 2 0
    //   138: ifne +59 -> 197
    //   141: new 64	java/util/HashMap
    //   144: dup
    //   145: invokespecial 65	java/util/HashMap:<init>	()V
    //   148: astore 6
    //   150: new 666	java/util/ArrayList
    //   153: dup
    //   154: invokespecial 667	java/util/ArrayList:<init>	()V
    //   157: astore 7
    //   159: aload 7
    //   161: aload 4
    //   163: invokeinterface 670 2 0
    //   168: pop
    //   169: aload 6
    //   171: aload_2
    //   172: aload 7
    //   174: invokeinterface 673 3 0
    //   179: pop
    //   180: aload_0
    //   181: getfield 67	ajt:j	Ljava/util/Map;
    //   184: aload 5
    //   186: aload 6
    //   188: invokeinterface 673 3 0
    //   193: pop
    //   194: goto -127 -> 67
    //   197: aload_0
    //   198: getfield 67	ajt:j	Ljava/util/Map;
    //   201: aload 5
    //   203: invokeinterface 495 2 0
    //   208: checkcast 293	java/util/Map
    //   211: astore 5
    //   213: aload 5
    //   215: aload_2
    //   216: invokeinterface 664 2 0
    //   221: ifne +6 -> 227
    //   224: goto -157 -> 67
    //   227: aload 5
    //   229: aload_2
    //   230: invokeinterface 495 2 0
    //   235: checkcast 524	java/util/List
    //   238: aload 4
    //   240: invokeinterface 670 2 0
    //   245: pop
    //   246: goto -179 -> 67
    //   249: aload_3
    //   250: invokeinterface 676 1 0
    //   255: aload_1
    //   256: astore_2
    //   257: aload_0
    //   258: aload_2
    //   259: invokespecial 678	ajt:a	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   262: astore_1
    //   263: aload_1
    //   264: ifnull +126 -> 390
    //   267: aload_1
    //   268: invokevirtual 441	org/json/JSONObject:toString	()Ljava/lang/String;
    //   271: invokevirtual 268	java/lang/String:getBytes	()[B
    //   274: arraylength
    //   275: i2l
    //   276: ldc2_w 442
    //   279: lcmp
    //   280: ifle +110 -> 390
    //   283: ldc_w 445
    //   286: new 447	java/lang/StringBuilder
    //   289: dup
    //   290: invokespecial 448	java/lang/StringBuilder:<init>	()V
    //   293: ldc_w 694
    //   296: invokevirtual 454	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   299: aload_1
    //   300: invokevirtual 441	org/json/JSONObject:toString	()Ljava/lang/String;
    //   303: invokevirtual 268	java/lang/String:getBytes	()[B
    //   306: arraylength
    //   307: invokevirtual 457	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   310: invokevirtual 458	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   313: invokestatic 460	com/meizu/statsapp/UsageStatusLog:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   316: aload_0
    //   317: getfield 67	ajt:j	Ljava/util/Map;
    //   320: invokeinterface 697 1 0
    //   325: invokeinterface 508 1 0
    //   330: astore_1
    //   331: aload_1
    //   332: ifnull +53 -> 385
    //   335: aload_0
    //   336: getfield 67	ajt:j	Ljava/util/Map;
    //   339: aload_1
    //   340: invokeinterface 517 1 0
    //   345: invokeinterface 700 2 0
    //   350: pop
    //   351: aload_0
    //   352: aload_2
    //   353: invokespecial 678	ajt:a	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   356: astore_1
    //   357: goto -94 -> 263
    //   360: astore_2
    //   361: aload_2
    //   362: invokevirtual 565	java/lang/Exception:printStackTrace	()V
    //   365: aload_3
    //   366: invokeinterface 676 1 0
    //   371: aload_1
    //   372: astore_2
    //   373: goto -116 -> 257
    //   376: astore_1
    //   377: aload_3
    //   378: invokeinterface 676 1 0
    //   383: aload_1
    //   384: athrow
    //   385: aconst_null
    //   386: astore_1
    //   387: goto -124 -> 263
    //   390: aload_1
    //   391: areturn
    //   392: astore_2
    //   393: goto -32 -> 361
    //   396: goto -282 -> 114
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	399	0	this	ajt
    //   66	306	1	localObject1	Object
    //   376	8	1	localObject2	Object
    //   386	5	1	localJSONObject	JSONObject
    //   111	242	2	localObject3	Object
    //   360	2	2	localException1	Exception
    //   372	1	2	localObject4	Object
    //   392	1	2	localException2	Exception
    //   18	360	3	localCursor	android.database.Cursor
    //   95	144	4	localEvent	UsageStatsProxy.Event
    //   119	109	5	localObject5	Object
    //   148	39	6	localHashMap	HashMap
    //   157	16	7	localArrayList	ArrayList
    // Exception table:
    //   from	to	target	type
    //   114	194	360	java/lang/Exception
    //   197	224	360	java/lang/Exception
    //   227	246	360	java/lang/Exception
    //   67	97	376	finally
    //   106	112	376	finally
    //   114	194	376	finally
    //   197	224	376	finally
    //   227	246	376	finally
    //   361	365	376	finally
    //   67	97	392	java/lang/Exception
    //   106	112	392	java/lang/Exception
  }
  
  private boolean d(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return false;
      try
      {
        int i1 = new JSONObject(paramString).getInt("code");
        if (200 == i1) {
          return true;
        }
      }
      catch (JSONException paramString)
      {
        paramString.printStackTrace();
      }
    }
    return false;
  }
  
  private JSONArray e()
  {
    JSONArray localJSONArray = new JSONArray();
    Iterator localIterator = j.keySet().iterator();
    if (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("package", str);
      if ("com.meizu.uxip.log".equals(str)) {}
      for (Object localObject = "1.0";; localObject = c(str))
      {
        localJSONObject.put("version", localObject);
        localObject = b(str);
        if (localObject == null) {
          break;
        }
        localJSONObject.put("sessions", localObject);
        localJSONArray.put(localJSONObject);
        break;
      }
    }
    if (localJSONArray.length() > 0) {
      return localJSONArray;
    }
    return null;
  }
  
  private void f()
  {
    Iterator localIterator1 = j.entrySet().iterator();
    while (localIterator1.hasNext())
    {
      Iterator localIterator2 = ((Map)((Map.Entry)localIterator1.next()).getValue()).entrySet().iterator();
      while (localIterator2.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator2.next();
        f.a((Collection)localEntry.getValue());
      }
    }
    if (d)
    {
      k = f.a();
      UsageStatusLog.d("UsageStatsUploader", "upload successful! update mEventCome=" + k);
    }
  }
  
  private boolean g()
  {
    return Utils.isNetworkWorking(c);
  }
  
  private void h()
  {
    long l1;
    long l2;
    if (d)
    {
      l1 = System.currentTimeMillis();
      l2 = i.getLong("online_last_upload_time", 0L);
      if (0L != l2) {
        break label55;
      }
      i.edit().putLong("online_last_upload_time", l1).apply();
    }
    label55:
    while (Math.abs(l1 - l2) < 86400000L) {
      return;
    }
    i.edit().putLong("online_last_upload_time", l1).apply();
    i();
  }
  
  private void i()
  {
    s = 1;
    t = 0L;
    i.edit().putInt("ratio", s).apply();
    i.edit().putLong("online_flow_sum", t).apply();
  }
  
  void a()
  {
    k += 1;
    UsageStatusLog.d("UsageStatsUploader", "eventComein, mEventCome=" + k + " mRatio=" + s);
    if (k == ajk.f * s)
    {
      u = true;
      b();
    }
  }
  
  boolean a(UsageStatsProxy.Event paramEvent)
  {
    if ((!e) || (paramEvent == null)) {
      return false;
    }
    JSONObject localJSONObject = new JSONObject();
    for (;;)
    {
      try
      {
        localJSONObject.put("ver", "2.0");
        a(localJSONObject, paramEvent.l());
        localJSONObject.put("package", paramEvent.e());
        localJSONObject.put("version", c(paramEvent.e()));
        localJSONObject.put("sid", paramEvent.d());
        localObject = new JSONObject();
        ((JSONObject)localObject).put("type", a(paramEvent.b()));
        ((JSONObject)localObject).put("name", paramEvent.a());
        ((JSONObject)localObject).put("time", paramEvent.c());
        ((JSONObject)localObject).put("page", paramEvent.f());
        paramEvent = paramEvent.h();
        if (Utils.isEmpty(paramEvent)) {
          continue;
        }
        ((JSONObject)localObject).put("value", new JSONObject(paramEvent));
        localJSONObject.put("event", localObject);
      }
      catch (JSONException paramEvent)
      {
        Object localObject;
        int i1;
        String str1;
        String str2;
        ArrayList localArrayList;
        paramEvent.printStackTrace();
        continue;
      }
      if (localJSONObject.length() < 1) {
        break;
      }
      paramEvent = localJSONObject.toString().getBytes();
      if (paramEvent == null) {
        break;
      }
      paramEvent = Utils.bytesToHexString(Utils.getMD5(paramEvent));
      i1 = (int)(System.currentTimeMillis() / 1000L);
      localObject = String.valueOf(new Random().nextInt() + i1);
      str1 = String.valueOf(i1);
      str2 = Utils.bytesToHexString(Utils.getMD5(String.format("key=OjUiuYe80AUYnbgBNT6&nonce=%s&ts=%s&md5=%s", new Object[] { localObject, str1, paramEvent }).getBytes()));
      localArrayList = new ArrayList();
      localArrayList.add(new BasicNameValuePair("nonce", (String)localObject));
      localArrayList.add(new BasicNameValuePair("ts", str1));
      localArrayList.add(new BasicNameValuePair("md5", paramEvent));
      localArrayList.add(new BasicNameValuePair("sign", str2));
      localArrayList.add(new BasicNameValuePair("uxip_data", localJSONObject.toString()));
      try
      {
        boolean bool = d(ajv.a("https://uxip.meizu.com/api/report/realtime", new UrlEncodedFormEntity(localArrayList)));
        return bool;
      }
      catch (UnsupportedEncodingException paramEvent)
      {
        paramEvent.printStackTrace();
      }
      ((JSONObject)localObject).put("value", new JSONObject());
    }
    return false;
  }
  
  void b()
  {
    if ((d) && (!e)) {
      UsageStatusLog.d("UsageStatsUploader", "checkUpload, mUpload=" + e);
    }
    long l1;
    do
    {
      return;
      l1 = Math.abs(System.currentTimeMillis() - o);
      UsageStatusLog.d("UsageStatsUploader", "checkUpload, mOnline=" + d + ", intervalTime=" + l1 / 3600000L + "h, mPowerConnecting=" + r + ", mLastUploadTime=" + o);
      if (l1 >= 86400000L) {
        c(true);
      }
    } while ((!d) && (0L != o) && (l1 < 86400000L) && ((!r) || (l1 < 28800000L)));
    h.removeMessages(1);
    h.sendEmptyMessage(1);
  }
  
  void b(boolean paramBoolean)
  {
    if (e != paramBoolean)
    {
      e = paramBoolean;
      UsageStatusLog.d("UsageStatsUploader", "setUploaded, mUpload=" + e);
    }
    if (e) {
      b();
    }
  }
  
  class a
    extends BroadcastReceiver
  {
    private a() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if ("android.intent.action.ACTION_POWER_CONNECTED".equals(paramIntent.getAction()))
      {
        if (!ajt.o(ajt.this)) {
          ajt.d(ajt.this, true);
        }
        ajt.f(ajt.this).removeMessages(5);
        ajt.f(ajt.this).sendEmptyMessage(5);
        UsageStatusLog.d("UsageStatsUploader", "ACTION_POWER_CONNECTED, mPowerConnecting=" + ajt.o(ajt.this));
      }
      do
      {
        return;
        if ("android.intent.action.ACTION_POWER_DISCONNECTED".equals(paramIntent.getAction()))
        {
          if (ajt.o(ajt.this)) {
            ajt.d(ajt.this, false);
          }
          UsageStatusLog.d("UsageStatsUploader", "ACTION_POWER_DISCONNECTED, mPowerConnecting=" + ajt.o(ajt.this));
          return;
        }
      } while (!"android.intent.action.TIME_SET".equals(paramIntent.getAction()));
      Log.d("UsageStatsUploader", "ACTION_TIME_CHANGED, ");
      ajt.f(ajt.this).sendEmptyMessage(4);
    }
  }
  
  class b
    extends Handler
  {
    public b(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      int j = 0;
      int i = 0;
      for (;;)
      {
        try
        {
          switch (what)
          {
          case 3: 
            if (ajt.b(ajt.this))
            {
              if (!ajt.c(ajt.this))
              {
                ajt.a(ajt.this, true);
                b();
              }
              UsageStatusLog.d("UsageStatsUploader", "NETWORK_STATE_CHANGED, mNetworkConnected=" + ajt.c(ajt.this));
              return;
            }
            break;
          }
        }
        catch (Exception paramMessage)
        {
          paramMessage.printStackTrace();
          return;
        }
        if (ajt.c(ajt.this)) {
          ajt.a(ajt.this, false);
        }
      }
      UsageStatusLog.d("UsageStatsUploader", "UPLOAD_TIME_ALARM");
      b();
      ajt.a(ajt.this, System.currentTimeMillis());
      return;
      long l = System.currentTimeMillis();
      if (l < ajt.d(ajt.this))
      {
        b();
        ajt.a(ajt.this, l);
        return;
        b();
        return;
        ajt.e(ajt.this);
        for (;;)
        {
          try
          {
            ajt.f(ajt.this).removeMessages(1);
            if (ajt.a(ajt.this).a() > 0)
            {
              ajt.a(ajt.this).b();
              if (!Utils.isWiFiWorking(ajt.g(ajt.this))) {
                continue;
              }
              UsageStatusLog.d("UsageStatsUploader", "upload by wifi");
              paramMessage = ajt.h(ajt.this);
              j = i;
              i = j;
              if (ajt.a(ajt.this, paramMessage, true))
              {
                i = j;
                if (j == 0) {
                  i = 1;
                }
                if (ajt.a(ajt.this).a() >= 1) {
                  continue;
                }
              }
              if ((i != 0) && (!ajt.i(ajt.this)))
              {
                l = System.currentTimeMillis();
                ajt.b(ajt.this, l);
                ajt.a(ajt.this, l);
              }
              if ((ajt.i(ajt.this)) && (ajt.k(ajt.this)))
              {
                if (i == 0)
                {
                  ajt.a(ajt.this, ajt.l(ajt.this) * 2);
                  ajt.m(ajt.this).edit().putInt("ratio", ajt.l(ajt.this)).apply();
                }
                ajt.b(ajt.this, 0);
                ajt.c(ajt.this, false);
              }
            }
            ajt.n(ajt.this).clear();
          }
          catch (Exception paramMessage)
          {
            paramMessage.printStackTrace();
            continue;
          }
          if (!ajt.i(ajt.this)) {
            break;
          }
          ajt.f(ajt.this).sendEmptyMessageDelayed(1, 1800000L);
          return;
          paramMessage = ajt.h(ajt.this);
          j = i;
          continue;
          i = j;
          if (!Utils.isWiFiWorking(ajt.g(ajt.this)))
          {
            i = j;
            if (Utils.isNetworkWorking(ajt.g(ajt.this)))
            {
              UsageStatusLog.d("UsageStatsUploader", "upload by mobile");
              if (ajt.i(ajt.this))
              {
                paramMessage = ajt.h(ajt.this);
                i = j;
                if (ajt.a(ajt.this, paramMessage, false)) {
                  i = 1;
                }
              }
              else
              {
                paramMessage = ajt.j(ajt.this);
                i = j;
                if (ajt.a(ajt.this, paramMessage, false))
                {
                  ajt.b(ajt.this, false);
                  i = 1;
                }
              }
            }
          }
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     ajt
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */