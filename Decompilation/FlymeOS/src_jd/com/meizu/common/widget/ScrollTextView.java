package com.meizu.common.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import com.meizu.common.R.color;
import com.meizu.common.R.dimen;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ScrollTextView
  extends View
{
  private static final int DEF_VISIBLE_ITEMS = 5;
  private static final int DEF_YSCROLL_END = Integer.MAX_VALUE;
  private static String TAG = "ScrollTextView";
  boolean isCyclic = false;
  private boolean isScrollingPerformed;
  private Paint mBitmapPaint;
  private List<OnScrollTextViewChangedListener> mChangingListeners = new LinkedList();
  private List<OnScrollTextViewClickedListener> mClickingListeners = new LinkedList();
  private int mCurrentItem = 0;
  private IDataAdapter mDataInterface;
  private float mFadingHeight;
  private Matrix mFadingMatrix;
  private Paint mFadingPaint;
  private Shader mFadingShader;
  private int mFirstItem;
  private float mNormalItemHeight = 0.0F;
  private int mNormalTextColor;
  private float mNormalTextSize;
  private int mOffsetX;
  private int mOffsetY;
  private VisibleItemsRange mRange;
  private ScrollingListener mScrollingListener = new ScrollTextView.1(this);
  private List<OnScrollTextViewScrollListener> mScrollingListeners = new LinkedList();
  private int mScrollingOffset;
  private float mSelectItemHeight = 0.0F;
  private int mSelectTextColor;
  private float mSelectTextSize;
  private Paint mTextPaint = new Paint(1);
  private Bitmap mTmpBitmap;
  private Canvas mTmpCanvas = new Canvas();
  private ScrollTextViewAdapter mViewAdapter;
  private int mVisibleItems = 5;
  private ScrollTextViewScroller mWheelScroller;
  
  public ScrollTextView(Context paramContext)
  {
    super(paramContext);
    initData(paramContext);
  }
  
  public ScrollTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public ScrollTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initData(paramContext);
  }
  
  private void computeTextSizeAndColor(int paramInt, float paramFloat)
  {
    int m = mSelectTextColor;
    int k = mNormalTextColor;
    paramInt = Color.alpha(m);
    int i = Color.red(m);
    int j = Color.green(m);
    m = Color.blue(m);
    int n = Color.alpha(k);
    int i1 = Color.red(k);
    int i2 = Color.green(k);
    k = Color.blue(k);
    paramInt = Color.argb((int)((paramInt - n) * paramFloat) + n, (int)((i - i1) * paramFloat) + i1, (int)((j - i2) * paramFloat) + i2, (int)((m - k) * paramFloat) + k);
    mTextPaint.setColor(paramInt);
    mTextPaint.setTextSize(mNormalTextSize + (mSelectTextSize - mNormalTextSize) * paramFloat);
  }
  
  private float configTextView(int paramInt, float paramFloat)
  {
    float f1 = getItemHeight();
    int i = (int)(mSelectItemHeight - mNormalItemHeight);
    int j = mVisibleItems / 2;
    float f2;
    if (paramInt == j)
    {
      f2 = i * paramFloat / 2.0F + f1;
      f1 = paramFloat;
      paramFloat = f2;
    }
    for (;;)
    {
      computeTextSizeAndColor(paramInt, f1);
      return paramFloat;
      if (paramInt == j + 1)
      {
        f1 = i / 2 + f1;
        f2 = 1.0F - paramFloat;
        paramFloat = f1;
        f1 = f2;
      }
      else if (paramInt == j + 2)
      {
        paramFloat = i * (1.0F - paramFloat) / 2.0F;
        f2 = 0.0F;
        paramFloat = f1 + paramFloat;
        f1 = f2;
      }
      else
      {
        f2 = 0.0F;
        paramFloat = f1;
        f1 = f2;
      }
    }
  }
  
  private void doScroll(int paramInt)
  {
    mScrollingOffset += paramInt;
    int m = getItemHeight();
    paramInt = mScrollingOffset / m;
    int i = mCurrentItem - paramInt;
    int n = mViewAdapter.getItemsCount();
    int k = mScrollingOffset % m;
    int j = k;
    if (Math.abs(k) <= m / 2) {
      j = 0;
    }
    if ((isCyclic) && (n > 0)) {
      if (j > 0)
      {
        j = i - 1;
        i = paramInt + 1;
        paramInt = j;
      }
    }
    for (;;)
    {
      if (paramInt < 0)
      {
        paramInt += n;
        continue;
        if (j < 0)
        {
          i += 1;
          j = paramInt - 1;
          paramInt = i;
          i = j;
        }
      }
      else
      {
        paramInt %= n;
        j = mScrollingOffset;
        if (paramInt != mCurrentItem) {
          setCurrentItem(paramInt, false);
        }
        for (;;)
        {
          mScrollingOffset = (j - i * m);
          if ((mScrollingOffset > getHeight()) && (getHeight() != 0)) {
            mScrollingOffset = (mScrollingOffset % getHeight() + getHeight());
          }
          return;
          if (i < getScrollStartItem())
          {
            i = mCurrentItem - getScrollStartItem();
            paramInt = getScrollStartItem();
            mScrollingOffset = 0;
            break;
          }
          if (i > getScrollEndItem())
          {
            i = mCurrentItem - getScrollEndItem();
            paramInt = getScrollEndItem();
            mScrollingOffset = 0;
            break;
          }
          if ((i > getScrollStartItem()) && (j > 0))
          {
            i -= 1;
            j = paramInt + 1;
            Log.i(TAG, "pos > 0 && fixPos > 0");
            paramInt = i;
            i = j;
            break;
          }
          if ((i < getScrollEndItem()) && (j < 0))
          {
            i += 1;
            j = paramInt - 1;
            paramInt = i;
            i = j;
            break;
          }
          if (i == getScrollEndItem())
          {
            if (mScrollingOffset >= 0) {
              break label368;
            }
            mScrollingOffset = 0;
            j = paramInt;
            paramInt = i;
            i = j;
            break;
          }
          if ((i == getScrollStartItem()) && (mScrollingOffset > 0)) {
            mScrollingOffset = 0;
          }
          label368:
          j = paramInt;
          paramInt = i;
          i = j;
          break;
          invalidate();
        }
      }
      j = paramInt;
      paramInt = i;
      i = j;
    }
  }
  
  private void drawItems(Canvas paramCanvas)
  {
    float f1 = -((mCurrentItem - mFirstItem) * getItemHeight() + ((int)mSelectItemHeight - getHeight()) / 2) + mScrollingOffset - getItemHeight();
    paramCanvas.translate(mOffsetX, f1);
    if (mScrollingOffset > 0) {}
    for (int i = mScrollingOffset;; i = getItemHeight() + mScrollingOffset)
    {
      float f2 = i * 1.0F / getItemHeight();
      i = 0;
      while (i < mRange.getCount())
      {
        float f3 = configTextView(i, f2);
        paramCanvas.translate(0.0F, f3);
        f1 += f3;
        String str = getItemText(i);
        f3 = mTextPaint.measureText(str);
        Paint.FontMetricsInt localFontMetricsInt = mTextPaint.getFontMetricsInt();
        float f4 = (mNormalItemHeight - bottom + top) / 2.0F;
        float f5 = top;
        paramCanvas.drawText(str, (getWidth() - f3) / 2.0F, f4 - f5, mTextPaint);
        i += 1;
      }
    }
    paramCanvas.translate(-mOffsetX, -f1);
  }
  
  private void drawVerticalFading(Canvas paramCanvas)
  {
    mFadingMatrix.setScale(1.0F, mFadingHeight);
    mFadingShader.setLocalMatrix(mFadingMatrix);
    paramCanvas.drawRect(0.0F, 0.0F, getWidth(), mFadingHeight, mFadingPaint);
    mFadingMatrix.setScale(1.0F, mFadingHeight);
    mFadingMatrix.postRotate(180.0F);
    mFadingMatrix.postTranslate(0.0F, getHeight());
    mFadingShader.setLocalMatrix(mFadingMatrix);
    paramCanvas.drawRect(0.0F, getHeight() - mFadingHeight, getWidth(), getHeight(), mFadingPaint);
  }
  
  private Bitmap getBitmap(int paramInt1, int paramInt2)
  {
    if (mTmpBitmap == null) {}
    for (mTmpBitmap = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);; mTmpBitmap = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888))
    {
      do
      {
        mTmpBitmap.eraseColor(0);
        return mTmpBitmap;
      } while ((mTmpBitmap.getWidth() == paramInt1) && (mTmpBitmap.getHeight() == paramInt2));
      mTmpBitmap.recycle();
    }
  }
  
  private int getItemHeight()
  {
    return (int)mNormalItemHeight;
  }
  
  private String getItemText(int paramInt)
  {
    paramInt += mFirstItem;
    String str1 = mViewAdapter.getItemText(paramInt);
    int i;
    if (paramInt < 0)
    {
      i = mViewAdapter.getItemsCount();
      if (isCyclic) {
        str1 = mViewAdapter.getItemText(i + paramInt);
      }
    }
    for (;;)
    {
      String str2 = str1;
      if (str1 == null) {
        str2 = "";
      }
      return str2;
      str1 = "";
      continue;
      if (paramInt >= mViewAdapter.getItemsCount())
      {
        i = mViewAdapter.getItemsCount();
        if (isCyclic) {
          str1 = mViewAdapter.getItemText(paramInt - i);
        } else {
          str1 = "";
        }
      }
    }
  }
  
  private VisibleItemsRange getItemsRange()
  {
    if (getItemHeight() == 0) {
      return null;
    }
    int i = mCurrentItem;
    int j = 1;
    while ((j + 2) * getItemHeight() < getHeight())
    {
      i -= 1;
      j += 2;
    }
    int m = j;
    int k = i;
    if (mScrollingOffset != 0)
    {
      k = i;
      if (mScrollingOffset > 0) {
        k = i - 1;
      }
      i = mScrollingOffset / getItemHeight();
      k -= i;
      double d = j + 1;
      m = (int)(Math.asin(i) + d);
    }
    return mRange.update(k, m);
  }
  
  private int getScrollEndItem()
  {
    int i = mViewAdapter.getItemsCount();
    if (isCyclic) {
      return 0;
    }
    if (i <= mVisibleItems) {
      return i - 1;
    }
    return i - 1 - mVisibleItems / 2;
  }
  
  private int getScrollStartItem()
  {
    int i = mViewAdapter.getItemsCount();
    if (isCyclic) {}
    while (i <= mVisibleItems) {
      return 0;
    }
    return mVisibleItems / 2;
  }
  
  private int getYScrollEnd()
  {
    if (isCyclic) {
      return Integer.MAX_VALUE;
    }
    return mScrollingOffset + (int)((getScrollEndItem() - getCurrentItem()) * mNormalItemHeight);
  }
  
  private int getYScrollStart()
  {
    if (isCyclic) {
      return -2147483647;
    }
    return mScrollingOffset + (int)((getScrollStartItem() - getCurrentItem()) * mNormalItemHeight);
  }
  
  private void initData(Context paramContext)
  {
    mWheelScroller = new ScrollTextViewScroller(getContext(), mScrollingListener);
    mSelectTextSize = paramContext.getResources().getDimension(R.dimen.mc_picker_selected_number_size);
    mNormalTextSize = paramContext.getResources().getDimension(R.dimen.mc_picker_normal_number_size);
    mSelectItemHeight = paramContext.getResources().getDimension(R.dimen.mc_picker_scroll_select_item_height);
    mNormalItemHeight = paramContext.getResources().getDimension(R.dimen.mc_picker_scroll_normal_item_height);
    mSelectTextColor = paramContext.getResources().getColor(R.color.mc_picker_selected_color);
    mNormalTextColor = paramContext.getResources().getColor(R.color.mc_picker_unselected_color);
    mViewAdapter = new ScrollTextViewAdapter();
    mFadingMatrix = new Matrix();
    mFadingShader = new LinearGradient(0.0F, 0.0F, 0.0F, 1.0F, -16777216, 0, Shader.TileMode.CLAMP);
    mFadingPaint = new Paint();
    mFadingPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    mFadingPaint.setShader(mFadingShader);
    mFadingHeight = paramContext.getResources().getDimension(R.dimen.mc_picker_fading_height);
    mRange = new VisibleItemsRange();
    mBitmapPaint = new Paint();
    mBitmapPaint.setAntiAlias(true);
    mBitmapPaint.setColor(-3355444);
  }
  
  private boolean isValidItemIndex(int paramInt)
  {
    return (mViewAdapter != null) && (mViewAdapter.getItemsCount() > 0) && ((isCyclic) || ((paramInt >= 0) && (paramInt < mViewAdapter.getItemsCount())));
  }
  
  private boolean rebuildItems()
  {
    mRange = getItemsRange();
    int i;
    if ((mFirstItem > mRange.getFirst()) && (mFirstItem <= mRange.getLast())) {
      i = mFirstItem - 1;
    }
    while (i >= mRange.getFirst())
    {
      mFirstItem = i;
      i -= 1;
      continue;
      mFirstItem = mRange.getFirst();
    }
    return false;
  }
  
  private void setViewAdapter(ScrollTextViewAdapter paramScrollTextViewAdapter)
  {
    mViewAdapter = paramScrollTextViewAdapter;
    invalidate();
  }
  
  public void addChangingListener(OnScrollTextViewChangedListener paramOnScrollTextViewChangedListener)
  {
    mChangingListeners.add(paramOnScrollTextViewChangedListener);
  }
  
  public void addClickingListener(OnScrollTextViewClickedListener paramOnScrollTextViewClickedListener)
  {
    mClickingListeners.add(paramOnScrollTextViewClickedListener);
  }
  
  public void addScrollingListener(OnScrollTextViewScrollListener paramOnScrollTextViewScrollListener)
  {
    mScrollingListeners.add(paramOnScrollTextViewScrollListener);
  }
  
  public int getCurrentItem()
  {
    return mCurrentItem;
  }
  
  public IDataAdapter getIDataAdapter()
  {
    return mDataInterface;
  }
  
  public int getItemsCount()
  {
    return mViewAdapter.getItemsCount();
  }
  
  public ScrollTextViewAdapter getViewAdapter()
  {
    return mViewAdapter;
  }
  
  public int getVisibleItems()
  {
    return mVisibleItems;
  }
  
  public boolean isCyclic()
  {
    return isCyclic;
  }
  
  protected void notifyChangingListeners(int paramInt1, int paramInt2)
  {
    Iterator localIterator = mChangingListeners.iterator();
    while (localIterator.hasNext()) {
      ((OnScrollTextViewChangedListener)localIterator.next()).onChanged(this, paramInt1, paramInt2);
    }
  }
  
  protected void notifyClickListenersAboutClick(int paramInt)
  {
    setCurrentItem(paramInt, true);
    Iterator localIterator = mClickingListeners.iterator();
    while (localIterator.hasNext()) {
      ((OnScrollTextViewClickedListener)localIterator.next()).onItemClicked(this, paramInt);
    }
  }
  
  protected void notifyScrollingListenersAboutEnd()
  {
    if (mDataInterface != null) {
      mDataInterface.onChanged(this, 0, getCurrentItem());
    }
    Iterator localIterator = mScrollingListeners.iterator();
    while (localIterator.hasNext()) {
      ((OnScrollTextViewScrollListener)localIterator.next()).onScrollingFinished(this);
    }
  }
  
  protected void notifyScrollingListenersAboutStart()
  {
    Iterator localIterator = mScrollingListeners.iterator();
    while (localIterator.hasNext()) {
      ((OnScrollTextViewScrollListener)localIterator.next()).onScrollingStarted(this);
    }
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    Bitmap localBitmap = getBitmap(getWidth(), getHeight());
    mTmpCanvas.setBitmap(localBitmap);
    if ((mViewAdapter != null) && (mViewAdapter.getItemsCount() > 0))
    {
      rebuildItems();
      drawItems(mTmpCanvas);
    }
    drawVerticalFading(mTmpCanvas);
    paramCanvas.drawBitmap(localBitmap, 0.0F, 0.0F, mBitmapPaint);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(paramInt1, (int)((mVisibleItems - 1) * mNormalItemHeight + mSelectItemHeight));
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((!isEnabled()) || (getViewAdapter() == null)) {
      return true;
    }
    switch (paramMotionEvent.getAction())
    {
    }
    do
    {
      for (;;)
      {
        return mWheelScroller.onTouchEvent(paramMotionEvent);
        if (getParent() != null) {
          getParent().requestDisallowInterceptTouchEvent(true);
        }
      }
    } while (isScrollingPerformed);
    int i = (int)paramMotionEvent.getY() - getHeight() / 2;
    if (i < 0) {}
    for (i = (int)(i + (mSelectItemHeight / 2.0F - getItemHeight()));; i = (int)(i - (mSelectItemHeight / 2.0F - getItemHeight())))
    {
      i /= getItemHeight();
      if ((i == 0) || (!isValidItemIndex(mCurrentItem + i))) {
        break;
      }
      notifyClickListenersAboutClick(i + mCurrentItem);
      break;
    }
  }
  
  protected void onVisibilityChanged(View paramView, int paramInt)
  {
    super.onVisibilityChanged(paramView, paramInt);
    if ((4 == paramInt) && (mTmpBitmap != null)) {
      mTmpBitmap = null;
    }
  }
  
  public void refreshCount(int paramInt)
  {
    refreshData(paramInt, mCurrentItem, 0, paramInt - 1);
  }
  
  public void refreshCountAndCurrent(int paramInt1, int paramInt2)
  {
    refreshData(paramInt1, paramInt2, 0, paramInt1 - 1);
  }
  
  public void refreshCurrent(int paramInt)
  {
    refreshData(mViewAdapter.getItemsCount(), paramInt, mViewAdapter.getValidStart(), mViewAdapter.getValidEnd());
  }
  
  void refreshData(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    stopScrolling();
    if (paramInt1 < 0) {
      return;
    }
    setViewAdapter(mViewAdapter.update(paramInt1, paramInt3, paramInt4));
    int i = mCurrentItem;
    if (paramInt2 != mCurrentItem)
    {
      mCurrentItem = Math.max(paramInt2, paramInt3);
      if ((mCurrentItem > paramInt4) || (mCurrentItem >= paramInt1)) {
        mCurrentItem = Math.min(paramInt4, paramInt1);
      }
    }
    if ((i != mCurrentItem) && (mDataInterface != null)) {
      mDataInterface.onChanged(this, i, mCurrentItem);
    }
    invalidate();
  }
  
  public void removeChangingListener(OnScrollTextViewChangedListener paramOnScrollTextViewChangedListener)
  {
    mChangingListeners.remove(paramOnScrollTextViewChangedListener);
  }
  
  public void removeClickingListener(OnScrollTextViewClickedListener paramOnScrollTextViewClickedListener)
  {
    mClickingListeners.remove(paramOnScrollTextViewClickedListener);
  }
  
  public void removeScrollingListener(OnScrollTextViewScrollListener paramOnScrollTextViewScrollListener)
  {
    mScrollingListeners.remove(paramOnScrollTextViewScrollListener);
  }
  
  public void scroll(int paramInt1, int paramInt2)
  {
    int i = getItemHeight();
    int j = mScrollingOffset;
    mWheelScroller.scroll(i * paramInt1 + j, paramInt2);
  }
  
  public void setCurrentItem(int paramInt)
  {
    setCurrentItem(paramInt, false);
  }
  
  public void setCurrentItem(int paramInt, boolean paramBoolean)
  {
    if ((mViewAdapter == null) || (mViewAdapter.getItemsCount() == 0)) {}
    int j;
    int i;
    do
    {
      do
      {
        return;
        j = mViewAdapter.getItemsCount();
        if (paramInt >= 0)
        {
          i = paramInt;
          if (paramInt < j) {
            break;
          }
        }
      } while (!isCyclic);
      while (paramInt < 0) {
        paramInt += j;
      }
      i = paramInt % j;
    } while (i == mCurrentItem);
    if (paramBoolean)
    {
      paramInt = i - mCurrentItem;
      if (!isCyclic) {
        break label166;
      }
      i = j + Math.min(i, mCurrentItem) - Math.max(i, mCurrentItem);
      if (i >= Math.abs(paramInt)) {
        break label166;
      }
      if (paramInt < 0) {
        paramInt = i;
      }
    }
    label166:
    for (;;)
    {
      scroll(paramInt, 0);
      return;
      paramInt = -i;
      continue;
      mScrollingOffset = 0;
      paramInt = mCurrentItem;
      mCurrentItem = i;
      notifyChangingListeners(paramInt, mCurrentItem);
      invalidate();
      return;
    }
  }
  
  public void setCyclic(boolean paramBoolean)
  {
    isCyclic = paramBoolean;
    invalidate();
  }
  
  public void setData(IDataAdapter paramIDataAdapter, float paramFloat, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean)
  {
    setIDataAdapter(paramIDataAdapter);
    mVisibleItems = paramInt3;
    isCyclic = paramBoolean;
    if (paramFloat == -1.0F) {}
    for (mOffsetY = getResources().getDimensionPixelSize(R.dimen.mc_picker_offset_y);; mOffsetY = ((int)(getContextgetResourcesgetDisplayMetricsdensity * paramFloat)))
    {
      if ((paramInt2 < mVisibleItems) || (paramInt5 + 1 < paramInt2) || (paramInt4 > 0)) {
        isCyclic = false;
      }
      refreshData(paramInt2, paramInt1, paramInt4, paramInt5);
      return;
    }
  }
  
  public void setData(IDataAdapter paramIDataAdapter, int paramInt1, int paramInt2, int paramInt3)
  {
    setData(paramIDataAdapter, -1.0F, paramInt1, paramInt2, paramInt3, 0, paramInt2 - 1, true);
  }
  
  public void setHorizontalOffset(int paramInt)
  {
    mOffsetX = paramInt;
  }
  
  public void setIDataAdapter(IDataAdapter paramIDataAdapter)
  {
    mDataInterface = paramIDataAdapter;
  }
  
  public void setInterpolator(Interpolator paramInterpolator)
  {
    mWheelScroller.setInterpolator(paramInterpolator);
  }
  
  public void setItemHeight(float paramFloat1, float paramFloat2)
  {
    setTextPreference(mSelectTextSize, mNormalTextSize, paramFloat1, paramFloat2);
  }
  
  public void setNormalItemHeight(float paramFloat)
  {
    setTextPreference(mSelectTextSize, mNormalTextSize, mSelectItemHeight, paramFloat);
  }
  
  public void setSelectItemHeight(float paramFloat)
  {
    setTextPreference(mSelectTextSize, mNormalTextSize, paramFloat, mNormalItemHeight);
  }
  
  public void setTextColor(int paramInt1, int paramInt2)
  {
    if ((mSelectTextColor == paramInt1) && (mNormalTextColor == paramInt2)) {
      return;
    }
    mSelectTextColor = paramInt1;
    mNormalTextColor = paramInt2;
    invalidate();
  }
  
  public void setTextPreference(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    if ((mSelectItemHeight == paramFloat3) && (mNormalItemHeight == paramFloat4) && (mSelectTextSize == paramFloat1) && (mNormalTextSize == paramFloat2)) {
      return;
    }
    mSelectItemHeight = paramFloat3;
    mNormalItemHeight = paramFloat4;
    mSelectTextSize = paramFloat1;
    mNormalTextSize = paramFloat2;
    invalidate();
  }
  
  public void setTextSize(float paramFloat1, float paramFloat2)
  {
    setTextPreference(paramFloat1, paramFloat2, mSelectItemHeight, mNormalItemHeight);
  }
  
  public void setTypeface(Typeface paramTypeface)
  {
    mTextPaint.setTypeface(paramTypeface);
    invalidate();
  }
  
  public void setVisibleItems(int paramInt)
  {
    mVisibleItems = paramInt;
  }
  
  public void stopScrolling()
  {
    if (mWheelScroller != null) {
      mWheelScroller.stopScrolling();
    }
  }
  
  static class AnimationHandler
    extends Handler
  {
    private final WeakReference<ScrollTextView.ScrollTextViewScroller> mScrollTextViewScroller;
    
    public AnimationHandler(ScrollTextView.ScrollTextViewScroller paramScrollTextViewScroller)
    {
      mScrollTextViewScroller = new WeakReference(paramScrollTextViewScroller);
    }
    
    public void handleMessage(Message paramMessage)
    {
      ScrollTextView.ScrollTextViewScroller localScrollTextViewScroller = (ScrollTextView.ScrollTextViewScroller)mScrollTextViewScroller.get();
      if (localScrollTextViewScroller != null)
      {
        ScrollTextView.ScrollTextViewScroller.access$900(localScrollTextViewScroller).computeScrollOffset();
        int i = ScrollTextView.ScrollTextViewScroller.access$900(localScrollTextViewScroller).getCurrY();
        int j = ScrollTextView.ScrollTextViewScroller.access$600(localScrollTextViewScroller) - i;
        ScrollTextView.ScrollTextViewScroller.access$602(localScrollTextViewScroller, i);
        if (j != 0) {
          ScrollTextView.ScrollTextViewScroller.access$1100(localScrollTextViewScroller).onScroll(j);
        }
        if (Math.abs(i - ScrollTextView.ScrollTextViewScroller.access$900(localScrollTextViewScroller).getFinalY()) < 1)
        {
          ScrollTextView.ScrollTextViewScroller.access$900(localScrollTextViewScroller).getFinalY();
          ScrollTextView.ScrollTextViewScroller.access$900(localScrollTextViewScroller).forceFinished(true);
        }
        if (!ScrollTextView.ScrollTextViewScroller.access$900(localScrollTextViewScroller).isFinished()) {
          ScrollTextView.ScrollTextViewScroller.access$1200(localScrollTextViewScroller).sendEmptyMessage(what);
        }
      }
      else
      {
        return;
      }
      if (what == 0)
      {
        ScrollTextView.ScrollTextViewScroller.access$1300(localScrollTextViewScroller);
        return;
      }
      localScrollTextViewScroller.finishScrolling();
    }
  }
  
  public static abstract interface IDataAdapter
  {
    public abstract String getItemText(int paramInt);
    
    public abstract void onChanged(View paramView, int paramInt1, int paramInt2);
  }
  
  public static abstract interface OnScrollTextViewChangedListener
  {
    public abstract void onChanged(ScrollTextView paramScrollTextView, int paramInt1, int paramInt2);
  }
  
  public static abstract interface OnScrollTextViewClickedListener
  {
    public abstract void onItemClicked(ScrollTextView paramScrollTextView, int paramInt);
  }
  
  public static abstract interface OnScrollTextViewScrollListener
  {
    public abstract void onScrollingFinished(ScrollTextView paramScrollTextView);
    
    public abstract void onScrollingStarted(ScrollTextView paramScrollTextView);
  }
  
  class ScrollTextViewAdapter
  {
    public static final int DEFAULT_MAX_VALUE = 9;
    private static final int DEFAULT_MIN_VALUE = 0;
    private int count = 0;
    private int validEnd = 0;
    private int validStart = 0;
    
    public ScrollTextViewAdapter()
    {
      this(10, 0, 9);
    }
    
    public ScrollTextViewAdapter(int paramInt1, int paramInt2)
    {
      this(paramInt2 - paramInt1 + 1, paramInt1, paramInt2);
    }
    
    public ScrollTextViewAdapter(int paramInt1, int paramInt2, int paramInt3)
    {
      update(paramInt1, paramInt2, paramInt3);
    }
    
    public String getItemText(int paramInt)
    {
      if ((paramInt >= 0) && (paramInt < getItemsCount()) && (mDataInterface != null)) {
        return mDataInterface.getItemText(paramInt);
      }
      return null;
    }
    
    public int getItemsCount()
    {
      return count;
    }
    
    public int getValidEnd()
    {
      return validEnd;
    }
    
    public int getValidStart()
    {
      return validStart;
    }
    
    public void setItemCount(int paramInt)
    {
      count = paramInt;
    }
    
    public ScrollTextViewAdapter update(int paramInt1, int paramInt2, int paramInt3)
    {
      validStart = paramInt2;
      validEnd = paramInt3;
      count = paramInt1;
      return this;
    }
  }
  
  class ScrollTextViewScroller
  {
    public static final int MIN_DELTA_FOR_SCROLLING = 1;
    private static final int SCROLLING_DURATION = 400;
    private final int MESSAGE_JUSTIFY = 1;
    private final int MESSAGE_SCROLL = 0;
    private Handler animationHandler = new ScrollTextView.AnimationHandler(this);
    private Context context;
    private GestureDetector gestureDetector = new GestureDetector(paramContext, gestureListener);
    private GestureDetector.SimpleOnGestureListener gestureListener = new ScrollTextView.ScrollTextViewScroller.1(this);
    private boolean isScrollingPerformed;
    private int lastScrollY;
    private float lastTouchedY;
    private ScrollTextView.ScrollingListener listener;
    private Scroller scroller;
    
    public ScrollTextViewScroller(Context paramContext, ScrollTextView.ScrollingListener paramScrollingListener)
    {
      gestureDetector.setIsLongpressEnabled(false);
      scroller = new Scroller(paramContext);
      listener = paramScrollingListener;
      context = paramContext;
    }
    
    private void clearMessages()
    {
      animationHandler.removeMessages(0);
      animationHandler.removeMessages(1);
    }
    
    private void justify()
    {
      listener.onJustify();
      setNextMessage(1);
    }
    
    private void setNextMessage(int paramInt)
    {
      clearMessages();
      animationHandler.sendEmptyMessage(paramInt);
    }
    
    private void startScrolling()
    {
      if (!isScrollingPerformed)
      {
        isScrollingPerformed = true;
        listener.onStarted();
      }
    }
    
    void finishScrolling()
    {
      if (isScrollingPerformed)
      {
        listener.onFinished();
        isScrollingPerformed = false;
      }
    }
    
    public boolean onTouchEvent(MotionEvent paramMotionEvent)
    {
      switch (paramMotionEvent.getAction())
      {
      }
      for (;;)
      {
        if ((!gestureDetector.onTouchEvent(paramMotionEvent)) && (paramMotionEvent.getAction() == 1)) {
          justify();
        }
        return true;
        lastTouchedY = paramMotionEvent.getY();
        scroller.forceFinished(true);
        clearMessages();
        continue;
        int i = (int)(paramMotionEvent.getY() - lastTouchedY);
        if (i != 0)
        {
          startScrolling();
          listener.onScroll(i);
          lastTouchedY = paramMotionEvent.getY();
        }
      }
    }
    
    public void scroll(int paramInt1, int paramInt2)
    {
      scroller.forceFinished(true);
      lastScrollY = 0;
      Scroller localScroller = scroller;
      if (paramInt2 != 0) {}
      for (;;)
      {
        localScroller.startScroll(0, 0, 0, paramInt1, paramInt2);
        setNextMessage(0);
        startScrolling();
        return;
        paramInt2 = 400;
      }
    }
    
    public void setInterpolator(Interpolator paramInterpolator)
    {
      scroller.forceFinished(true);
      scroller = new Scroller(context, paramInterpolator);
    }
    
    public void stopScrolling()
    {
      scroller.forceFinished(true);
    }
  }
  
  static abstract interface ScrollingListener
  {
    public abstract void onFinished();
    
    public abstract void onJustify();
    
    public abstract void onScroll(int paramInt);
    
    public abstract void onStarted();
  }
  
  class VisibleItemsRange
  {
    private int count;
    private int first;
    
    public VisibleItemsRange()
    {
      this(0, 0);
    }
    
    public VisibleItemsRange(int paramInt1, int paramInt2)
    {
      first = paramInt1;
      count = paramInt2;
    }
    
    public int getCount()
    {
      return count;
    }
    
    public int getFirst()
    {
      return first;
    }
    
    public int getLast()
    {
      return getFirst() + getCount() - 1;
    }
    
    public VisibleItemsRange update(int paramInt1, int paramInt2)
    {
      first = paramInt1;
      count = paramInt2;
      return this;
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.ScrollTextView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */