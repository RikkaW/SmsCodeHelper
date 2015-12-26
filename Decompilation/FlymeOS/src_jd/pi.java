import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Telephony.Sms;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import com.android.mms.transaction.SmsReceiver;
import com.google.android.mms.MmsException;

public class pi
  extends ph
{
  private final boolean i;
  private String l;
  private Uri m;
  private String n;
  
  public pi(Context paramContext, String paramString1, String paramString2, long paramLong, boolean paramBoolean, Uri paramUri, int paramInt, String paramString3)
  {
    super(paramContext, null, paramString2, paramLong, paramInt);
    i = paramBoolean;
    l = paramString1;
    m = paramUri;
    n = paramString3;
  }
  
  private void a(String paramString)
  {
    Log.d("Mms", "[SmsSingleRecipientSender] " + paramString);
  }
  
  public boolean a(long paramLong)
  {
    if (c == null) {
      throw new MmsException("Null message body or have multiple destinations.");
    }
    l = l.replaceAll(" ", "");
    if (wd.h(l)) {
      l = PhoneNumberUtils.stripSeparators(l);
    }
    Object localObject = Integer.TYPE;
    Class localClass = Integer.TYPE;
    Context localContext = a;
    Uri localUri = m;
    if (!((Boolean)aau.a(Telephony.Sms.class, "moveMessageToFolder", new Class[] { Context.class, Uri.class, localObject, localClass }, new Object[] { localContext, localUri, Integer.valueOf(4), Integer.valueOf(0) })).booleanValue()) {
      throw new MmsException("SipSmsSingleRecipientSender.sendMessage: couldn't move message to outbox: " + m);
    }
    localObject = new Intent("com.android.mms.transaction.SIP_MESSAGE_SENT", m, a, SmsReceiver.class);
    ((Intent)localObject).putExtra("SendNextMsg", true);
    ((Intent)localObject).putExtra("address", l);
    localObject = PendingIntent.getBroadcast(a, 0, (Intent)localObject, 0);
    try
    {
      aba.a().a(l, c, (PendingIntent)localObject, n, g, i);
      a("sendSipMessage: address=" + l + ", threadId=" + e + ", uri=" + m + ", msgs.count=" + 1);
      return false;
    }
    catch (Exception localException)
    {
      throw new MmsException("SipSmsSingleRecipientSender.sendSipTextMessage: caught " + localException + " from SipSmsManager.sendSipTextMessage()");
    }
  }
}

/* Location:
 * Qualified Name:     pi
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */