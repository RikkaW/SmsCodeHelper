package com.android.mms.ui;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import com.android.mms.LogTag;
import com.android.mms.MmsApp;
import com.android.mms.data.FestivalDatabase;

final class FestivalFragment$UpdateTask
  extends AsyncTask<Void, Void, Boolean>
{
  protected Boolean doInBackground(Void... paramVarArgs)
  {
    LogTag.verbose("Start updating messages", new Object[0]);
    return Boolean.valueOf(FestivalDatabase.getInstance().updateMessages());
  }
  
  protected void onPostExecute(Boolean paramBoolean)
  {
    LogTag.verbose("Done updating messages", new Object[0]);
    Application localApplication = MmsApp.getApp();
    if (paramBoolean.booleanValue())
    {
      LogTag.verbose("Succeeded updating messages", new Object[0]);
      localObject = PreferenceManager.getDefaultSharedPreferences(localApplication).edit();
      ((SharedPreferences.Editor)localObject).putLong("update_timestamp", System.currentTimeMillis());
      ((SharedPreferences.Editor)localObject).commit();
    }
    FestivalFragment.access$002(null);
    Object localObject = new Intent("com.android.mms.UPDATE_COMPLETE");
    ((Intent)localObject).putExtra("success", paramBoolean);
    localApplication.sendBroadcast((Intent)localObject);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.FestivalFragment.UpdateTask
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */