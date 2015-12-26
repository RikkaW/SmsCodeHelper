package android.support.v7.internal.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

class SpinnerCompat$SavedState
  extends AbsSpinnerCompat.SavedState
{
  public static final Parcelable.Creator<SavedState> CREATOR = new SpinnerCompat.SavedState.1();
  boolean showDropdown;
  
  private SpinnerCompat$SavedState(Parcel paramParcel)
  {
    super(paramParcel);
    if (paramParcel.readByte() != 0) {}
    for (boolean bool = true;; bool = false)
    {
      showDropdown = bool;
      return;
    }
  }
  
  SpinnerCompat$SavedState(Parcelable paramParcelable)
  {
    super(paramParcelable);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    if (showDropdown) {}
    for (paramInt = 1;; paramInt = 0)
    {
      paramParcel.writeByte((byte)paramInt);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.SpinnerCompat.SavedState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */