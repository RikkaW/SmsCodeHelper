import android.content.Context;
import android.os.AsyncTask;
import com.android.mms.transaction.MessagingNotification;

public final class oc
  extends AsyncTask<Void, Void, Integer>
{
  public oc(Context paramContext) {}
  
  protected Integer a(Void... paramVarArgs)
  {
    return Integer.valueOf(MessagingNotification.a(a, null));
  }
  
  protected void a(Integer paramInteger)
  {
    if (paramInteger.intValue() < 1)
    {
      MessagingNotification.a(a, 789);
      return;
    }
    MessagingNotification.d(a);
  }
}

/* Location:
 * Qualified Name:     oc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */