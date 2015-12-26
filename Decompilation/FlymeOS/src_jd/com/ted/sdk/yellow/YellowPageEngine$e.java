package com.ted.sdk.yellow;

import android.util.Log;
import anu;
import aod;
import asq;
import asr;
import com.ted.android.contacts.netparser.NumManager;
import com.ted.android.contacts.netparser.model.NumItem;
import com.ted.android.contacts.netparser.model.YellowPageData;
import com.ted.sdk.yellow.entry.RequestData;

public class YellowPageEngine$e
  extends YellowPageEngine.b
{
  private final RequestData b;
  private final ISingleCallback c;
  
  YellowPageEngine$e(YellowPageEngine paramYellowPageEngine, RequestData paramRequestData, ISingleCallback paramISingleCallback)
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
    localNumManager.a((NumItem)localObject, 0.0F, 0.0F, paramaod, localYellowPageData);
    return (NumItem)localObject;
  }
  
  private boolean a(NumItem paramNumItem)
  {
    String str = b.getNumber();
    Log.i(YellowPageEngine.access$0(), "invokeCallbackIfNeeded onSuccess: " + str);
    if (paramNumItem != null)
    {
      paramNumItem = YellowPageEngine.access$1(a, str, paramNumItem);
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

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.YellowPageEngine.e
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */