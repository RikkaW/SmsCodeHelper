package com.meizu.common.widget;

class CircularProgressButton$4
  implements CircularProgressButton.OnAnimationEndListener
{
  CircularProgressButton$4(CircularProgressButton paramCircularProgressButton) {}
  
  public void onAnimationEnd()
  {
    if (CircularProgressButton.access$700(this$0) != 0)
    {
      this$0.setText(null);
      CircularProgressButton.access$200(this$0, CircularProgressButton.access$700(this$0));
    }
    for (;;)
    {
      CircularProgressButton.access$002(this$0, false);
      this$0.setClickable(true);
      this$0.setTextColor(CircularProgressButton.access$900(this$0));
      return;
      this$0.setText(CircularProgressButton.access$800(this$0));
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CircularProgressButton.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */