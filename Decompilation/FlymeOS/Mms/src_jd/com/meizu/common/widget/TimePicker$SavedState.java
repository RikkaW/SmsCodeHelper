package com.meizu.common.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.view.View.BaseSavedState;

class TimePicker$SavedState
  extends View.BaseSavedState
{
  public static final Parcelable.Creator<SavedState> CREATOR = new TimePicker.SavedState.1();
  private final int mHour;
  private final int mMinute;
  
  private TimePicker$SavedState(Parcel paramParcel)
  {
    super(paramParcel);
    mHour = paramParcel.readInt();
    mMinute = paramParcel.readInt();
  }
  
  private TimePicker$SavedState(Parcelable paramParcelable, int paramInt1, int paramInt2)
  {
    super(paramParcelable);
    mHour = paramInt1;
    mMinute = paramInt2;
  }
  
  public int getHour()
  {
    return mHour;
  }
  
  public int getMinute()
  {
    return mMinute;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(mHour);
    paramParcel.writeInt(mMinute);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.TimePicker.SavedState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */