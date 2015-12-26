package com.xiaomi.mms.mx.utils;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.mms.mx.cache.DiskLruCache;
import com.xiaomi.mms.mx.cache.ImageCache;
import com.xiaomi.mms.mx.cache.ImageCacheManager;
import com.xiaomi.mms.mx.common.GlobalData;

public class AttachmentUtils
{
  public static long generateAttachmentId()
  {
    long l = PreferenceUtils.getSettingLong(GlobalData.app(), "pref_key_attachment_base_id", 10240L);
    l = Math.max(System.currentTimeMillis(), l) + 1L;
    PreferenceUtils.setSettingLong(GlobalData.app(), "pref_key_attachment_base_id", l);
    return l;
  }
  
  public static String getCachedImagePath(Context paramContext, String paramString)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (!TextUtils.isEmpty(paramString))
    {
      paramContext = ImageCacheManager.get(paramContext, "common_image_cache");
      localObject1 = localObject2;
      if (paramContext != null)
      {
        paramContext = paramContext.getDiskLruCache();
        localObject1 = localObject2;
        if (paramContext != null) {
          localObject1 = paramContext.getCacheFilePath(DiskLruCache.getCommonUrlDiskKey(paramString));
        }
      }
    }
    return (String)localObject1;
  }
  
  public static int getMessageTypeFromMimeType(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    do
    {
      return 0;
      if (paramString.startsWith("video/")) {
        return 4;
      }
      if (paramString.startsWith("audio/")) {
        return 3;
      }
    } while (!paramString.startsWith("image/"));
    return 2;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.utils.AttachmentUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */