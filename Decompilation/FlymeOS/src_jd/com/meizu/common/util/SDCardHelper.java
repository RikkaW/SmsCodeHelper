package com.meizu.common.util;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Observable;
import android.os.StatFs;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SDCardHelper
  extends BroadcastReceiver
{
  private static SDCardHelper sInstance = null;
  private Context mContext;
  private boolean mIsMounted = true;
  private List<MountPoint> mMountPathList = Collections.synchronizedList(new ArrayList());
  private final SDCardStateObservable mStateObservable = new SDCardStateObservable(null);
  private Method sDescription;
  private Method sIsRemovable;
  private Method sPath;
  private Method sVolumeState;
  
  private SDCardHelper(Context paramContext)
  {
    mContext = paramContext;
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
    localIntentFilter.addAction("android.intent.action.MEDIA_EJECT");
    localIntentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
    localIntentFilter.addDataScheme("file");
    mContext.registerReceiver(this, localIntentFilter);
    getMountPointList(paramContext);
    mIsMounted = isSDCardMounted();
  }
  
  public static void createInstance(Context paramContext)
  {
    if (sInstance == null) {
      sInstance = new SDCardHelper(paramContext);
    }
  }
  
  public static SDCardHelper getInstance()
  {
    return sInstance;
  }
  
  /* Error */
  public List<MountPoint> getMountPointList(Context paramContext)
  {
    // Byte code:
    //   0: aload_1
    //   1: ldc 105
    //   3: invokevirtual 109	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   6: checkcast 111	android/os/storage/StorageManager
    //   9: astore 4
    //   11: aload_0
    //   12: getfield 58	com/meizu/common/util/SDCardHelper:mMountPathList	Ljava/util/List;
    //   15: invokeinterface 116 1 0
    //   20: aload 4
    //   22: invokevirtual 122	java/lang/Object:getClass	()Ljava/lang/Class;
    //   25: ldc 124
    //   27: iconst_0
    //   28: anewarray 126	java/lang/Class
    //   31: invokevirtual 130	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   34: aload 4
    //   36: iconst_0
    //   37: anewarray 118	java/lang/Object
    //   40: invokevirtual 136	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   43: checkcast 138	[Ljava/lang/Object;
    //   46: checkcast 138	[Ljava/lang/Object;
    //   49: astore 5
    //   51: aload 5
    //   53: ifnull +264 -> 317
    //   56: aload 5
    //   58: arraylength
    //   59: istore_3
    //   60: iconst_0
    //   61: istore_2
    //   62: iload_2
    //   63: iload_3
    //   64: if_icmpge +253 -> 317
    //   67: aload 5
    //   69: iload_2
    //   70: aaload
    //   71: astore 6
    //   73: new 8	com/meizu/common/util/SDCardHelper$MountPoint
    //   76: dup
    //   77: aload_0
    //   78: invokespecial 141	com/meizu/common/util/SDCardHelper$MountPoint:<init>	(Lcom/meizu/common/util/SDCardHelper;)V
    //   81: astore 7
    //   83: aload_0
    //   84: getfield 143	com/meizu/common/util/SDCardHelper:sDescription	Ljava/lang/reflect/Method;
    //   87: ifnull +24 -> 111
    //   90: aload_0
    //   91: getfield 145	com/meizu/common/util/SDCardHelper:sPath	Ljava/lang/reflect/Method;
    //   94: ifnull +17 -> 111
    //   97: aload_0
    //   98: getfield 147	com/meizu/common/util/SDCardHelper:sIsRemovable	Ljava/lang/reflect/Method;
    //   101: ifnull +10 -> 111
    //   104: aload_0
    //   105: getfield 149	com/meizu/common/util/SDCardHelper:sVolumeState	Ljava/lang/reflect/Method;
    //   108: ifnonnull +85 -> 193
    //   111: aload_0
    //   112: aload 6
    //   114: invokevirtual 122	java/lang/Object:getClass	()Ljava/lang/Class;
    //   117: ldc -105
    //   119: iconst_1
    //   120: anewarray 126	java/lang/Class
    //   123: dup
    //   124: iconst_0
    //   125: ldc 80
    //   127: aastore
    //   128: invokevirtual 154	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   131: putfield 143	com/meizu/common/util/SDCardHelper:sDescription	Ljava/lang/reflect/Method;
    //   134: aload_0
    //   135: aload 6
    //   137: invokevirtual 122	java/lang/Object:getClass	()Ljava/lang/Class;
    //   140: ldc -100
    //   142: iconst_0
    //   143: anewarray 126	java/lang/Class
    //   146: invokevirtual 154	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   149: putfield 145	com/meizu/common/util/SDCardHelper:sPath	Ljava/lang/reflect/Method;
    //   152: aload_0
    //   153: aload 6
    //   155: invokevirtual 122	java/lang/Object:getClass	()Ljava/lang/Class;
    //   158: ldc -98
    //   160: iconst_0
    //   161: anewarray 126	java/lang/Class
    //   164: invokevirtual 154	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   167: putfield 147	com/meizu/common/util/SDCardHelper:sIsRemovable	Ljava/lang/reflect/Method;
    //   170: aload_0
    //   171: aload 4
    //   173: invokevirtual 122	java/lang/Object:getClass	()Ljava/lang/Class;
    //   176: ldc -96
    //   178: iconst_1
    //   179: anewarray 126	java/lang/Class
    //   182: dup
    //   183: iconst_0
    //   184: ldc -94
    //   186: aastore
    //   187: invokevirtual 130	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   190: putfield 149	com/meizu/common/util/SDCardHelper:sVolumeState	Ljava/lang/reflect/Method;
    //   193: aload_0
    //   194: getfield 145	com/meizu/common/util/SDCardHelper:sPath	Ljava/lang/reflect/Method;
    //   197: aload 6
    //   199: iconst_0
    //   200: anewarray 118	java/lang/Object
    //   203: invokevirtual 136	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   206: checkcast 162	java/lang/String
    //   209: astore 8
    //   211: aload 7
    //   213: aload_0
    //   214: getfield 143	com/meizu/common/util/SDCardHelper:sDescription	Ljava/lang/reflect/Method;
    //   217: aload 6
    //   219: iconst_1
    //   220: anewarray 118	java/lang/Object
    //   223: dup
    //   224: iconst_0
    //   225: aload_1
    //   226: aastore
    //   227: invokevirtual 136	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   230: checkcast 162	java/lang/String
    //   233: invokestatic 166	com/meizu/common/util/SDCardHelper$MountPoint:access$300	(Lcom/meizu/common/util/SDCardHelper$MountPoint;Ljava/lang/String;)V
    //   236: aload 7
    //   238: aload 8
    //   240: invokestatic 169	com/meizu/common/util/SDCardHelper$MountPoint:access$400	(Lcom/meizu/common/util/SDCardHelper$MountPoint;Ljava/lang/String;)V
    //   243: aload 7
    //   245: aload_0
    //   246: getfield 149	com/meizu/common/util/SDCardHelper:sVolumeState	Ljava/lang/reflect/Method;
    //   249: aload 4
    //   251: iconst_1
    //   252: anewarray 118	java/lang/Object
    //   255: dup
    //   256: iconst_0
    //   257: aload 8
    //   259: aastore
    //   260: invokevirtual 136	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   263: checkcast 162	java/lang/String
    //   266: invokestatic 172	com/meizu/common/util/SDCardHelper$MountPoint:access$500	(Lcom/meizu/common/util/SDCardHelper$MountPoint;Ljava/lang/String;)V
    //   269: aload 7
    //   271: aload_0
    //   272: getfield 147	com/meizu/common/util/SDCardHelper:sIsRemovable	Ljava/lang/reflect/Method;
    //   275: aload 6
    //   277: iconst_0
    //   278: anewarray 118	java/lang/Object
    //   281: invokevirtual 136	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   284: checkcast 174	java/lang/Boolean
    //   287: invokevirtual 177	java/lang/Boolean:booleanValue	()Z
    //   290: invokestatic 181	com/meizu/common/util/SDCardHelper$MountPoint:access$600	(Lcom/meizu/common/util/SDCardHelper$MountPoint;Z)V
    //   293: aload_0
    //   294: getfield 58	com/meizu/common/util/SDCardHelper:mMountPathList	Ljava/util/List;
    //   297: aload 7
    //   299: invokeinterface 185 2 0
    //   304: pop
    //   305: iload_2
    //   306: iconst_1
    //   307: iadd
    //   308: istore_2
    //   309: goto -247 -> 62
    //   312: astore_1
    //   313: aload_1
    //   314: invokevirtual 188	java/lang/IllegalAccessException:printStackTrace	()V
    //   317: aload_0
    //   318: getfield 58	com/meizu/common/util/SDCardHelper:mMountPathList	Ljava/util/List;
    //   321: areturn
    //   322: astore_1
    //   323: aload_1
    //   324: invokevirtual 189	java/lang/reflect/InvocationTargetException:printStackTrace	()V
    //   327: goto -10 -> 317
    //   330: astore_1
    //   331: aload_1
    //   332: invokevirtual 190	java/lang/NoSuchMethodException:printStackTrace	()V
    //   335: goto -18 -> 317
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	338	0	this	SDCardHelper
    //   0	338	1	paramContext	Context
    //   61	248	2	i	int
    //   59	6	3	j	int
    //   9	241	4	localStorageManager	android.os.storage.StorageManager
    //   49	19	5	arrayOfObject	Object[]
    //   71	205	6	localObject	Object
    //   81	217	7	localMountPoint	MountPoint
    //   209	49	8	str	String
    // Exception table:
    //   from	to	target	type
    //   20	51	312	java/lang/IllegalAccessException
    //   56	60	312	java/lang/IllegalAccessException
    //   73	111	312	java/lang/IllegalAccessException
    //   111	193	312	java/lang/IllegalAccessException
    //   193	305	312	java/lang/IllegalAccessException
    //   20	51	322	java/lang/reflect/InvocationTargetException
    //   56	60	322	java/lang/reflect/InvocationTargetException
    //   73	111	322	java/lang/reflect/InvocationTargetException
    //   111	193	322	java/lang/reflect/InvocationTargetException
    //   193	305	322	java/lang/reflect/InvocationTargetException
    //   20	51	330	java/lang/NoSuchMethodException
    //   56	60	330	java/lang/NoSuchMethodException
    //   73	111	330	java/lang/NoSuchMethodException
    //   111	193	330	java/lang/NoSuchMethodException
    //   193	305	330	java/lang/NoSuchMethodException
  }
  
  public MountPoint getOtgMountPoint()
  {
    Iterator localIterator = mMountPathList.iterator();
    while (localIterator.hasNext())
    {
      MountPoint localMountPoint = (MountPoint)localIterator.next();
      if ((mIsExternal) && (mPath.contains("otg"))) {
        return localMountPoint;
      }
    }
    return null;
  }
  
  public MountPoint getSDCardMountPoint()
  {
    Iterator localIterator = mMountPathList.iterator();
    while (localIterator.hasNext())
    {
      MountPoint localMountPoint = (MountPoint)localIterator.next();
      if ((mIsExternal) && (mPath.contains("sdcard"))) {
        return localMountPoint;
      }
    }
    return null;
  }
  
  public MountPoint getStorageMountPoint()
  {
    Iterator localIterator = mMountPathList.iterator();
    while (localIterator.hasNext())
    {
      MountPoint localMountPoint = (MountPoint)localIterator.next();
      if (!mIsExternal) {
        return localMountPoint;
      }
    }
    return null;
  }
  
  public boolean isMounted()
  {
    return mIsMounted;
  }
  
  public boolean isOtgMounted()
  {
    if (getOtgMountPoint() == null) {
      return false;
    }
    return getOtgMountPoint().isMounted();
  }
  
  public boolean isSDCardMounted()
  {
    if (getSDCardMountPoint() == null) {
      return false;
    }
    return getSDCardMountPoint().isMounted();
  }
  
  public void notifyStateChanged(Intent paramIntent, boolean paramBoolean)
  {
    mStateObservable.notifyStateChanged(paramIntent, paramBoolean);
  }
  
  public void onDestory()
  {
    mContext.unregisterReceiver(this);
    sInstance = null;
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getAction();
    getMountPointList(paramContext);
    if ("android.intent.action.MEDIA_MOUNTED".equals(str))
    {
      mIsMounted = true;
      notifyStateChanged(paramIntent, true);
    }
    do
    {
      return;
      if ("android.intent.action.MEDIA_EJECT".equals(str))
      {
        mIsMounted = false;
        notifyStateChanged(paramIntent, false);
        return;
      }
    } while (!"android.intent.action.MEDIA_UNMOUNTED".equals(str));
    mIsMounted = false;
    notifyStateChanged(paramIntent, false);
  }
  
  public void registerStateObserver(SDCardStateObserver paramSDCardStateObserver)
  {
    mStateObservable.registerObserver(paramSDCardStateObserver);
  }
  
  public void unregisterStateObserver(SDCardStateObserver paramSDCardStateObserver)
  {
    mStateObservable.unregisterObserver(paramSDCardStateObserver);
  }
  
  public class MountPoint
  {
    private String mDescription;
    private boolean mIsExternal;
    private String mMountedState;
    private String mPath;
    
    public MountPoint() {}
    
    private void setDescription(String paramString)
    {
      mDescription = paramString;
    }
    
    private void setExternal(boolean paramBoolean)
    {
      mIsExternal = paramBoolean;
    }
    
    private void setMountedState(String paramString)
    {
      mMountedState = paramString;
    }
    
    private void setPath(String paramString)
    {
      mPath = paramString;
    }
    
    @SuppressLint({"NewApi"})
    public long availableSpace()
    {
      if (!isMounted()) {
        return 0L;
      }
      StatFs localStatFs = new StatFs(mPath);
      long l = localStatFs.getBlockSizeLong();
      return localStatFs.getAvailableBlocksLong() * l;
    }
    
    public String getDescription()
    {
      return mDescription;
    }
    
    public String getMountedState()
    {
      return mMountedState;
    }
    
    public String getPath()
    {
      return mPath;
    }
    
    @SuppressLint({"NewApi"})
    public long getTotalBlocks()
    {
      if (!isMounted()) {
        return 0L;
      }
      StatFs localStatFs = new StatFs(mPath);
      long l = localStatFs.getBlockSizeLong();
      return localStatFs.getBlockCountLong() * l;
    }
    
    public boolean isExternal()
    {
      return mIsExternal;
    }
    
    public boolean isMounted()
    {
      return mMountedState.equals("mounted");
    }
  }
  
  static class SDCardStateObservable
    extends Observable<SDCardHelper.SDCardStateObserver>
  {
    public void notifyStateChanged(Intent paramIntent, boolean paramBoolean)
    {
      synchronized (mObservers)
      {
        int i = mObservers.size() - 1;
        while (i >= 0)
        {
          ((SDCardHelper.SDCardStateObserver)mObservers.get(i)).onChanged(paramIntent, paramBoolean);
          i -= 1;
        }
        return;
      }
    }
  }
  
  public static abstract interface SDCardStateObserver
  {
    public abstract void onChanged(Intent paramIntent, boolean paramBoolean);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.util.SDCardHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */