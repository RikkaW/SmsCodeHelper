package com.ted.sdk.yellow;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import anu;
import aod;
import aoh;
import asl;
import asm;
import aso;
import asp;
import asq;
import asr;
import ass;
import com.ted.android.contacts.common.ComManager;
import com.ted.android.contacts.netparser.NumManager;
import com.ted.android.contacts.netparser.model.NumItem;
import com.ted.android.contacts.netparser.model.YellowPageData;
import com.ted.sdk.yellow.entry.BaseItem;
import com.ted.sdk.yellow.entry.NumAllInfoItem;
import com.ted.sdk.yellow.entry.RequestData;
import com.ted.sdk.yellow.util.AssetsDatabaseHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;

public class YellowPageEngine
{
  private static final int DEFAULT_RAW_CONTACT_ID = -1;
  private static final String HANDLER_THREAD_NAME = "com.ted.android.contacts.yellow_page";
  private static final int MSG_GET_MULTIPLE = 20003;
  private static final int MSG_GET_SINLGE = 20001;
  private static final int MSG_GET_SINLGE_FROM_NET = 20002;
  private static final String TAG = YellowPageEngine.class.getSimpleName();
  private static final String prefix_cn = "【";
  private static final String prefix_en = "[";
  private static YellowPageEngine sEngine;
  private static final String suffix_cn = "】";
  private static final String suffix_en = "]";
  private Handler mHandler;
  private HandlerThread mHandlerThread;
  
  private void asyncGetItemFromNet(RequestData paramRequestData, ISingleCallback paramISingleCallback)
  {
    if (TextUtils.isEmpty(paramRequestData.getNumber()))
    {
      Log.e(TAG, "Input number in empty.");
      return;
    }
    if (!isAvailableHandlerQueue())
    {
      Log.e(TAG, "Handler is collected");
      return;
    }
    mHandlerThread.setUncaughtExceptionHandler(new a(paramISingleCallback));
    paramRequestData = new e(paramRequestData, paramISingleCallback);
    paramISingleCallback = mHandler.obtainMessage(20002);
    obj = paramRequestData;
    mHandler.sendMessage(paramISingleCallback);
  }
  
  public static YellowPageEngine getInstance()
  {
    if (sEngine == null)
    {
      Log.e(TAG, "Instnace is null, shoud call YellowPageEngine.init(context)");
      return null;
    }
    sEngine.initHanlder();
    return sEngine;
  }
  
  private String getMessageSign(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return paramString;
    }
    Object localObject1 = "";
    Object localObject2 = "";
    String str = paramString.trim();
    int i;
    if (str.startsWith("["))
    {
      i = str.indexOf("]");
      paramString = (String)localObject1;
      if (i > 0) {
        paramString = str.substring(1, i);
      }
      if (!str.endsWith("]")) {
        break label193;
      }
      i = str.lastIndexOf("[");
      localObject1 = localObject2;
      if (i > 0) {
        localObject1 = str.substring(i + 1, str.length() - 1);
      }
    }
    for (;;)
    {
      localObject2 = new JSONArray();
      if ((paramString.length() > 0) && (paramString.length() < 9)) {
        ((JSONArray)localObject2).put(paramString);
      }
      if ((((String)localObject1).length() > 0) && (((String)localObject1).length() < 9)) {
        ((JSONArray)localObject2).put(localObject1);
      }
      return ((JSONArray)localObject2).toString();
      paramString = (String)localObject1;
      if (!str.startsWith("【")) {
        break;
      }
      i = str.indexOf("】");
      paramString = (String)localObject1;
      if (i <= 0) {
        break;
      }
      paramString = str.substring(1, i);
      break;
      label193:
      localObject1 = localObject2;
      if (str.endsWith("】"))
      {
        i = str.lastIndexOf("【");
        localObject1 = localObject2;
        if (i > 0) {
          localObject1 = str.substring(i + 1, str.length() - 1);
        }
      }
    }
  }
  
  private BaseItem getTargetItem(String paramString, NumItem paramNumItem)
  {
    if (paramNumItem == null) {
      return null;
    }
    return new NumAllInfoItem(paramString, paramNumItem);
  }
  
  public static void init(Context paramContext)
  {
    init(paramContext, true);
  }
  
  public static void init(Context paramContext, asm paramasm)
  {
    init(paramContext, true, paramasm);
  }
  
  public static void init(Context paramContext, boolean paramBoolean)
  {
    init(paramContext, paramBoolean, null);
  }
  
  public static void init(Context paramContext, boolean paramBoolean, asm paramasm)
  {
    if (sEngine == null)
    {
      paramContext = paramContext.getApplicationContext();
      ComManager.a(paramContext);
      NumManager.a(paramContext);
      if (paramBoolean) {
        initDbOperator(paramContext);
      }
      sEngine = new YellowPageEngine();
      asl.a(paramContext);
      if (paramasm != null) {
        paramasm.a();
      }
    }
  }
  
  private static void initDbOperator(Context paramContext)
  {
    aoh localaoh = aoh.a(paramContext);
    localaoh.a(new ass(paramContext, "num"));
    localaoh.b(new ass(paramContext, "marker"));
    localaoh.c(new ass(paramContext, "m_mark"));
    localaoh.d(new ass(paramContext, "corrector"));
  }
  
  /* Error */
  private void initHanlder()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 120	com/ted/sdk/yellow/YellowPageEngine:mHandler	Landroid/os/Handler;
    //   6: ifnull +25 -> 31
    //   9: aload_0
    //   10: getfield 106	com/ted/sdk/yellow/YellowPageEngine:mHandlerThread	Landroid/os/HandlerThread;
    //   13: ifnull +18 -> 31
    //   16: aload_0
    //   17: getfield 106	com/ted/sdk/yellow/YellowPageEngine:mHandlerThread	Landroid/os/HandlerThread;
    //   20: invokevirtual 254	android/os/HandlerThread:isAlive	()Z
    //   23: istore_1
    //   24: iload_1
    //   25: ifeq +6 -> 31
    //   28: aload_0
    //   29: monitorexit
    //   30: return
    //   31: aload_0
    //   32: new 111	android/os/HandlerThread
    //   35: dup
    //   36: ldc 26
    //   38: invokespecial 257	android/os/HandlerThread:<init>	(Ljava/lang/String;)V
    //   41: putfield 106	com/ted/sdk/yellow/YellowPageEngine:mHandlerThread	Landroid/os/HandlerThread;
    //   44: aload_0
    //   45: getfield 106	com/ted/sdk/yellow/YellowPageEngine:mHandlerThread	Landroid/os/HandlerThread;
    //   48: invokevirtual 260	android/os/HandlerThread:start	()V
    //   51: aload_0
    //   52: new 262	asn
    //   55: dup
    //   56: aload_0
    //   57: aload_0
    //   58: getfield 106	com/ted/sdk/yellow/YellowPageEngine:mHandlerThread	Landroid/os/HandlerThread;
    //   61: invokevirtual 266	android/os/HandlerThread:getLooper	()Landroid/os/Looper;
    //   64: invokespecial 269	asn:<init>	(Lcom/ted/sdk/yellow/YellowPageEngine;Landroid/os/Looper;)V
    //   67: putfield 120	com/ted/sdk/yellow/YellowPageEngine:mHandler	Landroid/os/Handler;
    //   70: goto -42 -> 28
    //   73: astore_2
    //   74: aload_0
    //   75: monitorexit
    //   76: aload_2
    //   77: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	78	0	this	YellowPageEngine
    //   23	2	1	bool	boolean
    //   73	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	24	73	finally
    //   31	70	73	finally
  }
  
  /* Error */
  private boolean isAvailableHandlerQueue()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 120	com/ted/sdk/yellow/YellowPageEngine:mHandler	Landroid/os/Handler;
    //   6: ifnull +18 -> 24
    //   9: aload_0
    //   10: getfield 106	com/ted/sdk/yellow/YellowPageEngine:mHandlerThread	Landroid/os/HandlerThread;
    //   13: astore_2
    //   14: aload_2
    //   15: ifnull +9 -> 24
    //   18: iconst_1
    //   19: istore_1
    //   20: aload_0
    //   21: monitorexit
    //   22: iload_1
    //   23: ireturn
    //   24: iconst_0
    //   25: istore_1
    //   26: goto -6 -> 20
    //   29: astore_2
    //   30: aload_0
    //   31: monitorexit
    //   32: aload_2
    //   33: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	34	0	this	YellowPageEngine
    //   19	7	1	bool	boolean
    //   13	2	2	localHandlerThread	HandlerThread
    //   29	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	14	29	finally
  }
  
  private NumItem syncGetItemFromDb(RequestData paramRequestData)
  {
    asr localasr = asr.a();
    String str = paramRequestData.getNumber();
    Object localObject = (NumItem)localasr.get(str);
    if (localObject != null) {}
    NumItem localNumItem;
    do
    {
      return (NumItem)localObject;
      new YellowPageData(paramRequestData.getDataType(), paramRequestData.getOperationType(), paramRequestData.getDuration(), str, -1).a(getMessageSign(paramRequestData.getMsgBody()));
      localNumItem = NumManager.getInstnace().a(anu.b(str));
      localObject = localNumItem;
    } while (localNumItem == null);
    if (paramRequestData.isAccessNetwork())
    {
      localasr.a(str, localNumItem);
      return localNumItem;
    }
    localasr.b(str, localNumItem);
    return localNumItem;
  }
  
  public void asyncGetItem(RequestData paramRequestData, ISingleCallback paramISingleCallback)
  {
    if (TextUtils.isEmpty(paramRequestData.getNumber()))
    {
      Log.e(TAG, "Input number in empty.");
      return;
    }
    if (!isAvailableHandlerQueue())
    {
      Log.e(TAG, "Handler is collected");
      return;
    }
    mHandlerThread.setUncaughtExceptionHandler(new a(paramISingleCallback));
    Message localMessage = mHandler.obtainMessage(20001);
    obj = new d(paramRequestData, paramISingleCallback);
    mHandler.sendMessage(localMessage);
  }
  
  public void asyncGetItemList(List<RequestData> paramList, IListCallback paramIListCallback)
  {
    if (paramIListCallback == null)
    {
      Log.e(TAG, "No callback.");
      return;
    }
    if ((paramList == null) || (paramList.size() <= 0))
    {
      Log.e(TAG, "Input requestData are emtpy.");
      return;
    }
    if (!isAvailableHandlerQueue())
    {
      Log.e(TAG, "Handler is collected");
      return;
    }
    mHandlerThread.setUncaughtExceptionHandler(new a(paramIListCallback));
    Message localMessage = mHandler.obtainMessage(20003);
    obj = new c(paramList, paramIListCallback);
    mHandler.sendMessage(localMessage);
  }
  
  public void clear()
  {
    try
    {
      if (mHandler != null)
      {
        mHandler.removeMessages(20001);
        mHandler.removeMessages(20003);
        mHandler.removeMessages(20002);
        mHandler = null;
      }
      if (mHandlerThread != null)
      {
        mHandlerThread.quit();
        mHandlerThread = null;
      }
      asr.a().b();
      asl.b();
      return;
    }
    finally {}
  }
  
  public void correctName(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    paramString1 = anu.b(paramString1);
    NumManager.getInstnace().a(paramString1, paramString2, paramString3, paramString4, paramString5);
    asr.a().a(paramString1);
  }
  
  public void deleteCustomMarkClassifies(String paramString)
  {
    NumManager.getInstnace().b(paramString);
  }
  
  public void deleteMark(String paramString, int paramInt1, int paramInt2)
  {
    paramString = anu.b(paramString);
    NumManager.getInstnace().a(paramString, "无标记", paramInt1, paramInt2);
    asr.a().a(paramString);
  }
  
  public List<String> getAllManualClassifies()
  {
    return NumManager.getInstnace().g();
  }
  
  public List<String> getAllMarkClassifies()
  {
    return NumManager.getInstnace().e();
  }
  
  public NumAllInfoItem getNumInfoFromDb(RequestData paramRequestData, ISingleCallback paramISingleCallback)
  {
    String str = paramRequestData.getNumber();
    if (TextUtils.isEmpty(str)) {
      Log.e(TAG, "Input number in empty.");
    }
    NumItem localNumItem;
    do
    {
      return null;
      localNumItem = syncGetItemFromDb(paramRequestData);
      if ((localNumItem != null) && (localNumItem.g()) && (!localNumItem.h())) {
        break;
      }
    } while (!paramRequestData.isAccessNetwork());
    asyncGetItemFromNet(paramRequestData, paramISingleCallback);
    return null;
    return new NumAllInfoItem(str, localNumItem);
  }
  
  public NumAllInfoItem getNumInfoFromMemCache(RequestData paramRequestData, ISingleCallback paramISingleCallback)
  {
    String str = paramRequestData.getNumber();
    if (TextUtils.isEmpty(str))
    {
      Log.e(TAG, "Input number in empty.");
      return null;
    }
    asr localasr = asr.a();
    NumItem localNumItem = (NumItem)localasr.get(str);
    if (localNumItem == null)
    {
      if (!localasr.a(paramRequestData.isAccessNetwork(), str)) {
        asyncGetItem(paramRequestData, paramISingleCallback);
      }
      return null;
    }
    return new NumAllInfoItem(str, localNumItem);
  }
  
  public List<String> getSystemMarkClassifies()
  {
    return NumManager.getInstnace().f();
  }
  
  public void loadBuiltinData(Context paramContext)
  {
    AssetsDatabaseHelper.getInstance(paramContext).loadLocalData();
  }
  
  public void markNumber(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    paramString1 = anu.b(paramString1);
    NumManager.getInstnace().a(paramString1, paramString2, paramInt1, paramInt2);
    asr.a().a(paramString1);
  }
  
  public void retryFailedCorrectList()
  {
    NumManager.getInstnace().d();
  }
  
  public void retryFailedMarkList()
  {
    NumManager.getInstnace().c();
  }
  
  static class a
    implements Thread.UncaughtExceptionHandler
  {
    private final IExceptionCallback a;
    
    public a(IExceptionCallback paramIExceptionCallback)
    {
      a = paramIExceptionCallback;
    }
    
    public void uncaughtException(Thread paramThread, Throwable paramThrowable)
    {
      Log.e(YellowPageEngine.access$0(), "caught: " + paramThrowable);
      paramThrowable.printStackTrace();
      if (a != null) {
        a.onException(paramThrowable);
      }
    }
  }
  
  public static abstract class b
  {
    public abstract void a();
  }
  
  public class c
    extends YellowPageEngine.b
  {
    private final List<RequestData> b;
    private final IListCallback c;
    private final int d;
    
    c(IListCallback paramIListCallback)
    {
      super();
      b = paramIListCallback;
      IListCallback localIListCallback;
      c = localIListCallback;
      if (b == null) {}
      for (int i = 0;; i = b.size())
      {
        d = i;
        return;
      }
    }
    
    private List<NumItem> a(aod paramaod)
    {
      ArrayList localArrayList = new ArrayList();
      Object localObject1 = new ArrayList(localArrayList.size());
      Object localObject2 = b.iterator();
      for (;;)
      {
        if (!((Iterator)localObject2).hasNext())
        {
          localObject2 = NumManager.getInstnace();
          localObject3 = new ArrayList();
          localObject1 = ((NumManager)localObject2).a(localArrayList, (List)localObject1, (List)localObject3);
          ((NumManager)localObject2).a(localArrayList, 0.0F, 0.0F, paramaod, (List)localObject3);
          return (List<NumItem>)localObject1;
        }
        Object localObject3 = (RequestData)((Iterator)localObject2).next();
        YellowPageData localYellowPageData = new YellowPageData(((RequestData)localObject3).getDataType(), ((RequestData)localObject3).getOperationType(), ((RequestData)localObject3).getDuration(), ((RequestData)localObject3).getNumber(), -1);
        localYellowPageData.a(YellowPageEngine.this.getMessageSign(((RequestData)localObject3).getMsgBody()));
        localArrayList.add(localYellowPageData);
        ((List)localObject1).add(anu.b(((RequestData)localObject3).getNumber()));
      }
    }
    
    private void a(HashMap<String, NumAllInfoItem> paramHashMap, asr paramasr)
    {
      paramasr = paramasr.a(b);
      paramHashMap.keySet().removeAll(paramasr.keySet());
      paramHashMap.putAll(paramasr);
    }
    
    private boolean a(HashMap<String, NumAllInfoItem> paramHashMap)
    {
      if ((paramHashMap != null) && (paramHashMap.size() == d))
      {
        if (c != null) {
          c.onSuccess(paramHashMap);
        }
        return true;
      }
      return false;
    }
    
    public void a()
    {
      asr localasr = asr.a();
      HashMap localHashMap = localasr.a(b);
      if (a(localHashMap)) {
        return;
      }
      Iterator localIterator = a(new aso(this, localasr, localHashMap)).iterator();
      for (;;)
      {
        if (!localIterator.hasNext())
        {
          a(localHashMap, localasr);
          a(localHashMap);
          return;
        }
        NumItem localNumItem = (NumItem)localIterator.next();
        localasr.b(localNumItem.a(), localNumItem);
      }
    }
  }
  
  public class d
    extends YellowPageEngine.b
  {
    private final RequestData b;
    private final ISingleCallback c;
    
    d(RequestData paramRequestData, ISingleCallback paramISingleCallback)
    {
      super();
      b = paramRequestData;
      c = paramISingleCallback;
    }
    
    private NumItem a(aod paramaod)
    {
      Object localObject = b.getNumber();
      YellowPageData localYellowPageData = new YellowPageData(b.getDataType(), b.getOperationType(), b.getDuration(), (String)localObject, -1);
      localYellowPageData.a(YellowPageEngine.this.getMessageSign(b.getMsgBody()));
      NumManager localNumManager = NumManager.getInstnace();
      localObject = localNumManager.a(anu.b((String)localObject));
      if (((localObject == null) || (!((NumItem)localObject).g()) || (((NumItem)localObject).h())) && (b.isAccessNetwork())) {
        localNumManager.a((NumItem)localObject, 0.0F, 0.0F, paramaod, localYellowPageData);
      }
      return (NumItem)localObject;
    }
    
    private boolean a(NumItem paramNumItem)
    {
      String str = b.getNumber();
      if (paramNumItem != null)
      {
        paramNumItem = YellowPageEngine.this.getTargetItem(str, paramNumItem);
        if (paramNumItem != null) {
          if (c != null) {
            c.onSuccess(paramNumItem);
          }
        }
        while (asr.a().a(b.isAccessNetwork(), str)) {
          return true;
        }
      }
      return false;
    }
    
    public void a()
    {
      asr localasr = asr.a();
      String str = b.getNumber();
      if (a((NumItem)localasr.get(str))) {
        return;
      }
      NumItem localNumItem = a(new asp(this, localasr, str));
      if (b.isAccessNetwork()) {
        localasr.a(str, localNumItem);
      }
      for (;;)
      {
        a(localNumItem);
        if ((localNumItem != null) || (b.isAccessNetwork()) || (c == null)) {
          break;
        }
        c.onFail();
        return;
        localasr.b(str, localNumItem);
      }
    }
  }
  
  public class e
    extends YellowPageEngine.b
  {
    private final RequestData b;
    private final ISingleCallback c;
    
    e(RequestData paramRequestData, ISingleCallback paramISingleCallback)
    {
      super();
      b = paramRequestData;
      c = paramISingleCallback;
    }
    
    private NumItem a(aod paramaod)
    {
      Object localObject = b.getNumber();
      YellowPageData localYellowPageData = new YellowPageData(b.getDataType(), b.getOperationType(), b.getDuration(), (String)localObject, -1);
      localYellowPageData.a(YellowPageEngine.this.getMessageSign(b.getMsgBody()));
      NumManager localNumManager = NumManager.getInstnace();
      localObject = localNumManager.a(anu.b((String)localObject));
      localNumManager.a((NumItem)localObject, 0.0F, 0.0F, paramaod, localYellowPageData);
      return (NumItem)localObject;
    }
    
    private boolean a(NumItem paramNumItem)
    {
      String str = b.getNumber();
      Log.i(YellowPageEngine.access$0(), "invokeCallbackIfNeeded onSuccess: " + str);
      if (paramNumItem != null)
      {
        paramNumItem = YellowPageEngine.this.getTargetItem(str, paramNumItem);
        if (paramNumItem != null) {
          if (c != null)
          {
            c.onSuccess(paramNumItem);
            Log.i(YellowPageEngine.access$0(), "mClientCallback.onSuccess: " + str);
          }
        }
        while (asr.a().a(b.isAccessNetwork(), str)) {
          return true;
        }
      }
      return false;
    }
    
    public void a()
    {
      a(new asq(this, b.getNumber()));
    }
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.YellowPageEngine
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */