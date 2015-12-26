package com.amap.api.services.help;

import android.content.Context;
import android.os.Handler;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.l;
import com.amap.api.services.core.p;
import java.util.List;

public final class Inputtips
{
  private Context a;
  private InputtipsListener b;
  private Handler c;
  
  public Inputtips(Context paramContext, InputtipsListener paramInputtipsListener)
  {
    a = paramContext.getApplicationContext();
    b = paramInputtipsListener;
    c = p.a();
  }
  
  public void requestInputtips(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString1.equals(""))) {
      throw new AMapException("无效的参数 - IllegalArgumentException");
    }
    l.a(a);
    new Inputtips.1(this, paramString1, paramString2).start();
  }
  
  public static abstract interface InputtipsListener
  {
    public abstract void onGetInputtips(List<Tip> paramList, int paramInt);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.help.Inputtips
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */