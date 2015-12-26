import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.ted.android.contacts.netparser.model.CollectionDataModel;
import com.ted.android.contacts.netparser.model.CollectionItemModel;

public class aov
  implements Parcelable.Creator<CollectionDataModel>
{
  public CollectionDataModel a(Parcel paramParcel)
  {
    paramParcel = paramParcel.createTypedArrayList(CollectionItemModel.CREATOR);
    if (paramParcel != null) {
      return new CollectionDataModel(paramParcel);
    }
    return null;
  }
  
  public CollectionDataModel[] a(int paramInt)
  {
    return new CollectionDataModel[paramInt];
  }
}

/* Location:
 * Qualified Name:     aov
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */