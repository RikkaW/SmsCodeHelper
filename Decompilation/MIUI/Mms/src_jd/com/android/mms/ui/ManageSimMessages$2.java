package com.android.mms.ui;

import android.util.Log;
import com.android.mms.transaction.MmsSystemEventReceiver.SimStateChangedListener;
import com.android.mms.util.MSimUtils;

class ManageSimMessages$2
  implements MmsSystemEventReceiver.SimStateChangedListener
{
  ManageSimMessages$2(ManageSimMessages paramManageSimMessages) {}
  
  public void onSimStateChanged(String paramString)
  {
    Log.d("ManageSimMessages", "update sim info change");
    if (!MSimUtils.isSimInserted()) {
      this$0.finish();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ManageSimMessages.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */