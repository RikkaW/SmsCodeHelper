package com.android.mms;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;

public class TempFileProvider
  extends ContentProvider
{
  public static final Uri SCRAP_CONTENT_URI;
  private static String TAG = "TempFileProvider";
  private static final UriMatcher sURLMatcher;
  
  static
  {
    SCRAP_CONTENT_URI = Uri.parse("content://mms_temp_file/scrapSpace");
    sURLMatcher = new UriMatcher(-1);
    sURLMatcher.addURI("mms_temp_file", "scrapSpace", 1);
  }
  
  public static String getScrapPath(Context paramContext)
  {
    return getScrapPath(paramContext, ".temp.jpg");
  }
  
  public static String getScrapPath(Context paramContext, String paramString)
  {
    if (paramContext != null) {
      return paramContext.getCacheDir().getAbsolutePath() + "/" + paramString;
    }
    return null;
  }
  
  private ParcelFileDescriptor getTempStoreFd()
  {
    String str = getScrapPath(getContext());
    Object localObject = null;
    if (TextUtils.isEmpty(str)) {
      return null;
    }
    try
    {
      File localFile1 = new File(str);
      File localFile2 = localFile1.getParentFile();
      if ((!localFile2.exists()) && (!localFile2.mkdirs()))
      {
        Log.e(TAG, "[TempFileProvider] tempStoreFd: " + localFile2.getPath() + "does not exist!");
        return null;
      }
    }
    catch (Exception localException)
    {
      Log.e(TAG, "getTempStoreFd: error creating pfd for " + str, localException);
    }
    for (;;)
    {
      return (ParcelFileDescriptor)localObject;
      ParcelFileDescriptor localParcelFileDescriptor = ParcelFileDescriptor.open(localException, 939524096);
      localObject = localParcelFileDescriptor;
    }
  }
  
  public static Uri renameScrapFile(String paramString1, String paramString2, Context paramContext)
  {
    String str2 = getScrapPath(paramContext);
    if (TextUtils.isEmpty(str2)) {}
    do
    {
      return null;
      String str1 = paramString2;
      if (paramString2 == null) {
        str1 = "";
      }
      paramString1 = new File(getScrapPath(paramContext, ".temp" + str1 + paramString1));
      paramString2 = new File(str2);
      paramString1.delete();
    } while (!paramString2.renameTo(paramString1));
    return Uri.fromFile(paramString1);
  }
  
  public int delete(Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    return 0;
  }
  
  public String getType(Uri paramUri)
  {
    return "*/*";
  }
  
  public Uri insert(Uri paramUri, ContentValues paramContentValues)
  {
    return null;
  }
  
  public boolean onCreate()
  {
    return true;
  }
  
  public ParcelFileDescriptor openFile(Uri paramUri, String paramString)
    throws FileNotFoundException
  {
    int i = sURLMatcher.match(paramUri);
    if (Log.isLoggable(TAG, 2)) {
      Log.d(TAG, "openFile: uri=" + paramUri + ", mode=" + paramString);
    }
    switch (i)
    {
    default: 
      return null;
    }
    return getTempStoreFd();
  }
  
  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    return null;
  }
  
  public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString)
  {
    return 0;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.TempFileProvider
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */