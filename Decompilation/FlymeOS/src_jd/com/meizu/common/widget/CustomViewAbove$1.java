package com.meizu.common.widget;

class CustomViewAbove$1
  extends CustomViewAbove.SimpleOnPageChangeListener
{
  CustomViewAbove$1(CustomViewAbove paramCustomViewAbove) {}
  
  public void onPageSelected(int paramInt)
  {
    if (CustomViewAbove.access$000(this$0) != null) {}
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
    case 2: 
      CustomViewAbove.access$000(this$0).setChildrenEnabled(true);
      return;
    }
    CustomViewAbove.access$000(this$0).setChildrenEnabled(true);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CustomViewAbove.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */