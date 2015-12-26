package com.meizu.common.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.view.View.BaseSavedState;

class CustomPicker$SavedState
  extends View.BaseSavedState
{
  public static final Parcelable.Creator<SavedState> CREATOR = new CustomPicker.SavedState.1();
  private final int[] mSaveCurrentItems;
  
  private CustomPicker$SavedState(Parcel paramParcel)
  {
    super(paramParcel);
    mSaveCurrentItems = paramParcel.createIntArray();
  }
  
  private CustomPicker$SavedState(Parcelable paramParcelable, int... paramVarArgs)
  {
    super(paramParcelable);
    mSaveCurrentItems = ((int[])paramVarArgs.clone());
  }
  
  public int[] getCurrentItems()
  {
    return mSaveCurrentItems;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeIntArray(mSaveCurrentItems);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CustomPicker.SavedState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */