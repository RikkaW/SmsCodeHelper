import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.ted.android.contacts.netparser.model.CategoryServiceItem;

public class aou
  implements Parcelable.Creator<CategoryServiceItem>
{
  public CategoryServiceItem a(Parcel paramParcel)
  {
    String str1 = paramParcel.readString();
    String str2 = paramParcel.readString();
    String str3 = paramParcel.readString();
    String str4 = paramParcel.readString();
    paramParcel = paramParcel.readString();
    if ((str1 != null) && (str2 != null) && (str3 != null) && (str4 != null) && (paramParcel != null)) {
      return new CategoryServiceItem(str1, str2, str3, str4, paramParcel);
    }
    return null;
  }
  
  public CategoryServiceItem[] a(int paramInt)
  {
    return new CategoryServiceItem[paramInt];
  }
}

/* Location:
 * Qualified Name:     aou
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */