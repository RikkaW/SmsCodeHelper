package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.v4.view.TintableBackgroundView;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.internal.text.AllCapsTransformationMethod;
import android.support.v7.internal.widget.ThemeUtils;
import android.support.v7.internal.widget.TintInfo;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.internal.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;

public class AppCompatButton
  extends Button
  implements TintableBackgroundView
{
  private static final int[] TINT_ATTRS = { 16842964 };
  private TintInfo mBackgroundTint;
  private TintInfo mInternalBackgroundTint;
  private TintManager mTintManager;
  
  public AppCompatButton(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public AppCompatButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.buttonStyle);
  }
  
  public AppCompatButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    if (TintManager.SHOULD_BE_USED)
    {
      localObject = TintTypedArray.obtainStyledAttributes(getContext(), paramAttributeSet, TINT_ATTRS, paramInt, 0);
      if (((TintTypedArray)localObject).hasValue(0))
      {
        ColorStateList localColorStateList = ((TintTypedArray)localObject).getTintManager().getTintList(((TintTypedArray)localObject).getResourceId(0, -1));
        if (localColorStateList != null) {
          setInternalBackgroundTint(localColorStateList);
        }
      }
      mTintManager = ((TintTypedArray)localObject).getTintManager();
      ((TintTypedArray)localObject).recycle();
    }
    Object localObject = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.AppCompatTextView, paramInt, 0);
    int i = ((TypedArray)localObject).getResourceId(R.styleable.AppCompatTextView_android_textAppearance, -1);
    ((TypedArray)localObject).recycle();
    if (i != -1)
    {
      localObject = paramContext.obtainStyledAttributes(i, R.styleable.TextAppearance);
      if (((TypedArray)localObject).hasValue(R.styleable.TextAppearance_textAllCaps)) {
        setAllCaps(((TypedArray)localObject).getBoolean(R.styleable.TextAppearance_textAllCaps, false));
      }
      ((TypedArray)localObject).recycle();
    }
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.AppCompatTextView, paramInt, 0);
    if (paramAttributeSet.hasValue(R.styleable.AppCompatTextView_textAllCaps)) {
      setAllCaps(paramAttributeSet.getBoolean(R.styleable.AppCompatTextView_textAllCaps, false));
    }
    paramAttributeSet.recycle();
    paramAttributeSet = getTextColors();
    if ((paramAttributeSet != null) && (!paramAttributeSet.isStateful())) {
      if (Build.VERSION.SDK_INT >= 21) {
        break label234;
      }
    }
    label234:
    for (paramInt = ThemeUtils.getDisabledThemeAttrColor(paramContext, 16842808);; paramInt = ThemeUtils.getThemeAttrColor(paramContext, 16842808))
    {
      setTextColor(ThemeUtils.createDisabledStateList(paramAttributeSet.getDefaultColor(), paramInt));
      return;
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
  
  public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    super.onInitializeAccessibilityEvent(paramAccessibilityEvent);
    paramAccessibilityEvent.setClassName(Button.class.getName());
  }
  
  public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    super.onInitializeAccessibilityNodeInfo(paramAccessibilityNodeInfo);
    paramAccessibilityNodeInfo.setClassName(Button.class.getName());
  }
  
  public void setAllCaps(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (AllCapsTransformationMethod localAllCapsTransformationMethod = new AllCapsTransformationMethod(getContext());; localAllCapsTransformationMethod = null)
    {
      setTransformationMethod(localAllCapsTransformationMethod);
      return;
    }
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
  
  public void setTextAppearance(Context paramContext, int paramInt)
  {
    super.setTextAppearance(paramContext, paramInt);
    paramContext = paramContext.obtainStyledAttributes(paramInt, R.styleable.TextAppearance);
    if (paramContext.hasValue(R.styleable.TextAppearance_textAllCaps)) {
      setAllCaps(paramContext.getBoolean(R.styleable.TextAppearance_textAllCaps, false));
    }
    paramContext.recycle();
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.AppCompatButton
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */