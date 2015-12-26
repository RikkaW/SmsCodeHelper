package com.android.mms.transaction;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings.Global;
import android.provider.Settings.System;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import com.android.mms.LogTag;
import com.android.mms.util.MSimUtils;
import com.google.android.mms.util.PduCache;
import com.xiaomi.mms.transaction.MxActivateService;
import java.util.HashSet;
import java.util.Iterator;
import miui.telephony.TelephonyManager;

public class MmsSystemEventReceiver
  extends BroadcastReceiver
{
  private static MmsSystemEventReceiver sInstance;
  private Context mContext;
  private final Handler mHandler = new Handler();
  private volatile boolean mIsAvailable;
  private boolean[] mListening;
  private MSimPhoneStateListener mPhoneStateListener1;
  private MSimPhoneStateListener mPhoneStateListener2;
  private final HashSet<SimStateChangedListener> mSimStateChangedListeners = new HashSet();
  
  private MmsSystemEventReceiver(Context paramContext)
  {
    mContext = paramContext;
    int j = MSimUtils.getMultiSimCount();
    LogTag.verbose("MmsSystemEventReceiver count is " + j, new Object[0]);
    mListening = new boolean[j];
    int i = 0;
    while (i < j)
    {
      mListening[i] = false;
      i += 1;
    }
    Object localObject = new IntentFilter();
    ((IntentFilter)localObject).addAction("android.intent.action.CONTENT_CHANGED");
    ((IntentFilter)localObject).addAction("android.intent.action.BOOT_COMPLETED");
    ((IntentFilter)localObject).addAction("com.xiaomi.action.MICLOUD_SIM_STATE_CHANGED");
    ((IntentFilter)localObject).addAction("android.net.conn.CONNECTIVITY_CHANGE");
    ((IntentFilter)localObject).addAction("android.intent.action.SIM_STATE_CHANGED");
    paramContext.registerReceiver(this, (IntentFilter)localObject);
    localObject = new ContentObserver(mHandler)
    {
      public void onChange(boolean paramAnonymousBoolean)
      {
        LogTag.verbose("mobile data or enable mms is changed", new Object[0]);
        int i = 0;
        while (i < MSimUtils.getMultiSimCount())
        {
          MmsSystemEventReceiver.this.updateMmsAvailability(i);
          i += 1;
        }
      }
    };
    paramContext = paramContext.getContentResolver();
    if ((MSimUtils.isAndroid50()) && (!MSimUtils.isMtk()))
    {
      paramContext.registerContentObserver(Settings.Global.getUriFor("mobile_data0"), false, (ContentObserver)localObject);
      paramContext.registerContentObserver(Settings.Global.getUriFor("mobile_data1"), false, (ContentObserver)localObject);
    }
    for (;;)
    {
      paramContext.registerContentObserver(Settings.Global.getUriFor("always_enable_mms"), false, (ContentObserver)localObject);
      return;
      paramContext.registerContentObserver(Settings.Global.getUriFor("mobile_data"), false, (ContentObserver)localObject);
    }
  }
  
  public static MmsSystemEventReceiver getInstance()
  {
    return sInstance;
  }
  
  private void handleDataChanged(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    NetworkInfo localNetworkInfo = paramContext.getNetworkInfo(2);
    int j = MSimUtils.getMultiSimCount();
    int i;
    if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable()))
    {
      LogTag.verbose("mobile mms available", new Object[0]);
      setAvailable(true);
      i = 0;
    }
    while (i < j)
    {
      updateMmsAvailability(i);
      i += 1;
      continue;
      paramContext = paramContext.getNetworkInfo(0);
      if ((paramContext != null) && (paramContext.isAvailable()))
      {
        LogTag.verbose("mobile available", new Object[0]);
        setAvailable(true);
        i = 0;
      }
      while (i < j)
      {
        updateMmsAvailability(i);
        i += 1;
        continue;
        LogTag.verbose("mobile or mobile mms is not available", new Object[0]);
        setAvailable(false);
      }
    }
  }
  
  public static void init(Context paramContext)
  {
    if (sInstance != null) {
      return;
    }
    sInstance = new MmsSystemEventReceiver(paramContext.getApplicationContext());
  }
  
  private boolean isAlwaysEnableMmsAllowed()
  {
    if (Settings.System.getInt(mContext.getContentResolver(), "always_enable_mms", 1) == 1) {}
    for (boolean bool = true;; bool = false)
    {
      LogTag.verbose("isAlwaysEnableMmsAllowed allowed is " + bool, new Object[0]);
      return bool;
    }
  }
  
  private boolean isMobileDataAllowed()
  {
    if ((MSimUtils.isAndroid50()) && (!MSimUtils.isMtk()))
    {
      boolean bool2;
      if (Settings.Global.getInt(mContext.getContentResolver(), "mobile_data0", 1) == 1)
      {
        bool2 = true;
        bool1 = bool2;
        if (!bool2) {
          if (Settings.Global.getInt(mContext.getContentResolver(), "mobile_data1", 1) != 1) {
            break label89;
          }
        }
      }
      label89:
      for (bool1 = true;; bool1 = false)
      {
        LogTag.verbose("isMobileDataAllowed allowed is " + bool1, new Object[0]);
        return bool1;
        bool2 = false;
        break;
      }
    }
    if (Settings.Global.getInt(mContext.getContentResolver(), "mobile_data", 1) == 1) {}
    for (boolean bool1 = true;; bool1 = false) {
      break;
    }
  }
  
  private void setAvailable(boolean paramBoolean)
  {
    mIsAvailable = paramBoolean;
  }
  
  private void updateMmsAvailability(int paramInt)
  {
    if ((isListening(paramInt)) && (isMmsAllowed()) && (isAvailable()))
    {
      LogTag.verbose("updateMmsAvailability slotId " + paramInt, new Object[0]);
      wakeUpService(mContext, paramInt);
    }
  }
  
  private void wakeUpService(Context paramContext, int paramInt)
  {
    LogTag.verbose("wakeUpService: start transaction service slotId " + paramInt, new Object[0]);
    Intent localIntent = new Intent(mContext, TransactionService.class);
    localIntent.setAction("android.intent.action.ACTION_WAKEUP");
    localIntent.putExtra(MSimUtils.SLOT_ID, paramInt);
    paramContext.startService(localIntent);
  }
  
  public boolean isAvailable()
  {
    return mIsAvailable;
  }
  
  public boolean isListening()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (isListening(0))
    {
      bool1 = bool2;
      if (isListening(1)) {
        bool1 = true;
      }
    }
    LogTag.verbose("isListening allowed is " + bool1, new Object[0]);
    return bool1;
  }
  
  public boolean isListening(int paramInt)
  {
    int i = 0;
    if (MSimUtils.isMSimSlotIdValid(paramInt)) {
      i = mListening[paramInt];
    }
    LogTag.verbose("isListening slotId " + paramInt + " allowed is " + i, new Object[0]);
    return i;
  }
  
  public boolean isMmsAllowed()
  {
    return (isMobileDataAllowed()) || (isAlwaysEnableMmsAllowed());
  }
  
  public void listenForMmsAvailability(int paramInt)
  {
    LogTag.verbose("listenForMmsAvailability slotId " + paramInt, new Object[0]);
    if (MSimUtils.isMSimSlotIdValid(paramInt)) {
      mListening[paramInt] = true;
    }
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Object localObject = paramIntent.getAction();
    if ("android.intent.action.CONTENT_CHANGED".equals(localObject))
    {
      paramContext = (Uri)paramIntent.getParcelableExtra("deleted_contents");
      PduCache.getInstance().purge(paramContext);
    }
    do
    {
      for (;;)
      {
        return;
        if ("android.intent.action.BOOT_COMPLETED".equals(localObject))
        {
          MessagingNotification.nonBlockingUpdateNewMessageIndicator(paramContext, false, false);
          paramContext.startService(new Intent(paramContext, TransactionService.class));
          return;
        }
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(localObject))
        {
          handleDataChanged(paramContext);
          return;
        }
        if (!"android.intent.action.SIM_STATE_CHANGED".equals(localObject)) {
          break;
        }
        paramContext = paramIntent.getStringExtra("ss");
        paramIntent = mSimStateChangedListeners.iterator();
        while (paramIntent.hasNext())
        {
          localObject = (SimStateChangedListener)paramIntent.next();
          if (localObject != null) {
            ((SimStateChangedListener)localObject).onSimStateChanged(paramContext);
          }
        }
      }
    } while (!"com.xiaomi.action.MICLOUD_SIM_STATE_CHANGED".equals(localObject));
    boolean bool = paramIntent.getBooleanExtra("extra_sim_inserted", false);
    int i = paramIntent.getIntExtra("extra_sim_index", -1);
    if (!bool)
    {
      MxActivateService.closeChannel(mContext, i);
      return;
    }
    MxActivateService.enableMx(mContext, i, true, false);
  }
  
  public void registerForServiceStateChanged(int paramInt)
  {
    if (paramInt == 0)
    {
      if (mPhoneStateListener1 == null) {
        mPhoneStateListener1 = new MSimPhoneStateListener(mContext, paramInt, mHandler);
      }
      mPhoneStateListener1.register();
    }
    while (1 != paramInt) {
      return;
    }
    if (mPhoneStateListener2 == null) {
      mPhoneStateListener2 = new MSimPhoneStateListener(mContext, paramInt, mHandler);
    }
    mPhoneStateListener2.register();
  }
  
  public void registerSimStateChangedListener(SimStateChangedListener paramSimStateChangedListener)
  {
    if (!mSimStateChangedListeners.contains(paramSimStateChangedListener)) {
      mSimStateChangedListeners.add(paramSimStateChangedListener);
    }
  }
  
  public void unRegisterSimStateChangedListener(SimStateChangedListener paramSimStateChangedListener)
  {
    if (mSimStateChangedListeners.contains(paramSimStateChangedListener)) {
      mSimStateChangedListeners.remove(paramSimStateChangedListener);
    }
  }
  
  public void unlistenForMmsAvailability(int paramInt)
  {
    if ((MSimUtils.isMSimSlotIdValid(paramInt)) && (mListening[paramInt] != 0))
    {
      LogTag.verbose("unlistenForMmsAvailability slotId " + paramInt, new Object[0]);
      mListening[paramInt] = false;
    }
  }
  
  private class MSimPhoneStateListener
    extends PhoneStateListener
  {
    private int DELAY_TIME = 10000;
    private Context mContext;
    private Runnable mDelaySend = new Runnable()
    {
      public void run()
      {
        LogTag.verbose("send queued message without toast", new Object[0]);
        MSimUtils.sendQueuedMessageNoToast(mContext, mSlotId);
      }
    };
    private Handler mHandler;
    private boolean mRegisterd;
    private int mSlotId;
    
    public MSimPhoneStateListener(Context paramContext, int paramInt, Handler paramHandler)
    {
      mContext = paramContext;
      mSlotId = paramInt;
      mHandler = paramHandler;
      mRegisterd = false;
    }
    
    private void unRegister()
    {
      if (MSimUtils.isMSimSlotIdValid(mSlotId))
      {
        TelephonyManager.getDefault().listenForSlot(mSlotId, this, 0);
        mHandler.removeCallbacks(mDelaySend);
      }
    }
    
    public void onServiceStateChanged(ServiceState paramServiceState)
    {
      if (paramServiceState.getState() == 0)
      {
        LogTag.verbose("on service state is in service", new Object[0]);
        if (mRegisterd)
        {
          unRegister();
          mHandler.postDelayed(mDelaySend, DELAY_TIME);
        }
        mRegisterd = false;
      }
    }
    
    public void register()
    {
      if ((MSimUtils.isMSimSlotIdValid(mSlotId)) && (!mRegisterd))
      {
        TelephonyManager.getDefault().listenForSlot(mSlotId, this, 1);
        mRegisterd = true;
      }
    }
  }
  
  public static abstract interface SimStateChangedListener
  {
    public abstract void onSimStateChanged(String paramString);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MmsSystemEventReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */