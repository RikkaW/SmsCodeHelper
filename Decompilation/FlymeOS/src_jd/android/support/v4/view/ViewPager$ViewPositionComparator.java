package android.support.v4.view;

import android.view.View;
import java.util.Comparator;

class ViewPager$ViewPositionComparator
  implements Comparator<View>
{
  public int compare(View paramView1, View paramView2)
  {
    paramView1 = (ViewPager.LayoutParams)paramView1.getLayoutParams();
    paramView2 = (ViewPager.LayoutParams)paramView2.getLayoutParams();
    if (isDecor != isDecor)
    {
      if (isDecor) {
        return 1;
      }
      return -1;
    }
    return position - position;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.ViewPager.ViewPositionComparator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */