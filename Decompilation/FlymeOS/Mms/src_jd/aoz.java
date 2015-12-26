import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.ted.android.contacts.netparser.model.HistoryDataModel;
import com.ted.android.contacts.netparser.model.HistoryItemModel;

public class aoz
  implements Parcelable.Creator<HistoryDataModel>
{
  public HistoryDataModel a(Parcel paramParcel)
  {
    paramParcel = paramParcel.createTypedArrayList(HistoryItemModel.CREATOR);
    if (paramParcel != null) {
      return new HistoryDataModel(paramParcel);
    }
    return null;
  }
  
  public HistoryDataModel[] a(int paramInt)
  {
    return new HistoryDataModel[paramInt];
  }
}

/* Location:
 * Qualified Name:     aoz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */