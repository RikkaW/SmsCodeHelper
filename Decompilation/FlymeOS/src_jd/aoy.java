import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.ted.android.contacts.netparser.model.DottingEventItemModel;

public class aoy
  implements Parcelable.Creator<DottingEventItemModel>
{
  public DottingEventItemModel a(Parcel paramParcel)
  {
    return new DottingEventItemModel(paramParcel);
  }
  
  public DottingEventItemModel[] a(int paramInt)
  {
    return new DottingEventItemModel[paramInt];
  }
}

/* Location:
 * Qualified Name:     aoy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */