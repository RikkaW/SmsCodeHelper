import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.ted.android.contacts.netparser.model.CollectionItemModel;

public class aow
  implements Parcelable.Creator<CollectionItemModel>
{
  public CollectionItemModel a(Parcel paramParcel)
  {
    String str1 = paramParcel.readString();
    String str2 = paramParcel.readString();
    String str3 = paramParcel.readString();
    String str4 = paramParcel.readString();
    paramParcel = paramParcel.readString();
    if ((str1 != null) && (str2 != null) && (str3 != null) && (str4 != null) && (paramParcel != null)) {
      return new CollectionItemModel(str1, str2, str3, str4, paramParcel);
    }
    return null;
  }
  
  public CollectionItemModel[] a(int paramInt)
  {
    return new CollectionItemModel[paramInt];
  }
}

/* Location:
 * Qualified Name:     aow
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */