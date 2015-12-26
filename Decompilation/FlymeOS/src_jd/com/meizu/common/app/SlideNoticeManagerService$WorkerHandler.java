package com.meizu.common.app;

import android.os.Handler;
import android.os.Message;

final class SlideNoticeManagerService$WorkerHandler
  extends Handler
{
  private SlideNoticeManagerService$WorkerHandler(SlideNoticeManagerService paramSlideNoticeManagerService) {}
  
  public void handleMessage(Message paramMessage)
  {
    switch (what)
    {
    default: 
      return;
    }
    SlideNoticeManagerService.access$000(this$0, (SlideNoticeManagerService.NoticeRecord)obj);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.app.SlideNoticeManagerService.WorkerHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */