import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Handler;
import android.provider.Telephony.Mms.Inbox;
import android.provider.Telephony.Sms.Inbox;
import android.provider.Telephony.Threads;
import android.telephony.MzTelephony.MmsExt;
import android.telephony.MzTelephony.MmsSmsExt;
import android.telephony.MzTelephony.ThreadsExt;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.transaction.MessagingNotification;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class gr
{
  private static Handler I = new Handler();
  public static final Uri a = Telephony.Threads.CONTENT_URI.buildUpon().appendQueryParameter("simple", "true").build();
  public static String[] b = { "_id", "date", "message_count", "recipient_ids", "substr(snippet, 0, 300)", "snippet_cs", "read", "error", "has_attachment", "port", "last_msg_type", "top", "imsi" };
  public static final String[] c = { "_id", "read" };
  public static final String[] d = { "_id", "top" };
  public static ContentValues e;
  public static ContentValues f;
  static final String[] g = { "_id", "thread_id", "address", "body", "date", "read", "type", "status", "locked", "error_code" };
  private static final String[] j = { "seen" };
  private static boolean v;
  private static boolean w = false;
  private static boolean x;
  private static Object y = new Object();
  private boolean A;
  private long B = 0L;
  private CharSequence C;
  private int D = 0;
  private boolean E;
  private String F;
  private Uri G = Uri.parse("content://mms-sms/unread-message-count-selection");
  private boolean H;
  private boolean J = false;
  public int h;
  public boolean i = true;
  private final Context k;
  private long l;
  private gq m;
  private long n;
  private int o;
  private String p;
  private boolean q;
  private int r = 0;
  private boolean s;
  private boolean t;
  private boolean u;
  private boolean z;
  
  private gr(Context paramContext)
  {
    k = paramContext;
    m = new gq();
    l = 0L;
  }
  
  private gr(Context paramContext, long paramLong, boolean paramBoolean)
  {
    k = paramContext;
    if (!a(paramLong, paramBoolean))
    {
      m = new gq();
      l = 0L;
    }
    a(m);
    d(m);
  }
  
  private gr(Context paramContext, Cursor paramCursor, boolean paramBoolean)
  {
    k = paramContext;
    a(paramContext, this, paramCursor, paramBoolean);
    a(m);
    d(m);
  }
  
  private static long a(Context paramContext, gq arg1)
  {
    HashSet localHashSet = new HashSet();
    ??? = ???.iterator();
    while (???.hasNext())
    {
      gm localgm1 = (gm)???.next();
      gm localgm2 = gm.a(localgm1.d(), false);
      if (localgm2 != null) {
        localHashSet.add(localgm2.d());
      } else {
        localHashSet.add(localgm1.d());
      }
    }
    for (;;)
    {
      long l1;
      synchronized (y)
      {
        l1 = System.currentTimeMillis();
        boolean bool = x;
        if (!bool) {}
      }
      try
      {
        y.wait(30000L);
        if (System.currentTimeMillis() - l1 <= 29000L) {
          continue;
        }
        Log.e("Mms/conv", "getOrCreateThreadId timed out waiting for delete to complete", new Exception());
        x = false;
        l1 = ((Long)aau.a(Telephony.Threads.class, "getOrCreateThreadId", new Class[] { Context.class, Set.class }, new Object[] { paramContext, localHashSet })).longValue();
        if (Log.isLoggable("Mms:app", 2)) {
          fl.a("[Conversation] getOrCreateThreadId for (%s) returned %d", new Object[] { localHashSet, Long.valueOf(l1) });
        }
        return l1;
        paramContext = finally;
        throw paramContext;
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;) {}
      }
    }
  }
  
  public static Uri a(long paramLong)
  {
    return ContentUris.withAppendedId(Telephony.Threads.CONTENT_URI, paramLong);
  }
  
  public static gr a(Context paramContext)
  {
    return new gr(paramContext);
  }
  
  public static gr a(Context paramContext, long paramLong, boolean paramBoolean)
  {
    gr localgr = gr.a.a(paramLong);
    if (localgr != null) {
      paramContext = localgr;
    }
    do
    {
      return paramContext;
      localgr = new gr(paramContext, paramLong, paramBoolean);
      try
      {
        gr.a.a(localgr);
        return localgr;
      }
      catch (IllegalStateException paramContext)
      {
        fl.c("Tried to add duplicate Conversation to Cache (from threadId): " + localgr, new Object[0]);
        paramContext = localgr;
      }
    } while (gr.a.b(localgr));
    fl.c("get by threadId cache.replace failed on " + localgr, new Object[0]);
    return localgr;
  }
  
  public static gr a(Context paramContext, Cursor paramCursor)
  {
    long l1 = paramCursor.getLong(0);
    if (l1 > 0L)
    {
      gr localgr = gr.a.a(l1);
      if (localgr != null)
      {
        a(paramContext, localgr, paramCursor, false);
        i = false;
        return localgr;
      }
    }
    paramContext = new gr(paramContext, paramCursor, false);
    try
    {
      gr.a.a(paramContext);
      i = false;
      return paramContext;
    }
    catch (IllegalStateException paramCursor)
    {
      for (;;)
      {
        fl.c("Mms/conv", new Object[] { "Tried to add duplicate Conversation to Cache (from cursor): " + paramContext });
        if (!gr.a.b(paramContext)) {
          fl.c("Converations.from cache.replace failed on " + paramContext, new Object[0]);
        }
      }
    }
  }
  
  public static gr a(Context paramContext, Uri paramUri, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramUri == null) {
      paramUri = a(paramContext);
    }
    gq localgq;
    gr localgr2;
    do
    {
      return paramUri;
      if (paramUri.getPathSegments().size() >= 2) {
        try
        {
          gr localgr1 = a(paramContext, Long.parseLong((String)paramUri.getPathSegments().get(1)), paramBoolean1);
          return localgr1;
        }
        catch (NumberFormatException localNumberFormatException)
        {
          fl.c("Invalid URI: " + paramUri, new Object[0]);
        }
      }
      localgq = gq.a(PhoneNumberUtils.replaceUnicodeDigits(a(paramUri)).replace(',', ';'), paramBoolean1, true);
      if (localgq.size() < 1) {
        return a(paramContext);
      }
      localgr2 = gr.a.a(localgq);
      paramUri = localgr2;
    } while (localgr2 != null);
    paramContext = a(paramContext);
    paramContext.b(localgq);
    return paramContext;
  }
  
  public static gr a(Context paramContext, gq paramgq, boolean paramBoolean)
  {
    Object localObject;
    if (paramgq.size() < 1) {
      localObject = a(paramContext);
    }
    do
    {
      gr localgr;
      do
      {
        return (gr)localObject;
        localgr = gr.a.a(paramgq);
        localObject = localgr;
      } while (localgr != null);
      paramContext = new gr(paramContext, a(paramContext, paramgq), paramBoolean);
      Log.d("Mms/conv", "Conversation.get: created new conversation xxxxxxx");
      if (!paramContext.h().equals(paramgq)) {
        fl.c("Mms/conv", new Object[] { "Conversation.get: new conv's recipients don't match input recpients xxxxxxx" });
      }
      try
      {
        gr.a.a(paramContext);
        return paramContext;
      }
      catch (IllegalStateException paramgq)
      {
        fl.c("Tried to add duplicate Conversation to Cache (from recipients): " + paramContext, new Object[0]);
        localObject = paramContext;
      }
    } while (gr.a.b(paramContext));
    fl.c("get by recipients cache.replace failed on " + paramContext, new Object[0]);
    return paramContext;
  }
  
  public static gr a(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (TextUtils.isEmpty(paramString)) {
      paramString = a(paramContext);
    }
    gq localgq;
    gr localgr;
    do
    {
      return paramString;
      localgq = gq.a(paramString, paramBoolean, true);
      if (localgq.size() < 1) {
        return a(paramContext);
      }
      localgr = gr.a.a(localgq);
      paramString = localgr;
    } while (localgr != null);
    paramContext = a(paramContext);
    paramContext.b(localgq);
    return paramContext;
  }
  
  public static String a(Context paramContext, long paramLong, String paramString)
  {
    if (paramLong <= 0L)
    {
      fl.c("verifySingleRecipient threadId is ZERO, recipient: " + paramString, new Object[0]);
      fl.a(paramContext);
    }
    String str2;
    do
    {
      for (;;)
      {
        return paramString;
        Object localObject = paramContext.getContentResolver().query(a, b, "_id=" + Long.toString(paramLong), null, null);
        if (localObject == null)
        {
          fl.c("verifySingleRecipient threadId: " + paramLong + " resulted in NULL cursor , recipient: " + paramString, new Object[0]);
          fl.a(paramContext);
          return paramString;
        }
        try
        {
          if (!((Cursor)localObject).moveToFirst())
          {
            fl.c("verifySingleRecipient threadId: " + paramLong + " can't moveToFirst , recipient: " + paramString, new Object[0]);
            fl.a(paramContext);
            return paramString;
          }
          String str1 = ((Cursor)localObject).getString(3);
          ((Cursor)localObject).close();
          localObject = str1.split(" ");
          if (localObject.length == 1)
          {
            str2 = gx.a(paramContext, localObject[0]);
            if (TextUtils.isEmpty(str2))
            {
              fl.c("verifySingleRecipient threadId: " + paramLong + " getSingleNumberFromCanonicalAddresses returned empty number for: " + localObject[0] + " recipientIds: " + str1, new Object[0]);
              fl.a(paramContext);
              return paramString;
            }
          }
        }
        finally
        {
          ((Cursor)localObject).close();
        }
      }
    } while (((Boolean)aau.a("android.telephony.PhoneNumberUtils", "compareLoosely", new Class[] { String.class, String.class }, new Object[] { paramString, str2 })).booleanValue());
    if ((paramContext instanceof Activity)) {
      fl.a("verifySingleRecipient for threadId: " + paramLong + " original recipient: " + paramString + " recipient from DB: " + str2, (Activity)paramContext);
    }
    fl.a(paramContext);
    if (Log.isLoggable("Mms:threadcache", 2)) {
      fl.a("verifySingleRecipient for threadId: " + paramLong + " original recipient: " + paramString + " recipient from DB: " + str2, new Object[0]);
    }
    return str2;
  }
  
  private static final String a(Context paramContext, gr paramgr, String paramString)
  {
    int i1 = D / 10000;
    int i2 = D % 10000;
    switch (i1)
    {
    default: 
    case 0: 
    case 1: 
      do
      {
        return paramString;
        if (i2 == 1301) {
          return paramContext.getString(2131493484);
        }
        if ((i2 == 1321) || (i2 == 1281)) {
          return paramContext.getString(2131493596);
        }
        if (i2 == 130) {
          return paramContext.getString(2131493479);
        }
        if ((i2 / 10 == 128) || (i2 / 10 == 132))
        {
          if (TextUtils.isEmpty(paramString)) {
            return paramContext.getString(2131493476);
          }
          return paramContext.getString(2131493473, new Object[] { paramString });
        }
      } while ((i2 != 128) && (i2 != 132));
      if (TextUtils.isEmpty(paramString)) {
        return paramContext.getString(2131493476);
      }
      return paramContext.getString(2131493473, new Object[] { paramString });
    case 2: 
      if (i2 == 1301) {
        return paramContext.getString(2131493484);
      }
      if (i2 / 10 == 130) {
        return paramContext.getString(2131493470);
      }
      if ((i2 == 1321) || (i2 == 1281)) {
        return paramContext.getString(2131493596);
      }
      if ((i2 == 1282) || (i2 == 1322)) {
        return paramContext.getString(2131493474);
      }
      if ((i2 == 1283) || (i2 == 1323)) {
        return paramContext.getString(2131493486);
      }
      if ((i2 == 1284) || (i2 == 1324)) {
        return paramContext.getString(2131493466);
      }
      if ((i2 == 1285) || (i2 == 1325)) {
        return paramContext.getString(2131493485);
      }
      if ((i2 == 1286) || (i2 == 1326)) {
        return paramContext.getString(2131493469);
      }
      if ((i2 == 1287) || (i2 == 1327)) {
        return paramContext.getString(2131493475);
      }
      if (TextUtils.isEmpty(paramString)) {
        return paramContext.getString(2131493477);
      }
      return paramContext.getString(2131493471, new Object[] { paramString });
    }
    if (i2 == 1301) {
      return paramContext.getString(2131493484);
    }
    if (i2 / 10 == 130) {
      return paramContext.getString(2131493467);
    }
    if ((i2 == 1321) || (i2 == 1281)) {
      return paramContext.getString(2131493596);
    }
    if ((i2 == 1282) || (i2 == 1322)) {
      return paramContext.getString(2131493474);
    }
    if ((i2 == 1283) || (i2 == 1323)) {
      return paramContext.getString(2131493486);
    }
    if ((i2 == 1284) || (i2 == 1324)) {
      return paramContext.getString(2131493466);
    }
    if ((i2 == 1285) || (i2 == 1325)) {
      return paramContext.getString(2131493485);
    }
    if ((i2 == 1286) || (i2 == 1326)) {
      return paramContext.getString(2131493469);
    }
    if ((i2 == 1287) || (i2 == 1327)) {
      return paramContext.getString(2131493475);
    }
    return paramContext.getString(2131493477);
  }
  
  public static String a(Uri paramUri)
  {
    paramUri = paramUri.getSchemeSpecificPart();
    int i1 = paramUri.indexOf('?');
    if (i1 == -1) {
      return paramUri;
    }
    return paramUri.substring(0, i1);
  }
  
  public static void a(AsyncQueryHandler paramAsyncQueryHandler, int paramInt)
  {
    paramAsyncQueryHandler.startDelete(paramInt, null, Telephony.Threads.OBSOLETE_THREADS_URI, null, null);
  }
  
  public static void a(AsyncQueryHandler paramAsyncQueryHandler, int paramInt, String paramString)
  {
    paramAsyncQueryHandler.cancelOperation(paramInt);
    if (MmsApp.o())
    {
      b(paramAsyncQueryHandler, paramInt, paramString);
      return;
    }
    String str2 = "type != 266";
    String str1 = str2;
    if (paramString != null)
    {
      str1 = str2;
      if (paramString != "") {
        str1 = "type != 266" + " AND " + paramString;
      }
    }
    b(paramAsyncQueryHandler, paramInt, str1);
  }
  
  private static void a(Context paramContext, long paramLong)
  {
    paramContext = a(paramContext, paramLong, false);
    if (paramContext != null)
    {
      paramContext = paramContext.h().iterator();
      while (paramContext.hasNext()) {
        ((gm)paramContext.next()).a();
      }
    }
  }
  
  private static void a(Context paramContext, gr paramgr, Cursor paramCursor, boolean paramBoolean)
  {
    boolean bool2 = true;
    if (paramCursor.hashCode() != h) {}
    for (;;)
    {
      String str;
      try
      {
        l = paramCursor.getLong(0);
        n = paramCursor.getLong(1);
        o = paramCursor.getInt(2);
        D = paramCursor.getInt(10);
        str = a(paramContext, paramgr, wd.a(paramContext, wd.a(paramCursor, 4, 5)));
        if ((p != null) && (!p.equals(str))) {
          C = null;
        }
        B = paramCursor.getInt(9);
        if (B > 0L)
        {
          p = paramContext.getString(2131493489);
          if (paramCursor.getInt(6) != 0) {
            break label338;
          }
          bool1 = true;
          paramgr.c(bool1);
          if (paramCursor.getInt(11) != 1) {
            break label344;
          }
          bool1 = true;
          paramgr.d(bool1);
          if (D != 0) {
            break label356;
          }
          if (paramCursor.getInt(7) != 5) {
            break label350;
          }
          bool1 = true;
          u = bool1;
          if (paramCursor.getInt(8) == 0) {
            break label385;
          }
          bool1 = bool2;
          t = bool1;
          F = paramCursor.getString(12);
          h = paramCursor.hashCode();
          if (i)
          {
            paramContext = paramCursor.getString(3);
            paramCursor = gq.b(paramContext, paramBoolean);
          }
        }
      }
      finally {}
      label338:
      try
      {
        m = paramCursor;
        if (Log.isLoggable("Mms:threadcache", 2)) {
          Log.d("Mms/conv", "fillFromCursor: conv=" + paramgr + ", recipientIds=" + paramContext);
        }
        return;
      }
      finally {}
      p = str;
      continue;
      boolean bool1 = false;
      continue;
      label344:
      bool1 = false;
      continue;
      label350:
      bool1 = false;
      continue;
      label356:
      int i1 = paramCursor.getInt(7);
      if (i1 >= 10)
      {
        bool1 = true;
      }
      else
      {
        bool1 = false;
        continue;
        label385:
        bool1 = false;
      }
    }
  }
  
  public static void a(Context paramContext, String paramString)
  {
    new Thread(new gt(paramContext, paramString)).start();
  }
  
  public static void a(Context paramContext, Collection<Long> paramCollection)
  {
    if (paramCollection == null) {
      gm.a(paramContext);
    }
    for (;;)
    {
      b(paramContext);
      MessagingNotification.a(paramContext, -2L, false);
      MessagingNotification.e(paramContext);
      return;
      paramCollection = paramCollection.iterator();
      while (paramCollection.hasNext()) {
        a(paramContext, ((Long)paramCollection.next()).longValue());
      }
    }
  }
  
  public static void a(Context paramContext, HashSet<Long> paramHashSet)
  {
    Iterator localIterator = paramHashSet.iterator();
    paramHashSet = null;
    if (localIterator.hasNext())
    {
      Long localLong = (Long)localIterator.next();
      if (paramHashSet == null) {}
      for (paramHashSet = localLong + ";";; paramHashSet = paramHashSet + localLong + ";") {
        break;
      }
    }
    b(paramContext, true, paramHashSet);
  }
  
  public static void a(Context paramContext, boolean paramBoolean)
  {
    paramContext = new Thread(new gv(paramContext, paramBoolean), "Conversation.markAllConversationsAsSeen");
    paramContext.setPriority(1);
    paramContext.start();
  }
  
  public static void a(Context paramContext, boolean paramBoolean, Collection<Long> paramCollection)
  {
    new gw(paramCollection, paramBoolean, paramContext).execute(new Void[0]);
  }
  
  public static void a(gr.b paramb, int paramInt, boolean paramBoolean)
  {
    for (String str = null;; str = "locked=0") {
      synchronized (y)
      {
        if (x) {
          Log.e("Mms/conv", "startDeleteAll already in the middle of a delete", new Exception());
        }
        x = true;
        if (paramBoolean)
        {
          MmsApp localMmsApp = MmsApp.c();
          localMmsApp.e().b();
          localMmsApp.f().b();
          paramb.a(paramInt);
          paramb.startDelete(paramInt, null, Telephony.Threads.CONTENT_URI, str, null);
          return;
        }
      }
    }
  }
  
  public static void a(gr.b paramb, int paramInt, boolean paramBoolean, Collection<Long> paramCollection)
  {
    String str = null;
    for (;;)
    {
      Object localObject1;
      synchronized (y)
      {
        if (x) {
          Log.e("Mms/conv", "startDeleteAll already in the middle of a delete", new Exception());
        }
        x = true;
        Uri localUri = Uri.withAppendedPath(Telephony.Threads.CONTENT_URI, "groupdelete");
        Iterator localIterator = paramCollection.iterator();
        localObject1 = null;
        if (localIterator.hasNext())
        {
          long l1 = ((Long)localIterator.next()).longValue();
          if (l1 <= 0L) {
            continue;
          }
          if (localObject1 == null)
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append(l1);
            ((StringBuilder)localObject1).append(";");
          }
        }
        else
        {
          if (localObject1 == null) {
            break label209;
          }
          localObject1 = ((StringBuilder)localObject1).toString();
          break label202;
          MmsApp.c().e().b();
          MmsApp.c().f().b();
          paramb.a(paramInt);
          paramb.startDelete(paramInt, new HashSet(paramCollection), localUri, str, new String[] { localObject1 });
          return;
        }
      }
      continue;
      for (;;)
      {
        label202:
        if (!paramBoolean) {
          break label215;
        }
        break;
        label209:
        localObject1 = null;
      }
      label215:
      str = "locked=0";
    }
  }
  
  public static void a(gr.b paramb, int paramInt, boolean paramBoolean1, boolean paramBoolean2, String[] paramArrayOfString, Object paramObject)
  {
    if (paramBoolean1) {}
    for (String str = null; !paramBoolean2; str = "locked=0")
    {
      paramb.startDelete(paramInt, paramObject, MzTelephony.MmsSmsExt.CONTENT_MSG_AND_THREAD_MIX_OPERATE_URI, null, paramArrayOfString);
      return;
    }
    synchronized (y)
    {
      if (x) {
        Log.e("Mms/conv", "startDeleteAll already in the middle of a delete", new Exception());
      }
      x = true;
      MmsApp.c().e().b();
      MmsApp.c().f().b();
      paramb.a(paramInt);
      paramb.startDelete(paramInt, paramObject, MzTelephony.MmsSmsExt.CONTENT_MSG_AND_THREAD_MIX_OPERATE_URI, str, paramArrayOfString);
      return;
    }
  }
  
  private boolean a(long paramLong, boolean paramBoolean)
  {
    Cursor localCursor = k.getContentResolver().query(a, b, "_id=" + Long.toString(paramLong), null, null);
    try
    {
      if (localCursor.moveToFirst())
      {
        a(k, this, localCursor, paramBoolean);
        if (paramLong != l) {
          fl.c("loadFromThreadId: fillFromCursor returned differnt thread_id! threadId=" + paramLong + ", mThreadId=" + l, new Object[0]);
        }
        return true;
      }
      fl.c("loadFromThreadId: Can't find thread ID " + paramLong, new Object[0]);
      return false;
    }
    finally
    {
      localCursor.close();
    }
  }
  
  public static boolean a(gr paramgr)
  {
    if ((paramgr == null) || (paramgr.e() <= 0L)) {
      return false;
    }
    try
    {
      gr.a.a(paramgr);
      return true;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      for (;;)
      {
        fl.c("Tried to add duplicate Conversation to Cache (from recipients): " + paramgr, new Object[0]);
        if (!gr.a.b(paramgr)) {
          fl.c("get by recipients cache.replace failed on " + paramgr, new Object[0]);
        }
      }
    }
  }
  
  public static long b(Context paramContext, String paramString)
  {
    for (;;)
    {
      long l1;
      synchronized (y)
      {
        l1 = System.currentTimeMillis();
        boolean bool = x;
        if (!bool) {}
      }
      try
      {
        y.wait(30000L);
        if (System.currentTimeMillis() - l1 <= 29000L) {
          continue;
        }
        Log.e("Mms/conv", "getOrCreateThreadId timed out waiting for delete to complete", new Exception());
        x = false;
        l1 = ((Long)aau.a(Telephony.Threads.class, "getOrCreateThreadId", new Class[] { Context.class, String.class }, new Object[] { paramContext, paramString })).longValue();
        if (Log.isLoggable("Mms:app", 2)) {
          fl.a("[Conversation] getOrCreateThreadId for (%s) returned %d", new Object[] { paramString, Long.valueOf(l1) });
        }
        return l1;
        paramContext = finally;
        throw paramContext;
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;) {}
      }
    }
  }
  
  public static void b()
  {
    if (e == null)
    {
      e = new ContentValues(2);
      e.put("read", Integer.valueOf(1));
      e.put("seen", Integer.valueOf(1));
    }
  }
  
  public static void b(AsyncQueryHandler paramAsyncQueryHandler, int paramInt, String paramString)
  {
    paramAsyncQueryHandler.cancelOperation(paramInt);
    paramAsyncQueryHandler.startQuery(paramInt, null, a, b, paramString, null, "top DESC, date DESC");
  }
  
  public static void b(Context paramContext)
  {
    y();
    paramContext = new Thread(new gu(paramContext), "Conversation.init");
    paramContext.setPriority(1);
    paramContext.start();
  }
  
  private static void b(Context paramContext, boolean paramBoolean, String paramString)
  {
    Log.d("Mms/conv", "markAllThreadAsRead needUpdate = " + paramBoolean);
    if (paramString != null) {
      Log.d("Mms/conv", "markAllThreadAsRead threadIdList.length = " + paramString.length());
    }
    if ((paramBoolean) && (paramString != null) && (paramString.length() > 0))
    {
      ContentValues localContentValues = new ContentValues(3);
      localContentValues.put("read", Integer.valueOf(1));
      localContentValues.put("seen", Integer.valueOf(1));
      localContentValues.put("threadid", paramString);
      Log.d("Mms/conv", "markAsRead: update read/seen for thread uri: " + paramString);
      paramContext.getContentResolver().update(MzTelephony.ThreadsExt.UPDATEREADSTATUS_THREADS_URI, localContentValues, "(read=0 OR seen=0)", null);
    }
    MessagingNotification.c(paramContext);
  }
  
  public static HashSet<gr> c(gq paramgq)
  {
    return gr.a.b(paramgq);
  }
  
  public static void c(AsyncQueryHandler paramAsyncQueryHandler, int paramInt, String paramString)
  {
    paramAsyncQueryHandler.cancelOperation(paramInt);
    paramAsyncQueryHandler.startQuery(paramInt, null, Uri.parse("content://mms-sms/unread-message-count-selection"), null, paramString, null, null);
  }
  
  private static void d(Context paramContext)
  {
    if (Log.isLoggable("Mms:threadcache", 2)) {
      fl.a("[Conversation] cacheAllThreads: begin", new Object[0]);
    }
    HashSet localHashSet;
    synchronized (gr.a.a())
    {
      if (v)
      {
        w = true;
        return;
      }
      v = true;
      localHashSet = new HashSet();
      ??? = paramContext.getContentResolver().query(a, b, null, null, null);
      if (??? == null) {}
    }
    try
    {
      if (!((Cursor)???).moveToNext()) {}
    }
    finally
    {
      for (;;)
      {
        synchronized (gr.a.a())
        {
          if (w) {
            if (??? != null) {
              ((Cursor)???).close();
            }
          }
          synchronized (gr.a.a())
          {
            v = false;
            if (w)
            {
              Log.v("Mms/conv", "[Conversation] cacheAllThreads: restarted");
              w = false;
              d(paramContext);
              return;
              paramContext = finally;
              throw paramContext;
              long l1 = ((Cursor)???).getLong(0);
              localHashSet.add(Long.valueOf(l1));
              synchronized (gr.a.a())
              {
                ??? = gr.a.a(l1);
                if (??? == null)
                {
                  ??? = new gr(paramContext, (Cursor)???, true);
                  try
                  {
                    synchronized (gr.a.a())
                    {
                      gr.a.a((gr)???);
                    }
                    if (gr.a.b((gr)???)) {
                      continue;
                    }
                  }
                  catch (IllegalStateException localIllegalStateException)
                  {
                    fl.c("Tried to add duplicate Conversation to Cache for threadId: " + l1 + " new conv: " + ???, new Object[0]);
                  }
                  fl.c("cacheAllThreads cache.replace failed on " + ???, new Object[0]);
                  continue;
                  paramContext = finally;
                  if (??? != null) {
                    ((Cursor)???).close();
                  }
                }
              }
            }
          }
        }
        synchronized (gr.a.a())
        {
          v = false;
          throw paramContext;
          paramContext = finally;
          throw paramContext;
          paramContext = finally;
          throw paramContext;
          a(paramContext, localIllegalStateException, (Cursor)???, true);
          continue;
          paramContext = finally;
          throw paramContext;
        }
      }
    }
  }
  
  private static void d(Context paramContext, boolean paramBoolean)
  {
    ContentResolver localContentResolver = paramContext.getContentResolver();
    paramContext = localContentResolver.query(Telephony.Sms.Inbox.CONTENT_URI, j, "seen=0", null, null);
    int i1 = 0;
    if (paramContext != null) {}
    try
    {
      i1 = paramContext.getCount();
      paramContext.close();
      if (i1 == 0) {
        return;
      }
    }
    finally
    {
      paramContext.close();
    }
    if (Log.isLoggable("Mms:app", 2)) {
      Log.d("Mms/conv", "mark " + i1 + " SMS msgs as seen");
    }
    ContentValues localContentValues = new ContentValues(1);
    localContentValues.put("seen", Integer.valueOf(1));
    Uri localUri = Telephony.Sms.Inbox.CONTENT_URI;
    if (paramBoolean) {}
    for (paramContext = "seen=0";; paramContext = MessagingNotification.a(true, "seen=0"))
    {
      ((ContentResolver)localObject).update(localUri, localContentValues, paramContext, null);
      return;
    }
  }
  
  private static void e(Context paramContext, boolean paramBoolean)
  {
    ContentResolver localContentResolver = paramContext.getContentResolver();
    paramContext = localContentResolver.query(Telephony.Mms.Inbox.CONTENT_URI, j, "seen=0", null, null);
    if (paramContext != null) {}
    for (;;)
    {
      try
      {
        i1 = paramContext.getCount();
        paramContext.close();
        if (i1 == 0) {
          return;
        }
      }
      finally
      {
        paramContext.close();
      }
      if (Log.isLoggable("Mms:app", 2)) {
        Log.d("Mms/conv", "mark " + i1 + " MMS msgs as seen");
      }
      ContentValues localContentValues = new ContentValues(1);
      localContentValues.put("seen", Integer.valueOf(1));
      Uri localUri = Telephony.Mms.Inbox.CONTENT_URI;
      if (paramBoolean) {}
      for (paramContext = "seen=0";; paramContext = MessagingNotification.a(false, "seen=0"))
      {
        ((ContentResolver)localObject).update(localUri, localContentValues, paramContext, null);
        return;
      }
      int i1 = 0;
    }
  }
  
  public static boolean s()
  {
    synchronized ()
    {
      boolean bool = v;
      return bool;
    }
  }
  
  public static void t()
  {
    fl.a("[Conversation] clearAllThreadsCache: begin", new Object[0]);
    gr.a.a(new HashSet());
    fl.a("[Conversation] cacheAllThreads: finished", new Object[0]);
  }
  
  public static void u() {}
  
  public static void v()
  {
    try
    {
      Iterator localIterator = gr.a.c().values().iterator();
      while (localIterator.hasNext()) {
        nexti = true;
      }
    }
    finally {}
  }
  
  public static void y() {}
  
  public void a(int paramInt)
  {
    try
    {
      o = paramInt;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void a(gq paramgq)
  {
    if ((paramgq == null) || (paramgq.size() != 1))
    {
      E = false;
      return;
    }
    if (MzTelephony.MmsExt.isSnsRecipient(((gm)paramgq.get(0)).d()))
    {
      E = true;
      return;
    }
    E = false;
  }
  
  public void a(CharSequence paramCharSequence)
  {
    try
    {
      C = paramCharSequence;
      return;
    }
    finally
    {
      paramCharSequence = finally;
      throw paramCharSequence;
    }
  }
  
  public void a(String paramString)
  {
    try
    {
      F = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void a(boolean paramBoolean)
  {
    if (Log.isLoggable("Mms:app", 2)) {
      fl.a("blockMarkAsRead: " + paramBoolean, new Object[0]);
    }
    if (paramBoolean != z)
    {
      z = paramBoolean;
      if ((!z) && (A))
      {
        A = false;
        c();
      }
    }
  }
  
  public void a(boolean paramBoolean1, boolean paramBoolean2)
  {
    try
    {
      E = paramBoolean1;
      H = paramBoolean2;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean a()
  {
    try
    {
      boolean bool = E;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void b(gq paramgq)
  {
    try
    {
      if (Log.isLoggable("Mms:app", 2)) {
        Log.d("Mms/conv", "setRecipients before: " + toString());
      }
      m = paramgq;
      l = 0L;
      if (Log.isLoggable("Mms:app", 2)) {
        Log.d("Mms/conv", "setRecipients after: " + toString());
      }
      return;
    }
    finally {}
  }
  
  /* Error */
  public void b(boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 178	gr:l	J
    //   6: lstore_2
    //   7: lload_2
    //   8: lconst_0
    //   9: lcmp
    //   10: ifgt +6 -> 16
    //   13: aload_0
    //   14: monitorexit
    //   15: return
    //   16: invokestatic 881	zt:c	()Lzt;
    //   19: aload_0
    //   20: getfield 178	gr:l	J
    //   23: iload_1
    //   24: invokevirtual 884	zt:a	(JZ)V
    //   27: goto -14 -> 13
    //   30: astore 4
    //   32: aload_0
    //   33: monitorexit
    //   34: aload 4
    //   36: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	37	0	this	gr
    //   0	37	1	paramBoolean	boolean
    //   6	2	2	l1	long
    //   30	5	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	30	finally
    //   16	27	30	finally
  }
  
  public void c()
  {
    if (A) {
      return;
    }
    if (z)
    {
      A = true;
      return;
    }
    new gs(this, d()).execute(new Void[0]);
  }
  
  /* Error */
  public void c(boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_0
    //   3: monitorenter
    //   4: aload_0
    //   5: iload_1
    //   6: putfield 894	gr:q	Z
    //   9: aload_0
    //   10: getfield 894	gr:q	Z
    //   13: istore_1
    //   14: iload_1
    //   15: ifeq +122 -> 137
    //   18: invokestatic 674	com/android/mms/MmsApp:c	()Lcom/android/mms/MmsApp;
    //   21: invokevirtual 895	com/android/mms/MmsApp:getContentResolver	()Landroid/content/ContentResolver;
    //   24: aload_0
    //   25: getfield 165	gr:G	Landroid/net/Uri;
    //   28: aconst_null
    //   29: new 309	java/lang/StringBuilder
    //   32: dup
    //   33: invokespecial 310	java/lang/StringBuilder:<init>	()V
    //   36: ldc_w 897
    //   39: invokevirtual 316	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: aload_0
    //   43: invokevirtual 717	gr:e	()J
    //   46: invokevirtual 443	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   49: invokevirtual 322	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   52: aconst_null
    //   53: aconst_null
    //   54: invokevirtual 438	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   57: astore_2
    //   58: aload_2
    //   59: astore_3
    //   60: aload_3
    //   61: astore_2
    //   62: aload_3
    //   63: invokeinterface 448 1 0
    //   68: pop
    //   69: aload_3
    //   70: astore_2
    //   71: aload_0
    //   72: aload_3
    //   73: iconst_0
    //   74: invokeinterface 568 2 0
    //   79: putfield 153	gr:r	I
    //   82: aload_3
    //   83: ifnull +9 -> 92
    //   86: aload_3
    //   87: invokeinterface 453 1 0
    //   92: aload_0
    //   93: monitorexit
    //   94: return
    //   95: astore 4
    //   97: aconst_null
    //   98: astore_3
    //   99: aload_3
    //   100: astore_2
    //   101: aload 4
    //   103: invokevirtual 900	java/lang/Exception:printStackTrace	()V
    //   106: aload_3
    //   107: ifnull -15 -> 92
    //   110: aload_3
    //   111: invokeinterface 453 1 0
    //   116: goto -24 -> 92
    //   119: astore_2
    //   120: aload_0
    //   121: monitorexit
    //   122: aload_2
    //   123: athrow
    //   124: astore_2
    //   125: aload_3
    //   126: ifnull +9 -> 135
    //   129: aload_3
    //   130: invokeinterface 453 1 0
    //   135: aload_2
    //   136: athrow
    //   137: aload_0
    //   138: iconst_0
    //   139: putfield 153	gr:r	I
    //   142: goto -50 -> 92
    //   145: astore 4
    //   147: aload_2
    //   148: astore_3
    //   149: aload 4
    //   151: astore_2
    //   152: goto -27 -> 125
    //   155: astore 4
    //   157: goto -58 -> 99
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	160	0	this	gr
    //   0	160	1	paramBoolean	boolean
    //   57	44	2	localObject1	Object
    //   119	4	2	localObject2	Object
    //   124	24	2	localObject3	Object
    //   151	1	2	localObject4	Object
    //   1	148	3	localObject5	Object
    //   95	7	4	localException1	Exception
    //   145	5	4	localObject6	Object
    //   155	1	4	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   18	58	95	java/lang/Exception
    //   4	14	119	finally
    //   86	92	119	finally
    //   92	94	119	finally
    //   110	116	119	finally
    //   120	122	119	finally
    //   129	135	119	finally
    //   135	137	119	finally
    //   137	142	119	finally
    //   18	58	124	finally
    //   62	69	145	finally
    //   71	82	145	finally
    //   101	106	145	finally
    //   62	69	155	java/lang/Exception
    //   71	82	155	java/lang/Exception
  }
  
  /* Error */
  public Uri d()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 178	gr:l	J
    //   6: lstore_1
    //   7: lload_1
    //   8: lconst_0
    //   9: lcmp
    //   10: ifgt +9 -> 19
    //   13: aconst_null
    //   14: astore_3
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_3
    //   18: areturn
    //   19: getstatic 61	android/provider/Telephony$Threads:CONTENT_URI	Landroid/net/Uri;
    //   22: aload_0
    //   23: getfield 178	gr:l	J
    //   26: invokestatic 293	android/content/ContentUris:withAppendedId	(Landroid/net/Uri;J)Landroid/net/Uri;
    //   29: astore_3
    //   30: goto -15 -> 15
    //   33: astore_3
    //   34: aload_0
    //   35: monitorexit
    //   36: aload_3
    //   37: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	38	0	this	gr
    //   6	2	1	l1	long
    //   14	16	3	localUri	Uri
    //   33	4	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	33	finally
    //   19	30	33	finally
  }
  
  public void d(gq paramgq)
  {
    if ((paramgq == null) || (paramgq.size() != 1))
    {
      H = false;
      return;
    }
    if ((!MmsApp.a) && (((gm)paramgq.get(0)).d().equals("4007883333")))
    {
      H = true;
      return;
    }
    H = false;
  }
  
  public void d(boolean paramBoolean)
  {
    try
    {
      s = paramBoolean;
      return;
    }
    finally {}
  }
  
  public long e()
  {
    try
    {
      long l1 = l;
      return l1;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void e(boolean paramBoolean)
  {
    try
    {
      J = paramBoolean;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean equals(Object paramObject)
  {
    try
    {
      paramObject = (gr)paramObject;
      bool = m.equals(m);
      return bool;
    }
    catch (ClassCastException paramObject)
    {
      for (;;)
      {
        paramObject = paramObject;
        boolean bool = false;
      }
    }
    finally
    {
      paramObject = finally;
      throw ((Throwable)paramObject);
    }
  }
  
  public long f()
  {
    try
    {
      if (l <= 0L) {
        l = a(k, m);
      }
      long l1 = l;
      return l1;
    }
    finally {}
  }
  
  public void g()
  {
    try
    {
      if (Log.isLoggable("Mms:app", 2)) {
        fl.a("clearThreadId old threadId was: " + l + " now zero", new Object[0]);
      }
      gr.a.b(l);
      l = 0L;
      return;
    }
    finally {}
  }
  
  public gq h()
  {
    try
    {
      gq localgq = m;
      return localgq;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public int hashCode()
  {
    try
    {
      int i1 = m.hashCode();
      return i1;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  public boolean i()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 178	gr:l	J
    //   6: lstore_1
    //   7: lload_1
    //   8: lconst_0
    //   9: lcmp
    //   10: ifgt +9 -> 19
    //   13: iconst_0
    //   14: istore_3
    //   15: aload_0
    //   16: monitorexit
    //   17: iload_3
    //   18: ireturn
    //   19: invokestatic 881	zt:c	()Lzt;
    //   22: aload_0
    //   23: getfield 178	gr:l	J
    //   26: invokevirtual 918	zt:a	(J)Z
    //   29: istore_3
    //   30: goto -15 -> 15
    //   33: astore 4
    //   35: aload_0
    //   36: monitorexit
    //   37: aload 4
    //   39: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	40	0	this	gr
    //   6	2	1	l1	long
    //   14	16	3	bool	boolean
    //   33	5	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	33	finally
    //   19	30	33	finally
  }
  
  public long j()
  {
    try
    {
      long l1 = n;
      return l1;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public int k()
  {
    try
    {
      int i1 = o;
      return i1;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String l()
  {
    try
    {
      String str = p;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public CharSequence m()
  {
    try
    {
      CharSequence localCharSequence = C;
      return localCharSequence;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean n()
  {
    try
    {
      boolean bool = q;
      return bool;
    }
    finally {}
  }
  
  public int o()
  {
    try
    {
      if (q)
      {
        int i1 = r;
        return i1;
      }
      return 0;
    }
    finally {}
  }
  
  public boolean p()
  {
    try
    {
      boolean bool = s;
      return bool;
    }
    finally {}
  }
  
  public boolean q()
  {
    try
    {
      boolean bool = t;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean r()
  {
    try
    {
      boolean bool = u;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String toString()
  {
    try
    {
      String str = String.format("[%s] (tid %d)", new Object[] { m.a(), Long.valueOf(l) });
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  public int w()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic 930	zv:a	Z
    //   5: ifeq +15 -> 20
    //   8: aload_0
    //   9: getfield 595	gr:F	Ljava/lang/String;
    //   12: invokestatic 933	zv:a	(Ljava/lang/String;)I
    //   15: istore_1
    //   16: aload_0
    //   17: monitorexit
    //   18: iload_1
    //   19: ireturn
    //   20: iconst_0
    //   21: istore_1
    //   22: goto -6 -> 16
    //   25: astore_2
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_2
    //   29: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	30	0	this	gr
    //   15	7	1	i1	int
    //   25	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	16	25	finally
  }
  
  public String x()
  {
    try
    {
      String str = F;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean z()
  {
    return H;
  }
  
  static class a
  {
    private static a a = new a();
    private final ConcurrentHashMap<Long, gr> b = new ConcurrentHashMap();
    
    static a a()
    {
      return a;
    }
    
    static gr a(long paramLong)
    {
      gr localgr = (gr)ab.get(Long.valueOf(paramLong));
      if ((localgr != null) && (localgr.e() == paramLong)) {
        return localgr;
      }
      return null;
    }
    
    static gr a(gq paramgq)
    {
      Iterator localIterator = ab.values().iterator();
      while (localIterator.hasNext())
      {
        gr localgr = (gr)localIterator.next();
        if (localgr.h().equals(paramgq)) {
          return localgr;
        }
      }
      return null;
    }
    
    static void a(gm paramgm, HashSet<gr> paramHashSet)
    {
      synchronized (a)
      {
        if (Log.isLoggable("Mms:threadcache", 2)) {
          fl.a("Conversation get with ContactList: " + paramgm, new Object[0]);
        }
        Iterator localIterator = ab.values().iterator();
        while (localIterator.hasNext())
        {
          gr localgr = (gr)localIterator.next();
          if ((localgr.h().contains(paramgm)) && (!paramHashSet.contains(localgr))) {
            paramHashSet.add(localgr);
          }
        }
      }
    }
    
    static void a(gr paramgr)
    {
      if (ab.contains(paramgr)) {
        throw new IllegalStateException("cache already contains " + paramgr + " threadId: " + gr.c(paramgr));
      }
      ab.put(Long.valueOf(paramgr.e()), paramgr);
    }
    
    static void a(Set<Long> paramSet)
    {
      synchronized (a)
      {
        Iterator localIterator = ab.entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          Long localLong = (Long)localEntry.getKey();
          if ((!paramSet.contains(Long.valueOf(((gr)localEntry.getValue()).e()))) || (!paramSet.contains(localLong))) {
            localIterator.remove();
          }
        }
      }
      Log.d("Mms/conv", "after keepOnly() mCache size is " + ab.size());
    }
    
    static HashSet<gr> b(gq paramgq)
    {
      HashSet localHashSet = new HashSet();
      paramgq = paramgq.iterator();
      while (paramgq.hasNext()) {
        a((gm)paramgq.next(), localHashSet);
      }
      return localHashSet;
    }
    
    static void b()
    {
      fl.a("Conversation dumpCache: ", new Object[0]);
      Iterator localIterator = ab.values().iterator();
      while (localIterator.hasNext())
      {
        gr localgr = (gr)localIterator.next();
        fl.a("   conv: " + localgr.toString() + " hash: " + localgr.hashCode(), new Object[0]);
      }
    }
    
    static void b(long paramLong)
    {
      ab.remove(Long.valueOf(paramLong));
    }
    
    static boolean b(gr paramgr)
    {
      if (!ab.contains(paramgr)) {
        return false;
      }
      ab.replace(Long.valueOf(paramgr.e()), paramgr);
      return true;
    }
    
    static ConcurrentHashMap<Long, gr> c()
    {
      synchronized (a)
      {
        ConcurrentHashMap localConcurrentHashMap = ab;
        return localConcurrentHashMap;
      }
    }
  }
  
  public static class b
    extends AsyncQueryHandler
  {
    private int a;
    
    public b(ContentResolver paramContentResolver)
    {
      super();
    }
    
    public void a(int paramInt)
    {
      a = paramInt;
    }
    
    public void onDeleteComplete(int paramInt1, Object arg2, int paramInt2)
    {
      if (paramInt1 == a) {
        synchronized (gr.A())
        {
          gr.f(false);
          gr.A().notifyAll();
          return;
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     gr
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */