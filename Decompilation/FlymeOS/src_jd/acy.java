import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.android.mms.view.CustomTimePicker.SavedState;

public final class acy
  implements Parcelable.Creator<CustomTimePicker.SavedState>
{
  public CustomTimePicker.SavedState a(Parcel paramParcel)
  {
    return new CustomTimePicker.SavedState(paramParcel, null);
  }
  
  public CustomTimePicker.SavedState[] a(int paramInt)
  {
    return new CustomTimePicker.SavedState[paramInt];
  }
}

/* Location:
 * Qualified Name:     acy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */