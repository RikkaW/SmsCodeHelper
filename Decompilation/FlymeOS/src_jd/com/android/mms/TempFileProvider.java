package com.android.mms;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import java.io.File;

public class TempFileProvider
  extends ContentProvider
{
  public static final Uri a;
  public static final Uri b;
  public static final Uri c;
  public static final Uri d;
  public static final Uri e;
  public static final Uri f;
  private static String g = "TempFileProvider";
  private static final SparseArray<String> h = new SparseArray();
  private static final String[] i = { "_id", "_data", "ct", "fn" };
  private static final UriMatcher j;
  
  static
  {
    a = Uri.parse("content://temp_mms_file/scrapSpace/");
    b = Uri.withAppendedPath(a, "image");
    c = Uri.withAppendedPath(a, "video");
    d = Uri.withAppendedPath(a, "portsms");
    e = Uri.withAppendedPath(a, "vcard");
    f = Uri.withAppendedPath(a, "recorde");
    j = new UriMatcher(-1);
    j.addURI("temp_mms_file", "scrapSpace", 1);
    j.addURI("temp_mms_file", "scrapSpace/image", 2);
    j.addURI("temp_mms_file", "scrapSpace/video", 3);
    j.addURI("temp_mms_file", "scrapSpace/portsms", 4);
    j.addURI("temp_mms_file", "scrapSpace/vcard", 5);
    j.addURI("temp_mms_file", "scrapSpace/recorde", 6);
    j.addURI("temp_mms_file", "scrapSpace/video/*", 7);
    h.put(2, ".temp_image.jpg");
    h.put(3, ".temp_video.3gp");
    h.put(4, ".temp_port_vcard.vcf");
    h.put(5, ".temp_vcard.vcf");
    h.put(6, "recorde.3gp");
  }
  
  private Cursor a()
  {
    String str = a(getContext(), ".temp_image.jpg");
    Object localObject;
    if (TextUtils.isEmpty(str)) {
      localObject = null;
    }
    File localFile;
    MatrixCursor localMatrixCursor;
    do
    {
      do
      {
        return (Cursor)localObject;
        localFile = new File(str);
        localMatrixCursor = new MatrixCursor(i, 1);
        localObject = localMatrixCursor;
      } while (!localFile.exists());
      localObject = localMatrixCursor;
    } while (localFile.isDirectory());
    localMatrixCursor.addRow(a(1, str, localFile.getName(), "image/jpeg"));
    return a(localMatrixCursor);
  }
  
  private Cursor a(int paramInt)
  {
    String str2 = (String)h.get(paramInt);
    if (TextUtils.isEmpty(str2)) {
      return null;
    }
    String str1 = str2;
    if (!str2.toLowerCase().endsWith(".vcf")) {
      str1 = str2 + ".vcf";
    }
    str2 = a(getContext(), ".temp_vcard.vcf");
    File localFile = new File(str2);
    MatrixCursor localMatrixCursor = new MatrixCursor(i, 1);
    if ((!localFile.exists()) || (localFile.isDirectory())) {
      return localMatrixCursor;
    }
    localMatrixCursor.addRow(a(1, str2, str1, "text/x-vcard"));
    return a(localMatrixCursor);
  }
  
  private Cursor a(Cursor paramCursor)
  {
    paramCursor.setNotificationUri(getContext().getContentResolver(), a);
    return paramCursor;
  }
  
  public static Uri a(String paramString1, String paramString2, Context paramContext, int paramInt)
  {
    if (paramInt == 1) {}
    for (String str1 = a(paramContext, ".temp_image.jpg");; str1 = a(paramContext, ".temp_video.3gp"))
    {
      String str2 = paramString2;
      if (paramString2 == null) {
        str2 = "";
      }
      paramString1 = new File(a(paramContext, ".temp" + str2 + paramString1));
      paramString2 = new File(str1);
      paramString1.delete();
      if (paramString2.renameTo(paramString1)) {
        break;
      }
      Log.i("TempFileProvider", "renameScrapFile renameTo fail!");
      return null;
    }
    if (paramInt == 2) {
      return Uri.withAppendedPath(c, paramString1.getName());
    }
    return Uri.fromFile(paramString1);
  }
  
  private ParcelFileDescriptor a(Uri paramUri, String paramString)
  {
    switch (j.match(paramUri))
    {
    default: 
      return null;
    case 1: 
      paramUri = a(getContext(), ".temp_image.jpg");
    }
    File localFile1;
    for (;;)
    {
      try
      {
        localFile1 = new File(paramUri);
        File localFile2 = localFile1.getParentFile();
        if ((localFile2.exists()) || (localFile2.mkdirs())) {
          break;
        }
        Log.e(g, "[TempFileProvider] tempStoreFd: " + localFile2.getPath() + "does not exist!");
        return null;
      }
      catch (Exception paramString)
      {
        Log.e(g, "getTempStoreFd: error creating pfd for " + paramUri, paramString);
        return null;
      }
      paramUri = a(getContext(), ".temp_image.jpg");
      continue;
      paramUri = a(getContext(), ".temp_video.3gp");
      continue;
      paramUri = a(getContext(), ".temp_port_vcard.vcf");
      continue;
      paramUri = a(getContext(), ".temp_vcard.vcf");
      continue;
      paramUri = a(getContext(), "recorde.3gp");
      continue;
      Log.i("TempFileProvider", "MMS_SCRAP_VIDEO_SPACE_WITH_FILENAME uri.getLastPathSegment() = " + paramUri.getLastPathSegment());
      paramUri = a(getContext(), paramUri.getLastPathSegment());
    }
    int k = 939524096;
    if (paramString.equals("r")) {
      k = 268435456;
    }
    for (;;)
    {
      return ParcelFileDescriptor.open(localFile1, k);
      int m = paramString.indexOf("w");
      if (m != -1) {
        k = 1006632960;
      }
    }
  }
  
  public static String a(Context paramContext, String paramString)
  {
    return b(paramContext) + "/" + paramString;
  }
  
  public static void a(Context paramContext)
  {
    a(paramContext, a);
  }
  
  public static void a(Context paramContext, Uri paramUri)
  {
    paramContext.getContentResolver().delete(a, null, null);
  }
  
  private boolean a(int paramInt, String paramString)
  {
    h.put(paramInt, paramString);
    return !TextUtils.isEmpty((CharSequence)h.get(paramInt));
  }
  
  public static boolean a(String paramString)
  {
    return paramString.contains(".temp_");
  }
  
  private Object[] a(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    return new Object[] { Integer.valueOf(paramInt), paramString1, paramString3, paramString2 };
  }
  
  private Cursor b()
  {
    String str = a(getContext(), "recorde.3gp");
    Object localObject;
    if (TextUtils.isEmpty(str)) {
      localObject = null;
    }
    File localFile;
    MatrixCursor localMatrixCursor;
    do
    {
      do
      {
        return (Cursor)localObject;
        localFile = new File(str);
        localMatrixCursor = new MatrixCursor(i, 1);
        localObject = localMatrixCursor;
      } while (!localFile.exists());
      localObject = localMatrixCursor;
    } while (localFile.isDirectory());
    localMatrixCursor.addRow(a(1, str, localFile.getName(), "video/3gpp"));
    return a(localMatrixCursor);
  }
  
  private Cursor b(String paramString)
  {
    String str = a(getContext(), paramString);
    if (TextUtils.isEmpty(str)) {
      paramString = null;
    }
    File localFile;
    MatrixCursor localMatrixCursor;
    do
    {
      do
      {
        return paramString;
        localFile = new File(str);
        localMatrixCursor = new MatrixCursor(i, 1);
        paramString = localMatrixCursor;
      } while (!localFile.exists());
      paramString = localMatrixCursor;
    } while (localFile.isDirectory());
    localMatrixCursor.addRow(a(1, str, localFile.getName(), "video/3gpp"));
    return a(localMatrixCursor);
  }
  
  public static String b(Context paramContext)
  {
    return paramContext.getFilesDir().getAbsolutePath();
  }
  
  private Cursor c()
  {
    String str = a(getContext(), ".temp_port_vcard.vcf");
    Object localObject;
    if (TextUtils.isEmpty(str)) {
      localObject = null;
    }
    File localFile;
    MatrixCursor localMatrixCursor;
    do
    {
      do
      {
        return (Cursor)localObject;
        localFile = new File(str);
        localMatrixCursor = new MatrixCursor(i, 1);
        localObject = localMatrixCursor;
      } while (!localFile.exists());
      localObject = localMatrixCursor;
    } while (localFile.isDirectory());
    localMatrixCursor.addRow(a(1, str, localFile.getName(), "text/x-vcard"));
    return a(localMatrixCursor);
  }
  
  public static Uri c(Context paramContext)
  {
    Object localObject = a(paramContext, "recorde.3gp");
    paramContext = a(paramContext, ".temp_video.3gp");
    localObject = new File((String)localObject);
    paramContext = new File(paramContext);
    if ((!paramContext.isDirectory()) && (paramContext.exists())) {
      paramContext.delete();
    }
    if (!((File)localObject).renameTo(paramContext)) {
      return null;
    }
    return c;
  }
  
  private int d()
  {
    int k = 0;
    int m = 0;
    Object localObject = new File(b(getContext()));
    int n = m;
    if (((File)localObject).exists())
    {
      if (!((File)localObject).isDirectory()) {
        n = m;
      }
    }
    else {
      return n;
    }
    h.clear();
    localObject = ((File)localObject).listFiles();
    int i1 = localObject.length;
    m = 0;
    for (;;)
    {
      n = k;
      if (m >= i1) {
        break;
      }
      n = k;
      if (localObject[m].delete()) {
        n = k + 1;
      }
      m += 1;
      k = n;
    }
  }
  
  private int e()
  {
    File localFile = new File(a(getContext(), ".temp_port_vcard.vcf"));
    if ((!localFile.exists()) || (localFile.isDirectory())) {}
    while (!localFile.delete()) {
      return 0;
    }
    return 1;
  }
  
  public int delete(Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    switch (j.match(paramUri))
    {
    case 2: 
    case 3: 
    case 5: 
    default: 
      return 0;
    case 1: 
      return d();
    }
    return e();
  }
  
  public String getType(Uri paramUri)
  {
    switch (j.match(paramUri))
    {
    default: 
      return "*/*";
    }
    Cursor localCursor = query(paramUri, null, null, null, null);
    if (localCursor != null) {
      try
      {
        if ((localCursor.getCount() == 1) && (localCursor.moveToFirst())) {}
        String str;
        for (paramUri = localCursor.getString(localCursor.getColumnIndexOrThrow("ct"));; paramUri = str)
        {
          return paramUri;
          str = "image/jpeg";
          Log.e(g, "cursor.count() != 1: " + paramUri);
        }
        Log.e(g, "cursor == null: " + paramUri);
      }
      finally
      {
        localCursor.close();
      }
    }
    return "image/jpeg";
  }
  
  public Uri insert(Uri paramUri, ContentValues paramContentValues)
  {
    if (a(j.match(paramUri), paramContentValues.getAsString("temp_name_key"))) {
      return paramUri;
    }
    return null;
  }
  
  public boolean onCreate()
  {
    return true;
  }
  
  public ParcelFileDescriptor openFile(Uri paramUri, String paramString)
  {
    if (Log.isLoggable(g, 2)) {
      Log.d(g, "openFile: uri=" + paramUri + ", mode=" + paramString);
    }
    return a(paramUri, paramString);
  }
  
  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    int k = j.match(paramUri);
    switch (k)
    {
    default: 
      return null;
    case 2: 
      return a();
    case 3: 
      return b(".temp_video.3gp");
    case 4: 
      return c();
    case 5: 
      return a(k);
    case 6: 
      return b();
    }
    Log.i("TempFileProvider", "query MMS_SCRAP_VIDEO_SPACE_WITH_FILENAME uri.getLastPathSegment() = " + paramUri.getLastPathSegment());
    return b(paramUri.getLastPathSegment());
  }
  
  public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString)
  {
    if (a(j.match(paramUri), paramContentValues.getAsString("temp_name_key"))) {
      return 1;
    }
    return 0;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.TempFileProvider
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */