package com.xiaomi.mms.transaction;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.android.mms.data.Contact;
import com.xiaomi.mms.data.MidPhoneMap;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.data.MxIdCache.MxIdCacheItem;
import com.xiaomi.mms.net.CloudRequestExecutor;
import com.xiaomi.mms.net.CloudRequestFactory;
import com.xiaomi.mms.net.SimpleRequest;
import com.xiaomi.mms.net.exception.InvalidResponseException;
import com.xiaomi.mms.utils.MxMessageLogicHelper;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import miui.push.Presence;
import miui.push.Presence.Type;
import miui.push.ServiceClient;

public class MxTaskService
  extends IntentService
{
  private static final Set<String> sPendingAddress = new HashSet();
  private final Object REQ_LOCK = new Object();
  private SimpleRequest mRequest;
  
  public MxTaskService()
  {
    super("MxTaskService");
  }
  
  private void queryPendingAddresses()
  {
    Iterator localIterator = sPendingAddress.iterator();
    while (localIterator.hasNext()) {
      if (queryPresence((String)localIterator.next())) {
        localIterator.remove();
      }
    }
  }
  
  public static void queryPendingAddresses(Context paramContext)
  {
    Intent localIntent = new Intent(paramContext, MxTaskService.class);
    localIntent.setAction("com.xiaomi.mms.mx.ACTION_QUERY_PENDING_PRESENCE");
    paramContext.startService(localIntent);
  }
  
  private boolean queryPresence(String paramString)
  {
    Object localObject = PushSession.getInstance(this);
    int i = ((PushSession)localObject).getConnectedSimIndex();
    if (i < 0)
    {
      MyLog.d("MxTaskService", "Push is not connected, bail");
      return false;
    }
    localObject = ((PushSession)localObject).getMyFullMid(i);
    if (TextUtils.isEmpty((CharSequence)localObject))
    {
      MyLog.w("MxTaskService", "push channel not ready, skip query presence");
      return false;
    }
    Presence localPresence = new Presence(Presence.Type.probe);
    localPresence.setChannelId("3");
    localPresence.setFrom((String)localObject);
    localPresence.setTo(MxMessageLogicHelper.constructFullRecipientId(paramString));
    return ServiceClient.getInstance(this).sendPresence(localPresence);
  }
  
  public static void queryStatus(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent(paramContext, MxTaskService.class);
    localIntent.setAction("com.xiaomi.mms.mx.ACTION_QUERY_PRESENCE");
    localIntent.putExtra("extra_address", paramString);
    paramContext.startService(localIntent);
  }
  
  public void onDestroy()
  {
    synchronized (REQ_LOCK)
    {
      if (mRequest != null) {
        mRequest.close();
      }
      super.onDestroy();
      return;
    }
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    if (paramIntent == null) {}
    do
    {
      for (;;)
      {
        return;
        ??? = paramIntent.getAction();
        if ("com.xiaomi.mms.mx.ACTION_QUERY_PRESENCE".equals(???))
        {
          if (!MxActivateService.isMxEnabled(this)) {
            continue;
          }
          String str = Contact.get(paramIntent.getStringExtra("extra_address")).getMxPhoneNumber();
          if (TextUtils.isEmpty(str)) {
            continue;
          }
          ??? = MxIdCache.get(str, true);
          paramIntent = null;
          if (??? != null) {
            paramIntent = ((MxIdCache.MxIdCacheItem)???).getMId();
          }
          ??? = paramIntent;
          if (paramIntent == null) {}
          synchronized (REQ_LOCK)
          {
            mRequest = CloudRequestFactory.newGetUserIdRequest(str);
            try
            {
              ??? = CloudRequestExecutor.getUid(mRequest);
              paramIntent = (Intent)???;
            }
            catch (IOException localIOException)
            {
              for (;;)
              {
                localIOException.printStackTrace();
              }
            }
            catch (InvalidResponseException localInvalidResponseException)
            {
              for (;;)
              {
                localInvalidResponseException.printStackTrace();
              }
              MyLog.w("MxTaskService", "error when get mid");
              return;
            }
            ??? = paramIntent;
            if (paramIntent != null)
            {
              MxIdCache.put(str, paramIntent);
              MidPhoneMap.put(paramIntent, str);
              ??? = paramIntent;
            }
            if (??? != null)
            {
              if (!MxActivateService.isMxEnabled(this)) {
                continue;
              }
              sPendingAddress.add(???);
              queryPendingAddresses();
            }
          }
        }
      }
    } while (!"com.xiaomi.mms.mx.ACTION_QUERY_PENDING_PRESENCE".equals(localInvalidResponseException));
    queryPendingAddresses();
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.transaction.MxTaskService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */