package com.android.mms.understand;

import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.text.TextUtils;
import android.widget.Toast;
import com.android.mms.transaction.MessagingNotification;

public class UnderstandService
  extends Service
{
  private void markMessageAsReadSync(Uri paramUri)
  {
    ContentValues localContentValues = new ContentValues(2);
    localContentValues.put("read", Integer.valueOf(1));
    localContentValues.put("seen", Integer.valueOf(1));
    getContentResolver().update(paramUri, localContentValues, null, null);
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    super.onCreate();
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    if (paramIntent != null)
    {
      String str = paramIntent.getStringExtra("extra_text");
      final Uri localUri = paramIntent.getData();
      if (!TextUtils.isEmpty(str))
      {
        ((ClipboardManager)getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(null, str));
        Toast.makeText(this, getString(2131361821), 0).show();
        new Thread(new Runnable()
        {
          public void run()
          {
            UnderstandService.this.markMessageAsReadSync(localUri);
          }
        }).start();
        MessagingNotification.cancelNotification(this, 123);
        MessagingNotification.cancelFloatNotification(this);
      }
    }
    return super.onStartCommand(paramIntent, paramInt1, paramInt2);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.understand.UnderstandService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */