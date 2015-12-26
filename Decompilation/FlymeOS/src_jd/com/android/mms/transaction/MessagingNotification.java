package com.android.mms.transaction;

import aaa;
import aau;
import aaw;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Notification;
import android.app.Notification.BigPictureStyle;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Builder;
import android.app.Notification.InboxStyle;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.UserHandle;
import android.os.UserManager;
import android.preference.PreferenceManager;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Mms.Inbox;
import android.provider.Telephony.MmsSms;
import android.provider.Telephony.Sms;
import android.telephony.MzPhoneNumberUtils;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.RemoteViews;
import cj;
import cn.com.xy.sms.util.ParseManager;
import cn.com.xy.sms.util.ParseNotificationManager;
import com.android.mms.MmsApp;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.ui.ConversationList;
import com.android.mms.ui.MessagingPreferenceActivity;
import com.android.mms.ui.NotificationReply;
import com.google.android.mms.MmsException;
import com.meizu.android.mms.pdu.MzEncodedStringValue;
import com.meizu.android.mms.pdu.MzMultimediaMessagePdu;
import com.meizu.android.mms.pdu.MzPduPersister;
import com.meizu.android.mms.util.MzPduCacheEntry;
import ga;
import gm;
import gq;
import gr;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lq;
import lr;
import lu;
import mf;
import ne;
import nz;
import oa;
import ob;
import oc;
import od;
import oe;
import of;
import ol;
import wd;
import zg;
import zv;

public class MessagingNotification
{
  private static final String[] a = { "thread_id", "date", "_id", "sub", "sub_cs", "sim_id", "protocol" };
  private static final String[] b = { "thread_id", "date", "address", "subject", "body", "port", "_id", "sim_id", "protocol" };
  private static final String[] c = { "thread_id" };
  private static final String[] d = { "thread_id" };
  private static final c e = new c(null);
  private static final Uri f = Uri.parse("content://mms-sms/undelivered");
  private static long g;
  private static final Object h = new Object();
  private static Intent i;
  private static Handler j = new Handler();
  private static MzPduPersister k;
  private static Intent l;
  private static Intent m;
  private static Intent n;
  private static Intent o;
  private static boolean p = false;
  private static AlertDialog q;
  private static String r = "";
  private static ol s;
  
  private static int a(lr paramlr)
  {
    int i1 = paramlr.size();
    if (i1 == 0) {}
    do
    {
      return 0;
      if (i1 > 1) {
        return 4;
      }
      paramlr = paramlr.a(0);
      if (paramlr.e())
      {
        if (paramlr.k()) {
          return 8;
        }
        return 1;
      }
      if (paramlr.g()) {
        return 2;
      }
      if (paramlr.f()) {
        return 3;
      }
      if (paramlr.i()) {
        return 5;
      }
    } while (!paramlr.j());
    return 7;
  }
  
  public static long a(Context paramContext, Uri paramUri)
  {
    paramContext = paramContext.getContentResolver().query(paramUri, c, null, null, null);
    if (paramContext == null) {
      return -2L;
    }
    try
    {
      if (paramContext.moveToFirst())
      {
        int i1 = paramContext.getColumnIndex("thread_id");
        if (i1 < 0) {
          return -2L;
        }
        long l1 = paramContext.getLong(i1);
        return l1;
      }
      return -2L;
    }
    finally
    {
      paramContext.close();
    }
  }
  
  public static AlertDialog a(Context paramContext, String paramString1, DialogInterface.OnClickListener paramOnClickListener, String paramString2, View paramView)
  {
    q = new AlertDialog.Builder(paramContext, 2131623988).setTitle(paramString1).setMessage(paramString2).setPositiveButton(2131493166, paramOnClickListener).setNegativeButton(2131493022, null).setView(paramView).create();
    q.getWindow().setType(2010);
    return q;
  }
  
  private static PendingIntent a(Context paramContext, Intent paramIntent, UserHandle paramUserHandle)
  {
    return (PendingIntent)aau.b(PendingIntent.class, null, "getActivityAsUser", new Class[] { Context.class, Integer.TYPE, Intent.class, Integer.TYPE, Bundle.class, UserHandle.class }, new Object[] { paramContext, Integer.valueOf(0), paramIntent, Integer.valueOf(134217728), null, paramUserHandle });
  }
  
  private static PendingIntent a(Context paramContext, UserHandle paramUserHandle)
  {
    Object localObject1 = new ArrayList();
    Object localObject2 = new HashSet(4);
    c(paramContext, (Set)localObject2, (List)localObject1);
    d(paramContext, (Set)localObject2, (List)localObject1);
    if (((List)localObject1).size() == 0)
    {
      gr.a(paramContext, false);
      return null;
    }
    localObject1 = (b[])((List)localObject1).toArray(new b[((List)localObject1).size()]);
    localObject2 = new Intent(paramContext, NotificationReply.class);
    ((Intent)localObject2).putExtra("smsThreadId", 0k);
    ((Intent)localObject2).putExtra("smsMessageBody", localObject1[0].a(paramContext).toString());
    ((Intent)localObject2).putExtra("smsSlotId", 0m);
    ((Intent)localObject2).addFlags(268435456);
    return a(paramContext, (Intent)localObject2, paramUserHandle);
  }
  
  private static Bitmap a(Context paramContext, b paramb)
  {
    paramb = gr.a(paramContext, k, false).h();
    if (paramb.size() == 0) {
      return null;
    }
    Resources localResources = paramContext.getResources();
    return aaa.a(paramContext, paramb, localResources.getDimensionPixelSize(2131559760), localResources.getDimensionPixelSize(2131559759));
  }
  
  private static RemoteViews a(Context paramContext, Bitmap paramBitmap, b paramb, UserHandle paramUserHandle)
  {
    RemoteViews localRemoteViews = new RemoteViews("com.android.mms", 2130968805);
    localRemoteViews.setImageViewBitmap(2131886692, paramBitmap);
    localRemoteViews.setTextViewText(2131886173, e);
    localRemoteViews.setTextViewText(2131886213, paramb.a(paramContext));
    int i2 = zv.a(m, g.d());
    int i1;
    if (i2 != -2)
    {
      if (i2 != 0) {
        break label350;
      }
      i1 = 2130838643;
    }
    for (;;)
    {
      localRemoteViews.setImageViewResource(2131886624, i1);
      localRemoteViews.setViewVisibility(2131886624, 0);
      localRemoteViews.setOnClickPendingIntent(2131886691, a(paramContext, a, paramUserHandle));
      paramBitmap = Integer.TYPE;
      paramb = Integer.TYPE;
      Object localObject = m;
      paramBitmap = (PendingIntent)aau.b(PendingIntent.class, null, "getBroadcastAsUser", new Class[] { Context.class, paramBitmap, Intent.class, paramb, UserHandle.class }, new Object[] { paramContext, Integer.valueOf(0), localObject, Integer.valueOf(0), paramUserHandle });
      paramb = Integer.TYPE;
      localObject = Integer.TYPE;
      Intent localIntent = o;
      paramb = (PendingIntent)aau.b(PendingIntent.class, null, "getBroadcastAsUser", new Class[] { Context.class, paramb, Intent.class, localObject, UserHandle.class }, new Object[] { paramContext, Integer.valueOf(0), localIntent, Integer.valueOf(0), paramUserHandle });
      localRemoteViews.setOnClickPendingIntent(2131886695, paramBitmap);
      localRemoteViews.setOnClickPendingIntent(2131886697, paramb);
      localRemoteViews.setOnClickPendingIntent(2131886699, a(paramContext, paramUserHandle));
      Log.d("Mms:app", "createHeadsUpView() --> slotId = " + i2);
      return localRemoteViews;
      label350:
      if (i2 == 1) {
        i1 = 2130838652;
      } else {
        i1 = 2130837569;
      }
    }
  }
  
  private static final b a(Context paramContext, boolean paramBoolean, String paramString1, String paramString2, String paramString3, long paramLong1, long paramLong2, Bitmap paramBitmap, gm paramgm, int paramInt1, Uri paramUri, int paramInt2)
  {
    Intent localIntent = ComposeMessageActivity.a(paramContext, paramLong1, zv.c(paramInt2));
    localIntent.setFlags(872415232);
    String str = a(paramContext, paramString1, null, null).toString();
    str = str.substring(0, str.length() - 2);
    paramString1 = a(paramContext, paramString1, paramString3, paramString2);
    boolean bool = false;
    if (!MmsApp.d) {
      bool = MzPhoneNumberUtils.isNotificationNumber(paramContext, paramgm.d());
    }
    if ((wd.g(paramContext, paramgm.d())) || (!bool)) {
      p = true;
    }
    return new b(paramBoolean, localIntent, paramString2, paramString3, paramString1, paramLong2, str, paramBitmap, paramgm, paramInt1, paramLong1, paramUri, paramInt2);
  }
  
  protected static CharSequence a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    paramContext = gm.a(paramString1, true).g();
    if (paramContext == null) {}
    for (paramContext = "";; paramContext = paramContext.replace('\n', ' ').replace('\r', ' '))
    {
      paramContext = new StringBuilder(paramContext);
      paramContext.append(':').append(' ');
      int i1 = paramContext.length();
      if (!TextUtils.isEmpty(paramString2))
      {
        paramContext.append(paramString2.replace('\n', ' ').replace('\r', ' '));
        paramContext.append(' ');
      }
      if (!TextUtils.isEmpty(paramString3)) {
        paramContext.append(paramString3.replace('\n', ' ').replace('\r', ' '));
      }
      paramContext = new SpannableString(paramContext.toString());
      paramContext.setSpan(new StyleSpan(1), 0, i1, 33);
      return paramContext;
    }
  }
  
  private static CharSequence a(Context paramContext, ArrayList<b> paramArrayList)
  {
    TextAppearanceSpan localTextAppearanceSpan = new TextAppearanceSpan(paramContext, 2131624181);
    paramContext = paramContext.getString(2131492956);
    SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder();
    int i2 = paramArrayList.size();
    int i1 = 0;
    while (i1 < i2)
    {
      if (i1 > 0) {
        localSpannableStringBuilder.append(paramContext);
      }
      localSpannableStringBuilder.append(getg.g());
      i1 += 1;
    }
    localSpannableStringBuilder.setSpan(localTextAppearanceSpan, 0, localSpannableStringBuilder.length(), 0);
    return localSpannableStringBuilder;
  }
  
  private static String a(String paramString, int paramInt)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    return new MzEncodedStringValue(paramInt, MzPduPersister.getBytes(paramString)).getString();
  }
  
  public static final String a(boolean paramBoolean, String paramString)
  {
    MessagePopupService.b localb = MmsApp.c().p();
    if ((localb == null) || (c != paramBoolean)) {
      return paramString;
    }
    if (paramBoolean) {
      return "(" + paramString + " AND " + "_id" + " != " + d + ")";
    }
    return "(" + paramString + " AND " + "_id" + " != " + d + ")";
  }
  
  private static Map<String, String> a(String paramString1, String paramString2, String paramString3)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("simIndex", paramString1);
    localHashMap.put("msgId", paramString2);
    localHashMap.put("opensms_enable", "true");
    localHashMap.put("ALLOW_VERCODE_MSG", "true");
    localHashMap.put("thread_id", paramString3);
    return localHashMap;
  }
  
  public static void a(long paramLong)
  {
    synchronized (h)
    {
      g = paramLong;
      return;
    }
  }
  
  private static void a(Notification paramNotification, UserHandle paramUserHandle)
  {
    try
    {
      Class localClass = Class.forName("android.app.NotificationManager");
      Object localObject = localClass.getDeclaredMethod("from", new Class[] { Context.class }).invoke(localClass, new Object[] { MmsApp.c().getApplicationContext() });
      localClass.getDeclaredMethod("notifyAsUser", new Class[] { String.class, Integer.TYPE, Notification.class, UserHandle.class }).invoke(localObject, new Object[] { null, Integer.valueOf(123), paramNotification, paramUserHandle });
      return;
    }
    catch (NoSuchMethodException paramNotification)
    {
      paramNotification.printStackTrace();
      return;
    }
    catch (ClassNotFoundException paramNotification)
    {
      paramNotification.printStackTrace();
      return;
    }
    catch (InvocationTargetException paramNotification)
    {
      paramNotification.printStackTrace();
      return;
    }
    catch (IllegalAccessException paramNotification)
    {
      paramNotification.printStackTrace();
    }
  }
  
  public static void a(Context paramContext)
  {
    k = MzPduPersister.getPduPersister(paramContext);
    i = new Intent("com.android.mms.NOTIFICATION_DELETED_ACTION");
    l = new Intent("com.android.mms.NOTIFICATION_MARKASREAD_ACTION");
    m = new Intent("com.android.mms.NOTIFICATION_MARKASREAD_ACTION_HEADS_UP");
    n = new Intent("com.android.mms.NOTIFICATION_DELETED_MSG_ACTION");
    o = new Intent("com.android.mms.NOTIFICATION_DELETED_MSG_ACTION_HEADS_UP");
  }
  
  public static void a(Context paramContext, int paramInt)
  {
    paramContext = (NotificationManager)paramContext.getSystemService("notification");
    Log.d("Mms:app", "cancelNotification -> notificationId : " + paramInt);
    paramContext.cancel(paramInt);
  }
  
  public static void a(Context paramContext, long paramLong)
  {
    if (ga.C())
    {
      a(paramContext, paramLong, false);
      e(paramContext);
      f(paramContext);
    }
  }
  
  public static void a(Context paramContext, long paramLong, boolean paramBoolean)
  {
    a(paramContext, paramLong, paramBoolean, true);
  }
  
  public static void a(Context paramContext, long paramLong, boolean paramBoolean1, Uri paramUri, boolean paramBoolean2)
  {
    Log.d("Mms:app", "blockingUpdateNewMessageIndicator -> newMsgThreadId : " + paramLong + ", isStatusMessage : " + paramBoolean1);
    ArrayList localArrayList;
    HashSet localHashSet;
    if (!paramBoolean1)
    {
      localArrayList = new ArrayList();
      localHashSet = new HashSet(4);
      c(paramContext, localHashSet, localArrayList);
      d(paramContext, localHashSet, localArrayList);
      Collections.sort(localArrayList, e);
      if (localArrayList.isEmpty())
      {
        Log.d("Mms:app", "blockingUpdateNewMessageIndicator -> notificationSet is Empty : newMsgThreadId = " + paramLong);
        a(paramContext, 123);
        localHashSet.clear();
      }
    }
    else
    {
      paramUri = c(paramContext, paramUri);
      if (paramUri != null) {
        paramUri.a(paramContext, paramBoolean1);
      }
      return;
    }
    Log.d("Mms:app", "blockingUpdateNewMessageIndicator -> count = " + localArrayList.size() + " newMsgThreadId : " + paramLong + ", sCurrentlyDisplayedThreadId = " + g + ", threads.contains(newMsgThreadId) = " + localHashSet.contains(Long.valueOf(paramLong)));
    if (paramBoolean2) {
      b(paramContext);
    }
    Object localObject = h;
    if (paramLong > 0L) {
      try
      {
        if ((paramLong == g) && (localHashSet.contains(Long.valueOf(paramLong))))
        {
          a(paramContext, Uri.parse("file:///system/media/audio/ui/msgNotifyIncompose.wav"), 0.25F);
          return;
        }
      }
      finally {}
    }
    if (paramLong != -2L) {}
    for (boolean bool = true;; bool = false)
    {
      try
      {
        a(paramContext, bool, localHashSet.size(), localArrayList, paramBoolean2);
      }
      catch (Exception localException)
      {
        Log.d("Mms:app", "throw Exception in updateNotification()");
      }
      break;
    }
  }
  
  public static void a(Context paramContext, long paramLong, boolean paramBoolean1, Uri paramUri, boolean paramBoolean2, boolean paramBoolean3)
  {
    a(paramContext, paramLong, paramBoolean1, paramUri, paramBoolean2, paramBoolean3, null);
  }
  
  public static void a(Context paramContext, long paramLong, boolean paramBoolean1, Uri paramUri, boolean paramBoolean2, boolean paramBoolean3, String paramString)
  {
    if (paramBoolean2)
    {
      a(paramContext, paramLong, paramBoolean1, null, true);
      return;
    }
    try
    {
      l1 = Long.parseLong(paramUri.getLastPathSegment());
      if ((paramBoolean1) || (l1 <= 0L) || (paramLong <= 0L))
      {
        a(paramContext, paramLong, paramBoolean1, null, true);
        return;
      }
    }
    catch (Exception paramUri)
    {
      for (;;)
      {
        long l1 = 0L;
      }
      a(paramContext, paramLong, paramBoolean1, null, true);
    }
  }
  
  public static void a(Context paramContext, long paramLong, boolean paramBoolean1, boolean paramBoolean2)
  {
    new Thread(new oa(paramContext, paramLong, paramBoolean1, paramBoolean2), "MessagingNotification.nonBlockingUpdateNewMessageIndicator").start();
  }
  
  public static void a(Context paramContext, Uri paramUri, float paramFloat)
  {
    Log.d("Mms:app", "playInConversationNotificationSound...ringtoneUri = " + paramUri);
    if (s == null) {
      s = new ol("Mms:app");
    }
    s.a(paramContext, paramUri, false, 5, paramFloat);
  }
  
  public static void a(Context paramContext, boolean paramBoolean)
  {
    a(paramContext, false, 0L, paramBoolean);
  }
  
  private static void a(Context paramContext, boolean paramBoolean1, int paramInt, List<b> paramList, boolean paramBoolean2)
  {
    Log.d("Mms:app", "updateNotification -> isNew : " + paramBoolean1 + ", uniqueThreadCount : " + paramInt);
    int i3 = paramList.size();
    b localb = (b)paramList.get(0);
    boolean bool2 = PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("pref_key_enable_notifications", true);
    Object localObject3 = new Notification.Builder(paramContext).setWhen(d);
    if ((paramBoolean1) && (bool2)) {
      ((Notification.Builder)localObject3).setTicker(c);
    }
    Object localObject4 = TaskStackBuilder.create(paramContext);
    Bitmap localBitmap = a(paramContext, localb);
    ne.a(new BitmapDrawable(localBitmap));
    boolean bool1 = a();
    UserHandle localUserHandle;
    Object localObject1;
    Object localObject2;
    label259:
    label274:
    int i2;
    int i1;
    Object localObject5;
    if (bool1)
    {
      localUserHandle = (UserHandle)aau.a(UserHandle.class, "OWNER");
      if (paramInt <= 1) {
        break label934;
      }
      localObject1 = new Intent("android.intent.action.MAIN");
      ((Intent)localObject1).setFlags(872415232);
      ((Intent)localObject1).setType("vnd.android-dir/mms-sms");
      ((TaskStackBuilder)localObject4).addNextIntent((Intent)localObject1);
      localObject2 = paramContext.getString(2131492888) + " " + i3;
      localObject1 = MmsApp.a(paramContext, 2130903040);
      if (!(localObject1 instanceof BitmapDrawable)) {
        break label916;
      }
      localObject1 = (BitmapDrawable)localObject1;
      ((Notification.Builder)localObject3).setLargeIcon(((BitmapDrawable)localObject1).getBitmap());
      localObject1 = localObject2;
      ((Notification.Builder)localObject3).setSmallIcon(2130838482);
      localObject2 = (NotificationManager)paramContext.getSystemService("notification");
      localObject4 = (PendingIntent)aau.b(TaskStackBuilder.class, localObject4, "getPendingIntent", new Class[] { Integer.TYPE, Integer.TYPE, Bundle.class, UserHandle.class }, new Object[] { Integer.valueOf(0), Integer.valueOf(134217728), null, localUserHandle });
      ((Notification.Builder)localObject3).setContentTitle((CharSequence)localObject1).setContentIntent((PendingIntent)localObject4).setCategory("msg").setPriority(2);
      i2 = 0;
      i1 = i2;
      if (paramBoolean1)
      {
        i1 = i2;
        if (paramBoolean2)
        {
          i1 = 2;
          ((Notification.Builder)localObject3).setSound(wd.k(paramContext));
          Log.d("Mms:app", "updateNotification: new message, adding sound to the notification");
        }
      }
      localObject1 = Integer.TYPE;
      localObject4 = Integer.TYPE;
      localObject5 = l;
      localObject1 = (PendingIntent)aau.b(PendingIntent.class, null, "getBroadcastAsUser", new Class[] { Context.class, localObject1, Intent.class, localObject4, UserHandle.class }, new Object[] { paramContext, Integer.valueOf(0), localObject5, Integer.valueOf(0), localUserHandle });
      ((Notification.Builder)localObject3).addAction(0, paramContext.getString(2131493616), (PendingIntent)localObject1);
      localObject1 = Integer.TYPE;
      localObject4 = Integer.TYPE;
      localObject5 = n;
      localObject1 = (PendingIntent)aau.b(PendingIntent.class, null, "getBroadcastAsUser", new Class[] { Context.class, localObject1, Intent.class, localObject4, UserHandle.class }, new Object[] { paramContext, Integer.valueOf(0), localObject5, Integer.valueOf(0), localUserHandle });
      ((Notification.Builder)localObject3).addAction(0, paramContext.getString(2131493281), (PendingIntent)localObject1);
      if ((i3 == 1) || (paramInt == 1)) {
        ((Notification.Builder)localObject3).addAction(0, paramContext.getString(2131493783), a(paramContext, localUserHandle));
      }
      if (!paramBoolean1) {
        break label1527;
      }
      i1 |= 0x4;
    }
    label800:
    label824:
    label916:
    label934:
    label1514:
    label1522:
    label1527:
    for (;;)
    {
      aau.a(Notification.Builder.class, localObject3, "mFlymeNotificationBuilder", "setInternalApp", Integer.TYPE, Integer.valueOf(1));
      ((Notification.Builder)localObject3).setAutoCancel(true);
      Log.d("Mms:app", "notification.defaults is " + i1);
      ((Notification.Builder)localObject3).setDefaults(i1);
      ((Notification.Builder)localObject3).setDeleteIntent(PendingIntent.getBroadcast(paramContext, 0, i, 0));
      if (!bool2)
      {
        ((Notification.Builder)localObject3).setPriority(0);
        if (paramInt == 1)
        {
          paramList = paramContext.getString(2131493770, new Object[] { Integer.valueOf(i3) });
          ((Notification.Builder)localObject3).setContentText(paramList);
          paramList = new Notification.BigTextStyle((Notification.Builder)localObject3).bigText(paramList).build();
          if ((!bool2) || (!h)) {
            break label1522;
          }
        }
      }
      for (paramBoolean1 = a(paramContext, localb, paramList, i3);; paramBoolean1 = false)
      {
        if (!paramBoolean1) {
          headsUpContentView = a(paramContext, localBitmap, localb, localUserHandle);
        }
        if (p) {}
        for (paramInt = 0;; paramInt = 1)
        {
          MmsApp.a(paramInt);
          p = false;
          if (!bool1) {
            break label1514;
          }
          ((NotificationManager)localObject2).notify(123, paramList);
          return;
          localUserHandle = new mf(MmsApp.c().getApplicationContext()).b();
          break;
          localObject1 = (BitmapDrawable)paramContext.getResources().getDrawable(2130903040);
          break label259;
          wd.h(g.d());
          localObject1 = e;
          if (localBitmap != null) {
            ((Notification.Builder)localObject3).setLargeIcon(localBitmap);
          }
          ((TaskStackBuilder)localObject4).addParentStack(ComposeMessageActivity.class);
          ((TaskStackBuilder)localObject4).addNextIntent(a);
          break label274;
          paramList = paramContext.getString(2131493500, new Object[] { Integer.valueOf(paramInt), Integer.valueOf(i3) });
          break label800;
          if (i3 == 1)
          {
            ((Notification.Builder)localObject3).setContentText(localb.a(paramContext));
            aau.a(Notification.Builder.class, localObject3, "mFlymeNotificationBuilder", "setSimSlot", Integer.TYPE, Integer.valueOf(zv.a(m, g.d())));
            if (f != null)
            {
              paramList = new Notification.BigPictureStyle((Notification.Builder)localObject3).bigPicture(f).setSummaryText(localb.c(paramContext)).build();
              break label824;
            }
            paramList = new Notification.BigTextStyle((Notification.Builder)localObject3).bigText(localb.a(paramContext)).build();
            break label824;
          }
          if (paramInt == 1)
          {
            localObject1 = new SpannableStringBuilder();
            paramList = (b[])paramList.toArray(new b[i3]);
            ((SpannableStringBuilder)localObject1).append(paramList[0].a(paramContext));
            ((Notification.Builder)localObject3).setContentText((CharSequence)localObject1);
            aau.a(Notification.Builder.class, localObject3, "mFlymeNotificationBuilder", "setSubTitle", CharSequence.class, String.valueOf(i3));
            aau.a(Notification.Builder.class, localObject3, "mFlymeNotificationBuilder", "setSimSlot", Integer.TYPE, Integer.valueOf(zv.a(0m, 0g.d())));
            paramList = new Notification.BigTextStyle((Notification.Builder)localObject3).bigText((CharSequence)localObject1).build();
            break label824;
          }
          localObject1 = new HashSet(i3);
          localObject4 = new ArrayList();
          paramList = paramList.iterator();
          while (paramList.hasNext())
          {
            localObject5 = (b)paramList.next();
            if (!((HashSet)localObject1).contains(Long.valueOf(k)))
            {
              ((HashSet)localObject1).add(Long.valueOf(k));
              ((ArrayList)localObject4).add(localObject5);
            }
          }
          ((Notification.Builder)localObject3).setContentText(a(paramContext, (ArrayList)localObject4));
          paramList = new Notification.InboxStyle((Notification.Builder)localObject3);
          i1 = Math.min(8, ((ArrayList)localObject4).size());
          paramInt = 0;
          while (paramInt < i1)
          {
            localObject3 = (b)((ArrayList)localObject4).get(paramInt);
            localObject5 = Integer.TYPE;
            CharSequence localCharSequence = ((b)localObject3).b(paramContext);
            i2 = zv.a(m, g.d());
            aau.a(Notification.InboxStyle.class, paramList, "addLine", new Class[] { CharSequence.class, localObject5 }, new Object[] { localCharSequence, Integer.valueOf(i2) });
            paramInt += 1;
          }
          paramList = paramList.build();
          ((HashSet)localObject1).clear();
          ((ArrayList)localObject4).clear();
          break label824;
        }
        a(paramList, b());
        return;
      }
    }
  }
  
  private static void a(Context paramContext, boolean paramBoolean1, long paramLong, boolean paramBoolean2)
  {
    Log.d("Mms:app", "MessagingNotification -> notifyFailed ...");
    if (!MessagingPreferenceActivity.a(paramContext)) {}
    int i2;
    do
    {
      return;
      localObject4 = new long[2];
      Object tmp25_23 = localObject4;
      tmp25_23[0] = 0L;
      Object tmp29_25 = tmp25_23;
      tmp29_25[1] = 1L;
      tmp29_25;
      i2 = b(paramContext, (long[])localObject4);
    } while ((i2 == 0) && (!paramBoolean1));
    int i1;
    Notification.Builder localBuilder;
    Object localObject1;
    Object localObject2;
    Object localObject3;
    if ((localObject4[1] != 0L) || (paramBoolean1))
    {
      i1 = 1;
      localBuilder = new Notification.Builder(paramContext);
      if (i2 <= 1) {
        break label311;
      }
      localObject1 = paramContext.getString(2131493026, new Object[] { Integer.toString(i2) });
      localObject2 = paramContext.getString(2131493027);
      TaskStackBuilder.create(paramContext);
      if (i1 == 0) {
        break label374;
      }
      localObject3 = new Intent(paramContext, ComposeMessageActivity.class);
      if (!paramBoolean1) {
        break label356;
      }
      ((Intent)localObject3).putExtra("failed_download_flag", true);
      label150:
      ((Intent)localObject3).putExtra("thread_id", paramLong);
      ((Intent)localObject3).putExtra("init_imsi_from_converation", true);
      label169:
      ((Intent)localObject3).setFlags(335544320);
      if (!a()) {
        break label390;
      }
    }
    label311:
    label356:
    label374:
    label390:
    for (Object localObject4 = (UserHandle)aau.a(UserHandle.class, "OWNER");; localObject4 = new mf(MmsApp.c().getApplicationContext()).b())
    {
      localObject3 = a(paramContext, (Intent)localObject3, (UserHandle)localObject4);
      aau.a(Notification.Builder.class, localBuilder, "mFlymeNotificationBuilder", "mNotificationIcon", "mInternalApp", 2130838560, 1);
      localBuilder.setContentTitle((CharSequence)localObject2);
      localBuilder.setTicker((CharSequence)localObject2);
      localBuilder.setContentIntent((PendingIntent)localObject3);
      localBuilder.setSmallIcon(2130838483);
      localObject1 = new Notification.InboxStyle(localBuilder).addLine((CharSequence)localObject1).build();
      paramContext = (NotificationManager)paramContext.getSystemService("notification");
      if (!paramBoolean1) {
        break label411;
      }
      paramContext.notify(531, (Notification)localObject1);
      return;
      i1 = 0;
      break;
      if (paramBoolean1) {}
      for (localObject1 = paramContext.getString(2131493004);; localObject1 = paramContext.getString(2131493009))
      {
        localObject3 = paramContext.getString(2131493005);
        localObject2 = localObject1;
        localObject1 = localObject3;
        break;
      }
      paramLong = localObject4[0];
      ((Intent)localObject3).putExtra("undelivered_flag", true);
      break label150;
      localObject3 = new Intent(paramContext, ConversationList.class);
      break label169;
    }
    label411:
    paramContext.notify(789, (Notification)localObject1);
  }
  
  public static void a(Context paramContext, b[] paramArrayOfb)
  {
    ContentValues localContentValues = new ContentValues(2);
    localContentValues.put("read", Integer.valueOf(1));
    localContentValues.put("seen", Integer.valueOf(1));
    int i1 = 0;
    if (i1 < paramArrayOfb.length)
    {
      Uri localUri = l;
      if (localUri == null) {}
      for (;;)
      {
        i1 += 1;
        break;
        paramContext.getContentResolver().update(localUri, localContentValues, null, null);
      }
    }
  }
  
  public static void a(String paramString)
  {
    r = paramString;
  }
  
  private static boolean a()
  {
    Object localObject2 = new mf(MmsApp.c().getApplicationContext());
    boolean bool = ((mf)localObject2).a();
    Object localObject1 = (UserManager)MmsApp.c().getApplicationContext().getSystemService("user");
    localObject2 = ((mf)localObject2).b();
    try
    {
      localObject1 = ((UserManager)localObject1).getUserRestrictions((UserHandle)localObject2);
      Log.d("Mms:app", "isGuestMode = " + bool + ", defaultGuestRestrictions.getBoolean(UserManager.DISALLOW_SMS) = " + ((Bundle)localObject1).getBoolean("no_sms"));
      if ((bool) && (((Bundle)localObject1).getBoolean("no_sms")))
      {
        Log.d("Mms:app", "isGuestDisAllowSMS, skip SMS send operation!");
        return true;
      }
    }
    catch (Exception localException)
    {
      Log.d("Mms:app", "isGuestDisAllowSMS, Exception happened when getUserRestrictions");
      return false;
    }
    return false;
  }
  
  private static boolean a(Context paramContext, b paramb, Notification paramNotification, int paramInt)
  {
    boolean bool2 = false;
    String str1 = g.d();
    String str2 = b;
    long l1 = Long.parseLong(l.getLastPathSegment());
    boolean bool1 = ParseManager.isInitData();
    if (!bool1) {
      aaw.a(MmsApp.c(), true);
    }
    Log.d("Mms:app", "xyParseNotification() --> mSmsCenterNum = " + r + ", msgId = " + l1 + ", isInited = " + bool1);
    Map localMap = ParseNotificationManager.parseNotificationMsg(paramContext, l1, str1, r, str2, a(m + "", l1 + "", k + ""));
    bool1 = bool2;
    if (localMap != null)
    {
      bool1 = bool2;
      if (localMap.containsKey("Result")) {
        bool1 = ((Boolean)localMap.get("Result")).booleanValue();
      }
    }
    Log.d("Mms:app", "xyParseNotification() --> xyNotiReturn1 = " + bool1);
    bool2 = bool1;
    RemoteViews localRemoteViews;
    if (bool1)
    {
      int i1 = zv.a(m, g.d());
      String str3 = String.valueOf(k);
      headsUpContentView = cj.a(paramContext, l1, str1, str2, d, localMap, a(String.valueOf(i1), l1 + "", str3));
      if (headsUpContentView == null) {
        bool1 = false;
      }
      bool2 = bool1;
      if (paramInt == 1)
      {
        bool2 = bool1;
        if (bool1)
        {
          localRemoteViews = cj.c(paramContext, l1, str1, str2, d, localMap, a(String.valueOf(i1), l1 + "", str3));
          paramContext = cj.b(paramContext, l1, str1, str2, d, localMap, a(String.valueOf(i1), l1 + "", str3));
          if ((localRemoteViews != null) && (paramContext != null)) {
            break label502;
          }
        }
      }
    }
    for (bool2 = false;; bool2 = bool1)
    {
      Log.d("Mms:app", "xyParseNotification() --> xyNotiReturn2 = " + bool2);
      return bool2;
      label502:
      bigContentView = localRemoteViews;
      contentView = paramContext;
    }
  }
  
  public static boolean a(Intent paramIntent)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramIntent != null)
    {
      bool1 = bool2;
      if (paramIntent.getBooleanExtra("undelivered_flag", false)) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private static int b(Context paramContext, long[] paramArrayOfLong)
  {
    paramContext = paramContext.getContentResolver().query(f, d, "read=0", null, null);
    if (paramContext == null) {
      return 0;
    }
    int i1 = paramContext.getCount();
    if (paramArrayOfLong != null) {}
    try
    {
      if (paramContext.moveToFirst())
      {
        paramArrayOfLong[0] = paramContext.getLong(0);
        if (paramArrayOfLong.length >= 2)
        {
          long l2 = paramArrayOfLong[0];
          do
          {
            l1 = l2;
            if (!paramContext.moveToNext()) {
              break;
            }
          } while (paramContext.getLong(0) == l2);
          long l1 = 0L;
          paramArrayOfLong[1] = l1;
        }
      }
      return i1;
    }
    finally
    {
      paramContext.close();
    }
  }
  
  public static long b(Context paramContext, Uri paramUri)
  {
    paramContext = paramContext.getContentResolver().query(paramUri, d, null, null, null);
    if (paramContext == null) {
      return -2L;
    }
    try
    {
      if (paramContext.moveToFirst())
      {
        int i1 = paramContext.getColumnIndex("thread_id");
        if (i1 < 0) {
          return -2L;
        }
        long l1 = paramContext.getLong(i1);
        return l1;
      }
      return -2L;
    }
    finally
    {
      paramContext.close();
    }
  }
  
  private static UserHandle b()
  {
    try
    {
      Object localObject = Class.forName("android.os.UserHandle");
      localObject = (UserHandle)((Class)localObject).getDeclaredField("CURRENT").get(localObject);
      return (UserHandle)localObject;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      localClassNotFoundException.printStackTrace();
      return null;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      for (;;)
      {
        localNoSuchFieldException.printStackTrace();
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      for (;;)
      {
        localIllegalAccessException.printStackTrace();
      }
    }
  }
  
  public static void b(Context paramContext)
  {
    Log.d("Mms:app", "stopInConversationNotificationSound...");
    if (s != null) {
      s.a();
    }
  }
  
  public static void b(Context paramContext, int paramInt)
  {
    new Thread(new oe(paramContext, paramInt), "MessagingNotification.markUnSeenMsgAsRead").start();
  }
  
  public static void b(Context paramContext, long paramLong)
  {
    a(paramContext, true, paramLong, false);
  }
  
  public static void b(Context paramContext, long paramLong, boolean paramBoolean1, Uri paramUri, boolean paramBoolean2, boolean paramBoolean3)
  {
    new Thread(new nz(paramContext, paramLong, paramBoolean1, paramUri, paramBoolean2, paramBoolean3), "MessagingNotification.nonBlockingUpdateNewMessageIndicator").start();
  }
  
  public static void b(Context paramContext, long paramLong, boolean paramBoolean1, boolean paramBoolean2)
  {
    a(paramContext, paramLong, paramBoolean1, null, paramBoolean2);
  }
  
  private static void b(Context paramContext, boolean paramBoolean, CharSequence paramCharSequence, long paramLong)
  {
    if (!paramBoolean) {
      return;
    }
    j.post(new ob(paramCharSequence, paramContext));
  }
  
  public static void b(Context paramContext, b[] paramArrayOfb)
  {
    Object localObject = new StringBuilder();
    int i1 = 0;
    while (i1 < paramArrayOfb.length)
    {
      ((StringBuilder)localObject).append(l).append(";");
      i1 += 1;
    }
    paramArrayOfb = Uri.withAppendedPath(Telephony.MmsSms.CONTENT_URI, "messages_group_delete");
    localObject = ((StringBuilder)localObject).substring(0, ((StringBuilder)localObject).length() - 1);
    paramContext.getContentResolver().delete(paramArrayOfb, null, new String[] { localObject });
  }
  
  public static boolean b(Intent paramIntent)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramIntent != null)
    {
      bool1 = bool2;
      if (paramIntent.getBooleanExtra("failed_download_flag", false)) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private static final a c(Context paramContext, Uri paramUri)
  {
    Object localObject = paramContext.getContentResolver();
    if (paramUri == null) {
      localObject = ((ContentResolver)localObject).query(Telephony.Sms.CONTENT_URI, b, "(type = 2 AND status = 0)", null, "report_date desc");
    }
    while (localObject == null)
    {
      Log.d("Mms:app", "cursor == null getSmsNewDeliveryInfo is " + paramUri);
      return null;
      localObject = paramContext.getContentResolver().query(paramUri, b, "(type = 2 AND status = 0)", null, null);
      Log.d("Mms:app", "getSmsNewDeliveryInfo is " + paramUri);
    }
    try
    {
      boolean bool = ((Cursor)localObject).moveToFirst();
      if (!bool) {
        return null;
      }
      String str = ((Cursor)localObject).getString(2);
      paramUri = str;
      if (!TextUtils.isEmpty(str))
      {
        gm localgm = gm.a(str, true);
        paramUri = str;
        if (localgm != null)
        {
          paramUri = str;
          if (localgm.k())
          {
            str = localgm.g();
            paramUri = str;
            if (str.length() < 1) {
              paramUri = localgm.d();
            }
          }
        }
      }
      paramContext = new a(String.format(paramContext.getString(2131492937), new Object[] { paramUri }), 3000L);
      return paramContext;
    }
    finally
    {
      ((Cursor)localObject).close();
    }
  }
  
  public static void c(Context paramContext)
  {
    if (ga.C())
    {
      a(paramContext, -2L, false);
      e(paramContext);
      f(paramContext);
    }
  }
  
  public static void c(Context paramContext, long paramLong)
  {
    long[] arrayOfLong = new long[2];
    long[] tmp5_4 = arrayOfLong;
    tmp5_4[0] = 0L;
    long[] tmp9_5 = tmp5_4;
    tmp9_5[1] = 0L;
    tmp9_5;
    if ((b(paramContext, tmp5_4) > 0) && (tmp5_4[0] == paramLong) && (tmp5_4[1] != 0L)) {
      a(paramContext, 789);
    }
  }
  
  private static final void c(Context paramContext, Set<Long> paramSet, List<b> paramList)
  {
    Cursor localCursor = paramContext.getContentResolver().query(Telephony.Mms.CONTENT_URI, a, a(false, "(msg_box=1 AND seen=0 AND (m_type=130 OR m_type=132))"), null, "association_id desc");
    if (localCursor == null) {
      return;
    }
    for (;;)
    {
      try
      {
        if (!localCursor.moveToNext()) {
          break;
        }
        l1 = localCursor.getLong(2);
        localUri = Telephony.Mms.CONTENT_URI.buildUpon().appendPath(Long.toString(l1)).build();
        str1 = zg.a(paramContext, localUri);
        localgm = gm.a(str1, false);
        if (!localgm.f())
        {
          str2 = wd.a(paramContext, a(localCursor.getString(3), localCursor.getInt(4)));
          l1 = localCursor.getLong(0);
          l2 = localCursor.getLong(1);
          if (Log.isLoggable("Mms:app", 2)) {
            Log.d("Mms:app", "addMmsNotificationInfos: count=" + localCursor.getCount() + ", addr = " + str1 + ", thread_id=" + l1);
          }
          localObject2 = null;
          i3 = 0;
          i2 = localCursor.getInt(5);
          int i4 = localCursor.getInt(6);
          i1 = i2;
          if (i2 < 0)
          {
            i1 = i2;
            if (i4 == 2) {
              i1 = 4;
            }
          }
        }
      }
      finally
      {
        try
        {
          long l1;
          String str1;
          gm localgm;
          String str2;
          long l2;
          Object localObject2;
          int i3;
          int i1;
          Object localObject3 = k.load(localUri, true);
          Object localObject1 = localObject2;
          int i2 = i3;
          if (localObject3 != null)
          {
            localObject1 = localObject2;
            i2 = i3;
            if ((((MzPduCacheEntry)localObject3).getPdu() instanceof MzMultimediaMessagePdu))
            {
              localObject1 = lr.a(paramContext, (MzPduCacheEntry)localObject3);
              i3 = a((lr)localObject1);
              localObject3 = ((lr)localObject1).a(0);
              localObject1 = localObject2;
              i2 = i3;
              if (localObject3 != null)
              {
                localObject1 = localObject2;
                i2 = i3;
                if (((lq)localObject3).d())
                {
                  localObject1 = ((lq)localObject3).p().a();
                  i2 = i3;
                }
              }
            }
          }
          paramList.add(a(paramContext, false, str1, (String)localObject1, str2, l1, 1000L * l2, null, localgm, i2, localUri, i1));
          paramSet.add(Long.valueOf(l1));
        }
        catch (MmsException localMmsException)
        {
          Uri localUri;
          Log.e("Mms:app", "MmsException loading uri: " + localUri, localMmsException);
        }
        paramContext = finally;
        localCursor.close();
      }
    }
    localCursor.close();
  }
  
  public static void d(Context paramContext)
  {
    a(paramContext, false, 0L, false);
  }
  
  private static final void d(Context paramContext, Set<Long> paramSet, List<b> paramList)
  {
    Cursor localCursor = paramContext.getContentResolver().query(Telephony.Sms.CONTENT_URI, b, a(true, "(type = 1 AND seen = 0)"), null, "association_id desc");
    if (localCursor == null)
    {
      Log.d("Mms:app", "addSmsNotificationInfos(): cursor = null");
      return;
    }
    for (;;)
    {
      try
      {
        if (!localCursor.moveToNext()) {
          break;
        }
        int i1 = localCursor.getInt(5);
        String str2 = localCursor.getString(2);
        gm localgm = gm.a(str2, false);
        if (!localgm.f())
        {
          String str1;
          if (i1 != 9204)
          {
            str1 = localCursor.getString(4);
            long l1 = localCursor.getLong(0);
            long l2 = localCursor.getLong(1);
            Log.d("Mms:app", "addSmsNotificationInfos: count=" + localCursor.getCount() + ", addr= ******" + ", thread_id=" + l1);
            Uri localUri = ContentUris.withAppendedId(Telephony.Sms.CONTENT_URI, localCursor.getInt(6));
            int i2 = localCursor.getInt(7);
            int i3 = localCursor.getInt(8);
            i1 = i2;
            if (i2 < 0)
            {
              i1 = i2;
              if (i3 == 256) {
                i1 = 4;
              }
            }
            paramList.add(a(paramContext, true, str2, str1, null, l1, l2, null, localgm, 0, localUri, i1));
            paramSet.add(Long.valueOf(l1));
            paramSet.add(Long.valueOf(localCursor.getLong(0)));
          }
          else
          {
            str1 = paramContext.getString(2131493489);
          }
        }
      }
      finally
      {
        localCursor.close();
      }
    }
    localCursor.close();
  }
  
  private static CharSequence e(Context paramContext, int paramInt)
  {
    switch (paramInt)
    {
    default: 
      paramInt = 0;
    }
    while (paramInt > 0)
    {
      return new SpannableString(paramContext.getString(paramInt));
      paramInt = 2131492896;
      continue;
      paramInt = 2131492899;
      continue;
      paramInt = 2131492898;
      continue;
      paramInt = 2131492897;
      continue;
      paramInt = 2131493229;
      continue;
      paramInt = 2131493226;
      continue;
      paramInt = 2131493228;
      continue;
      paramInt = 2131493227;
    }
    return "";
  }
  
  public static void e(Context paramContext)
  {
    new oc(paramContext).execute(new Void[0]);
  }
  
  public static void f(Context paramContext)
  {
    if (i(paramContext) < 1) {
      a(paramContext, 531);
    }
  }
  
  private static void f(Context paramContext, int paramInt)
  {
    int i1 = 1;
    ArrayList localArrayList = new ArrayList();
    Object localObject = new HashSet(4);
    c(paramContext, (Set)localObject, localArrayList);
    d(paramContext, (Set)localObject, localArrayList);
    if (localArrayList.size() == 0)
    {
      gr.a(paramContext, false);
      return;
    }
    Log.e("Mms:app", "delOrQuickReplyMsg number is " + localArrayList.size() + ", type = " + paramInt);
    localObject = (b[])localArrayList.toArray(new b[localArrayList.size()]);
    Resources localResources = paramContext.getResources();
    if (paramInt == 1) {}
    for (;;)
    {
      a(paramContext, localResources.getString(2131492923, new Object[] { Integer.valueOf(i1) }), new of(paramContext, paramInt, (b[])localObject), null, null).show();
      return;
      i1 = localArrayList.size();
    }
  }
  
  public static void g(Context paramContext)
  {
    NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
    Object localObject2 = new Intent("android.intent.action.MAIN");
    ((Intent)localObject2).addCategory("android.intent.category.LAUNCHER");
    ((Intent)localObject2).setClassName("com.meizu.filemanager", "com.meizu.filemanager.managefile.FileManagerActivity");
    ((Intent)localObject2).putExtra("init_directory", Environment.getExternalStorageDirectory().getPath());
    ((Intent)localObject2).addFlags(524288);
    if (a()) {}
    for (Object localObject1 = (UserHandle)aau.a(UserHandle.class, "OWNER");; localObject1 = new mf(MmsApp.c().getApplicationContext()).b())
    {
      localObject1 = a(paramContext, (Intent)localObject2, (UserHandle)localObject1);
      Object localObject3 = new Notification.Builder(paramContext);
      localObject2 = paramContext.getString(2131493301);
      String str = paramContext.getString(2131493300);
      aau.a(Notification.Builder.class, localObject3, "mFlymeNotificationBuilder", "mNotificationIcon", "mInternalApp", 2130838559, 1);
      localObject3 = new Notification.InboxStyle((Notification.Builder)localObject3).build();
      icon = 2130838482;
      tickerText = ((CharSequence)localObject2);
      ((Notification)localObject3).setLatestEventInfo(paramContext, (CharSequence)localObject2, str, (PendingIntent)localObject1);
      defaults |= 0x2;
      flags |= 0x10;
      localNotificationManager.notify(533, (Notification)localObject3);
      return;
    }
  }
  
  private static int i(Context paramContext)
  {
    paramContext = paramContext.getContentResolver().query(Telephony.Mms.Inbox.CONTENT_URI, null, "m_type=" + String.valueOf(130) + " AND " + "st" + "=" + String.valueOf(135), null, null);
    if (paramContext == null) {
      return 0;
    }
    int i1 = paramContext.getCount();
    paramContext.close();
    return i1;
  }
  
  private static void j(Context paramContext)
  {
    new Thread(new od(paramContext), "MessagingNotification.markMsgAsSeen").start();
  }
  
  public static class OnNotifyDeleteReceiver
    extends BroadcastReceiver
  {
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent.getAction() == "com.android.mms.NOTIFICATION_DELETED_ACTION") {
        MessagingNotification.h(paramContext);
      }
    }
  }
  
  public static class OnNotifyOperationReceiver
    extends BroadcastReceiver
  {
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent.getAction() == "com.android.mms.NOTIFICATION_MARKASREAD_ACTION") {
        MessagingNotification.b(paramContext, 0);
      }
      do
      {
        return;
        if (paramIntent.getAction() == "com.android.mms.NOTIFICATION_MARKASREAD_ACTION_HEADS_UP")
        {
          MessagingNotification.b(paramContext, 1);
          return;
        }
        if (paramIntent.getAction() == "com.android.mms.NOTIFICATION_DELETED_MSG_ACTION")
        {
          MessagingNotification.c(paramContext, 0);
          return;
        }
      } while (paramIntent.getAction() != "com.android.mms.NOTIFICATION_DELETED_MSG_ACTION_HEADS_UP");
      MessagingNotification.c(paramContext, 1);
    }
  }
  
  static final class a
  {
    public CharSequence a;
    public long b;
    
    public a(CharSequence paramCharSequence, long paramLong)
    {
      a = paramCharSequence;
      b = paramLong;
    }
    
    public void a(Context paramContext, boolean paramBoolean)
    {
      MessagingNotification.a(paramContext, paramBoolean, a, b);
    }
  }
  
  public static final class b
  {
    public final Intent a;
    public final String b;
    public final CharSequence c;
    public final long d;
    public final String e;
    public final Bitmap f;
    public final gm g;
    public final boolean h;
    public final int i;
    public final String j;
    public final long k;
    public final Uri l;
    public final int m;
    
    public b(boolean paramBoolean, Intent paramIntent, String paramString1, String paramString2, CharSequence paramCharSequence, long paramLong1, String paramString3, Bitmap paramBitmap, gm paramgm, int paramInt1, long paramLong2, Uri paramUri, int paramInt2)
    {
      h = paramBoolean;
      a = paramIntent;
      b = paramString1;
      j = paramString2;
      c = paramCharSequence;
      d = paramLong1;
      e = paramString3;
      f = null;
      g = paramgm;
      i = paramInt1;
      k = paramLong2;
      l = paramUri;
      m = paramInt2;
    }
    
    public long a()
    {
      return d;
    }
    
    public CharSequence a(Context paramContext)
    {
      if (!TextUtils.isEmpty(b)) {
        b.replaceAll("\\n\\s+", "\n");
      }
      for (;;)
      {
        SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder();
        if (!TextUtils.isEmpty(j)) {
          localSpannableStringBuilder.append(j);
        }
        if (i > 0)
        {
          if (localSpannableStringBuilder.length() > 0) {
            localSpannableStringBuilder.append('\n');
          }
          localSpannableStringBuilder.append(MessagingNotification.d(paramContext, i));
        }
        if (b != null)
        {
          if (localSpannableStringBuilder.length() > 0) {
            localSpannableStringBuilder.append('\n');
          }
          localSpannableStringBuilder.append(b);
        }
        return localSpannableStringBuilder;
      }
    }
    
    public CharSequence b(Context paramContext)
    {
      if (!TextUtils.isEmpty(b)) {}
      for (String str1 = b.replaceAll("\\n\\s+", "\n");; str1 = "")
      {
        SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder();
        String str2 = g.g();
        if (!TextUtils.isEmpty(str2)) {
          localSpannableStringBuilder.append(str2);
        }
        str2 = paramContext.getString(2131493030);
        if (!h)
        {
          if (!TextUtils.isEmpty(j))
          {
            if (localSpannableStringBuilder.length() > 0) {
              localSpannableStringBuilder.append(str2);
            }
            localSpannableStringBuilder.length();
            localSpannableStringBuilder.append(j);
          }
          if (i > 0)
          {
            if (localSpannableStringBuilder.length() > 0) {
              localSpannableStringBuilder.append(str2);
            }
            localSpannableStringBuilder.append(MessagingNotification.d(paramContext, i));
          }
        }
        if (str1.length() > 0)
        {
          if (localSpannableStringBuilder.length() > 0) {
            localSpannableStringBuilder.append(str2);
          }
          localSpannableStringBuilder.length();
          localSpannableStringBuilder.append(str1);
        }
        return localSpannableStringBuilder;
      }
    }
    
    public CharSequence c(Context paramContext)
    {
      TextAppearanceSpan localTextAppearanceSpan = new TextAppearanceSpan(paramContext, 2131624181);
      if (!TextUtils.isEmpty(b)) {}
      for (paramContext = b.replaceAll("\\n\\s+", "\n");; paramContext = "")
      {
        SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder();
        if (!TextUtils.isEmpty(j))
        {
          localSpannableStringBuilder.append(j);
          localSpannableStringBuilder.setSpan(localTextAppearanceSpan, 0, j.length(), 0);
        }
        if ((paramContext.length() > 0) && (localSpannableStringBuilder.length() == 0))
        {
          localSpannableStringBuilder.append(paramContext);
          localSpannableStringBuilder.setSpan(localTextAppearanceSpan, 0, paramContext.length(), 0);
        }
        return localSpannableStringBuilder;
      }
    }
  }
  
  static final class c
    implements Comparator<MessagingNotification.b>
  {
    public int a(MessagingNotification.b paramb1, MessagingNotification.b paramb2)
    {
      return Long.signum(paramb2.a() - paramb1.a());
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MessagingNotification
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */