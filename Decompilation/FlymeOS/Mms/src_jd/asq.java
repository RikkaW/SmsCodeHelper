import android.util.Log;
import com.ted.android.contacts.netparser.model.NumItem;
import com.ted.sdk.yellow.ISingleCallback;
import com.ted.sdk.yellow.YellowPageEngine;
import com.ted.sdk.yellow.YellowPageEngine.e;
import java.util.List;

public class asq
  implements aod
{
  public asq(YellowPageEngine.e parame, String paramString) {}
  
  public void a(aod.a parama, NumItem paramNumItem)
  {
    Log.i(YellowPageEngine.access$0(), "Get item from network onSuccess: " + b);
    asr.a().a(b, paramNumItem);
    YellowPageEngine.e.a(a, paramNumItem);
  }
  
  public void a(aod.a parama, List<NumItem> paramList) {}
  
  public void a(Throwable paramThrowable)
  {
    Log.e(YellowPageEngine.access$0(), "Get item from network failed.");
    if (YellowPageEngine.e.a(a) != null) {
      YellowPageEngine.e.a(a).onFail();
    }
  }
}

/* Location:
 * Qualified Name:     asq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */