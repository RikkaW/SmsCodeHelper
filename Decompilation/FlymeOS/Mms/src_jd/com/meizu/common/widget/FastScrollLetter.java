package com.meizu.common.widget;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroupOverlay;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.widget.AbsListView;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.meizu.common.R.attr;
import com.meizu.common.R.color;
import com.meizu.common.R.dimen;
import com.meizu.common.R.drawable;
import com.meizu.common.R.layout;
import com.meizu.common.R.styleable;

@SuppressLint({"ViewConstructor"})
@TargetApi(18)
public class FastScrollLetter
  extends View
{
  public static final int SECTION_COMPARE_TYPE1 = 1;
  public static final int SECTION_COMPARE_TYPE2 = 2;
  private static final int STATE_DRAGGING = 1;
  private static final int STATE_NONE = 0;
  private static final String TAG = FastScrollLetter.class.getSimpleName();
  private static final String[] mDefaultLetters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
  private AbsListView mAbsListView;
  private String mCurrentLetter;
  private int mCurrentShowPos = -1;
  private int mDefaultSectionType = 2;
  private int mEventDownY = 0;
  private int mEventMoveY = 0;
  private int mHeaderCount = 0;
  private int mHeaderHeight = 53;
  private SparseArray<Integer> mHideLetteSparseArray;
  private int mHideNum = 0;
  private String mHideStr = "";
  private boolean mIsAlwayShowLetter = false;
  private boolean mIsEnable = true;
  private int mLetterActiveTextColor;
  private int mLetterMarginBottom = 0;
  private int mLetterMarginRight = 0;
  private int mLetterMarginTop = 0;
  private int mLetterTextColor;
  private int mLetterTextSize = 20;
  private int mLetterWidth = 24;
  private String[] mLetters;
  private ViewGroupOverlay mOverlay;
  private int mOverlayOneTextSize = 0;
  private TextView mOverlayText;
  private int mOverlayTextHeight = 0;
  private String[] mOverlayTextLetters;
  private int mOverlayTextMarginRight = 220;
  private int mOverlayTextTop = 0;
  private int mOverlayTextWidth = 0;
  private int mOverlayThreeTextSize = 0;
  private int mOverlayTwoTextSize = 0;
  private int mPaddingLeft = 0;
  Paint mPaint = new Paint();
  private Bitmap mPointBitmap = null;
  private SectionCompare mSectionCompare;
  private SectionIndexer mSectionIndexer;
  private String[] mShowLetters;
  private int mSingleLetterHeight = 0;
  private int mState;
  private String mTopLetter = "";
  
  public FastScrollLetter(Context paramContext, AbsListView paramAbsListView)
  {
    this(paramContext, paramAbsListView, (TextView)LayoutInflater.from(paramContext).inflate(R.layout.mc_letter_overlay, null));
  }
  
  public FastScrollLetter(Context paramContext, AbsListView paramAbsListView, int paramInt)
  {
    this(paramContext, paramAbsListView, (TextView)LayoutInflater.from(paramContext).inflate(R.layout.mc_letter_overlay, null));
    mDefaultSectionType = paramInt;
  }
  
  public FastScrollLetter(Context paramContext, AbsListView paramAbsListView, TextView paramTextView)
  {
    super(paramContext, null, R.attr.MeizuCommon_FastScrollLetter);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(null, R.styleable.FastScrollLetter, R.attr.MeizuCommon_FastScrollLetter, 0);
    Drawable localDrawable = localTypedArray.getDrawable(R.styleable.FastScrollLetter_mcFastScrollLetter);
    if (localDrawable != null) {
      paramTextView.setBackground(localDrawable);
    }
    if (paramAbsListView != null)
    {
      mAbsListView = paramAbsListView;
      mOverlay = paramAbsListView.getOverlay();
      mOverlay.add(this);
      mOverlayOneTextSize = getPxSize(paramContext, R.dimen.mc_fastscroll_letter_overlay_text_size);
      mOverlayTwoTextSize = getPxSize(paramContext, R.dimen.mc_fastscroll_letter_overlay_two_text_size);
      mOverlayThreeTextSize = getPxSize(paramContext, R.dimen.mc_fastscroll_letter_overlay_three_text_size);
      int i = getPxSize(paramContext, R.dimen.mc_fastscroll_letter_overlay_layout_width);
      mOverlayTextHeight = i;
      mOverlayTextWidth = i;
      mOverlayTextMarginRight = getPxSize(paramContext, R.dimen.mc_fastscroll_letter_overlay_layout_margin_right);
      mOverlayTextTop = (-mOverlayTextHeight / 2);
      mLetterTextSize = getPxSize(paramContext, R.dimen.mc_fastscroll_letter_text_size);
      mLetterTextColor = paramContext.getResources().getColor(R.color.mc_fastscroll_letter_text_color);
      mLetterMarginTop = getPxSize(paramContext, R.dimen.mc_fastscroll_letter_layout_margin_top);
      mLetterMarginRight = getPxSize(paramContext, R.dimen.mc_fastscroll_letter_layout_margin_right);
      mLetterMarginBottom = getPxSize(paramContext, R.dimen.mc_fastscroll_letter_layout_margin_bottom);
      mLetterWidth = getPxSize(paramContext, R.dimen.mc_fastscroll_letter_layout_wdith);
      mOverlayText = paramTextView;
      mLetterTextColor = localTypedArray.getColor(R.styleable.FastScrollLetter_mcLetterTextColor, mLetterTextColor);
      mLetterActiveTextColor = localTypedArray.getColor(R.styleable.FastScrollLetter_mcLetterActiveTextColor, mLetterActiveTextColor);
      mLetterTextSize = ((int)localTypedArray.getDimension(R.styleable.FastScrollLetter_mcLetterTextSize, mLetterTextSize));
      mLetterWidth = ((int)localTypedArray.getDimension(R.styleable.FastScrollLetter_mcLetterWidth, mLetterWidth));
      mLetterMarginTop = ((int)localTypedArray.getDimension(R.styleable.FastScrollLetter_mcLetterMarginTop, mLetterMarginTop));
      mLetterMarginBottom = ((int)localTypedArray.getDimension(R.styleable.FastScrollLetter_mcLetterMarginBottom, mLetterMarginBottom));
      mLetterMarginRight = ((int)localTypedArray.getDimension(R.styleable.FastScrollLetter_mcLetterMarginRight, mLetterMarginRight));
      mOverlay.add(mOverlayText);
      mOverlayText.setVisibility(4);
      mOverlayText.setLayoutDirection(mAbsListView.getLayoutDirection());
      mLetterActiveTextColor = mLetterTextColor;
      mPaint.setColor(mLetterTextColor);
      mPaint.setAntiAlias(true);
      mPaint.setTextSize(mLetterTextSize);
      mPointBitmap = ((BitmapDrawable)paramContext.getResources().getDrawable(R.drawable.mc_ic_letter_search_point)).getBitmap();
      setBackgroundResource(R.drawable.mc_ic_letter_search_bg);
      mShowLetters = mDefaultLetters;
      mOverlayTextLetters = mDefaultLetters;
      mLetters = mDefaultLetters;
      setVisibility(4);
    }
    localTypedArray.recycle();
  }
  
  public FastScrollLetter(Context paramContext, AbsListView paramAbsListView, TextView paramTextView, int paramInt)
  {
    this(paramContext, paramAbsListView, paramTextView);
    mDefaultSectionType = paramInt;
  }
  
  private void OnTouchingLetterChange(MotionEvent paramMotionEvent, int paramInt)
  {
    int i = (int)(mOverlayTextTop + paramMotionEvent.getY() - mEventDownY);
    int j = mOverlayTextHeight / 2;
    mOverlayText.setTranslationY(i + j);
    setSelection(paramInt, paramMotionEvent.getY());
  }
  
  private void alphaAnim(boolean paramBoolean, View paramView)
  {
    float f2 = 1.0F;
    if (paramView.getAnimation() == null)
    {
      if ((paramBoolean) && (paramView.getVisibility() == 0)) {}
      while ((!paramBoolean) && (paramView.getVisibility() == 4)) {
        return;
      }
    }
    float f1;
    if (paramBoolean)
    {
      f1 = 0.0F;
      if (!paramBoolean) {
        break label112;
      }
    }
    for (;;)
    {
      paramView.clearAnimation();
      AlphaAnimation localAlphaAnimation = new AlphaAnimation(f1, f2);
      localAlphaAnimation.setAnimationListener(new FastScrollLetter.1(this, paramView, paramBoolean));
      localAlphaAnimation.setDuration(180L);
      localAlphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
      paramView.setAnimation(localAlphaAnimation);
      localAlphaAnimation.startNow();
      return;
      f1 = 1.0F;
      break;
      label112:
      f2 = 0.0F;
    }
  }
  
  private void cancelFling()
  {
    MotionEvent localMotionEvent = MotionEvent.obtain(0L, 0L, 3, 0.0F, 0.0F, 0);
    mAbsListView.onTouchEvent(localMotionEvent);
    localMotionEvent.recycle();
  }
  
  private int getChoosePos(float paramFloat)
  {
    int i = 0;
    mSingleLetterHeight = (getHeight() / mShowLetters.length);
    int k = (int)(Math.ceil(paramFloat / mSingleLetterHeight) - 1.0D);
    if ((k < 0) || (k >= mShowLetters.length)) {
      j = -1;
    }
    do
    {
      return j;
      if (mHideLetteSparseArray != null) {
        break;
      }
      j = 0;
      if (j < k)
      {
        if (mShowLetters[j].equals(mHideStr)) {
          i += mHideNum;
        }
        for (;;)
        {
          j += 1;
          break;
          i += 1;
        }
      }
      f = mSingleLetterHeight * k;
      j = i;
    } while (!mShowLetters[k].equals(mHideStr));
    return i + (int)((paramFloat - f) / (mSingleLetterHeight / mHideNum));
    int j = 0;
    i = 0;
    if (j < k)
    {
      if (mShowLetters[j].equals(mHideStr)) {
        i = ((Integer)mHideLetteSparseArray.get(j)).intValue() + i;
      }
      for (;;)
      {
        j += 1;
        break;
        i += 1;
      }
    }
    float f = mSingleLetterHeight * k;
    if (mShowLetters[k].equals(mHideStr))
    {
      j = ((Integer)mHideLetteSparseArray.get(k)).intValue();
      paramFloat = (paramFloat - f) / mSingleLetterHeight;
      return (int)Math.floor(j * paramFloat) + i;
    }
    return i;
  }
  
  private int getCurrentPos(int paramInt)
  {
    mCurrentShowPos = -1;
    int i = -1;
    while (i == -1)
    {
      int j = paramInt - 1;
      if ((paramInt >= mOverlayTextLetters.length) || (paramInt < 0)) {
        break;
      }
      int k = getSection(paramInt);
      if (k == -1)
      {
        paramInt = j;
      }
      else
      {
        i = mSectionIndexer.getPositionForSection(k);
        if (i != -1) {
          mCurrentShowPos = Math.max(paramInt, 0);
        }
        paramInt = j;
      }
    }
    return i;
  }
  
  private int getNextPos(int paramInt)
  {
    int i = -1;
    int k;
    for (;;)
    {
      k = paramInt;
      if (i != -1) {
        break;
      }
      int j = paramInt + 1;
      k = j;
      if (j >= mOverlayTextLetters.length) {
        break;
      }
      k = j;
      if (j < 0) {
        break;
      }
      k = getSection(j);
      paramInt = j;
      if (k != -1)
      {
        i = mSectionIndexer.getPositionForSection(k);
        paramInt = j;
      }
    }
    if ((mCurrentShowPos < 0) && (k < mOverlayTextLetters.length)) {
      mCurrentShowPos = k;
    }
    if (i == -1) {
      return mAbsListView.getCount();
    }
    return i;
  }
  
  private int getPxSize(Context paramContext, int paramInt)
  {
    return paramContext.getResources().getDimensionPixelSize(paramInt);
  }
  
  private int getSection(int paramInt)
  {
    int j = 0;
    int k = -1;
    int i;
    if (mSectionCompare != null) {
      i = mSectionCompare.getSection(paramInt);
    }
    String str;
    Object[] arrayOfObject;
    do
    {
      return i;
      str = mOverlayTextLetters[paramInt];
      arrayOfObject = mSectionIndexer.getSections();
      i = k;
    } while (arrayOfObject == null);
    paramInt = 0;
    label54:
    if (paramInt < arrayOfObject.length) {
      if (!arrayOfObject[paramInt].toString().equals(str)) {}
    }
    for (;;)
    {
      if ((mDefaultSectionType == 2) && (paramInt >= 0))
      {
        return paramInt;
        paramInt += 1;
        break label54;
      }
      i = k;
      if (mDefaultSectionType != 1) {
        break;
      }
      int m = mSectionIndexer.getPositionForSection(paramInt);
      if ((mAbsListView instanceof ListView)) {
        j = ((ListView)mAbsListView).getFooterViewsCount();
      }
      i = k;
      if (m >= mAbsListView.getCount() - j) {
        break;
      }
      i = k;
      if (mSectionIndexer.getSectionForPosition(m) != paramInt) {
        break;
      }
      return paramInt;
      paramInt = -1;
    }
  }
  
  private boolean isContain(float paramFloat1, float paramFloat2)
  {
    return (getLeft() < paramFloat1) && (paramFloat1 < getRight()) && (getTop() < paramFloat2) && (getBottom() > paramFloat2);
  }
  
  private boolean isContainWithTop(float paramFloat1, float paramFloat2)
  {
    return (getLeft() < paramFloat1) && (paramFloat1 < getRight()) && (getTop() > paramFloat2) && (getBottom() > paramFloat2);
  }
  
  private void onTouchingLetterTop()
  {
    setOverLayText(mTopLetter);
    if ((mAbsListView instanceof ListView))
    {
      ((ListView)mAbsListView).setSelectionFromTop(0, -mHeaderHeight);
      return;
    }
    mAbsListView.setSelection(mHeaderCount);
  }
  
  private void setLetterState(boolean paramBoolean, View paramView)
  {
    alphaAnim(paramBoolean, mOverlayText);
    if (!mIsAlwayShowLetter) {
      alphaAnim(paramBoolean, paramView);
    }
  }
  
  private void setOverLayText(int paramInt)
  {
    setOverLayText(mOverlayTextLetters[paramInt]);
  }
  
  private void setOverLayText(String paramString)
  {
    int i = mOverlayThreeTextSize;
    if (paramString == mCurrentLetter) {
      return;
    }
    mCurrentLetter = paramString;
    switch (mCurrentLetter.length())
    {
    }
    for (;;)
    {
      mOverlayText.setTextSize(0, i);
      mOverlayText.setText(mCurrentLetter);
      return;
      i = mOverlayOneTextSize;
      continue;
      i = mOverlayTwoTextSize;
    }
  }
  
  @TargetApi(17)
  private void setOverlayTextLayout(float paramFloat)
  {
    mEventDownY = ((int)paramFloat);
    int i = mAbsListView.getWidth();
    int j = mOverlayTextMarginRight;
    int k = mOverlayTextWidth;
    int m = (int)(mOverlayTextTop + paramFloat);
    int n = mAbsListView.getWidth();
    int i1 = mOverlayTextMarginRight;
    int i2 = mOverlayTextHeight + m;
    mOverlayText.setTranslationY(0.0F);
    if (mAbsListView.getLayoutDirection() == 0)
    {
      mOverlayText.layout(i - (j + k), m, n - i1, i2);
      return;
    }
    mOverlayText.layout(mOverlayTextMarginRight, m, mOverlayTextMarginRight + mOverlayTextWidth, i2);
  }
  
  private void setSelection(int paramInt, float paramFloat)
  {
    ListAdapter localListAdapter2 = (ListAdapter)mAbsListView.getAdapter();
    ListAdapter localListAdapter1 = localListAdapter2;
    if ((localListAdapter2 instanceof HeaderViewListAdapter))
    {
      mHeaderCount = ((HeaderViewListAdapter)localListAdapter2).getHeadersCount();
      localListAdapter1 = ((HeaderViewListAdapter)localListAdapter2).getWrappedAdapter();
    }
    int j;
    int i;
    if ((localListAdapter1 instanceof SectionIndexer))
    {
      mSectionIndexer = ((SectionIndexer)localListAdapter1);
      j = mCurrentShowPos;
      i = getCurrentPos(paramInt);
      if (mCurrentShowPos != -1) {
        break label210;
      }
    }
    label210:
    for (paramInt = getNextPos(paramInt);; paramInt = i)
    {
      if ((mCurrentShowPos < 0) || (mCurrentShowPos >= mOverlayTextLetters.length)) {
        if ((mTopLetter != null) && (!mTopLetter.equals(""))) {
          setOverLayText(mTopLetter);
        }
      }
      while (j == mCurrentShowPos)
      {
        return;
        Log.w(TAG, "mSectionIndexer is null, adapter is not implements");
        return;
      }
      setOverLayText(mCurrentShowPos);
      if ((mAbsListView instanceof ListView))
      {
        ((ListView)mAbsListView).setSelectionFromTop(paramInt + mHeaderCount, -mHeaderHeight);
        return;
      }
      mAbsListView.setSelection(paramInt + mHeaderCount);
      return;
    }
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    int i = mPaddingLeft;
    if (mAbsListView.getLayoutDirection() == 1) {
      i = mPaddingLeft * -1;
    }
    String[] arrayOfString = mShowLetters;
    if ((arrayOfString == null) || (arrayOfString.length == 0)) {
      return;
    }
    int j = getHeight();
    int k = getWidth();
    mSingleLetterHeight = (j / arrayOfString.length);
    j = 0;
    label73:
    float f1;
    float f2;
    float f3;
    float f4;
    if (j < arrayOfString.length)
    {
      f1 = k / 2;
      f2 = mPaint.measureText(arrayOfString[j].toString()) / 2.0F;
      f3 = i;
      f4 = mSingleLetterHeight * j + mSingleLetterHeight;
      if ((mPointBitmap == null) || (!arrayOfString[j].toString().equals(mHideStr))) {
        break label208;
      }
      f1 = k / 2 - mPointBitmap.getWidth() / 2 + i;
      f2 = mSingleLetterHeight * j + mSingleLetterHeight / 2;
      paramCanvas.drawBitmap(mPointBitmap, f1, f2, mPaint);
    }
    for (;;)
    {
      j += 1;
      break label73;
      break;
      label208:
      paramCanvas.drawText(arrayOfString[j].toString(), f1 - f2 + f3, f4, mPaint);
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  @TargetApi(17)
  public void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    paramInt1 = mAbsListView.getWidth();
    paramInt2 = mLetterMarginRight;
    paramInt3 = mLetterWidth;
    paramInt4 = mLetterMarginTop;
    int i = mAbsListView.getWidth();
    int j = mLetterMarginRight;
    int k = mAbsListView.getHeight() - mLetterMarginBottom;
    if (mAbsListView.getLayoutDirection() == 0) {
      layout(paramInt1 - (paramInt2 + paramInt3), paramInt4, i - j, k);
    }
    for (;;)
    {
      mOverlayText.measure(mOverlayTextWidth, mOverlayTextWidth);
      setOverlayTextLayout(0.0F);
      return;
      layout(mLetterMarginRight, paramInt4, mLetterMarginRight + mLetterWidth, k);
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    float f1 = 0.0F;
    if (!mIsEnable) {
      return false;
    }
    if (mAbsListView.getScrollY() != 0) {
      return false;
    }
    int i = paramMotionEvent.getAction();
    float f2 = paramMotionEvent.getY();
    if (f2 < 0.0F) {}
    for (;;)
    {
      if ((mShowLetters == null) || (mShowLetters.length == 0) || (mAbsListView == null) || (mOverlayText == null) || (mOverlayTextLetters == null) || (mOverlayTextLetters.length == 0)) {
        return false;
      }
      int j = getChoosePos(f1 - mLetterMarginTop);
      switch (i)
      {
      }
      while (mState != 1)
      {
        return false;
        if (j < 0) {
          return false;
        }
        mEventMoveY = ((int)paramMotionEvent.getY());
        if (isContain(paramMotionEvent.getX(), paramMotionEvent.getY()))
        {
          mPaint.setColor(mLetterActiveTextColor);
          invalidate();
          mAbsListView.requestDisallowInterceptTouchEvent(true);
          cancelFling();
          setOverlayTextLayout(paramMotionEvent.getY());
          setLetterState(true, this);
          OnTouchingLetterChange(paramMotionEvent, j);
          mState = 1;
          return true;
        }
        if ((!mTopLetter.equals("")) && (isContainWithTop(paramMotionEvent.getX(), paramMotionEvent.getY())))
        {
          mCurrentShowPos = -1;
          mPaint.setColor(mLetterActiveTextColor);
          invalidate();
          mState = 1;
          setOverlayTextLayout(getTop());
          setLetterState(true, this);
          onTouchingLetterTop();
          return true;
        }
        if (mState == 1) {
          if ((j >= 0) && (j < mOverlayTextLetters.length))
          {
            mEventMoveY = ((int)paramMotionEvent.getY());
            if (mCurrentShowPos == -1) {
              setOverlayTextLayout(paramMotionEvent.getY());
            }
            OnTouchingLetterChange(paramMotionEvent, j);
          }
          else if ((!mTopLetter.equals("")) && (isContainWithTop(paramMotionEvent.getX(), paramMotionEvent.getY())))
          {
            mCurrentShowPos = -1;
            onTouchingLetterTop();
            continue;
            mAbsListView.requestDisallowInterceptTouchEvent(false);
            if (mState == 1)
            {
              mCurrentShowPos = -1;
              mPaint.setColor(mLetterTextColor);
              invalidate();
              setOverlayTextLayout(mEventMoveY);
              setLetterState(false, this);
              mState = 0;
              return true;
            }
          }
        }
      }
      return true;
      f1 = f2;
    }
  }
  
  public void setFastScrollAlwaysVisible(boolean paramBoolean)
  {
    mIsAlwayShowLetter = paramBoolean;
    if (mIsAlwayShowLetter) {
      setVisibility(0);
    }
  }
  
  public void setFastScrollEnabled(boolean paramBoolean)
  {
    mIsEnable = paramBoolean;
    if (paramBoolean) {}
    for (int i = 0;; i = 8)
    {
      setVisibility(i);
      return;
    }
  }
  
  public void setHeaderHeight(int paramInt)
  {
    mHeaderHeight = paramInt;
  }
  
  public void setHideLetter(SparseArray<Integer> paramSparseArray, String[] paramArrayOfString)
  {
    mHideLetteSparseArray = paramSparseArray;
    mShowLetters = paramArrayOfString;
  }
  
  public void setHideLetterNum(String paramString, int paramInt)
  {
    int j = 0;
    mHideStr = paramString;
    mHideNum = paramInt;
    paramString = new String[(mShowLetters.length - 1) / (mHideNum + 1) * 2 + 2];
    int i = 0;
    paramInt = j;
    while (paramInt < mShowLetters.length)
    {
      j = i + 1;
      paramString[i] = mShowLetters[paramInt];
      i = j + 1;
      paramString[j] = mHideStr;
      paramInt += mHideNum + 1;
    }
    paramString[(paramString.length - 1)] = mShowLetters[(mShowLetters.length - 1)];
    mShowLetters = paramString;
  }
  
  public void setHideLetterStr(String paramString, Bitmap paramBitmap)
  {
    if (paramBitmap != null) {
      mPointBitmap = paramBitmap;
    }
    mHideStr = paramString;
  }
  
  public void setLayoutPaddingLeft(int paramInt)
  {
    mPaddingLeft = paramInt;
  }
  
  public void setLetterActiveColor(int paramInt1, int paramInt2)
  {
    mLetterActiveTextColor = paramInt2;
    mLetterTextColor = paramInt1;
    mPaint.setColor(mLetterTextColor);
    invalidate();
  }
  
  @TargetApi(16)
  public void setLetterBackground(Drawable paramDrawable)
  {
    setBackground(paramDrawable);
  }
  
  public void setLetterParam(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    if (paramInt1 != -1)
    {
      mLetterTextSize = paramInt1;
      mPaint.setTextSize(mLetterTextSize);
    }
    if (paramInt2 != -1)
    {
      mLetterTextColor = paramInt2;
      mLetterActiveTextColor = mLetterTextColor;
      mPaint.setColor(mLetterTextColor);
      invalidate();
    }
    if (paramInt3 != -1) {
      mLetterMarginTop = paramInt3;
    }
    if (paramInt4 != -1) {
      mLetterMarginBottom = paramInt4;
    }
    if (paramInt5 != -1) {
      mLetterMarginRight = paramInt5;
    }
    if (paramInt6 != -1) {
      mLetterWidth = paramInt6;
    }
  }
  
  public void setLetters(String[] paramArrayOfString)
  {
    mShowLetters = paramArrayOfString;
    mLetters = paramArrayOfString;
    setOverlayTextLetters(paramArrayOfString);
  }
  
  @TargetApi(16)
  public void setOverlayBackground(Drawable paramDrawable)
  {
    mOverlayText.setBackground(paramDrawable);
  }
  
  public void setOverlayParam(int paramInt1, int paramInt2)
  {
    if (paramInt1 != -1)
    {
      mOverlayTextHeight = paramInt1;
      mOverlayTextWidth = paramInt1;
    }
    if (paramInt2 != -1) {
      mOverlayTextMarginRight = paramInt2;
    }
  }
  
  public void setOverlayTextLetters(String[] paramArrayOfString)
  {
    mOverlayTextLetters = paramArrayOfString;
    if ((mOverlayTextLetters == null) || (mOverlayTextLetters.length == 0)) {
      setOverLayText(mTopLetter);
    }
  }
  
  public void setSectionCompare(SectionCompare paramSectionCompare)
  {
    mSectionCompare = paramSectionCompare;
  }
  
  public void setTopLetter(String paramString)
  {
    mTopLetter = paramString;
    if ((mOverlayTextLetters == null) || (mOverlayTextLetters.length == 0)) {
      setOverLayText(mTopLetter);
    }
  }
  
  public static abstract interface SectionCompare
  {
    public abstract int getSection(int paramInt);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.FastScrollLetter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */