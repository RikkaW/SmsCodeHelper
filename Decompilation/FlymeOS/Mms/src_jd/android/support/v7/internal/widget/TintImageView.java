package android.support.v7.internal.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class TintImageView
  extends ImageView
{
  private static final int[] TINT_ATTRS = { 16842964, 16843033 };
  private final TintManager mTintManager;
  
  public TintImageView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public TintImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public TintImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = TintTypedArray.obtainStyledAttributes(getContext(), paramAttributeSet, TINT_ATTRS, paramInt, 0);
    if (paramContext.length() > 0)
    {
      if (paramContext.hasValue(0)) {
        setBackgroundDrawable(paramContext.getDrawable(0));
      }
      if (paramContext.hasValue(1)) {
        setImageDrawable(paramContext.getDrawable(1));
      }
    }
    paramContext.recycle();
    mTintManager = paramContext.getTintManager();
  }
  
  public void setImageResource(int paramInt)
  {
    setImageDrawable(mTintManager.getDrawable(paramInt));
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.TintImageView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */