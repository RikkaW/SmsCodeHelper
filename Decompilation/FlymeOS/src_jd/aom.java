import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.ted.android.contacts.netparser.model.BannerItemModel;

public class aom
  implements Parcelable.Creator<BannerItemModel>
{
  public BannerItemModel a(Parcel paramParcel)
  {
    return new BannerItemModel(paramParcel);
  }
  
  public BannerItemModel[] a(int paramInt)
  {
    return new BannerItemModel[paramInt];
  }
}

/* Location:
 * Qualified Name:     aom
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */