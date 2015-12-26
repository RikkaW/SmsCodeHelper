package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.internal.text.AllCapsTransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;

public class AppCompatTextView
  extends TextView
{
  public AppCompatTextView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public AppCompatTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 16842884);
  }
  
  public AppCompatTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.AppCompatTextView, paramInt, 0);
    int i = localTypedArray.getResourceId(R.styleable.AppCompatTextView_android_textAppearance, -1);
    localTypedArray.recycle();
    if (i != -1)
    {
      localTypedArray = paramContext.obtainStyledAttributes(i, R.styleable.TextAppearance);
      if (localTypedArray.hasValue(R.styleable.TextAppearance_textAllCaps)) {
        setAllCaps(localTypedArray.getBoolean(R.styleable.TextAppearance_textAllCaps, false));
      }
      localTypedArray.recycle();
    }
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.AppCompatTextView, paramInt, 0);
    if (paramContext.hasValue(R.styleable.AppCompatTextView_textAllCaps)) {
      setAllCaps(paramContext.getBoolean(R.styleable.AppCompatTextView_textAllCaps, false));
    }
    paramContext.recycle();
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
 * Qualified Name:     android.support.v7.widget.AppCompatTextView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */