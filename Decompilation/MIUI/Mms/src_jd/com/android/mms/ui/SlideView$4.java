package com.android.mms.ui;

import java.util.Comparator;

class SlideView$4
  implements Comparator<SlideView.Position>
{
  SlideView$4(SlideView paramSlideView) {}
  
  public int compare(SlideView.Position paramPosition1, SlideView.Position paramPosition2)
  {
    int k = mLeft;
    int i = mTop;
    int m = mLeft;
    int j = i - mTop;
    i = j;
    if (j == 0) {
      i = k - m;
    }
    j = i;
    if (i == 0) {
      j = -1;
    }
    return j;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideView.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */