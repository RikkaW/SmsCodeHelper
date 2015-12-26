import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.ted.android.contacts.netparser.model.NumItem.RelevantNumber;

public class api
  implements Parcelable.Creator<NumItem.RelevantNumber>
{
  public NumItem.RelevantNumber a(Parcel paramParcel)
  {
    return new NumItem.RelevantNumber(paramParcel, null);
  }
  
  public NumItem.RelevantNumber[] a(int paramInt)
  {
    return new NumItem.RelevantNumber[paramInt];
  }
}

/* Location:
 * Qualified Name:     api
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */