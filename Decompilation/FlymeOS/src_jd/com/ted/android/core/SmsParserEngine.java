package com.ted.android.core;

import android.content.Context;
import apx;
import apy;
import aqa;
import aqb;
import aqc;
import aqe;
import aqf;
import aqg;
import aqi;
import aqj;
import aqk;
import aql;
import aqo;
import asf;
import ash;
import com.ted.android.contacts.bubble.SmsCoreEngine;
import com.ted.android.contacts.common.ComManager;
import com.ted.android.data.BubbleEntity;
import com.ted.android.data.SmsEntity;
import com.ted.android.data.bubbleAction.ActionBase;
import com.ted.android.smscard.CardBase;
import com.ted.android.utils.TedSDKLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SmsParserEngine
{
  private static final String b = SmsParserEngine.class.getSimpleName();
  private static SmsParserEngine c = null;
  ash a;
  private Context d;
  private OnInitialiseListener e;
  private boolean f = false;
  private int g = 65535;
  private List<aqc> h = Collections.synchronizedList(new ArrayList());
  private List<aqc> i = new ArrayList();
  
  private SmsParserEngine(Context paramContext)
  {
    d = paramContext;
    ComManager.a(d);
    setParseTypes(65535);
    b();
  }
  
  private CardBase a(BubbleEntity paramBubbleEntity, ActionBase paramActionBase, String paramString)
  {
    TedSDKLog.d(b, "getCardBaseFromCourier Start");
    CardBase localCardBase = new CardBase();
    localCardBase.setMatchedId(-2);
    HashMap localHashMap = new HashMap();
    localHashMap.put("快递通知", "");
    localCardBase.setTitle(localHashMap);
    mCardType = 28;
    localHashMap = new HashMap();
    localHashMap.put("快递单号", paramBubbleEntity.getMatchedWords());
    localCardBase.setSubTitle(localHashMap);
    paramBubbleEntity = new HashMap();
    paramBubbleEntity.put("短信内容", paramString);
    localCardBase.addData(paramBubbleEntity);
    paramBubbleEntity = new ArrayList();
    paramBubbleEntity.add(paramActionBase);
    localCardBase.setActions(paramBubbleEntity);
    return localCardBase;
  }
  
  private CardBase a(SmsEntity paramSmsEntity, String paramString)
  {
    Object localObject = paramSmsEntity.getAllEntities();
    paramSmsEntity = paramSmsEntity.getAllActions();
    if ((localObject != null) && (((List)localObject).size() > 0) && (paramSmsEntity != null) && (paramSmsEntity.size() > 0))
    {
      paramSmsEntity = (ActionBase)paramSmsEntity.get(0);
      localObject = (BubbleEntity)((List)localObject).get(0);
      if (action == 12) {
        return a((BubbleEntity)localObject, paramSmsEntity, paramString);
      }
      if (action == 16) {
        return b((BubbleEntity)localObject, paramSmsEntity, paramString);
      }
    }
    else
    {
      return null;
    }
    return null;
  }
  
  private List<BubbleEntity> a(String paramString1, String paramString2)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = h.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return localArrayList;
      }
      List localList = ((aqc)localIterator.next()).a(paramString1, paramString2);
      if ((localList != null) && (localList.size() > 0)) {
        localArrayList.addAll(localList);
      }
    }
  }
  
  private void a()
  {
    h.clear();
    if ((g & 0x1) == 1) {
      h.add(aql.a());
    }
    if ((g & 0x2) == 2) {
      h.add(apy.a());
    }
    if ((g & 0x4) == 4) {
      h.add(aqg.a(d));
    }
    if ((g & 0x8) == 8) {
      h.add(aqb.a());
    }
    if ((g & 0x10) == 16) {
      h.add(aqo.a());
    }
    if ((g & 0x20) == 32) {
      h.add(aqe.a());
    }
    if ((g & 0x40) == 64) {
      h.add(aqf.a());
    }
    if ((g & 0x100) == 256) {
      h.add(aqa.a());
    }
    if ((g & 0x80) == 128) {
      h.add(aqk.a());
    }
  }
  
  private CardBase b(BubbleEntity paramBubbleEntity, ActionBase paramActionBase, String paramString)
  {
    TedSDKLog.d(b, "getCardBaseFromVerification Start");
    CardBase localCardBase = new CardBase();
    localCardBase.setMatchedId(-1);
    HashMap localHashMap = new HashMap();
    localHashMap.put("验证消息", "");
    localCardBase.setTitle(localHashMap);
    mCardType = 33;
    localHashMap = new HashMap();
    localHashMap.put(buttonText.replace("复制", ""), paramBubbleEntity.getMatchedWords());
    localCardBase.setSubTitle(localHashMap);
    paramBubbleEntity = new HashMap();
    paramBubbleEntity.put("短信内容", paramString);
    localCardBase.addData(paramBubbleEntity);
    paramBubbleEntity = new ArrayList();
    paramBubbleEntity.add(paramActionBase);
    localCardBase.setActions(paramBubbleEntity);
    return localCardBase;
  }
  
  private List<BubbleEntity> b(String paramString1, String paramString2)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = i.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return localArrayList;
      }
      List localList = ((aqc)localIterator.next()).a(paramString1, paramString2);
      if ((localArrayList != null) && (localArrayList.size() > 0)) {
        localArrayList.addAll(localList);
      }
    }
  }
  
  private void b()
  {
    i.add(aqf.a());
    i.add(aqa.a());
    i.add(aqk.a());
  }
  
  private void c()
  {
    a = new ash();
    new Thread(new aqi(this)).start();
  }
  
  public static SmsParserEngine getInstance(Context paramContext)
  {
    if (c == null) {}
    try
    {
      if (c == null) {
        c = new SmsParserEngine(paramContext);
      }
      return c;
    }
    finally {}
  }
  
  public void initSync()
  {
    if (!f)
    {
      SmsCoreEngine.init(d);
      f = true;
      asf.a().a(d);
    }
  }
  
  public void initialise(Context paramContext, OnInitialiseListener paramOnInitialiseListener)
  {
    e = paramOnInitialiseListener;
    getInstance(paramContext);
    c();
  }
  
  public SmsEntity parseMessage(long paramLong1, String paramString1, String paramString2, long paramLong2)
  {
    TedSDKLog.d(b, "parseMessage start");
    SmsEntity localSmsEntity = new SmsEntity();
    localSmsEntity.setMsgId(paramLong1);
    localSmsEntity.setBody(paramString1);
    localSmsEntity.setNumber(paramString2);
    localSmsEntity.setDate(paramLong2);
    aqo.a().a(paramLong2);
    paramString1 = a(paramString1, paramString2);
    if (paramString1 != null)
    {
      localSmsEntity.addBubbleEntities(paramString1);
      localSmsEntity.updateBubbleParent();
    }
    return localSmsEntity;
  }
  
  public SmsEntity parseMessageWithTimeOut(long paramLong1, String paramString1, String paramString2, long paramLong2, int paramInt)
  {
    TedSDKLog.d("SmsParserEngine", "parseMessageWithTimeOut....");
    SmsEntity localSmsEntity = new SmsEntity();
    localSmsEntity.setMsgId(paramLong1);
    localSmsEntity.setBody(paramString1);
    localSmsEntity.setNumber(paramString2);
    Object localObject1 = new aqj(this, paramString1, paramString2);
    Object localObject5 = Executors.newSingleThreadExecutor();
    localObject1 = ((ExecutorService)localObject5).submit((Callable)localObject1);
    paramLong1 = paramInt;
    try
    {
      localObject1 = (List)((Future)localObject1).get(paramLong1, TimeUnit.MILLISECONDS);
      if (!((ExecutorService)localObject5).isShutdown()) {
        ((ExecutorService)localObject5).shutdownNow();
      }
      localObject5 = localObject1;
      if (localObject1 == null) {
        localObject5 = new ArrayList();
      }
      localSmsEntity.addBubbleEntities((List)localObject5);
      localSmsEntity.updateBubbleParent();
      localObject1 = apx.a(d).a(paramString1, paramString2);
      paramString2 = (String)localObject1;
      if (localObject1 == null) {
        paramString2 = a(localSmsEntity, paramString1);
      }
      localSmsEntity.setCardBase(paramString2);
      return localSmsEntity;
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;)
      {
        localInterruptedException.printStackTrace();
        Object localObject2 = null;
      }
    }
    catch (ExecutionException localExecutionException)
    {
      for (;;)
      {
        localExecutionException.printStackTrace();
        Object localObject3 = null;
      }
    }
    catch (TimeoutException localTimeoutException)
    {
      for (;;)
      {
        TedSDKLog.e("SmsParserEngine", "PARSE TIME OUT !!!");
        localTimeoutException.printStackTrace();
        Object localObject4 = null;
      }
    }
  }
  
  public SmsEntity parseOutboxMsgWithBaseRule(long paramLong1, String paramString1, String paramString2, long paramLong2)
  {
    TedSDKLog.d(b, "parseOutboxMsgWithBaseRule start");
    SmsEntity localSmsEntity = new SmsEntity();
    localSmsEntity.setMsgId(paramLong1);
    localSmsEntity.setBody(paramString1);
    localSmsEntity.setNumber(paramString2);
    paramString1 = b(paramString1, paramString2);
    if (paramString1 != null) {
      localSmsEntity.addBubbleEntities(paramString1);
    }
    return localSmsEntity;
  }
  
  public void setParseTypes(int paramInt)
  {
    g = paramInt;
    a();
  }
  
  public static abstract interface OnInitialiseListener
  {
    public abstract void onCompleted();
  }
}

/* Location:
 * Qualified Name:     com.ted.android.core.SmsParserEngine
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */