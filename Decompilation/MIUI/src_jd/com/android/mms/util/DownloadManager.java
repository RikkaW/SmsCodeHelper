package com.android.mms.util;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemProperties;
import android.preference.PreferenceManager;
import android.telephony.ServiceState;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.android.mms.data.Contact;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.NotificationInd;
import miui.os.Build;
import miui.telephony.TelephonyManagerEx;

public class DownloadManager
{
  private static DownloadManager sInstance;
  private boolean[] mAutoDownload;
  private final Context mContext;
  private final Handler mHandler;
  private boolean mIsOutOfMemory = false;
  private final SharedPreferences mPreferences;
  private final SharedPreferences.OnSharedPreferenceChangeListener mPreferencesChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener()
  {
    public void onSharedPreferenceChanged(SharedPreferences paramAnonymousSharedPreferences, String paramAnonymousString)
    {
      int j = mAutoDownload.length;
      int i = 0;
      for (;;)
      {
        if (i < j)
        {
          long l = MSimUtils.getSimIdBySlotId(i);
          if (l >= 0L)
          {
            ??? = MSimUtils.createKeyWithSimId(l, "pref_key_mms_auto_retrieval");
            String str = MSimUtils.createKeyWithSimId(l, "pref_key_mms_retrieval_during_roaming");
            if (((!TextUtils.isEmpty((CharSequence)???)) && (((String)???).equals(paramAnonymousString))) || ((!TextUtils.isEmpty(str)) && (str.equals(paramAnonymousString)))) {
              synchronized (DownloadManager.sInstance)
              {
                mAutoDownload[i] = DownloadManager.this.getAutoDownloadState(paramAnonymousSharedPreferences, l);
              }
            }
          }
        }
        else
        {
          return;
        }
        i += 1;
      }
    }
  };
  private final BroadcastReceiver mRoamingStateListener = new BroadcastReceiver()
  {
    public void onReceive(Context arg1, Intent paramAnonymousIntent)
    {
      if ("android.intent.action.SERVICE_STATE".equals(paramAnonymousIntent.getAction()))
      {
        ServiceState localServiceState = ServiceState.newFromBundle(paramAnonymousIntent.getExtras());
        boolean bool = localServiceState.getRoaming();
        synchronized (DownloadManager.sInstance)
        {
          int i = MSimUtils.getSlotIdFromIntent(paramAnonymousIntent);
          mSimServiceState[i] = localServiceState.getState();
          if (MSimUtils.isMSimSlotIdValid(i))
          {
            long l = MSimUtils.getSimIdBySlotId(i);
            if (l < 0L)
            {
              Log.e("DownloadManager", "Download manager : cannot get sim id for slot " + i);
              return;
            }
            mAutoDownload[i] = DownloadManager.this.getAutoDownloadState(mPreferences, bool, l);
          }
          return;
        }
      }
    }
  };
  private int[] mSimServiceState;
  
  private DownloadManager(Context paramContext)
  {
    mContext = paramContext;
    mHandler = new Handler();
    int j = MSimUtils.getMultiSimCount();
    mAutoDownload = new boolean[j];
    mSimServiceState = new int[j];
    mPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
    mPreferences.registerOnSharedPreferenceChangeListener(mPreferencesChangeListener);
    paramContext.registerReceiver(mRoamingStateListener, new IntentFilter("android.intent.action.SERVICE_STATE"));
    int i = 0;
    while (i < j)
    {
      mSimServiceState[i] = 0;
      long l = MSimUtils.getSimIdBySlotId(i);
      if (l >= 0L) {
        mAutoDownload[i] = getAutoDownloadState(mPreferences, l);
      }
      i += 1;
    }
  }
  
  private boolean getAutoDownloadState(SharedPreferences paramSharedPreferences, long paramLong)
  {
    return getAutoDownloadState(paramSharedPreferences, isRoaming(paramLong), paramLong);
  }
  
  private boolean getAutoDownloadState(SharedPreferences paramSharedPreferences, boolean paramBoolean, long paramLong)
  {
    if (paramSharedPreferences.getBoolean(MSimUtils.createKeyWithSimId(paramLong, "pref_key_mms_auto_retrieval"), true))
    {
      boolean bool = paramSharedPreferences.getBoolean(MSimUtils.createKeyWithSimId(paramLong, "pref_key_mms_retrieval_during_roaming"), false);
      if ((!paramBoolean) || (bool)) {
        return true;
      }
    }
    return false;
  }
  
  public static DownloadManager getInstance()
  {
    if (sInstance == null) {
      throw new IllegalStateException("Uninitialized.");
    }
    return sInstance;
  }
  
  private String getMessage(Uri paramUri)
    throws MmsException
  {
    Object localObject = (NotificationInd)MiuiPduPersister.getPduPersister(mContext).load(paramUri);
    paramUri = ((NotificationInd)localObject).getSubject();
    if (paramUri != null)
    {
      paramUri = paramUri.getString();
      localObject = ((NotificationInd)localObject).getFrom();
      if (localObject == null) {
        break label89;
      }
    }
    label89:
    for (localObject = Contact.get(((EncodedStringValue)localObject).getString()).load(true, true).getName();; localObject = mContext.getString(2131362003))
    {
      return mContext.getString(2131362004, new Object[] { paramUri, localObject });
      paramUri = mContext.getString(2131362002);
      break;
    }
  }
  
  public static void init(Context paramContext)
  {
    if (sInstance != null) {
      Log.w("DownloadManager", "Already initialized.");
    }
    sInstance = new DownloadManager(paramContext.getApplicationContext());
  }
  
  private boolean isRoaming(long paramLong)
  {
    if (MSimUtils.isMSim())
    {
      int i = MSimUtils.getSlotIdBySimInfoId(paramLong);
      return TelephonyManagerEx.getDefault().isNetworkRoamingForSlot(i);
    }
    return "true".equals(SystemProperties.get("gsm.operator.isroaming", null));
  }
  
  public int getServiceStateForSlotId(int paramInt)
  {
    if ((MSimUtils.isMSimSlotIdValid(paramInt)) && (paramInt >= 0) && (paramInt < mSimServiceState.length)) {
      return mSimServiceState[paramInt];
    }
    return -1;
  }
  
  public boolean isAuto(long paramLong)
  {
    return (getAutoDownloadState(PreferenceManager.getDefaultSharedPreferences(mContext), paramLong)) && (!mIsOutOfMemory);
  }
  
  public void markState(Uri paramUri, int paramInt, long paramLong)
  {
    final Object localObject;
    try
    {
      localObject = MiuiPduPersister.getPduPersister(mContext).load(paramUri);
      if ((localObject instanceof NotificationInd))
      {
        if ((((NotificationInd)localObject).getExpiry() >= System.currentTimeMillis() / 1000L) || (paramInt != 129)) {
          break label141;
        }
        mHandler.post(new Runnable()
        {
          public void run()
          {
            Toast.makeText(mContext, 2131361862, 1).show();
          }
        });
        SqliteWrapper.delete(mContext, mContext.getContentResolver(), paramUri, null, null);
        return;
      }
      if (localObject != null)
      {
        Log.e("DownloadManager", "pdu is not Notification ind for uri " + localObject.toString());
        return;
      }
    }
    catch (MmsException paramUri)
    {
      Log.e("DownloadManager", paramUri.getMessage(), paramUri);
      return;
    }
    Log.e("DownloadManager", "pdu is not Notification ind");
    return;
    label141:
    if (paramInt == 135) {}
    for (;;)
    {
      try
      {
        localObject = getMessage(paramUri);
        mHandler.post(new Runnable()
        {
          public void run()
          {
            Toast.makeText(mContext, localObject, 1).show();
          }
        });
        i = paramInt;
      }
      catch (MmsException localMmsException)
      {
        Log.e("DownloadManager", localMmsException.getMessage(), localMmsException);
        i = paramInt;
        continue;
      }
      localObject = new ContentValues();
      ((ContentValues)localObject).put("st", Integer.valueOf(i));
      SqliteWrapper.update(mContext, mContext.getContentResolver(), paramUri, (ContentValues)localObject, null, null);
      return;
      int i = paramInt;
      if (paramLong >= 0L)
      {
        int j = MSimUtils.getSlotIdBySimInfoId(paramLong);
        i = paramInt;
        if (MSimUtils.isMSimSlotIdValid(j))
        {
          i = paramInt;
          if (mAutoDownload[j] == 0) {
            i = paramInt | 0x4;
          }
        }
      }
    }
  }
  
  public void setOutOfMemory(boolean paramBoolean)
  {
    if (Build.IS_CM_CUSTOMIZATION_TEST) {
      mIsOutOfMemory = paramBoolean;
    }
  }
  
  public void showErrorCodeToast(final int paramInt)
  {
    mHandler.post(new Runnable()
    {
      public void run()
      {
        try
        {
          Toast.makeText(mContext, paramInt, 1).show();
          return;
        }
        catch (Exception localException)
        {
          Log.e("DownloadManager", "Caught an exception in showErrorCodeToast");
        }
      }
    });
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.DownloadManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */