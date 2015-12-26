package com.android.mms.transaction;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.mms.MmsException;
import ny;
import ot;

public class BackgroundSmsMmsSenderService
  extends Service
{
  private void a(Intent paramIntent)
  {
    Log.d("BackgroundSmsMmsSendService", "START sendSmsMmsWithoutAuthority");
    String str = paramIntent.getStringExtra("address");
    int i = paramIntent.getIntExtra("slot_id", -1);
    if (i < 0) {
      Log.e("BackgroundSmsMmsSendService", "slot < 0 !");
    }
    if (TextUtils.isEmpty(str))
    {
      Log.e("BackgroundSmsMmsSendService", "address is null!");
      return;
    }
    paramIntent = paramIntent.getStringExtra("body");
    if (TextUtils.isEmpty(paramIntent))
    {
      Log.e("BackgroundSmsMmsSendService", "body is null!");
      return;
    }
    paramIntent = new ot(this, TextUtils.split(str, ";"), paramIntent, 0L);
    paramIntent.a(i);
    try
    {
      paramIntent.a(0L);
      Log.d("BackgroundSmsMmsSendService", "END sendSmsMmsWithoutAuthority");
      return;
    }
    catch (MmsException paramIntent)
    {
      for (;;)
      {
        Log.e("BackgroundSmsMmsSendService", "throw : " + paramIntent);
      }
    }
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    new a(null).execute(new Intent[] { paramIntent });
    return 1;
  }
  
  class a
    extends AsyncTask<Intent, Void, Void>
  {
    private a() {}
    
    protected Void a(Intent... paramVarArgs)
    {
      BackgroundSmsMmsSenderService.a(BackgroundSmsMmsSenderService.this, paramVarArgs[0]);
      return null;
    }
    
    protected void a(Void paramVoid)
    {
      stopSelf();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.BackgroundSmsMmsSenderService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */