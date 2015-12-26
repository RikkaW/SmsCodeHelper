package com.meizu.common.widget;

class CircularProgressButton$3
  implements CircularProgressButton.OnAnimationEndListener
{
  CircularProgressButton$3(CircularProgressButton paramCircularProgressButton) {}
  
  public void onAnimationEnd()
  {
    this$0.removeIcon();
    this$0.setText(CircularProgressButton.access$500(this$0));
    CircularProgressButton.access$002(this$0, false);
    this$0.setClickable(true);
    this$0.setTextColor(CircularProgressButton.access$600(this$0));
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CircularProgressButton.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */