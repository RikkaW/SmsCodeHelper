package com.google.android.mms.util;

import android.content.Context;
import android.drm.DrmManagerClient;
import android.util.Log;

public class DownloadDrmHelper
{
  public static final String EXTENSION_DRM_MESSAGE = ".dm";
  public static final String EXTENSION_INTERNAL_FWDL = ".fl";
  public static final String MIMETYPE_DRM_MESSAGE = "application/vnd.oma.drm.message";
  private static final String TAG = "DownloadDrmHelper";
  
  public static String getOriginalMimeType(Context paramContext, String paramString1, String paramString2)
  {
    DrmManagerClient localDrmManagerClient = new DrmManagerClient(paramContext);
    paramContext = paramString2;
    try
    {
      if (localDrmManagerClient.canHandle(paramString1, null)) {
        paramContext = localDrmManagerClient.getOriginalMimeType(paramString1);
      }
      return paramContext;
    }
    catch (IllegalArgumentException paramContext)
    {
      Log.w("DownloadDrmHelper", "Can't get original mime type since path is null or empty string.");
      return paramString2;
    }
    catch (IllegalStateException paramContext)
    {
      Log.w("DownloadDrmHelper", "DrmManagerClient didn't initialize properly.");
    }
    return paramString2;
  }
  
  public static boolean isDrmConvertNeeded(String paramString)
  {
    return "application/vnd.oma.drm.message".equals(paramString);
  }
  
  public static boolean isDrmMimeType(Context paramContext, String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramContext != null) {}
    try
    {
      paramContext = new DrmManagerClient(paramContext);
      bool1 = bool2;
      if (paramContext != null)
      {
        bool1 = bool2;
        if (paramString != null)
        {
          bool1 = bool2;
          if (paramString.length() > 0) {
            bool1 = paramContext.canHandle("", paramString);
          }
        }
      }
      return bool1;
    }
    catch (IllegalArgumentException paramContext)
    {
      Log.w("DownloadDrmHelper", "DrmManagerClient instance could not be created, context is Illegal.");
      return false;
    }
    catch (IllegalStateException paramContext)
    {
      Log.w("DownloadDrmHelper", "DrmManagerClient didn't initialize properly.");
    }
    return false;
  }
  
  public static String modifyDrmFwLockFileExtension(String paramString)
  {
    String str = paramString;
    if (paramString != null)
    {
      int i = paramString.lastIndexOf(".");
      str = paramString;
      if (i != -1) {
        str = paramString.substring(0, i);
      }
      str = str.concat(".fl");
    }
    return str;
  }
}

/* Location:
 * Qualified Name:     com.google.android.mms.util.DownloadDrmHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */