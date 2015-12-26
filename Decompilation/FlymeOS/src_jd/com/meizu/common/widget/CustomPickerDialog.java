package com.meizu.common.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import com.meizu.common.R.string;

public class CustomPickerDialog
  extends AlertDialog
  implements DialogInterface.OnClickListener
{
  private static final String KEY_CURRENT_ITEMS = "key_current_items";
  private final OnValueSetListener mCallback;
  private final CustomPicker mPicker;
  private DialogInterface.OnClickListener mPositiveBtnClickListener;
  
  public CustomPickerDialog(Context paramContext, int paramInt1, OnValueSetListener paramOnValueSetListener, int paramInt2, CustomPicker.ColumnData... paramVarArgs)
  {
    super(paramContext, paramInt1);
    mCallback = paramOnValueSetListener;
    super.setButton(-1, paramContext.getText(R.string.mc_yes), this);
    super.setButton(-2, paramContext.getText(R.string.mc_cancel), (DialogInterface.OnClickListener)null);
    mPicker = new CustomPicker(paramContext, paramInt2, paramVarArgs);
    setView(mPicker);
  }
  
  public CustomPickerDialog(Context paramContext, OnValueSetListener paramOnValueSetListener, int paramInt, CustomPicker.ColumnData... paramVarArgs)
  {
    this(paramContext, 0, paramOnValueSetListener, paramInt, paramVarArgs);
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (mCallback != null)
    {
      mPicker.clearFocus();
      mCallback.onValueSet(mPicker, mPicker.getCurrentItems());
    }
    if (mPositiveBtnClickListener != null) {
      mPositiveBtnClickListener.onClick(paramDialogInterface, paramInt);
    }
  }
  
  public void onRestoreInstanceState(Bundle paramBundle)
  {
    super.onRestoreInstanceState(paramBundle);
    paramBundle = paramBundle.getIntArray("key_current_items");
    mPicker.updateCurrentItems(paramBundle);
  }
  
  public Bundle onSaveInstanceState()
  {
    Bundle localBundle = super.onSaveInstanceState();
    localBundle.putIntArray("key_current_items", mPicker.getCurrentItems());
    return localBundle;
  }
  
  public void setButton(int paramInt, CharSequence paramCharSequence, DialogInterface.OnClickListener paramOnClickListener)
  {
    Object localObject = paramOnClickListener;
    if (paramInt == -1)
    {
      mPositiveBtnClickListener = paramOnClickListener;
      localObject = this;
    }
    super.setButton(paramInt, paramCharSequence, (DialogInterface.OnClickListener)localObject);
  }
  
  public void setCurrentItem(int... paramVarArgs)
  {
    if ((mPicker == null) || (paramVarArgs == null)) {}
    for (;;)
    {
      return;
      int i = 0;
      while (i < paramVarArgs.length)
      {
        mPicker.setCurrentItem(i, paramVarArgs[i]);
        i += 1;
      }
    }
  }
  
  public void setOnCurrentItemChangedListener(CustomPicker.OnCurrentItemChangedListener paramOnCurrentItemChangedListener)
  {
    if (mPicker != null) {
      mPicker.setOnCurrentItemChangedListener(paramOnCurrentItemChangedListener);
    }
  }
  
  public void setTextColor(int paramInt1, int paramInt2, int paramInt3)
  {
    mPicker.setTextColor(paramInt1, paramInt2, paramInt3);
  }
  
  public void updateCurrentItems(int... paramVarArgs)
  {
    mPicker.updateCurrentItems(paramVarArgs);
  }
  
  public static abstract interface OnValueSetListener
  {
    public abstract void onValueSet(CustomPicker paramCustomPicker, int... paramVarArgs);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CustomPickerDialog
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */