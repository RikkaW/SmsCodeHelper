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

public class po
  implements ny
{
  private static final Uri j = Uri.parse("content://sms/association_id");
  protected final Context a;
  protected final int b;
  protected final String c;
  protected final String d;
  protected final String e;
  protected final long f;
  protected long g;
  protected int h;
  private final String[] i;
  
  public po(Context paramContext, String[] paramArrayOfString, String paramString1, String paramString2, long paramLong, int paramInt)
  {
    a = paramContext;
    d = paramString1;
    c = paramString2;
    if (paramArrayOfString != null)
    {
      b = paramArrayOfString.length;
      i = new String[b];
      System.arraycopy(paramArrayOfString, 0, i, 0, b);
    }
    for (;;)
    {
      g = System.currentTimeMillis();
      f = paramLong;
      e = c(f);
      h = paramInt;
      return;
      b = 0;
      i = null;
    }
  }
  
  private boolean b(long paramLong)
  {
    if ((c == null) || (b == 0)) {
      throw new MmsException("Null message body or dest.");
    }
    String str1 = null;
    int k = 0;
    if (k < b)
    {
      if (str1 == null) {}
      for (str1 = i[k] + ";";; str1 = str1 + i[k] + ";")
      {
        k += 1;
        break;
      }
    }
    Uri.parse("content://sms/groupsend");
    try
    {
      String str2 = zv.c(0);
      MzTelephony.SmsExt.addMessageToUri(a.getContentResolver(), Uri.parse("content://sms/groupsend"), str1, c, d, Long.valueOf(g), Long.valueOf(g), true, false, f, h, 266, str2);
      a.sendBroadcast(new Intent("com.android.mms.transaction.SEND_SNS_MESSAGE", null, a, SmsReceiver.class));
      return false;
    }
    catch (SQLiteException localSQLiteException)
    {
      for (;;)
      {
        aau.a(a, localSQLiteException);
      }
    }
  }
  
  private String c(long paramLong)
  {
    String str = PreferenceManager.getDefaultSharedPreferences(a).getString("pref_key_mms_center_number", null);
    if (TextUtils.isEmpty(str)) {
      return null;
    }
    return str;
  }
  
  public void a(int paramInt) {}
  
  public boolean a(long paramLong)
  {
    return b(paramLong);
  }
}

/* Location:
 * Qualified Name:     po
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */