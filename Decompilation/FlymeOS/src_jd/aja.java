import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Telephony.Sms.Inbox;
import android.provider.Telephony.Threads;
import android.telephony.SmsMessage;
import android.util.Log;
import com.android.internal.telephony.IccUtils;
import com.android.mms.transaction.MessagingNotification;
import com.meizu.android.mms.util.MzSqliteWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.xml.sax.SAXException;

public class aja
{
  private static ContentValues a(SmsMessage paramSmsMessage)
  {
    int i = 0;
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("address", paramSmsMessage.getDisplayOriginatingAddress());
    localContentValues.put("date", Long.valueOf(paramSmsMessage.getTimestampMillis()));
    localContentValues.put("protocol", Integer.valueOf(paramSmsMessage.getProtocolIdentifier()));
    localContentValues.put("read", Integer.valueOf(0));
    if (paramSmsMessage.getPseudoSubject().length() > 0) {
      localContentValues.put("subject", paramSmsMessage.getPseudoSubject());
    }
    if (paramSmsMessage.isReplyPathPresent()) {
      i = 1;
    }
    localContentValues.put("reply_path_present", Integer.valueOf(i));
    localContentValues.put("service_center", paramSmsMessage.getServiceCenterAddress());
    return localContentValues;
  }
  
  private static Uri a(Context paramContext, ContentValues paramContentValues, gm paramgm)
  {
    String str = paramContentValues.getAsString("address");
    if (paramgm != null) {}
    for (;;)
    {
      if (paramgm != null) {
        str = paramgm.d();
      }
      if (str != null) {
        paramContentValues.put("thread_id", (Long)aau.a(Telephony.Threads.class, "getOrCreateThreadId", new Class[] { Context.class, String.class }, new Object[] { paramContext, str }));
      }
      paramgm = MzSqliteWrapper.insert(paramContext, paramContext.getContentResolver(), Telephony.Sms.Inbox.CONTENT_URI, paramContentValues);
      long l = paramContentValues.getAsLong("thread_id").longValue();
      aat.a().a(paramContext, l);
      return paramgm;
      paramgm = gm.a(str, true);
    }
  }
  
  private static Uri a(Context paramContext, Intent paramIntent, int paramInt)
  {
    Object localObject = paramIntent.getByteArrayExtra("data");
    long l = zv.a(paramIntent);
    int i = aac.a(l);
    Log.d("PushProcesser", "intent is " + paramIntent + ", subId = " + l + ", slotId = " + i);
    ContentValues localContentValues = a(android.provider.Telephony.Sms.Intents.getMessagesFromIntent(paramIntent)[0]);
    localContentValues.put("sim_id", Integer.valueOf(i));
    localContentValues.put("imsi", aac.d(paramContext, i));
    localContentValues.put("sub_id", Long.valueOf(l));
    String str = localContentValues.getAsString("address");
    try
    {
      paramIntent = a((byte[])localObject, paramInt);
      if (paramIntent == null)
      {
        localObject = paramIntent;
        if (paramIntent.isEmpty()) {}
      }
      else
      {
        localObject = wd.k(paramIntent);
      }
      localContentValues.put("body", (String)localObject);
      paramIntent = abl.a(str, (String)localObject, localContentValues.getAsLong("date").longValue());
      if (b) {
        return abl.a(paramContext, localContentValues, paramIntent);
      }
    }
    catch (IOException paramIntent)
    {
      for (;;)
      {
        paramIntent = paramIntent.toString() + "\r\n" + IccUtils.bytesToHexString((byte[])localObject);
      }
    }
    catch (SAXException paramIntent)
    {
      for (;;)
      {
        paramIntent = paramIntent.toString() + "\r\n" + IccUtils.bytesToHexString((byte[])localObject);
      }
    }
    return a(paramContext, localContentValues, a);
  }
  
  public static String a(byte[] paramArrayOfByte, int paramInt)
  {
    Object localObject2 = new aiz();
    String str = "";
    Object localObject1 = "";
    int i = aaiyByteArrayInputStreamd;
    if (paramInt == 46)
    {
      localObject1 = new ajc().a(new ByteArrayInputStream(paramArrayOfByte, i, paramArrayOfByte.length));
      if (localObject1 == null)
      {
        str = "\r\nGeneral parse error: \r\n" + IccUtils.bytesToHexString(paramArrayOfByte);
        paramArrayOfByte = "";
        return paramArrayOfByte + str;
      }
      if (((ajb)localObject1).d() != null) {
        str = "\r\n" + IccUtils.bytesToHexString(paramArrayOfByte);
      }
      localObject2 = new StringBuilder();
      if (((ajb)localObject1).b() != null)
      {
        paramArrayOfByte = ((ajb)localObject1).b();
        label177:
        localObject2 = ((StringBuilder)localObject2).append(paramArrayOfByte).append("\r\n");
        if (((ajb)localObject1).c() == null) {
          break label224;
        }
      }
      label224:
      for (paramArrayOfByte = ((ajb)localObject1).c();; paramArrayOfByte = "")
      {
        paramArrayOfByte = paramArrayOfByte;
        break;
        paramArrayOfByte = "";
        break label177;
      }
    }
    if (paramInt == 48)
    {
      localObject2 = new aje().a(new ByteArrayInputStream(paramArrayOfByte, i, paramArrayOfByte.length));
      if (localObject2 == null)
      {
        str = "\r\nGeneral parse error: \r\n" + IccUtils.bytesToHexString(paramArrayOfByte);
        paramArrayOfByte = (byte[])localObject1;
      }
      for (;;)
      {
        break;
        if (((ajd)localObject2).b() != null) {
          str = "\r\n" + IccUtils.bytesToHexString(paramArrayOfByte);
        }
        if (((ajd)localObject2).a() != null) {
          paramArrayOfByte = ((ajd)localObject2).a();
        } else {
          paramArrayOfByte = "";
        }
      }
    }
    throw new SAXException("Unsupported Push Type");
  }
  
  public static void a(Context paramContext, Intent paramIntent)
  {
    if (paramIntent.getAction().equals("android.provider.Telephony.WAP_PUSH_DELIVER"))
    {
      if (!"application/vnd.wap.sic".equals(paramIntent.getType())) {
        break label52;
      }
      paramIntent = a(paramContext, paramIntent, 46);
      if (paramIntent != null) {
        MessagingNotification.a(paramContext, MessagingNotification.b(paramContext, paramIntent), false, paramIntent, false, true);
      }
    }
    label52:
    do
    {
      do
      {
        return;
      } while (!"application/vnd.wap.slc".equals(paramIntent.getType()));
      paramIntent = a(paramContext, paramIntent, 48);
    } while (paramIntent == null);
    MessagingNotification.a(paramContext, MessagingNotification.b(paramContext, paramIntent), false, paramIntent, false, true);
  }
}

/* Location:
 * Qualified Name:     aja
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */