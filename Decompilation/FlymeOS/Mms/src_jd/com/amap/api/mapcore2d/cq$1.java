package com.amap.api.mapcore2d;

import android.os.Process;
import java.util.concurrent.atomic.AtomicBoolean;

class cq$1
  extends cq.e<Params, Result>
{
  cq$1(cq paramcq)
  {
    super(null);
  }
  
  public Result call()
  {
    cq.a(a).set(true);
    Process.setThreadPriority(10);
    return (Result)cq.a(a, a.a(b));
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.cq.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */