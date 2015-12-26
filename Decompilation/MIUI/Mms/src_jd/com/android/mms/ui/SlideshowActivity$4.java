package com.android.mms.ui;

import org.w3c.dom.events.Event;

class SlideshowActivity$4
  implements Runnable
{
  SlideshowActivity$4(SlideshowActivity paramSlideshowActivity, Event paramEvent) {}
  
  public void run()
  {
    if (val$event.getType().equals("SimlDocumentEnd")) {
      this$0.finish();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideshowActivity.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */