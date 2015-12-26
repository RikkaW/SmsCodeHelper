package com.xiaomi.measite.smack;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.smack.util.WriterListener;
import java.text.SimpleDateFormat;
import java.util.Date;

class AndroidDebugger$2
  implements WriterListener
{
  AndroidDebugger$2(AndroidDebugger paramAndroidDebugger) {}
  
  public void write(String paramString)
  {
    MyLog.v("SMACK " + AndroidDebugger.access$000(this$0).format(new Date()) + " SENT (" + AndroidDebugger.access$100(this$0).hashCode() + "): " + paramString);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.measite.smack.AndroidDebugger.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */