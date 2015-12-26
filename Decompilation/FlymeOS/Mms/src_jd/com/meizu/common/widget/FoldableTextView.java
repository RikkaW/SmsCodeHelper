package com.meizu.common.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.DynamicLayout;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.method.Touch;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.meizu.common.R.string;
import com.meizu.common.R.style;
import com.meizu.common.R.styleable;
import com.meizu.common.util.ResourceUtils;

public class FoldableTextView
  extends TextView
  implements View.OnClickListener
{
  private static final boolean DEBUG = false;
  private static final String ELLIPSIS_TWO_DOTS = "‥";
  private static final String TAG = "FoldableTextView";
  private Layout layout = null;
  private boolean mAlignViewEdge = false;
  private Float mAnimatorFraction = Float.valueOf(1.0F);
  private boolean mClickToFold = true;
  private int mDuration = 250;
  private CharSequence mEllipseText;
  private int mFoldLineMax;
  private CharSequence mFoldedText;
  private boolean mIsAnimating = false;
  private boolean mIsfolded = true;
  private int mLinkColor = 0;
  private boolean mLinkHit;
  private FoldingListener mListener;
  private int mMaxHeight = 0;
  private int mMinHeight = 0;
  private CharSequence mMoreText;
  private boolean mNonClicks = true;
  private boolean mNonSpanClickable = true;
  private CharSequence mainText;
  private CharSequence mainTextCache;
  
  public FoldableTextView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public FoldableTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.style.Widget_MeizuCommon_FoldableTextView);
  }
  
  public FoldableTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.FoldableTextView, paramInt, 0);
    int j = paramAttributeSet.getIndexCount();
    paramInt = 0;
    if (paramInt < j)
    {
      int i = paramAttributeSet.getIndex(paramInt);
      if (i == R.styleable.FoldableTextView_mzTextEllipse) {
        mEllipseText = paramAttributeSet.getText(i);
      }
      for (;;)
      {
        paramInt += 1;
        break;
        if (i == R.styleable.FoldableTextView_mzTextUnfold)
        {
          mMoreText = paramAttributeSet.getText(i);
        }
        else if (i == R.styleable.FoldableTextView_mzMaxFoldLine)
        {
          mFoldLineMax = paramAttributeSet.getInt(i, 0);
        }
        else if (i == R.styleable.FoldableTextView_mzUnfoldAlignViewEdge)
        {
          mAlignViewEdge = paramAttributeSet.getBoolean(i, false);
        }
        else if (i == R.styleable.FoldableTextView_mzClickToFold)
        {
          mClickToFold = paramAttributeSet.getBoolean(i, false);
        }
        else if (i == R.styleable.FoldableTextView_mzNonSpanClickable)
        {
          mNonSpanClickable = paramAttributeSet.getBoolean(i, true);
        }
        else if (i == R.styleable.FoldableTextView_mzLinkColor)
        {
          mLinkColor = paramAttributeSet.getColor(i, 0);
          if (mLinkColor == 0)
          {
            if (ResourceUtils.getMzThemeColor(paramContext) == null) {}
            for (i = 0;; i = ResourceUtils.getMzThemeColor(paramContext).intValue())
            {
              mLinkColor = i;
              break;
            }
          }
        }
        else if (i == R.styleable.FoldableTextView_mzIsFold)
        {
          mIsfolded = paramAttributeSet.getBoolean(i, true);
        }
      }
    }
    paramAttributeSet.recycle();
    if (TextUtils.isEmpty(mMoreText)) {
      mMoreText = paramContext.getString(R.string.more_item_label);
    }
    if (TextUtils.isEmpty(mEllipseText)) {
      mEllipseText = "‥";
    }
    setMovementMethod(LocalLinkMovementMethod.getInstance());
    setOnClickListener(true);
  }
  
  private CharSequence foldText(CharSequence paramCharSequence)
  {
    layout = getLayout();
    if (layout == null) {}
    SpannableStringBuilder localSpannableStringBuilder;
    DynamicLayout localDynamicLayout;
    do
    {
      return paramCharSequence;
      localSpannableStringBuilder = new SpannableStringBuilder(paramCharSequence);
      localDynamicLayout = new DynamicLayout(localSpannableStringBuilder, layout.getPaint(), layout.getWidth(), layout.getAlignment(), 1.0F, 0.0F, false);
    } while ((localDynamicLayout.getLineCount() <= mFoldLineMax) || (mFoldLineMax == 0));
    int i = mFoldLineMax;
    int j;
    label141:
    int k;
    if (i > 1)
    {
      j = i - 1;
      if (localDynamicLayout.getLineStart(j) >= localDynamicLayout.getLineVisibleEnd(j)) {}
    }
    else
    {
      j = localDynamicLayout.getLineVisibleEnd(i - 1);
      if (!TextUtils.isEmpty(mEllipseText)) {
        break label237;
      }
      localSpannableStringBuilder.delete(j, localSpannableStringBuilder.length());
      localSpannableStringBuilder.append(' ');
      k = localSpannableStringBuilder.length();
      localSpannableStringBuilder.append(mMoreText);
      localSpannableStringBuilder.setSpan(new MoreClickSpan(paramCharSequence), k, localSpannableStringBuilder.length(), 33);
      if ((j <= 0) || (localDynamicLayout.getLineCount() <= i)) {
        break label256;
      }
      do
      {
        j -= 1;
        localSpannableStringBuilder.delete(j, j + 1);
      } while ((j > 0) && (localDynamicLayout.getLineCount() > i));
    }
    label237:
    label256:
    label313:
    for (;;)
    {
      return localSpannableStringBuilder;
      i = j;
      break;
      localSpannableStringBuilder.replace(j, localSpannableStringBuilder.length(), mEllipseText);
      break label141;
      if (mAlignViewEdge)
      {
        j = k;
        for (;;)
        {
          if (localDynamicLayout.getLineCount() != i) {
            break label313;
          }
          localSpannableStringBuilder.replace(j, j, " ");
          if (localDynamicLayout.getLineCount() > i)
          {
            localSpannableStringBuilder.delete(j, j + 1);
            break;
          }
          j += 1;
        }
      }
    }
  }
  
  private void initHeight(CharSequence paramCharSequence)
  {
    if (!mIsAnimating)
    {
      layout = getLayout();
      if (layout != null) {
        break label23;
      }
    }
    label23:
    do
    {
      return;
      mMaxHeight = ((int)(layout.getLineBottom(layout.getLineCount() - 1) - layout.getLineTop(0) + getPaddingBottom() + getPaddingTop() + 0.5D));
    } while (mFoldedText == null);
    mMinHeight = new StaticLayout(mFoldedText, layout.getPaint(), layout.getWidth(), Layout.Alignment.ALIGN_NORMAL, layout.getSpacingMultiplier(), layout.getSpacingAdd(), true).getHeight();
  }
  
  private void setOnClickListener(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      setOnClickListener(this);
      return;
    }
    setOnClickListener(null);
  }
  
  private void startAnimator()
  {
    if (mFoldLineMax == 0) {
      return;
    }
    initHeight(mainText);
    Object localObject = new ValueHolder(null);
    int i;
    if (mIsfolded)
    {
      i = mMaxHeight;
      if (!mIsfolded) {
        break label127;
      }
    }
    label127:
    for (int j = mMinHeight;; j = mMaxHeight)
    {
      localObject = ObjectAnimator.ofInt(localObject, "height", new int[] { i, j });
      ((ObjectAnimator)localObject).setDuration(mDuration);
      ((ObjectAnimator)localObject).setInterpolator(new AccelerateDecelerateInterpolator());
      ((ObjectAnimator)localObject).addListener(new FoldableTextView.1(this));
      ((ObjectAnimator)localObject).addUpdateListener(new FoldableTextView.2(this));
      ((ObjectAnimator)localObject).start();
      return;
      i = mMinHeight;
      break;
    }
  }
  
  public void append(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    super.append(paramCharSequence, paramInt1, paramInt2);
    mainText = getText();
  }
  
  public void changeFoldState()
  {
    if (mIsAnimating) {}
    label7:
    do
    {
      do
      {
        do
        {
          do
          {
            break label7;
            break label7;
            do
            {
              return;
            } while (!mClickToFold);
            if (!mIsfolded) {
              break;
            }
          } while ((mListener != null) && (!mListener.onFolding(this, false)));
          mIsfolded = false;
          setText(mainText, TextView.BufferType.NORMAL);
        } while (mMinHeight == 0);
        startAnimator();
        return;
      } while ((mListener != null) && (!mListener.onFolding(this, true)));
      mIsfolded = true;
    } while ((mMaxHeight == 0) || (getLayout() == null) || (getLayout().getLineCount() <= mFoldLineMax));
    startAnimator();
  }
  
  public boolean getFoldStatus()
  {
    return mIsfolded;
  }
  
  public boolean hasFocusable()
  {
    return false;
  }
  
  public void onClick(View paramView)
  {
    changeFoldState();
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (mainText == null) {
      mainText = getText();
    }
    initHeight(mainText);
    if (mIsAnimating)
    {
      if (!mIsfolded)
      {
        setMeasuredDimension(paramInt1, (int)(mMinHeight + (mMaxHeight - mMinHeight) * mAnimatorFraction.floatValue()));
        return;
      }
      setMeasuredDimension(paramInt1, (int)(mMaxHeight - (mMaxHeight - mMinHeight) * mAnimatorFraction.floatValue()));
      return;
    }
    if ((mFoldedText == null) || (mainTextCache != mainText))
    {
      mFoldedText = foldText(mainText);
      mainTextCache = mainText;
    }
    if ((!mIsfolded) || (mFoldLineMax <= 0))
    {
      setText(mainText, TextView.BufferType.SPANNABLE);
      setMeasuredDimension(paramInt1, mMaxHeight);
      return;
    }
    setText(mFoldedText, TextView.BufferType.SPANNABLE);
    setMeasuredDimension(paramInt1, mMinHeight);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    mLinkHit = false;
    boolean bool2 = super.onTouchEvent(paramMotionEvent);
    boolean bool1 = bool2;
    if (mNonClicks)
    {
      bool1 = bool2;
      if (!mNonSpanClickable) {
        bool1 = mLinkHit;
      }
    }
    return bool1;
  }
  
  public void setClickToFold(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      mClickToFold = true;
      return;
    }
    mClickToFold = false;
  }
  
  public void setFoldDuration(int paramInt)
  {
    mDuration = paramInt;
  }
  
  public void setFoldStatus(boolean paramBoolean)
  {
    if (mIsfolded != paramBoolean)
    {
      mIsfolded = paramBoolean;
      requestLayout();
      invalidate();
    }
  }
  
  public void setFoldText(String paramString1, String paramString2, boolean paramBoolean)
  {
    mAlignViewEdge = paramBoolean;
    if (paramString1 != null) {
      mEllipseText = paramString1;
    }
    if (paramString2 != null) {
      mMoreText = paramString2;
    }
  }
  
  public void setFolding(int paramInt, FoldingListener paramFoldingListener)
  {
    mFoldLineMax = paramInt;
    mListener = paramFoldingListener;
    setText(mainText, TextView.BufferType.NORMAL);
  }
  
  public void setLinkColor(int paramInt)
  {
    mLinkColor = paramInt;
    invalidate();
  }
  
  public void setNonSpanClickable(boolean paramBoolean)
  {
    mNonSpanClickable = paramBoolean;
  }
  
  public void setText(CharSequence paramCharSequence, TextView.BufferType paramBufferType)
  {
    super.setText(paramCharSequence, paramBufferType);
    if ((mainText != null) && (mFoldedText != null) && (paramCharSequence != null) && (!paramCharSequence.toString().equals(mainText.toString())) && (!paramCharSequence.toString().equals(mFoldedText.toString()))) {
      mainText = paramCharSequence;
    }
    requestLayout();
  }
  
  public static abstract interface FoldingListener
  {
    public abstract boolean onFolding(FoldableTextView paramFoldableTextView, boolean paramBoolean);
  }
  
  public static class LocalLinkMovementMethod
    extends LinkMovementMethod
  {
    static LocalLinkMovementMethod sInstance;
    
    public static LocalLinkMovementMethod getInstance()
    {
      if (sInstance == null) {
        sInstance = new LocalLinkMovementMethod();
      }
      return sInstance;
    }
    
    public boolean onTouchEvent(TextView paramTextView, Spannable paramSpannable, MotionEvent paramMotionEvent)
    {
      int i = paramMotionEvent.getAction();
      if ((i == 1) || (i == 0))
      {
        int j = (int)paramMotionEvent.getX();
        int k = (int)paramMotionEvent.getY();
        int m = paramTextView.getTotalPaddingLeft();
        int n = paramTextView.getTotalPaddingTop();
        int i1 = paramTextView.getScrollX();
        int i2 = paramTextView.getScrollY();
        Object localObject = paramTextView.getLayout();
        j = ((Layout)localObject).getOffsetForHorizontal(((Layout)localObject).getLineForVertical(k - n + i2), j - m + i1);
        localObject = (ClickableSpan[])paramSpannable.getSpans(j, j, ClickableSpan.class);
        if (localObject.length != 0)
        {
          if (i == 1) {
            localObject[0].onClick(paramTextView);
          }
          for (;;)
          {
            if ((paramTextView instanceof FoldableTextView)) {
              FoldableTextView.access$1002((FoldableTextView)paramTextView, true);
            }
            return true;
            if (i == 0) {
              Selection.setSelection(paramSpannable, paramSpannable.getSpanStart(localObject[0]), paramSpannable.getSpanEnd(localObject[0]));
            }
          }
        }
        Selection.removeSelection(paramSpannable);
        Touch.onTouchEvent(paramTextView, paramSpannable, paramMotionEvent);
        return false;
      }
      return Touch.onTouchEvent(paramTextView, paramSpannable, paramMotionEvent);
    }
  }
  
  class MoreClickSpan
    extends ClickableSpan
  {
    private final CharSequence mText;
    
    public MoreClickSpan(CharSequence paramCharSequence)
    {
      mText = paramCharSequence;
    }
    
    public void onClick(View paramView)
    {
      if (mIsAnimating) {}
      while ((mListener != null) && (!mListener.onFolding(FoldableTextView.this, false))) {
        return;
      }
      FoldableTextView.access$302(FoldableTextView.this, false);
      setText(mainText, TextView.BufferType.NORMAL);
      FoldableTextView.this.startAnimator();
    }
    
    public void updateDrawState(TextPaint paramTextPaint)
    {
      if (mLinkColor == 0)
      {
        paramTextPaint.setColor(linkColor);
        return;
      }
      paramTextPaint.setColor(mLinkColor);
    }
  }
  
  class ValueHolder
  {
    private int mHeight;
    
    private ValueHolder() {}
    
    public int getHeight()
    {
      return mHeight;
    }
    
    public void setHeight(int paramInt)
    {
      mHeight = paramInt;
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.FoldableTextView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */