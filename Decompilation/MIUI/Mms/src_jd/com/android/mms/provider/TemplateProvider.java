package com.android.mms.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.android.mms.ui.MessageUtils;
import java.io.File;
import java.io.FileNotFoundException;

public class TemplateProvider
  extends ContentProvider
{
  private static final String[] COLUMNS;
  private static final UriMatcher sURLMatcher = new UriMatcher(-1);
  
  static
  {
    COLUMNS = new String[] { "allowed" };
    sURLMatcher.addURI("msg-template", "downloads", 1);
    sURLMatcher.addURI("msg-template", "version", 2);
    sURLMatcher.addURI("msg-template", "updated", 3);
    sURLMatcher.addURI("msg-template", "allowed", 4);
  }
  
  private ParcelFileDescriptor getFileFD(String paramString)
  {
    localObject1 = null;
    try
    {
      Object localObject2 = new File(paramString);
      File localFile = ((File)localObject2).getParentFile();
      if ((!localFile.exists()) && (!localFile.mkdirs()))
      {
        Log.e("TemplateProvider", "[TemplateProvider] getFileFD: " + localFile.getPath() + "does not exist!");
        return null;
      }
      localObject2 = ParcelFileDescriptor.open((File)localObject2, 939524096);
      paramString = (String)localObject2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.e("TemplateProvider", "getFileFD: error creating pfd for " + paramString, localException);
        paramString = (String)localObject1;
      }
    }
    Log.v("TemplateProvider", "getTempStoreFd success!");
    return paramString;
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
    switch (sURLMatcher.match(paramUri))
    {
    default: 
      Log.e("TemplateProvider", " Unsupported uri: " + paramUri);
      return null;
    case 1: 
      return getFileFD("/data/data/com.android.mms/app_understand/downloads.tmp");
    case 2: 
      return getFileFD("/data/data/com.android.mms/app_understand/version");
    }
    return getFileFD("/data/data/com.android.mms/app_understand/understand.zip");
  }
  
  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    int i = 1;
    switch (sURLMatcher.match(paramUri))
    {
    default: 
      return null;
    }
    paramUri = new MatrixCursor(COLUMNS);
    if (MessageUtils.isMessagingTemplateAllowed(getContext())) {}
    for (;;)
    {
      paramUri.addRow(new Object[] { Integer.valueOf(i) });
      return paramUri;
      i = 0;
    }
  }
  
  public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString)
  {
    return 0;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.provider.TemplateProvider
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */