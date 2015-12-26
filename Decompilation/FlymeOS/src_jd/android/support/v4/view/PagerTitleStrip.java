package android.support.v4.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.TextView;
import java.lang.ref.WeakReference;

public class PagerTitleStrip
  extends ViewGroup
  implements ViewPager.Decor
{
  private static final int[] ATTRS = { 16842804, 16842901, 16842904, 16842927 };
  private static final PagerTitleStripImpl IMPL = new PagerTitleStripImplBase();
  private static final float SIDE_ALPHA = 0.6F;
  private static final String TAG = "PagerTitleStrip";
  private static final int[] TEXT_ATTRS = { 16843660 };
  private static final int TEXT_SPACING = 16;
  TextView mCurrText;
  private int mGravity;
  private int mLastKnownCurrentPage = -1;
  private float mLastKnownPositionOffset = -1.0F;
  TextView mNextText;
  private int mNonPrimaryAlpha;
  private final PageListener mPageListener = new PageListener(null);
  ViewPager mPager;
  TextView mPrevText;
  private int mScaledTextSpacing;
  int mTextColor;
  private boolean mUpdatingPositions;
  private boolean mUpdatingText;
  private WeakReference<PagerAdapter> mWatchingAdapter;
  
  static
  {
    if (Build.VERSION.SDK_INT >= 14)
    {
      IMPL = new PagerTitleStripImplIcs();
      return;
    }
  }
  
  public PagerTitleStrip(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public PagerTitleStrip(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    TextView localTextView = new TextView(paramContext);
    mPrevText = localTextView;
    addView(localTextView);
    localTextView = new TextView(paramContext);
    mCurrText = localTextView;
    addView(localTextView);
    localTextView = new TextView(paramContext);
    mNextText = localTextView;
    addView(localTextView);
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, ATTRS);
    int i = paramAttributeSet.getResourceId(0, 0);
    if (i != 0)
    {
      mPrevText.setTextAppearance(paramContext, i);
      mCurrText.setTextAppearance(paramContext, i);
      mNextText.setTextAppearance(paramContext, i);
    }
    int j = paramAttributeSet.getDimensionPixelSize(1, 0);
    if (j != 0) {
      setTextSize(0, j);
    }
    if (paramAttributeSet.hasValue(2))
    {
      j = paramAttributeSet.getColor(2, 0);
      mPrevText.setTextColor(j);
      mCurrText.setTextColor(j);
      mNextText.setTextColor(j);
    }
    mGravity = paramAttributeSet.getInteger(3, 80);
    paramAttributeSet.recycle();
    mTextColor = mCurrText.getTextColors().getDefaultColor();
    setNonPrimaryAlpha(0.6F);
    mPrevText.setEllipsize(TextUtils.TruncateAt.END);
    mCurrText.setEllipsize(TextUtils.TruncateAt.END);
    mNextText.setEllipsize(TextUtils.TruncateAt.END);
    if (i != 0)
    {
      paramAttributeSet = paramContext.obtainStyledAttributes(i, TEXT_ATTRS);
      bool = paramAttributeSet.getBoolean(0, false);
      paramAttributeSet.recycle();
    }
    if (bool)
    {
      setSingleLineAllCaps(mPrevText);
      setSingleLineAllCaps(mCurrText);
      setSingleLineAllCaps(mNextText);
    }
    for (;;)
    {
      mScaledTextSpacing = ((int)(getResourcesgetDisplayMetricsdensity * 16.0F));
      return;
      mPrevText.setSingleLine();
      mCurrText.setSingleLine();
      mNextText.setSingleLine();
    }
  }
  
  private static void setSingleLineAllCaps(TextView paramTextView)
  {
    IMPL.setSingleLineAllCaps(paramTextView);
  }
  
  int getMinHeight()
  {
    int i = 0;
    Drawable localDrawable = getBackground();
    if (localDrawable != null) {
      i = localDrawable.getIntrinsicHeight();
    }
    return i;
  }
  
  public int getTextSpacing()
  {
    return mScaledTextSpacing;
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    Object localObject = getParent();
    if (!(localObject instanceof ViewPager)) {
      throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
    }
    localObject = (ViewPager)localObject;
    PagerAdapter localPagerAdapter = ((ViewPager)localObject).getAdapter();
    ((ViewPager)localObject).setInternalPageChangeListener(mPageListener);
    ((ViewPager)localObject).setOnAdapterChangeListener(mPageListener);
    mPager = ((ViewPager)localObject);
    if (mWatchingAdapter != null) {}
    for (localObject = (PagerAdapter)mWatchingAdapter.get();; localObject = null)
    {
      updateAdapter((PagerAdapter)localObject, localPagerAdapter);
      return;
    }
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (mPager != null)
    {
      updateAdapter(mPager.getAdapter(), null);
      mPager.setInternalPageChangeListener(null);
      mPager.setOnAdapterChangeListener(null);
      mPager = null;
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    float f = 0.0F;
    if (mPager != null)
    {
      if (mLastKnownPositionOffset >= 0.0F) {
        f = mLastKnownPositionOffset;
      }
      updateTextPositions(mLastKnownCurrentPage, f, true);
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int j = View.MeasureSpec.getMode(paramInt1);
    int i = View.MeasureSpec.getMode(paramInt2);
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    if (j != 1073741824) {
      throw new IllegalStateException("Must measure with an exact width");
    }
    j = getMinHeight();
    int k = getPaddingTop() + getPaddingBottom();
    int m = View.MeasureSpec.makeMeasureSpec((int)(paramInt1 * 0.8F), Integer.MIN_VALUE);
    int n = View.MeasureSpec.makeMeasureSpec(paramInt2 - k, Integer.MIN_VALUE);
    mPrevText.measure(m, n);
    mCurrText.measure(m, n);
    mNextText.measure(m, n);
    if (i == 1073741824)
    {
      setMeasuredDimension(paramInt1, paramInt2);
      return;
    }
    setMeasuredDimension(paramInt1, Math.max(j, mCurrText.getMeasuredHeight() + k));
  }
  
  public void requestLayout()
  {
    if (!mUpdatingText) {
      super.requestLayout();
    }
  }
  
  public void setGravity(int paramInt)
  {
    mGravity = paramInt;
    requestLayout();
  }
  
  public void setNonPrimaryAlpha(float paramFloat)
  {
    mNonPrimaryAlpha = ((int)(255.0F * paramFloat) & 0xFF);
    int i = mNonPrimaryAlpha << 24 | mTextColor & 0xFFFFFF;
    mPrevText.setTextColor(i);
    mNextText.setTextColor(i);
  }
  
  public void setTextColor(int paramInt)
  {
    mTextColor = paramInt;
    mCurrText.setTextColor(paramInt);
    paramInt = mNonPrimaryAlpha << 24 | mTextColor & 0xFFFFFF;
    mPrevText.setTextColor(paramInt);
    mNextText.setTextColor(paramInt);
  }
  
  public void setTextSize(int paramInt, float paramFloat)
  {
    mPrevText.setTextSize(paramInt, paramFloat);
    mCurrText.setTextSize(paramInt, paramFloat);
    mNextText.setTextSize(paramInt, paramFloat);
  }
  
  public void setTextSpacing(int paramInt)
  {
    mScaledTextSpacing = paramInt;
    requestLayout();
  }
  
  void updateAdapter(PagerAdapter paramPagerAdapter1, PagerAdapter paramPagerAdapter2)
  {
    if (paramPagerAdapter1 != null)
    {
      paramPagerAdapter1.unregisterDataSetObserver(mPageListener);
      mWatchingAdapter = null;
    }
    if (paramPagerAdapter2 != null)
    {
      paramPagerAdapter2.registerDataSetObserver(mPageListener);
      mWatchingAdapter = new WeakReference(paramPagerAdapter2);
    }
    if (mPager != null)
    {
      mLastKnownCurrentPage = -1;
      mLastKnownPositionOffset = -1.0F;
      updateText(mPager.getCurrentItem(), paramPagerAdapter2);
      requestLayout();
    }
  }
  
  void updateText(int paramInt, PagerAdapter paramPagerAdapter)
  {
    Object localObject2 = null;
    int i;
    if (paramPagerAdapter != null)
    {
      i = paramPagerAdapter.getCount();
      mUpdatingText = true;
      if ((paramInt < 1) || (paramPagerAdapter == null)) {
        break label250;
      }
    }
    label250:
    for (Object localObject1 = paramPagerAdapter.getPageTitle(paramInt - 1);; localObject1 = null)
    {
      mPrevText.setText((CharSequence)localObject1);
      TextView localTextView = mCurrText;
      if ((paramPagerAdapter != null) && (paramInt < i)) {}
      for (localObject1 = paramPagerAdapter.getPageTitle(paramInt);; localObject1 = null)
      {
        localTextView.setText((CharSequence)localObject1);
        localObject1 = localObject2;
        if (paramInt + 1 < i)
        {
          localObject1 = localObject2;
          if (paramPagerAdapter != null) {
            localObject1 = paramPagerAdapter.getPageTitle(paramInt + 1);
          }
        }
        mNextText.setText((CharSequence)localObject1);
        int m = getWidth();
        int n = getPaddingLeft();
        int i1 = getPaddingRight();
        i = getHeight();
        int j = getPaddingTop();
        int k = getPaddingBottom();
        m = View.MeasureSpec.makeMeasureSpec((int)((m - n - i1) * 0.8F), Integer.MIN_VALUE);
        i = View.MeasureSpec.makeMeasureSpec(i - j - k, Integer.MIN_VALUE);
        mPrevText.measure(m, i);
        mCurrText.measure(m, i);
        mNextText.measure(m, i);
        mLastKnownCurrentPage = paramInt;
        if (!mUpdatingPositions) {
          updateTextPositions(paramInt, mLastKnownPositionOffset, false);
        }
        mUpdatingText = false;
        return;
        i = 0;
        break;
      }
    }
  }
  
  void updateTextPositions(int paramInt, float paramFloat, boolean paramBoolean)
  {
    int m;
    int i4;
    int k;
    int i3;
    int n;
    int i;
    int i2;
    int i1;
    int j;
    int i5;
    int i7;
    int i6;
    int i8;
    if (paramInt != mLastKnownCurrentPage)
    {
      updateText(paramInt, mPager.getAdapter());
      mUpdatingPositions = true;
      m = mPrevText.getMeasuredWidth();
      i4 = mCurrText.getMeasuredWidth();
      k = mNextText.getMeasuredWidth();
      i3 = i4 / 2;
      n = getWidth();
      i = getHeight();
      i2 = getPaddingLeft();
      i1 = getPaddingRight();
      paramInt = getPaddingTop();
      j = getPaddingBottom();
      i5 = i1 + i3;
      float f2 = 0.5F + paramFloat;
      float f1 = f2;
      if (f2 > 1.0F) {
        f1 = f2 - 1.0F;
      }
      i3 = n - i5 - (int)(f1 * (n - (i2 + i3) - i5)) - i4 / 2;
      i4 = i3 + i4;
      i7 = mPrevText.getBaseline();
      i6 = mCurrText.getBaseline();
      i5 = mNextText.getBaseline();
      i8 = Math.max(Math.max(i7, i6), i5);
      i7 = i8 - i7;
      i6 = i8 - i6;
      i5 = i8 - i5;
      i8 = mPrevText.getMeasuredHeight();
      int i9 = mCurrText.getMeasuredHeight();
      int i10 = mNextText.getMeasuredHeight();
      i8 = Math.max(Math.max(i8 + i7, i9 + i6), i10 + i5);
      switch (mGravity & 0x70)
      {
      default: 
        j = paramInt + i7;
        i = i6 + paramInt;
        paramInt += i5;
      }
    }
    for (;;)
    {
      mCurrText.layout(i3, i, i4, mCurrText.getMeasuredHeight() + i);
      i = Math.min(i2, i3 - mScaledTextSpacing - m);
      mPrevText.layout(i, j, m + i, mPrevText.getMeasuredHeight() + j);
      i = Math.max(n - i1 - k, mScaledTextSpacing + i4);
      mNextText.layout(i, paramInt, i + k, mNextText.getMeasuredHeight() + paramInt);
      mLastKnownPositionOffset = paramFloat;
      mUpdatingPositions = false;
      return;
      if ((paramBoolean) || (paramFloat != mLastKnownPositionOffset)) {
        break;
      }
      return;
      paramInt = (i - paramInt - j - i8) / 2;
      j = paramInt + i7;
      i = i6 + paramInt;
      paramInt += i5;
      continue;
      paramInt = i - j - i8;
      j = paramInt + i7;
      i = i6 + paramInt;
      paramInt += i5;
    }
  }
  
  class PageListener
    extends DataSetObserver
    implements ViewPager.OnAdapterChangeListener, ViewPager.OnPageChangeListener
  {
    private int mScrollState;
    
    private PageListener() {}
    
    public void onAdapterChanged(PagerAdapter paramPagerAdapter1, PagerAdapter paramPagerAdapter2)
    {
      updateAdapter(paramPagerAdapter1, paramPagerAdapter2);
    }
    
    public void onChanged()
    {
      float f = 0.0F;
      updateText(mPager.getCurrentItem(), mPager.getAdapter());
      if (mLastKnownPositionOffset >= 0.0F) {
        f = mLastKnownPositionOffset;
      }
      updateTextPositions(mPager.getCurrentItem(), f, true);
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
      updateTextPositions(paramInt2, paramFloat, false);
    }
    
    public void onPageSelected(int paramInt)
    {
      float f = 0.0F;
      if (mScrollState == 0)
      {
        updateText(mPager.getCurrentItem(), mPager.getAdapter());
        if (mLastKnownPositionOffset >= 0.0F) {
          f = mLastKnownPositionOffset;
        }
        updateTextPositions(mPager.getCurrentItem(), f, true);
      }
    }
  }
  
  static abstract interface PagerTitleStripImpl
  {
    public abstract void setSingleLineAllCaps(TextView paramTextView);
  }
  
  static class PagerTitleStripImplBase
    implements PagerTitleStrip.PagerTitleStripImpl
  {
    public void setSingleLineAllCaps(TextView paramTextView)
    {
      paramTextView.setSingleLine();
    }
  }
  
  static class PagerTitleStripImplIcs
    implements PagerTitleStrip.PagerTitleStripImpl
  {
    public void setSingleLineAllCaps(TextView paramTextView)
    {
      PagerTitleStripIcs.setSingleLineAllCaps(paramTextView);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.PagerTitleStrip
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */