import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.ted.android.contacts.netparser.model.DottingEventDataModel;

public class aox
  implements Parcelable.Creator<DottingEventDataModel>
{
  public DottingEventDataModel a(Parcel paramParcel)
  {
    return new DottingEventDataModel(paramParcel);
  }
  
  public DottingEventDataModel[] a(int paramInt)
  {
    return new DottingEventDataModel[paramInt];
  }
}

/* Location:
 * Qualified Name:     aox
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */