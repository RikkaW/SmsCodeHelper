import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.provider.Settings.Global;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import cn.com.xy.sms.sdk.action.AbsSdkDoAction;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import com.android.mms.MmsApp;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class zv
{
  public static boolean a = false;
  public static int b = 1;
  public static String[] c = null;
  public static aac.a[] d = null;
  public static int e = 0;
  public static int f = 0;
  public static boolean g;
  public static boolean[] h = null;
  public static int i = 0;
  public static int j = 0;
  private static String k = "";
  private static String l = "";
  private static HashSet<zv.a> m = new HashSet();
  private static HashSet<zv.a> n;
  
  static
  {
    g = false;
    n = new HashSet();
  }
  
  public static int a()
  {
    if (b == 2)
    {
      Log.i("DualSimMessageUtils", "getUseableSlotId mSlotState[0] = " + d[0]);
      Log.i("DualSimMessageUtils", "getUseableSlotId mSlotState[1] = " + d[1]);
      if (d[0] != aac.a.d) {}
    }
    else
    {
      return 0;
    }
    return 1;
  }
  
  public static int a(int paramInt, String paramString)
  {
    boolean bool = paramString.equals("4007883333");
    if ((e == 2) && (i == 2) && (!bool))
    {
      if (paramInt >= 0) {
        return paramInt;
      }
      return ((Integer)aau.a("android.app.NotificationBuilderExt", "SIM_SLOT_UNKNOWN")).intValue();
    }
    return -2;
  }
  
  public static int a(Context paramContext, String paramString1, String paramString2, List<String> paramList, int paramInt1, long paramLong, int paramInt2)
  {
    try
    {
      paramContext = aac.c(aac.b(paramInt2));
      paramInt1 = ((Integer)paramContext.getClass().getDeclaredMethod("copyTextMessageToIccCard", new Class[] { String.class, String.class, List.class, Integer.TYPE, Long.TYPE }).invoke(paramContext, new Object[] { paramString1, paramString2, paramList, Integer.valueOf(paramInt1), Long.valueOf(paramLong) })).intValue();
      return paramInt1;
    }
    catch (NoSuchMethodException paramContext)
    {
      paramContext = paramContext;
      Log.e("DualSimMessageUtils", "throw Exception :", paramContext);
      return -1;
    }
    catch (InvocationTargetException paramContext)
    {
      paramContext = paramContext;
      Log.e("DualSimMessageUtils", "throw Exception :", paramContext);
      return -1;
    }
    catch (IllegalAccessException paramContext)
    {
      paramContext = paramContext;
      Log.e("DualSimMessageUtils", "throw Exception :", paramContext);
      return -1;
    }
    finally {}
    return -1;
  }
  
  public static int a(Intent paramIntent, String paramString)
  {
    if (e > 1)
    {
      if (paramIntent == null) {
        throw new IllegalArgumentException("intent is null in DualSimMessageUtils.getSlotIdFromIntent");
      }
      if (paramIntent.hasExtra(paramString)) {
        return paramIntent.getIntExtra(paramString, -1);
      }
      throw new IllegalArgumentException("intent:" + paramIntent + " not has extra " + paramString);
    }
    if ((paramIntent != null) && (paramIntent.hasExtra(paramString))) {
      return paramIntent.getIntExtra(paramString, -1);
    }
    return 0;
  }
  
  public static int a(String paramString)
  {
    int i2 = 0;
    int i1 = 0;
    if (!TextUtils.isEmpty(paramString)) {
      if (paramString.equals("-10")) {
        i1 = -10;
      }
    }
    while (!a)
    {
      do
      {
        return i1;
      } while (!a);
      for (;;)
      {
        if (i2 >= c.length) {
          break label88;
        }
        if ((!TextUtils.isEmpty(c[i2])) && (c[i2].equals(paramString)))
        {
          i1 = i2;
          if (d[i2] != aac.a.b) {
            break;
          }
        }
        i2 += 1;
      }
    }
    label88:
    return -1;
  }
  
  public static long a(Intent paramIntent)
  {
    if (Build.VERSION.SDK_INT > 21) {
      return paramIntent.getIntExtra("subscription", -1);
    }
    return paramIntent.getLongExtra("subscription", -1L);
  }
  
  public static String a(Context paramContext, int paramInt)
  {
    Resources localResources = paramContext.getResources();
    String str2 = aac.b(paramContext, paramInt);
    String str1 = str2;
    if (str2 == null)
    {
      Log.i("DualSimMessageUtils", "getSimNameBySlotId slotId = " + paramInt + ", name is null");
      if (!aac.e(paramContext, paramInt)) {
        break label91;
      }
      str1 = "";
    }
    for (;;)
    {
      switch (paramInt)
      {
      default: 
        return "";
        label91:
        if ((MmsApp.b) && (paramInt == 0)) {
          str1 = localResources.getString(2131493767);
        } else {
          str1 = localResources.getString(2131493766);
        }
        break;
      }
    }
    return localResources.getString(2131493695, new Object[] { str1 });
    return localResources.getString(2131493696, new Object[] { str1 });
    return "";
  }
  
  private static void a(long paramLong, int paramInt)
  {
    Log.i("DualSimMessageUtils", "initServiceStates subId = " + paramLong + ", index = " + paramInt);
    h[paramInt] = a(paramLong);
    Log.i("DualSimMessageUtils", "initServiceStates mServiceStates[" + paramInt + "] = " + h[paramInt]);
  }
  
  public static void a(Context paramContext)
  {
    n();
    b(paramContext);
  }
  
  public static void a(Context paramContext, int paramInt1, int paramInt2, int paramInt3)
  {
    boolean bool = false;
    n();
    Log.i("DualSimMessageUtils", "updateServiceStateValues slotId = " + paramInt1 + ", voiceRegState = " + paramInt2 + ", dataRegState = " + paramInt3);
    int i1;
    int i2;
    if (a)
    {
      i1 = paramInt1;
      i2 = paramInt2;
      if (MmsApp.b)
      {
        i1 = paramInt1;
        i2 = paramInt2;
        if (paramInt1 == 0)
        {
          i1 = paramInt1;
          i2 = paramInt2;
          if (f(paramContext)) {
            if (paramInt2 == 3)
            {
              i1 = paramInt1;
              i2 = paramInt2;
              if (paramInt3 == 3) {}
            }
            else
            {
              i2 = 0;
              i1 = paramInt1;
            }
          }
        }
      }
      if (a(i1))
      {
        if (i2 == 3) {
          break label202;
        }
        if (h[i1] == 0)
        {
          h[i1] = true;
          bool = true;
        }
      }
    }
    for (;;)
    {
      Log.i("DualSimMessageUtils", "updateServiceStateValues needUpdate = " + bool);
      if (bool) {
        g();
      }
      return;
      Log.i("DualSimMessageUtils", "updateServiceStateValues set single slot device's slotId to be 0");
      i1 = 0;
      i2 = paramInt2;
      break;
      label202:
      if (h[i1] != 0)
      {
        h[i1] = false;
        bool = true;
      }
    }
  }
  
  public static void a(Context paramContext, int paramInt, String paramString)
  {
    n();
    Log.i("DualSimMessageUtils", "updateImsiSlotValues slotId = " + paramInt + ", slotState = " + paramString);
    if ((!TextUtils.isEmpty(paramString)) && (a(paramInt)))
    {
      if ((!"READY".equals(paramString)) && (!"IMSI".equals(paramString)) && (!"LOADED".equals(paramString))) {
        break label141;
      }
      c[paramInt] = aac.d(paramContext, paramInt);
      Log.i("DualSimMessageUtils", "updateImsiSlotValues IMSI_SLOT_VALUES[" + paramInt + "] = " + c[paramInt]);
    }
    for (;;)
    {
      e(paramContext);
      b();
      return;
      label141:
      if ("ABSENT".equals(paramString))
      {
        c[paramInt] = "";
        h[paramInt] = false;
        g();
      }
    }
  }
  
  public static void a(ImageView paramImageView, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    int i5 = 1;
    int i2;
    int i3;
    label30:
    int i1;
    label53:
    int i4;
    if ((paramInt1 == 3) && (paramInt2 == -10))
    {
      i2 = 1;
      if ((paramInt1 != 0) || (paramInt2 != -10)) {
        break label152;
      }
      i3 = 1;
      if ((a) || (i2 != 0) || (i3 != 0))
      {
        if (paramInt2 < 0) {
          break label158;
        }
        i1 = 1;
        if ((i1 == 0) || (paramBoolean) || (e != 2) || (i != 2)) {
          break label164;
        }
        i4 = 1;
        switch (paramInt1)
        {
        case 4: 
        default: 
          label79:
          i1 = i5;
          if (i3 == 0)
          {
            if (i4 == 0) {
              break label200;
            }
            i1 = i5;
          }
          break;
        }
      }
    }
    for (;;)
    {
      if (i1 != 0) {
        break label206;
      }
      paramImageView.setVisibility(8);
      return;
      i2 = 0;
      break;
      label152:
      i3 = 0;
      break label30;
      label158:
      i1 = 0;
      break label53;
      label164:
      i4 = 0;
      break label79;
      i1 = 0;
      continue;
      i1 = i5;
      if (i2 == 0)
      {
        i1 = i5;
        if (i4 == 0)
        {
          i1 = 0;
          continue;
          label200:
          i1 = 0;
        }
      }
    }
    label206:
    paramImageView.setVisibility(0);
    switch (paramInt2)
    {
    default: 
      return;
    case -10: 
      if (MmsApp.n) {
        break label442;
      }
      if (paramInt1 == 3) {
        paramInt1 = 2130837586;
      }
      break;
    }
    for (;;)
    {
      paramImageView.setImageResource(paramInt1);
      return;
      if (!MmsApp.n)
      {
        if (paramInt1 == 3) {
          paramInt1 = 2130837582;
        }
        for (;;)
        {
          paramImageView.setImageResource(paramInt1);
          return;
          if (paramInt1 == 6) {
            paramInt1 = 2130838643;
          } else {
            paramInt1 = 2130838641;
          }
        }
      }
      if (paramInt1 == 3) {
        paramInt1 = 2130837583;
      }
      for (;;)
      {
        paramImageView.setImageResource(paramInt1);
        return;
        if (paramInt1 == 6) {
          paramInt1 = 2130838643;
        } else {
          paramInt1 = 2130838642;
        }
      }
      if (!MmsApp.n)
      {
        if (paramInt1 == 3) {
          paramInt1 = 2130837584;
        }
        for (;;)
        {
          paramImageView.setImageResource(paramInt1);
          return;
          if (paramInt1 == 6) {
            paramInt1 = 2130838652;
          } else {
            paramInt1 = 2130838650;
          }
        }
      }
      if (paramInt1 == 3) {
        paramInt1 = 2130837585;
      }
      for (;;)
      {
        paramImageView.setImageResource(paramInt1);
        return;
        if (paramInt1 == 6) {
          paramInt1 = 2130838652;
        } else {
          paramInt1 = 2130838651;
        }
      }
      if (paramInt1 == 6) {
        paramInt1 = 2130838664;
      } else {
        paramInt1 = 2130838662;
      }
    }
    label442:
    if (paramInt1 == 3) {
      paramInt1 = 2130837587;
    }
    for (;;)
    {
      paramImageView.setImageResource(paramInt1);
      return;
      if (paramInt1 == 6) {
        paramInt1 = 2130838664;
      } else {
        paramInt1 = 2130838663;
      }
    }
  }
  
  public static void a(zv.a parama)
  {
    synchronized (m)
    {
      m.add(parama);
      return;
    }
  }
  
  public static boolean a(int paramInt)
  {
    return (paramInt > -1) && (paramInt < b);
  }
  
  public static boolean a(long paramLong)
  {
    Log.i("DualSimMessageUtils", "isRadioOn subId = " + paramLong);
    if (aac.b(paramLong))
    {
      if (Build.VERSION.SDK_INT > 21) {
        return ((Boolean)aau.a("android.os.ServiceManager", "getService", String.class, "phone", "com.android.internal.telephony.ITelephony$Stub", "asInterface", IBinder.class, "isRadioOnForSubscriber", Integer.TYPE, Integer.valueOf((int)paramLong))).booleanValue();
      }
      return ((Boolean)aau.a("android.os.ServiceManager", "getService", String.class, "phone", "com.android.internal.telephony.ITelephony$Stub", "asInterface", IBinder.class, "isRadioOnForSubscriber", Long.TYPE, Long.valueOf(paramLong))).booleanValue();
    }
    Log.i("DualSimMessageUtils", "isRadioOn failed to get radio state for sub " + paramLong + ", ITelephony is null or subId is valid");
    return false;
  }
  
  public static boolean a(String paramString1, String paramString2)
  {
    if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2))) {
      return false;
    }
    return paramString1.equals(paramString2);
  }
  
  public static int b(long paramLong)
  {
    int i1 = aac.a(paramLong);
    Log.d("DualSimMessageUtils", "get slot id " + i1 + " from subId " + paramLong);
    if (a(i1)) {
      return i1;
    }
    return 0;
  }
  
  public static String b(Context paramContext, int paramInt)
  {
    Resources localResources = paramContext.getResources();
    paramContext = aac.b(paramContext, paramInt);
    if (paramContext == null) {
      Log.i("DualSimMessageUtils", "getClearSimNameBySlotId simName is null, slotId = " + paramInt);
    }
    switch (paramInt)
    {
    default: 
      return paramContext;
    case 0: 
      return localResources.getString(2131493797);
    case 1: 
      return localResources.getString(2131493798);
    }
    return "";
  }
  
  public static void b()
  {
    int i1 = 0;
    f = e;
    e = 0;
    while (i1 < d.length)
    {
      if (d[i1] == aac.a.d) {
        e += 1;
      }
      i1 += 1;
    }
    Log.i("DualSimMessageUtils", "updateCurrentUseableSimCount LAST_TIME_USEABLE_SIM_COUNT = " + f + ", CURRENT_USEABLE_SIM_COUNT = " + e);
    if (f != e) {
      p();
    }
  }
  
  public static void b(int paramInt)
  {
    Log.i("DualSimMessageUtils", "saveNewConversationImsi slotId = " + paramInt);
    if (a)
    {
      if (a(paramInt)) {
        k = c[paramInt];
      }
    }
    else {
      return;
    }
    d();
  }
  
  public static void b(int paramInt, String paramString)
  {
    if (a)
    {
      Log.i("DualSimMessageUtils", "setConversationDraftImsi slotId = " + paramInt + ", selectedImsi = " + paramString);
      l = c(paramInt);
      if (TextUtils.isEmpty(l)) {
        l = paramString;
      }
    }
  }
  
  public static void b(Context paramContext)
  {
    o();
    int i1 = 0;
    while (i1 < b)
    {
      aac.a locala = aac.a(paramContext, i1);
      d[i1] = locala;
      c[i1] = aac.d(paramContext, i1);
      if (d[i1] != aac.a.b) {
        a(aac.b(i1), i1);
      }
      Log.i("DualSimMessageUtils", "initSimInfoCache slotId = " + i1 + ", slotState = " + locala + ", IMSI_SLOT_VALUES[" + i1 + "] = " + c[i1] + ", mServiceStates[" + i1 + "] = " + h[i1]);
      i1 += 1;
    }
    b();
    g();
  }
  
  public static void b(zv.a parama)
  {
    synchronized (m)
    {
      m.remove(parama);
      return;
    }
  }
  
  public static int c()
  {
    return a(k);
  }
  
  public static String c(int paramInt)
  {
    if (paramInt == -10) {
      return "-10";
    }
    if (a(paramInt)) {
      return c[paramInt];
    }
    return "";
  }
  
  /* Error */
  public static String c(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 442	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   4: getstatic 447	aba:a	Landroid/net/Uri;
    //   7: aconst_null
    //   8: aconst_null
    //   9: aconst_null
    //   10: aconst_null
    //   11: invokevirtual 453	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   14: astore_1
    //   15: aload_1
    //   16: ifnull +68 -> 84
    //   19: aload_1
    //   20: astore_0
    //   21: aload_1
    //   22: invokeinterface 458 1 0
    //   27: pop
    //   28: aload_1
    //   29: astore_0
    //   30: ldc 69
    //   32: new 71	java/lang/StringBuilder
    //   35: dup
    //   36: invokespecial 72	java/lang/StringBuilder:<init>	()V
    //   39: ldc_w 460
    //   42: invokevirtual 78	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: aload_1
    //   46: iconst_0
    //   47: invokeinterface 461 2 0
    //   52: invokevirtual 78	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   55: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   58: invokestatic 90	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   61: pop
    //   62: aload_1
    //   63: astore_0
    //   64: aload_1
    //   65: iconst_0
    //   66: invokeinterface 461 2 0
    //   71: astore_2
    //   72: aload_1
    //   73: ifnull +9 -> 82
    //   76: aload_1
    //   77: invokeinterface 464 1 0
    //   82: aload_2
    //   83: areturn
    //   84: aload_1
    //   85: ifnull +9 -> 94
    //   88: aload_1
    //   89: invokeinterface 464 1 0
    //   94: aconst_null
    //   95: areturn
    //   96: astore_2
    //   97: aconst_null
    //   98: astore_1
    //   99: aload_1
    //   100: astore_0
    //   101: ldc 69
    //   103: new 71	java/lang/StringBuilder
    //   106: dup
    //   107: invokespecial 72	java/lang/StringBuilder:<init>	()V
    //   110: ldc_w 466
    //   113: invokevirtual 78	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   116: aload_2
    //   117: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   120: invokevirtual 85	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   123: invokestatic 90	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   126: pop
    //   127: aload_1
    //   128: ifnull +9 -> 137
    //   131: aload_1
    //   132: invokeinterface 464 1 0
    //   137: aconst_null
    //   138: areturn
    //   139: astore_1
    //   140: aconst_null
    //   141: astore_0
    //   142: aload_0
    //   143: ifnull +9 -> 152
    //   146: aload_0
    //   147: invokeinterface 464 1 0
    //   152: aload_1
    //   153: athrow
    //   154: astore_1
    //   155: goto -13 -> 142
    //   158: astore_2
    //   159: goto -60 -> 99
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	162	0	paramContext	Context
    //   14	118	1	localCursor	android.database.Cursor
    //   139	14	1	localObject1	Object
    //   154	1	1	localObject2	Object
    //   71	12	2	str	String
    //   96	21	2	localException1	Exception
    //   158	1	2	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   0	15	96	java/lang/Exception
    //   0	15	139	finally
    //   21	28	154	finally
    //   30	62	154	finally
    //   64	72	154	finally
    //   101	127	154	finally
    //   21	28	158	java/lang/Exception
    //   30	62	158	java/lang/Exception
    //   64	72	158	java/lang/Exception
  }
  
  public static void c(zv.a parama)
  {
    synchronized (n)
    {
      n.add(parama);
      return;
    }
  }
  
  public static String d(Context paramContext)
  {
    Resources localResources = paramContext.getResources();
    e(0);
    e(1);
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
    boolean bool1 = paramContext.getBoolean("pref_key_sim1_auto_download_roaming_switch", false);
    boolean bool2 = paramContext.getBoolean("pref_key_sim2_auto_download_roaming_switch", false);
    if (bool1)
    {
      paramContext = localResources.getString(2131493802);
      if (!bool2) {
        break label102;
      }
    }
    label102:
    for (String str = localResources.getString(2131493802);; str = localResources.getString(2131493799))
    {
      return localResources.getString(2131493803, new Object[] { paramContext, str });
      paramContext = localResources.getString(2131493799);
      break;
    }
  }
  
  public static void d()
  {
    k = "";
  }
  
  public static void d(int paramInt)
  {
    if (a)
    {
      Log.i("DualSimMessageUtils", "setConversationDraftImsi slotId = " + paramInt);
      l = c(paramInt);
    }
  }
  
  public static void d(zv.a parama)
  {
    synchronized (n)
    {
      n.remove(parama);
      return;
    }
  }
  
  public static String e()
  {
    return l;
  }
  
  public static void e(Context paramContext)
  {
    int i1 = 0;
    while (i1 < b)
    {
      aac.a locala = aac.a(paramContext, i1);
      d[i1] = locala;
      Log.i("DualSimMessageUtils", "updateSlotStateCache slotId = " + i1 + ", slotState = " + locala);
      i1 += 1;
    }
  }
  
  public static boolean e(int paramInt)
  {
    return d[paramInt] == aac.a.d;
  }
  
  public static void f()
  {
    l = "";
  }
  
  public static boolean f(Context paramContext)
  {
    boolean bool = false;
    if (paramContext == null) {
      return false;
    }
    if (Settings.Global.getInt(paramContext.getContentResolver(), "lte_on_cdma_rat_mode", 0) == 2) {
      bool = true;
    }
    Log.d("DualSimMessageUtils", "is4GDataOnly : " + bool);
    return bool;
  }
  
  public static void g()
  {
    int i1 = 0;
    j = i;
    i = 0;
    while (i1 < h.length)
    {
      if (h[i1] != 0) {
        i += 1;
      }
      i1 += 1;
    }
    Log.i("DualSimMessageUtils", "updateCurrentNotRadioOffSimCount LAST_TITME_NOT_RADIO_OFF_SIM_COUNT = " + j + ", CURRENT_NOT_RADIO_OFF_SIM_COUNT = " + i);
    if (j != i) {
      q();
    }
  }
  
  public static int h()
  {
    if (b == 2)
    {
      Log.i("DualSimMessageUtils", "getNotRadioOffSlotId mServiceStates[0] = " + h[0] + ", mSlotState[0] = " + d[0] + ", mServiceStates[1] = " + h[1] + ", mSlotState[1] = " + d[1]);
      if ((h[0] == 0) || (d[0] != aac.a.d)) {}
    }
    else
    {
      return 0;
    }
    if ((h[1] != 0) && (d[1] == aac.a.d)) {
      return 1;
    }
    return -1;
  }
  
  public static int i()
  {
    if (d[0] == aac.a.d) {
      return 0;
    }
    return 1;
  }
  
  public static boolean j()
  {
    return (i == 2) && (e == 2);
  }
  
  public static boolean k()
  {
    return (m()) && (!j());
  }
  
  public static boolean l()
  {
    return (i == 0) || (e == 0);
  }
  
  public static boolean m()
  {
    return (e > 0) && (i > 0);
  }
  
  private static void n()
  {
    if (!g)
    {
      a = ((Boolean)aau.a((TelephonyManager)MmsApp.c().getSystemService("phone"), "isMultiSimEnabled")).booleanValue();
      Log.i("DualSimMessageUtils", " initDeviceMode IS_MULTI_SIM_DEVICE = " + a);
      if (!a) {
        break label104;
      }
    }
    label104:
    for (int i1 = 2;; i1 = 1)
    {
      b = i1;
      c = new String[b];
      d = new aac.a[b];
      h = new boolean[b];
      g = true;
      return;
    }
  }
  
  private static void o()
  {
    int i1 = 0;
    while (i1 < b)
    {
      d[i1] = aac.a.b;
      h[i1] = false;
      i1 += 1;
    }
  }
  
  private static void p()
  {
    synchronized (m)
    {
      HashSet localHashSet = (HashSet)m.clone();
      ??? = localHashSet.iterator();
      if (((Iterator)???).hasNext()) {
        ((zv.a)((Iterator)???).next()).a(f, e);
      }
    }
    DuoquUtils.getSdkDoAction().simChange();
  }
  
  private static void q()
  {
    synchronized (n)
    {
      HashSet localHashSet = (HashSet)n.clone();
      ??? = localHashSet.iterator();
      if (((Iterator)???).hasNext()) {
        ((zv.a)((Iterator)???).next()).a(j, i);
      }
    }
  }
  
  public static abstract interface a
  {
    public abstract void a(int paramInt1, int paramInt2);
  }
}

/* Location:
 * Qualified Name:     zv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */