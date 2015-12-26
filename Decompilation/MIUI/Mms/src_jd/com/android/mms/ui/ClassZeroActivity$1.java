package com.android.mms.ui;

import android.os.Handler;
import android.os.Message;
import miui.app.AlertDialog;

class ClassZeroActivity$1
  extends Handler
{
  ClassZeroActivity$1(ClassZeroActivity paramClassZeroActivity) {}
  
  public void handleMessage(Message paramMessage)
  {
    if (what == 1)
    {
      ClassZeroActivity.access$002(this$0, false);
      ClassZeroActivity.access$100(this$0).dismiss();
      ClassZeroActivity.access$200(this$0);
      ClassZeroActivity.access$300(this$0);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ClassZeroActivity.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */