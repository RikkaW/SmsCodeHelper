import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import com.android.mms.MmsApp;

public class zj
{
  public static void a(ContentValues paramContentValues)
  {
    Uri localUri = Uri.parse("content://blockmessage/");
    MmsApp localMmsApp = MmsApp.c();
    paramContentValues.put("android.intent.extra.KEY_CONFIRM", Boolean.valueOf(a()));
    localMmsApp.getContentResolver().insert(localUri, paramContentValues);
  }
  
  private static boolean a()
  {
    return PreferenceManager.getDefaultSharedPreferences(MmsApp.c()).getBoolean("pref_key_enable_notifications", false);
  }
  
  public static boolean a(String paramString)
  {
    String str = wd.d(paramString);
    paramString = Uri.parse("content://blackList/");
    ContentResolver localContentResolver = MmsApp.c().getContentResolver();
    str = "address = " + str;
    paramString = localContentResolver.query(paramString, new String[] { "_id" }, str, null, null);
    if (paramString.getCount() > 0) {}
    for (boolean bool = true;; bool = false)
    {
      paramString.close();
      return bool;
    }
  }
  
  /* Error */
  public static boolean a(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 6
    //   6: ldc 112
    //   8: invokestatic 14	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   11: astore 8
    //   13: ldc 114
    //   15: invokestatic 14	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   18: astore 7
    //   20: new 33	android/content/ContentValues
    //   23: dup
    //   24: invokespecial 115	android/content/ContentValues:<init>	()V
    //   27: astore 9
    //   29: aload 9
    //   31: ldc 117
    //   33: aload_0
    //   34: invokevirtual 120	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   37: aload 9
    //   39: ldc 122
    //   41: aload_1
    //   42: invokevirtual 120	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   45: aload 9
    //   47: ldc 124
    //   49: ldc 126
    //   51: invokevirtual 120	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   54: invokestatic 20	com/android/mms/MmsApp:c	()Lcom/android/mms/MmsApp;
    //   57: astore_0
    //   58: aload_0
    //   59: invokevirtual 43	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   62: aload 8
    //   64: aload 9
    //   66: invokevirtual 49	android/content/ContentResolver:insert	(Landroid/net/Uri;Landroid/content/ContentValues;)Landroid/net/Uri;
    //   69: astore_1
    //   70: aload_1
    //   71: ifnull +141 -> 212
    //   74: aload_1
    //   75: aload 7
    //   77: invokevirtual 130	android/net/Uri:equals	(Ljava/lang/Object;)Z
    //   80: ifne +132 -> 212
    //   83: aload_0
    //   84: invokevirtual 43	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   87: aload_1
    //   88: aconst_null
    //   89: aconst_null
    //   90: aconst_null
    //   91: aconst_null
    //   92: invokevirtual 98	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   95: astore_0
    //   96: aload_0
    //   97: astore_1
    //   98: aload_0
    //   99: invokeinterface 133 1 0
    //   104: pop
    //   105: aload_0
    //   106: astore_1
    //   107: aload_0
    //   108: invokeinterface 104 1 0
    //   113: istore_2
    //   114: iload_2
    //   115: ifle +92 -> 207
    //   118: iconst_1
    //   119: istore_3
    //   120: aload_0
    //   121: astore_1
    //   122: aload_0
    //   123: invokeinterface 107 1 0
    //   128: iload_3
    //   129: istore 4
    //   131: aload_0
    //   132: ifnull +12 -> 144
    //   135: aload_0
    //   136: invokeinterface 107 1 0
    //   141: iload_3
    //   142: istore 4
    //   144: iload 4
    //   146: ireturn
    //   147: astore 5
    //   149: aconst_null
    //   150: astore_0
    //   151: iconst_0
    //   152: istore_3
    //   153: aload_0
    //   154: astore_1
    //   155: aload 5
    //   157: invokevirtual 136	java/lang/Exception:printStackTrace	()V
    //   160: iload_3
    //   161: istore 4
    //   163: aload_0
    //   164: ifnull -20 -> 144
    //   167: aload_0
    //   168: invokeinterface 107 1 0
    //   173: iload_3
    //   174: ireturn
    //   175: astore_0
    //   176: aload 5
    //   178: astore_1
    //   179: aload_1
    //   180: ifnull +9 -> 189
    //   183: aload_1
    //   184: invokeinterface 107 1 0
    //   189: aload_0
    //   190: athrow
    //   191: astore_0
    //   192: goto -13 -> 179
    //   195: astore 5
    //   197: iconst_0
    //   198: istore_3
    //   199: goto -46 -> 153
    //   202: astore 5
    //   204: goto -51 -> 153
    //   207: iconst_0
    //   208: istore_3
    //   209: goto -89 -> 120
    //   212: iconst_0
    //   213: istore_3
    //   214: aload 6
    //   216: astore_0
    //   217: goto -89 -> 128
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	220	0	paramString1	String
    //   0	220	1	paramString2	String
    //   113	2	2	i	int
    //   119	95	3	bool1	boolean
    //   129	33	4	bool2	boolean
    //   1	1	5	localObject1	Object
    //   147	30	5	localException1	Exception
    //   195	1	5	localException2	Exception
    //   202	1	5	localException3	Exception
    //   4	211	6	localObject2	Object
    //   18	58	7	localUri1	Uri
    //   11	52	8	localUri2	Uri
    //   27	38	9	localContentValues	ContentValues
    // Exception table:
    //   from	to	target	type
    //   6	70	147	java/lang/Exception
    //   74	96	147	java/lang/Exception
    //   6	70	175	finally
    //   74	96	175	finally
    //   98	105	191	finally
    //   107	114	191	finally
    //   122	128	191	finally
    //   155	160	191	finally
    //   98	105	195	java/lang/Exception
    //   107	114	195	java/lang/Exception
    //   122	128	202	java/lang/Exception
  }
  
  public static void b(String paramString1, String paramString2)
  {
    Uri localUri = Uri.parse("content://blackList/");
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("address", paramString1);
    if (paramString2 != null) {
      localContentValues.put("contact_name", paramString2);
    }
    MmsApp.c().getContentResolver().insert(localUri, localContentValues);
  }
}

/* Location:
 * Qualified Name:     zj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */