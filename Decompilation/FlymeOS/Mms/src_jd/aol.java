import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.ted.android.contacts.netparser.model.BannerDataModel;
import com.ted.android.contacts.netparser.model.BannerItemModel;

public class aol
  implements Parcelable.Creator<BannerDataModel>
{
  public BannerDataModel a(Parcel paramParcel)
  {
    String str1 = paramParcel.readString();
    String str2 = paramParcel.readString();
    paramParcel = paramParcel.createTypedArrayList(BannerItemModel.CREATOR);
    if ((str1 != null) && (str2 != null) && (paramParcel != null)) {
      return new BannerDataModel(str1, str2, paramParcel);
    }
    return null;
  }
  
  public BannerDataModel[] a(int paramInt)
  {
    return new BannerDataModel[paramInt];
  }
}

/* Location:
 * Qualified Name:     aol
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */