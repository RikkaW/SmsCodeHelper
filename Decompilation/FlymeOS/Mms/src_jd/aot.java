import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.ted.android.contacts.netparser.model.CategoryNumberItem;

public class aot
  implements Parcelable.Creator<CategoryNumberItem>
{
  public CategoryNumberItem a(Parcel paramParcel)
  {
    String str1 = paramParcel.readString();
    String str2 = paramParcel.readString();
    paramParcel = paramParcel.readString();
    if ((str1 != null) && (str2 != null) && (paramParcel != null)) {
      return new CategoryNumberItem(str1, str2, paramParcel);
    }
    return null;
  }
  
  public CategoryNumberItem[] a(int paramInt)
  {
    return new CategoryNumberItem[paramInt];
  }
}

/* Location:
 * Qualified Name:     aot
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */