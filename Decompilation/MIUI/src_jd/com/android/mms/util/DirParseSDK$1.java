package com.android.mms.util;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.android.fileexplorer.service.IDirParse.Stub;

class DirParseSDK$1
  implements ServiceConnection
{
  DirParseSDK$1(DirParseSDK paramDirParseSDK) {}
  
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    Log.d(DirParseSDK.access$000(this$0), "dirparse service connected");
    DirParseSDK.access$102(this$0, IDirParse.Stub.asInterface(paramIBinder));
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    Log.d(DirParseSDK.access$000(this$0), "dirparse service disconnected");
    DirParseSDK.access$102(this$0, null);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.DirParseSDK.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */