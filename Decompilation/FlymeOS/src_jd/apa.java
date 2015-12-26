import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.ted.android.contacts.netparser.model.HistoryItemModel;

public class apa
  implements Parcelable.Creator<HistoryItemModel>
{
  public HistoryItemModel a(Parcel paramParcel)
  {
    return new HistoryItemModel(paramParcel);
  }
  
  public HistoryItemModel[] a(int paramInt)
  {
    return new HistoryItemModel[paramInt];
  }
}

/* Location:
 * Qualified Name:     apa
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */