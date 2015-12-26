import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.meizu.update.iresponse.MzUpdateResponse;

public final class amu
  implements Parcelable.Creator<MzUpdateResponse>
{
  public MzUpdateResponse a(Parcel paramParcel)
  {
    return new MzUpdateResponse(paramParcel);
  }
  
  public MzUpdateResponse[] a(int paramInt)
  {
    return new MzUpdateResponse[paramInt];
  }
}

/* Location:
 * Qualified Name:     amu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */