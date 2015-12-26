package android.support.v4.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.view.View.BaseSavedState;

class NestedScrollView$SavedState
  extends View.BaseSavedState
{
  public static final Parcelable.Creator<SavedState> CREATOR = new NestedScrollView.SavedState.1();
  public int scrollPosition;
  
  public NestedScrollView$SavedState(Parcel paramParcel)
  {
    super(paramParcel);
    scrollPosition = paramParcel.readInt();
  }
  
  NestedScrollView$SavedState(Parcelable paramParcelable)
  {
    super(paramParcelable);
  }
  
  public String toString()
  {
    return "HorizontalScrollView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " scrollPosition=" + scrollPosition + "}";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(scrollPosition);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.widget.NestedScrollView.SavedState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */