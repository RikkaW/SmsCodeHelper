package com.android.mms.audio;

import android.os.AsyncTask;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.data.Conversation;
import com.android.mms.data.WorkingMessage.MessageStatusListener;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.data.Mx2MessageModel;
import com.xiaomi.mms.mx.data.Attachment;
import com.xiaomi.mms.mx.utils.Mx2ExtentionHelper;
import com.xiaomi.mms.transaction.Mx2MmsTransactionService;
import com.xiaomi.mms.transaction.MxMmsTransactionService;
import com.xiaomi.mms.utils.Mx2PduPersister;
import com.xiaomi.mms.utils.MxMessagePduHelper;
import com.xiaomi.mms.utils.MxMessageUtils;
import java.io.File;

final class AudioHelper$2
  extends AsyncTask<Void, Void, Void>
{
  AudioHelper$2(String paramString, Conversation paramConversation, long paramLong, int paramInt1, int paramInt2, boolean paramBoolean, WorkingMessage.MessageStatusListener paramMessageStatusListener) {}
  
  protected Void doInBackground(Void... paramVarArgs)
  {
    paramVarArgs = new File(val$audioPath);
    Mx2MessageModel localMx2MessageModel = new Mx2MessageModel();
    mConversation = val$con;
    localMx2MessageModel.setThreadId(val$threadId);
    localMx2MessageModel.setDate(System.currentTimeMillis() / 1000L);
    localMx2MessageModel.setDateSent(System.currentTimeMillis() / 1000L);
    localMx2MessageModel.setType(128);
    localMx2MessageModel.setMxType(String.valueOf(3));
    Attachment localAttachment = new Attachment();
    playTime = val$audioDuration;
    mimeType = "audio/amr";
    String[] arrayOfString = val$audioPath.split("/");
    filename = arrayOfString[(arrayOfString.length - 1)];
    datasize = paramVarArgs.length();
    localMx2MessageModel.addAttachment(localAttachment);
    localMx2MessageModel.setMxExtension(Mx2ExtentionHelper.generateAttachmentsExtentionString(localMx2MessageModel.getAttachments(), true));
    localMx2MessageModel.setBoxId(3);
    localMx2MessageModel.setSimId(MSimUtils.getSimIdBySlotId(val$slotId));
    paramVarArgs = Mx2PduPersister.insertMxMessage(MmsApp.getApp(), localMx2MessageModel);
    if (paramVarArgs != null)
    {
      MxMessagePduHelper.markMmsSendAsMx(MmsApp.getApp(), paramVarArgs, true);
      if (val$isMx2) {
        Mx2MmsTransactionService.startSendMx2(MmsApp.getApp(), paramVarArgs);
      }
    }
    for (;;)
    {
      return null;
      MxMessageUtils.convertMx2toMms(MmsApp.getApp(), val$listener, paramVarArgs, false);
      MxMmsTransactionService.startSendMms(MmsApp.getApp(), paramVarArgs);
      continue;
      Log.e("AudioHelper.RICH", "insert MxMessage failed, AudioHelper sendRecordedAudio    isMx2 = " + val$isMx2 + "     audioPath = " + val$audioPath + "     audioDuration = " + val$audioDuration + "       threadId = " + val$threadId + "     slotId = " + val$slotId);
    }
  }
  
  protected void onPostExecute(Void paramVoid)
  {
    val$listener.onMessageSent();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.audio.AudioHelper.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */