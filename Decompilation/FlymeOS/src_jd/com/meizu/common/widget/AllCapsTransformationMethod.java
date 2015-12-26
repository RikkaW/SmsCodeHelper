package com.meizu.common.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import java.util.Locale;

class AllCapsTransformationMethod
  implements TransformationMethod2
{
  private static final String TAG = "AllCapsTransformationMethod";
  private boolean mEnabled;
  private Locale mLocale;
  
  public AllCapsTransformationMethod(Context paramContext)
  {
    mLocale = getResourcesgetConfigurationlocale;
  }
  
  public CharSequence getTransformation(CharSequence paramCharSequence, View paramView)
  {
    if (mEnabled)
    {
      if (paramCharSequence != null) {
        return paramCharSequence.toString().toUpperCase(mLocale);
      }
      return null;
    }
    return paramCharSequence;
  }
  
  public void onFocusChanged(View paramView, CharSequence paramCharSequence, boolean paramBoolean, int paramInt, Rect paramRect) {}
  
  public void setLengthChangesAllowed(boolean paramBoolean)
  {
    mEnabled = paramBoolean;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.AllCapsTransformationMethod
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */