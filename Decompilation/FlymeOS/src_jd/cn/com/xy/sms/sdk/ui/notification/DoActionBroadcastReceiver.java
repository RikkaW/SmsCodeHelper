package cn.com.xy.sms.sdk.ui.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DoActionBroadcastReceiver
  extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getAction();
    paramIntent = (MessageItem)paramIntent.getParcelableExtra("message");
    Intent localIntent = new Intent(paramContext, DoActionActivity.class);
    localIntent.addFlags(268468224);
    localIntent.putExtra("message", paramIntent);
    localIntent.putExtra("action", str);
    paramContext.startActivity(localIntent);
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.ui.notification.DoActionBroadcastReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */