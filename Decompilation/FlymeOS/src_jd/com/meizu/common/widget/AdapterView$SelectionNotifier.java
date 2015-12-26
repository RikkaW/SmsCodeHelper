package com.meizu.common.widget;

class AdapterView$SelectionNotifier
  implements Runnable
{
  private AdapterView$SelectionNotifier(AdapterView paramAdapterView) {}
  
  public void run()
  {
    if (this$0.mDataChanged)
    {
      if (this$0.getAdapter() != null) {
        this$0.post(this);
      }
      return;
    }
    AdapterView.access$200(this$0);
    AdapterView.access$300(this$0);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.AdapterView.SelectionNotifier
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */