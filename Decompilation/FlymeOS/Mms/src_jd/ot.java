import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.telephony.MzTelephony.SmsExt;
import android.util.Log;
import com.android.mms.transaction.SmsReceiver;
import com.android.mms.ui.SimSelectionPreferenceActivity;
import com.google.android.mms.MmsException;

public class ot
  extends ov
  implements ny
{
  private static final String[] m = { "reply_path_present", "service_center" };
  protected final Context a;
  protected final int b;
  protected final String c;
  protected final String d;
  protected final long e;
  protected long f;
  protected long g;
  protected int h;
  protected int i = 0;
  private final String[] l;
  
  public ot(Context paramContext, String[] paramArrayOfString, String paramString, long paramLong)
  {
    this(paramContext, paramArrayOfString, paramString, paramLong, -1);
  }
  
  public ot(Context paramContext, String[] paramArrayOfString, String paramString, long paramLong, int paramInt)
  {
    a = paramContext;
    c = paramString;
    if (paramArrayOfString != null)
    {
      b = paramArrayOfString.length;
      l = new String[b];
      System.arraycopy(paramArrayOfString, 0, l, 0, b);
    }
    for (;;)
    {
      g = System.currentTimeMillis();
      e = paramLong;
      d = d(e);
      h = paramInt;
      return;
      b = 0;
      l = null;
    }
  }
  
  public ot(Context paramContext, String[] paramArrayOfString, String paramString, long paramLong, int paramInt1, int paramInt2)
  {
    a = paramContext;
    c = paramString;
    if (paramArrayOfString != null)
    {
      b = paramArrayOfString.length;
      l = new String[b];
      System.arraycopy(paramArrayOfString, 0, l, 0, b);
    }
    for (;;)
    {
      g = System.currentTimeMillis();
      e = paramLong;
      i = paramInt2;
      f = aac.b(i);
      d = d(e);
      h = paramInt1;
      return;
      b = 0;
      l = null;
    }
  }
  
  private boolean c(long paramLong)
  {
    if ((c == null) || (b == 0)) {
      throw new MmsException("Null message body or dest.");
    }
    Object localObject1 = new StringBuilder();
    boolean bool = SimSelectionPreferenceActivity.a(i);
    int j = 0;
    if (j < b)
    {
      if (j == 0) {
        ((StringBuilder)localObject1).append(l[j]);
      }
      for (;;)
      {
        j += 1;
        break;
        ((StringBuilder)localObject1).append(";").append(l[j]);
      }
    }
    try
    {
      Object localObject3 = aac.d(a, i);
      localObject1 = MzTelephony.SmsExt.addMessageToUri(a.getContentResolver(), Uri.parse("content://sms/groupsend"), ((StringBuilder)localObject1).toString(), c, null, Long.valueOf(g), Long.valueOf(g), true, bool, e, h, (String)localObject3, i);
      if (this.j)
      {
        localObject3 = a;
        if (l.length > 1)
        {
          bool = true;
          a((Context)localObject3, (Uri)localObject1, bool);
          return false;
        }
      }
    }
    catch (SQLiteException localSQLiteException)
    {
      for (;;)
      {
        aau.a(a, localSQLiteException);
        Object localObject2 = null;
        continue;
        bool = false;
        continue;
        a.sendBroadcast(new Intent("com.android.mms.transaction.SEND_MESSAGE", null, a, SmsReceiver.class));
      }
    }
  }
  
  private String d(long paramLong)
  {
    return wd.b();
  }
  
  public void a(int paramInt)
  {
    i = paramInt;
  }
  
  public boolean a(long paramLong)
  {
    Log.d("SmsMessageSender", "sendMessage -> mSlotId:" + i);
    return c(paramLong);
  }
}

/* Location:
 * Qualified Name:     ot
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */