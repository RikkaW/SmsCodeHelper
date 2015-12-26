package com.ted.sdk.location;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import anu;
import com.ted.sdk.yellow.entry.MessageItem.SpItem.SpType;
import com.ted.sdk.yellow.util.ProvinceUtils;
import com.ted.sdk.yellow.util.SdkSharedPrefs;
import ff;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Location
{
  static final int DEFAULT_INDEX = 1023;
  private static File FILES_FOLDER;
  private static final String LOCATION_FILE = "num_segment.dat";
  private static final String N_170 = "N_170";
  private static final String PFEFIX_DATA_FILE = "N_PREFIX";
  private static final String PREFIX = "PREFIX";
  private static final String TAG = Location.class.getSimpleName();
  
  private static int get170Offsite(int paramInt)
  {
    int m = 1023;
    Object localObject = new File(FILES_FOLDER, "N_170");
    if (!((File)localObject).exists()) {
      return 1023;
    }
    localObject = new RandomAccessFile((File)localObject, "r");
    int n = (int)(((RandomAccessFile)localObject).length() / 4L);
    int i = 0;
    int j = n - 1;
    for (;;)
    {
      int k = m;
      if (i <= j)
      {
        k = m;
        if (i <= n - 1) {
          if (j <= n - 1) {
            break label100;
          }
        }
      }
      label100:
      int i1;
      int i2;
      for (k = m;; k = i1 & 0x3FF)
      {
        ((RandomAccessFile)localObject).close();
        return k;
        k = (j - i >> 1) + i;
        ((RandomAccessFile)localObject).seek(k * 4);
        i1 = ((RandomAccessFile)localObject).readInt();
        i2 = (0x7FFFFC00 & i1) >> 10;
        if (paramInt != i2) {
          break;
        }
      }
      if (paramInt < i2) {
        j = k - 1;
      } else {
        i = k + 1;
      }
    }
  }
  
  public static String getHostLocation(String paramString)
  {
    Pair localPair = getProvinceCity(paramString);
    if (localPair == null) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder((String)first);
    if ((!ProvinceUtils.PROVINCE_BJ.equals(first)) && (!ProvinceUtils.PROVINCE_SH.equals(first)) && (!ProvinceUtils.PROVINCE_TJ.equals(first)) && (!ProvinceUtils.PROVINCE_CQ.equals(second))) {
      localStringBuilder.append((String)second);
    }
    localPair = null;
    MessageItem.SpItem.SpType localSpType = getOperator(paramString);
    paramString = localPair;
    if (localSpType != null) {
      switch (localSpType)
      {
      default: 
        paramString = "";
      }
    }
    for (;;)
    {
      if (!TextUtils.isEmpty(paramString)) {
        localStringBuilder.append(paramString);
      }
      return localStringBuilder.toString();
      paramString = "电信";
      continue;
      paramString = "联通";
      continue;
      paramString = "移动";
    }
  }
  
  private static int getOffsite(String paramString, int paramInt)
  {
    paramString = new File(FILES_FOLDER, paramString);
    if (!paramString.exists()) {
      return 1023;
    }
    paramString = new RandomAccessFile(paramString, "r");
    paramString.seek(paramInt * 4);
    paramInt = paramString.readInt();
    paramString.close();
    return paramInt;
  }
  
  public static MessageItem.SpItem.SpType getOperator(String paramString)
  {
    paramString = prepareMsisdn(paramString);
    if ((paramString == null) || (paramString.length() < 5)) {
      return null;
    }
    if ((paramString.length() >= 6) && ("1349".equals(paramString.substring(2, 6)))) {
      return MessageItem.SpItem.SpType.CE;
    }
    switch (Integer.valueOf(paramString.substring(2, 5)).intValue())
    {
    case 140: 
    case 141: 
    case 142: 
    case 143: 
    case 144: 
    case 146: 
    case 148: 
    case 154: 
    case 160: 
    case 161: 
    case 162: 
    case 163: 
    case 164: 
    case 165: 
    case 166: 
    case 167: 
    case 168: 
    case 169: 
    case 170: 
    case 171: 
    case 172: 
    case 173: 
    case 174: 
    case 175: 
    case 179: 
    default: 
      switch (Integer.valueOf(paramString.substring(2, 6)).intValue())
      {
      case 1702: 
      case 1703: 
      case 1704: 
      case 1706: 
      default: 
        return null;
      case 1700: 
      case 1701: 
        return MessageItem.SpItem.SpType.CE;
      }
    case 134: 
    case 135: 
    case 136: 
    case 137: 
    case 138: 
    case 139: 
    case 147: 
    case 150: 
    case 151: 
    case 152: 
    case 157: 
    case 158: 
    case 159: 
    case 178: 
    case 182: 
    case 183: 
    case 184: 
    case 187: 
    case 188: 
      return MessageItem.SpItem.SpType.CM;
    case 130: 
    case 131: 
    case 132: 
    case 145: 
    case 155: 
    case 156: 
    case 176: 
    case 185: 
    case 186: 
      return MessageItem.SpItem.SpType.CU;
    }
    return MessageItem.SpItem.SpType.CE;
    return MessageItem.SpItem.SpType.CM;
    return MessageItem.SpItem.SpType.CU;
  }
  
  private static Pair<String, String> getProvCityPair(int paramInt)
  {
    int i = paramInt & 0x1F;
    paramInt = paramInt - i >> 5;
    return Pair.create(ProvinceUtils.ALL[paramInt], ProvinceUtils.CITIES[paramInt][i]);
  }
  
  public static Pair<String, String> getProvinceCity(String paramString)
  {
    if (FILES_FOLDER == null) {
      throw new RuntimeException("Location.init(Context ctx) need be called before!");
    }
    Object localObject = AreaCodeLocation.getAreaCodeLocation(paramString);
    if (localObject != null) {
      return (Pair<String, String>)localObject;
    }
    paramString = prepareMsisdn(paramString);
    if (paramString.length() < 9) {
      return null;
    }
    int i = CmSpecailLocation.getInSpecialIdx(paramString);
    if (i != 1023) {
      return getProvCityPair(i);
    }
    if ("349".equals(paramString.substring(3, 6))) {
      return Pair.create("中国电信", "卫星卡");
    }
    localObject = "N_PREFIX".replace("PREFIX", paramString.subSequence(2, 6));
    int k = Integer.valueOf(paramString.substring(6, 9)).intValue();
    int j;
    try
    {
      j = getOffsite((String)localObject, k / 3);
      i = j;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localIOException.printStackTrace();
        continue;
        j = (i & 0x3FF00000) >> 20;
        continue;
        j = (i & 0xFFC00) >> 10;
        continue;
        j = i & 0x3FF;
      }
    }
    if (i != 1023)
    {
      j = i;
      if (k != 999) {}
      switch (k % 3)
      {
      default: 
        j = i;
        if (j == 1023) {
          return null;
        }
        break;
      }
      return getProvCityPair(j);
    }
    if ("170".equals(paramString.substring(2, 5)))
    {
      j = Integer.valueOf(paramString.substring(2, 9)).intValue();
      try
      {
        j = get170Offsite(j);
        i = j;
      }
      catch (IOException paramString)
      {
        for (;;)
        {
          paramString.printStackTrace();
        }
      }
      if (i != 1023) {
        return getProvCityPair(i);
      }
    }
    return null;
  }
  
  public static void init(Context paramContext)
  {
    FILES_FOLDER = paramContext.getFilesDir();
    if (SdkSharedPrefs.isNumSegmentDataLoaded(paramContext)) {
      return;
    }
    ExecutorService localExecutorService = Executors.newSingleThreadExecutor(new HandlerThreadFactory(null));
    localExecutorService.execute(new Location.1(paramContext));
    localExecutorService.shutdown();
  }
  
  private static void moveAssetsFile(Context paramContext)
  {
    CmSpecailLocation.getInSpecialIdx(null);
    AreaCodeLocation.getAreaCode(null, null);
    try
    {
      paramContext = paramContext.getAssets().open("num_segment.dat");
      if ((paramContext != null) && (FILES_FOLDER != null)) {
        ff.a(paramContext, FILES_FOLDER.toString() + File.separator);
      }
      return;
    }
    catch (IOException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = null;
      }
    }
  }
  
  private static String prepareMsisdn(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return paramString;
    }
    String str = anu.b(paramString.trim());
    paramString = str;
    if (!str.startsWith("86")) {
      paramString = "86" + str;
    }
    return paramString;
  }
  
  public static void setFilesFolder(File paramFile)
  {
    FILES_FOLDER = paramFile;
  }
  
  static class HandlerThreadFactory
    implements ThreadFactory
  {
    public Thread newThread(Runnable paramRunnable)
    {
      paramRunnable = new Thread(paramRunnable);
      paramRunnable.setUncaughtExceptionHandler(new Location.MyUncaughtExceptionHandler(null));
      return paramRunnable;
    }
  }
  
  static class MyUncaughtExceptionHandler
    implements Thread.UncaughtExceptionHandler
  {
    public void uncaughtException(Thread paramThread, Throwable paramThrowable)
    {
      Log.e(Location.TAG, "caught: " + paramThrowable);
      paramThrowable.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.location.Location
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */