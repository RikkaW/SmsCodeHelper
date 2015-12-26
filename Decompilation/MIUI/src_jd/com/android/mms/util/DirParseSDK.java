package com.android.mms.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.android.fileexplorer.service.IDirParse;
import com.android.fileexplorer.service.IDirParse.Stub;
import com.android.mms.MmsApp;

public class DirParseSDK
{
  private static DirParseSDK mInstance;
  private final String TAG = DirParseSDK.class.getSimpleName();
  private ServiceConnection mConnection = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
    {
      Log.d(TAG, "dirparse service connected");
      DirParseSDK.access$102(DirParseSDK.this, IDirParse.Stub.asInterface(paramAnonymousIBinder));
    }
    
    public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
    {
      Log.d(TAG, "dirparse service disconnected");
      DirParseSDK.access$102(DirParseSDK.this, null);
    }
  };
  private Context mContext;
  private IDirParse mService;
  
  private DirParseSDK(Context paramContext)
  {
    mContext = paramContext;
  }
  
  public static DirParseSDK getInstance()
  {
    if (mInstance == null) {}
    try
    {
      if (mInstance == null) {
        mInstance = new DirParseSDK(MmsApp.getApp());
      }
      return mInstance;
    }
    finally {}
  }
  
  public void close()
  {
    mContext.unbindService(mConnection);
    mService = null;
  }
  
  public IDirParse getService()
  {
    return mService;
  }
  
  public void init()
  {
    Intent localIntent = new Intent("com.android.fileexplorer.DirParseService").setPackage("com.android.fileexplorer");
    mContext.bindService(localIntent, mConnection, 1);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.DirParseSDK
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */