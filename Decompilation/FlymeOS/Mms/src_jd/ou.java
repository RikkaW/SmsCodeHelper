import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.util.Log;
import com.android.mms.transaction.MessageStatusReceiver;
import com.android.mms.transaction.SmsReceiver;
import com.google.android.mms.MmsException;
import java.util.ArrayList;

public class ou
  extends ot
{
  private final boolean l;
  private String m;
  private Uri n;
  private String o;
  
  public ou(Context paramContext, String paramString1, String paramString2, long paramLong1, boolean paramBoolean, Uri paramUri, int paramInt, String paramString3, long paramLong2)
  {
    super(paramContext, null, paramString2, paramLong1, paramInt);
    l = paramBoolean;
    m = paramString1;
    n = paramUri;
    o = paramString3;
    f = paramLong2;
  }
  
  private void a(String paramString)
  {
    Log.d("Mms", "[SmsSingleRecipientSender] " + paramString);
  }
  
  public boolean a(long paramLong)
  {
    Log.v("SmsSingleRecipientSender", "sendMessage token: " + paramLong);
    if (c == null) {
      throw new MmsException("Null message body or have multiple destinations.");
    }
    SmsManager localSmsManager = aac.c(f);
    Object localObject1;
    if ((ga.j() != null) && ((aau.d(m)) || (wd.b(m))))
    {
      localObject1 = m + " " + c;
      m = ga.j();
      localObject1 = localSmsManager.divideMessage((String)localObject1);
    }
    int k;
    for (;;)
    {
      k = ((ArrayList)localObject1).size();
      if (k != 0) {
        break;
      }
      throw new MmsException("SmsMessageSender.sendMessage: divideMessage returned empty messages. Original message is \"" + c + "\"");
      localObject1 = localSmsManager.divideMessage(c);
      m = PhoneNumberUtils.stripSeparators(m);
      m = gr.a(a, e, m);
    }
    Object localObject2 = Integer.TYPE;
    Object localObject3 = Integer.TYPE;
    Object localObject4 = a;
    Uri localUri = n;
    if (!((Boolean)aau.a("android.provider.Telephony$Sms", "moveMessageToFolder", new Class[] { Context.class, Uri.class, localObject2, localObject3 }, new Object[] { localObject4, localUri, Integer.valueOf(4), Integer.valueOf(0) })).booleanValue()) {
      throw new MmsException("SmsMessageSender.sendMessage: couldn't move message to outbox: " + n);
    }
    Log.v("SmsSingleRecipientSender", "sendMessage mDest: " + m + " mRequestDeliveryReport: " + l + ", mSubId = " + f);
    for (;;)
    {
      try
      {
        localObject2 = new ArrayList(k);
        localObject3 = new ArrayList(k);
        int i = 0;
        if (i < k)
        {
          if ((l) && (i == k - 1))
          {
            localObject4 = new Intent("com.android.mms.transaction.MessageStatusReceiver.MESSAGE_STATUS_RECEIVED", n, a, MessageStatusReceiver.class).putExtra("subscription", f);
            ((ArrayList)localObject2).add(PendingIntent.getBroadcast(a, 0, (Intent)localObject4, 0));
            localObject4 = new Intent("com.android.mms.transaction.MESSAGE_SENT", n, a, SmsReceiver.class);
            ((Intent)localObject4).putExtra("subscription", f);
            if (i != k - 1) {
              break label732;
            }
            ((Intent)localObject4).putExtra("SendNextMsg", true);
            j = 1;
            Log.v("SmsSingleRecipientSender", "sendMessage sendIntent: " + localObject4);
            ((ArrayList)localObject3).add(PendingIntent.getBroadcast(a, j, (Intent)localObject4, 134217728));
            i += 1;
            continue;
          }
          ((ArrayList)localObject2).add(null);
          continue;
        }
        localSmsManager.sendMultipartTextMessage(m, d, localException, (ArrayList)localObject3, (ArrayList)localObject2);
      }
      catch (Exception localException)
      {
        Log.e("SmsSingleRecipientSender", "SmsMessageSender.sendMessage: caught", localException);
        throw new MmsException("SmsMessageSender.sendMessage: caught " + localException + " from SmsManager.sendTextMessage()");
      }
      a("sendMessage: address=" + m + ", threadId=" + e + ", uri=" + n + ", msgs.count=" + k + ", mSubId = " + f);
      return false;
      label732:
      int j = 0;
    }
  }
}

/* Location:
 * Qualified Name:     ou
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */