package com.xiaomi.mms.utils;

import android.accounts.NetworkErrorException;
import com.xiaomi.micloudsdk.exception.CloudParameterError;
import com.xiaomi.micloudsdk.exception.CloudRichMediaServerException;
import com.xiaomi.micloudsdk.exception.CloudServerException;
import com.xiaomi.micloudsdk.exception.FileTooLargeException;
import com.xiaomi.micloudsdk.micloudrichmedia.MiCloudRichMediaManager;
import com.xiaomi.micloudsdk.micloudrichmedia.UploadEntity;
import com.xiaomi.micloudsdk.micloudrichmedia.UploadResult;
import com.xiaomi.mms.net.exception.InvalidTokenException;
import com.xiaomi.mms.net.exception.MxLogicException;
import com.xiaomi.mms.net.exception.ServerErrorException;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

public class StorageServerHelper
{
  public static void downloadFile(MiCloudRichMediaManager paramMiCloudRichMediaManager, String paramString, File paramFile)
    throws IOException, ServerErrorException, InvalidTokenException
  {
    try
    {
      if (isPublicUrl(paramString))
      {
        downloadFileFromPublicUrl(paramMiCloudRichMediaManager, paramString, paramFile);
        return;
      }
      paramMiCloudRichMediaManager.download2(paramString, "mixin2", paramFile);
      return;
    }
    catch (NetworkErrorException paramMiCloudRichMediaManager)
    {
      throw new IOException(paramMiCloudRichMediaManager);
    }
    catch (CloudRichMediaServerException paramMiCloudRichMediaManager)
    {
      if (isServiceTokenInvalid(paramMiCloudRichMediaManager)) {
        throw new InvalidTokenException("download mx2.0 richmedia failed, because token invalid when downloading pdu", paramMiCloudRichMediaManager);
      }
      throw new ServerErrorException("download mx2.0 richmedia failed, because micloud server error", paramMiCloudRichMediaManager);
    }
    catch (CloudParameterError paramMiCloudRichMediaManager)
    {
      throw new ServerErrorException("download mx2.0 richmedia failed, because param error", paramMiCloudRichMediaManager);
    }
  }
  
  private static void downloadFileFromPublicUrl(MiCloudRichMediaManager paramMiCloudRichMediaManager, String paramString, File paramFile)
    throws CloudRichMediaServerException, IOException
  {
    paramString = paramString.split("\\s+");
    if (paramString.length == 2)
    {
      MyLog.d("StorageServerHelper", "download multi-media part form public url");
      paramMiCloudRichMediaManager = paramMiCloudRichMediaManager.downloadFromPublicUrl(paramString[0], paramString[1]);
      paramString = new FileOutputStream(paramFile);
      paramString.write(paramMiCloudRichMediaManager);
      paramString.close();
    }
  }
  
  public static byte[] downloadPdu(MiCloudRichMediaManager paramMiCloudRichMediaManager, String paramString)
    throws IOException, ServerErrorException, InvalidTokenException
  {
    try
    {
      if (isPublicUrl(paramString)) {
        return downloadPduFromPublicUrl(paramMiCloudRichMediaManager, paramString);
      }
      paramMiCloudRichMediaManager = paramMiCloudRichMediaManager.download2(paramString, "mixin");
      return paramMiCloudRichMediaManager;
    }
    catch (NetworkErrorException paramMiCloudRichMediaManager)
    {
      throw new IOException("download mx richmedia failed, because io exception ", paramMiCloudRichMediaManager);
    }
    catch (CloudRichMediaServerException paramMiCloudRichMediaManager)
    {
      if (isServiceTokenInvalid(paramMiCloudRichMediaManager)) {
        throw new InvalidTokenException("download mx richmedia failed, because token expired", paramMiCloudRichMediaManager);
      }
      throw new ServerErrorException("download mx richmedia failed, because micloud server error", paramMiCloudRichMediaManager);
    }
    catch (CloudParameterError paramMiCloudRichMediaManager)
    {
      throw new ServerErrorException("download mx richmedia failed, because param error", paramMiCloudRichMediaManager);
    }
  }
  
  private static byte[] downloadPduFromPublicUrl(MiCloudRichMediaManager paramMiCloudRichMediaManager, String paramString)
    throws IOException, InvalidTokenException, ServerErrorException, CloudRichMediaServerException
  {
    paramString = paramString.split("\\s+");
    if (paramString.length == 2) {
      return paramMiCloudRichMediaManager.downloadFromPublicUrl(paramString[0], paramString[1]);
    }
    return null;
  }
  
  /* Error */
  public static byte[] downloadPduViaMxV2(android.content.Context paramContext, MiCloudRichMediaManager paramMiCloudRichMediaManager, String paramString)
    throws IOException, ServerErrorException, InvalidTokenException, com.xiaomi.mms.net.exception.MxV2DownloadException
  {
    // Byte code:
    //   0: new 116	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 117	java/lang/StringBuilder:<init>	()V
    //   7: aload_0
    //   8: invokevirtual 123	android/content/Context:getCacheDir	()Ljava/io/File;
    //   11: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   14: getstatic 133	java/io/File:separator	Ljava/lang/String;
    //   17: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   20: aload_2
    //   21: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   24: invokevirtual 140	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   27: astore 5
    //   29: aconst_null
    //   30: astore_3
    //   31: aconst_null
    //   32: astore 4
    //   34: new 129	java/io/File
    //   37: dup
    //   38: aload 5
    //   40: invokespecial 143	java/io/File:<init>	(Ljava/lang/String;)V
    //   43: astore_0
    //   44: aload_1
    //   45: aload_2
    //   46: aload_0
    //   47: invokestatic 145	com/xiaomi/mms/utils/StorageServerHelper:downloadFile	(Lcom/xiaomi/micloudsdk/micloudrichmedia/MiCloudRichMediaManager;Ljava/lang/String;Ljava/io/File;)V
    //   50: aload_0
    //   51: invokevirtual 149	java/io/File:exists	()Z
    //   54: ifne +38 -> 92
    //   57: new 114	com/xiaomi/mms/net/exception/MxV2DownloadException
    //   60: dup
    //   61: ldc -105
    //   63: invokespecial 152	com/xiaomi/mms/net/exception/MxV2DownloadException:<init>	(Ljava/lang/String;)V
    //   66: athrow
    //   67: astore_1
    //   68: aload_3
    //   69: astore_2
    //   70: aload_2
    //   71: ifnull +7 -> 78
    //   74: aload_2
    //   75: invokevirtual 155	java/io/InputStream:close	()V
    //   78: aload_0
    //   79: invokevirtual 149	java/io/File:exists	()Z
    //   82: ifeq +8 -> 90
    //   85: aload_0
    //   86: invokevirtual 158	java/io/File:delete	()Z
    //   89: pop
    //   90: aload_1
    //   91: athrow
    //   92: new 160	java/io/FileInputStream
    //   95: dup
    //   96: aload 5
    //   98: invokespecial 161	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   101: astore_2
    //   102: aload_2
    //   103: invokevirtual 165	java/io/InputStream:available	()I
    //   106: newarray <illegal type>
    //   108: astore_1
    //   109: aload_2
    //   110: aload_1
    //   111: invokevirtual 169	java/io/InputStream:read	([B)I
    //   114: pop
    //   115: aload_2
    //   116: ifnull +7 -> 123
    //   119: aload_2
    //   120: invokevirtual 155	java/io/InputStream:close	()V
    //   123: aload_0
    //   124: invokevirtual 149	java/io/File:exists	()Z
    //   127: ifeq +8 -> 135
    //   130: aload_0
    //   131: invokevirtual 158	java/io/File:delete	()Z
    //   134: pop
    //   135: aload_1
    //   136: areturn
    //   137: astore_1
    //   138: aload_3
    //   139: astore_2
    //   140: aload 4
    //   142: astore_0
    //   143: goto -73 -> 70
    //   146: astore_1
    //   147: goto -77 -> 70
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	150	0	paramContext	android.content.Context
    //   0	150	1	paramMiCloudRichMediaManager	MiCloudRichMediaManager
    //   0	150	2	paramString	String
    //   30	109	3	localObject1	Object
    //   32	109	4	localObject2	Object
    //   27	70	5	str	String
    // Exception table:
    //   from	to	target	type
    //   44	67	67	finally
    //   92	102	67	finally
    //   34	44	137	finally
    //   102	115	146	finally
  }
  
  private static boolean isPublicUrl(String paramString)
  {
    return paramString.startsWith("http");
  }
  
  private static boolean isServiceTokenInvalid(CloudServerException paramCloudServerException)
  {
    int i = paramCloudServerException.getStatusCode();
    return (i == 401) || (i == 400);
  }
  
  public static UploadResult uploadFile(MiCloudRichMediaManager paramMiCloudRichMediaManager, UploadEntity paramUploadEntity, Collection<String> paramCollection)
    throws MxLogicException, IOException, ServerErrorException, InvalidTokenException
  {
    try
    {
      paramMiCloudRichMediaManager = paramMiCloudRichMediaManager.upload(paramUploadEntity, paramCollection);
      return paramMiCloudRichMediaManager;
    }
    catch (NetworkErrorException paramMiCloudRichMediaManager)
    {
      throw new IOException("upload mx richmedia failed, because no active network", paramMiCloudRichMediaManager);
    }
    catch (CloudParameterError paramMiCloudRichMediaManager)
    {
      throw new ServerErrorException("upload mx richmedia failed, because param error", paramMiCloudRichMediaManager);
    }
    catch (FileTooLargeException paramMiCloudRichMediaManager)
    {
      throw new ServerErrorException("upload mx richmedia failed, because file too large", paramMiCloudRichMediaManager);
    }
    catch (CloudRichMediaServerException paramMiCloudRichMediaManager)
    {
      if (isServiceTokenInvalid(paramMiCloudRichMediaManager)) {
        throw new InvalidTokenException("upload mx richmedia failed, because token is invalid or expired", paramMiCloudRichMediaManager);
      }
      throw new ServerErrorException("upload mx richmedia failed, because micloud server error", paramMiCloudRichMediaManager);
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.StorageServerHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */