package com.meizu.common.widget;

class SlidingMenu$1
  implements CustomViewAbove.OnPageChangeListener
{
  public static final int POSITION_CLOSE = 1;
  public static final int POSITION_OPEN = 0;
  
  SlidingMenu$1(SlidingMenu paramSlidingMenu) {}
  
  public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
  {
    if (SlidingMenu.access$000(this$0) != null) {
      SlidingMenu.access$000(this$0).onScrolling(paramInt2);
    }
  }
  
  public void onPageSelected(int paramInt)
  {
    if ((paramInt == 0) && (SlidingMenu.access$100(this$0) != null))
    {
      SlidingMenu.access$100(this$0).onOpen();
      if (SlidingMenu.access$000(this$0) != null) {
        if (paramInt != 1) {
          break label89;
        }
      }
    }
    label89:
    for (SlidingMenu.State localState = SlidingMenu.State.CLOSE;; localState = SlidingMenu.State.OPEN)
    {
      SlidingMenu.access$000(this$0).onMenuState(localState);
      return;
      if ((paramInt != 1) || (SlidingMenu.access$200(this$0) == null)) {
        break;
      }
      SlidingMenu.access$200(this$0).onClose();
      break;
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.SlidingMenu.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */