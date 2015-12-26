package com.android.mms.ui;

import android.app.Application;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import com.android.mms.MmsApp;
import miui.provider.ExtraTelephony.SimCards;

class WildMsgDialog$2$1
  implements Runnable
{
  WildMsgDialog$2$1(WildMsgDialog.2 param2, String paramString) {}
  
  public void run()
  {
    Application localApplication = MmsApp.getApp();
    ContentValues localContentValues = new ContentValues(1);
    localContentValues.put("download_status", Integer.valueOf(1));
    int i = localApplication.getContentResolver().update(ExtraTelephony.SimCards.CONTENT_URI, localContentValues, val$selection, null);
    Log.v("WildMsgDialog", "update number to sim cards is " + i);
    if (i > 0)
    {
      Log.v("WildMsgDialog", "force sync");
      MessageUtils.forceSync(localApplication);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.WildMsgDialog.2.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */