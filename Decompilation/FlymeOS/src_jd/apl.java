import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.ted.android.contacts.netparser.model.ServiceItemModel;

public class apl
  implements Parcelable.Creator<ServiceItemModel>
{
  public ServiceItemModel a(Parcel paramParcel)
  {
    String str1 = paramParcel.readString();
    String str2 = paramParcel.readString();
    String str3 = paramParcel.readString();
    paramParcel = paramParcel.readString();
    if ((str1 != null) && (str2 != null) && (str3 != null) && (paramParcel != null)) {
      return new ServiceItemModel(str1, str2, str3, paramParcel);
    }
    return null;
  }
  
  public ServiceItemModel[] a(int paramInt)
  {
    return new ServiceItemModel[paramInt];
  }
}

/* Location:
 * Qualified Name:     apl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */