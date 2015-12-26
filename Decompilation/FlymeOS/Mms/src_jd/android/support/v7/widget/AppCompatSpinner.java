package android.support.v7.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.v4.view.TintableBackgroundView;
import android.support.v7.appcompat.R.attr;
import android.support.v7.internal.widget.TintInfo;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.internal.widget.TintTypedArray;
import android.util.AttributeSet;
import android.widget.ListPopupWindow;
import android.widget.Spinner;
import java.lang.reflect.Field;

public class AppCompatSpinner
  extends Spinner
  implements TintableBackgroundView
{
  private static final int[] TINT_ATTRS = { 16842964, 16843126 };
  private TintInfo mBackgroundTint;
  private TintInfo mInternalBackgroundTint;
  private TintManager mTintManager;
  
  public AppCompatSpinner(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public AppCompatSpinner(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.spinnerStyle);
  }
  
  public AppCompatSpinner(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    if (TintManager.SHOULD_BE_USED)
    {
      paramContext = TintTypedArray.obtainStyledAttributes(getContext(), paramAttributeSet, TINT_ATTRS, paramInt, 0);
      if (paramContext.hasValue(0))
      {
        paramAttributeSet = paramContext.getTintManager().getTintList(paramContext.getResourceId(0, -1));
        if (paramAttributeSet != null) {
          setInternalBackgroundTint(paramAttributeSet);
        }
      }
      if (paramContext.hasValue(1))
      {
        paramAttributeSet = paramContext.getDrawable(1);
        if (Build.VERSION.SDK_INT < 16) {
          break label98;
        }
        setPopupBackgroundDrawable(paramAttributeSet);
      }
    }
    for (;;)
    {
      mTintManager = paramContext.getTintManager();
      paramContext.recycle();
      return;
      label98:
      if (Build.VERSION.SDK_INT >= 11) {
        setPopupBackgroundDrawableV11(this, paramAttributeSet);
      }
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
  
  @TargetApi(11)
  private static void setPopupBackgroundDrawableV11(Spinner paramSpinner, Drawable paramDrawable)
  {
    try
    {
      Field localField = Spinner.class.getDeclaredField("mPopup");
      localField.setAccessible(true);
      paramSpinner = localField.get(paramSpinner);
      if ((paramSpinner instanceof ListPopupWindow)) {
        ((ListPopupWindow)paramSpinner).setBackgroundDrawable(paramDrawable);
      }
      return;
    }
    catch (NoSuchFieldException paramSpinner)
    {
      paramSpinner.printStackTrace();
      return;
    }
    catch (IllegalAccessException paramSpinner)
    {
      paramSpinner.printStackTrace();
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
 * Qualified Name:     android.support.v7.widget.AppCompatSpinner
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */