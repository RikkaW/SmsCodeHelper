package com.android.mms.audio;

import android.app.Application;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.data.Conversation;
import com.android.mms.data.WorkingMessage.MessageStatusListener;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.data.Mx2MessageModel;
import com.xiaomi.mms.mx.audio.player.AudioTalkMediaPlayer;
import com.xiaomi.mms.mx.audio.player.MediaPlayerObserver;
import com.xiaomi.mms.mx.data.Attachment;
import com.xiaomi.mms.mx.utils.Mx2ExtentionHelper;
import com.xiaomi.mms.transaction.Mx2MmsTransactionService;
import com.xiaomi.mms.transaction.MxMmsTransactionService;
import com.xiaomi.mms.utils.Mx2PduPersister;
import com.xiaomi.mms.utils.MxMessagePduHelper;
import com.xiaomi.mms.utils.MxMessageUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AudioHelper
{
  private static final String MMS_PRIORITY_CACHE_DIR;
  private static String sCachePath = null;
  private static AudioManager.OnAudioFocusChangeListener sOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener()
  {
    public void onAudioFocusChange(int paramAnonymousInt)
    {
      if (paramAnonymousInt == -1)
      {
        AudioTalkMediaPlayer localAudioTalkMediaPlayer = AudioTalkMediaPlayer.getInstance(MmsApp.getApp());
        if (localAudioTalkMediaPlayer.isPlaying()) {
          localAudioTalkMediaPlayer.stop();
        }
      }
    }
  };
  private static final float sScaleConstant;
  
  static
  {
    MMS_PRIORITY_CACHE_DIR = Environment.getExternalStorageDirectory() + "/Mms/cache";
    sScaleConstant = getAppgetResourcesgetDisplayMetricsdensity;
  }
  
  public static int convertdipTopx(int paramInt)
  {
    float f1 = paramInt;
    float f2 = sScaleConstant;
    if (paramInt >= 0) {}
    for (paramInt = 1;; paramInt = -1) {
      return (int)(paramInt * 0.5F + f2 * f1);
    }
  }
  
  public static boolean gainFocus()
  {
    return ((AudioManager)MmsApp.getApp().getSystemService("audio")).requestAudioFocus(sOnAudioFocusChangeListener, 3, 2) == 1;
  }
  
  public static String getAudioDir()
  {
    return getAudioDir(false);
  }
  
  public static String getAudioDir(boolean paramBoolean)
  {
    Object localObject;
    if (TextUtils.isEmpty(sCachePath))
    {
      localObject = MxMessageUtils.getMmsExternalFileDir() + "/cache/audio";
      paramBoolean = true;
      sCachePath = (String)localObject;
    }
    if (paramBoolean)
    {
      localObject = new File(sCachePath);
      if (!((File)localObject).exists()) {
        ((File)localObject).mkdirs();
      }
    }
    return sCachePath;
  }
  
  public static String getAudioPath()
  {
    return getAudioDir() + "/" + System.currentTimeMillis() + ".amr";
  }
  
  /* Error */
  public static String getMediaFilePathByUri(android.content.Context paramContext, Uri paramUri)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 4
    //   6: aload_0
    //   7: invokevirtual 150	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   10: aload_1
    //   11: iconst_1
    //   12: anewarray 152	java/lang/String
    //   15: dup
    //   16: iconst_0
    //   17: ldc -102
    //   19: aastore
    //   20: aconst_null
    //   21: aconst_null
    //   22: aconst_null
    //   23: invokevirtual 160	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   26: astore_1
    //   27: aload 5
    //   29: astore_0
    //   30: aload_1
    //   31: ifnull +29 -> 60
    //   34: aload 4
    //   36: astore_0
    //   37: aload_1
    //   38: invokeinterface 165 1 0
    //   43: ifeq +11 -> 54
    //   46: aload_1
    //   47: iconst_0
    //   48: invokeinterface 169 2 0
    //   53: astore_0
    //   54: aload_1
    //   55: invokeinterface 172 1 0
    //   60: aload_0
    //   61: astore_1
    //   62: invokestatic 177	com/android/mms/util/MSimUtils:isAndroid50	()Z
    //   65: ifeq +71 -> 136
    //   68: aload_0
    //   69: ldc 127
    //   71: invokevirtual 181	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   74: astore_0
    //   75: aload_0
    //   76: aload_0
    //   77: arraylength
    //   78: iconst_1
    //   79: isub
    //   80: aaload
    //   81: ldc -73
    //   83: invokevirtual 181	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   86: iconst_0
    //   87: aaload
    //   88: ldc -71
    //   90: invokevirtual 181	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   93: astore_0
    //   94: aload_0
    //   95: aload_0
    //   96: arraylength
    //   97: iconst_1
    //   98: isub
    //   99: aaload
    //   100: invokestatic 191	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   103: lstore_2
    //   104: new 23	java/lang/StringBuilder
    //   107: dup
    //   108: invokespecial 26	java/lang/StringBuilder:<init>	()V
    //   111: iconst_1
    //   112: invokestatic 98	com/android/mms/audio/AudioHelper:getAudioDir	(Z)Ljava/lang/String;
    //   115: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: ldc 127
    //   120: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   123: lload_2
    //   124: invokevirtual 136	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   127: ldc -118
    //   129: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: invokevirtual 45	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   135: astore_1
    //   136: aload_1
    //   137: areturn
    //   138: astore_0
    //   139: aload_1
    //   140: invokeinterface 172 1 0
    //   145: aload_0
    //   146: athrow
    //   147: astore_0
    //   148: ldc -63
    //   150: ldc -61
    //   152: invokestatic 201	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   155: pop
    //   156: aconst_null
    //   157: areturn
    //   158: astore_0
    //   159: ldc -63
    //   161: ldc -53
    //   163: invokestatic 201	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   166: pop
    //   167: aconst_null
    //   168: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	169	0	paramContext	android.content.Context
    //   0	169	1	paramUri	Uri
    //   103	21	2	l	long
    //   4	31	4	localObject1	Object
    //   1	27	5	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   37	54	138	finally
    //   68	136	147	java/lang/NumberFormatException
    //   68	136	158	java/lang/Exception
  }
  
  public static void markReadAsync(final Uri paramUri, Attachment paramAttachment)
  {
    new AsyncTask()
    {
      protected Void doInBackground(Void... paramAnonymousVarArgs)
      {
        val$attachment.mIsRead = true;
        paramAnonymousVarArgs = new ArrayList();
        paramAnonymousVarArgs.add(val$attachment);
        Mx2PduPersister.updateExtension(MmsApp.getApp(), paramUri, paramAnonymousVarArgs);
        return null;
      }
    }.execute(new Void[0]);
  }
  
  public static void playAudio(long paramLong, String paramString, AudioSensorManager paramAudioSensorManager, AudioItemCache paramAudioItemCache)
  {
    AudioTalkMediaPlayer localAudioTalkMediaPlayer = AudioTalkMediaPlayer.getInstance(MmsApp.getApp());
    localAudioTalkMediaPlayer.addToPlayList(paramLong, 0L, 0, paramString, "0", new MediaPlayerObserver(new AudioPlayingHandler(paramLong, paramAudioSensorManager, paramAudioItemCache)), false);
    localAudioTalkMediaPlayer.playNext();
  }
  
  public static void releaseFocus()
  {
    ((AudioManager)MmsApp.getApp().getSystemService("audio")).abandonAudioFocus(sOnAudioFocusChangeListener);
  }
  
  public static void sendRecordedAudio(String paramString, int paramInt1, final long paramLong, final boolean paramBoolean, final WorkingMessage.MessageStatusListener paramMessageStatusListener, final int paramInt2, final Conversation paramConversation)
  {
    Log.i("AudioHelper.RICH", "AudioHelper   sendRecordedAudio   isMx2 = " + paramBoolean + "     audioPath =" + paramString + "     audioDuration=" + paramInt1 + "       threadId=" + paramLong + "     slotId=" + paramInt2);
    new AsyncTask()
    {
      protected Void doInBackground(Void... paramAnonymousVarArgs)
      {
        paramAnonymousVarArgs = new File(val$audioPath);
        Mx2MessageModel localMx2MessageModel = new Mx2MessageModel();
        mConversation = paramConversation;
        localMx2MessageModel.setThreadId(paramLong);
        localMx2MessageModel.setDate(System.currentTimeMillis() / 1000L);
        localMx2MessageModel.setDateSent(System.currentTimeMillis() / 1000L);
        localMx2MessageModel.setType(128);
        localMx2MessageModel.setMxType(String.valueOf(3));
        Attachment localAttachment = new Attachment();
        playTime = paramInt2;
        mimeType = "audio/amr";
        String[] arrayOfString = val$audioPath.split("/");
        filename = arrayOfString[(arrayOfString.length - 1)];
        datasize = paramAnonymousVarArgs.length();
        localMx2MessageModel.addAttachment(localAttachment);
        localMx2MessageModel.setMxExtension(Mx2ExtentionHelper.generateAttachmentsExtentionString(localMx2MessageModel.getAttachments(), true));
        localMx2MessageModel.setBoxId(3);
        localMx2MessageModel.setSimId(MSimUtils.getSimIdBySlotId(paramBoolean));
        paramAnonymousVarArgs = Mx2PduPersister.insertMxMessage(MmsApp.getApp(), localMx2MessageModel);
        if (paramAnonymousVarArgs != null)
        {
          MxMessagePduHelper.markMmsSendAsMx(MmsApp.getApp(), paramAnonymousVarArgs, true);
          if (paramMessageStatusListener) {
            Mx2MmsTransactionService.startSendMx2(MmsApp.getApp(), paramAnonymousVarArgs);
          }
        }
        for (;;)
        {
          return null;
          MxMessageUtils.convertMx2toMms(MmsApp.getApp(), val$listener, paramAnonymousVarArgs, false);
          MxMmsTransactionService.startSendMms(MmsApp.getApp(), paramAnonymousVarArgs);
          continue;
          Log.e("AudioHelper.RICH", "insert MxMessage failed, AudioHelper sendRecordedAudio    isMx2 = " + paramMessageStatusListener + "     audioPath = " + val$audioPath + "     audioDuration = " + paramInt2 + "       threadId = " + paramLong + "     slotId = " + paramBoolean);
        }
      }
      
      protected void onPostExecute(Void paramAnonymousVoid)
      {
        val$listener.onMessageSent();
      }
    }.execute(new Void[0]);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.audio.AudioHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */