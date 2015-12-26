package com.android.mms;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import miui.telephony.phonenumber.CountryCode;

public class ServiceCategoryUpdateAsyncTask
  extends AsyncTask<Void, Void, Boolean>
{
  private static String TAG = "ServiceProviderUpdateAsyncTask";
  private static ServiceCategoryUpdateAsyncTask sUpdateTask;
  private Context mContext;
  private boolean mIsFirstUpdate;
  private Set<IServiceCategoryUpdateListener> mUpdateListeners = new HashSet(4);
  
  private ServiceCategoryUpdateAsyncTask(Context paramContext, boolean paramBoolean, IServiceCategoryUpdateListener paramIServiceCategoryUpdateListener)
  {
    mContext = paramContext;
    mIsFirstUpdate = paramBoolean;
  }
  
  /* Error */
  private boolean addListener(IServiceCategoryUpdateListener paramIServiceCategoryUpdateListener)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 37	com/android/mms/ServiceCategoryUpdateAsyncTask:mUpdateListeners	Ljava/util/Set;
    //   6: aload_1
    //   7: invokeinterface 49 2 0
    //   12: ifne +18 -> 30
    //   15: aload_0
    //   16: getfield 37	com/android/mms/ServiceCategoryUpdateAsyncTask:mUpdateListeners	Ljava/util/Set;
    //   19: aload_1
    //   20: invokeinterface 52 2 0
    //   25: istore_2
    //   26: aload_0
    //   27: monitorexit
    //   28: iload_2
    //   29: ireturn
    //   30: iconst_0
    //   31: istore_2
    //   32: goto -6 -> 26
    //   35: astore_1
    //   36: aload_0
    //   37: monitorexit
    //   38: aload_1
    //   39: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	40	0	this	ServiceCategoryUpdateAsyncTask
    //   0	40	1	paramIServiceCategoryUpdateListener	IServiceCategoryUpdateListener
    //   25	7	2	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   2	26	35	finally
  }
  
  private boolean removeListener(IServiceCategoryUpdateListener paramIServiceCategoryUpdateListener)
  {
    try
    {
      boolean bool = mUpdateListeners.remove(paramIServiceCategoryUpdateListener);
      return bool;
    }
    finally
    {
      paramIServiceCategoryUpdateListener = finally;
      throw paramIServiceCategoryUpdateListener;
    }
  }
  
  public static boolean startUpdateAsyncTask(Context paramContext, boolean paramBoolean, IServiceCategoryUpdateListener paramIServiceCategoryUpdateListener)
  {
    int j = 0;
    int i = j;
    try
    {
      if (sUpdateTask == null)
      {
        i = j;
        if (CountryCode.isChinaEnvironment())
        {
          sUpdateTask = new ServiceCategoryUpdateAsyncTask(paramContext, paramBoolean, paramIServiceCategoryUpdateListener);
          i = 1;
        }
      }
      if (sUpdateTask != null)
      {
        sUpdateTask.addListener(paramIServiceCategoryUpdateListener);
        if (i != 0) {
          sUpdateTask.execute(new Void[] { (Void)null });
        }
      }
      return true;
    }
    finally {}
  }
  
  public static void unRegisterListener(IServiceCategoryUpdateListener paramIServiceCategoryUpdateListener)
  {
    if (sUpdateTask != null) {
      synchronized (sUpdateTask)
      {
        sUpdateTask.removeListener(paramIServiceCategoryUpdateListener);
        return;
      }
    }
  }
  
  /* Error */
  private void updateAdvancedSeen(android.content.ContentResolver paramContentResolver)
  {
    // Byte code:
    //   0: aload_1
    //   1: getstatic 88	android/provider/Telephony$Sms:CONTENT_URI	Landroid/net/Uri;
    //   4: iconst_1
    //   5: anewarray 90	java/lang/String
    //   8: dup
    //   9: iconst_0
    //   10: ldc 92
    //   12: aastore
    //   13: ldc 94
    //   15: aconst_null
    //   16: aconst_null
    //   17: invokevirtual 100	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   20: astore_2
    //   21: aload_2
    //   22: ifnull +61 -> 83
    //   25: aload_2
    //   26: invokeinterface 105 1 0
    //   31: ifeq +46 -> 77
    //   34: aload_2
    //   35: iconst_0
    //   36: invokeinterface 109 2 0
    //   41: lconst_0
    //   42: lcmp
    //   43: ifle +34 -> 77
    //   46: new 111	android/content/ContentValues
    //   49: dup
    //   50: iconst_1
    //   51: invokespecial 112	android/content/ContentValues:<init>	(I)V
    //   54: astore_3
    //   55: aload_3
    //   56: ldc 114
    //   58: iconst_3
    //   59: invokestatic 120	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   62: invokevirtual 124	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   65: aload_1
    //   66: getstatic 88	android/provider/Telephony$Sms:CONTENT_URI	Landroid/net/Uri;
    //   69: aload_3
    //   70: ldc 94
    //   72: aconst_null
    //   73: invokevirtual 128	android/content/ContentResolver:update	(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   76: pop
    //   77: aload_2
    //   78: invokeinterface 131 1 0
    //   83: aload_1
    //   84: getstatic 134	android/provider/Telephony$Mms:CONTENT_URI	Landroid/net/Uri;
    //   87: iconst_1
    //   88: anewarray 90	java/lang/String
    //   91: dup
    //   92: iconst_0
    //   93: ldc 92
    //   95: aastore
    //   96: ldc 94
    //   98: aconst_null
    //   99: aconst_null
    //   100: invokevirtual 100	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   103: astore_2
    //   104: aload_2
    //   105: ifnull +61 -> 166
    //   108: aload_2
    //   109: invokeinterface 105 1 0
    //   114: ifeq +46 -> 160
    //   117: aload_2
    //   118: iconst_0
    //   119: invokeinterface 109 2 0
    //   124: lconst_0
    //   125: lcmp
    //   126: ifle +34 -> 160
    //   129: new 111	android/content/ContentValues
    //   132: dup
    //   133: iconst_1
    //   134: invokespecial 112	android/content/ContentValues:<init>	(I)V
    //   137: astore_3
    //   138: aload_3
    //   139: ldc 114
    //   141: iconst_3
    //   142: invokestatic 120	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   145: invokevirtual 124	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   148: aload_1
    //   149: getstatic 134	android/provider/Telephony$Mms:CONTENT_URI	Landroid/net/Uri;
    //   152: aload_3
    //   153: ldc 94
    //   155: aconst_null
    //   156: invokevirtual 128	android/content/ContentResolver:update	(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   159: pop
    //   160: aload_2
    //   161: invokeinterface 131 1 0
    //   166: return
    //   167: astore_1
    //   168: aload_2
    //   169: invokeinterface 131 1 0
    //   174: aload_1
    //   175: athrow
    //   176: astore_1
    //   177: aload_2
    //   178: invokeinterface 131 1 0
    //   183: aload_1
    //   184: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	185	0	this	ServiceCategoryUpdateAsyncTask
    //   0	185	1	paramContentResolver	android.content.ContentResolver
    //   20	158	2	localCursor	android.database.Cursor
    //   54	99	3	localContentValues	android.content.ContentValues
    // Exception table:
    //   from	to	target	type
    //   25	77	167	finally
    //   108	160	176	finally
  }
  
  /* Error */
  protected Boolean doInBackground(Void... paramVarArgs)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 39	com/android/mms/ServiceCategoryUpdateAsyncTask:mContext	Landroid/content/Context;
    //   4: invokevirtual 144	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   7: astore 11
    //   9: invokestatic 150	java/lang/System:currentTimeMillis	()J
    //   12: lstore 7
    //   14: new 152	java/util/ArrayList
    //   17: dup
    //   18: invokespecial 153	java/util/ArrayList:<init>	()V
    //   21: astore 12
    //   23: getstatic 156	android/provider/Telephony$Threads:CONTENT_URI	Landroid/net/Uri;
    //   26: invokevirtual 162	android/net/Uri:buildUpon	()Landroid/net/Uri$Builder;
    //   29: ldc -92
    //   31: ldc -90
    //   33: invokevirtual 172	android/net/Uri$Builder:appendQueryParameter	(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   36: invokevirtual 176	android/net/Uri$Builder:build	()Landroid/net/Uri;
    //   39: pop
    //   40: aload 11
    //   42: getstatic 179	android/provider/Telephony$MmsSms:CONTENT_URI	Landroid/net/Uri;
    //   45: ldc -75
    //   47: invokestatic 185	android/net/Uri:withAppendedPath	(Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;
    //   50: aconst_null
    //   51: aconst_null
    //   52: aconst_null
    //   53: aconst_null
    //   54: invokevirtual 100	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   57: astore_1
    //   58: aload_1
    //   59: ifnull +215 -> 274
    //   62: aload_1
    //   63: ldc -69
    //   65: invokeinterface 191 2 0
    //   70: istore_2
    //   71: aload_1
    //   72: ldc -63
    //   74: invokeinterface 191 2 0
    //   79: istore_3
    //   80: aload_1
    //   81: ldc -61
    //   83: invokeinterface 191 2 0
    //   88: istore 4
    //   90: aload_1
    //   91: iconst_m1
    //   92: invokeinterface 199 2 0
    //   97: pop
    //   98: aload_1
    //   99: invokeinterface 202 1 0
    //   104: ifeq +164 -> 268
    //   107: aload_1
    //   108: iload_2
    //   109: invokeinterface 206 2 0
    //   114: astore 13
    //   116: aload_1
    //   117: iload_3
    //   118: invokeinterface 210 2 0
    //   123: istore 5
    //   125: aload_1
    //   126: iload 4
    //   128: invokeinterface 206 2 0
    //   133: astore 14
    //   135: aload_0
    //   136: getfield 39	com/android/mms/ServiceCategoryUpdateAsyncTask:mContext	Landroid/content/Context;
    //   139: aload 13
    //   141: aload 14
    //   143: invokestatic 216	com/android/mms/ui/MessageUtils:getServiceCategory	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)I
    //   146: istore 6
    //   148: iload 6
    //   150: ifeq -52 -> 98
    //   153: new 111	android/content/ContentValues
    //   156: dup
    //   157: invokespecial 217	android/content/ContentValues:<init>	()V
    //   160: astore 13
    //   162: aload 13
    //   164: ldc -37
    //   166: iload 6
    //   168: invokestatic 120	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   171: invokevirtual 124	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   174: aload 12
    //   176: getstatic 156	android/provider/Telephony$Threads:CONTENT_URI	Landroid/net/Uri;
    //   179: invokestatic 225	android/content/ContentProviderOperation:newUpdate	(Landroid/net/Uri;)Landroid/content/ContentProviderOperation$Builder;
    //   182: ldc -29
    //   184: iconst_1
    //   185: anewarray 90	java/lang/String
    //   188: dup
    //   189: iconst_0
    //   190: iload 5
    //   192: invokestatic 229	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   195: aastore
    //   196: invokevirtual 235	android/content/ContentProviderOperation$Builder:withSelection	(Ljava/lang/String;[Ljava/lang/String;)Landroid/content/ContentProviderOperation$Builder;
    //   199: aload 13
    //   201: invokevirtual 239	android/content/ContentProviderOperation$Builder:withValues	(Landroid/content/ContentValues;)Landroid/content/ContentProviderOperation$Builder;
    //   204: invokevirtual 242	android/content/ContentProviderOperation$Builder:build	()Landroid/content/ContentProviderOperation;
    //   207: invokevirtual 243	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   210: pop
    //   211: aload 12
    //   213: invokevirtual 247	java/util/ArrayList:size	()I
    //   216: istore 5
    //   218: iload 5
    //   220: bipush 50
    //   222: if_icmplt -124 -> 98
    //   225: aload 11
    //   227: ldc -7
    //   229: aload 12
    //   231: invokevirtual 253	android/content/ContentResolver:applyBatch	(Ljava/lang/String;Ljava/util/ArrayList;)[Landroid/content/ContentProviderResult;
    //   234: pop
    //   235: aload 12
    //   237: invokevirtual 256	java/util/ArrayList:clear	()V
    //   240: goto -142 -> 98
    //   243: astore 11
    //   245: aload 11
    //   247: invokevirtual 260	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   250: iconst_0
    //   251: anewarray 262	java/lang/Object
    //   254: invokestatic 268	com/android/mms/LogTag:error	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   257: aload_1
    //   258: invokeinterface 131 1 0
    //   263: iconst_0
    //   264: invokestatic 273	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   267: areturn
    //   268: aload_1
    //   269: invokeinterface 131 1 0
    //   274: aload 12
    //   276: invokevirtual 276	java/util/ArrayList:isEmpty	()Z
    //   279: ifne +13 -> 292
    //   282: aload 11
    //   284: ldc -7
    //   286: aload 12
    //   288: invokevirtual 253	android/content/ContentResolver:applyBatch	(Ljava/lang/String;Ljava/util/ArrayList;)[Landroid/content/ContentProviderResult;
    //   291: pop
    //   292: aload_0
    //   293: getfield 41	com/android/mms/ServiceCategoryUpdateAsyncTask:mIsFirstUpdate	Z
    //   296: ifeq +9 -> 305
    //   299: aload_0
    //   300: aload 11
    //   302: invokespecial 278	com/android/mms/ServiceCategoryUpdateAsyncTask:updateAdvancedSeen	(Landroid/content/ContentResolver;)V
    //   305: invokestatic 150	java/lang/System:currentTimeMillis	()J
    //   308: lstore 9
    //   310: getstatic 25	com/android/mms/ServiceCategoryUpdateAsyncTask:TAG	Ljava/lang/String;
    //   313: new 280	java/lang/StringBuilder
    //   316: dup
    //   317: invokespecial 281	java/lang/StringBuilder:<init>	()V
    //   320: ldc_w 283
    //   323: invokevirtual 287	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   326: lload 9
    //   328: lload 7
    //   330: lsub
    //   331: invokevirtual 290	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   334: ldc_w 292
    //   337: invokevirtual 287	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   340: invokevirtual 295	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   343: invokestatic 301	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   346: pop
    //   347: iconst_1
    //   348: invokestatic 273	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   351: areturn
    //   352: astore 11
    //   354: aload_1
    //   355: invokeinterface 131 1 0
    //   360: aload 11
    //   362: athrow
    //   363: astore_1
    //   364: aload_1
    //   365: invokevirtual 260	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   368: iconst_0
    //   369: anewarray 262	java/lang/Object
    //   372: invokestatic 268	com/android/mms/LogTag:error	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   375: invokestatic 150	java/lang/System:currentTimeMillis	()J
    //   378: lstore 9
    //   380: getstatic 25	com/android/mms/ServiceCategoryUpdateAsyncTask:TAG	Ljava/lang/String;
    //   383: new 280	java/lang/StringBuilder
    //   386: dup
    //   387: invokespecial 281	java/lang/StringBuilder:<init>	()V
    //   390: ldc_w 283
    //   393: invokevirtual 287	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   396: lload 9
    //   398: lload 7
    //   400: lsub
    //   401: invokevirtual 290	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   404: ldc_w 292
    //   407: invokevirtual 287	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   410: invokevirtual 295	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   413: invokestatic 301	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   416: pop
    //   417: iconst_0
    //   418: invokestatic 273	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   421: areturn
    //   422: astore_1
    //   423: invokestatic 150	java/lang/System:currentTimeMillis	()J
    //   426: lstore 9
    //   428: getstatic 25	com/android/mms/ServiceCategoryUpdateAsyncTask:TAG	Ljava/lang/String;
    //   431: new 280	java/lang/StringBuilder
    //   434: dup
    //   435: invokespecial 281	java/lang/StringBuilder:<init>	()V
    //   438: ldc_w 283
    //   441: invokevirtual 287	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   444: lload 9
    //   446: lload 7
    //   448: lsub
    //   449: invokevirtual 290	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   452: ldc_w 292
    //   455: invokevirtual 287	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   458: invokevirtual 295	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   461: invokestatic 301	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   464: pop
    //   465: aload_1
    //   466: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	467	0	this	ServiceCategoryUpdateAsyncTask
    //   0	467	1	paramVarArgs	Void[]
    //   70	39	2	i	int
    //   79	39	3	j	int
    //   88	39	4	k	int
    //   123	100	5	m	int
    //   146	21	6	n	int
    //   12	435	7	l1	long
    //   308	137	9	l2	long
    //   7	219	11	localContentResolver	android.content.ContentResolver
    //   243	58	11	localException	Exception
    //   352	9	11	localObject1	Object
    //   21	266	12	localArrayList	java.util.ArrayList
    //   114	86	13	localObject2	Object
    //   133	9	14	str	String
    // Exception table:
    //   from	to	target	type
    //   225	240	243	java/lang/Exception
    //   62	98	352	finally
    //   98	148	352	finally
    //   153	218	352	finally
    //   225	240	352	finally
    //   245	257	352	finally
    //   274	292	363	java/lang/Exception
    //   292	305	363	java/lang/Exception
    //   274	292	422	finally
    //   292	305	422	finally
    //   364	375	422	finally
  }
  
  protected void onPostExecute(Boolean paramBoolean)
  {
    if (paramBoolean.booleanValue())
    {
      paramBoolean = PreferenceManager.getDefaultSharedPreferences(MmsApp.getApp()).edit();
      paramBoolean.putLong("pref_service_category_upadate_time", System.currentTimeMillis());
      paramBoolean.commit();
      paramBoolean = mUpdateListeners.iterator();
      while (paramBoolean.hasNext()) {
        ((IServiceCategoryUpdateListener)paramBoolean.next()).onUpdateSuccess();
      }
    }
    paramBoolean = mUpdateListeners.iterator();
    while (paramBoolean.hasNext()) {
      ((IServiceCategoryUpdateListener)paramBoolean.next()).onUpdateSuccess();
    }
    try
    {
      mUpdateListeners.clear();
      sUpdateTask = null;
      mContext = null;
      return;
    }
    finally {}
  }
  
  public static abstract interface IServiceCategoryUpdateListener
  {
    public abstract void onUpdateSuccess();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ServiceCategoryUpdateAsyncTask
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */