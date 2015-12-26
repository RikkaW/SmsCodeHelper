package cn.com.xy.sms.sdk.ui.notification;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import br.e;
import cj;
import cn.com.xy.sms.sdk.action.AbsSdkDoAction;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.util.DuoquUtils;

public class DoActionActivity
  extends Activity
{
  private void a(Context paramContext)
  {
    Object localObject = getIntent();
    MessageItem localMessageItem = (MessageItem)((Intent)localObject).getParcelableExtra("message");
    localObject = ((Intent)localObject).getStringExtra("action");
    if ("com.xy.sms.ui.notification.fristServiceButtonClickAction".equals(localObject)) {
      cj.a(paramContext, localMessageItem, 0);
    }
    for (;;)
    {
      if ((a != 0) && (!"com.xy.sms.ui.notification.deleteButtonClickAction".equals(localObject)))
      {
        cj.a(paramContext, 123);
        finish();
      }
      return;
      if ("com.xy.sms.ui.notification.secondServiceButtonClickAction".equals(localObject)) {
        cj.a(paramContext, localMessageItem, 1);
      } else if ("com.xy.sms.ui.notification.layoutClickAction".equals(localObject)) {
        cj.a(paramContext, localMessageItem);
      } else if ("com.xy.sms.ui.notification.hasReadButtonClickAction".equals(localObject)) {
        DuoquUtils.getSdkDoAction().markAsReadForDatabase(paramContext, String.valueOf(a));
      } else if ("com.xy.sms.ui.notification.deleteButtonClickAction".equals(localObject)) {
        DuoquUtils.getSdkDoAction().deleteMsgForDatabase(paramContext, String.valueOf(a));
      } else if ("com.xy.sms.ui.notification.replyButtonClickAction".equals(localObject)) {
        DuoquUtils.getSdkDoAction().replySms(paramContext, String.valueOf(a), b, c, d);
      }
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(br.e.duoqu_activity_do_action);
    LogManager.i("DoActionActivity", "DoActionActivity is onCreated...");
    a(this);
  }
  
  protected void onDestroy()
  {
    cj.a();
    super.onDestroy();
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.ui.notification.DoActionActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */