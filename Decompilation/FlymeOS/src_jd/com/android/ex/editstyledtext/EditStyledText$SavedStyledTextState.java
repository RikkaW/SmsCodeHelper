package com.android.ex.editstyledtext;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View.BaseSavedState;

public class EditStyledText$SavedStyledTextState
  extends View.BaseSavedState
{
  public int a;
  
  EditStyledText$SavedStyledTextState(Parcelable paramParcelable)
  {
    super(paramParcelable);
  }
  
  public String toString()
  {
    return "EditStyledText.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " bgcolor=" + a + "}";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(a);
  }
}

/* Location:
 * Qualified Name:     com.android.ex.editstyledtext.EditStyledText.SavedStyledTextState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */