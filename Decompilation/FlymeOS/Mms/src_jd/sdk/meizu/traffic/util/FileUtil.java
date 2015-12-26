package sdk.meizu.traffic.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import org.apache.http.util.EncodingUtils;

public class FileUtil
{
  private static final int ASSETS_SUFFIX_BEGIN = 101;
  private static final int ASSETS_SUFFIX_END = 102;
  
  public static boolean copyAssertFileTo(Context paramContext, String paramString1, String paramString2)
  {
    localObject1 = null;
    Object localObject2 = null;
    try
    {
      localFileOutputStream = new FileOutputStream(paramString2);
      paramString2 = (String)localObject2;
      try
      {
        paramContext = paramContext.getAssets().open(paramString1);
        paramString2 = paramContext;
        paramString1 = new byte['Ѐ'];
        for (;;)
        {
          paramString2 = paramContext;
          int i = paramContext.read(paramString1);
          if (i <= 0) {
            break;
          }
          paramString2 = paramContext;
          localFileOutputStream.write(paramString1, 0, i);
        }
        if (paramString1 == null) {
          break label73;
        }
      }
      finally
      {
        paramString1 = localFileOutputStream;
      }
    }
    finally
    {
      for (;;)
      {
        FileOutputStream localFileOutputStream;
        label73:
        paramString1 = null;
        paramString2 = (String)localObject1;
      }
    }
    paramString1.close();
    if (paramString2 != null) {
      paramString2.close();
    }
    throw paramContext;
    paramString2 = paramContext;
    localFileOutputStream.flush();
    if (localFileOutputStream != null) {
      localFileOutputStream.close();
    }
    if (paramContext != null) {
      paramContext.close();
    }
    return true;
  }
  
  public static final void deleteDirectory(String paramString)
  {
    paramString = new File(paramString);
    if (!paramString.exists()) {}
    while (paramString.delete()) {
      return;
    }
    File[] arrayOfFile = paramString.listFiles();
    int i = 0;
    if (i < arrayOfFile.length)
    {
      if (arrayOfFile[i].isDirectory()) {
        if (!arrayOfFile[i].delete()) {
          deleteDirectory(arrayOfFile[i].getAbsolutePath());
        }
      }
      for (;;)
      {
        i += 1;
        break;
        arrayOfFile[i].delete();
      }
    }
    paramString.delete();
  }
  
  public static final boolean deleteFile(String paramString)
  {
    makeParentPath(paramString);
    paramString = new File(paramString);
    if (paramString.isDirectory()) {
      return false;
    }
    return paramString.delete();
  }
  
  public static boolean exist(String paramString)
  {
    return new File(paramString).exists();
  }
  
  public static final void makeParentPath(String paramString)
  {
    paramString = new File(paramString).getParentFile();
    if ((paramString != null) && (!paramString.exists())) {
      paramString.mkdirs();
    }
  }
  
  public static String readFileSdcardFile(String paramString)
  {
    makeParentPath(paramString);
    try
    {
      FileInputStream localFileInputStream = new FileInputStream(paramString);
      paramString = new byte[localFileInputStream.available()];
      localFileInputStream.read(paramString);
      paramString = EncodingUtils.getString(paramString, "UTF-8");
      localException1.printStackTrace();
    }
    catch (Exception localException1)
    {
      try
      {
        localFileInputStream.close();
        return paramString;
      }
      catch (Exception localException2)
      {
        for (;;) {}
      }
      localException1 = localException1;
      paramString = "";
    }
    return paramString;
  }
  
  public static boolean unzipAssertFile(InputStream paramInputStream, String paramString)
  {
    localObject1 = null;
    Object localObject2 = null;
    File localFile = new File(paramString);
    if (!localFile.exists()) {
      localFile.mkdirs();
    }
    if (!localFile.isDirectory()) {
      throw new IOException("Invalid Unzip destination " + localFile);
    }
    if (paramInputStream == null) {
      throw new IOException("InputStream is null");
    }
    for (;;)
    {
      try
      {
        paramString = new ZipInputStream(paramInputStream);
        paramInputStream = (InputStream)localObject2;
        try
        {
          localObject2 = paramString.getNextEntry();
          if (localObject2 == null) {
            continue;
          }
          localObject1 = localFile.getAbsolutePath() + File.separator + ((ZipEntry)localObject2).getName();
          localObject2 = ((ZipEntry)localObject2).getName();
          if (((String)localObject2).charAt(((String)localObject2).length() - 1) != File.separatorChar) {
            continue;
          }
          localObject1 = new File((String)localObject1);
          if ((((File)localObject1).exists()) || (((File)localObject1).mkdirs())) {
            continue;
          }
          throw new IOException("Unable to create folder " + localObject1);
        }
        finally
        {
          localObject1 = paramString;
          paramString = paramInputStream;
          paramInputStream = (InputStream)localObject3;
        }
      }
      finally
      {
        String str = null;
        paramString = (String)localObject1;
        localObject1 = str;
        continue;
      }
      if (localObject1 != null)
      {
        ((ZipInputStream)localObject1).closeEntry();
        ((ZipInputStream)localObject1).close();
      }
      if (paramString != null) {
        paramString.close();
      }
      throw paramInputStream;
      localObject1 = new FileOutputStream((String)localObject1);
      try
      {
        paramInputStream = new byte['Ѐ'];
        int i = paramString.read(paramInputStream);
        if (i != -1)
        {
          ((FileOutputStream)localObject1).write(paramInputStream, 0, i);
          continue;
          continue;
        }
      }
      finally
      {
        str = paramString;
        paramString = (String)localObject1;
        localObject1 = str;
      }
      paramInputStream = (InputStream)localObject1;
    }
    if (paramString != null)
    {
      paramString.closeEntry();
      paramString.close();
    }
    if (paramInputStream != null) {
      paramInputStream.close();
    }
    return true;
  }
  
  public static boolean unzipFile(String paramString1, String paramString2)
  {
    ZipInputStream localZipInputStream;
    if (!TextUtils.isEmpty(paramString1))
    {
      paramString1 = new File(paramString1);
      if (paramString1 != null) {
        localZipInputStream = new ZipInputStream(new FileInputStream(paramString1));
      }
    }
    else
    {
      for (;;)
      {
        ZipFile localZipFile;
        try
        {
          localZipFile = new ZipFile(paramString1);
          localObject1 = localZipInputStream.getNextEntry();
          if (localObject1 == null) {
            break label200;
          }
          localObject2 = new File(paramString2 + ((ZipEntry)localObject1).getName());
          if (!((ZipEntry)localObject1).isDirectory()) {
            break label121;
          }
          ((File)localObject2).mkdirs();
          continue;
          paramString1 = null;
        }
        finally
        {
          localZipInputStream.closeEntry();
          localZipInputStream.close();
        }
        break;
        label121:
        if (!((File)localObject2).exists()) {
          ((File)localObject2).createNewFile();
        }
        Object localObject1 = localZipFile.getInputStream((ZipEntry)localObject1);
        Object localObject2 = new FileOutputStream((File)localObject2);
        byte[] arrayOfByte = new byte['Ѐ'];
        for (;;)
        {
          int i = ((InputStream)localObject1).read(arrayOfByte);
          if (i == -1) {
            break;
          }
          ((FileOutputStream)localObject2).write(arrayOfByte, 0, i);
        }
        ((FileOutputStream)localObject2).close();
        ((InputStream)localObject1).close();
      }
      label200:
      paramString1.delete();
      localZipInputStream.closeEntry();
      localZipInputStream.close();
    }
    return true;
  }
  
  public static void writeFileSdcardFile(Context paramContext, String paramString1, String paramString2)
  {
    makeParentPath(paramString1);
    try
    {
      paramContext = paramContext.openFileOutput(paramString1, 0);
      paramContext.write(paramString2.getBytes());
      paramContext.close();
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.util.FileUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */