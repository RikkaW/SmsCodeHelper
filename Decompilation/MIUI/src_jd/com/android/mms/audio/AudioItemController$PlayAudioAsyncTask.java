package com.android.mms.audio;

import android.content.ContentUris;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.Telephony.Mms;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.android.mms.MmsApp;
import com.android.mms.transaction.DownloadMxV2FileService;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.mx.data.Attachment;
import java.io.File;

class AudioItemController$PlayAudioAsyncTask
  extends AsyncTask<String, Void, Integer>
{
  private AudioItemController$PlayAudioAsyncTask(AudioItemController paramAudioItemController) {}
  
  protected Integer doInBackground(String... paramVarArgs)
  {
    Log.v("AudioItemController.RICH", "MX2AudioPlay the spath = " + AudioItemController.access$100());
    if ((AudioItemController.access$200(this$0) != null) && (TextUtils.equals(AudioItemController.access$200(this$0), AudioItemController.access$100())))
    {
      AudioHelper.getAudioDir(true);
      paramVarArgs = new File(AudioItemController.access$200(this$0));
      String[] arrayOfString = paramVarArgs;
      if (!paramVarArgs.exists())
      {
        arrayOfString = paramVarArgs;
        if (AudioItemController.access$300(this$0) != 3)
        {
          arrayOfString = AudioItemController.access$200(this$0).split("/");
          try
          {
            l = Long.parseLong(arrayOfString[(arrayOfString.length - 1)].split("\\.")[0]);
            if (System.currentTimeMillis() - l < 561600000L)
            {
              int i = MSimUtils.getSlotIdBySimInfoId(AudioItemController.access$400(this$0));
              Uri localUri = ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, AudioItemController.access$500(this$0));
              arrayOfString = paramVarArgs;
              if (i == -1) {
                break label322;
              }
              DownloadMxV2FileService.startDownloadAudio(MmsApp.getApp(), access$600this$0).shareId, i, localUri, AudioItemController.access$700(this$0), paramVarArgs.getName());
              return Integer.valueOf(3);
            }
          }
          catch (NumberFormatException paramVarArgs)
          {
            for (;;)
            {
              Log.e("AudioItemController.RICH", "MX2AudioPlay parse path to number error, mPath = " + AudioItemController.access$200(this$0));
              long l = System.currentTimeMillis();
              paramVarArgs = AudioHelper.getAudioDir(true);
              AudioItemController.access$202(this$0, paramVarArgs + "/" + l + ".amr");
              AudioItemController.access$102(AudioItemController.access$200(this$0));
              paramVarArgs = new File(AudioItemController.access$200(this$0));
            }
          }
          return Integer.valueOf(2);
        }
      }
      label322:
      if (arrayOfString.exists()) {
        return Integer.valueOf(0);
      }
      return Integer.valueOf(1);
    }
    Log.v("AudioItemController.RICH", "MX2AudioPlay play failed the audio path is " + AudioItemController.access$200(this$0) + ", and spath = " + AudioItemController.access$100());
    return Integer.valueOf(-1);
  }
  
  protected void onPostExecute(Integer paramInteger)
  {
    if ((AudioItemController.access$200(this$0) != null) && (AudioItemController.access$200(this$0).equals(AudioItemController.access$100()))) {
      switch (paramInteger.intValue())
      {
      default: 
        Toast.makeText(MmsApp.getApp(), 2131362357, 0).show();
      }
    }
    for (;;)
    {
      super.onPostExecute(paramInteger);
      return;
      AudioSensorManager localAudioSensorManager = new AudioSensorManager(AudioItemController.access$800(this$0));
      AudioHelper.playAudio(AudioItemController.access$500(this$0), AudioItemController.access$200(this$0), localAudioSensorManager, AudioItemController.access$900(this$0));
      continue;
      Toast.makeText(MmsApp.getApp(), 2131362365, 0).show();
      continue;
      Toast.makeText(MmsApp.getApp(), 2131362360, 0).show();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.audio.AudioItemController.PlayAudioAsyncTask
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */