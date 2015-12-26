package com.ted.sdk.yellow;

import anu;
import aod;
import asp;
import asr;
import com.ted.android.contacts.netparser.NumManager;
import com.ted.android.contacts.netparser.model.NumItem;
import com.ted.android.contacts.netparser.model.YellowPageData;
import com.ted.sdk.yellow.entry.RequestData;

public class YellowPageEngine$d
  extends YellowPageEngine.b
{
  private final RequestData b;
  private final ISingleCallback c;
  
  YellowPageEngine$d(YellowPageEngine paramYellowPageEngine, RequestData paramRequestData, ISingleCallback paramISingleCallback)
  {
    super(null);
    b = paramRequestData;
    c = paramISingleCallback;
  }
  
  private NumItem a(aod paramaod)
  {
    Object localObject = b.getNumber();
    YellowPageData localYellowPageData = new YellowPageData(b.getDataType(), b.getOperationType(), b.getDuration(), (String)localObject, -1);
    localYellowPageData.a(YellowPageEngine.access$2(a, b.getMsgBody()));
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
      paramNumItem = YellowPageEngine.access$1(a, str, paramNumItem);
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

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.YellowPageEngine.d
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */