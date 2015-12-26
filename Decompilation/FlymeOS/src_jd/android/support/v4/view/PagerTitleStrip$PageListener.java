package android.support.v4.view;

import android.database.DataSetObserver;

class PagerTitleStrip$PageListener
  extends DataSetObserver
  implements ViewPager.OnAdapterChangeListener, ViewPager.OnPageChangeListener
{
  private int mScrollState;
  
  private PagerTitleStrip$PageListener(PagerTitleStrip paramPagerTitleStrip) {}
  
  public void onAdapterChanged(PagerAdapter paramPagerAdapter1, PagerAdapter paramPagerAdapter2)
  {
    this$0.updateAdapter(paramPagerAdapter1, paramPagerAdapter2);
  }
  
  public void onChanged()
  {
    float f = 0.0F;
    this$0.updateText(this$0.mPager.getCurrentItem(), this$0.mPager.getAdapter());
    if (PagerTitleStrip.access$100(this$0) >= 0.0F) {
      f = PagerTitleStrip.access$100(this$0);
    }
    this$0.updateTextPositions(this$0.mPager.getCurrentItem(), f, true);
  }
  
  public void onPageScrollStateChanged(int paramInt)
  {
    mScrollState = paramInt;
  }
  
  public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
  {
    paramInt2 = paramInt1;
    if (paramFloat > 0.5F) {
      paramInt2 = paramInt1 + 1;
    }
    this$0.updateTextPositions(paramInt2, paramFloat, false);
  }
  
  public void onPageSelected(int paramInt)
  {
    float f = 0.0F;
    if (mScrollState == 0)
    {
      this$0.updateText(this$0.mPager.getCurrentItem(), this$0.mPager.getAdapter());
      if (PagerTitleStrip.access$100(this$0) >= 0.0F) {
        f = PagerTitleStrip.access$100(this$0);
      }
      this$0.updateTextPositions(this$0.mPager.getCurrentItem(), f, true);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.PagerTitleStrip.PageListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */