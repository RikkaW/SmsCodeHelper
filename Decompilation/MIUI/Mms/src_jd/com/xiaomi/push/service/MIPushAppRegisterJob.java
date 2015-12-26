package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.smack.XMPPException;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import org.json.JSONException;

public class MIPushAppRegisterJob
  extends XMPushService.Job
{
  private String appId;
  private String appToken;
  private String packageName;
  private byte[] payload;
  private XMPushService pushService;
  
  public MIPushAppRegisterJob(XMPushService paramXMPushService, String paramString1, String paramString2, String paramString3, byte[] paramArrayOfByte)
  {
    super(9);
    pushService = paramXMPushService;
    packageName = paramString1;
    payload = paramArrayOfByte;
    appId = paramString2;
    appToken = paramString3;
  }
  
  public String getDesc()
  {
    return "register app";
  }
  
  public void process()
  {
    Object localObject4 = MIPushAccountUtils.getMIPushAccount(pushService);
    Object localObject1 = localObject4;
    if (localObject4 == null) {}
    try
    {
      localObject1 = MIPushAccountUtils.register(pushService, packageName, appId, appToken);
      if (localObject1 == null)
      {
        MyLog.e("no account for mipush");
        MIPushClientManager.notifyRegisterError(pushService, 70000002, "no account.");
        return;
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        MyLog.e(localIOException);
        Object localObject2 = localObject4;
      }
    }
    catch (JSONException localJSONException)
    {
      PushClientsManager.ClientLoginInfo localClientLoginInfo;
      do
      {
        Object localObject3;
        for (;;)
        {
          MyLog.e(localJSONException);
          localObject3 = localObject4;
        }
        localObject4 = PushClientsManager.getInstance().getAllClientLoginInfoByChid("5");
        if (((Collection)localObject4).isEmpty())
        {
          localObject3 = ((MIPushAccount)localObject3).toClientLoginInfo(pushService);
          pushService.prepareMIPushClientLoginInfo((PushClientsManager.ClientLoginInfo)localObject3);
          PushClientsManager.getInstance().addActiveClient((PushClientsManager.ClientLoginInfo)localObject3);
        }
        for (;;)
        {
          if (!pushService.isConnected()) {
            break label226;
          }
          try
          {
            if (status != PushClientsManager.ClientStatus.binded) {
              break;
            }
            pushService.sendMIPushPacket(packageName, payload);
            return;
          }
          catch (XMPPException localXMPPException)
          {
            MyLog.e(localXMPPException);
            pushService.disconnect(10, localXMPPException);
            return;
          }
          localClientLoginInfo = (PushClientsManager.ClientLoginInfo)((Collection)localObject4).iterator().next();
        }
      } while (status != PushClientsManager.ClientStatus.unbind);
      localObject4 = pushService;
      XMPushService localXMPushService = pushService;
      localXMPushService.getClass();
      ((XMPushService)localObject4).executeJob(new XMPushService.BindJob(localXMPushService, localClientLoginInfo));
      return;
    }
    label226:
    pushService.scheduleConnect(true);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.MIPushAppRegisterJob
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */