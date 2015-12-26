package com.meizu.common.widget;

class CircularProgressButton$5
  implements CircularProgressButton.OnAnimationEndListener
{
  CircularProgressButton$5(CircularProgressButton paramCircularProgressButton) {}
  
  public void onAnimationEnd()
  {
    this$0.removeIcon();
    this$0.setText(CircularProgressButton.access$500(this$0));
    CircularProgressButton.access$002(this$0, false);
    this$0.setClickable(true);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CircularProgressButton.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */