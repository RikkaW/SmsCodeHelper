package com.meizu.common.preference;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.ArrowKeyMovementMethod;
import android.text.method.DialerKeyListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.meizu.common.R.id;
import com.meizu.common.R.layout;
import com.meizu.common.R.style;
import com.meizu.common.R.styleable;

public class EditPhoneNumberPreference
  extends EditTextPreference
{
  private static final int CM_ACTIVATION = 1;
  private static final int CM_CONFIRM = 0;
  private static final String VALUE_OFF = "0";
  private static final String VALUE_ON = "1";
  private static final String VALUE_SEPARATOR = ":";
  private int mButtonClicked;
  private CharSequence mChangeNumberText;
  private boolean mChecked;
  private int mConfirmationMode;
  private Intent mContactListIntent;
  private ImageButton mContactPickButton;
  private View.OnFocusChangeListener mDialogFocusChangeListener;
  private OnDialogClosedListener mDialogOnClosedListener;
  private CharSequence mDisableText;
  private boolean mEmptyAllow = false;
  private CharSequence mEnableText;
  private String mEncodedText = null;
  private GetDefaultNumberListener mGetDefaultNumberListener;
  private Activity mParentActivity;
  private String mPhoneNumber;
  private int mPrefId;
  private CharSequence mSummaryOff;
  private CharSequence mSummaryOn;
  private TextWatcher watcher = new EditPhoneNumberPreference.1(this);
  
  public EditPhoneNumberPreference(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public EditPhoneNumberPreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setDialogLayoutResource(R.layout.mc_preference_editphonenumber);
    mContactListIntent = new Intent("android.intent.action.GET_CONTENT");
    mContactListIntent.setType("vnd.android.cursor.item/phone_v2");
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.EditPhoneNumberPreference, 0, R.style.Widget_MeizuCommon_EditPhoneNumberPreference);
    mEnableText = localTypedArray.getString(R.styleable.EditPhoneNumberPreference_mcEnableButtonText);
    mDisableText = localTypedArray.getString(R.styleable.EditPhoneNumberPreference_mcDisableButtonText);
    mChangeNumberText = localTypedArray.getString(R.styleable.EditPhoneNumberPreference_mcChangeNumButtonText);
    mConfirmationMode = localTypedArray.getInt(R.styleable.EditPhoneNumberPreference_mcConfirmMode, 0);
    localTypedArray.recycle();
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.SwitchPreference, 0, 0);
    mSummaryOn = paramContext.getString(R.styleable.SwitchPreference_mcSummaryOn);
    mSummaryOff = paramContext.getString(R.styleable.SwitchPreference_mcSummaryOff);
    paramContext.recycle();
  }
  
  public String getPhoneNumber()
  {
    return PhoneNumberUtils.stripSeparators(mPhoneNumber);
  }
  
  protected String getRawPhoneNumber()
  {
    return mPhoneNumber;
  }
  
  protected String getStringValue()
  {
    if (mConfirmationMode == 1)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      if (isToggled()) {}
      for (String str = "1";; str = "0") {
        return str + ":" + getPhoneNumber();
      }
    }
    return getPhoneNumber();
  }
  
  public CharSequence getSummaryOff()
  {
    return mSummaryOff;
  }
  
  public CharSequence getSummaryOn()
  {
    return mSummaryOn;
  }
  
  public boolean isEmptyAllow()
  {
    return mEmptyAllow;
  }
  
  public boolean isToggled()
  {
    return mChecked;
  }
  
  protected void onAddEditTextToDialogView(View paramView, EditText paramEditText)
  {
    paramView = (ViewGroup)paramView.findViewById(R.id.mc_edit_container);
    if (paramView != null) {
      paramView.addView(paramEditText, -1, -2);
    }
  }
  
  protected void onBindDialogView(View paramView)
  {
    mButtonClicked = -2;
    super.onBindDialogView(paramView);
    paramView = getEditText();
    if (paramView != null)
    {
      if (mGetDefaultNumberListener != null)
      {
        String str = mGetDefaultNumberListener.onGetDefaultNumber(this);
        if (str != null) {
          mPhoneNumber = str;
        }
      }
      paramView.setText(mPhoneNumber);
      if (paramView.length() > 0) {
        paramView.setSelection(0, paramView.length());
      }
      paramView.setMovementMethod(ArrowKeyMovementMethod.getInstance());
      paramView.setKeyListener(DialerKeyListener.getInstance());
      paramView.setOnFocusChangeListener(mDialogFocusChangeListener);
      paramView.addTextChangedListener(watcher);
    }
  }
  
  protected void onBindView(View paramView)
  {
    super.onBindView(paramView);
    TextView localTextView = (TextView)paramView.findViewById(16908304);
    if (localTextView != null)
    {
      if (mConfirmationMode != 1) {
        break label103;
      }
      if (!mChecked) {
        break label80;
      }
      if (mSummaryOn != null) {
        break label72;
      }
      paramView = getSummary();
      if (paramView == null) {
        break label111;
      }
      localTextView.setText(paramView);
    }
    label72:
    label80:
    label103:
    label111:
    for (int i = 0;; i = 8)
    {
      if (i != localTextView.getVisibility()) {
        localTextView.setVisibility(i);
      }
      return;
      paramView = mSummaryOn;
      break;
      if (mSummaryOff == null)
      {
        paramView = getSummary();
        break;
      }
      paramView = mSummaryOff;
      break;
      paramView = getSummary();
      break;
    }
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    boolean bool = true;
    if ((mConfirmationMode == 1) && (paramInt == -3)) {
      if (isToggled()) {
        break label41;
      }
    }
    for (;;)
    {
      setToggled(bool);
      mButtonClicked = paramInt;
      super.onClick(paramDialogInterface, paramInt);
      return;
      label41:
      bool = false;
    }
  }
  
  protected void onDialogClosed(boolean paramBoolean)
  {
    if ((mButtonClicked == -1) || (mButtonClicked == -3))
    {
      setPhoneNumber(getEditText().getText().toString());
      super.onDialogClosed(paramBoolean);
      setText(getStringValue());
    }
    for (;;)
    {
      if (mDialogOnClosedListener != null) {
        mDialogOnClosedListener.onDialogClosed(this, mButtonClicked);
      }
      return;
      super.onDialogClosed(paramBoolean);
    }
  }
  
  public void onPickActivityResult(String paramString)
  {
    EditText localEditText = getEditText();
    if (localEditText != null) {
      localEditText.setText(paramString);
    }
  }
  
  protected void onPrepareDialogBuilder(AlertDialog.Builder paramBuilder)
  {
    if (mConfirmationMode == 1)
    {
      if (mChecked)
      {
        paramBuilder.setPositiveButton(mChangeNumberText, this);
        paramBuilder.setNeutralButton(mDisableText, this);
      }
    }
    else {
      return;
    }
    paramBuilder.setPositiveButton(null, null);
    paramBuilder.setNeutralButton(mEnableText, this);
  }
  
  protected void onSetInitialValue(boolean paramBoolean, Object paramObject)
  {
    if (paramBoolean) {}
    for (paramObject = getPersistedString(getStringValue());; paramObject = (String)paramObject)
    {
      setValueFromString((String)paramObject);
      return;
    }
  }
  
  protected boolean persistString(String paramString)
  {
    mEncodedText = paramString;
    return super.persistString(paramString);
  }
  
  public EditPhoneNumberPreference setConfirmationMode(int paramInt)
  {
    mConfirmationMode = paramInt;
    notifyChanged();
    return this;
  }
  
  public void setDialogOnClosedListener(OnDialogClosedListener paramOnDialogClosedListener)
  {
    mDialogOnClosedListener = paramOnDialogClosedListener;
  }
  
  public void setDialogOnFocusChangeListener(View.OnFocusChangeListener paramOnFocusChangeListener)
  {
    mDialogFocusChangeListener = paramOnFocusChangeListener;
  }
  
  public void setEmptyAllow(boolean paramBoolean)
  {
    mEmptyAllow = paramBoolean;
  }
  
  public void setParentActivity(Activity paramActivity, int paramInt)
  {
    mParentActivity = paramActivity;
    mPrefId = paramInt;
    mGetDefaultNumberListener = null;
  }
  
  public void setParentActivity(Activity paramActivity, int paramInt, GetDefaultNumberListener paramGetDefaultNumberListener)
  {
    mParentActivity = paramActivity;
    mPrefId = paramInt;
    mGetDefaultNumberListener = paramGetDefaultNumberListener;
  }
  
  public EditPhoneNumberPreference setPhoneNumber(String paramString)
  {
    mPhoneNumber = paramString;
    setText(getStringValue());
    notifyChanged();
    return this;
  }
  
  public EditPhoneNumberPreference setSummaryOff(int paramInt)
  {
    return setSummaryOff(getContext().getString(paramInt));
  }
  
  public EditPhoneNumberPreference setSummaryOff(CharSequence paramCharSequence)
  {
    mSummaryOff = paramCharSequence;
    if (!isToggled()) {
      notifyChanged();
    }
    return this;
  }
  
  public EditPhoneNumberPreference setSummaryOn(int paramInt)
  {
    return setSummaryOn(getContext().getString(paramInt));
  }
  
  public EditPhoneNumberPreference setSummaryOn(CharSequence paramCharSequence)
  {
    mSummaryOn = paramCharSequence;
    if (isToggled()) {
      notifyChanged();
    }
    return this;
  }
  
  public EditPhoneNumberPreference setToggled(boolean paramBoolean)
  {
    mChecked = paramBoolean;
    setText(getStringValue());
    notifyChanged();
    return this;
  }
  
  protected void setValueFromString(String paramString)
  {
    if (mConfirmationMode == 1)
    {
      paramString = paramString.split(":", 2);
      setToggled(paramString[0].equals("1"));
      setPhoneNumber(paramString[1]);
      return;
    }
    setPhoneNumber(paramString);
  }
  
  public boolean shouldDisableDependents()
  {
    boolean bool = true;
    if ((mConfirmationMode == 1) && (mEncodedText != null)) {
      bool = mEncodedText.split(":", 2)[0].equals("1");
    }
    while ((TextUtils.isEmpty(mPhoneNumber)) && (mConfirmationMode == 0)) {
      return bool;
    }
    return false;
  }
  
  protected void showDialog(Bundle paramBundle)
  {
    super.showDialog(paramBundle);
    if ((TextUtils.isEmpty(mPhoneNumber)) && (getDialog() != null) && (!isEmptyAllow()))
    {
      paramBundle = (AlertDialog)getDialog();
      if (mConfirmationMode != 1) {
        break label58;
      }
    }
    label58:
    for (int i = -3;; i = -1)
    {
      paramBundle.getButton(i).setEnabled(false);
      return;
    }
  }
  
  public void showPhoneNumberDialog()
  {
    showDialog(null);
  }
  
  public static abstract interface GetDefaultNumberListener
  {
    public abstract String onGetDefaultNumber(EditPhoneNumberPreference paramEditPhoneNumberPreference);
  }
  
  public static abstract interface OnDialogClosedListener
  {
    public abstract void onDialogClosed(EditPhoneNumberPreference paramEditPhoneNumberPreference, int paramInt);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.preference.EditPhoneNumberPreference
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */