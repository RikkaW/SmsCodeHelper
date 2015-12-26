import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.ted.android.contacts.netparser.model.CategoryDetailService;

public class aor
  implements Parcelable.Creator<CategoryDetailService>
{
  public CategoryDetailService a(Parcel paramParcel)
  {
    return new CategoryDetailService(paramParcel);
  }
  
  public CategoryDetailService[] a(int paramInt)
  {
    return new CategoryDetailService[paramInt];
  }
}

/* Location:
 * Qualified Name:     aor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */