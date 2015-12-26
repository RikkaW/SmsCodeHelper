import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.ted.android.contacts.netparser.model.CategoryDataModel;

public class aon
  implements Parcelable.Creator<CategoryDataModel>
{
  public CategoryDataModel a(Parcel paramParcel)
  {
    return new CategoryDataModel(paramParcel);
  }
  
  public CategoryDataModel[] a(int paramInt)
  {
    return new CategoryDataModel[paramInt];
  }
}

/* Location:
 * Qualified Name:     aon
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */