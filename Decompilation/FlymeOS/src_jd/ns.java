import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Pair;
import com.android.mms.transaction.MessagePopupService;

public class ns
  extends AsyncTask<Void, Void, Pair<String, Long>>
{
  public ns(MessagePopupService paramMessagePopupService, long paramLong) {}
  
  protected Pair<String, Long> a(Void... paramVarArgs)
  {
    return new Pair(MessagePopupService.b(b, a), Long.valueOf(a));
  }
  
  protected void a(Pair<String, Long> paramPair)
  {
    if ((!TextUtils.isEmpty((CharSequence)first)) && (MessagePopupService.a(b) != null)) {
      MessagePopupService.a(b).a(((Long)second).longValue(), (String)first);
    }
  }
}

/* Location:
 * Qualified Name:     ns
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */