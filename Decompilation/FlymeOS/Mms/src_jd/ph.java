import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.telephony.MzTelephony.SmsExt;
import android.text.TextUtils;
import com.android.mms.transaction.SmsReceiver;
import com.google.android.mms.MmsException;

public class ph
  extends ov
  implements ny
{
  private static final Uri l = Uri.parse("content://sms/association_id");
  protected final Context a;
  protected final int b;
  protected final String c;
  protected final String d;
  protected final long e;
  protected long f;
  protected int g;
  protected int h = 0;
  private final String[] i;
  
  public ph(Context paramContext, String[] paramArrayOfString, String paramString, long paramLong, int paramInt)
  {
    a = paramContext;
    c = paramString;
    if (paramArrayOfString != null)
    {
      b = paramArrayOfString.length;
      i = new String[b];
      System.arraycopy(paramArrayOfString, 0, i, 0, b);
    }
    for (;;)
    {
      f = System.currentTimeMillis();
      e = paramLong;
      d = d(e);
      g = paramInt;
      return;
      b = 0;
      i = null;
    }
  }
  
  private boolean c(long paramLong)
  {
    if ((c == null) || (b == 0)) {
      throw new MmsException("Null message body or dest.");
    }
    Object localObject1 = null;
    int j = 0;
    if (j < b)
    {
      if (localObject1 == null) {}
      for (localObject1 = i[j] + ";";; localObject1 = (String)localObject1 + i[j] + ";")
      {
        j += 1;
        break;
      }
    }
    Uri.parse("content://sms/groupsend");
    try
    {
      localObject1 = MzTelephony.SmsExt.addMessageToUri(a.getContentResolver(), Uri.parse("content://sms/groupsend"), (String)localObject1, c, null, Long.valueOf(f), Long.valueOf(f), true, true, e, g, 256, "-10", -10);
      if (this.j)
      {
        Context localContext = a;
        if (i.length > 1)
        {
          bool = true;
          a(localContext, (Uri)localObject1, bool);
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
        boolean bool = false;
        continue;
        a.sendBroadcast(new Intent("com.android.mms.transaction.SEND_SIP_MESSAGE", null, a, SmsReceiver.class));
      }
    }
  }
  
  private String d(long paramLong)
  {
    String str = PreferenceManager.getDefaultSharedPreferences(a).getString("pref_key_mms_center_number", null);
    if (TextUtils.isEmpty(str)) {
      return null;
    }
    return str;
  }
  
  public void a(int paramInt)
  {
    h = paramInt;
  }
  
  public boolean a(long paramLong)
  {
    return c(paramLong);
  }
}

/* Location:
 * Qualified Name:     ph
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */