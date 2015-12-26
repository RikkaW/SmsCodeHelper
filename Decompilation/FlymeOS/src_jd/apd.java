import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.ted.android.contacts.netparser.model.IndexItemModel;
import com.ted.android.contacts.netparser.model.IndexListModel;

public class apd
  implements Parcelable.Creator<IndexListModel>
{
  public IndexListModel a(Parcel paramParcel)
  {
    try
    {
      int i = paramParcel.readInt();
      String str = paramParcel.readString();
      paramParcel = paramParcel.createTypedArrayList(IndexItemModel.CREATOR);
      if ((str != null) && (paramParcel != null))
      {
        paramParcel = new IndexListModel(i, str, paramParcel);
        return paramParcel;
      }
      return null;
    }
    catch (OutOfMemoryError paramParcel)
    {
      paramParcel.printStackTrace();
      return null;
    }
    catch (Exception paramParcel)
    {
      for (;;)
      {
        paramParcel.printStackTrace();
      }
    }
  }
  
  public IndexListModel[] a(int paramInt)
  {
    return new IndexListModel[paramInt];
  }
}

/* Location:
 * Qualified Name:     apd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */