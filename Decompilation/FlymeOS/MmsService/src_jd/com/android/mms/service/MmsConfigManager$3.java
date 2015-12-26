package com.android.mms.service;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;

class MmsConfigManager$3
  extends Thread
{
  MmsConfigManager$3(MmsConfigManager paramMmsConfigManager) {}
  
  public void run()
  {
    Configuration localConfiguration = MmsConfigManager.access$100(this$0).getResources().getConfiguration();
    Log.i("MmsService", "MmsConfigManager.loadInBackground(): mcc/mnc: " + mcc + "/" + mnc);
    MmsConfigManager.access$200(this$0, MmsConfigManager.access$100(this$0));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.service.MmsConfigManager.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */