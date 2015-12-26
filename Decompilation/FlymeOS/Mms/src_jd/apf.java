import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.ted.android.contacts.netparser.model.NearDataModel;
import com.ted.android.contacts.netparser.model.NearItemModel;
import com.ted.android.contacts.netparser.model.NearMoreModel;
import java.util.ArrayList;

public class apf
  implements Parcelable.Creator<NearDataModel>
{
  public NearDataModel a(Parcel paramParcel)
  {
    String str1 = paramParcel.readString();
    String str2 = paramParcel.readString();
    ArrayList localArrayList = paramParcel.createTypedArrayList(NearItemModel.CREATOR);
    paramParcel = paramParcel.createTypedArrayList(NearMoreModel.CREATOR);
    if ((str1 != null) && (str2 != null) && (localArrayList != null) && (paramParcel != null)) {
      return new NearDataModel(str1, str2, localArrayList, paramParcel);
    }
    return null;
  }
  
  public NearDataModel[] a(int paramInt)
  {
    return new NearDataModel[paramInt];
  }
}

/* Location:
 * Qualified Name:     apf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */