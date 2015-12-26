import android.content.ContentUris;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony.Mms;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.MmsApp;
import com.google.android.mms.MmsException;
import com.meizu.android.mms.pdu.MzEncodedStringValue;
import com.meizu.android.mms.pdu.MzMultimediaMessagePdu;
import com.meizu.android.mms.pdu.MzNotificationInd;
import com.meizu.android.mms.pdu.MzPduPersister;
import com.meizu.android.mms.pdu.MzRetrieveConf;
import com.meizu.android.mms.pdu.MzSendReq;
import java.util.ArrayList;
import java.util.List;

public class zd
{
  private static String a(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getResources();
    switch (paramInt)
    {
    case 129: 
    default: 
      return paramContext.getString(2131493072);
    case 130: 
      return paramContext.getString(2131493069);
    }
    return paramContext.getString(2131493071);
  }
  
  private static String a(Context paramContext, MzEncodedStringValue paramMzEncodedStringValue)
  {
    if (paramMzEncodedStringValue != null) {
      return paramMzEncodedStringValue.getString();
    }
    return "";
  }
  
  private static List<ze> a(Context paramContext, Cursor paramCursor)
  {
    ArrayList localArrayList = new ArrayList();
    Resources localResources = paramContext.getResources();
    long l = paramCursor.getLong(1);
    paramCursor = ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, l);
    for (;;)
    {
      MzNotificationInd localMzNotificationInd;
      try
      {
        localMzNotificationInd = (MzNotificationInd)MzPduPersister.getPduPersister(paramContext).load(paramCursor);
        localArrayList.add(new ze(localResources.getString(2131493014), localResources.getString(2131493020)));
        paramCursor = a(paramContext, localMzNotificationInd.getFrom());
        String str = localResources.getString(2131492966);
        if (!TextUtils.isEmpty(paramCursor))
        {
          localArrayList.add(new ze(str, paramCursor));
          localArrayList.add(new ze(localResources.getString(2131492961), wd.a(paramContext, localMzNotificationInd.getExpiry() * 1000L, true)));
          paramCursor = localMzNotificationInd.getSubject();
          str = localResources.getString(2131493143);
          if (paramCursor == null) {
            break label338;
          }
          paramCursor = paramCursor.getString();
          localArrayList.add(new ze(str, paramCursor));
          localArrayList.add(new ze(localResources.getString(2131493000), new String(localMzNotificationInd.getMessageClass())));
          if (!MmsApp.a) {
            break label344;
          }
          localArrayList.add(new ze(localResources.getString(2131493011), wd.a(localMzNotificationInd.getMessageSize(), paramContext)));
          return localArrayList;
        }
      }
      catch (MmsException localMmsException)
      {
        Log.e("SpcialMessageUtils", "Failed to load the message: " + paramCursor, localMmsException);
        localArrayList.add(new ze(paramContext.getResources().getString(2131492905), ""));
        return localArrayList;
      }
      paramCursor = localMmsException.getString(2131492969);
      continue;
      label338:
      paramCursor = "";
      continue;
      label344:
      localArrayList.add(new ze(localMmsException.getString(2131493011), String.valueOf((localMzNotificationInd.getMessageSize() + 1023L) / 1024L) + paramContext.getString(2131492974)));
    }
  }
  
  public static List<ze> a(Context paramContext, Cursor paramCursor, long paramLong)
  {
    if (paramCursor == null) {
      return null;
    }
    if ("mms".equals(paramCursor.getString(0)))
    {
      switch (paramCursor.getInt(18))
      {
      case 129: 
      case 131: 
      default: 
        Log.w("SpcialMessageUtils", "No details could be retrieved.");
        return null;
      case 130: 
        return a(paramContext, paramCursor);
      }
      return b(paramContext, paramCursor, paramLong);
    }
    return b(paramContext, paramCursor);
  }
  
  private static List<ze> b(Context paramContext, Cursor paramCursor)
  {
    Log.d("SpcialMessageUtils", "getTextMessageDetails");
    ArrayList localArrayList = new ArrayList();
    Resources localResources = paramContext.getResources();
    localArrayList.add(new ze(localResources.getString(2131493014), localResources.getString(2131493144)));
    ze localze = new ze();
    int i = paramCursor.getInt(8);
    long l;
    if (((Boolean)aau.a("android.provider.Telephony$Sms", "isOutgoingFolder", Integer.TYPE, Integer.valueOf(i))).booleanValue())
    {
      localze.a(localResources.getString(2131493145));
      localze.b(paramCursor.getString(3));
      localArrayList.add(localze);
      if (i == 1)
      {
        l = paramCursor.getLong(6);
        if (l > 0L) {
          localArrayList.add(new ze(localResources.getString(2131493109), wd.a(paramContext, l, true)));
        }
      }
      localze = new ze();
      if (i != 3) {
        break label333;
      }
      localze.a(localResources.getString(2131493090));
    }
    for (;;)
    {
      localze.b(wd.a(paramContext, paramCursor.getLong(5), true));
      localArrayList.add(localze);
      if (i == 2)
      {
        l = paramCursor.getLong(6);
        if (l > 0L) {
          localArrayList.add(new ze(localResources.getString(2131492934), wd.a(paramContext, l, true)));
        }
      }
      i = paramCursor.getInt(11);
      if (i != 0) {
        localArrayList.add(new ze(localResources.getString(2131492957), String.valueOf(i)));
      }
      return localArrayList;
      localze.a(localResources.getString(2131492966));
      break;
      label333:
      if (i == 1) {
        localze.a(localResources.getString(2131493074));
      } else {
        localze.a(localResources.getString(2131493109));
      }
    }
  }
  
  private static List<ze> b(Context paramContext, Cursor paramCursor, long paramLong)
  {
    if (paramCursor.getInt(18) == 130) {
      return a(paramContext, paramCursor);
    }
    ArrayList localArrayList = new ArrayList();
    Resources localResources = paramContext.getResources();
    long l = paramCursor.getLong(1);
    Object localObject1 = ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, l);
    for (;;)
    {
      int i;
      try
      {
        MzMultimediaMessagePdu localMzMultimediaMessagePdu = (MzMultimediaMessagePdu)MzPduPersister.getPduPersister(paramContext).load((Uri)localObject1);
        localArrayList.add(new ze(localResources.getString(2131493014), localResources.getString(2131493019)));
        Object localObject2;
        if ((localMzMultimediaMessagePdu instanceof MzRetrieveConf))
        {
          localObject1 = a(paramContext, ((MzRetrieveConf)localMzMultimediaMessagePdu).getFrom());
          localObject2 = localResources.getString(2131492966);
          if (!TextUtils.isEmpty((CharSequence)localObject1)) {
            localArrayList.add(new ze((String)localObject2, (String)localObject1));
          }
        }
        else
        {
          localObject2 = localMzMultimediaMessagePdu.getTo();
          String str = localResources.getString(2131493145);
          if (localObject2 == null) {
            break label545;
          }
          localObject1 = MzEncodedStringValue.concat((MzEncodedStringValue[])localObject2);
          localArrayList.add(new ze(str, (String)localObject1));
          if ((localMzMultimediaMessagePdu instanceof MzSendReq))
          {
            localObject1 = ((MzSendReq)localMzMultimediaMessagePdu).getBcc();
            if ((localObject1 != null) && (localObject1.length > 0)) {
              localArrayList.add(new ze(MzEncodedStringValue.concat((MzEncodedStringValue[])localObject2), MzEncodedStringValue.concat((MzEncodedStringValue[])localObject1)));
            }
          }
          localObject1 = new ze();
          i = paramCursor.getInt(19);
          if (i != 3) {
            break label552;
          }
          ((ze)localObject1).a(localResources.getString(2131493090));
          ((ze)localObject1).b(wd.a(paramContext, localMzMultimediaMessagePdu.getDate() * 1000L, true));
          localArrayList.add(localObject1);
          paramCursor = new ze();
          paramCursor.a(localResources.getString(2131493143));
          localObject1 = localMzMultimediaMessagePdu.getSubject();
          l = paramLong;
          if (localObject1 != null)
          {
            localObject1 = ((MzEncodedStringValue)localObject1).getString();
            l = paramLong + ((String)localObject1).length();
            paramCursor.b((String)localObject1);
          }
          localArrayList.add(paramCursor);
          localArrayList.add(new ze(localResources.getString(2131493070), a(paramContext, localMzMultimediaMessagePdu.getPriority())));
          if (!MmsApp.a) {
            break label588;
          }
          localArrayList.add(new ze(localResources.getString(2131493011) + ":", wd.a(l, paramContext)));
          return localArrayList;
        }
      }
      catch (MmsException paramCursor)
      {
        Log.e("SpcialMessageUtils", "Failed to load the message: " + localObject1, paramCursor);
        localArrayList.add(new ze(paramContext.getResources().getString(2131492905), ""));
        return localArrayList;
      }
      localObject1 = localResources.getString(2131492969);
      continue;
      label545:
      localObject1 = "";
      continue;
      label552:
      if (i == 1)
      {
        ((ze)localObject1).a(localResources.getString(2131493074));
      }
      else
      {
        ((ze)localObject1).a(localResources.getString(2131493109));
        continue;
        label588:
        localArrayList.add(new ze(localResources.getString(2131493011) + ":", String.valueOf((l - 1L) / 1000L + 1L) + " KB"));
      }
    }
  }
}

/* Location:
 * Qualified Name:     zd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */