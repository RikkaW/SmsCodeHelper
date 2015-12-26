package com.android.mms.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.SubscriptionManager.OnSubscriptionsChangedListener;
import android.util.ArrayMap;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MmsConfigManager
{
  private static volatile MmsConfigManager sInstance = new MmsConfigManager();
  private Context mContext;
  private final SubscriptionManager.OnSubscriptionsChangedListener mOnSubscriptionsChangedListener = new SubscriptionManager.OnSubscriptionsChangedListener()
  {
    public void onSubscriptionsChanged()
    {
      MmsConfigManager.this.loadInBackground();
    }
  };
  private final BroadcastReceiver mReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      paramAnonymousContext = paramAnonymousIntent.getAction();
      Log.i("MmsService", "mReceiver action: " + paramAnonymousContext);
      if (paramAnonymousContext.equals("LOADED")) {
        MmsConfigManager.this.loadInBackground();
      }
    }
  };
  private final Map<Integer, MmsConfig> mSubIdConfigMap = new ArrayMap();
  private SubscriptionManager mSubscriptionManager;
  
  public static MmsConfigManager getInstance()
  {
    return sInstance;
  }
  
  private void load(Context arg1)
  {
    Object localObject2 = mSubscriptionManager.getActiveSubscriptionInfoList();
    if ((localObject2 == null) || (((List)localObject2).size() < 1))
    {
      Log.e("MmsService", "MmsConfigManager.load -- empty getActiveSubInfoList");
      return;
    }
    Log.d("MmsService", "MmsConfigManager.load -- subs.size()" + ((List)localObject2).size());
    ArrayMap localArrayMap = new ArrayMap();
    Object localObject3 = new ArrayList();
    for (;;)
    {
      Object localObject4;
      try
      {
        ((List)localObject3).addAll((Collection)localObject2);
        localObject2 = ((List)localObject3).iterator();
        if (!((Iterator)localObject2).hasNext()) {
          break;
        }
        localObject3 = (SubscriptionInfo)((Iterator)localObject2).next();
        if (localObject3 == null) {
          continue;
        }
        localObject4 = new Configuration();
        if ((((SubscriptionInfo)localObject3).getMcc() == 0) && (((SubscriptionInfo)localObject3).getMnc() == 0))
        {
          Configuration localConfiguration = mContext.getResources().getConfiguration();
          mcc = mcc;
          mnc = mnc;
          Log.i("MmsService", "MmsConfigManager.load -- no mcc/mnc for sub: " + localObject3 + " using mcc/mnc from main context: " + mcc + "/" + mnc);
          localObject4 = ???.createConfigurationContext((Configuration)localObject4);
          int i = ((SubscriptionInfo)localObject3).getSubscriptionId();
          localArrayMap.put(Integer.valueOf(i), new MmsConfig((Context)localObject4, i));
          continue;
        }
        Log.i("MmsService", "MmsConfigManager.load -- mcc/mnc for sub: " + localObject3);
      }
      finally {}
      mcc = ((SubscriptionInfo)localObject3).getMcc();
      mnc = ((SubscriptionInfo)localObject3).getMnc();
    }
    synchronized (mSubIdConfigMap)
    {
      mSubIdConfigMap.clear();
      mSubIdConfigMap.putAll(localArrayMap);
      return;
    }
  }
  
  private void loadInBackground()
  {
    new Thread()
    {
      public void run()
      {
        Configuration localConfiguration = mContext.getResources().getConfiguration();
        Log.i("MmsService", "MmsConfigManager.loadInBackground(): mcc/mnc: " + mcc + "/" + mnc);
        MmsConfigManager.this.load(mContext);
      }
    }.start();
  }
  
  public MmsConfig getMmsConfigBySubId(int paramInt)
  {
    synchronized (mSubIdConfigMap)
    {
      MmsConfig localMmsConfig = (MmsConfig)mSubIdConfigMap.get(Integer.valueOf(paramInt));
      Log.i("MmsService", "getMmsConfigBySubId -- for sub: " + paramInt + " mmsConfig: " + localMmsConfig);
      return localMmsConfig;
    }
  }
  
  public void init(Context paramContext)
  {
    mContext = paramContext;
    mSubscriptionManager = SubscriptionManager.from(paramContext);
    IntentFilter localIntentFilter = new IntentFilter("LOADED");
    paramContext.registerReceiver(mReceiver, localIntentFilter);
    SubscriptionManager.from(mContext).addOnSubscriptionsChangedListener(mOnSubscriptionsChangedListener);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.service.MmsConfigManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */