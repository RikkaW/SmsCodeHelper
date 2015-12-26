package com.meizu.common.preference;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.preference.Preference.BaseSavedState;

class EditTextPreference$SavedState
  extends Preference.BaseSavedState
{
  public static final Parcelable.Creator<SavedState> CREATOR = new EditTextPreference.SavedState.1();
  String text;
  
  public EditTextPreference$SavedState(Parcel paramParcel)
  {
    super(paramParcel);
    text = paramParcel.readString();
  }
  
  public EditTextPreference$SavedState(Parcelable paramParcelable)
  {
    super(paramParcelable);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeString(text);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.preference.EditTextPreference.SavedState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */