package com.android.mms.view;

import acy;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.view.View.BaseSavedState;

public class CustomTimePicker$SavedState
  extends View.BaseSavedState
{
  public static final Parcelable.Creator<SavedState> CREATOR = new acy();
  private final int a;
  private final int b;
  private final int c;
  private final int d;
  private final int e;
  private final int f;
  private final int g;
  
  private CustomTimePicker$SavedState(Parcel paramParcel)
  {
    super(paramParcel);
    a = paramParcel.readInt();
    b = paramParcel.readInt();
    c = paramParcel.readInt();
    d = paramParcel.readInt();
    e = paramParcel.readInt();
    f = paramParcel.readInt();
    g = paramParcel.readInt();
  }
  
  private CustomTimePicker$SavedState(Parcelable paramParcelable, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean1, boolean paramBoolean2)
  {
    super(paramParcelable);
    a = paramInt1;
    b = paramInt2;
    c = paramInt3;
    d = paramInt4;
    e = paramInt5;
    if (paramBoolean1)
    {
      paramInt1 = 1;
      f = paramInt1;
      if (!paramBoolean2) {
        break label67;
      }
    }
    label67:
    for (paramInt1 = i;; paramInt1 = 0)
    {
      g = paramInt1;
      return;
      paramInt1 = 0;
      break;
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(a);
    paramParcel.writeInt(b);
    paramParcel.writeInt(c);
    paramParcel.writeInt(d);
    paramParcel.writeInt(e);
    paramParcel.writeInt(f);
    paramParcel.writeInt(g);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.CustomTimePicker.SavedState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */