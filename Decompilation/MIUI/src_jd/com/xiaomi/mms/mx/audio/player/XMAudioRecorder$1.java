package com.xiaomi.mms.mx.audio.player;

import android.media.MediaRecorder;
import android.media.MediaRecorder.OnInfoListener;
import android.util.Log;

class XMAudioRecorder$1
  implements MediaRecorder.OnInfoListener
{
  XMAudioRecorder$1(XMAudioRecorder paramXMAudioRecorder) {}
  
  public void onInfo(MediaRecorder paramMediaRecorder, int paramInt1, int paramInt2)
  {
    XMAudioRecorder.access$000(this$0, -3);
    Log.v("XMAudioRecorder", "unknown audio failed, errorcode=" + paramInt1);
    XMAudioRecorder.access$102(this$0, false);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.audio.player.XMAudioRecorder.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */