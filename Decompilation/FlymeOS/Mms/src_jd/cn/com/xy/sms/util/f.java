package cn.com.xy.sms.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import cn.com.xy.sms.sdk.db.entity.IccidInfo;
import cn.com.xy.sms.sdk.db.entity.IccidInfoManager;
import cn.com.xy.sms.sdk.iccid.IccidLocationUtil;
import cn.com.xy.sms.sdk.util.StringUtils;

final class f
  extends BroadcastReceiver
{
  public final void onReceive(Context paramContext, Intent paramIntent)
  {
    if ("cn.com.xy.sms.iccidinfo.action".equals(paramIntent.getAction()))
    {
      paramContext = paramIntent.getStringExtra("iccid");
      if (!StringUtils.isNull(paramContext)) {
        break label27;
      }
    }
    label27:
    do
    {
      return;
      paramIntent = IccidInfoManager.queryIccidInfo(paramContext, null);
    } while (paramIntent == null);
    IccidLocationUtil.putIccidAreaCodeToCache(paramContext, areaCode, operator, userAreacode, userOperator);
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.f
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */