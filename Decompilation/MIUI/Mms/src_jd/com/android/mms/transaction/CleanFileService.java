package com.android.mms.transaction;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony.Mms;
import com.android.mms.MmsApp;
import com.android.mms.audio.AudioHelper;
import com.xiaomi.mms.mx.audio.player.AudioTalkMediaPlayer;
import java.io.File;

public class CleanFileService
  extends IntentService
{
  public CleanFileService()
  {
    super("FileService");
  }
  
  private void cleanFile(String paramString)
  {
    String str = paramString.replace("/", "\\/");
    Object localObject1 = getBaseContext();
    Object localObject2 = getBaseContext().getContentResolver();
    Object localObject3 = Telephony.Mms.CONTENT_URI;
    str = "%" + str + "%";
    localObject1 = SqliteWrapper.query((Context)localObject1, (ContentResolver)localObject2, (Uri)localObject3, new String[] { "mx_extension" }, "mx_extension like ?", new String[] { str }, null);
    if (localObject1 != null) {}
    try
    {
      if (!((Cursor)localObject1).moveToFirst())
      {
        paramString = AudioHelper.getAudioDir() + "/" + paramString;
        localObject2 = new File(paramString);
        if (((File)localObject2).exists())
        {
          localObject3 = AudioTalkMediaPlayer.getInstance(MmsApp.getApp());
          if (((AudioTalkMediaPlayer)localObject3).isPlaying(paramString)) {
            ((AudioTalkMediaPlayer)localObject3).stop();
          }
          ((File)localObject2).delete();
        }
      }
      return;
    }
    finally
    {
      ((Cursor)localObject1).close();
    }
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    paramIntent = paramIntent.getExtras().getStringArray("paths");
    int j = paramIntent.length;
    int i = 0;
    while (i < j)
    {
      cleanFile(paramIntent[i]);
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.CleanFileService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */