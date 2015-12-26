package com.meizu.common.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.view.View.BaseSavedState;

class DatePicker$SavedState
  extends View.BaseSavedState
{
  public static final Parcelable.Creator<SavedState> CREATOR = new DatePicker.SavedState.1();
  private final int mDay;
  private final int mMonth;
  private final int mYear;
  
  private DatePicker$SavedState(Parcel paramParcel)
  {
    super(paramParcel);
    mYear = paramParcel.readInt();
    mMonth = paramParcel.readInt();
    mDay = paramParcel.readInt();
  }
  
  private DatePicker$SavedState(Parcelable paramParcelable, int paramInt1, int paramInt2, int paramInt3)
  {
    super(paramParcelable);
    mYear = paramInt1;
    mMonth = paramInt2;
    mDay = paramInt3;
  }
  
  public int getDay()
  {
    return mDay;
  }
  
  public int getMonth()
  {
    return mMonth;
  }
  
  public int getYear()
  {
    return mYear;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(mYear);
    paramParcel.writeInt(mMonth);
    paramParcel.writeInt(mDay);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.DatePicker.SavedState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */