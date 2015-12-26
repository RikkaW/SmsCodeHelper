package com.xiaomi.mms.data;

import android.content.Context;
import com.android.mms.data.Contact;
import com.xiaomi.mms.transaction.MxTaskService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MxIdCache
{
  private static final List<MxCacheStatusListener> mStatusListener = new LinkedList();
  private static final Map<String, MxIdCacheItem> map = new HashMap();
  
  public static void addStatusListener(MxCacheStatusListener paramMxCacheStatusListener)
  {
    synchronized (mStatusListener)
    {
      if (!mStatusListener.contains(paramMxCacheStatusListener))
      {
        mStatusListener.add(paramMxCacheStatusListener);
        return;
      }
      throw new IllegalStateException("listener already added to cache");
    }
  }
  
  public static MxIdCacheItem get(String paramString)
  {
    try
    {
      paramString = get(paramString, false);
      return paramString;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public static MxIdCacheItem get(String paramString, boolean paramBoolean)
  {
    try
    {
      paramString = get(paramString, paramBoolean, true);
      return paramString;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  /* Error */
  private static MxIdCacheItem get(String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: astore 7
    //   6: iload_2
    //   7: ifeq +9 -> 16
    //   10: aload_0
    //   11: invokestatic 66	com/android/mms/data/Contact:normalizePhoneNumber	(Ljava/lang/String;)Ljava/lang/String;
    //   14: astore 7
    //   16: getstatic 27	com/xiaomi/mms/data/MxIdCache:map	Ljava/util/Map;
    //   19: aload 7
    //   21: invokeinterface 71 2 0
    //   26: checkcast 11	com/xiaomi/mms/data/MxIdCache$MxIdCacheItem
    //   29: astore 7
    //   31: aload 7
    //   33: ifnull +36 -> 69
    //   36: aload 7
    //   38: getfield 75	com/xiaomi/mms/data/MxIdCache$MxIdCacheItem:expire	J
    //   41: lstore_3
    //   42: invokestatic 81	java/lang/System:currentTimeMillis	()J
    //   45: lstore 5
    //   47: aload 7
    //   49: astore_0
    //   50: lload_3
    //   51: lload 5
    //   53: lcmp
    //   54: ifgt +10 -> 64
    //   57: iload_1
    //   58: ifeq +11 -> 69
    //   61: aload 7
    //   63: astore_0
    //   64: ldc 2
    //   66: monitorexit
    //   67: aload_0
    //   68: areturn
    //   69: aconst_null
    //   70: astore_0
    //   71: goto -7 -> 64
    //   74: astore_0
    //   75: ldc 2
    //   77: monitorexit
    //   78: aload_0
    //   79: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	80	0	paramString	String
    //   0	80	1	paramBoolean1	boolean
    //   0	80	2	paramBoolean2	boolean
    //   41	10	3	l1	long
    //   45	7	5	l2	long
    //   4	58	7	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   10	16	74	finally
    //   16	31	74	finally
    //   36	47	74	finally
  }
  
  public static MxIdCacheItem getOrQuery(Context paramContext, String paramString)
  {
    try
    {
      paramContext = getOrQuery(paramContext, paramString, true);
      return paramContext;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  public static MxIdCacheItem getOrQuery(Context paramContext, String paramString, boolean paramBoolean)
  {
    try
    {
      paramString = Contact.normalizePhoneNumber(paramString);
      MxIdCacheItem localMxIdCacheItem = get(paramString, paramBoolean, false);
      if ((localMxIdCacheItem == null) || (expire <= System.currentTimeMillis()) || (capability == -2147483648L)) {
        MxTaskService.queryStatus(paramContext, paramString);
      }
      return localMxIdCacheItem;
    }
    finally {}
  }
  
  public static void offline(String arg0)
  {
    int j = 1;
    for (;;)
    {
      try
      {
        String str = Contact.normalizePhoneNumber(???);
        MxIdCacheItem localMxIdCacheItem = (MxIdCacheItem)map.get(str);
        if ((localMxIdCacheItem == null) || (mid == null)) {
          break label152;
        }
        int i = j;
        if (capability <= 0L)
        {
          if (expire < System.currentTimeMillis()) {
            i = j;
          }
        }
        else
        {
          capability = 0L;
          transientState = true;
          localMxIdCacheItem.updateExpire();
          if (i == 0) {
            break label152;
          }
          synchronized (mStatusListener)
          {
            Iterator localIterator = mStatusListener.iterator();
            if (!localIterator.hasNext()) {
              break;
            }
            ((MxCacheStatusListener)localIterator.next()).onMxIdOffline(mid, str);
          }
        }
        i = 0;
      }
      finally {}
    }
    label152:
  }
  
  public static boolean online(String arg0, long paramLong, boolean paramBoolean)
  {
    boolean bool2 = false;
    for (;;)
    {
      String str;
      MxIdCacheItem localMxIdCacheItem;
      boolean bool1;
      int i;
      try
      {
        str = Contact.normalizePhoneNumber(???);
        localMxIdCacheItem = (MxIdCacheItem)map.get(str);
        bool1 = bool2;
        if (localMxIdCacheItem == null) {
          break label203;
        }
        bool1 = bool2;
        if (mid == null) {
          break label203;
        }
        long l2 = capability;
        long l1 = paramLong;
        if (paramBoolean) {
          l1 = paramLong | capability;
        }
        capability = l1;
        if (l2 != capability) {
          break label209;
        }
        if (expire >= System.currentTimeMillis()) {
          break label188;
        }
      }
      finally {}
      localMxIdCacheItem.updateExpire();
      if (!paramBoolean) {
        transientState = paramBoolean;
      }
      if (i != 0)
      {
        synchronized (mStatusListener)
        {
          Iterator localIterator = mStatusListener.iterator();
          if (!localIterator.hasNext()) {
            break label194;
          }
          ((MxCacheStatusListener)localIterator.next()).onMxIdOnline(mid, str);
        }
        label188:
        i = 0;
      }
      else
      {
        label194:
        bool1 = transientState;
        label203:
        return bool1;
        label209:
        i = 1;
      }
    }
  }
  
  public static void put(String paramString1, String arg1)
  {
    for (;;)
    {
      int j;
      int i;
      try
      {
        String str = Contact.normalizePhoneNumber(paramString1);
        paramString1 = (MxIdCacheItem)map.get(str);
        j = 0;
        i = 0;
        if (paramString1 == null)
        {
          Object localObject = new MxIdCacheItem(???, null);
          map.put(str, localObject);
          paramString1 = (String)localObject;
          if (??? != null)
          {
            i = 1;
            paramString1 = (String)localObject;
          }
          if (i == 0) {
            break label163;
          }
          synchronized (mStatusListener)
          {
            localObject = mStatusListener.iterator();
            if (!((Iterator)localObject).hasNext()) {
              break;
            }
            ((MxCacheStatusListener)((Iterator)localObject).next()).onMxIdAdded(mid, str);
          }
        }
        i = j;
      }
      finally {}
      if (mid == null)
      {
        i = j;
        if (??? != null) {
          i = 1;
        }
      }
      mid = ???;
      paramString1.updateExpire();
    }
    label163:
  }
  
  public static void removeStatusListener(MxCacheStatusListener paramMxCacheStatusListener)
  {
    int i;
    synchronized (mStatusListener)
    {
      i = mStatusListener.indexOf(paramMxCacheStatusListener);
      if (i < 0) {
        throw new IllegalStateException("listener not in cached");
      }
    }
    mStatusListener.remove(i);
  }
  
  public static abstract interface MxCacheStatusListener
  {
    public abstract void onMxIdAdded(String paramString1, String paramString2);
    
    public abstract void onMxIdOffline(String paramString1, String paramString2);
    
    public abstract void onMxIdOnline(String paramString1, String paramString2);
  }
  
  public static class MxIdCacheItem
  {
    protected long capability = -2147483648L;
    protected long expire = System.currentTimeMillis();
    protected String mid;
    protected boolean transientState = true;
    
    private MxIdCacheItem(String paramString)
    {
      mid = paramString;
    }
    
    public boolean allowMms()
    {
      return (mid != null) && ((capability & 0x2) > 0L);
    }
    
    public boolean allowMxV2()
    {
      return (mid != null) && ((capability & 0x400000000) > 0L);
    }
    
    public boolean allowSms()
    {
      return (mid != null) && ((capability & 1L) > 0L);
    }
    
    public long getCapability()
    {
      return capability;
    }
    
    public String getMId()
    {
      return mid;
    }
    
    public boolean isExpired()
    {
      return expire <= System.currentTimeMillis();
    }
    
    public String toString()
    {
      return "MxIdCacheItem{mid='" + mid + '\'' + ", expire=" + expire + ", capability=" + capability + '}';
    }
    
    protected void updateExpire()
    {
      long l2 = System.currentTimeMillis();
      if (mid != null) {}
      for (long l1 = 300000L;; l1 = 120000L)
      {
        expire = (l1 + l2);
        return;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.data.MxIdCache
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */