import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.ted.android.contacts.netparser.model.NearMoreModel;
import com.ted.android.contacts.netparser.model.ServiceItemModel;

public class aph
  implements Parcelable.Creator<NearMoreModel>
{
  public NearMoreModel a(Parcel paramParcel)
  {
    String str = paramParcel.readString();
    int i = paramParcel.readInt();
    paramParcel = paramParcel.createTypedArrayList(ServiceItemModel.CREATOR);
    if ((str != null) && (paramParcel != null)) {
      return new NearMoreModel(str, i, paramParcel);
    }
    return null;
  }
  
  public NearMoreModel[] a(int paramInt)
  {
    return new NearMoreModel[paramInt];
  }
}

/* Location:
 * Qualified Name:     aph
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */