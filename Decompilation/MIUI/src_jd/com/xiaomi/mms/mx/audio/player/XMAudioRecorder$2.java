package com.xiaomi.mms.mx.audio.player;

import android.media.MediaRecorder;
import android.media.MediaRecorder.OnErrorListener;
import android.util.Log;

class XMAudioRecorder$2
  implements MediaRecorder.OnErrorListener
{
  XMAudioRecorder$2(XMAudioRecorder paramXMAudioRecorder) {}
  
  public void onError(MediaRecorder paramMediaRecorder, int paramInt1, int paramInt2)
  {
    XMAudioRecorder.access$000(this$0, -2);
    Log.v("XMAudioRecorder", "unknown audio error failed, errorcode=" + paramInt1);
    XMAudioRecorder.access$102(this$0, false);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.audio.player.XMAudioRecorder.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */