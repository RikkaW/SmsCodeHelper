package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v7.appcompat.R.attr;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.internal.widget.TintTypedArray;
import android.util.AttributeSet;
import android.widget.CheckBox;

public class AppCompatCheckBox
  extends CheckBox
{
  private static final int[] TINT_ATTRS = { 16843015 };
  private Drawable mButtonDrawable;
  private TintManager mTintManager;
  
  public AppCompatCheckBox(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public AppCompatCheckBox(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.checkboxStyle);
  }
  
  public AppCompatCheckBox(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    if (TintManager.SHOULD_BE_USED)
    {
      paramContext = TintTypedArray.obtainStyledAttributes(getContext(), paramAttributeSet, TINT_ATTRS, paramInt, 0);
      setButtonDrawable(paramContext.getDrawable(0));
      paramContext.recycle();
      mTintManager = paramContext.getTintManager();
    }
  }
  
  public int getCompoundPaddingLeft()
  {
    int j = super.getCompoundPaddingLeft();
    int i = j;
    if (Build.VERSION.SDK_INT < 17)
    {
      i = j;
      if (mButtonDrawable != null) {
        i = j + mButtonDrawable.getIntrinsicWidth();
      }
    }
    return i;
  }
  
  public void setButtonDrawable(int paramInt)
  {
    if (mTintManager != null)
    {
      setButtonDrawable(mTintManager.getDrawable(paramInt));
      return;
    }
    super.setButtonDrawable(paramInt);
  }
  
  public void setButtonDrawable(Drawable paramDrawable)
  {
    super.setButtonDrawable(paramDrawable);
    mButtonDrawable = paramDrawable;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.AppCompatCheckBox
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */