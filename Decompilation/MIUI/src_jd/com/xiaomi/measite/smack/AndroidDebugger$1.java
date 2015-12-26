package com.xiaomi.measite.smack;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.smack.util.ReaderListener;
import java.text.SimpleDateFormat;
import java.util.Date;

class AndroidDebugger$1
  implements ReaderListener
{
  AndroidDebugger$1(AndroidDebugger paramAndroidDebugger) {}
  
  public void read(String paramString)
  {
    MyLog.v("SMACK " + AndroidDebugger.access$000(this$0).format(new Date()) + " RCV  (" + AndroidDebugger.access$100(this$0).hashCode() + "): " + paramString);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.measite.smack.AndroidDebugger.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */