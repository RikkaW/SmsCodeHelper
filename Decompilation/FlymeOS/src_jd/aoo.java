import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.ted.android.contacts.netparser.model.CategoryDetailData;

public class aoo
  implements Parcelable.Creator<CategoryDetailData>
{
  public CategoryDetailData a(Parcel paramParcel)
  {
    return new CategoryDetailData(paramParcel);
  }
  
  public CategoryDetailData[] a(int paramInt)
  {
    return new CategoryDetailData[paramInt];
  }
}

/* Location:
 * Qualified Name:     aoo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */