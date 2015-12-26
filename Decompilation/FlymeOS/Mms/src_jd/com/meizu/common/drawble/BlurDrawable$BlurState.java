package com.meizu.common.drawble;

import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.view.ViewDebug.ExportedProperty;

final class BlurDrawable$BlurState
  extends Drawable.ConstantState
{
  int mAlpha = 255;
  int mBaseColor = -637534209;
  @ViewDebug.ExportedProperty
  int mChangingConfigurations;
  float mLevel = 0.9F;
  Paint mPaint = new Paint();
  int mUseColor = BlurDrawable.access$000(-637534209, 255, 0.9F);
  
  BlurDrawable$BlurState(BlurState paramBlurState)
  {
    if (paramBlurState != null)
    {
      mLevel = mLevel;
      mPaint = new Paint(mPaint);
      mChangingConfigurations = mChangingConfigurations;
    }
    while (BlurDrawable.sDrawBlurRectMethod != null) {
      return;
    }
    mPaint.setColor(mUseColor);
  }
  
  public int getChangingConfigurations()
  {
    return mChangingConfigurations;
  }
  
  public Drawable newDrawable()
  {
    return new BlurDrawable(this, null);
  }
  
  public Drawable newDrawable(Resources paramResources)
  {
    return new BlurDrawable(this, null);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.drawble.BlurDrawable.BlurState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */