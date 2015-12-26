package com.ted.sdk.yellow.update;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import ff;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class OfflineUpdateHelper
{
  private static final String OFFLINE_DAT = "offline.dat";
  private static final String OFFLINE_DAT_DIR = "yellowpage/offline/";
  
  public static boolean deleteCache(Context paramContext)
  {
    if ("mounted".equals(Environment.getExternalStorageState())) {}
    for (paramContext = Environment.getExternalStorageDirectory();; paramContext = paramContext.getFilesDir())
    {
      paramContext = new File(paramContext, "yellowpage/offline/");
      if (!paramContext.exists()) {
        break;
      }
      return deleteDir(paramContext);
    }
    return false;
  }
  
  private static boolean deleteDir(File paramFile)
  {
    boolean bool2 = false;
    String[] arrayOfString;
    int i;
    if (paramFile.isDirectory())
    {
      arrayOfString = paramFile.list();
      i = 0;
    }
    for (;;)
    {
      boolean bool1;
      if (i >= arrayOfString.length) {
        bool1 = paramFile.delete();
      }
      do
      {
        return bool1;
        bool1 = bool2;
      } while (!deleteDir(new File(paramFile, arrayOfString[i])));
      i += 1;
    }
  }
  
  public static boolean extract(Context paramContext)
  {
    localObject = null;
    try
    {
      InputStream localInputStream = paramContext.getAssets().open("offline.dat");
      localObject = localInputStream;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localIOException.printStackTrace();
      }
      if (!"mounted".equals(Environment.getExternalStorageState())) {
        break label96;
      }
      for (paramContext = Environment.getExternalStorageDirectory();; paramContext = paramContext.getFilesDir())
      {
        paramContext = new File(paramContext, "yellowpage/offline/");
        if ((!paramContext.exists()) && (!paramContext.mkdirs())) {
          break;
        }
        return ff.a((InputStream)localObject, paramContext.getAbsolutePath() + File.separator);
      }
    }
    return localObject != null;
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.update.OfflineUpdateHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */