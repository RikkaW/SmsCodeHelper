package com.amap.api.mapcore2d;

import java.util.Collections;

class bf
  implements Runnable
{
  bf(be parambe) {}
  
  public void run()
  {
    try
    {
      Collections.sort(be.a(a), a.b);
      Collections.sort(be.b(a), a.b);
      a.invalidate();
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        ed.a(localThrowable, "MapOverlayImageView", "changeOverlayIndex");
      }
    }
    finally {}
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.bf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */