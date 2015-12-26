package com.xiaomi.mms.utils;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import com.android.mms.MmsApp;
import com.android.mms.audio.AudioHelper;
import com.android.mms.data.Conversation;
import com.android.mms.data.Mx2WorkingMessage;
import com.android.mms.data.WorkingMessage.MessageStatusListener;
import com.google.android.mms.ContentType;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.micloudsdk.micloudrichmedia.MiCloudRichMediaManager;
import com.xiaomi.micloudsdk.micloudrichmedia.UploadEntity;
import com.xiaomi.micloudsdk.micloudrichmedia.UploadResult;
import com.xiaomi.mms.data.Mx2MessageModel;
import com.xiaomi.mms.mx.cache.ImageCacheUtils;
import com.xiaomi.mms.mx.common.GlobalData;
import com.xiaomi.mms.mx.data.Attachment;
import com.xiaomi.mms.net.exception.InvalidTokenException;
import com.xiaomi.mms.net.exception.MxLogicException;
import com.xiaomi.mms.net.exception.ServerErrorException;
import com.xiaomi.mms.transaction.MiCloudMxMmsTransactionHandler.MiCloudMediaManagerCallback;
import com.xiaomi.mms.transaction.MiCloudRichMediaManagerFactory;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class MxMessageUtils
{
  public static boolean convertMx2toMms(Context paramContext, WorkingMessage.MessageStatusListener paramMessageStatusListener, Uri paramUri, boolean paramBoolean)
  {
    Object localObject2 = null;
    Mx2MessageModel localMx2MessageModel = Mx2PduPersister.loadMessageByUri(paramContext, paramUri);
    Object localObject1 = localObject2;
    if (localMx2MessageModel != null)
    {
      Conversation localConversation = Conversation.createNew(paramContext, localMx2MessageModel.getThreadId());
      paramMessageStatusListener = Mx2WorkingMessage.loadDraft(paramMessageStatusListener, localConversation);
      paramMessageStatusListener.setConversation(localConversation);
      localObject1 = localObject2;
      if (localMx2MessageModel.hasAttachment())
      {
        Attachment localAttachment = localMx2MessageModel.getAttachment();
        File localFile = localAttachment.getFile(paramContext, AudioHelper.getAudioDir());
        localObject1 = localObject2;
        if (localFile != null)
        {
          paramMessageStatusListener.removeAttachment(false);
          String str = localMx2MessageModel.getMxType();
          if (!str.equals(String.valueOf(3))) {
            localMx2MessageModel.setAttachments(null);
          }
          localMx2MessageModel.setMxType(String.valueOf(0));
          paramMessageStatusListener.setMxMessage(localMx2MessageModel);
          paramMessageStatusListener.setMessageUri(paramUri);
          paramMessageStatusListener.persistMxMessage();
          paramMessageStatusListener.setText(localMx2MessageModel.getBody());
          paramMessageStatusListener.setSubject(null, false);
          paramUri = createTempFileWithExtension(localFile, getExtensionByMimeType(mimeType));
          localObject1 = localObject2;
          if (paramUri != null)
          {
            localObject1 = localObject2;
            if (paramUri.exists())
            {
              paramMessageStatusListener.setAttachment(getAttachmentType(str), Uri.fromFile(paramUri), true);
              localObject1 = paramMessageStatusListener.saveAsMms(false);
              localConversation.setDraftState(false);
              if ((localObject1 != null) && (paramBoolean)) {
                MxMessagePduHelper.markMmsAsCommon(paramContext, (Uri)localObject1);
              }
              paramUri.delete();
            }
          }
        }
      }
    }
    return localObject1 != null;
  }
  
  /* Error */
  public static void copyFile(File paramFile1, File paramFile2)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 160	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   4: aload_1
    //   5: invokevirtual 160	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   8: invokevirtual 76	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   11: ifeq +4 -> 15
    //   14: return
    //   15: aconst_null
    //   16: astore_3
    //   17: aconst_null
    //   18: astore 7
    //   20: aconst_null
    //   21: astore 4
    //   23: aconst_null
    //   24: astore 6
    //   26: aconst_null
    //   27: astore 5
    //   29: new 162	java/io/FileInputStream
    //   32: dup
    //   33: aload_0
    //   34: invokespecial 165	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   37: astore_0
    //   38: new 167	java/io/FileOutputStream
    //   41: dup
    //   42: aload_1
    //   43: invokespecial 168	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   46: astore_1
    //   47: sipush 1024
    //   50: newarray <illegal type>
    //   52: astore_3
    //   53: aload_0
    //   54: aload_3
    //   55: invokevirtual 174	java/io/InputStream:read	([B)I
    //   58: istore_2
    //   59: iload_2
    //   60: iflt +53 -> 113
    //   63: aload_1
    //   64: aload_3
    //   65: iconst_0
    //   66: iload_2
    //   67: invokevirtual 180	java/io/OutputStream:write	([BII)V
    //   70: goto -17 -> 53
    //   73: astore_3
    //   74: aload_1
    //   75: astore 5
    //   77: aload_3
    //   78: astore_1
    //   79: aload_0
    //   80: astore_3
    //   81: aload 5
    //   83: astore 4
    //   85: ldc -74
    //   87: aload_1
    //   88: invokevirtual 185	java/io/IOException:toString	()Ljava/lang/String;
    //   91: invokestatic 191	com/xiaomi/mms/utils/logger/MyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   94: aload_0
    //   95: ifnull +7 -> 102
    //   98: aload_0
    //   99: invokevirtual 194	java/io/InputStream:close	()V
    //   102: aload 5
    //   104: ifnull -90 -> 14
    //   107: aload 5
    //   109: invokevirtual 195	java/io/OutputStream:close	()V
    //   112: return
    //   113: aload_0
    //   114: ifnull +7 -> 121
    //   117: aload_0
    //   118: invokevirtual 194	java/io/InputStream:close	()V
    //   121: aload_1
    //   122: ifnull +7 -> 129
    //   125: aload_1
    //   126: invokevirtual 195	java/io/OutputStream:close	()V
    //   129: return
    //   130: astore_0
    //   131: aload_3
    //   132: ifnull +7 -> 139
    //   135: aload_3
    //   136: invokevirtual 194	java/io/InputStream:close	()V
    //   139: aload 4
    //   141: ifnull +8 -> 149
    //   144: aload 4
    //   146: invokevirtual 195	java/io/OutputStream:close	()V
    //   149: aload_0
    //   150: athrow
    //   151: astore_1
    //   152: aload_0
    //   153: astore_3
    //   154: aload 6
    //   156: astore 4
    //   158: aload_1
    //   159: astore_0
    //   160: goto -29 -> 131
    //   163: astore 5
    //   165: aload_0
    //   166: astore_3
    //   167: aload_1
    //   168: astore 4
    //   170: aload 5
    //   172: astore_0
    //   173: goto -42 -> 131
    //   176: astore_1
    //   177: aload 7
    //   179: astore_0
    //   180: goto -101 -> 79
    //   183: astore_1
    //   184: goto -105 -> 79
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	187	0	paramFile1	File
    //   0	187	1	paramFile2	File
    //   58	9	2	i	int
    //   16	49	3	arrayOfByte	byte[]
    //   73	5	3	localIOException	IOException
    //   80	87	3	localFile1	File
    //   21	148	4	localObject1	Object
    //   27	81	5	localFile2	File
    //   163	8	5	localObject2	Object
    //   24	131	6	localObject3	Object
    //   18	160	7	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   47	53	73	java/io/IOException
    //   53	59	73	java/io/IOException
    //   63	70	73	java/io/IOException
    //   29	38	130	finally
    //   85	94	130	finally
    //   38	47	151	finally
    //   47	53	163	finally
    //   53	59	163	finally
    //   63	70	163	finally
    //   29	38	176	java/io/IOException
    //   38	47	183	java/io/IOException
  }
  
  public static File createTempFileWithExtension(File paramFile, String paramString)
  {
    paramString = null;
    try
    {
      localFile = new File(getImageCacheTempDir(), paramFile.getName());
      MyLog.v("MxMessageUtils.RICH", "createTempFile create dir failed");
    }
    catch (IOException paramFile)
    {
      try
      {
        if (!localFile.exists()) {
          copyFile(paramFile, localFile);
        }
        return localFile;
      }
      catch (IOException paramFile)
      {
        for (;;)
        {
          File localFile;
          paramFile = localFile;
        }
      }
      paramFile = paramFile;
      paramFile = paramString;
    }
    return paramFile;
  }
  
  private static int getAttachmentType(String paramString)
  {
    switch (Integer.parseInt(paramString))
    {
    default: 
      return -1;
    case 2: 
      return 1;
    }
    return 3;
  }
  
  public static String getExtensionByMimeType(String paramString)
  {
    String str2 = "";
    String str1 = str2;
    if (!TextUtils.isEmpty(paramString))
    {
      if ((!ContentType.isImageType(paramString)) || ("image/gif".equalsIgnoreCase(paramString))) {
        break label33;
      }
      str1 = ".jpg";
    }
    label33:
    do
    {
      return str1;
      str1 = str2;
    } while (!"audio/amr".equalsIgnoreCase(paramString));
    return ".amr";
  }
  
  public static File getImageCacheTempDir()
  {
    File localFile2 = new File(ImageCacheUtils.getExternalCacheDir(GlobalData.app()).getAbsolutePath() + "/tmp");
    File localFile1 = localFile2;
    if (!localFile2.exists())
    {
      localFile1 = localFile2;
      if (!localFile2.mkdirs())
      {
        MyLog.v("MxMessageUtils.RICH", "getImageCacheTempDir create dir failed");
        localFile1 = null;
      }
    }
    return localFile1;
  }
  
  public static String getMmsExternalFileDir()
  {
    return Environment.getExternalStorageDirectory().getAbsolutePath() + "/Mms";
  }
  
  public static String getNotificationDescByMx2Type(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 2: 
      return MmsApp.getApp().getString(2131362364);
    }
    return MmsApp.getApp().getString(2131362363);
  }
  
  public static String saveAttachmentFileToDisk(Context paramContext, Attachment paramAttachment)
  {
    localObject1 = null;
    File localFile = paramAttachment.getFile(paramContext, AudioHelper.getAudioDir());
    Object localObject2 = Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DOWNLOADS + "/";
    paramAttachment = (Attachment)localObject1;
    if (localFile != null) {}
    for (paramAttachment = (String)localObject2 + localFile.getName();; paramAttachment = (Attachment)localObject1)
    {
      try
      {
        localObject2 = new File(paramAttachment);
        boolean bool2 = ((File)localObject2).getParentFile().exists();
        boolean bool1 = bool2;
        if (!bool2) {
          bool1 = ((File)localObject2).getParentFile().mkdirs();
        }
        if (!bool1) {
          break label166;
        }
        copyFile(localFile, new File(paramAttachment));
      }
      catch (IOException paramAttachment)
      {
        for (;;)
        {
          label166:
          MyLog.v("MxMessageUtils.RICH", "saveAttachmentFileToDisk fail " + paramAttachment);
          paramAttachment = (Attachment)localObject1;
        }
      }
      if (!TextUtils.isEmpty(paramAttachment)) {
        paramContext.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(new File(paramAttachment))));
      }
      return paramAttachment;
      MyLog.v("MxMessageUtils.RICH", "saveAttachmentFileToDisk create dir failed");
    }
  }
  
  private static UploadResult tryUpload(Context paramContext, Uri paramUri, String paramString1, UploadEntity paramUploadEntity, Collection<String> paramCollection, String paramString2, MiCloudMxMmsTransactionHandler.MiCloudMediaManagerCallback paramMiCloudMediaManagerCallback, MiCloudRichMediaManagerFactory paramMiCloudRichMediaManagerFactory)
  {
    Object localObject2 = null;
    int i = 3;
    int k;
    Object localObject1;
    int j;
    for (;;)
    {
      k = 0;
      localObject1 = null;
      j = 0;
      for (;;)
      {
        try
        {
          localExtendedAuthToken = paramMiCloudMediaManagerCallback.getMediaManagerToken(paramString1);
          if (localExtendedAuthToken == null) {
            continue;
          }
          localObject1 = localExtendedAuthToken;
          MyLog.d("MxMessageUtils.RICH", "upload mx richmedia with fileToken : " + localExtendedAuthToken.toPlain());
          localObject1 = localExtendedAuthToken;
        }
        catch (IOException localIOException2)
        {
          ExtendedAuthToken localExtendedAuthToken;
          MyLog.e("MxMessageUtils.RICH", "io error when getting file token");
          j = 1;
          continue;
          if (localObject1 != null) {
            continue;
          }
          MyLog.e("MxMessageUtils.RICH", "failed to get file token, upload ignore");
          MxMessagePduHelper.setResponseStatus(paramContext, paramUri, 225);
          j = k;
          continue;
          localObject1 = paramMiCloudRichMediaManagerFactory.getMiCloudRichMediaManager(paramContext, paramString1, (ExtendedAuthToken)localObject1, paramString2);
          try
          {
            localObject1 = StorageServerHelper.uploadFile((MiCloudRichMediaManager)localObject1, paramUploadEntity, paramCollection);
            localObject2 = localObject1;
            j = k;
          }
          catch (MxLogicException localMxLogicException)
          {
            MyLog.e("MxMessageUtils.RICH", "error when uploading pdu", localMxLogicException);
            MxMessagePduHelper.setResponseStatus(paramContext, paramUri, 224);
            j = k;
          }
          catch (IOException localIOException1)
          {
            MyLog.e("MxMessageUtils.RICH", "io error when uploading pdu", localIOException1);
            MxMessagePduHelper.setResponseStatus(paramContext, paramUri, 195);
            j = k;
          }
          catch (ServerErrorException localServerErrorException)
          {
            MyLog.e("MxMessageUtils.RICH", "server error when uploading pdu", localServerErrorException);
            MxMessagePduHelper.setResponseStatus(paramContext, paramUri, 195);
            j = k;
          }
          catch (InvalidTokenException localInvalidTokenException)
          {
            MyLog.e("MxMessageUtils.RICH", "upload mx richmeida failed, because token invalid or expired, retty refresh file token", localInvalidTokenException);
            j = 1;
            paramMiCloudMediaManagerCallback.onMediaManagerTokenInvalid(paramString1);
          }
          continue;
          i -= 1;
        }
        if (j == 0) {
          continue;
        }
        MxMessagePduHelper.setResponseStatus(paramContext, paramUri, 195);
        j = k;
        if ((j != 0) && (i > 0)) {
          continue;
        }
        return (UploadResult)localObject2;
        localObject1 = localExtendedAuthToken;
        MyLog.d("MxMessageUtils.RICH", "upload mx richmedia, but fileToken is null");
        localObject1 = localExtendedAuthToken;
      }
    }
  }
  
  public static UploadResult tryUploadFile(Context paramContext, Uri paramUri, String paramString1, UploadEntity paramUploadEntity, Collection<String> paramCollection, String paramString2, MiCloudMxMmsTransactionHandler.MiCloudMediaManagerCallback paramMiCloudMediaManagerCallback, MiCloudRichMediaManagerFactory paramMiCloudRichMediaManagerFactory)
  {
    return tryUpload(paramContext, paramUri, paramString1, paramUploadEntity, paramCollection, paramString2, paramMiCloudMediaManagerCallback, paramMiCloudRichMediaManagerFactory);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.MxMessageUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */