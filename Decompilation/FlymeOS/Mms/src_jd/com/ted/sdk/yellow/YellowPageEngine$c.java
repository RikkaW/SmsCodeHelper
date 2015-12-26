package com.ted.sdk.yellow;

import anu;
import aod;
import aso;
import asr;
import com.ted.android.contacts.netparser.NumManager;
import com.ted.android.contacts.netparser.model.NumItem;
import com.ted.android.contacts.netparser.model.YellowPageData;
import com.ted.sdk.yellow.entry.NumAllInfoItem;
import com.ted.sdk.yellow.entry.RequestData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class YellowPageEngine$c
  extends YellowPageEngine.b
{
  private final List<RequestData> b;
  private final IListCallback c;
  private final int d;
  
  YellowPageEngine$c(List<RequestData> paramList, IListCallback paramIListCallback)
  {
    super(null);
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
      localYellowPageData.a(YellowPageEngine.access$2(a, ((RequestData)localObject3).getMsgBody()));
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

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.YellowPageEngine.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */