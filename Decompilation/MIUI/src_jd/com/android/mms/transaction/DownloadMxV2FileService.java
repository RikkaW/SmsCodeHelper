package com.android.mms.transaction;

import android.app.IntentService;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.Telephony.Mms;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.audio.AudioHelper;
import com.android.mms.util.MSimUtils;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.micloudsdk.micloudrichmedia.MiCloudRichMediaManager;
import com.xiaomi.mms.data.Mx2MessageModel;
import com.xiaomi.mms.mx.common.GlobalData;
import com.xiaomi.mms.mx.data.Attachment;
import com.xiaomi.mms.net.exception.InvalidTokenException;
import com.xiaomi.mms.net.exception.ServerErrorException;
import com.xiaomi.mms.transaction.MxV2FileTokenUtils;
import com.xiaomi.mms.transaction.PushSession;
import com.xiaomi.mms.utils.Mx2PduPersister;
import com.xiaomi.mms.utils.MxConfigCompat;
import com.xiaomi.mms.utils.MxMessagePduHelper;
import com.xiaomi.mms.utils.MxRequestEnv;
import com.xiaomi.mms.utils.StorageServerHelper;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DownloadMxV2FileService
  extends IntentService
{
  private static Set<String> sDownloadIngSharedIdSet = Collections.synchronizedSet(new HashSet());
  
  public DownloadMxV2FileService()
  {
    super("DownloadMxV2FileService");
  }
  
  private void downloadAudioFile(String paramString1, int paramInt, String paramString2)
  {
    paramString2 = new File(paramString2);
    if (paramString2.exists()) {
      return;
    }
    downloadFile(getBaseContext(), paramString1, paramString2, paramInt);
  }
  
  private void downloadFile(Context paramContext, String paramString, File paramFile, int paramInt)
  {
    int j = 0;
    int m = 3;
    int n;
    int k;
    int i;
    for (;;)
    {
      n = 0;
      MiCloudRichMediaManager localMiCloudRichMediaManager = getMiCloudRichMediaManager(paramContext, paramString, paramInt);
      k = j;
      i = n;
      if (localMiCloudRichMediaManager != null) {}
      try
      {
        MxRequestEnv.getMxRequestEnv(paramContext).setSimIndex(paramInt).setIsMxV2(true);
        StorageServerHelper.downloadFile(localMiCloudRichMediaManager, paramString, paramFile);
        boolean bool = paramFile.exists();
        k = j;
        i = n;
        if (!bool)
        {
          i = 1;
          k = j;
        }
      }
      catch (InvalidTokenException localInvalidTokenException1)
      {
        for (;;)
        {
          k = 1;
          MxV2FileTokenUtils.invalidFileToken(GlobalData.app(), PushSession.getInstance(GlobalData.app()).getMyMid(paramInt));
          MyLog.e("DownloadFileService.RICH", "fail to download file, InvalidTokenException ", localInvalidTokenException1);
          i = n;
        }
      }
      catch (ServerErrorException localServerErrorException1)
      {
        for (;;)
        {
          i = 1;
          MyLog.e("DownloadFileService.RICH", "fail to download file, ServerErrorException ", localServerErrorException1);
          k = j;
        }
      }
      catch (IOException localIOException1)
      {
        for (;;)
        {
          i = 1;
          MyLog.e("DownloadFileService.RICH", "fail to download file, IOException", localIOException1);
          k = j;
        }
      }
      j = k;
      if (i != 0)
      {
        localMiCloudRichMediaManager = getOtherIDCMiCloudRichMediaManager(paramContext, paramString, paramInt);
        j = k;
        if (localMiCloudRichMediaManager == null) {}
      }
      try
      {
        MxRequestEnv.getMxRequestEnv(paramContext).setSimIndex(paramInt).setIsMxV2(true);
        StorageServerHelper.downloadFile(localMiCloudRichMediaManager, paramString, paramFile);
        j = k;
      }
      catch (InvalidTokenException localInvalidTokenException2)
      {
        for (;;)
        {
          j = 1;
          MxV2FileTokenUtils.invalidOtherIDCFileToken(GlobalData.app(), PushSession.getInstance(GlobalData.app()).getMyMid(paramInt));
          MyLog.e("DownloadFileService.RICH", "fail to download file  from other idc, InvalidTokenException ", localInvalidTokenException2);
        }
      }
      catch (ServerErrorException localServerErrorException2)
      {
        for (;;)
        {
          MyLog.e("DownloadFileService.RICH", "fail to download file from other idc, ServerErrorException ", localServerErrorException2);
          j = k;
        }
      }
      catch (IOException localIOException2)
      {
        for (;;)
        {
          MyLog.e("DownloadFileService.RICH", "fail to download file from other idc, IOException", localIOException2);
          j = k;
        }
        m -= 1;
      }
      if ((j == 0) || (m <= 0)) {
        return;
      }
    }
  }
  
  private MiCloudRichMediaManager getMiCloudRichMediaManager(Context paramContext, String paramString, int paramInt)
  {
    if (paramInt < 0) {}
    ExtendedAuthToken localExtendedAuthToken;
    do
    {
      do
      {
        return null;
        paramString = PushSession.getInstance(GlobalData.app()).getMyMid(paramInt);
      } while (TextUtils.isEmpty(paramString));
      localExtendedAuthToken = MxV2FileTokenUtils.getFileTokenbysimIndex(GlobalData.app(), paramInt);
    } while (localExtendedAuthToken == null);
    return new MiCloudRichMediaManager(GlobalData.app(), paramString, localExtendedAuthToken, MxConfigCompat.getMxV2RichMediaUrl(paramContext, paramString));
  }
  
  private MiCloudRichMediaManager getOtherIDCMiCloudRichMediaManager(Context paramContext, String paramString, int paramInt)
  {
    if (paramInt < 0) {}
    ExtendedAuthToken localExtendedAuthToken;
    do
    {
      do
      {
        return null;
        paramString = PushSession.getInstance(GlobalData.app()).getMyMid(paramInt);
      } while (TextUtils.isEmpty(paramString));
      localExtendedAuthToken = MxV2FileTokenUtils.getOtherIDCFileTokenbysimIndex(GlobalData.app(), paramInt);
    } while (localExtendedAuthToken == null);
    return new MiCloudRichMediaManager(GlobalData.app(), paramString, localExtendedAuthToken, MxConfigCompat.getOtherIDCMxV2RichMediaUrl(paramContext, paramString));
  }
  
  public static boolean isDownloading(String paramString)
  {
    return sDownloadIngSharedIdSet.contains(paramString);
  }
  
  public static void startDownloadAudio(Context paramContext, String paramString, int paramInt, Uri paramUri, boolean paramBoolean)
  {
    startDownloadAudio(paramContext, paramString, paramInt, paramUri, paramBoolean, null);
  }
  
  public static void startDownloadAudio(Context paramContext, String paramString1, int paramInt, Uri paramUri, boolean paramBoolean, String paramString2)
  {
    Intent localIntent = new Intent("com.xiaomi.mms.action.download.audio");
    localIntent.setClass(GlobalData.app(), DownloadMxV2FileService.class);
    localIntent.putExtra("shareId", paramString1);
    localIntent.putExtra(MSimUtils.SLOT_ID, paramInt);
    localIntent.putExtra("uri", paramUri.toString());
    localIntent.putExtra("isMsgIn", paramBoolean);
    if (!TextUtils.isEmpty(paramString2)) {
      localIntent.putExtra("fileName", paramString2);
    }
    paramContext.startService(localIntent);
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    if (paramIntent == null) {}
    Uri localUri;
    boolean bool;
    do
    {
      do
      {
        do
        {
          return;
        } while (!"com.xiaomi.mms.action.download.audio".equalsIgnoreCase(paramIntent.getAction()));
        String str = paramIntent.getStringExtra("shareId");
        if (TextUtils.isEmpty(str))
        {
          Log.e("DownloadFileService.RICH", "Download audio get shareId is null");
          return;
        }
        i = MSimUtils.getSlotIdFromIntent(paramIntent);
        localUri = Uri.parse(paramIntent.getStringExtra("uri"));
        bool = paramIntent.getBooleanExtra("isMsgIn", false);
        localObject = paramIntent.getStringExtra("fileName");
        paramIntent = (Intent)localObject;
        if (TextUtils.isEmpty((CharSequence)localObject)) {
          paramIntent = System.currentTimeMillis() + ".amr";
        }
        localObject = AudioHelper.getAudioDir(true);
        localObject = (String)localObject + "/" + paramIntent;
        sDownloadIngSharedIdSet.add(str);
        downloadAudioFile(str, i, (String)localObject);
        sDownloadIngSharedIdSet.remove(str);
      } while (!new File((String)localObject).exists());
      Object localObject = Mx2PduPersister.loadMessageByUri(getApplicationContext(), localUri);
      if (localObject == null)
      {
        Log.v("DownloadFileService.RICH", "DownloadFileService.   loadMessageByUri    fail    uri = " + localUri);
        return;
      }
      localObject = ((Mx2MessageModel)localObject).getAttachment();
      filename = paramIntent;
      paramIntent = new ArrayList();
      paramIntent.add(localObject);
      int i = Mx2PduPersister.updateExtension(getApplicationContext(), localUri, paramIntent);
      Log.i("DownloadFileService.RICH", "onHandleIntent    result = " + i);
    } while (!bool);
    MxMessagePduHelper.markMmsMxStatus(getApplicationContext(), ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, ContentUris.parseId(localUri)).buildUpon().appendQueryParameter("blocked_flag", "2").build(), 65537);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.DownloadMxV2FileService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */