package com.meizu.common.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.preference.Preference;
import android.preference.TwoStatePreference;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import com.meizu.common.R.attr;
import com.meizu.common.R.id;
import com.meizu.common.R.styleable;
import com.meizu.common.widget.Switch;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class SwitchPreference
  extends TwoStatePreference
{
  private static Method sOnPreferenceChanged;
  private static Field sRecycle;
  private final Listener mListener = new Listener(null);
  private CharSequence mSwitchOff;
  private CharSequence mSwitchOn;
  boolean mUseAnim = true;
  
  public SwitchPreference(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public SwitchPreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.MeizuCommon_SwitchPreferenceStyle);
  }
  
  public SwitchPreference(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    for (;;)
    {
      try
      {
        if (sRecycle == null)
        {
          if (Build.VERSION.SDK_INT < 19) {
            continue;
          }
          sRecycle = Preference.class.getDeclaredField("mCanRecycleLayout");
          sRecycle.setAccessible(true);
        }
        localField = sRecycle;
        if (Build.VERSION.SDK_INT < 19) {
          continue;
        }
      }
      catch (Exception localException)
      {
        Field localField;
        localException.printStackTrace();
        continue;
        bool = false;
        continue;
      }
      localField.set(this, Boolean.valueOf(bool));
      paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.SwitchPreference, paramInt, 0).recycle();
      return;
      sRecycle = Preference.class.getDeclaredField("mHasSpecifiedLayout");
    }
  }
  
  private void performPreferenceChanged()
  {
    try
    {
      if (sOnPreferenceChanged == null)
      {
        sOnPreferenceChanged = SwitchPreference.class.getMethod("onPreferenceChange", new Class[0]);
        sOnPreferenceChanged.setAccessible(true);
      }
      sOnPreferenceChanged.invoke(this, new Object[0]);
      return;
    }
    catch (Exception localException) {}
  }
  
  public CharSequence getSwitchTextOff()
  {
    return mSwitchOff;
  }
  
  public CharSequence getSwitchTextOn()
  {
    return mSwitchOn;
  }
  
  protected void onBindView(View paramView)
  {
    super.onBindView(paramView);
    View localView = paramView.findViewById(R.id.switchWidget);
    Object localObject1;
    if ((localView != null) && ((localView instanceof Checkable)))
    {
      if ((localView instanceof Switch))
      {
        Switch localSwitch = (Switch)localView;
        localSwitch.setOnCheckedChangeListener(null);
        Object localObject2 = paramView.findViewById(16908312);
        if (localObject2 != null)
        {
          if (!isChecked()) {
            break label211;
          }
          localObject1 = switchOn;
          ((View)localObject2).setContentDescription((CharSequence)localObject1);
        }
        localObject2 = new String();
        localObject1 = localObject2;
        if (getTitle() != null) {
          localObject1 = (String)localObject2 + getTitle() + ",";
        }
        localObject2 = localObject1;
        if (getSummary() != null) {
          localObject2 = (String)localObject1 + getSummary() + ",";
        }
        localSwitch.setContentDescription((CharSequence)localObject2);
      }
      if (!(localView instanceof Switch)) {
        break label223;
      }
      localObject1 = (Switch)localView;
      ((Switch)localObject1).setChecked(isChecked(), mUseAnim);
      mUseAnim = true;
      ((Switch)localObject1).setOnCheckedChangeListener(mListener);
    }
    for (;;)
    {
      syncSummaryView(paramView);
      return;
      label211:
      localObject1 = switchOff;
      break;
      label223:
      ((Checkable)localView).setChecked(isChecked());
    }
  }
  
  public void setChecked(boolean paramBoolean)
  {
    setChecked(paramBoolean, true);
  }
  
  public void setChecked(boolean paramBoolean1, boolean paramBoolean2)
  {
    super.setChecked(paramBoolean1);
    mUseAnim = paramBoolean2;
  }
  
  public void setSwitchTextOff(int paramInt)
  {
    setSwitchTextOff(getContext().getString(paramInt));
  }
  
  public void setSwitchTextOff(CharSequence paramCharSequence)
  {
    mSwitchOff = paramCharSequence;
    notifyChanged();
  }
  
  public void setSwitchTextOn(int paramInt)
  {
    setSwitchTextOn(getContext().getString(paramInt));
  }
  
  public void setSwitchTextOn(CharSequence paramCharSequence)
  {
    mSwitchOn = paramCharSequence;
    notifyChanged();
  }
  
  void syncSummaryView(View paramView)
  {
    int j = 0;
    paramView = (TextView)paramView.findViewById(16908304);
    if (paramView != null)
    {
      CharSequence localCharSequence = getSummary();
      if (TextUtils.isEmpty(localCharSequence)) {
        break label64;
      }
      paramView.setText(localCharSequence);
    }
    label64:
    for (int i = 0;; i = 1)
    {
      if (i == 0) {}
      for (i = j;; i = 8)
      {
        if (i != paramView.getVisibility()) {
          paramView.setVisibility(i);
        }
        return;
      }
    }
  }
  
  class Listener
    implements CompoundButton.OnCheckedChangeListener
  {
    private Listener() {}
    
    public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
    {
      if (!callChangeListener(Boolean.valueOf(paramBoolean)))
      {
        if (!paramBoolean) {}
        for (paramBoolean = true;; paramBoolean = false)
        {
          paramCompoundButton.setChecked(paramBoolean);
          return;
        }
      }
      setChecked(paramBoolean);
      SwitchPreference.this.performPreferenceChanged();
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.preference.SwitchPreference
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */