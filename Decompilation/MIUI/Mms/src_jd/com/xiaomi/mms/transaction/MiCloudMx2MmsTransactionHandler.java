package com.xiaomi.mms.transaction;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.audio.AudioHelper;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.util.MSimUtils;
import com.xiaomi.micloudsdk.micloudrichmedia.UploadEntity;
import com.xiaomi.micloudsdk.micloudrichmedia.UploadFile;
import com.xiaomi.micloudsdk.micloudrichmedia.UploadResult;
import com.xiaomi.mms.data.Mx2MessageModel;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.data.MxIdCache.MxIdCacheItem;
import com.xiaomi.mms.mx.data.Attachment;
import com.xiaomi.mms.mx.utils.AttachmentUtils;
import com.xiaomi.mms.net.exception.MxLogicException;
import com.xiaomi.mms.utils.Mx2PduPersister;
import com.xiaomi.mms.utils.MxConfigCompat;
import com.xiaomi.mms.utils.MxMessagePduHelper;
import com.xiaomi.mms.utils.MxMessageUtils;
import com.xiaomi.mms.utils.MxRequestEnv;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MiCloudMx2MmsTransactionHandler
  implements MxMmsTransactionHandler
{
  private static final String[] MX_MMS_PROJECT = { "ct_t", "m_size", "mx_id", "mx_status", "sim_id" };
  private MiCloudMxMmsTransactionHandler.MiCloudMediaManagerCallback mCallback;
  private Context mContext;
  private MiCloudRichMediaManagerFactory mFactory;
  
  public MiCloudMx2MmsTransactionHandler(Context paramContext, MiCloudMxMmsTransactionHandler.MiCloudMediaManagerCallback paramMiCloudMediaManagerCallback, MiCloudRichMediaManagerFactory paramMiCloudRichMediaManagerFactory)
  {
    mContext = paramContext;
    mCallback = paramMiCloudMediaManagerCallback;
    mFactory = paramMiCloudRichMediaManagerFactory;
  }
  
  public Uri handleReceiveMxMms(Uri paramUri)
  {
    Log.v("MiCloudMx2MmsTransactionHandler.RICH", "handleReceiveMx2");
    return null;
  }
  
  public Uri handleSendMxMms(Uri paramUri)
  {
    Mx2MessageModel localMx2MessageModel = Mx2PduPersister.loadMessageByUri(mContext, paramUri);
    if (localMx2MessageModel == null)
    {
      Log.v("MiCloudMx2MmsTransactionHandler.RICH", "PduDBUtils.loadMessageFromPdu fail");
      localObject3 = null;
    }
    Object localObject2;
    Object localObject1;
    String str1;
    ArrayList localArrayList1;
    Object localObject4;
    int j;
    long l4;
    long l3;
    long l1;
    long l2;
    int m;
    for (;;)
    {
      return (Uri)localObject3;
      localObject3 = localMx2MessageModel.getAttachments();
      localObject2 = null;
      localObject1 = localObject2;
      if (localObject3 != null)
      {
        localObject1 = localObject2;
        if (!((List)localObject3).isEmpty()) {
          localObject1 = (Attachment)((List)localObject3).get(0);
        }
      }
      localObject3 = paramUri;
      if (localObject1 != null)
      {
        localObject3 = paramUri;
        if (!TextUtils.isEmpty(filename))
        {
          localObject2 = new File(((Attachment)localObject1).getLocalPath(mContext, AudioHelper.getAudioDir()));
          if (((File)localObject2).exists()) {
            Log.v("MiCloudMx2MmsTransactionHandler.RICH", "file exist");
          }
          for (;;)
          {
            str1 = ((File)localObject2).getAbsolutePath();
            i = filename.lastIndexOf(".");
            localObject2 = ".jpg";
            if (i > 0) {
              localObject2 = filename.substring(i);
            }
            localArrayList1 = new ArrayList();
            localObject3 = Conversation.get(localMx2MessageModel.getThreadId()).getRecipients().iterator();
            while (((Iterator)localObject3).hasNext())
            {
              localObject4 = (Contact)((Iterator)localObject3).next();
              localObject4 = MxIdCache.getOrQuery(mContext, ((Contact)localObject4).getNumber());
              if (localObject4 != null) {
                localArrayList1.add(((MxIdCache.MxIdCacheItem)localObject4).getMId());
              }
            }
            MxMessagePduHelper.setResponseStatus(mContext, paramUri, 228);
          }
          i = 0;
          j = 0;
          l4 = 0L;
          l3 = -1L;
          localObject3 = mContext.getContentResolver().query(paramUri, MX_MMS_PROJECT, null, null, null);
          l1 = l4;
          l2 = l3;
          if (localObject3 != null)
          {
            l1 = l4;
            i = j;
            l2 = l3;
          }
          try
          {
            if (((Cursor)localObject3).moveToFirst()) {
              if (1 != ((Cursor)localObject3).getInt(3)) {
                break label422;
              }
            }
            label422:
            for (i = 1;; i = 0)
            {
              l1 = ((Cursor)localObject3).getLong(2);
              l2 = ((Cursor)localObject3).getLong(4);
              ((Cursor)localObject3).close();
              localObject3 = paramUri;
              if (i == 0) {
                break;
              }
              l3 = ContentUris.parseId(paramUri);
              i = MSimUtils.getSlotIdBySimInfoId(l2);
              m = i;
              if (i >= 0) {
                break label437;
              }
              i = MSimUtils.getInsertedSlotId();
              m = i;
              if (i >= 0) {
                break label437;
              }
              MxMessagePduHelper.handleMxMmsFailed(mContext, l3, true, true);
              return paramUri;
            }
            bool = MxActivateService.isMxEnabled(mContext, m);
          }
          finally
          {
            ((Cursor)localObject3).close();
          }
        }
      }
    }
    label437:
    boolean bool;
    Object localObject3 = PushSession.getInstance(mContext);
    String str2 = ((PushSession)localObject3).getMyFullMid(m);
    String str3 = ((PushSession)localObject3).getMyMid(m);
    i = 0;
    k = 0;
    if (localArrayList1 == null) {
      MxMessagePduHelper.setResponseStatus(mContext, paramUri, 224);
    }
    while (k != 0)
    {
      MxMessagePduHelper.markMmsStatus(mContext, paramUri, 128);
      return paramUri;
      if (!bool)
      {
        MxMessagePduHelper.setResponseStatus(mContext, paramUri, 224);
      }
      else if ((str2 == null) || (str3 == null))
      {
        MxMessagePduHelper.setResponseStatus(mContext, paramUri, 130);
      }
      else
      {
        ArrayList localArrayList2 = new ArrayList(4);
        j = 0;
        for (;;)
        {
          k = i;
          if (j >= localArrayList1.size()) {
            break;
          }
          k = j + 4;
          int n = k;
          if (k > localArrayList1.size()) {
            n = localArrayList1.size();
          }
          k = j;
          while (k < n)
          {
            localArrayList2.add(localArrayList1.get(k));
            k += 1;
          }
          for (;;)
          {
            try
            {
              localObject3 = new UploadFile(str1, "mixin2", (String)localObject2);
              k = i;
            }
            catch (IOException localIOException)
            {
              try
              {
                MxRequestEnv.getMxRequestEnv(mContext).setSimIndex(m).setIsMxV2(true);
                k = i;
                localObject3 = MxMessageUtils.tryUploadFile(mContext, paramUri, str3, (UploadEntity)localObject3, localArrayList2, MxConfigCompat.getMxV2RichMediaUrl(mContext, str3), mCallback, mFactory);
                k = i;
                if (localObject3 != null)
                {
                  k = i;
                  if (TextUtils.isEmpty(shareId)) {
                    break label1129;
                  }
                  k = i;
                  if (expireAt <= 0L) {
                    break label1129;
                  }
                  int i2 = 1;
                  k = i;
                  String str4 = shareId;
                  k = i;
                  localObject4 = downloadUrl;
                  k = i;
                  shareId = str4;
                  k = i;
                  link = ((String)localObject4);
                  k = i;
                  l2 = expireAt;
                  k = i;
                  localObject4 = localMx2MessageModel.getBody();
                  localObject3 = localObject4;
                  if (localObject4 == null) {
                    localObject3 = "";
                  }
                  k = i;
                  int i3 = AttachmentUtils.getMessageTypeFromMimeType(mimeType);
                  k = i;
                  l4 = System.currentTimeMillis();
                  k = i;
                  localMx2MessageModel.setDate(l4 / 1000L);
                  k = i;
                  localMx2MessageModel.setDateSent(l4 / 1000L);
                  k = i;
                  localMx2MessageModel.setDateMsPart(l4 % 1000L);
                  i1 = j;
                  k = i2;
                  if (i1 < n)
                  {
                    k = i;
                    if (mCallback.onUploadMediaSuccess(paramUri, l1, (String)localObject3, str2, (String)localArrayList1.get(i1), String.valueOf(i3), str4, MxMessageService.adjustExpiredTime(l2), datasize, localMx2MessageModel.buildXmppExtension())) {
                      break label1100;
                    }
                    k = 0;
                  }
                  if (k == 0) {
                    break label1109;
                  }
                  k = i;
                  Mx2PduPersister.updateMxMessage(mContext, localMx2MessageModel, localMx2MessageModel.getMsgId());
                  k = i;
                  MxMessagePduHelper.setResponseStatus(mContext, paramUri, 128);
                  k = 1;
                  i = 1;
                  MxMessageTrackService.startTrack(mContext);
                  k = i;
                }
                localArrayList2.clear();
                if (k != 0) {
                  break label1160;
                }
                Log.w("MiCloudMx2MmsTransactionHandler.RICH", "sending progress stops, due to uploading failure");
              }
              catch (MxLogicException localMxLogicException2)
              {
                for (;;)
                {
                  int i1;
                  i = k;
                }
              }
              localIOException = localIOException;
              Log.e("MiCloudMx2MmsTransactionHandler.RICH", "error when construct upload entity", localIOException);
              throw new MxLogicException("failed to construct upload entity", localIOException);
            }
            catch (MxLogicException localMxLogicException1) {}
            MyLog.e("MiCloudMx2MmsTransactionHandler.RICH", "upload mx2 richmedia error", localMxLogicException1);
            MxMessagePduHelper.setResponseStatus(mContext, paramUri, 224);
            k = i;
            continue;
            label1100:
            i1 += 1;
            continue;
            label1109:
            k = i;
            MxMessagePduHelper.setResponseStatus(mContext, paramUri, 224);
            k = i;
            continue;
            label1129:
            k = i;
            MyLog.e("MiCloudMx2MmsTransactionHandler.RICH", "no shared id or expireAt value, uploading failed");
            k = i;
            MxMessagePduHelper.setResponseStatus(mContext, paramUri, 224);
            k = i;
          }
          label1160:
          j += 4;
          i = k;
        }
      }
    }
    MxMessagePduHelper.handleMxMmsFailed(mContext, l3, true, true);
    return paramUri;
  }
  
  public void setSendByMxV2(boolean paramBoolean) {}
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.transaction.MiCloudMx2MmsTransactionHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */