package cn.com.xy.sms.sdk.ui.popu.util;

import android.os.Handler;
import android.os.Message;
import cn.com.xy.sms.util.ParseSmsToBubbleUtil;

final class XySdkUtil$1
  extends Handler
{
  public void handleMessage(Message paramMessage)
  {
    ParseSmsToBubbleUtil.beforeHandParseReceiveSms(500, 3);
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.ui.popu.util.XySdkUtil.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */