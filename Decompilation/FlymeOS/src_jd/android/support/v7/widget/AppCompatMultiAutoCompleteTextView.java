package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.view.TintableBackgroundView;
import android.support.v7.appcompat.R.attr;
import android.support.v7.internal.widget.TintContextWrapper;
import android.support.v7.internal.widget.TintInfo;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.internal.widget.TintTypedArray;
import android.util.AttributeSet;
import android.widget.MultiAutoCompleteTextView;

public class AppCompatMultiAutoCompleteTextView
  extends MultiAutoCompleteTextView
  implements TintableBackgroundView
{
  private static final int[] TINT_ATTRS = { 16842964, 16843126 };
  private TintInfo mBackgroundTint;
  private TintInfo mInternalBackgroundTint;
  private TintManager mTintManager;
  
  public AppCompatMultiAutoCompleteTextView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public AppCompatMultiAutoCompleteTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.autoCompleteTextViewStyle);
  }
  
  public AppCompatMultiAutoCompleteTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(TintContextWrapper.wrap(paramContext), paramAttributeSet, paramInt);
    if (TintManager.SHOULD_BE_USED)
    {
      paramContext = TintTypedArray.obtainStyledAttributes(getContext(), paramAttributeSet, TINT_ATTRS, paramInt, 0);
      mTintManager = paramContext.getTintManager();
      if (paramContext.hasValue(0))
      {
        paramAttributeSet = paramContext.getTintManager().getTintList(paramContext.getResourceId(0, -1));
        if (paramAttributeSet != null) {
          setInternalBackgroundTint(paramAttributeSet);
        }
      }
      if (paramContext.hasValue(1)) {
        setDropDownBackgroundDrawable(paramContext.getDrawable(1));
      }
      paramContext.recycle();
    }
  }
  
  private void applySupportBackgroundTint()
  {
    if (getBackground() != null)
    {
      if (mBackgroundTint == null) {
        break label23;
      }
      TintManager.tintViewBackground(this, mBackgroundTint);
    }
    label23:
    while (mInternalBackgroundTint == null) {
      return;
    }
    TintManager.tintViewBackground(this, mInternalBackgroundTint);
  }
  
  private void setInternalBackgroundTint(ColorStateList paramColorStateList)
  {
    if (paramColorStateList != null)
    {
      if (mInternalBackgroundTint == null) {
        mInternalBackgroundTint = new TintInfo();
      }
      mInternalBackgroundTint.mTintList = paramColorStateList;
      mInternalBackgroundTint.mHasTintList = true;
    }
    for (;;)
    {
      applySupportBackgroundTint();
      return;
      mInternalBackgroundTint = null;
    }
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    applySupportBackgroundTint();
  }
  
  @Nullable
  public ColorStateList getSupportBackgroundTintList()
  {
    if (mBackgroundTint != null) {
      return mBackgroundTint.mTintList;
    }
    return null;
  }
  
  @Nullable
  public PorterDuff.Mode getSupportBackgroundTintMode()
  {
    if (mBackgroundTint != null) {
      return mBackgroundTint.mTintMode;
    }
    return null;
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable)
  {
    super.setBackgroundDrawable(paramDrawable);
    setInternalBackgroundTint(null);
  }
  
  public void setBackgroundResource(int paramInt)
  {
    super.setBackgroundResource(paramInt);
    if (mTintManager != null) {}
    for (ColorStateList localColorStateList = mTintManager.getTintList(paramInt);; localColorStateList = null)
    {
      setInternalBackgroundTint(localColorStateList);
      return;
    }
  }
  
  public void setDropDownBackgroundResource(int paramInt)
  {
    if (mTintManager != null)
    {
      setDropDownBackgroundDrawable(mTintManager.getDrawable(paramInt));
      return;
    }
    super.setDropDownBackgroundResource(paramInt);
  }
  
  public void setSupportBackgroundTintList(@Nullable ColorStateList paramColorStateList)
  {
    if (mBackgroundTint == null) {
      mBackgroundTint = new TintInfo();
    }
    mBackgroundTint.mTintList = paramColorStateList;
    mBackgroundTint.mHasTintList = true;
    applySupportBackgroundTint();
  }
  
  public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode paramMode)
  {
    if (mBackgroundTint == null) {
      mBackgroundTint = new TintInfo();
    }
    mBackgroundTint.mTintMode = paramMode;
    mBackgroundTint.mHasTintMode = true;
    applySupportBackgroundTint();
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.AppCompatMultiAutoCompleteTextView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */