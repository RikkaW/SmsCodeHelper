package com.meizu.common.preference;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.preference.Preference;
import android.preference.Preference.BaseSavedState;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;
import com.meizu.common.R.color;
import com.meizu.common.R.id;
import com.meizu.common.R.layout;

public class EditTextPreference
  extends Preference
  implements View.OnAttachStateChangeListener, View.OnFocusChangeListener
{
  private static EditTextPreference focusClass;
  private boolean isFocus = false;
  private EditText mEditText;
  private LinearLayout.LayoutParams mParams = null;
  private String mText = "";
  
  public EditTextPreference(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public EditTextPreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public EditTextPreference(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setLayoutResource(R.layout.mz_preference_edittext);
    mEditText = new EditText(paramContext, paramAttributeSet);
    initEditText(mEditText, paramContext);
    setPersistent(false);
    paramInt = (int)getResourcesgetDisplayMetricsdensity;
    mParams = new LinearLayout.LayoutParams(-1, -1);
    mParams.leftMargin = (paramInt * 10);
    mParams.rightMargin = (paramInt * 28);
  }
  
  private void initEditText(EditText paramEditText, Context paramContext)
  {
    paramContext = paramContext.getResources();
    paramEditText.setId(16908291);
    paramEditText.setTextSize(16.0F);
    paramEditText.setHintTextColor(paramContext.getColor(R.color.mz_edittext_preference_hint_text_color));
    paramEditText.setBackgroundDrawable(null);
    paramEditText.setPadding(0, 0, 0, 0);
    paramEditText.setEnabled(true);
    paramEditText.setFocusable(true);
    paramEditText.setClickable(true);
    paramEditText.setFocusableInTouchMode(true);
    paramEditText.setIncludeFontPadding(false);
    paramEditText.setOnFocusChangeListener(this);
    paramEditText.addOnAttachStateChangeListener(this);
    paramEditText.setImeOptions(33554432);
    paramEditText.setPrivateImeOptions("com.meizu.input.candidateAlwaysVisible");
    if (!isPasswordInputType(paramEditText.getInputType())) {
      paramEditText.setSingleLine(true);
    }
  }
  
  private static boolean isPasswordInputType(int paramInt)
  {
    paramInt &= 0xFFF;
    return (paramInt == 129) || (paramInt == 225) || (paramInt == 18);
  }
  
  public EditText getEditText()
  {
    return mEditText;
  }
  
  public String getText()
  {
    String str = mEditText.getText().toString();
    if ((!str.equals(mText)) && (callChangeListener(str))) {
      setText(str);
    }
    return mText;
  }
  
  public void onBindView(View paramView)
  {
    super.onBindView(paramView);
  }
  
  protected View onCreateView(ViewGroup paramViewGroup)
  {
    paramViewGroup = super.onCreateView(paramViewGroup);
    Object localObject = mEditText.getParent();
    if (localObject != paramViewGroup)
    {
      if (localObject != null) {
        ((ViewGroup)localObject).removeView(mEditText);
      }
      localObject = (ViewGroup)paramViewGroup.findViewById(R.id.edittext_container);
      if (localObject != null) {
        ((ViewGroup)localObject).addView(mEditText, mParams);
      }
    }
    return paramViewGroup;
  }
  
  public void onDependencyChanged(Preference paramPreference, boolean paramBoolean)
  {
    super.onDependencyChanged(paramPreference, paramBoolean);
    paramPreference = mEditText;
    if (!paramBoolean) {}
    for (boolean bool = true;; bool = false)
    {
      paramPreference.setFocusableInTouchMode(bool);
      if (paramBoolean)
      {
        paramPreference = (InputMethodManager)getContext().getSystemService("input_method");
        if (paramPreference != null) {
          paramPreference.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
        }
      }
      return;
    }
  }
  
  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    isFocus = false;
    if (paramBoolean == true)
    {
      focusClass = this;
      isFocus = true;
    }
    do
    {
      return;
      paramView = mEditText.getText().toString();
    } while (!callChangeListener(paramView));
    setText(paramView);
  }
  
  protected Object onGetDefaultValue(TypedArray paramTypedArray, int paramInt)
  {
    return paramTypedArray.getString(paramInt);
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if ((paramParcelable == null) || (!paramParcelable.getClass().equals(SavedState.class)))
    {
      super.onRestoreInstanceState(paramParcelable);
      return;
    }
    paramParcelable = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    setText(text);
  }
  
  protected Parcelable onSaveInstanceState()
  {
    Object localObject = super.onSaveInstanceState();
    if (isPersistent()) {
      return (Parcelable)localObject;
    }
    localObject = new SavedState((Parcelable)localObject);
    text = getText();
    return (Parcelable)localObject;
  }
  
  protected void onSetInitialValue(boolean paramBoolean, Object paramObject)
  {
    if (paramBoolean) {}
    for (paramObject = getPersistedString(mText);; paramObject = (String)paramObject)
    {
      setText((String)paramObject);
      return;
    }
  }
  
  public void onViewAttachedToWindow(View paramView)
  {
    if (!TextUtils.equals(mEditText.getText(), mText)) {
      mEditText.setText(mText);
    }
    if (isFocus == true)
    {
      focusClass = this;
      mEditText.requestFocus();
    }
  }
  
  public void onViewDetachedFromWindow(View paramView)
  {
    if (focusClass == this)
    {
      isFocus = true;
      focusClass = null;
      return;
    }
    isFocus = false;
  }
  
  public void setAutoShowSoftInput(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      mEditText.requestFocus();
      InputMethodManager localInputMethodManager = (InputMethodManager)mEditText.getContext().getSystemService("input_method");
      if (localInputMethodManager != null) {
        localInputMethodManager.toggleSoftInput(0, 2);
      }
    }
  }
  
  public void setDialogTitle(int paramInt)
  {
    setDialogTitle(getContext().getString(paramInt));
  }
  
  public void setDialogTitle(CharSequence paramCharSequence)
  {
    super.setTitle((String)paramCharSequence);
  }
  
  public void setSummary(int paramInt)
  {
    setSummary(getContext().getString(paramInt));
  }
  
  public void setSummary(CharSequence paramCharSequence)
  {
    setText((String)paramCharSequence);
  }
  
  public void setText(String paramString)
  {
    boolean bool1 = shouldDisableDependents();
    mText = paramString;
    persistString(paramString);
    if ((mEditText != null) && (paramString != null) && (!paramString.equals(mEditText.getText().toString())))
    {
      mEditText.setText(paramString);
      if (paramString.length() > 0) {
        mEditText.setSelection(paramString.length());
      }
    }
    boolean bool2 = shouldDisableDependents();
    if (bool2 != bool1) {
      notifyDependencyChange(bool2);
    }
  }
  
  public boolean shouldDisableDependents()
  {
    return (TextUtils.isEmpty(mText)) || (super.shouldDisableDependents());
  }
  
  static class SavedState
    extends Preference.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new EditTextPreference.SavedState.1();
    String text;
    
    public SavedState(Parcel paramParcel)
    {
      super();
      text = paramParcel.readString();
    }
    
    public SavedState(Parcelable paramParcelable)
    {
      super();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeString(text);
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.preference.EditTextPreference
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */