package com.meizu.common.preference;

import android.content.Context;
import android.content.res.Resources;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import com.meizu.common.R.dimen;

public class RingtonePreference
  extends android.preference.RingtonePreference
{
  private static final String TAG = "com.meizu.common.preference.RingtonePreference";
  
  public RingtonePreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  protected View onCreateView(ViewGroup paramViewGroup)
  {
    paramViewGroup = super.onCreateView(paramViewGroup);
    if (paramViewGroup != null)
    {
      ViewGroup localViewGroup = (ViewGroup)paramViewGroup.findViewById(16908312);
      if (localViewGroup != null)
      {
        Object localObject = localViewGroup.getLayoutParams();
        if ((localObject instanceof ViewGroup.MarginLayoutParams))
        {
          localObject = (ViewGroup.MarginLayoutParams)localObject;
          rightMargin = 0;
          width = getContext().getResources().getDimensionPixelSize(R.dimen.mz_preference_widget_icon_width);
          localViewGroup.setLayoutParams((ViewGroup.LayoutParams)localObject);
        }
      }
    }
    return paramViewGroup;
  }
  
  protected Uri onRestoreRingtone()
  {
    return RingtoneManager.getActualDefaultRingtoneUri(getContext(), getRingtoneType());
  }
  
  protected void onSaveRingtone(Uri paramUri)
  {
    RingtoneManager.setActualDefaultRingtoneUri(getContext(), getRingtoneType(), paramUri);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.preference.RingtonePreference
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */