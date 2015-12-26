import android.util.Log;
import com.ted.android.contacts.netparser.model.NumItem;
import com.ted.sdk.yellow.ISingleCallback;
import com.ted.sdk.yellow.YellowPageEngine;
import com.ted.sdk.yellow.YellowPageEngine.d;
import com.ted.sdk.yellow.entry.RequestData;
import java.util.List;

public class asp
  implements aod
{
  public asp(YellowPageEngine.d paramd, asr paramasr, String paramString) {}
  
  public void a(aod.a parama, NumItem paramNumItem)
  {
    b.a(c, paramNumItem);
    YellowPageEngine.d.a(a, paramNumItem);
  }
  
  public void a(aod.a parama, List<NumItem> paramList) {}
  
  public void a(Throwable paramThrowable)
  {
    Log.e(YellowPageEngine.access$0(), "Get item from network failed. Number is " + YellowPageEngine.d.a(a).getNumber());
    if (YellowPageEngine.d.b(a) != null) {
      YellowPageEngine.d.b(a).onFail();
    }
  }
}

/* Location:
 * Qualified Name:     asp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */