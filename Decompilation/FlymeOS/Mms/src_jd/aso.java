import android.util.Log;
import com.ted.android.contacts.netparser.model.NumItem;
import com.ted.sdk.yellow.IListCallback;
import com.ted.sdk.yellow.YellowPageEngine;
import com.ted.sdk.yellow.YellowPageEngine.c;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class aso
  implements aod
{
  private boolean b;
  
  public aso(YellowPageEngine.c paramc, asr paramasr, HashMap paramHashMap) {}
  
  public void a(aod.a parama, NumItem paramNumItem) {}
  
  public void a(aod.a parama, List<NumItem> paramList)
  {
    if (b) {
      return;
    }
    parama = paramList.iterator();
    for (;;)
    {
      if (!parama.hasNext())
      {
        YellowPageEngine.c.a(a, d, c);
        YellowPageEngine.c.a(a, d);
        return;
      }
      paramList = (NumItem)parama.next();
      c.a(paramList.a(), paramList);
    }
  }
  
  public void a(Throwable paramThrowable)
  {
    Log.e(YellowPageEngine.access$0(), "Get list from network failed.");
    b = true;
    if (YellowPageEngine.c.a(a) == null) {
      return;
    }
    if (d.size() <= 0)
    {
      YellowPageEngine.c.a(a).onFail();
      return;
    }
    if (d.size() != YellowPageEngine.c.b(a))
    {
      YellowPageEngine.c.a(a).onPartSuccess(d);
      return;
    }
    YellowPageEngine.c.a(a).onSuccess(d);
  }
}

/* Location:
 * Qualified Name:     aso
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */