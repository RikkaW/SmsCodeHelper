package com.android.mms.ui;

import android.app.Application;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.MmsApp;
import java.util.ArrayList;
import miui.provider.ExtraTelephony.SimCards;

class WildMsgDialog$2
  implements DialogInterface.OnClickListener
{
  WildMsgDialog$2(WildMsgDialog paramWildMsgDialog) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramInt = Settings.System.getInt(WildMsgDialog.access$100(this$0).getContentResolver(), "mms_sync_wild_msg_state", 0);
    if ((paramInt != 4) && (paramInt != 5)) {
      Settings.System.putInt(WildMsgDialog.access$100(this$0).getContentResolver(), "mms_sync_wild_msg_state", 2);
    }
    int i = 0;
    paramInt = 0;
    paramDialogInterface = new ArrayList();
    int j = i;
    if (WildMsgDialog.access$200(this$0) != null)
    {
      j = i;
      if (WildMsgDialog.access$000(this$0) != null)
      {
        i = 0;
        j = paramInt;
        if (i < WildMsgDialog.access$000(this$0).length)
        {
          if (WildMsgDialog.access$000(this$0)[i] != 0) {
            paramDialogInterface.add(WildMsgDialog.access$200(this$0)[i]);
          }
          for (;;)
          {
            i += 1;
            break;
            paramInt = 1;
          }
        }
      }
    }
    Log.v("WildMsgDialog", "update list.size is " + paramDialogInterface.size());
    if (paramDialogInterface.size() > 0) {
      new Thread(new Runnable()
      {
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
      }, "update_sim_cards_thread").start();
    }
    if ((j != 0) && (!WildMsgDialog.access$300(this$0))) {
      WildMsgDialog.access$400(this$0, WildMsgDialog.access$100(this$0));
    }
    WildMsgDialog.access$500(this$0);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.WildMsgDialog.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */