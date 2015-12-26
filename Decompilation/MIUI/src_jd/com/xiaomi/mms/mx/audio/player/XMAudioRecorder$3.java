package com.xiaomi.mms.mx.audio.player;

import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Message;

class XMAudioRecorder$3
  implements Runnable
{
  XMAudioRecorder$3(XMAudioRecorder paramXMAudioRecorder) {}
  
  public void run()
  {
    if (XMAudioRecorder.access$100(this$0))
    {
      Message localMessage = XMAudioRecorder.access$200(this$0).obtainMessage();
      what = 2;
      obj = Integer.valueOf(XMAudioRecorder.access$300(this$0).getMaxAmplitude());
      localMessage.sendToTarget();
      XMAudioRecorder.access$404(this$0);
      XMAudioRecorder.access$200(this$0).postDelayed(this, 100L);
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.audio.player.XMAudioRecorder.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */