package com.xiaomi.mms.transaction;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.mms.mx.fw.futils.HmsConstants;
import java.io.Closeable;

public class MipubThread
  extends MsgThread
{
  public MipubThread(long paramLong, String paramString)
  {
    super(paramLong, paramString);
  }
  
  private void closeQuietly(Closeable paramCloseable)
  {
    if (paramCloseable == null)
    {
      Log.d("MipubThread", "closeQuietly, get a null object");
      return;
    }
    try
    {
      paramCloseable.close();
      return;
    }
    catch (Exception paramCloseable)
    {
      paramCloseable.printStackTrace();
    }
  }
  
  private Uri getMipubDetailUri(long paramLong, String paramString, Uri paramUri)
  {
    Uri localUri = Uri.withAppendedPath(paramUri, String.valueOf(paramLong));
    paramUri = localUri;
    if (!TextUtils.isEmpty(paramString)) {
      paramUri = Uri.withAppendedPath(localUri, "?subId=" + paramString);
    }
    return paramUri;
  }
  
  private int getMipubIdFromAddress(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
    {
      Log.e("MipubThread", "getMipubIdFromAddress, get a empty address: ");
      return 0;
    }
    int i = paramString.indexOf("@");
    if ((-1 == i) || (i == 0))
    {
      Log.e("MipubThread", "getMipubIdFromAddress, get a invalid address: " + paramString);
      return 0;
    }
    return Integer.valueOf(paramString.substring(0, i)).intValue();
  }
  
  private String getSenderNameFromDb(Context paramContext, String paramString)
  {
    Object localObject1 = null;
    Object localObject2 = Mipub.CONTENT_URI_MIPUB_DETAIL_ITEM;
    ContentResolver localContentResolver = paramContext.getContentResolver();
    paramContext = null;
    try
    {
      localObject2 = localContentResolver.query((Uri)localObject2, new String[] { "name" }, "address = ?", new String[] { paramString }, null);
      paramString = (String)localObject1;
      if (localObject2 != null)
      {
        paramString = (String)localObject1;
        paramContext = (Context)localObject2;
        if (((Cursor)localObject2).moveToFirst())
        {
          paramContext = (Context)localObject2;
          paramString = ((Cursor)localObject2).getString(0);
        }
      }
      closeQuietly((Closeable)localObject2);
      return paramString;
    }
    finally
    {
      closeQuietly(paramContext);
    }
  }
  
  private String getSubMipubIdFromAddress(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
    {
      Log.e("MipubThread", "getSubMipubIdFromAddress, get a empty address: ");
      return "";
    }
    int i = paramString.indexOf("/");
    if ((i != -1) && (i != 0)) {
      return paramString.substring(i, paramString.length());
    }
    Log.e("MipubThread", "getSubMipubIdFromAddress, get a invalid address: " + paramString);
    return "";
  }
  
  private boolean loadMipubDetailBackgroundFromService(Context paramContext, String paramString)
  {
    long l = getMipubIdFromAddress(paramString);
    if (l <= 0L) {
      return false;
    }
    paramString = getMipubDetailUri(l, getSubMipubIdFromAddress(paramString), Mipub.CONTENT_URI_MIPUB_DETAIL_ITEM_NET);
    paramContext = paramContext.getContentResolver();
    try
    {
      paramContext = paramContext.query(paramString, null, null, null, null);
      closeQuietly(paramContext);
      return true;
    }
    finally
    {
      closeQuietly(null);
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (getClass() != paramObject.getClass())) {
        return false;
      }
      paramObject = (MipubThread)paramObject;
    } while (mThreadId == mThreadId);
    return false;
  }
  
  public Intent getConversationClickIntent(Context paramContext)
  {
    paramContext = new Intent();
    paramContext.setAction("com.miui.mipub.action_open_thread");
    paramContext.putExtra("thread_id", mThreadId);
    paramContext.putExtra("open_cv_from", "notification");
    paramContext.setPackage(getPackageName());
    return paramContext;
  }
  
  public Intent getCvListClickIntent(Context paramContext)
  {
    paramContext = new Intent();
    paramContext.setAction("com.miui.mipub.action_open_threadlist");
    paramContext.setPackage("com.miui.mipub");
    paramContext.putExtra("open_cvlist_from", "notification");
    return paramContext;
  }
  
  public String getPackageName()
  {
    return "com.miui.mipub";
  }
  
  public Uri getPeoplePreferenceUri(String paramString)
  {
    return null;
  }
  
  public String getSenderName(Context paramContext, String paramString)
  {
    String str2 = getSenderNameFromDb(paramContext, paramString);
    String str1 = str2;
    int i;
    if (TextUtils.isEmpty(str2)) {
      i = 0;
    }
    try
    {
      boolean bool = loadMipubDetailBackgroundFromService(paramContext, paramString);
      i = bool;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.e("MipubThread", "loadMipubDetailBackgroundFromService ", localException);
      }
    }
    if (i != 0) {
      str2 = getSenderNameFromDb(paramContext, paramString);
    }
    str1 = str2;
    if (TextUtils.isEmpty(str2)) {
      str1 = paramContext.getString(2131361986);
    }
    return str1;
  }
  
  public int hashCode()
  {
    return ("MipubThread" + mThreadId).hashCode();
  }
  
  public static abstract interface Mipub
  {
    public static final Uri CONTENT_URI;
    public static final Uri CONTENT_URI_DETAIL;
    public static final Uri CONTENT_URI_MIPUB_DETAIL_ITEM = Uri.withAppendedPath(CONTENT_URI_DETAIL, "mipub_detail_item");
    public static final Uri CONTENT_URI_MIPUB_DETAIL_ITEM_NET;
    public static final String[] STATUS_PROJECTION = { "thread_id", "date", "_id", "snippet", "address" };
    
    static
    {
      CONTENT_URI = HmsConstants.MIPUB_MSG_URI;
      CONTENT_URI_DETAIL = Uri.parse("content://com.miui.mipub.MipubDetailProvider/");
      CONTENT_URI_MIPUB_DETAIL_ITEM_NET = Uri.withAppendedPath(CONTENT_URI_DETAIL, "mipub_detail_item_net");
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.transaction.MipubThread
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */