package com.meizu.common.widget;

class CircularProgressButton$2
  implements CircularProgressButton.OnAnimationEndListener
{
  CircularProgressButton$2(CircularProgressButton paramCircularProgressButton) {}
  
  public void onAnimationEnd()
  {
    if (CircularProgressButton.access$100(this$0) != 0)
    {
      this$0.setText(null);
      CircularProgressButton.access$200(this$0, CircularProgressButton.access$100(this$0));
    }
    for (;;)
    {
      CircularProgressButton.access$002(this$0, false);
      this$0.setClickable(true);
      this$0.setTextColor(CircularProgressButton.access$400(this$0));
      return;
      this$0.setText(CircularProgressButton.access$300(this$0));
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CircularProgressButton.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */