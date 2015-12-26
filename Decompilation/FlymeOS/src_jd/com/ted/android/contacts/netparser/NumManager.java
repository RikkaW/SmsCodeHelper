package com.ted.android.contacts.netparser;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import anw;
import aoa;
import aoc;
import aod;
import aoe;
import aof;
import aog;
import aoh;
import ape;
import apm;
import apn;
import apo;
import aut;
import bp;
import com.ted.android.contacts.common.DataBus;
import com.ted.android.contacts.common.util.NovoFileUtil;
import com.ted.android.contacts.netparser.model.NumItem;
import com.ted.android.contacts.netparser.model.YellowPageData;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NumManager
{
  public static String a = "1.0";
  public static int b = 0;
  private static final String c = NumManager.class.getSimpleName();
  private static aoa e = new aoa();
  private static NumManager f = null;
  private Context d;
  private String g;
  private int h;
  private String i = "";
  
  private NumManager() {}
  
  private NumManager(Context paramContext)
  {
    d = paramContext;
    g = Build.MODEL;
    h = Build.VERSION.SDK_INT;
    DataBus.DID = anw.a(d);
    b();
    a();
  }
  
  private aof a(String paramString, aod paramaod)
  {
    aof localaof = new aof();
    localaof.a(paramString);
    localaof.a(paramaod);
    localaof.a(d);
    return localaof;
  }
  
  private String a(String paramString1, float paramFloat1, float paramFloat2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString2, int paramInt5)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("header", a(paramFloat1, paramFloat2));
      paramString1 = (JSONObject)c(paramString1);
      paramString1.put("dataType", String.valueOf(paramInt1));
      paramString1.put("operationType", String.valueOf(paramInt2));
      paramString1.put("duration", String.valueOf(paramInt3));
      paramString1.put("contact", String.valueOf(paramInt4));
      if ((1 == paramInt1) && (!TextUtils.isEmpty(paramString2))) {
        paramString1.put("contentSign", new JSONArray(paramString2));
      }
      paramString1.put("lasttime", paramInt5);
      localJSONObject.put("data", paramString1);
    }
    catch (JSONException paramString1)
    {
      for (;;)
      {
        Log.e(c, "make json error, maybe a value is null", paramString1);
      }
    }
    return apm.a(localJSONObject.toString());
  }
  
  private JSONObject a(float paramFloat1, float paramFloat2)
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("deviceid", DataBus.DID);
    localJSONObject.put("osversion", h);
    localJSONObject.put("appversion", a);
    localJSONObject.put("phonetype", g);
    localJSONObject.put("map_type", b);
    JSONArray localJSONArray = new JSONArray();
    localJSONArray.put(Float.toString(paramFloat1));
    localJSONArray.put(Float.toString(paramFloat2));
    localJSONObject.put("location", localJSONArray);
    return localJSONObject;
  }
  
  public static void a(Context paramContext)
  {
    try
    {
      if (f == null) {
        f = new NumManager(paramContext.getApplicationContext());
      }
      return;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  private Object c(String paramString)
  {
    JSONObject localJSONObject = new JSONObject();
    Log.e(c, "before make json phone" + paramString);
    localJSONObject.put("phone", apo.a(paramString));
    Log.e(c, "after make json phone - " + paramString);
    return localJSONObject;
  }
  
  public static NumManager getInstnace()
  {
    if (f == null) {
      Log.e(c, "instnace is null, shoud call NumManager.init(context)");
    }
    return f;
  }
  
  public NumItem a(String paramString)
  {
    NumItem localNumItem = aoh.a(d).a(paramString);
    if (localNumItem == null) {
      aoh.a(d).e(paramString);
    }
    return localNumItem;
  }
  
  public List<NumItem> a(List<YellowPageData> paramList, List<String> paramList1, List<ape> paramList2)
  {
    paramList1 = aoh.a(d).a(paramList1);
    HashMap localHashMap = new HashMap();
    Object localObject = paramList1.iterator();
    if (!((Iterator)localObject).hasNext()) {
      paramList = paramList.iterator();
    }
    for (;;)
    {
      NumItem localNumItem;
      if (!paramList.hasNext())
      {
        return paramList1;
        localNumItem = (NumItem)((Iterator)localObject).next();
        localHashMap.put(localNumItem.a(), localNumItem);
        break;
      }
      localObject = ((YellowPageData)paramList.next()).d();
      if (localHashMap.containsKey(localObject))
      {
        localNumItem = (NumItem)localHashMap.get(localObject);
        if ((localNumItem.g()) && (!localNumItem.h()))
        {
          paramList.remove();
        }
        else if ((localNumItem.q()) && (paramList2 != null))
        {
          localObject = new ape((String)localObject);
          ((ape)localObject).a(localNumItem.c());
          ((ape)localObject).a(localNumItem.n());
          paramList2.add(localObject);
        }
      }
      else
      {
        aoh.a(d).e((String)localObject);
        localNumItem = new NumItem();
        localNumItem.c((String)localObject);
        localNumItem.e("");
        paramList1.add(localNumItem);
      }
    }
  }
  
  public void a()
  {
    try
    {
      InputStream localInputStream = NovoFileUtil.openLatestInputFile(d, "cache.dat");
      if (localInputStream != null) {
        try
        {
          aoe.a = Integer.valueOf(new DataInputStream(localInputStream).readLine()).intValue();
          Log.e("reloadCacheTime", aoe.a);
          return;
        }
        catch (Exception localException1)
        {
          for (;;)
          {
            localException1.printStackTrace();
          }
        }
      }
      return;
    }
    catch (Exception localException2)
    {
      localException2.printStackTrace();
    }
  }
  
  public void a(float paramFloat1, float paramFloat2, String paramString1, String paramString2, int paramInt1, int paramInt2, aut paramaut)
  {
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      JSONObject localJSONObject2 = new JSONObject();
      localJSONObject2.put("phone", paramString1);
      localJSONObject2.put("type", paramString2);
      localJSONObject2.put("dataType", String.valueOf(paramInt1));
      localJSONObject2.put("operationType", String.valueOf(paramInt2));
      localJSONObject1.put("header", a(paramFloat1, paramFloat2));
      localJSONObject1.put("data", localJSONObject2);
      paramString1 = apm.a(localJSONObject1.toString());
    }
    catch (JSONException paramString1)
    {
      try
      {
        paramString1 = new StringEntity(paramString1, "UTF-8");
        aoa.a(aoe.j, paramString1, paramaut);
        return;
        paramString1 = paramString1;
        Log.e(c, "make json error, maybe a value is null", paramString1);
      }
      catch (UnsupportedEncodingException paramString1)
      {
        for (;;)
        {
          Log.e(c, "new param entity error", paramString1);
          paramString1 = null;
        }
      }
    }
  }
  
  public void a(float paramFloat1, float paramFloat2, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, aut paramaut)
  {
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      JSONObject localJSONObject2 = new JSONObject();
      localJSONObject2.put("phone", paramString1);
      localJSONObject2.put("name", paramString2);
      localJSONObject2.put("oldName", paramString3);
      localJSONObject2.put("remark", paramString4);
      localJSONObject2.put("contactInfo", paramString5);
      localJSONObject1.put("header", a(paramFloat1, paramFloat2));
      localJSONObject1.put("data", localJSONObject2);
      paramString1 = apm.a(localJSONObject1.toString());
    }
    catch (JSONException paramString1)
    {
      try
      {
        paramString1 = new StringEntity(paramString1, "UTF-8");
        aoa.a(aoe.k, paramString1, paramaut);
        return;
        paramString1 = paramString1;
        Log.e(c, "make json error, maybe a value is null", paramString1);
      }
      catch (UnsupportedEncodingException paramString1)
      {
        for (;;)
        {
          Log.e(c, "new param entity error", paramString1);
          paramString1 = null;
        }
      }
    }
  }
  
  public void a(NumItem paramNumItem, float paramFloat1, float paramFloat2, aod paramaod, YellowPageData paramYellowPageData)
  {
    int j = 0;
    if (paramNumItem != null) {
      j = paramNumItem.f();
    }
    try
    {
      StringEntity localStringEntity = new StringEntity(a(paramYellowPageData.d(), paramFloat1, paramFloat2, paramYellowPageData.a(), paramYellowPageData.b(), paramYellowPageData.c(), paramYellowPageData.e(), paramYellowPageData.f(), j), "UTF-8");
      aof localaof = a(paramYellowPageData.d(), paramaod);
      localaof.a(paramaod);
      if ((paramNumItem != null) && (paramNumItem.q()))
      {
        paramaod = new ape(paramYellowPageData.d());
        paramaod.a(paramNumItem.c());
        paramaod.a(paramNumItem.n());
        localaof.a(paramaod);
      }
      aoa.a(aoe.f, localStringEntity, localaof);
      return;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      for (;;)
      {
        Log.e(c, "new param entity error", localUnsupportedEncodingException);
        Object localObject1 = null;
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;)
      {
        Log.e(c, "new param entity error", localIllegalArgumentException);
        Object localObject2 = null;
      }
    }
  }
  
  public void a(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    aoh.a(d).a(paramString1, paramString2, paramInt1, paramInt2);
    if (!"无标记".equals(paramString2)) {
      a(0.0F, 0.0F, paramString1, paramString2, paramInt1, paramInt2, new b(paramString1));
    }
  }
  
  public void a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    aoh.a(d).a(paramString1, paramString2, paramString3, paramString4, paramString5);
    a(0.0F, 0.0F, paramString1, paramString2, paramString3, paramString4, paramString5, new a(paramString1));
  }
  
  public void a(List<YellowPageData> paramList, float paramFloat1, float paramFloat2, aod paramaod, List<ape> paramList1)
  {
    int n = paramList.size();
    Log.i(c, "getNumListFromNetwork. size: " + n);
    int i1 = (int)Math.ceil(n * 1.0D / 20.0D);
    int j = 0;
    if (j >= i1) {
      return;
    }
    ArrayList localArrayList = new ArrayList();
    int m = j * 20;
    int k = m + 20;
    label93:
    Object localObject1;
    if (k > n)
    {
      k = n;
      localObject1 = new JSONObject();
    }
    for (;;)
    {
      try
      {
        ((JSONObject)localObject1).put("header", a(paramFloat1, paramFloat2));
        localObject3 = new JSONObject();
        ((JSONObject)localObject1).put("data", localObject3);
        localJSONArray = new JSONArray();
        if (m >= k)
        {
          ((JSONObject)localObject3).put("phones", localJSONArray);
          localObject1 = apm.a(((JSONObject)localObject1).toString());
        }
      }
      catch (JSONException localJSONException)
      {
        try
        {
          JSONArray localJSONArray;
          localObject1 = new StringEntity((String)localObject1, "UTF-8");
          Object localObject3 = new aog();
          ((aog)localObject3).a(localArrayList);
          ((aog)localObject3).a(d);
          ((aog)localObject3).a(paramaod);
          ((aog)localObject3).b(paramList1);
          aoa.a(aoe.g, (HttpEntity)localObject1, (aut)localObject3);
          j += 1;
          break;
          YellowPageData localYellowPageData = (YellowPageData)paramList.get(m);
          if (localYellowPageData == null) {
            break label446;
          }
          JSONObject localJSONObject = new JSONObject();
          String str = localYellowPageData.d();
          localArrayList.add(str);
          localJSONObject.put("phone", apo.a(str));
          localJSONObject.put("dataType", localYellowPageData.a());
          localJSONObject.put("operationType", localYellowPageData.b());
          localJSONObject.put("duration", localYellowPageData.c());
          localJSONObject.put("contact", localYellowPageData.e());
          if ((1 == localYellowPageData.a()) && (!TextUtils.isEmpty(localYellowPageData.f()))) {
            localJSONObject.put("contentSign", new JSONArray(localYellowPageData.f()));
          }
          localJSONArray.put(localJSONObject);
          break label446;
          localJSONException = localJSONException;
          Log.e(c, "make json error, maybe a value is null", localJSONException);
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException)
        {
          Log.e(c, "new param entity error", localUnsupportedEncodingException);
          Object localObject2 = null;
          continue;
        }
      }
      break label93;
      label446:
      m += 1;
    }
  }
  
  public void b()
  {
    bp localbp = new bp(d);
    aoe.b = localbp.a("number", "url");
    aoe.c = localbp.a("number", "murl");
    aoe.d = localbp.a("number", "turl");
    aoe.e = localbp.a("yellow", "surl");
    aoe.a();
  }
  
  public void b(String paramString)
  {
    aoh.a(d).b(paramString);
  }
  
  public void c()
  {
    Cursor localCursor = aoh.a(d).a();
    if ((localCursor != null) && (localCursor.moveToFirst()))
    {
      do
      {
        String str = localCursor.getString(0);
        a(0.0F, 0.0F, str, localCursor.getString(1), localCursor.getInt(2), localCursor.getInt(3), new b(str));
      } while (localCursor.moveToNext());
      localCursor.close();
    }
  }
  
  public void clickUrl(String paramString, float paramFloat1, float paramFloat2)
  {
    Object localObject = new JSONObject();
    try
    {
      ((JSONObject)localObject).put("header", a(paramFloat1, paramFloat2));
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("url", paramString);
      ((JSONObject)localObject).put("data", localJSONObject);
      paramString = apm.a(((JSONObject)localObject).toString());
    }
    catch (JSONException paramString)
    {
      try
      {
        paramString = new StringEntity(paramString, "UTF-8");
        localObject = new aoc();
        aoa.a(aoe.h, paramString, (aut)localObject);
        return;
        paramString = paramString;
        Log.e(c, "make json error, maybe a value is null", paramString);
      }
      catch (UnsupportedEncodingException paramString)
      {
        for (;;)
        {
          Log.e(c, "new param entity error", paramString);
          paramString = null;
        }
      }
    }
  }
  
  public void d()
  {
    Cursor localCursor = aoh.a(d).b();
    if ((localCursor != null) && (localCursor.moveToFirst()))
    {
      do
      {
        String str = localCursor.getString(0);
        a(0.0F, 0.0F, str, localCursor.getString(1), localCursor.getString(2), localCursor.getString(3), localCursor.getString(4), new a(str));
      } while (localCursor.moveToNext());
      localCursor.close();
    }
  }
  
  public List<String> e()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(Arrays.asList(apn.a));
    localArrayList.addAll(aoh.a(d).c());
    return localArrayList;
  }
  
  public List<String> f()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(Arrays.asList(apn.a));
    return localArrayList;
  }
  
  public List<String> g()
  {
    return aoh.a(d).c();
  }
  
  class a
    extends aoc
  {
    final String a;
    
    public a(String paramString)
    {
      a = paramString;
    }
    
    public void a(HttpResponse paramHttpResponse)
    {
      if (!Thread.currentThread().isInterrupted())
      {
        int i = paramHttpResponse.getStatusLine().getStatusCode();
        if ((i >= 200) && (i < 300) && (!Thread.currentThread().isInterrupted())) {
          aoh.a(NumManager.a(NumManager.this)).g(a);
        }
      }
    }
  }
  
  class b
    extends aoc
  {
    final String a;
    
    public b(String paramString)
    {
      a = paramString;
    }
    
    public void a(HttpResponse paramHttpResponse)
    {
      if (!Thread.currentThread().isInterrupted())
      {
        int i = paramHttpResponse.getStatusLine().getStatusCode();
        if ((i >= 200) && (i < 300) && (!Thread.currentThread().isInterrupted())) {
          aoh.a(NumManager.a(NumManager.this)).f(a);
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.ted.android.contacts.netparser.NumManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */