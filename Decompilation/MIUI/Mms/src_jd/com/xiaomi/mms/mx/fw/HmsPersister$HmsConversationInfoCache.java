package com.xiaomi.mms.mx.fw;

import android.text.TextUtils;
import android.util.LruCache;
import com.xiaomi.mms.mx.fw.fdata.HmsConversationInfo;

 enum HmsPersister$HmsConversationInfoCache
{
  INSTANCE;
  
  private LruCache<String, HmsConversationInfo> mLruCache = new LruCache(40);
  
  private HmsPersister$HmsConversationInfoCache() {}
  
  public HmsConversationInfo get(String paramString)
  {
    Object localObject;
    if (TextUtils.isEmpty(paramString)) {
      localObject = null;
    }
    HmsConversationInfo localHmsConversationInfo;
    do
    {
      do
      {
        return (HmsConversationInfo)localObject;
        localHmsConversationInfo = (HmsConversationInfo)mLruCache.get(paramString);
        localObject = localHmsConversationInfo;
      } while (localHmsConversationInfo != null);
      localHmsConversationInfo = HmsPersister.access$100(paramString);
      localObject = localHmsConversationInfo;
    } while (localHmsConversationInfo == null);
    mLruCache.put(paramString, localHmsConversationInfo);
    return localHmsConversationInfo;
  }
  
  public void put(String paramString, HmsConversationInfo paramHmsConversationInfo)
  {
    if ((TextUtils.isEmpty(paramString)) || (paramHmsConversationInfo == null)) {
      return;
    }
    synchronized (INSTANCE)
    {
      mLruCache.put(paramString, paramHmsConversationInfo);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.fw.HmsPersister.HmsConversationInfoCache
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */