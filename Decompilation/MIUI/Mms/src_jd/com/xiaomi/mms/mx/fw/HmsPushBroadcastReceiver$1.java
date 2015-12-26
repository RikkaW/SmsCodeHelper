package com.xiaomi.mms.mx.fw;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.mms.mx.utils.Log;

class HmsPushBroadcastReceiver$1
  extends AsyncTask<Void, Void, ExtendedAuthToken>
{
  HmsPushBroadcastReceiver$1(HmsPushBroadcastReceiver paramHmsPushBroadcastReceiver, Context paramContext) {}
  
  protected ExtendedAuthToken doInBackground(Void... paramVarArgs)
  {
    try
    {
      paramVarArgs = HmsPushBroadcastReceiver.access$000(this$0, val$context);
      return paramVarArgs;
    }
    catch (Exception paramVarArgs)
    {
      Log.d("HmsPushBroadcastReceiver", paramVarArgs.toString());
    }
    return null;
  }
  
  protected void onPostExecute(ExtendedAuthToken paramExtendedAuthToken)
  {
    super.onPostExecute(paramExtendedAuthToken);
    if (paramExtendedAuthToken != null)
    {
      Log.d("HmsPushBroadcastReceiver", "the new auth token ..... token : " + authToken + "  security :" + security);
      Intent localIntent = new Intent("com.xiaomi.mms.action_open_channel");
      localIntent.putExtra("auth_token", authToken);
      localIntent.putExtra("auth_security", security);
      localIntent.setPackage(val$context.getPackageName());
      val$context.startService(localIntent);
    }
    HmsPushBroadcastReceiver.access$102(false);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.fw.HmsPushBroadcastReceiver.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */