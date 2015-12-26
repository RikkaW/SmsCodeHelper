package com.meizu.common.preference;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import com.meizu.common.R.id;
import com.meizu.common.R.layout;

public class SeekBarPreference
  extends Preference
{
  protected SeekBar mSeekBar;
  
  public SeekBarPreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public SeekBarPreference(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setLayoutResource(R.layout.mz_preference_seekbar);
  }
  
  public SeekBar getSeekBar()
  {
    return mSeekBar;
  }
  
  public void onBindView(View paramView)
  {
    super.onBindView(paramView);
    mSeekBar = ((SeekBar)paramView.findViewById(R.id.seekbar));
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.preference.SeekBarPreference
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */