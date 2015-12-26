import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.provider.Telephony.Mms.Draft;
import android.provider.Telephony.Mms.Outbox;
import android.provider.Telephony.MmsSms.PendingMessages;
import android.util.Log;
import com.android.mms.transaction.TransactionService;
import com.android.mms.transaction.flyme.FlymeTransactionService;
import com.android.mms.transaction.sns.SnsTransactionService;
import com.android.mms.ui.SimSelectionPreferenceActivity;
import com.google.android.mms.MmsException;
import com.meizu.android.mms.pdu.MzGenericPdu;
import com.meizu.android.mms.pdu.MzPduPersister;
import com.meizu.android.mms.pdu.MzSendReq;
import com.meizu.android.mms.util.MzSqliteWrapper;

public class oh
  implements ny
{
  public Handler a;
  private final Context b;
  private final Uri c;
  private final long d;
  private final int e;
  private boolean f;
  private int g;
  
  public oh(Context paramContext, Uri paramUri, long paramLong, int paramInt)
  {
    this(paramContext, paramUri, paramLong, paramInt, false);
  }
  
  public oh(Context paramContext, Uri paramUri, long paramLong, int paramInt, boolean paramBoolean)
  {
    b = paramContext;
    c = paramUri;
    d = paramLong;
    e = paramInt;
    f = paramBoolean;
    if (c == null) {
      throw new IllegalArgumentException("Null message URI.");
    }
    if (b.getMainLooper() != null)
    {
      Looper.prepare();
      a = new Handler(b.getMainLooper());
    }
  }
  
  private void a(MzSendReq paramMzSendReq)
  {
    int j = 128;
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(b);
    paramMzSendReq.setExpiry(localSharedPreferences.getLong("pref_key_mms_expiry", 604800L));
    paramMzSendReq.setPriority(localSharedPreferences.getInt("pref_key_mms_priority", 129));
    if (e == 1) {}
    for (boolean bool = SimSelectionPreferenceActivity.a(g);; bool = true)
    {
      if (bool)
      {
        i = 128;
        paramMzSendReq.setDeliveryReport(i);
        bool = false;
        if (e == 1) {
          bool = SimSelectionPreferenceActivity.d(g);
        }
        if (!bool) {
          break label116;
        }
      }
      label116:
      for (int i = j;; i = 129)
      {
        paramMzSendReq.setReadReport(i);
        return;
        i = 129;
        break;
      }
    }
  }
  
  public void a(int paramInt)
  {
    g = paramInt;
  }
  
  public boolean a(long paramLong)
  {
    fl.a("sendMessage uri: " + c, new Object[0]);
    Object localObject4 = MzPduPersister.getPduPersister(b);
    Object localObject3 = ((MzPduPersister)localObject4).load(c);
    if (((MzGenericPdu)localObject3).getMessageType() != 128) {
      throw new MmsException("Invalid message: " + ((MzGenericPdu)localObject3).getMessageType());
    }
    Object localObject1 = (MzSendReq)localObject3;
    a((MzSendReq)localObject1);
    if (d > 0L) {}
    for (long l1 = d;; l1 = ((MzSendReq)localObject1).getMessageSize())
    {
      ((MzSendReq)localObject1).setMessageClass("personal".getBytes());
      ((MzSendReq)localObject1).setDate(System.currentTimeMillis() / 1000L);
      ((MzSendReq)localObject1).setMessageSize(d);
      ((MzPduPersister)localObject4).updateHeaders(c, (MzSendReq)localObject1);
      if ((e != 1) || (l1 <= ga.f())) {
        break;
      }
      a.post(new oi(this));
      throw new MmsException("SendPdu is big than normal message size, this pdu size is " + ((MzSendReq)localObject1).getMessageSize());
    }
    l1 = ContentUris.parseId(c);
    boolean bool3;
    ContentValues localContentValues;
    long l2;
    long l3;
    boolean bool2;
    boolean bool1;
    label335:
    int i;
    boolean bool4;
    if (5 == aac.c(b, g))
    {
      bool3 = true;
      localObject1 = new ContentValues(1);
      localContentValues = new ContentValues(2);
      l2 = ContentUris.parseId(c);
      l3 = aac.b(g);
      if (e != 1) {
        break label1067;
      }
      bool2 = wd.c(b, g);
      if ((!bool3) || (!bool2)) {
        break label1061;
      }
      bool1 = true;
      if (aac.b(l3)) {
        break label1321;
      }
      i = 0;
      bool4 = bool2;
      bool2 = bool1;
      bool1 = bool4;
    }
    for (;;)
    {
      label357:
      Log.v("MmsMessageSender", "mSlotId = " + g + ", isSIMReady = " + bool3 + ", isMMs_Network_AVA = " + bool1 + ", isNetworkReady = " + bool2 + ", subId = " + l3);
      if (!c.toString().startsWith(Telephony.Mms.Draft.CONTENT_URI.toString()))
      {
        localObject4 = new ContentValues(7);
        ((ContentValues)localObject4).put("proto_type", Integer.valueOf(e));
        ((ContentValues)localObject4).put("msg_id", Long.valueOf(l1));
        ((ContentValues)localObject4).put("msg_type", Integer.valueOf(((MzGenericPdu)localObject3).getMessageType()));
        ((ContentValues)localObject4).put("err_type", Integer.valueOf(0));
        ((ContentValues)localObject4).put("err_code", Integer.valueOf(0));
        ((ContentValues)localObject4).put("retry_index", Integer.valueOf(0));
        ((ContentValues)localObject4).put("due_time", Integer.valueOf(0));
        if (!f)
        {
          MzSqliteWrapper.insert(b, b.getContentResolver(), Telephony.MmsSms.PendingMessages.CONTENT_URI, (ContentValues)localObject4);
          label574:
          if ((bool2) && (i != 0)) {
            break label1138;
          }
          localContentValues.put("resp_st", Integer.valueOf(9527));
          label597:
          localContentValues.put("association_id", new Long(System.currentTimeMillis()));
          MzSqliteWrapper.update(b, b.getContentResolver(), c, localContentValues, null, null);
          label638:
          localObject3 = new ContentValues(3);
          ((ContentValues)localObject3).put("sim_id", Integer.valueOf(g));
          ((ContentValues)localObject3).put("imsi", aac.d(b, g));
          ((ContentValues)localObject3).put("sub_id", Long.valueOf(l3));
          MzSqliteWrapper.update(b, b.getContentResolver(), c, (ContentValues)localObject3, null, null);
          localObject3 = Telephony.MmsSms.PendingMessages.CONTENT_URI.buildUpon();
          ((Uri.Builder)localObject3).appendQueryParameter("protocol", "mms");
          ((Uri.Builder)localObject3).appendQueryParameter("message", String.valueOf(l2));
          localObject3 = MzSqliteWrapper.query(b, b.getContentResolver(), ((Uri.Builder)localObject3).build(), null, null, null, null);
          if (localObject3 == null) {}
        }
      }
      for (;;)
      {
        try
        {
          if ((((Cursor)localObject3).getCount() == 1) && (((Cursor)localObject3).moveToFirst()))
          {
            localObject4 = new ContentValues();
            ((ContentValues)localObject4).put("pending_sim_id", Integer.valueOf(g));
            ((ContentValues)localObject4).put("pending_sub_id", Long.valueOf(l3));
            l3 = ((Cursor)localObject3).getLong(((Cursor)localObject3).getColumnIndexOrThrow("_id"));
            MzSqliteWrapper.update(b, b.getContentResolver(), Telephony.MmsSms.PendingMessages.CONTENT_URI, (ContentValues)localObject4, "_id=" + l3, null);
            ((Cursor)localObject3).close();
            if ((!bool2) || (i == 0))
            {
              ((ContentValues)localObject1).put("err_type", Integer.valueOf(19));
              MzSqliteWrapper.update(b, b.getContentResolver(), Telephony.MmsSms.PendingMessages.CONTENT_URI, (ContentValues)localObject1, "msg_id=" + l2, null);
            }
            aav.a(Long.valueOf(l1), paramLong);
            Log.d("MmsMessageSender", "MmsMessageSender -> mProtocl : " + e);
            if (e != 1) {
              break label1275;
            }
            localObject1 = new Intent(b, TransactionService.class);
            if ((bool2) && (i != 0)) {
              b.startService((Intent)localObject1);
            }
            return true;
            bool3 = false;
            break;
            label1061:
            bool1 = false;
            break label335;
            label1067:
            bool2 = wd.f(b);
            i = 1;
            bool1 = true;
            break label357;
            if ((!bool2) || (i == 0)) {
              break label574;
            }
            MzSqliteWrapper.update(b, b.getContentResolver(), Telephony.MmsSms.PendingMessages.CONTENT_URI, (ContentValues)localObject4, "msg_id=" + l2, null);
            break label574;
            label1138:
            localContentValues.put("resp_st", Integer.valueOf(0));
            break label597;
            ((MzPduPersister)localObject4).move(c, Telephony.Mms.Outbox.CONTENT_URI);
            if ((!bool2) || (i == 0)) {
              localContentValues.put("resp_st", Integer.valueOf(9527));
            }
            localContentValues.put("association_id", new Long(System.currentTimeMillis()));
            MzSqliteWrapper.update(b, b.getContentResolver(), c, localContentValues, null, null);
            break label638;
          }
          Log.w("MmsMessageSender", "can not find message to set pending sim id, msgId =" + l2);
          continue;
          if (e != 3) {
            break label1302;
          }
        }
        finally
        {
          ((Cursor)localObject3).close();
        }
        label1275:
        Intent localIntent = new Intent(b, SnsTransactionService.class);
        continue;
        label1302:
        localIntent = new Intent(b, FlymeTransactionService.class);
      }
      label1321:
      i = 1;
      bool4 = bool1;
      bool1 = bool2;
      bool2 = bool4;
    }
  }
}

/* Location:
 * Qualified Name:     oh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */