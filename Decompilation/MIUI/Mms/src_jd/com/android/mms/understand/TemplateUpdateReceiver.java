package com.android.mms.understand;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.android.mms.util.MiStatSdkHelper;

public class TemplateUpdateReceiver
  extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Log.w("TemplateUpdateReceiver", " on receive ");
    paramContext = new TemplateUpdate.UpdatePatch();
    mIsIncremental = paramIntent.getBooleanExtra("increment", false);
    mVersion = paramIntent.getLongExtra("version", 0L);
    mMd5 = paramIntent.getStringExtra("md5");
    mOldMd5 = paramIntent.getStringExtra("old_md5");
    MiStatSdkHelper.recourdUpdateEvent("yellowpage");
    TemplateUpdate.onTemplateDownloaded(paramContext);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.understand.TemplateUpdateReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */