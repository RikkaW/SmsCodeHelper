package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.util.HashSet;

class SimMessagesFragment$ModeCallback$1
  implements DialogInterface.OnClickListener
{
  SimMessagesFragment$ModeCallback$1(SimMessagesFragment.ModeCallback paramModeCallback, HashSet paramHashSet) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    SimMessagesFragment.access$200(this$1.this$0, 2);
    SimMessagesFragment.access$900(this$1.this$0, val$positions);
    paramDialogInterface.dismiss();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SimMessagesFragment.ModeCallback.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */