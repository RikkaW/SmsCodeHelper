import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.ted.android.contacts.netparser.model.CategoryItemModel;

public class aos
  implements Parcelable.Creator<CategoryItemModel>
{
  public CategoryItemModel a(Parcel paramParcel)
  {
    String str1 = paramParcel.readString();
    String str2 = paramParcel.readString();
    String str3 = paramParcel.readString();
    String str4 = paramParcel.readString();
    String str5 = paramParcel.readString();
    paramParcel = paramParcel.readString();
    if ((str1 != null) && (str2 != null) && (str3 != null) && (str4 != null) && (str5 != null) && (paramParcel != null)) {
      return new CategoryItemModel(str1, str2, str3, str4, str5, paramParcel);
    }
    return null;
  }
  
  public CategoryItemModel[] a(int paramInt)
  {
    return new CategoryItemModel[paramInt];
  }
}

/* Location:
 * Qualified Name:     aos
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */