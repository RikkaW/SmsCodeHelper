package cn.com.xy.sms.sdk.ui.popu.util;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.log.LogManager;

final class ViewManger$1
  implements ViewTreeObserver.OnGlobalLayoutListener
{
  ViewManger$1(View paramView, XyCallBack paramXyCallBack) {}
  
  @SuppressLint({"NewApi"})
  public void onGlobalLayout()
  {
    try
    {
      val$view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
      val$callBack.execute(new Object[0]);
      return;
    }
    catch (NoSuchMethodError localNoSuchMethodError1)
    {
      for (;;)
      {
        try
        {
          val$view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        }
        catch (NoSuchMethodError localNoSuchMethodError2)
        {
          if (!LogManager.debug) {
            continue;
          }
          localNoSuchMethodError2.printStackTrace();
        }
        catch (Exception localException1) {}
        if (LogManager.debug) {
          localException1.printStackTrace();
        }
      }
    }
    catch (Exception localException2)
    {
      for (;;)
      {
        if (LogManager.debug) {
          localException2.printStackTrace();
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.ui.popu.util.ViewManger.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */