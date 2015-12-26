package com.android.mms.ui;

import abe;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import zc;
import zv;

public class SmsCenterNumberEditTextPreference
  extends EditTextPreference
{
  TextWatcher a = new zc(this);
  private Button b;
  private int c = -1;
  
  public SmsCenterNumberEditTextPreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setDialogTitle(2131493338);
    getEditText().setInputType(3);
    getEditText().setFilters(new InputFilter[] { new InputFilter.LengthFilter(20) });
  }
  
  protected void onDialogClosed(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      String str1 = getEditText().getText().toString();
      String str2 = abe.a(c);
      if (!str1.equals(str2))
      {
        Log.d("SmsCenterNumberEditTextPreference", "new SC address : " + str1 + ", old SC address : " + str2);
        abe.a(str1, c);
      }
    }
    getEditText().removeTextChangedListener(a);
    super.onDialogClosed(paramBoolean);
  }
  
  protected void onPrepareDialogBuilder(AlertDialog.Builder paramBuilder)
  {
    super.onPrepareDialogBuilder(paramBuilder);
    paramBuilder = getKey();
    if (zv.e > 1)
    {
      if (paramBuilder.equals("pref_key_sim1_center")) {
        c = 0;
      }
      while (!paramBuilder.equals("pref_key_sim2_center")) {
        return;
      }
      c = 1;
      return;
    }
    if (zv.e == 1)
    {
      c = zv.i();
      return;
    }
    c = 0;
  }
  
  protected void showDialog(Bundle paramBundle)
  {
    super.showDialog(paramBundle);
    b = ((AlertDialog)getDialog()).getButton(-1);
    b.setEnabled(false);
    getEditText().setText(abe.a(c));
    getEditText().setSelectAllOnFocus(true);
    getEditText().addTextChangedListener(a);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SmsCenterNumberEditTextPreference
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */