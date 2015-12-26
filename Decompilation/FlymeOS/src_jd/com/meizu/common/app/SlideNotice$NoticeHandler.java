package com.meizu.common.app;

import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

final class SlideNotice$NoticeHandler
  extends Handler
{
  private WeakReference<SlideNotice> mNotice;
  
  public SlideNotice$NoticeHandler(SlideNotice paramSlideNotice)
  {
    mNotice = new WeakReference(paramSlideNotice);
  }
  
  public void handleMessage(Message paramMessage)
  {
    switch (what)
    {
    default: 
      return;
    }
    ((SlideNotice.OnClickNoticeListener)obj).onClick((SlideNotice)mNotice.get());
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.app.SlideNotice.NoticeHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */