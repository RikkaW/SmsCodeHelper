package android.support.v7.internal.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.view.View.BaseSavedState;

class AbsSpinnerCompat$SavedState
  extends View.BaseSavedState
{
  public static final Parcelable.Creator<SavedState> CREATOR = new AbsSpinnerCompat.SavedState.1();
  int position;
  long selectedId;
  
  AbsSpinnerCompat$SavedState(Parcel paramParcel)
  {
    super(paramParcel);
    selectedId = paramParcel.readLong();
    position = paramParcel.readInt();
  }
  
  AbsSpinnerCompat$SavedState(Parcelable paramParcelable)
  {
    super(paramParcelable);
  }
  
  public String toString()
  {
    return "AbsSpinner.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " selectedId=" + selectedId + " position=" + position + "}";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeLong(selectedId);
    paramParcel.writeInt(position);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.AbsSpinnerCompat.SavedState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */