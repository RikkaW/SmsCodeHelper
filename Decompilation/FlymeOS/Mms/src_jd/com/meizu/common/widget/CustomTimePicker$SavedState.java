package com.meizu.common.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.view.View.BaseSavedState;

class CustomTimePicker$SavedState
  extends View.BaseSavedState
{
  public static final Parcelable.Creator<SavedState> CREATOR = new CustomTimePicker.SavedState.1();
  private final int d;
  private final int h;
  private final int leapmonth;
  private final int lunar;
  private final int min;
  private final int mon;
  private final int y;
  
  private CustomTimePicker$SavedState(Parcel paramParcel)
  {
    super(paramParcel);
    h = paramParcel.readInt();
    min = paramParcel.readInt();
    y = paramParcel.readInt();
    mon = paramParcel.readInt();
    d = paramParcel.readInt();
    lunar = paramParcel.readInt();
    leapmonth = paramParcel.readInt();
  }
  
  private CustomTimePicker$SavedState(Parcelable paramParcelable, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean1, boolean paramBoolean2)
  {
    super(paramParcelable);
    h = paramInt1;
    min = paramInt2;
    y = paramInt3;
    mon = paramInt4;
    d = paramInt5;
    if (paramBoolean1)
    {
      paramInt1 = 1;
      lunar = paramInt1;
      if (!paramBoolean2) {
        break label67;
      }
    }
    label67:
    for (paramInt1 = i;; paramInt1 = 0)
    {
      leapmonth = paramInt1;
      return;
      paramInt1 = 0;
      break;
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(h);
    paramParcel.writeInt(min);
    paramParcel.writeInt(y);
    paramParcel.writeInt(mon);
    paramParcel.writeInt(d);
    paramParcel.writeInt(lunar);
    paramParcel.writeInt(leapmonth);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CustomTimePicker.SavedState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */