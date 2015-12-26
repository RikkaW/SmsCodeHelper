package com.ted.sdk.yellow.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.util.Pair;
import aoh;
import apm;
import ff;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class AssetsDatabaseHelper
{
  private static final String BUBBLE_DB = "sms_core.db";
  private static final String BUILT_IN_DATA = "vcard.dat";
  private static final String NUMCACHE_SQLITE_NAME = "numcache.db";
  private static final String TAG = AssetsDatabaseHelper.class.getSimpleName();
  private static AssetsDatabaseHelper sInstance = null;
  private volatile boolean mBubbleInitialized;
  private Context mContext;
  
  private AssetsDatabaseHelper(Context paramContext)
  {
    mContext = paramContext;
  }
  
  private void bulkInsert(HashSet<Pair<String, String>> paramHashSet)
  {
    aoh localaoh = aoh.a(mContext);
    localaoh.getWritableDatabase().beginTransaction();
    paramHashSet = paramHashSet.iterator();
    for (;;)
    {
      if (!paramHashSet.hasNext())
      {
        localaoh.getWritableDatabase().setTransactionSuccessful();
        localaoh.getWritableDatabase().endTransaction();
        return;
      }
      Pair localPair = (Pair)paramHashSet.next();
      String str = apm.a((String)second);
      localaoh.a((String)first, str, 0);
    }
  }
  
  private void doBubbleLoad()
  {
    if (SdkSharedPrefs.isBubbleDataLoaded(mContext))
    {
      initBubbleMsgEngine();
      return;
    }
    String str = mContext.getFilesDir() + File.separator;
    ff.b(mContext, "sms_core.db", str);
    initBubbleMsgEngine();
    SdkSharedPrefs.setBubbleDataLoaded(mContext, true);
  }
  
  private void doLoad()
  {
    Object localObject3 = mContext.getCacheDir().getAbsolutePath();
    if (!ff.a(mContext, "vcard.dat", (String)localObject3)) {}
    for (;;)
    {
      return;
      Object localObject4 = localObject3 + File.separator + "vcard.dat";
      try
      {
        Object localObject1 = new FileInputStream((String)localObject4);
        if ((localObject1 != null) && (ff.a((InputStream)localObject1, localObject3 + File.separator)))
        {
          localObject1 = new File(localObject3 + File.separator + "numcache.db");
          localObject4 = new File((String)localObject4);
          if ((localObject1 != null) && (((File)localObject1).exists()) && (((File)localObject1).length() == 0L))
          {
            ((File)localObject1).delete();
            if ((localObject4 == null) || (!((File)localObject4).exists())) {
              continue;
            }
            ((File)localObject4).delete();
          }
        }
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        Object localObject2;
        for (;;)
        {
          localFileNotFoundException.printStackTrace();
          localObject2 = null;
        }
        localObject3 = readFromAsset((String)localObject3);
        if ((localObject2 != null) && (((File)localObject2).exists())) {
          ((File)localObject2).delete();
        }
        if ((localObject4 != null) && (((File)localObject4).exists())) {
          ((File)localObject4).delete();
        }
        bulkInsert((HashSet)localObject3);
        SdkSharedPrefs.setLocalDataLoaded(mContext, true);
      }
    }
  }
  
  public static AssetsDatabaseHelper getInstance(Context paramContext)
  {
    try
    {
      if (sInstance == null) {
        sInstance = new AssetsDatabaseHelper(paramContext);
      }
      paramContext = sInstance;
      return paramContext;
    }
    finally {}
  }
  
  private HashSet<Pair<String, String>> readFromAsset(String paramString)
  {
    AssetsDatabaseManager.initManager(mContext);
    paramString = AssetsDatabaseManager.getManager().getDatabase("numcache.db", paramString);
    Cursor localCursor = paramString.rawQuery("SELECT phone, json FROM num", null);
    HashSet localHashSet = new HashSet(localCursor.getCount());
    if ((localCursor != null) && (localCursor.moveToFirst())) {
      do
      {
        localHashSet.add(Pair.create(localCursor.getString(0), localCursor.getString(1)));
      } while (localCursor.moveToNext());
    }
    if (localCursor != null) {
      localCursor.close();
    }
    if (paramString != null) {
      paramString.close();
    }
    return localHashSet;
  }
  
  public void initBubbleMsgEngine()
  {
    if (mBubbleInitialized) {
      return;
    }
    mBubbleInitialized = true;
  }
  
  public void loadBubbleData()
  {
    ExecutorService localExecutorService = Executors.newSingleThreadExecutor(new HandlerThreadFactory(null));
    localExecutorService.execute(new AssetsDatabaseHelper.2(this));
    localExecutorService.shutdown();
  }
  
  public void loadLocalData()
  {
    if (SdkSharedPrefs.isLocalDataLoaded(mContext)) {
      return;
    }
    ExecutorService localExecutorService = Executors.newSingleThreadExecutor(new HandlerThreadFactory(null));
    localExecutorService.execute(new AssetsDatabaseHelper.1(this));
    localExecutorService.shutdown();
  }
  
  static class HandlerThreadFactory
    implements ThreadFactory
  {
    public Thread newThread(Runnable paramRunnable)
    {
      paramRunnable = new Thread(paramRunnable);
      paramRunnable.setUncaughtExceptionHandler(new AssetsDatabaseHelper.MyUncaughtExceptionHandler(null));
      return paramRunnable;
    }
  }
  
  static class MyUncaughtExceptionHandler
    implements Thread.UncaughtExceptionHandler
  {
    public void uncaughtException(Thread paramThread, Throwable paramThrowable)
    {
      Log.e(AssetsDatabaseHelper.TAG, "caught: " + paramThrowable);
      paramThrowable.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.util.AssetsDatabaseHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */