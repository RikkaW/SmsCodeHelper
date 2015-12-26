import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.ted.android.contacts.netparser.model.IndexItemModel;

public class apb
  implements Parcelable.Creator<IndexItemModel>
{
  public IndexItemModel a(Parcel paramParcel)
  {
    String str = paramParcel.readString();
    int i = paramParcel.readInt();
    int j = paramParcel.readInt();
    int k = paramParcel.readInt();
    if (str != null) {
      return new IndexItemModel(str, i, j, k);
    }
    return null;
  }
  
  public IndexItemModel[] a(int paramInt)
  {
    return new IndexItemModel[paramInt];
  }
}

/* Location:
 * Qualified Name:     apb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */