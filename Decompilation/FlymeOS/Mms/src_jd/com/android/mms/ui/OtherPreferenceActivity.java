package com.android.mms.ui;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceScreen;
import android.util.Log;
import android.view.MenuItem;
import fz;
import zv;

public class OtherPreferenceActivity
  extends fz
  implements Preference.OnPreferenceChangeListener
{
  private static String a = "default";
  private Preference b;
  private Preference c;
  private Preference d;
  
  private void c()
  {
    if (zv.e > 1) {
      addPreferencesFromResource(2131230722);
    }
    for (;;)
    {
      d();
      b = findPreference("pref_key_mms_read_reports");
      c = findPreference("pref_key_mms_allow_return_read_reports");
      d = findPreference("pref_key_message_capacity");
      Log.v("OtherPreferenceActivity", "mMessageCapacityStr:" + a);
      d.setSummary(a);
      return;
      addPreferencesFromResource(2131230721);
    }
  }
  
  /* Error */
  private void d()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 6
    //   6: aload_0
    //   7: invokevirtual 100	com/android/mms/ui/OtherPreferenceActivity:getResources	()Landroid/content/res/Resources;
    //   10: ldc 101
    //   12: invokevirtual 107	android/content/res/Resources:getText	(I)Ljava/lang/CharSequence;
    //   15: invokeinterface 110 1 0
    //   20: putstatic 18	com/android/mms/ui/OtherPreferenceActivity:a	Ljava/lang/String;
    //   23: aload_0
    //   24: invokevirtual 114	com/android/mms/ui/OtherPreferenceActivity:getContentResolver	()Landroid/content/ContentResolver;
    //   27: ldc 116
    //   29: invokestatic 122	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   32: aconst_null
    //   33: aconst_null
    //   34: aconst_null
    //   35: aconst_null
    //   36: invokevirtual 128	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   39: astore_3
    //   40: aload_3
    //   41: astore_2
    //   42: new 28	java/lang/StringBuilder
    //   45: dup
    //   46: invokespecial 29	java/lang/StringBuilder:<init>	()V
    //   49: getstatic 18	com/android/mms/ui/OtherPreferenceActivity:a	Ljava/lang/String;
    //   52: invokevirtual 33	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   55: astore 4
    //   57: aload_3
    //   58: ifnull +262 -> 320
    //   61: aload_3
    //   62: astore_2
    //   63: aload_3
    //   64: invokeinterface 134 1 0
    //   69: istore_1
    //   70: aload_3
    //   71: astore_2
    //   72: aload 4
    //   74: iload_1
    //   75: invokestatic 140	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   78: invokevirtual 33	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: invokevirtual 40	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   84: putstatic 18	com/android/mms/ui/OtherPreferenceActivity:a	Ljava/lang/String;
    //   87: aload_3
    //   88: ifnull +9 -> 97
    //   91: aload_3
    //   92: invokeinterface 143 1 0
    //   97: new 28	java/lang/StringBuilder
    //   100: dup
    //   101: invokespecial 29	java/lang/StringBuilder:<init>	()V
    //   104: getstatic 18	com/android/mms/ui/OtherPreferenceActivity:a	Ljava/lang/String;
    //   107: invokevirtual 33	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   110: aload_0
    //   111: invokevirtual 100	com/android/mms/ui/OtherPreferenceActivity:getResources	()Landroid/content/res/Resources;
    //   114: ldc -112
    //   116: invokevirtual 107	android/content/res/Resources:getText	(I)Ljava/lang/CharSequence;
    //   119: invokeinterface 110 1 0
    //   124: invokevirtual 33	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   127: invokevirtual 40	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   130: putstatic 18	com/android/mms/ui/OtherPreferenceActivity:a	Ljava/lang/String;
    //   133: aload 6
    //   135: astore_2
    //   136: aload 5
    //   138: astore_3
    //   139: aload_0
    //   140: invokevirtual 114	com/android/mms/ui/OtherPreferenceActivity:getContentResolver	()Landroid/content/ContentResolver;
    //   143: ldc -110
    //   145: invokestatic 122	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   148: aconst_null
    //   149: aconst_null
    //   150: aconst_null
    //   151: aconst_null
    //   152: invokevirtual 128	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   155: astore 4
    //   157: aload 4
    //   159: astore_2
    //   160: aload 4
    //   162: astore_3
    //   163: new 28	java/lang/StringBuilder
    //   166: dup
    //   167: invokespecial 29	java/lang/StringBuilder:<init>	()V
    //   170: getstatic 18	com/android/mms/ui/OtherPreferenceActivity:a	Ljava/lang/String;
    //   173: invokevirtual 33	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   176: astore 5
    //   178: aload 4
    //   180: ifnull +189 -> 369
    //   183: aload 4
    //   185: astore_2
    //   186: aload 4
    //   188: astore_3
    //   189: aload 4
    //   191: invokeinterface 134 1 0
    //   196: istore_1
    //   197: aload 4
    //   199: astore_2
    //   200: aload 4
    //   202: astore_3
    //   203: aload 5
    //   205: iload_1
    //   206: invokestatic 140	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   209: invokevirtual 33	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   212: invokevirtual 40	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   215: putstatic 18	com/android/mms/ui/OtherPreferenceActivity:a	Ljava/lang/String;
    //   218: aload 4
    //   220: ifnull +10 -> 230
    //   223: aload 4
    //   225: invokeinterface 143 1 0
    //   230: new 28	java/lang/StringBuilder
    //   233: dup
    //   234: invokespecial 29	java/lang/StringBuilder:<init>	()V
    //   237: getstatic 18	com/android/mms/ui/OtherPreferenceActivity:a	Ljava/lang/String;
    //   240: invokevirtual 33	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   243: aload_0
    //   244: invokevirtual 100	com/android/mms/ui/OtherPreferenceActivity:getResources	()Landroid/content/res/Resources;
    //   247: ldc -109
    //   249: invokevirtual 107	android/content/res/Resources:getText	(I)Ljava/lang/CharSequence;
    //   252: invokeinterface 110 1 0
    //   257: invokevirtual 33	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   260: invokevirtual 40	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   263: putstatic 18	com/android/mms/ui/OtherPreferenceActivity:a	Ljava/lang/String;
    //   266: aload_0
    //   267: invokevirtual 151	com/android/mms/ui/OtherPreferenceActivity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   270: astore_2
    //   271: aload_2
    //   272: invokevirtual 157	java/lang/Object:getClass	()Ljava/lang/Class;
    //   275: ldc -97
    //   277: iconst_2
    //   278: anewarray 161	java/lang/Class
    //   281: dup
    //   282: iconst_0
    //   283: ldc -120
    //   285: aastore
    //   286: dup
    //   287: iconst_1
    //   288: ldc -93
    //   290: aastore
    //   291: invokevirtual 167	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   294: aload_2
    //   295: iconst_2
    //   296: anewarray 153	java/lang/Object
    //   299: dup
    //   300: iconst_0
    //   301: ldc -87
    //   303: aastore
    //   304: dup
    //   305: iconst_1
    //   306: new 171	xe
    //   309: dup
    //   310: aload_0
    //   311: invokespecial 174	xe:<init>	(Lcom/android/mms/ui/OtherPreferenceActivity;)V
    //   314: aastore
    //   315: invokevirtual 180	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   318: pop
    //   319: return
    //   320: iconst_0
    //   321: istore_1
    //   322: goto -252 -> 70
    //   325: astore 4
    //   327: aconst_null
    //   328: astore_3
    //   329: aload_3
    //   330: astore_2
    //   331: ldc 69
    //   333: ldc -74
    //   335: aload 4
    //   337: invokestatic 185	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   340: pop
    //   341: aload_3
    //   342: ifnull -245 -> 97
    //   345: aload_3
    //   346: invokeinterface 143 1 0
    //   351: goto -254 -> 97
    //   354: astore_3
    //   355: aconst_null
    //   356: astore_2
    //   357: aload_2
    //   358: ifnull +9 -> 367
    //   361: aload_2
    //   362: invokeinterface 143 1 0
    //   367: aload_3
    //   368: athrow
    //   369: iconst_0
    //   370: istore_1
    //   371: goto -174 -> 197
    //   374: astore 4
    //   376: aload_2
    //   377: astore_3
    //   378: ldc 69
    //   380: ldc -69
    //   382: aload 4
    //   384: invokestatic 185	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   387: pop
    //   388: aload_2
    //   389: ifnull -159 -> 230
    //   392: aload_2
    //   393: invokeinterface 143 1 0
    //   398: goto -168 -> 230
    //   401: astore_2
    //   402: aload_3
    //   403: ifnull +9 -> 412
    //   406: aload_3
    //   407: invokeinterface 143 1 0
    //   412: aload_2
    //   413: athrow
    //   414: astore_2
    //   415: ldc 69
    //   417: ldc -67
    //   419: aload_2
    //   420: invokestatic 185	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   423: pop
    //   424: return
    //   425: astore_2
    //   426: ldc 69
    //   428: ldc -65
    //   430: aload_2
    //   431: invokestatic 185	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   434: pop
    //   435: return
    //   436: astore_2
    //   437: ldc 69
    //   439: ldc -63
    //   441: aload_2
    //   442: invokestatic 185	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   445: pop
    //   446: return
    //   447: astore_2
    //   448: ldc 69
    //   450: ldc -61
    //   452: aload_2
    //   453: invokestatic 185	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   456: pop
    //   457: return
    //   458: astore_2
    //   459: ldc 69
    //   461: ldc -59
    //   463: aload_2
    //   464: invokestatic 185	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   467: pop
    //   468: return
    //   469: astore_3
    //   470: goto -113 -> 357
    //   473: astore 4
    //   475: goto -146 -> 329
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	478	0	this	OtherPreferenceActivity
    //   69	302	1	i	int
    //   41	352	2	localObject1	Object
    //   401	12	2	localObject2	Object
    //   414	6	2	localIllegalAccessException	IllegalAccessException
    //   425	6	2	localIllegalArgumentException	IllegalArgumentException
    //   436	6	2	localInvocationTargetException	java.lang.reflect.InvocationTargetException
    //   447	6	2	localNoSuchMethodException	NoSuchMethodException
    //   458	6	2	localException	Exception
    //   39	307	3	localObject3	Object
    //   354	14	3	localObject4	Object
    //   377	30	3	localObject5	Object
    //   469	1	3	localObject6	Object
    //   55	169	4	localObject7	Object
    //   325	11	4	localSQLiteException1	android.database.sqlite.SQLiteException
    //   374	9	4	localSQLiteException2	android.database.sqlite.SQLiteException
    //   473	1	4	localSQLiteException3	android.database.sqlite.SQLiteException
    //   1	203	5	localStringBuilder	StringBuilder
    //   4	130	6	localObject8	Object
    // Exception table:
    //   from	to	target	type
    //   23	40	325	android/database/sqlite/SQLiteException
    //   23	40	354	finally
    //   139	157	374	android/database/sqlite/SQLiteException
    //   163	178	374	android/database/sqlite/SQLiteException
    //   189	197	374	android/database/sqlite/SQLiteException
    //   203	218	374	android/database/sqlite/SQLiteException
    //   139	157	401	finally
    //   163	178	401	finally
    //   189	197	401	finally
    //   203	218	401	finally
    //   378	388	401	finally
    //   266	319	414	java/lang/IllegalAccessException
    //   266	319	425	java/lang/IllegalArgumentException
    //   266	319	436	java/lang/reflect/InvocationTargetException
    //   266	319	447	java/lang/NoSuchMethodException
    //   266	319	458	java/lang/Exception
    //   42	57	469	finally
    //   63	70	469	finally
    //   72	87	469	finally
    //   331	341	469	finally
    //   42	57	473	android/database/sqlite/SQLiteException
    //   63	70	473	android/database/sqlite/SQLiteException
    //   72	87	473	android/database/sqlite/SQLiteException
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    c();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return false;
    }
    onBackPressed();
    return true;
  }
  
  public boolean onPreferenceChange(Preference paramPreference, Object paramObject)
  {
    return true;
  }
  
  public boolean onPreferenceTreeClick(PreferenceScreen paramPreferenceScreen, Preference paramPreference)
  {
    if (zv.e > 1)
    {
      if (paramPreference != b) {
        break label27;
      }
      MessagingPreferenceActivity.a(5, this);
    }
    for (;;)
    {
      return super.onPreferenceTreeClick(paramPreferenceScreen, paramPreference);
      label27:
      if (paramPreference == c) {
        MessagingPreferenceActivity.a(6, this);
      }
    }
  }
  
  protected void onResume()
  {
    super.onResume();
  }
  
  protected void onStart()
  {
    super.onStart();
  }
  
  protected void onStop()
  {
    super.onStop();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.OtherPreferenceActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */