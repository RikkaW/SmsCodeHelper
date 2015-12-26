package com.xiaomi.measite.smack;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.smack.Connection;
import com.xiaomi.smack.ConnectionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

class AndroidDebugger$4
  implements ConnectionListener
{
  AndroidDebugger$4(AndroidDebugger paramAndroidDebugger) {}
  
  public void connectionClosed(Connection paramConnection, int paramInt, Exception paramException)
  {
    MyLog.v("SMACK " + AndroidDebugger.access$000(this$0).format(new Date()) + " Connection closed (" + AndroidDebugger.access$100(this$0).hashCode() + ")");
  }
  
  public void connectionStarted(Connection paramConnection)
  {
    MyLog.v("SMACK " + AndroidDebugger.access$000(this$0).format(new Date()) + " Connection started (" + AndroidDebugger.access$100(this$0).hashCode() + ")");
  }
  
  public void reconnectionFailed(Connection paramConnection, Exception paramException)
  {
    MyLog.v("SMACK " + AndroidDebugger.access$000(this$0).format(new Date()) + " Reconnection failed due to an exception (" + AndroidDebugger.access$100(this$0).hashCode() + ")");
    paramException.printStackTrace();
  }
  
  public void reconnectionSuccessful(Connection paramConnection)
  {
    MyLog.v("SMACK " + AndroidDebugger.access$000(this$0).format(new Date()) + " Connection reconnected (" + AndroidDebugger.access$100(this$0).hashCode() + ")");
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.measite.smack.AndroidDebugger.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */