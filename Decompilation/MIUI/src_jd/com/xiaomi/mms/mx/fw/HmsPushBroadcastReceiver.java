package com.xiaomi.mms.mx.fw;

import android.accounts.AccountManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.mms.mx.fw.faccount.XiaoMiJIDUtils;
import com.xiaomi.mms.mx.utils.Log;
import com.xiaomi.smack.packet.IQ;
import com.xiaomi.smack.packet.Message;
import com.xiaomi.smack.packet.Presence;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HmsPushBroadcastReceiver
  extends BroadcastReceiver
{
  private static boolean mChannelConnected = false;
  private static XmppIQHandler mIQHandler;
  private static volatile boolean mIsRefreshingTokens = false;
  private static Set<ChannelConnListener> mListeners = Collections.synchronizedSet(new HashSet());
  private static XmppMessageHandler mMsgHandler;
  private static int mTokenInvalidCount = 0;
  
  public static boolean isChannelConnected()
  {
    return mChannelConnected;
  }
  
  private ExtendedAuthToken loadTokens(Context paramContext)
  {
    try
    {
      Log.d("HmsPushBroadcastReceiver", "start invalidating auth token .....");
      AccountManager.get(paramContext).invalidateAuthToken("com.xiaomi", XiaoMiJIDUtils.getAuthtokenString(paramContext, "mipub"));
      paramContext = XiaoMiJIDUtils.getAuthtoken(paramContext, "mipub");
      return paramContext;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  private void notifyConnectionEvent(boolean paramBoolean, int paramInt)
  {
    synchronized (mListeners)
    {
      Iterator localIterator = mListeners.iterator();
      if (localIterator.hasNext()) {
        ((ChannelConnListener)localIterator.next()).onChannelConnChanged(paramBoolean, paramInt);
      }
    }
  }
  
  private void refreshTokens(final Context paramContext)
  {
    try
    {
      if (!mIsRefreshingTokens)
      {
        mIsRefreshingTokens = true;
        new AsyncTask()
        {
          protected ExtendedAuthToken doInBackground(Void... paramAnonymousVarArgs)
          {
            try
            {
              paramAnonymousVarArgs = HmsPushBroadcastReceiver.this.loadTokens(paramContext);
              return paramAnonymousVarArgs;
            }
            catch (Exception paramAnonymousVarArgs)
            {
              Log.d("HmsPushBroadcastReceiver", paramAnonymousVarArgs.toString());
            }
            return null;
          }
          
          protected void onPostExecute(ExtendedAuthToken paramAnonymousExtendedAuthToken)
          {
            super.onPostExecute(paramAnonymousExtendedAuthToken);
            if (paramAnonymousExtendedAuthToken != null)
            {
              Log.d("HmsPushBroadcastReceiver", "the new auth token ..... token : " + authToken + "  security :" + security);
              Intent localIntent = new Intent("com.xiaomi.mms.action_open_channel");
              localIntent.putExtra("auth_token", authToken);
              localIntent.putExtra("auth_security", security);
              localIntent.setPackage(paramContext.getPackageName());
              paramContext.startService(localIntent);
            }
            HmsPushBroadcastReceiver.access$102(false);
          }
        }.execute(new Void[0]);
      }
      return;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  private void reopenChannel(Context paramContext)
  {
    Log.d("HmsPushBroadcastReceiver", "HmsPushBroadcastReceiver , close channel and open the channel, because receive 'wait' or 'not-bind' message");
    HmsChannelService.tryCloseChannel(paramContext);
    HmsChannelService.tryOpenChannel(paramContext);
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getAction();
    if ("com.xiaomi.push.service_started".equalsIgnoreCase(str)) {
      HmsChannelService.tryOpenChannel(paramContext);
    }
    do
    {
      do
      {
        for (;;)
        {
          return;
          if (TextUtils.equals(paramIntent.getStringExtra("ext_chid"), "8"))
          {
            if (mMsgHandler == null) {
              mMsgHandler = XmppMessageHandler.getInstance();
            }
            if (mIQHandler == null) {
              mIQHandler = XmppIQHandler.getInstance(paramContext.getApplicationContext());
            }
            if ("com.xiaomi.push.channel_closed".equals(str))
            {
              mChannelConnected = false;
              i = paramIntent.getIntExtra("ext_reason", 0);
              Log.d("HmsPushBroadcastReceiver", "HmsPushBroadcastReceiver channel disconnected! " + i);
              notifyConnectionEvent(false, i);
              return;
            }
            if ("com.xiaomi.push.new_iq".equals(str))
            {
              paramContext = new IQ(paramIntent.getBundleExtra("ext_packet"));
              Log.v("HmsPushBroadcastReceiver", "RECV:" + paramContext.toXML());
              mIQHandler.handle(paramContext);
              return;
            }
            if ("com.xiaomi.push.new_msg".equals(str))
            {
              paramContext = new Message(paramIntent.getBundleExtra("ext_packet"));
              Log.v("HmsPushBroadcastReceiver", "RECV:" + paramContext.toXML());
              mMsgHandler.handle(paramContext);
              return;
            }
            if ("com.xiaomi.push.new_pres".equals(str))
            {
              paramContext = new Presence(paramIntent.getBundleExtra("ext_packet"));
              Log.v("HmsPushBroadcastReceiver", "RECV:" + paramContext.toXML());
              mMsgHandler.handle(paramContext);
              return;
            }
            if (!"com.xiaomi.push.kicked".equals(str)) {
              break;
            }
            str = paramIntent.getStringExtra("ext_kick_type");
            paramIntent = paramIntent.getStringExtra("ext_kick_reason");
            Log.d("HmsPushBroadcastReceiver", "HmsPushBroadcastReceiver kick by server, type: " + str + ", reason: " + paramIntent);
            int i = 1;
            if ("cancel".equals(str)) {
              if ("multi-login".equals(paramIntent))
              {
                paramIntent = new Intent("com.xiaomi.mms.action_close_channel");
                paramIntent.setPackage(paramContext.getPackageName());
                paramContext.startService(paramIntent);
                notifyConnectionEvent(false, 6);
              }
            }
            while (i == 0)
            {
              Log.d("HmsPushBroadcastReceiver", "HmsPushBroadcastReceiver kick by server, but not be disposed........");
              return;
              i = 0;
              continue;
              if ("auth".equals(str))
              {
                if (("token-expired".equals(paramIntent)) || ("invalid-token".equals(paramIntent))) {
                  refreshTokens(paramContext);
                } else if ("not-bind".equals(paramIntent)) {
                  reopenChannel(paramContext);
                } else {
                  i = 0;
                }
              }
              else if ("modify".equals(str))
              {
                if ("invalid-pid".equals(paramIntent)) {
                  refreshTokens(paramContext);
                } else {
                  i = 0;
                }
              }
              else if (!"wait".equals(str)) {
                i = 0;
              }
            }
          }
        }
      } while (!"com.xiaomi.push.channel_opened".equals(str));
      mChannelConnected = paramIntent.getBooleanExtra("ext_succeeded", false);
      if (mChannelConnected)
      {
        Log.d("HmsPushBroadcastReceiver", "HmsPushBroadcastReceiver channel connected!");
        mTokenInvalidCount = 0;
        notifyConnectionEvent(true, 0);
        return;
      }
      str = paramIntent.getStringExtra("ext_reason_msg");
      Log.d("HmsPushBroadcastReceiver", "channel connect failed, reason=" + str);
      notifyConnectionEvent(false, paramIntent.getIntExtra("ext_reason", 0));
    } while ((!"invalid-sig".equals(str)) && (!"invalid-token".equals(str)) && (!"token-expired".equals(str)));
    mTokenInvalidCount += 1;
    if (mTokenInvalidCount < 3)
    {
      refreshTokens(paramContext);
      return;
    }
    mTokenInvalidCount = 0;
    Log.w("HmsPushBroadcastReceiver", "max token refresh time reaches.");
  }
  
  public static abstract interface ChannelConnListener
  {
    public abstract void onChannelConnChanged(boolean paramBoolean, int paramInt);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.fw.HmsPushBroadcastReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */