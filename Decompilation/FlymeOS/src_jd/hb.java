import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteFullException;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony.Mms.Draft;
import android.provider.Telephony.Mms.Outbox;
import android.provider.Telephony.MmsSms.PendingMessages;
import android.provider.Telephony.Sms;
import android.provider.Telephony.Sms.Conversations;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.ui.MessagingPreferenceActivity;
import com.google.android.mms.MmsException;
import com.meizu.android.mms.pdu.MzEncodedStringValue;
import com.meizu.android.mms.pdu.MzPduBody;
import com.meizu.android.mms.pdu.MzPduPersister;
import com.meizu.android.mms.pdu.MzSendReq;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class hb
{
  private static boolean n = ;
  private static final String[] q = { "_id", "m_size" };
  private static final String[] t = { "_id", "sub", "sub_cs" };
  private static final String[] u = { "body" };
  private static long w = 0L;
  private final ComposeMessageActivity a;
  private final ContentResolver b;
  private int c;
  private int d;
  private gr e;
  private CharSequence f;
  private int g;
  private lr h;
  private Uri i;
  private CharSequence j;
  private boolean k = false;
  private volatile boolean l;
  private volatile boolean m;
  private final hb.a o;
  private List<String> p;
  private int r = 0;
  private Uri s;
  private Uri v;
  private boolean x = false;
  private boolean y;
  
  private hb(ComposeMessageActivity paramComposeMessageActivity)
  {
    a = paramComposeMessageActivity;
    b = a.getContentResolver();
    o = paramComposeMessageActivity;
    d = 0;
    f = "";
    g = -1;
  }
  
  private void A()
  {
    if ((h == null) || (h.size() != 1)) {
      return;
    }
    if (a.r()) {
      h.d(2);
    }
    while (MmsApp.a)
    {
      E();
      return;
      if (a.l()) {
        h.d(1);
      } else if (h.o()) {
        h.d(0);
      } else {
        h.d(1);
      }
    }
    lq locallq = h.a(0);
    lu locallu;
    if (!locallq.d())
    {
      locallu = new lu(a, "text/plain", "text_0.txt", h.f().b());
      locallq.a(locallu);
    }
    for (;;)
    {
      locallu.a(f);
      return;
      locallu = locallq.p();
    }
  }
  
  private void B()
  {
    if (h.size() != 1) {}
    lq locallq;
    do
    {
      return;
      locallq = h.a(0);
    } while ((locallq == null) || (!locallq.d()));
    f = locallq.p().a();
  }
  
  private void C()
  {
    Log.i("WorkingMessage", "-- mWorkingRecipients:");
    if (p != null)
    {
      int i2 = p.size();
      int i1 = 0;
      while (i1 < i2)
      {
        Log.i("WorkingMessage", "   [" + i1 + "] " + (String)p.get(i1));
        i1 += 1;
      }
      Log.i("WorkingMessage", "");
    }
  }
  
  private boolean D()
  {
    if (c == 0) {}
    while ((c == 32) && (!d())) {
      return false;
    }
    return true;
  }
  
  private void E()
  {
    int i2 = 0;
    lq locallq = h.a(0);
    if (locallq != null) {}
    int i1;
    int i3;
    for (;;)
    {
      try
      {
        lu locallu3 = locallq.p();
        if (!locallq.d()) {
          break label218;
        }
        lu locallu1 = locallq.p();
        if ((locallu3 == null) || (locallu3.a() == null)) {
          break label126;
        }
        i1 = locallu3.a().getBytes().length;
        if (f != null) {
          i2 = f.toString().getBytes().length;
        }
        i3 = i2 - i1;
        if (i3 <= 0) {
          break label195;
        }
        if (h.d() + i3 <= ga.a(true)) {
          break;
        }
        throw new fk();
      }
      catch (fk localfk)
      {
        y = true;
      }
      return;
      label126:
      i1 = 0;
    }
    h.d(i3);
    h.b(i3);
    locallq.a(i3);
    for (;;)
    {
      localfk.a(f);
      Log.i("WorkingMessage", "syncTextToSlideshowForTextSizeChange new = " + localfk.a());
      return;
      label195:
      h.c(i1 - i2);
      locallq.b(i1 - i2);
      continue;
      label218:
      lu locallu2 = new lu(a, "text/plain", "text_0.txt", h.f().b());
      locallq.a(locallu2);
    }
  }
  
  private int a(int paramInt1, Uri paramUri, int paramInt2, yw paramyw)
  {
    if (paramInt1 == 1) {}
    try
    {
      paramyw.a(paramInt2, paramUri);
      return 0;
    }
    catch (MmsException paramUri)
    {
      Log.e("WorkingMessage", "internalChangeMedia:", paramUri);
      return -1;
      if (paramInt1 != 3) {
        break label67;
      }
      paramyw.b(paramInt2, paramUri);
      return 0;
    }
    catch (gd paramUri)
    {
      Log.e("WorkingMessage", "internalChangeMedia:", paramUri);
      return -3;
      if (paramInt1 != 5) {
        break label94;
      }
      paramyw.d(paramInt2, paramUri);
      return 0;
    }
    catch (fk paramUri)
    {
      Log.e("WorkingMessage", "internalChangeMedia:", paramUri);
      return -2;
      if (paramInt1 != 6) {
        break label122;
      }
      paramyw.e(paramInt2, paramUri);
      return 0;
    }
    catch (gc paramUri)
    {
      Log.e("WorkingMessage", "internalChangeMedia:", paramUri);
      return -4;
    }
    if (paramInt1 == 2)
    {
      paramyw.c(paramInt2, paramUri);
      return 0;
    }
    label67:
    label94:
    label122:
    if (paramInt1 == 7)
    {
      paramyw.f(paramInt2, paramUri);
      return 0;
    }
    return -3;
  }
  
  private int a(int paramInt, Uri paramUri, yw paramyw)
  {
    int i3 = 1;
    int i2 = 0;
    int i1;
    if (paramInt == 0) {
      i1 = i2;
    }
    do
    {
      do
      {
        return i1;
        i1 = i3;
        if (h.size() == 1)
        {
          i1 = i3;
          if (!h.g()) {
            i1 = 0;
          }
        }
        if (i1 == 0) {
          break;
        }
        i1 = i2;
      } while (!paramyw.a());
      i2 = h.size() - 1;
      paramInt = a(paramInt, paramUri, i2, paramyw);
      i1 = paramInt;
    } while (paramInt == 0);
    paramyw.b(i2);
    return paramInt;
  }
  
  private int a(int paramInt1, Uri paramUri, yw paramyw, int paramInt2)
  {
    int i2 = 0;
    ArrayList localArrayList = new ArrayList();
    int i1 = 0;
    while (i1 < h.size())
    {
      localArrayList.add(h.a(i1));
      i1 += 1;
    }
    if (localArrayList.size() > 0) {
      paramyw.b();
    }
    paramyw.a(0);
    if (h.a(0) == null) {
      Log.w("Mms", "[WorkingMessage] changeMedia: no slides!");
    }
    do
    {
      return 0;
      d = 0;
      if (paramInt1 != 0) {
        break;
      }
    } while (h.size() != 1);
    h.clear();
    return 0;
    h.d(paramInt2);
    paramInt2 = a(paramInt1, paramUri, 0, paramyw);
    if (paramInt2 != 0)
    {
      paramyw.b(0);
      paramInt1 = i2;
      while (paramInt1 < localArrayList.size())
      {
        paramyw.a(paramInt1, (lq)localArrayList.get(paramInt1));
        paramInt1 += 1;
      }
    }
    return paramInt2;
  }
  
  private static Uri a(MzPduPersister paramMzPduPersister, MzSendReq paramMzSendReq, lr paramlr, Uri paramUri, HashMap<Uri, InputStream> paramHashMap)
  {
    if (paramlr == null) {
      return null;
    }
    for (;;)
    {
      try
      {
        MzPduBody localMzPduBody = paramlr.a();
        paramMzSendReq.setBody(localMzPduBody);
        if (paramUri == null)
        {
          paramUri = Telephony.Mms.Draft.CONTENT_URI;
          paramMzPduPersister = paramMzPduPersister.persist(paramMzSendReq, paramUri, true, false, paramHashMap, c(paramlr.q()));
          paramlr.a(localMzPduBody);
          return paramMzPduPersister;
        }
      }
      catch (MmsException paramMzPduPersister)
      {
        return null;
      }
    }
  }
  
  public static hb a(ComposeMessageActivity paramComposeMessageActivity)
  {
    return new hb(paramComposeMessageActivity);
  }
  
  public static hb a(ComposeMessageActivity paramComposeMessageActivity, Uri paramUri)
  {
    Object localObject2 = null;
    Object localObject1 = paramUri;
    if (!paramUri.toString().startsWith(Telephony.Mms.Draft.CONTENT_URI.toString()))
    {
      localObject1 = MzPduPersister.getPduPersister(paramComposeMessageActivity);
      if (Log.isLoggable("Mms:app", 2)) {
        fl.a("load: moving %s to drafts", new Object[] { paramUri });
      }
    }
    try
    {
      localObject1 = ((MzPduPersister)localObject1).move(paramUri, Telephony.Mms.Draft.CONTENT_URI);
      paramUri = new hb(paramComposeMessageActivity);
      paramComposeMessageActivity = (ComposeMessageActivity)localObject2;
      if (paramUri.c((Uri)localObject1))
      {
        l = true;
        paramComposeMessageActivity = paramUri;
      }
      return paramComposeMessageActivity;
    }
    catch (MmsException paramComposeMessageActivity)
    {
      fl.c("Can't move %s to drafts", new Object[] { paramUri });
    }
    return null;
  }
  
  public static hb a(ComposeMessageActivity paramComposeMessageActivity, Uri paramUri, hb paramhb, boolean paramBoolean)
  {
    if (paramhb == null) {}
    for (paramComposeMessageActivity = new hb(paramComposeMessageActivity);; paramComposeMessageActivity = paramhb)
    {
      if (paramBoolean) {
        v = paramUri;
      }
      if (paramComposeMessageActivity.c(paramUri))
      {
        if (!paramBoolean) {}
        for (paramBoolean = true;; paramBoolean = false)
        {
          l = paramBoolean;
          return paramComposeMessageActivity;
        }
      }
      return null;
    }
  }
  
  public static hb a(ComposeMessageActivity paramComposeMessageActivity, gr paramgr, Runnable paramRunnable, long paramLong)
  {
    if (Log.isLoggable("Mms:app", 2)) {
      fl.a("loadDraft %s", new Object[] { paramgr });
    }
    paramComposeMessageActivity = a(paramComposeMessageActivity);
    if (paramgr.e() <= 0L)
    {
      if (paramRunnable != null) {
        paramRunnable.run();
      }
      return paramComposeMessageActivity;
    }
    new hc(paramComposeMessageActivity, paramgr, paramLong, paramRunnable).execute(new Void[0]);
    return paramComposeMessageActivity;
  }
  
  private void a(int paramInt1, Uri paramUri, String paramString1, String paramString2, int paramInt2, boolean paramBoolean)
  {
    ContentValues localContentValues = new ContentValues();
    if ((paramInt1 == 2) || (paramInt1 == 3))
    {
      localContentValues.put("protocol", Integer.valueOf(2));
      localContentValues.put("file_link", paramString1);
      localContentValues.put("slideshow_description", paramString2);
    }
    for (;;)
    {
      b.update(paramUri, localContentValues, null, null);
      return;
      localContentValues.put("protocol", Integer.valueOf(1));
      localContentValues.put("file_link", "");
      localContentValues.put("slideshow_description", paramString2);
      if (paramBoolean)
      {
        localContentValues.put("imsi", aac.d(a, paramInt2));
        localContentValues.put("sim_id", Integer.valueOf(paramInt2));
      }
    }
  }
  
  private void a(int paramInt, boolean paramBoolean, Uri paramUri)
  {
    if (paramBoolean)
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("imsi", aac.d(a, paramInt));
      localContentValues.put("sim_id", Integer.valueOf(paramInt));
      b.update(paramUri, localContentValues, null, null);
    }
  }
  
  private void a(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (!n) {
      return;
    }
    int i1 = c;
    if (paramBoolean1)
    {
      c |= paramInt;
      label27:
      if ((c == 16) && ((i1 & 0xFFFFFFEF) > 0)) {
        c = 0;
      }
      if ((paramBoolean2) && (o != null))
      {
        if ((i1 != 0) || (c == 0)) {
          break label158;
        }
        o.a(true);
      }
      label82:
      if ((i1 == c) || (!Log.isLoggable("Mms:app", 2))) {
        break label181;
      }
      if (!paramBoolean1) {
        break label183;
      }
    }
    label158:
    label181:
    label183:
    for (String str = "+";; str = "-")
    {
      fl.a("updateState: %s%s = %s", new Object[] { str, d(paramInt), d(c) });
      return;
      c &= (paramInt ^ 0xFFFFFFFF);
      break label27;
      if ((i1 == 0) || (c != 0)) {
        break label82;
      }
      o.a(false);
      break label82;
      break;
    }
  }
  
  public static void a(Uri paramUri)
  {
    if (paramUri != null)
    {
      MmsApp.c().f().a(paramUri);
      MmsApp.c().f().d();
    }
  }
  
  private void a(Uri paramUri, long paramLong, int paramInt)
  {
    if (paramUri == null) {
      return;
    }
    try
    {
      MzPduPersister.getPduPersister(a).move(paramUri, Telephony.Mms.Outbox.CONTENT_URI);
      ContentValues localContentValues = new ContentValues(1);
      localContentValues.put("resp_st", Integer.valueOf(9527));
      localContentValues.put("m_size", Long.valueOf(paramLong));
      localContentValues.put("sim_id", Integer.valueOf(paramInt));
      b.update(paramUri, localContentValues, null, null);
      localContentValues.clear();
      localContentValues.put("err_type", Integer.valueOf(19));
      localContentValues.put("pending_sim_id", Integer.valueOf(paramInt));
      paramLong = ContentUris.parseId(paramUri);
      b.update(Telephony.MmsSms.PendingMessages.CONTENT_URI, localContentValues, "msg_id=" + paramLong, null);
      return;
    }
    catch (MmsException localMmsException)
    {
      Log.e("WorkingMessage", "Failed to move message to outbox and mark as error: " + paramUri, localMmsException);
    }
  }
  
  private static void a(Uri paramUri, MzPduPersister paramMzPduPersister, lr paramlr, MzSendReq paramMzSendReq, HashMap<Uri, InputStream> paramHashMap)
  {
    if (Log.isLoggable("Mms:app", 2)) {
      fl.a("updateDraftMmsMessage uri=%s", new Object[] { paramUri });
    }
    if (paramUri == null)
    {
      Log.e("WorkingMessage", "updateDraftMmsMessage null uri");
      return;
    }
    paramMzPduPersister.updateHeaders(paramUri, paramMzSendReq, c(paramlr.q()));
    paramMzSendReq = paramlr.a();
    try
    {
      paramMzPduPersister.updateParts(paramUri, paramMzSendReq, paramHashMap, c(paramlr.q()));
      paramlr.a(paramMzSendReq);
      return;
    }
    catch (MmsException paramMzPduPersister)
    {
      for (;;)
      {
        Log.e("WorkingMessage", "updateDraftMmsMessage: cannot update message " + paramUri);
      }
    }
  }
  
  private void a(Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    if (Log.isLoggable("Mms:app", 2)) {
      fl.a("asyncDelete %s where %s", new Object[] { paramUri, paramString });
    }
    new Thread(new hj(this, paramUri, paramString, paramArrayOfString), "WorkingMessage.asyncDelete").start();
  }
  
  private void a(gr paramgr, Uri paramUri, MzPduPersister paramMzPduPersister, MzSendReq paramMzSendReq, int paramInt1, String[] paramArrayOfString, boolean paramBoolean, int paramInt2)
  {
    Log.i("WorkingMessage", "sendTalkWorker slotId = " + paramInt2);
    Object localObject1;
    if (paramBoolean) {
      try
      {
        paramArrayOfString = new lt(a, paramUri);
        if (paramArrayOfString == null)
        {
          if (o != null) {
            o.a(1001, true);
          }
          Log.e("WorkingMessage", "sendTalkWorker()--> TalkModel is null, audioUri is " + paramUri);
          return;
        }
      }
      catch (MmsException paramArrayOfString)
      {
        for (;;)
        {
          paramArrayOfString.printStackTrace();
          paramArrayOfString = null;
        }
        localObject1 = a(paramArrayOfString, paramInt1);
      }
    }
    for (;;)
    {
      long l1;
      lr locallr;
      try
      {
        zt.c().a(true);
        if (o != null) {
          o.b(true);
        }
        l1 = paramgr.f();
        if (Log.isLoggable("Mms:app", 2)) {
          fl.a("sendMmsWorker: update draft TALK threadId: " + l1, new Object[0]);
        }
        Object localObject2 = paramgr.h().a(true);
        if (localObject2.length == 1)
        {
          paramgr = gr.a(a, paramgr.e(), localObject2[0]);
          if (Log.isLoggable("Mms:app", 2)) {
            fl.a("sendMmsWorker: newAddress " + paramgr + " dests[0]: " + localObject2[0], new Object[0]);
          }
          if (!paramgr.equals(localObject2[0]))
          {
            localObject2[0] = paramgr;
            paramgr = MzEncodedStringValue.encodeStrings((String[])localObject2);
            if (paramgr != null)
            {
              if (Log.isLoggable("Mms:app", 2)) {
                fl.a("sendMmsWorker: REPLACING number!!!", new Object[0]);
              }
              paramMzSendReq.setTo(paramgr);
            }
          }
        }
        if (paramBoolean)
        {
          i1 = (paramArrayOfString.i() + 500) / 1000;
          paramgr = "1;;talk#audio/amr-meizu:" + i1;
          paramArrayOfString = new ContentValues();
          paramArrayOfString.put("msg_box", Integer.valueOf(4));
          paramArrayOfString.put("thread_id", Long.valueOf(l1));
          paramArrayOfString.put("m_type", Integer.valueOf(128));
          if (paramInt1 != 1) {
            break label1112;
          }
          i1 = 2;
          paramArrayOfString.put("protocol", Integer.valueOf(i1));
          paramArrayOfString.put("slideshow_description", paramgr);
          paramArrayOfString.put("file_link", paramUri.getPath());
          paramArrayOfString.put("imsi", aac.d(a, paramInt2));
          paramgr = paramMzSendReq.getSubject();
          if (paramgr != null)
          {
            paramArrayOfString.put("sub", MzPduPersister.toIsoString(paramgr.getTextString()));
            paramArrayOfString.put("sub_cs", Integer.valueOf(paramgr.getCharacterSet()));
          }
          paramArrayOfString.put("sim_id", Integer.valueOf(paramInt2));
          paramgr = b.insert(Telephony.Mms.Outbox.CONTENT_URI, paramArrayOfString);
          Log.i("WorkingMessage", "sendTalkWorker--> updateDraftMmsMessage mmsUri = " + paramgr);
          if (paramgr == null)
          {
            if (o != null) {
              o.a(1001, false);
            }
            Log.e("WorkingMessage", "updateDraftMmsMessage null mmsUri");
            return;
            locallr = lr.a(a);
            locallr.d(paramInt1);
            localObject2 = new lq(locallr);
            try
            {
              localObject1 = new lk(a, paramUri, locallr.f().a());
              lu locallu;
              localMmsException1.printStackTrace();
            }
            catch (MmsException localMmsException1)
            {
              try
              {
                ((lk)localObject1).b(paramArrayOfString[0]);
                ((lq)localObject2).a((lm)localObject1);
                locallu = new lu(a, "text/plain", "text_0.txt", locallr.f().b());
                locallu.a(paramArrayOfString[1]);
                ((lq)localObject2).a(locallu);
                locallr.a((lq)localObject2);
                paramArrayOfString = (String[])localObject1;
                if (paramArrayOfString != null) {
                  break label1102;
                }
                if (o != null) {
                  o.a(1001, true);
                }
                Log.e("WorkingMessage", "sendLocationWorker()--> ImageModel is null, locationMapUri is " + paramUri);
                return;
              }
              catch (MmsException localMmsException2)
              {
                long l2;
                paramArrayOfString = (String[])localObject1;
                continue;
                localObject1 = locallr;
                paramArrayOfString = null;
              }
              localMmsException1 = localMmsException1;
              paramArrayOfString = null;
            }
            continue;
          }
        }
        else
        {
          paramgr = "1;text#text/plain;location#image/jpg";
          continue;
        }
        if (o != null) {
          o.d();
        }
        fh.a(a);
        if (0 != 0) {
          throw new NullPointerException();
        }
        zt.c().a(false);
        if (paramBoolean)
        {
          paramgr = a(paramMzPduPersister, paramMzSendReq, (lr)localObject1, paramgr, null);
          if (paramgr != null) {
            break label969;
          }
          if (o != null) {
            o.a(1001, false);
          }
          Log.e("WorkingMessage", "sendTalkWorker--> updateDraftMmsMessage mmsUri is " + paramgr);
          return;
        }
      }
      finally
      {
        if (0 != 0) {
          throw new NullPointerException();
        }
        zt.c().a(false);
      }
      paramgr = b(paramMzPduPersister, paramMzSendReq, (lr)localObject1, paramgr, a, null, false);
      MmsApp.c().e().a(paramgr);
      continue;
      label969:
      paramUri = a;
      l2 = ((lr)localObject1).d();
      if (paramInt1 == 1) {}
      for (paramInt1 = 2;; paramInt1 = 3)
      {
        paramUri = new oh(paramUri, paramgr, l2, paramInt1);
        paramUri.a(paramInt2);
        try
        {
          if (!paramUri.a(l1)) {
            b.delete(paramgr, null, null);
          }
          aat.b().a(a, l1);
          return;
        }
        catch (Exception paramUri)
        {
          Log.e("WorkingMessage", "Failed to send message: " + paramgr + ", threadId=" + l1, paramUri);
          return;
        }
      }
      label1102:
      continue;
      label1112:
      int i1 = 3;
    }
  }
  
  private void a(gr paramgr, Uri paramUri, MzPduPersister paramMzPduPersister, lr paramlr, MzSendReq paramMzSendReq, boolean paramBoolean, int paramInt)
  {
    long l1;
    Object localObject1;
    int i1;
    String str;
    long l2;
    for (;;)
    {
      try
      {
        zt.c().a(true);
        if (o != null) {
          o.b(false);
        }
        l1 = paramgr.f();
        if (Log.isLoggable("Mms:app", 2)) {
          fl.a("sendMmsWorker: update draft MMS message " + paramUri + " threadId: " + l1, new Object[0]);
        }
        localObject1 = paramgr.h().a(true);
        if (localObject1.length == 1)
        {
          paramgr = gr.a(a, paramgr.e(), localObject1[0]);
          if (Log.isLoggable("Mms:app", 2)) {
            fl.a("sendMmsWorker: newAddress " + paramgr + " dests[0]: " + localObject1[0], new Object[0]);
          }
          if (!paramgr.equals(localObject1[0]))
          {
            localObject1[0] = paramgr;
            paramgr = MzEncodedStringValue.encodeStrings((String[])localObject1);
            if (paramgr != null)
            {
              if (Log.isLoggable("Mms:app", 2)) {
                fl.a("sendMmsWorker: REPLACING number!!!", new Object[0]);
              }
              paramMzSendReq.setTo(paramgr);
            }
          }
        }
        paramlr.d(r);
        if (paramUri == null)
        {
          i1 = 1;
          str = aac.d(a, paramInt);
          Log.d("WorkingMessage", "sendMmsWorker -> slotId : " + paramInt + "  imsi : " + str);
          if (i1 == 0) {
            break label725;
          }
          paramgr = new ContentValues();
          paramgr.put("msg_box", Integer.valueOf(4));
          paramgr.put("thread_id", Long.valueOf(l1));
          paramgr.put("m_type", Integer.valueOf(128));
          if (paramBoolean) {
            paramgr.put("text_only", Integer.valueOf(1));
          }
          paramUri = paramMzSendReq.getSubject();
          if (paramUri != null)
          {
            paramgr.put("sub", MzPduPersister.toIsoString(paramUri.getTextString()));
            paramgr.put("sub_cs", Integer.valueOf(paramUri.getCharacterSet()));
          }
          paramgr.put("slideshow_description", paramlr.l());
          paramUri = paramlr.m();
          if (!TextUtils.isEmpty(paramUri)) {
            paramgr.put("first_media_id", "0;" + paramUri);
          }
          if (((r != 1) || (paramlr.q() != 1)) && (r != 2) && (paramlr.q() != 2)) {
            continue;
          }
          l2 = paramlr.p();
          if (l2 > 0L) {
            paramgr.put("m_size", Long.valueOf(l2));
          }
          paramgr.put("protocol", Integer.valueOf(c(r)));
          paramUri = paramlr.k();
          if (paramUri != null) {
            paramgr.put("file_link", Uri.decode(paramUri.toString()));
          }
          paramgr.put("imsi", str);
          paramgr.put("sim_id", Integer.valueOf(paramInt));
          paramgr = b.insert(Telephony.Mms.Outbox.CONTENT_URI, paramgr);
          if (o != null) {
            o.d();
          }
          fh.a(a);
          if (0 != 0) {
            throw new NullPointerException();
          }
          if (i1 == 0) {
            break label1058;
          }
        }
      }
      finally
      {
        label630:
        if (0 != 0) {
          throw new NullPointerException();
        }
      }
      try
      {
        paramUri = b(paramMzPduPersister, paramMzSendReq, paramlr, paramgr, a, null, false);
        MmsApp.c().e().a(paramUri);
        b(l1);
        zt.c().a(false);
        if (paramUri != null) {
          break label1083;
        }
        if (o != null)
        {
          a(paramgr, paramlr.p(), paramInt);
          o.b(-1);
          o.a(1001, false);
        }
        return;
      }
      finally
      {
        ContentValues localContentValues;
        zt.c().a(false);
      }
      i1 = 0;
      continue;
      paramgr.put("protocol", Integer.valueOf(1));
    }
    label725:
    localContentValues = new ContentValues();
    localContentValues.put("m_size", Long.valueOf(paramlr.e()));
    localContentValues.put("slideshow_description", paramlr.l());
    if (((r == 1) && (paramlr.q() == 1)) || (r == 2) || (paramlr.q() == 2))
    {
      l2 = paramlr.p();
      if (l2 > 0L) {
        localContentValues.put("m_size", Long.valueOf(l2));
      }
      localContentValues.put("protocol", Integer.valueOf(c(r)));
      paramgr = paramlr.j();
      if (paramgr == null) {
        break label1307;
      }
    }
    label1015:
    label1058:
    label1083:
    label1307:
    for (paramgr = paramgr.k();; paramgr = null)
    {
      if (paramgr != null)
      {
        if (!paramgr.getAuthority().startsWith("mms")) {
          break label1015;
        }
        Object localObject2 = paramMzPduPersister.queryPartFilePath(paramgr);
        localObject1 = localObject2;
        if (MzPduPersister.isDataInSystem((String)localObject2))
        {
          localObject1 = new ContentValues();
          ((ContentValues)localObject1).put("_data", (String)localObject2);
          localObject2 = Uri.parse("content://mms/part/move/" + paramgr.getLastPathSegment());
          b.update((Uri)localObject2, (ContentValues)localObject1, null, null);
          localObject1 = (String)aau.a(paramgr, "toSafeString");
        }
        localContentValues.put("file_link", (String)localObject1);
      }
      for (;;)
      {
        localContentValues.put("sim_id", Integer.valueOf(paramInt));
        localContentValues.put("imsi", str);
        b.update(paramUri, localContentValues, null, null);
        paramgr = paramUri;
        break;
        localContentValues.put("file_link", Uri.decode(paramgr.toString()));
        continue;
        localContentValues.put("protocol", Integer.valueOf(1));
        localContentValues.put("file_link", "");
      }
      a(paramgr, paramMzPduPersister, paramlr, paramMzSendReq, null);
      paramUri = paramgr;
      break label630;
      i1 = 0;
      try
      {
        paramlr.a(paramUri);
        if (i1 != 0)
        {
          a(paramUri, paramlr.p(), paramInt);
          o.b(i1);
          return;
        }
      }
      catch (fk paramgr)
      {
        for (;;)
        {
          i1 = -2;
        }
      }
      catch (MmsException paramgr)
      {
        for (;;)
        {
          i1 = -1;
        }
        i1 = c(r);
        Log.d("WorkingMessage", "mmsProtocl : " + i1);
        paramgr = new ContentValues();
        paramgr.put("protocol", Integer.valueOf(i1));
        b.update(paramUri, paramgr, null, null);
        paramgr = new oh(a, paramUri, paramlr.d(), i1);
        paramgr.a(paramInt);
        try
        {
          if (!paramgr.a(l1)) {
            b.delete(paramUri, null, null);
          }
          aat.b().a(a, l1);
          return;
        }
        catch (Exception paramgr)
        {
          Log.e("WorkingMessage", "Failed to send message: " + paramUri + ", threadId=" + l1, paramgr);
          return;
        }
      }
    }
  }
  
  private void a(gr paramgr, String paramString1, String paramString2, boolean paramBoolean1, int paramInt, boolean paramBoolean2)
  {
    fh.a(a);
    Object localObject;
    boolean bool;
    long l2;
    String str;
    if (o != null)
    {
      localObject = o;
      if (!paramBoolean1)
      {
        bool = true;
        ((hb.a)localObject).b(bool);
      }
    }
    else
    {
      long l1 = paramgr.e();
      l2 = paramgr.f();
      localObject = paramgr.h();
      str = ((gq)localObject).a();
      if (((l1 != 0L) && (l1 != l2)) || ((!str.equals(paramString2)) && (!TextUtils.isEmpty(paramString2))))
      {
        if ((l1 == 0L) || (l1 == l2)) {
          break label213;
        }
        paramgr = "WorkingMessage.preSendSmsWorker threadId changed or recipients changed. origThreadId: " + l1 + " new threadId: " + l2 + " also mConversation.getThreadId(): " + e.e();
        label157:
        fl.a(paramgr, a);
      }
      if (!wd.c(str, ";")) {
        break label254;
      }
      Log.d("MMS_APP", "procReceivedPortDataSms: sendd a sns msg");
      a(paramString1, str, l2);
    }
    for (;;)
    {
      label195:
      if (paramBoolean1) {
        b(l2);
      }
      label213:
      label254:
      do
      {
        return;
        bool = false;
        break;
        paramgr = "Recipients in window: \"" + paramString2 + "\" differ from recipients from conv: \"" + str + "\"";
        break label157;
        if ((r == 1) || ((((gq)localObject).size() == 1) && (aau.d(((gm)((gq)localObject).get(0)).d()))))
        {
          Log.d("MMS_APP", "procReceivedPortDataSms: send a sip msg slotId = " + paramInt);
          a(paramString1, str, l2, paramInt, paramBoolean2);
          break label195;
        }
      } while (e.z());
      b(paramString1, str, l2, paramInt, paramBoolean2);
    }
  }
  
  private void a(gr paramgr, String paramString, boolean paramBoolean)
  {
    new Thread(new hi(this, paramgr, paramBoolean, paramString), "WorkingMessage.asyncUpdateDraftSmsMessage").start();
  }
  
  private void a(String paramString1, String paramString2, long paramLong)
  {
    Log.d("SnsMessageManager", "WorkingMessage-->sendSnsSmsWorker(), send SNS message, threadId=" + paramLong);
    String[] arrayOfString = TextUtils.split(paramString2, ";");
    if (Log.isLoggable("Mms:transaction", 2)) {
      fl.a("sendSmsWorker sending message: recipients=" + paramString2 + ", threadId=" + paramLong, new Object[0]);
    }
    paramString1 = new po(a, arrayOfString, "", paramString1, paramLong, g);
    try
    {
      paramString1.a(paramLong);
      aat.a().a(a, paramLong);
      aat.c(a);
      if (o != null) {
        o.d();
      }
      return;
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        Log.e("WorkingMessage", "Failed to send SMS message, threadId=" + paramLong, paramString1);
      }
    }
  }
  
  private void a(String paramString1, String paramString2, long paramLong, int paramInt, boolean paramBoolean)
  {
    String[] arrayOfString = TextUtils.split(paramString2, ";");
    if (Log.isLoggable("Mms:transaction", 2)) {
      fl.a("sendSmsWorker sending message: recipients=" + paramString2 + ", threadId=" + paramLong, new Object[0]);
    }
    paramString1 = new ph(a, arrayOfString, paramString1, paramLong, g);
    try
    {
      paramString1.a(paramInt);
      paramString1.a(paramBoolean);
      paramString1.b(w);
      paramString1.a(paramLong);
      aat.a().a(a, paramLong);
      aat.c(a);
      if (o != null) {
        o.d();
      }
      return;
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        Log.e("WorkingMessage", "Failed to send SMS message, threadId=" + paramLong, paramString1);
      }
    }
  }
  
  public static void a(lr paramlr)
  {
    abm localabm;
    int i1;
    Object localObject;
    if (paramlr != null)
    {
      localabm = MmsApp.c().f();
      paramlr = paramlr.iterator();
      i1 = 0;
      if (paramlr.hasNext())
      {
        localObject = (lq)paramlr.next();
        if (((lq)localObject).e())
        {
          localObject = ((lq)localObject).q().k();
          localabm.a((Uri)localObject);
          if (((Uri)localObject).getScheme().equals("file"))
          {
            Log.d("WorkingMessage", "delete cache file, path is " + ((Uri)localObject).getPath());
            localObject = new File(((Uri)localObject).getPath());
            if (((File)localObject).exists()) {
              ((File)localObject).delete();
            }
          }
          i1 = 1;
        }
      }
    }
    for (;;)
    {
      break;
      if (((lq)localObject).g())
      {
        localabm.a(((lq)localObject).s().k());
        i1 = 1;
        continue;
        if (i1 != 0) {
          MmsApp.c().f().d();
        }
        return;
      }
    }
  }
  
  private boolean a(gr paramgr, String paramString)
  {
    paramgr = paramgr.h().c();
    int i2 = paramgr.length;
    int i1 = 0;
    while (i1 < i2)
    {
      if (((((Boolean)aau.a("android.provider.Telephony$Mms", "isEmailAddress", String.class, paramgr[i1])).booleanValue()) || (wd.b(paramgr[i1]))) && (android.telephony.SmsMessage.calculateLength(paramgr[i1] + " " + paramString, false)[0] > 0))
      {
        a(1, true, true);
        z();
        A();
        return true;
      }
      i1 += 1;
    }
    return false;
  }
  
  public static final int b(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return 0;
    case 3: 
    case 266: 
      return 2;
    }
    return 1;
  }
  
  private static Uri b(Context paramContext, gr paramgr, StringBuilder paramStringBuilder, long paramLong)
  {
    if (!paramgr.i()) {}
    String str;
    do
    {
      return null;
      if (Log.isLoggable("Mms:app", 2)) {
        fl.a("readDraftMmsMessage conv: " + paramgr, new Object[0]);
      }
      paramContext.getContentResolver();
      str = "thread_id = " + paramgr.e();
      paramgr = str;
      if (paramLong > 0L) {
        paramgr = str + " and _id != " + paramLong;
      }
      paramContext = paramContext.getContentResolver().query(Telephony.Mms.Draft.CONTENT_URI, t, paramgr, null, null);
    } while (paramContext == null);
    try
    {
      if (paramContext.moveToFirst())
      {
        paramgr = ContentUris.withAppendedId(Telephony.Mms.Draft.CONTENT_URI, paramContext.getLong(0));
        str = wd.a(paramContext, 1, 2);
        if (str != null) {
          paramStringBuilder.append(str);
        }
        if (Log.isLoggable("Mms:app", 2)) {
          fl.a("readDraftMmsMessage uri: ", new Object[] { paramgr });
        }
        return paramgr;
      }
      return null;
    }
    finally
    {
      paramContext.close();
    }
  }
  
  private static Uri b(MzPduPersister paramMzPduPersister, MzSendReq paramMzSendReq, lr paramlr, Uri paramUri, Context paramContext, HashMap<Uri, InputStream> paramHashMap, boolean paramBoolean)
  {
    if (paramlr == null) {
      return null;
    }
    try
    {
      MzPduBody localMzPduBody = paramlr.a();
      paramMzSendReq.setBody(localMzPduBody);
      int i1 = c(paramlr.q());
      ContentValues localContentValues1;
      ContentValues localContentValues2;
      if (i1 == 1) {
        if (paramBoolean)
        {
          localContentValues1 = new ContentValues(4);
          localContentValues1.put("imsi", zv.e());
          zv.f();
          localContentValues1.put("protocol", Integer.valueOf(i1));
          localContentValues1.put("slideshow_description", paramlr.l());
          localContentValues1.put("file_link", "");
          localContentValues2 = localContentValues1;
          if (paramUri != null) {
            break label279;
          }
          paramUri = Telephony.Mms.Draft.CONTENT_URI;
        }
      }
      label279:
      for (;;)
      {
        paramMzPduPersister = paramMzPduPersister.persist(paramMzSendReq, paramUri, true, MessagingPreferenceActivity.b(paramContext), paramHashMap, i1, localContentValues2);
        paramlr.a(localMzPduBody);
        return paramMzPduPersister;
        localContentValues1 = new ContentValues(3);
        break;
        if (paramBoolean)
        {
          localContentValues1 = new ContentValues(5);
          localContentValues1.put("imsi", zv.e());
          zv.f();
        }
        for (;;)
        {
          long l1 = paramlr.p();
          if (l1 > 0L) {
            localContentValues1.put("m_size", Long.valueOf(l1));
          }
          localContentValues1.put("protocol", Integer.valueOf(i1));
          localContentValues1.put("slideshow_description", paramlr.l());
          Uri localUri = paramlr.k();
          localContentValues2 = localContentValues1;
          if (localUri == null) {
            break;
          }
          localContentValues1.put("file_link", Uri.decode(localUri.toString()));
          localContentValues2 = localContentValues1;
          break;
          localContentValues1 = new ContentValues(4);
        }
      }
      return null;
    }
    catch (MmsException paramMzPduPersister) {}
  }
  
  private static MzSendReq b(gr paramgr, CharSequence paramCharSequence)
  {
    Object localObject = paramgr.h().a(true);
    paramgr = new MzSendReq();
    localObject = MzEncodedStringValue.encodeStrings((String[])localObject);
    if (localObject != null) {
      paramgr.setTo((MzEncodedStringValue[])localObject);
    }
    if (!TextUtils.isEmpty(paramCharSequence)) {
      paramgr.setSubject(new MzEncodedStringValue(paramCharSequence.toString()));
    }
    paramgr.setDate(System.currentTimeMillis() / 1000L);
    return paramgr;
  }
  
  private void b(long paramLong)
  {
    b.delete(ContentUris.withAppendedId(Telephony.Sms.Conversations.CONTENT_URI, paramLong), "type=3", null);
  }
  
  private void b(Uri paramUri, int paramInt1, boolean paramBoolean1, String paramString1, String paramString2, long paramLong1, long paramLong2, int paramInt2, boolean paramBoolean2)
  {
    fl.a("resendMmsWorker: resend MMS message " + paramUri + ", protocol -> " + paramInt1 + ", slotId = " + paramInt2 + ", isSlotIdChanged = " + paramBoolean2, new Object[0]);
    if (paramUri == null) {
      Log.e("WorkingMessage", "Failed to resend message: mmsUri == null");
    }
    if (paramBoolean1) {
      a(paramInt1, paramUri, paramString1, paramString2, paramInt2, paramBoolean2);
    }
    for (;;)
    {
      paramString1 = new oh(a, paramUri, paramLong1, paramInt1, true);
      paramString1.a(paramInt2);
      try
      {
        paramString1.a(paramLong2);
        aat.b().a(a, paramLong2);
        aat.c(a);
        if (o != null) {
          o.d();
        }
        return;
        a(paramInt2, paramBoolean2, paramUri);
      }
      catch (Exception paramString1)
      {
        for (;;)
        {
          Log.e("WorkingMessage", "Failed to send message: " + paramUri + ", threadId=" + paramLong2, paramString1);
        }
      }
    }
  }
  
  private static void b(Uri paramUri, MzPduPersister paramMzPduPersister, lr paramlr, MzSendReq paramMzSendReq, HashMap<Uri, InputStream> paramHashMap, boolean paramBoolean)
  {
    if (Log.isLoggable("Mms:app", 2)) {
      fl.a("updateDraftMmsMessage uri=%s", new Object[] { paramUri });
    }
    if (paramUri == null)
    {
      Log.e("WorkingMessage", "updateDraftMmsMessage null uri");
      return;
    }
    int i1 = c(paramlr.q());
    ContentValues localContentValues1;
    if (i1 == 1) {
      if (paramBoolean)
      {
        localContentValues1 = new ContentValues(4);
        localContentValues1.put("imsi", zv.e());
        zv.f();
      }
    }
    for (;;)
    {
      localContentValues1.put("protocol", Integer.valueOf(1));
      localContentValues1.put("slideshow_description", paramlr.l());
      localContentValues1.put("file_link", "");
      ContentValues localContentValues2 = localContentValues1;
      paramMzPduPersister.updateHeaders(paramUri, paramMzSendReq, localContentValues2);
      paramMzSendReq = paramlr.a();
      try
      {
        paramMzPduPersister.updateParts(paramUri, paramMzSendReq, paramHashMap, c(paramlr.q()));
        paramlr.a(paramMzSendReq);
        return;
        localContentValues1 = new ContentValues(3);
        continue;
        if (paramBoolean)
        {
          localContentValues1 = new ContentValues(5);
          localContentValues1.put("imsi", zv.e());
          zv.f();
        }
        for (;;)
        {
          long l1 = paramlr.p();
          if (l1 > 0L) {
            localContentValues1.put("m_size", Long.valueOf(l1));
          }
          localContentValues1.put("protocol", Integer.valueOf(i1));
          localContentValues1.put("slideshow_description", paramlr.l());
          Uri localUri = paramlr.k();
          localContentValues2 = localContentValues1;
          if (localUri == null) {
            break;
          }
          localContentValues1.put("file_link", Uri.decode(localUri.toString()));
          localContentValues2 = localContentValues1;
          break;
          localContentValues1 = new ContentValues(4);
        }
      }
      catch (MmsException paramMzPduPersister)
      {
        for (;;)
        {
          Log.e("WorkingMessage", "updateDraftMmsMessage: cannot update message " + paramUri);
        }
      }
    }
  }
  
  private void b(gr paramgr, String paramString)
  {
    long l1 = paramgr.e();
    if (Log.isLoggable("Mms:app", 2)) {
      fl.a("updateDraftSmsMessage tid=%d, contents=\"%s\"", new Object[] { Long.valueOf(l1), paramString });
    }
    if (l1 <= 0L) {
      return;
    }
    ContentValues localContentValues = new ContentValues(3);
    localContentValues.put("thread_id", Long.valueOf(l1));
    localContentValues.put("body", paramString);
    localContentValues.put("type", Integer.valueOf(3));
    localContentValues.put("imsi", zv.e());
    zv.f();
    b.insert(Telephony.Sms.CONTENT_URI, localContentValues);
    e(paramgr);
    i = null;
  }
  
  private void b(gr paramgr, boolean paramBoolean)
  {
    if (Log.isLoggable("Mms:app", 2)) {
      fl.a("asyncUpdateDraftMmsMessage conv=%s mMessageUri=%s", new Object[] { paramgr, i });
    }
    new Thread(new hh(this, paramgr, paramBoolean, h.a(b)), "WorkingMessage.asyncUpdateDraftMmsMessage").start();
  }
  
  private void b(String paramString1, String paramString2, long paramLong, int paramInt, boolean paramBoolean)
  {
    String[] arrayOfString = TextUtils.split(paramString2, ";");
    Log.d("WorkingMessage", "sendSmsWorker sending message: recipients=" + paramString2 + ", threadId=" + paramLong + ", slotId =" + paramInt);
    paramString1 = new ot(a, arrayOfString, paramString1, paramLong, -1, paramInt);
    try
    {
      paramString1.a(paramInt);
      paramString1.a(paramBoolean);
      paramString1.b(w);
      paramString1.a(paramLong);
      aat.a().a(a, paramLong);
      aat.c(a);
      if (o != null) {
        o.d();
      }
      return;
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        Log.e("WorkingMessage", "Failed to send SMS message, threadId=" + paramLong, paramString1);
      }
    }
  }
  
  private static void b(HashMap<Uri, InputStream> paramHashMap)
  {
    if (paramHashMap == null) {}
    for (;;)
    {
      return;
      Iterator localIterator = paramHashMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        InputStream localInputStream = (InputStream)paramHashMap.get((Uri)localIterator.next());
        if (localInputStream != null) {
          try
          {
            localInputStream.close();
          }
          catch (IOException localIOException) {}
        }
      }
    }
  }
  
  public static final int c(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return 1;
    case 2: 
    case 266: 
      return 3;
    }
    return 2;
  }
  
  private void c(gr paramgr)
  {
    try
    {
      paramgr = new ArrayList(Arrays.asList(paramgr.h().c()));
      new fg(a).a(paramgr);
      return;
    }
    catch (SQLiteFullException paramgr)
    {
      Log.e("WorkingMessage", "Throw Exception : ", paramgr);
    }
  }
  
  private void c(gr paramgr, boolean paramBoolean)
  {
    if ((paramBoolean) && (paramgr.k() == 0)) {
      paramgr.g();
    }
    if (!paramgr.h().isEmpty()) {
      paramgr.f();
    }
  }
  
  private boolean c(Uri paramUri)
  {
    if (Log.isLoggable("Mms:app", 2)) {
      fl.a("loadFromUri %s", new Object[] { paramUri });
    }
    try
    {
      h = lr.b(a, paramUri);
      i = paramUri;
      B();
      f(false);
      return true;
    }
    catch (Exception localException)
    {
      fl.c("Couldn't load URI %s", new Object[] { paramUri });
    }
    return false;
  }
  
  private static String d(int paramInt)
  {
    if (paramInt == 0) {
      return "<none>";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    if ((paramInt & 0x1) > 0) {
      localStringBuilder.append("RECIPIENTS_REQUIRE_MMS | ");
    }
    if ((paramInt & 0x2) > 0) {
      localStringBuilder.append("HAS_SUBJECT | ");
    }
    if ((paramInt & 0x4) > 0) {
      localStringBuilder.append("HAS_ATTACHMENT | ");
    }
    if ((paramInt & 0x8) > 0) {
      localStringBuilder.append("LENGTH_REQUIRES_MMS | ");
    }
    if ((paramInt & 0x10) > 0) {
      localStringBuilder.append("FORCE_MMS | ");
    }
    if ((paramInt & 0x20) > 0) {
      localStringBuilder.append("MULTIPLE_RECIPIENTS | ");
    }
    localStringBuilder.delete(localStringBuilder.length() - 3, localStringBuilder.length());
    return localStringBuilder.toString();
  }
  
  private String d(gr paramgr)
  {
    boolean bool = true;
    long l1 = paramgr.e();
    if (Log.isLoggable("Mms:app", 2)) {
      Log.d("WorkingMessage", "readDraftSmsMessage conv: " + paramgr);
    }
    if ((l1 <= 0L) || (!paramgr.i()))
    {
      paramgr = "";
      return paramgr;
    }
    Object localObject = ContentUris.withAppendedId(Telephony.Sms.Conversations.CONTENT_URI, l1);
    Cursor localCursor = b.query((Uri)localObject, u, "type=3", null, null);
    if (localCursor != null) {}
    for (;;)
    {
      try
      {
        if (!localCursor.moveToFirst()) {
          break label210;
        }
        localObject = localCursor.getString(0);
        i1 = 1;
        localCursor.close();
        if ((!MmsApp.a) && (i1 != 0) && (paramgr.k() == 0))
        {
          b(paramgr);
          a(paramgr, true);
        }
        paramgr = (gr)localObject;
        if (!Log.isLoggable("Mms:app", 2)) {
          break;
        }
        if (!TextUtils.isEmpty((CharSequence)localObject))
        {
          fl.a("readDraftSmsMessage haveDraft: ", new Object[] { Boolean.valueOf(bool) });
          return (String)localObject;
        }
      }
      finally
      {
        localCursor.close();
      }
      bool = false;
      continue;
      label210:
      int i1 = 0;
      localObject = "";
      continue;
      i1 = 0;
      localObject = "";
    }
  }
  
  private void e(gr paramgr)
  {
    l = false;
    long l1 = paramgr.e();
    StringBuilder localStringBuilder = new StringBuilder().append("thread_id");
    if (l1 > 0L) {}
    for (paramgr = " = " + l1;; paramgr = " IS NULL")
    {
      paramgr = paramgr;
      a(Telephony.Mms.Draft.CONTENT_URI, paramgr, null);
      return;
    }
  }
  
  private void f(boolean paramBoolean)
  {
    int i1 = h.size();
    if (i1 == 0) {
      a(false);
    }
    for (;;)
    {
      a(4, h(), paramBoolean);
      return;
      if (i1 > 1)
      {
        d = 4;
      }
      else
      {
        lq locallq = h.a(0);
        if (locallq.e()) {
          d = 1;
        } else if (locallq.g()) {
          d = 2;
        } else if (locallq.f()) {
          d = 3;
        } else if (locallq.i()) {
          d = 5;
        } else if (locallq.j()) {
          d = 7;
        } else if (locallq.d()) {
          d = 0;
        }
      }
    }
  }
  
  private void g(boolean paramBoolean)
  {
    m();
    if (D())
    {
      z();
      A();
    }
  }
  
  private void y()
  {
    if (h != null) {}
    for (int i1 = h.size();; i1 = 0)
    {
      if (i1 > 0)
      {
        lk locallk = h.a(i1 - 1).q();
        if (locallk != null) {
          locallk.d();
        }
      }
      return;
    }
  }
  
  private void z()
  {
    if (h != null) {
      return;
    }
    lr locallr = lr.a(a);
    locallr.a(new lq(locallr));
    h = locallr;
  }
  
  public int a(int paramInt, Uri paramUri, boolean paramBoolean)
  {
    s = paramUri;
    return a(paramInt, paramUri, paramBoolean, a.n());
  }
  
  public int a(int paramInt1, Uri paramUri, boolean paramBoolean, int paramInt2)
  {
    boolean bool2 = true;
    boolean bool1 = true;
    if (Log.isLoggable("Mms:app", 2)) {
      fl.a("setAttachment type=%d uri %s", new Object[] { Integer.valueOf(paramInt1), paramUri });
    }
    yw localyw = new yw(a, h);
    if ((paramInt1 == 0) && (d == 4) && (h != null) && (paramUri == null) && (!paramBoolean)) {
      localyw.b();
    }
    z();
    localyw.a(h);
    h.d(paramInt2);
    if (paramBoolean)
    {
      paramInt2 = a(paramInt1, paramUri, localyw);
      if (paramInt2 == 0) {
        d = paramInt1;
      }
      f(true);
      if ((h != null) && (paramInt1 == 1))
      {
        i1 = h.size();
        if (i1 > 0)
        {
          paramUri = h.a(i1 - 1).q();
          if (paramUri != null)
          {
            y();
            paramUri.a(null);
          }
        }
      }
      if (o != null) {
        o.b();
      }
      if ((paramBoolean) || (d != 0) || (paramInt1 != 0)) {
        break label310;
      }
      paramInt1 = android.telephony.SmsMessage.calculateLength(a(), false)[0];
      if (ga.t()) {
        break label277;
      }
      if (paramInt1 <= 1) {
        break label272;
      }
    }
    label272:
    for (paramBoolean = bool1;; paramBoolean = false)
    {
      a(paramBoolean, false);
      return paramInt2;
      paramInt2 = a(paramInt1, paramUri, localyw, paramInt2);
      break;
    }
    label277:
    int i1 = ga.b();
    if ((i1 > 0) && (paramInt1 > i1)) {}
    for (paramBoolean = bool2;; paramBoolean = false)
    {
      a(paramBoolean, false);
      return paramInt2;
    }
    label310:
    if ((paramInt2 == 0) || (h()))
    {
      a(4, h(), false);
      return paramInt2;
    }
    a(6, false, false);
    return paramInt2;
  }
  
  public long a(Context paramContext, Uri paramUri, String[] paramArrayOfString, int paramInt1, int paramInt2)
  {
    m();
    paramContext = e;
    long l1 = paramContext.f();
    new Thread(new hl(this, paramContext, paramUri, MzPduPersister.getPduPersister(a), paramInt1, paramArrayOfString, paramInt2), "WorkingMessage.send Location").start();
    gx.a(l1, paramContext.h());
    k = true;
    return l1;
  }
  
  public long a(String paramString, int paramInt)
  {
    Log.i("WorkingMessage", "send slotId = " + paramInt);
    g(true);
    gr localgr = e;
    long l1 = localgr.f();
    boolean bool;
    if ((x) && (w > System.currentTimeMillis()))
    {
      bool = true;
      if ((!TextUtils.isEmpty(f.toString())) || (!l()) || (h())) {
        break label401;
      }
    }
    label401:
    for (int i1 = 1;; i1 = 0)
    {
      if ((!MmsApp.a) && (f.length() > 0) && (!l()) && (!h())) {
        c = 0;
      }
      Object localObject;
      if (((u()) && (i1 == 0)) || ((MmsApp.a) && (a(localgr, f.toString()))))
      {
        if (ga.i() == null)
        {
          paramString = new NullPointerException("WorkingMessage.send MMS sending failure. mms_config.xml is missing uaProfUrl setting.  uaProfUrl is required for MMS service, but can be absent for SMS.");
          Log.e("WorkingMessage", "WorkingMessage.send MMS sending failure. mms_config.xml is missing uaProfUrl setting.  uaProfUrl is required for MMS service, but can be absent for SMS.", paramString);
          throw paramString;
          bool = false;
          break;
        }
        paramString = i;
        localObject = h;
        MzPduPersister localMzPduPersister = MzPduPersister.getPduPersister(a);
        CharSequence localCharSequence = j;
        if (d == 0)
        {
          bool = true;
          if (Log.isLoggable("Mms:transaction", 2)) {
            fl.a("Send mmsUri: " + paramString, new Object[0]);
          }
          new Thread(new he(this, localgr, localCharSequence, (lr)localObject, paramString, localMzPduPersister, bool, paramInt), "WorkingMessage.send MMS").start();
        }
      }
      for (;;)
      {
        gx.a(l1, localgr.h());
        k = true;
        return l1;
        bool = false;
        break;
        localObject = f.toString();
        if (i1 != 0)
        {
          localObject = k().toString();
          a(null, false);
          a(false);
        }
        new Thread(new hf(this, localgr, (String)localObject, paramString, paramInt, bool), "WorkingMessage.send SMS").start();
      }
    }
  }
  
  public long a(String paramString, Uri paramUri, int paramInt1, int paramInt2)
  {
    m();
    paramString = e;
    long l1 = paramString.f();
    Log.d("WorkingMessage", "sendTalk talk thread_id is " + l1 + ", sendProtocl = " + paramInt1 + ", audioUri is " + paramUri.toString());
    if ((paramInt1 == 1) || (paramInt1 == 2))
    {
      MzPduPersister localMzPduPersister = MzPduPersister.getPduPersister(a);
      new Thread(new hk(this, paramString, a.getString(2131493596), paramUri, localMzPduPersister, paramInt1, paramInt2), "WorkingMessage.send TALK").start();
    }
    gx.a(l1, paramString.h());
    k = true;
    return l1;
  }
  
  public long a(String paramString1, String paramString2, int paramInt)
  {
    Log.i("WorkingMessage", "sendLocation as sms, slotId = " + paramInt);
    m();
    gr localgr = e;
    long l1 = localgr.f();
    new Thread(new hd(this, localgr, paramString1, paramString2, paramInt), "WorkingMessage.send SMS -- location").start();
    gx.a(l1, localgr.h());
    k = true;
    return l1;
  }
  
  public CharSequence a()
  {
    return f;
  }
  
  public lr a(lt paramlt, int paramInt)
  {
    lr locallr = lr.a(a);
    locallr.d(paramInt);
    lq locallq = new lq(locallr);
    locallr.a(locallq);
    if (paramlt != null) {
      locallq.a(paramlt);
    }
    return locallr;
  }
  
  public void a(int paramInt)
  {
    r = paramInt;
    Log.i("WorkingMessage", "mSendProtocl is " + paramInt);
  }
  
  public void a(long paramLong)
  {
    w = paramLong;
  }
  
  public void a(Context paramContext)
  {
    Object localObject2 = null;
    Object localObject1;
    if (u())
    {
      localObject1 = localObject2;
      if (D())
      {
        if (!l()) {
          break label61;
        }
        localObject1 = paramContext.getString(2131493836, new Object[] { j.toString() });
      }
    }
    for (;;)
    {
      wd.a(e.e(), (String)localObject1);
      return;
      label61:
      localObject1 = localObject2;
      if (c != 2)
      {
        localObject1 = paramContext.getString(2131493836, new Object[] { paramContext.getString(2131493835) });
        continue;
        localObject1 = localObject2;
        if (!TextUtils.isEmpty(f.toString())) {
          localObject1 = f.toString();
        }
      }
    }
  }
  
  public void a(Uri paramUri, int paramInt1, boolean paramBoolean1, String paramString1, String paramString2, long paramLong1, long paramLong2, int paramInt2, boolean paramBoolean2)
  {
    if (Log.isLoggable("Mms:transaction", 2)) {
      fl.a("resendMms", new Object[0]);
    }
    new Thread(new hg(this, paramUri, paramInt1, paramBoolean1, paramString1, paramString2, paramLong1, paramLong2, paramInt2, paramBoolean2)).start();
  }
  
  public void a(Bundle paramBundle)
  {
    if (l()) {
      paramBundle.putString("subject", j.toString());
    }
    if (i != null) {
      paramBundle.putParcelable("msg_uri", i);
    }
    while (!d()) {
      return;
    }
    paramBundle.putString("sms_body", f.toString());
  }
  
  public void a(gr paramgr)
  {
    e = paramgr;
  }
  
  public void a(gr paramgr, boolean paramBoolean)
  {
    if ((paramBoolean) && (paramgr.k() == 0)) {
      paramgr.g();
    }
    paramgr.b(false);
  }
  
  public void a(CharSequence paramCharSequence)
  {
    f = paramCharSequence;
    if ((MmsApp.a) && (f != null) && (h()) && (!j())) {
      A();
    }
  }
  
  public void a(CharSequence paramCharSequence, boolean paramBoolean)
  {
    j = paramCharSequence;
    if (!TextUtils.isEmpty(paramCharSequence)) {}
    for (boolean bool = true;; bool = false)
    {
      a(2, bool, paramBoolean);
      return;
    }
  }
  
  public void a(List<String> paramList)
  {
    p = paramList;
    int i1;
    if (paramList != null) {
      i1 = paramList.size();
    }
    switch (i1)
    {
    default: 
      new StringBuilder().append("{...} len=").append(i1).toString();
      return;
    case 1: 
      paramList = (String)paramList.get(0);
      return;
    }
  }
  
  public void a(boolean paramBoolean)
  {
    a(h);
    d = 0;
    h = null;
    if (i != null)
    {
      a(i, null, null);
      i = null;
    }
    if (l()) {
      a(4, false, paramBoolean);
    }
    for (;;)
    {
      if ((paramBoolean) && (o != null))
      {
        o.b();
        o.c();
      }
      return;
      a(6, false, paramBoolean);
    }
  }
  
  public void a(boolean paramBoolean1, boolean paramBoolean2)
  {
    a(8, paramBoolean1, paramBoolean2);
  }
  
  public int b()
  {
    if ((f == null) || (f.length() <= 0)) {
      return 0;
    }
    return f.length();
  }
  
  /* Error */
  public Uri b(boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 80	hb:k	Z
    //   4: ifeq +41 -> 45
    //   7: new 183	java/lang/StringBuilder
    //   10: dup
    //   11: invokespecial 184	java/lang/StringBuilder:<init>	()V
    //   14: ldc_w 1416
    //   17: invokevirtual 190	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   20: aload_0
    //   21: getfield 818	hb:e	Lgr;
    //   24: invokevirtual 517	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   27: ldc_w 1418
    //   30: invokevirtual 190	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: invokevirtual 202	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   36: iconst_0
    //   37: anewarray 4	java/lang/Object
    //   40: invokestatic 1420	fl:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   43: aconst_null
    //   44: areturn
    //   45: aload_0
    //   46: bipush 16
    //   48: iconst_1
    //   49: iload_1
    //   50: invokespecial 997	hb:a	(IZZ)V
    //   53: aload_0
    //   54: iconst_1
    //   55: invokespecial 1310	hb:g	(Z)V
    //   58: invokestatic 582	zt:c	()Lzt;
    //   61: iconst_1
    //   62: invokevirtual 583	zt:a	(Z)V
    //   65: aload_0
    //   66: getfield 818	hb:e	Lgr;
    //   69: invokevirtual 592	gr:h	()Lgq;
    //   72: invokevirtual 1188	gq:isEmpty	()Z
    //   75: ifne +11 -> 86
    //   78: aload_0
    //   79: getfield 818	hb:e	Lgr;
    //   82: invokevirtual 587	gr:f	()J
    //   85: pop2
    //   86: aload_0
    //   87: getfield 818	hb:e	Lgr;
    //   90: iconst_1
    //   91: invokevirtual 1405	gr:b	(Z)V
    //   94: aload_0
    //   95: getfield 86	hb:a	Lcom/android/mms/ui/ComposeMessageActivity;
    //   98: invokestatic 354	com/meizu/android/mms/pdu/MzPduPersister:getPduPersister	(Landroid/content/Context;)Lcom/meizu/android/mms/pdu/MzPduPersister;
    //   101: astore_2
    //   102: aload_0
    //   103: getfield 818	hb:e	Lgr;
    //   106: aload_0
    //   107: getfield 1078	hb:j	Ljava/lang/CharSequence;
    //   110: invokestatic 339	hb:b	(Lgr;Ljava/lang/CharSequence;)Lcom/meizu/android/mms/pdu/MzSendReq;
    //   113: astore_3
    //   114: aload_0
    //   115: getfield 1059	hb:i	Landroid/net/Uri;
    //   118: ifnonnull +40 -> 158
    //   121: aload_0
    //   122: aload_2
    //   123: aload_3
    //   124: aload_0
    //   125: getfield 107	hb:h	Llr;
    //   128: aconst_null
    //   129: aload_0
    //   130: getfield 86	hb:a	Lcom/android/mms/ui/ComposeMessageActivity;
    //   133: aconst_null
    //   134: iconst_0
    //   135: invokestatic 305	hb:b	(Lcom/meizu/android/mms/pdu/MzPduPersister;Lcom/meizu/android/mms/pdu/MzSendReq;Llr;Landroid/net/Uri;Landroid/content/Context;Ljava/util/HashMap;Z)Landroid/net/Uri;
    //   138: putfield 1059	hb:i	Landroid/net/Uri;
    //   141: aload_0
    //   142: iconst_1
    //   143: putfield 376	hb:l	Z
    //   146: invokestatic 582	zt:c	()Lzt;
    //   149: iconst_0
    //   150: invokevirtual 583	zt:a	(Z)V
    //   153: aload_0
    //   154: getfield 1059	hb:i	Landroid/net/Uri;
    //   157: areturn
    //   158: aload_0
    //   159: getfield 1059	hb:i	Landroid/net/Uri;
    //   162: aload_2
    //   163: aload_0
    //   164: getfield 107	hb:h	Llr;
    //   167: aload_3
    //   168: aconst_null
    //   169: iconst_0
    //   170: invokestatic 538	hb:b	(Landroid/net/Uri;Lcom/meizu/android/mms/pdu/MzPduPersister;Llr;Lcom/meizu/android/mms/pdu/MzSendReq;Ljava/util/HashMap;Z)V
    //   173: goto -32 -> 141
    //   176: astore_2
    //   177: invokestatic 582	zt:c	()Lzt;
    //   180: iconst_0
    //   181: invokevirtual 583	zt:a	(Z)V
    //   184: aload_2
    //   185: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	186	0	this	hb
    //   0	186	1	paramBoolean	boolean
    //   101	62	2	localMzPduPersister	MzPduPersister
    //   176	9	2	localObject	Object
    //   113	55	3	localMzSendReq	MzSendReq
    // Exception table:
    //   from	to	target	type
    //   58	86	176	finally
    //   86	141	176	finally
    //   141	146	176	finally
    //   158	173	176	finally
  }
  
  /* Error */
  public void b(Uri paramUri)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc_w 356
    //   5: iconst_2
    //   6: invokestatic 360	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   9: ifeq +13 -> 22
    //   12: ldc_w 1422
    //   15: iconst_0
    //   16: anewarray 4	java/lang/Object
    //   19: invokestatic 367	fl:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   22: aload_0
    //   23: getfield 80	hb:k	Z
    //   26: istore_2
    //   27: iload_2
    //   28: iconst_1
    //   29: if_icmpne +6 -> 35
    //   32: aload_0
    //   33: monitorexit
    //   34: return
    //   35: aload_0
    //   36: iconst_1
    //   37: putfield 80	hb:k	Z
    //   40: aload_0
    //   41: invokespecial 1273	hb:y	()V
    //   44: aload_0
    //   45: getfield 376	hb:l	Z
    //   48: ifeq +18 -> 66
    //   51: aload_0
    //   52: aload_0
    //   53: getfield 818	hb:e	Lgr;
    //   56: invokespecial 1117	hb:e	(Lgr;)V
    //   59: aload_0
    //   60: aload_1
    //   61: aconst_null
    //   62: aconst_null
    //   63: invokespecial 1239	hb:a	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)V
    //   66: aload_0
    //   67: getfield 1005	hb:m	Z
    //   70: ifeq +11 -> 81
    //   73: aload_0
    //   74: aload_0
    //   75: getfield 818	hb:e	Lgr;
    //   78: invokevirtual 1226	hb:b	(Lgr;)V
    //   81: aload_0
    //   82: aload_0
    //   83: getfield 818	hb:e	Lgr;
    //   86: iconst_1
    //   87: invokevirtual 1228	hb:a	(Lgr;Z)V
    //   90: goto -58 -> 32
    //   93: astore_1
    //   94: aload_0
    //   95: monitorexit
    //   96: aload_1
    //   97: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	98	0	this	hb
    //   0	98	1	paramUri	Uri
    //   26	4	2	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   2	22	93	finally
    //   22	27	93	finally
    //   35	66	93	finally
    //   66	81	93	finally
    //   81	90	93	finally
  }
  
  public void b(Bundle paramBundle)
  {
    if (paramBundle == null) {
      return;
    }
    a(paramBundle.getString("subject"), false);
    Uri localUri = (Uri)paramBundle.getParcelable("msg_uri");
    if (localUri != null)
    {
      c(localUri);
      return;
    }
    f = paramBundle.getString("sms_body");
  }
  
  public void b(gr paramgr)
  {
    m = false;
    long l1 = paramgr.e();
    if (l1 > 0L) {
      a(ContentUris.withAppendedId(Telephony.Sms.Conversations.CONTENT_URI, l1), "type=3", null);
    }
  }
  
  public void c(boolean paramBoolean)
  {
    if (k) {
      fl.b("saveDraft mDiscarded: true mConversation: " + e + " skipping saving draft and bailing", new Object[0]);
    }
    do
    {
      return;
      if (e == null) {
        throw new IllegalStateException("saveDraft() called with no conversation");
      }
      if (Log.isLoggable("Mms:app", 2)) {
        fl.a("saveDraft for mConversation " + e, new Object[0]);
      }
      g(false);
      if (!u()) {
        break;
      }
    } while (!D());
    b(e, paramBoolean);
    l = true;
    return;
    String str = f.toString();
    if (!TextUtils.isEmpty(str))
    {
      a(e, str, paramBoolean);
      m = true;
      return;
    }
    e(e);
    i = null;
  }
  
  public boolean c()
  {
    return wd.a(f);
  }
  
  public void d(boolean paramBoolean)
  {
    x = paramBoolean;
  }
  
  public boolean d()
  {
    return (f != null) && (TextUtils.getTrimmedLength(f) > 0);
  }
  
  public Uri e()
  {
    return s;
  }
  
  public void e(boolean paramBoolean)
  {
    y = paramBoolean;
  }
  
  public boolean f()
  {
    if ((d()) || (l()) || (h()) || (j())) {}
    while (g()) {
      return true;
    }
    return false;
  }
  
  public boolean g()
  {
    return (c & 0x10) > 0;
  }
  
  public boolean h()
  {
    return d > 0;
  }
  
  public lr i()
  {
    if (h == null) {
      z();
    }
    return h;
  }
  
  public boolean j()
  {
    return d == 4;
  }
  
  public CharSequence k()
  {
    return j;
  }
  
  public boolean l()
  {
    return (j != null) && (TextUtils.getTrimmedLength(j) > 0);
  }
  
  public void m()
  {
    if (p != null)
    {
      gq localgq = gq.a(p, false);
      e.b(localgq);
      p = null;
    }
  }
  
  public String n()
  {
    if (p == null) {
      return null;
    }
    return gq.a(p, false).a();
  }
  
  public void o()
  {
    a(16, false, false);
  }
  
  /* Error */
  public void p()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc_w 356
    //   5: iconst_2
    //   6: invokestatic 360	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   9: ifeq +13 -> 22
    //   12: ldc_w 1422
    //   15: iconst_0
    //   16: anewarray 4	java/lang/Object
    //   19: invokestatic 367	fl:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   22: aload_0
    //   23: getfield 80	hb:k	Z
    //   26: istore_1
    //   27: iload_1
    //   28: iconst_1
    //   29: if_icmpne +6 -> 35
    //   32: aload_0
    //   33: monitorexit
    //   34: return
    //   35: aload_0
    //   36: iconst_1
    //   37: putfield 80	hb:k	Z
    //   40: aload_0
    //   41: invokespecial 1273	hb:y	()V
    //   44: aload_0
    //   45: getfield 376	hb:l	Z
    //   48: ifeq +11 -> 59
    //   51: aload_0
    //   52: aload_0
    //   53: getfield 818	hb:e	Lgr;
    //   56: invokespecial 1117	hb:e	(Lgr;)V
    //   59: aload_0
    //   60: getfield 1005	hb:m	Z
    //   63: ifeq +11 -> 74
    //   66: aload_0
    //   67: aload_0
    //   68: getfield 818	hb:e	Lgr;
    //   71: invokevirtual 1226	hb:b	(Lgr;)V
    //   74: aload_0
    //   75: aload_0
    //   76: getfield 818	hb:e	Lgr;
    //   79: iconst_1
    //   80: invokevirtual 1228	hb:a	(Lgr;Z)V
    //   83: goto -51 -> 32
    //   86: astore_2
    //   87: aload_0
    //   88: monitorexit
    //   89: aload_2
    //   90: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	91	0	this	hb
    //   26	4	1	bool	boolean
    //   86	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	22	86	finally
    //   22	27	86	finally
    //   35	59	86	finally
    //   59	74	86	finally
    //   74	83	86	finally
  }
  
  public void q()
  {
    k = false;
  }
  
  public boolean r()
  {
    return k;
  }
  
  public void s()
  {
    Log.i("WorkingMessage", "WorkingMessage:");
    C();
    if (e != null) {
      Log.i("WorkingMessage", "mConversation: " + e.toString());
    }
  }
  
  public gr t()
  {
    return e;
  }
  
  public boolean u()
  {
    return c > 0;
  }
  
  public boolean v()
  {
    if ((h == null) || (h.size() != 1)) {}
    while (!h()) {
      return true;
    }
    lq locallq = h.a(0);
    Uri localUri = null;
    if (locallq.e()) {
      localUri = locallq.q().k();
    }
    for (;;)
    {
      return wd.c(a, localUri);
      if (locallq.g()) {
        localUri = locallq.s().k();
      } else if (locallq.f()) {
        localUri = locallq.r().k();
      } else if (locallq.i()) {
        localUri = locallq.t().k();
      } else if (locallq.j()) {
        localUri = locallq.u().k();
      }
    }
  }
  
  public long w()
  {
    return w;
  }
  
  public boolean x()
  {
    return y;
  }
  
  public static abstract interface a
  {
    public abstract void a(int paramInt, boolean paramBoolean);
    
    public abstract void a(boolean paramBoolean);
    
    public abstract void b();
    
    public abstract void b(int paramInt);
    
    public abstract void b(boolean paramBoolean);
    
    public abstract void c();
    
    public abstract void d();
  }
}

/* Location:
 * Qualified Name:     hb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */