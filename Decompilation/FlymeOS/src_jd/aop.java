import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.ted.android.contacts.netparser.model.CategoryDetailModel;

public class aop
  implements Parcelable.Creator<CategoryDetailModel>
{
  public CategoryDetailModel a(Parcel paramParcel)
  {
    return new CategoryDetailModel(paramParcel);
  }
  
  public CategoryDetailModel[] a(int paramInt)
  {
    return new CategoryDetailModel[paramInt];
  }
}

/* Location:
 * Qualified Name:     aop
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */