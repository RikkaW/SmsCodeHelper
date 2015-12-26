import android.os.Parcel;
import android.os.Parcelable.Creator;
import flyme.support.v7.widget.MzRecyclerView.MZSavedState;

public final class arh
  implements Parcelable.Creator<MzRecyclerView.MZSavedState>
{
  public MzRecyclerView.MZSavedState a(Parcel paramParcel)
  {
    return new MzRecyclerView.MZSavedState(paramParcel, null);
  }
  
  public MzRecyclerView.MZSavedState[] a(int paramInt)
  {
    return new MzRecyclerView.MZSavedState[paramInt];
  }
}

/* Location:
 * Qualified Name:     arh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */