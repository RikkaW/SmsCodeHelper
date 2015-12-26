package com.xiaomi.mipush.sdk;

import com.xiaomi.channel.commonutils.string.XMStringUtils;
import com.xiaomi.push.service.DeviceInfo;
import com.xiaomi.xmpush.thrift.ActionType;
import com.xiaomi.xmpush.thrift.XmPushActionNotification;
import java.util.HashMap;
import java.util.Map;

final class MiPushClient$2
  implements Runnable
{
  public void run()
  {
    if (DeviceInfo.blockingGetIMEI(MiPushClient.access$000()) != null)
    {
      XmPushActionNotification localXmPushActionNotification = new XmPushActionNotification();
      localXmPushActionNotification.setAppId(AppInfoHolder.getInstance(MiPushClient.access$000()).getAppID());
      localXmPushActionNotification.setType("client_info_update");
      localXmPushActionNotification.setId(MiPushClient.generatePacketID());
      localXmPushActionNotification.setExtra(new HashMap());
      localXmPushActionNotification.getExtra().put("imei_md5", XMStringUtils.getMd5Digest(DeviceInfo.blockingGetIMEI(MiPushClient.access$000())));
      PushServiceClient.getInstance(MiPushClient.access$000()).sendMessage(localXmPushActionNotification, ActionType.Notification, false, null);
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mipush.sdk.MiPushClient.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */