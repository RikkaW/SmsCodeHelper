import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.ted.android.contacts.netparser.model.CategoryDetailNumber;

public class aoq
  implements Parcelable.Creator<CategoryDetailNumber>
{
  public CategoryDetailNumber a(Parcel paramParcel)
  {
    return new CategoryDetailNumber(paramParcel);
  }
  
  public CategoryDetailNumber[] a(int paramInt)
  {
    return new CategoryDetailNumber[paramInt];
  }
}

/* Location:
 * Qualified Name:     aoq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */