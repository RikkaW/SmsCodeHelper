package com.xiaomi.smack.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.SerializedAsyncTaskProcessor;
import com.xiaomi.channel.commonutils.misc.SerializedAsyncTaskProcessor.SerializedAsyncTask;
import com.xiaomi.push.providers.TrafficDatabaseHelper;
import com.xiaomi.push.service.XMPushService;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TrafficUtils
{
  private static TrafficDatabaseHelper dbHelper = null;
  private static String imsi;
  private static final Object lock;
  private static SerializedAsyncTaskProcessor mAsyncProcessor = new SerializedAsyncTaskProcessor(true);
  private static int networkType = -1;
  private static List<TrafficInfo> trafficList;
  
  static
  {
    lock = new Object();
    trafficList = Collections.synchronizedList(new ArrayList());
    imsi = "";
  }
  
  public static void distributionTraffic(XMPushService paramXMPushService, String paramString, long paramLong1, boolean paramBoolean, long paramLong2)
  {
    if ((paramXMPushService == null) || (TextUtils.isEmpty(paramString)) || (!"com.xiaomi.xmsf".equals(paramXMPushService.getPackageName())) || ("com.xiaomi.xmsf".equals(paramString))) {}
    for (;;)
    {
      return;
      int j = getNetworkType(paramXMPushService);
      if (-1 == j) {
        continue;
      }
      synchronized (lock)
      {
        boolean bool = trafficList.isEmpty();
        if (paramBoolean) {}
        for (int i = 1;; i = 0)
        {
          if (j != 0) {
            break label139;
          }
          str = getIMSI(paramXMPushService);
          insertTrafficInfo2List(new TrafficInfo(paramString, paramLong2, j, i, str, getTraffic(j, paramLong1)));
          if (!bool) {
            break;
          }
          mAsyncProcessor.addNewTaskWithDelayed(new SerializedAsyncTaskProcessor.SerializedAsyncTask()
          {
            public void process()
            {
              ArrayList localArrayList;
              synchronized (TrafficUtils.lock)
              {
                localArrayList = new ArrayList(TrafficUtils.trafficList);
              }
              try
              {
                TrafficUtils.trafficList.clear();
                TrafficUtils.insertTraffic(val$pushService, localArrayList);
                return;
              }
              finally {}
              localObject1 = finally;
              throw ((Throwable)localObject1);
            }
          }, 5000L);
          return;
        }
        label139:
        String str = "";
      }
    }
  }
  
  private static int getActiveNetworkType(Context paramContext)
  {
    for (;;)
    {
      try
      {
        paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
        if (paramContext == null) {
          return -1;
        }
      }
      catch (Exception paramContext)
      {
        return -1;
      }
      try
      {
        paramContext = paramContext.getActiveNetworkInfo();
        if (paramContext != null) {
          return paramContext.getType();
        }
      }
      catch (Exception paramContext) {}
    }
    return -1;
  }
  
  private static String getIMSI(Context paramContext)
  {
    for (;;)
    {
      try
      {
        if (!TextUtils.isEmpty(imsi))
        {
          paramContext = imsi;
          return paramContext;
        }
      }
      finally {}
      try
      {
        paramContext = (TelephonyManager)paramContext.getSystemService("phone");
        if (paramContext != null) {
          imsi = paramContext.getSubscriberId();
        }
      }
      catch (Exception paramContext)
      {
        continue;
      }
      paramContext = imsi;
    }
  }
  
  private static int getNetworkType(Context paramContext)
  {
    if (networkType == -1) {
      networkType = getActiveNetworkType(paramContext);
    }
    return networkType;
  }
  
  private static long getTraffic(int paramInt, long paramLong)
  {
    if (paramInt == 0) {}
    for (paramInt = 13;; paramInt = 11) {
      return paramInt * paramLong / 10L;
    }
  }
  
  private static TrafficDatabaseHelper getTrafficDatabaseHelper(Context paramContext)
  {
    if (dbHelper != null) {
      return dbHelper;
    }
    dbHelper = new TrafficDatabaseHelper(paramContext);
    return dbHelper;
  }
  
  public static int getTrafficFlow(String paramString)
  {
    try
    {
      int i = paramString.getBytes("UTF-8").length;
      return i;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException) {}
    return paramString.getBytes().length;
  }
  
  private static void insertTraffic(Context paramContext, List<TrafficInfo> paramList)
  {
    try
    {
      synchronized (TrafficDatabaseHelper.DataBaseLock)
      {
        paramContext = getTrafficDatabaseHelper(paramContext).getWritableDatabase();
        paramContext.beginTransaction();
        try
        {
          paramList = paramList.iterator();
          while (paramList.hasNext())
          {
            TrafficInfo localTrafficInfo = (TrafficInfo)paramList.next();
            ContentValues localContentValues = new ContentValues();
            localContentValues.put("package_name", packageName);
            localContentValues.put("message_ts", Long.valueOf(messageTs));
            localContentValues.put("network_type", Integer.valueOf(networkType));
            localContentValues.put("bytes", Long.valueOf(bytes));
            localContentValues.put("rcv", Integer.valueOf(rcv));
            localContentValues.put("imsi", imsi);
            paramContext.insert("traffic", null, localContentValues);
            continue;
            paramContext = finally;
          }
        }
        finally
        {
          paramContext.endTransaction();
        }
      }
      paramContext.setTransactionSuccessful();
    }
    catch (SQLiteException paramContext)
    {
      MyLog.e(paramContext);
      return;
    }
    paramContext.endTransaction();
  }
  
  private static void insertTrafficInfo2List(TrafficInfo paramTrafficInfo)
  {
    Iterator localIterator = trafficList.iterator();
    while (localIterator.hasNext())
    {
      TrafficInfo localTrafficInfo = (TrafficInfo)localIterator.next();
      if (localTrafficInfo.canAccumulate(paramTrafficInfo))
      {
        bytes += bytes;
        return;
      }
    }
    trafficList.add(paramTrafficInfo);
  }
  
  static class TrafficInfo
  {
    public long bytes = 0L;
    public String imsi = "";
    public long messageTs = 0L;
    public int networkType = -1;
    public String packageName = "";
    public int rcv = -1;
    
    public TrafficInfo(String paramString1, long paramLong1, int paramInt1, int paramInt2, String paramString2, long paramLong2)
    {
      packageName = paramString1;
      messageTs = paramLong1;
      networkType = paramInt1;
      rcv = paramInt2;
      imsi = paramString2;
      bytes = paramLong2;
    }
    
    public boolean canAccumulate(TrafficInfo paramTrafficInfo)
    {
      return (TextUtils.equals(packageName, packageName)) && (TextUtils.equals(imsi, imsi)) && (networkType == networkType) && (rcv == rcv) && (Math.abs(messageTs - messageTs) <= 5000L);
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.util.TrafficUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */