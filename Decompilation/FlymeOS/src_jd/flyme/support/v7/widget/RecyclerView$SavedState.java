package flyme.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.view.View.BaseSavedState;
import arp;

public class RecyclerView$SavedState
  extends View.BaseSavedState
{
  public static final Parcelable.Creator<SavedState> CREATOR = new arp();
  Parcelable a;
  
  public RecyclerView$SavedState(Parcel paramParcel)
  {
    super(paramParcel);
    a = paramParcel.readParcelable(RecyclerView.h.class.getClassLoader());
  }
  
  RecyclerView$SavedState(Parcelable paramParcelable)
  {
    super(paramParcelable);
  }
  
  private void a(SavedState paramSavedState)
  {
    a = a;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeParcelable(a, 0);
  }
}

/* Location:
 * Qualified Name:     flyme.support.v7.widget.RecyclerView.SavedState
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */