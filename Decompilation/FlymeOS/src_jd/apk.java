import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.ted.android.contacts.netparser.model.ServiceDataModel;
import com.ted.android.contacts.netparser.model.ServiceItemModel;

public class apk
  implements Parcelable.Creator<ServiceDataModel>
{
  public ServiceDataModel a(Parcel paramParcel)
  {
    String str1 = paramParcel.readString();
    String str2 = paramParcel.readString();
    paramParcel = paramParcel.createTypedArrayList(ServiceItemModel.CREATOR);
    if ((str1 != null) && (str2 != null) && (paramParcel != null)) {
      return new ServiceDataModel(str1, str2, paramParcel);
    }
    return null;
  }
  
  public ServiceDataModel[] a(int paramInt)
  {
    return new ServiceDataModel[paramInt];
  }
}

/* Location:
 * Qualified Name:     apk
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */