package com.meizu.common.widget;

import android.animation.TimeInterpolator;

class SelectionButton$1
  implements TimeInterpolator
{
  SelectionButton$1(SelectionButton paramSelectionButton) {}
  
  public float getInterpolation(float paramFloat)
  {
    int i = Math.round(12.0F * paramFloat);
    if (SelectionButton.access$000(this$0) == 0) {}
    for (float[] arrayOfFloat = SelectionButton.access$100();; arrayOfFloat = SelectionButton.access$200()) {
      return arrayOfFloat[i];
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.SelectionButton.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */