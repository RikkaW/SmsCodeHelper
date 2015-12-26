import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.ted.android.contacts.netparser.model.NearItemModel;

public class apg
  implements Parcelable.Creator<NearItemModel>
{
  public NearItemModel a(Parcel paramParcel)
  {
    String str1 = paramParcel.readString();
    String str2 = paramParcel.readString();
    String str3 = paramParcel.readString();
    paramParcel = paramParcel.readString();
    if ((str1 != null) && (str2 != null) && (str3 != null) && (paramParcel != null)) {
      return new NearItemModel(str1, str2, str3, paramParcel);
    }
    return null;
  }
  
  public NearItemModel[] a(int paramInt)
  {
    return new NearItemModel[paramInt];
  }
}

/* Location:
 * Qualified Name:     apg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */